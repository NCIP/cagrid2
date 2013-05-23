package org.cagrid.dorian.idp.impl;

import org.cagrid.dorian.idp.Application;
import org.cagrid.dorian.idp.ApplicationReview;
import org.cagrid.dorian.idp.LocalUserRole;
import org.cagrid.dorian.idp.LocalUserStatus;
import org.cagrid.dorian.types.DorianInternalException;
import org.cagrid.dorian.types.InvalidUserPropertyException;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public class ManualRegistrationPolicy implements IdPRegistrationPolicy {

    public String getDescription() {
        return "This policy requires registering users, to be manually approved my an administrator";
    }


    public String getName() {
        return "Manual Registration";
    }


	public ApplicationReview register(Application a)
			throws DorianInternalException, InvalidUserPropertyException {
        ApplicationReview ar = new ApplicationReview();
        ar.setStatus(LocalUserStatus.PENDING);
        ar.setRole(LocalUserRole.NON_ADMINISTRATOR);
        ar.setMessage("Your application will be reviewed by an administrator.");
        return ar;
    }

}
