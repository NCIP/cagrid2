/**
   SSLv2ClientHello.java

   Copyright (C) 2000, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Wed Nov  1 15:12:54 2000

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

   $Id: SSLv2ClientHello.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/
package COM.claymoresystems.ptls;
import java.io.*;
import java.util.*;

class SSLv2ClientHello extends SSLPDU
{
     SSLuint16 len=new SSLuint16();
     SSLuint16 client_version=new SSLuint16();
     SSLopaque session_id;
     SSLopaque challenge;
     SSLvector cipher_specs;
     byte[] message_value;
     
     public int decode(SSLConn conn,InputStream s)
       throws IOException {
       SSLuint16 cs_len=new SSLuint16();
       SSLuint16 sid_len=new SSLuint16();
       SSLuint16 chall_len=new SSLuint16();
       int rb=0;
       
       rb+=len.decode(conn,s); // Assume 2-byte header form
       
       byte[] tmp=new byte[len.value & 0x7fff];
       rb+=s.read(tmp);

       ByteArrayInputStream is=new ByteArrayInputStream(tmp);
       int msg_num=is.read();
       client_version.decode(conn,is);
       cs_len.decode(conn,is);
       sid_len.decode(conn,is);
       chall_len.decode(conn,is);

       cipher_specs=new SSLvector(cs_len.value,new SSLuint24());
       cipher_specs.decode(conn,is);

       session_id=new SSLopaque(sid_len.value);
       session_id.decode(conn,is);

       challenge=new SSLopaque(chall_len.value);
       challenge.decode(conn,is);

       // Finally save the whole message so that we can
       // digest it
       message_value=tmp;
       
       return(rb);
     }
}

     
