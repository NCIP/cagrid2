/**
   SSLuintX.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Thu May  6 21:35:50 1999

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

   $Id: SSLuintX.java,v 1.3 2004/01/09 22:34:07 gawor Exp $

*/



package COM.claymoresystems.ptls;
import java.io.*;


public class SSLuintX extends SSLPDU
{
     int value;
     short size;

     public SSLuintX(short s){
       size=s;
     }

     public SSLuintX(short s,int v){
       size=s;
       value=v;
     }

     // Build a uintX big enough to hold an integer as big as this
     public SSLuintX(int range) {
       size=0;
       
       while(range>0){
	 size++;
	 range>>=8;
       }
     }

     public SSLuintX(int range,int v) {
       this(range);
       value=v;
     }

    public int getValue() {
	return this.value;
    }

     public int encode (SSLConn conn, OutputStream s)
       throws Error,java.io.IOException {

	 if (conn.isDebugEnabled(SSLDebug.DEBUG_CODEC)) {
	     conn.debug(SSLDebug.DEBUG_CODEC,"Integer size "+ size +
			"value " + value);
	 }
       	 
       switch(size){
	 case 4:
	   s.write(value>>24 & 0xff);
	   
	 case 3:
	   s.write(value>>16 & 0xff);
	 case 2:
	   s.write(value>>8 & 0xff);
	 case 1:
	   s.write(value & 0xff);
	   break;
	 default:
	   throw new Error("Bad size for uintX");
       }

       return(size);
     }

     public int decode (SSLConn conn, InputStream s)
       throws java.io.IOException {
       int i;

       value=0;

       if (conn.isDebugEnabled(SSLDebug.DEBUG_CODEC)) {
	   conn.debug(SSLDebug.DEBUG_CODEC, "Reading a " + size +
		      "byte integer");
       }

       for(i=0;i<size;i++){
	 int x;

	 value <<= 8;
	 x=s.read();

	 if(x<0) throw new SSLPrematureCloseException("Short read");
	   
	 value += x;

	 if (conn.isDebugEnabled(SSLDebug.DEBUG_CODEC)) {
	     conn.debug(SSLDebug.DEBUG_CODEC,"Read byte " + x);
	 }
       }

       if (conn.isDebugEnabled(SSLDebug.DEBUG_CODEC)) {
	   conn.debug(SSLDebug.DEBUG_CODEC,
		      "Read Integer size " + size + "value " + value);
       }

       return(size);
     }
     
     public void print (SSLConn conn, PrintWriter w){
       w.println("Integer[" + size + "] =" + value);
     } 

     
}
     
     
     
