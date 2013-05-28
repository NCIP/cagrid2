/**
   SSLHandshakeServer.java
 
   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Mon May 24 21:23:27 1999

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

   $Id: SSLHandshakeServer.java,v 1.3 2003/12/18 06:17:56 gawor Exp $

*/

package COM.claymoresystems.ptls;
import COM.claymoresystems.sslg.*;
import COM.claymoresystems.cert.*;
import COM.claymoresystems.util.*;
import java.io.*;
import java.util.Vector;
import java.util.Hashtable;
import java.math.BigInteger;
import java.security.PrivateKey;

class SSLHandshakeServer extends SSLHandshake
{
     public static final int SSL_HS_WAIT_FOR_CLIENT_HELLO	=1;
     public static final int SSL_HS_WAIT_FOR_CERTIFICATE       = 2;
     public static final int SSL_HS_WAIT_FOR_CLIENT_KEY_EXCHANGE=3;
     public static final int SSL_HS_WAIT_FOR_CERTIFICATE_VERIFY= 4;
     public static final int SSL_HS_SEND_HELLO_REQUEST = 5;

     private static final int SSL2_CK_RC4_128_WITH_MD5=0x010080;
     private static final int SSL2_CK_RC4_128_EXPORT40_WITH_MD5=0x020080;
     private static final int SSL2_CK_RC2_128_CBC_WITH_MD5=0x010080;
     private static final int SSL2_CK_RC2_128_CBC_EXPORT40_WITH_MD5=0x020080; // No match in v3
     private static final int SSL2_CK_IDEA_128_CBC_WITH_MD5=0x050080;         // No match in v3
     private static final int SSL2_CK_DES_64_CBC_WITH_MD5=0x060080;           // No match in v3
     private static final int SSL2_CK_DES_192_EDE3_CBC_WITH_MD5=0x060080;     // No match in v3

    
     boolean resume=false;
     boolean clientAuth=false;
     SSLSessionData possibleResume=null;
     Vector offered_cipher_suites;
     Vector offered_compression_methods;
     private static Hashtable _v2_v3_cipher_suite_map;

     static {
       _v2_v3_cipher_suite_map=new Hashtable();

       _v2_v3_cipher_suite_map.put(new Integer(SSL2_CK_RC4_128_WITH_MD5),
         new Integer(SSLPolicyInt.TLS_RSA_WITH_RC4_128_MD5));
       _v2_v3_cipher_suite_map.put(new Integer(SSL2_CK_RC4_128_EXPORT40_WITH_MD5),
         new Integer(SSLPolicyInt.TLS_RSA_EXPORT_WITH_RC4_40_MD5));
       _v2_v3_cipher_suite_map.put(new Integer(SSL2_CK_RC2_128_CBC_EXPORT40_WITH_MD5),
         new Integer(SSLPolicyInt.TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5));
     }

     public SSLHandshakeServer(SSLConn c){
       super(c);
       
       stateChange((_conn.write_cipher_state==null)?
	 SSL_HS_WAIT_FOR_CLIENT_HELLO:
	 SSL_HS_SEND_HELLO_REQUEST);
       SSLDebug.debug(SSLDebug.DEBUG_HANDSHAKE,"Starting server handshake (connection " + c + ") in state "+state + " write cipher state " + _conn.write_cipher_state);
       
       client=false;
     }

     protected void filterCipherSuites(PrivateKey key,SSLPolicyInt policy){
       String alg=key.getAlgorithm();
       cipher_suites=new Vector();
       short[] policySuites=_conn.getPolicy().getCipherSuites();
       
       for(int i=0;i<policySuites.length;i++){
	 SSLCipherSuite cs=SSLCipherSuite.findCipherSuite(policySuites[i]);

	 if(cs==null){
	   SSLDebug.debug(SSLDebug.DEBUG_INIT,
	     "Rejecting unrecognized cipher suite" + policySuites[i]);
	   continue;
	 }

	 if(!cs.getSignatureAlgBase().equals(alg)){
	   SSLDebug.debug(SSLDebug.DEBUG_INIT,
	     "Rejecting cipher suite: " + cs.getName() +
	     " -- incompatible with signature algorithm "+ alg);
	   continue;
	 }

	 SSLDebug.debug(SSLDebug.DEBUG_INIT,"Accepting cipher suite: " +
	   cs.getName());
	 cipher_suites.addElement((Object)cs);
       }
     }
    
    public boolean processTokens()
	throws IOException {
	
	if  (_conn.sock_in.available() == 0 &&
	     _conn.sock_in_hp.available() == 0) {
	    return false;
	}

	InputStream is=null;
	int type=-1;
	boolean v2_hello=false;
	
	SSLHandshakeHdr hdr=new SSLHandshakeHdr();

	if(state==SSL_HS_SEND_HELLO_REQUEST){
	    sendHelloRequest();
	    stateChange(SSL_HS_WAIT_FOR_CLIENT_HELLO);
	}

       if(_conn.read_cipher_state==null &&
         state==SSL_HS_WAIT_FOR_CLIENT_HELLO){
         /* Figure out what kind of ClientHello this is, either
            SSLv2 backward-compatible or SSLv3 and call the
            appropriate function. This only applies on the first
            handshake.
            
            The way we do this is by reading the high byte. If it's
            0x16 then we assume that it's an SSLv3 record. We
            can safely do so because the first byte of an SSLv2
            record is the high byte of the length and 22*256 is
            way too long for a CLIENT-HELLO

            Note that this read is off the raw in pipe, not the
            handshake pipe
         */
         int b1=_conn.sock_in.read(); 

         SSLDebug.debug(SSLDebug.DEBUG_HANDSHAKE,"Testing for SSLv2 handshake. First byte off wire is: " + b1);
         _conn.sock_in.unread(b1);
         
         if(b1!=0x16) {
           /* Can't renegotiate with SSLv2 handshake */
           if(_conn.read_cipher_state!=null)
             _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
           
           /* This is either a v2hello or trash. We'll see which
              one in the v2hello parser */
           v2_hello=true;
           type=SSL_HT_V2_CLIENT_HELLO;
         }
       }

       if(!v2_hello){
	   is=recvHandshakeToken(_conn,hdr);
	   if (is == null) {
	       return false;
	   }
	   type=hdr.ct.value;
	   _conn.debug(SSLDebug.DEBUG_STATE,
		       "Processing handshake message of type " + type);
       }
       switch(type){
         case SSL_HT_V2_CLIENT_HELLO:
           stateAssert(SSL_HS_WAIT_FOR_CLIENT_HELLO);
           recvSSLv2ClientHello(_conn.sock_in);
           resume=false; // Resume is totally forbidden here

           sendServerPhase1();
           _conn.sock_out.flush();           
           break;
	 case SSL_HT_CLIENT_HELLO:
	   stateAssert(SSL_HS_WAIT_FOR_CLIENT_HELLO);
	   recvSSLv3ClientHello(is);

	   resume=false;
	   if(possibleResume!=null){
	     // If client auth was requested, and it wasn't done already,
	     // we can't resume
	     if(_conn.getPolicy().requireClientAuthP() &&
	       (possibleResume.getPeerCertificateChain()==null))
	       resume=false;
	     else{
	       restoreSession(possibleResume);
	       resume=true;
	     }
	   }
           sendServerPhase1();
           _conn.sock_out.flush();
	   break;
	 case SSL_HT_CERTIFICATE:
	   stateAssert(SSL_HS_WAIT_FOR_CERTIFICATE);
	   recvCertificate(is);
	   stateChange(SSL_HS_WAIT_FOR_CLIENT_KEY_EXCHANGE);
	   break;
	 case SSL_HT_CLIENT_KEY_EXCHANGE:
	   stateAssert(SSL_HS_WAIT_FOR_CLIENT_KEY_EXCHANGE);
	   recvClientKeyExchange(is);
	   stateChange(clientAuth?SSL_HS_WAIT_FOR_CERTIFICATE_VERIFY:
	     SSL_HS_WAIT_FOR_CHANGE_CIPHER_SPECS);
	   break;
	 case SSL_HT_CERTIFICATE_VERIFY:
	   stateAssert(SSL_HS_WAIT_FOR_CERTIFICATE_VERIFY);
	   recvCertificateVerify(is);
	   stateChange(SSL_HS_WAIT_FOR_CHANGE_CIPHER_SPECS);
	   break;
	 case SSL_HT_FINISHED:
	   stateAssert(SSL_HS_WAIT_FOR_FINISHED);
	   recvFinished(is);
	   if(!resume){
	     sendChangeCipherSpec();
	     sendFinished();
             // Test
             // sendFinished();
	   }
	   if(session_id.length!=0)
	     storeSession(Util.toHex(session_id));	   
	   stateChange(SSL_HANDSHAKE_FINISHED);
	   break;
	 default:
	   _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
       }

       if(type!=SSL_HT_CLIENT_HELLO && type!=SSL_HT_V2_CLIENT_HELLO){
         // Check to make sure we've read the entire message
         int x=is.read();
         if(x!=-1)
           _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
       }

       return true;
     }
 
     public void handshakeContinue()
       throws IOException {
       InputStream is=null;
       int type=-1;
       boolean v2_hello=false;
       
       SSLHandshakeHdr hdr=new SSLHandshakeHdr();

       if(state==SSL_HS_SEND_HELLO_REQUEST){
	 sendHelloRequest();
	 stateChange(SSL_HS_WAIT_FOR_CLIENT_HELLO);
       }

       if(_conn.read_cipher_state==null &&
         state==SSL_HS_WAIT_FOR_CLIENT_HELLO){
         /* Figure out what kind of ClientHello this is, either
            SSLv2 backward-compatible or SSLv3 and call the
            appropriate function. This only applies on the first
            handshake.
            
            The way we do this is by reading the high byte. If it's
            0x16 then we assume that it's an SSLv3 record. We
            can safely do so because the first byte of an SSLv2
            record is the high byte of the length and 22*256 is
            way too long for a CLIENT-HELLO

            Note that this read is off the raw in pipe, not the
            handshake pipe
         */
         int b1=_conn.sock_in.read(); 

         SSLDebug.debug(SSLDebug.DEBUG_HANDSHAKE,"Testing for SSLv2 handshake. First byte off wire is: " + b1);
         _conn.sock_in.unread(b1);
         
         if(b1!=0x16) {
           /* Can't renegotiate with SSLv2 handshake */
           if(_conn.read_cipher_state!=null)
             _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
           
           /* This is either a v2hello or trash. We'll see which
              one in the v2hello parser */
           v2_hello=true;
           type=SSL_HT_V2_CLIENT_HELLO;
         }
       }

       if(!v2_hello){
         is=recvHandshakeMsg(_conn,hdr);
         type=hdr.ct.value;
         _conn.debug(SSLDebug.DEBUG_STATE,
           "Processing handshake message of type " + type);
       }

       switch(type){
         case SSL_HT_V2_CLIENT_HELLO:
           stateAssert(SSL_HS_WAIT_FOR_CLIENT_HELLO);
           recvSSLv2ClientHello(_conn.sock_in);
           resume=false; // Resume is totally forbidden here

           sendServerPhase1();
           _conn.sock_out.flush();           
           break;
	 case SSL_HT_CLIENT_HELLO:
	   stateAssert(SSL_HS_WAIT_FOR_CLIENT_HELLO);
	   recvSSLv3ClientHello(is);

	   resume=false;
	   if(possibleResume!=null){
	     // If client auth was requested, and it wasn't done already,
	     // we can't resume
	     if(_conn.getPolicy().requireClientAuthP() &&
	       (possibleResume.getPeerCertificateChain()==null))
	       resume=false;
	     else{
	       restoreSession(possibleResume);
	       resume=true;
	     }
	   }
           sendServerPhase1();
           _conn.sock_out.flush();
	   break;
	 case SSL_HT_CERTIFICATE:
	   stateAssert(SSL_HS_WAIT_FOR_CERTIFICATE);
	   recvCertificate(is);
	   stateChange(SSL_HS_WAIT_FOR_CLIENT_KEY_EXCHANGE);
	   break;
	 case SSL_HT_CLIENT_KEY_EXCHANGE:
	   stateAssert(SSL_HS_WAIT_FOR_CLIENT_KEY_EXCHANGE);
	   recvClientKeyExchange(is);
	   stateChange(clientAuth?SSL_HS_WAIT_FOR_CERTIFICATE_VERIFY:
	     SSL_HS_WAIT_FOR_CHANGE_CIPHER_SPECS);
	   break;
	 case SSL_HT_CERTIFICATE_VERIFY:
	   stateAssert(SSL_HS_WAIT_FOR_CERTIFICATE_VERIFY);
	   recvCertificateVerify(is);
	   stateChange(SSL_HS_WAIT_FOR_CHANGE_CIPHER_SPECS);
	   break;
	 case SSL_HT_FINISHED:
	   stateAssert(SSL_HS_WAIT_FOR_FINISHED);
	   recvFinished(is);
	   if(!resume){
	     sendChangeCipherSpec();
	     sendFinished();
             // Test
             // sendFinished();
	   }
	   if(session_id.length!=0)
	     storeSession(Util.toHex(session_id));	   
	   stateChange(SSL_HANDSHAKE_FINISHED);
	   break;
	 default:
	   _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
       }

       if(type!=SSL_HT_CLIENT_HELLO && type!=SSL_HT_V2_CLIENT_HELLO){
         // Check to make sure we've read the entire message
         int x=is.read();
         if(x!=-1)
           _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
       }
     }

     public void sendCertificate()
       throws IOException{
       Vector certs=_conn.ctx.getCertificateChain();       

       if(certs==null){
	 _conn.sendAlertNoException(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE,true);
	 throw new IOException("Certificate needed but no certificate available");
       }
       sendCertificate(certs);
     }
       
     public void sendServerPhase1()
       throws IOException {
       if(!resume){
	 session_id=new byte[32];
         rng.nextBytes(session_id);
	 SSLDebug.debug(SSLDebug.DEBUG_HANDSHAKE,"Session ID",session_id);
         selectCipherSuite();
       }
       
       sendServerHello();
       
       if(resume){
         computeNextCipherStates();
         sendChangeCipherSpec();
         sendFinished();
         stateChange(SSL_HS_WAIT_FOR_CHANGE_CIPHER_SPECS);
       }
       else{
         sendCertificate();
         sendServerKeyExchange(); 
         if(_conn.getPolicy().requireClientAuthP()){
           clientAuth=true;
           sendCertificateRequest();
           stateChange(SSL_HS_WAIT_FOR_CERTIFICATE);
         }
         else{
           stateChange(SSL_HS_WAIT_FOR_CLIENT_KEY_EXCHANGE);
         }
         sendServerHelloDone();           
       }
     }

     public void recvSSLv2ClientHello(InputStream is)
       throws IOException {
       SSLv2ClientHello ch=new SSLv2ClientHello();

       ch.decode(_conn,is);
       
       // Choose the highest common version
       if(ch.client_version.value<SSL_V3_VERSION)
         _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
       _conn.ssl_version=Util.min(ch.client_version.value,_conn.ssl_version);
       client_offered_version=ch.client_version.value;
       
       // First check for attempts by the client to resume and complain
       // You can't resume SSLv3 messages using SSLv2 ClientHello and
       // we don't support v2
       if(ch.session_id.length!=0)
         _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);

       // Map the cipher suites to SSLv3 cipher suites. This
       // actually requires two things
       // 1. Any cipher suite with a leading zero is a v3 cipher
       // suite encoded as v2 and we just strip the zero
       // 2. There are also some SSLv2 cipher suites that are
       // held over into v3/TLS. Although it's unlikely, the client
       // might offer these but NOT the corresponding v3 cipher suite
       // so we need a map table
       Vector v=new Vector();
       
       for(int i=0;i<ch.cipher_specs.value.size();i++){
         SSLuint24 cs;
         

         cs=(SSLuint24)ch.cipher_specs.value.elementAt(i);
         if((cs.value & 0xff0000)==0){
           v.addElement(new SSLuint16(cs.value));
           continue;
         }
         
         Integer x=new Integer(cs.value);
         Integer y=(Integer)_v2_v3_cipher_suite_map.get(x);
         if(y!=null){
           v.addElement(new SSLuint16(y.intValue()));
         }
       }
       offered_cipher_suites=v;
       
       
       // SSLv2 didn't have compression 
       offered_compression_methods=new Vector();
       offered_compression_methods.addElement(new SSLuint16(0));

       // Finally, grab the CHALLENGE and left pad with zeros if necessary
       client_random=new byte[32];
       if(ch.challenge.length>32)
         _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
       if(ch.challenge.length<16)
         _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
       int offset=32-ch.challenge.length;
       System.arraycopy(ch.challenge.value,0,client_random,offset,
         ch.challenge.length);

       // Digest the message
       hashes.update(ch.message_value);
     }
       
     public void recvSSLv3ClientHello(InputStream is)
       throws IOException {
       SSLClientHello ch=new SSLClientHello();

       ch.decode(_conn,is);

       // Choose the highest common version
       if(ch.client_version.value<SSL_V3_VERSION)
         _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
       _conn.ssl_version=Util.min(ch.client_version.value,_conn.ssl_version);
       client_offered_version=ch.client_version.value;
       
       client_random=ch.random.value;

       if(ch.session_id.value.length!=0)
         possibleResume=findSession(Util.toHex(ch.session_id.value));

       //Now remember the cipher_suites and compression
       offered_cipher_suites=ch.cipher_suites.value;
       offered_compression_methods=ch.compression_methods.value;
     }

     public void selectCipherSuite()
       throws IOException {

       SSLDebug.debug(SSLDebug.DEBUG_HANDSHAKE,"Client offered " +
         offered_cipher_suites.size() + "ciphersuites");
       
	 for(int i=0;i<cipher_suites.size();i++){
	   SSLCipherSuite oursuite=(SSLCipherSuite)cipher_suites.elementAt(i);
             SSLDebug.debug(SSLDebug.DEBUG_HANDSHAKE,"Seeing if client supports our ciphersuite " + oursuite.getValue());
	   for(int j=0;j<offered_cipher_suites.size();j++){
	     if(oursuite.getValue()
	       ==((SSLuintX)offered_cipher_suites.elementAt(j)).value){
	       cipher_suite=oursuite;
	       SSLDebug.debug(SSLDebug.DEBUG_HANDSHAKE,"Choosing cipher" +
		 cipher_suite.getName());
	       return;
	     }
	   }
	 }

	 _conn.alert(SSLAlertX.TLS_ALERT_HANDSHAKE_FAILURE);
       }
       
       public void sendServerHello()
	 throws IOException {
	 SSLServerHello sh=new SSLServerHello();

	 sh.server_version.value=_conn.ssl_version;
	 makeRandomValue(server_random);
	 sh.random.value=server_random;
	 sh.session_id.value=session_id;
	 sh.cipher_suite.value=cipher_suite.getValue();
	 sh.compression_method.value=0;  // The only one we support

	 sendHandshakeMsg(_conn,SSL_HT_SERVER_HELLO,sh);
       }

       public void recvClientKeyExchange(InputStream is)
         throws IOException {
	 SSLClientKeyExchange ck=new SSLClientKeyExchange();

	 ck.decode(_conn,is);			
	 computeMasterSecret();
	 computeNextCipherStates();
       }

       public void recvCertificateVerify(InputStream is)
         throws IOException {
	 SSLCertificateVerify cv=new SSLCertificateVerify(_conn,
	   this,false);

	 cv.decode(_conn,is);
       }
     
       public void sendServerKeyExchange()
	 throws IOException {
	 if(cipher_suite.requireServerKeyExchangeP(_conn.ctx.getPrivateKey())){
	   SSLServerKeyExchange sk=new SSLServerKeyExchange();
	   sendHandshakeMsg(_conn,SSL_HT_SERVER_KEY_EXCHANGE,sk);
	 }
       }
	   
       public void sendCertificateRequest()
	 throws IOException {
	 SSLCertificateRequest cr=new SSLCertificateRequest();

	 sendHandshakeMsg(_conn,SSL_HT_CERTIFICATE_REQUEST,cr);
       }
       
       public void sendServerHelloDone()
	 throws IOException {
	 sendHandshakeMsg(_conn,SSL_HT_SERVER_HELLO_DONE,new SSLServerHelloDone());
       }

       public void sendHelloRequest()
         throws IOException {
	 sendHandshakeMsg(_conn,SSL_HT_HELLO_REQUEST,new SSLHelloRequest(),false);
         _conn.sock_out.flush();
       }

     
}

