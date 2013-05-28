/**
   SSLSocket.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue May 18 09:30:32 1999

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

   $Id: SSLSocket.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/


package COM.claymoresystems.ptls;
import COM.claymoresystems.sslg.*;
import COM.claymoresystems.cert.*;
import java.util.Vector;
import java.util.Date;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.SocketException;
import java.io.*;

public class SSLSocket extends Socket
implements SSLSocketXInt {
     SSLConn conn;
     String remote_host;
     int remote_port;

     /** Create an SSLSocket and connect it to the server on the
	 specified host and port, doing the SSLHandshake.
	 We need this to support the CSJ interface, which is why
	 it duplicates the "int" version.

	 @param ctx the SSLContext to use to create this socket
	 @param remote_addr the hostname of the remote machine to connect to
	 @param remote_port the port to connect to

	 @exception IOException if something goes wrong in the handshake or making the connection
     */
     public SSLSocket(SSLContext ctx,
       String remote_addr, Integer remote_port)
       throws java.net.UnknownHostException,java.io.IOException {
       this(ctx,remote_addr,remote_port.intValue());
     }

     /** Create an SSLSocket and connect it to the server on the
	 specified host and port, doing the SSLHandshake

	 @param ctx the SSLContext to use to create this socket
	 @param remote_addr the hostname of the remote machine to connect to
	 @param port the port to connect to

	 @exception IOException if something goes wrong in the handshake or making the connection
     */
     public SSLSocket(SSLContext ctx,
       String remote_addr, int port)
       throws java.net.UnknownHostException,java.io.IOException {
       super(remote_addr,port);
       this.remote_host=remote_addr;
       this.remote_port=port;
       internalSocket(ctx);
     }

     /** Creates a stream socket and connects it to the specified port number at the specified IP address performing the SSL Handshake

	 @param ctx the SSLContext to use to create this socket
	 @param addr the address to connect to
	 @param port the port to connect to 


	 @exception IOException if something goes wrong in the handshake or making the connection performing the SSL Handshake
     */	 
     public SSLSocket(SSLContext ctx,InetAddress addr, int port)
       throws IOException {
       super(addr,port);
       this.remote_host=addr.toString();
       this.remote_port=port;
       internalSocket(ctx);
     }

     /** Creates a stream socket and connects it to the specified port number at the specified IP address performing the SSL Handshake

	 @param ctx the SSLContext to use to create this socket
	 @param addr the address to connect to
	 @param port the port to connect to
	 @param localAddr the local address to bind to
	 @param localPort the local port to bind to

	 @exception IOException if something goes wrong in the handshake or making the connection
     */	 
     public SSLSocket(SSLContext ctx,InetAddress addr, int port, InetAddress localAddr, int localPort)
       throws IOException {
       super(addr,port,localAddr,localPort);
       this.remote_host=addr.toString();
       this.remote_port=port;
       internalSocket(ctx);
     }

     /** Creates a stream socket and connects it to the specified port number at the specified host, performing the SSL Handshake

	 @param ctx the SSLContext to use to create this socket
	 @param host the address to connect to
	 @param port the port to connect to
	 @param localAddr the local address to bind to
	 @param localPort the local port to bind to

	 @exception IOException if something goes wrong in the handshake or making the connection
     */	 
     public SSLSocket(SSLContext ctx,String host, int port, InetAddress localAddr, int localPort)
       throws IOException {
       super(host,port,localAddr,localPort);
       this.remote_host=host;
       this.remote_port=port;
       internalSocket(ctx);
     }

     /** Create an SSLSocket and connect it to the server on the
	 using the specified input stream and output stream

	 @param ctx the SSLContext to use to create this socket
         @param input where data is read from
         @param output where data is written to
	 @param remote_addr the hostname of the remote machine (used for session resumption)
	 @param port the port to connect to (used for session resumption: the host/port pair should be unique)
         @param how which role to take in handshake SSLSocket.CLIENT or SSLSocket.SERVER

	 @exception IOException if something goes wrong in the handshake or making the connection
     */
     
     public SSLSocket(SSLContext ctx,InputStream input,OutputStream output,
       String host,int port,int how)
       throws IllegalArgumentException, IOException{
       this();
       int how2;
       
       switch(how){
         case CLIENT:
           how2=SSLConn.SSL_CLIENT;
           break;
         case SERVER:
           how2=SSLConn.SSL_SERVER;
           break;
         default:
           throw new IllegalArgumentException ("how value" +how+" not supported");
       }

       conn=new SSLConn(this,input,output,ctx,how2);
       this.remote_host=host;
       this.remote_port=port;
       if(conn.getPolicy().handshakeOnConnectP())
         conn.handshake();
     }

     /** Create an SSLSocket around the specified socket.
         Provided by Ronald Tschalar

	 @param ctx the SSLContext to use to create this socket
	 @param socket the underlying socket to wrap around
	 @param remote_addr the hostname of the remote machine (used for session resumption)
	 @param port the port to connect to (used for session resumption: the host/port pair should be unique)
         @param how which role to take in handshake SSLSocket.CLIENT or SSLSocket.SERVER

	 @exception IOException if something goes wrong in the handshake
     */

     public SSLSocket(SSLContext ctx,Socket sock,String host,int port,int how)
       throws IllegalArgumentException, IOException{
       super(new SocketBasedSocketImpl(sock));
       int how2;
       
       switch(how){
         case CLIENT:
           how2=SSLConn.SSL_CLIENT;
           break;
         case SERVER:
           how2=SSLConn.SSL_SERVER;
           break;
         default:
           throw new IllegalArgumentException ("how value" +how+" not supported");
       }

       conn=new SSLConn(this,sock.getInputStream(),sock.getOutputStream(),ctx,how2);
       this.remote_host=host;
       this.remote_port=port;
       if(conn.getPolicy().handshakeOnConnectP())
         conn.handshake();
     }
     
     // No-arg constructor for empty socket
     public SSLSocket(){
     }

     
     void internalSocket(SSLContext ctx)
       throws IOException {
       // Make the SSL connection
       setTcpNoDelay(true);
       conn=new SSLConn(this,super.getInputStream(),
	 super.getOutputStream(),ctx,SSLConn.SSL_CLIENT);

       if(conn.getPolicy().handshakeOnConnectP())
         conn.handshake();
     }
	 
     // Initialize the server side
     void serverSideInit(SSLContext ctx)
       throws IOException {
       conn=new SSLConn(this,super.getInputStream(),
	 super.getOutputStream(),ctx,SSLConn.SSL_SERVER);

       if(conn.getPolicy().handshakeOnConnectP())
         conn.handshake();
     }

     /** Hard close. Don't do SSL closure */
     public void hardClose()
       throws java.io.IOException {
       if(conn.s!=null)
         super.close();
     }
     
     /** close the connection. This executes the closure procedure
	 and throws an error if the close_notify exchange doesn't
	 succeed.

	 @exception IOException if there is a problem with the close_notify
     */
     public void close()
       throws java.io.IOException {
       if(conn!=null) {
	 conn.close();
	 hardClose();
       }
     }

     /**
	Get the input stream associated with this socket.
	Data read from this input stream is automatically SSL
	decrypted

     */
     public InputStream getInputStream(){
       return conn.getInStream();
     }
     
     /**
	Get the input stream associated with this socket.
	Data read from this input stream is automatically SSL
	encrypted
     */
     public OutputStream getOutputStream(){
       return conn.getOutStream();
     }

     /** Converts this socket to a string

	 @return a string description of this socket
     */
     public String toString(){
       return "SSL: " + super.toString();
     }

     // SSL extensions
     /** Get the cipherSuite in use on this socket, as an
	 integer

	 @return the ciphersuite in use
     */	 
     public int getCipherSuite()
       throws IOException {
       return conn.getCipherSuite();
     }

	 
     /** get the certificate chain presented by the peer. This is relevant
	 for clients and servers if Client Authentication is being used.

	 @return the certificate chain as a Vector of X509Certs, null if unavailable            The root is at 0 and the user cert is at n-1
     */
     
     public Vector getCertificateChain()
       throws IOException {
       return conn.getCertificateChain();
     }


     /** Get the SessionID associated with this socket

	 @return the session ID or null if none
     */
     public byte[] getSessionID()
       throws IOException {
       return conn.getSessionID();
     }
     
     /** Get the policy associated with this socket

	 @return the policy
     */
     public SSLPolicyInt getPolicy(){
       return conn.getPolicy();
     }

     /** Get the version of SSL negotiated.

	 @return 768 (0x300) for SSLv3 or 769 (0x301) for TLSv1
     */
     public int getVersion()
       throws IOException {
       return conn.getVersion();
     }

     /** Handshake. Used when automatic handshaking on connect
         is turned off
     */
     public void handshake() throws IOException {
       conn.handshake();
     }
     
     /** Renegotiate the SSL connection using the given policy
	 <P>
         This is useful (for instance) for a server to renegotiate
         using client authentication

	 @param policy the policy to use
     */
     public void renegotiate(SSLPolicyInt policy)
       throws IOException {
       conn.renegotiate(policy);
     }

     /** Renegotiate the SSL connection using the same policy
	 <P>
	 This is mainly useful when a client is responding to a server's
	 request for renegotiation
     */
     public void renegotiate()
       throws IOException {
       conn.renegotiate(conn.getPolicy());
     }

     /** Send our half of the SSL close_notify handshake

	 @exception IOException if the close_notify alert can't be sent
     */
     public void sendClose()
       throws java.io.IOException {
       conn.sendClose();
     }

     /** Wait to receive a close_notify from the other side.

	 @param enforceFinished insist that no more data be present on the connection before the close_notify is received. This ensures that the application has read all the data that the peer sent

	 @exception IOException if the close_notify couldn't be read or if enforceFinished is true and more data was present.
     */
     public void waitForClose(boolean enforceFinished)
       throws java.io.IOException {
       conn.recvClose(enforceFinished);
     }

     /** Don't call this */
     private static void testConn(SSLContext c,String host,String port)
       throws IOException, java.net.UnknownHostException {
       try {
	 SSLSocket s;
	 s=new SSLSocket(c,host,Integer.parseInt(port));

	 Vector cc=s.getCertificateChain();

	 if(cc!=null){
	   System.out.println("Cert chain");

	 
	   for(int i=0;i<cc.size();i++){
	     X509Cert cert=(X509Cert)cc.elementAt(i);

	     System.out.println("Issuer "+cert.getIssuerName().getNameString());
	     System.out.println("Subject "+cert.getSubjectName().getNameString());
	     System.out.println("Serial "+cert.getSerial());
	     System.out.println("Validity "+cert.getValidityNotBefore() +"-"+
	       cert.getValidityNotAfter());

	   }
	 }	   
	 byte buf[]=new byte[4096];

	 InputStreamReader ir=new InputStreamReader(s.getInputStream());
	 BufferedReader br=new BufferedReader(ir);
	 
	 OutputStreamWriter or=new OutputStreamWriter(s.getOutputStream());
	 BufferedWriter bw=new BufferedWriter(or);
	 
	 bw.write("Test string",0,11);
	 bw.newLine();
	 bw.newLine();	 
	 bw.flush();

	 for(;;){
	   String str=br.readLine();

	   if(str==null)
	     break;
	   System.out.println(str);
	 }
	 
	 s.close();
       }       
       catch (SSLAlertException e){
	 throw new Error(e.toString());
       }
     }

     public void _stompOutputStream(java.io.OutputStream out){
       conn._sock_out=out;
       conn.sock_out=new BufferedOutputStream(out);
     }
       
     /** Test code */
     public static void main(String args[])
	  throws java.net.UnknownHostException, java.io.IOException {
       String host="localhost";
       String port="4433";
       SSLSocket s;

       if(args.length==2){
	 host=args[0];
	 port=args[1];
       }

       SSLContext c=new SSLContext();
       c.loadRootCertificates("root.b64");
       c.loadEAYKeyFile("bookdsa.pem",
	 "password");
       System.out.println("Trying 1");
       testConn(c,host,port);
       System.out.println("Trying 2");       
       testConn(c,host,port);
     }
}
