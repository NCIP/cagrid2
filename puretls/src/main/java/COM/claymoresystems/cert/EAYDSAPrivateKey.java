/**
   EAYDSAPrivateKey.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Mon Jun  7 12:07:48 1999

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

   $Id: EAYDSAPrivateKey.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.cert;
import COM.claymoresystems.crypto.BaseDSAPrivateKey;
import COM.claymoresystems.crypto.RawDSAParams;

import cryptix.asn1.lang.ASNObject;
import cryptix.asn1.lang.ASNSpecification;
import cryptix.asn1.lang.Parser;
import cryptix.asn1.lang.ParseException;
import cryptix.asn1.lang.ASNBitString;
import cryptix.asn1.encoding.BaseCoder;
import cryptix.asn1.encoding.CoderOperations;
import cryptix.asn1.encoding.DER;
import java.security.PublicKey;
import java.security.interfaces.DSAPrivateKey;
import java.math.BigInteger;
import java.io.*;

/** Construct a DSA private from the SSLeay style BER encoding*/
public class EAYDSAPrivateKey extends BaseDSAPrivateKey {
     public EAYDSAPrivateKey(byte[] encoding)
       throws IOException {
       synchronized(CertContext.getSpec()){
         CoderOperations der_coder=BaseCoder.getInstance("DER");
         ByteArrayInputStream is=new ByteArrayInputStream(encoding);
         der_coder.init(is);
         ASNObject dsaKey=CertContext.getSpec().getComponent("EAYDSAPrivateKey");
         dsaKey.accept(der_coder,null);
       
         BigInteger p=(BigInteger)(dsaKey.getComponent("EAYDSAPrivateKey.p").getValue());
         BigInteger q=(BigInteger)(dsaKey.getComponent("EAYDSAPrivateKey.q").getValue());
         BigInteger g=(BigInteger)(dsaKey.getComponent("EAYDSAPrivateKey.g").getValue());
         params=new RawDSAParams(p,q,g);
       
         X=(BigInteger)(dsaKey.getComponent("EAYDSAPrivateKey.x").getValue());
         //     System.out.println("X= "+ X.toString());
       }
     }

     public EAYDSAPrivateKey(DSAPrivateKey key){
       super(key);
     }
     
     public String getFormat(){
       return "DER";
     }

     public byte[] getEncoded(){
       ByteArrayOutputStream os;
       try {
	 os=new ByteArrayOutputStream();

	 DERUtils.encodeInteger(BigInteger.valueOf(0),os);
	 DERUtils.encodeInteger(params.getP(),os);
	 DERUtils.encodeInteger(params.getQ(),os);       
	 DERUtils.encodeInteger(params.getG(),os);
	 DERUtils.encodeInteger(getY(),os);
	 DERUtils.encodeInteger(X,os);
	 
	 byte[] tmp=os.toByteArray();

	 os.reset();
	 DERUtils.encodeSequence(tmp,os);
       } catch (IOException e){
	 throw new InternalError(e.toString());
       }
       
       return os.toByteArray();
     }
}

