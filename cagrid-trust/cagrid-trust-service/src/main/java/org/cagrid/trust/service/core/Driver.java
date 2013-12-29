package org.cagrid.trust.service.core;

import java.io.File;

import org.cagrid.trust.model.SyncDescription;

public class Driver {

	public static void main(String[] args) {
		try {
			File trustedCertificatesDirectory = new File("/Users/langella/Desktop/trust/certificates");
			File historyDirectory = new File("/Users/langella/Desktop/trust/history");
			SyncGTS gts = new SyncGTS(trustedCertificatesDirectory, historyDirectory);
			SyncDescription des = (SyncDescription) XMLUtils.fromXMLFile(SyncDescription.class, new File("/Users/langella/Desktop/trust/sync-description.xml"));
			gts.sync(des);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
