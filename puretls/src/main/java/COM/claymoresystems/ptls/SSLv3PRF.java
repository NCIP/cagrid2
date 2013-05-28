/**
   SSLv3PRF.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sun May  9 20:18:57 1999

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

   $Id: SSLv3PRF.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/



package COM.claymoresystems.ptls;
import java.security.MessageDigest;

class SSLv3PRF extends SSLPRF {
     MessageDigest md5;
     MessageDigest sha;

     public SSLv3PRF(){
       super();
       try {
	 md5=MessageDigest.getInstance("MD5");
	 sha=MessageDigest.getInstance("SHA-1");
       } catch (java.security.NoSuchAlgorithmException e){
	 throw new Error("Internal inconsistency");
       }
     }


     public void PRF(byte[] secret, int usage, byte[] client_random,
       byte[] server_random, byte[] out) {
       
       switch(usage){
         case SSL_PRF_CLIENT_WRITE_KEY:
         case SSL_PRF_SERVER_WRITE_KEY:
         case SSL_PRF_CLIENT_WRITE_IV:
         case SSL_PRF_SERVER_WRITE_IV:
           PRFHash(secret,usage,client_random,server_random,out);
           break;
         default:
           PRFPRF(secret,usage,client_random,server_random,out);
       }
     }
     
     public void PRFPRF(byte[] secret, int usage, byte[] client_random,
       byte[] server_random, byte[] out) {
       int off=0;
       byte[] buf=new byte[20];
       int i=0,j;

       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,
         "Secret",secret);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,
         "Client random",client_random);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,
         "Server random",server_random);
       
       for(off=0;off<out.length;off+=16){
	 i++;

	 // A, BB, CCC,  ...
	 for(j=0;j<i;j++){
	   buf[j]=(byte)(64+i);
	 }

         SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"BUF",buf);
	 sha.update(buf,0,i);
	 sha.update(secret);

	 switch(usage){
	   case SSL_PRF_MASTER_SECRET:
	     sha.update(client_random);
	     sha.update(server_random);
	     break;
	   case SSL_PRF_KEY_BLOCK:
	     sha.update(server_random);
	     sha.update(client_random);
             break;
           default:
             throw new InternalError("Bad usage");
	 }
         
	 byte[] sha_out=sha.digest();

         SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,
           "SHA out",sha_out);
         	 
	 md5.update(secret);
	 md5.update(sha_out);

	 byte[] md5_out=md5.digest();
         SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,
           "MD5 out",md5_out);
	 System.arraycopy(md5_out,0,out,off,
	   (16>(out.length-off))?out.length-off:16);
       }

     }

     public void PRFHash(byte[] secret, int usage, byte[] client_random,
       byte[] server_random, byte[] out) {
       md5.update(secret);

       switch(usage){
         case SSL_PRF_CLIENT_WRITE_KEY:
         case SSL_PRF_CLIENT_WRITE_IV:
           md5.update(client_random);
           md5.update(server_random);
           break;
         default:
           md5.update(server_random);
           md5.update(client_random);
       }

       byte[] md5out=md5.digest();
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,
         "PRFHash out",md5out);
       System.arraycopy(md5out,0,out,0,out.length);
     }
}

       
       
       
     
     
