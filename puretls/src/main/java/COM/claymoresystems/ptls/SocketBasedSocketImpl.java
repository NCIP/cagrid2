/**
   SocketBasedSocketImpl.java

   Copyright (C) 2002, RTFM, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sun Jan 13 16:17:06 2002
*/

package COM.claymoresystems.ptls;

import java.net.*;
import java.io.*;



/* Contributed by Ronald Tschalar */
class SocketBasedSocketImpl extends SocketImpl {
     private Socket socket;

     SocketBasedSocketImpl(Socket socket) {
       this.socket    = socket;
       this.address   = socket.getInetAddress();
       this.port      = socket.getPort();
       this.localport = socket.getLocalPort();
     }

     protected void create(boolean stream) { }
     
     protected void connect(String host, int port) { }
       
     protected void connect(InetAddress address, int port) { }
       
     protected void bind(InetAddress host, int port) { }

     protected void listen(int backlog) { }

     protected void accept(SocketImpl s) { }
     
     protected int available() { return 0; }
       
     protected InputStream getInputStream() throws IOException {
       return socket.getInputStream();
     }
       
     protected OutputStream getOutputStream() throws IOException {
       return socket.getOutputStream();
     }

     protected void close() throws IOException {
       socket.close();
     }

     /* This method is valid in JDK 1.4 (1.3?) or later */
     protected void sendUrgentData(int data) throws IOException { }

     /* This method is valid in JDK 1.4 (1.3?) or later */
     protected void connect(SocketAddress address, int timeout) throws IOException { }

     /* These are only valid in JDK 1.3 or later
        protected void shutdownInput() throws IOException {
        socket.shutdownInput();
        }

        protected void shutdownOutput() throws IOException {
        socket.shutdownOutput();
        }
     */

     public void setOption(int optID, Object value) throws SocketException {
       switch (optID) {
         case TCP_NODELAY:
           socket.setTcpNoDelay(((Boolean) value).booleanValue());
           break;
         case SO_LINGER:
           if (value instanceof Integer)
             socket.setSoLinger(true, ((Integer) value).intValue());
           else
             socket.setSoLinger(false, -1);
           break;
         case SO_TIMEOUT:
           socket.setSoTimeout(((Integer) value).intValue());
           break;
	   /* These are only valid in JDK 1.2 or later
              and don't make much sense for SSL/TLS
              case SO_SNDBUF:
              socket.setSendBufferSize(((Integer) value).intValue());
              break;
              case SO_RCVBUF:
              socket.setReceiveBufferSize(((Integer) value).intValue());
              break;
           */
	   /* These are only valid in JDK 1.3 or later
              Removed for portability to 1.2 and 1.2              
              case SO_KEEPALIVE:
              socket.setKeepAlive(((Boolean) value).booleanValue());
              break;
           */
         default:
           throw new SocketException("Unexpected option " + optID);
       }
     }

     public Object getOption(int optID) throws SocketException {
       switch (optID) {
         case TCP_NODELAY:
           return new Boolean(socket.getTcpNoDelay());
         case SO_BINDADDR:
           return socket.getLocalAddress();
         case SO_LINGER:
           int linger = socket.getSoLinger();
           return (linger < 0) ? (Object) Boolean.FALSE : (Object) new Integer(linger);
         case SO_TIMEOUT:
           return new Integer(socket.getSoTimeout());
	   /* These are only valid in JDK 1.2 or later
              and don't make much sense for SSL/TLS              
              case SO_SNDBUF:
              return new Integer(socket.getSendBufferSize());
              case SO_RCVBUF:
              return new Integer(socket.getReceiveBufferSize());
           */
	   /* These are only valid in JDK 1.3 or later
              Removed for portability to 1.2 and 1.2
              case SO_KEEPALIVE:
              return new Boolean(socket.getKeepAlive());
           */
         default:
           throw new SocketException("Unexpected option " + optID);
       }
     }
}
