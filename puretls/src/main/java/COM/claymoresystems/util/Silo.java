/**
   Silo.java

   This class is an unlimited size Silo (aka FIFO)
   Hence no Silo overflows!
   
   copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sat May  8 15:10:21 1999

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

   $Id: Silo.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/


package COM.claymoresystems.util;

public class Silo {
     byte buf[];
     int first_ptr;
     int next_ptr;

     public Silo(int size){
       buf=new byte[size];
       first_ptr=0;
       next_ptr=0;
     }

     public Silo(){
       this(1024);
     }

     public void write(byte[] in){
       //First make sure we've got enough room
       if(in.length>(buf.length - next_ptr)){
	 if(in.length< (buf.length - (next_ptr-first_ptr))){
	   // Slide over if possible
	   compact(); 
	 }
	 else{
	   // Otherwise resize the array
	   int minlength=(next_ptr - first_ptr) + in.length;
	   int newlength=buf.length;

	   while(newlength < minlength) newlength *=2;

	   byte[] tmp=new byte[newlength];
	   System.arraycopy(buf,first_ptr,tmp,0,next_ptr-first_ptr);
	   buf=tmp;
	   next_ptr-=first_ptr;
	   first_ptr=0;
	 }
       }

       System.arraycopy(in,0,buf,next_ptr,in.length);
       next_ptr+=in.length;
     }

     public int read(){
       int available=next_ptr-first_ptr;
       int rv;
       
       if(available>0)
	 return (int)(buf[first_ptr++] & 0xff);
       else
	 return -1;
     }

     public int bytesAvailable(){
       return next_ptr-first_ptr;
     }
     
     // Read as many bytes as possible, returning the number read
     public int read(byte[] out,int off,int len){
       int available=next_ptr-first_ptr;
       int tocpy=len>available?available:len;

       if(available<=0)
	 return -1;

       System.arraycopy(buf,first_ptr,out,off,tocpy);
       first_ptr+=tocpy;

       if(first_ptr==next_ptr) compact();
       return tocpy;
     }

     void compact(){
       if(first_ptr != 0){
	 System.arraycopy(buf,first_ptr,buf,0,next_ptr - first_ptr);
	 next_ptr-=first_ptr;
	 first_ptr=0;
       }
     }
}
	 

     
     
