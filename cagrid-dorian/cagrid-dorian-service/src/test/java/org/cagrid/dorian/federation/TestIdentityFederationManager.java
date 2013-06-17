package org.cagrid.dorian.federation;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.cagrid.opensaml.SAMLAttribute;
import gov.nih.nci.cagrid.opensaml.SAMLAttributeStatement;
import gov.nih.nci.cagrid.opensaml.SAMLAuthenticationStatement;
import gov.nih.nci.cagrid.opensaml.SAMLNameIdentifier;
import gov.nih.nci.cagrid.opensaml.SAMLSubject;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.namespace.QName;

import junit.framework.TestCase;

import org.apache.xml.security.signature.XMLSignature;
import org.cagrid.dorian.ca.impl.CertificateAuthorityManager;
import org.cagrid.dorian.common.AuditConstants;
import org.cagrid.dorian.common.CommonUtils;
import org.cagrid.dorian.common.Lifetime;
import org.cagrid.dorian.common.SAMLConstants;
import org.cagrid.dorian.federation.impl.AccountPolicy;
import org.cagrid.dorian.federation.impl.AutoApprovalPolicy;
import org.cagrid.dorian.federation.impl.FederationDefaults;
import org.cagrid.dorian.federation.impl.FederationUtils;
import org.cagrid.dorian.federation.impl.IdentityFederationManager;
import org.cagrid.dorian.federation.impl.IdentityFederationProperties;
import org.cagrid.dorian.federation.impl.ManualApprovalPolicy;
import org.cagrid.dorian.federation.impl.UserManager;
import org.cagrid.dorian.ifs.CertificateLifetime;
import org.cagrid.dorian.ifs.DateRange;
import org.cagrid.dorian.ifs.FederationAudit;
import org.cagrid.dorian.ifs.FederationAuditFilter;
import org.cagrid.dorian.ifs.FederationAuditRecord;
import org.cagrid.dorian.ifs.GridUser;
import org.cagrid.dorian.ifs.GridUserFilter;
import org.cagrid.dorian.ifs.GridUserRecord;
import org.cagrid.dorian.ifs.GridUserSearchCriteria;
import org.cagrid.dorian.ifs.GridUserStatus;
import org.cagrid.dorian.ifs.HostCertificateFilter;
import org.cagrid.dorian.ifs.HostCertificateRecord;
import org.cagrid.dorian.ifs.HostCertificateRequest;
import org.cagrid.dorian.ifs.HostCertificateStatus;
import org.cagrid.dorian.ifs.HostCertificateUpdate;
import org.cagrid.dorian.ifs.HostRecord;
import org.cagrid.dorian.ifs.HostSearchCriteria;
import org.cagrid.dorian.ifs.SAMLAttributeDescriptor;
import org.cagrid.dorian.ifs.SAMLAuthenticationMethod;
import org.cagrid.dorian.ifs.TrustedIdP;
import org.cagrid.dorian.ifs.TrustedIdPStatus;
import org.cagrid.dorian.ifs.UserCertificateFilter;
import org.cagrid.dorian.ifs.UserCertificateRecord;
import org.cagrid.dorian.ifs.UserCertificateStatus;
import org.cagrid.dorian.ifs.UserCertificateUpdate;
import org.cagrid.dorian.policy.HostCertificateRenewalPolicy;
import org.cagrid.dorian.policy.SearchPolicyType;
import org.cagrid.dorian.service.CertificateSignatureAlgorithm;
import org.cagrid.dorian.service.DorianConstants;
import org.cagrid.dorian.service.PropertyManager;
import org.cagrid.dorian.types.DorianInternalException;
import org.cagrid.dorian.types.InvalidAssertionException;
import org.cagrid.dorian.types.InvalidHostCertificateException;
import org.cagrid.dorian.types.PermissionDeniedException;
import org.cagrid.dorian.types.UserPolicyException;
import org.cagrid.gaards.dorian.test.CA;
import org.cagrid.gaards.dorian.test.Credential;
<<<<<<< HEAD
=======
import org.cagrid.gaards.dorian.test.Constants;
>>>>>>> 998f1cc0c5eff2975cb156f9e8f70b24fb3365f3
import org.cagrid.gaards.dorian.test.Utils;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.tools.database.Database;
import org.cagrid.tools.events.EventManager;
import org.globus.gsi.GlobusCredential;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public class TestIdentityFederationManager extends TestCase {

	private static final int MIN_NAME_LENGTH = 4;

	private static final int MAX_NAME_LENGTH = 50;

	private static final int SHORT_PROXY_VALID = 10;

	private static final int SHORT_CREDENTIALS_VALID = 35;

	public final static String INITIAL_ADMIN = "admin";

	private Database db;

	private CertificateAuthorityManager ca;

	private CA memoryCA;

	private PropertyManager props;

	private EventManager eventManager;

	public void testUserSearchAdmin() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			conf.setUserSearchPolicy(SearchPolicyType.ADMIN.value());
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			GridUserRecord userRecord = toUserRecord(usr);
			GridUserSearchCriteria criteria = new GridUserSearchCriteria();
			criteria.setIdentity(usr.getGridId());
			try {
				ifs.userSearch(DorianConstants.ANONYMOUS_CALLER, criteria);
				fail("Should have failed.");
			} catch (PermissionDeniedException f) {

			}
			performAndValidateSingleAudit(ifs, adminGridId, DorianConstants.ANONYMOUS_CALLER, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED);

			try {
				ifs.userSearch(usr.getGridId(), criteria);
				fail("Should have failed.");
			} catch (PermissionDeniedException f) {

			}
			performAndValidateSingleAudit(ifs, adminGridId, usr.getGridId(), AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED);

			List<GridUserRecord> users = ifs.userSearch(adminGridId, criteria);
			assertEquals(1, users.size());
			assertEquals(userRecord, users.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testHostSearchAdminRequired() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			conf.setHostSearchPolicy(SearchPolicyType.ADMIN.value());
			conf.setAutoHostCertificateApproval(true);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			String host = "myhost.example.com";
			HostCertificateRequest req = getHostCertificateRequest(host);
			HostCertificateRecord record = ifs.requestHostCertificate(usr.getGridId(), req);

			HostSearchCriteria criteria = new HostSearchCriteria();
			criteria.setHostname(host);
			try {
				ifs.hostSearch(DorianConstants.ANONYMOUS_CALLER, criteria);
				fail("Should have failed.");
			} catch (PermissionDeniedException f) {

			}
			performAndValidateSingleAudit(ifs, adminGridId, DorianConstants.ANONYMOUS_CALLER, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED);

			try {
				ifs.hostSearch(usr.getGridId(), criteria);
				fail("Should have failed.");
			} catch (PermissionDeniedException f) {

			}
			performAndValidateSingleAudit(ifs, adminGridId, usr.getGridId(), AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED);

			List<HostRecord> list = ifs.hostSearch(adminGridId, criteria);
			assertEquals(record.getHost(), list.get(0).getHostname());
			assertEquals(record.getSubject(), list.get(0).getHostCertificateSubject());
			assertEquals(CommonUtils.subjectToIdentity(record.getSubject()), list.get(0).getIdentity());
			assertEquals(usr.getGridId(), list.get(0).getOwner());
			assertEquals(usr.getEmail(), list.get(0).getOwnerEmail());
			assertEquals(usr.getFirstName(), list.get(0).getOwnerFirstName());
			assertEquals(usr.getLastName(), list.get(0).getOwnerLastName());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testHostSearchAuthenticationRequired() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			conf.setHostSearchPolicy(SearchPolicyType.AUTHENTICATED.value());
			conf.setAutoHostCertificateApproval(true);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			String host = "myhost.example.com";
			HostCertificateRequest req = getHostCertificateRequest(host);
			HostCertificateRecord record = ifs.requestHostCertificate(usr.getGridId(), req);

			HostSearchCriteria criteria = new HostSearchCriteria();
			criteria.setHostname(host);
			try {
				ifs.hostSearch(DorianConstants.ANONYMOUS_CALLER, criteria);
				fail("Should have failed.");
			} catch (PermissionDeniedException f) {

			}
			performAndValidateSingleAudit(ifs, adminGridId, DorianConstants.ANONYMOUS_CALLER, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED);

			List<HostRecord> list = ifs.hostSearch(usr.getGridId(), criteria);
			assertEquals(1, list.size());
			assertEquals(record.getHost(), list.get(0).getHostname());
			assertEquals(record.getSubject(), list.get(0).getHostCertificateSubject());
			assertEquals(CommonUtils.subjectToIdentity(record.getSubject()), list.get(0).getIdentity());
			assertEquals(usr.getGridId(), list.get(0).getOwner());
			assertEquals(usr.getEmail(), list.get(0).getOwnerEmail());
			assertEquals(usr.getFirstName(), list.get(0).getOwnerFirstName());
			assertEquals(usr.getLastName(), list.get(0).getOwnerLastName());
			list = ifs.hostSearch(adminGridId, criteria);
			assertEquals(record.getHost(), list.get(0).getHostname());
			assertEquals(record.getSubject(), list.get(0).getHostCertificateSubject());
			assertEquals(CommonUtils.subjectToIdentity(record.getSubject()), list.get(0).getIdentity());
			assertEquals(usr.getGridId(), list.get(0).getOwner());
			assertEquals(usr.getEmail(), list.get(0).getOwnerEmail());
			assertEquals(usr.getFirstName(), list.get(0).getOwnerFirstName());
			assertEquals(usr.getLastName(), list.get(0).getOwnerLastName());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testHostSearchPublic() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			conf.setHostSearchPolicy(SearchPolicyType.PUBLIC.value());
			conf.setAutoHostCertificateApproval(true);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			String host = "myhost.example.com";
			HostCertificateRequest req = getHostCertificateRequest(host);
			HostCertificateRecord record = ifs.requestHostCertificate(usr.getGridId(), req);

			HostSearchCriteria criteria = new HostSearchCriteria();
			criteria.setHostname(host);

			List<HostRecord> list = ifs.hostSearch(DorianConstants.ANONYMOUS_CALLER, criteria);
			assertEquals(1, list.size());
			assertEquals(record.getHost(), list.get(0).getHostname());
			assertEquals(record.getSubject(), list.get(0).getHostCertificateSubject());
			assertEquals(CommonUtils.subjectToIdentity(record.getSubject()), list.get(0).getIdentity());
			assertEquals(usr.getGridId(), list.get(0).getOwner());
			assertEquals(usr.getEmail(), list.get(0).getOwnerEmail());
			assertEquals(usr.getFirstName(), list.get(0).getOwnerFirstName());
			assertEquals(usr.getLastName(), list.get(0).getOwnerLastName());

			list = ifs.hostSearch(usr.getGridId(), criteria);
			assertEquals(1, list.size());
			assertEquals(record.getHost(), list.get(0).getHostname());
			assertEquals(record.getSubject(), list.get(0).getHostCertificateSubject());
			assertEquals(CommonUtils.subjectToIdentity(record.getSubject()), list.get(0).getIdentity());
			assertEquals(usr.getGridId(), list.get(0).getOwner());
			assertEquals(usr.getEmail(), list.get(0).getOwnerEmail());
			assertEquals(usr.getFirstName(), list.get(0).getOwnerFirstName());
			assertEquals(usr.getLastName(), list.get(0).getOwnerLastName());

			list = ifs.hostSearch(adminGridId, criteria);
			assertEquals(record.getHost(), list.get(0).getHostname());
			assertEquals(record.getSubject(), list.get(0).getHostCertificateSubject());
			assertEquals(CommonUtils.subjectToIdentity(record.getSubject()), list.get(0).getIdentity());
			assertEquals(usr.getGridId(), list.get(0).getOwner());
			assertEquals(usr.getEmail(), list.get(0).getOwnerEmail());
			assertEquals(usr.getFirstName(), list.get(0).getOwnerFirstName());
			assertEquals(usr.getLastName(), list.get(0).getOwnerLastName());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testUserSearchAuthenticationRequired() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			conf.setUserSearchPolicy(SearchPolicyType.AUTHENTICATED.value());
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			GridUserRecord userRecord = toUserRecord(usr);
			GridUserSearchCriteria criteria = new GridUserSearchCriteria();
			criteria.setIdentity(usr.getGridId());
			try {
				ifs.userSearch(DorianConstants.ANONYMOUS_CALLER, criteria);
				fail("Should have failed.");
			} catch (PermissionDeniedException f) {

			}
			performAndValidateSingleAudit(ifs, adminGridId, DorianConstants.ANONYMOUS_CALLER, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED);

			List<GridUserRecord> users = ifs.userSearch(usr.getGridId(), criteria);
			assertEquals(1, users.size());
			assertEquals(userRecord, users.get(0));

			users = ifs.userSearch(adminGridId, criteria);
			assertEquals(1, users.size());
			assertEquals(userRecord, users.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testUserSearchPublic() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			conf.setUserSearchPolicy(SearchPolicyType.AUTHENTICATED.value());
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			GridUserRecord userRecord = toUserRecord(usr);
			GridUserSearchCriteria criteria = new GridUserSearchCriteria();
			criteria.setIdentity(usr.getGridId());

			List<GridUserRecord> users = ifs.userSearch(SearchPolicyType.PUBLIC.value(), criteria);
			assertEquals(1, users.size());
			assertEquals(userRecord, users.get(0));

			users = ifs.userSearch(usr.getGridId(), criteria);
			assertEquals(1, users.size());
			assertEquals(userRecord, users.get(0));

			users = ifs.userSearch(adminGridId, criteria);
			assertEquals(1, users.size());
			assertEquals(userRecord, users.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private GridUserRecord toUserRecord(GridUser usr) {
		GridUserRecord r = new GridUserRecord();
		r.setIdentity(usr.getGridId());
		r.setFirstName(usr.getFirstName());
		r.setLastName(usr.getLastName());
		r.setEmail(usr.getEmail());
		return r;
	}

	public void testRequestHostCertificateManualApproval() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			String host = "localhost";
			HostCertificateRequest req = getHostCertificateRequest(host);
			HostCertificateRecord record = ifs.requestHostCertificate(usr.getGridId(), req);
			assertEquals(HostCertificateStatus.PENDING, record.getStatus());
			assertEquals(null, record.getCertificate());
			String hostId = String.valueOf(record.getId());
			performAndValidateSingleAudit(ifs, adminGridId, hostId, usr.getGridId(), FederationAudit.HOST_CERTIFICATE_REQUESTED);

			String subject = org.cagrid.dorian.service.util.Utils.getHostCertificateSubject(ca.getDefaultCertificateAuthority().getCACertificate(), host);
			record = ifs.approveHostCertificate(adminGridId, record.getId());
			assertEquals(HostCertificateStatus.ACTIVE, record.getStatus());
			;
			assertEquals(subject, record.getSubject());
			assertEquals(subject, CertUtil.loadCertificate(record.getCertificate().getCertificateAsString()).getSubjectDN().getName());
			performAndValidateSingleAudit(ifs, adminGridId, hostId, adminGridId, FederationAudit.HOST_CERTIFICATE_APPROVED);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testRequestHostCertificateAutoApproval() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(true);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			String host = "localhost";
			HostCertificateRequest req = getHostCertificateRequest(host);
			HostCertificateRecord record = ifs.requestHostCertificate(usr.getGridId(), req);
			String subject = org.cagrid.dorian.service.util.Utils.getHostCertificateSubject(ca.getDefaultCertificateAuthority().getCACertificate(), host);
			assertEquals(HostCertificateStatus.ACTIVE, record.getStatus());
			;
			assertEquals(subject, record.getSubject());
			assertEquals(subject, CertUtil.loadCertificate(record.getCertificate().getCertificateAsString()).getSubjectDN().getName());
			String hostId = String.valueOf(record.getId());
			performAndValidateSingleAudit(ifs, adminGridId, hostId, usr.getGridId(), FederationAudit.HOST_CERTIFICATE_REQUESTED);
			performAndValidateSingleAudit(ifs, adminGridId, hostId, AuditConstants.SYSTEM_ID, FederationAudit.HOST_CERTIFICATE_APPROVED);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testRequestHostCertificateInvalidUser() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			createUser(ifs, adminGridId, idp, "user");
			String host = "localhost";
			HostCertificateRequest req = getHostCertificateRequest(host);
			try {
				ifs.requestHostCertificate("bad user", req);
				fail("Should have failed.");
			} catch (PermissionDeniedException f) {

			}
			performAndValidateSingleAudit(ifs, adminGridId, "bad user", AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void testApproveHostCertificateInvalidUser() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			String host = "localhost";
			HostCertificateRequest req = getHostCertificateRequest(host);
			HostCertificateRecord record = ifs.requestHostCertificate(usr.getGridId(), req);
			assertEquals(HostCertificateStatus.PENDING, record.getStatus());
			assertEquals(null, record.getCertificate());

			String hostId = String.valueOf(record.getId());
			performAndValidateSingleAudit(ifs, adminGridId, hostId, usr.getGridId(), FederationAudit.HOST_CERTIFICATE_REQUESTED);

			try {
				ifs.approveHostCertificate("bad subject", record.getId());
			} catch (PermissionDeniedException f) {

			}

			performAndValidateSingleAudit(ifs, adminGridId, "bad subject", AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED);
			assertEquals(0, ifs.performAudit(adminGridId, getHostCertificatedApprovedAuditingFilter(hostId, usr.getGridId())).size());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testFindHostCertificates() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(true);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			String subjectPrefix = org.cagrid.dorian.service.util.Utils.getHostCertificateSubjectPrefix(ca.getDefaultCertificateAuthority().getCACertificate());
			String hostPrefix = "localhost";
			int total = 3;

			for (int i = 0; i < total; i++) {
				HostCertificateRequest req = getHostCertificateRequest(hostPrefix + i);
				String hostId = String.valueOf(ifs.requestHostCertificate(usr.getGridId(), req).getId());
				performAndValidateSingleAudit(ifs, adminGridId, hostId, usr.getGridId(), FederationAudit.HOST_CERTIFICATE_REQUESTED);
				performAndValidateSingleAudit(ifs, adminGridId, hostId, AuditConstants.SYSTEM_ID, FederationAudit.HOST_CERTIFICATE_APPROVED);
			}

			// Find by Subject;
			HostCertificateFilter f1 = new HostCertificateFilter();
			f1.setSubject(subjectPrefix);
			assertEquals(total, ifs.findHostCertificates(adminGridId, f1).length);
			for (int i = 0; i < total; i++) {
				String subject = org.cagrid.dorian.service.util.Utils.getHostCertificateSubject(ca.getDefaultCertificateAuthority().getCACertificate(), hostPrefix + i);
				f1.setSubject(subject);
				HostCertificateRecord[] r = ifs.findHostCertificates(adminGridId, f1);
				assertEquals(1, r.length);
				assertEquals(subject, r[0].getSubject());
			}

			// Find by host;
			HostCertificateFilter f2 = new HostCertificateFilter();
			f2.setHost(hostPrefix);
			assertEquals(total, ifs.findHostCertificates(adminGridId, f2).length);
			for (int i = 0; i < total; i++) {
				String host = hostPrefix + i;
				f2.setHost(host);
				HostCertificateRecord[] r = ifs.findHostCertificates(adminGridId, f2);
				assertEquals(1, r.length);
				assertEquals(host, r[0].getHost());
			}

			// Find by Owner;
			HostCertificateFilter f3 = new HostCertificateFilter();
			f3.setOwner(usr.getGridId());
			assertEquals(total, ifs.findHostCertificates(adminGridId, f3).length);

			// Find by host;
			HostCertificateFilter f4 = new HostCertificateFilter();
			f4.setStatus(HostCertificateStatus.ACTIVE);
			assertEquals(total, ifs.findHostCertificates(adminGridId, f4).length);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testFindHostCertificatesInvalidUser() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			createUser(ifs, adminGridId, idp, "user");
			try {
				ifs.findHostCertificates("bad user", new HostCertificateFilter());
				fail("Should have failed.");
			} catch (PermissionDeniedException f) {

			}

			performAndValidateSingleAudit(ifs, adminGridId, "bad user", AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testUpdateHostCertificate() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(true);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			String host = "localhost";
			HostCertificateRequest req = getHostCertificateRequest(host);
			HostCertificateRecord record = ifs.requestHostCertificate(usr.getGridId(), req);
			String subject = org.cagrid.dorian.service.util.Utils.getHostCertificateSubject(ca.getDefaultCertificateAuthority().getCACertificate(), host);
			assertEquals(HostCertificateStatus.ACTIVE, record.getStatus());
			;
			assertEquals(subject, record.getSubject());
			assertEquals(subject, CertUtil.loadCertificate(record.getCertificate().getCertificateAsString()).getSubjectDN().getName());
			String hostId = String.valueOf(record.getId());
			performAndValidateSingleAudit(ifs, adminGridId, hostId, usr.getGridId(), FederationAudit.HOST_CERTIFICATE_REQUESTED);
			performAndValidateSingleAudit(ifs, adminGridId, hostId, AuditConstants.SYSTEM_ID, FederationAudit.HOST_CERTIFICATE_APPROVED);

			HostCertificateUpdate update = new HostCertificateUpdate();
			update.setId(record.getId());
			update.setStatus(HostCertificateStatus.SUSPENDED);
			ifs.updateHostCertificateRecord(adminGridId, update);
			HostCertificateFilter f = new HostCertificateFilter();
			f.setId(BigInteger.valueOf(record.getId()));
			HostCertificateRecord[] r = ifs.findHostCertificates(adminGridId, f);
			assertEquals(1, r.length);
			assertEquals(HostCertificateStatus.SUSPENDED, r[0].getStatus());
			performAndValidateSingleAudit(ifs, adminGridId, hostId, adminGridId, FederationAudit.HOST_CERTIFICATE_UPDATED);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testUpdateHostCertificatesInvalidUser() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			createUser(ifs, adminGridId, idp, "user");
			try {
				ifs.updateHostCertificateRecord("bad user", new HostCertificateUpdate());
				fail("Should have failed.");
			} catch (PermissionDeniedException f) {

			}

			performAndValidateSingleAudit(ifs, adminGridId, "bad user", AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testUpdateInvalidOwner() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			String host = "localhost";
			HostCertificateRequest req = getHostCertificateRequest(host);
			HostCertificateRecord record = ifs.requestHostCertificate(usr.getGridId(), req);
			String hostId = String.valueOf(record.getId());
			performAndValidateSingleAudit(ifs, adminGridId, hostId, usr.getGridId(), FederationAudit.HOST_CERTIFICATE_REQUESTED);
			try {
				HostCertificateUpdate update = new HostCertificateUpdate();
				update.setId(record.getId());
				update.setOwner("invalid user");
				ifs.updateHostCertificateRecord(adminGridId, update);
				fail("Should have failed.");
			} catch (InvalidHostCertificateException f) {

			}

			try {
				HostCertificateUpdate update = new HostCertificateUpdate();
				update.setId(record.getId());
				update.setOwner("");
				ifs.updateHostCertificateRecord(adminGridId, update);
				fail("Should have failed.");
			} catch (InvalidHostCertificateException f) {

			}

			GridUser usr2 = createUser(ifs, adminGridId, idp, "user2");
			usr2.setUserStatus(GridUserStatus.SUSPENDED);
			ifs.updateUser(adminGridId, usr2);

			try {
				HostCertificateUpdate update = new HostCertificateUpdate();
				update.setId(record.getId());
				update.setOwner(usr2.getGridId());
				ifs.updateHostCertificateRecord(adminGridId, update);
				fail("Should have failed.");
			} catch (InvalidHostCertificateException f) {

			}

			usr2.setUserStatus(GridUserStatus.ACTIVE);
			ifs.updateUser(adminGridId, usr2);

			HostCertificateUpdate update = new HostCertificateUpdate();
			update.setId(record.getId());
			update.setOwner(usr2.getGridId());
			ifs.updateHostCertificateRecord(adminGridId, update);
			HostCertificateFilter f = new HostCertificateFilter();
			f.setId(BigInteger.valueOf(record.getId()));
			assertEquals(usr2.getGridId(), ifs.findHostCertificates(adminGridId, f)[0].getOwner());
			performAndValidateSingleAudit(ifs, adminGridId, hostId, adminGridId, FederationAudit.HOST_CERTIFICATE_UPDATED);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testGetHostCertificatesForCaller() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(true);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			String hostPrefix = "localhost";
			int total = 3;

			for (int i = 0; i < total; i++) {
				HostCertificateRequest req = getHostCertificateRequest(hostPrefix + i);
				String hostId = String.valueOf(ifs.requestHostCertificate(usr.getGridId(), req).getId());
				performAndValidateSingleAudit(ifs, adminGridId, hostId, usr.getGridId(), FederationAudit.HOST_CERTIFICATE_REQUESTED);
				performAndValidateSingleAudit(ifs, adminGridId, hostId, AuditConstants.SYSTEM_ID, FederationAudit.HOST_CERTIFICATE_APPROVED);
			}

			HostCertificateRecord[] r = ifs.getHostCertificatesForCaller(usr.getGridId());
			assertEquals(total, r.length);
			for (int i = 0; i < total; i++) {
				String host = hostPrefix + i;
				boolean found = false;
				for (int j = 0; j < r.length; j++) {
					if (host.equals(r[j].getHost())) {
						found = true;
						break;
					}
				}
				if (!found) {
					fail("A host certificate that was expected was not found.");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void testGetHostCertificatesForCallerInvalidUser() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			createUser(ifs, adminGridId, idp, "user");
			try {
				ifs.getHostCertificatesForCaller("bad user");
				fail("Should have failed.");
			} catch (PermissionDeniedException f) {

			}
			performAndValidateSingleAudit(ifs, adminGridId, "bad user", AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testHostCeritifcateStatusAfterUserRemoval() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			String host = "localhost1";
			HostCertificateRequest req = getHostCertificateRequest(host);
			HostCertificateRecord record = ifs.requestHostCertificate(usr.getGridId(), req);
			String hostId = String.valueOf(record.getId());
			assertEquals(HostCertificateStatus.PENDING, record.getStatus());
			assertEquals(null, record.getCertificate());
			String subject = org.cagrid.dorian.service.util.Utils.getHostCertificateSubject(ca.getDefaultCertificateAuthority().getCACertificate(), host);

			performAndValidateSingleAudit(ifs, adminGridId, hostId, usr.getGridId(), FederationAudit.HOST_CERTIFICATE_REQUESTED);

			record = ifs.approveHostCertificate(adminGridId, record.getId());

			assertEquals(HostCertificateStatus.ACTIVE, record.getStatus());
			assertEquals(subject, record.getSubject());
			assertEquals(subject, CertUtil.loadCertificate(record.getCertificate().getCertificateAsString()).getSubjectDN().getName());

			performAndValidateSingleAudit(ifs, adminGridId, hostId, adminGridId, FederationAudit.HOST_CERTIFICATE_APPROVED);

			String host2 = "localhost2";
			HostCertificateRequest req2 = getHostCertificateRequest(host2);
			HostCertificateRecord record2 = ifs.requestHostCertificate(usr.getGridId(), req2);
			assertEquals(HostCertificateStatus.PENDING, record2.getStatus());
			assertEquals(null, record2.getCertificate());
			String hostId2 = String.valueOf(record.getId());
			performAndValidateSingleAudit(ifs, adminGridId, hostId2, usr.getGridId(), FederationAudit.HOST_CERTIFICATE_REQUESTED);

			ifs.removeUser(adminGridId, usr);

			performAndValidateSingleAudit(ifs, adminGridId, usr.getGridId(), adminGridId, FederationAudit.ACCOUNT_REMOVED);

			HostCertificateFilter f = new HostCertificateFilter();
			f.setId(BigInteger.valueOf(record.getId()));

			HostCertificateRecord[] r = ifs.findHostCertificates(adminGridId, f);
			assertEquals(1, r.length);
			assertEquals(HostCertificateStatus.COMPROMISED, r[0].getStatus());

			performAndValidateSingleAudit(ifs, adminGridId, hostId, AuditConstants.SYSTEM_ID, FederationAudit.HOST_CERTIFICATE_UPDATED);

			f.setId(BigInteger.valueOf(record2.getId()));
			HostCertificateRecord[] r2 = ifs.findHostCertificates(adminGridId, f);
			assertEquals(1, r2.length);
			assertEquals(HostCertificateStatus.REJECTED, r2[0].getStatus());

			performAndValidateSingleAudit(ifs, adminGridId, hostId2, AuditConstants.SYSTEM_ID, FederationAudit.HOST_CERTIFICATE_UPDATED);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void testAdminAcessAfterUserRemoval() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");

			ifs.addAdmin(adminGridId, usr.getGridId());

			performAndValidateSingleAudit(ifs, adminGridId, usr.getGridId(), adminGridId, FederationAudit.ADMIN_ADDED);

			ifs.removeUser(adminGridId, usr);

			performAndValidateSingleAudit(ifs, adminGridId, usr.getGridId(), adminGridId, FederationAudit.ACCOUNT_REMOVED);

			performAndValidateSingleAudit(ifs, adminGridId, usr.getGridId(), AuditConstants.SYSTEM_ID, FederationAudit.ADMIN_REMOVED);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void testGetCRL() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf();
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);

			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			IdPContainer idp2 = this.getTrustedIdpAutoApprove("My IdP2");
			ifs.addTrustedIdP(adminGridId, idp2.getIdp());
			String hostPrefix = "myhost";
			int hostCount = 1;
			int totalUsers = 3;
			int userHostCerts = 2;
			int total = (totalUsers) + (totalUsers * userHostCerts);

			// Create users and host certificates
			List<UserContainer> list = new ArrayList<UserContainer>();
			for (int i = 0; i < totalUsers; i++) {
				String uid = "user" + i;
				KeyPair pair = KeyUtil.generateRSAKeyPair1024();
				PublicKey publicKey = pair.getPublic();
				CertificateLifetime lifetime = getLifetime();
				SAMLAssertion saml = getSAMLAssertion(uid, idp2);
				X509Certificate cert = ifs.requestUserCertificate(saml, publicKey, lifetime, CertificateSignatureAlgorithm.SHA2);
				String expectedIdentity = CommonUtils.subjectToIdentity(UserManager.getUserSubject(ifs.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp2.getIdp(),
						uid));

				checkCertificate(expectedIdentity, lifetime, pair.getPrivate(), cert);
				GridUserFilter f1 = new GridUserFilter();
				f1.setIdPId(idp2.getIdp().getId());
				f1.setUID(uid);
				GridUser[] users = ifs.findUsers(adminGridId, f1);
				assertEquals(1, users.length);
				UserContainer usr = new UserContainer(users[0]);
				list.add(usr);
				for (int j = 0; j < userHostCerts; j++) {
					usr.addHostCertificate(createAndSubmitHostCert(ifs, conf, adminGridId, usr.getUser().getGridId(), hostPrefix + hostCount));
					hostCount++;
				}
			}

			X509CRL crl = ifs.getCRL(CertificateSignatureAlgorithm.SHA2);
			assertEquals(null, crl.getRevokedCertificates());

			// Suspend IDP
			idp2.getIdp().setStatus(TrustedIdPStatus.SUSPENDED);
			ifs.updateTrustedIdP(adminGridId, idp2.getIdp());
			crl = ifs.getCRL(CertificateSignatureAlgorithm.SHA2);
			assertEquals(total, crl.getRevokedCertificates().size());
			for (int i = 0; i < list.size(); i++) {
				UserCertificateFilter f = new UserCertificateFilter();
				f.setGridIdentity(list.get(i).getUser().getGridId());
				DateRange now = new DateRange();
				now.setStartDate(new GregorianCalendar());
				now.setEndDate(new GregorianCalendar());
				f.setDateRange(now);
				List<UserCertificateRecord> records = ifs.findUserCertificateRecords(adminGridId, f);
				for (int j = 0; j < records.size(); j++) {
					X509Certificate cert = CertUtil.loadCertificate(records.get(j).getCertificate().getCertificateAsString());
					assertNotNull(crl.getRevokedCertificate(cert));
				}
				List<HostCertificateRecord> hosts = list.get(i).getHostCertificates();
				for (int j = 0; j < hosts.size(); j++) {
					assertNotNull(crl.getRevokedCertificate(CertUtil.loadCertificate(hosts.get(j).getCertificate().getCertificateAsString())));
				}
			}

			idp2.getIdp().setStatus(TrustedIdPStatus.ACTIVE);
			ifs.updateTrustedIdP(adminGridId, idp2.getIdp());

			crl = ifs.getCRL(CertificateSignatureAlgorithm.SHA2);
			assertEquals(null, crl.getRevokedCertificates());
			for (int i = 0; i < list.size(); i++) {
				UserCertificateFilter f = new UserCertificateFilter();
				f.setGridIdentity(list.get(i).getUser().getGridId());
				DateRange now = new DateRange();
				now.setStartDate(new GregorianCalendar());
				now.setEndDate(new GregorianCalendar());
				f.setDateRange(now);
				List<UserCertificateRecord> records = ifs.findUserCertificateRecords(adminGridId, f);
				for (int j = 0; j < records.size(); j++) {
					X509Certificate cert = CertUtil.loadCertificate(records.get(j).getCertificate().getCertificateAsString());
					assertNull(crl.getRevokedCertificate(cert));
				}
				List<HostCertificateRecord> hosts = list.get(i).getHostCertificates();
				for (int j = 0; j < hosts.size(); j++) {
					assertNull(crl.getRevokedCertificate(CertUtil.loadCertificate(hosts.get(j).getCertificate().getCertificateAsString())));
				}
			}

			this.validateCRLOnDisabledUserStatus(ifs, list, GridUserStatus.SUSPENDED, adminGridId, userHostCerts);

			assertTrue(userHostCerts >= 1);

			X509Certificate oldHostCert = CertUtil.loadCertificate(list.get(0).getHostCertificates().get(0).getCertificate().getCertificateAsString());
			HostCertificateRecord hcr = ifs.renewHostCertificate(adminGridId, list.get(0).getHostCertificates().get(0).getId());
			assertEquals(list.get(0).getHostCertificates().get(0).getId(), hcr.getId());
			X509Certificate newHostCert = CertUtil.loadCertificate(hcr.getCertificate().getCertificateAsString());
			crl = ifs.getCRL(CertificateSignatureAlgorithm.SHA2);
			int alreadyRevokedCertificates = 1;
			assertNotNull(crl.getRevokedCertificate(oldHostCert));
			assertNull(crl.getRevokedCertificate(newHostCert));

			alreadyRevokedCertificates = this.validateCRLOnDisabledHostStatus(ifs, list, HostCertificateStatus.SUSPENDED, adminGridId, userHostCerts, alreadyRevokedCertificates);
			alreadyRevokedCertificates = this.validateCRLOnDisabledHostStatus(ifs, list, HostCertificateStatus.COMPROMISED, adminGridId, userHostCerts, alreadyRevokedCertificates);

			// Test compromising user certificates
			for (int i = 0; i < list.size(); i++) {
				UserContainer usr = list.get(i);
				KeyPair pair = KeyUtil.generateRSAKeyPair1024();
				PublicKey publicKey = pair.getPublic();
				CertificateLifetime lifetime = getLifetime();
				X509Certificate cert = ifs.requestUserCertificate(getSAMLAssertion(usr.getUser().getUID(), idp2), publicKey, lifetime, CertificateSignatureAlgorithm.SHA2);
				String expectedIdentity = CommonUtils.subjectToIdentity(UserManager.getUserSubject(ifs.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp2.getIdp(),
						usr.getUser().getUID()));

				checkCertificate(expectedIdentity, lifetime, pair.getPrivate(), cert);

				crl = ifs.getCRL(CertificateSignatureAlgorithm.SHA2);
				assertEquals(alreadyRevokedCertificates, crl.getRevokedCertificates().size());
				assertNull(crl.getRevokedCertificate(cert));
				UserCertificateUpdate u = new UserCertificateUpdate();
				u.setSerialNumber(cert.getSerialNumber().longValue());
				u.setStatus(UserCertificateStatus.COMPROMISED);
				ifs.updateUserCertificateRecord(adminGridId, u);
				alreadyRevokedCertificates = alreadyRevokedCertificates + 1;
				crl = ifs.getCRL(CertificateSignatureAlgorithm.SHA2);
				assertEquals(alreadyRevokedCertificates, crl.getRevokedCertificates().size());
				assertNotNull(crl.getRevokedCertificate(cert));
				UserCertificateFilter f = new UserCertificateFilter();
				f.setGridIdentity(usr.getUser().getGridId());
				List<UserCertificateRecord> records = ifs.findUserCertificateRecords(adminGridId, f);
				assertEquals(2, records.size());
				for (int j = 0; j < records.size(); j++) {
					X509Certificate cert2 = CertUtil.loadCertificate(records.get(j).getCertificate().getCertificateAsString());
					if (records.get(j).getSerialNumber() == cert.getSerialNumber().longValue()) {
						assertNotNull(crl.getRevokedCertificate(cert2));
						usr.addCompromisedUserCertificate(cert2);
					} else {
						assertNull(crl.getRevokedCertificate(cert2));
						usr.addUserCertificate(cert2);
					}
				}
			}

			// Test deleting users

			for (int i = 0; i < list.size(); i++) {
				UserContainer usr = list.get(i);
				ifs.removeUser(adminGridId, usr.getUser());
				crl = ifs.getCRL(CertificateSignatureAlgorithm.SHA2);
				List<X509Certificate> compromisedUserCerts = usr.getCompromisedUserCerts();
				for (int j = 0; j < compromisedUserCerts.size(); j++) {
					assertNotNull(crl.getRevokedCertificate(compromisedUserCerts.get(j)));
				}

				List<X509Certificate> userCerts = usr.getUserCerts();
				for (int j = 0; j < userCerts.size(); j++) {
					assertNull(crl.getRevokedCertificate(userCerts.get(j)));
				}

				List<HostCertificateRecord> hostCerts = usr.getHostCertificates();
				for (int j = 0; j < compromisedUserCerts.size(); j++) {
					X509Certificate hostCert = CertUtil.loadCertificate(hostCerts.get(j).getCertificate().getCertificateAsString());
					assertNotNull(crl.getRevokedCertificate(hostCert));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testFindRemoveUpdateUsers() {
		IdentityFederationManager ifs = null;
		try {
			int times = 3;
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf();
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String uidPrefix = "user";
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			int ucount = 1;
			for (int i = 0; i < times; i++) {
				String uid = uidPrefix + i;
				KeyPair pair = KeyUtil.generateRSAKeyPair1024();
				PublicKey publicKey = pair.getPublic();
				CertificateLifetime lifetime = getLifetime();
				X509Certificate cert = ifs.requestUserCertificate(getSAMLAssertion(uid, idp), publicKey, lifetime, CertificateSignatureAlgorithm.SHA2);
				String expectedIdentity = CommonUtils
						.subjectToIdentity(UserManager.getUserSubject(ifs.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), uid));

				checkCertificate(expectedIdentity, lifetime, pair.getPrivate(), cert);
				performAndValidateSingleAudit(ifs, adminGridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCOUNT_CREATED);
				performAndValidateSingleAudit(ifs, adminGridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.SUCCESSFUL_USER_CERTIFICATE_REQUEST);
				ucount = ucount + 1;
				assertEquals(ucount, ifs.findUsers(adminGridId, new GridUserFilter()).length);
				GridUserFilter f1 = new GridUserFilter();
				f1.setIdPId(idp.getIdp().getId());
				f1.setUID(uid);
				GridUser[] usr = ifs.findUsers(adminGridId, f1);
				assertEquals(1, usr.length);

				try {
					ifs.findUsers(usr[0].getGridId(), new GridUserFilter());
					fail("Should have thrown exception attempting to find users.");
				} catch (PermissionDeniedException f) {

				}

				performAndValidateSingleAudit(ifs, adminGridId, usr[0].getGridId(), AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED);

				ifs.addAdmin(adminGridId, usr[0].getGridId());
				performAndValidateSingleAudit(ifs, adminGridId, expectedIdentity, adminGridId, FederationAudit.ADMIN_ADDED);
				assertEquals(ucount, ifs.findUsers(usr[0].getGridId(), new GridUserFilter()).length);
			}

			for (int i = 0; i < times; i++) {
				String uid = uidPrefix + i;
				GridUserFilter f1 = new GridUserFilter();
				f1.setIdPId(idp.getIdp().getId());
				f1.setUID(uid);
				GridUser[] usr = ifs.findUsers(adminGridId, f1);
				assertEquals(1, usr.length);
				usr[0].setUserStatus(GridUserStatus.SUSPENDED);
				ifs.updateUser(adminGridId, usr[0]);
				performAndValidateSingleAudit(ifs, adminGridId, usr[0].getGridId(), adminGridId, FederationAudit.ACCOUNT_UPDATED);
			}

			int rcount = ucount;

			for (int i = 0; i < times; i++) {
				String uid = uidPrefix + i;
				GridUserFilter f1 = new GridUserFilter();
				f1.setIdPId(idp.getIdp().getId());
				f1.setUID(uid);
				GridUser[] usr = ifs.findUsers(adminGridId, f1);
				assertEquals(1, usr.length);
				ifs.removeUser(adminGridId, usr[0]);
				performAndValidateSingleAudit(ifs, adminGridId, usr[0].getGridId(), adminGridId, FederationAudit.ACCOUNT_REMOVED);
				performAndValidateSingleAudit(ifs, adminGridId, usr[0].getGridId(), AuditConstants.SYSTEM_ID, FederationAudit.ADMIN_REMOVED);
				rcount = rcount - 1;
				assertEquals(rcount, ifs.findUsers(adminGridId, new GridUserFilter()).length);
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void testRequestCertificate() {
		IdentityFederationManager ifs = null;
		try {

			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf();
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			KeyPair pair = KeyUtil.generateRSAKeyPair1024();
			PublicKey publicKey = pair.getPublic();
			CertificateLifetime lifetime = getLifetime();
			String uid = "user";
			X509Certificate cert = ifs.requestUserCertificate(getSAMLAssertion(uid, idp), publicKey, lifetime, CertificateSignatureAlgorithm.SHA2);
			String expectedIdentity = CommonUtils.subjectToIdentity(UserManager.getUserSubject(ifs.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), uid));

			checkCertificate(expectedIdentity, lifetime, pair.getPrivate(), cert);
			performAndValidateSingleAudit(ifs, adminGridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCOUNT_CREATED);
			performAndValidateSingleAudit(ifs, adminGridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.SUCCESSFUL_USER_CERTIFICATE_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void testRequestCertificateSuspendedIdP() {
		IdentityFederationManager ifs = null;
		try {

			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			idp.getIdp().setStatus(TrustedIdPStatus.SUSPENDED);
			IdentityFederationProperties conf = getConf();
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			KeyPair pair = KeyUtil.generateRSAKeyPair1024();
			PublicKey publicKey = pair.getPublic();
			CertificateLifetime lifetime = getLifetime();
			try {
				ifs.requestUserCertificate(getSAMLAssertion("user", idp), publicKey, lifetime, CertificateSignatureAlgorithm.SHA2);
				fail("Should not be able to request a certificate if the IdP is suspended.");
			} catch (PermissionDeniedException f) {
			}

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void testRequestCertificateAutoApproval() {
		IdentityFederationManager ifs = null;
		try {
			KeyPair pair = KeyUtil.generateRSAKeyPair1024();
			CertificateLifetime lifetime = getLifetimeShort();
			String username = "user";
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getExpiringCredentialsConf();
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String gridId = CommonUtils.subjectToIdentity(UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN));
			// give a chance for others to run right before we enter timing
			// sensitive code
			X509Certificate cert = ifs.requestUserCertificate(getSAMLAssertion(username, idp), pair.getPublic(), lifetime, CertificateSignatureAlgorithm.SHA2);
			String expectedIdentity = CommonUtils.subjectToIdentity(UserManager.getUserSubject(ifs.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(),
					username));

			checkCertificate(expectedIdentity, lifetime, pair.getPrivate(), cert);
			performAndValidateSingleAudit(ifs, gridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCOUNT_CREATED);
			performAndValidateSingleAudit(ifs, gridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.SUCCESSFUL_USER_CERTIFICATE_REQUEST);
			assertEquals(ifs.getUser(gridId, idp.getIdp().getId(), username).getUserStatus(), GridUserStatus.ACTIVE);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testRequestCertificateManualApproval() {
		IdentityFederationManager ifs = null;
		try {
			KeyPair pair = KeyUtil.generateRSAKeyPair1024();
			CertificateLifetime lifetime = getLifetimeShort();
			String username = "user";
			IdPContainer idp = this.getTrustedIdpManualApprove("My IdP");
			IdentityFederationProperties conf = getExpiringCredentialsConf();
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String gridId = CommonUtils.subjectToIdentity(UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), defaults
					.getDefaultUser().getUID()));

			try {
				ifs.requestUserCertificate(getSAMLAssertion(username, idp), pair.getPublic(), lifetime, CertificateSignatureAlgorithm.SHA2);
				fail("Should have thrown exception attempting to create proxy.");
			} catch (PermissionDeniedException fault) {

			}

			String expectedIdentity = CommonUtils.subjectToIdentity(UserManager.getUserSubject(ifs.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(),
					username));

			performAndValidateSingleAudit(ifs, gridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCOUNT_CREATED);
			performAndValidateSingleAudit(ifs, gridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST);
			assertEquals(ifs.getUser(gridId, idp.getIdp().getId(), username).getUserStatus(), GridUserStatus.PENDING);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testRenewHostCertificateAdminOnly() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			conf.setHostSearchPolicy(SearchPolicyType.ADMIN.value());
			conf.setAutoHostCertificateApproval(true);
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			GridUser usr2 = createUser(ifs, adminGridId, idp, "user2");
			String host = "myhost.example.com";
			HostCertificateRequest req = getHostCertificateRequest(host);
			HostCertificateRecord record = ifs.requestHostCertificate(usr.getGridId(), req);

			try {
				ifs.renewHostCertificate(usr2.getGridId(), record.getId());
				fail("Only admins should be able to renew a host certificate.");
			} catch (PermissionDeniedException e) {

			}

			try {
				ifs.renewHostCertificate(usr.getGridId(), record.getId());
				fail("Only admins should be able to renew a host certificate.");
			} catch (PermissionDeniedException e) {

			}

			ifs.renewHostCertificate(adminGridId, record.getId());

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testRenewHostCertificateOwnerAndAdminOnly() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf(false);
			conf.setHostSearchPolicy(SearchPolicyType.ADMIN.value());
			conf.setAutoHostCertificateApproval(true);
			conf.setHostCertificateRenewalPolicy(HostCertificateRenewalPolicy.OWNER.value());
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String adminSubject = UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), INITIAL_ADMIN);
			String adminGridId = CommonUtils.subjectToIdentity(adminSubject);
			GridUser usr = createUser(ifs, adminGridId, idp, "user");
			GridUser usr2 = createUser(ifs, adminGridId, idp, "user2");
			String host = "myhost.example.com";
			HostCertificateRequest req = getHostCertificateRequest(host);
			HostCertificateRecord record = ifs.requestHostCertificate(usr.getGridId(), req);

			try {
				ifs.renewHostCertificate(usr2.getGridId(), record.getId());
				fail("Only admins and owners should be able to renew a host certificate.");
			} catch (PermissionDeniedException e) {

			}

			ifs.renewHostCertificate(usr.getGridId(), record.getId());

			ifs.renewHostCertificate(adminGridId, record.getId());

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testRequestCertificateInvalidLifetime() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf();
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			String username = "user";
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			Thread.sleep(500);
			try {
				CertificateLifetime valid = new CertificateLifetime();
				valid.setHours(12);
				valid.setMinutes(0);
				valid.setSeconds(1);
				KeyPair pair = KeyUtil.generateRSAKeyPair1024();
				PublicKey publicKey = pair.getPublic();
				ifs.requestUserCertificate(getSAMLAssertion(username, idp), publicKey, valid, CertificateSignatureAlgorithm.SHA2);
				fail("Should not be able to request a certificate with an invalid lifetime.");
			} catch (UserPolicyException f) {

			}

			String gridId = CommonUtils.subjectToIdentity(UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), defaults
					.getDefaultUser().getUID()));

			String expectedIdentity = CommonUtils.subjectToIdentity(UserManager.getUserSubject(ifs.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(),
					username));

			performAndValidateSingleAudit(ifs, gridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCOUNT_CREATED);
			performAndValidateSingleAudit(ifs, gridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST);
			assertEquals(ifs.getUser(gridId, idp.getIdp().getId(), username).getUserStatus(), GridUserStatus.PENDING);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testRequestCertificateInvalidAuthenticationMethod() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf();
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			String username = "user";
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			try {
				KeyPair pair = KeyUtil.generateRSAKeyPair1024();
				PublicKey publicKey = pair.getPublic();
				ifs.requestUserCertificate(getSAMLAssertionUnspecifiedMethod(username, idp), publicKey, getLifetime(), CertificateSignatureAlgorithm.SHA2);
				fail("Should not be able to request a certificate with an Invalid Authentication Method.");
			} catch (InvalidAssertionException f) {

			}
			String gridId = CommonUtils.subjectToIdentity(UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), defaults
					.getDefaultUser().getUID()));

			String expectedIdentity = CommonUtils.subjectToIdentity(UserManager.getUserSubject(ifs.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(),
					username));

			performAndValidateSingleAudit(ifs, gridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testRequestCertificateUntrustedIdP() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdPContainer idp2 = this.getTrustedIdpAutoApprove("My IdP 2");

			IdentityFederationProperties conf = getConf();
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			String username = "user";
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);

			try {
				KeyPair pair = KeyUtil.generateRSAKeyPair1024();
				PublicKey publicKey = pair.getPublic();
				ifs.requestUserCertificate(getSAMLAssertion(username, idp2), publicKey, getLifetime(), CertificateSignatureAlgorithm.SHA2);
				fail("Should not be able to request a certificate when the IdP is not trusted.");
			} catch (InvalidAssertionException f) {

			}
			String gridId = CommonUtils.subjectToIdentity(UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), defaults
					.getDefaultUser().getUID()));

			performAndValidateSingleAudit(ifs, gridId, AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testRequestCertificateExpiredAssertion() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf();
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			String username = "user";
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			try {
				KeyPair pair = KeyUtil.generateRSAKeyPair1024();
				PublicKey publicKey = pair.getPublic();
				ifs.requestUserCertificate(getExpiredSAMLAssertion(username, idp), publicKey, getLifetime(), CertificateSignatureAlgorithm.SHA2);
				fail("Should not be able to request a certificate if the SAML Asserion is expired");
			} catch (InvalidAssertionException f) {

			}
			String gridId = CommonUtils.subjectToIdentity(UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), defaults
					.getDefaultUser().getUID()));

			String expectedIdentity = CommonUtils.subjectToIdentity(UserManager.getUserSubject(ifs.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(),
					username));

			performAndValidateSingleAudit(ifs, gridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testGetTrustedIdPs() {
		IdentityFederationManager ifs = null;
		try {
			IdPContainer idp0 = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getExpiringCredentialsConf();
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp0.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			String gridId = CommonUtils.subjectToIdentity(UserManager.getUserSubject(conf.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp0.getIdp(), defaults
					.getDefaultUser().getUID()));

			int times = 3;
			String baseName = "Test IdP";
			int count = 1;
			for (int i = 0; i < times; i++) {
				assertEquals(count, ifs.getTrustedIdPs(gridId).length);
				count = count + 1;
				String name = baseName + " " + count;
				IdPContainer cont = getTrustedIdpAutoApprove(name);
				TrustedIdP idp = cont.getIdp();
				idp = ifs.addTrustedIdP(gridId, idp);
				assertEquals(count, ifs.getTrustedIdPs(gridId).length);
				performAndValidateSingleAudit(ifs, gridId, idp.getName(), gridId, FederationAudit.ID_P_ADDED);

				// Test Updates
				IdPContainer updateCont = getTrustedIdpManualApprove(name);
				TrustedIdP updateIdp = updateCont.getIdp();
				updateIdp.setId(idp.getId());
				ifs.updateTrustedIdP(gridId, updateIdp);
				assertEquals(count, ifs.getTrustedIdPs(gridId).length);
				performAndValidateSingleAudit(ifs, gridId, idp.getName(), gridId, FederationAudit.ID_P_UPDATED);
			}

			TrustedIdP[] idps = ifs.getTrustedIdPs(gridId);
			assertEquals(times + 1, idps.length);
			count = times + 1;
			for (int i = 0; i < idps.length; i++) {
				if (idps[i].getId() != idp0.getIdp().getId()) {
					count = count - 1;
					ifs.removeTrustedIdP(gridId, idps[i].getId());
					assertEquals(count, ifs.getTrustedIdPs(gridId).length);
					performAndValidateSingleAudit(ifs, gridId, idps[i].getName(), gridId, FederationAudit.ID_P_REMOVED);
				}
			}

			assertEquals(count, ifs.getTrustedIdPs(gridId).length);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testAdministrators() {
		IdentityFederationManager ifs = null;
		try {

			IdPContainer idp = this.getTrustedIdpAutoApprove("My IdP");
			IdentityFederationProperties conf = getConf();
			FederationDefaults defaults = getDefaults();
			defaults.setDefaultIdP(idp.getIdp());
			ifs = new IdentityFederationManager(conf, db, props, ca, eventManager, defaults);
			KeyPair pair = KeyUtil.generateRSAKeyPair1024();
			PublicKey publicKey = pair.getPublic();
			CertificateLifetime lifetime = getLifetime();
			String uid = "user";
			X509Certificate cert = ifs.requestUserCertificate(getSAMLAssertion(uid, idp), publicKey, lifetime, CertificateSignatureAlgorithm.SHA2);
			String expectedIdentity = CommonUtils.subjectToIdentity(UserManager.getUserSubject(ifs.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), uid));

			checkCertificate(expectedIdentity, lifetime, pair.getPrivate(), cert);

			String adminGridId = defaults.getDefaultUser().getGridId();

			performAndValidateSingleAudit(ifs, adminGridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCOUNT_CREATED);
			performAndValidateSingleAudit(ifs, adminGridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.SUCCESSFUL_USER_CERTIFICATE_REQUEST);

			String userId = CommonUtils.subjectToIdentity(cert.getSubjectDN().toString());
			// Check that the user cannot call any admin methods
			int count = validateAccessControl(ifs, adminGridId, userId, 0);
			ifs.addAdmin(defaults.getDefaultUser().getGridId(), userId);
			assertEquals(2, ifs.findUsers(userId, null).length);

			performAndValidateSingleAudit(ifs, adminGridId, userId, adminGridId, FederationAudit.ADMIN_ADDED);
			ifs.removeAdmin(userId, userId);
			validateAccessControl(ifs, adminGridId, userId, count);
			performAndValidateSingleAudit(ifs, adminGridId, userId, userId, FederationAudit.ADMIN_REMOVED);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		} finally {
			try {
				ifs.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private int validateAccessControl(IdentityFederationManager ifs, String adminId, String userId, int startCount) throws Exception {
		int count = startCount;
		try {
			ifs.addAdmin(userId, null);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}
		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.addTrustedIdP(userId, null);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.findUsers(userId, null);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.getAdmins(userId);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.getTrustedIdPs(userId);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.getUser(userId, 0, null);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.getUserPolicies(userId);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.removeAdmin(userId, null);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.removeTrustedIdP(userId, 0);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.removeUser(userId, null);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.updateTrustedIdP(userId, null);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.updateUser(userId, null);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.findUserCertificateRecords(userId, null);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.updateUserCertificateRecord(userId, null);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.removeUserCertificate(userId, 0);
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		try {
			ifs.performAudit(userId, new FederationAuditFilter());
			fail("Should not have permission to execute the operation.");
		} catch (PermissionDeniedException f) {

		}

		count = count + 1;
		performAndValidateMultipleAudit(ifs, adminId, userId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED, count);

		return count;
	}

	private IdentityFederationProperties getConf() throws Exception {
		return getConf(true);
	}

	private IdentityFederationProperties getConf(boolean autoHostCertificateApproval) throws Exception {
		IdentityFederationProperties conf = new IdentityFederationProperties();
		conf.setIdentityAssignmentPolicy(org.cagrid.dorian.federation.impl.IdentityAssignmentPolicy.NAME);
		Lifetime l = new Lifetime();
		l.setYears(1);
		l.setMonths(0);
		l.setDays(0);
		l.setHours(0);
		l.setMinutes(0);
		l.setSeconds(0);
		conf.setIssuedCertificateLifetime(l);
		conf.setAutoHostCertificateApproval(autoHostCertificateApproval);

		conf.setMinIdPNameLength(MIN_NAME_LENGTH);
		conf.setMaxIdPNameLength(MAX_NAME_LENGTH);

		Lifetime pl = new Lifetime();
		pl.setHours(12);
		pl.setMinutes(0);
		pl.setSeconds(0);
		conf.setUserCertificateLifetime(pl);
		List<AccountPolicy> policies = new ArrayList<AccountPolicy>();
		policies.add(new ManualApprovalPolicy());
		policies.add(new AutoApprovalPolicy());
		conf.setAccountPolicies(policies);
		conf.setUserSearchPolicy(SearchPolicyType.PUBLIC.value());
		conf.setHostSearchPolicy(SearchPolicyType.PUBLIC.value());
		conf.setHostCertificateRenewalPolicy(HostCertificateRenewalPolicy.ADMIN.value());
		return conf;
	}

	private IdentityFederationProperties getExpiringCredentialsConf() throws Exception {

		IdentityFederationProperties conf = new IdentityFederationProperties();
		conf.setIdentityAssignmentPolicy(org.cagrid.dorian.federation.impl.IdentityAssignmentPolicy.NAME);
		Lifetime l = new Lifetime();
		l.setYears(0);
		l.setMonths(0);
		l.setDays(0);
		l.setHours(0);
		l.setMinutes(0);
		l.setSeconds(SHORT_CREDENTIALS_VALID);
		conf.setIssuedCertificateLifetime(l);
		conf.setAutoHostCertificateApproval(false);

		conf.setMinIdPNameLength(MIN_NAME_LENGTH);
		conf.setMaxIdPNameLength(MAX_NAME_LENGTH);

		Lifetime pl = new Lifetime();
		pl.setHours(12);
		pl.setMinutes(0);
		pl.setSeconds(0);
		conf.setUserCertificateLifetime(pl);
		List<AccountPolicy> policies = new ArrayList<AccountPolicy>();
		policies.add(new ManualApprovalPolicy());
		policies.add(new AutoApprovalPolicy());
		conf.setAccountPolicies(policies);
		conf.setHostCertificateRenewalPolicy(HostCertificateRenewalPolicy.ADMIN.value());
		return conf;
	}

	private FederationDefaults getDefaults() throws Exception {
		TrustedIdP idp = this.getTrustedIdpAutoApprove("Initial IdP").getIdp();
		GridUser usr = new GridUser();
		usr.setUID(INITIAL_ADMIN);
		usr.setFirstName("Mr");
		usr.setLastName("Admin");
		usr.setEmail(INITIAL_ADMIN + "@test.com");
		usr.setUserStatus(GridUserStatus.ACTIVE);
		return new FederationDefaults(idp, usr);
	}

	private SAMLAssertion getSAMLAssertion(String id, IdPContainer idp) throws Exception {
		GregorianCalendar cal = new GregorianCalendar();
		Date start = cal.getTime();
		cal.add(Calendar.MINUTE, 2);
		Date end = cal.getTime();
		return this.getSAMLAssertion(id, idp, start, end, "urn:oasis:names:tc:SAML:1.0:am:password");
	}

	private SAMLAssertion getSAMLAssertionUnspecifiedMethod(String id, IdPContainer idp) throws Exception {
		GregorianCalendar cal = new GregorianCalendar();
		Date start = cal.getTime();
		cal.add(Calendar.MINUTE, 2);
		Date end = cal.getTime();
		return this.getSAMLAssertion(id, idp, start, end, "urn:oasis:names:tc:SAML:1.0:am:unspecified");
	}

	private SAMLAssertion getExpiredSAMLAssertion(String id, IdPContainer idp) throws Exception {
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.MINUTE, -1);
		Date start = cal.getTime();
		Date end = cal.getTime();
		return this.getSAMLAssertion(id, idp, start, end, "urn:oasis:names:tc:SAML:1.0:am:password");
	}

	private SAMLAssertion getSAMLAssertion(String id, IdPContainer idp, Date start, Date end, String method) throws Exception {
		try {
			org.apache.xml.security.Init.init();
			String certStr = CertUtil.writeCertificate(idp.getCert());
			X509Certificate cert = CertUtil.loadCertificate(certStr);
			String keyStr = KeyUtil.writePrivateKey(idp.getKey(), "test");
			PrivateKey key = KeyUtil.loadPrivateKey(new ByteArrayInputStream(keyStr.getBytes()), "test");
			String firstName = "first" + id;
			String lastName = "first" + id;
			String email = id + "@test.com";

			String issuer = cert.getSubjectDN().toString();
			String federation = cert.getSubjectDN().toString();
			String ipAddress = null;
			String subjectDNS = null;
			SAMLNameIdentifier ni = new SAMLNameIdentifier(id, federation, "urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified");
			SAMLNameIdentifier ni2 = new SAMLNameIdentifier(id, federation, "urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified");
			SAMLSubject sub = new SAMLSubject(ni, null, null, null);
			SAMLSubject sub2 = new SAMLSubject(ni2, null, null, null);
			SAMLAuthenticationStatement auth = new SAMLAuthenticationStatement(sub, method, new Date(), ipAddress, subjectDNS, null);

			QName quid = new QName(SAMLConstants.UID_ATTRIBUTE_NAMESPACE, SAMLConstants.UID_ATTRIBUTE);
			List vals1 = new ArrayList();
			vals1.add(id);
			SAMLAttribute uidAtt = new SAMLAttribute(quid.getLocalPart(), quid.getNamespaceURI(), quid, 0, vals1);

			QName qfirst = new QName(SAMLConstants.FIRST_NAME_ATTRIBUTE_NAMESPACE, SAMLConstants.FIRST_NAME_ATTRIBUTE);
			List vals2 = new ArrayList();
			vals2.add(firstName);
			SAMLAttribute firstNameAtt = new SAMLAttribute(qfirst.getLocalPart(), qfirst.getNamespaceURI(), qfirst, 0, vals2);

			QName qLast = new QName(SAMLConstants.LAST_NAME_ATTRIBUTE_NAMESPACE, SAMLConstants.LAST_NAME_ATTRIBUTE);
			List vals3 = new ArrayList();
			vals3.add(lastName);
			SAMLAttribute lastNameAtt = new SAMLAttribute(qLast.getLocalPart(), qLast.getNamespaceURI(), qLast, 0, vals3);

			QName qemail = new QName(SAMLConstants.EMAIL_ATTRIBUTE_NAMESPACE, SAMLConstants.EMAIL_ATTRIBUTE);
			List vals4 = new ArrayList();
			vals4.add(email);
			SAMLAttribute emailAtt = new SAMLAttribute(qemail.getLocalPart(), qemail.getNamespaceURI(), qemail, 0, vals4);

			List atts = new ArrayList();
			atts.add(uidAtt);
			atts.add(firstNameAtt);
			atts.add(lastNameAtt);
			atts.add(emailAtt);
			SAMLAttributeStatement attState = new SAMLAttributeStatement(sub2, atts);

			List l = new ArrayList();
			l.add(auth);
			l.add(attState);

			SAMLAssertion saml = new SAMLAssertion(issuer, start, end, null, null, l);
			List a = new ArrayList();
			a.add(cert);
			saml.sign(XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1, key, a);

			return saml;
		} catch (Exception e) {
			e.printStackTrace();
			DorianInternalException fault = new DorianInternalException(null, "Error creating SAML Assertion.");
			throw fault;

		}
	}

	private String identityToSubject(String identity) {
		String s = identity.substring(1);
		return s.replace('/', ',');
	}

	private IdPContainer getTrustedIdpAutoApprove(String name) throws Exception {
		return this.getTrustedIdp(name, AutoApprovalPolicy.class.getName());
	}

	private IdPContainer getTrustedIdpManualApprove(String name) throws Exception {
		return this.getTrustedIdp(name, ManualApprovalPolicy.class.getName());
	}

	private void validateCRLOnDisabledUserStatus(IdentityFederationManager ifs, List<UserContainer> list, GridUserStatus status, String adminGridId, int userHostCerts) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			GridUser usr = list.get(i).getUser();
			usr.setUserStatus(status);
			ifs.updateUser(adminGridId, usr);
			X509CRL crl = ifs.getCRL(CertificateSignatureAlgorithm.SHA2);
			int sum = (i + 1) + ((i + 1) * userHostCerts);
			assertEquals(sum, crl.getRevokedCertificates().size());
			for (int j = 0; j < list.size(); j++) {
				UserContainer curr = list.get(j);
				if (j <= i) {
					UserCertificateFilter f = new UserCertificateFilter();
					f.setGridIdentity(curr.getUser().getGridId());
					DateRange now = new DateRange();
					now.setStartDate(new GregorianCalendar());
					now.setEndDate(new GregorianCalendar());
					f.setDateRange(now);
					List<UserCertificateRecord> records = ifs.findUserCertificateRecords(adminGridId, f);
					for (int x = 0; x < records.size(); x++) {
						X509Certificate cert = CertUtil.loadCertificate(records.get(x).getCertificate().getCertificateAsString());
						assertNotNull(crl.getRevokedCertificate(cert));
					}

					for (int x = 0; x < curr.getHostCertificates().size(); x++) {
						assertNotNull(crl.getRevokedCertificate(CertUtil.loadCertificate(curr.getHostCertificates().get(x).getCertificate().getCertificateAsString())));
					}
				} else {
					UserCertificateFilter f = new UserCertificateFilter();
					f.setGridIdentity(curr.getUser().getGridId());
					DateRange now = new DateRange();
					now.setStartDate(new GregorianCalendar());
					now.setEndDate(new GregorianCalendar());
					f.setDateRange(now);
					List<UserCertificateRecord> records = ifs.findUserCertificateRecords(adminGridId, f);
					for (int x = 0; x < records.size(); x++) {
						X509Certificate cert = CertUtil.loadCertificate(records.get(x).getCertificate().getCertificateAsString());
						assertNull(crl.getRevokedCertificate(cert));
					}

					for (int x = 0; x < curr.getHostCertificates().size(); x++) {
						assertNull(crl.getRevokedCertificate(CertUtil.loadCertificate(curr.getHostCertificates().get(x).getCertificate().getCertificateAsString())));
					}
				}

			}
		}

		for (int i = 0; i < list.size(); i++) {
			GridUser usr = list.get(i).getUser();
			usr.setUserStatus(GridUserStatus.ACTIVE);
			ifs.updateUser(adminGridId, usr);
		}
		assertEquals(null, ifs.getCRL(CertificateSignatureAlgorithm.SHA2).getRevokedCertificates());
	}

	private int validateCRLOnDisabledHostStatus(IdentityFederationManager ifs, List<UserContainer> list, HostCertificateStatus status, String adminGridId, int userHostCerts, int alreadyRevokedCerts)
			throws Exception {
		int sum = alreadyRevokedCerts;
		for (int i = 0; i < list.size(); i++) {
			UserContainer usr = list.get(i);
			for (int j = 0; j < usr.getHostCertificates().size(); j++) {
				HostCertificateUpdate update = new HostCertificateUpdate();
				update.setId(usr.getHostCertificates().get(j).getId());
				update.setStatus(status);
				ifs.updateHostCertificateRecord(adminGridId, update);
			}
			X509CRL crl = ifs.getCRL(CertificateSignatureAlgorithm.SHA2);
			sum = ((i + 1) * userHostCerts) + alreadyRevokedCerts;
			assertEquals(sum, crl.getRevokedCertificates().size());
			for (int j = 0; j < list.size(); j++) {
				UserContainer curr = list.get(j);
				UserCertificateFilter f = new UserCertificateFilter();
				f.setGridIdentity(curr.getUser().getGridId());
				DateRange now = new DateRange();
				now.setStartDate(new GregorianCalendar());
				now.setEndDate(new GregorianCalendar());
				f.setDateRange(now);
				List<UserCertificateRecord> records = ifs.findUserCertificateRecords(adminGridId, f);
				for (int x = 0; x < records.size(); x++) {
					X509Certificate cert = CertUtil.loadCertificate(records.get(x).getCertificate().getCertificateAsString());
					assertNull(crl.getRevokedCertificate(cert));
				}

				for (int x = 0; x < curr.getHostCertificates().size(); x++) {
					if (j <= i) {
						assertNotNull(crl.getRevokedCertificate(CertUtil.loadCertificate(curr.getHostCertificates().get(x).getCertificate().getCertificateAsString())));
					} else {
						assertNull(crl.getRevokedCertificate(CertUtil.loadCertificate(curr.getHostCertificates().get(x).getCertificate().getCertificateAsString())));
					}
				}
			}
		}
		if (!status.equals(HostCertificateStatus.COMPROMISED)) {
			for (int i = 0; i < list.size(); i++) {
				UserContainer usr = list.get(i);
				for (int j = 0; j < usr.getHostCertificates().size(); j++) {
					HostCertificateUpdate update = new HostCertificateUpdate();
					update.setId(usr.getHostCertificates().get(j).getId());
					update.setStatus(HostCertificateStatus.ACTIVE);
					ifs.updateHostCertificateRecord(adminGridId, update);
				}
			}
			if (alreadyRevokedCerts > 0) {
				assertEquals(alreadyRevokedCerts, ifs.getCRL(CertificateSignatureAlgorithm.SHA2).getRevokedCertificates().size());
			} else {
				assertEquals(null, ifs.getCRL(CertificateSignatureAlgorithm.SHA2).getRevokedCertificates());
			}
			return alreadyRevokedCerts;
		} else {
			return sum;
		}
	}

	private IdPContainer getTrustedIdp(String name, String policyClass) throws Exception {
		TrustedIdP idp = new TrustedIdP();
		idp.setName(name);
		idp.setDisplayName(name);
		idp.setUserPolicyClass(policyClass);
		idp.setStatus(TrustedIdPStatus.ACTIVE);
		SAMLAttributeDescriptor uid = new SAMLAttributeDescriptor();
		uid.setNamespaceURI(SAMLConstants.UID_ATTRIBUTE_NAMESPACE);
		uid.setName(SAMLConstants.UID_ATTRIBUTE);
		idp.setUserIdAttributeDescriptor(uid);

		SAMLAttributeDescriptor firstName = new SAMLAttributeDescriptor();
		firstName.setNamespaceURI(SAMLConstants.FIRST_NAME_ATTRIBUTE_NAMESPACE);
		firstName.setName(SAMLConstants.FIRST_NAME_ATTRIBUTE);
		idp.setFirstNameAttributeDescriptor(firstName);

		SAMLAttributeDescriptor lastName = new SAMLAttributeDescriptor();
		lastName.setNamespaceURI(SAMLConstants.LAST_NAME_ATTRIBUTE_NAMESPACE);
		lastName.setName(SAMLConstants.LAST_NAME_ATTRIBUTE);
		idp.setLastNameAttributeDescriptor(lastName);

		SAMLAttributeDescriptor email = new SAMLAttributeDescriptor();
		email.setNamespaceURI(SAMLConstants.EMAIL_ATTRIBUTE_NAMESPACE);
		email.setName(SAMLConstants.EMAIL_ATTRIBUTE);
		idp.setEmailAttributeDescriptor(email);

		idp.getAuthenticationMethod().add(SAMLAuthenticationMethod.fromValue("urn:oasis:names:tc:SAML:1.0:am:password"));

		String subject = Utils.CA_SUBJECT_PREFIX + ",CN=" + name;
		Credential cred = memoryCA.createIdentityCertificate(name);
		X509Certificate cert = cred.getCertificate();
		assertNotNull(cert);
		assertEquals(cert.getSubjectDN().getName(), subject);
		idp.setIdPCertificate(CertUtil.writeCertificate(cert));
		return new IdPContainer(idp, cert, cred.getPrivateKey());
	}

	private GridUser createUser(IdentityFederationManager ifs, String adminGridId, IdPContainer idp, String uid) throws Exception {
		KeyPair pair = KeyUtil.generateRSAKeyPair1024();
		PublicKey publicKey = pair.getPublic();
		CertificateLifetime lifetime = getLifetime();
		X509Certificate cert = ifs.requestUserCertificate(getSAMLAssertion(uid, idp), publicKey, lifetime, CertificateSignatureAlgorithm.SHA2);
		String expectedIdentity = CommonUtils.subjectToIdentity(UserManager.getUserSubject(ifs.getIdentityAssignmentPolicy(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName(), idp.getIdp(), uid));
		checkCertificate(expectedIdentity, lifetime, pair.getPrivate(), cert);
		performAndValidateSingleAudit(ifs, adminGridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCOUNT_CREATED);
		performAndValidateSingleAudit(ifs, adminGridId, expectedIdentity, AuditConstants.SYSTEM_ID, FederationAudit.SUCCESSFUL_USER_CERTIFICATE_REQUEST);
		GridUserFilter f1 = new GridUserFilter();
		f1.setIdPId(idp.getIdp().getId());
		f1.setUID(uid);
		GridUser[] users = ifs.findUsers(adminGridId, f1);
		assertEquals(1, users.length);
		return users[0];
	}

	private FederationAuditFilter getHostCertificatedApprovedAuditingFilter(String target, String reporter) {
		FederationAuditFilter f = new FederationAuditFilter();
		f.setTargetId(target);
		f.setReportingPartyId(reporter);
		f.setAuditType(FederationAudit.HOST_CERTIFICATE_APPROVED);
		return f;
	}

	private void performAndValidateMultipleAudit(IdentityFederationManager ifm, String adminId, String target, String reportingParty, FederationAudit type, int count) throws Exception {
		FederationAuditFilter f = new FederationAuditFilter();
		f.setTargetId(target);
		f.setReportingPartyId(reportingParty);
		f.setAuditType(type);
		List<FederationAuditRecord> results = ifm.performAudit(adminId, f);
		assertEquals(count, results.size());
	}

	private void performAndValidateSingleAudit(IdentityFederationManager ifm, String adminId, String target, String reportingParty, FederationAudit type) throws Exception {
		FederationAuditFilter f = new FederationAuditFilter();
		f.setTargetId(target);
		f.setReportingPartyId(reportingParty);
		f.setAuditType(type);
		List<FederationAuditRecord> results = ifm.performAudit(adminId, f);
		assertEquals(1, results.size());
		// printAuditingResults(results);
		validateAuditingResult(results.get(0), target, reportingParty, type);
	}

	private void validateAuditingResult(FederationAuditRecord a, String target, String reportingParty, FederationAudit type) {
		assertEquals(target, a.getTargetId());
		assertEquals(reportingParty, a.getReportingPartyId());
		assertEquals(type, a.getAuditType());
	}

	protected void setUp() throws Exception {
		super.setUp();
		try {
			db = Utils.getDB();
			assertEquals(0, db.getUsedConnectionCount());
			ca = Utils.getCertificateAuthorityManager();
			memoryCA = new CA(Utils.getCASubject());
			props = new PropertyManager(db);
			eventManager = Utils.getEventManager();
			eventManager.clearHandlers();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		}
	}

	protected void tearDown() throws Exception {
		super.setUp();
		try {
			eventManager.clearHandlers();
			assertEquals(0, db.getUsedConnectionCount());
			assertEquals(0, db.getRootUsedConnectionCount());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception occured:" + e.getMessage());
		}
		// return the thread to normal priority for those tests which raise the
		// thread priority
		Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
	}

	private CertificateLifetime getLifetimeShort() {
		CertificateLifetime valid = new CertificateLifetime();
		valid.setHours(0);
		valid.setMinutes(0);
		valid.setSeconds(SHORT_PROXY_VALID);
		return valid;
	}

	private CertificateLifetime getLifetime() {
		CertificateLifetime valid = new CertificateLifetime();
		valid.setHours(12);
		valid.setMinutes(0);
		valid.setSeconds(0);
		return valid;
	}

	private HostCertificateRecord createAndSubmitHostCert(IdentityFederationManager ifs, IdentityFederationProperties conf, String admin, String owner, String host) throws Exception {
		HostCertificateRequest req = getHostCertificateRequest(host);
		HostCertificateRecord record = ifs.requestHostCertificate(owner, req);
		if (!conf.autoHostCertificateApproval()) {
			assertEquals(HostCertificateStatus.PENDING, record.getStatus());
			record = ifs.approveHostCertificate(admin, record.getId());
		}
		assertEquals(HostCertificateStatus.ACTIVE, record.getStatus());
		assertEquals(host, record.getHost());
		return record;
	}

	private HostCertificateRequest getHostCertificateRequest(String host) throws Exception {
		KeyPair pair = KeyUtil.generateRSAKeyPair(ca.getDefaultCertificateAuthority().getProperties().getIssuedCertificateKeySize());
		HostCertificateRequest req = new HostCertificateRequest();
		req.setHostname(host);
		org.cagrid.dorian.ifs.PublicKey key = new org.cagrid.dorian.ifs.PublicKey();
		key.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));
		req.setPublicKey(key);
		return req;
	}

	private void checkCertificate(String expectedIdentity, CertificateLifetime lifetime, PrivateKey key, X509Certificate cert) throws Exception {
		assertNotNull(cert);
		GlobusCredential cred = new GlobusCredential(key, new X509Certificate[] { cert });
		assertNotNull(cred);
		long max = FederationUtils.getTimeInSeconds(lifetime);
		// what is this 3 for?
		long min = max - 3;
		long timeLeft = cred.getTimeLeft();
		assertTrue(min <= timeLeft);
		assertTrue(timeLeft <= max);
		assertEquals(expectedIdentity, cred.getIdentity());
		assertEquals(cert.getSubjectDN().toString(), identityToSubject(cred.getIdentity()));
		assertEquals(cred.getIssuer(), ca.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName());
		cred.verify();
	}

	public class IdPContainer {

		TrustedIdP idp;

		X509Certificate cert;

		PrivateKey key;

		public IdPContainer(TrustedIdP idp, X509Certificate cert, PrivateKey key) {
			this.idp = idp;
			this.cert = cert;
			this.key = key;
		}

		public X509Certificate getCert() {
			return cert;
		}

		public TrustedIdP getIdp() {
			return idp;
		}

		public PrivateKey getKey() {
			return key;
		}
	}

	public class UserContainer {
		private GridUser usr;
		private List<HostCertificateRecord> hostCertificates;
		private List<X509Certificate> compromisedUserCerts;
		private List<X509Certificate> userCerts;

		public UserContainer(GridUser usr) {
			this.usr = usr;
			this.hostCertificates = new ArrayList<HostCertificateRecord>();
			this.compromisedUserCerts = new ArrayList<X509Certificate>();
			this.userCerts = new ArrayList<X509Certificate>();
		}

		public GridUser getUser() {
			return usr;
		}

		public List<HostCertificateRecord> getHostCertificates() {
			return hostCertificates;
		}

		public void addHostCertificate(HostCertificateRecord record) {
			hostCertificates.add(record);
		}

		public void addCompromisedUserCertificate(X509Certificate cert) {
			compromisedUserCerts.add(cert);
		}

		public void addUserCertificate(X509Certificate cert) {
			userCerts.add(cert);
		}

		public List<X509Certificate> getCompromisedUserCerts() {
			return compromisedUserCerts;
		}

		public List<X509Certificate> getUserCerts() {
			return userCerts;
		}

	}

}
