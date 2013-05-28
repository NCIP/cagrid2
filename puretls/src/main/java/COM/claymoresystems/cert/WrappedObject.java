/**
   WrappedObject.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Fri Jun  4 09:11:27 1999

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

   $Id: WrappedObject.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.cert;
import java.io.*;
import cryptix.util.mime.Base64InputStream;
import cryptix.util.mime.Base64OutputStream;
/** Utilities to read -----BEGIN style Base64 encoded objects
    from files and the like. This is used to read what EAY
    refers to as "PEM" keyfiles.

    This class is used internally by PureTLS and should not
    be called by programmers. We'll document it someday, though
*/
public class WrappedObject {
     public WrappedObject() {}; // All methods are static

     public static boolean findObject(BufferedReader br,String end,
       StringBuffer type)
       throws IOException{
       String prefix="-----BEGIN ";
       String postfix=(end==null)?"-----":end + "-----";
       String line;
       
       for(;;){
	 line=br.readLine();

	 if(line==null)
	   return false;

	 if(!line.startsWith(prefix))
	   continue;
	 if(!line.endsWith(postfix))
	   continue;

	 break;
       }

       if(type!=null){
	 type.setLength(0);
	   
	 // Copy the data, minus the prefix and postfix
	 type.append(line.toString().substring(prefix.length(),
	   line.length()-postfix.length()).trim());
       }
       
       return true;
     }
     
     /** Read a base64 encoded block up to an
	 -----END terminator, base64 decode it and return*/
     public static byte[] readBlock(BufferedReader br)
       throws IOException {
       String line;

       ByteArrayOutputStream bos=new ByteArrayOutputStream();
       OutputStreamWriter ow=new OutputStreamWriter(bos);

       for(;;){
	 line=br.readLine();
	   
	 if(line==null)
	     break;
	   if(line.startsWith("-----END "))
	     break;
	   ow.write(line,0,line.length());
	 }

	 ow.flush();

	 byte[] b64data=bos.toByteArray();
	 ByteArrayInputStream bis=new ByteArrayInputStream(b64data);
	 Base64InputStream b64is=new Base64InputStream(bis);

	 byte[] data=new byte[b64data.length];

	 int dlen=b64is.read(data);

	 // Isn't there some way to shorten an array???
	 byte[] trimmeddata=new byte[dlen];
	 System.arraycopy(data,0,trimmeddata,0,dlen);

	 return trimmeddata;
     }

     /* Write an object header */
     public static void writeHeader(String type,BufferedWriter out)
       throws IOException {
       String start="-----BEGIN "+type+"-----"; 

       out.write(start);
       out.newLine();
     }
     
     /** Write a base64 encoded block, and add the terminator
      */
     public static void writeObject(byte[] object,String type,
       BufferedWriter out)
       throws IOException {
       String finish="-----END "+type+"-----";
       
       ByteArrayOutputStream bos=new ByteArrayOutputStream();
       Base64OutputStream b64os=new Base64OutputStream(bos);
       b64os.write(object);
       b64os.flush();
       b64os.close();

       byte[] objEnc=bos.toByteArray();

       ByteArrayInputStream bis=new ByteArrayInputStream(objEnc);
       InputStreamReader ir=new InputStreamReader(bis);
       BufferedReader r=new BufferedReader(ir);

       String line;
       
       while((line=r.readLine())!=null){
	 out.write(line);
	 out.newLine();
       }

       out.write(finish);
       out.newLine();
       out.flush();
     }
       
     public static byte[] loadObject(BufferedReader rdr,String end,
       StringBuffer type)
       throws IOException {

       if(findObject(rdr,end,type))
	 return WrappedObject.readBlock(rdr);
       else
	 return null;
     }

     public static String base64Encode(byte[] in)
       throws IOException {
       ByteArrayOutputStream bos=new ByteArrayOutputStream();
       Base64OutputStream b64os=new Base64OutputStream(bos);
       b64os.write(in);
       b64os.flush();
       b64os.close();

       byte[] enc=bos.toByteArray();
       
       ByteArrayInputStream bis=new ByteArrayInputStream(enc);
       InputStreamReader ir=new InputStreamReader(bis);
       BufferedReader r=new BufferedReader(ir);

       String line;
       StringBuffer sb=new StringBuffer();
       
       while((line=r.readLine())!=null){
	 sb.append(line);
       }

       return sb.toString();
     }

     public static byte[] base64Decode(String in)
       throws IOException {
       ByteArrayOutputStream bos=new ByteArrayOutputStream();
       OutputStreamWriter ow=new OutputStreamWriter(bos);

       ow.write(in);
       byte[] b64data=bos.toByteArray();
       ByteArrayInputStream bis=new ByteArrayInputStream(b64data);
       Base64InputStream b64is=new Base64InputStream(bis);
       
       byte[] data=new byte[b64data.length];
       
       int dlen=b64is.read(data);
       byte[] trimmeddata=new byte[dlen];
       System.arraycopy(data,0,trimmeddata,0,dlen);
       
       return trimmeddata;
     }
}
     
	 
