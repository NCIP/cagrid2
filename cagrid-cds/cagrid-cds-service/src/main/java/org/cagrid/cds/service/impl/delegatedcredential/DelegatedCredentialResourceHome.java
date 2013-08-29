package org.cagrid.cds.service.impl.delegatedcredential;

import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.DelegationRecord;
import org.cagrid.cds.model.DelegationStatus;
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.impl.manager.DelegatedCredentialManager;
import org.cagrid.cds.service.impl.manager.DelegationManager;
import org.cagrid.core.resource.SimpleResourceKey;
import org.cagrid.wsrf.properties.InvalidResourceKeyException;
import org.cagrid.wsrf.properties.NoSuchResourceException;
import org.cagrid.wsrf.properties.RemoveNotSupportedException;
import org.cagrid.wsrf.properties.Resource;
import org.cagrid.wsrf.properties.ResourceException;
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.Date;

public class DelegatedCredentialResourceHome implements ResourceHome {

	public static final String SERVICE_NS = "http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential";
	public static final QName RESOURCE_KEY = new QName(SERVICE_NS, "DelegatedCredentialKey");

	private DelegationManager cds;
    private final Logger log;

	public DelegatedCredentialResourceHome(DelegationManager cds) {
		this();
        this.cds = cds;
	}

    public DelegatedCredentialResourceHome() {
        this.log = LoggerFactory.getLogger(this.getClass().getName());
    }

	public Resource find(ResourceKey key) throws ResourceException,
			NoSuchResourceException, InvalidResourceKeyException {
		try {
			DelegationIdentifier id = (DelegationIdentifier) key.getValue();
			DelegatedCredentialManager cdm = cds.getDelegatedCredentialManager();
			if (cdm.delegationExists(id)) {
				DelegationRecord r = cdm.getDelegationRecord(id);
				if (r.getDelegationStatus().equals(DelegationStatus.APPROVED)) {
					Date now = new Date();
					Date expires = new Date(r.getExpiration());
					if (now.before(expires)) {
						return new DelegatedCredentialResource(this.cds, id);
					}
				}
			}
			throw new NoSuchResourceException();
		} catch (DelegationException f) {
			log.error(f.getMessage(), f);
			throw new ResourceException(f.getMessage(), f);
		} catch (CDSInternalException f) {
			log.error(f.getMessage(), f);
			throw new ResourceException(f.getMessage(), f);
		}
	}

	public Class getKeyTypeClass() {
		return DelegationIdentifier.class;
	}

	public QName getKeyTypeName() {
		return RESOURCE_KEY;
	}

	public void remove(ResourceKey arg0) throws ResourceException,
			NoSuchResourceException, InvalidResourceKeyException,
			RemoveNotSupportedException {
		DelegatedCredentialResource r = (DelegatedCredentialResource) find(arg0);
		r.remove();
	}

	public ResourceKey getResourceKey(DelegationIdentifier id) throws Exception {
		ResourceKey key = new SimpleResourceKey(getKeyTypeName(), id);
		return key;
	}

	public DelegationManager getCDS() {
		return cds;
	}

	public void setCDS(DelegationManager cds) {
		this.cds = cds;
	}

}
