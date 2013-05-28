/**
   SSLPolicyInt.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Wed May  5 08:47:37 1999

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

   $Id: SSLPolicyInt.java,v 1.2 2003/12/18 06:17:57 gawor Exp $

*/

package COM.claymoresystems.sslg;
import java.util.Vector;

public class SSLPolicyInt extends Object {
     private static Vector _name_table;
     
     public static final short TLS_RSA_WITH_NULL_MD5                  =  0x0001 ;
     public static final short TLS_RSA_WITH_NULL_SHA                  =  0x0002 ;
     public static final short TLS_RSA_EXPORT_WITH_RC4_40_MD5         =  0x0003 ;
//strip EXPORT
     public static final short TLS_RSA_WITH_RC4_128_MD5               =  0x0004 ;
     public static final short TLS_RSA_WITH_RC4_128_SHA               =  0x0005 ;
//estrip     
     public static final short TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5     =  0x0006 ;
//strip EXPORT     
     public static final short TLS_RSA_WITH_IDEA_CBC_SHA              =  0x0007 ;
//estrip     
     public static final short TLS_RSA_EXPORT_WITH_DES40_CBC_SHA      =  0x0008 ;
     public static final short TLS_RSA_WITH_DES_CBC_SHA               =  0x0009 ;
//strip EXPORT     
     public static final short TLS_RSA_WITH_3DES_EDE_CBC_SHA          =  0x000A ;
//estrip     
     public static final short TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA   =  0x000B ;
     public static final short TLS_DH_DSS_WITH_DES_CBC_SHA            =  0x000C ;
//strip EXPORT     
     public static final short TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA       =  0x000D ;
//estrip
     public static final short TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA   =  0x000E ;
     public static final short TLS_DH_RSA_WITH_DES_CBC_SHA            =  0x000F ;
//strip EXPORT     
     public static final short TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA       =  0x0010 ;
//estrip     
     public static final short TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA  =  0x0011 ;
     public static final short TLS_DHE_DSS_WITH_DES_CBC_SHA           =  0x0012 ;
//strip EXPORT     
     public static final short TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA      =  0x0013 ;
//estrip     
     public static final short TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA  =  0x0014 ;
     public static final short TLS_DHE_RSA_WITH_DES_CBC_SHA           =  0x0015 ;
//strip EXPORT     
     public static final short TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA      =  0x0016 ;
//estrip     
     public static final short TLS_DH_anon_EXPORT_WITH_RC4_40_MD5     =  0x0017 ;
     public static final short TLS_DH_anon_WITH_RC4_128_MD5           =  0x0018 ;
     public static final short TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA  =  0x0019 ;
     public static final short TLS_DH_anon_WITH_DES_CBC_SHA           =  0x001A ;
//strip EXPORT
     public static final short TLS_DH_anon_WITH_3DES_EDE_CBC_SHA      =  0x001B ;
     public static final short TLS_DHE_DSS_WITH_RC4_128_SHA	    =  0x0066 ;
//estrip
     public static final short TLS_DHE_DSS_WITH_NULL_SHA		    =  0x0067 ;     

     static {
        _name_table=new Vector(0x100);
	_name_table.setSize(100);
	
	_name_table.insertElementAt("TLS_RSA_WITH_NULL_MD5",
	  TLS_RSA_WITH_NULL_MD5);
	_name_table.insertElementAt("TLS_RSA_WITH_NULL_SHA",
	  TLS_RSA_WITH_NULL_SHA);
	_name_table.insertElementAt("TLS_RSA_EXPORT_WITH_RC4_40_MD5",
	  TLS_RSA_EXPORT_WITH_RC4_40_MD5);
//strip EXPORT	
	_name_table.insertElementAt("TLS_RSA_WITH_RC4_128_MD5",
	  TLS_RSA_WITH_RC4_128_MD5);
	_name_table.insertElementAt("TLS_RSA_WITH_RC4_128_SHA",
	  TLS_RSA_WITH_RC4_128_SHA);
//estrip	
	_name_table.insertElementAt("TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5",
	  TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5);
//strip EXPORT	
	_name_table.insertElementAt("TLS_RSA_WITH_IDEA_CBC_SHA",
	  TLS_RSA_WITH_IDEA_CBC_SHA);
//estrip	
	_name_table.insertElementAt("TLS_RSA_EXPORT_WITH_DES40_CBC_SHA",
	  TLS_RSA_EXPORT_WITH_DES40_CBC_SHA);
	_name_table.insertElementAt("TLS_RSA_WITH_DES_CBC_SHA",
	  TLS_RSA_WITH_DES_CBC_SHA);
//strip EXPORT	
	_name_table.insertElementAt("TLS_RSA_WITH_3DES_EDE_CBC_SHA",
	  TLS_RSA_WITH_3DES_EDE_CBC_SHA);
//estrip	
	_name_table.insertElementAt("TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA",
	  TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA);
	_name_table.insertElementAt("TLS_DH_DSS_WITH_DES_CBC_SHA",
	  TLS_DH_DSS_WITH_DES_CBC_SHA);
//strip EXPORT	
	_name_table.insertElementAt("TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA",
	  TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA);
//estrip	
	_name_table.insertElementAt("TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA",
	  TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA);
	_name_table.insertElementAt("TLS_DH_RSA_WITH_DES_CBC_SHA",
	  TLS_DH_RSA_WITH_DES_CBC_SHA);
//strip EXPORT	
	_name_table.insertElementAt("TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA",
	  TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA);
//estrip	
	_name_table.insertElementAt("TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA",
	  TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA);
	_name_table.insertElementAt("TLS_DHE_DSS_WITH_DES_CBC_SHA",
	  TLS_DHE_DSS_WITH_DES_CBC_SHA);
//strip EXPORT	
	_name_table.insertElementAt("TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA",
	  TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA);
//estrip	
	_name_table.insertElementAt("TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA",
	  TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA);
	_name_table.insertElementAt("TLS_DHE_RSA_WITH_DES_CBC_SHA",
	  TLS_DHE_RSA_WITH_DES_CBC_SHA);
//strip EXPORT	
	_name_table.insertElementAt("TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA",
	  TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA);
//estrip	
	_name_table.insertElementAt("TLS_DH_anon_EXPORT_WITH_RC4_40_MD5",
	  TLS_DH_anon_EXPORT_WITH_RC4_40_MD5);
	_name_table.insertElementAt("TLS_DH_anon_WITH_RC4_128_MD5",
	  TLS_DH_anon_WITH_RC4_128_MD5);
	_name_table.insertElementAt("TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA",
	  TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA);
	_name_table.insertElementAt("TLS_DH_anon_WITH_DES_CBC_SHA",
	  TLS_DH_anon_WITH_DES_CBC_SHA);
//strip EXPORT	
	_name_table.insertElementAt("TLS_DH_anon_WITH_3DES_EDE_CBC_SHA",
	  TLS_DH_anon_WITH_3DES_EDE_CBC_SHA);
	_name_table.insertElementAt("TLS_DHE_DSS_WITH_RC4_128_SHA",
	  TLS_DHE_DSS_WITH_RC4_128_SHA);
//estrip	
	_name_table.insertElementAt("TLS_DHE_DSS_WITH_NULL_SHA",
	  TLS_DHE_DSS_WITH_NULL_SHA);
	  }
     
    private boolean requireClientAuth = false;
    private boolean acceptNoClientCert = false;
    
     private short[] cipherSuites={
	  TLS_RSA_WITH_3DES_EDE_CBC_SHA,
	  TLS_RSA_WITH_RC4_128_SHA,
	  TLS_RSA_WITH_RC4_128_MD5,
	  TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA,
	  TLS_DHE_DSS_WITH_RC4_128_SHA,
	  TLS_RSA_WITH_DES_CBC_SHA,
	  TLS_DHE_DSS_WITH_DES_CBC_SHA
     };
     
     private boolean negotiateTLS=true;
     private byte[][] rootCertificates=null;
     private int sessionLife=86400;
     private boolean acceptUnverifiable=false;
     private boolean dhAlwaysEphemeral=true;
     private boolean handshakeOnConnect=true;
     private boolean waitOnClose=true;
     private CertVerifyPolicyInt certVerifyPolicy=new CertVerifyPolicyInt();
     
     /** Set whether or not to require client authentication when
	 negotiating (this is relevant only for servers)

	 @param val a boolean specifying whether client auth is required. The default is no
     */
     public void requireClientAuth(boolean val) {
       requireClientAuth=val;
     }

     /** Get whether client auth is required. This is relevant only
	 for servers

	 @return val a boolean indicating whether client auth is required
     */
     public boolean requireClientAuthP(){
       return requireClientAuth;
     }

    /**
     * Only used with client authentication. If true,
     * the handshake will be established properly even
     * though the client did not send any certificates
     * and client authentication was requested. If false,
     * the handshake will fail.
     */
    public void setAcceptNoClientCert(boolean val) {
	this.acceptNoClientCert = val;
    }
    
    /**
     * Only used with client authentication.
     * If returns true, the handshake will be established property
     * even though the client did not send any certificates
     * and client authentication was requested. Returns false,
     * if handshake should fail.
     */
    public boolean getAcceptNoClientCert() {
	return this.acceptNoClientCert;
    }

     /** Specify which cipherSuites may be negotiated.
	 <P>
	 Currently there is no check made as to whether these cipherSuites
	 are in fact negotiatable given the current keying material. This
	 is a bug.
	 <P>
	 Currently supported cipher suites:
	 <PRE>
	 TLS_DHE_DSS_EXPORT_WITH_DES40_RSA
	 TLS_DHE_DSS_WITH_DES_CBC_SHA
	 TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA
	 TLS_RSA_WITH_3DES_EDE_CBC_SHA,
	 TLS_RSA_WITH_DES_CBC_SHA,
	 TLS_RSA_WITH_RC4_128_MD5,
	 TLS_RSA_WITH_RC4_128_SHA,
	 TLS_RSA_EXPORT_WITH_RC4_40_MD5,
	 TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5,
	 TLS_RSA_EXPORT_WITH_DES40_CBC_SHA
	 </PRE>
	 @param cS the list of allowed cipherSuites as an array of shorts. The values are specified as constants in this class
     */
     public void setCipherSuites(short[] cS){
       cipherSuites=cS;
     }

     /** Return the allowed cipherSuites

	 @return a short[] containing all the cipherSuites currently allowed
     */
     public short[] getCipherSuites(){
       return(cipherSuites);
     }

     /** Set whether to try to negotiate TLS. IF this value is false,
	 SSLv3 will be negotiated. The default is true/yes.

	 @param tls a boolean indicating whether to try to negotiate TLS
     */
     public void negotiateTLS(boolean tls) {
       negotiateTLS=tls;
     }

     /** return whether TLS is to be negotiated

	 @return a boolean indicating whether TLS negotiation is attempted.
     */
     public boolean negotiateTLSP(){
       return negotiateTLS;
     }

     /** Set the lifetime of a cached session. Any attempt to resume a
	 session after it has expired will fail. This has no effect on
	 sessions that are currently active, however.

	 @param lifetime lifetime of a cached session in seconds. Default is 86400 (1 day)
     */
     public void setSessonLifetime(int lifetime){
       sessionLife=lifetime;
     }

     /** return the lifetime of a session

	 @return the lifetime setting, in seconds
     */
     public int getSessionLifetime(){
       return sessionLife;
     }

     /** get the name of a cipher from the number

	 @return the name
     */
     public static String getCipherSuiteName(int num){
       return (String)_name_table.elementAt(num);
     }

     /** get the number of a cipher from the name

	 @return the number (or -1)
     */
     public static int getCipherSuiteNumber(String name){
       return _name_table.indexOf(name);
     }
	
	
     /** allow unverifiable certificates. If we encounter a certificate
	 which cannot be verified by a known root, we ordinarily reject,
	 but this accepts that behavior to be overridden. The value of
	 getCertificateChain() will be null.
	 <P>
	 Setting this value to true completely compromises security against
	 active attack. This should only be used for testing purposes.

	 @param accept a boolean indicating whether unverifiable certificates should be accepted
     */

     public void acceptUnverifiableCertificates(boolean accept){
       acceptUnverifiable=accept;
     }


     /** return whether unverifiable certificates are accepted

	 @return a boolean indicating whether unverifiable certs will be accepted
     */
     public boolean acceptUnverifiableCertificatesP(){
       return acceptUnverifiable;
     }

     /** Force the creation of a new ephemeral DH key for each connection

         Only  set this to false if you are using a Sophie-Germain or other prime designed
         to resist small subgroup attacks.
         
         @param dhephemeral a boolean indicating whether to force a new DH key for each connection--default to true */
     public void setDHAlwaysEphemeral(boolean dhephemeral){
       dhAlwaysEphemeral=dhephemeral;
     }

     /* return the dhAlwaysEphemeral value */
     public boolean dhAlwaysEphemeralP(){
       return dhAlwaysEphemeral;
     }

     /* Whether to do an immediate handshake on establishing
        the connection
        */
     public void handshakeOnConnect(boolean value) {
       handshakeOnConnect=value;
     }

     /* Whether to do an immediate handshake on establishing
        the connection
        */
     public boolean handshakeOnConnectP() {
       return handshakeOnConnect;
     }

     /* Whether to wait for the peer's close_notify or not
        when close() is called

        @param wait wait for close_notify or not
     */
     public void waitOnClose(boolean v) {
       waitOnClose=v;
     }

     /* Whether to wait for the peer's close_notify or not
        when close() is called
     */
     public boolean waitOnCloseP() {
       return waitOnClose;
     }

     /*
       Certificate checking policy
     */
     public void setCertVerifyPolicy(CertVerifyPolicyInt p){
       certVerifyPolicy=p;
     }

     /*
       Return our certificate verification policy
     */
     public CertVerifyPolicyInt getCertVerifyPolicy() {
       return certVerifyPolicy;
     }
}

