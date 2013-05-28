/**
   SSLServerSocket.java

   Copyright (C) 1999 Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue Jun 15 13:32:28 1999

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

   $Id: SSLServerSocket.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.ptls;
import COM.claymoresystems.sslg.*;
import COM.claymoresystems.cert.*;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Vector;
import java.util.Date;
import java.io.*;


public class SSLServerSocket extends java.net.ServerSocket
//implements SSLSocketXInt {
{
     SSLContext ctx;

     /** Create an SSLServerSocket specifying the port, backlog, and listen
	 address. We need this to support the CSJ interface, which is why
	 it duplicates the "int" version

	 @param ctx the SSLContext to use to create this socket
	 @param port the port to listen on
	 @param backlog the number of connections to queue up
	 @param inetaddr the address to listen on, assuming a multihomes machine. A null value listens on all interfaces

	 @exception IOException if something goes wrong creating the socket
     */
     
     public SSLServerSocket(SSLContext ctx, Integer port, Integer backlog,
       InetAddress inetaddr)
       throws IOException {
       this(ctx,port.intValue(),backlog.intValue(),inetaddr);
     }
     /** Create an SSLServerSocket specifying the port, backlog, and listen
	 address

	 @param ctx the SSLContext to use to create this socket
	 @param port the port to listen on
	 @param backlog the number of connections to queue up
	 @param inetaddr the address to listen on, assuming a multihomes machine. A null value listens on all interfaces

	 @exception IOException if something goes wrong creating the socket
     */
     public SSLServerSocket(SSLContext ctx, int port, int backlog,
       InetAddress inetaddr)
       throws IOException {
       super(port,backlog,inetaddr);
       this.ctx=ctx;
     }

     /** Create an SSLServerSocket specifying the port and backlog

	 @param ctx the SSLContext to use to create this socket
	 @param port the port to listen on

	 @exception IOException if something goes wrong creating the socket
     */
     public SSLServerSocket(SSLContext ctx,int port)
       throws IOException {
       this(ctx,port,50);
     }


     /** Create an SSLServerSocket specifying the port only

	 @param ctx the SSLContext to use to create this socket
	 @param port the port to listen on
	 @param backlog the number of connections to queue up

	 @exception IOException if something goes wrong creating the socket
     */
     public SSLServerSocket(SSLContext ctx,int port,int backlog)
       throws IOException {
       this(ctx,port,backlog,(InetAddress) null);
     }

     /** Accept a connection on this socket, and perform the
	 SSL server handshake in the process

	 @return an SSLSocket attached to the opposite end

	 @exception IOException if an error occurs either during the accept or the handshake
     */	 
     public Socket accept()
       throws IOException {
       SSLSocket s=new SSLSocket();
       implAccept(s);       
       s.serverSideInit(ctx);
       return s;
     }

     // Test code
     /** Internal test code*/
     public static void main(String[] args)
       throws IOException {
       SSLPolicyInt policy=new SSLPolicyInt();
       policy.requireClientAuth(true);
       SSLContext ctx=new SSLContext();
       ctx.setPolicy(policy);
       ctx.loadRootCertificates("root.b64");
       ctx.loadEAYKeyFile("bookdsa.pem","password");
       SSLServerSocket sock=new SSLServerSocket(ctx,2311);

       for(;;){
       
	 SSLSocket s=(SSLSocket)sock.accept();

	 System.out.println("Cert chain");
	 if(policy.requireClientAuthP()){
	   Vector cc=s.getCertificateChain();
	   
	   for(int i=0;i<cc.size();i++){
	     X509Cert cert=(X509Cert)cc.elementAt(i);

	     System.out.println("Issuer "+cert.getIssuerName().getNameString());
	     System.out.println("Subject "+cert.getSubjectName().getNameString());
	     System.out.println("Serial "+cert.getSerial());
	     System.out.println("Validity "+cert.getValidityNotBefore() +"-"+
	       cert.getValidityNotAfter());

	   }
	 }
	 
	 InputStreamReader ir=new InputStreamReader(s.getInputStream());
	 BufferedReader br=new BufferedReader(ir);
	 
	 OutputStreamWriter or=new OutputStreamWriter(s.getOutputStream());
	 BufferedWriter bw=new BufferedWriter(or);

	 String req=br.readLine();

	 System.out.println(req);

	 String rsp="Server stuff";
	 bw.write(rsp,0,rsp.length());
	 bw.flush();
	 s.close();
	 System.out.println("Success");
       }

//       sock.close();

     }
}
