/**
   SSLAlertX.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue May 18 10:15:29 1999

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

   $Id: SSLAlertX.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/


package COM.claymoresystems.ptls;

class SSLAlertX {
  public static int TLS_ALERT_WARNING=1;
  public static int TLS_ALERT_FATAL=2;
     
  public static int TLS_ALERT_CLOSE_NOTIFY=0;
  public static int TLS_ALERT_UNEXPECTED_MESSAGE=10;
  public static int TLS_ALERT_BAD_RECORD_MAC=20;
  public static int TLS_ALERT_DECRYPTION_FAILED=21;
  public static int TLS_ALERT_RECORD_OVERFLOW=22;
  public static int TLS_ALERT_DECOMPRESSION_FAILURE=30;
  public static int TLS_ALERT_HANDSHAKE_FAILURE=40;
  public static int SSL_ALERT_NO_CERTIFICATE=41;
  public static int TLS_ALERT_BAD_CERTIFICATE=42;
  public static int TLS_ALERT_UNSUPPORTED_CERTIFICATE=43;
  public static int TLS_ALERT_CERTIFICATE_REVOKED=44;
  public static int TLS_ALERT_CERTIFICATE_EXPIRED=45;
  public static int TLS_ALERT_CERTIFICATE_UNKNOWN=46;
  public static int TLS_ALERT_ILLEGAL_PARAMETER=47;
  public static int TLS_ALERT_UNKNOWN_CA=48;
  public static int TLS_ALERT_ACCESS_DENIED=49;
  public static int TLS_ALERT_DECODE_ERROR=50;
  public static int TLS_ALERT_DECRYPT_ERROR=51;
  public static int TLS_ALERT_EXPORT_RESTRICTION=60;
  public static int TLS_ALERT_PROTOCOL_VERSION=70;
  public static int TLS_ALERT_INSUFFICIENT_SECURITY=71;
  public static int TLS_ALERT_INTERNAL_ERROR=80;
  public static int TLS_ALERT_USER_CANCELLED=90;
  public static int TLS_ALERT_NO_RENEGOTIATION=100;

  private static final SSLAlertX master_list[]={
       new SSLAlertX(
	    TLS_ALERT_CLOSE_NOTIFY,
	    false,
	    0,
	    "Close notify"),
       new SSLAlertX(
	    TLS_ALERT_UNEXPECTED_MESSAGE,
	    true,
	    0,
	    "Unexpected message"),
       new SSLAlertX(
	    TLS_ALERT_BAD_RECORD_MAC,
	    true,
	    0,
	    "Bad record MAC"),
       new SSLAlertX(
	    TLS_ALERT_DECRYPTION_FAILED,
	    true,
            TLS_ALERT_BAD_RECORD_MAC, // We'd get this
	    "Decryption failed"),
       new SSLAlertX(
	    TLS_ALERT_RECORD_OVERFLOW,
	    true,
            TLS_ALERT_BAD_RECORD_MAC,
	    "Record overflow"),
       new SSLAlertX(
	    TLS_ALERT_DECOMPRESSION_FAILURE,
	    true,
	    0,
	    "Decompression failure"),
       new SSLAlertX(
	    TLS_ALERT_HANDSHAKE_FAILURE,
	    true,
	    0,
	    "Handshake failure"),
       new SSLAlertX(
	    SSL_ALERT_NO_CERTIFICATE,
	    false,
	    0,
	    "No certificate"),
       new SSLAlertX(
	    TLS_ALERT_BAD_CERTIFICATE,
	    false,
	    0,
	    "Bad certificate"),
       new SSLAlertX(
	    TLS_ALERT_UNSUPPORTED_CERTIFICATE,
	    false,
	    0,
	    "Unsupported certificate type"),
       new SSLAlertX(
	    TLS_ALERT_CERTIFICATE_REVOKED,
	    false,
	    0,
	    "Revoked certificate"),
       new SSLAlertX(
	    TLS_ALERT_CERTIFICATE_EXPIRED,
	    false,
	    0,
	    "Expired certificate"),
       new SSLAlertX(
	    TLS_ALERT_CERTIFICATE_UNKNOWN,
	    false,
	    TLS_ALERT_BAD_CERTIFICATE,
	    "Unknown certificate processing problem"),
       new SSLAlertX(
	    TLS_ALERT_ILLEGAL_PARAMETER,
	    true,
	    0,
	    "Illegal parameter"),
       new SSLAlertX(
	    TLS_ALERT_UNKNOWN_CA,
	    true,
	    TLS_ALERT_BAD_CERTIFICATE,
	    "Unknown CA"),
       new SSLAlertX(
	    TLS_ALERT_ACCESS_DENIED,
	    true,
	    TLS_ALERT_HANDSHAKE_FAILURE,
	    "Access denied"),
       new SSLAlertX(
	    TLS_ALERT_DECODE_ERROR,
	    true,
	    TLS_ALERT_HANDSHAKE_FAILURE,
	    "Decode error"),
       new SSLAlertX(
	    TLS_ALERT_DECRYPT_ERROR,
	    false,
	    TLS_ALERT_HANDSHAKE_FAILURE,
	    "Decrypt error"),
       new SSLAlertX(
	    TLS_ALERT_EXPORT_RESTRICTION,
	    true,
	    TLS_ALERT_HANDSHAKE_FAILURE,
	    "Export restriction"),
       new SSLAlertX(
	    TLS_ALERT_PROTOCOL_VERSION,
	    true,
	    TLS_ALERT_HANDSHAKE_FAILURE,
	    "Protocol version"),
       new SSLAlertX(
	    TLS_ALERT_INSUFFICIENT_SECURITY,
	    true,
	    TLS_ALERT_HANDSHAKE_FAILURE,
	    "Insufficient security"),
       new SSLAlertX(
	    TLS_ALERT_INTERNAL_ERROR,
	    true,
	    TLS_ALERT_HANDSHAKE_FAILURE, // HUH?
	    "Internal error"),
       new SSLAlertX(
	    TLS_ALERT_USER_CANCELLED,
	    false,
	    TLS_ALERT_HANDSHAKE_FAILURE,
	    "User cancelled"),
       new SSLAlertX(
	    TLS_ALERT_NO_RENEGOTIATION,
	    false,
	    TLS_ALERT_HANDSHAKE_FAILURE,
	    "No reenegotiation")
  };

  private int value;
  private int ssl_value;  // A pointer to the ssl exception if TLS only
  private boolean always_fatal;
  private boolean fatal;
  private String explanation;
  
  // This constructor is used to build the exception list
  public SSLAlertX(int value,boolean always_fatal,int ssl_value,
    String explanation){
    this.value=value;
    this.always_fatal=always_fatal;
    this.ssl_value=ssl_value;
    this.explanation=explanation;
  }

  // Incoming
  public SSLAlertX(int thrown_value,int level){
    int found;

    found=findAlert(thrown_value);

    if(found<0)
      found=findAlert(TLS_ALERT_INTERNAL_ERROR);

    value=master_list[found].value;
    always_fatal=master_list[found].always_fatal;
    
    fatal=false;
    if(always_fatal || (level!=TLS_ALERT_WARNING))
      fatal=true;
    explanation=master_list[found].explanation;
  }

  // Outgoing
  public SSLAlertX(int version,int thrown_value,boolean thrown_fatal){
    int found,v3_found;

    found=findAlert(thrown_value);

    if(found<0)
      found=findAlert(TLS_ALERT_INTERNAL_ERROR);

    if((version<SSLHandshake.TLS_V1_VERSION) &&
      (master_list[found].ssl_value!=0)){
      v3_found=findAlert(master_list[found].ssl_value);
      value=master_list[v3_found].value;
      always_fatal=master_list[v3_found].always_fatal;
    }
    else{
      value=master_list[found].value;
      always_fatal=master_list[found].always_fatal;
    }
    fatal=false;
    if(always_fatal || thrown_fatal)
      fatal=true;
    explanation=master_list[found].explanation;
    SSLDebug.debug(SSLDebug.DEBUG_MSG,"Throwing alert "+explanation);
  }

  public int getValue(){
    return value;
  }

  public int getLevel(){
    return fatal?TLS_ALERT_FATAL:TLS_ALERT_WARNING;
  }
       
  public boolean fatalP(){
    return fatal;
  }

  public String getExplanation(){
    return explanation;
  }

  private int findAlert(int num){
    int i;
    
    for(i=0;i<master_list.length;i++){
      if(master_list[i].value==num)
	return(i);
    }

    return(-1);
  }
}
  
