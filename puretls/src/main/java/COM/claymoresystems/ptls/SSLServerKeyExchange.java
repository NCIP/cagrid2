/**
   SSLServerKeyExchange.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sat May  8 21:33:02 1999

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

   $Id: SSLServerKeyExchange.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/


package COM.claymoresystems.ptls;
import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.security.Signature;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.MessageDigest;
import xjava.security.interfaces.CryptixRSAPublicKey;
import cryptix.provider.rsa.RawRSAPublicKey;
import COM.claymoresystems.crypto.DHPublicKey;
import COM.claymoresystems.crypto.DHPrivateKey;
import COM.claymoresystems.crypto.Blindable;

class SSLServerKeyExchange extends SSLPDU
{
     SSLDHParams dh_params;
     SSLRSAParams rsa_params;
     SSLopaque signature=new SSLopaque(-65535);
     SSLPDU par;
     int wb=0;
     String algorithm;
     
     public int encode(SSLConn conn, OutputStream s)
       throws IOException{
       ByteArrayOutputStream kex_os=new ByteArrayOutputStream();
       
       switch(conn.hs.cipher_suite.getKeyExchangeAlg()){
	 case SSLCipherSuite.SSL_KEX_DH:
	   conn.hs.dhEphemeral=conn.ctx.getEphemeralDHPrivateKey
             (conn.policy.dhAlwaysEphemeralP());
	   par=dh_params=new SSLDHParams(conn.hs.dhEphemeral);
	   break;
	 case SSLCipherSuite.SSL_KEX_RSA:
	   conn.hs.rsaEphemeral=conn.ctx.getEphemeralRSAPrivateKey();
           conn.hs.rsaEphemeralPublic=conn.ctx.getEphemeralRSAPublicKey();
	   par=rsa_params=new SSLRSAParams(conn.ctx.getEphemeralRSAPublicKey());
           break;
	 default:
	   throw new Error("Unknown key exchange algorithm");
       }
       par.encode(conn,kex_os);			
       byte[] kex_enc=kex_os.toByteArray();

       try {
         byte[] toBeSigned;

	 // Sign the buffer
	 PrivateKey pk=conn.ctx.getPrivateKey();
	 String alg=conn.hs.cipher_suite.getSignatureAlgCV();
	 Signature sigChecker;

	 if(alg.equals("RawDSA")){
	   sigChecker=Signature.getInstance(alg,
	     LoadProviders.getDSAProvider());
	   sigChecker.setParameter("SecureRandom",
	     conn.hs.rng);
	 }
	 else if(alg.equals("RawRSA")){
	   sigChecker=Signature.getInstance(alg);
           ((Blindable)sigChecker).setBlindingInfo(conn.hs.rng,
          (CryptixRSAPublicKey)conn.ctx.getPublicKey());
         }
         else{
           throw new Exception("Unknown key type");
         }
	 sigChecker.initSign(pk);

         toBeSigned=getToBeSigned(conn,alg,kex_enc);
         
         // Test
         // toBeSigned[0]++;
         
         sigChecker.update(toBeSigned);

	 byte[] sig=sigChecker.sign();

	 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Signed Data", kex_enc);
	 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Signature Data", sig);

         // Test
         //sig[sig.length-1]++;
         
	 signature.value=sig;
       } catch (Exception e){
	 throw new InternalError(e.toString());
       }

       wb=par.encode(conn,s);
       wb+=signature.encode(conn,s);

       return wb;
     }       
       
     public int decode(SSLConn conn, InputStream s)
       throws Error, java.io.IOException{
       int rb;
       PublicKey tmp_pk=null;
       PublicKey pk=conn.hs.peerSignatureKey;
       String algorithm;

       ByteArrayOutputStream kex_os=new ByteArrayOutputStream();

       if(!conn.hs.cipher_suite.allowServerKeyExchangeP(pk)){
         conn.alert(SSLAlertX.TLS_ALERT_ILLEGAL_PARAMETER);
       }
       switch(conn.hs.cipher_suite.getKeyExchangeAlg()){
	 case SSLCipherSuite.SSL_KEX_DH:
	   dh_params=new SSLDHParams();
	   rb=dh_params.decode(conn,s);
	   dh_params.encode(conn,kex_os);
	   tmp_pk=new DHPublicKey(
		new BigInteger(1,dh_params.DH_g.value),
		new BigInteger(1,dh_params.DH_p.value),
		new BigInteger(1,dh_params.DH_Ys.value));
	   break;
	 case SSLCipherSuite.SSL_KEX_RSA:
	   rsa_params=new SSLRSAParams();
	   rb=rsa_params.decode(conn,s);
	   rsa_params.encode(conn,kex_os);
           BigInteger mod=new BigInteger(1,rsa_params.RSA_modulus.value);
           BigInteger exp=new BigInteger(1,rsa_params.RSA_exponent.value);
           if(mod.bitLength()>512)
             conn.alert(SSLAlertX.TLS_ALERT_ILLEGAL_PARAMETER);
	   tmp_pk=new RawRSAPublicKey(mod,exp);

	   break;
	 default:
	   throw new Error("Unknown key exchange algorithm");
       }
       int params_size=rb;
       
       rb+=signature.decode(conn,s);

       byte[] kex_enc=kex_os.toByteArray();

       if(kex_enc.length!=params_size)
         throw new InternalError("Inconsistency in param size");
       
       try {
	 // Check the signature
         String alg=conn.hs.cipher_suite.getSignatureAlgCV();
	 Signature sigChecker=Signature.getInstance(alg);

         checkSignatureKey(conn,pk,alg);
              
	 sigChecker.initVerify(pk);

         byte[] toBeSigned=getToBeSigned(conn,alg,kex_enc);
         sigChecker.update(toBeSigned);
	 
	 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Signed Data", kex_enc);
	 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Signature Data", signature.value);
	 if(!sigChecker.verify(signature.value))
	   conn.alert(SSLAlertX.TLS_ALERT_DECRYPT_ERROR);
       } catch (java.security.NoSuchAlgorithmException e){
	 throw new InternalError(e.toString());
       } catch (java.security.InvalidKeyException e){
         conn.alert(SSLAlertX.TLS_ALERT_DECRYPT_ERROR);
       } catch (java.security.SignatureException e){
         conn.alert(SSLAlertX.TLS_ALERT_DECRYPT_ERROR);
       } 
       
       conn.hs.peerEncryptionKey=tmp_pk;

       return rb;
     }

     private byte[] getToBeSigned(SSLConn conn,String alg,
       byte[] kex_enc) throws java.security.NoSuchAlgorithmException{
       byte[] toBeSigned;
       MessageDigest md5,sha;
       
       sha=MessageDigest.getInstance("SHA-1");

       sha.update(conn.hs.client_random);
       sha.update(conn.hs.server_random);
       sha.update(kex_enc);

       if(alg.equals("RawRSA")){
         md5=MessageDigest.getInstance("MD5");           
         md5.update(conn.hs.client_random);
         md5.update(conn.hs.server_random);
         md5.update(kex_enc);

         byte[] md5dig=md5.digest();
         byte[] shadig=sha.digest();
         toBeSigned=new byte[36];
           
         System.arraycopy(md5dig,0,toBeSigned,0,md5dig.length);
         System.arraycopy(shadig,0,toBeSigned,16,shadig.length);           
       }
       else
         toBeSigned=sha.digest();

       return(toBeSigned);
     }

     private void checkSignatureKey(SSLConn conn,PublicKey key,String alg)
       throws IOException {
       if(alg.equals("RawRSA")){
         if(!(key instanceof xjava.security.interfaces.CryptixRSAPublicKey)){
           conn.alert(SSLAlertX.TLS_ALERT_ILLEGAL_PARAMETER);
         }
       }
       else if(alg.equals("RawDSA")){
         if(!(key instanceof java.security.interfaces.DSAPublicKey)){
           conn.alert(SSLAlertX.TLS_ALERT_ILLEGAL_PARAMETER);
         }
       }
       else {
         throw new InternalError("Unknown Algorithm");
       }
     }
}
       
       
     
