/**
   RFC822Hdr.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Wed Jun  2 16:45:34 1999

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

   $Id: RFC822Hdr.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.util;
import java.io.BufferedReader;
import java.util.Vector;

/**
   An <code>RFC822Hdr</code> is a single RFC822 style header. This is a colon
   delimited header of the form Name: value (,value2)*. A header can be folded
   across multiple lines by using whitespace characters at the beginning of the
   continuation line.
*/
public class RFC822Hdr {
     String name=null;
     String value=null;
     Vector subfields=null;
     
     /**
	Create an RFC822Hdr by reading from the indicated reader
     */
     public RFC822Hdr(BufferedReader rdr)
       throws IllegalArgumentException, java.io.IOException {
       StringBuffer buf=new StringBuffer();
       boolean cont=false;
       boolean first=true;
       
       do {
	 rdr.mark(1024);
	 String line=rdr.readLine();

	 line=line.trim();

	 // This isn't RFC-822 compliant, but we need it to detect
	 // headerless EAY files. We require a colon in the first header
	 // line or we reject with an end of headers
	 if(first){
	   if(line.indexOf(':')==-1){
	     rdr.reset();
	     break;
	   }

	   first=false;
	 }
	     
	 
	 if(line.length()==0){
	   break;
	 }
	 
	 rdr.mark(2);
	 
	 buf.append(line);

	 // Detect the end of headers
	 if(line.trim().length()==0){
	   break;
	 }

	 // Read ahead one char to see if we've got whitespace
	 int ch=rdr.read();

	 if(ch<0)
	   break;
	 
	 rdr.reset();

	 if(ch!=' ' && ch!='\t')
	   break;
       } while(true);

       if(buf.length()>0){
	 initRFC822Hdr(buf.toString());
       }
     }

     /**
	Create an RFC822Hdr from the indicated string
     */
     public RFC822Hdr(String str)
       throws IllegalArgumentException {
       initRFC822Hdr(str);
     }
     
     private void initRFC822Hdr(String str)
	  throws IllegalArgumentException {
       int col;

       col=str.indexOf(':');

       if(col==-1)
	 throw new IllegalArgumentException("Colon not found in header line");

       name=str.substring(0,col).trim();
       value=str.substring(col+1,str.length()).trim();

       int start=0;
       int end;
       String subfield;
       
       subfields=new Vector();
       
       while((end=value.indexOf(',',start))!=-1){
	 subfield=value.substring(start,end).trim();

	 subfields.addElement((Object)subfield);
	 start=end+1;
       }
       
       subfield=value.substring(start,value.length()).trim();
       subfields.addElement((Object)subfield);       
     }

     /**
	Return the field name
     */
     public String getName(){
       return name;
     }

     /**
	Return the field value
     */
     public String getValue(){
       return value;
     }

     /** Return the field value parsed as if it were a comma-delimited string.
	 Syntactic sugar
     */
     public Vector getSubfields(){
       return subfields;
     }

       
     public String getSubfield(int index){
       String str=(String)subfields.elementAt(index);
//       System.out.println("Returning "+str + str.length());
       return str;
     }
}
