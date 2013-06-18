package org.cagrid.dorian.federation.impl;

import gov.nih.nci.cagrid.common.ThreadManager;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.cagrid.opensaml.SAMLAttribute;
import gov.nih.nci.cagrid.opensaml.SAMLAttributeStatement;
import gov.nih.nci.cagrid.opensaml.SAMLAuthenticationStatement;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.PublicKey;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bouncycastle.asn1.x509.CRLReason;
import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.ca.impl.CertificateAuthority;
import org.cagrid.dorian.ca.impl.CertificateAuthorityException;
import org.cagrid.dorian.ca.impl.CertificateAuthorityManager;
import org.cagrid.dorian.common.AuditConstants;
import org.cagrid.dorian.common.CommonUtils;
import org.cagrid.dorian.ifs.CertificateLifetime;
import org.cagrid.dorian.ifs.FederationAudit;
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
import org.cagrid.dorian.ifs.HostCertificateStatus;
import org.cagrid.dorian.ifs.HostCertificateUpdate;
import org.cagrid.dorian.ifs.HostRecord;
import org.cagrid.dorian.ifs.HostSearchCriteria;
import org.cagrid.dorian.ifs.TrustedIdP;
import org.cagrid.dorian.ifs.TrustedIdPStatus;
import org.cagrid.dorian.ifs.TrustedIdentityProvider;
import org.cagrid.dorian.ifs.TrustedIdentityProviders;
import org.cagrid.dorian.ifs.UserCertificateFilter;
import org.cagrid.dorian.ifs.UserCertificateRecord;
import org.cagrid.dorian.ifs.UserCertificateUpdate;
import org.cagrid.dorian.policy.FederationPolicy;
import org.cagrid.dorian.policy.HostCertificateLifetime;
import org.cagrid.dorian.policy.HostCertificateRenewalPolicy;
import org.cagrid.dorian.policy.HostPolicy;
import org.cagrid.dorian.policy.SearchPolicy;
import org.cagrid.dorian.policy.SearchPolicyType;
import org.cagrid.dorian.policy.UserCertificateLifetime;
import org.cagrid.dorian.policy.UserPolicy;
import org.cagrid.dorian.service.CertificateSignatureAlgorithm;
import org.cagrid.dorian.service.DorianConstants;
import org.cagrid.dorian.service.PropertyManager;
import org.cagrid.dorian.service.util.AddressValidator;
import org.cagrid.dorian.types.DorianInternalException;
import org.cagrid.dorian.types.InvalidAssertionException;
import org.cagrid.dorian.types.InvalidHostCertificateException;
import org.cagrid.dorian.types.InvalidHostCertificateRequestException;
import org.cagrid.dorian.types.InvalidTrustedIdPException;
import org.cagrid.dorian.types.InvalidUserCertificateException;
import org.cagrid.dorian.types.InvalidUserException;
import org.cagrid.dorian.types.PermissionDeniedException;
import org.cagrid.dorian.types.UserPolicyException;
import org.cagrid.gaards.pki.CRLEntry;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.tools.database.Database;
import org.cagrid.tools.events.Event;
import org.cagrid.tools.events.EventAuditor;
import org.cagrid.tools.events.EventManager;
import org.cagrid.tools.events.EventToHandlerMapping;
import org.cagrid.tools.groups.Group;
import org.cagrid.tools.groups.GroupException;
import org.cagrid.tools.groups.GroupManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdentityFederationManager implements Publisher {

	private final int CERTIFICATE_START_OFFSET_SECONDS = -10;

	private final static Logger logger = LoggerFactory.getLogger(IdentityFederationManager.class);

	private UserManager um;

	private TrustedIdPManager tm;

	private IdentityFederationProperties conf;

	private CertificateAuthorityManager caManager;

	private Object mutex = new Object();

	public static final String ADMINISTRATORS = "administrators";

	private Group administrators;

	private GroupManager groupManager;

	private HostCertificateManager hostManager;

	private ThreadManager threadManager;

	private boolean publishCRL = false;

	private CertificateBlacklistManager blackList;

	private UserCertificateManager userCertificateManager;

	private Database db;

	private EventManager eventManager;

	private EventAuditor federationAuditor;
	private EventAuditor gridAccountAuditor;
	private EventAuditor hostAuditor;
	private EventAuditor userCertificateAuditor;

	public IdentityFederationManager(IdentityFederationProperties conf, Database db, PropertyManager properties, CertificateAuthorityManager caManager, EventManager eventManager,
			FederationDefaults defaults) throws DorianInternalException {
		this(conf, db, properties, caManager, eventManager, defaults, false);
	}

	public IdentityFederationManager(IdentityFederationProperties conf, Database db, PropertyManager properties, CertificateAuthorityManager caManager, EventManager eventManager,
			FederationDefaults defaults, boolean ignoreCRL) throws DorianInternalException {
		super();
		this.conf = conf;
		this.caManager = caManager;
		this.db = db;
		this.eventManager = eventManager;
		this.initializeEventManager();
		this.threadManager = new ThreadManager();
		this.blackList = new CertificateBlacklistManager(db);
		this.userCertificateManager = new UserCertificateManager(db, this, this.blackList);
		tm = new TrustedIdPManager(conf, db);
		um = new UserManager(db, conf, properties, this.caManager.getDefaultCertificateAuthority(), tm, this, defaults);
		um.buildDatabase();
		this.groupManager = new GroupManager(db);
		try {
			if (!this.groupManager.groupExists(ADMINISTRATORS)) {
				this.groupManager.addGroup(ADMINISTRATORS);
				this.administrators = this.groupManager.getGroup(ADMINISTRATORS);
				if (defaults.getDefaultUser() != null) {
					this.administrators.addMember(defaults.getDefaultUser().getGridId());
				} else {
					String mess = "COULD NOT ADD DEFAULT USER TO ADMINISTRATORS GROUP, NO DEFAULT USER WAS FOUND!!!";
					logger.warn(mess);
					this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess);
				}
			} else {
				this.administrators = this.groupManager.getGroup(ADMINISTRATORS);
			}
		} catch (GroupException e) {
			logger.error(e.getMessage(), e);
			String mess = "An unexpected error occurred in setting up the administrators group.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, mess);
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
		this.hostManager = new HostCertificateManager(db, this.conf, caManager.getDefaultCertificateAuthority(), this, blackList);

		try {
			TrustedIdP idp = tm.getTrustedIdP(CertUtil.loadCertificate(defaults.getDefaultIdP().getIdPCertificate()));
			if ((idp.getAuthenticationServiceURL() == null) && (defaults.getDefaultIdP().getAuthenticationServiceURL() != null)) {
				idp.setAuthenticationServiceURL(defaults.getDefaultIdP().getAuthenticationServiceURL());
				tm.updateIdP(idp);
			} else if ((defaults.getDefaultIdP().getAuthenticationServiceURL() != null) && (!defaults.getDefaultIdP().getAuthenticationServiceURL().equals(idp.getAuthenticationServiceURL()))) {
				idp.setAuthenticationServiceURL(defaults.getDefaultIdP().getAuthenticationServiceURL());
				tm.updateIdP(idp);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			String mess = "An unexpected error occurred in ensuring the integrity of the Dorian IdP.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, mess);
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
		if (!ignoreCRL) {
			publishCRL = true;
			publishCRL();
		}
	}

	private void initializeEventManager() throws DorianInternalException {
		try {
			if (this.eventManager.isHandlerRegistered(AuditingConstants.FEDERATION_AUDITOR)) {
				this.federationAuditor = (EventAuditor) this.eventManager.getEventHandler(AuditingConstants.FEDERATION_AUDITOR);
			} else {
				this.federationAuditor = new EventAuditor(AuditingConstants.FEDERATION_AUDITOR, this.db, AuditingConstants.FEDERATION_AUDITOR_DB);
				this.eventManager.registerHandler(this.federationAuditor);
			}

			if (this.eventManager.isHandlerRegistered(AuditingConstants.GRID_ACCOUNT_AUDITOR)) {
				this.gridAccountAuditor = (EventAuditor) this.eventManager.getEventHandler(AuditingConstants.GRID_ACCOUNT_AUDITOR);
			} else {
				this.gridAccountAuditor = new EventAuditor(AuditingConstants.GRID_ACCOUNT_AUDITOR, this.db, AuditingConstants.GRID_ACCOUNT_AUDITOR_DB);
				this.eventManager.registerHandler(this.gridAccountAuditor);
			}

			if (this.eventManager.isHandlerRegistered(AuditingConstants.HOST_AUDITOR)) {
				this.hostAuditor = (EventAuditor) this.eventManager.getEventHandler(AuditingConstants.HOST_AUDITOR);
			} else {
				this.hostAuditor = new EventAuditor(AuditingConstants.HOST_AUDITOR, this.db, AuditingConstants.HOST_AUDITOR_DB);
				this.eventManager.registerHandler(this.hostAuditor);
			}

			if (this.eventManager.isHandlerRegistered(AuditingConstants.USER_CERTIFICATE_AUDITOR)) {
				this.userCertificateAuditor = (EventAuditor) this.eventManager.getEventHandler(AuditingConstants.USER_CERTIFICATE_AUDITOR);
			} else {
				this.userCertificateAuditor = new EventAuditor(AuditingConstants.USER_CERTIFICATE_AUDITOR, this.db, AuditingConstants.USER_CERTIFICATE_AUDITOR_DB);
				this.eventManager.registerHandler(this.userCertificateAuditor);
			}

			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.SYSTEM_STARTUP.value(), AuditingConstants.FEDERATION_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.INTERNAL_ERROR.value(), AuditingConstants.FEDERATION_AUDITOR));

			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.ACCESS_DENIED.value(), AuditingConstants.FEDERATION_AUDITOR));

			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.ID_P_ADDED.value(), AuditingConstants.FEDERATION_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.ID_P_UPDATED.value(), AuditingConstants.FEDERATION_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.ID_P_REMOVED.value(), AuditingConstants.FEDERATION_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.ADMIN_ADDED.value(), AuditingConstants.FEDERATION_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.ADMIN_REMOVED.value(), AuditingConstants.FEDERATION_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.CRL_PUBLISHED.value(), AuditingConstants.FEDERATION_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.ACCOUNT_CREATED.value(), AuditingConstants.GRID_ACCOUNT_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.ACCOUNT_UPDATED.value(), AuditingConstants.GRID_ACCOUNT_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.ACCOUNT_REMOVED.value(), AuditingConstants.GRID_ACCOUNT_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.SUCCESSFUL_USER_CERTIFICATE_REQUEST.value(), AuditingConstants.GRID_ACCOUNT_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.INVALID_USER_CERTIFICATE_REQUEST.value(), AuditingConstants.GRID_ACCOUNT_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.HOST_CERTIFICATE_REQUESTED.value(), AuditingConstants.HOST_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.HOST_CERTIFICATE_APPROVED.value(), AuditingConstants.HOST_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.HOST_CERTIFICATE_UPDATED.value(), AuditingConstants.HOST_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.HOST_CERTIFICATE_RENEWED.value(), AuditingConstants.HOST_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.USER_CERTIFICATE_UPDATED.value(), AuditingConstants.USER_CERTIFICATE_AUDITOR));
			this.eventManager.registerEventWithHandler(new EventToHandlerMapping(FederationAudit.USER_CERTIFICATE_REMOVED.value(), AuditingConstants.USER_CERTIFICATE_AUDITOR));
		} catch (Exception e) {
			logger.error(Utils.getExceptionMessage(e), e);
			String mess = "An unexpected error occurred initializing the auditing system:\n" + Utils.getExceptionMessage(e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, mess);
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public String getIdentityAssignmentPolicy() {
		return um.getIdentityAssignmentPolicy();
	}

	public GridUserPolicy[] getUserPolicies(String callerGridIdentity) throws DorianInternalException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			return tm.getAccountPolicies();
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred obtaining the supported user polcies.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Permission denied to obtaining supported user policies:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public String getUserIdVerifyTrustedIdP(X509Certificate idpCert, String identity) throws DorianInternalException, InvalidUserException, InvalidTrustedIdPException, PermissionDeniedException {
		if (identity == null) {
			PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "No credentials specified.");
			throw fault;
		}
		TrustedIdP idp = tm.getTrustedIdPByDN(idpCert.getSubjectDN().getName());
		GridUser usr = um.getUser(identity);
		if (usr.getIdPId() != idp.getId()) {
			PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "Not a valid user of the IdP " + idp.getName());
			throw fault;
		}
		return usr.getUID();
	}

	public TrustedIdP addTrustedIdP(String callerGridIdentity, TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			idp = tm.addTrustedIdP(idp);
			this.eventManager.logEvent(idp.getName(), callerGridIdentity, FederationAudit.ID_P_ADDED.value(), "The Trusted Identity Provider " + idp.getName() + " (" + idp.getId() + ") was added by "
					+ callerGridIdentity + ".");
			return idp;
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in adding a trusted identity provider.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to add a trusted identity provider:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public void updateTrustedIdP(String callerGridIdentity, TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			TrustedIdP curr = tm.getTrustedIdPById(idp.getId());
			boolean statusChanged = false;
			if ((idp.getStatus() != null) && (!idp.getStatus().equals(curr.getStatus()))) {
				statusChanged = true;
			}
			tm.updateIdP(idp);

			if (statusChanged) {
				publishCRL();
			}
			this.eventManager.logEvent(idp.getName(), callerGridIdentity, FederationAudit.ID_P_UPDATED.value(), ReportUtils.generateReport(curr, idp));
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in updating the identity provider " + idp.getName() + " (" + idp.getId() + "):";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to update a trusted identity provider:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public void removeTrustedIdP(String callerGridIdentity, long idpId) throws DorianInternalException, InvalidTrustedIdPException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			TrustedIdP idp = tm.getTrustedIdPById(idpId);
			tm.removeTrustedIdP(idpId);
			this.eventManager.logEvent(idp.getName(), callerGridIdentity, FederationAudit.ID_P_REMOVED.value(), "The Identity Provider " + idp.getName() + " (" + idp.getId() + ") was removed by "
					+ callerGridIdentity + ".");
			GridUserFilter uf = new GridUserFilter();
			uf.setIdPId(idpId);
			GridUser[] users = um.getUsers(uf);
			for (int i = 0; i < users.length; i++) {
				try {
					removeUser(users[i]);
					this.eventManager.logEvent(users[i].getGridId(), callerGridIdentity, FederationAudit.ACCOUNT_REMOVED.value(), users[i].getFirstName() + " " + users[i].getLastName()
							+ "'s account was removed because the IdP " + idp.getName() + " (" + idp.getId() + ") was removed by " + callerGridIdentity + " was removed as a Trusted IdP.");
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					this.eventManager
							.logEvent(
									AuditConstants.SYSTEM_ID,
									AuditConstants.SYSTEM_ID,
									FederationAudit.INTERNAL_ERROR.value(),
									"In removing the Trusted IdP "
											+ idp.getName()
											+ " ("
											+ idp.getId()
											+ ") an unexpected error was encountered when trying to remove the user account "
											+ users[i].getGridId()
											+ ".  Although the Trusted IdP was removed the user account may not have been removed as it should have been because of this error.   Details of this error are provided below:\n\n"
											+ e.getMessage());
				}
			}

		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in removing the identity provider " + idpId + ":";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to remove a identity provider:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public TrustedIdP[] getTrustedIdPs(String callerGridIdentity) throws DorianInternalException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			return tm.getTrustedIdPs();
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in listing the trusted identity providers.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to list trusted identity providers:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public GridUser getUser(String callerGridIdentity, long idpId, String uid) throws DorianInternalException, InvalidUserException, PermissionDeniedException {
		try {
			GridUser caller = um.getUser(callerGridIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			return um.getUser(idpId, uid);
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in loading a user account.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to load grid user accounts:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public GridUser[] findUsers(String callerGridIdentity, GridUserFilter filter) throws DorianInternalException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			return um.getUsers(filter);

		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in searching for grid user accounts.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to search for grid user accounts:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public void updateUser(String callerGridIdentity, GridUser usr) throws DorianInternalException, InvalidUserException, PermissionDeniedException {
		try {
			GridUser caller = um.getUser(callerGridIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			GridUser curr = um.getUser(usr.getIdPId(), usr.getUID());
			um.updateUser(usr);
			this.eventManager.logEvent(curr.getGridId(), callerGridIdentity, FederationAudit.ACCOUNT_UPDATED.value(), ReportUtils.generateReport(curr, usr));
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in updating the grid user account " + usr.getGridId() + ":";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to update grid user accounts:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public void removeUserByLocalIdIfExists(X509Certificate idpCert, String localId) throws DorianInternalException {
		try {
			TrustedIdP idp = tm.getTrustedIdPByDN(idpCert.getSubjectDN().getName());
			GridUser usr = um.getUser(idp.getId(), localId);
			removeUser(usr);
			this.eventManager.logEvent(usr.getGridId(), AuditConstants.SYSTEM_ID, FederationAudit.ACCOUNT_REMOVED.value(), usr.getFirstName() + " " + usr.getLastName()
					+ "'s account was removed because their local account (" + localId + ") with the Dorian IdP was removed.");
		} catch (InvalidUserException e) {

		} catch (InvalidTrustedIdPException f) {
			logger.error(f.getMessage(), f);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected error occurred removing the grid user, the IdP "
					+ idpCert.getSubjectDN().getName() + " could not be resolved!!!");
			throw fault;
		}
	}

	private void removeUser(GridUser usr) throws DorianInternalException, InvalidUserException {
		try {
			um.removeUser(usr);
			this.userCertificateManager.removeCertificates(usr.getGridId());

			List<HostCertificateRecord> records = this.hostManager.getHostCertificateRecords(usr.getGridId());
			boolean updateCRL = false;
			for (int i = 0; i < records.size(); i++) {
				HostCertificateRecord r = records.get(i);
				if ((r.getStatus().equals(HostCertificateStatus.ACTIVE)) || (r.getStatus().equals(HostCertificateStatus.SUSPENDED))) {
					HostCertificateUpdate update = new HostCertificateUpdate();
					update.setId(r.getId());
					update.setStatus(HostCertificateStatus.COMPROMISED);
					this.hostManager.updateHostCertificateRecord(update);
					this.eventManager.logEvent(String.valueOf(r.getId()), AuditConstants.SYSTEM_ID, FederationAudit.HOST_CERTIFICATE_UPDATED.value(),
							"The status of the certificate for the host " + r.getHost() + " (" + r.getId() + "), was changed from " + r.getStatus().value() + " to "
									+ HostCertificateStatus.COMPROMISED.value() + ", because its owner's (" + r.getOwner() + ") account was removed.");
					updateCRL = true;
				} else if (r.getStatus().equals(HostCertificateStatus.PENDING)) {
					HostCertificateUpdate update = new HostCertificateUpdate();
					update.setId(r.getId());
					update.setStatus(HostCertificateStatus.REJECTED);
					this.hostManager.updateHostCertificateRecord(update);
					this.eventManager.logEvent(
							String.valueOf(r.getId()),
							AuditConstants.SYSTEM_ID,
							FederationAudit.HOST_CERTIFICATE_UPDATED.value(),
							"The status of the certificate for the host " + r.getHost() + " (" + r.getId() + "), was changed from " + r.getStatus().value() + " to "
									+ HostCertificateStatus.REJECTED.value() + ", because its owner's (" + r.getOwner() + ") account was removed.");
				}
			}

			if (this.administrators.isMember(usr.getGridId())) {
				this.administrators.removeMember(usr.getGridId());
				this.eventManager.logEvent(usr.getGridId(), AuditConstants.SYSTEM_ID, FederationAudit.ADMIN_REMOVED.value(), "Administrative privileges were revoked for the user " + usr.getGridId()
						+ " because the account was removed.");
			}
			this.groupManager.removeUserFromAllGroups(usr.getGridId());

			if (updateCRL) {
				publishCRL();
			}
		} catch (InvalidUserException e) {
			throw e;
		} catch (GroupException e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected error occurred in removing the user from all groups.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} catch (InvalidHostCertificateException e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected error occurred.");
			FaultHelper.addCause(fault, e.getFault());
		}
	}

	public void removeUser(String callerGridIdentity, GridUser usr) throws DorianInternalException, InvalidUserException, PermissionDeniedException {
		try {
			GridUser caller = um.getUser(callerGridIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			removeUser(usr);
			this.eventManager.logEvent(usr.getGridId(), callerGridIdentity, FederationAudit.ACCOUNT_REMOVED.value(), usr.getFirstName() + " " + usr.getLastName()
					+ "'s account was removed by the administrator " + callerGridIdentity + ".");
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in removing the grid user account " + usr.getGridId() + ": ";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to remove grid user accounts:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public void addAdmin(String callerGridIdentity, String gridIdentity) throws RemoteException, DorianInternalException, PermissionDeniedException {
		GridUser caller = getUser(callerGridIdentity);
		try {
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			try {
				if (!this.administrators.isMember(gridIdentity)) {
					GridUser admin = getUser(gridIdentity);
					verifyActiveUser(admin);
					this.administrators.addMember(gridIdentity);
					this.eventManager.logEvent(gridIdentity, callerGridIdentity, FederationAudit.ADMIN_ADDED.value(), "The user " + gridIdentity + " was granted administrator privileges by "
							+ callerGridIdentity + ".");
				}
			} catch (GroupException e) {
				logger.error(e.getMessage(), e);
				DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected error occurred in adding the user to the administrators group.");
				FaultHelper.addMessage(fault, e.getMessage());
				throw fault;
			}

		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in granting administrative privileges to " + gridIdentity + ":";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to grant administrative privileges:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public void removeAdmin(String callerGridIdentity, String gridIdentity) throws RemoteException, DorianInternalException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			try {
				this.administrators.removeMember(gridIdentity);
				this.eventManager.logEvent(gridIdentity, callerGridIdentity, FederationAudit.ADMIN_REMOVED.value(), "The administrative privileges for the user, " + gridIdentity + " were revoked by "
						+ callerGridIdentity + ".");
			} catch (GroupException e) {
				logger.error(e.getMessage(), e);
				DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected error occurred in removing the user from the administrators group.");
				FaultHelper.addMessage(fault, e.getMessage());
				throw fault;
			}

		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in revoking administrative privileges from " + gridIdentity + ":";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to revoke administrative privileges:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public String[] getAdmins(String callerGridIdentity) throws RemoteException, DorianInternalException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			try {
				List<String> members = this.administrators.getMembers();
				String[] admins = new String[members.size()];
				for (int i = 0; i < members.size(); i++) {
					admins[i] = (String) members.get(i);
				}
				return admins;
			} catch (GroupException e) {
				logger.error(e.getMessage(), e);
				DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected error occurred determining the members of the administrators group.");
				FaultHelper.addMessage(fault, e.getMessage());
				throw fault;
			}
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in listing users with administrative privileges:";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to list users with administrative privileges:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public X509Certificate requestUserCertificate(SAMLAssertion saml, PublicKey publicKey, CertificateLifetime lifetime, CertificateSignatureAlgorithm sa) throws DorianInternalException,
			InvalidAssertionException, UserPolicyException, PermissionDeniedException {
		TrustedIdP idp = null;
		try {
			idp = tm.getTrustedIdP(saml);
		} catch (InvalidAssertionException e) {
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST.value(), Utils.getExceptionMessage(e));
			throw e;
		}

		String uid = null;
		try {
			uid = this.getAttribute(saml, idp.getUserIdAttributeDescriptor().getNamespaceURI(), idp.getUserIdAttributeDescriptor().getName());
		} catch (InvalidAssertionException e) {
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST.value(), Utils.getExceptionMessage(e));
			throw e;
		}

		String gid = null;

		try {
			gid = CommonUtils.subjectToIdentity(UserManager.getUserSubject(this.conf.getIdentityAssignmentPolicy(), caManager.getDefaultCertificateAuthority().getCACertificate().getSubjectDN()
					.getName(), idp, uid));
		} catch (Exception e) {
			String msg = "An unexpected error occurred in determining the grid identity for the user.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), msg + "\n" + Utils.getExceptionMessage(e) + "\n\n" + e.getMessage());
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, msg);
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}

		// Determine whether or not the assertion is expired
		Calendar cal = new GregorianCalendar();
		Date now = cal.getTime();
		if ((now.before(saml.getNotBefore())) || (now.after(saml.getNotOnOrAfter()))) {
			String msg = "The Assertion is not valid at " + now + ", the assertion is valid from " + saml.getNotBefore() + " to " + saml.getNotOnOrAfter() + ".";
			this.eventManager.logEvent(gid, AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST.value(), msg);
			InvalidAssertionException fault = FaultHelper.createFaultException(InvalidAssertionException.class, msg);
			throw fault;
		}

		// Make sure the assertion is trusted

		SAMLAuthenticationStatement auth = getAuthenticationStatement(saml);

		// We need to verify the authentication method now
		boolean allowed = false;
		for (int i = 0; i < idp.getAuthenticationMethod().size(); i++) {
			if (idp.getAuthenticationMethod().get(i).value().equals(auth.getAuthMethod())) {
				allowed = true;
			}
		}
		if (!allowed) {
			String msg = "The authentication method " + auth.getAuthMethod() + " is not acceptable for the IdP " + idp.getName() + ".";
			this.eventManager.logEvent(gid, AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST.value(), msg);
			InvalidAssertionException fault = FaultHelper.createFaultException(InvalidAssertionException.class, msg);
			throw fault;
		}

		String email = null;
		String firstName = null;
		String lastName = null;

		try {
			email = this.getAttribute(saml, idp.getEmailAttributeDescriptor().getNamespaceURI(), idp.getEmailAttributeDescriptor().getName());
			firstName = this.getAttribute(saml, idp.getFirstNameAttributeDescriptor().getNamespaceURI(), idp.getFirstNameAttributeDescriptor().getName());
			lastName = this.getAttribute(saml, idp.getLastNameAttributeDescriptor().getNamespaceURI(), idp.getLastNameAttributeDescriptor().getName());
			AddressValidator.validateEmail(email);
		} catch (InvalidAssertionException e) {
			this.eventManager.logEvent(gid, AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST.value(), Utils.getExceptionMessage(e));
			throw e;
		}

		// If the user does not exist, add them
		GridUser usr = null;
		if (!um.determineIfUserExists(idp.getId(), uid)) {
			try {
				usr = new GridUser();
				usr.setIdPId(idp.getId());
				usr.setUID(uid);
				usr.setFirstName(firstName);
				usr.setLastName(lastName);
				usr.setEmail(email);
				usr.setUserStatus(GridUserStatus.PENDING);
				usr = um.addUser(idp, usr);
				this.eventManager.logEvent(gid, AuditConstants.SYSTEM_ID, FederationAudit.ACCOUNT_CREATED.value(), "User Account Created!!!");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				String msg = "An unexpected error occurred in adding the user " + usr.getUID() + " from the IdP " + idp.getName() + ".";
				this.eventManager.logEvent(gid, AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST.value(), msg + "\n" + e.getMessage());
				this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(),
						msg + "\n" + Utils.getExceptionMessage(e) + "\n\n" + e.getMessage());
				DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, msg);
				FaultHelper.addMessage(fault, e.getMessage());
				throw fault;
			}
		} else {
			try {
				usr = um.getUser(idp.getId(), uid);
				boolean performUpdate = false;

				if ((usr.getFirstName() == null) || (!usr.getFirstName().equals(firstName))) {
					usr.setFirstName(firstName);
					performUpdate = true;
				}
				if ((usr.getLastName() == null) || (!usr.getLastName().equals(lastName))) {
					usr.setLastName(lastName);
					performUpdate = true;
				}
				if ((usr.getEmail() == null) || (!usr.getEmail().equals(email))) {
					usr.setEmail(email);
					performUpdate = true;
				}
				if (performUpdate) {
					GridUser orig = um.getUser(idp.getId(), uid);
					um.updateUser(usr);
					this.eventManager.logEvent(usr.getGridId(), AuditConstants.SYSTEM_ID, FederationAudit.ACCOUNT_UPDATED.value(), ReportUtils.generateReport(orig, usr));
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				String msg = "An unexpected error occurred in obtaining/updating the user " + usr.getUID() + " from the IdP " + idp.getName() + ".";
				this.eventManager.logEvent(gid, AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST.value(), msg + "\n" + e.getMessage());
				this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(),
						msg + "\n" + Utils.getExceptionMessage(e) + "\n\n" + e.getMessage());
				DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, msg);
				FaultHelper.addMessage(fault, e.getMessage());
				throw fault;
			}
		}

		// Validate that the certificate is of valid length

		if (FederationUtils.getProxyValid(lifetime).after(FederationUtils.getMaxProxyLifetime(conf))) {
			String msg = "The requested certificate lifetime exceeds the maximum certificate lifetime (hrs=" + conf.getUserCertificateLifetime().getHours() + ", mins="
					+ conf.getUserCertificateLifetime().getMinutes() + ", sec=" + conf.getUserCertificateLifetime().getSeconds() + ")";
			this.eventManager.logEvent(usr.getGridId(), AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST.value(), msg);
			UserPolicyException fault = FaultHelper.createFaultException(UserPolicyException.class, msg);
			throw fault;
		}

		// Run the policy
		AccountPolicy policy = null;
		try {
			Class c = Class.forName(idp.getUserPolicyClass());
			policy = (AccountPolicy) c.newInstance();
			policy.configure(conf, um);

		} catch (Exception e) {
			String msg = "An unexpected error occurred in creating an instance of the user policy " + idp.getUserPolicyClass() + ".";
			this.eventManager.logEvent(usr.getGridId(), AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST.value(), msg + "\n" + e.getMessage());
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), msg + "\n" + Utils.getExceptionMessage(e) + "\n\n" + e.getMessage());
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, msg);
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
		policy.applyPolicy(idp, usr);

		// Check to see if authorized
		try {
			this.verifyActiveUser(usr);
		} catch (PermissionDeniedException e) {
			this.eventManager.logEvent(usr.getGridId(), AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST.value(), Utils.getExceptionMessage(e));
			throw e;
		}

		// create user certificate
		try {
			String caSubject = caManager.getDefaultCertificateAuthority().getCACertificate().getSubjectDN().getName();
			String sub = um.getUserSubject(caSubject, idp, usr.getUID());
			Calendar c1 = new GregorianCalendar();
			c1.add(Calendar.SECOND, CERTIFICATE_START_OFFSET_SECONDS);
			Date start = c1.getTime();
			Calendar c2 = new GregorianCalendar();
			c2.add(Calendar.HOUR, lifetime.getHours());
			c2.add(Calendar.MINUTE, lifetime.getMinutes());
			c2.add(Calendar.SECOND, lifetime.getSeconds());
			Date end = c2.getTime();
			X509Certificate userCert = caManager.getDefaultCertificateAuthority().signCertificate(sub, publicKey, start, end, sa);
			userCertificateManager.addUserCertifcate(usr.getGridId(), userCert);
			this.eventManager.logEvent(usr.getGridId(), AuditConstants.SYSTEM_ID, FederationAudit.SUCCESSFUL_USER_CERTIFICATE_REQUEST.value(), "User certificate (" + userCert.getSerialNumber()
					+ ") successfully issued for " + usr.getGridId() + ".");
			return userCert;
		} catch (Exception e) {
			String msg = "An unexpected error occurred in creating a certificate for the user " + usr.getGridId() + ".";
			this.eventManager.logEvent(usr.getGridId(), AuditConstants.SYSTEM_ID, FederationAudit.INVALID_USER_CERTIFICATE_REQUEST.value(), msg + "\n" + e.getMessage());
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), msg + "\n" + Utils.getExceptionMessage(e) + "\n\n" + e.getMessage());
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, msg);
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}

	}

	// ///////////////////////////////
	/* HOST CERTIFICATE OPERATIONS */
	// ///////////////////////////////
	public HostCertificateRecord requestHostCertificate(String callerGridId, HostCertificateRequest req) throws DorianInternalException, InvalidHostCertificateRequestException,
			InvalidHostCertificateException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridId);
			verifyActiveUser(caller);
			long id = hostManager.requestHostCertifcate(callerGridId, req);
			this.eventManager.logEvent(String.valueOf(id), callerGridId, FederationAudit.HOST_CERTIFICATE_REQUESTED.value(), "Host certificate requested for " + req.getHostname() + ".");
			HostCertificateRecord record = null;
			if (this.conf.autoHostCertificateApproval()) {
				record = hostManager.approveHostCertifcate(id);
				this.eventManager.logEvent(String.valueOf(id), AuditConstants.SYSTEM_ID, FederationAudit.HOST_CERTIFICATE_APPROVED.value(), "The host certificate for the host " + req.getHostname()
						+ " was automatically approved.");
			} else {
				record = hostManager.getHostCertificateRecord(id);
			}

			return record;
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in requesting a host certificate for the host " + req.getHostname() + ":";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to request a certificate:";
			this.eventManager.logEvent(callerGridId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public HostCertificateRecord[] getHostCertificatesForCaller(String callerGridId) throws DorianInternalException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridId);
			verifyActiveUser(caller);
			List<HostCertificateRecord> list = hostManager.getHostCertificateRecords(callerGridId);
			HostCertificateRecord[] records = new HostCertificateRecord[list.size()];
			for (int i = 0; i < list.size(); i++) {
				records[i] = list.get(i);
			}

			return records;

		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in obtaining the host certificates owned by " + callerGridId + ":";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to obtain a list of host certificates:";
			this.eventManager.logEvent(callerGridId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public HostCertificateRecord approveHostCertificate(String callerGridId, long recordId) throws DorianInternalException, InvalidHostCertificateException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridId);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			HostCertificateRecord record = hostManager.approveHostCertifcate(recordId);
			this.eventManager.logEvent(String.valueOf(recordId), callerGridId, FederationAudit.HOST_CERTIFICATE_APPROVED.value(), "The host certificate for the host " + record.getHost()
					+ " was approved by " + callerGridId + ".");
			return record;
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in approving the host certificate " + recordId + ":";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to approve host certificates:";
			this.eventManager.logEvent(callerGridId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public HostCertificateRecord[] findHostCertificates(String callerGridId, HostCertificateFilter f) throws DorianInternalException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridId);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			List<HostCertificateRecord> list = hostManager.findHostCertificates(f);
			HostCertificateRecord[] records = new HostCertificateRecord[list.size()];
			for (int i = 0; i < list.size(); i++) {
				records[i] = list.get(i);
			}
			return records;
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in searching for host certificates:";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to search for host certificates:";
			this.eventManager.logEvent(callerGridId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public void updateHostCertificateRecord(String callerGridId, HostCertificateUpdate update) throws DorianInternalException, InvalidHostCertificateException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridId);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			// We need to make sure that if the owner changed, that the owner is
			// an
			// active user.
			HostCertificateRecord record = hostManager.getHostCertificateRecord(update.getId());
			if (update.getOwner() != null) {
				if (!record.getOwner().equals(update.getOwner())) {
					try {
						verifyActiveUser(getUser(update.getOwner()));
					} catch (PermissionDeniedException f) {
						InvalidHostCertificateException fault = FaultHelper.createFaultException(InvalidHostCertificateException.class, "The owner specified does not exist or is not an active user.");
						throw fault;
					}
				}
			}
			hostManager.updateHostCertificateRecord(update);
			HostCertificateRecord updated = hostManager.getHostCertificateRecord(update.getId());
			this.eventManager.logEvent(String.valueOf(record.getId()), callerGridId, FederationAudit.HOST_CERTIFICATE_UPDATED.value(), ReportUtils.generateReport(record, updated));
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in updating a host certificate:";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to update host certificates:";
			this.eventManager.logEvent(callerGridId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public HostCertificateRecord renewHostCertificate(String callerGridId, long recordId) throws DorianInternalException, InvalidHostCertificateException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridId);
			if (conf.getHostCertificateRenewalPolicy().equals(HostCertificateRenewalPolicy.ADMIN.value())) {
				verifyActiveUser(caller);
				verifyAdminUser(caller);
			} else if (conf.getHostCertificateRenewalPolicy().equals(HostCertificateRenewalPolicy.OWNER.value())) {
				verifyActiveUser(caller);
				HostCertificateRecord r = hostManager.getHostCertificateRecord(recordId);
				if (!callerGridId.equals(r.getOwner())) {
					if (!this.administrators.isMember(callerGridId)) {
						PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "Cannot renew the requested host certificate, you are not the owner.");
						throw fault;
					}
				}
			} else {
				PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "Could not determine if you have permission to renew host certificates.");
				throw fault;
			}
			HostCertificateRecord record = hostManager.renewHostCertificate(recordId);
			this.eventManager.logEvent(String.valueOf(recordId), callerGridId, FederationAudit.HOST_CERTIFICATE_RENEWED.value(), "The host certificate for the host " + record.getHost()
					+ " was renewed by " + callerGridId + ".");
			return record;
		} catch (GroupException e) {
			String mess = "An unexpected error occurred in determining if the user is a member of the administrators group.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, mess);
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in renewing the host certificate " + recordId + ":";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to renew the host certificates " + recordId + ":";
			this.eventManager.logEvent(callerGridId, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public void publishCRL() {
		// TODO depends on GTS
		// if (publishCRL) {
		// if ((conf.getCRLPublishingList() != null)
		// && (conf.getCRLPublishingList().size() > 0)) {
		// Runner runner = new Runner() {
		// public void execute() {
		// synchronized (mutex) {
		// List<String> services = conf.getCRLPublishingList();
		// try {
		// X509CRL crl = getCRL();
		// gov.nih.nci.cagrid.gts.bean.X509CRL x509 = new
		// gov.nih.nci.cagrid.gts.bean.X509CRL();
		// x509.setCrlEncodedString(CertUtil.writeCRL(crl));
		// String authName = ca.getCACertificate()
		// .getSubjectDN().getName();
		// for (int i = 0; i < services.size(); i++) {
		// String uri = services.get(i);
		// try {
		// debug("Publishing CRL to the GTS "
		// + uri);
		// GTSAdminClient client = new GTSAdminClient(
		// uri, null);
		// client.updateCRL(authName, x509);
		// debug("Published CRL to the GTS " + uri);
		// eventManager.logEvent(
		// AuditConstants.SYSTEM_ID,
		// AuditConstants.SYSTEM_ID,
		// FederationAudit.CRLPublished
		// .value(),
		// "Published CRL to the GTS "
		// + uri + ".");
		// } catch (Exception ex) {
		// String msg = "Error publishing the CRL to the GTS "
		// + uri + "!!!";
		// getLog().error(msg, ex);
		// eventManager.logEvent(
		// AuditConstants.SYSTEM_ID,
		// AuditConstants.SYSTEM_ID,
		// FederationAudit.INTERNAL_ERROR
		// .value(), msg + "\n"
		// + ed.getMessage());
		// }
		//
		// }
		//
		// } catch (Exception e) {
		// String msg = "Unexpected Error publishing the CRL!!!";
		// getLog().error(msg, e);
		// eventManager.logEvent(AuditConstants.SYSTEM_ID,
		// AuditConstants.SYSTEM_ID,
		// FederationAudit.INTERNAL_ERROR.value(),
		// msg + "\n" + e.getMessage());
		// }
		// }
		// }
		// };
		// try {
		// threadManager.executeInBackground(runner);
		// } catch (Exception t) {
		// t.getMessage();
		// }
		// }
		// }
	}

	private void addToCRLList(Map<String, Map<Long, CRLEntry>> crls, String issuer, BigInteger serialNumber) {

		if (crls.containsKey(issuer)) {
			Map<Long, CRLEntry> list = crls.get(issuer);
			if (!list.containsKey(serialNumber)) {
				CRLEntry entry = new CRLEntry(serialNumber, CRLReason.PRIVILEGE_WITHDRAWN);
				list.put(serialNumber.longValue(), entry);
			}
		} else {
			Map<Long, CRLEntry> list = new HashMap<Long, CRLEntry>();
			CRLEntry entry = new CRLEntry(serialNumber, CRLReason.PRIVILEGE_WITHDRAWN);
			list.put(serialNumber.longValue(), entry);
			crls.put(issuer, list);
		}

	}

	public Map<String, X509CRL> getCRL(CertificateSignatureAlgorithm sa) throws DorianInternalException {

		Map<String, Map<Long, CRLEntry>> crls = new HashMap<String, Map<Long, CRLEntry>>();
		Set<String> users = this.um.getDisabledUsers();
		Iterator<String> itr = users.iterator();
		while (itr.hasNext()) {
			String gid = itr.next();
			List<BigInteger> userCerts = this.userCertificateManager.getActiveCertificates(gid);
			for (BigInteger sn : userCerts) {
				try {
					UserCertificateRecord record = this.userCertificateManager.getUserCertificateRecord(sn.longValue());
					X509Certificate cert = CertUtil.loadCertificate(record.getCertificate().getCertificateAsString());
					this.addToCRLList(crls, cert.getIssuerDN().getName(), sn);
				} catch (Exception e) {
					logger.error("An unexpected error occured loading the user certificate, " + sn.longValue() + ": " + e.getMessage(), e);
				}
			}

			List<Long> hostCerts = this.hostManager.getHostCertificateRecordsSerialNumbers(gid);
			for (Long sn : hostCerts) {
				try {
					HostCertificateRecord record = this.hostManager.getHostCertificateRecordBySerialNumber(sn);
					X509Certificate cert = CertUtil.loadCertificate(record.getCertificate().getCertificateAsString());
					this.addToCRLList(crls, cert.getIssuerDN().getName(), BigInteger.valueOf(sn));
				} catch (Exception e) {
					logger.error("An unexpected error occured loading the host certificate, " + sn + ": " + e.getMessage(), e);
				}
			}
		}

		List<BigInteger> compromisedUserCerts = this.userCertificateManager.getCompromisedCertificates();
		for (BigInteger sn : compromisedUserCerts) {
			try {
				UserCertificateRecord record = this.userCertificateManager.getUserCertificateRecord(sn.longValue());
				X509Certificate cert = CertUtil.loadCertificate(record.getCertificate().getCertificateAsString());
				this.addToCRLList(crls, cert.getIssuerDN().getName(), sn);
			} catch (Exception e) {
				logger.error("An unexpected error occured loading the user certificate, " + sn.longValue() + ": " + e.getMessage(), e);
			}
		}

		List<Long> hosts = this.hostManager.getDisabledHostCertificatesSerialNumbers();
		for (Long sn : hosts) {
			try {
				HostCertificateRecord record = this.hostManager.getHostCertificateRecordBySerialNumber(sn);
				X509Certificate cert = CertUtil.loadCertificate(record.getCertificate().getCertificateAsString());
				this.addToCRLList(crls, cert.getIssuerDN().getName(), BigInteger.valueOf(sn));
			} catch (Exception e) {
				logger.error("An unexpected error occured loading the host certificate, " + sn.longValue() + ": " + e.getMessage(), e);
			}
		}

		List<Long> blist = this.blackList.getBlackList();

		for (Long sn : blist) {
			try {
				X509Certificate cert = this.blackList.getCertificate(sn);
				this.addToCRLList(crls, cert.getIssuerDN().getName(), BigInteger.valueOf(sn));
			} catch (Exception e) {
				logger.error("An unexpected error occured loading the black listed certificate, " + sn.longValue() + ": " + e.getMessage(), e);
			}
		}

		Map<String, X509CRL> crlMap = new HashMap<String, X509CRL>();
		// We need to create a CRL for each certificate authority
		List<CertificateAuthority> caList = this.caManager.getCertificateAuthorities();
		for (CertificateAuthority ca : caList) {
			CRLEntry[] entries = null;
			String issuer = null;
			try {
				issuer = ca.getCACertificate().getSubjectDN().getName();

				if (crls.containsKey(issuer)) {
					Map<Long, CRLEntry> entryMap = crls.get(issuer);
					entries = new CRLEntry[entryMap.size()];
					Iterator<CRLEntry> itr2 = entryMap.values().iterator();
					int count = 0;
					while (itr2.hasNext()) {
						entries[count] = itr2.next();
						count++;
					}
				} else {
					entries = new CRLEntry[0];
				}
				X509CRL crl = this.caManager.getCertificateAuthority(issuer).getCRL(entries, sa);
				crlMap.put(issuer, crl);

			} catch (Exception e) {
				logger.error("Unexpected error obtaining the CRL for the issuer, " + issuer + ": " + e.getMessage() + ".", e);
			}
		}

//		
//		Iterator<String> crlItr = crls.keySet().iterator();
//
//		while (crlItr.hasNext()) {
//			String issuer = crlItr.next();
//			try {
//
//				Map<Long, CRLEntry> entryMap = crls.get(issuer);
//
//				CRLEntry[] entries = new CRLEntry[entryMap.size()];
//				Iterator<CRLEntry> itr2 = entryMap.values().iterator();
//				int count = 0;
//				while (itr2.hasNext()) {
//					entries[count] = itr2.next();
//					count++;
//				}
//				X509CRL crl = this.caManager.getCertificateAuthority(issuer).getCRL(entries, sa);
//				crlMap.put(issuer, crl);
//
//			} catch (Exception e) {
//				logger.error("Unexpected error obtaining the CRL for the issuer, " + issuer + ": " + e.getMessage() + ".", e);
//			}
//		}
		return crlMap;
	}

	private GridUser getUser(String gridId) throws DorianInternalException, PermissionDeniedException {
		try {
			return um.getUser(gridId);
		} catch (InvalidUserException f) {
			PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "You are not a valid user!!!");
			throw fault;
		}
	}

	private void verifyAdminUser(GridUser usr) throws DorianInternalException, PermissionDeniedException {
		try {
			if (administrators.isMember(usr.getGridId())) {
				return;
			} else {
				PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "You are NOT an Administrator!!!");
				throw fault;
			}

		} catch (GroupException e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class,
					"An unexpected error occurred in determining if the user is a member of the administrators group.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	private void verifyActiveUser(GridUser usr) throws DorianInternalException, PermissionDeniedException {

		try {
			TrustedIdP idp = this.tm.getTrustedIdPById(usr.getIdPId());

			if (!idp.getStatus().equals(TrustedIdPStatus.ACTIVE)) {
				PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "Access for your Identity Provider has been suspended!!!");
				throw fault;
			}
		} catch (InvalidTrustedIdPException f) {
			PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "Unexpected error in determining your Identity Provider has been suspended!!!");
			throw fault;
		}

		if (!usr.getUserStatus().equals(GridUserStatus.ACTIVE)) {
			if (usr.getUserStatus().equals(GridUserStatus.SUSPENDED)) {
				PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "The account has been suspended.");
				throw fault;

			} else if (usr.getUserStatus().equals(GridUserStatus.REJECTED)) {
				PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "The request for an account was rejected.");
				throw fault;

			} else if (usr.getUserStatus().equals(GridUserStatus.PENDING)) {
				PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "The request for an account has not been reviewed.");
				throw fault;
			} else {
				PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "Unknown Reason");
				throw fault;
			}
		}

	}

	protected UserManager getUserManager() {
		return um;
	}

	private String getAttribute(SAMLAssertion saml, String namespace, String name) throws InvalidAssertionException {
		Iterator itr = saml.getStatements();
		while (itr.hasNext()) {
			Object o = itr.next();
			if (o instanceof SAMLAttributeStatement) {
				SAMLAttributeStatement att = (SAMLAttributeStatement) o;
				Iterator attItr = att.getAttributes();
				while (attItr.hasNext()) {
					SAMLAttribute a = (SAMLAttribute) attItr.next();
					if ((a.getNamespace().equals(namespace)) && (a.getName().equals(name))) {
						Iterator vals = a.getValues();
						while (vals.hasNext()) {

							String val = Utils.clean((String) vals.next());
							if (val != null) {
								return val;
							}
						}
					}
				}
			}
		}
		InvalidAssertionException fault = FaultHelper.createFaultException(InvalidAssertionException.class, "The assertion does not contain the required attribute, " + namespace + ":" + name);
		throw fault;
	}

	private SAMLAuthenticationStatement getAuthenticationStatement(SAMLAssertion saml) throws InvalidAssertionException {
		Iterator itr = saml.getStatements();
		SAMLAuthenticationStatement auth = null;
		while (itr.hasNext()) {
			Object o = itr.next();
			if (o instanceof SAMLAuthenticationStatement) {
				if (auth != null) {
					InvalidAssertionException fault = FaultHelper.createFaultException(InvalidAssertionException.class, "The assertion specified contained more that one authentication statement.");
					throw fault;
				}
				auth = (SAMLAuthenticationStatement) o;
			}
		}
		if (auth == null) {
			InvalidAssertionException fault = FaultHelper.createFaultException(InvalidAssertionException.class, "No authentication statement specified in the assertion provided.");
			throw fault;
		}
		return auth;
	}

	public void clearDatabase() throws DorianInternalException {
		this.um.clearDatabase();
		this.tm.clearDatabase();
		try {
			this.groupManager.clearDatabase();
		} catch (GroupException e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected error occurred in deleting the groups database.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
		this.userCertificateManager.clearDatabase();
		this.hostManager.clearDatabase();
		this.blackList.clearDatabase();
		List<CertificateAuthority> list = this.caManager.getCertificateAuthorities();
		for (CertificateAuthority ca : list) {
			try {
				ca.clearCertificateAuthority();
			} catch (CertificateAuthorityException e) {
				DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, e.getMessage());
				FaultHelper.addCause(fault, e.getFault());
				throw fault;
			}
		}
		try {
			this.gridAccountAuditor.clear();
			this.federationAuditor.clear();
			this.hostAuditor.clear();
			this.userCertificateAuditor.clear();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "An unexpected error occurred in deleting the auditing logs.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public TrustedIdentityProviders getTrustedIdentityProviders() throws DorianInternalException {
		try {
			TrustedIdentityProviders idps = new TrustedIdentityProviders();
			TrustedIdP[] list1 = this.tm.getTrustedIdPs();
			if (list1 != null) {
				List<TrustedIdentityProvider> list2 = new ArrayList<TrustedIdentityProvider>();
				for (int i = 0; i < list1.length; i++) {
					if ((list1[i].getStatus().equals(TrustedIdPStatus.ACTIVE)) && tm.getPublish(list1[i])) {
						TrustedIdentityProvider idp = new TrustedIdentityProvider();
						idp.setName(list1[i].getName());
						idp.setDisplayName(list1[i].getDisplayName());
						idp.setAuthenticationServiceURL(list1[i].getAuthenticationServiceURL());
						idp.setAuthenticationServiceIdentity(list1[i].getAuthenticationServiceIdentity());
						list2.add(idp);
					}
				}

				TrustedIdentityProvider[] list3 = new TrustedIdentityProvider[list2.size()];
				for (int i = 0; i < list2.size(); i++) {
					list3[i] = list2.get(i);
				}
				idps.getTrustedIdentityProvider().addAll(Arrays.asList(list3));
			}
			return idps;

		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in obtaining a list of trusted identity providers :";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (InvalidTrustedIdPException e) {
			String mess = "An unexpected error occurred in obtaining a trusted identity provider :";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, e.getMessage());
			throw fault;
		}
	}

	public List<UserCertificateRecord> findUserCertificateRecords(String callerIdentity, UserCertificateFilter f) throws DorianInternalException, InvalidUserCertificateException,
			PermissionDeniedException {
		try {
			GridUser caller = getUser(callerIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			return this.userCertificateManager.findUserCertificateRecords(f);
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in searching for user certificates:";

			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to search for user certificates:";
			this.eventManager.logEvent(callerIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public void updateUserCertificateRecord(String callerIdentity, UserCertificateUpdate update) throws DorianInternalException, InvalidUserCertificateException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			UserCertificateRecord original = this.userCertificateManager.getUserCertificateRecord(update.getSerialNumber());
			this.userCertificateManager.updateUserCertificateRecord(update);
			UserCertificateRecord updated = this.userCertificateManager.getUserCertificateRecord(update.getSerialNumber());
			this.eventManager.logEvent(String.valueOf(original.getSerialNumber()), callerIdentity, FederationAudit.USER_CERTIFICATE_UPDATED.value(), ReportUtils.generateReport(original, updated));
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in updating a user certificate:";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to update user certificates:";
			this.eventManager.logEvent(callerIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public void removeUserCertificate(String callerIdentity, long serialNumber) throws DorianInternalException, InvalidUserCertificateException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			this.userCertificateManager.removeCertificate(serialNumber);
			this.eventManager.logEvent(String.valueOf(serialNumber), callerIdentity, FederationAudit.USER_CERTIFICATE_REMOVED.value(), "User certificate (" + serialNumber + ") removed by "
					+ callerIdentity + ".");
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in removing the host certificate " + serialNumber + ":";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to remove host certificates:";
			this.eventManager.logEvent(callerIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public List<FederationAuditRecord> performAudit(String callerIdentity, FederationAuditFilter f) throws DorianInternalException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			List<EventAuditor> handlers = new ArrayList<EventAuditor>();
			handlers.add(this.federationAuditor);
			handlers.add(this.hostAuditor);
			handlers.add(this.gridAccountAuditor);
			handlers.add(this.userCertificateAuditor);
			List<FederationAuditRecord> list = new ArrayList<FederationAuditRecord>();
			for (int i = 0; i < handlers.size(); i++) {
				EventAuditor eh = handlers.get(i);
				if (f == null) {
					f = new FederationAuditFilter();
				}
				String eventType = null;
				if (f.getAuditType() != null) {
					eventType = f.getAuditType().value();
				}

				Date start = null;
				Date end = null;

				if (f.getStartDate() != null) {
					start = f.getStartDate().getTime();
				}
				if (f.getEndDate() != null) {
					end = f.getEndDate().getTime();
				}

				try {
					List<Event> events = eh.findEvents(f.getTargetId(), f.getReportingPartyId(), eventType, start, end, f.getAuditMessage());
					for (int j = 0; j < events.size(); j++) {
						Event e = events.get(j);
						FederationAuditRecord r = new FederationAuditRecord();
						r.setTargetId(e.getTargetId());
						r.setReportingPartyId(e.getReportingPartyId());
						r.setAuditType(FederationAudit.fromValue(e.getEventType()));
						Calendar c = new GregorianCalendar();
						c.setTimeInMillis(e.getOccurredAt());
						r.setOccurredAt(c);
						r.setAuditMessage(e.getMessage());
						list.add(r);
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					String msg = "An unexpected error occurred in searching the auditing logs.";
					this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(),
							msg + "\n" + Utils.getExceptionMessage(e) + "\n\n" + e.getMessage());
					DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, msg);
					FaultHelper.addMessage(fault, e.getMessage());
					throw fault;
				}
			}
			return list;

		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in performing an audit:";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to perform audits:";
			this.eventManager.logEvent(callerIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public List<HostRecord> hostSearch(String callerIdentity, HostSearchCriteria criteria) throws RemoteException, DorianInternalException, PermissionDeniedException {
		try {

			if (conf.getHostSearchPolicy().equals(SearchPolicyType.ADMIN.value())) {
				GridUser caller = getUser(callerIdentity);
				verifyActiveUser(caller);
				verifyAdminUser(caller);
			} else if (conf.getHostSearchPolicy().equals(SearchPolicyType.AUTHENTICATED.value())) {
				if ((callerIdentity == null) || (callerIdentity.equals(DorianConstants.ANONYMOUS_CALLER))) {
					PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "Authentication Required!!!");
					throw fault;
				}
			} else if (conf.getHostSearchPolicy().equals(SearchPolicyType.PUBLIC.value())) {
				// No security checks needed if public searches are allowed
			} else {
				DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unknown Search Policy Type: " + conf.getHostSearchPolicy());
				throw fault;
			}
			List<HostRecord> records = this.hostManager.getHostRecords(criteria);
			for (int i = 0; i < records.size(); i++) {
				HostRecord r = records.get(i);
				GridUserRecord g = this.um.getUserRecord(r.getOwner());
				r.setOwnerEmail(g.getEmail());
				r.setOwnerFirstName(g.getFirstName());
				r.setOwnerLastName(g.getLastName());
			}
			return records;
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in performing a host search:";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Under the configured host search policy (" + this.conf.getHostSearchPolicy() + "), the caller not permitted to perform the host search:";
			this.eventManager.logEvent(callerIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		} catch (InvalidUserException e) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unexpected exception in hostSearch");
			FaultHelper.addCause(fault, e.getFault());
			throw fault;
		}
	}

	public List<GridUserRecord> userSearch(String callerIdentity, GridUserSearchCriteria criteria) throws RemoteException, DorianInternalException, PermissionDeniedException {
		try {

			if (conf.getUserSearchPolicy().equals(SearchPolicyType.ADMIN.value())) {
				GridUser caller = getUser(callerIdentity);
				verifyActiveUser(caller);
				verifyAdminUser(caller);
			} else if (conf.getUserSearchPolicy().equals(SearchPolicyType.AUTHENTICATED.value())) {
				if ((callerIdentity == null) || (callerIdentity.equals(DorianConstants.ANONYMOUS_CALLER))) {
					PermissionDeniedException fault = FaultHelper.createFaultException(PermissionDeniedException.class, "Authentication Required!!!");
					throw fault;
				}
			} else if (conf.getUserSearchPolicy().equals(SearchPolicyType.PUBLIC.value())) {
				// No security checks needed if public searches are allowed
			} else {
				DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Unknown Search Policy Type: " + conf.getUserSearchPolicy());
				throw fault;
			}
			return um.getUsers(criteria);
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in performing user search:";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Under the configured user search policy (" + this.conf.getUserSearchPolicy() + "), the caller not permitted to perform user search:";
			this.eventManager.logEvent(callerIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public FederationPolicy getFederationPolicy() {
		FederationPolicy policy = new FederationPolicy();

		// Populate User Policy
		UserPolicy user = new UserPolicy();
		UserCertificateLifetime ucl = new UserCertificateLifetime();
		ucl.setHours(conf.getUserCertificateLifetime().getHours());
		ucl.setMinutes(conf.getUserCertificateLifetime().getMinutes());
		ucl.setSeconds(conf.getUserCertificateLifetime().getSeconds());
		user.setUserCertificateLifetime(ucl);
		policy.setUserPolicy(user);

		// Populate Host Policy
		HostPolicy host = new HostPolicy();
		host.setAdministrativeApprovalRequired(!this.conf.autoHostCertificateApproval());
		HostCertificateLifetime hcl = new HostCertificateLifetime();
		hcl.setYears(conf.getIssuedCertificateLifetime().getYears());
		hcl.setMonths(conf.getIssuedCertificateLifetime().getMonths());
		hcl.setDays(conf.getIssuedCertificateLifetime().getDays());
		hcl.setHours(conf.getIssuedCertificateLifetime().getHours());
		hcl.setMinutes(conf.getIssuedCertificateLifetime().getMinutes());
		hcl.setSeconds(conf.getIssuedCertificateLifetime().getSeconds());
		host.setHostCertificateLifetime(hcl);
		host.setHostCertificateRenewalPolicy(HostCertificateRenewalPolicy.fromValue(conf.getHostCertificateRenewalPolicy()));
		policy.setHostPolicy(host);

		// Populate Search Policy

		SearchPolicy search = new SearchPolicy();
		search.setHostSearchPolicy(SearchPolicyType.fromValue(conf.getHostSearchPolicy()));
		search.setUserSearchPolicy(SearchPolicyType.fromValue(conf.getUserSearchPolicy()));
		policy.setSearchPolicy(search);
		return policy;
	}

	public void setPublish(String callerGridIdentity, TrustedIdP idp, boolean publish) throws DorianInternalException, InvalidTrustedIdPException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			tm.setPublish(idp, publish);
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in updating the identity provider " + idp.getName() + " (" + idp.getId() + "):";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to update a trusted identity provider:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public boolean getPublish(String callerGridIdentity, TrustedIdP idp) throws DorianInternalException, InvalidTrustedIdPException, PermissionDeniedException {
		try {
			GridUser caller = getUser(callerGridIdentity);
			verifyActiveUser(caller);
			verifyAdminUser(caller);
			return tm.getPublish(idp);
		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in listing the trusted identity providers.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID, AuditConstants.SYSTEM_ID, FederationAudit.INTERNAL_ERROR.value(), mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to list trusted identity providers:";
			this.eventManager.logEvent(callerGridIdentity, AuditConstants.SYSTEM_ID, FederationAudit.ACCESS_DENIED.value(), mess + "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}
}
