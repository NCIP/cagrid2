/**
   SSLopaque.java

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

   $Id: SSLopaque.java,v 1.3 2004/01/09 22:34:07 gawor Exp $

*/


package COM.claymoresystems.ptls;
import java.io.*;

public class SSLopaque extends SSLPDU
{
     int length;
     byte[] value;
     
     public SSLopaque(int l){
       length=l;
     }

     public SSLopaque(int l,byte[] v){
       length=l;
       value=v;
     }

    public byte[] getValue() {
	return this.value;
    }

     public int encode (SSLConn conn, OutputStream s)
       throws Error, java.io.IOException {
       int written=0;

       if(length<0){
	 SSLuintX lu=new SSLuintX(-length,value.length);
	 if (SSLDebug.getDebug(SSLDebug.DEBUG_CODEC)) {
	     SSLDebug.debug(SSLDebug.DEBUG_CODEC,"Opaque <" + 
			    -1 * length +">" +
			    "length" + value.length);
	 }
	 written=lu.encode(conn,s);
       }
       else{
	   if (SSLDebug.getDebug(SSLDebug.DEBUG_CODEC)) {
	       SSLDebug.debug(SSLDebug.DEBUG_CODEC,"Opaque [" +length +"]");
	   }
	 if(length!=value.length)
	   throw new Error("Array length doesn't match opaque size");
       }

       s.write(value);
       written+=value.length;

       return(written);
     }

     public int decode (SSLConn conn, InputStream s)
       throws java.io.IOException {
       int readb=0,rb;

       if(length<0){
	 SSLuintX lu=new SSLuintX(-length);
	 
	 readb=lu.decode(conn,s);

         if(lu.value>(-length))
           throw new IOException("Opaque length "+lu.value+" > maximum size "+-length);
         
	 value=new byte[lu.value];
       }
       else
	 value=new byte[length];
       
       int off=0;
       int left=value.length;

       while(left>0){
	 rb=s.read(value,off,left);
	 if(rb<0)
	   throw new SSLPrematureCloseException("Short read");
       
	 off+=rb;
	 left-=rb;
       }
       
       return(readb+value.length);
     }
     
     public void print (SSLConn conn, PrintWriter w){
       w.print("Opaque ");
       if(length<0)
	 w.print("max (" + (-1*length) + ")");
       w.println("length " + value.length);

       //Uh, we should print the data
     }

     public Object clone() throws CloneNotSupportedException {
       return super.clone();
     }
}
       
     
