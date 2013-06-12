package edu.internet2.middleware.grouper;

import org.cagrid.gridgrouper.model.GroupDescriptor;
import org.cagrid.gridgrouper.model.MembershipRequestDescriptor;
import org.cagrid.gridgrouper.model.MembershipRequestStatus;
import org.cagrid.gridgrouper.model.MembershipRequestUpdate;
import org.cagrid.gridgrouper.model.StemDescriptor;
import org.cagrid.gridgrouper.service.exception.MemberAddException;
import org.cagrid.gridgrouper.service.impl.testutils.Utils;
import org.cagrid.gridgrouper.service.impl.tools.GridGrouperBootstrapper;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class TestMembershipRequestsEdgeCases extends GrouperBaseTest {

	public static final String SUPER_USER = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=super admin";

	public static final String GROUPER_ALL = "GrouperAll";

	private String USER_A = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user a";

	private String USER_B = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user b";

	private String USER_C = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user c";

	private String USER_D = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user d";

    @Test
	public void testAddMembershipRequestsTwice() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();
			grouper.enableMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp));
			grouper.enableMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp));


			grouper.addMembershipRequest(USER_A, Utils.getGroupIdentifier(grp));
			grouper.addMembershipRequest(USER_A, Utils.getGroupIdentifier(grp));
			
			List<MembershipRequestDescriptor> members = grouper.getMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Pending);

			assertEquals("Only one membership request should be generated.", 1, members.size());
		} catch (MemberAddException e) {
			// Expected fault
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testMembershipRetrieveal() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();
			grouper.enableMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp));

			grouper.addMembershipRequest(USER_A, Utils.getGroupIdentifier(grp));

			List<MembershipRequestDescriptor> members = grouper.getMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Pending);

			MembershipRequestUpdate update = new MembershipRequestUpdate("A note", "A note",MembershipRequestStatus.Rejected);
			grouper.updateMembershipRequest(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A, update);

			members = grouper.getMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Rejected);

			assertEquals("Only one membership request should be generated.", 1, members.size());

		} catch (MemberAddException e) {
			// Expected fault
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testAddMembershipRequestsForExistingMember() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();
			grouper.enableMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp));


			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A);
			grouper.addMembershipRequest(USER_A, Utils.getGroupIdentifier(grp));

			List<MembershipRequestDescriptor> members = grouper.getMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Pending);

			assertEquals("Shouldn't be able to request membership if already a member", 0, members.size());
		} catch (MemberAddException e) {
			// Expected fault
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testAddMembershipRequestsForRejectedMember() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();
			grouper.enableMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp));

			grouper.addMembershipRequest(USER_A, Utils.getGroupIdentifier(grp));

			MembershipRequestUpdate update = new MembershipRequestUpdate(null, "A note", MembershipRequestStatus.Rejected);
			grouper.updateMembershipRequest(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A, update);

			try {
				grouper.addMembershipRequest(USER_A, Utils.getGroupIdentifier(grp));
				fail("Should not be able to add a membership request if a previous request has been rejected");
			} catch (MemberAddException e) {
				// Expected fault
			}

			List<MembershipRequestDescriptor> members = grouper.getMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Rejected);

			assertEquals("Membership request should still be rejected", 1, members.size());
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testAddMembershipRequestsForRemovedMember() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();
			grouper.enableMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp));

			grouper.addMembershipRequest(USER_A, Utils.getGroupIdentifier(grp));

			MembershipRequestUpdate update = new MembershipRequestUpdate(null, "A note", MembershipRequestStatus.Approved);
			grouper.updateMembershipRequest(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A, update);

			grouper.deleteMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A);

			grouper.addMembershipRequest(USER_A, Utils.getGroupIdentifier(grp));

			List<MembershipRequestDescriptor> members = grouper.getMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Pending);

			assertEquals("Should be able to request membership", 1, members.size());

			assertEquals("Should have membership request history", 4, members.get(0).getHistory().size());
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testNotePrivieges() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();
			grouper.enableMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp));

			grouper.addMembershipRequest(USER_A, Utils.getGroupIdentifier(grp));

			MembershipRequestUpdate update = new MembershipRequestUpdate("An Approved Admin Note", "An Approved Public Note", MembershipRequestStatus.Approved);
			grouper.updateMembershipRequest(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A, update);

			List<MembershipRequestDescriptor> members = grouper.getMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Approved);

			assertEquals("Should be able to request membership", 1, members.size());

			assertEquals("Should have membership request history", 2, members.get(0).getHistory().size());

			if (members.get(0).getHistory().get(0).getAdminNote() != null) {
				assertEquals("Admin should be able to see see the admin note", "An Approved Admin Note", members.get(0).getHistory().get(0).getAdminNote());
			} else {
				assertEquals("Admin should be able to see see the admin note", "An Approved Admin Note", members.get(0).getHistory().get(1).getAdminNote());
			}

			members = grouper.getMembershipRequests(USER_A, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Approved);

			assertEquals("Should have membership request history", 2, members.get(0).getHistory().size());
			assertNull("User should not see the admin not", members.get(0).getHistory().get(0).getAdminNote());
			assertNull("User should not see the admin not", members.get(0).getHistory().get(1).getAdminNote());
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}
	
	private GroupDescriptor initialGroupAndRequestSetup() throws Exception {
		GridGrouperBootstrapper.addAdminMember(SUPER_USER);
		grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());

		String testStem = "TestStem";
		StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);
		final String groupExtension = "mygroup";
		final String groupDisplayExtension = "My Group";

		GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);

		final String subGroupExtension = "mysubgroup";
		final String subGroupDisplayExtension = "My Sub Group";

		createAndCheckGroup(test, subGroupExtension, subGroupDisplayExtension, 2);
		return grp;
	}


	protected GroupDescriptor createAndCheckGroup(StemDescriptor stem, String extension, String displayExtension,
			int childGroupCount) throws Exception {
		GroupDescriptor grp = grouper.addChildGroup(SUPER_USER, Utils.getStemIdentifier(stem), extension, displayExtension);
		return grp;
	}

}
