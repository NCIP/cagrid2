/**
 X509Cert.java

 Copyright (C) 1999, Claymore Systems, Inc.
 All Rights Reserved.

 ekr@rtfm.com  Thu May 13 20:52:29 1999

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

 $Id: X509Cert.java,v 1.3 2004/10/20 15:55:50 gawor Exp $

 */

package COM.claymoresystems.cert;

import COM.claymoresystems.util.Util;
import COM.claymoresystems.sslg.Certificate;
import COM.claymoresystems.sslg.CertVerifyPolicyInt;
import COM.claymoresystems.sslg.DistinguishedName;
import COM.claymoresystems.ptls.SSLDebug;

import cryptix.asn1.lang.ASNObject;
import cryptix.asn1.lang.ASNSpecification;
import cryptix.asn1.lang.Parser;
import cryptix.asn1.lang.ParseException;
import cryptix.asn1.lang.ASNBitString;
import cryptix.asn1.encoding.BaseCoder;
import cryptix.asn1.encoding.CoderOperations;
import cryptix.asn1.encoding.DER;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.Date;
import java.util.Hashtable;
import java.io.*;

/**
 * A single X509 Certificate.
 * <P>
 * The interface here does not match Sun's certificate interface, and even
 * though that interface is pretty useless, we should still probably implement
 * it.
 */
public class X509Cert implements Certificate {
	ASNObject signedCert;
	ASNObject unsignedCert;
	ASNObject issuer;
	ASNObject subject;
	ASNObject sigAlg;
	ASNObject sig;

	byte[] DER;
	byte[] unsignedCertDER;
	byte[] subjectDER;
	byte[] issuerDER;
	byte[] signature;
	String signatureAlgorithm;

	PublicKey pubKey;
	X509Name subjectName;
	X509Name issuerName;
	BigInteger serialNumber;
	Date notBefore;
	Date notAfter;

	Vector extensions = null;

	private static Hashtable oid2NameMap;
	private final static Set<String> jcaHashes = new HashSet<String>();

	static {
		oid2NameMap = new Hashtable();

		oid2NameMap.put("1.2.840.10040.4.3", "DSA");
		oid2NameMap.put("1.2.840.113549.1.1.2", "MD2/RSA");
		oid2NameMap.put("1.2.840.113549.1.1.3", "MD4/RSA");
		oid2NameMap.put("1.2.840.113549.1.1.4", "MD5/RSA");
		oid2NameMap.put("1.2.840.113549.1.1.5", "SHA-1/RSA/PKCS#1");
		oid2NameMap.put("1.2.840.113549.1.1.11", "SHA256withRSA");

		jcaHashes.add("SHA256withRSA");
	}

	/**
	 * Create an X509Cert from an encoded value
	 * 
	 * @param ber_
	 *            the encoded certificate as a byte[]
	 *            <P>
	 *            Currently this chokes if the cert is poorly encoded. We have
	 *            to fix that to throw an IOException. TODO
	 */
	public X509Cert(byte[] ber_) throws CertificateException {
		ASNObject x509;
		InputStream is;
		DER = ber_;

		synchronized (CertContext.getSpec()) {
			x509 = CertContext.getSpec().getComponent("UsefulCertificate");

			// TODO: See if we can make this read BER.
			CoderOperations der_coder = BaseCoder.getInstance("DER");
			is = new ByteArrayInputStream(ber_);

			der_coder.init(is);

			try {
				// Synchronize to give us exclusive access to the ASN definition
				// tree, which is also used to temporarily store the results of
				// the decoding.
				ASNObject tmp;
				x509.accept(der_coder, null);

				// Parse the outer cert wrapper
				signedCert = x509;
				tmp = x509.getComponent("UsefulCertificate.tbsCertificate");
				unsignedCertDER = (byte[]) tmp.getValue();

				sigAlg = x509
						.getComponent("UsefulCertificate.signatureAlgorithm");
				Vector v = (Vector) sigAlg.getValue();
				Vector v2 = (Vector) v.elementAt(0);
				signatureAlgorithm = (String) v2.elementAt(0);
				SSLDebug.debug(SSLDebug.DEBUG_CERT, "Signed by "
						+ signatureAlgorithm);

				sig = x509.getComponent("UsefulCertificate.signature");
				byte[] sig_bs = (byte[]) sig.getValue();
				if (sig_bs[0] != 0)
					throw new IOException();
				signature = new byte[sig_bs.length - 1];
				System.arraycopy(sig_bs, 1, signature, 0, signature.length);
				SSLDebug.debug(SSLDebug.DEBUG_CERT, "Signature ", signature);

				// Now parse the inner certificate
				unsignedCert = CertContext.getSpec().getComponent(
						"UsefulTBSCertificate");
				// unsignedCert.dump("UsefulTBSCertificate");

				// COM.claymoresystems.Util.xdump("UnsignedCertDER",unsignedCertDER);
				SSLDebug.debug(SSLDebug.DEBUG_CERT, "Unsigned cert DER",
						unsignedCertDER);
				is = new ByteArrayInputStream(unsignedCertDER);
				der_coder.init(is);
				unsignedCert.accept(der_coder, null);

				issuer = unsignedCert
						.getComponent("UsefulTBSCertificate.issuer");
				issuerDER = (byte[]) issuer.getValue();
				issuerName = new X509Name(issuerDER);
				SSLDebug.debug(SSLDebug.DEBUG_CERT, "Issuer DER", issuerDER);

				subject = unsignedCert
						.getComponent("UsefulTBSCertificate.subject");
				subjectDER = (byte[]) subject.getValue();
				subjectName = new X509Name(subjectDER);
				SSLDebug.debug(SSLDebug.DEBUG_CERT, "Subject DER", subjectDER);

				ASNObject spki = unsignedCert
						.getComponent("UsefulTBSCertificate.subjectPublicKeyInfo");
				byte[] spkiDER = (byte[]) spki.getValue();
				pubKey = X509SubjectPublicKeyInfo.createPublicKey(spkiDER);

				tmp = unsignedCert
						.getComponent("UsefulTBSCertificate.serialNumber");
				serialNumber = (BigInteger) tmp.getValue();

				ASNObject validity = unsignedCert
						.getComponent("UsefulTBSCertificate.validity");
				tmp = validity.getComponent("Validity.notBefore");
				notBefore = (Date) tmp.getValue();
				tmp = validity.getComponent("Validity.notAfter");
				notAfter = (Date) tmp.getValue();

				ASNObject ext = unsignedCert
						.getComponent("UsefulTBSCertificate.extensions");

				v = (Vector) ext.getValue();

				if (v != null) {
					for (int i = 0; i < v.size(); i++) {
						if (i == 0) {
							extensions = new Vector();
						}
						v2 = (Vector) v.elementAt(i);
						byte[] extnval = (byte[]) v2.elementAt(0);
						extensions.addElement(new X509Ext(extnval));
					}
				}
			} catch (java.io.IOException e) {
				throw new CertificateDecodeException(e.toString());
			}
		}
	}

	/**
	 * Get the publicKey associated with this certificate
	 * 
	 * @return the public key
	 */
	public PublicKey getPublicKey() {
		return pubKey;
	}

	/**
	 * Get the encoded form of this certificate
	 * 
	 * @return the encoded form
	 */
	public byte[] getDER() {
		return DER;
	}

	/**
	 * Get the encoded form of the issuerName
	 * 
	 * @return the DER encoded issuerName
	 */
	public byte[] getIssuerDER() {
		return issuerDER;
	}

	/**
	 * Get the encoded form of the subjectName
	 * 
	 * @return the DER encoded subjectName
	 */
	public byte[] getSubjectDER() {
		return subjectDER;
	}

	/**
	 * Get the subject name as an X509Name
	 * 
	 * @return an object representing the subjectName
	 */
	public DistinguishedName getSubjectName() {
		return subjectName;
	}

	/**
	 * Get the issuer name as an X509Name
	 * 
	 * @return an object representing the issuerName
	 */
	public DistinguishedName getIssuerName() {
		return issuerName;
	}

	/**
	 * Get the beginning of the validity window
	 * 
	 * @return the beginning of the certificate validity period
	 */
	public Date getValidityNotBefore() {
		return notBefore;
	}

	/**
	 * Get the end of the validity window
	 * 
	 * @return the end of the certificate validity period
	 */
	public Date getValidityNotAfter() {
		return notAfter;
	}

	public Vector getExtensions() {
		return extensions;
	}

	/**
	 * Get the serial number
	 * 
	 * @return the serial number
	 */
	public BigInteger getSerial() {
		return serialNumber;
	}

	void checkSignatureKey(PublicKey key, String alg)
			throws CertificateVerifyException {
		if (alg.equals("MD2/RSA") || alg.equals("MD4/RSA")
				|| alg.equals("MD5/RSA") || alg.equals("SHA-1/RSA/PKCS#1")
				|| jcaHashes.contains(alg)) {
			if (!(key instanceof xjava.security.interfaces.CryptixRSAPublicKey)) {
				throw new CertificateVerifyException(
						"Public key doesn't match algorithm " + alg);
			}
		} else if (alg.equals("DSA")) {
			if (!(key instanceof java.security.interfaces.DSAPublicKey)) {
				throw new CertificateVerifyException(
						"Public key doesn't match algorithm " + alg);
			}
		} else {
			throw new CertificateVerifyException("Unknown algorithm " + alg);
		}
	}

	/**
	 * Check a certificate signature using the specified public key
	 * 
	 * @return true if the signature checks, otherwise false
	 */
	public boolean verify(PublicKey key) throws CertificateException {
		try {
			// Lookup the algoritm
			String alg = (String) oid2NameMap.get(signatureAlgorithm);
			if (alg != null)
				SSLDebug.debug(SSLDebug.DEBUG_CERT, "OID " + signatureAlgorithm
						+ "mapped to " + alg);
			checkSignatureKey(key, alg);

			Signature sig = null;
			if (jcaHashes.contains(alg)) {
				sig = Signature.getInstance(alg);
				key = convertToJCARSAPublicKey(key);
			} else {
				sig = Signature.getInstance(alg != null ? alg
						: signatureAlgorithm, "Cryptix");
			}

			sig.initVerify(key);
			sig.update(unsignedCertDER);
			return sig.verify(signature);
		} catch (java.security.NoSuchAlgorithmException e) {
			if (SSLDebug.getDebug(SSLDebug.DEBUG_CERT))
				e.printStackTrace();
			throw new CertificateVerifyException(e.toString());
		} catch (java.security.SignatureException e) {
			if (SSLDebug.getDebug(SSLDebug.DEBUG_CERT))
				e.printStackTrace();
			throw new CertificateVerifyException(e.toString());
		} catch (java.security.InvalidKeyException e) {
			if (SSLDebug.getDebug(SSLDebug.DEBUG_CERT))
				e.printStackTrace();
			return false;
		} catch (java.security.NoSuchProviderException e) {
			if (SSLDebug.getDebug(SSLDebug.DEBUG_CERT))
				e.printStackTrace();
			return false;
		} catch (InvalidKeySpecException e) {
			if (SSLDebug.getDebug(SSLDebug.DEBUG_CERT))
				e.printStackTrace();
			return false;
		}
	}

	/**
	 * Verify a certificate chain.
	 * 
	 * @param ctx
	 *            the cert context
	 * @param certs
	 *            the certs to start with, ordered root first
	 * @return the canonicalized chain, with spurious certificates trimmed from
	 *         the front and the root (if necessary) prepended
	 */
	/*
	 * Our algorithm is to proceed through the chain till we find a root or run
	 * out of certs.
	 * 
	 * Once we find a root, we chain from there
	 */
	public static Vector verifyCertChain(CertContext ctx, Vector certs,
			CertVerifyPolicyInt policy) throws CertificateException {
		int i;
		int num_certs = certs.size();
		Vector chain = new Vector();
		X509Cert cert, last = null;
		boolean found_root = false;
		int pathLen = 255;

		for (i = 0; i < num_certs; i++) {
			cert = (X509Cert) certs.elementAt(i);

			SSLDebug.debug(SSLDebug.DEBUG_CERT, "Trying to verify",
					cert.getDER());

			if (!found_root) {
				if (ctx.isRoot(cert.getDER())) {
					SSLDebug.debug(SSLDebug.DEBUG_CERT, "Is root");
					last = cert;
					chain.addElement((Object) last);
					found_root = true;
					continue;
				} else {
					SSLDebug.debug(SSLDebug.DEBUG_CERT,
							"Trying to find root with DN", cert.getIssuerDER());
					last = ctx.signedByRoot(cert.getIssuerDER());
					if (last == null) {
						SSLDebug.debug(SSLDebug.DEBUG_CERT, "Nope");
						continue;
					}
					SSLDebug.debug(SSLDebug.DEBUG_CERT, "Found one");
				}
				chain.addElement((Object) last);
				found_root = true;
			}

			// Now check that the signer's subjectName is the same
			// as the issuerName
			if (!cryptix.util.core.ArrayUtil.areEqual(last.getSubjectDER(),
					cert.getIssuerDER())) {
				String subject = cert.getSubjectName().getNameString();
				throw new CertificateVerifyException(
						"Invalid certificate chain at '"
								+ subject
								+ "' certificate. Subject and issuer names do not"
								+ " match");
			}

			// Ok, now we have to verify this certificate
			if (!cert.verify(last.getPublicKey())) {
				String subject = cert.getSubjectName().getNameString();
				throw new CertificateVerifyException("The signature of '"
						+ subject + "' certificate does not match its issuer");
			}

			if (policy.checkDatesP()) {
				checkExpiry(cert, new Date());
			}

			/*
			 * // Now check if it can be a CA if(chain.size()==1){ // Trust
			 * anchors don't need these extensions but they // better be right
			 * if they're there int
			 * tmpPathLen=last.checkBasicConstraintExtension(false,
			 * policy.requireBasicConstraintsCriticalP()); if(tmpPathLen!=-1)
			 * pathLen=tmpPathLen;
			 * 
			 * last.checkKeyUsage(false); } else { // There must be an extension
			 * int tmpPathLen=last.checkBasicConstraintExtension
			 * (policy.requireBasicConstraintsP(),
			 * policy.requireBasicConstraintsCriticalP());
			 * 
			 * if(tmpPathLen<pathLen) pathLen=tmpPathLen;
			 * last.checkKeyUsage(policy.requireKeyUsageP()); }
			 */

			// Finally, check the pathLen, which is the number of
			// certificates allowed after "last"
			if (pathLen < 1)
				throw new CertificateVerifyException(
						"No more certificates allowed. Ran out of pathLen");
			pathLen--;

			last = cert;

			chain.addElement((Object) cert);
		}

		if (last != null)
			return chain;

		return null;
	}

	static void checkExpiry(Certificate cert, Date d)
			throws CertificateVerifyException {
		Date nb = cert.getValidityNotBefore();
		if (d.before(nb)) {
			String subject = cert.getSubjectName().getNameString();
			throw new CertificateVerifyException("Certificate '" + subject
					+ "' not yet valid. Not before date " + nb);
		}

		Date na = cert.getValidityNotAfter();
		if (d.after(na)) {
			String subject = cert.getSubjectName().getNameString();
			throw new CertificateVerifyException("Certificate '" + subject
					+ "' expired. Not after date " + na);
		}
	}

	// Returns the BC depth if it is set
	private int checkBasicConstraintExtension(boolean require,
			boolean checkCritical) throws CertificateVerifyException {
		X509BasicConstraints bcExt = null;

		try {
			X509Ext ext = X509Ext.getExtensionFromCert(this,
					X509BasicConstraints.oid);

			if (ext != null) {
				bcExt = new X509BasicConstraints(ext);
			}
		} catch (IOException e) {
			throw new CertificateVerifyException(
					"Problem parsing Basic Constraints" + e.toString());
		}

		if (bcExt != null) {
			if (bcExt.isCA()) {
				if (checkCritical && !bcExt.isCritical()) {
					throw new CertificateVerifyException(
							"Basic constraints for a CA must be critical");
				}

				return bcExt.getPathLen();
			} else {
				throw new CertificateVerifyException(
						"Basic Constraints present in signing cert but not a CA");
			}
		} else {
			if (require)
				throw new CertificateVerifyException(
						"Basic Constraints not present");

			return 255;
		}
	}

	private void checkKeyUsage(boolean require)
			throws CertificateVerifyException {
		X509KeyUsage kuExt = null;

		try {
			X509Ext ext = X509Ext.getExtensionFromCert(this, X509KeyUsage.oid);

			if (ext != null) {
				kuExt = new X509KeyUsage(ext);
			}
		} catch (IOException e) {
			throw new CertificateVerifyException("Problem parsing Key Usage"
					+ e.toString());
		}

		if (kuExt == null) {
			if (require)
				throw new CertificateVerifyException(
						"Key Usage required for CAs");
		} else {
			if (!kuExt.isAsserted(X509KeyUsage.BIT_keyCertSign))
				throw new CertificateVerifyException(
						"Key Usage present but keyCertSign not asserted");
		}
	}

	private PublicKey convertToJCARSAPublicKey(PublicKey publicKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		if (publicKey instanceof X509RSAPublicKey) {
			X509RSAPublicKey x509RSAPublicKey = (X509RSAPublicKey) publicKey;
			RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(
					x509RSAPublicKey.getModulus(),
					x509RSAPublicKey.getExponent());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(rsaPublicKeySpec);
		}
		return publicKey;
	}
}
