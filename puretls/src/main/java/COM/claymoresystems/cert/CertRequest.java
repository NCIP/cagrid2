/**
   CertRequest.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sat Jul 24 19:06:19 1999
*/

package COM.claymoresystems.cert;

import COM.claymoresystems.util.Util;
import COM.claymoresystems.ptls.LoadProviders;
import COM.claymoresystems.crypto.EAYEncryptedPrivateKey;
import COM.claymoresystems.cert.EAYDSAPrivateKey;
import COM.claymoresystems.cert.DERUtils;
import COM.claymoresystems.cert.X509DSAPublicKey;
import COM.claymoresystems.cert.X509RSAPublicKey;
import java.io.*;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.interfaces.DSAKeyPairGenerator;
import java.security.SecureRandom;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.util.Vector;
import xjava.security.interfaces.CryptixRSAPublicKey;
import xjava.security.interfaces.CryptixRSAPrivateKey;
import java.security.Signature;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import cryptix.util.mime.Base64OutputStream;

/** Generate various kinds of certificate signing requests.
    <p>
    The three main interfaces are<br>
    <code>
    makeSPKACRequest()
    <br>
    makePKCS10Request()
    <br>
    makeSelfSignedCert()
    </code>
    <p>
    You can access these in a primitive fashion through the
    <code>main()</main> function but this gives you a mostly
    hardcoded DN with PKCS10 and self-signed (you can choose
    the CN). <code>makeSimpleDN()</code> offers a way to construct
    a fairly simple DN from a simpler construct than the standard
    X509Name. At some point we may expose simpler functionality
    at the command line.
*/
public class CertRequest {
     static {
       LoadProviders.init();
     }

     /** Generate a key pair

         @param type DSA or RSA
         @param size the length
         @param password the password to use to encrypted the key
         @param keyfile the keyfile to store the key in
         @param newParams generate new parameters if using DSA--by default Sun uses fixed precomputed params
         @return the keypair

         @exception NoSuchAlgorithmException if you choose a key we don't know about
         @exception NoSuchProviderException internal errors
         @exception IOException encoding errors
     */
     public static KeyPair generateKey(String type,int size,
       String password,BufferedWriter keyfile,boolean newParams) 
       throws NoSuchAlgorithmException, NoSuchProviderException, IOException {
       PrivateKey priv;
       
       KeyPairGenerator kg;
       
       if(type.equals("DSA")){
         kg=KeyPairGenerator.getInstance(type);
         DSAKeyPairGenerator dsaGen=(DSAKeyPairGenerator)kg;
         dsaGen.initialize(size,newParams,new SecureRandom());
       }
       else{
         kg=KeyPairGenerator.getInstance(type,"Cryptix");         
         kg.initialize(size);
       }
       KeyPair pair=kg.generateKeyPair();

       // We need to map the DSA keys given us by Sun into
       // our own DSA keys because Sun gives us a bogus
       // DER encoding
       if(type.equals("DSA")){
	 priv=new EAYDSAPrivateKey((DSAPrivateKey)pair.getPrivate());
       }
       else {
	 priv=new X509RSAPrivateKey((CryptixRSAPrivateKey)pair.getPrivate());
       }
       
       EAYEncryptedPrivateKey.writePrivateKey(priv,password.getBytes(),keyfile);
       return pair;
     }

     /** Make a Netscape Signed Public Key and Cert request

      @param p the keypair to make it with
      @return the SPKAC as a bytestring
      @exception IOException for errors */
     public static byte[] makeSPKACRequest(KeyPair p)
       throws IOException {
       PrivateKey priv=p.getPrivate();
       PublicKey pub=p.getPublic();
       String alg=priv.getAlgorithm();
       byte[] DSA_algid={(byte)0x30,(byte)0x0b,(byte)0x06,(byte)0x07,(byte)0x2a,
			 (byte)0x86,(byte)0x48,(byte)0xce,(byte)0x38,(byte)0x04,
			 (byte)0x03,(byte)0x05,(byte)0x00};		
       byte[] RSA_algid={(byte)0x30,(byte)0x0d,(byte)0x06,(byte)0x09,
                         (byte)0x2a,(byte)0x86,(byte)0x48,(byte)0x86,(byte)0xf7,
                         (byte)0x0d,(byte)0x01,(byte)0x01,(byte)0x05,(byte)0x05,
                         (byte)0x00};
       
       // We need to map the keys given us by Sun or Cryptix into
       // our own DSA keys because they gives us a bogus
       // DER encoding (or none at all)
       byte[] algId;
       String algorithm;
       
       if(alg.equals("DSA")){
         algId=DSA_algid;
         algorithm="DSA";
	 pub=new X509DSAPublicKey((DSAPublicKey)pub);
       }
       else if(alg.equals("RSA")){
         algId=RSA_algid;
         algorithm="SHA-1/RSA/PKCS#1";
         pub=new X509RSAPublicKey((CryptixRSAPublicKey)pub);
       }
       else throw new InternalError("Unknown algorithm "+alg);

       byte[] key_enc=pub.getEncoded(); // SPKI

       ByteArrayOutputStream os=new ByteArrayOutputStream();
       os.write(key_enc);
       DERUtils.encodeIA5String("Challenge",os);
       byte[] tmp=os.toByteArray();
       os.reset();
       DERUtils.encodeSequence(tmp,os);

       // At this point we have the public key and challenge sequence
       byte[] toBeSigned=os.toByteArray();
       byte[] signature;
       
       try {
	 Signature sig=Signature.getInstance(algorithm);
	 sig.initSign(priv);

	 sig.update(toBeSigned);
	 signature=sig.sign();
       } catch (Exception e){
	 throw new InternalError(e.toString());
       }

       
       os.reset();
       os.write(toBeSigned);
       os.write(algId);

       // Make the signature the right size
       if(alg.equals("RSA")){
         signature=fitSignature(signature,pub);
       }
       DERUtils.encodeBitString(signature,os);


       byte[] spkac_c=os.toByteArray();
       // Now we've got the SPKAC as a byte array, but we need to wrap it in a sequence
       os.reset();
       DERUtils.encodeSequence(spkac_c,os);

       byte[] spkac=os.toByteArray();
       
       return spkac;
     }

     /** Make a PKCS10 CSR

      @param p the keypair to make it with
      @param name the subject name as an X509Name
      
      @return the CSR as a bytestring
      @exception IOException for errors */
     public static byte[] makePKCS10Request(KeyPair p,X509Name name)
       throws IOException {
       PrivateKey priv=p.getPrivate();
       PublicKey pub=p.getPublic();
       String alg=priv.getAlgorithm();
       byte[] DSA_algid={(byte)0x30,(byte)0x0b,(byte)0x06,(byte)0x07,(byte)0x2a,
			 (byte)0x86,(byte)0x48,(byte)0xce,(byte)0x38,(byte)0x04,
			 (byte)0x03,(byte)0x05,(byte)0x00};		
       byte[] RSA_algid={(byte)0x30,(byte)0x0d,(byte)0x06,(byte)0x09,
                         (byte)0x2a,(byte)0x86,(byte)0x48,(byte)0x86,(byte)0xf7,
                         (byte)0x0d,(byte)0x01,(byte)0x01,(byte)0x05,(byte)0x05,
                         (byte)0x00};
       
       // We need to map the keys given us by Sun or Cryptix into
       // our own DSA keys because they gives us a bogus
       // DER encoding (or none at all)
       byte[] algId;
       String algorithm;
       
       if(alg.equals("DSA")){
         algId=DSA_algid;
         algorithm="DSA";
	 pub=new X509DSAPublicKey((DSAPublicKey)pub);
       }
       else if(alg.equals("RSA")){
         algId=RSA_algid;
         algorithm="SHA-1/RSA/PKCS#1";
         pub=new X509RSAPublicKey((CryptixRSAPublicKey)pub);
       }
       else throw new InternalError("Unknown algorithm "+alg);

       byte[] key_enc=pub.getEncoded(); // SPKI

       ByteArrayOutputStream os=new ByteArrayOutputStream();
       DERUtils.encodeInteger(new BigInteger("0"),os); // Version
       os.write(name.getNameDER());  // DN
       os.write(key_enc);	     // SPKI

       byte[] tmp=os.toByteArray();
       os.reset();
       DERUtils.encodeSequence(tmp,os);

       // At this point we have the inner content to be signed
       byte[] toBeSigned=os.toByteArray();
       byte[] signature;
       
       try {
	 Signature sig=Signature.getInstance(algorithm);
	 sig.initSign(priv);

	 sig.update(toBeSigned);
	 signature=sig.sign();
       } catch (Exception e){
	 throw new InternalError(e.toString());
       }

       os.reset();
       os.write(toBeSigned);
       os.write(algId);

       // Make the signature the right size
       if(alg.equals("RSA")){
         signature=fitSignature(signature,pub);
       }
       DERUtils.encodeBitString(signature,os);
       
       byte[] pkcs10_c=os.toByteArray();
       // Now we've got the PKCS10 as a byte array, but we need to wrap it in a sequence
       os.reset();
       DERUtils.encodeSequence(pkcs10_c,os);

       byte[] pkcs10=os.toByteArray();
       
       return pkcs10;
     }

     /** Make a Self-signed cert

      @param p the keypair to make it with
      @param the name to use
      @param the lifetime in seconds
      
      @return the CSR as a bytestring
      @exception IOException for errors */
     public static byte[] makeSelfSignedCert(KeyPair p,X509Name name,int lifetime)
       throws IOException {
       PrivateKey priv=p.getPrivate();
       PublicKey pub=p.getPublic();
       String alg=priv.getAlgorithm();
       byte[] DSA_algid={(byte)0x30,(byte)0x0b,(byte)0x06,(byte)0x07,(byte)0x2a,
			 (byte)0x86,(byte)0x48,(byte)0xce,(byte)0x38,(byte)0x04,
			 (byte)0x03,(byte)0x05,(byte)0x00};		
       byte[] RSA_algid={(byte)0x30,(byte)0x0d,(byte)0x06,(byte)0x09,
                         (byte)0x2a,(byte)0x86,(byte)0x48,(byte)0x86,(byte)0xf7,
                         (byte)0x0d,(byte)0x01,(byte)0x01,(byte)0x05,(byte)0x05,
                         (byte)0x00};
       
       // We need to map the keys given us by Sun or Cryptix into
       // our own DSA keys because they gives us a bogus
       // DER encoding (or none at all)
       byte[] algId;
       String algorithm;
       
       if(alg.equals("DSA")){
         algId=DSA_algid;
         algorithm="DSA";
	 pub=new X509DSAPublicKey((DSAPublicKey)pub);
       }
       else if(alg.equals("RSA")){
         algId=RSA_algid;
         algorithm="SHA-1/RSA/PKCS#1";
         pub=new X509RSAPublicKey((CryptixRSAPublicKey)pub);
       }
       else throw new InternalError("Unknown algorithm "+alg);

       byte[] key_enc=pub.getEncoded(); // SPKI

       ByteArrayOutputStream os=new ByteArrayOutputStream();
       // Omit version to default to 0 (version 1)
       DERUtils.encodeInteger(new BigInteger("0"),os); // Serial
       os.write(algId);                                // signature alg
       os.write(name.getNameDER());  // Issuer

       // Validity
       ByteArrayOutputStream os2=new ByteArrayOutputStream();
       long notBefore=System.currentTimeMillis();
       long increment=lifetime;
       long notAfter=notBefore+(increment*1000);

       DERUtils.encodeUTCTime(notBefore,os2);
       DERUtils.encodeUTCTime(notAfter,os2);
       DERUtils.encodeSequence(os2,os);              

       os.write(name.getNameDER());      // Subject
       os.write(key_enc);	     // SPKI

       byte[] tmp=os.toByteArray();
       os.reset();
       DERUtils.encodeSequence(tmp,os);

       // At this point we have the inner content to be signed
       byte[] toBeSigned=os.toByteArray();
       byte[] signature;
       
       try {
	 Signature sig=Signature.getInstance(algorithm);
	 sig.initSign(priv);

	 sig.update(toBeSigned);
	 signature=sig.sign();
       } catch (Exception e){
	 throw new InternalError(e.toString());
       }

       os.reset();
       os.write(toBeSigned);
       os.write(algId);


       // Make the signature the right size
       if(alg.equals("RSA")){
         signature=fitSignature(signature,pub);
       }
       DERUtils.encodeBitString(signature,os);
       
       byte[] cert_c=os.toByteArray();
       // Now we've got the CERT as a byte array, but we need to wrap it in a sequence
       os.reset();
       DERUtils.encodeSequence(cert_c,os);

       byte[] cert=os.toByteArray();
       
       return cert;
     }
       
     /* Make a DN from a vector of AVAs. This assumes
        one AVA per RDN */
     public static X509Name makeSimpleDN(Vector rdns){
       Vector dn=new Vector();

       for(int i=0;i<rdns.size();i++){
         String[] ava=(String [])rdns.elementAt(i);
         Vector nrdn=new Vector();
         String[] nava=new String[2];

         nava[0]=new String(ava[0]);
         nava[1]=new String(ava[1]);

         nrdn.addElement(nava);
         dn.addElement(nrdn);
       }

       return new X509Name(dn);
     }

     protected static byte[] fitSignature(byte[] tmp,PublicKey pub){
       CryptixRSAPublicKey rsa=(CryptixRSAPublicKey)pub;
       int bitLength=rsa.getModulus().bitLength();
       int length=bitLength/8;
       length+=(bitLength%8>0)?1:0;
         
       int i;
       
       // Ensure that this is the right length
       if(tmp.length==length)
         return tmp;

       // If it's too short, pad with zeros on the right
       // If it's too long, remove zeros from the left.
       // if it's too long without zeros, throw an error.
       byte[] ntmp=new byte[length];
         
       if(tmp.length<length){

         for(i=0;i<(length-tmp.length);i++){
           ntmp[i]=0;
         }
         System.arraycopy(tmp,0,ntmp,i,tmp.length);
       }
       else {
         for(i=0;i<(tmp.length-length);i++){
           if(tmp[i]!=0)
             throw new InternalError("RSA signature error");
         }
         System.arraycopy(tmp,i,ntmp,0,length);
       }
       return ntmp;
     }
     
     private static X509Name makeSuperSimpleDN(String CN) {
       Vector tdn=new Vector();
       String[] c=new String[2];
       String[] o=new String[2];
       String[] ou=new String[2];
       String[] cn=new String[2];

       c[0]="C";
       c[1]="US";
       o[0]="O";
       o[1]="Snake Oil, Inc.";
       ou[0]="OU";
       ou[1]="Test";
       cn[0]="CN";
       cn[1]=CN;

       tdn.addElement(c);
       tdn.addElement(o);
       tdn.addElement(ou);
       tdn.addElement(cn);

       return makeSimpleDN(tdn);
     }
       
            
     public static void main(String[] args)
       throws IOException, Exception {
       String keyFileName=args[0];
       String keyType;
       String cn=null;
       int type=0;
       
       if(args.length>=2){
         keyType=args[1];
       }
       else
         keyType="DSA";

       if(args.length>=3){
         if(args[2].equals("SPKAC"))
           type=0;
         else if (args[2].equals("PKCS10"))
           type=1;
         else if (args[2].equals("X509"))
           type=2;
         else
           throw new InternalError("Unknown type "+args[2]);
       }

       if(type==1 || type==2){
         if(args.length!=4)
           throw new InternalError("Must supply common name for type"+type);
         cn=args[3];
       }

       int size=1024;

       InputStreamReader ir=new InputStreamReader(System.in);
       BufferedReader br=new BufferedReader(ir);

       String password=br.readLine();
       
       FileWriter fw=new FileWriter(keyFileName);
       BufferedWriter bw=new BufferedWriter(fw);

       KeyPair kp=generateKey(keyType,size,password,bw,true);
       byte[] req;
       
       switch(type){
         case 0:
           req=makeSPKACRequest(kp);
           break;
         case 1:
           req=makePKCS10Request(kp,makeSuperSimpleDN(cn));
           break;
         case 2:
           req=makeSelfSignedCert(kp,makeSuperSimpleDN(cn),30000000); // 30Msec is almost a year
           break;
         default:
           throw new InternalError("Bad type");
       }
             
       ByteArrayOutputStream bos=new ByteArrayOutputStream();
       Base64OutputStream b64os=new Base64OutputStream(bos);
       b64os.write(req);
       b64os.flush();
       b64os.close();
       
       ByteArrayInputStream bis=new ByteArrayInputStream(
	    bos.toByteArray());
       InputStreamReader irr=new InputStreamReader(bis);
       BufferedReader r=new BufferedReader(irr);

       String line;

       System.out.println(password);
       switch(type){
         case 1:
           System.out.println("-----BEGIN CERTIFICATE REQUEST-----");
           break;
         case 2:
           System.out.println("-----BEGIN CERTIFICATE-----");
           break;
       }
       while((line=r.readLine())!=null){
	 System.out.println(line);
       }
       switch(type){
         case 1:
           System.out.println("-----END CERTIFICATE REQUEST-----");
           break;
         case 2:
           System.out.println("-----END CERTIFICATE-----");
           break;
       }
     }
}
     

