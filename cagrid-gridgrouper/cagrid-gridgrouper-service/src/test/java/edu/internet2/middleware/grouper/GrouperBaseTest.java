package edu.internet2.middleware.grouper;

import edu.internet2.middleware.GrouperInit;
import edu.internet2.middleware.subject.AnonymousGridUserSubject;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.exception.ExceptionUtils;
import org.cagrid.gridgrouper.model.GroupDescriptor;
import org.cagrid.gridgrouper.model.GroupPrivilege;
import org.cagrid.gridgrouper.model.GroupPrivilegeType;
import org.cagrid.gridgrouper.model.MemberDescriptor;
import org.cagrid.gridgrouper.model.MemberFilter;
import org.cagrid.gridgrouper.model.MemberType;
import org.cagrid.gridgrouper.model.MembershipDescriptor;
import org.cagrid.gridgrouper.model.MembershipType;
import org.cagrid.gridgrouper.model.StemDescriptor;
import org.cagrid.gridgrouper.service.impl.GridGrouper;
import org.cagrid.gridgrouper.service.impl.testutils.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public abstract class GrouperBaseTest {

	public static final String SUPER_USER = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=super admin";

	public GridGrouper grouper = null;
	
	public static final String GROUPER_ALL = "GrouperAll";

    private static boolean dbInitialized = false;

    @Before
	public void setUp() throws Exception {
        clearMembershipRequestsTable();
		RegistryReset.reset();
		this.grouper = new GridGrouper();
	}

    @After
	public void tearDown() throws Exception {
        clearMembershipRequestsTable();
		RegistryReset.reset();
	}

    @BeforeClass
    public static void initDb() {

        if (!dbInitialized) {
            GrouperInit.main(new String[]{"schema-export.sql", "src/test/resources/grouper.hibernate.properties", "src/test/resources/edu/internet2/middleware/grouper"});
            dbInitialized = true;
        }
    }

    private void clearMembershipRequestsTable() throws HibernateException {
        Session hs = GridGrouperHibernateHelper.getSession();
        Transaction tx = hs.beginTransaction();

        hs.delete("from MembershipRequestHistory");
        hs.delete("from MembershipRequest");

        tx.commit();
        hs.close();
    }

	protected GroupDescriptor createAndCheckGroup(StemDescriptor stem, String extension, String displayExtension,
		int childGroupCount) throws Exception {
		GroupDescriptor grp = grouper.addChildGroup(SUPER_USER, Utils.getStemIdentifier(stem), extension,
			displayExtension);
		assertEquals(extension, grp.getExtension());
		assertEquals(displayExtension, grp.getDisplayExtension());
		assertEquals(childGroupCount, grouper.getChildGroups(SUPER_USER, Utils.getStemIdentifier(stem)).size());
		assertFalse(grp.isHasComposite());
		Map expected = new HashMap();
		expected.clear();
		verifyMembers(grp, MemberFilter.ALL, expected);
		expected.clear();
		verifyMembers(grp, MemberFilter.EFFECTIVE_MEMBERS, expected);
		expected.clear();
		verifyMembers(grp, MemberFilter.IMMEDIATE_MEMBERS, expected);
		expected.clear();
		verifyMembers(grp, MemberFilter.COMPOSITE_MEMBERS, expected);

		expected.clear();
		verifyMemberships(grp, MemberFilter.ALL, 0, expected);
		expected.clear();
		verifyMemberships(grp, MemberFilter.EFFECTIVE_MEMBERS, 0, expected);
		expected.clear();
		verifyMemberships(grp, MemberFilter.IMMEDIATE_MEMBERS, 0, expected);
		expected.clear();
		verifyMemberships(grp, MemberFilter.COMPOSITE_MEMBERS, 0, expected);
		return grp;
	}


	protected void verifyMembers(GroupDescriptor grp, MemberFilter filter, Map expected) {
		verifyMembers(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, grp, filter, expected);
	}


	protected void verifyMembers(String caller, GroupDescriptor grp, MemberFilter filter, Map expected) {
		try {
			int expectedCount = expected.size();
			assertEquals(expectedCount, expected.size());
			List<MemberDescriptor> members = grouper.getMembers(caller, Utils.getGroupIdentifier(grp), filter);
			assertEquals(expectedCount, members.size());

			for (int i = 0; i < expectedCount; i++) {
				if (expected.containsKey(members.get(i).getSubjectId())) {
					MemberCaddy caddy = (MemberCaddy) expected.remove(members.get(i).getSubjectId());
					assertEquals(caddy.getMemberId(), members.get(i).getSubjectId());
					assertEquals(caddy.getMemberType(), members.get(i).getMemberType());
					if (!filter.equals(MemberFilter.COMPOSITE_MEMBERS)) {
						assertTrue(grouper.isMemberOf(caller, Utils
							.getGroupIdentifier(grp), caddy.getMemberId(), filter));
					}
				} else {
					fail("Member " + members.get(i).getSubjectId() + " not expected!!!");
				}
			}
			assertEquals(0, expected.size());
		} catch (Exception e) {
			fail(ExceptionUtils.getFullStackTrace(e));
		}

	}
	
	protected void verifyMembersGroups(String caller, String member, MembershipType type, Map expected) {
		try {
			int expectedCount = expected.size();
			assertEquals(expectedCount, expected.size());
			List<GroupDescriptor> groups = grouper.getMembersGroups(caller, member, type);
			assertEquals(expectedCount, groups.size());

			for (int i = 0; i < expectedCount; i++) {
				if (expected.containsKey(groups.get(i).getName())) {
					GroupDescriptor grp = (GroupDescriptor) expected.remove(groups.get(i).getName());
					assertEquals(grp.getUUID(), groups.get(i).getUUID());
				} else {
					fail("Group " + groups.get(i).getName() + " not expected!!!");
				}
			}
			assertEquals(0, expected.size());
		} catch (Exception e) {
			fail(ExceptionUtils.getFullStackTrace(e));
		}

	}


	protected void verifyMemberships(GroupDescriptor grp, MemberFilter filter, int expectedCount, Map expected) {
		try {
			assertEquals(expectedCount, expected.size());
			List<MembershipDescriptor> members = grouper.getMemberships(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID,
				Utils.getGroupIdentifier(grp), filter);
			assertEquals(expectedCount, members.size());

			for (int i = 0; i < expectedCount; i++) {
				if (expected.containsKey(members.get(i).getMember().getSubjectId())) {
					MembershipCaddy caddy = (MembershipCaddy) expected.remove(members.get(i).getMember().getSubjectId());
					assertEquals(caddy.getMemberId(), members.get(i).getMember().getSubjectId());
					assertEquals(caddy.getMemberType(), members.get(i).getMember().getMemberType());
					assertEquals(caddy.getDepth(), members.get(i).getDepth());

					assertEquals(caddy.getGroupName(), members.get(i).getGroup().getName());
					String viaGN = null;
					if (members.get(i).getViaGroup() != null) {
						viaGN = members.get(i).getViaGroup().getName();
					}
					assertEquals(caddy.getViaGroupName(), viaGN);
					if (!filter.equals(MemberFilter.COMPOSITE_MEMBERS)) {
						assertTrue(grouper.isMemberOf(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
							.getGroupIdentifier(grp), caddy.getMemberId(), filter));
					}
				} else {
					fail("Membership " + members.get(i).getMember().getSubjectId() + " not expected!!!");
				}
			}
			assertEquals(0, expected.size());
		} catch (Exception e) {
			fail(ExceptionUtils.getFullStackTrace(e));
		}

	}


	protected void verifyUserPrivileges(GroupDescriptor grp, String user, HashSet expected) {
		verifyUserPrivileges(SUPER_USER, grp, user, expected);
	}


	protected void verifyUserPrivileges(String caller, GroupDescriptor grp, String user, HashSet expected) {
		try {
			List<GroupPrivilege> privs = grouper.getGroupPrivileges(caller, Utils.getGroupIdentifier(grp), user);
			assertEquals(expected.size(), privs.size());
			for (int i = 0; i < privs.size(); i++) {
				if (expected.contains(privs.get(i).getPrivilegeType())) {
					assertEquals(user, privs.get(i).getSubject());
					expected.remove(privs.get(i).getPrivilegeType());
				} else {
					fail("The privilege " + privs.get(i).getPrivilegeType().value() + " was not expected!!!");
				}
			}
			assertEquals(0, expected.size());
		} catch (Exception e) {
			fail(ExceptionUtils.getFullStackTrace(e));
		}

	}


	protected void verifyPrivileges(GroupDescriptor grp, GroupPrivilegeType priv, HashSet expected) {
		verifyPrivileges(SUPER_USER, grp, priv, expected);
	}


	protected void verifyPrivileges(String caller, GroupDescriptor grp, GroupPrivilegeType priv, HashSet expected) {
		try {
			List<String> users = grouper.getSubjectsWithGroupPrivilege(caller, Utils.getGroupIdentifier(grp), priv);
			assertEquals(expected.size(), users.size());
			for (int i = 0; i < users.size(); i++) {
				if (expected.contains(users.get(i))) {
					expected.remove(users.get(i));
				} else {
					fail("The privilege " + priv.value() + " was not expected for the user " + users.get(i) + "!!!");
				}
			}
			assertEquals(0, expected.size());
		} catch (Exception e) {
			fail(ExceptionUtils.getFullStackTrace(e));
		}

	}


	protected MemberCaddy getGridMember(String name) {
		return new MemberCaddy(name, MemberType.GRID);
	}


	protected class MemberCaddy {
		private String memberId;

		private MemberType memberType;


		public MemberCaddy(String id, MemberType type) {
			this.memberId = id;
			this.memberType = type;
		}


		public String getMemberId() {
			return memberId;
		}


		public MemberType getMemberType() {
			return memberType;
		}

	}


	protected class MembershipCaddy {
		private String memberId;

		private String groupName;

		private String viaGroupName;

		private int depth;

		private MemberType memberType;


		public MembershipCaddy(String id, String groupName, String viaGroupName, int depth, MemberType type) {
			this.memberId = id;
			this.memberType = type;
			this.groupName = groupName;
			this.viaGroupName = viaGroupName;
			this.depth = depth;
		}


		public String getMemberId() {
			return memberId;
		}


		public MemberType getMemberType() {
			return memberType;
		}


		public int getDepth() {
			return depth;
		}


		public String getGroupName() {
			return groupName;
		}


		public String getViaGroupName() {
			return viaGroupName;
		}

	}
	
	protected MemberCaddy getGroupMember(String name) {
		return new MemberCaddy(name, MemberType.GROUPER_GROUP);
	}

}
