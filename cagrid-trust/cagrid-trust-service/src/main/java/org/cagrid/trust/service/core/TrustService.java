package org.cagrid.trust.service.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrustService implements org.cagrid.trust.service.TrustService {

	private Synchronizer synchronizer;

	private Logger log;

	public TrustService() {
		log = LoggerFactory.getLogger(this.getClass().getName());
	}

	public Synchronizer getSynchronizer() {
		return synchronizer;
	}

	public void setSynchronizer(Synchronizer synchronizer) {
		this.synchronizer = synchronizer;
	}

}
