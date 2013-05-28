/**
   SSLRecordReader.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sat May  8 14:28:23 1999

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

   $Id: SSLRecordReader.java,v 1.3 2003/12/18 06:17:56 gawor Exp $

*/

package COM.claymoresystems.ptls;
import java.io.*;

public class SSLRecordReader {
     SSLConn conn;

     public SSLRecordReader(SSLConn c){
       conn=c;

       // Yuck. This is rather a data hiding violation
       conn.sock_in_hp=streams[0];
       conn.sock_in_data=streams[1];              
     }
     
     // The array of input streams.
     // this is the content type -21
     // Is there a cleaner way to build this?
     SSLInputStream streams[]={
	  new SSLInputStream(this),
	  new SSLInputStream(this)};
     
     public int readRecord()
       throws java.io.IOException {
       int rtype;

       if(conn.recvdClose)
	 return(-1);
       
       SSLRecord r=new SSLRecord(conn);
	 
       r.decode(conn,conn.sock_in);

       // Check the version number
       if(conn.read_cipher_state!=null){
         if(conn.ssl_version!=r.version.value)
           conn.alert(SSLAlertX.TLS_ALERT_ILLEGAL_PARAMETER);
       }
       
       rtype=r.type.value;

       
       switch(rtype){
	 case SSLRecord.SSL_CT_CHANGE_CIPHER_SPEC:
	   conn.hs.recvChangeCipherSpecs(r.data.value);
           
	   break;
	 case SSLRecord.SSL_CT_ALERT:
	   processAlert(r.data.value);
	   break;
	 case SSLRecord.SSL_CT_HANDSHAKE:
	   // Check for obviously bogus handshake messages
           boolean startRehandshake;

           startRehandshake=
             conn.processIncomingHandshakeRecord(r.data.value);
           
	   SSLDebug.debug(SSLDebug.DEBUG_CODEC,"Read a new record type: handshake length"
             + r.data.value.length);
           streams[0].write(r);

           // This is only called if we're a server.
           // If we're a client the exception is thrown
           // from processIncoming HandshakeRecord
           if(startRehandshake)
             throw new SSLReHandshakeException();
           break;
	 case SSLRecord.SSL_CT_APPLICATION_DATA:
           if((!conn.secureMode) || (conn.read_cipher_state==null))
             conn.alert(SSLAlertX.TLS_ALERT_UNEXPECTED_MESSAGE);
	   SSLDebug.debug(SSLDebug.DEBUG_CODEC,"Read a new record type: data length"
             + r.data.value.length);
	   streams[1].write(r);
	   break;
	 default:
	   throw new java.io.IOException("Bad record type" + rtype);
       }

       return(0);
     }

     public void processAlert(byte[] rec)
       throws java.io.IOException {
       ByteArrayInputStream bis=new ByteArrayInputStream(rec);

       SSLAlert alert= new SSLAlert();

       alert.decode(conn,bis);

       SSLAlertX alertx=new SSLAlertX(alert.description.value,
	 alert.level.value);

       if(alertx.fatalP()){
	 conn.recvdClose=true;
         conn.invalid=true;
	 conn.makeUnresumable();
       }
       

       SSLHandshake hd = this.conn.getHandshake();
       if (alert.description.value == SSLAlertX.SSL_ALERT_NO_CERTIFICATE &&
	   hd.state == SSLHandshakeServer.SSL_HS_WAIT_FOR_CERTIFICATE &&
	   this.conn.getPolicy().requireClientAuthP() &&
	   this.conn.getPolicy().getAcceptNoClientCert() &&
	   hd instanceof SSLHandshakeServer) {
	   hd.state=SSLHandshakeServer.SSL_HS_WAIT_FOR_CLIENT_KEY_EXCHANGE;
	   ((SSLHandshakeServer)hd).clientAuth = false;
	   return;
       }
       
       if(alert.description.value!=SSLAlertX.TLS_ALERT_CLOSE_NOTIFY){
	   throw new SSLCaughtAlertException(alertx);
       }

       conn.recvdClose=true;
     }
}
     
	 
       
       
     
     
     

