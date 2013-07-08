package org.cagrid.dorian.service.idp;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.cagrid.opensaml.SAMLAttribute;
import gov.nih.nci.cagrid.opensaml.SAMLAttributeStatement;
import gov.nih.nci.cagrid.opensaml.SAMLAuthenticationStatement;
import gov.nih.nci.cagrid.opensaml.SAMLNameIdentifier;
import gov.nih.nci.cagrid.opensaml.SAMLSubject;
import gov.nih.nci.cagrid.opensaml.SAMLSubjectStatement;

import java.io.ByteArrayInputStream;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.xml.security.signature.XMLSignature;
import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.common.SAMLConstants;
import org.cagrid.dorian.model.exceptions.DorianInternalException;
import org.cagrid.dorian.service.CertificateSignatureAlgorithm;
import org.cagrid.dorian.service.ca.CertificateAuthority;
import org.cagrid.dorian.service.ca.WrappedKey;
import org.cagrid.dorian.service.ca.WrappingCertificateAuthority;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.gaards.saml.encoding.SAMLUtils;
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
public class AssertionCredentialsManager {

	public static String CREDENTIALS_TABLE = "idp_asserter";

	public final static String CERT_DN = "Dorian IdP Authentication Asserter";

	private final static Logger log = LoggerFactory.getLogger(AssertionCredentialsManager.class);

	private CertificateAuthority ca;

	private IdentityProviderProperties conf;

	private Database db;

	private boolean dbBuilt = false;

	public AssertionCredentialsManager(IdentityProviderProperties conf, CertificateAuthority ca, Database db) throws DorianInternalException {
		try {
			this.ca = ca;
			this.conf = conf;
			this.db = db;

			if (System.getProperty(SAMLUtils.XMLSEC_IGNORE_LINE_BREAK) == null)
				System.setProperty(SAMLUtils.XMLSEC_IGNORE_LINE_BREAK, Boolean.TRUE.toString());
			org.apache.xml.security.Init.init();

			if (!hasAssertingCredentials()) {
				createNewCredentials();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error initializing the IDP Asserting Manager.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	private synchronized boolean hasAssertingCredentials() throws DorianInternalException {
		this.buildDatabase();
		Connection c = null;
		boolean exists = false;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select count(*) from " + CREDENTIALS_TABLE + " where ALIAS= ?");
			s.setString(1, CERT_DN);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					exists = true;
				}
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected error determining if the Dorian IdP has asserting credentials");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		return exists;
	}

	public synchronized void storeCredentials(X509Certificate cert, PrivateKey key) throws Exception {
		this.buildDatabase();
		Connection c = null;

		try {
			if (!hasAssertingCredentials()) {
				if (ca instanceof WrappingCertificateAuthority) {
					WrappingCertificateAuthority wca = (WrappingCertificateAuthority) ca;
					WrappedKey wk = wca.wrap(key);
					String certStr = CertUtil.writeCertificate(cert);
					c = db.getConnection();
					PreparedStatement s = c.prepareStatement("INSERT INTO " + CREDENTIALS_TABLE + " SET ALIAS= ?,CERTIFICATE= ?,PRIVATE_KEY= ?,IV= ?");
					s.setString(1, CERT_DN);
					s.setString(2, certStr);
					s.setBytes(3, wk.getWrappedKeyData());
					s.setBytes(4, wk.getIV());
					s.execute();
					s.close();
				} else {
					String certStr = CertUtil.writeCertificate(cert);
					c = db.getConnection();
					PreparedStatement s = c.prepareStatement("INSERT INTO " + CREDENTIALS_TABLE + " SET ALIAS= ?,CERTIFICATE= ?,PRIVATE_KEY= ?");
					s.setString(1, CERT_DN);
					s.setString(2, certStr);
					s.setBytes(3, KeyUtil.writePrivateKey(key, conf.getAssertingCredentialsEncryptionPassword()).getBytes());
					s.execute();
					s.close();
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Could not store IdP asserting credentials.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	private synchronized void createNewCredentials() throws Exception {
		// VALIDATE DN
		X509Certificate cacert = ca.getCACertificate();
		String caSubject = cacert.getSubjectDN().getName();
		int caindex = caSubject.lastIndexOf(",");
		String caPreSub = caSubject.substring(0, caindex);

		String subject = caPreSub + ",CN=" + CERT_DN;
		KeyPair pair = KeyUtil.generateRSAKeyPair1024();
		GregorianCalendar cal = new GregorianCalendar();
		Date start = cal.getTime();
		X509Certificate cert = ca.signCertificate(subject, pair.getPublic(), start, cacert.getNotAfter(), CertificateSignatureAlgorithm.SHA2);
		storeCredentials(cert, pair.getPrivate());
	}

	public synchronized PrivateKey getIdPKey() throws DorianInternalException {
		this.buildDatabase();
		Connection c = null;
		PrivateKey key = null;
		try {
			// force updating expiring credentials
			getIdPCertificate();
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select PRIVATE_KEY, IV from " + CREDENTIALS_TABLE + " where ALIAS= ?");
			s.setString(1, CERT_DN);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				byte[] wrapped = rs.getBytes("PRIVATE_KEY");
				byte[] iv = rs.getBytes("IV");
				if (ca instanceof WrappingCertificateAuthority) {
					WrappingCertificateAuthority wca = (WrappingCertificateAuthority) ca;
					WrappedKey wk = new WrappedKey(wrapped, iv);
					key = wca.unwrap(wk);
				} else {
					key = KeyUtil.loadPrivateKey(new ByteArrayInputStream(wrapped), conf.getAssertingCredentialsEncryptionPassword());
				}
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error obtaining the IDP Asserting Key.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		if (key == null) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "No private key for the Dorian IdP asserting credentials could be found.");
			throw fault;
		}
		return key;
	}

	public synchronized X509Certificate getIdPCertificate() throws DorianInternalException {
		return getIdPCertificate(true);
	}

	public synchronized SAMLAssertion getAuthenticationAssertion(String uid, String firstName, String lastName, String email) throws DorianInternalException {
		try {
			// org.apache.xml.security.Init.init();
			X509Certificate cert = getIdPCertificate();
			PrivateKey key = getIdPKey();
			GregorianCalendar cal = new GregorianCalendar();
			Date start = cal.getTime();
			cal.add(Calendar.MINUTE, 2);
			Date end = cal.getTime();
			String issuer = cert.getSubjectDN().toString();
			String federation = cert.getSubjectDN().toString();
			String ipAddress = null;
			String subjectDNS = null;

			SAMLNameIdentifier ni1 = new SAMLNameIdentifier(uid, federation, "urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified");
			SAMLSubject sub = new SAMLSubject(ni1, null, null, null);
			sub.addConfirmationMethod(SAMLSubject.CONF_BEARER);
			SAMLNameIdentifier ni2 = new SAMLNameIdentifier(uid, federation, "urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified");
			SAMLSubject sub2 = new SAMLSubject(ni2, null, null, null);
			sub2.addConfirmationMethod(SAMLSubject.CONF_BEARER);
			SAMLAuthenticationStatement auth = new SAMLAuthenticationStatement(sub, "urn:oasis:names:tc:SAML:1.0:am:password", new Date(), ipAddress, subjectDNS, null);

			QName quid = new QName(SAMLConstants.UID_ATTRIBUTE_NAMESPACE, SAMLConstants.UID_ATTRIBUTE);
			List<String> vals1 = new ArrayList<String>();
			vals1.add(uid);
			SAMLAttribute uidAtt = new SAMLAttribute(quid.getLocalPart(), quid.getNamespaceURI(), null, 0, vals1);

			QName qfirst = new QName(SAMLConstants.FIRST_NAME_ATTRIBUTE_NAMESPACE, SAMLConstants.FIRST_NAME_ATTRIBUTE);
			List<String> vals2 = new ArrayList<String>();
			vals2.add(firstName);
			SAMLAttribute firstNameAtt = new SAMLAttribute(qfirst.getLocalPart(), qfirst.getNamespaceURI(), null, 0, vals2);

			QName qLast = new QName(SAMLConstants.LAST_NAME_ATTRIBUTE_NAMESPACE, SAMLConstants.LAST_NAME_ATTRIBUTE);
			List<String> vals3 = new ArrayList<String>();
			vals3.add(lastName);
			SAMLAttribute lastNameAtt = new SAMLAttribute(qLast.getLocalPart(), qLast.getNamespaceURI(), null, 0, vals3);

			QName qemail = new QName(SAMLConstants.EMAIL_ATTRIBUTE_NAMESPACE, SAMLConstants.EMAIL_ATTRIBUTE);
			List<String> vals4 = new ArrayList<String>();
			vals4.add(email);
			SAMLAttribute emailAtt = new SAMLAttribute(qemail.getLocalPart(), qemail.getNamespaceURI(), null, 0, vals4);

			List<SAMLAttribute> atts = new ArrayList<SAMLAttribute>();
			atts.add(uidAtt);
			atts.add(firstNameAtt);
			atts.add(lastNameAtt);
			atts.add(emailAtt);

			SAMLAttributeStatement attState = new SAMLAttributeStatement(sub2, atts);

			List<SAMLSubjectStatement> l = new ArrayList<SAMLSubjectStatement>();
			l.add(auth);
			l.add(attState);

			SAMLAssertion saml = new SAMLAssertion(issuer, start, end, null, null, l);
			List<X509Certificate> a = new ArrayList<X509Certificate>();
			a.add(cert);
			saml.sign(XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1, key, a);

			return saml;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error creating SAML Assertion.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;

		}

	}

	private synchronized X509Certificate getIdPCertificate(boolean firstTime) throws DorianInternalException {
		this.buildDatabase();
		Connection c = null;
		X509Certificate cert = null;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select CERTIFICATE from " + CREDENTIALS_TABLE + " where ALIAS= ?");
			s.setString(1, CERT_DN);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {
				String certStr = rs.getString("CERTIFICATE");
				cert = CertUtil.loadCertificate(certStr);
			}
			rs.close();
			s.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected error obtaining the Dorian IdP asserting credentials certificate.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		if (cert == null) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "No Dorian IdP asserting credentials certificate could be found.");
			throw fault;
		}

		try {

			Date now = new Date();
			if (now.before(cert.getNotBefore()) || (now.after(cert.getNotAfter()))) {
				if ((firstTime) && (conf.autoRenewAssertingCredentials())) {
					deleteAssertingCredentials();
					createNewCredentials();
					return getIdPCertificate(false);
				} else {
					DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "IDP Asserting Certificate expired.");
					throw fault;
				}
			} else {
				return cert;
			}

		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error obtaining the IDP Asserting Certificate.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}

	}

	public synchronized void deleteAssertingCredentials() throws DorianInternalException {
		this.buildDatabase();
		Connection c = null;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("delete from " + CREDENTIALS_TABLE + " where ALIAS= ? ");
			s.setString(1, CERT_DN);
			s.execute();
			s.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected error deleting the Dorian IdP asserting credentials.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	private void buildDatabase() throws DorianInternalException {
		if (!dbBuilt) {
			try {
				if (!this.db.tableExists(CREDENTIALS_TABLE)) {
					String users = "CREATE TABLE " + CREDENTIALS_TABLE + " (" + "ALIAS VARCHAR(255) NOT NULL PRIMARY KEY," + "CERTIFICATE TEXT NOT NULL," + "PRIVATE_KEY BLOB," + "IV BLOB,"
							+ "INDEX document_index (ALIAS));";
					db.update(users);
				}
				this.dbBuilt = true;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected error creating the CA database");
				FaultHelper.addMessage(fault, e.getMessage());
				throw fault;
			}
		}
	}

	public void clearDatabase() throws DorianInternalException {
		this.buildDatabase();
		try {
			db.update("delete from " + CREDENTIALS_TABLE);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected database error occurred.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}
}
