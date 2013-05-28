/**
   SSLOutputStream.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Thu May 13 15:58:47 1999

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

   $Id: SSLOutputStream.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.ptls;
import COM.claymoresystems.*;
import java.io.*;

class SSLOutputStream extends OutputStream {
     SSLConn conn;

     public SSLOutputStream(SSLConn c){
       conn=c;
     }
       
     public void write(int b)
       throws IOException{
       
       if(conn.invalid)
         throw new IOException("Can't write to connection where an error has occurred");
         
       byte[] buf=new byte[1];

       buf[0]=(byte)(b&0xff);

       write(buf,0,1);
     }

     public void write(byte b[],int off,int len)
       throws IOException {
       SSLRecord r;

       if(conn.invalid)
         throw new IOException("Can't write to connection where an error has occurred");
       
       SSLDebug.debug(SSLDebug.DEBUG_MSG,"Writing buffer of length " + len);
       while(len>0){
	 int towrite=(len>16000)?16000:len;
	 byte[] tmp_buf; // TODO: Make this efficient

	 tmp_buf=new byte[towrite]; // TODO: Yuck. Slow. FIX SSLopaque
	 
	 System.arraycopy(b,off,tmp_buf,0,towrite);

	 r=new SSLRecord(conn,SSLRecord.SSL_CT_APPLICATION_DATA,tmp_buf);
	 r.send(conn);
         
	 len-=towrite;
	 off+=towrite;

       }

       conn.sock_out.flush();       
     }
}
       
