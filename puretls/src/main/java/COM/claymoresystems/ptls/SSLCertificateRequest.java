/**
   SSLCertificateRequest.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sat Jun 12 15:54:50 1999

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

   $Id: SSLCertificateRequest.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.ptls;
import COM.claymoresystems.cert.X509Cert;
import java.io.*;
import java.util.*;

class SSLCertificateRequest extends SSLPDU
{
     SSLvector certificate_types=new SSLvector(-255,new SSLuint8());
     SSLvector distinguished_names=new SSLvector(-65535,new SSLopaque(-65535));

     public int encode(SSLConn conn,OutputStream os)
       throws IOException {
       int wb;
       
       // This should really depend on what algorithms we
       // have available
       SSLuint8 cert_type_dss=new SSLuint8(2);
       SSLuint8 cert_type_rsa=new SSLuint8(1);
       certificate_types.value.addElement((Object)cert_type_rsa);       
       certificate_types.value.addElement((Object)cert_type_dss);

       Vector roots=conn.hs.cert_ctx.getRootList();
       
       for(int i=0;i<roots.size();i++){
	 byte[] root_dn=((X509Cert)roots.elementAt(i)).getSubjectDER();

	 SSLopaque root_opaque=new SSLopaque(-65535);
	 root_opaque.value=root_dn;

	 distinguished_names.value.addElement(root_opaque);
       }

       wb=certificate_types.encode(conn,os);
       wb+=distinguished_names.encode(conn,os);

       return wb;
     }

     public int decode(SSLConn conn,InputStream is)
       throws IOException {
       int rb;

       rb=certificate_types.decode(conn,is);
       rb+=distinguished_names.decode(conn,is);
       
       return rb;
     }
     
}

