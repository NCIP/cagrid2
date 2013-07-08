package org.cagrid.dorian.service.ca;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

import org.bouncycastle.asn1.x509.X509Name;
import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.common.Lifetime;
import org.cagrid.dorian.service.CertificateSignatureAlgorithm;
import org.cagrid.dorian.service.util.Utils;
import org.cagrid.gaards.pki.CRLEntry;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public abstract class CertificateAuthority {

	private final static Logger logger = LoggerFactory.getLogger(CertificateAuthority.class);

	private boolean initialized = false;

	private CertificateAuthorityProperties properties;

	public CertificateAuthority(CertificateAuthorityProperties properties) {
		this.properties = properties;
	}

	public abstract String getUserCredentialsProvider();

	public abstract String getCACredentialsProvider();

	public abstract String getSignatureAlgorithm(CertificateSignatureAlgorithm alg) throws CertificateAuthorityException;

	public abstract boolean hasCACredentials() throws CertificateAuthorityException;

	public abstract void setCACredentials(X509Certificate cert, PrivateKey key, String password) throws CertificateAuthorityException;

	public abstract void deleteCACredentials() throws CertificateAuthorityException;

	public abstract PrivateKey getPrivateKey(String password) throws CertificateAuthorityException, NoCACredentialsException;

	protected abstract java.security.cert.X509Certificate getCertificate() throws CertificateAuthorityException;

	public void clearCertificateAuthority() throws CertificateAuthorityException {
		deleteCACredentials();
		this.initialized = false;
	}

	private synchronized void init() throws CertificateAuthorityException {
		try {
			if (!initialized) {
				if (!hasCACredentials()) {
					if (properties.isAutoCreateCAEnabled()) {
						Lifetime lifetime = properties.getCreationPolicy().getLifetime();
						this.createCertifcateAuthorityCredentials(properties.getCreationPolicy().getSubject(), Utils.getExpiredDate(lifetime), properties.getCreationPolicy().getKeySize());
					}
				}
				initialized = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, could not initialize the Dorian Certificate Authority.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	private void createCertifcateAuthorityCredentials(String dn, Date expirationDate, int keySize) throws CertificateAuthorityException, NoCACredentialsException {
		try {
			KeyPair pair = KeyUtil.generateRSAKeyPair(getCACredentialsProvider(), keySize);
			X509Certificate cacert = CertUtil.generateCACertificate(getCACredentialsProvider(), new X509Name(dn), new Date(), expirationDate, pair, CertUtil.SHA2_SIGNATURE_ALGORITHM);
			deleteCACredentials();
			this.setCACredentials(cacert, pair.getPrivate(), properties.getCertificateAuthorityPassword());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, could not create the CA credentials.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public java.security.cert.X509Certificate getCACertificate() throws CertificateAuthorityException, NoCACredentialsException {
		return getCACertificate(true);
	}

	private java.security.cert.X509Certificate getCACertificate(boolean errorOnExpiredCredentials) throws CertificateAuthorityException, NoCACredentialsException {
		X509Certificate cert = null;
		init();
		try {
			if (!hasCACredentials()) {
				NoCACredentialsException fault = FaultHelper.createFaultException(NoCACredentialsException.class, "No certificate exists for the CA.");
				throw fault;
			} else {
				cert = getCertificate();
			}
		} catch (NoCACredentialsException f) {
			throw f;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, Error obtaining the CA private key.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}

		if (errorOnExpiredCredentials) {
			Date now = new Date();
			if (now.before(cert.getNotBefore()) || (now.after(cert.getNotAfter()))) {
				if (properties.isAutoRenewCAEnabled()) {
					Lifetime lifetime = properties.getRenewalLifetime();
					return renewCertifcateAuthorityCredentials(Utils.getExpiredDate(lifetime));

				} else {
					NoCACredentialsException fault = FaultHelper.createFaultException(NoCACredentialsException.class, "The CA certificate had expired or is not valid at this time.");
					throw fault;
				}
			}
		}
		return cert;
	}

	public synchronized X509Certificate signCertificate(String subject, PublicKey publicKey, Date start, Date expiration, CertificateSignatureAlgorithm signatureAlgorithm)
			throws CertificateAuthorityException, NoCACredentialsException {
		init();
		X509Certificate cacert = getCACertificate();

		Date caDate = cacert.getNotAfter();
		if (start.after(caDate)) {
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Certificate start date is after the CA certificates expiration date.");
			throw fault;
		}
		if (expiration.after(caDate)) {
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Certificate expiration date is after the CA certificates expiration date.");
			throw fault;
		}

		try {
			// VALIDATE DN
			String caSubject = cacert.getSubjectDN().getName();
			int caindex = caSubject.lastIndexOf(",");
			String caPreSub = caSubject.substring(0, caindex);

			if (!subject.startsWith(caPreSub)) {
				CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Invalid certificate subject, the subject must start with, " + caPreSub);
				throw fault;
			}
			X509Certificate cert = CertUtil.generateCertificate(getCACredentialsProvider(), new X509Name(subject), start, expiration, publicKey, cacert, getPrivateKey(),
					getSignatureAlgorithm(signatureAlgorithm), properties.getPolicyOID());
			return cert;
		} catch (CertificateAuthorityException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, could not sign certificate.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public synchronized X509Certificate signHostCertificate(String host, PublicKey publicKey, Date start, Date expiration, CertificateSignatureAlgorithm signatureAlgorithm)
			throws CertificateAuthorityException, NoCACredentialsException {
		init();
		X509Certificate cacert = getCACertificate();
		try {
			String subject = Utils.getHostCertificateSubject(cacert, host);
			return signCertificate(subject, publicKey, start, expiration, signatureAlgorithm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, could not sign host certificate.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public synchronized X509Certificate renewCertifcateAuthorityCredentials(Date expirationDate) throws CertificateAuthorityException, NoCACredentialsException {
		init();
		try {
			X509Certificate oldcert = getCACertificate(false);
			int size = ((RSAPublicKey) oldcert.getPublicKey()).getModulus().bitLength();
			KeyPair pair = KeyUtil.generateRSAKeyPair(getCACredentialsProvider(), size);
			X509Certificate cacert = CertUtil.generateCACertificate(getCACredentialsProvider(), new X509Name(oldcert.getSubjectDN().getName()), new Date(), expirationDate, pair,
					CertUtil.SHA2_SIGNATURE_ALGORITHM);
			deleteCACredentials();
			this.setCACredentials(cacert, pair.getPrivate(), properties.getCertificateAuthorityPassword());
			return cacert;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, could renew the CA credentials.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public X509CRL getCRL(CRLEntry[] entries, CertificateSignatureAlgorithm signatureAlgorithm) throws CertificateAuthorityException, NoCACredentialsException {
		try {
			init();
			return CertUtil.createCRL(getCACredentialsProvider(), getCACertificate(), getPrivateKey(), entries, getCACertificate().getNotAfter(), getSignatureAlgorithm(signatureAlgorithm));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, could not create the CRL.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public PrivateKey getPrivateKey() throws CertificateAuthorityException, NoCACredentialsException {
		init();
		try {
			if (!hasCACredentials()) {
				NoCACredentialsException fault = FaultHelper.createFaultException(NoCACredentialsException.class, "No Private Key exists for the CA.");
				throw fault;
			} else {
				return getPrivateKey(properties.getCertificateAuthorityPassword());
			}
		} catch (NoCACredentialsException f) {
			throw f;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, Error obtaining the CA private key.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public CertificateAuthorityProperties getProperties() {
		return properties;
	}
}
