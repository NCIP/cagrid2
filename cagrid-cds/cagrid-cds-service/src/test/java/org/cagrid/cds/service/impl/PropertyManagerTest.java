package org.cagrid.cds.service.impl;

import junit.framework.TestCase;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.cagrid.cds.service.impl.manager.PropertyManager;
import org.cagrid.cds.service.impl.testutils.Utils;

public class PropertyManagerTest extends TestCase {

	public void testPropertyMangager() {
		PropertyManager pm = null;
		try {
			pm = Utils.getPropertyManager();
			pm.clearAllProperties();
			pm = Utils.getPropertyManager();
			assertEquals(PropertyManager.CURRENT_VERSION, pm.getVersion());
//			assertNull(pm.getKeyManager()); why null?
		} catch (Exception e) {
			fail(ExceptionUtils.getFullStackTrace(e));
		} finally {
			try {
				pm.clearAllProperties();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void setUp() throws Exception {
		super.setUp();
		Utils.getDatabase().createDatabaseIfNeeded();
	}
}
