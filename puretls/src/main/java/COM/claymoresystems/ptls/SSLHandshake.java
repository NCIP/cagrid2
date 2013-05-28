/**
   SSLHandshake.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Fri May  7 16:19:13 1999

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

   $Id: SSLHandshake.java,v 1.3 2003/12/18 06:17:55 gawor Exp $

*/

package COM.claymoresystems.ptls;
import COM.claymoresystems.cert.*;
import COM.claymoresystems.sslg.SSLPolicyInt;
import COM.claymoresystems.crypto.DHPrivateKey;
import COM.claymoresystems.util.Util;

import java.io.*;
import xjava.security.*;
import java.security.*;
import xjava.security.interfaces.CryptixRSAPrivateKey;
import xjava.security.interfaces.CryptixRSAPublicKey;
import java.util.Vector;
import java.util.Date;

public abstract class SSLHandshake {
     public static final int SSL_HT_HELLO_REQUEST = 0;
     public static final int SSL_HT_CLIENT_HELLO = 1;
     public static final int SSL_HT_SERVER_HELLO = 2;
     public static final int SSL_HT_CERTIFICATE = 11;
     public static final int SSL_HT_SERVER_KEY_EXCHANGE = 12;
     public static final int SSL_HT_CERTIFICATE_REQUEST = 13;
     public static final int SSL_HT_SERVER_HELLO_DONE = 14;
     public static final int SSL_HT_CERTIFICATE_VERIFY = 15;
     public static final int SSL_HT_CLIENT_KEY_EXCHANGE = 16;
     public static final int SSL_HT_FINISHED=20;
     public static final int SSL_HT_V2_CLIENT_HELLO=255;     
     
     
     // Common states
     public static final int SSL_HS_WAIT_FOR_CHANGE_CIPHER_SPECS=20;
     public static final int SSL_HS_WAIT_FOR_FINISHED=21;
     public static final int SSL_HANDSHAKE_FINISHED = 255;

     /* Note that the code assumes elsewhere that
        TLS_V1_VERSION > SSL_V3_VERSION */
     public static final int SSL_V3_VERSION = 768;
     public static final int TLS_V1_VERSION = 769;

     public static final int MASTER_SECRET_SIZE = 48;

     public static byte[] pad_1={0x36};
     public static byte[] pad_2={0x5c};
     ByteArrayOutputStream os=new ByteArrayOutputStream();
     
     /*Housekeeping*/
     int state;
     SSLConn _conn;
     byte session_id[];
     boolean client;
     CertContext cert_ctx;
     Vector cipher_suites;
     
     /*Cryptographic data*/
     SecureRandom rng;
     byte client_random[]=new byte[32];
     byte server_random[]=new byte[32];
     SSLHandshakeHashes hashes;
     SSLHandshakeHashes save_hashes; // We need to store this so we don't
				//   overwrite when we read the record
     SSLCipherSuite cipher_suite;
     byte pre_master_secret[];
     byte master_secret[];
     PublicKey peerSignatureKey;
     PublicKey peerEncryptionKey;
     DHPrivateKey dhEphemeral;
     CryptixRSAPrivateKey rsaEphemeral=null;
     CryptixRSAPublicKey rsaEphemeralPublic=null;
     
     int client_offered_version=0;
     
     public SSLHandshake(SSLConn conn){
       _conn=conn;
       hashes=new SSLHandshakeHashes();
///       cert_ctx=new CertContext(_conn.getPolicy().getRootCertificatesInt());
       cert_ctx=new CertContext(conn.ctx.getRootList());
       rng=new SecureRandom(conn.ctx.getSeedBytes());
       filterCipherSuites(conn.ctx.getPrivateKey(),conn.getPolicy());
     }

     public void handshake()
       throws java.io.IOException{
       while(state!=SSL_HANDSHAKE_FINISHED){
	 handshakeContinue();
       }

       _conn.session_id=(session_id.length!=0)?session_id:null;
       _conn.sock_out_external=new SSLOutputStream(_conn);
       SSLDebug.debug(SSLDebug.DEBUG_STATE,"Handshake completed");
     }

    public void processHandshake() 
	throws IOException {
	while( processTokens() ) { }
    }

    public abstract boolean processTokens() throws IOException;
    
     public abstract void handshakeContinue() throws java.io.IOException;

     public void sendHandshakeMsg(SSLConn conn,int msgtype,SSLPDU pdu)
       throws IOException {
       sendHandshakeMsg(conn,msgtype,pdu,true);
     }
     
     public void sendHandshakeMsg(SSLConn conn,int msgtype,SSLPDU pdu,boolean digest)
       throws java.io.IOException, Error {
       pdu.encode(conn,os);
       
       ByteArrayOutputStream nos=new ByteArrayOutputStream(
	    os.size() + 10);
       
       byte buf[];

       SSLHandshakeHdr hdr=new SSLHandshakeHdr(msgtype,os.size());
       
       hdr.encode(conn,nos);
       os.writeTo(nos);
       os.reset();

       buf=nos.toByteArray();

       if(digest) hashes.update(buf);
       
       SSLRecord rec=new SSLRecord(conn,SSLRecord.SSL_CT_HANDSHAKE,buf);
       
       rec.send(conn);
     }

    public InputStream recvHandshakeToken(SSLConn conn,SSLHandshakeHdr hdr)
	throws IOException {
	for (;;) {
	    if (conn.sock_in_hp.available() > 0) {
		return recvHandshakeMsg(conn, hdr);
	    } else if (conn.sock_in.available() > 0) {
		conn.reader.readRecord();
	    } else {
		return null;
	    }
	}
    }

     public InputStream recvHandshakeMsg(SSLConn conn,SSLHandshakeHdr hdr)
       throws java.io.IOException {
       hdr.decode(conn,conn.sock_in_hp);
       int br;

       switch(hdr.ct.value){
	 case SSL_HT_CERTIFICATE_VERIFY:
	 case SSL_HT_FINISHED:
	   try {
	     save_hashes=(SSLHandshakeHashes)hashes.clone();
	   } catch (java.lang.CloneNotSupportedException e) {
	     throw new Error("Internal error");
	   }
	   break;
       }
       
       // Arrange to digest the header
       ByteArrayOutputStream tmp_os=new ByteArrayOutputStream();
       hdr.encode(conn,tmp_os);
       byte[] tmp=tmp_os.toByteArray();
       hashes.update(tmp);
       
       byte[] buf=new byte[hdr.length.value];

       br=0;

       // Loop until we've filled the buffer. In a nonblocking mode
       // we'd need to stash partly filled buffers somewhere
       while(br<buf.length){
	 br=conn.sock_in_hp.read(buf,br,buf.length-br);
       }

       hashes.update(buf);
       
       return new ByteArrayInputStream(buf);
     }

     public boolean finishedP(){
       return state==SSL_HANDSHAKE_FINISHED;
     }
     
     public void stateChange(int st){
       state=st;

       SSLDebug.debug(SSLDebug.DEBUG_STATE,"New handshake state " + st);
     }

     public void stateAssert(int st)
       throws java.io.IOException {
       if(state == st) return;
       _conn.alert(SSLAlertX.TLS_ALERT_UNEXPECTED_MESSAGE);
     }
	 

     public void stateAssert(int st1,int st2)
       throws java.io.IOException {
       if(state == st1) return;
       if(state == st2) return;
       _conn.alert(SSLAlertX.TLS_ALERT_UNEXPECTED_MESSAGE);
     }

     public void stateAssert(int st1,int st2,int st3)
       throws java.io.IOException {
       if(state == st1) return;
       if(state == st2) return;
       if(state == st3) return;       
       _conn.alert(SSLAlertX.TLS_ALERT_UNEXPECTED_MESSAGE);
     }

     public void sendCertificate(Vector certs)
       throws java.io.IOException {
       SSLCertificate cert_list=new SSLCertificate();
       
       for(int i=1;i<=certs.size();i++){
	 SSLopaque cert=new SSLopaque(-16777215);

	 cert.value=(byte[])certs.elementAt(certs.size()-i);
	 
	 cert_list.certificate_list.value.addElement((Object)
	   cert);
       }

       sendHandshakeMsg(_conn,SSL_HT_CERTIFICATE,cert_list);
     }
	    
     public void recvCertificate(InputStream is)
       throws java.io.IOException {
       SSLCertificate cert_list=new SSLCertificate();
       Vector certs=new Vector();
       Vector verified_certs=null;
       
       cert_list.decode(_conn,is);

       if(cert_list.certificate_list.value.size()==0)
	 _conn.alert(SSLAlertX.TLS_ALERT_ILLEGAL_PARAMETER);
       
       for(int i=1;i<=cert_list.certificate_list.value.size();i++){

	 SSLopaque op=(SSLopaque)cert_list.certificate_list.value.
	   elementAt(cert_list.certificate_list.value.size()-i);
         
         byte[] cert=op.value;
         // Test
         // cert[cert.length-1]=(byte)255;
	 certs.addElement(new X509Cert(cert));
       }

       try {
	 verified_certs=X509Cert.verifyCertChain(cert_ctx,certs,
           _conn.getPolicy().getCertVerifyPolicy());
       } catch (CertificateDecodeException e){
	 _conn.alert(SSLAlertX.TLS_ALERT_BAD_CERTIFICATE);
       } catch (CertificateVerifyException e){
	 if(SSLDebug.getDebug(SSLDebug.DEBUG_CERT))
	   e.printStackTrace();
	 _conn.alert(SSLAlertX.TLS_ALERT_BAD_CERTIFICATE, e.getMessage());
       }

       if(verified_certs==null){
	 if(!_conn.getPolicy().acceptUnverifiableCertificatesP())
	   _conn.alert(SSLAlertX.TLS_ALERT_UNKNOWN_CA);
       }

       X509Cert peerCert=(X509Cert)certs.elementAt(certs.size()-1);
       PublicKey pk=peerCert.getPublicKey();
//       System.out.println("Public key algorithm"+pk.getAlgorithm());
       peerSignatureKey=pk;
       _conn.peerCertificateChain=verified_certs;
     }

     public void computeMasterSecret(){
       SSLPRF prf;

       // First choose an appopriate PRF for this version
       prf=SSLPRF.getPRFInstance(_conn.ssl_version);
	      
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Pre master secret",
	 pre_master_secret);
       
       // Now build the master secret
       master_secret=new byte[MASTER_SECRET_SIZE];
       prf.PRF(pre_master_secret, SSLPRF.SSL_PRF_MASTER_SECRET,
	 client_random, server_random, master_secret);

       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Master secret", master_secret);
     }

     public void computeNextCipherStates(){
       _conn.next_write_cipher_state=new SSLCipherState();
       _conn.next_read_cipher_state=new SSLCipherState();
       
       try {
	 SSLCipherState.computeSSLCipherState(this,
	   _conn.next_write_cipher_state,
	   _conn.next_read_cipher_state);
       } catch (java.security.NoSuchAlgorithmException e){
	 throw new Error(e.toString());
       } catch (java.security.KeyException e){
	 e.printStackTrace();
	 throw new Error(e.toString());
       }
       
     }
     
     public void sendChangeCipherSpec()
       throws java.io.IOException{
       byte[] css={1};
       SSLRecord rec=new SSLRecord(_conn,SSLRecord.SSL_CT_CHANGE_CIPHER_SPEC,
	 css);
       rec.send(_conn);
       
       _conn.write_cipher_state=_conn.next_write_cipher_state;
       _conn.write_sequence_num=0;
     }

     public void recvFinished(InputStream is)
       throws java.io.IOException {
       SSLFinished finished=new SSLFinished(_conn,this,false);
       
       finished.decode(_conn,is);
     }
     
     public void sendFinished()
       throws java.io.IOException {
       SSLFinished finished=new SSLFinished(_conn,this,true);

       sendHandshakeMsg(_conn,SSL_HT_FINISHED,finished);
       _conn.sock_out.flush();
     }
     
     public void recvChangeCipherSpecs(byte[] data)
       throws java.io.IOException {
       byte[] ccsTmpl={1};
       
       stateAssert(SSL_HS_WAIT_FOR_CHANGE_CIPHER_SPECS);
       if(!cryptix.util.core.ArrayUtil.areEqual(ccsTmpl,data))
         _conn.alert(SSLAlertX.TLS_ALERT_ILLEGAL_PARAMETER);
       _conn.read_cipher_state=_conn.next_read_cipher_state;
       _conn.read_sequence_num=0;
       stateChange(SSL_HS_WAIT_FOR_FINISHED);
     }


     protected void storeSession(String key){
       SSLDebug.debug(SSLDebug.DEBUG_HANDSHAKE,"Storing session ",session_id);
       SSLSessionData sd=new SSLSessionData(this,key);
       _conn.ctx.storeSession(key,sd);
     }

     protected SSLSessionData findSession(String key){
       SSLDebug.debug(SSLDebug.DEBUG_STATE,"Trying to recover session using key"+key);

       SSLSessionData d=_conn.ctx.findSession(key);

       if(d!=null){
	 if(d.getExpiryTime()<System.currentTimeMillis()){
	   _conn.ctx.destroySession(d.getLookupKey());
	   return null;
	 }
       }

       return d;
     }

     protected void restoreSession(SSLSessionData sess){
       sess.restoreSession(this);
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

     // Make an SSL client or server random as defined in RFC 2246.
     // Top 4 bytes are seconds since the epoch, rest are random
     protected void makeRandomValue(byte[] val) {
       if(val.length!=32) throw new InternalError("Incorrect random value length");
       rng.nextBytes(val);

       long t=System.currentTimeMillis();
       t/=1000;

       byte[] tb=Util.toBytes(t,4);
       System.arraycopy(tb,0,val,0,4);
     }
}
 
