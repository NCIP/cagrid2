/**
   SSLCipherState.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Mon May 10 07:26:21 1999

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

   $Id: SSLCipherState.java,v 1.2 2003/05/10 05:44:45 gawor Exp $

*/

package COM.claymoresystems.ptls;

import cryptix.provider.key.RawSecretKey;
import xjava.security.Cipher;
import xjava.security.FeedbackCipher;

public class SSLCipherState {
     SSLCipherSuite cipher_suite;
     Cipher cipher;
     byte[] cipher_key;
     byte[] mac_key;
     byte[] iv;
     
    public byte[] getMacKey() {
	return this.mac_key;
    }

    public SSLCipherSuite getCipherSuite() {
	return this.cipher_suite;
    }

     public static void computeSSLCipherState(SSLHandshake hs,
       SSLCipherState write,SSLCipherState read)
       throws java.security.NoSuchAlgorithmException,
	  java.security.KeyException{
       int kb_size=0;
       int digest_size=hs.cipher_suite.getDigestOutputLength();
       int key_size=hs.cipher_suite.getCipherEffectiveKeyLength();
       int block=hs.cipher_suite.blockCipherP()?8:0;
       int off=0;
       SSLPRF prf;
       
       write.iv=new byte[8];
       read.iv=new byte[8];


       write.cipher_suite=hs.cipher_suite;
       read.cipher_suite=hs.cipher_suite;
       
       prf=SSLPRF.getPRFInstance(hs._conn.ssl_version);
       
       kb_size+=2 * digest_size;
       kb_size+=2 * key_size;
       kb_size+=2 * block;

       byte[] key_block=new byte[kb_size];
       
       prf.PRF(hs.master_secret,SSLPRF.SSL_PRF_KEY_BLOCK,
	 hs.client_random, hs.server_random, key_block);

       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Key block",key_block);

       byte[] client_write_key=null;
       byte[] server_write_key=null;

       // Allocate storage
       if(key_size>0){
	 client_write_key=new byte[key_size];
	 server_write_key=new byte[key_size];
       }

       write.mac_key=new byte[digest_size];
       read.mac_key=new byte[digest_size];

			       
       // Now slice and dice
       if(hs.client){
	 System.arraycopy(key_block,off,write.mac_key,0,digest_size);
	 off+=digest_size;
	 System.arraycopy(key_block,off,read.mac_key,0,digest_size);
	 off+=digest_size;
       }
       else{
	 System.arraycopy(key_block,off,read.mac_key,0,digest_size);
	 off+=digest_size;
	 System.arraycopy(key_block,off,write.mac_key,0,digest_size);
	 off+=digest_size;
       }

       if(key_size>0){
	 System.arraycopy(key_block,off,client_write_key,0,key_size);
	 off+=key_size;
	 System.arraycopy(key_block,off,server_write_key,0,key_size);
	 off+=key_size;
       
	 if((block>0) && !hs.cipher_suite.exportableP()){
	 
	   if(hs.client){
	     System.arraycopy(key_block,off,write.iv,0,block);
	     off+=block;
	     System.arraycopy(key_block,off,read.iv,0,block);	   
	   }
	   else{
	     System.arraycopy(key_block,off,read.iv,0,block);
	     off+=block;
	     System.arraycopy(key_block,off,write.iv,0,block);	   
	   }
	 }

	 // Export stuff needs postprocessing
	 if(hs.cipher_suite.exportableP()){
	   byte[] old_client_write_key=client_write_key;
	   byte[] old_server_write_key=server_write_key;

	   // The old keys are shorter
	   client_write_key=new byte[hs.cipher_suite.getCipherKeyLength()];
	   server_write_key=new byte[hs.cipher_suite.getCipherKeyLength()];	 
	 
	   prf.PRF(old_client_write_key,SSLPRF.SSL_PRF_CLIENT_WRITE_KEY,
	     hs.client_random,hs.server_random,client_write_key);
	   prf.PRF(old_server_write_key,SSLPRF.SSL_PRF_SERVER_WRITE_KEY,
	     hs.client_random,hs.server_random,server_write_key);

	   if(block>0){
	     byte[] iv_block=new byte[2*block];
	 
	     if(hs.client){
	       prf.PRF(new byte[0],SSLPRF.SSL_PRF_CLIENT_WRITE_IV,
		 hs.client_random,hs.server_random,write.iv);
	       prf.PRF(new byte[0],SSLPRF.SSL_PRF_SERVER_WRITE_IV,
		 hs.client_random,hs.server_random,read.iv);
	     }
	     else{
	       prf.PRF(new byte[0],SSLPRF.SSL_PRF_SERVER_WRITE_IV,
		 hs.client_random,hs.server_random,write.iv);
	       prf.PRF(new byte[0],SSLPRF.SSL_PRF_CLIENT_WRITE_IV,
		 hs.client_random,hs.server_random,read.iv);
	     }
	   }
	 }

	 // Now we're ready to assign the keys
	 if(hs.client){
	   write.cipher_key=client_write_key;
	   read.cipher_key=server_write_key;
	 }
	 else{
	   write.cipher_key=server_write_key;
	   read.cipher_key=client_write_key;
	 }

	 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Write key",write.cipher_key);
	 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Read key",read.cipher_key);
       }
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Write IV",write.iv);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Read IV",read.iv);       
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Write MAC key",write.mac_key);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Read MAC key",read.mac_key);       

       if(key_size>0){
	 // Now make the Cipher objects
	 read.cipher=Cipher.getInstance(hs.cipher_suite.getCipherAlg());
	 write.cipher=Cipher.getInstance(hs.cipher_suite.getCipherAlg());

	 RawSecretKey rwrite_key = new RawSecretKey(hs.cipher_suite.getCipherAlg(),
	   write.cipher_key);
	 RawSecretKey rread_key = new RawSecretKey(hs.cipher_suite.getCipherAlg(),
	   read.cipher_key);

	 if(block>0){
	   ((FeedbackCipher)write.cipher).setInitializationVector(write.iv);
	   ((FeedbackCipher)read.cipher).setInitializationVector(read.iv);
	 }
	 write.cipher.initEncrypt(rwrite_key);
	 read.cipher.initDecrypt(rread_key);
       }
     }
}
     
