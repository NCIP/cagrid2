/**
   HMACInputStream.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Thu Oct  7 15:00:14 1999

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


   $Id: HMACInputStream.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/


package COM.claymoresystems.crypto;

import java.io.InputStream;
import java.io.IOException;

import java.security.MessageDigest;
import xjava.security.Parameterized;

/** Provides an InputStream filter that MACs the data*/
public class HMACInputStream extends InputStream {
     private InputStream sub;
     private MessageDigest md;
     
     protected void _HMACInputStream(String digest,byte[] key,InputStream in){
       sub=in;

       try {
	 md=MessageDigest.getInstance("HMAC-"+digest);

	 ((Parameterized)md).setParameter("key",key);
       } catch (java.lang.Exception e){
	 throw new InternalError(e.toString());
       }
     }

     /** Create the input stream with the indicated key

	 @param digest the digest name
	 @param key the key
	 @param in the input stream

     */
     public HMACInputStream(String digest, byte[] key, InputStream in){
       _HMACInputStream(digest,key,in);
     }

     /** Create the input stream with the indicated key

	 @param digest the digest name
	 @param key the key
	 @param in the input stream

     */
     public HMACInputStream(String digest,String key,InputStream out){
       try {
	 md=MessageDigest.getInstance(digest);
	 _HMACInputStream(digest,md.digest(key.getBytes()),out);
	 	
       } catch (java.lang.Exception e){
	 throw new InternalError(e.toString());
       }
     }
     
     public int read()
       throws IOException {
       int b=sub.read();
       md.update((byte)(0xff & b));
       return b;
     }

     public int read(byte[] b)
       throws IOException {
       int ret=sub.read(b);
       md.update(b,0,ret);

       return ret;
     }

     public byte[] digest(){
       return md.digest();
     }
}     
