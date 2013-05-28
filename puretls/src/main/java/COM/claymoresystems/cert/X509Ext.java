/**
   X509Ext.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sun Jul 25 21:12:15 1999

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


   $Id: X509Ext.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.cert;

import COM.claymoresystems.util.Util;
import COM.claymoresystems.sslg.Extension;
import COM.claymoresystems.ptls.SSLDebug;
import java.io.*;
import java.util.Vector;

public class X509Ext implements Extension{
     private byte[] oid;
     private boolean critical;
     private byte[] value;
     
     X509Ext(byte[] encoding)
       throws IOException {
       ByteArrayInputStream is=new ByteArrayInputStream(encoding);

       byte[] inner=DERUtils.decodeSequence(is);

       is=new ByteArrayInputStream(inner);
       oid=DERUtils.decodeOID(is);
       if(DERUtils.isTag(DERUtils.BOOLEAN,is)){
	 critical=DERUtils.decodeBoolean(is);
       }
       value=DERUtils.decodeAny(is);

//       Util.xdump("OID",oid);
//       Util.xdump("value",value);
     }


     public byte[] getOID(){
       return oid;
     }

     public boolean isCritical(){
       return critical;
     }

     public byte[] getValue(){
       return value;
     }

     static X509Ext getExtensionFromCert(X509Cert cert,byte [] oid)
       throws IOException {
       X509Ext fExt=null;
       Vector extensions=cert.getExtensions();

       SSLDebug.debug(SSLDebug.DEBUG_CERT,"Looking for extension",oid);
       
       if(extensions==null)
         return null;

       for(int i=0;i<extensions.size();i++){
         X509Ext ext=(X509Ext)(extensions.elementAt(i));
         SSLDebug.debug(SSLDebug.DEBUG_CERT,"Found extension",ext.getOID());
         if(cryptix.util.core.ArrayUtil.areEqual(ext.getOID(),oid)){
           if(fExt!=null)  
             throw new IOException
               ("Can't have two extensions of this type");

           fExt=ext;
         }
       }

       return fExt;
     }
}

