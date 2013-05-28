/**
   SSLConn.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Thu May  6 22:26:01 1999

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

   $Id: SSLConn.java,v 1.4 2004/03/31 06:47:33 gawor Exp $

*/


package COM.claymoresystems.ptls;
import COM.claymoresystems.sslg.*;
import java.util.Vector;
import java.net.*;
import java.io.*;

public class SSLConn {
    public static final int SSL_CLIENT = 1;
    public static final int SSL_SERVER = 2;


//     static int debugVal=DEBUG_CRYPTO;
     static int debugVal=0;
     
     /* Connection Housekeeping*/
     int ssl_version=0;
     int max_ssl_version=0;
     
     SSLContext ctx;
     SSLSocket s=null;
     SSLPolicyInt policy;               // The policy we're following
     PushbackInputStream sock_in;               // The stream to do our raw reading on
     InputStream sock_in_hp;	// The stream for handshake messages
     InputStream sock_in_data;          // The stream for app data;

     OutputStream _sock_out; // The stream to do our raw writing on
     BufferedOutputStream sock_out;
     OutputStream sock_out_external; // The stream to expose
     boolean sentClose=false;
     boolean recvdClose=false;
     Vector peerCertificateChain=null;
     String sessionLookupKey=null;
     int how;
     byte[] session_id;
     
     // Our cipher states
     SSLCipherState write_cipher_state=null;
     SSLCipherState read_cipher_state=null;
     SSLCipherState next_write_cipher_state;
     SSLCipherState next_read_cipher_state;
     long write_sequence_num;
     long read_sequence_num;

     boolean secureMode=false;    // Have we seen at least one handshake?
     boolean invalid=false;       // Set once we've sent or received a fatal alert
     
     SSLHandshake hs;	    // The handshake object
     SSLRecordReader reader;   // The record reader

    public SSLConn(SSLSocket sock,InputStream in,OutputStream out,SSLContext c,
       int how)
       throws java.io.IOException {
       this.s=sock;
       this.how=how;
       ctx=c;
       policy=c.getPolicy();
       sock_in=new PushbackInputStream(in);
       _sock_out=out;
       sock_out=new BufferedOutputStream(_sock_out);

       
       //Set up the streams for reading different record types
       reader=new SSLRecordReader(this);
     }  

     void renegotiate(SSLPolicyInt p)
       throws IOException {
       policy=p;

       handshake();
     }
     
    void handshake()
	throws IOException {
	init();
	finishHandshake();
    }

    public void init() {
	// You can't renegotiate to a different version so ignore
	// new policy information if we're already encrypting
	if(read_cipher_state==null){
	    max_ssl_version=policy.negotiateTLSP()?SSLHandshake.TLS_V1_VERSION:SSLHandshake.SSL_V3_VERSION;
	    ssl_version=policy.negotiateTLSP()?SSLHandshake.TLS_V1_VERSION:SSLHandshake.SSL_V3_VERSION;
	}
       
	if(how == SSL_CLIENT){
	    hs=new SSLHandshakeClient(this);
	}
	else{
	    hs=new SSLHandshakeServer(this);
	}
    }

    public void finishHandshake() 
	throws IOException {
	try {
	    hs.handshake();

	    if(sock_in_hp.available()!=0)
		alert(SSLAlertX.TLS_ALERT_UNEXPECTED_MESSAGE);
         
	    secureMode=true;
	} catch (IOException e){
	    if((SSLDebug.debugVal & SSLDebug.DEBUG_HANDSHAKE)>0){
		e.printStackTrace();
	    } 

	    if(!(e instanceof SSLAlertException))
		throw new SSLHandshakeFailedException(e.toString());
	    else
		throw e;
	}
    }
     
    public int getCipherSuite()
       throws IOException {
       if(!hs.finishedP()){
	 throw new SSLException("Handshake not finished");
       }

       return write_cipher_state.cipher_suite.getValue();
     }

     SSLPolicyInt getPolicy(){
       return policy;
     }

     byte[] getSessionID()
       throws IOException {
       if(!hs.finishedP()){
	 throw new SSLException("Handshake not finished");
       }

       return session_id;
     }
       
     int getVersion()
       throws IOException {
       if(!hs.finishedP()){
	 throw new SSLException("Handshake not finished");
       }

       return ssl_version;
     }

    public SSLRecordReader getRecordReader() {
	return reader;
    }

    public SSLCipherState getReadCipherState() {
	return read_cipher_state;
    }
    
    public SSLCipherState getWriteCipherState() {
	return write_cipher_state;
    }

    public long getWriteSequence() {
	return write_sequence_num;
    }

    public long getReadSequence() {
	return read_sequence_num;
    }

    public void incrementReadSequence() {
	read_sequence_num++;
    }

    public void incrementWriteSequence() {
	write_sequence_num++;
    }

    public SSLHandshake getHandshake() {
	return hs;
    }

    public Vector getCertificateChain()
       throws IOException {
       if(!hs.finishedP()){
	 throw new SSLException("Handshake not finished");
       }
       
       return peerCertificateChain;
     }

     void alert(int a)
       throws java.io.IOException {
       sendAlertNoException(a,true);

       throw new SSLThrewAlertException(new SSLAlertX(ssl_version,a,
	 true));
     }

    void alert(int a, String msg)
	throws java.io.IOException {
	sendAlertNoException(a,true);
	
	throw new SSLThrewAlertException(new SSLAlertX(ssl_version,a,
						       true), msg);
    }

     void sendAlertNoException(int a,boolean fatal)
       throws java.io.IOException {
       SSLAlertX alertx=new SSLAlertX(ssl_version,a,fatal);
       
       if(fatal){
         SSLDebug.debug(SSLDebug.DEBUG_STATE,
           "Throwing a fatal alert, lookup key "+ sessionLookupKey);
         makeUnresumable();
         invalid=true;
       }
       
       SSLAlert alert=new SSLAlert(alertx);
       ByteArrayOutputStream bos=new ByteArrayOutputStream();
       alert.encode(this,bos);

       SSLRecord r=new SSLRecord(this,SSLRecord.SSL_CT_ALERT,
	 bos.toByteArray());
       r.send(this);
       sock_out.flush();
     }

     boolean processIncomingHandshakeRecord(byte[] data)
       throws IOException {
       byte[] helloRequest={0,0,0,0};
       if(hs.finishedP()){
         // If we're finished then only accept messages
         // that restart the handshake
         switch(data[0]){
           case SSLHandshake.SSL_HT_HELLO_REQUEST:
             if(how!=SSL_CLIENT)
               alert(SSLAlertX.TLS_ALERT_UNEXPECTED_MESSAGE);
             if(!cryptix.util.core.ArrayUtil.areEqual(data,helloRequest))
               alert(SSLAlertX.TLS_ALERT_ILLEGAL_PARAMETER);
             // Throw the exception from here so that the
             // data isn't written to the pipeline
             throw new SSLReHandshakeException();
             // break; (good form but Java hates it)
           case SSLHandshake.SSL_HT_CLIENT_HELLO:
             if(how!=SSL_SERVER)
               alert(SSLAlertX.TLS_ALERT_UNEXPECTED_MESSAGE);
             // return true so that the caller will throw a
             // ReHandshakeException
             return true;
             // break; (good form but Java hates it)
           default:
             alert(SSLAlertX.TLS_ALERT_UNEXPECTED_MESSAGE);               
         }
       }
       else {
         // Check for HelloRequest but otherwise ignore the
         // content type
         if(data[0]==SSLHandshake.SSL_HT_HELLO_REQUEST)
           alert(SSLAlertX.TLS_ALERT_UNEXPECTED_MESSAGE);
       }
       return false;
     }
     
    static boolean isDebugEnabled(int type) {
	return ((debugVal & type) > 0);
    }
    
     static void debug(int type,String val){
       if((debugVal & type) > 0){
	 System.out.println(val);
       }
     }

     static void debug(int type,String label,byte[] hd){
       if((debugVal & type) > 0){
	 COM.claymoresystems.util.Util.xdump(label,hd);
       }
     }

    public InputStream getInStream(){
       if(!hs.finishedP()){
         return null;
       }
       if(read_cipher_state==null)
         return null;
       
       return sock_in_data;
     }

    public OutputStream getOutStream(){
       if(!hs.finishedP()){
         return null;
       }
       if(write_cipher_state==null)
         return null;
       
       return sock_out_external;
     }

     void makeUnresumable(){
       if(sessionLookupKey!=null){
         SSLDebug.debug(SSLDebug.DEBUG_STATE,"Making session "+
           sessionLookupKey + "Unresumable");
	 ctx.destroySession(sessionLookupKey);
       }
     }

     void sendClose()
       throws java.io.IOException {
       if(!sentClose){
	 sendAlertNoException(SSLAlertX.TLS_ALERT_CLOSE_NOTIFY,false);
	 sentClose=true;
       }
     }	 

     void recvClose(boolean enforceFinished)
       throws java.io.IOException {
       InputStream in=getInStream();
       byte[] buf=new byte[1024];

       // Flush the in buffer until we hit a closed or a
       // FIN
       while(in.read(buf)>=0){
	 if(enforceFinished){
	   throw new SSLException("Excess data in pipe when closed");
	 }
       }
     }

     void close()
       throws java.io.IOException {
       sendClose();
       if(policy.waitOnCloseP()){
         recvClose(false);
       }
       if(s!=null)
         s.hardClose();
     }

     public static void setDebug(int flag){
       debugVal=flag;
     }
}
