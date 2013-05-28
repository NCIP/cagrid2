/**
   SSLSessionData.java

   Copyright (C) 1999 Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Wed Jun 16 10:47:37 1999

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

   $Id: SSLSessionData.java,v 1.2 2003/05/10 05:44:46 gawor Exp $

*/

package COM.claymoresystems.ptls;

import java.util.Vector;
import java.util.Date;

public class SSLSessionData {
     byte[] session_id;
     int ssl_version;	  
     byte[] master_secret;
     Vector peerCertificateChain;
     SSLCipherSuite cipher_suite;
     long expiry;
     String lookupKey;
	  
     SSLSessionData(SSLHandshake hs,String key){
       session_id=hs.session_id;
       ssl_version=hs._conn.ssl_version;
       master_secret=hs.master_secret;
       peerCertificateChain=hs._conn.peerCertificateChain;
       cipher_suite=hs.cipher_suite;
       expiry=System.currentTimeMillis()
	 + (hs._conn.getPolicy().getSessionLifetime() * 1000);
       lookupKey=key;
       hs._conn.sessionLookupKey=key;
     }

     void restoreSession(SSLHandshake hs){
       hs.session_id=session_id;
       hs._conn.ssl_version=ssl_version;
       hs.master_secret=master_secret;
       hs._conn.peerCertificateChain=peerCertificateChain;
       hs.cipher_suite=cipher_suite;
       hs._conn.sessionLookupKey=lookupKey;
     }

     byte[] getSessionID(){
       return session_id;
     }

     SSLCipherSuite getCipherSuite(){
       return cipher_suite;
     }

     int getSSLVersion(){
       return ssl_version;
     }

     Vector getPeerCertificateChain(){
       return peerCertificateChain;
     }

     long getExpiryTime(){
       return expiry;
     }

     String getLookupKey(){
       return lookupKey;
     }
}
     

