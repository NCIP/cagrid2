/**
   PEMData.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue Aug 17 18:05:43 1999

   This package is a SSLv3/TLS implementation written by Eric Rescorla
   <ekr\@rtfm.com> and licensed by Claymore Systems, Inc.

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


   $Id: PEMData.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.crypto;
import COM.claymoresystems.util.Util;
import COM.claymoresystems.util.RFC822Hdr;
import COM.claymoresystems.cert.WrappedObject;
import COM.claymoresystems.ptls.SSLDebug;

import xjava.security.Cipher;
import java.security.PrivateKey;
import xjava.security.FeedbackCipher;
import java.security.MessageDigest;

import cryptix.provider.key.RawSecretKey;
import cryptix.util.core.Hex;

import java.io.*;
import java.util.Random;
import java.util.Hashtable;

class PEMData {
     private static Hashtable eay2jce=null;
     private static Hashtable kSize=null;
     
     private static String encryptionAlg;
     
     static {
//strip EXPORT       
       encryptionAlg="DES-EDE3-CBC";
//else
//       encryptionAlg="DES-CBC";       
//estrip       
       eay2jce=new Hashtable();

       eay2jce.put("DES-EDE3-CBC","3DES/CBC/PKCS5Padding");
       eay2jce.put("DES-CBC","DES/CBC/PKCS5Padding");

       kSize=new Hashtable();
       kSize.put("DES-EDE3-CBC",new Integer(24));
       kSize.put("DES-CBC",new Integer(8));
     }
     
     static byte[] readPEMObject(BufferedReader in, byte[] password)
       throws IOException {
       byte[] plainText;
       RFC822Hdr proc_type=new RFC822Hdr(in);
       RFC822Hdr dek_info=null;
       
       if(proc_type.getName()!=null){
	 if(!proc_type.getSubfield(0).equals("4"))
	   throw new IOException("Unknown proc type" + proc_type.getSubfield(0));
	 
	 dek_info=new RFC822Hdr(in);

	 try {
	   byte[] cipherText=WrappedObject.readBlock(in);
 	   
	   String alg=dek_info.getSubfield(0);
	   String algorithm=(String)eay2jce.get(alg);
	   if(algorithm==null)
	     throw new InternalError("Algorithm "+alg+" not recognized");
	   byte[] key_bytes=new byte[((Integer)kSize.get(alg)).intValue()];
	   
	   byte[] iv=Hex.fromString(dek_info.getSubfield(1));
	   
	   EVP_BytesToKey("MD5",iv, // Salt is IV
	     password,(short)1,key_bytes,null);
	   
	   RawSecretKey key=new RawSecretKey(algorithm,key_bytes);

//	   Util.xdump("Decryption key",key_bytes);
	   
	   Cipher ciph=Cipher.getInstance(algorithm);
	   ((FeedbackCipher)ciph).setInitializationVector(iv);
	   ciph.initDecrypt(key);

//	   Util.xdump("Encrypted key",cipherText);
           SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"PEM Data cipherText",
             cipherText);
	   
	   plainText=ciph.crypt(cipherText);
           SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"PEM Data plaintext",
             plainText);
	 } catch (java.security.KeyException e){
	   throw new InternalError(e.toString());
	 }
	 catch (java.security.NoSuchAlgorithmException e){
	   throw new InternalError(e.toString());
	 }
       }
       else{
	 plainText=WrappedObject.readBlock(in);
       }

       return plainText;
     }

     static void writePEMObject(byte[] plainText, byte[] password,
       String type,BufferedWriter out)
       throws IOException {
       byte[] cipherText;
       byte[] iv=new byte[8];
       Random r=new Random();
       r.nextBytes(iv);

       try {
	 String algorithm=(String)eay2jce.get(encryptionAlg);
	 byte[] key_bytes=new byte[((Integer)kSize.get
	   (encryptionAlg)).intValue()];

	 PEMData.EVP_BytesToKey("MD5",iv, // Salt is IV
	   password,(short)1,key_bytes,null);

	 Cipher ciph=Cipher.getInstance(algorithm);
	 ((FeedbackCipher)ciph).setInitializationVector(iv);

	 RawSecretKey key=new RawSecretKey(algorithm,key_bytes);
//	 Util.xdump("Decryption key",key_bytes);	 
	 ciph.initEncrypt(key);
         SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Writing PEM Data plaintext",
           plainText);
//	 Util.xdump("Raw private",plainText);
	 cipherText=ciph.crypt(plainText);
//	 Util.xdump("Encrypted private",cipherText);
         SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Writing PEM Data cipherText",
           cipherText);
       } catch (java.security.KeyException e){
	 throw new InternalError(e.toString());
       }
       catch (java.security.NoSuchAlgorithmException e){
	 throw new InternalError(e.toString());
       }
	 
       // Write the header
       String begin="-----BEGIN "+type+"-----";

       out.write(begin);
       out.newLine();
       out.write("Proc-Type: 4,ENCRYPTED");
       out.newLine();

       // Write the DEK-Info line
       String ivl="DEK-Info: "+encryptionAlg+","+Hex.toString(iv);
       out.write(ivl);
       out.newLine();
       out.newLine();       

       out.flush();
       WrappedObject.writeObject(cipherText,type,out);
     }
     

     /**
	  This is a straight translation of SSLeay's EVP_BytesToKey()
	  from evp_key.c. It converts a password into a key

	  @param digest the digest to use
	  @param salt salt octets
	  @param data the input password/key
	  @param count the iteration count
	  @param key the key (OUT)
	  @param iv the iv (OUT)
     */
     static void EVP_BytesToKey(String digest,
       byte[] salt, byte[] data, short count, byte[] key, byte[] iv){
       byte[] md_buf=null;
       int key_offset=0;
       int key_left=key.length;
       int iv_offset=0;
       int iv_left=0;

       if(iv!=null) iv_left=iv.length;
	   
       try {
	 MessageDigest md;

	 md=MessageDigest.getInstance(digest);

	 for(;;){
	   if(md_buf!=null)
	     md.update(md_buf);
	   md.update(data);
	   if(salt!=null)
	     md.update(salt);
	   md_buf=md.digest();

	   // Repeat this count times
	   for(short i=1;i<count;i++){
	     md.update(md_buf);
	     md_buf=md.digest();
	   }

	   // First copy the key if we need more key
	   int left=md_buf.length;
	   int offset=0;
	     
	   if(key_left!=0){
	     int tocpy=Util.min(key_left,left);
	     System.arraycopy(md_buf,0,key,key_offset,tocpy);
	     left-=tocpy;
	     offset=tocpy;
	     key_left-=tocpy;
	     key_offset+=tocpy;
	   }

	   // Now copy the IV if we need more IV
	   if(left>=0 && iv_left>0){
	     int tocpy=Util.min(iv_left,left);
	     System.arraycopy(md_buf,offset,iv,iv_offset,tocpy);
	     iv_left-=tocpy;
	     iv_offset+=tocpy;
	   }

	   // If we've generated all the keying material we need, break
	   if(key_left==0 && iv_left==0)
	     break;
	 }
       }
       catch (java.lang.Exception e){
	 throw new InternalError(e.toString());
       }
     }
}
