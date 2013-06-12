package edu.internet2.middleware.grouper;

import org.cagrid.gridgrouper.model.GroupDescriptor;
import org.cagrid.gridgrouper.model.GroupPrivilegeType;
import org.cagrid.gridgrouper.model.MembershipRequestDescriptor;
import org.cagrid.gridgrouper.model.MembershipRequestStatus;
import org.cagrid.gridgrouper.model.MembershipRequestUpdate;
import org.cagrid.gridgrouper.model.StemDescriptor;
import org.cagrid.gridgrouper.service.exception.InsufficientPrivilegeException;
import org.cagrid.gridgrouper.service.impl.testutils.Utils;
import org.cagrid.gridgrouper.service.impl.tools.GridGrouperBootstrapper;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestMembershipRequestsPrivileges extends GrouperBaseTest {

	public static final String SUPER_USER = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=super admin";
	public static final String SUPER_USER2 = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=super admin2";

	public static final String GROUPER_ALL = "GrouperAll";

	private String USER_A = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user a";

	private String USER_Aadmin = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user aadmin";

	private String USER_B = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user b";

	private String USER_Badmin = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user badmin";

	private String USER_C = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user c";

	private String USER_D = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user d";

    @Test
	public void testWheelGetPrivileges() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();

			List<MembershipRequestDescriptor> members = grouper.getMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Pending);

			assertEquals("Did not retrieve the expected pending membership requests", 4, members.size());

			members = grouper.getMembershipRequests(SUPER_USER2, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Pending);

			assertEquals("Did not retrieve the expected pending membership requests", 4, members.size());

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testAdminGetPrivileges() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();

			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_Aadmin);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_Badmin);
			grouper.grantGroupPrivilege(SUPER_USER, Utils.getGroupIdentifier(grp), USER_Aadmin, GroupPrivilegeType.ADMIN);

			List<MembershipRequestDescriptor> members = grouper.getMembershipRequests(USER_Aadmin, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Pending);
			assertEquals("Did not retrieve the expected pending membership requests", 4, members.size());

			members = grouper.getMembershipRequests(USER_Badmin, Utils.getGroupIdentifier(grp), MembershipRequestStatus.Pending);
			assertEquals("USER_Badmin should not be able to retrieve any requests", 0, members.size());
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testAdminUpdatePrivileges() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();

			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_Aadmin);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_Badmin);
			grouper.grantGroupPrivilege(SUPER_USER, Utils.getGroupIdentifier(grp), USER_Aadmin, GroupPrivilegeType.ADMIN);
			

			MembershipRequestUpdate update = new MembershipRequestUpdate("", "A note", MembershipRequestStatus.Approved);
			try {
				grouper.updateMembershipRequest(USER_Aadmin, Utils.getGroupIdentifier(grp), USER_A, update);
			} catch (InsufficientPrivilegeException e) {
				fail("Should be able to approve membership to group with admin privileges");
			}

			try {
				grouper.updateMembershipRequest(USER_Badmin, Utils.getGroupIdentifier(grp), USER_B, update);
				fail("Should not be able to approve membership to group without admin privileges");
			} catch (InsufficientPrivilegeException e) {
				// Expected Fault
			}

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testUserGetPrivileges() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();

			List<MembershipRequestDescriptor> members = grouper.getMembershipRequests(USER_A, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Pending);

			assertEquals("Did not retrieve the expected pending membership requests", 1, members.size());
			assertEquals("User A retrieved a request different than his own.", USER_A, members.get(0).getRequestorId());

			members = grouper.getMembershipRequests(USER_Aadmin, Utils.getGroupIdentifier(grp), MembershipRequestStatus.Pending);

			assertEquals("Did not retrieve the expected pending membership requests", 0, members.size());

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testUserUpdatePrivileges() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();

			MembershipRequestUpdate update = new MembershipRequestUpdate("", "A note", MembershipRequestStatus.Approved);
			grouper.updateMembershipRequest(USER_A, Utils.getGroupIdentifier(grp), USER_A, update);

			fail("Should not be able to self approve membership");
		} catch (InsufficientPrivilegeException e) {
			// Expected Fault
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testUserGrantMembershipRequests() {
		try {
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);
			grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);
			final String groupExtension = "mygroup";
			final String groupDisplayExtension = "My Group";

			GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A);
			grouper.enableMembershipRequests(USER_A, Utils.getGroupIdentifier(grp));

			fail("Should not be able to grant membership requests");
		} catch (InsufficientPrivilegeException e) {
			// Expected Fault
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testUserRevokeMembershipRequests() {
		try {
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);
			grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);
			final String groupExtension = "mygroup";
			final String groupDisplayExtension = "My Group";

			GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A);
			grouper.enableMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp));
			grouper.disableMembershipRequests(USER_A, Utils.getGroupIdentifier(grp));
			fail("Should not be able to revoke membership requests");
		} catch (InsufficientPrivilegeException e) {
			// Expected Fault
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testAdminGrantMembershipRequests() {
		try {
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);
			grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);
			String groupExtension = "mygroup";
			String groupDisplayExtension = "My Group";

			GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A);
			grouper.grantGroupPrivilege(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A, GroupPrivilegeType.ADMIN);
			grouper.enableMembershipRequests(USER_A, Utils.getGroupIdentifier(grp));

			groupExtension = "mygroup2";
			groupDisplayExtension = "My Group 2";

			GroupDescriptor grp2 = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp2), USER_B);
			grouper.grantGroupPrivilege(SUPER_USER, Utils.getGroupIdentifier(grp2), USER_B, GroupPrivilegeType.ADMIN);
			try {
				grouper.enableMembershipRequests(USER_A, Utils.getGroupIdentifier(grp2));
				fail("Should not be able to grant membership requests");
			} catch (InsufficientPrivilegeException e) {
				// Expected Fault
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testAdminRevokeMembershipRequests() {
		try {
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);
			grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);
			String groupExtension = "mygroup";
			String groupDisplayExtension = "My Group";

			GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A);
			grouper.grantGroupPrivilege(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A, GroupPrivilegeType.ADMIN);
			grouper.enableMembershipRequests(USER_A, Utils.getGroupIdentifier(grp));
			grouper.disableMembershipRequests(USER_A, Utils.getGroupIdentifier(grp));

			groupExtension = "mygroup2";
			groupDisplayExtension = "My Group 2";

			GroupDescriptor grp2 = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp2), USER_B);
			grouper.grantGroupPrivilege(SUPER_USER, Utils.getGroupIdentifier(grp2), USER_B, GroupPrivilegeType.ADMIN);
			grouper.enableMembershipRequests(USER_B, Utils.getGroupIdentifier(grp2));
			try {
				grouper.disableMembershipRequests(USER_A, Utils.getGroupIdentifier(grp2));
				fail("Should not be able to revoke membership requests");
			} catch (InsufficientPrivilegeException e) {
				// Expected Fault
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testWheelGrantMembershipRequests() {
		try {
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);
			GridGrouperBootstrapper.addAdminMember(SUPER_USER2);
			grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);
			String groupExtension = "mygroup";
			String groupDisplayExtension = "My Group";

			GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);
			grouper.addMember(SUPER_USER2, Utils.getGroupIdentifier(grp), USER_A);
			grouper.grantGroupPrivilege(SUPER_USER2, Utils.getGroupIdentifier(grp), USER_A, GroupPrivilegeType.ADMIN);
			grouper.enableMembershipRequests(SUPER_USER2, Utils.getGroupIdentifier(grp));

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testWheelRevokeMembershipRequests() {
		try {
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);
			GridGrouperBootstrapper.addAdminMember(SUPER_USER2);
			grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);
			String groupExtension = "mygroup";
			String groupDisplayExtension = "My Group";

			GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);
			grouper.addMember(SUPER_USER2, Utils.getGroupIdentifier(grp), USER_A);
			grouper.grantGroupPrivilege(SUPER_USER2, Utils.getGroupIdentifier(grp), USER_A, GroupPrivilegeType.ADMIN);
			grouper.enableMembershipRequests(SUPER_USER2, Utils.getGroupIdentifier(grp));
			grouper.disableMembershipRequests(SUPER_USER2, Utils.getGroupIdentifier(grp));

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	private GroupDescriptor initialGroupAndRequestSetup() throws Exception {
		GridGrouperBootstrapper.addAdminMember(SUPER_USER);
		GridGrouperBootstrapper.addAdminMember(SUPER_USER2);
		grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());

		String testStem = "TestStem";
		StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);
		final String groupExtension = "mygroup";
		final String groupDisplayExtension = "My Group";

		GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);

		final String subGroupExtension = "mysubgroup";
		final String subGroupDisplayExtension = "My Sub Group";

		createAndCheckGroup(test, subGroupExtension, subGroupDisplayExtension, 2);

		grouper.enableMembershipRequests(SUPER_USER2, Utils.getGroupIdentifier(grp));

		grouper.addMembershipRequest(USER_A, Utils.getGroupIdentifier(grp));
		grouper.addMembershipRequest(USER_B, Utils.getGroupIdentifier(grp));
		grouper.addMembershipRequest(USER_C, Utils.getGroupIdentifier(grp));
		grouper.addMembershipRequest(USER_D, Utils.getGroupIdentifier(grp));
		return grp;
	}

	protected GroupDescriptor createAndCheckGroup(StemDescriptor stem, String extension, String displayExtension,
			int childGroupCount) throws Exception {
		GroupDescriptor grp = grouper.addChildGroup(SUPER_USER, Utils.getStemIdentifier(stem), extension, displayExtension);
		return grp;
	}

}
