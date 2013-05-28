/**
   Extension.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sun Jul 25 21:12:59 1999
*/

package COM.claymoresystems.sslg;

public interface Extension {
     public byte[] getOID();
     public boolean isCritical();
     public byte[] getValue();
}
