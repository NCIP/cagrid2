package org.cagrid.dorian.service.core;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import java.io.IOException;
import java.rmi.RemoteException;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.cagrid.core.common.FaultHelper;
import org.cagrid.core.common.JAXBUtils;
import org.cagrid.dorian.common.AuditConstants;
import org.cagrid.dorian.common.SAMLConstants;
import org.cagrid.dorian.model.exceptions.DorianInternalException;
import org.cagrid.dorian.model.exceptions.InvalidAssertionException;
import org.cagrid.dorian.model.exceptions.InvalidHostCertificateException;
import org.cagrid.dorian.model.exceptions.InvalidHostCertificateRequestException;
import org.cagrid.dorian.model.exceptions.InvalidTrustedIdPException;
import org.cagrid.dorian.model.exceptions.InvalidUserCertificateException;
import org.cagrid.dorian.model.exceptions.InvalidUserException;
import org.cagrid.dorian.model.exceptions.InvalidUserPropertyException;
import org.cagrid.dorian.model.exceptions.NoSuchUserException;
import org.cagrid.dorian.model.exceptions.PermissionDeniedException;
import org.cagrid.dorian.model.exceptions.UserPolicyException;
import org.cagrid.dorian.model.federation.CertificateLifetime;
import org.cagrid.dorian.model.federation.FederationAuditFilter;
import org.cagrid.dorian.model.federation.FederationAuditRecord;
import org.cagrid.dorian.model.federation.GridUser;
import org.cagrid.dorian.model.federation.GridUserFilter;
import org.cagrid.dorian.model.federation.GridUserPolicy;
import org.cagrid.dorian.model.federation.GridUserRecord;
import org.cagrid.dorian.model.federation.GridUserSearchCriteria;
import org.cagrid.dorian.model.federation.GridUserStatus;
import org.cagrid.dorian.model.federation.HostCertificateFilter;
import org.cagrid.dorian.model.federation.HostCertificateRecord;
import org.cagrid.dorian.model.federation.HostCertificateRequest;
import org.cagrid.dorian.model.federation.HostCertificateUpdate;
import org.cagrid.dorian.model.federation.HostRecord;
import org.cagrid.dorian.model.federation.HostSearchCriteria;
import org.cagrid.dorian.model.federation.SAMLAttributeDescriptor;
import org.cagrid.dorian.model.federation.SAMLAuthenticationMethod;
import org.cagrid.dorian.model.federation.TrustedIdP;
import org.cagrid.dorian.model.federation.TrustedIdPStatus;
import org.cagrid.dorian.model.federation.TrustedIdentityProviders;
import org.cagrid.dorian.model.federation.UserCertificateFilter;
import org.cagrid.dorian.model.federation.UserCertificateRecord;
import org.cagrid.dorian.model.federation.UserCertificateUpdate;
import org.cagrid.dorian.model.idp.AccountProfile;
import org.cagrid.dorian.model.idp.Application;
import org.cagrid.dorian.model.idp.IdentityProviderAudit;
import org.cagrid.dorian.model.idp.IdentityProviderAuditFilter;
import org.cagrid.dorian.model.idp.IdentityProviderAuditRecord;
import org.cagrid.dorian.model.idp.LocalUser;
import org.cagrid.dorian.model.idp.LocalUserFilter;
import org.cagrid.dorian.policy.DorianPolicy;
import org.cagrid.dorian.service.CertificateSignatureAlgorithm;
import org.cagrid.dorian.service.Dorian;
import org.cagrid.dorian.service.PropertyManager;
import org.cagrid.dorian.service.ca.CertificateAuthorityManager;
import org.cagrid.dorian.service.federation.AutoApprovalPolicy;
import org.cagrid.dorian.service.federation.FederationDefaults;
import org.cagrid.dorian.service.federation.IdentityFederationManager;
import org.cagrid.dorian.service.federation.IdentityFederationProperties;
import org.cagrid.dorian.service.idp.IdentityProvider;
import org.cagrid.dorian.service.idp.UserManager;
import org.cagrid.gaards.authentication.AuthenticationProfiles;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.gaards.authentication.Credential;
import org.cagrid.gaards.authentication.faults.AuthenticationProviderException;
import org.cagrid.gaards.authentication.faults.CredentialNotSupportedException;
import org.cagrid.gaards.authentication.faults.InvalidCredentialException;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.tools.database.Database;
import org.cagrid.tools.database.DatabaseException;
import org.cagrid.tools.events.EventManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.util.logging.resources.logging;

public class DorianImpl implements Dorian {

	public static final String IDP_ADMIN_USER_ID = "dorian";
	public static final String IDP_ADMIN_PASSWORD = "DorianAdmin$1";

	private final static Logger log = LoggerFactory.getLogger(DorianImpl.class);

	private final DorianProperties dorianProperties;

	private EventManager eventManager;
	private Database db;
	private PropertyManager propertyManager;
	private CertificateAuthorityManager caManager;
	private IdentityProvider identityProvider;
	private IdentityFederationProperties ifsConfiguration;
	private IdentityFederationManager ifm;

	public DorianImpl(DorianProperties dorianProperties) {
		this.dorianProperties = dorianProperties;
	}

	public Database getDatabase() {
		return this.db;
	}

	@Override
	public X509Certificate getCACertificate() throws DorianInternalException {
		try {
			return caManager.getDefaultCertificateAuthority().getCACertificate();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected error occurred, in obtaining the CA certificate.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	@Override
	public X509Certificate getIdPCertificate() throws DorianInternalException {
		return identityProvider.getIdPCertificate();
	}

	@Override
	public void changeLocalUserPassword(BasicAuthentication credential, String newPassword) throws DorianInternalException, PermissionDeniedException, InvalidUserPropertyException {
		identityProvider.changePassword(credential, newPassword);
	}

	@Override
	public LocalUser[] findLocalUsers(String gridIdentity, LocalUserFilter filter) throws DorianInternalException, PermissionDeniedException {
		String uid = null;
		try {
			uid = ifm.getUserIdVerifyTrustedIdP(identityProvider.getIdPCertificate(), gridIdentity);
		} catch (Exception e) {
			String message = "Permission to find local users was denied, caller is not a valid user.";
			eventManager.logEvent(gridIdentity, AuditConstants.SYSTEM_ID, IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message);
			PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, message);
			throw fault;
		}
		return this.identityProvider.findUsers(uid, filter);
	}

	@Override
	public void updateLocalUser(String gridIdentity, LocalUser u) throws DorianInternalException, PermissionDeniedException, NoSuchUserException, InvalidUserPropertyException {
		String uid = null;
		try {
			uid = ifm.getUserIdVerifyTrustedIdP(identityProvider.getIdPCertificate(), gridIdentity);
		} catch (Exception e) {
			String message = "Permission to update a user was denied, caller is not a valid user.";
			this.eventManager.logEvent(gridIdentity, AuditConstants.SYSTEM_ID, IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message);
			PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, message);
			throw fault;
		}
		this.identityProvider.updateUser(uid, u);
	}

	@Override
	public void removeLocalUser(String gridIdentity, String userId) throws DorianInternalException, PermissionDeniedException {
		String uid = null;
		try {
			uid = ifm.getUserIdVerifyTrustedIdP(identityProvider.getIdPCertificate(), gridIdentity);
		} catch (Exception e) {
			String message = "Permission to remove a user was denied, caller is not a valid user.";
			this.eventManager.logEvent(gridIdentity, AuditConstants.SYSTEM_ID, IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message);
			PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, message);
			throw fault;
		}
		this.identityProvider.removeUser(uid, userId);
		this.ifm.removeUserByLocalIdIfExists(identityProvider.getIdPCertificate(), userId);
	}

	@Override
	public List<IdentityProviderAuditRecord> performIdentityProviderAudit(String gridIdentity, IdentityProviderAuditFilter f) throws DorianInternalException, PermissionDeniedException {
		String uid = null;
		try {
			uid = ifm.getUserIdVerifyTrustedIdP(identityProvider.getIdPCertificate(), gridIdentity);
		} catch (Exception e) {
			String message = "Permission to perform an audit was denied, caller is not a valid user.";
			this.eventManager.logEvent(gridIdentity, AuditConstants.SYSTEM_ID, IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message);
			PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, message);
			throw fault;
		}
		return this.identityProvider.performAudit(uid, f);
	}

	@Override
	public SAMLAssertion authenticate(Credential credential) throws AuthenticationProviderException, InvalidCredentialException, CredentialNotSupportedException {
		return identityProvider.authenticate(credential);
	}

	@Override
	public String registerLocalUser(Application a) throws DorianInternalException, InvalidUserPropertyException {
		return this.identityProvider.register(a);
	}

	@Override
	public GridUserPolicy[] getGridUserPolicies(String callerGridIdentity) throws DorianInternalException, PermissionDeniedException {
		return ifm.getUserPolicies(callerGridIdentity);
	}

	@Override
	public X509Certificate requestUserCertificate(gov.nih.nci.cagrid.opensaml.SAMLAssertion saml, PublicKey publicKey, CertificateLifetime lifetime, CertificateSignatureAlgorithm sa)
			throws DorianInternalException, InvalidAssertionException, UserPolicyException, PermissionDeniedException {
		return ifm.requestUserCertificate(saml, publicKey, lifetime, sa);
	}

	@Override
	public TrustedIdP[] getTrustedIdPs(String callerGridIdentity) throws DorianInternalException, PermissionDeniedException {
		return ifm.getTrustedIdPs(callerGridIdentity);
	}

	@Override
	public TrustedIdP addTrustedIdP(String callerGridIdentity, TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException, PermissionDeniedException {
		return ifm.addTrustedIdP(callerGridIdentity, idp);
	}

	@Override
	public void updateTrustedIdP(String callerGridIdentity, TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException, PermissionDeniedException {
		ifm.updateTrustedIdP(callerGridIdentity, idp);
	}

	@Override
	public void removeTrustedIdP(String callerGridIdentity, TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException, PermissionDeniedException {
		ifm.removeTrustedIdP(callerGridIdentity, idp.getId());
	}

	@Override
	public GridUser[] findGridUsers(String callerGridIdentity, GridUserFilter filter) throws DorianInternalException, PermissionDeniedException {
		return ifm.findUsers(callerGridIdentity, filter);
	}

	@Override
	public void updateGridUser(String callerGridIdentity, GridUser usr) throws DorianInternalException, InvalidUserException, PermissionDeniedException {
		ifm.updateUser(callerGridIdentity, usr);
	}

	@Override
	public void removeGridUser(String callerGridIdentity, GridUser user) throws DorianInternalException, InvalidUserException, PermissionDeniedException {
		ifm.removeUser(callerGridIdentity, user);
	}

	@Override
	public void addAdmin(String callerGridIdentity, String gridIdentity) throws RemoteException, DorianInternalException, PermissionDeniedException {
		ifm.addAdmin(callerGridIdentity, gridIdentity);
	}

	@Override
	public void removeAdmin(String callerGridIdentity, String gridIdentity) throws RemoteException, DorianInternalException, PermissionDeniedException {
		ifm.removeAdmin(callerGridIdentity, gridIdentity);
	}

	@Override
	public String[] getAdmins(String callerGridIdentity) throws RemoteException, DorianInternalException, PermissionDeniedException {
		return ifm.getAdmins(callerGridIdentity);
	}

	@Override
	public HostCertificateRecord requestHostCertificate(String callerGridId, HostCertificateRequest req, CertificateSignatureAlgorithm alg) throws DorianInternalException,
			InvalidHostCertificateRequestException, InvalidHostCertificateException, PermissionDeniedException {
		return ifm.requestHostCertificate(callerGridId, req, alg);
	}

	@Override
	public HostCertificateRecord[] getOwnedHostCertificates(String callerGridId) throws DorianInternalException, PermissionDeniedException {
		return ifm.getHostCertificatesForCaller(callerGridId);
	}

	@Override
	public HostCertificateRecord approveHostCertificate(String callerGridId, long recordId, CertificateSignatureAlgorithm alg) throws DorianInternalException, InvalidHostCertificateException,
			PermissionDeniedException {
		return ifm.approveHostCertificate(callerGridId, recordId, alg);
	}

	@Override
	public HostCertificateRecord[] findHostCertificates(String callerGridId, HostCertificateFilter hostCertificateFilter) throws DorianInternalException, PermissionDeniedException {
		return ifm.findHostCertificates(callerGridId, hostCertificateFilter);
	}

	@Override
	public void updateHostCertificateRecord(String callerGridId, HostCertificateUpdate update) throws DorianInternalException, InvalidHostCertificateException, PermissionDeniedException {
		ifm.updateHostCertificateRecord(callerGridId, update);
	}

	@Override
	public HostCertificateRecord renewHostCertificate(String callerGridId, long recordId, CertificateSignatureAlgorithm algorithm) throws DorianInternalException, InvalidHostCertificateException, PermissionDeniedException {
		return ifm.renewHostCertificate(callerGridId, recordId, algorithm);
	}

	@Override
	public boolean doesLocalUserExist(String userId) throws DorianInternalException {
		return identityProvider.doesUserExist(userId);
	}

	@Override
	public void clearDatabase() throws DorianInternalException {
		try {
			this.identityProvider.clearDatabase();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		try {
			this.ifm.clearDatabase();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		try {
			propertyManager.clearDatabase();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public TrustedIdentityProviders getTrustedIdentityProviders() throws DorianInternalException {
		return ifm.getTrustedIdentityProviders();
	}

	@Override
	public List<UserCertificateRecord> findUserCertificateRecords(String callerIdentity, UserCertificateFilter f) throws DorianInternalException, InvalidUserCertificateException,
			PermissionDeniedException {
		return ifm.findUserCertificateRecords(callerIdentity, f);
	}

	@Override
	public void updateUserCertificateRecord(String callerIdentity, UserCertificateUpdate update) throws DorianInternalException, InvalidUserCertificateException, PermissionDeniedException {
		ifm.updateUserCertificateRecord(callerIdentity, update);
	}

	@Override
	public void removeUserCertificate(String callerIdentity, long serialNumber) throws DorianInternalException, InvalidUserCertificateException, PermissionDeniedException {
		ifm.removeUserCertificate(callerIdentity, serialNumber);
	}

	@Override
	public List<FederationAuditRecord> performFederationAudit(String callerIdentity, FederationAuditFilter f) throws DorianInternalException, PermissionDeniedException {
		return ifm.performAudit(callerIdentity, f);
	}

	@Override
	public List<GridUserRecord> userSearch(String callerIdentity, GridUserSearchCriteria criteria) throws RemoteException, DorianInternalException, PermissionDeniedException {
		return ifm.userSearch(callerIdentity, criteria);
	}

	@Override
	public List<HostRecord> hostSearch(String callerIdentity, HostSearchCriteria criteria) throws RemoteException, DorianInternalException, PermissionDeniedException {
		return ifm.hostSearch(callerIdentity, criteria);
	}

	@Override
	public AccountProfile getAccountProfile(String gridIdentity) throws RemoteException, DorianInternalException, PermissionDeniedException {
		String uid = null;
		try {
			uid = ifm.getUserIdVerifyTrustedIdP(identityProvider.getIdPCertificate(), gridIdentity);
		} catch (Exception e) {
			String message = "Permission to get the account profile for the user was denied, caller is not a valid user.";
			eventManager.logEvent(gridIdentity, AuditConstants.SYSTEM_ID, IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message);
			PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, message);
			throw fault;
		}
		return this.identityProvider.getAccountProfile(uid);
	}

	@Override
	public void updateAccountProfile(String gridIdentity, AccountProfile profile) throws RemoteException, DorianInternalException, InvalidUserPropertyException, PermissionDeniedException,
			NoSuchUserException {
		String uid = null;
		try {
			uid = ifm.getUserIdVerifyTrustedIdP(identityProvider.getIdPCertificate(), gridIdentity);
		} catch (Exception e) {
			String message = "Permission to update the account profile for the user was denied, caller is not a valid user.";
			eventManager.logEvent(gridIdentity, AuditConstants.SYSTEM_ID, IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message);
			PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, message);
			throw fault;
		}
		this.identityProvider.updateAccountProfile(uid, profile);
	}

	@Override
	public DorianPolicy getDorianPolicy() {
		DorianPolicy p = new DorianPolicy();
		p.setIdentityProviderPolicy(identityProvider.getPolicy());
		p.setFederationPolicy(ifm.getFederationPolicy());
		return p;
	}

	@Override
	public void setPublish(String callerGridIdentity, TrustedIdP idp, boolean publish) throws DorianInternalException, InvalidTrustedIdPException, PermissionDeniedException {
		ifm.setPublish(callerGridIdentity, idp, publish);
	}

	@Override
	public boolean getPublish(String callerGridIdentity, TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException, PermissionDeniedException {
		return ifm.getPublish(callerGridIdentity, idp);
	}

	@Override
	public AuthenticationProfiles getAuthenticationProfiles() {
		AuthenticationProfiles authProfiles = new AuthenticationProfiles();
		QName basicAuthenticationQName = JAXBUtils.getQName(BasicAuthentication.class);
		authProfiles.getProfile().add(basicAuthenticationQName);
		return authProfiles;
	}

	public void initialize() throws JAXBException, DatabaseException, DorianInternalException, IOException {
		eventManager = dorianProperties.getEventManager();
		UserManager.ADMIN_USER_ID = IDP_ADMIN_USER_ID;
		UserManager.ADMIN_PASSWORD = IDP_ADMIN_PASSWORD;
		db = dorianProperties.getDatabase();
		db.createDatabaseIfNeeded();
		propertyManager = new PropertyManager(db);

		this.caManager = dorianProperties.getCertificateAuthorityManager();
		identityProvider = new IdentityProvider(dorianProperties.getIdentityProviderProperties(), db, this.caManager.getDefaultCertificateAuthority(), eventManager);

		TrustedIdP idp = new TrustedIdP();
		idp.setName(dorianProperties.getIdentityProviderProperties().getName());
		idp.setDisplayName(dorianProperties.getIdentityProviderProperties().getName());
		SAMLAuthenticationMethod[] methods = new SAMLAuthenticationMethod[1];
		methods[0] = SAMLAuthenticationMethod.fromValue("urn:oasis:names:tc:SAML:1.0:am:password");
		idp.getAuthenticationMethod().addAll(Arrays.asList(methods));
		idp.setUserPolicyClass(AutoApprovalPolicy.class.getName());
		idp.setIdPCertificate(CertUtil.writeCertificate(identityProvider.getIdPCertificate()));
		idp.setStatus(TrustedIdPStatus.ACTIVE);
		// TODO
		final String serviceId = "https://localhost";
		idp.setAuthenticationServiceURL(serviceId);
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

		GridUser usr = null;
		try {
			LocalUser idpUsr = identityProvider.getUser(IDP_ADMIN_USER_ID, IDP_ADMIN_USER_ID);
			usr = new GridUser();
			usr.setUID(idpUsr.getUserId());
			usr.setFirstName(idpUsr.getFirstName());
			usr.setLastName(idpUsr.getLastName());
			usr.setEmail(idpUsr.getEmail());
			usr.setUserStatus(GridUserStatus.ACTIVE);
		} catch (Exception e) {
		}

		ifsConfiguration = dorianProperties.getIdentityFederationProperties();
		FederationDefaults defaults = new FederationDefaults(idp, usr);
		boolean ignoreCRL = true;

		List<String> crls = ifsConfiguration.getCRLPublishingList();
		if (crls != null) {
			for (String crl : crls) {
				if (!StringUtils.isBlank(crl)) {
					ignoreCRL = false;
				}
			}
		}

        if(ignoreCRL){
            log.info("No CRLS configured, ignoring CRL publishing");
        }

		ifm = new IdentityFederationManager(ifsConfiguration, db, propertyManager, caManager, this.eventManager, defaults, ignoreCRL);

		if (!propertyManager.getVersion().equals(PropertyManager.CURRENT_VERSION)) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Version conflict detected, your are running Dorian " + PropertyManager.CURRENT_VERSION
					+ " against a Dorian " + propertyManager.getVersion() + " database.");
			throw fault;
		}

	}
}
