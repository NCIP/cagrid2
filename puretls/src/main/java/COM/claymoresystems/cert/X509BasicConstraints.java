/**
   X509BasicConstraints.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Wed Aug 14 09:43:51 2002

   This package is a SSLv3/TLS implementation written by Eric Rescorla
   <ekr\@rtfm.com> and licensed by Claymore Systems, Inc.

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


   $Id: X509BasicConstraints.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.cert;

import COM.claymoresystems.util.Util;
import COM.claymoresystems.sslg.Extension;
import COM.claymoresystems.ptls.SSLDebug;
import java.io.*;
import java.util.*;

class X509BasicConstraints {
     private boolean critical;     
     private boolean cA;
     private int pathLen;
     static byte[] oid={(byte)0x55,(byte)0x1d,(byte)0x13};

     X509BasicConstraints(X509Ext ext)
       throws IOException {
       ByteArrayInputStream bis;
       critical=false;
       cA=false;
       pathLen=255;

       critical=ext.isCritical();

       SSLDebug.debug(SSLDebug.DEBUG_CERT,
         "Contents of basic constraints",ext.getValue());

       // Now parse the extension
       bis=new ByteArrayInputStream(ext.getValue());
       byte[] encoding=DERUtils.decodeOctetString(bis);
       if(bis.available()!=0)
         throw new IOException("Overlong Basic Constraints encoding, bytes left="+bis.available());
       SSLDebug.debug(SSLDebug.DEBUG_CERT,"Sequence encoding",encoding);

       bis=new ByteArrayInputStream(encoding);
       byte[] tmp=DERUtils.decodeSequence(bis);
       if(bis.available()!=0)
         throw new IOException("Overlong Basic Constraints encoding, bytes left="+bis.available());
       SSLDebug.debug(SSLDebug.DEBUG_CERT,"Sequence internal data",tmp);

       bis=new ByteArrayInputStream(tmp);
       if(bis.available()>0){
         // Decode the cA field, default to false
         if(DERUtils.isTag(DERUtils.BOOLEAN,bis)){
           cA=DERUtils.decodeBoolean(bis);
         }

         if(bis.available()>0){
           // Decode the pathLenConstraint field, default to
           // 255, which is plenty big
           if(DERUtils.isTag(DERUtils.INTEGER,bis)){
             pathLen=DERUtils.decodeIntegerX(bis);
           }
         }
       }

       if(bis.available()!=0)
         throw new IOException("Bad encoding for Basic Constraints");
     }

     boolean isCritical() {
       return critical;
     }

     boolean isCA() {
       return cA;
     }

     int getPathLen() {
       return pathLen;
     }
}

       




