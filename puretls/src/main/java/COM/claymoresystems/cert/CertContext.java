/**
   CertContext.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Fri May 14 08:27:53 1999

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

   $Id: CertContext.java,v 1.2 2004/01/09 22:34:05 gawor Exp $

 */

package COM.claymoresystems.cert;

import COM.claymoresystems.ptls.SSLDebug;

import cryptix.asn1.lang.ASNSpecification;
import cryptix.asn1.lang.Parser;
import java.util.Vector;
import java.io.*;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.security.auth.x500.X500Principal;

/**
 * Provide a generic context for ASN.1 computations and cert verification
 * 
 * This class is used internally by PureTLS. It should not be called by
 * programmers. We'll document it eventually, though.
 */
public class CertContext {
	Vector root_list = new Vector();

	static Parser parser;
	static ASNSpecification spec;

	final static LdapNameComparator ldapComparator = new LdapNameComparator();

	// TODO: Make this a loaded byte string
	// static String defs="/users/ekr/java/COM/claymoresystems/cert/x509.asn";

	static {

		parser = new Parser(new Pickledx509());

		parser.disable_tracing();
		try {
			spec = parser.Specification(false);
		} catch (cryptix.asn1.lang.ParseException e) {
			throw new InternalError(e.toString());
		}
	}

	public CertContext() {
		;
	}

	public CertContext(Vector roots) {
		if (roots != null) {
			for (int i = 0; i < roots.size(); i++) {
				Object obj = roots.elementAt(i);
				if (obj instanceof X509Cert) {
					root_list.addElement(obj);
				} else {
					addRoot((byte[]) roots.elementAt(i));
				}
			}
		}
	}

	public static ASNSpecification getSpec() {
		return spec;
	}

	public void addRoot(byte[] root_ber) {
		X509Cert cert;
		try {
			cert = new X509Cert(root_ber);
		} catch (CertificateException e) {
			SSLDebug.debug(SSLDebug.DEBUG_CERT,
					"Couldn't parse. Skipping cert", root_ber);
			return;
		}
		root_list.addElement(cert);
		SSLDebug.debug(SSLDebug.DEBUG_CERT, "Adding root with DN",
				cert.getSubjectDER());
	}

	// Return the root list as a Vector of X509Certs
	public Vector getRootList() {
		return root_list;
	}

	public boolean isRoot(byte[] cert) {
		for (int i = 0; i < root_list.size(); i++) {
			byte[] root = ((X509Cert) root_list.elementAt(i)).getDER();
			if (cryptix.util.core.ArrayUtil.areEqual(cert, root))
				return true;
		}

		return false;
	}

	public X509Cert signedByRoot(byte[] issuer) throws InvalidNameException {
		X500Principal issuerPrincipal = new X500Principal(issuer);
		LdapName issuerName = new LdapName(issuerPrincipal.getName());

		for (int i = 0; i < root_list.size(); i++) {
			X509Cert root;

			root = (X509Cert) root_list.elementAt(i);
			byte[] subject = root.getSubjectDER();
			X500Principal subjectPrincipal = new X500Principal(subject);
			LdapName subjectName = new LdapName(subjectPrincipal.getName());
			if (ldapComparator.compare(subjectName, issuerName) == 0) return root;
//			if (cryptix.util.core.ArrayUtil.areEqual(issuer, subject))
//				return root;
		}

		return null;
	}

};
