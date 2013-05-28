/**
   SSLCipherSuite.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sat May  8 21:38:13 1999

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

   $Id: SSLCipherSuite.java,v 1.2 2003/05/10 05:44:45 gawor Exp $

*/

package COM.claymoresystems.ptls;

import COM.claymoresystems.sslg.SSLPolicyInt;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import xjava.security.interfaces.CryptixRSAPrivateKey;
import xjava.security.interfaces.CryptixRSAPublicKey;

public class SSLCipherSuite {
     private int cipher_suite;
     private int keyExchangeAlg;
     private int signatureAlg;
     private String digestAlg;
     private int digestBytes;
     private String cipherAlg;
     private int cipherBytes;
     private int effectiveBytes;
     private boolean require_ske;
     private boolean export;
     private boolean block_cipher;

     public static final int SSL_KEX_DH=1;
     public static final int SSL_KEX_RSA=2;

     public static final int SSL_SIG_DSS=1;
     public static final int SSL_SIG_RSA=2;
     public static final int SSL_SIG_anon=3;
     
    private static final SSLCipherSuite[] master_list={
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_RSA_WITH_NULL_MD5, 
	    SSL_KEX_RSA, SSL_SIG_RSA, "MD5", 16, 
	    "NULL", 0, 0, false, false, false),
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_RSA_WITH_NULL_SHA, 
	    SSL_KEX_RSA, SSL_SIG_RSA, "SHA", 20, 
	    "NULL", 0, 0, false, false, false),
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_RSA_EXPORT_WITH_RC4_40_MD5, 
	    SSL_KEX_RSA, SSL_SIG_RSA, "MD5", 16, 
	    "RC4", 16, 5, false, true, false),
//strip EXPORT	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_RSA_WITH_RC4_128_MD5, 
	    SSL_KEX_RSA, SSL_SIG_RSA, "MD5", 16, 
	    "RC4", 16, 16, false, false, false),
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_RSA_WITH_RC4_128_SHA, 
	    SSL_KEX_RSA, SSL_SIG_RSA, "SHA", 20, 
	    "RC4", 16, 16, false, false, false),
//estrip	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5, 
	    SSL_KEX_RSA, SSL_SIG_RSA, "MD5", 16, 
	    "RC2", 16, 5, false, true, true),
//strip	EXPORT
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_RSA_WITH_IDEA_CBC_SHA, 
	    SSL_KEX_RSA, SSL_SIG_RSA, "SHA", 20, 
	    "IDEA", 16, 16, false, false, true),
//estrip 	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_RSA_EXPORT_WITH_DES40_CBC_SHA,
	    SSL_KEX_RSA, SSL_SIG_RSA, "SHA", 20, 
	    "DES", 8, 5, false, true, true),
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_RSA_WITH_DES_CBC_SHA, 
	    SSL_KEX_RSA, SSL_SIG_RSA, "SHA", 20, 
	    "DES", 8, 8, false, false, true),
//strip EXPORT	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_RSA_WITH_3DES_EDE_CBC_SHA, 
	    SSL_KEX_RSA, SSL_SIG_RSA, "SHA", 20, 
	    "DES-EDE3", 24, 24, false, false, true),
//estrip	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA, 
	    SSL_KEX_DH, SSL_SIG_DSS, "SHA", 20, 
	    "DES", 8, 5, false, true, true),
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DH_DSS_WITH_DES_CBC_SHA, 
	    SSL_KEX_DH, SSL_SIG_DSS, "SHA", 20, 
	    "DES", 8, 8, false, false, true),
//strip EXPORT	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA, 
	    SSL_KEX_DH, SSL_SIG_DSS, "SHA", 20, 
	    "DES-EDE3", 24, 24, false, false, true),
//estrip	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA,
	    SSL_KEX_DH, SSL_SIG_RSA, "SHA", 20, 
	    "DES", 8, 5, false, true, true),
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DH_RSA_WITH_DES_CBC_SHA, 
	    SSL_KEX_DH, SSL_SIG_RSA, "SHA", 20, 
	    "DES", 8, 8, false, false, true),
//strip EXPORT	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA, 
	    SSL_KEX_DH, SSL_SIG_RSA, "SHA", 20, 
	    "DES-EDE3", 24, 24, false, false, true),
//estrip	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA,
	    SSL_KEX_DH, SSL_SIG_DSS, "SHA", 20, 
	    "DES", 8, 5, true, true, true),
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DHE_DSS_WITH_DES_CBC_SHA, 
	    SSL_KEX_DH, SSL_SIG_DSS, "SHA", 20, 
	    "DES", 8, 8, true, false, true),
//strip EXPORT	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA, 
	    SSL_KEX_DH, SSL_SIG_DSS, "SHA", 20, 
	    "DES-EDE3", 24, 24, true, false, true),
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DH_anon_EXPORT_WITH_RC4_40_MD5, 
	    SSL_KEX_DH, SSL_SIG_anon, "MD5", 16, 
	    "RC4", 16, 5, false, true, false),
//strip EXPORT	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DH_anon_WITH_RC4_128_MD5, 
	    SSL_KEX_DH, SSL_SIG_anon, "MD5", 16, 
	    "RC4", 16, 16, false, false, false),
//estrip	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA, 
	    SSL_KEX_DH, SSL_SIG_anon, "SHA", 20, 
	    "DES", 8, 8, false, false, true),
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DH_anon_WITH_DES_CBC_SHA, 
	    SSL_KEX_DH, SSL_SIG_anon, "SHA", 20, 
	    "DES", 8, 8, false, false, true),
//strip EXPORT	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DH_anon_WITH_3DES_EDE_CBC_SHA, 
	    SSL_KEX_DH, SSL_SIG_anon, "SHA", 20, 
	    "DES-EDE3", 24, 24, false, false, true),
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DHE_DSS_WITH_RC4_128_SHA, 
	    SSL_KEX_DH, SSL_SIG_DSS, "SHA", 20, 
	    "RC4", 16, 16, true, false, false),
//estrip	
	new SSLCipherSuite(
	    SSLPolicyInt.TLS_DHE_DSS_WITH_NULL_SHA, 
	    SSL_KEX_DH, SSL_SIG_DSS, "SHA", 20, 
	    "NULL", 0, 0, true, false, false),
    };
     
     public SSLCipherSuite(int cipher_suite_,
       int keyExchange,
       int signature,
       String digest,
       int digestBytes_,
       String cipher,
       int cipherBytes_,
       int effectiveBytes_,
       boolean require_ske_,
       boolean export_,
       boolean block_cipher_){
       cipher_suite=cipher_suite_;

       keyExchangeAlg=keyExchange;
       signatureAlg=signature;
       digestAlg=digest;
       digestBytes=digestBytes_;
       cipherAlg=cipher;
       cipherBytes=cipherBytes_;
       effectiveBytes=effectiveBytes_;
       require_ske=require_ske_;
       export=export_;
       block_cipher=block_cipher_;
     }
     
     public static SSLCipherSuite findCipherSuite(int cs){
       int i;

       for(i=0;i<master_list.length;i++){
	 if(master_list[i].cipher_suite==cs){
	   return master_list[i];
	 }
       }

       throw new Error("Unknown cipher suite  "+cs);
     }

     int getValue(){
       return cipher_suite;
     }

     String getName(){
       return SSLPolicyInt.getCipherSuiteName(cipher_suite);
     }

     int getKeyExchangeAlg(){
       return keyExchangeAlg;
     }

     int getSignatureAlg(){
       return signatureAlg;
     }

     String getSignatureAlgNorm(){
       switch(signatureAlg){
	 case SSL_SIG_RSA:
	   return(digestAlg + "/RSA/PKCS#1");
	 case SSL_SIG_DSS:
	   return("DSA");
	 default:
	   throw new InternalError("Bogus algorithm");
       }
     }

     String getSignatureAlgBase(){
       switch(signatureAlg){
	 case SSL_SIG_RSA:
	   return("RSA");
	 case SSL_SIG_DSS:
	   return("DSA");
	 default:
	   throw new InternalError("Bogus algorithm");
       }
     }

     String getSignatureAlgCV(){
       switch(signatureAlg){
	 case SSL_SIG_RSA:
	   return("RawRSA");
	 case SSL_SIG_DSS:
	   return("RawDSA");
	 default:
	   throw new InternalError("Bogus algorithm");
       }
     }
			 
    public String getDigestAlg(){
       return digestAlg;
     }

    public int getDigestOutputLength(){
       return digestBytes;
     }
     
    public String getCipherAlg(){
       String alg;

       alg=cipherAlg;

       if(blockCipherP()) alg += "/CBC";
       
       return alg;
     }

     int getCipherKeyLength(){
       return cipherBytes;
     }

     int getCipherEffectiveKeyLength(){
       return effectiveBytes;
     }
     
     boolean requireServerKeyExchangeP(PrivateKey key){
       if(require_ske) return true;
       if(!exportableP()) return false;

       if(keyExchangeAlg==SSL_KEX_RSA){
	 BigInteger mod=((CryptixRSAPrivateKey)key).getModulus();
	 
	 if(mod.bitLength()>512)
	   return true;
       }

       return false;
     }

     boolean allowServerKeyExchangeP(PublicKey key){
       if(require_ske) return true;
       if(!exportableP()) return false;

       if(keyExchangeAlg==SSL_KEX_RSA){
	 BigInteger mod=((CryptixRSAPublicKey)key).getModulus();
	 
	 if(mod.bitLength()>512)
	   return true;
       }

       return false;
     }
     
     boolean exportableP(){
       return export;
     }

     boolean blockCipherP(){
       return block_cipher;
     }
     
}
