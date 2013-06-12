package edu.internet2.middleware.grouper;

import net.sf.hibernate.exception.ExceptionUtils;
import org.cagrid.gridgrouper.model.GroupDescriptor;
import org.cagrid.gridgrouper.model.MemberFilter;
import org.cagrid.gridgrouper.model.MembershipRequestDescriptor;
import org.cagrid.gridgrouper.model.MembershipRequestStatus;
import org.cagrid.gridgrouper.model.MembershipRequestUpdate;
import org.cagrid.gridgrouper.model.StemDescriptor;
import org.cagrid.gridgrouper.service.impl.testutils.Utils;
import org.cagrid.gridgrouper.service.impl.tools.GridGrouperBootstrapper;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestMembershipRequests extends GrouperBaseTest {

	public static final String SUPER_USER = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=super admin";

	public static final String GROUPER_ALL = "GrouperAll";

	private String USER_A = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user a";

	private String USER_B = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user b";

	private String USER_C = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user c";

	private String USER_D = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user d";

    @Test
	public void testAddMembershipRequests() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();
			grouper.enableMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp));

			grouper.addMembershipRequest(USER_A, Utils.getGroupIdentifier(grp));
			grouper.addMembershipRequest(USER_B, Utils.getGroupIdentifier(grp));
			grouper.addMembershipRequest(USER_C, Utils.getGroupIdentifier(grp));
			grouper.addMembershipRequest(USER_D, Utils.getGroupIdentifier(grp));

			List<MembershipRequestDescriptor> members = grouper.getMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Pending);

			assertEquals("Do not retrieve the expected pending membership requests", 4, members.size());
		} catch (Exception e) {
			fail(ExceptionUtils.getFullStackTrace(e));
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

    @Test
	public void testApproveMembershipRequest() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();
			grouper.enableMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp));

			grouper.addMembershipRequest(USER_A, Utils.getGroupIdentifier(grp));
			grouper.addMembershipRequest(USER_B, Utils.getGroupIdentifier(grp));
			grouper.addMembershipRequest(USER_C, Utils.getGroupIdentifier(grp));
			grouper.addMembershipRequest(USER_D, Utils.getGroupIdentifier(grp));

			MembershipRequestUpdate update = new MembershipRequestUpdate();
			update.setStatus(MembershipRequestStatus.Approved);
			update.setPublicNote("I approve of this approval.");

			grouper.updateMembershipRequest(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A, update);

			GrouperSession session = GrouperSession.start(SubjectFinder.findById(SUPER_USER));

			assertTrue(grouper.isMemberOf(session, Utils.getGroupIdentifier(grp), USER_A, MemberFilter.ALL));
			assertFalse(grouper.isMemberOf(session, Utils.getGroupIdentifier(grp), USER_B, MemberFilter.ALL));

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testRejectMembershipRequest() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();
			grouper.enableMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp));

			grouper.addMembershipRequest(USER_A, Utils.getGroupIdentifier(grp));
			grouper.addMembershipRequest(USER_B, Utils.getGroupIdentifier(grp));
			grouper.addMembershipRequest(USER_C, Utils.getGroupIdentifier(grp));
			grouper.addMembershipRequest(USER_D, Utils.getGroupIdentifier(grp));

			MembershipRequestUpdate update = new MembershipRequestUpdate();
			update.setStatus(MembershipRequestStatus.Rejected);
			update.setPublicNote("I reject this rejection.");

			grouper.updateMembershipRequest(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A, update);

			GrouperSession session = GrouperSession.start(SubjectFinder.findById(SUPER_USER));

			assertFalse(grouper.isMemberOf(session, Utils.getGroupIdentifier(grp), USER_A, MemberFilter.ALL));

			List<MembershipRequestDescriptor> members = grouper.getMembershipRequests(SUPER_USER, Utils.getGroupIdentifier(grp),
					MembershipRequestStatus.Rejected);
			assertEquals("Did not find the rejected request", 1, members.size());

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	protected GroupDescriptor createAndCheckGroup(StemDescriptor stem, String extension, String displayExtension,
			int childGroupCount) throws Exception {
		GroupDescriptor grp = grouper.addChildGroup(SUPER_USER, Utils.getStemIdentifier(stem), extension, displayExtension);
		return grp;
	}

}
