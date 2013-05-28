/**
   X509RSAPrivateKey.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sat Jun 19 20:14:56 1999

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

   $Id: X509RSAPrivateKey.java,v 1.2 2003/05/10 05:42:32 gawor Exp $

*/

package COM.claymoresystems.cert;
import COM.claymoresystems.ptls.SSLDebug;

import cryptix.provider.rsa.BaseRSAPrivateKey;
import xjava.security.interfaces.RSAFactors;
import xjava.security.interfaces.CryptixRSAPrivateKey;
import cryptix.asn1.lang.ASNObject;
import cryptix.asn1.lang.ASNSpecification;
import cryptix.asn1.lang.Parser;
import cryptix.asn1.lang.ParseException;
import cryptix.asn1.lang.ASNBitString;
import cryptix.asn1.encoding.BaseCoder;
import cryptix.asn1.encoding.CoderOperations;
import cryptix.asn1.encoding.DER;
import java.security.PrivateKey;
import java.math.BigInteger;
import java.io.*;


/** A RSA Private key BER encoded a la PKCS-1 */
public class X509RSAPrivateKey extends BaseRSAPrivateKey{
     private byte[] encoding;

    public X509RSAPrivateKey(CryptixRSAPrivateKey key)
       throws ClassCastException {
       RSAFactors rsa=(RSAFactors)key;
       
       setRsaParams(key.getExponent(),rsa.getP(),rsa.getQ(),
         rsa.getInverseOfQModP());
     }
       
     public X509RSAPrivateKey(String oid, byte[] parameters, byte[] key)
       throws IOException {
       
       synchronized(CertContext.getSpec()){
         SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"RSA Private key encoding",key);
	    
         /*Everything here is in the key*/
         CoderOperations der_coder=BaseCoder.getInstance("DER");
         ByteArrayInputStream is=new ByteArrayInputStream(key);
         der_coder.init(is);
         ASNObject rsaKey=CertContext.getSpec().getComponent("RSAPrivateKey");
         rsaKey.accept(der_coder,null);

         BigInteger d=(BigInteger)(rsaKey.getComponent("RSAPrivateKey.privateExponent")
           .getValue());
         BigInteger p=(BigInteger)(rsaKey.getComponent("RSAPrivateKey.prime1")
           .getValue());
         BigInteger q=(BigInteger)(rsaKey.getComponent("RSAPrivateKey.prime2")
           .getValue());
         BigInteger u=(BigInteger)(rsaKey.getComponent("RSAPrivateKey.coefficient")
           .getValue());

         SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"RSA Private decoded");
         
         setRsaParams(d,p,q,u);
       }
     }

     public byte[] getEncoded(){
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Decoding private key");

       BigInteger pub,dmp1,dmq1,q1,p1;
       
       if(encoding!=null)
         return encoding;
       
       ByteArrayOutputStream os=new ByteArrayOutputStream();

       p1=getP().subtract(new BigInteger("1"));
       q1=getQ().subtract(new BigInteger("1"));
       pub=getExponent().modInverse(p1.multiply(q1));       
       dmp1=getExponent().mod(p1);
       dmq1=getExponent().mod(q1);
       
       try {
         DERUtils.encodeInteger(new BigInteger("0"),os); // Version
	 DERUtils.encodeInteger(getModulus(),os);
         DERUtils.encodeInteger(pub,os);
	 DERUtils.encodeInteger(getExponent(),os);
	 DERUtils.encodeInteger(getP(),os);
	 DERUtils.encodeInteger(getQ(),os);                  
         DERUtils.encodeInteger(dmp1,os);
         DERUtils.encodeInteger(dmq1,os);
         DERUtils.encodeInteger(getInverseOfQModP(),os);
         
         byte[] tmp=os.toByteArray();
         os.reset();
         DERUtils.encodeSequence(tmp,os);
	 encoding=os.toByteArray();
         return encoding;
       } catch (IOException e){
	 throw new InternalError(e.toString());
       }
     }

     public String getFormat(){
       return "X509";
     }
}
