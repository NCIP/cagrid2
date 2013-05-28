/**
   DistinguishedName.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Thu Jul  8 21:22:31 1999
*/

package COM.claymoresystems.sslg;
import java.util.Vector;

public interface DistinguishedName {
     public byte[] getNameDER();
     public Vector getName();
     public String getNameString();
}
