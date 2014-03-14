package org.cagrid.trust.service.core;

import org.cagrid.core.soapclient.ClientConfigurer;
import org.cagrid.trust.model.SyncDescription;
import org.cagrid.trust.model.SyncReport;

public interface Synchronizer {
	public SyncReport sync(SyncDescription description);
    public ClientConfigurer getClientConfigurer();
}
