/**
   SSLCertificateVerify.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue Jun  8 11:07:54 1999

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

   $Id: SSLCertificateVerify.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.ptls;

import java.io.*;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import xjava.security.interfaces.CryptixRSAPublicKey;
import COM.claymoresystems.crypto.Blindable;

class SSLCertificateVerify extends SSLPDU
{
     SSLopaque signature=new SSLopaque(-65535);
     byte[] toBeSigned;

     private String getCVAlg(String alg) {
       if(alg.equals("DSA"))
         return("RawDSA");
       if(alg.equals("RSA"))
         return("RawRSA");
       
       throw new InternalError("Bogus algorithm");
     }
       
     public SSLCertificateVerify(SSLConn conn,SSLHandshake hs,boolean mine){
       switch(conn.ssl_version){
	 case SSLHandshake.SSL_V3_VERSION:
	   toBeSigned=SSLv3CertificateVerify.computeToBeSigned(hs,mine);
	   break;
	 case SSLHandshake.TLS_V1_VERSION:
	   toBeSigned=TLSCertificateVerify.computeToBeSigned(hs,mine);
	   break;
	 default:
	   throw new InternalError("Bogus version number");
       }
     }

     public int encode(SSLConn conn,OutputStream s)
       throws IOException {
       try {
	 PrivateKey pk=conn.ctx.getPrivateKey();

	 String alg=getCVAlg(pk.getAlgorithm());
	 
	 Signature sig=Signature.getInstance(alg);

	 sig.initSign(pk);
         
         if(alg.equals("RawRSA")){
           ((Blindable)sig).setBlindingInfo(conn.hs.rng,
           (CryptixRSAPublicKey)conn.ctx.getPublicKey());
         }  	 
	 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Certificate verify toBeSigned",
	   toBeSigned);

         // Test
         // toBeSigned[0]++;
         
	 // For DSA we sign only the SHA
	 if(alg.equals("RawDSA")){
	   sig.setParameter("SecureRandom",conn.hs.rng);
	   sig.update(toBeSigned,16,20);
	 }
	 else{
	   sig.update(toBeSigned,0,toBeSigned.length);
	 }
         
	 byte[] sig_bytes=sig.sign();

         // Test
         // sig_bytes[sig_bytes.length-1]++;
	 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Certificate verify signature",
	   sig_bytes);

	 signature.value=sig_bytes;

	 return signature.encode(conn,s);
       }
       catch (java.security.NoSuchAlgorithmException e){
	 throw new InternalError(e.toString());
       }
       catch (java.security.SignatureException e){
	 throw new InternalError(e.toString());
       }
       catch (java.security.InvalidKeyException e){
	 throw new InternalError(e.toString());
       }
     }

     public int decode(SSLConn conn, InputStream s)
       throws IOException {
       int rb=0;
       
       try {
	 PublicKey pk=conn.hs.peerSignatureKey;
	 String alg=getCVAlg(pk.getAlgorithm());
	 
	 Signature sig=Signature.getInstance(alg);
	 sig.initVerify(pk);

	 rb=signature.decode(conn,s);

	 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Certificate verify toBeSigned",
	   toBeSigned);

	 // For DSA we sign only the SHA
	 if(alg.equals("RawDSA")){
	   sig.update(toBeSigned,16,20);
	 }
	 else{
	   sig.update(toBeSigned,0,toBeSigned.length);
	 }

	 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Certificate verify signature",
	   signature.value);

	 if(!sig.verify(signature.value))
	   conn.alert(SSLAlertX.TLS_ALERT_DECRYPT_ERROR);
       } catch (java.security.NoSuchAlgorithmException e){
	 throw new InternalError(e.toString());
       } catch (java.security.InvalidKeyException e){
         conn.alert(SSLAlertX.TLS_ALERT_DECRYPT_ERROR);
       } catch (java.security.SignatureException e){
         conn.alert(SSLAlertX.TLS_ALERT_DECRYPT_ERROR);
       } 

       return rb;
     }
}
