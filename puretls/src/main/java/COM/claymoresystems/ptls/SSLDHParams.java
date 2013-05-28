/**
SSLDHParams.java

Copyright (C) 1999, Claymore Systems, Inc.
All Rights Reserved.

ekr@rtfm.com  Sat Jun 12 14:13:35 1999

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

   $Id: SSLDHParams.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $
*/

package COM.claymoresystems.ptls;
import COM.claymoresystems.crypto.DHPrivateKey;

import java.io.*;
import java.util.*;

class SSLDHParams extends SSLPDU {
     SSLopaque DH_p=new SSLopaque(-65535);
     SSLopaque DH_g=new SSLopaque(-65535);     
     SSLopaque DH_Ys=new SSLopaque(-65535);

     // Noarg constructor for the client
     public SSLDHParams(){};

     public SSLDHParams(DHPrivateKey key){
       DH_p.value=key.getpBytes();
       DH_g.value=key.getgBytes();
       DH_Ys.value=key.getYBytes();
     }
 
     public int decode(SSLConn conn, InputStream s)
       throws Error, java.io.IOException{
       int r=0;
       
       r=DH_p.decode(conn,s);
       r+=DH_g.decode(conn,s);
       r+=DH_Ys.decode(conn,s);

       return r;
     }

     public int encode(SSLConn conn, OutputStream s)
       throws IOException {
       int r=0;

       r=DH_p.encode(conn,s);
       r+=DH_g.encode(conn,s);
       r+=DH_Ys.encode(conn,s);

       return r;
     }
}
