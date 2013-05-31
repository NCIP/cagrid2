package org.cagrid.gts.tools.service;

import org.cagrid.gts.common.MySQLDatabase;
import org.cagrid.gts.model.Permission;
import org.cagrid.gts.model.Role;
import org.cagrid.gts.service.db.mysql.MySQLManager;
import org.cagrid.gts.service.exception.GTSInternalException;
import org.cagrid.gts.service.exception.IllegalPermissionException;
import org.cagrid.gts.service.impl.Configuration;
import org.cagrid.gts.service.impl.PermissionManager;
import org.cagrid.gts.service.impl.SimpleResourceManager;


/**
 * @author <A HREF="MAILTO:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A HREF="MAILTO:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A HREF="MAILTO:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: TrustedAuthorityManager.java,v 1.1 2006/03/08 19:48:46 langella
 *          Exp $
 */

public class AntPermissionBootstapper {

	private PermissionManager pm;


	public AntPermissionBootstapper(Configuration conf) {
		pm = new PermissionManager(new MySQLManager(new MySQLDatabase(conf.getConnectionManager(), conf
			.getGTSInternalId())));
	}


	public void addAdminUser(String gridIdentity) throws GTSInternalException, IllegalPermissionException {
		Permission p = new Permission();
		p.setGridIdentity(gridIdentity);
		p.setRole(Role.TRUST_SERVICE_ADMIN);
		pm.addPermission(p);
	}


	public static void usage() {
		System.err.println(AntPermissionBootstapper.class.getName() + " Usage:");
		System.err.println();
		System.err.println("java " + AntPermissionBootstapper.class.getName() + " GTS_CONFIGURATION_FILE");
	}


	public static void main(String[] args) {
		if (args.length != 2) {
			usage();
			System.exit(1);
		}
		Configuration conf = null;
		try {
			SimpleResourceManager srm = new SimpleResourceManager(args[0]);
			conf = (Configuration) srm.getResource(Configuration.RESOURCE);
		} catch (Exception e) {
			System.out.println("Error loading the GTS config file, " + args[0]);
			e.printStackTrace();
			System.exit(1);
		}
		try {
			AntPermissionBootstapper util = new AntPermissionBootstapper(conf);
			String gridId = args[1];
			util.addAdminUser(gridId);
			System.out.println("The user " + gridId + " was succesfully added as an administrator of the GTS ("
				+ conf.getGTSInternalId() + ")");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
