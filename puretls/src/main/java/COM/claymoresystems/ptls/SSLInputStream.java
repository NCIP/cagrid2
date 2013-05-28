/**
   SSLInputStream.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sat May  8 14:28:31 1999

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

   $Id: SSLInputStream.java,v 1.2 2004/01/09 22:34:07 gawor Exp $

*/

package COM.claymoresystems.ptls;
import COM.claymoresystems.util.*;
import java.io.*;
import java.util.*;

class SSLInputStream extends InputStream {

     SSLRecordReader rdr;
     Silo silo=new Silo(1024);
     
     public SSLInputStream(SSLRecordReader r){
       rdr=r;
     }

     public void write(SSLRecord r){
       silo.write(r.data.value);
     }

     public int read()
       throws java.io.IOException {
       if(rdr.conn.invalid)
         throw new IOException("Can't read from a connection where an error has occurred");
       
       int r;

       for(;;){
	 r=silo.read();

	 if(r>=0)
	   return(r);
         
	 SSLDebug.debug(SSLDebug.DEBUG_CODEC,"Silo empty, reading a record" + r);

	 int rv=rdr.readRecord();
	 if(rv==-1)
	   return(-1);
       } 
     }

     // Note that read() could be more aggressive.
     // I.e. we could check to see if there were
     // more records available and read 'em if they
     // were. This requires propagating available
     // checks all the way down to the record decode
     // level, though, so we don't do it.
     public int read(byte[] b,int off,int len)
       throws java.io.IOException {

       if(rdr.conn.invalid)
         throw new IOException("Can't read from a connection where an error has occurred");
       
       int br;
       
       for(;;){
	 br=silo.read(b,off,len);
	 
	 if(br>=0)
	   return(br);

	 SSLDebug.debug(SSLDebug.DEBUG_CODEC,"Silo empty, reading a record");
	 
	 int rv=rdr.readRecord();
	 if(rv==-1)
	   return(-1);
       }
     }

     public int available()
       throws java.io.IOException {
       return silo.bytesAvailable();
     }
}





