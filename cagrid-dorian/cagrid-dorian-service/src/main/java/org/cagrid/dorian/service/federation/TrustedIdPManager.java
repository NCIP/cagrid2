package org.cagrid.dorian.service.federation;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.cagrid.opensaml.SAMLException;

import java.io.StringReader;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.model.exceptions.DorianInternalException;
import org.cagrid.dorian.model.exceptions.InvalidAssertionException;
import org.cagrid.dorian.model.exceptions.InvalidTrustedIdPException;
import org.cagrid.dorian.model.federation.GridUserPolicy;
import org.cagrid.dorian.model.federation.SAMLAttributeDescriptor;
import org.cagrid.dorian.model.federation.SAMLAuthenticationMethod;
import org.cagrid.dorian.model.federation.TrustedIdP;
import org.cagrid.dorian.model.federation.TrustedIdPStatus;
import org.cagrid.gaards.pki.CertUtil;
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
public class TrustedIdPManager {

	private Database db;

	public final static String TRUST_MANAGER_TABLE = "trust_manager";

	public final static String ID_FIELD = "ID";
	public final static String NAME_FIELD = "NAME";
	public final static String DISPLAY_NAME_FIELD = "DISPLAY_NAME";
	public final static String STATUS_FIELD = "STATUS";
	public final static String POLICY_CLASS_FIELD = "POLICY_CLASS";
	public final static String IDP_SUBJECT_FIELD = "IDP_SUBJECT";
	public final static String IDP_CERTIFICATE_FIELD = "IDP_CERTIFICATE";
	public final static String AUTHENTICATION_SERVICE_URL_FIELD = "AUTHENTICATION_SERVICE_URL";
	public final static String AUTHENTICATION_SERVICE_IDENTITY_FIELD = "AUTHENTICATION_SERVICE_IDENTITY";
	public final static String PUBLISH_FIELD = "PUBLISH";
	public final static String USER_ID_ATT_NS_FIELD = "USER_ID_ATT_NS";
	public final static String USER_ID_ATT_NAME_FIELD = "USER_ID_ATT_NAME";
	public final static String FIRST_NAME_ATT_NS_FIELD = "FIRST_NAME_ATT_NS";
	public final static String FIRST_NAME_ATT_NAME_FIELD = "FIRST_NAME_ATT_NAME";
	public final static String LAST_NAME_ATT_NS_FIELD = "LAST_NAME_ATT_NS";
	public final static String LAST_NAME_ATT_NAME_FIELD = "LAST_NAME_ATT_NAME";
	public final static String EMAIL_ATT_NS_FIELD = "EMAIL_ATT_NS";
	public final static String EMAIL_ATT_NAME_FIELD = "EMAIL_ATT_NAME";

	public final static String AUTH_METHODS_TABLE = "trust_manager_auth_methods";

	public final static String METHOD_ID_FIELD = "ID";
	public final static String IDP_ID_FIELD = "IDP_ID";
	public final static String METHOD_FIELD = "METHOD";

	private final static Logger logger = LoggerFactory.getLogger(TrustedIdPManager.class);

	private boolean dbBuilt = false;

	private IdentityFederationProperties conf;

	private GridUserPolicy[] accountPolicies;

	public TrustedIdPManager(IdentityFederationProperties conf, Database db) throws DorianInternalException {
		this.db = db;
		this.conf = conf;
		List<AccountPolicy> policies = conf.getAccountPolicies();
		this.accountPolicies = new GridUserPolicy[policies.size()];
		for (int i = 0; i < policies.size(); i++) {
			AccountPolicy p = policies.get(i);
			accountPolicies[i] = new GridUserPolicy();
			accountPolicies[i].setName(p.getDisplayName());
			accountPolicies[i].setClassName(p.getClass().getName());
		}
	}

	public GridUserPolicy[] getAccountPolicies() {
		return accountPolicies;
	}

	public void clearDatabase() throws DorianInternalException {
		buildDatabase();
		try {
			db.update("DROP TABLE IF EXISTS " + TRUST_MANAGER_TABLE);
			db.update("DROP TABLE IF EXISTS " + AUTH_METHODS_TABLE);
			dbBuilt = false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected database error occurred.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public void buildDatabase() throws DorianInternalException {
		if (!dbBuilt) {
			try {
				if (!this.db.tableExists(TRUST_MANAGER_TABLE)) {
					String trust = "CREATE TABLE " + TRUST_MANAGER_TABLE + " (" + ID_FIELD + " INT NOT NULL AUTO_INCREMENT PRIMARY KEY," + NAME_FIELD + " VARCHAR(255) NOT NULL," + DISPLAY_NAME_FIELD
							+ " TEXT NOT NULL," + STATUS_FIELD + " VARCHAR(50) NOT NULL," + POLICY_CLASS_FIELD + " TEXT NOT NULL," + IDP_SUBJECT_FIELD + " TEXT NOT NULL," + IDP_CERTIFICATE_FIELD
							+ " TEXT NOT NULL," + AUTHENTICATION_SERVICE_URL_FIELD + " TEXT," + AUTHENTICATION_SERVICE_IDENTITY_FIELD + " TEXT," + PUBLISH_FIELD + " VARCHAR(1),"
							+ USER_ID_ATT_NS_FIELD + " TEXT NOT NULL," + USER_ID_ATT_NAME_FIELD + " TEXT NOT NULL," + FIRST_NAME_ATT_NS_FIELD + " TEXT NOT NULL," + FIRST_NAME_ATT_NAME_FIELD
							+ " TEXT NOT NULL," + LAST_NAME_ATT_NS_FIELD + " TEXT NOT NULL," + LAST_NAME_ATT_NAME_FIELD + " TEXT NOT NULL," + EMAIL_ATT_NS_FIELD + " TEXT NOT NULL,"
							+ EMAIL_ATT_NAME_FIELD + " TEXT NOT NULL," + "INDEX document_index (" + NAME_FIELD + "));";
					db.update(trust);

					String methods = "CREATE TABLE " + AUTH_METHODS_TABLE + " (" + METHOD_ID_FIELD + " INT NOT NULL AUTO_INCREMENT PRIMARY KEY," + IDP_ID_FIELD + " INT NOT NULL," + METHOD_FIELD
							+ " VARCHAR(255) NOT NULL," + "INDEX document_index (" + METHOD_ID_FIELD + "));";
					db.update(methods);
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected database error occurred.");
				FaultHelper.addMessage(fault, e.getMessage());
				throw fault;
			}
			dbBuilt = true;
		}
	}

	public synchronized void removeTrustedIdP(long id) throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("delete from " + TRUST_MANAGER_TABLE + " WHERE " + ID_FIELD + "= ?");
			s.setLong(1, id);
			s.execute();
			s.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected Database Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		removeAuthenticationMethodsForTrustedIdP(id);
	}

	private void removeAuthenticationMethodsForTrustedIdP(long id) throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("delete from " + AUTH_METHODS_TABLE + " WHERE " + IDP_ID_FIELD + "= ?");
			s.setLong(1, id);
			s.execute();
			s.close();
		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected Database Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	public synchronized SAMLAuthenticationMethod[] getAuthenticationMethods(long id) throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select * from " + AUTH_METHODS_TABLE + " where " + IDP_ID_FIELD + "= ? ORDER BY " + METHOD_ID_FIELD);
			s.setLong(1, id);
			ResultSet rs = s.executeQuery();
			List<SAMLAuthenticationMethod> methods = new ArrayList<SAMLAuthenticationMethod>();
			while (rs.next()) {
				SAMLAuthenticationMethod method = SAMLAuthenticationMethod.fromValue(rs.getString(METHOD_FIELD));
				methods.add(method);
			}
			rs.close();
			s.close();
			if (methods.size() > 0) {
				SAMLAuthenticationMethod[] list = new SAMLAuthenticationMethod[methods.size()];
				for (int i = 0; i < methods.size(); i++) {
					list[i] = (SAMLAuthenticationMethod) methods.get(i);
				}
				return list;
			} else {
				return null;
			}

		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected Database Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}

	}

	public synchronized void updateIdP(TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException {
		TrustedIdP curr = this.getTrustedIdPById(idp.getId());
		boolean needsUpdate = false;
		String name = curr.getName();
		if ((Utils.clean(idp.getName()) != null) && (!idp.getName().equals(curr.getName()))) {
			InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "The name of a TrustedIdP cannot be changed.");
			throw fault;
		}

		String displayName = curr.getDisplayName();

		if ((Utils.clean(idp.getDisplayName()) != null) && (!idp.getDisplayName().equals(curr.getDisplayName()))) {
			needsUpdate = true;
			displayName = validateAndGetDisplayName(idp);
		}

		String policy = curr.getUserPolicyClass();

		if ((Utils.clean(idp.getUserPolicyClass()) != null) && (!idp.getUserPolicyClass().equals(curr.getUserPolicyClass()))) {
			needsUpdate = true;
			policy = validateAndGetPolicy(idp.getUserPolicyClass()).getClassName();
		}
		String status = curr.getStatus().value();
		if ((idp.getStatus() != null) && (!idp.getStatus().equals(curr.getStatus()))) {
			needsUpdate = true;
			status = idp.getStatus().value();
		}
		X509Certificate currcert = validateAndGetCertificate(curr);
		String certSubject = currcert.getSubjectDN().getName();
		String certEncoded = curr.getIdPCertificate();
		if ((Utils.clean(idp.getIdPCertificate()) != null) && (!idp.getIdPCertificate().equals(curr.getIdPCertificate()))) {
			if (!isCertificateUnique(idp.getIdPCertificate())) {
				InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "Cannot update the Trusted IdP, " + idp.getName()
						+ ", it does not contain a unique certificate.");
				throw fault;
			}

			X509Certificate cert = validateAndGetCertificate(idp);
			certSubject = cert.getSubjectDN().getName();
			certEncoded = idp.getIdPCertificate();
			needsUpdate = true;
		}

		String authenticationServiceURL = curr.getAuthenticationServiceURL();

		if ((idp.getAuthenticationServiceURL() != null) && (!idp.getAuthenticationServiceURL().equals(curr.getAuthenticationServiceURL()))) {
			needsUpdate = true;
			authenticationServiceURL = validateAndGetAuthenticationServiceURL(idp);
		}

		String authenticationServiceIdentity = curr.getAuthenticationServiceIdentity();

		if ((idp.getAuthenticationServiceIdentity() != null) && (!idp.getAuthenticationServiceIdentity().equals(curr.getAuthenticationServiceIdentity()))) {
			needsUpdate = true;
			authenticationServiceIdentity = validateAndGetAuthenticationServiceIdentity(idp);
		}

		String uidNS = curr.getUserIdAttributeDescriptor().getNamespaceURI();
		String uidName = curr.getUserIdAttributeDescriptor().getName();
		if ((idp.getUserIdAttributeDescriptor() != null) && (!idp.getUserIdAttributeDescriptor().equals(curr.getUserIdAttributeDescriptor()))) {
			verifyUserIdAttributeDescriptor(idp.getUserIdAttributeDescriptor());
			uidNS = idp.getUserIdAttributeDescriptor().getNamespaceURI();
			needsUpdate = true;
			uidName = idp.getUserIdAttributeDescriptor().getName();

		}

		String firstNS = curr.getFirstNameAttributeDescriptor().getNamespaceURI();
		String firstName = curr.getFirstNameAttributeDescriptor().getName();

		if ((idp.getFirstNameAttributeDescriptor() != null) && (!idp.getFirstNameAttributeDescriptor().equals(curr.getFirstNameAttributeDescriptor()))) {
			verifyFirstNameAttributeDescriptor(idp.getFirstNameAttributeDescriptor());
			firstNS = idp.getFirstNameAttributeDescriptor().getNamespaceURI();
			needsUpdate = true;
			firstName = idp.getFirstNameAttributeDescriptor().getName();

		}
		String lastNS = curr.getLastNameAttributeDescriptor().getNamespaceURI();
		String lastName = curr.getLastNameAttributeDescriptor().getName();
		if ((idp.getLastNameAttributeDescriptor() != null) && (!idp.getLastNameAttributeDescriptor().equals(curr.getLastNameAttributeDescriptor()))) {
			verifyFirstNameAttributeDescriptor(idp.getFirstNameAttributeDescriptor());
			lastNS = idp.getLastNameAttributeDescriptor().getNamespaceURI();
			needsUpdate = true;
			lastName = idp.getLastNameAttributeDescriptor().getName();
		}

		String emailNS = curr.getEmailAttributeDescriptor().getNamespaceURI();
		String emailName = curr.getEmailAttributeDescriptor().getName();
		if ((idp.getEmailAttributeDescriptor() != null) && (!idp.getEmailAttributeDescriptor().equals(curr.getEmailAttributeDescriptor()))) {
			verifyEmailAttributeDescriptor(idp.getEmailAttributeDescriptor());
			emailNS = idp.getEmailAttributeDescriptor().getNamespaceURI();
			needsUpdate = true;
			emailName = idp.getEmailAttributeDescriptor().getName();
		}

		Connection c = null;
		try {

			if (needsUpdate) {
				c = db.getConnection();
				PreparedStatement s = c.prepareStatement("UPDATE " + TRUST_MANAGER_TABLE + " SET " + NAME_FIELD + "= ?, " + DISPLAY_NAME_FIELD + "= ?, " + IDP_SUBJECT_FIELD + "= ?, " + STATUS_FIELD
						+ "= ?, " + POLICY_CLASS_FIELD + "= ?, " + IDP_CERTIFICATE_FIELD + "= ?, " + AUTHENTICATION_SERVICE_URL_FIELD + "= ?, " + AUTHENTICATION_SERVICE_IDENTITY_FIELD + "= ?, "
						+ USER_ID_ATT_NS_FIELD + " = ?, " + USER_ID_ATT_NAME_FIELD + " = ?, " + FIRST_NAME_ATT_NS_FIELD + " = ?, " + FIRST_NAME_ATT_NAME_FIELD + " = ?, " + LAST_NAME_ATT_NS_FIELD
						+ " = ?, " + LAST_NAME_ATT_NAME_FIELD + " = ?, " + EMAIL_ATT_NS_FIELD + " = ?, " + EMAIL_ATT_NAME_FIELD + " = ? WHERE " + ID_FIELD + "= ?");

				s.setString(1, name);
				s.setString(2, displayName);
				s.setString(3, certSubject);
				s.setString(4, status);
				s.setString(5, policy);
				s.setString(6, certEncoded);
				s.setString(7, authenticationServiceURL);
				s.setString(8, authenticationServiceIdentity);
				s.setString(9, uidNS);
				s.setString(10, uidName);
				s.setString(11, firstNS);
				s.setString(12, firstName);
				s.setString(13, lastNS);
				s.setString(14, lastName);
				s.setString(15, emailNS);
				s.setString(16, emailName);
				s.setLong(17, curr.getId());
				s.execute();
				s.close();
			}

			if (!Arrays.equals(curr.getAuthenticationMethod().toArray(), idp.getAuthenticationMethod().toArray())) {
				removeAuthenticationMethodsForTrustedIdP(idp.getId());
				if (idp.getAuthenticationMethod() != null) {
					for (int i = 0; i < idp.getAuthenticationMethod().size(); i++) {
						this.addAuthenticationMethod(idp.getId(), idp.getAuthenticationMethod().get(i));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error updating the Trusted IdP " + idp.getName()
					+ ", an unexpected database error occurred.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	public TrustedIdP getTrustedIdP(SAMLAssertion saml) throws DorianInternalException, InvalidAssertionException {

		if (!saml.isSigned()) {
			String mess = "The assertion specified is invalid, it MUST be signed by a Trusted IdP";
			InvalidAssertionException fault = FaultHelper.createFaultException(InvalidAssertionException.class, mess);
			throw fault;
		}
		TrustedIdP[] idps = getTrustedIdPs();
		for (int i = 0; i < idps.length; i++) {
			try {
				X509Certificate cert = CertUtil.loadCertificate(idps[i].getIdPCertificate());
				saml.verify(cert);
				if (logger.isDebugEnabled()) {
					logger.debug("The idp " + idps[i].getName() + " signed SAML Assertion " + saml.getId() + ".");
				}
				return idps[i];
			} catch (SAMLException se) {
				if (logger.isDebugEnabled()) {
					logger.debug("The idp " + idps[i].getName() + " did not sign the SAML Assertion " + saml.getId() + ".");
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		InvalidAssertionException fault = FaultHelper.createFaultException(InvalidAssertionException.class, "The assertion specified, is not signed by a trusted IdP and therefore is not trusted.");
		throw fault;
	}

	public TrustedIdP getTrustedIdP(X509Certificate certificate) throws DorianInternalException, InvalidAssertionException {
		TrustedIdP[] idps = getTrustedIdPs();
		for (int i = 0; i < idps.length; i++) {
			try {
				X509Certificate cert = CertUtil.loadCertificate(idps[i].getIdPCertificate());
				if (cert.equals(certificate)) {
					return idps[i];
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		InvalidAssertionException fault = FaultHelper.createFaultException(InvalidAssertionException.class, "No Trusted IdP could be found matching the certificate specified.");
		throw fault;
	}

	public synchronized TrustedIdP[] getTrustedIdPs() throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		try {
			c = db.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from " + TRUST_MANAGER_TABLE);
			List<TrustedIdP> idps = new ArrayList<TrustedIdP>();
			while (rs.next()) {
				TrustedIdP idp = new TrustedIdP();
				idp.setId(rs.getLong(ID_FIELD));
				idp.setName(rs.getString(NAME_FIELD));
				idp.setDisplayName(rs.getString(DISPLAY_NAME_FIELD));
				idp.setStatus(TrustedIdPStatus.fromValue(rs.getString(STATUS_FIELD)));
				idp.setIdPCertificate(rs.getString(IDP_CERTIFICATE_FIELD));
				idp.setUserPolicyClass(rs.getString(POLICY_CLASS_FIELD));
				idp.setAuthenticationServiceURL(Utils.clean(rs.getString(AUTHENTICATION_SERVICE_URL_FIELD)));
				idp.setAuthenticationServiceIdentity(Utils.clean(rs.getString(AUTHENTICATION_SERVICE_IDENTITY_FIELD)));

				SAMLAttributeDescriptor uid = new SAMLAttributeDescriptor();
				uid.setNamespaceURI(rs.getString(USER_ID_ATT_NS_FIELD));
				uid.setName(rs.getString(USER_ID_ATT_NAME_FIELD));
				idp.setUserIdAttributeDescriptor(uid);

				SAMLAttributeDescriptor firstName = new SAMLAttributeDescriptor();
				firstName.setNamespaceURI(rs.getString(FIRST_NAME_ATT_NS_FIELD));
				firstName.setName(rs.getString(FIRST_NAME_ATT_NAME_FIELD));
				idp.setFirstNameAttributeDescriptor(firstName);

				SAMLAttributeDescriptor lastName = new SAMLAttributeDescriptor();
				lastName.setNamespaceURI(rs.getString(LAST_NAME_ATT_NS_FIELD));
				lastName.setName(rs.getString(LAST_NAME_ATT_NAME_FIELD));
				idp.setLastNameAttributeDescriptor(lastName);

				SAMLAttributeDescriptor email = new SAMLAttributeDescriptor();
				email.setNamespaceURI(rs.getString(EMAIL_ATT_NS_FIELD));
				email.setName(rs.getString(EMAIL_ATT_NAME_FIELD));
				idp.setEmailAttributeDescriptor(email);
				idps.add(idp);
			}
			rs.close();
			s.close();

			TrustedIdP[] list = new TrustedIdP[idps.size()];
			for (int i = 0; i < idps.size(); i++) {
				list[i] = (TrustedIdP) idps.get(i);
				SAMLAuthenticationMethod[] methods = getAuthenticationMethods(list[i].getId());
				if (methods != null) {
					list[i].getAuthenticationMethod().addAll(Arrays.asList(methods));
				}
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error obtaining a list of trusted IdPs, unexpected database error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}

	}

	public synchronized TrustedIdP[] getSuspendedTrustedIdPs() throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		try {
			c = db.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from " + TRUST_MANAGER_TABLE + " where STATUS='" + TrustedIdPStatus.SUSPENDED + "'");
			List<TrustedIdP> idps = new ArrayList<TrustedIdP>();
			while (rs.next()) {
				TrustedIdP idp = new TrustedIdP();
				idp.setId(rs.getLong(ID_FIELD));
				idp.setName(rs.getString(NAME_FIELD));
				idp.setDisplayName(rs.getString(DISPLAY_NAME_FIELD));
				idp.setStatus(TrustedIdPStatus.fromValue(rs.getString(STATUS_FIELD)));
				idp.setIdPCertificate(rs.getString(IDP_CERTIFICATE_FIELD));
				idp.setUserPolicyClass(rs.getString(POLICY_CLASS_FIELD));
				idp.setAuthenticationServiceURL(Utils.clean(rs.getString(AUTHENTICATION_SERVICE_URL_FIELD)));
				idp.setAuthenticationServiceIdentity(Utils.clean(rs.getString(AUTHENTICATION_SERVICE_IDENTITY_FIELD)));

				SAMLAttributeDescriptor uid = new SAMLAttributeDescriptor();
				uid.setNamespaceURI(rs.getString(USER_ID_ATT_NS_FIELD));
				uid.setName(rs.getString(USER_ID_ATT_NAME_FIELD));
				idp.setUserIdAttributeDescriptor(uid);

				SAMLAttributeDescriptor firstName = new SAMLAttributeDescriptor();
				firstName.setNamespaceURI(rs.getString(FIRST_NAME_ATT_NS_FIELD));
				firstName.setName(rs.getString(FIRST_NAME_ATT_NAME_FIELD));
				idp.setFirstNameAttributeDescriptor(firstName);

				SAMLAttributeDescriptor lastName = new SAMLAttributeDescriptor();
				lastName.setNamespaceURI(rs.getString(LAST_NAME_ATT_NS_FIELD));
				lastName.setName(rs.getString(LAST_NAME_ATT_NAME_FIELD));
				idp.setLastNameAttributeDescriptor(lastName);

				SAMLAttributeDescriptor email = new SAMLAttributeDescriptor();
				email.setNamespaceURI(rs.getString(EMAIL_ATT_NS_FIELD));
				email.setName(rs.getString(EMAIL_ATT_NAME_FIELD));
				idp.setEmailAttributeDescriptor(email);
				idps.add(idp);
			}
			rs.close();
			s.close();

			TrustedIdP[] list = new TrustedIdP[idps.size()];
			for (int i = 0; i < idps.size(); i++) {
				list[i] = (TrustedIdP) idps.get(i);
				list[i].getAuthenticationMethod().addAll(Arrays.asList(getAuthenticationMethods(list[i].getId())));
			}
			return list;

		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error obtaining a list of trusted IdPs, unexpected database error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}

	}

	public synchronized TrustedIdP getTrustedIdPById(long id) throws DorianInternalException, InvalidTrustedIdPException {
		buildDatabase();
		Connection c = null;

		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select * from " + TRUST_MANAGER_TABLE + " WHERE ID= ?");
			s.setLong(1, id);
			ResultSet rs = s.executeQuery();
			TrustedIdP idp = null;
			if (rs.next()) {
				idp = new TrustedIdP();
				idp.setId(rs.getLong(ID_FIELD));
				idp.setName(rs.getString(NAME_FIELD));
				idp.setDisplayName(rs.getString(DISPLAY_NAME_FIELD));
				idp.setStatus(TrustedIdPStatus.fromValue(rs.getString(STATUS_FIELD)));
				idp.setIdPCertificate(rs.getString(IDP_CERTIFICATE_FIELD));
				idp.setUserPolicyClass(rs.getString(POLICY_CLASS_FIELD));
				idp.setAuthenticationServiceURL(Utils.clean(rs.getString(AUTHENTICATION_SERVICE_URL_FIELD)));
				idp.setAuthenticationServiceIdentity(Utils.clean(rs.getString(AUTHENTICATION_SERVICE_IDENTITY_FIELD)));

				SAMLAttributeDescriptor uid = new SAMLAttributeDescriptor();
				uid.setNamespaceURI(rs.getString(USER_ID_ATT_NS_FIELD));
				uid.setName(rs.getString(USER_ID_ATT_NAME_FIELD));
				idp.setUserIdAttributeDescriptor(uid);

				SAMLAttributeDescriptor firstName = new SAMLAttributeDescriptor();
				firstName.setNamespaceURI(rs.getString(FIRST_NAME_ATT_NS_FIELD));
				firstName.setName(rs.getString(FIRST_NAME_ATT_NAME_FIELD));
				idp.setFirstNameAttributeDescriptor(firstName);

				SAMLAttributeDescriptor lastName = new SAMLAttributeDescriptor();
				lastName.setNamespaceURI(rs.getString(LAST_NAME_ATT_NS_FIELD));
				lastName.setName(rs.getString(LAST_NAME_ATT_NAME_FIELD));
				idp.setLastNameAttributeDescriptor(lastName);

				SAMLAttributeDescriptor email = new SAMLAttributeDescriptor();
				email.setNamespaceURI(rs.getString(EMAIL_ATT_NS_FIELD));
				email.setName(rs.getString(EMAIL_ATT_NAME_FIELD));
				idp.setEmailAttributeDescriptor(email);
			} else {
				InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "The Trusted IdP " + id + " does not exist.");
				throw fault;
			}
			rs.close();
			s.close();
			SAMLAuthenticationMethod[] methods = getAuthenticationMethods(idp.getId());
			if (methods != null) {
				idp.getAuthenticationMethod().addAll(Arrays.asList(methods));
			}
			return idp;
		} catch (InvalidTrustedIdPException f) {
			throw f;
		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error obtaining the Trusted IdP " + id + ", unexpected database error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	public synchronized TrustedIdP getTrustedIdPByName(String name) throws DorianInternalException, InvalidTrustedIdPException {
		buildDatabase();
		Connection c = null;

		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select * from " + TRUST_MANAGER_TABLE + " WHERE NAME= ?");
			s.setString(1, name);
			ResultSet rs = s.executeQuery();
			TrustedIdP idp = null;
			if (rs.next()) {
				idp = new TrustedIdP();
				idp.setId(rs.getLong(ID_FIELD));
				idp.setName(rs.getString(NAME_FIELD));
				idp.setDisplayName(rs.getString(DISPLAY_NAME_FIELD));
				idp.setStatus(TrustedIdPStatus.fromValue(rs.getString(STATUS_FIELD)));
				idp.setIdPCertificate(rs.getString(IDP_CERTIFICATE_FIELD));
				idp.setUserPolicyClass(rs.getString(POLICY_CLASS_FIELD));
				idp.setAuthenticationServiceURL(Utils.clean(rs.getString(AUTHENTICATION_SERVICE_URL_FIELD)));
				idp.setAuthenticationServiceIdentity(Utils.clean(rs.getString(AUTHENTICATION_SERVICE_IDENTITY_FIELD)));

				SAMLAttributeDescriptor uid = new SAMLAttributeDescriptor();
				uid.setNamespaceURI(rs.getString(USER_ID_ATT_NS_FIELD));
				uid.setName(rs.getString(USER_ID_ATT_NAME_FIELD));
				idp.setUserIdAttributeDescriptor(uid);

				SAMLAttributeDescriptor firstName = new SAMLAttributeDescriptor();
				firstName.setNamespaceURI(rs.getString(FIRST_NAME_ATT_NS_FIELD));
				firstName.setName(rs.getString(FIRST_NAME_ATT_NAME_FIELD));
				idp.setFirstNameAttributeDescriptor(firstName);

				SAMLAttributeDescriptor lastName = new SAMLAttributeDescriptor();
				lastName.setNamespaceURI(rs.getString(LAST_NAME_ATT_NS_FIELD));
				lastName.setName(rs.getString(LAST_NAME_ATT_NAME_FIELD));
				idp.setLastNameAttributeDescriptor(lastName);

				SAMLAttributeDescriptor email = new SAMLAttributeDescriptor();
				email.setNamespaceURI(rs.getString(EMAIL_ATT_NS_FIELD));
				email.setName(rs.getString(EMAIL_ATT_NAME_FIELD));
				idp.setEmailAttributeDescriptor(email);
			} else {
				InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "The Trusted IdP " + name + " does not exist.");
				throw fault;
			}
			rs.close();
			s.close();
			SAMLAuthenticationMethod[] methods = getAuthenticationMethods(idp.getId());
			if (methods != null) {
				idp.getAuthenticationMethod().addAll(Arrays.asList(methods));
			}
			return idp;
		} catch (InvalidTrustedIdPException f) {
			throw f;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error obtaining the Trusted IdP " + name + ", unexpected database error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	public synchronized TrustedIdP getTrustedIdPByDN(String dn) throws DorianInternalException, InvalidTrustedIdPException {
		buildDatabase();
		Connection c = null;

		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select * from " + TRUST_MANAGER_TABLE + " WHERE IDP_SUBJECT= ?");
			s.setString(1, dn);
			ResultSet rs = s.executeQuery();
			TrustedIdP idp = null;
			if (rs.next()) {
				idp = new TrustedIdP();
				idp.setId(rs.getLong(ID_FIELD));
				idp.setName(rs.getString(NAME_FIELD));
				idp.setDisplayName(rs.getString(DISPLAY_NAME_FIELD));
				idp.setStatus(TrustedIdPStatus.fromValue(rs.getString(STATUS_FIELD)));
				idp.setIdPCertificate(rs.getString(IDP_CERTIFICATE_FIELD));
				idp.setUserPolicyClass(rs.getString(POLICY_CLASS_FIELD));
				idp.setAuthenticationServiceURL(Utils.clean(rs.getString(AUTHENTICATION_SERVICE_URL_FIELD)));
				idp.setAuthenticationServiceIdentity(Utils.clean(rs.getString(AUTHENTICATION_SERVICE_IDENTITY_FIELD)));

				SAMLAttributeDescriptor uid = new SAMLAttributeDescriptor();
				uid.setNamespaceURI(rs.getString(USER_ID_ATT_NS_FIELD));
				uid.setName(rs.getString(USER_ID_ATT_NAME_FIELD));
				idp.setUserIdAttributeDescriptor(uid);

				SAMLAttributeDescriptor firstName = new SAMLAttributeDescriptor();
				firstName.setNamespaceURI(rs.getString(FIRST_NAME_ATT_NS_FIELD));
				firstName.setName(rs.getString(FIRST_NAME_ATT_NAME_FIELD));
				idp.setFirstNameAttributeDescriptor(firstName);

				SAMLAttributeDescriptor lastName = new SAMLAttributeDescriptor();
				lastName.setNamespaceURI(rs.getString(LAST_NAME_ATT_NS_FIELD));
				lastName.setName(rs.getString(LAST_NAME_ATT_NAME_FIELD));
				idp.setLastNameAttributeDescriptor(lastName);

				SAMLAttributeDescriptor email = new SAMLAttributeDescriptor();
				email.setNamespaceURI(rs.getString(EMAIL_ATT_NS_FIELD));
				email.setName(rs.getString(EMAIL_ATT_NAME_FIELD));
				idp.setEmailAttributeDescriptor(email);
			} else {
				InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "The Trusted IdP " + dn + " does not exist.");
				throw fault;
			}
			rs.close();
			s.close();
			SAMLAuthenticationMethod[] methods = getAuthenticationMethods(idp.getId());
			if (methods != null) {
				idp.getAuthenticationMethod().addAll(Arrays.asList(methods));
			}
			return idp;
		} catch (InvalidTrustedIdPException f) {
			throw f;
		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error obtaining the Trusted IdP " + dn + ", unexpected database error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	private String validateAndGetName(TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException {
		String name = idp.getName();
		if ((name == null) || (name.trim().length() < conf.getMinIdPNameLength()) || (name.trim().length() > conf.getMaxIdPNameLength())) {
			InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class,
					"Invalid IdP name specified, the IdP name must be between " + conf.getMinIdPNameLength() + " and " + conf.getMaxIdPNameLength() + " in length.");
			throw fault;
		}

		return name.trim();
	}

	private String validateAndGetDisplayName(TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException {
		String displayName = idp.getDisplayName();
		if ((displayName == null) || (displayName.trim().length() < conf.getMinIdPDisplayNameLength()) || (displayName.trim().length() > conf.getMaxIdPDisplayNameLength())) {
			InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class,
					"Invalid IdP display name specified, the IdP name must be between " + conf.getMinIdPDisplayNameLength() + " and " + conf.getMaxIdPDisplayNameLength() + " in length.");
			throw fault;
		}
		return displayName.trim();
	}

	private String validateAndGetAuthenticationServiceURL(TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException {
		if (Utils.clean(idp.getAuthenticationServiceURL()) == null) {
			return "";
		}
		try {
			new URL(idp.getAuthenticationServiceURL());
		} catch (Exception e) {
			InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "Invalid Authentication Service URL specified!!!");
			throw fault;
		}
		return idp.getAuthenticationServiceURL();
	}

	private String validateAndGetAuthenticationServiceIdentity(TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException {
		String id = idp.getAuthenticationServiceIdentity();
		if (Utils.clean(id) == null) {
			return "";
		} else {
			return id;
		}
	}

	private GridUserPolicy validateAndGetPolicy(String className) throws DorianInternalException, InvalidTrustedIdPException {
		for (int i = 0; i < accountPolicies.length; i++) {
			if (accountPolicies[i].getClassName().equals(className)) {
				return accountPolicies[i];
			}
		}
		InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "Invalid User Policy Class Specified.");
		throw fault;
	}

	private X509Certificate validateAndGetCertificate(TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException {

		if (idp.getIdPCertificate() == null) {
			InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "Invalid Trusted IdP, no IdP certificate specified.");
			throw fault;
		}
		StringReader reader = new StringReader(idp.getIdPCertificate());
		X509Certificate cert = null;
		try {
			cert = CertUtil.loadCertificate(reader);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "Invalid IdP Certificate specified.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}

		if (CertUtil.isExpired(cert)) {
			InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "The IdP Certificate specified is expired.");
			throw fault;
		}

		return cert;

	}

	private boolean isCertificateUnique(String certAsString) throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		boolean exists = true;
		try {
			c = db.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select count(*) from " + TRUST_MANAGER_TABLE + " where " + IDP_CERTIFICATE_FIELD + "='" + certAsString + "'");
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					exists = false;
				}
			}
			rs.close();
			s.close();

		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected Database Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		return exists;
	}

	private void verifyUserIdAttributeDescriptor(SAMLAttributeDescriptor des) throws InvalidTrustedIdPException {
		verifySAMLAttributeDescriptor(des, "User Id");
	}

	private void verifyFirstNameAttributeDescriptor(SAMLAttributeDescriptor des) throws InvalidTrustedIdPException {
		verifySAMLAttributeDescriptor(des, "First Name");
	}

	private void verifyLastNameAttributeDescriptor(SAMLAttributeDescriptor des) throws InvalidTrustedIdPException {
		verifySAMLAttributeDescriptor(des, "Last Name");
	}

	private void verifyEmailAttributeDescriptor(SAMLAttributeDescriptor des) throws InvalidTrustedIdPException {
		verifySAMLAttributeDescriptor(des, "Email");
	}

	private void verifySAMLAttributeDescriptor(SAMLAttributeDescriptor des, String name) throws InvalidTrustedIdPException {
		if ((des == null) || (Utils.clean(des.getNamespaceURI()) == null) || (Utils.clean(des.getName()) == null)) {
			InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "Cannot add the Trusted IdP, it does not contain a valid " + name
					+ " Attribute Descriptor");
			throw fault;
		}
	}

	public synchronized TrustedIdP addTrustedIdP(TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException {
		buildDatabase();
		if (!determineTrustedIdPExistsByName(idp.getName())) {
			String name = validateAndGetName(idp);
			String displayName = validateAndGetDisplayName(idp);
			String authenticationServiceURL = validateAndGetAuthenticationServiceURL(idp);
			String authenticationServiceIdentity = validateAndGetAuthenticationServiceIdentity(idp);
			X509Certificate cert = validateAndGetCertificate(idp);
			String policyClass = validateAndGetPolicy(idp.getUserPolicyClass()).getClassName();
			verifyUserIdAttributeDescriptor(idp.getUserIdAttributeDescriptor());
			verifyFirstNameAttributeDescriptor(idp.getFirstNameAttributeDescriptor());
			verifyLastNameAttributeDescriptor(idp.getLastNameAttributeDescriptor());
			verifyEmailAttributeDescriptor(idp.getEmailAttributeDescriptor());

			String publish = "Y";

			if (!isCertificateUnique(idp.getIdPCertificate())) {
				InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "Cannot add the Trusted IdP, " + idp.getName()
						+ ", it does not contain a unique certificate.");
				throw fault;
			}
			Connection c = null;
			try {
				c = db.getConnection();
				PreparedStatement s = c.prepareStatement("INSERT INTO " + TRUST_MANAGER_TABLE + " SET " + NAME_FIELD + "= ?, " + DISPLAY_NAME_FIELD + "= ?, " + IDP_SUBJECT_FIELD + "= ?, "
						+ STATUS_FIELD + "= ?, " + POLICY_CLASS_FIELD + "= ?, " + IDP_CERTIFICATE_FIELD + "= ?, " + AUTHENTICATION_SERVICE_URL_FIELD + "= ?, " + AUTHENTICATION_SERVICE_IDENTITY_FIELD
						+ "= ?, " + PUBLISH_FIELD + "= ?, " + USER_ID_ATT_NS_FIELD + " = ?, " + USER_ID_ATT_NAME_FIELD + " = ?, " + FIRST_NAME_ATT_NS_FIELD + " = ?, " + FIRST_NAME_ATT_NAME_FIELD
						+ " = ?, " + LAST_NAME_ATT_NS_FIELD + " = ?, " + LAST_NAME_ATT_NAME_FIELD + " = ?, " + EMAIL_ATT_NS_FIELD + " = ?, " + EMAIL_ATT_NAME_FIELD + " = ?");

				s.setString(1, name);
				s.setString(2, displayName);
				s.setString(3, cert.getSubjectDN().toString());
				s.setString(4, idp.getStatus().value());
				s.setString(5, policyClass);
				s.setString(6, idp.getIdPCertificate());
				s.setString(7, authenticationServiceURL);
				s.setString(8, authenticationServiceIdentity);
				s.setString(9, publish);
				s.setString(10, idp.getUserIdAttributeDescriptor().getNamespaceURI());
				s.setString(11, idp.getUserIdAttributeDescriptor().getName());
				s.setString(12, idp.getFirstNameAttributeDescriptor().getNamespaceURI());
				s.setString(13, idp.getFirstNameAttributeDescriptor().getName());
				s.setString(14, idp.getLastNameAttributeDescriptor().getNamespaceURI());
				s.setString(15, idp.getLastNameAttributeDescriptor().getName());
				s.setString(16, idp.getEmailAttributeDescriptor().getNamespaceURI());
				s.setString(17, idp.getEmailAttributeDescriptor().getName());
				s.execute();
				idp.setId(db.getLastAutoId(c));
				s.close();
				if (idp.getAuthenticationMethod() != null) {
					for (int i = 0; i < idp.getAuthenticationMethod().size(); i++) {
						this.addAuthenticationMethod(idp.getId(), idp.getAuthenticationMethod().get(i));
					}
				}
			} catch (Exception e) {
				try {
					this.removeTrustedIdP(idp.getId());
				} catch (Exception ex) {
					logger.error(ex.getMessage(), ex);
				}
				logger.error(e.getMessage(), e);
				DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error adding the Trusted IdP " + name + ", an unexpected database error occurred.");
				FaultHelper.addMessage(fault, e.getMessage());
				throw fault;
			} finally {
				if (c != null) {
					db.releaseConnection(c);
				}
			}

		} else {
			InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "Cannot not add IdP, an IdP with the name " + idp.getName() + " already exists.");
			throw fault;
		}
		return idp;

	}

	private synchronized void addAuthenticationMethod(long id, SAMLAuthenticationMethod method) throws DorianInternalException {
		Connection c = null;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("INSERT INTO " + AUTH_METHODS_TABLE + " SET " + IDP_ID_FIELD + "= ?," + METHOD_FIELD + "= ?");
			s.setLong(1, id);
			s.setString(2, method.value());
			s.execute();
			s.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error adding the authentication method " + method.value() + " for the  Trusted IdP " + id
					+ ", an unexpected database error occurred.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			if (c != null) {
				db.releaseConnection(c);
			}
		}
	}

	public synchronized boolean determineTrustedIdPExistsByDN(String subject) throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		boolean exists = false;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select count(*) from " + TRUST_MANAGER_TABLE + " where " + IDP_SUBJECT_FIELD + "= ?");
			s.setString(1, subject);
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
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected Database Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		return exists;
	}

	public synchronized boolean determineTrustedIdPExistsByName(String name) throws DorianInternalException {
		buildDatabase();
		Connection c = null;
		boolean exists = false;
		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select count(*) from " + TRUST_MANAGER_TABLE + " where " + NAME_FIELD + "= ?");
			s.setString(1, name);
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
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected Database Error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
		return exists;
	}

	public synchronized void removeAllTrustedIdPs() throws DorianInternalException {
		buildDatabase();
		try {
			db.update("delete from " + TRUST_MANAGER_TABLE);
			db.update("delete from " + AUTH_METHODS_TABLE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected database error occurred.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public void setPublish(TrustedIdP idp, boolean publish) throws DorianInternalException, InvalidTrustedIdPException {
		TrustedIdP curr = this.getTrustedIdPById(idp.getId());

		boolean currentPublish = this.getPublish(idp);
		boolean needsUpdate = false;

		String setpublish = "Y";
		if (publish != currentPublish) {
			needsUpdate = true;
			if (publish) {
				setpublish = "Y";
			} else {
				setpublish = "N";
			}
		}

		Connection c = null;
		try {

			if (needsUpdate) {
				c = db.getConnection();
				PreparedStatement s = c.prepareStatement("UPDATE " + TRUST_MANAGER_TABLE + " SET " + PUBLISH_FIELD + "= ? " + "WHERE " + ID_FIELD + "= ?");

				s.setString(1, setpublish);
				s.setLong(2, curr.getId());
				s.execute();
				s.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error updating the Trusted IdP " + idp.getName()
					+ ", an unexpected database error occurred.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

	public boolean getPublish(TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException {
		buildDatabase();
		Connection c = null;

		try {
			c = db.getConnection();
			PreparedStatement s = c.prepareStatement("select " + PUBLISH_FIELD + " from " + TRUST_MANAGER_TABLE + " WHERE ID= ?");
			s.setLong(1, idp.getId());
			ResultSet rs = s.executeQuery();
			boolean publish = true;
			if (rs.next()) {
				String publishStr = rs.getString(PUBLISH_FIELD);
				if ((publishStr != null) && (publishStr.equalsIgnoreCase("Y"))) {
					publish = true;
				} else {
					publish = false;
				}
			} else {
				InvalidTrustedIdPException fault = FaultHelper.createFaultException(InvalidTrustedIdPException.class, "The Trusted IdP " + idp.getId() + " does not exist.");
				throw fault;
			}
			rs.close();
			s.close();
			return publish;
		} catch (InvalidTrustedIdPException f) {
			throw f;
		} catch (Exception e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Error obtaining the Trusted IdP " + idp.getId() + ", unexpected database error");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} finally {
			db.releaseConnection(c);
		}
	}

}