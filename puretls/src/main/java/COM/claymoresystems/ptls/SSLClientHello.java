/**
   SSLClientHello.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Fri May  7 08:12:17 1999

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

   $Id: SSLClientHello.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.ptls;
import java.io.*;
import java.util.*;

class SSLClientHello extends SSLPDU
{
     SSLuint16 client_version=new SSLuint16();
     SSLopaque random=new SSLopaque(32);
     SSLopaque session_id=new SSLopaque(-32);
     SSLvector cipher_suites;
     SSLvector compression_methods;

     public int encode (SSLConn conn, OutputStream s)
       throws IOException {
       int written=0;
       SSLDebug.debug(SSLDebug.DEBUG_MSG,"Encoding client hello");
       
       written=client_version.encode(conn,s);
       written+=random.encode(conn,s);
       written+=session_id.encode(conn,s);
       written+=cipher_suites.encode(conn,s);
       written+=compression_methods.encode(conn,s);
       
       return written;
     }

     public int decode (SSLConn conn, InputStream s)
       throws IOException {
       int rb=0;
       SSLDebug.debug(SSLDebug.DEBUG_MSG,"Decoding client hello");
       cipher_suites=new SSLvector(-65535,new SSLuint16());
       compression_methods=new SSLvector(-255,new SSLuint8());
       
       rb=client_version.decode(conn,s);
       rb+=random.decode(conn,s);
       rb+=session_id.decode(conn,s);
       rb+=cipher_suites.decode(conn,s);
       rb+=compression_methods.decode(conn,s);
       
       return rb;
     }

}
