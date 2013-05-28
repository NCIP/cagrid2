/**
   SSLRecord.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Fri May  7 17:26:42 1999

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

   $Id: SSLRecord.java,v 1.2 2003/05/10 05:44:46 gawor Exp $

*/

package COM.claymoresystems.ptls;
import COM.claymoresystems.util.Bench;
import java.io.*;

public class SSLRecord extends SSLPDU
{
     SSLuint8 type=new SSLuint8();
     SSLuint16 version=new SSLuint16();
     SSLopaque data=new SSLopaque(-65535);

     public static final int SSL_CT_CHANGE_CIPHER_SPEC = 20;
     public static final int SSL_CT_ALERT = 21;
     public static final int SSL_CT_HANDSHAKE = 22;
     public static final int SSL_CT_APPLICATION_DATA = 23;
     
     public SSLRecord(SSLConn conn,int ct,byte[] buf){
       type.value=ct;
       version.value=conn.ssl_version;
       data.value=buf;
     }

     
     public SSLRecord(SSLConn conn){
       ;
     }
       
    public  SSLuint8 getType() {
	return this.type;
    }

    public SSLopaque getData() {
	return this.data;
    }

     public int encode(SSLConn conn,OutputStream s)
       throws Error,java.io.IOException {
       int written=0;

       synchronized(conn){
         Bench.start(1);
         written=type.encode(conn,s);
         written+=version.encode(conn,s);

         if(conn.write_cipher_state!=null){
           byte[] encrypted;
           int length=data.value.length;
           int pad=0;
           SSLCipherSuite cs;

           SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Encoding record");
	   
           cs=conn.write_cipher_state.cipher_suite;
           length+=cs.getDigestOutputLength();

           SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Encrypting: plain text",
             data.value);
           if(cs.blockCipherP()){
             length++;

             pad=8-length%8;
             if(pad==8) pad=0;
             length+=pad;
           }

           Bench.start(5);
           byte[] mac=calcMac(conn,conn.write_cipher_state,
             conn.write_sequence_num++,data.value);
           // Test
           // data.value[0]++;
         
           Bench.end(5);
           SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Encoding MAC",mac);
	 
           byte[] total=new byte[length];

           Bench.start(6);
           System.arraycopy(data.value,0,total,0,data.value.length);
           System.arraycopy(mac,0,total,data.value.length,mac.length);

           if(cs.blockCipherP()){
             for(int i=0;i<(pad+1);i++){
               total[i+mac.length + data.value.length]=(byte)pad;
             }
           }
           Bench.end(6);
         
           Bench.start(3);
           if(conn.write_cipher_state.cipher!=null)
             data.value=conn.write_cipher_state.cipher.update(total);
           else
             data.value=total;
           Bench.end(3);	 
           SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Encrypting: cipher text",
             data.value);
         }
       
         written+=data.encode(conn,s);

         Bench.end(1);
       }
       return written;
     }

     //There's a lot of array creation and copying going on here...
     //We may want to tweak this for further efficiency
     public int decode(SSLConn conn,InputStream s)
       throws java.io.IOException,Error {
       int readb=0;
       int length,maclength;
       SSLopaque ciphertext=new SSLopaque(-65535);
       boolean throw_bad_mac=false;
       
       try {
         // Read the data off the wire
         readb=type.decode(conn,s);
         readb+=version.decode(conn,s);
         readb+=ciphertext.decode(conn,s);

         // Decrypt the record if we have an active algorithm
         if(conn.read_cipher_state != null){
           byte[] plain;
           SSLCipherSuite cs;

           // Just convenience
           cs=conn.read_cipher_state.cipher_suite;

           SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Ciphertext",
             ciphertext.value);
	 
           if(conn.read_cipher_state.cipher != null){
             // Decrypt the plaintext
             plain=conn.read_cipher_state.cipher.
               update(ciphertext.value);
           }
           else{
             // This is a null cipher so just assign the reference
             plain=ciphertext.value;
           }

           SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Plaintext",
             plain);
	 
           if(cs.blockCipherP()){
             // Test
             // plain=new byte[5];
             // plain[4]=8;
             
             length=plain.length;
             
             int pad=(int)(plain[length-1] & 0xff);

             /* Check pad length */
             if(pad>length){
               SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Pad longer than plaintext");
               pad=0;  // Set pad to 0 so that the message MAC can proceed
               throw_bad_mac=true;
             }
             
             // Check the pad bytes if it's TLS
             if(conn.ssl_version>=SSLHandshake.TLS_V1_VERSION){
               int b,offset;

               offset=length-2;
               for(int i=0;i<pad;i++){
                 
                 b=(plain[offset] & 0xff);
                 if(b!=pad){
                   SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Some pad bytes don't match");
                   pad=0; // Set pad to 0 so that the message MAC can proceed
                   throw_bad_mac=true;
                 }

                 offset--;
               }
             }
               
             // SSLv3 padding should be < blocksize
             if(conn.ssl_version==SSLHandshake.SSL_V3_VERSION){
               if(pad>8){
                 SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Pad too long");
                 pad=0; // Set pad to 0 so that the message MAC can proceed
                 throw_bad_mac=true;
               }
             }
            
             length-=pad + 1;
           }
           else{
             length=plain.length;
           }

           
           maclength=conn.read_cipher_state.cipher_suite.getDigestOutputLength();
           if(length<maclength) {
             SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,
               "MAC too long for record--garbage");
             throw_bad_mac=true;
           }

           byte[] msg_data=new byte[length-maclength];
           System.arraycopy(plain,0,msg_data,0,msg_data.length);

	 
           byte[] c_mac=calcMac(conn,conn.read_cipher_state,
             conn.read_sequence_num++,msg_data);
           SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Computed MAC",
             c_mac);
           
           // Test
           // if(conn.read_sequence_num>1) c_mac[0]++;

           // Sanity check
           if(maclength!=c_mac.length)
             throw new InternalError("Digest Length inconsistency");
           
           //We really need a memcmp() equivalent. This sucks
           byte[] p_mac=new byte[c_mac.length];
           
           System.arraycopy(plain,length-p_mac.length,
             p_mac,0,p_mac.length);

           SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Message MAC",p_mac);

           if(!cryptix.util.core.ArrayUtil.areEqual(p_mac,c_mac))
             conn.alert(SSLAlertX.TLS_ALERT_BAD_RECORD_MAC);

           /* If we saw a padding error, throw the alert now.
              This allows us to avoid the Vaudenay timing
              attack by ensuring that we compute the MAC
              anyway */
           if(throw_bad_mac)
             conn.alert(SSLAlertX.TLS_ALERT_BAD_RECORD_MAC);
           
           length-=p_mac.length;
           if(length!=msg_data.length)
             throw new InternalError("Sanity check failed");
	 
           data.value=new byte[msg_data.length];
           System.arraycopy(msg_data,0,data.value,0,msg_data.length);
         }
         else{
           // This was an unencrypted record so copy the "ciphertext" to
           // the value
           data.value=new byte[ciphertext.value.length];
           System.arraycopy(ciphertext.value,0,data.value,0,ciphertext.value.length);
         }
       } catch (IOException ex) {
         // Invalidate the session. This is redundant if we threw
         // an alert since that invalidates the session for us.
         // Still, if something else went wrong...
         conn.makeUnresumable();
         throw ex;
       }
       return readb;
     }

     // The intermediate step where we pack into a single block isn't
     // strictly necessary, but some SSL implementations choke if
     // the data is weirdly fragmented.
     public void send(SSLConn conn)
       throws Error,java.io.IOException {
       ByteArrayOutputStream tos=new ByteArrayOutputStream(data.value.length+30);

       encode(conn,tos);
       
       tos.writeTo(conn.sock_out);
     }

     public byte[] calcMac(SSLConn conn,SSLCipherState st,long sequenceNum,
       byte[] buf){
       return SSLMAC.calcMAC(conn,st,type.value,sequenceNum,buf);
     }
}

