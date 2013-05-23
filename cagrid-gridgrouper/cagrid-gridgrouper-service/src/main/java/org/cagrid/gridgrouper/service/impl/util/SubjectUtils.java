package org.cagrid.gridgrouper.service.impl.util;

import edu.internet2.middleware.subject.GridGroupSubject;
import edu.internet2.middleware.subject.GridSourceAdapter;
import edu.internet2.middleware.subject.NonGridSourceAdapter;
import edu.internet2.middleware.subject.Subject;
import edu.internet2.middleware.subject.SubjectNotFoundException;
import org.cagrid.gridgrouper.model.GroupDescriptor;
import org.cagrid.gridgrouper.model.MemberDescriptor;
import org.cagrid.gridgrouper.model.MemberType;


/**
 * @author <A HREF="MAILTO:langella@bmi.osu.edu">Stephen Langella</A>
 * @author <A HREF="MAILTO:oster@bmi.osu.edu">Scott Oster</A>
 * @author <A HREF="MAILTO:hastings@bmi.osu.edu">Shannon Hastings</A>
 * @author <A HREF="MAILTO:ervin@bmi.osu.edu">David W. Ervin</A>
 * @version $Id: GridGrouperBaseTreeNode.java,v 1.1 2006/08/04 03:49:26 langella
 *          Exp $
 */
public class SubjectUtils {

	public static final GridSourceAdapter GRID_SOURCE = new GridSourceAdapter("grid",
		"Grid Grouper: Grid Source Adapter");

	public static final NonGridSourceAdapter NON_GRID_SOURCE = new NonGridSourceAdapter("Unknown",
		"Grid Grouper: Unknown Source Adapter");


	public static Subject getSubject(String id) throws SubjectNotFoundException {
		return getSubject(id, false);
	}


	public static Subject getSubject(String id, boolean allowNonGridSubject) throws SubjectNotFoundException {
		try {
			return GRID_SOURCE.getSubject(id);

		} catch (SubjectNotFoundException e) {
			if (allowNonGridSubject) {
				return NON_GRID_SOURCE.getSubject(id);
			} else {
				throw e;
			}
		}

	}


	public static Subject getSubject(GroupDescriptor des) throws SubjectNotFoundException {
		return new GridGroupSubject(des.getUUID(), des.getName(), null);

	}


	public static Subject getSubject(MemberDescriptor des) throws SubjectNotFoundException {
		if (des.getMemberType().equals(MemberType.GRID)) {
			return GRID_SOURCE.getSubject(des.getSubjectId());
		} else if (des.getMemberType().equals(MemberType.OTHER)) {
			return NON_GRID_SOURCE.getSubject(des.getSubjectId());
		} else if (des.getMemberType().equals(MemberType.GROUPER_GROUP)) {
			return new GridGroupSubject(des.getSubjectId(), des.getSubjectName(), null);
		} else {
			throw new SubjectNotFoundException(des.getSubjectId() + " was not found.");
		}
	}
}
