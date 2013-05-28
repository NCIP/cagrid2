/**
   SSLHandshakeHashes.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Mon May 10 17:07:04 1999

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

   $Id: SSLHandshakeHashes.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/



package COM.claymoresystems.ptls;
import java.security.MessageDigest;

class SSLHandshakeHashes {
     private MessageDigest md5;
     private MessageDigest sha;

     public SSLHandshakeHashes(){
       try {
	 md5=MessageDigest.getInstance("MD5");
	 sha=MessageDigest.getInstance("SHA-1");
       }
       catch (java.security.NoSuchAlgorithmException e){
	 throw new Error("Inconsistency. Shouldn't be missing MD5 or SHA");
       }
     }

     public void update(byte[] buf){
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Handshake hash update",
	 buf);
       md5.update(buf);
       sha.update(buf);
     }

     public MessageDigest getMD5Digest() {
       try {
	 return (MessageDigest)md5.clone();
       } catch (java.lang.CloneNotSupportedException e){
	 throw new Error("Hey, this should be cloneable");
       }
     }

     public MessageDigest getSHADigest() {
       try {
	 return (MessageDigest)sha.clone();
       } catch (java.lang.CloneNotSupportedException e){
	 throw new Error("Hey, this should be cloneable");
       }
     }
     
     public byte[] getMD5Value(){
       return getMD5Value(new byte[0]);
     }

     public byte[] getSHAValue(){
       return getSHAValue(new byte[0]);
     }

     public byte[] getMD5Value(byte[] buf){
       try {
	 MessageDigest n_md5=(MessageDigest)md5.clone();

	 n_md5.update(buf);
	 return n_md5.digest();
	 
       } catch (java.lang.CloneNotSupportedException e){
	 throw new Error("Hey, this should be cloneable");
       }
     }
     
     public byte[] getSHAValue(byte[] buf){
       try {
	 MessageDigest n_sha=(MessageDigest)sha.clone();


	 n_sha.update(buf);
	 return n_sha.digest();
       } catch (java.lang.CloneNotSupportedException e){
	 throw new Error("Hey, this should be cloneable");
       }
     }

     public Object clone() throws CloneNotSupportedException {
       SSLHandshakeHashes hh=new SSLHandshakeHashes();
       hh.md5=getMD5Digest();
       hh.sha=getSHADigest();
       return(hh);
     }
     
     public void reset(){
       md5.reset();
       sha.reset();
     }
}
