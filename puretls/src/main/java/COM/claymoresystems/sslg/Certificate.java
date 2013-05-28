/**
   Certificate.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Wed May  5 10:24:01 1999

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

   $Id: Certificate.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.sslg;

import java.math.BigInteger;
import java.util.Date;
import java.util.Vector;

/** An interface specifying minimal certificate access functions*/

public interface Certificate {
     /** get the DER of the cert itself

	 @return the encoding as a bytestring
     */
     public byte[] getDER();

     /**get the DER encoded issuer name

	@return the encoding as a bytestring
     */
     public byte[] getIssuerDER();

     /** get the serialNumber

	 @return the serial as a BigInteger
     */
     public BigInteger getSerial();

     /** get the DER encoded subject name

	 @return the encoding as a bytestring
     */
     public byte[] getSubjectDER();

     /** Get the Subject name as a DistinguishedName

	 @return the subject name as a DistinguishedName*/
     public DistinguishedName getSubjectName();

     /** Get the Isuser name as a DistinguishedName

	 @return the issuer name as a DistinguishedName */
     public DistinguishedName getIssuerName();
     
     /** get the not-valid-before date of the certificate
	 (the beginning of the validity period)

	 @return the notBefore Date
     */
     public Date getValidityNotBefore();

     /** get the not-valid-after date of the certificate
	 (the end of the validity period)

	 @return the notAfter Date
     */
     public Date getValidityNotAfter();

     /** Get the vector of extensions (if any)

	 @return the extensions
     */
     public Vector getExtensions();
};



