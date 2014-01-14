package org.cagrid.trust.service.core;

import java.io.File;

import org.cagrid.core.xml.XMLUtils;
import org.cagrid.trust.model.SyncDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrustService implements org.cagrid.trust.service.TrustService {

	private Synchronizer synchronizer;

	private String syncDescription;

	private TrustServiceTrustManager trustManager;

	private Object syncMutex = new Object();

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

	public String getSyncDescription() {
		return syncDescription;
	}

	public void setSyncDescription(String syncDescription) {
		this.syncDescription = syncDescription;
	}

	public TrustServiceTrustManager getTrustManager() {
		return trustManager;
	}

	public void setTrustManager(TrustServiceTrustManager trustManager) {
		this.trustManager = trustManager;
	}

	public void syncWithTrustFabric() {
		long start = System.currentTimeMillis();
		log.info("Syncing with the trust fabric.....");
		if (getSynchronizer() != null) {
			synchronized (syncMutex) {
				File syncDescriptionFile = null;
				if (getSyncDescription() != null) {
					syncDescriptionFile = new File(getSyncDescription());
					SyncDescription des = (SyncDescription) XMLUtils.fromXMLFile(SyncDescription.class, syncDescriptionFile);
					getSynchronizer().sync(des);
				} else {
					log.warn("Cannot sync with the trust fabric, no sync description file configured");
				}

				if (getTrustManager() != null) {
					getTrustManager().reloadTrustManager();
				} else {
					log.warn("No trust manager configured for the trust service");
				}
			}
		} else {
			log.warn("No synchronizer configured for the trust service.");
		}

		long end = System.currentTimeMillis();
		log.info("Successfull synced with the trust fabric in " + (end - start) + " milliseconds.");
	}
}
