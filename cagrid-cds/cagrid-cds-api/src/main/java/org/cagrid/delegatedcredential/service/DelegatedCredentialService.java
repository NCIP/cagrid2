package org.cagrid.delegatedcredential.service;

import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import org.cagrid.cds.model.CertificateChain;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.PublicKey;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.exception.PermissionDeniedException;
import org.cagrid.wsrf.properties.ResourceException;
import org.cagrid.wsrf.properties.ResourceHome;

public interface DelegatedCredentialService {

    ResourceHome getResourceHome();

    ServiceSecurityMetadata getServiceSecurityMetadata();

    public void suspendDelegatedCredential(String callerGridIdentity, DelegationIdentifier id) throws CDSInternalException, DelegationException,
            PermissionDeniedException;

    CertificateChain getDelegatedCredential(String callerGridIdentity, DelegationIdentifier did, PublicKey publicKey) throws DelegationException,
            PermissionDeniedException, CDSInternalException, ResourceException;
}
