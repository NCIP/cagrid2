package org.cagrid.dorian.service.impl;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.core.common.JAXBUtils;
import org.cagrid.core.resource.ExternalSingletonResourceProperty;
import org.cagrid.core.resource.ExternalSingletonResourcePropertyValue;
import org.cagrid.core.resource.JAXBResourceProperties;
import org.cagrid.core.resource.JAXBResourcePropertySupport;
import org.cagrid.core.resource.ResourceImpl;
import org.cagrid.core.resource.ResourcePropertyDescriptor;
import org.cagrid.core.resource.SingletonResourceHomeImpl;
import org.cagrid.dorian.DorianResourceProperties;
import org.cagrid.dorian.ca.impl.CertificateAuthorityManager;
import org.cagrid.dorian.common.AuditConstants;
import org.cagrid.dorian.common.SAMLConstants;
import org.cagrid.dorian.federation.impl.AutoApprovalPolicy;
import org.cagrid.dorian.federation.impl.FederationDefaults;
import org.cagrid.dorian.federation.impl.IdentityFederationManager;
import org.cagrid.dorian.federation.impl.IdentityFederationProperties;
import org.cagrid.dorian.idp.AccountProfile;
import org.cagrid.dorian.idp.Application;
import org.cagrid.dorian.idp.IdentityProviderAudit;
import org.cagrid.dorian.idp.IdentityProviderAuditFilter;
import org.cagrid.dorian.idp.IdentityProviderAuditRecord;
import org.cagrid.dorian.idp.LocalUser;
import org.cagrid.dorian.idp.LocalUserFilter;
import org.cagrid.dorian.idp.impl.IdentityProvider;
import org.cagrid.dorian.idp.impl.UserManager;
import org.cagrid.dorian.ifs.CertificateLifetime;
import org.cagrid.dorian.ifs.FederationAuditFilter;
import org.cagrid.dorian.ifs.FederationAuditRecord;
import org.cagrid.dorian.ifs.GridUser;
import org.cagrid.dorian.ifs.GridUserFilter;
import org.cagrid.dorian.ifs.GridUserPolicy;
import org.cagrid.dorian.ifs.GridUserRecord;
import org.cagrid.dorian.ifs.GridUserSearchCriteria;
import org.cagrid.dorian.ifs.GridUserStatus;
import org.cagrid.dorian.ifs.HostCertificateFilter;
import org.cagrid.dorian.ifs.HostCertificateRecord;
import org.cagrid.dorian.ifs.HostCertificateRequest;
import org.cagrid.dorian.ifs.HostCertificateUpdate;
import org.cagrid.dorian.ifs.HostRecord;
import org.cagrid.dorian.ifs.HostSearchCriteria;
import org.cagrid.dorian.ifs.SAMLAttributeDescriptor;
import org.cagrid.dorian.ifs.SAMLAuthenticationMethod;
import org.cagrid.dorian.ifs.TrustedIdP;
import org.cagrid.dorian.ifs.TrustedIdPStatus;
import org.cagrid.dorian.ifs.TrustedIdentityProviders;
import org.cagrid.dorian.ifs.UserCertificateFilter;
import org.cagrid.dorian.ifs.UserCertificateRecord;
import org.cagrid.dorian.ifs.UserCertificateUpdate;
import org.cagrid.dorian.policy.DorianPolicy;
import org.cagrid.dorian.service.CertificateSignatureAlgorithm;
import org.cagrid.dorian.service.Dorian;
import org.cagrid.dorian.service.PropertyManager;
import org.cagrid.dorian.types.DorianInternalException;
import org.cagrid.dorian.types.InvalidAssertionException;
import org.cagrid.dorian.types.InvalidHostCertificateException;
import org.cagrid.dorian.types.InvalidHostCertificateRequestException;
import org.cagrid.dorian.types.InvalidTrustedIdPException;
import org.cagrid.dorian.types.InvalidUserCertificateException;
import org.cagrid.dorian.types.InvalidUserException;
import org.cagrid.dorian.types.InvalidUserPropertyException;
import org.cagrid.dorian.types.NoSuchUserException;
import org.cagrid.dorian.types.PermissionDeniedException;
import org.cagrid.dorian.types.UserPolicyException;
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
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public class DorianImpl implements Dorian {

	public static final String IDP_ADMIN_USER_ID = "dorian";
	public static final String IDP_ADMIN_PASSWORD = "DorianAdmin$1";

	private final static String AUTHENTICATION_PROFILES_PREFIX = "gauth";

	private final static Logger log = LoggerFactory.getLogger(DorianImpl.class);

	private final ResourceImpl resource = new ResourceImpl(null);
	private final ResourceHome resourceHome = new SingletonResourceHomeImpl(resource);

	private final DorianProperties dorianProperties;
	private final Map<String, String> jaxbResourcePropertiesMap;

	private EventManager eventManager;
	private Database db;
	private PropertyManager propertyManager;
	private CertificateAuthorityManager caManager;
	private IdentityProvider identityProvider;
	private IdentityFederationProperties ifsConfiguration;
	private IdentityFederationManager ifm;

	private ResourceProperty<ServiceMetadata> serviceMetadataResourceProperty;
	private ResourceProperty<ServiceSecurityMetadata> serviceSecurityMetadataResourceProperty;

	public DorianImpl(DorianProperties dorianProperties, Map<String, String> jaxbResourcePropertiesMap) {
		this.dorianProperties = dorianProperties;
		this.jaxbResourcePropertiesMap = jaxbResourcePropertiesMap;
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
	public HostCertificateRecord requestHostCertificate(String callerGridId, HostCertificateRequest req) throws DorianInternalException, InvalidHostCertificateRequestException,
			InvalidHostCertificateException, PermissionDeniedException {
		return ifm.requestHostCertificate(callerGridId, req);
	}

	@Override
	public HostCertificateRecord[] getOwnedHostCertificates(String callerGridId) throws DorianInternalException, PermissionDeniedException {
		return ifm.getHostCertificatesForCaller(callerGridId);
	}

	@Override
	public HostCertificateRecord approveHostCertificate(String callerGridId, long recordId) throws DorianInternalException, InvalidHostCertificateException, PermissionDeniedException {
		return ifm.approveHostCertificate(callerGridId, recordId);
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
	public HostCertificateRecord renewHostCertificate(String callerGridId, long recordId) throws DorianInternalException, InvalidHostCertificateException, PermissionDeniedException {
		return ifm.renewHostCertificate(callerGridId, recordId);
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
	public ServiceMetadata getServiceMetadata() {
		return (serviceMetadataResourceProperty != null) ? serviceMetadataResourceProperty.get(0) : null;
	}

	@Override
	public AuthenticationProfiles getAuthenticationProfiles() {
		AuthenticationProfiles authProfiles = new AuthenticationProfiles();
		QName basicAuthenticationQName = JAXBUtils.getQName(BasicAuthentication.class);
		authProfiles.getProfile().add(basicAuthenticationQName);
		return authProfiles;
	}

	/*
	 * The client-side reconstruction of QNames from the getResourceProperty
	 * response is broken. It depends on the namespace prefix in the 'profile'
	 * element content being the same as in the element tag. To try to work
	 * around this, regenerate the appropriate QNames with a specific prefix and
	 * marshal the container with that prefix. The final response probably won't
	 * have the prefix used here, but the necessary prefixes should agree.
	 */
	private Element getAuthenticationProfilesElement() {
		AuthenticationProfiles authProfiles = getAuthenticationProfiles();
		QName authProfilesQName = JAXBUtils.getQName(AuthenticationProfiles.class);
		String authProfilesNamespace = authProfilesQName.getNamespaceURI();

		// New QName for marshalling
		authProfilesQName = new QName(authProfilesNamespace, authProfilesQName.getLocalPart(), AUTHENTICATION_PROFILES_PREFIX);

		// New QName elements
		List<QName> oldQNames = authProfiles.getProfile();
		List<QName> newQNames = new ArrayList<QName>(oldQNames.size());
		for (QName oldQName : oldQNames) {
			QName newQName = oldQName;
			if (authProfilesNamespace.equals(oldQName.getNamespaceURI())) {
				newQName = new QName(authProfilesNamespace, oldQName.getLocalPart(), AUTHENTICATION_PROFILES_PREFIX);
			}
			newQNames.add(newQName);
		}
		oldQNames.clear();
		oldQNames.addAll(newQNames);

		// Marshal to element with, hopefully, consistent prefixes.
		Element authProfilesElement = null;
		try {
			authProfilesElement = JAXBUtils.marshalToElement(authProfiles, authProfilesQName);
		} catch (Exception e) {
			log.error("Exception marshalling AuthenticationProfiles", e);
		}
		return authProfilesElement;
	}

	@Override
	public ResourceHome getResourceHome() {
		return resourceHome;
	}

	@Override
	public ServiceSecurityMetadata getServiceSecurityMetadata() {
		return (serviceSecurityMetadataResourceProperty != null) ? serviceSecurityMetadataResourceProperty.get(0) : null;
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
		// TODO
		final boolean ignoreCRL = false;
		ifm = new IdentityFederationManager(ifsConfiguration, db, propertyManager, caManager, this.eventManager, defaults, ignoreCRL);

		if (!propertyManager.getVersion().equals(PropertyManager.CURRENT_VERSION)) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Version conflict detected, your are running Dorian " + PropertyManager.CURRENT_VERSION
					+ " against a Dorian " + propertyManager.getVersion() + " database.");
			throw fault;
		}

		// What resource properties should we know about?
		Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors = ResourcePropertyDescriptor.analyzeResourcePropertiesHolder(DorianResourceProperties.class);

		// Map them by field.
		Map<String, ResourcePropertyDescriptor<?>> descriptorsByField = ResourcePropertyDescriptor.mapByField(resourcePropertyDescriptors);

		// Load the static jaxb resource properties.
		if (jaxbResourcePropertiesMap != null) {
			JAXBResourceProperties jaxbResourceProperties = new JAXBResourceProperties(getClass().getClassLoader(), descriptorsByField, jaxbResourcePropertiesMap);

			// The serviceMetadata property is static.
			@SuppressWarnings("unchecked")
			ResourcePropertyDescriptor<ServiceMetadata> serviceMetadataDescriptor = (ResourcePropertyDescriptor<ServiceMetadata>) descriptorsByField.get("serviceMetadata");
			if (serviceMetadataDescriptor != null) {
				@SuppressWarnings("unchecked")
				ResourceProperty<ServiceMetadata> resourceProperty = (ResourceProperty<ServiceMetadata>) jaxbResourceProperties.getResourceProperties().get(serviceMetadataDescriptor);
				serviceMetadataResourceProperty = resourceProperty;
				resource.add(serviceMetadataResourceProperty);
			}

			// The rest of the properties are callbacks.
			@SuppressWarnings("unchecked")
			ResourcePropertyDescriptor<AuthenticationProfiles> authenticationProfilesDescriptor = (ResourcePropertyDescriptor<AuthenticationProfiles>) descriptorsByField.get("authenticationProfiles");
			if (authenticationProfilesDescriptor != null) {
				// Must treat auth profiles as Element!
				ResourcePropertyDescriptor<Element> authenticationProfilesElementDescriptor = new ResourcePropertyDescriptor<Element>(authenticationProfilesDescriptor.getResourcePropertyQName(),
						Element.class, authenticationProfilesDescriptor.getFieldName());

				ExternalSingletonResourcePropertyValue<Element> propertyValue = new ExternalSingletonResourcePropertyValue<Element>() {
					@Override
					public Element getPropertyValue() {
						return getAuthenticationProfilesElement();
					}
				};
				ResourceProperty<Element> resourceProperty = new ExternalSingletonResourceProperty<Element>(authenticationProfilesElementDescriptor, propertyValue);
				resource.add(resourceProperty);
			}

			@SuppressWarnings("unchecked")
			ResourcePropertyDescriptor<TrustedIdentityProviders> trustedIdentityProvidersDescriptor = (ResourcePropertyDescriptor<TrustedIdentityProviders>) descriptorsByField
					.get("trustedIdentityProviders");
			if (trustedIdentityProvidersDescriptor != null) {
				ExternalSingletonResourcePropertyValue<TrustedIdentityProviders> propertyValue = new ExternalSingletonResourcePropertyValue<TrustedIdentityProviders>() {
					@Override
					public TrustedIdentityProviders getPropertyValue() {
						TrustedIdentityProviders trustedIdentityProviders = null;
						try {
							trustedIdentityProviders = getTrustedIdentityProviders();
						} catch (DorianInternalException ignored) {
						}
						return trustedIdentityProviders;
					}
				};
				ResourceProperty<TrustedIdentityProviders> resourceProperty = new ExternalSingletonResourceProperty<TrustedIdentityProviders>(trustedIdentityProvidersDescriptor, propertyValue);
				resource.add(resourceProperty);
			}

			@SuppressWarnings("unchecked")
			ResourcePropertyDescriptor<DorianPolicy> dorianPolicyDescriptor = (ResourcePropertyDescriptor<DorianPolicy>) descriptorsByField.get("dorianPolicy");
			if (dorianPolicyDescriptor != null) {
				ExternalSingletonResourcePropertyValue<DorianPolicy> propertyValue = new ExternalSingletonResourcePropertyValue<DorianPolicy>() {
					@Override
					public DorianPolicy getPropertyValue() {
						return getDorianPolicy();
					}
				};
				ResourceProperty<DorianPolicy> resourceProperty = new ExternalSingletonResourceProperty<DorianPolicy>(dorianPolicyDescriptor, propertyValue);
				resource.add(resourceProperty);
			}

			/*
			 * ServiceSecurityMetadata isn't a resource property, but use that
			 * framework to handle it.
			 */
			String serviceSecurityMetadataURLString = jaxbResourcePropertiesMap.get("serviceSecurityMetadata");
			if (serviceSecurityMetadataURLString != null) {
				URL url = null;
				try {
					url = new URL(serviceSecurityMetadataURLString);
				} catch (MalformedURLException ignored) {
				}
				if (url == null) {
					url = getClass().getClassLoader().getResource(serviceSecurityMetadataURLString);
				}
				if (url != null) {
					QName serviceSecurityMetadataQName = new QName(DorianImpl.class.getName(), "serviceSecurityMetadata");
					ResourcePropertyDescriptor<ServiceSecurityMetadata> serviceSecurityMetadataDescriptor = new ResourcePropertyDescriptor<ServiceSecurityMetadata>(serviceSecurityMetadataQName,
							ServiceSecurityMetadata.class, "serviceSecurityMetadata");
					serviceSecurityMetadataResourceProperty = JAXBResourcePropertySupport.createJAXBResourceProperty(serviceSecurityMetadataDescriptor, url);
				}
			}
		}
	}
}
