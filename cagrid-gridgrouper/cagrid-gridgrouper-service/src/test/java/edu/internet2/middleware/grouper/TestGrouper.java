package edu.internet2.middleware.grouper;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import org.cagrid.gridgrouper.model.GroupDescriptor;
import org.cagrid.gridgrouper.model.GroupPrivilegeType;
import org.cagrid.gridgrouper.model.MemberDescriptor;
import org.cagrid.gridgrouper.model.MemberFilter;
import org.cagrid.gridgrouper.model.StemDescriptor;
import org.cagrid.gridgrouper.service.impl.testutils.Utils;
import org.cagrid.gridgrouper.service.impl.tools.GridGrouperBootstrapper;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestGrouper extends GrouperBaseTest {

//	public static final String SUPER_USER = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=super admin";
	public static final String SUPER_USER = "/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=jmgeorge";

	public static final String GROUPER_ALL = "GrouperAll";

	private String USER_A = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user a";

	private String USER_Aadmin = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user aadmin";

	private String USER_B = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user b";

	private String USER_Badmin = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user badmin";

	private String USER_C = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user c";

	private String USER_D = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user d";

    private void clearMembershipRequestsTable() throws HibernateException {
        Session hs = GridGrouperHibernateHelper.getSession();
        Transaction tx = hs.beginTransaction();

        hs.delete("from MembershipRequestHistory");
        hs.delete("from MembershipRequest");

        tx.commit();
        hs.close();
    }

    @Test
	public void testAdminGetPrivileges() {
		try {
			GroupDescriptor grp = initialGroupAndRequestSetup();

			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_Aadmin);
			grouper.grantGroupPrivilege(SUPER_USER, Utils.getGroupIdentifier(grp), USER_Aadmin, GroupPrivilegeType.ADMIN);
			grouper.addMember(USER_Aadmin, Utils.getGroupIdentifier(grp), USER_A);
			grouper.addMember(USER_Aadmin, Utils.getGroupIdentifier(grp), USER_B);
			grouper.addMember(USER_Aadmin, Utils.getGroupIdentifier(grp), USER_C);

			grouper.revokeGroupPrivilege(USER_Aadmin, Utils.getGroupIdentifier(grp), "GrouperAll", GroupPrivilegeType.READ);

			List<MemberDescriptor> members = grouper.getMembers(USER_Badmin, Utils.getGroupIdentifier(grp), MemberFilter.ALL);

			assertEquals(0, members.size());

			grouper.grantGroupPrivilege(USER_Aadmin, Utils.getGroupIdentifier(grp), "GrouperAll", GroupPrivilegeType.READ);

			members = grouper.getMembers(USER_Badmin, Utils.getGroupIdentifier(grp), MemberFilter.ALL);

			assertEquals(4, members.size());

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
