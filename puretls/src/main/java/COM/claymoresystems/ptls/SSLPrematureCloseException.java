/**
   SSLPrematureCloseException.java

   Copyright (C) 2001, RTFM, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sun Apr  8 10:47:08 2001
*/


package COM.claymoresystems.ptls;

/**
   This exception is thrown when the peer closes the connection without
   sending a close notify
*/
public class SSLPrematureCloseException extends SSLException {
     SSLPrematureCloseException(String s){
       super(s);
     }
}
