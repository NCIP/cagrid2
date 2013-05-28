/**
   DebuggingByteArrayInputStream.java

   Copyright (C) 2001, RTFM, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sat Oct 27 09:38:31 2001
*/

package COM.claymoresystems.cert;

import java.io.*;
import COM.claymoresystems.util.Util;

public class DebuggingByteArrayInputStream extends InputStream {
     InputStream is;
     
     public DebuggingByteArrayInputStream(InputStream is_){
       is=is_;
     }

     public int read() throws IOException {
       int c=is.read();

       System.out.println("DEBUG: read " + c);
       return c;
     }

     public int read(byte[] b,int off,int len)
       throws java.io.IOException {
       int x=is.read(b,off,len);

       byte[] b2=new byte[x];

       System.arraycopy(b,off,b2,0,x);
       Util.xdump("DEBUG: read ",b);
       return x;
     }
}
