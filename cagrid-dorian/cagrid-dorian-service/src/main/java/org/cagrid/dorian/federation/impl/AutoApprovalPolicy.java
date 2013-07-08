package org.cagrid.dorian.federation.impl;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.model.exceptions.DorianInternalException;
import org.cagrid.dorian.model.exceptions.UserPolicyException;
import org.cagrid.dorian.model.federation.GridUser;
import org.cagrid.dorian.model.federation.GridUserStatus;
import org.cagrid.dorian.model.federation.TrustedIdP;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */

public class AutoApprovalPolicy extends AccountPolicy {
	public void applyPolicy(TrustedIdP idp, GridUser user)
			throws DorianInternalException, UserPolicyException {
		UserManager um = getUserManager();
		// First we approve if the user has not been approved.
		if (user.getUserStatus().equals(GridUserStatus.PENDING)) {
			user.setUserStatus(GridUserStatus.ACTIVE);
			try {
				um.updateUser(user);
			} catch (Exception e) {
				DorianInternalException fault = FaultHelper
						.createFaultException(
								DorianInternalException.class,
								"Error updating the status of the user "
										+ user.getGridId());
				FaultHelper.addMessage(fault, e.getMessage());
				throw fault;
			}
		}
	}

	public String getDisplayName() {
		return "Auto Approval";
	}
}
