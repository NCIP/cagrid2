package org.cagrid.cds.service;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
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
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.exception.InvalidPolicyException;
import org.cagrid.cds.service.exception.PermissionDeniedException;
import org.cagrid.wsrf.properties.ResourceHome;

import java.util.List;

public interface CredentialDelegationService {

    void addAdmin(String callerGridIdentity, String gridIdentity) throws PermissionDeniedException, CDSInternalException;

    ResourceHome getResourceHome();

    ServiceMetadata getServiceMetadata();

    ServiceSecurityMetadata getServiceSecurityMetadata();

    void deleteDelegatedCredential(String callerGridIdentity, DelegationIdentifier id) throws PermissionDeniedException, CDSInternalException;

    DelegationIdentifier approveDelegation(String callerGridIdentity, DelegationSigningResponse delegationSigningResponse) throws DelegationException, PermissionDeniedException, CDSInternalException;

    List<DelegationRecord> findDelegatedCredentials(String callerGridIdentity, DelegationRecordFilter filter) throws PermissionDeniedException, CDSInternalException;

    DelegationSigningRequest initiateDelegation(String callerGridIdentity, DelegationRequest req) throws DelegationException, PermissionDeniedException, CDSInternalException, InvalidPolicyException;

    void updateDelegatedCredentialStatus(String callerGridIdentity, DelegationIdentifier id, DelegationStatus status) throws DelegationException, PermissionDeniedException, CDSInternalException;

    List<DelegationRecord> findCredentialsDelegatedToClient(String callerGridIdentity, ClientDelegationFilter filter) throws PermissionDeniedException, CDSInternalException;

    List<DelegatedCredentialAuditRecord> searchDelegatedCredentialAuditLog(String callerGridIdentity, DelegatedCredentialAuditFilter f) throws DelegationException, PermissionDeniedException, CDSInternalException;

    void removeAdmin(String callerGridIdentity, String gridIdentity) throws PermissionDeniedException, CDSInternalException;

    List<String> getAdmins(String callerGridIdentity) throws PermissionDeniedException, CDSInternalException;
}
