package org.cagrid.trust.service.core;

import java.io.File;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.xml.XMLUtils;
import org.cagrid.trust.model.SyncDescription;

public class Driver {

	public static void main(String[] args) {
		try {
			File trustedCertificatesDirectory = new File("/Users/langella/Desktop/trust/certificates");
			File historyDirectory = new File("/Users/langella/Desktop/trust/history");
			KeyStoreType ts = new KeyStoreType();
			ts.setFile("/Users/langella/Documents/caGrid/environments/keys/training-truststore.jks");
			ts.setPassword("changeit");
			SyncGTS gts = new SyncGTS(ts, trustedCertificatesDirectory, historyDirectory);
			SyncDescription des = (SyncDescription) XMLUtils.fromXMLFile(SyncDescription.class, new File("/Users/langella/Desktop/trust/sync-description.xml"));
			gts.sync(des);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
