/**
   SSLContext.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue May 18 09:43:47 1999

   This package is a SSLv3/TLS implementation written by Eric Rescorla
   <ekr@rtfm.com> and licensed by Claymore Systems, Inc.

   Redistribution and use in source and binary forms, with or without
   modification, are permitted provided that the following conditions
   are met:
   1. Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
   2. Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
   3. All advertising materials mentioning features or use of this software
      must display the following acknowledgement:
      This product includes software developed by Claymore Systems, Inc.
   4. Neither the name of Claymore Systems, Inc. nor the name of Eric
      Rescorla may be used to endorse or promote products derived from this
      software without specific prior written permission.

   THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND
   ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
   IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
   ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE
   FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
   DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
   OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
   HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
   LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
   OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
   SUCH DAMAGE.

   $Id: SSLContext.java,v 1.2 2003/05/10 05:44:46 gawor Exp $

*/

package COM.claymoresystems.ptls;
import COM.claymoresystems.sslg.*;
import COM.claymoresystems.cert.WrappedObject;
import COM.claymoresystems.cert.EAYDHParams;
import COM.claymoresystems.cert.X509Cert;
import COM.claymoresystems.crypto.EAYEncryptedPrivateKey;
import COM.claymoresystems.crypto.RandomStore;
import COM.claymoresystems.crypto.DHPrivateKey;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import xjava.security.interfaces.CryptixRSAPrivateKey;
import xjava.security.interfaces.CryptixRSAPublicKey;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
import java.io.*;

import java.security.Security;

/** SSLContext holds all state relevant to more than one SSL Session/
    Connection. In particular, it's the access point for user keying
    material, user policy settings, and the session cache.
    <P>
    For clients, it is legal to merely create an SSLContext and
    use it immediately, though it is advisable to set the SSLPolicy.
    <P>
    Since servers must have keying material to operate, all SSLContexts
    which are to be used for servers must be initialized using
    loadEAYKeyFile() or loadPKS12KeyFile().
    <P>
    Currently, SSLContext supports only one key at a time and
    loading a new keyfile overrides any exiting keys/certificates.
    Future implementations may support multiple keys automatically
    selected based on the cipherSuite.
*/
public class SSLContext extends SSLContextInt
{
     private final static int SEED_BYTES=128;
     
     private Hashtable session_cache=new Hashtable();
     Vector root_list=new Vector();
     Vector certificates=null;
     PrivateKey privateKey=null;
     PublicKey publicKey=null;
     private int ephemeralDHKeyLength=1024;     
     DHPrivateKey dhEphemeral=null;
     EAYDHParams dhParams=null;
     KeyPair rsaEphemeral=null;
     SecureRandom rng=null;
     private boolean sophieGermain=false;
     
     static {
            LoadProviders.init();
     }
     
    public void setPrivateKey(PrivateKey key) {
	privateKey = key;
    }
    
    public void setPublicKey(PublicKey key) {
	publicKey = key;
    }

    public void setPublicKeyFromCert(byte [] certData)
	throws IOException {
	// Now extract the public key
	X509Cert cert=new X509Cert(certData);
	publicKey=cert.getPublicKey(); 
    }

    public void setRootList(Vector rootCerts) {
	root_list = rootCerts;
    }

    public void setCertificateChain(Vector chain) {
	certificates = chain;
    }

     /** Build new RNG based on the indicated seed, or
	 update current RNG

	 @param seed the seed
      */
     public void seedRNG(byte [] seed){
       
       if(seed==null){
         // This is a dummy to let us do fast testing
	 seed=new byte[0];
       }

       if(rng==null)
	 rng=new SecureRandom(seed);
       else
	 rng.setSeed(seed);

       // Differentiate RNG streams even if
       // we seeded with null
       rng.setSeed(System.currentTimeMillis());
     }


     /** use the indicated file for randomness
	 If the file does not exist, it is created.
	 
	 @param path the file name
	 @param passphrase the passphrase needed to decrypt/verify the keyfile

	 @exception IOException if something goes wrong
	 @exception FileNotFoundException if we're unable to update the file
     */
     public void useRandomnessFile(String file, String passphrase)
       throws IOException,FileNotFoundException {
       rng=null;

       try {
	 rng=RandomStore.readRandomStore(file,passphrase.getBytes());
       } catch (FileNotFoundException e){
	 ;
       }

       if(rng==null){
	 rng=new SecureRandom();
	 RandomStore.writeRandomStore(file,passphrase.getBytes(),rng);
       }
     }
     
     /** Load keying material from the indicated PKCS12/PFX keyfile,
	 using the passphrase passed in

	 @param path the filename for the keyfile
	 @param passphrase the passphrase needed to decrypt/verify the keyfile

	 <B>Currently not implemented</B>
     */
     public void loadPKCS12File(String path, String passphrase){
       throw new InternalError("Not implemented");
     }

     /** Load a subset of SSLeay keyfiles.
	 <P>
	 We assume that the first key is bound to the first group
	 of certificates
	 <P>
	 We assume that any certificates we find are strictly ordered
	 from the user's certificate to the root.

	 @param path the filename for the fiel
	 @param passphrase the passphrase needed to decrypt the private key

	 @exception IOException if the keyfile is badly formatted
	 @exception FileNotFoundException if the keyfile doesn't exist
     */
     public void loadEAYKeyFile(String path, String passphrase)
       throws FileNotFoundException,IOException {
       FileInputStream fis=new FileInputStream(path);
       loadEAYKeyFile(fis,passphrase);
     }
       
     /** Load a subset of SSLeay keyfiles.
	 <P>
	 We assume that the first key is bound to the first group
	 of certificates
	 <P>
	 We assume that any certificates we find are strictly ordered
	 from the user's certificate to the root.

	 @param is the file
	 @param passphrase the passphrase needed to decrypt the private key

	 @exception IOException if the keyfile is badly formatted
	 @exception FileNotFoundException if the keyfile doesn't exist
     */
     public void loadEAYKeyFile(InputStream is, String passphrase)
       throws IOException {

       // first, we copy this into a bytearray so we can open it twice
       byte[] blk=new byte[1024];
       int r;
       ByteArrayOutputStream tos=new ByteArrayOutputStream();
       
       while((r=is.read(blk))>0){
	 tos.write(blk,0,r);
       }
       byte[] tmp=tos.toByteArray();
       
       ByteArrayInputStream tis=new ByteArrayInputStream(tmp);

       // Now read it
       BufferedReader br=new BufferedReader(new InputStreamReader(tis));

       PrivateKey tmpPrivateKey;
       StringBuffer keyType=new StringBuffer();

       SSLDebug.debug(SSLDebug.DEBUG_INIT,"Loading key file");

       if(!WrappedObject.findObject(br,"PRIVATE KEY",keyType))
	 throw new IOException("Couldn't find private key in this file");
       
       try {
	 tmpPrivateKey=EAYEncryptedPrivateKey.createPrivateKey(br,
	   keyType.toString(),passphrase.getBytes());
       } catch (IllegalArgumentException e){
	 throw new IOException(e.toString());
       }

       // Now reopen the file to get certs
       tis=new ByteArrayInputStream(tmp);
       br=new BufferedReader(new InputStreamReader(tis));

       Vector certs=new Vector();
       
       for(;;){
	 byte[] cert=WrappedObject.loadObject(br,"CERTIFICATE",null);

	 if(cert==null)
	   break;
	 SSLDebug.debug(SSLDebug.DEBUG_INIT,"Loading certificate",cert);	 
	 certs.insertElementAt((Object)cert,0);
       }

       // Enforce a minimum of one certificate
       if(certs.size()==0)
         throw new IOException("Need at least one certificate");

       // Now extract the public key
       X509Cert cert=new X509Cert((byte[])certs.elementAt(0));

       publicKey=cert.getPublicKey(); // Some genius made private keys
                                      // not contain the public key
       privateKey=tmpPrivateKey;
       certificates=certs;
     }

     public void saveEAYKeyFile(String path, String passphrase)
       throws IOException, FileNotFoundException {
       FileOutputStream fos=new FileOutputStream(path);
       OutputStreamWriter fw=new OutputStreamWriter(fos);
       BufferedWriter bw=new BufferedWriter(fw);

       EAYEncryptedPrivateKey.writePrivateKey(privateKey,passphrase.getBytes(),
	 bw);
       
       for(int i=1;i<=certificates.size();i++){
	 byte[] cert=(byte[])certificates.elementAt(certificates.size()-i);

	 WrappedObject.writeHeader("CERTIFICATE",bw);
	 WrappedObject.writeObject(cert,"CERTIFICATE",bw);
       }

       bw.flush();
       fos.close();
     }

     /** Load a list of acceptable roots.
	 <P>
	 Roots are not used for verifying the keys found in the
	 keyfile. They are only used for verifying the certificates
	 of peer entities.
	 <P>
	 Roots are formatted in SSLeay "PEM" style

	 @param path the filename containing the root list
     */
     public void loadRootCertificates(String path)
       throws FileNotFoundException,IOException {
       FileInputStream fis=new FileInputStream(path);
       loadRootCertificates(fis);
       fis.close();
     }

     /** Load a list of acceptable roots.
	 <P>
	 Roots are not used for verifying the keys found in the
	 keyfile. They are only used for verifying the certificates
	 of peer entities.
	 <P>
	 Roots are formatted in SSLeay "PEM" style

	 @param path the filename containing the root list
     */
     // Root certificates come in a file list bracketed by
     // -----BEGIN CERTIFICATE-----
     // -----END CERTIFICATE-----
     public void loadRootCertificates(InputStream is)
       throws java.io.IOException {
       BufferedReader br=new BufferedReader(new InputStreamReader(is));

       for(;;){
	 byte[] root=WrappedObject.loadObject(br,"CERTIFICATE",null);

	 if(root==null)
	   break;
	   
	 SSLDebug.debug(SSLDebug.DEBUG_INIT,"Loading root",root);
	 root_list.addElement((Object)root);
       }
     }

     /** Load the DH parameters structure from a file

	 @param path the file
     */
     public void loadDHParams(String path)
       throws FileNotFoundException, IOException {
       FileInputStream fis=new FileInputStream(path);
       loadDHParams(fis);
       fis.close();
     }
       
     /** Load a DH parameters structure from disk.
	 This saves the time consuming prime generation phase

	 @param is the params file
     */
     public void loadDHParams(InputStream is)
       throws java.io.IOException {
       BufferedReader br=new BufferedReader(new InputStreamReader(is));

       byte[] dh=WrappedObject.loadObject(br,"DH PARAMETERS",null);

       if(dh==null)
	 return;

       SSLDebug.debug(SSLDebug.DEBUG_INIT,"Loading DH params",dh);
       dhParams=new EAYDHParams(dh);
     }

     /** Save DH parameters to disk, generating them if necessary

      @param path the file to save to
      @param sophieGermainPrimes generate sophieGermainPrimes (VERY slow)
     */
     
     public void saveDHParams(String path,int size,boolean sophieGermainPrimes)
       throws java.io.IOException,FileNotFoundException {
       
       DHPrivateKey eph=getEphemeralDHPrivateKey(size,sophieGermainPrimes,true);
       EAYDHParams par=new EAYDHParams(eph.getg(),
	 eph.getp());
       byte[] dhEnc=par.getEncoded();
       
       FileOutputStream fos=new FileOutputStream(path);
       OutputStreamWriter fw=new OutputStreamWriter(fos);
       BufferedWriter bw=new BufferedWriter(fw);
       WrappedObject.writeHeader("DH PARAMETERS",bw);
       WrappedObject.writeObject(dhEnc,"DH PARAMETERS",bw);
       bw.flush();
       fw.flush();
       fos.close();
     }
     // Everything from here on in is internal
     
     /** return the root list

	 @return a Vector of byte[]
     */
    public Vector getRootList(){
       return root_list;
     }

     /** return our certificate list (corresponds to our one private key)

	 @return a Vector of byte[]
     */
     Vector getCertificateChain(){
       return certificates;
     }

     /** return our private key

	 @return a PrivateKey
     */
     PrivateKey getPrivateKey(){
       return privateKey;
     }

     /** return our public key

	 @return a PublicKey
     */
     PublicKey getPublicKey(){
       return publicKey;
     }
     
    
     DHPrivateKey getEphemeralDHPrivateKey(boolean alwaysgenerate)
       throws IOException {
       if(dhParams==null)
         throw new IOException("Must install DH parameters");
       return getEphemeralDHPrivateKey(ephemeralDHKeyLength,sophieGermain,alwaysgenerate);
     }
     
     synchronized private DHPrivateKey getEphemeralDHPrivateKey(int size,boolean
       strongp,boolean alwaysgenerate){
       
       seedRNG();
       
       if((dhEphemeral==null) || (alwaysgenerate) ){
	 dhEphemeral=DHPrivateKey.getInstance();
	 if(dhParams==null){
           throw new InternalError("Can't generate ephemeral key without setting DH params");
	 }
	 else {
	   dhEphemeral.initPrivateKey(dhParams.getG(),
	     dhParams.getP(),rng);
	 }
       }

       return dhEphemeral;
     }

     synchronized private KeyPair getEphemeralRSAPair(){
       seedRNG();
              
       if(rsaEphemeral==null){
	 try {
	   KeyPairGenerator kg=KeyPairGenerator.getInstance("RSA");

	   kg.initialize(512,rng);
	   rsaEphemeral=kg.generateKeyPair();
	 } catch (Exception e){
	   throw new InternalError(e.toString()); // Nothing should go wrong
	 }
       }

       return rsaEphemeral;
     }


     synchronized CryptixRSAPrivateKey getEphemeralRSAPrivateKey(){
       
       return (CryptixRSAPrivateKey)getEphemeralRSAPair().getPrivate();
     }

     synchronized CryptixRSAPublicKey getEphemeralRSAPublicKey(){
       return (CryptixRSAPublicKey)getEphemeralRSAPair().getPublic();
     }
	 
	 
     // Use this to branch off each connection's PRNG
     synchronized byte[] getSeedBytes(){
       byte[] buf=new byte[SEED_BYTES];

       seedRNG();
       
       rng.nextBytes(buf);
       return buf;
     }

     protected synchronized void storeSession(String key,SSLSessionData sd){
       SSLDebug.debug(SSLDebug.DEBUG_STATE,"Storing session under key"+key);
       session_cache.put(key,(Object)sd);
     }

     protected synchronized SSLSessionData findSession(String key){
       SSLDebug.debug(SSLDebug.DEBUG_STATE,"Trying to recover session using key"+key);
       Object obj=session_cache.get(key);

       if(obj==null)
	 return null;
       
       return (SSLSessionData)obj;
     }

     protected synchronized void destroySession(String sessionLookupKey){
       SSLDebug.debug(SSLDebug.DEBUG_STATE,"Destroying session" +
         sessionLookupKey);
       session_cache.remove(sessionLookupKey);
     }

     private void seedRNG(){
       if(rng!=null)
	 return;

       rng=new SecureRandom();
     }
}

     

