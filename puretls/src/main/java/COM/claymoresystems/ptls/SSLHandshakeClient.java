/**
   SSLHandshakeClient.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Fri May  7 16:23:10 1999

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

   $Id: SSLHandshakeClient.java,v 1.3 2004/01/09 22:34:07 gawor Exp $

*/

package COM.claymoresystems.ptls;
import COM.claymoresystems.sslg.*;
import COM.claymoresystems.cert.*;
import COM.claymoresystems.crypto.*;
import java.io.*;
import java.util.*;
import java.math.BigInteger;
import xjava.security.Cipher;
import java.security.PrivateKey;

class SSLHandshakeClient extends SSLHandshake
{
     public final int SSL_HS_HANDSHAKE_START		    = 0;
     public final int SSL_HS_SENT_CLIENT_HELLO = 1;
     public final int SSL_HS_RECEIVED_SERVER_HELLO	    = 2;
     public final int SSL_HS_RECEIVED_CERTIFICATE	    = 3;
     public final int SSL_HS_RECEIVED_SERVER_KEY_EXCHANGE =4;
     public final int SSL_HS_RECEIVED_CERTIFICATE_REQUEST=5;
     public final int SSL_HS_RECEIVED_SERVER_HELLO_DONE=6;

     boolean resume=false;
     SSLSessionData possibleResume;
     boolean clientAuth=false;
     
     public SSLHandshakeClient(SSLConn c){
       super(c);
       client=true;
     }

     protected void filterCipherSuites(PrivateKey key,SSLPolicyInt policy){
       cipher_suites=new Vector();
       short[] policySuites=_conn.getPolicy().getCipherSuites();
       
       for(int i=0;i<policySuites.length;i++){
	 SSLCipherSuite cs=SSLCipherSuite.findCipherSuite(policySuites[i]);

	 if(cs==null){
	   SSLDebug.debug(SSLDebug.DEBUG_INIT,
	     "Rejecting unrecognized cipher suite" + policySuites[i]);
	   continue;
	 }

	 SSLDebug.debug(SSLDebug.DEBUG_INIT,"Accepting cipher suite: " +
	   cs.getName());
	 cipher_suites.addElement((Object)cs);
       }
     }
    
    public boolean processTokens()
	throws IOException {
	InputStream is=null;
	int type=-1;
	
	if(state==SSL_HS_HANDSHAKE_START){
	    sendClientHello();
	    state=SSL_HS_SENT_CLIENT_HELLO;
       }
	
	if  (_conn.sock_in.available() == 0 &&
	     _conn.sock_in_hp.available() == 0) {
	    return false;
	}
	
	SSLHandshakeHdr hdr=new SSLHandshakeHdr();
	
       is=recvHandshakeToken(_conn,hdr);
       if (is == null) {
	   return false;
       }
       
       type=hdr.ct.value;

       _conn.debug(SSLDebug.DEBUG_STATE,
	 "Processing handshake message of type " + type);
       switch(type){
	 case SSL_HT_SERVER_HELLO:
	   stateAssert(SSL_HS_SENT_CLIENT_HELLO);
	   recvServerHello(is);
	   if(!resume)
	     stateChange(SSL_HS_RECEIVED_SERVER_HELLO);
	   else
	     stateChange(SSL_HS_WAIT_FOR_CHANGE_CIPHER_SPECS);
	   break;
	 case SSL_HT_CERTIFICATE:
	   stateAssert(SSL_HS_RECEIVED_SERVER_HELLO);
	   recvCertificate(is);
	   stateChange(SSL_HS_RECEIVED_CERTIFICATE);
	   break;
	 case SSL_HT_SERVER_KEY_EXCHANGE:
	   stateAssert(SSL_HS_RECEIVED_CERTIFICATE);
	   recvServerKeyExchange(is);
	   stateChange(SSL_HS_RECEIVED_SERVER_KEY_EXCHANGE);
	   break;
	 case SSL_HT_CERTIFICATE_REQUEST:
	   stateAssert(SSL_HS_RECEIVED_SERVER_KEY_EXCHANGE,
	     SSL_HS_RECEIVED_CERTIFICATE);
	   recvCertificateRequest(is);
	   stateChange(SSL_HS_RECEIVED_CERTIFICATE_REQUEST);
	   break;
	 case SSL_HT_SERVER_HELLO_DONE:
	   stateAssert(SSL_HS_RECEIVED_CERTIFICATE,
	     SSL_HS_RECEIVED_SERVER_KEY_EXCHANGE,
	     SSL_HS_RECEIVED_CERTIFICATE_REQUEST);
	   if(clientAuth)
	     sendCertificate();
	   sendClientKeyExchange();
	   if(clientAuth)
	     sendCertificateVerify();
	   sendChangeCipherSpec();
	   sendFinished();
	   stateChange(SSL_HS_WAIT_FOR_CHANGE_CIPHER_SPECS);
	   break;
	 case SSL_HT_FINISHED:
	   stateAssert(SSL_HS_WAIT_FOR_FINISHED);
	   recvFinished(is);
	   if(resume){
	     sendChangeCipherSpec();	     
	     sendFinished();
	   }
	   if(session_id.length!=0)
	     storeSession(sessionLookupKey());
	   stateChange(SSL_HANDSHAKE_FINISHED);
	   break;
	 default:
	   _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
       }

       // Check to make sure we've ready the entire message
       int x=is.read();
       if(x!=-1)
         _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);

       return true;
     }

     public void handshakeContinue()
       throws java.io.IOException {
       InputStream is=null;
       int type=-1;
       
       if(state==SSL_HS_HANDSHAKE_START){
	 sendClientHello();
	 state=SSL_HS_SENT_CLIENT_HELLO;
       }

       SSLHandshakeHdr hdr=new SSLHandshakeHdr();

       is=recvHandshakeMsg(_conn,hdr);
       type=hdr.ct.value;

       _conn.debug(SSLDebug.DEBUG_STATE,
	 "Processing handshake message of type " + type);
       switch(type){
	 case SSL_HT_SERVER_HELLO:
	   stateAssert(SSL_HS_SENT_CLIENT_HELLO);
	   recvServerHello(is);
	   if(!resume)
	     stateChange(SSL_HS_RECEIVED_SERVER_HELLO);
	   else
	     stateChange(SSL_HS_WAIT_FOR_CHANGE_CIPHER_SPECS);
	   break;
	 case SSL_HT_CERTIFICATE:
	   stateAssert(SSL_HS_RECEIVED_SERVER_HELLO);
	   recvCertificate(is);
	   stateChange(SSL_HS_RECEIVED_CERTIFICATE);
	   break;
	 case SSL_HT_SERVER_KEY_EXCHANGE:
	   stateAssert(SSL_HS_RECEIVED_CERTIFICATE);
	   recvServerKeyExchange(is);
	   stateChange(SSL_HS_RECEIVED_SERVER_KEY_EXCHANGE);
	   break;
	 case SSL_HT_CERTIFICATE_REQUEST:
	   stateAssert(SSL_HS_RECEIVED_SERVER_KEY_EXCHANGE,
	     SSL_HS_RECEIVED_CERTIFICATE);
	   recvCertificateRequest(is);
	   stateChange(SSL_HS_RECEIVED_CERTIFICATE_REQUEST);
	   break;
	 case SSL_HT_SERVER_HELLO_DONE:
	   stateAssert(SSL_HS_RECEIVED_CERTIFICATE,
	     SSL_HS_RECEIVED_SERVER_KEY_EXCHANGE,
	     SSL_HS_RECEIVED_CERTIFICATE_REQUEST);
	   if(clientAuth)
	     sendCertificate();
	   sendClientKeyExchange();
	   if(clientAuth)
	     sendCertificateVerify();
	   sendChangeCipherSpec();
	   sendFinished();
	   stateChange(SSL_HS_WAIT_FOR_CHANGE_CIPHER_SPECS);
	   break;
	 case SSL_HT_FINISHED:
	   stateAssert(SSL_HS_WAIT_FOR_FINISHED);
	   recvFinished(is);
	   if(resume){
	     sendChangeCipherSpec();	     
	     sendFinished();
	   }
	   if(session_id.length!=0)
	     storeSession(sessionLookupKey());
	   stateChange(SSL_HANDSHAKE_FINISHED);
	   break;
	 default:
	   _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
       }

       // Check to make sure we've ready the entire message
       int x=is.read();
       if(x!=-1)
         _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
     }

     private void sendClientHello()
       throws java.io.IOException {
       SSLClientHello ch=new SSLClientHello();
       byte[] sid=new byte[0];
       
       int i;

       possibleResume=findSession(sessionLookupKey());
       
       ch.client_version.value=_conn.ssl_version;
       makeRandomValue(client_random);
       ch.random.value=client_random;

       if(possibleResume ==null){
	 ch.session_id.value=sid;
       }
       else{
	 ch.session_id.value=possibleResume.getSessionID();
       }
	 
       //TODO: Make sure that if we're resuming that cpher is on the list
       Vector cs=new Vector();
       
       for(i=0;i<cipher_suites.size();i++){
	 SSLCipherSuite suite=(SSLCipherSuite)cipher_suites.elementAt(i);
	 cs.addElement(new SSLuint16(suite.getValue()));
       }

       ch.cipher_suites=new SSLvector(-65535,cs);
	 
       // There's only one valid compression method (0);
       Vector cm=new Vector();
       cm.addElement(new SSLuint8(0));
       ch.compression_methods=new SSLvector(-255,cm);

       sendHandshakeMsg(_conn,SSL_HT_CLIENT_HELLO,ch);
       _conn.sock_out.flush();
     }

     private void recvServerHello(InputStream is)
       throws java.io.IOException {
       SSLServerHello sh=new SSLServerHello();

       sh.decode(_conn,is);

       if(sh.server_version.value<SSL_V3_VERSION ||
	 sh.server_version.value>_conn.ssl_version)
	 _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
       _conn.ssl_version=sh.server_version.value;
       System.arraycopy(sh.random.value,0,server_random,0,32);
       
       session_id=(byte[])sh.session_id.value;
       SSLDebug.debug(SSLDebug.DEBUG_MSG,"Received Session ID",session_id);
       
       if((session_id.length!=0) && (possibleResume!=null)){
	 if(cryptix.util.core.ArrayUtil.areEqual(session_id,
	   possibleResume.getSessionID())){
	   restoreSession(possibleResume);

	   //They must use the same cipherSuite
	   if(sh.cipher_suite.value != cipher_suite.getValue())
	     _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
	   
	   resume=true;
	   computeNextCipherStates();	 
	   SSLDebug.debug(SSLDebug.DEBUG_STATE,"Resuming...");
	   return;
	 }
       }

       cipher_suite=null;
       for(int i=0;i<cipher_suites.size();i++){
	 SSLCipherSuite suite=(SSLCipherSuite)cipher_suites.elementAt(i);

	 if(suite.getValue()==sh.cipher_suite.value){
	   cipher_suite=suite;
	   break;
	 }
       }

       // They chose a cipher not on our list
       if(cipher_suite==null)
	 _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
	     
       SSLDebug.debug(SSLDebug.DEBUG_HANDSHAKE,"Server chose cipher" +
	 cipher_suite.getName());
	 
       if(sh.compression_method.value != 0)
	 _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
     }

     private void recvServerKeyExchange(InputStream is)
       throws java.io.IOException {
       SSLServerKeyExchange sk=new SSLServerKeyExchange();

       sk.decode(_conn,is);
     }
     
     private void recvCertificateRequest(InputStream is)
       throws java.io.IOException {
       SSLCertificateRequest cr=new SSLCertificateRequest();

       cr.decode(_conn,is);
       
       // TODO: Actually pay attention to what they sent us in the
       // CA list
       // and if it doesn't match, don't send out certificate
       clientAuth=true;
     }

     public void sendCertificate()
       throws IOException {
       Vector certs=_conn.ctx.getCertificateChain();       
       
       if(certs==null){
         clientAuth=false;
         
         switch(_conn.ssl_version){
           case SSLHandshake.SSL_V3_VERSION:
             _conn.sendAlertNoException
               (SSLAlertX.SSL_ALERT_NO_CERTIFICATE,false);
             return;
             // break;
           case SSLHandshake.TLS_V1_VERSION:
             certs=new Vector();
             break;
           default:
             throw new InternalError("Inconsistent version");
         }
       }
       sendCertificate(certs);
     }
     
     private void sendCertificateVerify()
       throws IOException {
       SSLCertificateVerify cv=new SSLCertificateVerify(_conn,this,true);

       sendHandshakeMsg(_conn,SSL_HT_CERTIFICATE_VERIFY,cv);
     }
     

     // As a side effect, this writes the preMasterSecret
     private void sendClientKeyExchange()
       throws java.io.IOException {
       SSLClientKeyExchange ck=new SSLClientKeyExchange();

       sendHandshakeMsg(_conn,SSL_HT_CLIENT_KEY_EXCHANGE,ck);

       computeMasterSecret();
       computeNextCipherStates();
     }

     private String sessionLookupKey(){
	 if (_conn.s == null) {
	     return null;
	 } else {
	     return _conn.s.remote_host + ":"+ _conn.s.remote_port;
	 }
     }
   
}
