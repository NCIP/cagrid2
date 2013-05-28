/**
   PKCS1Pad.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sun Jun 20 17:29:34 1999

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

   $Id: PKCS1Pad.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.crypto;

import COM.claymoresystems.ptls.SSLDebug;

import java.security.PublicKey;
import java.security.PrivateKey;
import xjava.security.interfaces.CryptixRSAPublicKey;
import xjava.security.interfaces.CryptixRSAPrivateKey;
import xjava.security.IllegalBlockSizeException;
import java.math.BigInteger;
import java.security.SecureRandom;


/** Implement PKCS1 padding*/
public class PKCS1Pad {
     public static final int ENCRYPT=1;
     public static final int SIGN=2;
     public static final int DECRYPT=1;
     public static final int VERIFY=2;

     /** Pad a buffer for encryption with this public key*/
     public static byte[] pkcs1PadBuf(SecureRandom rnd,
       byte[] input,PublicKey key){
       BigInteger modulus=((CryptixRSAPublicKey)key).getModulus();

       return pkcs1PadBuf(rnd,input,modulus,ENCRYPT);
     }

     /* Pad a buffer for signature*/
     public static byte[] pkcs1PadBuf(byte[] input,PrivateKey key){
       BigInteger modulus=((CryptixRSAPrivateKey)key).getModulus();

       return pkcs1PadBuf(input,modulus,SIGN);
     }

     public static byte[] pkcs1PadBuf(byte[] input,BigInteger modulus,
       int how){
       SecureRandom rnd=null;
       
       if(how==ENCRYPT) rnd=new SecureRandom();

       return pkcs1PadBuf(rnd,input,modulus,how);
     }
       
     public static byte[] pkcs1PadBuf(SecureRandom rnd,
       byte[] input, BigInteger modulus,
       int how){
       int length;

       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"PKCS1 pad input",
	 input);


       length=modulus.bitLength()/8;
       length+=((modulus.bitLength()%8)>0)?1:0;

       // And who asked Cryptix to add the zero on the front?
       int num_pad;
       if(how == ENCRYPT) {
	 length--;
	 num_pad=(length-(2+input.length));
       }
       else {
	 num_pad=(length-(3+input.length));
       }
       
       byte[] output=new byte[length];
       
       if(num_pad<8)
	 throw new InternalError("Input too long");

       int offset=0;
       
       if(how == ENCRYPT){
	 output[offset++]=2;	 
                         
	 byte[] tmp=new byte[1];

	 for(int ct=0;ct<num_pad;ct++){
	   do {
	     rnd.nextBytes(tmp);
	   } while(tmp[0]==0);

	   output[offset++]=tmp[0];
	 }
       }
       else{
	 output[offset++]=0;
	 output[offset++]=1;	 
	 for(int i=0;i<num_pad;i++)
	   output[offset++]=(byte)0xff;
       }
       output[offset++]=0;
       System.arraycopy(input,0,output,offset,input.length);

       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"PKCS1 padded output",
	 output);
       return output;
     }

     public static byte[] pkcs1UnpadBuf(byte[] input,int how,
       CryptixRSAPrivateKey key){
       return pkcs1UnpadBuf(input,how,key.getModulus());
     }
     
     /* Note that Cryptix strips the leading zero so actually
        the first byte is the PKCS1 block type byte ! */
     public static byte[] pkcs1UnpadBuf(byte[] input,int how,
       BigInteger modulus){
       int i;
       int length;
       int pad_bytes=0;
       
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"PKCS1 padded input",
	 input);

       length=modulus.bitLength()/8;
       length+=((modulus.bitLength()%8)>0)?1:0;

       if((length-1)!=input.length){
         SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Encryption block wrong length");
         throw new
           xjava.security.IllegalBlockSizeException("Bad RSA padding: wrong length" + input.length);
       }
       if(how==DECRYPT){
         if(input[0]!=2)
           throw new
             xjava.security.IllegalBlockSizeException("Bad RSA padding");
       }
       else {
          if(input[0]!=1)
           throw new
             xjava.security.IllegalBlockSizeException("Bad RSA padding");
       }
         
       for(i=1;i<input.length;i++){
	 if(input[i]==0){
           if(pad_bytes<8){
             SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Bad RSA padding" +
               pad_bytes + "bytes");
             throw new xjava.security.IllegalBlockSizeException
               ("Bad RSA padding");
           }
           
	   byte[] result=new byte[input.length-(i+1)];

	   System.arraycopy(input,i+1,result,0,result.length);

	   SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"PKCS1 unpadded output",
	     result);
	   
	   return result;
	 }
         else {
           pad_bytes++;
           
           // Check for 0xff
           if((how!=DECRYPT) && (input[i]!=(byte)0xff))
             throw new xjava.security.IllegalBlockSizeException("Bad RSA padding");
         }
       }       

       throw new xjava.security.IllegalBlockSizeException("Bad RSA padding");
     }
}
       
