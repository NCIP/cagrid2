package org.cagrid.dorian.idp.impl;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import java.rmi.RemoteException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.ca.impl.CertificateAuthority;
import org.cagrid.dorian.common.AuditConstants;
import org.cagrid.dorian.model.exceptions.DorianInternalException;
import org.cagrid.dorian.model.exceptions.InvalidUserPropertyException;
import org.cagrid.dorian.model.exceptions.NoSuchUserException;
import org.cagrid.dorian.model.exceptions.PermissionDeniedException;
import org.cagrid.dorian.model.federation.FederationAudit;
import org.cagrid.dorian.model.idp.AccountProfile;
import org.cagrid.dorian.model.idp.Application;
import org.cagrid.dorian.model.idp.ApplicationReview;
import org.cagrid.dorian.model.idp.IdentityProviderAudit;
import org.cagrid.dorian.model.idp.IdentityProviderAuditFilter;
import org.cagrid.dorian.model.idp.IdentityProviderAuditRecord;
import org.cagrid.dorian.model.idp.LocalUser;
import org.cagrid.dorian.model.idp.LocalUserFilter;
import org.cagrid.dorian.model.idp.LocalUserRole;
import org.cagrid.dorian.model.idp.LocalUserStatus;
import org.cagrid.dorian.policy.AccountInformationModificationPolicy;
import org.cagrid.dorian.policy.IdentityProviderPolicy;
import org.cagrid.dorian.policy.PasswordLockoutPolicy;
import org.cagrid.dorian.policy.PasswordPolicy;
import org.cagrid.dorian.policy.UserIdPolicy;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.gaards.authentication.Credential;
import org.cagrid.gaards.authentication.faults.AuthenticationProviderException;
import org.cagrid.gaards.authentication.faults.CredentialNotSupportedException;
import org.cagrid.gaards.authentication.faults.InvalidCredentialException;
import org.cagrid.tools.database.Database;
import org.cagrid.tools.events.Event;
import org.cagrid.tools.events.EventAuditor;
import org.cagrid.tools.events.EventManager;
import org.cagrid.tools.events.EventToHandlerMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */

public class IdentityProvider {

	private final static Logger log = LoggerFactory
			.getLogger(IdentityProvider.class);

	private UserManager userManager;

	private AssertionCredentialsManager assertionManager;

	private IdPRegistrationPolicy registrationPolicy;

	private EventManager eventManager;

	private EventAuditor identityProviderAuditor;

	private Database db;

	private IdentityProviderProperties conf;

	public IdentityProvider(IdentityProviderProperties conf, Database db,
			CertificateAuthority ca, EventManager eventManager)
			throws DorianInternalException {
		this.conf = conf;
		this.eventManager = eventManager;
		this.db = db;
		try {
			initializeEventManager();
			this.registrationPolicy = conf.getRegistrationPolicy();
			this.userManager = new UserManager(db, conf);
			this.assertionManager = new AssertionCredentialsManager(conf, ca,
					db);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			String message = "Error initializing the Identity Manager Provider.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
					AuditConstants.SYSTEM_ID, e.getMessage()
							+ FederationAudit.INTERNAL_ERROR.value(), message
							+ "\n\n" + e.getMessage());
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class, message);
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	private void initializeEventManager() throws DorianInternalException {
		try {
			if (this.eventManager
					.isHandlerRegistered(AuditingConstants.IDENTITY_PROVIDER_AUDITOR)) {
				this.identityProviderAuditor = (EventAuditor) this.eventManager
						.getEventHandler(AuditingConstants.IDENTITY_PROVIDER_AUDITOR);
			} else {
				this.identityProviderAuditor = new EventAuditor(
						AuditingConstants.IDENTITY_PROVIDER_AUDITOR, this.db,
						AuditingConstants.IDENTITY_PROVIDER_AUDITOR_DB);
				this.eventManager.registerHandler(this.identityProviderAuditor);
			}

			this.eventManager
					.registerEventWithHandler(new EventToHandlerMapping(
							IdentityProviderAudit.LOCAL_ACCOUNT_LOCKED.value(),
							AuditingConstants.IDENTITY_PROVIDER_AUDITOR));

			this.eventManager
					.registerEventWithHandler(new EventToHandlerMapping(
							IdentityProviderAudit.LOCAL_ACCOUNT_REMOVED.value(),
							AuditingConstants.IDENTITY_PROVIDER_AUDITOR));
			this.eventManager
					.registerEventWithHandler(new EventToHandlerMapping(
							IdentityProviderAudit.LOCAL_ACCOUNT_UPDATED.value(),
							AuditingConstants.IDENTITY_PROVIDER_AUDITOR));

			this.eventManager
					.registerEventWithHandler(new EventToHandlerMapping(
							IdentityProviderAudit.INVALID_LOGIN.value(),
							AuditingConstants.IDENTITY_PROVIDER_AUDITOR));
			this.eventManager
					.registerEventWithHandler(new EventToHandlerMapping(
							IdentityProviderAudit.PASSWORD_CHANGED.value(),
							AuditingConstants.IDENTITY_PROVIDER_AUDITOR));
			this.eventManager
					.registerEventWithHandler(new EventToHandlerMapping(
							IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(),
							AuditingConstants.IDENTITY_PROVIDER_AUDITOR));
			this.eventManager
					.registerEventWithHandler(new EventToHandlerMapping(
							IdentityProviderAudit.REGISTRATION.value(),
							AuditingConstants.IDENTITY_PROVIDER_AUDITOR));
			this.eventManager
					.registerEventWithHandler(new EventToHandlerMapping(
							IdentityProviderAudit.SUCCESSFUL_LOGIN.value(),
							AuditingConstants.IDENTITY_PROVIDER_AUDITOR));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			String mess = "An unexpected error occurred initializing the auditing system:\n"
					+ e.getMessage();
			DorianInternalException fault = FaultHelper.createFaultException(
					DorianInternalException.class, mess);
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public SAMLAssertion authenticate(Credential credential)
			throws AuthenticationProviderException, InvalidCredentialException,
			CredentialNotSupportedException {
		String uid = "UNKNOWN";
		if (credential.getClass().equals(BasicAuthentication.class)) {
			BasicAuthentication cred = (BasicAuthentication) credential;
			uid = cred.getUserId();
		}
		try {
			LocalUser requestor = userManager.authenticateAndVerifyUser(
					credential, this.eventManager);

			SAMLAssertion saml = assertionManager.getAuthenticationAssertion(
					requestor.getUserId(), requestor.getFirstName(),
					requestor.getLastName(), requestor.getEmail());
			this.eventManager.logEvent(requestor.getUserId(),
					AuditConstants.SYSTEM_ID,
					IdentityProviderAudit.SUCCESSFUL_LOGIN.value(),
					"Successful login using " + credential.getClass().getName()
							+ ".");
			return saml;
		} catch (InvalidCredentialException e) {
			this.eventManager.logEvent(uid, AuditConstants.SYSTEM_ID,
					IdentityProviderAudit.INVALID_LOGIN.value(),
					"Authentication Failed:\n\n" + e.getMessage());
			throw e;
		} catch (CredentialNotSupportedException e) {
			this.eventManager.logEvent(uid, AuditConstants.SYSTEM_ID,
					IdentityProviderAudit.INVALID_LOGIN.value(),
					"Authentication Failed:\n\n" + e.getMessage());
			throw e;
		} catch (DorianInternalException e) {
			log.error(e.getMessage(), e);
			String message = "An unexpected error occurred while trying to authenticate.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
					AuditConstants.SYSTEM_ID,
					FederationAudit.INTERNAL_ERROR.value(), message + "\n\n"
							+ e.getMessage());
			AuthenticationProviderException fault = FaultHelper
					.createFaultException(
							AuthenticationProviderException.class, message);
			FaultHelper.addCause(fault, e.getFault());
			throw fault;
		}
	}

	public void changePassword(BasicAuthentication credential,
			String newPassword) throws DorianInternalException,
			PermissionDeniedException, InvalidUserPropertyException {
		try {
			LocalUser requestor = userManager.authenticateAndVerifyUser(
					credential, this.eventManager);

			try {
				String newPasswordDigest = PasswordSecurityManager.encrypt(
						newPassword, requestor.getPasswordSecurity()
								.getDigestSalt());
				if (newPasswordDigest.equals(requestor.getPassword())) {
					InvalidUserPropertyException fault = FaultHelper
							.createFaultException(
									InvalidUserPropertyException.class,
									"Unacceptable password specified, cannot change password to be the same as the current password.");
					throw fault;
				}
			} catch (InvalidUserPropertyException e) {
				throw e;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				String message = "Could not changed password and unexpected error occurred calculating the password digest";
				this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
						AuditConstants.SYSTEM_ID,
						FederationAudit.INTERNAL_ERROR.value(), message
								+ "\n\n" + e.getMessage());
				DorianInternalException fault = FaultHelper
						.createFaultException(DorianInternalException.class,
								message);
				throw fault;
			}

			try {
				requestor.setPassword(newPassword);
				this.userManager.updateUser(requestor);
				this.eventManager.logEvent(requestor.getUserId(),
						AuditConstants.SYSTEM_ID,
						IdentityProviderAudit.PASSWORD_CHANGED.value(),
						"Password changed by user.");
			} catch (NoSuchUserException e) {
				log.error(e.getMessage(), e);
				String message = "An unexpected error occurred in trying to changing the password for the user "
						+ requestor.getUserId() + ":";
				this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
						AuditConstants.SYSTEM_ID,
						FederationAudit.INTERNAL_ERROR.value(), message
								+ "\n\n" + e.getMessage());
				DorianInternalException fault = FaultHelper
						.createFaultException(DorianInternalException.class,
								message);
				FaultHelper.addCause(fault, e.getFault());
				throw fault;
			}
		} catch (CredentialNotSupportedException e) {
			String message = "Permission to change the "
					+ credential.getUserId() + " password was denied.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
					AuditConstants.SYSTEM_ID,
					IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message
							+ "\n\n" + e.getMessage());
			PermissionDeniedException fault = FaultHelper.createFaultException(
					PermissionDeniedException.class, e.getMessage());
			throw fault;
		} catch (InvalidCredentialException e) {
			String message = "Permission to change the "
					+ credential.getUserId() + " password was denied.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
					AuditConstants.SYSTEM_ID,
					IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message
							+ "\n\n" + e.getMessage());

			PermissionDeniedException fault = FaultHelper.createFaultException(
					PermissionDeniedException.class, e.getMessage());
			throw fault;
		}
	}

	public X509Certificate getIdPCertificate() throws DorianInternalException {
		return assertionManager.getIdPCertificate();
	}

	public String register(Application a) throws DorianInternalException,
			InvalidUserPropertyException {
		try {
			ApplicationReview ar = this.registrationPolicy.register(a);
			LocalUserStatus status = ar.getStatus();
			LocalUserRole role = ar.getRole();
			String message = ar.getMessage();
			if (status == null) {
				status = LocalUserStatus.PENDING;
			}

			if (role == null) {
				role = LocalUserRole.NON_ADMINISTRATOR;
			}
			if (message == null) {
				message = "None";
			}

			LocalUser u = new LocalUser();
			u.setUserId(a.getUserId());
			u.setEmail(a.getEmail());
			u.setPassword(a.getPassword());
			u.setFirstName(a.getFirstName());
			u.setLastName(a.getLastName());
			u.setOrganization(a.getOrganization());
			u.setAddress(a.getAddress());
			u.setAddress2(a.getAddress2());
			u.setCity(a.getCity());
			u.setState(a.getState());
			u.setZipcode(a.getZipcode());
			u.setCountry(a.getCountry());
			u.setPhoneNumber(a.getPhoneNumber());
			u.setRole(role);
			u.setStatus(status);
			userManager.addUser(u);
			this.eventManager
					.logEvent(
							u.getUserId(),
							AuditConstants.SYSTEM_ID,
							IdentityProviderAudit.REGISTRATION.value(),
							"User registered with the initial satus: "
									+ status.value());
			return message;
		} catch (DorianInternalException e) {
			String message = "An unexpected error occurred while trying to register the user "
					+ a.getUserId() + ":";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
					AuditConstants.SYSTEM_ID,
					FederationAudit.INTERNAL_ERROR.value(), message + "\n\n"
							+ e.getMessage());
			throw e;
		}
	}

	public LocalUser getUser(String requestorUID, String uid)
			throws DorianInternalException, PermissionDeniedException,
			NoSuchUserException {
		try {
			LocalUser requestor = verifyUser(requestorUID);
			verifyAdministrator(requestor);
			return this.userManager.getUser(uid);
		} catch (DorianInternalException e) {
			String message = "An unexpected error occurred while trying to obtain the user "
					+ uid + ":";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
					AuditConstants.SYSTEM_ID,
					FederationAudit.INTERNAL_ERROR.value(), message + "\n\n"
							+ e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String message = "Permission to obtain information on the  user, "
					+ uid + " was denied:";
			this.eventManager.logEvent(requestorUID, AuditConstants.SYSTEM_ID,
					IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message
							+ "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public LocalUser[] findUsers(String requestorUID, LocalUserFilter filter)
			throws DorianInternalException, PermissionDeniedException {
		try {
			LocalUser requestor = verifyUser(requestorUID);
			verifyAdministrator(requestor);
			return this.userManager.getUsers(filter, false);
		} catch (DorianInternalException e) {
			String message = "An unexpected error occurred while trying to search for users.";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
					AuditConstants.SYSTEM_ID,
					FederationAudit.INTERNAL_ERROR.value(), message + "\n\n"
							+ e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String message = "Permission to search for users was denied.";
			this.eventManager.logEvent(requestorUID, AuditConstants.SYSTEM_ID,
					IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message
							+ "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public void updateUser(String requestorUID, LocalUser u)
			throws DorianInternalException, PermissionDeniedException,
			NoSuchUserException, InvalidUserPropertyException {
		try {
			LocalUser requestor = verifyUser(requestorUID);
			verifyAdministrator(requestor);
			LocalUser beforeUpdate = this.userManager.getUser(u.getUserId());
			this.userManager.updateUser(u);
			if (!StringUtils.isBlank(u.getPassword())) {
				this.eventManager.logEvent(u.getUserId(), requestorUID,
						IdentityProviderAudit.PASSWORD_CHANGED.value(),
						"Password changed by " + u.getUserId() + ".");
			}
			this.eventManager.logEvent(
					u.getUserId(),
					requestorUID,
					IdentityProviderAudit.LOCAL_ACCOUNT_UPDATED.value(),
					ReportUtils.generateReport(beforeUpdate,
							this.userManager.getUser(u.getUserId())));
		} catch (DorianInternalException e) {
			String message = "An unexpected error occurred while trying to update the user "
					+ u.getUserId() + ": ";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
					AuditConstants.SYSTEM_ID,
					FederationAudit.INTERNAL_ERROR.value(), message + "\n\n"
							+ e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String message = "Permission to update the user " + u.getUserId()
					+ " was denied.";
			this.eventManager.logEvent(requestorUID, AuditConstants.SYSTEM_ID,
					IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message
							+ "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	private void verifyAdministrator(LocalUser u)
			throws PermissionDeniedException {
		if (!u.getRole().equals(LocalUserRole.ADMINISTRATOR)) {
			PermissionDeniedException fault = FaultHelper.createFaultException(
					PermissionDeniedException.class,
					"You are NOT an administrator.");
			throw fault;
		}
	}

	private LocalUser verifyUser(String uid) throws DorianInternalException,
			PermissionDeniedException {
		try {
			LocalUser u = this.userManager.getUser(uid);
			userManager.verifyUser(u);
			return u;
		} catch (NoSuchUserException e) {
			PermissionDeniedException fault = FaultHelper.createFaultException(
					PermissionDeniedException.class, "Invalid User!!!");
			throw fault;
		}
	}

	public void removeUser(String requestorUID, String userId)
			throws DorianInternalException, PermissionDeniedException {
		try {
			LocalUser requestor = verifyUser(requestorUID);
			verifyAdministrator(requestor);
			userManager.removeUser(userId);
			this.eventManager.logEvent(userId, requestorUID,
					IdentityProviderAudit.LOCAL_ACCOUNT_REMOVED.value(),
					"The user account for " + userId + " was removed by "
							+ requestorUID + ".");
		} catch (DorianInternalException e) {
			String message = "An unexpected error occurred while trying to remove the user "
					+ userId + ": ";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
					AuditConstants.SYSTEM_ID,
					FederationAudit.INTERNAL_ERROR.value(), message + "\n\n"
							+ e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String message = "Permission to remove the user " + userId
					+ " was denied.";
			this.eventManager.logEvent(requestorUID, AuditConstants.SYSTEM_ID,
					IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message
							+ "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public void clearDatabase() throws DorianInternalException {
		assertionManager.clearDatabase();
		userManager.clearDatabase();
		try {
			this.identityProviderAuditor.clear();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			DorianInternalException fault = FaultHelper
					.createFaultException(DorianInternalException.class,
							"An unexpected error occurred in deleting the auditing logs.");
			FaultHelper.addMessage(fault, e.getMessage());
			throw fault;
		}
	}

	public boolean doesUserExist(String userId) throws DorianInternalException {
		return this.userManager.userExists(userId);
	}

	public List<IdentityProviderAuditRecord> performAudit(String requestorUID,
			IdentityProviderAuditFilter f) throws DorianInternalException,
			PermissionDeniedException {
		try {
			LocalUser requestor = verifyUser(requestorUID);
			verifyAdministrator(requestor);
			List<EventAuditor> handlers = new ArrayList<EventAuditor>();
			handlers.add(this.identityProviderAuditor);
			List<IdentityProviderAuditRecord> list = new ArrayList<IdentityProviderAuditRecord>();
			for (int i = 0; i < handlers.size(); i++) {
				EventAuditor eh = handlers.get(i);
				if (f == null) {
					f = new IdentityProviderAuditFilter();
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
					List<Event> events = eh.findEvents(f.getTargetId(),
							f.getReportingPartyId(), eventType, start, end,
							f.getAuditMessage());
					for (int j = 0; j < events.size(); j++) {
						Event e = events.get(j);
						IdentityProviderAuditRecord r = new IdentityProviderAuditRecord();
						r.setTargetId(e.getTargetId());
						r.setReportingPartyId(e.getReportingPartyId());
						r.setAuditType(IdentityProviderAudit.fromValue(e
								.getEventType()));
						Calendar c = new GregorianCalendar();
						c.setTimeInMillis(e.getOccurredAt());
						r.setOccurredAt(c);
						r.setAuditMessage(e.getMessage());
						list.add(r);
					}
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					String msg = "An unexpected error occurred in searching the auditing logs.";
					this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
							AuditConstants.SYSTEM_ID,
							FederationAudit.INTERNAL_ERROR.value(),
							msg + "\n" + Utils.getExceptionMessage(e) + "\n\n"
									+ e.getMessage());
					DorianInternalException fault = FaultHelper
							.createFaultException(
									DorianInternalException.class, msg);
					FaultHelper.addMessage(fault, e.getMessage());
					throw fault;
				}
			}
			return list;

		} catch (DorianInternalException e) {
			String mess = "An unexpected error occurred in performing an audit:";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
					AuditConstants.SYSTEM_ID,
					FederationAudit.INTERNAL_ERROR.value(),
					mess + "\n\n" + e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String mess = "Caller not permitted to perform audits:";
			this.eventManager.logEvent(requestorUID, AuditConstants.SYSTEM_ID,
					IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), mess
							+ "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	private AccountProfile userToAccountProfile(LocalUser u) {
		AccountProfile p = new AccountProfile();
		p.setUserId(u.getUserId());
		p.setAddress(u.getAddress());
		p.setAddress2(u.getAddress2());
		p.setCity(u.getCity());
		p.setCountry(u.getCountry());
		p.setEmail(u.getEmail());
		p.setFirstName(u.getFirstName());
		p.setLastName(u.getLastName());
		p.setOrganization(u.getOrganization());
		p.setPhoneNumber(u.getPhoneNumber());
		p.setState(u.getState());
		p.setZipcode(u.getZipcode());
		return p;
	}

	public AccountProfile getAccountProfile(String requestorUID)
			throws RemoteException, DorianInternalException,
			PermissionDeniedException {
		try {
			LocalUser requestor = verifyUser(requestorUID);
			return userToAccountProfile(requestor);
		} catch (DorianInternalException e) {
			String message = "An unexpected error occurred while trying to get the account profile for the user "
					+ requestorUID + ".";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
					AuditConstants.SYSTEM_ID,
					FederationAudit.INTERNAL_ERROR.value(), message + "\n\n"
							+ e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String message = "Permission to get the requested account profile was denied.";
			this.eventManager.logEvent(requestorUID, AuditConstants.SYSTEM_ID,
					IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message
							+ "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public void updateAccountProfile(String requestorUID, AccountProfile profile)
			throws RemoteException, DorianInternalException,
			InvalidUserPropertyException, PermissionDeniedException,
			NoSuchUserException {
		try {
			LocalUser requestor = verifyUser(requestorUID);
			if (profile == null) {
				InvalidUserPropertyException f = FaultHelper
						.createFaultException(
								InvalidUserPropertyException.class,
								"Could not update the account profile, no profile was provided.");
				throw f;
			} else if (conf.getAccountInformationModificationPolicy().equals(
					AccountInformationModificationPolicy.ADMIN.value())) {
				PermissionDeniedException f = FaultHelper.createFaultException(
						PermissionDeniedException.class,
						"You do not have permission to update the account profile for "
								+ profile.getUserId() + ".");
				throw f;
			} else if ((Utils.clean(profile.getUserId()) == null)
					|| (!requestor.getUserId().equals(profile.getUserId()))) {
				PermissionDeniedException f = FaultHelper.createFaultException(
						PermissionDeniedException.class,
						"You do not have permission to update the account profile for "
								+ profile.getUserId() + ".");
				throw f;
			} else if (conf.getAccountInformationModificationPolicy().equals(
					AccountInformationModificationPolicy.USER.value())) {
				LocalUser beforeUpdate = this.userManager.getUser(requestor
						.getUserId());
				requestor.setPassword(null);
				requestor.setAddress(profile.getAddress());
				requestor.setAddress2(profile.getAddress2());
				requestor.setCity(profile.getCity());
				requestor.setCountry(profile.getCountry());
				requestor.setEmail(profile.getEmail());
				requestor.setFirstName(profile.getFirstName());
				requestor.setLastName(profile.getLastName());
				requestor.setOrganization(profile.getOrganization());
				requestor.setPhoneNumber(profile.getPhoneNumber());
				requestor.setState(profile.getState());
				requestor.setZipcode(profile.getZipcode());
				this.userManager.updateUser(requestor);
				this.eventManager.logEvent(requestor.getUserId(), requestorUID,
						IdentityProviderAudit.LOCAL_ACCOUNT_UPDATED.value(),
						ReportUtils
								.generateReport(beforeUpdate, this.userManager
										.getUser(requestor.getUserId())));
			} else {
				DorianInternalException f = FaultHelper
						.createFaultException(
								DorianInternalException.class,
								"Unknown account information modification policy: "
										+ conf.getAccountInformationModificationPolicy());
				throw f;
			}
		} catch (DorianInternalException e) {
			String message = "An unexpected error occurred while trying to get the account profile for the user "
					+ requestorUID + ".";
			this.eventManager.logEvent(AuditConstants.SYSTEM_ID,
					AuditConstants.SYSTEM_ID,
					FederationAudit.INTERNAL_ERROR.value(), message + "\n\n"
							+ e.getMessage());
			throw e;
		} catch (PermissionDeniedException e) {
			String message = "Permission to get the requested account profile was denied.";
			this.eventManager.logEvent(requestorUID, AuditConstants.SYSTEM_ID,
					IdentityProviderAudit.LOCAL_ACCESS_DENIED.value(), message
							+ "\n\n" + Utils.getExceptionMessage(e));
			throw e;
		}
	}

	public IdentityProviderPolicy getPolicy() {
		IdentityProviderPolicy policy = new IdentityProviderPolicy();
		UserIdPolicy u = new UserIdPolicy();
		u.setMinLength(this.conf.getMinUserIdLength());
		u.setMaxLength(this.conf.getMaxUserIdLength());
		policy.setUserIdPolicy(u);
		PasswordPolicy p = new PasswordPolicy();
		p.setDictionaryWordsAllowed(false);
		p.setLowerCaseLetterRequired(true);
		p.setMaxLength(conf.getPasswordSecurityPolicy().getMaxPasswordLength());
		p.setMinLength(conf.getPasswordSecurityPolicy().getMinPasswordLength());
		p.setNumericRequired(true);
		PasswordLockoutPolicy plp = new PasswordLockoutPolicy();
		plp.setConsecutiveInvalidLogins(conf.getPasswordSecurityPolicy()
				.getConsecutiveInvalidLogins());
		plp.setHours(conf.getPasswordSecurityPolicy().getLockout().getHours());
		plp.setMinutes(conf.getPasswordSecurityPolicy().getLockout()
				.getMinutes());
		plp.setSeconds(conf.getPasswordSecurityPolicy().getLockout()
				.getSeconds());
		p.setPasswordLockoutPolicy(plp);
		policy.setAccountInformationModificationPolicy(AccountInformationModificationPolicy
				.fromValue(this.conf.getAccountInformationModificationPolicy()));
		p.setSymbolRequired(true);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < PasswordUtils.SYMBOLS.length; i++) {
			sb.append(PasswordUtils.SYMBOLS[i]);
			if ((i + 1) != PasswordUtils.SYMBOLS.length) {
				sb.append(", ");
			}
		}
		p.setSymbolsSupported(sb.toString());
		p.setUpperCaseLetterRequired(true);
		p.setDescription("A valid password MUST contain at least 1 lower case letter, 1 upper case letter, 1 number, and 1 symbol ("
				+ sb.toString()
				+ ").  A valid password MUST be between "
				+ conf.getPasswordSecurityPolicy().getMinPasswordLength()
				+ " and "
				+ conf.getPasswordSecurityPolicy().getMaxPasswordLength()
				+ " characters in length.  A valid password MAY NOT contain a dictionary word greater than 3 characters, spelled forwards or backwards.");
		policy.setPasswordPolicy(p);
		return policy;
	}
}
