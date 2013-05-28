/**
   LoadProviders.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Thu Oct  7 16:18:58 1999
*/

/** Force the CSPs to load.

    Necessary only if Crypto is used before the SSL code is
    loaded. (I.e. for key generation)*/
package COM.claymoresystems.ptls;

import java.security.Security;
import java.security.Provider;
import java.lang.reflect.*;

public class LoadProviders {
     private static final Class _workaround=java.security.Security.class;
     private static boolean hasOpenssl=false;
     
     /** Force the CSP loads*/
     public static void init(){
       try {
	 String cl_name="COM.claymoresystems.gnp.GoNativeProvider";
	 Class clazz;

	 clazz=Class.forName(cl_name);
	 Provider openssl=(Provider)clazz.newInstance();
	 Security.addProvider(openssl);
	 hasOpenssl=true;
       } catch (NoClassDefFoundError e) {
	 // Do nothing
	 ;
       }
       catch (Exception e){
	 // Do nothing
	 ;
       }
       Security.addProvider(new cryptix.provider.Cryptix());
       Security.addProvider(new COM.claymoresystems.provider.
	 ClaymoreProvider());
     }

     public static String getDSAProvider(){
       if(hasOpenssl)
	 return "GoNativeProvider";
       else
	 return "ClaymoreProvider";
     }

     public static boolean haveGoNativeProvider(){
       return hasOpenssl;
     }
}     


