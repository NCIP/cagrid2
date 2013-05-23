package org.cagrid.dorian.service;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import java.rmi.RemoteException;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.List;

import org.cagrid.dorian.idp.AccountProfile;
import org.cagrid.dorian.idp.Application;
import org.cagrid.dorian.idp.IdentityProviderAuditFilter;
import org.cagrid.dorian.idp.IdentityProviderAuditRecord;
import org.cagrid.dorian.idp.LocalUser;
import org.cagrid.dorian.idp.LocalUserFilter;
import org.cagrid.dorian.ifs.CertificateLifetime;
import org.cagrid.dorian.ifs.FederationAuditFilter;
import org.cagrid.dorian.ifs.FederationAuditRecord;
import org.cagrid.dorian.ifs.GridUser;
import org.cagrid.dorian.ifs.GridUserFilter;
import org.cagrid.dorian.ifs.GridUserPolicy;
import org.cagrid.dorian.ifs.GridUserRecord;
import org.cagrid.dorian.ifs.GridUserSearchCriteria;
import org.cagrid.dorian.ifs.HostCertificateFilter;
import org.cagrid.dorian.ifs.HostCertificateRecord;
import org.cagrid.dorian.ifs.HostCertificateRequest;
import org.cagrid.dorian.ifs.HostCertificateUpdate;
import org.cagrid.dorian.ifs.HostRecord;
import org.cagrid.dorian.ifs.HostSearchCriteria;
import org.cagrid.dorian.ifs.TrustedIdP;
import org.cagrid.dorian.ifs.TrustedIdentityProviders;
import org.cagrid.dorian.ifs.UserCertificateFilter;
import org.cagrid.dorian.ifs.UserCertificateRecord;
import org.cagrid.dorian.ifs.UserCertificateUpdate;
import org.cagrid.dorian.policy.DorianPolicy;
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
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import org.cagrid.wsrf.properties.ResourceHome;

public interface Dorian {

//	public DorianProperties getConfiguration();

//	public Database getDatabase();

	public X509Certificate getCACertificate() throws DorianInternalException;

	public X509Certificate getIdPCertificate() throws DorianInternalException;

	public void changeLocalUserPassword(BasicAuthentication credential,
			String newPassword) throws DorianInternalException,
			PermissionDeniedException, InvalidUserPropertyException;

	public LocalUser[] findLocalUsers(String gridIdentity,
			LocalUserFilter filter) throws DorianInternalException,
			PermissionDeniedException;

	public void updateLocalUser(String gridIdentity, LocalUser u)
			throws DorianInternalException, PermissionDeniedException, NoSuchUserException,
			InvalidUserPropertyException;

	public void removeLocalUser(String gridIdentity, String userId)
			throws DorianInternalException, PermissionDeniedException;

	public List<IdentityProviderAuditRecord> performIdentityProviderAudit(
			String gridIdentity, IdentityProviderAuditFilter f)
			throws DorianInternalException, PermissionDeniedException;

	public SAMLAssertion authenticate(Credential credential)
			throws AuthenticationProviderException, InvalidCredentialException,
			CredentialNotSupportedException;

	public String registerLocalUser(Application a) throws DorianInternalException,
			InvalidUserPropertyException;

	/** *************** Federation FUNCTIONS ********************** */

	public GridUserPolicy[] getGridUserPolicies(String callerGridIdentity)
			throws DorianInternalException, PermissionDeniedException;

	public X509Certificate requestUserCertificate(SAMLAssertion saml,
			PublicKey publicKey, CertificateLifetime lifetime)
			throws DorianInternalException, InvalidAssertionException, UserPolicyException,
			PermissionDeniedException;

	public TrustedIdP[] getTrustedIdPs(String callerGridIdentity)
			throws DorianInternalException, PermissionDeniedException;

	public TrustedIdP addTrustedIdP(String callerGridIdentity, TrustedIdP idp)
			throws DorianInternalException, InvalidTrustedIdPException,
			PermissionDeniedException;

	public void updateTrustedIdP(String callerGridIdentity, TrustedIdP idp)
			throws DorianInternalException, InvalidTrustedIdPException,
			PermissionDeniedException;

	public void removeTrustedIdP(String callerGridIdentity, TrustedIdP idp)
			throws DorianInternalException, InvalidTrustedIdPException,
			PermissionDeniedException;

	public GridUser[] findGridUsers(String callerGridIdentity,
			GridUserFilter filter) throws DorianInternalException,
			PermissionDeniedException;

	public void updateGridUser(String callerGridIdentity, GridUser usr)
			throws DorianInternalException, InvalidUserException, PermissionDeniedException;

	public void removeGridUser(String callerGridIdentity, GridUser user)
			throws DorianInternalException, InvalidUserException, PermissionDeniedException;

	public void addAdmin(String callerGridIdentity, String gridIdentity)
			throws RemoteException, DorianInternalException, PermissionDeniedException;

	public void removeAdmin(String callerGridIdentity, String gridIdentity)
			throws RemoteException, DorianInternalException, PermissionDeniedException;

	public String[] getAdmins(String callerGridIdentity)
			throws RemoteException, DorianInternalException, PermissionDeniedException;

	public HostCertificateRecord requestHostCertificate(String callerGridId,
			HostCertificateRequest req) throws DorianInternalException,
			InvalidHostCertificateRequestException, InvalidHostCertificateException,
			PermissionDeniedException;

	public HostCertificateRecord[] getOwnedHostCertificates(String callerGridId)
			throws DorianInternalException, PermissionDeniedException;

	public HostCertificateRecord approveHostCertificate(String callerGridId,
			long recordId) throws DorianInternalException,
			InvalidHostCertificateException, PermissionDeniedException;

	public HostCertificateRecord[] findHostCertificates(String callerGridId,
			HostCertificateFilter hostCertificateFilter)
			throws DorianInternalException, PermissionDeniedException;

	public void updateHostCertificateRecord(String callerGridId,
			HostCertificateUpdate update) throws DorianInternalException,
			InvalidHostCertificateException, PermissionDeniedException;

	public HostCertificateRecord renewHostCertificate(String callerGridId,
			long recordId) throws DorianInternalException,
			InvalidHostCertificateException, PermissionDeniedException;

	public boolean doesLocalUserExist(String userId) throws DorianInternalException;

	public void clearDatabase() throws DorianInternalException;

	public TrustedIdentityProviders getTrustedIdentityProviders()
			throws DorianInternalException;

	public List<UserCertificateRecord> findUserCertificateRecords(
			String callerIdentity, UserCertificateFilter f)
			throws DorianInternalException, InvalidUserCertificateException,
			PermissionDeniedException;

	public void updateUserCertificateRecord(String callerIdentity,
			UserCertificateUpdate update) throws DorianInternalException,
			InvalidUserCertificateException, PermissionDeniedException;

	public void removeUserCertificate(String callerIdentity, long serialNumber)
			throws DorianInternalException, InvalidUserCertificateException,
			PermissionDeniedException;

	public List<FederationAuditRecord> performFederationAudit(
			String callerIdentity, FederationAuditFilter f)
			throws DorianInternalException, PermissionDeniedException;

	public List<GridUserRecord> userSearch(String callerIdentity,
			GridUserSearchCriteria criteria) throws RemoteException,
			DorianInternalException, PermissionDeniedException;

	public List<HostRecord> hostSearch(String callerIdentity,
			HostSearchCriteria criteria) throws RemoteException,
			DorianInternalException, PermissionDeniedException;

	public AccountProfile getAccountProfile(String gridIdentity)
			throws RemoteException, DorianInternalException, PermissionDeniedException;

	public void updateAccountProfile(String gridIdentity, AccountProfile profile)
			throws RemoteException, DorianInternalException,
			InvalidUserPropertyException, PermissionDeniedException,
			NoSuchUserException;

	public DorianPolicy getDorianPolicy();

	public void setPublish(String callerGridIdentity, TrustedIdP idp,
			boolean publish) throws DorianInternalException,
			InvalidTrustedIdPException, PermissionDeniedException;

	public boolean getPublish(String callerGridIdentity, TrustedIdP idp)
			throws DorianInternalException, InvalidTrustedIdPException,
			PermissionDeniedException;
	
	public ServiceMetadata getServiceMetadata();
	
	public AuthenticationProfiles getAuthenticationProfiles();
	
	public ResourceHome getResourceHome();
	
	public ServiceSecurityMetadata getServiceSecurityMetadata();
}