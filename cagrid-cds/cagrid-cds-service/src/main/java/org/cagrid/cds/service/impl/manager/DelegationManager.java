package org.cagrid.cds.service.impl.manager;

import gov.nih.nci.cagrid.common.Utils;
import org.cagrid.cds.model.CertificateChain;
import org.cagrid.cds.model.ClientDelegationFilter;
import org.cagrid.cds.model.DelegatedCredentialAuditFilter;
import org.cagrid.cds.model.DelegatedCredentialAuditRecord;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.DelegationRecord;
import org.cagrid.cds.model.DelegationRecordFilter;
import org.cagrid.cds.model.DelegationRequest;
import org.cagrid.cds.model.DelegationSigningRequest;
import org.cagrid.cds.model.DelegationSigningResponse;
import org.cagrid.cds.model.DelegationStatus;
import org.cagrid.cds.model.PublicKey;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.exception.InvalidPolicyException;
import org.cagrid.cds.service.exception.PermissionDeniedException;
import org.cagrid.cds.service.impl.util.Errors;
import org.cagrid.core.common.FaultHelper;
import org.cagrid.tools.groups.Group;
import org.cagrid.tools.groups.GroupException;
import org.cagrid.tools.groups.GroupManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DelegationManager {

	public static final String ADMINISTRATORS = "administrators";
	private DelegatedCredentialManager dcm;
	private PropertyManager properties;
	private Group administrators;
	private GroupManager groupManager;
    private Logger log;

	public DelegationManager(PropertyManager properties,
                             DelegatedCredentialManager dcm, GroupManager groupManager)
			throws CDSInternalException {
		this.dcm = dcm;
		this.log = LoggerFactory.getLogger(this.getClass().getName());
		this.properties = properties;
		this.groupManager = groupManager;
		try {
			if (!this.groupManager.groupExists(ADMINISTRATORS)) {
				this.groupManager.addGroup(ADMINISTRATORS);
				this.administrators = this.groupManager
						.getGroup(ADMINISTRATORS);
			} else {
				this.administrators = this.groupManager
						.getGroup(ADMINISTRATORS);
			}
		} catch (GroupException e) {
			log.error(e.getMessage(), e);
            CDSInternalException fault = FaultHelper
                    .createFaultException(CDSInternalException.class,
                            "An unexpected error occurred in setting up the administrators group.");
            FaultHelper.addMessage(fault, e.getMessage());
            throw fault;
		}
	}

	public DelegationSigningRequest initiateDelegation(String callerIdentity,
			DelegationRequest req) throws CDSInternalException, InvalidPolicyException,
            DelegationException, PermissionDeniedException {
		verifyAuthenticated(callerIdentity);
		return this.dcm.initiateDelegation(callerIdentity, req);
	}

	public List<DelegationRecord> findCredentialsDelegatedToClient(
			String callerIdentity, ClientDelegationFilter filter)
			throws CDSInternalException, PermissionDeniedException {
		verifyAuthenticated(callerIdentity);
		return this.dcm.findCredentialsDelegatedToClient(callerIdentity, filter);
	}

	public DelegationIdentifier approveDelegation(String callerIdentity,
			DelegationSigningResponse res) throws CDSInternalException,
			DelegationException, PermissionDeniedException {
		verifyAuthenticated(callerIdentity);
		return this.dcm.approveDelegation(callerIdentity, res);
	}

	public CertificateChain getDelegatedCredential(String gridIdentity,
			DelegationIdentifier id, PublicKey publicKey)
			throws CDSInternalException, DelegationException, PermissionDeniedException {
		verifyAuthenticated(gridIdentity);
		return this.dcm.getDelegatedCredential(gridIdentity, id, publicKey);
	}

	public void suspendDelegatedCredential(String callerIdentity,
			DelegationIdentifier id) throws CDSInternalException, DelegationException,
            PermissionDeniedException {
		verifyAuthenticated(callerIdentity);
		DelegationRecord r = this.dcm.getDelegationRecord(id);
		if (r.getGridIdentity().equals(callerIdentity)) {
			this.dcm.updateDelegatedCredentialStatus(callerIdentity, id,
					DelegationStatus.SUSPENDED);
		} else {
			throw Errors.makeException(PermissionDeniedException.class, Errors.PERMISSION_DENIED);
		}
	}

	public void updateDelegatedCredentialStatus(String callerIdentity,
			DelegationIdentifier id, DelegationStatus status)
			throws CDSInternalException, DelegationException, PermissionDeniedException {
		verifyAuthenticated(callerIdentity);
		if (isAdmin(callerIdentity)) {
			this.dcm
					.updateDelegatedCredentialStatus(callerIdentity, id, status);
		} else {
			DelegationRecord r = this.dcm.getDelegationRecord(id);
			if (r.getGridIdentity().equals(callerIdentity)) {
				if ((r.getDelegationStatus().equals(DelegationStatus.APPROVED))
						&& (status.equals(DelegationStatus.SUSPENDED))) {
					this.dcm.updateDelegatedCredentialStatus(callerIdentity,
							id, status);
				} else {
					throw Errors.makeException(PermissionDeniedException.class, Errors.PERMISSION_DENIED);
				}
			} else {
				throw Errors.makeException(PermissionDeniedException.class, Errors.PERMISSION_DENIED);
			}
		}
	}

	public List<DelegationRecord> findDelegatedCredentials(String callerIdentity,
			DelegationRecordFilter f) throws CDSInternalException,
			PermissionDeniedException {
		verifyAuthenticated(callerIdentity);
		if (f == null) {
			f = new DelegationRecordFilter();
		}
		if (isAdmin(callerIdentity)) {
			return this.dcm.findDelegatedCredentials(f);
		} else {
			f.setGridIdentity(callerIdentity);
			return this.dcm.findDelegatedCredentials(f);
		}
	}

	public void addAdmin(String callerIdentity, String gridIdentity)
			throws CDSInternalException, PermissionDeniedException {
		verifyAuthenticated(callerIdentity);
		verifyAdmin(callerIdentity);
		try {
			this.administrators.addMember(gridIdentity);
		} catch (GroupException e) {
			log.error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, "An unexpected error occurred in adding the user as a administrator.", e);
		}
	}

	public void removeAdmin(String callerIdentity, String gridIdentity)
			throws CDSInternalException, PermissionDeniedException {
		verifyAuthenticated(callerIdentity);
		verifyAdmin(callerIdentity);
		try {
			this.administrators.removeMember(gridIdentity);
		} catch (GroupException e) {
			log.error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, "An unexpected error occurred in removing the user from the administrators group.", e);
		}
	}

	public List<String> getAdmins(String callerIdentity) throws CDSInternalException,
			PermissionDeniedException {
		verifyAuthenticated(callerIdentity);
		verifyAdmin(callerIdentity);
		try {
			return this.administrators.getMembers();
		} catch (GroupException e) {
			log.error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, "An unexpected error occurred in obtaining a list of administrators.", e);
		}
	}

	public void deleteDelegatedCredential(String callerIdentity,
			DelegationIdentifier id) throws CDSInternalException,
			PermissionDeniedException {
		verifyAuthenticated(callerIdentity);
		verifyAdmin(callerIdentity);
		this.dcm.delete(id);
	}

	private void verifyAdmin(String gridIdentity) throws CDSInternalException,
			PermissionDeniedException {
		if (!isAdmin(gridIdentity)) {
			throw Errors.getPermissionDeniedFault(Errors.ADMIN_REQUIRED);
		}
	}

	private void verifyAuthenticated(String callerIdentity)
			throws PermissionDeniedException {
		if (Utils.clean(callerIdentity) == null) {
			throw Errors
					.getPermissionDeniedFault(Errors.AUTHENTICATION_REQUIRED);
		}
	}

	private boolean isAdmin(String gridIdentity) throws CDSInternalException {
		try {
			if (this.administrators.isMember(gridIdentity)) {
				return true;
			} else {
				return false;
			}
		} catch (GroupException e) {
			log.error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, "An unexpected error occurred in determining if the user is an administrator.", e);
		}
	}

	public void clear() throws CDSInternalException {
		dcm.clearDatabase();
		properties.clearAllProperties();
		try {
			groupManager.clearDatabase();
		} catch (GroupException e) {
			log.error(e.getMessage(), e);
            throw Errors.makeException(CDSInternalException.class, "An unexpected error occurred in removing all groups.", e);
		}
	}

	public DelegatedCredentialManager getDelegatedCredentialManager() {
		return this.dcm;
	}

	public List<DelegatedCredentialAuditRecord> searchDelegatedCredentialAuditLog(
			String callerIdentity, DelegatedCredentialAuditFilter f)
			throws CDSInternalException, DelegationException, PermissionDeniedException {
		verifyAuthenticated(callerIdentity);
		if (f == null) {
			f = new DelegatedCredentialAuditFilter();
		}
		if (isAdmin(callerIdentity)) {
			return this.dcm.searchAuditLog(f);
		} else {
			if (f.getDelegationIdentifier() == null) {
				throw Errors
						.getPermissionDeniedFault(Errors.PERMISSION_DENIED_NO_DELEGATED_CREDENTIAL_SPECIFIED);
			} else {
				DelegationRecord r = this.dcm.getDelegationRecord(f
						.getDelegationIdentifier());
				if (!r.getGridIdentity().equals(callerIdentity)) {
					throw Errors
							.getPermissionDeniedFault(Errors.PERMISSION_DENIED_TO_AUDIT);
				} else {
					return this.dcm.searchAuditLog(f);
				}
			}
		}
	}
}
