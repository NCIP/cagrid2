/**
   SSLv3CertificateVerify.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue Jun  8 11:30:24 1999

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

   $Id: SSLv3CertificateVerify.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/


package COM.claymoresystems.ptls;

import java.security.MessageDigest;

class SSLv3CertificateVerify {
     static byte[] computeToBeSigned(SSLHandshake hs,boolean mine){
       SSLHandshakeHashes hashes=mine?hs.hashes:hs.save_hashes;
       int i;
       
       MessageDigest md5=hashes.getMD5Digest();
       MessageDigest sha=hashes.getSHADigest();       

       // Inner MD5
       md5.update(hs.master_secret);
       for(i=0;i<48;i++){
	 md5.update(SSLHandshake.pad_1);
       }
       byte[] md5_bytes=md5.digest();

       // Outer MD5
       md5.update(hs.master_secret);
       for(i=0;i<48;i++){
	 md5.update(SSLHandshake.pad_2);
       }
       md5.update(md5_bytes);
       md5_bytes=md5.digest();
       
       // Inner SHA
       sha.update(hs.master_secret);
       for(i=0;i<40;i++){
	 sha.update(SSLHandshake.pad_1);
       }
       byte[] sha_bytes=sha.digest();

       // Outer SHA
       sha.update(hs.master_secret);
       for(i=0;i<40;i++){
	 sha.update(SSLHandshake.pad_2);
       }
       sha.update(sha_bytes);
       sha_bytes=sha.digest();

       byte[] result=new byte[36];
       System.arraycopy(md5_bytes,0,result,0,16);
       System.arraycopy(sha_bytes,0,result,16,20);

       return result;
     }
}

