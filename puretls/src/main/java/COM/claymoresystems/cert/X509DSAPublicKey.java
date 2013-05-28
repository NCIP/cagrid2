/**
   X509DSAPublicKey.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sun May 23 17:17:25 1999

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

   $Id: X509DSAPublicKey.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.cert;
import COM.claymoresystems.crypto.BaseDSAPublicKey;
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
import java.security.interfaces.DSAPublicKey;
import java.math.BigInteger;
import java.io.*;

/** A DSA Public key BER encoded a la PKIX*/
public class X509DSAPublicKey extends BaseDSAPublicKey{
     private byte[] oid={(byte)0x2a,(byte)0x86,(byte)0x48,(byte)0xce,
		   (byte)0x38,(byte)0x04,(byte)0x01};		
	 
     
     public X509DSAPublicKey(String oid,byte[] parameters, byte[] key)
       throws IOException {
       synchronized(CertContext.getSpec()){
         // First decode the parameters
         CoderOperations der_coder=BaseCoder.getInstance("DER");
         ByteArrayInputStream is=new ByteArrayInputStream(parameters);
         der_coder.init(is);
         ASNObject dsaParams=CertContext.getSpec().getComponent("DSAParameters");
         dsaParams.accept(der_coder,null);

         BigInteger p=(BigInteger)(dsaParams.getComponent("DSAParameters.p").getValue());
         BigInteger q=(BigInteger)(dsaParams.getComponent("DSAParameters.q").getValue());       
         BigInteger g=(BigInteger)(dsaParams.getComponent("DSAParameters.g").getValue());       

         params=new RawDSAParams(p,q,g);
       
         //Now recover Y. This is an INTEGER encoded as a BIT string, so we need to lop off the
         //first octet, which had better be 0
         if(key[0]!=0)
           throw new IOException("Bad encoded key");
         byte[] enc_key=new byte[key.length-1];
         System.arraycopy(key,1,enc_key,0,enc_key.length);
         is=new ByteArrayInputStream(enc_key);
         ASNObject dsaKey=CertContext.getSpec().getComponent("DSAKey");
         der_coder.init(is);
         dsaKey.accept(der_coder,null);
         Y=(BigInteger)dsaKey.getValue();
       }
     }

     public X509DSAPublicKey(DSAPublicKey key){
       super(key);
     }

     public String getFormat(){
       return "DER";
     }

     public byte[] getEncoded(){
       try {
	 ByteArrayOutputStream os=new ByteArrayOutputStream();

       // Compute the paramaters sequence
	 DERUtils.encodeInteger(params.getP(),os);
	 DERUtils.encodeInteger(params.getQ(),os);
	 DERUtils.encodeInteger(params.getG(),os);
	 byte[] tmp=os.toByteArray();
	 os.reset();
	 DERUtils.encodeSequence(tmp,os);
	 byte[] p=os.toByteArray();

	 // And the key
	 os.reset();
	 DERUtils.encodeInteger(Y,os);
	 byte[] key=os.toByteArray();

	 return X509SubjectPublicKeyInfo.encodePublicKey(oid,p,key);
       } catch (IOException e){
	 throw new InternalError(e.toString());
       }
     }
}
       
       
       
       

       

