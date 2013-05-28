/**
   SSLFinished.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue May 11 11:15:57 1999

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

   $Id: SSLFinished.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/


package COM.claymoresystems.ptls;
import java.io.*;

class SSLFinished extends SSLPDU {
     SSLopaque finished;
     byte[] value;
     
     public SSLFinished(SSLConn conn,SSLHandshake hs,boolean mine){
       switch(conn.ssl_version){
	 case SSLHandshake.SSL_V3_VERSION:
	   value=SSLv3Finished.computeFinished(hs,mine);
	   break;
	 case SSLHandshake.TLS_V1_VERSION:
	   value=TLSFinished.computeFinished(hs,mine);
	   break;
	 default:
	   throw new Error("Unsupported version");
       }

       finished=new SSLopaque(value.length);
     }
     
     public int encode (SSLConn conn, OutputStream s)
       throws Error,java.io.IOException {
       // Test
       // value[0]++;
       
       finished.value=value;

       // Test
       // finished=new SSLopaque(0);
       // finished.value=new byte[0];
       
       return finished.encode(conn,s);
     }

     public int decode (SSLConn conn, InputStream s)
       throws java.io.IOException {
       int rb;
       
       rb=finished.decode(conn,s);
       // Test
       // finished.value[0]++;
       
       if(!cryptix.util.core.ArrayUtil.areEqual(value,finished.value))
	 conn.alert(SSLAlertX.TLS_ALERT_BAD_RECORD_MAC);
       
       return rb;
     }
}
