/**
   SSLSocketXInt

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Wed May  5 08:44:30 1999

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

   $Id: SSLSocketXInt.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/


package COM.claymoresystems.sslg;
import java.util.Vector;
import java.io.*;

/** Extended socket functions that only apply to SSL Sockets
 */
public interface SSLSocketXInt {
     public static final int CLIENT=1;
     public static final int SERVER=2;
     // Interrogators

     /** get the cipherSuite that was negotiated on this socket

	 @return the cipherSuite as one of the integers defined in SSLPolicyInt
     */
     public abstract int getCipherSuite() throws IOException;

     /** get the certificate chain presented by the peer. This is relevant
	 for clients and servers if Client Authentication is being used.

	 @return the certificate chain as a Vector of Certificates, null if unavailable
     */
     public abstract Vector getCertificateChain() throws IOException;

     /** Get the policy associated with this socket

	 @return the policy
     */
     public abstract SSLPolicyInt getPolicy();

     /** Get the SessionID associated with this session */
     public abstract byte[] getSessionID() throws IOException;
     
     /** Get the version of SSL negotiated.

	 @return 768 (0x300) for SSLv3 or 769 (0x301) for TLSv1
     */
     public int getVersion() throws IOException;
     
     /** Renegotiate this connection using the specified policy.
	 This may be used (for instance) to renegotiate using client
	 authentication. If renegotiation is successful, this policy
	 becomes the current policy for this socket

	 @param the policy specified

	 @exception IOException if something goes wrong in renegotiation
     */
     public abstract void renegotiate (SSLPolicyInt policy) throws IOException;
     
     /** Send our half of the SSL close_notify handshake

	 @exception IOException if the close_notify alert can't be sent
     */
     public abstract void sendClose() throws java.io.IOException;

     /** Wait to receive a close_notify from the other side.

	 @param enforceFinished insist that no more data be present on the connection before the close_notify is received. This ensures that the application has read all the data that the peer sent

	 @exception IOException if the close_notify couldn't be read or if enforceFinished is true and more data was present.
     */
     public abstract void waitForClose(boolean enforceFinished) throws
     java.io.IOException;

}
