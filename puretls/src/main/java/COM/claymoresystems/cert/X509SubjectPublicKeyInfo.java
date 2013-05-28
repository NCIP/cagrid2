/**
   X509SubjectPublicKeyInfo.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sun May 23 16:10:00 1999

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

   $Id: X509SubjectPublicKeyInfo.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.cert;

import cryptix.asn1.lang.ASNObject;
import cryptix.asn1.lang.ASNSpecification;
import cryptix.asn1.lang.Parser;
import cryptix.asn1.lang.ParseException;
import cryptix.asn1.lang.ASNBitString;
import cryptix.asn1.encoding.BaseCoder;
import cryptix.asn1.encoding.CoderOperations;
import cryptix.asn1.encoding.DER;
import COM.claymoresystems.ptls.SSLDebug;
import java.util.Hashtable;

import java.security.PublicKey;
import java.io.*;

public class X509SubjectPublicKeyInfo {
     static Hashtable algorithmMap=new Hashtable();

     /** Construct a public key based on this SubjectPublicKeyInfo

	 @param spkiDER the encoded public key

	 @return a public key

	 @exception IOException in case of a parse error
     */	 
     public static PublicKey createPublicKey(byte[] spkiDER)
       throws IOException {
       ASNObject spki;
       String algorithm;

       synchronized(CertContext.getSpec()){
         SSLDebug.debug(SSLDebug.DEBUG_CERT,"SPKI encoding",
           spkiDER);
         CoderOperations der_coder=BaseCoder.getInstance("DER");
         ByteArrayInputStream is=new ByteArrayInputStream(spkiDER);
//         DebuggingByteArrayInputStream dis=new
//           DebuggingByteArrayInputStream(is);
         der_coder.init(is);

         spki=CertContext.getSpec().getComponent("SubjectPublicKeyInfo");
         spki.accept(der_coder,null);
       
         ASNObject tmp=spki.getComponent("SubjectPublicKeyInfo.algorithm");
         ASNObject params=tmp.getComponent("AlgorithmIdentifier.parameters");
       
         tmp=tmp.getComponent("AlgorithmIdentifier.algorithm");
         algorithm=(String)tmp.getValue();

         byte[] param=(byte[])params.getValue();
       
         tmp=spki.getComponent("SubjectPublicKeyInfo.subjectPublicKey");
         byte[] pk=(byte[])tmp.getValue();

         if(algorithm.equals("1.2.840.113549.1.1.1")){
           return new X509RSAPublicKey(algorithm,param,pk);
         }
         else if(algorithm.equals("1.2.840.10040.4.1")){
           return new X509DSAPublicKey(algorithm,param,pk);
         }
         else{
           // Uh, don't we want another exception?
           throw new IOException("Unrecognized OID for key" + algorithm);
         }
       }
    }

     public static byte[] encodePublicKey(byte[] OID,byte[] params,byte[] key)
      throws IOException {
      ByteArrayOutputStream os=new ByteArrayOutputStream();

      DERUtils.encodeOID(OID,os);
      os.write(params);
      byte[] algId_c=os.toByteArray();
      os.reset();

      DERUtils.encodeSequence(algId_c,os);
      DERUtils.encodeBitString(key,os);

      byte[] tmp=os.toByteArray();
      os.reset();

      DERUtils.encodeSequence(tmp,os);
      return os.toByteArray();
    }

}
/*

     static {
       algorithmMap.put("1.2.840.10040.4.1","COM.claymoresystems.certs.X509DSAPublicKey");
     }

     private static Class[] constructorParameters={CertContext,String,byte[], byte[]};

       Object algorithmClassName=algorithmMap.get(algorithm);
       if(algorithmClassName==null)
	 throw new InternalError("Unsupported Algorithm"); // TODO: Throw a better exception

       Class algorithmClass=Class.forName((String)algorithmClassName);
       if(!algorithmClass)
	 throw new InternalError("Algorithm in map but class "+algorithmClassName+" not available");
       Constructor constructor=algorithmClass.getConstructor(constructorParameters);

       Object[] parameters=new Object[4];
       parameters[0]=ctx;
       parameters[1]=algorithmClassName;
       parameters[2]=param;
       parameters[3]=pk;
       
       return (PublicKey)algorithmClass.newInstance(parameters);
*/
