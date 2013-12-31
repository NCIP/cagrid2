package org.cagrid.trust.service.core;

import java.io.File;

import org.cagrid.core.xml.XMLUtils;
import org.cagrid.trust.model.SyncDescription;

public class Driver {

	public static void main(String[] args) {
		try {
			SpringUtils utils = new SpringUtils();
			Synchronizer gts = utils.getSynchronizer();
			SyncDescription des = (SyncDescription) XMLUtils.fromXMLFile(SyncDescription.class, new File("/Users/langella/Desktop/trust/sync-description.xml"));
			gts.sync(des);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
