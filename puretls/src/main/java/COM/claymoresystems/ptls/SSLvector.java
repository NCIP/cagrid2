/**
   SSLvector.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Fri May  7 07:04:42 1999

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

   $Id: SSLvector.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/


package COM.claymoresystems.ptls;
import java.io.*;
import java.util.*;
       
class SSLvector extends SSLPDU
{
     Vector value;
     SSLPDU prototype;
     int size;

     // Use this constructor to create an object for encoding
     public SSLvector(int s,Vector v){
       size=s;
       value=v;
     }
     
     // Use this constructor to create an object for decoding
     public SSLvector(int s,SSLPDU p){
       size=s;
       value=new Vector();
       prototype=p;
     }
     
      
     public int encode (SSLConn conn, OutputStream s)
	  throws java.io.IOException {
       ByteArrayOutputStream bs=null;
       OutputStream save=null;
       int byteswritten=0;

       if(size<0){
	 // If we're variable length, copy the bytes to a temp stream
	 save=s;
	 bs= new ByteArrayOutputStream();
	 s=bs;
	 conn.debug(SSLDebug.DEBUG_CODEC,"Vector, max length " + -1*size);
       }
       
       int last=value.size();
       int i;

       for(i=0;i<last;i++){
	 SSLPDU el=(SSLPDU)value.elementAt(i);
	 byteswritten+=el.encode(conn,s);
       }

       if(size<0){
	 // Now write the bytes back out along with the length field
	 SSLuintX lu=new SSLuintX(-size);
	 
	 lu.value=byteswritten;
	 byteswritten+=lu.encode(conn,save);
	 bs.writeTo(save);
	 
	 conn.debug(SSLDebug.DEBUG_CODEC,"Actual length " + lu.value);
       }

       return(byteswritten);
     }
	 
       
     public int decode (SSLConn conn, InputStream s)
       throws java.io.IOException, Error {
       int bytesread=0,br;
       int length;

       // If the size is variable, compute the length
       if(size<0){
	 SSLuintX lu=new SSLuintX(-size);
	 
	 bytesread=lu.decode(conn,s);
	 length=lu.value;
       }
       else
	 length=size;
       
       while(length>0){
	 SSLPDU n;

	 try {
	   n=(SSLPDU)prototype.clone();
	 } catch (java.lang.CloneNotSupportedException e){
	   throw new Error("This SHOULD be cloneable...");
	 }
	 
	 br=n.decode(conn,s);
	 
	 length-=br;
	 bytesread+=br;
	 
	 value.addElement((Object)n);
       }

       return bytesread;
     }

}
       
     
     
