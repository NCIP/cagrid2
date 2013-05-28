/**
   SSLClientKeyExchange.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sun May  9 17:09:40 1999

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

   $Id: SSLClientKeyExchange.java,v 1.2 2003/05/10 05:44:46 gawor Exp $

*/

package COM.claymoresystems.ptls;

import COM.claymoresystems.crypto.PKCS1Pad;
import COM.claymoresystems.crypto.DHPublicKey;
import COM.claymoresystems.crypto.DHPrivateKey;
import COM.claymoresystems.crypto.Blindable;
import java.math.BigInteger;
import xjava.security.Cipher;
import xjava.security.interfaces.CryptixRSAPrivateKey;
import xjava.security.interfaces.CryptixRSAPublicKey;
import java.io.*;

class SSLClientKeyExchange extends SSLPDU
{
     SSLopaque client_data=new SSLopaque(-65535);

     public int encode (SSLConn conn, OutputStream s)
       throws IOException {
       switch(conn.hs.cipher_suite.getKeyExchangeAlg()){
	 case SSLCipherSuite.SSL_KEX_DH:
	   DHPublicKey dhpub=(DHPublicKey)conn.hs.peerEncryptionKey;
	   DHPrivateKey dhpriv=DHPrivateKey.getInstance();
	   dhpriv.initPrivateKey(dhpub.getg(),
	     dhpub.getp(),conn.hs.rng);
	   client_data.value=dhpriv.getYBytes();
	   conn.hs.pre_master_secret=dhpriv.keyAgree(dhpub,true);
	   return client_data.encode(conn,s);
	 case SSLCipherSuite.SSL_KEX_RSA:
	   try {
	     conn.hs.pre_master_secret=new byte[48];
	     conn.hs.rng.nextBytes(conn.hs.pre_master_secret);
	     conn.hs.pre_master_secret[0]=3;
	     conn.hs.pre_master_secret[1]=(byte)(conn.max_ssl_version & 0xff);
	     
	     Cipher ciph=Cipher.getInstance("RSA", "Cryptix");
	     if(conn.hs.peerEncryptionKey==null)
	       conn.hs.peerEncryptionKey=conn.hs.peerSignatureKey;
	     ciph.initEncrypt(conn.hs.peerEncryptionKey);

	     byte[] pkcs1_padded_data=PKCS1Pad.pkcs1PadBuf
	       (conn.hs.rng,conn.hs.pre_master_secret,
                 conn.hs.peerEncryptionKey);
	     SSLDebug.debug(SSLDebug.DEBUG_CRYPTO, "RSA input",
	       pkcs1_padded_data);
	     byte[] enc_data=ciph.crypt(pkcs1_padded_data);
	     client_data.value=enc_data;
	     SSLDebug.debug(SSLDebug.DEBUG_CRYPTO, "PreMasterSecret",
	       conn.hs.pre_master_secret);
	     SSLDebug.debug(SSLDebug.DEBUG_CRYPTO, "EncryptedPreMasterSecret",
	       enc_data);

	     if(conn.ssl_version >= 0x301){
	       return client_data.encode(conn,s);
	     }
	     else {
	       // SSLv3 implementations don't use the length bytes
	       s.write(enc_data);
	       return enc_data.length;
	     }
	   }
	   catch (Exception e){
	     e.printStackTrace();
	     throw new InternalError(e.toString());
	   }
	 default:
	   throw new InternalError("Inconsistent algorithm");
       }
     }
       
     public int decode(SSLConn conn, InputStream s)
       throws IOException {
       int rb;
       
       switch(conn.hs.cipher_suite.getKeyExchangeAlg()){
	 // In this case it's Y
	 case SSLCipherSuite.SSL_KEX_DH:
	   rb=client_data.decode(conn,s);
	   conn.hs.peerEncryptionKey=new DHPublicKey(
		new BigInteger(1,client_data.value));
	   conn.hs.pre_master_secret=conn.hs.dhEphemeral.keyAgree(
		(DHPublicKey)conn.hs.peerEncryptionKey,false);
	   break;
	 case SSLCipherSuite.SSL_KEX_RSA:
	   byte[] val;
	   
	   if(conn.ssl_version>=0x301){
	     rb=client_data.decode(conn,s);
	     val=client_data.value;
	   }
	   else{
	     // SSLv3 implementations forgot the length
	     // bytes, so we read in the whole thing
	     byte[] in=new byte[512];
	     
	     rb=s.read(in);

	     if(rb<0)
	       throw new SSLException("Short RSA key");
	       
	     val=new byte[rb];

	     System.arraycopy(in,0,val,0,rb);
	   }
	     
	   try {
	     Cipher ciph=Cipher.getInstance("RSABlind");
             java.security.PrivateKey privk=null;
             java.security.PublicKey pubk=null;

             if(conn.hs.rsaEphemeral==null){
               privk=conn.ctx.getPrivateKey();
               pubk=conn.ctx.getPublicKey();
             }
             else{
               privk=conn.hs.rsaEphemeral;
               pubk=conn.hs.rsaEphemeralPublic;
             }
                 
	     ciph.initDecrypt(privk);
             ((Blindable)ciph).setBlindingInfo(conn.hs.rng,
              (CryptixRSAPublicKey)pubk);

	     byte[] decrypted=ciph.crypt(val);

             // Catch any error and fill with random bytes to
             // protect against Bleichenbacher's million message
             // attack
             conn.hs.pre_master_secret=PKCS1Pad.pkcs1UnpadBuf(decrypted,
               PKCS1Pad.DECRYPT,(CryptixRSAPrivateKey)privk);
             if(conn.hs.pre_master_secret.length!=48)
               throw new Exception("Bad PMS length");

             // Check that the PreMasterSecret contains the same
             // first two bytes as the client offered. This prevents
             // rollback attacks
             SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Checking client offered version against RSA block for rollback " + conn.hs.client_offered_version);
             if((conn.hs.pre_master_secret[0]!=
               ((conn.hs.client_offered_version>>8) & 0xff))
               || (conn.hs.pre_master_secret[1]!=
                 ((conn.hs.client_offered_version) & 0xff))){
               // Special case. Some client use the negotiated version.
               // Tolerate this if you're rolling back from TLS to
               // SSLv3 since these are roughly equivalent in security
               // and this was a common error
               if((conn.hs.pre_master_secret[0]==3) &&
                 (conn.hs.pre_master_secret[1]==0) &&
                 conn.hs.client_offered_version==SSLHandshake.TLS_V1_VERSION &&
                 conn.ssl_version==SSLHandshake.SSL_V3_VERSION) {
                 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Accepting rollback to SSLv3 from TLS since this is a common SSLv3/TLS bug");
               }
               else {
                 throw new Exception("Bad PMS version number");
               }
             }
           } catch (Exception e){
             conn.hs.pre_master_secret=new byte[48];

             SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Bad padding. Randomizing PMS");
             conn.ctx.rng.nextBytes(conn.hs.pre_master_secret);
           }

	   break;
	 default:
	   throw new InternalError("Inconsistent algorithm");
       }
       return rb;       
     }

}
       
