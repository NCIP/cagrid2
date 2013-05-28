/**
   SSLContextInt.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Wed May  5 11:05:30 1999

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

   $Id: SSLContextInt.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/



package COM.claymoresystems.sslg;

import COM.claymoresystems.sslg.*;
import java.io.*;

abstract public class SSLContextInt extends Object
{
     protected SSLPolicyInt policy=new SSLPolicyInt();

     // Load a PKCS-12 keyfile
     public abstract void loadPKCS12File(String path,
       String passphrase) throws java.io.IOException;

     // Load an OpenSSL style key file
     public abstract void loadEAYKeyFile(String path,
       String passphrase) throws java.io.IOException;
     public abstract void loadEAYKeyFile(InputStream is,
       String passphrase) throws java.io.IOException;

     // Save an OpenSSL style key file
     public abstract void saveEAYKeyFile(String path,
       String passphrase) throws java.io.IOException;

     // Load randomness
     public abstract void useRandomnessFile(String path,
       String passphrase) throws java.io.IOException;

     // Load DH params
     public abstract void loadDHParams(String path) throws java.io.IOException;
     public abstract void loadDHParams(InputStream is) throws java.io.IOException;
     public abstract void saveDHParams(String path,int size,
       boolean sophieGermain) throws java.io.IOException;

     
     // Load the roots we trust
     public abstract void loadRootCertificates(String path) throws java.io.IOException ;
     public abstract void loadRootCertificates(InputStream is) throws java.io.IOException ;

     /** Set the policy for this SSLContext. This controls the
	 various cryptographic negotiation decisions made during
	 the handshake.
	 <P>
	 This policy will be used as the policy for all
	 SSLSocket/SSLServerSockets created using this SSLContext.

	 @param p the policy to attach to this context
     */
     public void setPolicy(SSLPolicyInt p){
       policy=p;
     }

     /** Get the current policy for this SSLContext

	 @return the current policy
     */
     public SSLPolicyInt getPolicy() {
       return policy;
     }
}


