package org.cagrid.gridgrouper.service.impl.testutils;

import edu.internet2.middleware.grouper.Group;
import edu.internet2.middleware.grouper.GroupFinder;
import edu.internet2.middleware.grouper.GroupNotFoundException;
import edu.internet2.middleware.grouper.GrouperSession;
import edu.internet2.middleware.grouper.Membership;
import edu.internet2.middleware.grouper.OwnerNotFoundException;
import edu.internet2.middleware.subject.AnonymousGridUserSubject;
import edu.internet2.middleware.subject.Subject;
import org.cagrid.gridgrouper.model.GroupDescriptor;
import org.cagrid.gridgrouper.model.GroupIdentifier;
import org.cagrid.gridgrouper.model.GroupPrivilege;
import org.cagrid.gridgrouper.model.GroupPrivilegeType;
import org.cagrid.gridgrouper.model.MemberDescriptor;
import org.cagrid.gridgrouper.model.StemDescriptor;
import org.cagrid.gridgrouper.model.StemIdentifier;
import org.cagrid.gridgrouper.service.GridGrouperService;
import org.cagrid.gridgrouper.service.impl.GridGrouper;
import org.cagrid.gridgrouper.service.impl.util.SubjectUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class Utils {

	private static final String SUPER_USER = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=super admin";

	
	public static void printMemberDesciptor(MemberDescriptor m){
		System.out.println("UUID: "+m.getUUID());
		System.out.println("Name: "+m.getSubjectName());
		System.out.println("Id: "+m.getSubjectId());
		System.out.println("Type: "+m.getMemberType().value());
		
	}

	public static StemIdentifier getStemIdentifier(StemDescriptor des) {
		StemIdentifier id = new StemIdentifier();
		id.setStemName(des.getName());
		return id;
	}


	public static StemIdentifier getRootStemIdentifier() {
		StemIdentifier id = new StemIdentifier();
		id.setStemName(GridGrouperService.ROOT_STEM);
		return id;
	}


	public static GroupIdentifier getGroupIdentifier(GroupDescriptor des) {
		GroupIdentifier id = new GroupIdentifier();
		id.setGroupName(des.getName());
		return id;
	}


	public static void printMemberships(GroupDescriptor grp) throws Exception {
		printMemberships(grp, AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID);
	}
	
	public static void printMemberships(GroupDescriptor grp, String userId) throws Exception {
		Subject subject = SubjectUtils.getSubject(userId);
		GrouperSession session = GrouperSession.start(subject);
		Group group = GroupFinder.findByName(session, grp.getName());
		System.out.println();
		System.out.println("All Memberships of " + group.getName());
		System.out.println("-------------------------------------------------");
		System.out.println();
		Set set = group.getMemberships();
		Iterator itr = set.iterator();
		while (itr.hasNext()) {
			Membership m = (Membership) itr.next();
			System.out.println("Member Name: " + m.getMember().getSubject().getId());
			System.out.println("Depth: " + m.getDepth());
			System.out.println("Group: " + m.getGroup().getName());
			try {
				System.out.println("Via Group: " + m.getViaGroup().getName());
			} catch (GroupNotFoundException e) {
				System.out.println("Via Group: NONE");
			}

			try {
				System.out.println("Via Owner: " + m.getVia().getUuid());
			} catch (OwnerNotFoundException e) {
				System.out.println("Via Owner: NONE");
			}
			System.out.println();
		}
	}


	public static void printCompositeMemberships(GroupDescriptor grp) throws Exception {
		Subject subject = SubjectUtils.getSubject(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID);
		GrouperSession session = GrouperSession.start(subject);
		Group group = GroupFinder.findByName(session, grp.getName());
		System.out.println();
		System.out.println("Composite Memberships of " + group.getName());
		System.out.println("-------------------------------------------------");
		System.out.println();
		Set set = group.getCompositeMemberships();
		Iterator itr = set.iterator();
		while (itr.hasNext()) {
			Membership m = (Membership) itr.next();
			System.out.println("Member Name: " + m.getMember().getSubject().getId());
			System.out.println("Depth: " + m.getDepth());
			System.out.println("Group: " + m.getGroup().getName());
			try {
				System.out.println("Via Group: " + m.getViaGroup().getName());
			} catch (GroupNotFoundException e) {
				System.out.println("Via Group: NONE");
			}

			try {
				System.out.println("Via Owner: " + m.getVia().getUuid());
			} catch (OwnerNotFoundException e) {
				System.out.println("Via Owner: NONE");
			}
			System.out.println();
		}
	}


	public static void printUsersWithPrivilege(GroupDescriptor des, GroupPrivilegeType priv) throws Exception {
		GridGrouper grouper = new GridGrouper();
		List<String> subs = grouper.getSubjectsWithGroupPrivilege(SUPER_USER, Utils.getGroupIdentifier(des), priv);
		System.out.println("Users with the Privilege, " + priv.value() + " on the group " + des.getName() + ":");
		System.out.println("");
		for (String sub : subs) {
			System.out.println(sub);
		}
		System.out.println("");
	}


	public static void printPrivilegesForUser(GroupDescriptor des, String user) throws Exception {
		GridGrouper grouper = new GridGrouper();
		List<GroupPrivilege> privs = grouper.getGroupPrivileges(SUPER_USER, Utils.getGroupIdentifier(des), user);
		System.out.println("Privileges for " + user + ", on the group " + des.getName() + ":");
		System.out.println("");
		for (GroupPrivilege gp : privs) {
			System.out.println(gp.getPrivilegeType().value());
		}
		System.out.println("");
	}

}
