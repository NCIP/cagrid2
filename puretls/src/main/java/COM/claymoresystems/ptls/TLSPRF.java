/**
   TLSPRF.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue May 18 16:01:51 1999

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

   $Id: TLSPRF.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.ptls;
import COM.claymoresystems.util.Util;
import java.security.MessageDigest;
import xjava.security.Parameterized;
import java.io.*;


class TLSPRF extends SSLPRF {
     MessageDigest md5;
     MessageDigest sha;

     private static byte labels[][]={
	  {},
{ 109, 97, 115, 116, 101, 114, 32, 115, 101, 99, 114, 101, 116},
{ 107, 101, 121, 32, 101, 120, 112, 97, 110, 115, 105, 111, 110},
{ 99, 108, 105, 101, 110, 116, 32, 119, 114, 105, 116, 101, 32, 107, 101, 121},
{ 115, 101, 114, 118, 101, 114, 32, 119, 114, 105, 116, 101, 32, 107, 101, 121},
{ 73, 86, 32, 98, 108, 111, 99, 107},
{ 73, 86, 32, 98, 108, 111, 99, 107},
{ 99, 108, 105, 101, 110, 116, 32, 102, 105, 110, 105, 115, 104, 101, 100},
{ 115, 101, 114, 118, 101, 114, 32, 102, 105, 110, 105, 115, 104, 101, 100}
     };

     public static final int TLS_PRF_CLIENT_FINISHED=7;
     public static final int TLS_PRF_SERVER_FINISHED=8;
     
     public TLSPRF(){
       super();
       try {
	 md5=MessageDigest.getInstance("HMAC-MD5");
	 sha=MessageDigest.getInstance("HMAC-SHA-1");
       } catch (java.security.NoSuchAlgorithmException e){
	 throw new Error("Internal inconsistency");
       }
     }


     void PRF(byte[] secret, int usage, byte[] client_random,
       byte[] server_random, byte[] out){
       int length=out.length;
       byte[] md5_out=null;
       byte[] sha_out=null;

       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"PRF in: secret",secret);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"PRF in: client_random",client_random);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"PRF in: server_random",server_random);              
       
       int slen=secret.length / 2 + secret.length % 2;

       byte[] S1=new byte[slen];
       byte[] S2=new byte[slen];

       System.arraycopy(secret,0,S1,0,slen);
       System.arraycopy(secret,secret.length-slen,S2,0,slen);

       

       try {
	 ByteArrayOutputStream os=new ByteArrayOutputStream();

	 os.write(labels[usage]);
	 
	 switch(usage){
	   case SSL_PRF_MASTER_SECRET:
	   case SSL_PRF_CLIENT_WRITE_KEY:
	   case SSL_PRF_SERVER_WRITE_KEY:
	   case TLS_PRF_CLIENT_FINISHED:
	   case TLS_PRF_SERVER_FINISHED:
	     os.write(client_random);
	     os.write(server_random);
	     break;
	   case SSL_PRF_KEY_BLOCK:
	     os.write(server_random);
	     os.write(client_random);
	     break;
	   case SSL_PRF_SERVER_WRITE_IV:	   
	     length*=2;
	   case SSL_PRF_CLIENT_WRITE_IV:
	     os.write(client_random);
	     os.write(server_random);
	     break;
	   default:
	     throw new Error("Unknown PRF usage");
	 }

	 byte[] seed=os.toByteArray();

	 md5_out=P_hash(S1,seed,md5,length);
	 sha_out=P_hash(S2,seed,sha,length);       
       } catch (java.io.IOException e){
	 throw new Error("Internal Error");
       }
       
       // Ack, deal with the fact that this is a block in TLS
       if(usage==SSL_PRF_SERVER_WRITE_IV){
	 System.arraycopy(md5_out,out.length,md5_out,0,out.length);
	 System.arraycopy(sha_out,out.length,sha_out,0,out.length);
       }

       for(int i=0;i<out.length;i++){
	 out[i]=(byte)(md5_out[i] ^ sha_out[i]);
       }
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"PRF out",out);
     }

     // The main function
     private byte[] P_hash(byte[] secret, byte[] seed,
       MessageDigest md, int required){
       byte[] out=new byte[required];
       int offset=0,tocpy;
       byte[] A,tmp;

//       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"P_hash secret",secret);
//      SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"P_hash seed",seed);       

       

       A=seed;
       
       while(required>0){
	 setKey(secret,md);
	 md.update(A);
	 A=md.digest();

	 setKey(secret,md);
//	 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"A",A);
	 md.update(A);
	 md.update(seed);
	 tmp=md.digest();

//	 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"P block",tmp);
	 
	 tocpy=Util.min(required,tmp.length);
	 System.arraycopy(tmp,0,out,offset,tocpy);
	 offset+=tocpy;
	 required-=tocpy;
       }

//      SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"P_hash out",out);

       return out;
     }

     private void setKey(byte[] secret,MessageDigest md){
       try {
	 ((Parameterized)md).setParameter("key",secret);
       } catch (xjava.security.InvalidParameterTypeException e){
	 throw new InternalError(e.toString());	 
       } catch (xjava.security.NoSuchParameterException e){	 
	 throw new InternalError(e.toString());
       }
     }

}
	 
	 


	 

       
     
