package org.cagrid.gts.tools.service;

import gov.nih.nci.cagrid.common.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.cagrid.gts.model.Permission;
import org.cagrid.gts.model.Role;
import org.cagrid.gts.service.exception.GTSInternalException;
import org.cagrid.gts.service.exception.IllegalPermissionException;
import org.cagrid.gts.service.impl.Configuration;
import org.cagrid.gts.service.impl.MySQLDatabase;
import org.cagrid.gts.service.impl.PermissionManager;
import org.cagrid.gts.service.impl.SimpleResourceManager;
import org.cagrid.gts.service.impl.db.mysql.MySQLManager;


/**
 * @author <A HREF="MAILTO:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A HREF="MAILTO:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A HREF="MAILTO:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: TrustedAuthorityManager.java,v 1.1 2006/03/08 19:48:46 langella
 *          Exp $
 */

public class PermissionBootstrapper {

	private PermissionManager pm;


	public PermissionBootstrapper(Configuration conf) {
		pm = new PermissionManager(new MySQLManager(new MySQLDatabase(conf.getConnectionManager(), conf
			.getGTSInternalId())));
	}
	
	public PermissionBootstrapper(File f) throws FileNotFoundException, Exception {
	    SimpleResourceManager srm = new SimpleResourceManager(new FileInputStream(f));
	    Configuration conf = (Configuration) srm.getResource(Configuration.RESOURCE);
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
		System.err.println(PermissionBootstrapper.class.getName() + " Usage:");
		System.err.println();
		System.err.println("java " + PermissionBootstrapper.class.getName() + " GTS_CONFIGURATION_FILE");
	}


	public static void main(String[] args) {
		if (args.length != 1) {
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
			PermissionBootstrapper util = new PermissionBootstrapper(conf);
			System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
			System.out.println("*            Grid Trust Service (GTS) Permission Bootstrapper             *");
			System.out.println("*                                                                         *");
			System.out.println("*  This tool is used for bootstrapping the Grid Trust Service (GTS).  It  *");
			System.out.println("*enables the GTS to be configured with a preliminary list of admins. It   *");
			System.out.println("*is not intended to be used as the method of managing GTS administrators. *");
			System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
			System.out.println();
			System.out.println("Below please enter the Grid Identity of the GTS Administrator:");
			String gridId = IOUtils.readLine("Grid Identity>", true);
			util.addAdminUser(gridId);
			System.out.println("The user " + gridId + " was succesfully added as an administrator of the GTS ("
				+ conf.getGTSInternalId() + ")");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
