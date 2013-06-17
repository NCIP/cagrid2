package org.cagrid.dorian.ca.impl;

import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.service.CertificateSignatureAlgorithm;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.SecurityUtil;
import org.cagrid.tools.database.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public class DBCertificateAuthority extends CertificateAuthority {

	public static final String SIGNATURE_ALGORITHM = CertUtil.SHA2_SIGNATURE_ALGORITHM;

	private final static Logger logger = LoggerFactory.getLogger(DBCertificateAuthority.class);

	private CredentialsManager manager;

	private String alias;

	public DBCertificateAuthority(String alias, Database db, CertificateAuthorityProperties properties) {
		super(properties);
		SecurityUtil.init();
		this.alias = alias;
		this.manager = new CredentialsManager(db);
	}

	public String getCACredentialsProvider() {
		return getProvider();
	}

	public String getUserCredentialsProvider() {
		return getProvider();
	}

	public String getProvider() {
		return "BC";
	}

	public String getSignatureAlgorithm(CertificateSignatureAlgorithm alg) throws CertificateAuthorityException {
		if (alg.equals(CertificateSignatureAlgorithm.SHA1)) {
			return CertUtil.SHA1_SIGNATURE_ALGORITHM;
		} else if (alg.equals(CertificateSignatureAlgorithm.SHA2)) {
			return CertUtil.SHA2_SIGNATURE_ALGORITHM;
		} else {
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "The signature algorithm " + alg.value()
					+ " is not supported by the certificate authority " + getClass().getName() + ".");
			throw fault;
		}
	}

	public void deleteCACredentials() throws CertificateAuthorityException {
		try {
			manager.deleteCredentials(this.alias);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "An unexpected error occurred, could not delete the CA credentials.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}

	}

	public X509Certificate getCertificate() throws CertificateAuthorityException {
		try {
			if (!hasCACredentials()) {
				CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "The CA certificate does not exist.");
				throw fault;
			} else {
				return manager.getCertificate(this.alias);
			}
		} catch (CertificateAuthorityException f) {
			throw f;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, could not obtain the certificate.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}

	}

	public PrivateKey getPrivateKey(String password) throws CertificateAuthorityException {
		try {
			if (!hasCACredentials()) {
				CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "The CA private key does not exist.");
				throw fault;
			} else {
				return manager.getPrivateKey(this.alias, password);
			}
		} catch (CertificateAuthorityException f) {
			throw f;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Unexpected Error, could not obtain the private key.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public boolean hasCACredentials() throws CertificateAuthorityException {
		try {
			return this.manager.hasCredentials(this.alias);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "An unexpected error occurred, could not determine if credentials exist.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public void setCACredentials(X509Certificate cert, PrivateKey key, String password) throws CertificateAuthorityException {
		try {

			if (hasCACredentials()) {
				CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "Credentials already exist for the CA.");
				throw fault;
			}
			manager.addCredentials(this.alias, password, cert, key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			CertificateAuthorityException fault = FaultHelper.createFaultException(CertificateAuthorityException.class, "An unexpected error occurred, could not add CA credentials.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

}