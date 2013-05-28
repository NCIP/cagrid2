/**
   X509KeyUsage.java

   Copyright (C) 2002, RTFM, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Wed Aug 14 09:43:39 2002
*/

package COM.claymoresystems.cert;

import COM.claymoresystems.util.Util;
import COM.claymoresystems.sslg.Extension;
import COM.claymoresystems.ptls.SSLDebug;
import java.io.*;
import java.util.*;

class X509KeyUsage {
     static byte[] oid={(byte)0x55,(byte)0x1d,(byte)0xf};
     static int BIT_digitalSignature       =0;
     static int BIT_nonRepudiation         =1;
     static int BIT_keyEncipherment        =2;
     static int BIT_dataEncipherment       =3;
     static int BIT_keyAgreement           =4;
     static int BIT_keyCertSign            =5;
     static int BIT_cRLSign                =6;
     static int BIT_encipherOnly           =7;
     static int BIT_decipherOnly           =8;
     
     private boolean critical;     
     BitSet bitsAsserted=null;

     X509KeyUsage(X509Ext ext)
       throws IOException {
       ByteArrayInputStream bis;

       critical=ext.isCritical();

       SSLDebug.debug(SSLDebug.DEBUG_CERT,
         "Contents of keyUsage",ext.getValue());

       // Now parse the extension
       bis=new ByteArrayInputStream(ext.getValue());
       byte[] encoding=DERUtils.decodeOctetString(bis);
       if(bis.available()!=0)
         throw new IOException("Overlong keyUsage encoding, bytes left="+bis.available());
       SSLDebug.debug(SSLDebug.DEBUG_CERT,"Sequence encoding",encoding);
       
       // Now parse the bitstring
       bis=new ByteArrayInputStream(encoding);
       bitsAsserted=DERUtils.decodeBitStringX(bis);
       if(bis.available()!=0)
         throw new IOException("Overlong keyUsage encoding, bytes left="+bis.available());

     }

     boolean isAsserted(int bit) {
       return bitsAsserted.get(bit);
     }
}       
