/**
   X509RSAPublicKey.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sat Jun 19 20:08:25 1999

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

   $Id: X509RSAPublicKey.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/


package COM.claymoresystems.cert;

import COM.claymoresystems.ptls.SSLDebug;
import xjava.security.interfaces.CryptixRSAPublicKey;
import cryptix.provider.rsa.BaseRSAPublicKey;
import cryptix.asn1.lang.ASNObject;
import cryptix.asn1.lang.ASNSpecification;
import cryptix.asn1.lang.Parser;
import cryptix.asn1.lang.ParseException;
import cryptix.asn1.lang.ASNBitString;
import cryptix.asn1.encoding.BaseCoder;
import cryptix.asn1.encoding.CoderOperations;
import cryptix.asn1.encoding.DER;
import java.security.PublicKey;
import java.math.BigInteger;
import java.io.*;

/** A RSA Public key BER encoded a la PKCS-1 */
public class X509RSAPublicKey extends BaseRSAPublicKey{
     private byte[] encoding;
     private byte[] oid={(byte)0x2a,(byte)0x86,(byte)0x48,(byte)0x86,
                         (byte)0xf7,(byte)0x0d,(byte)0x01,(byte)0x01,
                         (byte)0x01};
     
     public X509RSAPublicKey(String oid, byte[] parameters, byte[] key)
       throws IOException {
       /*Everything here is in the key*/
       encoding=key;

       synchronized(CertContext.getSpec()){       
         SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"RSA Public key encoding",key);       
         if(key[0]!=0)
           throw new IOException("Bad encoded key");
         byte[] enc_key=new byte[key.length-1];
         System.arraycopy(key,1,enc_key,0,enc_key.length);
         ByteArrayInputStream is=new ByteArrayInputStream(enc_key);
         CoderOperations der_coder=BaseCoder.getInstance("DER");
         der_coder.init(is);
         ASNObject rsaKey=CertContext.getSpec().getComponent("RSAPublicKey");
         rsaKey.accept(der_coder,null);

         BigInteger n=(BigInteger)(rsaKey.getComponent("RSAPublicKey.modulus").getValue());
         BigInteger e=(BigInteger)(rsaKey.getComponent("RSAPublicKey.publicExponent").getValue());

         SSLDebug.debug(SSLDebug.DEBUG_CERT,"Modulus ",n.toByteArray());
         SSLDebug.debug(SSLDebug.DEBUG_CERT,"Public ",e.toByteArray());
         
         setRsaParams(n,e);
       }
     }

     public X509RSAPublicKey(CryptixRSAPublicKey k){
       BaseRSAPublicKey key=(BaseRSAPublicKey)k;
       
       setRsaParams(key.getModulus(),key.getExponent());
     }

     public String getFormat(){
       return "PKCS-1";
     }

     public byte[] getEncoded(){
       ByteArrayOutputStream os=new ByteArrayOutputStream();

       if(encoding!=null)
         return encoding;
       
       try { 
	 DERUtils.encodeInteger(getModulus(),os);
	 DERUtils.encodeInteger(getExponent(),os);
         byte[] tmp=os.toByteArray();
         os.reset();
         DERUtils.encodeSequence(tmp,os);
	 byte[] key=os.toByteArray();
         byte[] p=new byte[0];
	 encoding=X509SubjectPublicKeyInfo.encodePublicKey(oid,p,key);
         return encoding;
       } catch (IOException e){
	 throw new InternalError(e.toString());
       }
     }
}
     
