package org.cagrid.cds.service.impl.delegatedcredential;

import org.cagrid.cds.model.CertificateChain;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.PublicKey;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.exception.PermissionDeniedException;
import org.cagrid.cds.service.impl.manager.DelegationManager;
import org.cagrid.wsrf.properties.RemoveCallback;
import org.cagrid.wsrf.properties.Resource;
import org.cagrid.wsrf.properties.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelegatedCredentialResource implements Resource, RemoveCallback {

	private DelegationManager cds;
	private DelegationIdentifier id;
    private final Logger log;

	public DelegatedCredentialResource(DelegationManager cds,
			DelegationIdentifier id) {
		this.cds = cds;
		this.id = id;
        this.log = LoggerFactory.getLogger(this.getClass().getName());
	}

	public CertificateChain getDelegatedCredential(String callerGridIdentity, PublicKey publicKey) throws DelegationException, PermissionDeniedException, CDSInternalException {
		return this.cds.getDelegatedCredential(callerGridIdentity, id, publicKey);
	}

    @Override
    public void remove() throws ResourceException {
        //TODO, don't have grid identity here
        log.error("********************Don't know how to remove yet*******************");
        throw new ResourceException("********************Don't know how to remove yet*******************");

//        try {
//            this.cds.suspendDelegatedCredential(callerGridIdentity, id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ResourceException(Utils.getExceptionMessage(e), e);
//        }
    }
}
