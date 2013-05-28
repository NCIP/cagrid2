/**
   SSLHandshakeFailedException.java

   Copyright (C) 2001, RTFM, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Sun Apr  8 10:53:38 2001
*/


package COM.claymoresystems.ptls;

/**
   This exception is thrown when the handshake fails for some
   reason other than an alert being sent/received

*/
public class SSLHandshakeFailedException extends SSLException {
     public SSLHandshakeFailedException(String s){
       super(s);
     }
}
