/**
   EAYDHParams.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Mon Nov 15 11:03:07 1999

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


   $Id: EAYDHParams.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/



package COM.claymoresystems.cert;
import COM.claymoresystems.crypto.BaseDSAPrivateKey;
import COM.claymoresystems.crypto.RawDSAParams;
import java.math.BigInteger;

import java.io.*;

public class EAYDHParams {
     private BigInteger g;
     private BigInteger p;

     public EAYDHParams(BigInteger g,BigInteger p){
       this.p=p;
       this.g=g;
     }

     public EAYDHParams(byte[] encoding)
       throws IOException {
       ByteArrayInputStream is=new ByteArrayInputStream(encoding);

       byte[] seq=DERUtils.decodeSequence(is);
       is=new ByteArrayInputStream(seq);
       p=DERUtils.decodeInteger(is);
       g=DERUtils.decodeInteger(is);
     }

     public BigInteger getG(){
       return g;
     }

     public BigInteger getP(){
       return p;
     }

     public byte[] getEncoded(){
       ByteArrayOutputStream os;
       try {
	 os=new ByteArrayOutputStream();

	 DERUtils.encodeInteger(p,os);
	 DERUtils.encodeInteger(g,os);       
	 
	 byte[] tmp=os.toByteArray();

	 os.reset();
	 DERUtils.encodeSequence(tmp,os);
       } catch (IOException e){
	 throw new InternalError(e.toString());
       } return os.toByteArray();
     }
}
     
