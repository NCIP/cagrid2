package org.cagrid.gts.service.impl.db;

import org.cagrid.gts.service.impl.Database;


/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public interface DBManager {
	public AuthorityTable getAuthorityTable();


	public TrustedAuthorityTable getTrustedAuthorityTable();


	public TrustedAuthorityTrustLevelsTable getTrustedAuthorityTrustLevelsTable();


	public TrustLevelTable getTrustLevelTable();


	public PermissionsTable getPermissionsTable();


	public Database getDatabase();
}
