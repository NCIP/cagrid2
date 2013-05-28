/**
   SSLServerHello.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Fri May  7 15:36:35 1999

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

   $Id: SSLServerHello.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/



package COM.claymoresystems.ptls;
import java.io.*;
import java.util.*;

class SSLServerHello extends SSLPDU
{
     SSLuint16 server_version=new SSLuint16();
     SSLopaque random=new SSLopaque(32);
     SSLopaque session_id=new SSLopaque(-32);
     SSLuint16 cipher_suite=new SSLuint16();
     SSLuint8 compression_method=new SSLuint8();

     public int decode(SSLConn conn, InputStream s)
       throws java.io.IOException {
       SSLDebug.debug(SSLDebug.DEBUG_MSG,"Decoding server hello");       
       int br=0;

       br+=server_version.decode(conn,s);
       br+=random.decode(conn,s);
       br+=session_id.decode(conn,s);
       br+=cipher_suite.decode(conn,s);
       br+=compression_method.decode(conn,s);

       return(br);
     }

     public int encode(SSLConn conn, OutputStream s)
       throws java.io.IOException {
       SSLDebug.debug(SSLDebug.DEBUG_MSG,"Encoding server hello");       
       int br=0;

       br+=server_version.encode(conn,s);
       br+=random.encode(conn,s);
       br+=session_id.encode(conn,s);
       br+=cipher_suite.encode(conn,s);
       br+=compression_method.encode(conn,s);

       return(br);
     }
     
}

       
