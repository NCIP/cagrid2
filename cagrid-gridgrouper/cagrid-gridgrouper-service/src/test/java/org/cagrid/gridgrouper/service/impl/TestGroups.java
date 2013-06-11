package org.cagrid.gridgrouper.service.impl;

import edu.internet2.middleware.subject.AnonymousGridUserSubject;
import org.cagrid.gridgrouper.model.GroupCompositeType;
import org.cagrid.gridgrouper.model.GroupDescriptor;
import org.cagrid.gridgrouper.model.GroupIdentifier;
import org.cagrid.gridgrouper.model.GroupPrivilegeType;
import org.cagrid.gridgrouper.model.GroupUpdate;
import org.cagrid.gridgrouper.model.LogicalOperator;
import org.cagrid.gridgrouper.model.MemberFilter;
import org.cagrid.gridgrouper.model.MemberType;
import org.cagrid.gridgrouper.model.MembershipExpression;
import org.cagrid.gridgrouper.model.MembershipQuery;
import org.cagrid.gridgrouper.model.MembershipStatus;
import org.cagrid.gridgrouper.model.StemDescriptor;
import org.cagrid.gridgrouper.model.StemPrivilegeType;
import org.cagrid.gridgrouper.service.exception.InsufficientPrivilegeException;
import org.cagrid.gridgrouper.service.exception.MemberAddException;
import org.cagrid.gridgrouper.service.impl.testutils.Utils;
import org.cagrid.gridgrouper.service.impl.tools.GridGrouperBootstrapper;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.*;


/**
* @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
* @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
* @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
* @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
*          Exp $
*/
public class TestGroups extends GrouperBaseTest {

	private String USER_A = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user a";

	private String USER_B = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user b";

	private String USER_C = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user c";

	private String USER_D = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user d";


	// private String GROUPER_ALL = "GrouperAll";
    @Test
	public void testViewReadPrivilege() {
		try {
			Map memberExpected = new HashMap();
			HashSet userExpected = new HashSet();
			HashSet privsExpected = new HashSet();
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.CREATE));

			StemDescriptor root = grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());
			assertNotNull(root);
			assertEquals(root.getName(), Utils.getRootStemIdentifier().getStemName());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);
			final String groupExtension = "mygroup";
			final String groupDisplayExtension = "My Group";

			GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);
			GroupIdentifier gid = Utils.getGroupIdentifier(grp);

			userExpected.clear();
			userExpected.add(GroupPrivilegeType.ADMIN);
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			verifyUserPrivileges(grp, SUPER_USER, userExpected);
			assertTrue(grouper.hasGroupPrivilege(SUPER_USER, gid, SUPER_USER, GroupPrivilegeType.READ));

			// Test Default Privileges
			userExpected.clear();
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			verifyUserPrivileges(grp, USER_A, userExpected);
			assertTrue(grouper.hasGroupPrivilege(SUPER_USER, gid, USER_A, GroupPrivilegeType.READ));
			assertTrue(grouper.hasGroupPrivilege(SUPER_USER, gid, USER_A, GroupPrivilegeType.VIEW));
			assertFalse(grouper.hasGroupPrivilege(SUPER_USER, gid, USER_A, GroupPrivilegeType.OPTIN));
			assertFalse(grouper.hasGroupPrivilege(SUPER_USER, gid, USER_A, GroupPrivilegeType.OPTOUT));
			assertFalse(grouper.hasGroupPrivilege(SUPER_USER, gid, USER_A, GroupPrivilegeType.UPDATE));
			assertFalse(grouper.hasGroupPrivilege(SUPER_USER, gid, USER_A, GroupPrivilegeType.ADMIN));

			// TODO: Should this pass Should we be able to remove a default
			// privilege?

			// We want to test doing everything

			String description = "This is a test group";
			GroupUpdate update = new GroupUpdate();
			update.setDescription(description);
			grouper.updateGroup(SUPER_USER, gid, update);
			grouper.addMember(SUPER_USER, gid, USER_B);
			memberExpected.clear();
			memberExpected.put(USER_B, getGridMember(USER_B));
			verifyMembers(grp, MemberFilter.ALL, memberExpected);

			grouper.grantGroupPrivilege(SUPER_USER, gid, USER_C, GroupPrivilegeType.UPDATE);

			userExpected.clear();
			userExpected.add(GroupPrivilegeType.UPDATE);
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			verifyUserPrivileges(grp, USER_C, userExpected);

			privsExpected.clear();
			privsExpected.add(USER_C);
			verifyPrivileges(grp, GroupPrivilegeType.UPDATE, privsExpected);

			// Reading Description

			GroupDescriptor g = grouper.getGroup(USER_A, gid);
			assertEquals(grp.getName(), g.getName());
			assertEquals(description, g.getDescription());

			// Reading Members
			memberExpected.clear();
			memberExpected.put(USER_B, getGridMember(USER_B));
			verifyMembers(USER_A, grp, MemberFilter.ALL, memberExpected);

			// Reading Privileges

			userExpected.clear();
			userExpected.add(GroupPrivilegeType.UPDATE);
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			verifyUserPrivileges(USER_A, grp, USER_C, userExpected);

			// TODO: READ/VIEW users should be able to do this
			// privsExpected.clear();
			// privsExpected.add(USER_C);
			// verifyPrivileges(USER_A,grp, GroupPrivilegeType.UPDATE,
			// privsExpected);

			// Adding members
			try {
				grouper.addMember(USER_A, gid, USER_D);
				fail("Should not be able to add member!!!");
			} catch (InsufficientPrivilegeException f) {

			}

			// Updating
			try {
				GroupUpdate u = new GroupUpdate();
				u.setDescription("New Description");
				grouper.updateGroup(USER_A, gid, u);
				fail("Should not be able to update!!!");
			} catch (InsufficientPrivilegeException f) {

			}

			// Adding privileges
			try {
				grouper.grantGroupPrivilege(USER_A, gid, USER_D, GroupPrivilegeType.ADMIN);
				fail("Should not be able to add privilege!!!");
			} catch (InsufficientPrivilegeException f) {

			}

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

    @Test
	public void testUpdatePrivilege() {
		try {
			Map memberExpected = new HashMap();
			HashSet userExpected = new HashSet();
			HashSet privsExpected = new HashSet();
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.CREATE));

			StemDescriptor root = grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());
			assertNotNull(root);
			assertEquals(root.getName(), Utils.getRootStemIdentifier().getStemName());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);
			final String groupExtension = "mygroup";
			final String groupDisplayExtension = "My Group";

			GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);
			GroupIdentifier gid = Utils.getGroupIdentifier(grp);

			userExpected.clear();
			userExpected.add(GroupPrivilegeType.ADMIN);
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			verifyUserPrivileges(grp, SUPER_USER, userExpected);

			// Test Default Privileges
			userExpected.clear();
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			verifyUserPrivileges(grp, USER_A, userExpected);

			// Grant user update

			grouper.grantGroupPrivilege(SUPER_USER, gid, USER_A, GroupPrivilegeType.UPDATE);

			userExpected.clear();
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			userExpected.add(GroupPrivilegeType.UPDATE);
			verifyUserPrivileges(grp, USER_A, userExpected);

			privsExpected.clear();
			privsExpected.add(USER_A);
			verifyPrivileges(SUPER_USER, grp, GroupPrivilegeType.UPDATE, privsExpected);

			// We want to test doing everything
			String description = "This is a test group";
			GroupUpdate update = new GroupUpdate();
			update.setDescription(description);
			grouper.updateGroup(SUPER_USER, gid, update);
			grouper.addMember(SUPER_USER, gid, USER_B);
			memberExpected.clear();
			memberExpected.put(USER_B, getGridMember(USER_B));
			verifyMembers(grp, MemberFilter.ALL, memberExpected);

			// Reading Description

			GroupDescriptor g = grouper.getGroup(USER_A, gid);
			assertEquals(grp.getName(), g.getName());
			assertEquals(description, g.getDescription());

			// Reading Members
			memberExpected.clear();
			memberExpected.put(USER_B, getGridMember(USER_B));
			verifyMembers(USER_A, grp, MemberFilter.ALL, memberExpected);

			// Reading Privileges
			grouper.grantGroupPrivilege(SUPER_USER, gid, USER_C, GroupPrivilegeType.UPDATE);
			userExpected.clear();
			userExpected.add(GroupPrivilegeType.UPDATE);
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			verifyUserPrivileges(USER_A, grp, USER_C, userExpected);

			// Adding members

			grouper.addMember(USER_A, gid, USER_D);
			memberExpected.clear();
			memberExpected.put(USER_B, getGridMember(USER_B));
			memberExpected.put(USER_D, getGridMember(USER_D));
			verifyMembers(USER_A, grp, MemberFilter.ALL, memberExpected);

			// Updating

			try {
				String des = "New Description";
				GroupUpdate u = new GroupUpdate();
				u.setDescription(des);
				grouper.updateGroup(USER_A, gid, u);
				assertEquals(des, grouper.getGroup(SUPER_USER, gid).getDescription());
				fail("Should not be able to update the group!!!");
			} catch (InsufficientPrivilegeException e) {

			}

			// Adding privileges
			try {
				grouper.grantGroupPrivilege(USER_A, gid, USER_D, GroupPrivilegeType.ADMIN);
				fail("Should not be able to add privilege!!!");
			} catch (InsufficientPrivilegeException f) {

			}

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

    @Test
	public void testOptinOptoutPrivilege() {
		try {
			Map memberExpected = new HashMap();
			HashSet userExpected = new HashSet();
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.CREATE));

			StemDescriptor root = grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());
			assertNotNull(root);
			assertEquals(root.getName(), Utils.getRootStemIdentifier().getStemName());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);
			final String groupExtension = "mygroup";
			final String groupDisplayExtension = "My Group";

			GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);
			GroupIdentifier gid = Utils.getGroupIdentifier(grp);

			userExpected.clear();
			userExpected.add(GroupPrivilegeType.ADMIN);
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			verifyUserPrivileges(grp, SUPER_USER, userExpected);

			// Test Default Privileges
			userExpected.clear();
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			verifyUserPrivileges(grp, USER_A, userExpected);

			// Test to make sure the user can not opt into a group
			try {
				grouper.addMember(USER_A, gid, USER_A);
				fail("User should not be able to OPTIN into group");
			} catch (InsufficientPrivilegeException f) {

			}
			grouper.grantGroupPrivilege(SUPER_USER, gid, USER_A, GroupPrivilegeType.OPTIN);
			userExpected.clear();
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			userExpected.add(GroupPrivilegeType.OPTIN);
			verifyUserPrivileges(grp, USER_A, userExpected);

			grouper.addMember(USER_A, gid, USER_A);

			memberExpected.clear();
			memberExpected.put(USER_A, getGridMember(USER_A));
			verifyMembers(SUPER_USER, grp, MemberFilter.ALL, memberExpected);

			try {
				grouper.deleteMember(USER_A, gid, USER_A);
				fail("User should not be able to OPTOUT into group");
			} catch (InsufficientPrivilegeException f) {

			}

			memberExpected.clear();
			memberExpected.put(USER_A, getGridMember(USER_A));
			verifyMembers(SUPER_USER, grp, MemberFilter.ALL, memberExpected);

			grouper.grantGroupPrivilege(SUPER_USER, gid, USER_A, GroupPrivilegeType.OPTOUT);
			userExpected.clear();
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			userExpected.add(GroupPrivilegeType.OPTIN);
			userExpected.add(GroupPrivilegeType.OPTOUT);
			verifyUserPrivileges(grp, USER_A, userExpected);

			grouper.deleteMember(USER_A, gid, USER_A);

			memberExpected.clear();
			verifyMembers(SUPER_USER, grp, MemberFilter.ALL, memberExpected);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

    @Test
	public void testAdminPrivilege() {
		try {
			Map memberExpected = new HashMap();
			HashSet userExpected = new HashSet();
			HashSet privsExpected = new HashSet();
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.CREATE));

			StemDescriptor root = grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());
			assertNotNull(root);
			assertEquals(root.getName(), Utils.getRootStemIdentifier().getStemName());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);
			final String groupExtension = "mygroup";
			final String groupDisplayExtension = "My Group";

			GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);
			GroupIdentifier gid = Utils.getGroupIdentifier(grp);

			userExpected.clear();
			userExpected.add(GroupPrivilegeType.ADMIN);
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			verifyUserPrivileges(grp, SUPER_USER, userExpected);

			// Test Default Privileges
			userExpected.clear();
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			verifyUserPrivileges(grp, USER_A, userExpected);

			// Grant user update

			grouper.grantGroupPrivilege(SUPER_USER, gid, USER_A, GroupPrivilegeType.ADMIN);

			userExpected.clear();
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			userExpected.add(GroupPrivilegeType.ADMIN);
			verifyUserPrivileges(grp, USER_A, userExpected);

			privsExpected.clear();
			privsExpected.add(USER_A);
			privsExpected.add(SUPER_USER);
			verifyPrivileges(SUPER_USER, grp, GroupPrivilegeType.ADMIN, privsExpected);

			// We want to test doing everything
			String description = "This is a test group";
			GroupUpdate update = new GroupUpdate();
			update.setDescription(description);
			grouper.updateGroup(SUPER_USER, gid, update);
			grouper.addMember(SUPER_USER, gid, USER_B);
			memberExpected.clear();
			memberExpected.put(USER_B, getGridMember(USER_B));
			verifyMembers(grp, MemberFilter.ALL, memberExpected);

			// Reading Description
			GroupDescriptor g = grouper.getGroup(USER_A, gid);
			assertEquals(grp.getName(), g.getName());
			assertEquals(description, g.getDescription());

			// Reading Members
			memberExpected.clear();
			memberExpected.put(USER_B, getGridMember(USER_B));
			verifyMembers(USER_A, grp, MemberFilter.ALL, memberExpected);

			// Reading Privileges
			grouper.grantGroupPrivilege(SUPER_USER, gid, USER_C, GroupPrivilegeType.UPDATE);
			userExpected.clear();
			userExpected.add(GroupPrivilegeType.UPDATE);
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			verifyUserPrivileges(USER_A, grp, USER_C, userExpected);

			// Adding members

			grouper.addMember(USER_A, gid, USER_D);
			memberExpected.clear();
			memberExpected.put(USER_B, getGridMember(USER_B));
			memberExpected.put(USER_D, getGridMember(USER_D));
			verifyMembers(USER_A, grp, MemberFilter.ALL, memberExpected);

			// Updating

			String des = "New Description";
			GroupUpdate u = new GroupUpdate();
			u.setDescription(des);
			grouper.updateGroup(USER_A, gid, u);
			assertEquals(des, grouper.getGroup(SUPER_USER, gid).getDescription());

			// Adding privileges

			grouper.grantGroupPrivilege(USER_A, gid, USER_D, GroupPrivilegeType.ADMIN);

			userExpected.clear();
			userExpected.add(GroupPrivilegeType.VIEW);
			userExpected.add(GroupPrivilegeType.READ);
			userExpected.add(GroupPrivilegeType.ADMIN);
			verifyUserPrivileges(grp, USER_D, userExpected);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

    @Test
	public void testIsMember() {
		try {
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.CREATE));

			StemDescriptor root = grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());
			assertNotNull(root);
			assertEquals(root.getName(), Utils.getRootStemIdentifier().getStemName());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);

			final String grpExtension1 = "mygroup1";
			final String grpDisplayExtension1 = "My Group 1";
			GroupDescriptor grp1 = createAndCheckGroup(test, grpExtension1, grpDisplayExtension1, 1);
			GroupIdentifier group1 = Utils.getGroupIdentifier(grp1);
			grouper.addMember(SUPER_USER, group1, USER_A);

			assertTrue(grouper.isMemberOf(SUPER_USER, group1, USER_A, MemberFilter.ALL));
			assertTrue(grouper.isMember(SUPER_USER, USER_A, getSimpleInExpression(group1)));
			assertFalse(grouper.isMember(SUPER_USER, USER_A, getSimpleOutExpression(group1)));

			grouper.addMember(SUPER_USER, group1, USER_B);
			assertTrue(grouper.isMemberOf(SUPER_USER, group1, USER_B, MemberFilter.ALL));
			assertTrue(grouper.isMember(SUPER_USER, USER_B, getSimpleInExpression(group1)));
			assertFalse(grouper.isMember(SUPER_USER, USER_B, getSimpleOutExpression(group1)));

			grouper.addMember(SUPER_USER, group1, USER_C);
			assertTrue(grouper.isMemberOf(SUPER_USER, group1, USER_C, MemberFilter.ALL));
			assertTrue(grouper.isMember(SUPER_USER, USER_C, getSimpleInExpression(group1)));
			assertFalse(grouper.isMember(SUPER_USER, USER_C, getSimpleOutExpression(group1)));

			assertFalse(grouper.isMember(SUPER_USER, USER_D, getSimpleInExpression(group1)));
			assertTrue(grouper.isMember(SUPER_USER, USER_D, getSimpleOutExpression(group1)));

			final String grpExtension2 = "mygroup2";
			final String grpDisplayExtension2 = "My Group 2";
			GroupDescriptor grp2 = createAndCheckGroup(test, grpExtension2, grpDisplayExtension2, 2);
			GroupIdentifier group2 = Utils.getGroupIdentifier(grp2);

			assertFalse(grouper.isMember(SUPER_USER, USER_A, getSimpleInExpression(group2)));
			assertTrue(grouper.isMember(SUPER_USER, USER_A, getSimpleOutExpression(group2)));

			grouper.addMember(SUPER_USER, group2, USER_B);
			assertTrue(grouper.isMemberOf(SUPER_USER, group2, USER_B, MemberFilter.ALL));
			assertTrue(grouper.isMember(SUPER_USER, USER_B, getSimpleInExpression(group2)));
			assertFalse(grouper.isMember(SUPER_USER, USER_B, getSimpleOutExpression(group2)));

			grouper.addMember(SUPER_USER, group2, USER_C);
			assertTrue(grouper.isMemberOf(SUPER_USER, group2, USER_C, MemberFilter.ALL));
			assertTrue(grouper.isMember(SUPER_USER, USER_C, getSimpleInExpression(group2)));
			assertFalse(grouper.isMember(SUPER_USER, USER_C, getSimpleOutExpression(group2)));

			grouper.addMember(SUPER_USER, group2, USER_D);
			assertTrue(grouper.isMemberOf(SUPER_USER, group2, USER_D, MemberFilter.ALL));
			assertTrue(grouper.isMember(SUPER_USER, USER_D, getSimpleInExpression(group2)));
			assertFalse(grouper.isMember(SUPER_USER, USER_D, getSimpleOutExpression(group2)));

			// Test that a user is a member of both groups
			MembershipExpression both = getExpression(true, group1, true, group2, true);
			assertFalse(grouper.isMember(SUPER_USER, USER_A, both));
			assertTrue(grouper.isMember(SUPER_USER, USER_B, both));
			assertTrue(grouper.isMember(SUPER_USER, USER_C, both));
			assertFalse(grouper.isMember(SUPER_USER, USER_D, both));

			// Test that a user is a member of either groups
			MembershipExpression either = getExpression(false, group1, true, group2, true);

			assertTrue(grouper.isMember(SUPER_USER, USER_A, either));
			assertTrue(grouper.isMember(SUPER_USER, USER_B, either));
			assertTrue(grouper.isMember(SUPER_USER, USER_C, either));
			assertTrue(grouper.isMember(SUPER_USER, USER_D, either));

			// Test that a user is a member of 1 but not 2
			MembershipExpression in1Not2 = getExpression(true, group1, true, group2, false);

			assertTrue(grouper.isMember(SUPER_USER, USER_A, in1Not2));
			assertFalse(grouper.isMember(SUPER_USER, USER_B, in1Not2));
			assertFalse(grouper.isMember(SUPER_USER, USER_C, in1Not2));
			assertFalse(grouper.isMember(SUPER_USER, USER_D, in1Not2));

			// Test that a user is a member of 2 but not 1
			MembershipExpression in2Not1 = getExpression(true, group1, false, group2, true);

			assertFalse(grouper.isMember(SUPER_USER, USER_A, in2Not1));
			assertFalse(grouper.isMember(SUPER_USER, USER_B, in2Not1));
			assertFalse(grouper.isMember(SUPER_USER, USER_C, in2Not1));
			assertTrue(grouper.isMember(SUPER_USER, USER_D, in2Not1));

			// Test that a user is a member or 1 but not 2 OR in 2 but not 1
			MembershipExpression complex = getExpression(false, in1Not2, in2Not1);
			assertTrue(grouper.isMember(SUPER_USER, USER_A, complex));
			assertFalse(grouper.isMember(SUPER_USER, USER_B, complex));
			assertFalse(grouper.isMember(SUPER_USER, USER_C, complex));
			assertTrue(grouper.isMember(SUPER_USER, USER_D, complex));
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}


	private MembershipExpression getExpression(boolean and, MembershipExpression exp1, MembershipExpression exp2) {
		MembershipExpression[] expression = new MembershipExpression[2];
		expression[0] = exp1;
		expression[1] = exp2;
		MembershipExpression exp = new MembershipExpression();
		exp.getMembershipQueryOrMembershipExpression().add(expression[0]);
        exp.getMembershipQueryOrMembershipExpression().add(expression[1]);
		if (and) {
			exp.setLogicRelation(LogicalOperator.AND);
		} else {
			exp.setLogicRelation(LogicalOperator.OR);
		}
		return exp;
	}


	private MembershipExpression getExpression(boolean and, GroupIdentifier grp1, boolean in1, GroupIdentifier grp2,
		boolean in2) {
		MembershipQuery[] query = new MembershipQuery[2];
		query[0] = new MembershipQuery();
		query[0].setGroupIdentifier(grp1);
		if (in1) {
			query[0].setMembershipStatus(MembershipStatus.MEMBER_OF);
		} else {
			query[0].setMembershipStatus(MembershipStatus.NOT_MEMBER_OF);
		}

		query[1] = new MembershipQuery();
		query[1].setGroupIdentifier(grp2);
		if (in2) {
			query[1].setMembershipStatus(MembershipStatus.MEMBER_OF);
		} else {
			query[1].setMembershipStatus(MembershipStatus.NOT_MEMBER_OF);
		}

		MembershipExpression exp = new MembershipExpression();
		exp.getMembershipQueryOrMembershipExpression().add(query[0]);
        exp.getMembershipQueryOrMembershipExpression().add(query[1]);
		if (and) {
			exp.setLogicRelation(LogicalOperator.AND);
		} else {
			exp.setLogicRelation(LogicalOperator.OR);
		}
		return exp;
	}


	private MembershipExpression getSimpleInExpression(GroupIdentifier grp) {
		MembershipQuery[] query = new MembershipQuery[1];
		query[0] = new MembershipQuery();
		query[0].setGroupIdentifier(grp);
		query[0].setMembershipStatus(MembershipStatus.MEMBER_OF);
		MembershipExpression exp = new MembershipExpression();
		exp.getMembershipQueryOrMembershipExpression().add(query[0]);
		exp.setLogicRelation(LogicalOperator.AND);
		return exp;
	}


	private MembershipExpression getSimpleOutExpression(GroupIdentifier grp) {
		MembershipQuery[] query = new MembershipQuery[1];
		query[0] = new MembershipQuery();
		query[0].setGroupIdentifier(grp);
		query[0].setMembershipStatus(MembershipStatus.NOT_MEMBER_OF);
		MembershipExpression exp = new MembershipExpression();
		exp.getMembershipQueryOrMembershipExpression().add(query[0]);
		exp.setLogicRelation(LogicalOperator.OR);
		return exp;
	}

    @Test
	public void testMembers() {
		try {
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.CREATE));

			StemDescriptor root = grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());
			assertNotNull(root);
			assertEquals(root.getName(), Utils.getRootStemIdentifier().getStemName());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);
			Map expected = new HashMap();
			final String groupExtension = "mygroup";
			final String groupDisplayExtension = "My Group";

			GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);

			final String subGroupExtension = "mysubgroup";
			final String subGroupDisplayExtension = "My Sub Group";

			GroupDescriptor subgrp = createAndCheckGroup(test, subGroupExtension, subGroupDisplayExtension, 2);

			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A);

			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			verifyMembers(grp, MemberFilter.ALL, expected);

			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			verifyMembers(grp, MemberFilter.IMMEDIATE_MEMBERS, expected);

			expected.clear();
			verifyMembers(grp, MemberFilter.EFFECTIVE_MEMBERS, expected);

			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(subgrp), USER_B);

			expected.clear();
			expected.put(USER_B, getGridMember(USER_B));
			verifyMembers(subgrp, MemberFilter.ALL, expected);

			expected.clear();
			expected.put(USER_B, getGridMember(USER_B));
			verifyMembers(subgrp, MemberFilter.IMMEDIATE_MEMBERS, expected);

			expected.clear();
			verifyMembers(subgrp, MemberFilter.EFFECTIVE_MEMBERS, expected);

			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), subgrp.getUUID());

			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			expected.put(USER_B, getGridMember(USER_B));
			expected.put(subgrp.getUUID(), getGroupMember(subgrp.getUUID()));
			verifyMembers(grp, MemberFilter.ALL, expected);

			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			expected.put(subgrp.getUUID(), getGroupMember(subgrp.getUUID()));
			verifyMembers(grp, MemberFilter.IMMEDIATE_MEMBERS, expected);

			expected.clear();
			expected.put(USER_B, getGridMember(USER_B));
			verifyMembers(grp, MemberFilter.EFFECTIVE_MEMBERS, expected);

			grouper.deleteMember(SUPER_USER, Utils.getGroupIdentifier(subgrp), USER_B);

			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			expected.put(subgrp.getUUID(), getGroupMember(subgrp.getUUID()));
			verifyMembers(grp, MemberFilter.ALL, expected);

			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			expected.put(subgrp.getUUID(), getGroupMember(subgrp.getUUID()));
			verifyMembers(grp, MemberFilter.IMMEDIATE_MEMBERS, expected);

			expected.clear();
			verifyMembers(grp, MemberFilter.EFFECTIVE_MEMBERS, expected);

			expected.clear();
			verifyMembers(subgrp, MemberFilter.ALL, expected);
			expected.clear();
			verifyMembers(subgrp, MemberFilter.EFFECTIVE_MEMBERS, expected);
			expected.clear();
			verifyMembers(subgrp, MemberFilter.IMMEDIATE_MEMBERS, expected);

			grouper.deleteMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A);

			expected.clear();
			expected.put(subgrp.getUUID(), getGroupMember(subgrp.getUUID()));
			verifyMembers(grp, MemberFilter.ALL, expected);

			expected.clear();
			expected.put(subgrp.getUUID(), getGroupMember(subgrp.getUUID()));
			verifyMembers(grp, MemberFilter.IMMEDIATE_MEMBERS, expected);

			expected.clear();
			verifyMembers(grp, MemberFilter.EFFECTIVE_MEMBERS, expected);

			grouper.deleteMember(SUPER_USER, Utils.getGroupIdentifier(grp), subgrp.getUUID());

			expected.clear();
			verifyMembers(grp, MemberFilter.ALL, expected);

			expected.clear();
			verifyMembers(grp, MemberFilter.IMMEDIATE_MEMBERS, expected);

			expected.clear();
			verifyMembers(grp, MemberFilter.EFFECTIVE_MEMBERS, expected);

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testUnionComposite() {
		try {
			Map expected = new HashMap();
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.CREATE));

			StemDescriptor root = grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());
			assertNotNull(root);
			assertEquals(root.getName(), Utils.getRootStemIdentifier().getStemName());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);

			final String groupExtensionX = "mygroupx";
			final String groupDisplayExtensionX = "My Group X";

			GroupDescriptor grpx = createAndCheckGroup(test, groupExtensionX, groupDisplayExtensionX, 1);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grpx), USER_A);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grpx), USER_B);

			final String groupExtensionY = "mygroupy";
			final String groupDisplayExtensionY = "My Group Y";

			GroupDescriptor grpy = createAndCheckGroup(test, groupExtensionY, groupDisplayExtensionY, 2);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grpy), USER_B);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grpy), USER_C);

			final String compositeGroupExtension = "compositegroup";
			final String compositeGroupDisplayExtension = "Composite Group";

			// Create Composite Union Group
			GroupDescriptor composite = createAndCheckGroup(test, compositeGroupExtension,
				compositeGroupDisplayExtension, 3);
			assertFalse(composite.isHasComposite());
			composite = grouper.addCompositeMember(SUPER_USER, GroupCompositeType.UNION, Utils
				.getGroupIdentifier(composite), Utils.getGroupIdentifier(grpx), Utils.getGroupIdentifier(grpy));
			assertTrue(composite.isHasComposite());
			assertFalse(grpx.isIsComposite());
			assertFalse(grpy.isIsComposite());
			grpx = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpx));
			grpy = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpy));
			assertTrue(grpx.isIsComposite());
			assertTrue(grpy.isIsComposite());

			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			expected.put(USER_B, getGridMember(USER_B));
			expected.put(USER_C, getGridMember(USER_C));
			verifyMembers(composite, MemberFilter.ALL, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.EFFECTIVE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.IMMEDIATE_MEMBERS, expected);
			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			expected.put(USER_B, getGridMember(USER_B));
			expected.put(USER_C, getGridMember(USER_C));
			verifyMembers(composite, MemberFilter.COMPOSITE_MEMBERS, expected);

			// TODO: Possible Grouper BUG: Make sure that the Membership is
			// working as intended.
			expected.clear();
			expected.put(USER_A, getGridMembership(USER_A, composite.getName(), null, 0));
			expected.put(USER_B, getGridMembership(USER_B, composite.getName(), null, 0));
			expected.put(USER_C, getGridMembership(USER_C, composite.getName(), null, 0));
			verifyMemberships(composite, MemberFilter.ALL, 3, expected);
			expected.clear();
			verifyMemberships(composite, MemberFilter.EFFECTIVE_MEMBERS, 0, expected);
			expected.clear();
			verifyMemberships(composite, MemberFilter.IMMEDIATE_MEMBERS, 0, expected);
			expected.clear();
			expected.put(USER_A, getGridMembership(USER_A, composite.getName(), null, 0));
			expected.put(USER_B, getGridMembership(USER_B, composite.getName(), null, 0));
			expected.put(USER_C, getGridMembership(USER_C, composite.getName(), null, 0));
			verifyMemberships(composite, MemberFilter.COMPOSITE_MEMBERS, 3, expected);

			// Test Remove the shared user
			grouper.deleteMember(SUPER_USER, Utils.getGroupIdentifier(grpx), USER_B);
			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			expected.put(USER_B, getGridMember(USER_B));
			expected.put(USER_C, getGridMember(USER_C));
			verifyMembers(composite, MemberFilter.ALL, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.EFFECTIVE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.IMMEDIATE_MEMBERS, expected);
			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			expected.put(USER_B, getGridMember(USER_B));
			expected.put(USER_C, getGridMember(USER_C));
			verifyMembers(composite, MemberFilter.COMPOSITE_MEMBERS, expected);

			grouper.deleteMember(SUPER_USER, Utils.getGroupIdentifier(grpy), USER_B);

			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			expected.put(USER_C, getGridMember(USER_C));
			verifyMembers(composite, MemberFilter.ALL, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.EFFECTIVE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.IMMEDIATE_MEMBERS, expected);
			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			expected.put(USER_C, getGridMember(USER_C));
			verifyMembers(composite, MemberFilter.COMPOSITE_MEMBERS, expected);

			grouper.deleteCompositeMember(SUPER_USER, Utils.getGroupIdentifier(composite));

			expected.clear();
			verifyMembers(composite, MemberFilter.ALL, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.EFFECTIVE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.IMMEDIATE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.COMPOSITE_MEMBERS, expected);

			grpx = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpx));
			grpy = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpy));
			assertFalse(grpx.isIsComposite());
			assertFalse(grpy.isIsComposite());

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testIntersectionComposite() {
		try {
			Map expected = new HashMap();
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.CREATE));

			StemDescriptor root = grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());
			assertNotNull(root);
			assertEquals(root.getName(), Utils.getRootStemIdentifier().getStemName());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);

			final String groupExtensionX = "mygroupx";
			final String groupDisplayExtensionX = "My Group X";

			GroupDescriptor grpx = createAndCheckGroup(test, groupExtensionX, groupDisplayExtensionX, 1);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grpx), USER_A);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grpx), USER_B);

			final String groupExtensionY = "mygroupy";
			final String groupDisplayExtensionY = "My Group Y";

			GroupDescriptor grpy = createAndCheckGroup(test, groupExtensionY, groupDisplayExtensionY, 2);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grpy), USER_B);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grpy), USER_C);

			final String compositeGroupExtension = "compositegroup";
			final String compositeGroupDisplayExtension = "Composite Group";

			GroupDescriptor composite = createAndCheckGroup(test, compositeGroupExtension,
				compositeGroupDisplayExtension, 3);
			assertFalse(composite.isHasComposite());
			composite = grouper.addCompositeMember(SUPER_USER, GroupCompositeType.INTERSECTION, Utils
				.getGroupIdentifier(composite), Utils.getGroupIdentifier(grpx), Utils.getGroupIdentifier(grpy));
			assertTrue(composite.isHasComposite());
			assertFalse(grpx.isIsComposite());
			assertFalse(grpy.isIsComposite());
			grpx = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpx));
			grpy = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpy));
			assertTrue(grpx.isIsComposite());
			assertTrue(grpy.isIsComposite());

			expected.clear();
			expected.put(USER_B, getGridMember(USER_B));
			verifyMembers(composite, MemberFilter.ALL, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.EFFECTIVE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.IMMEDIATE_MEMBERS, expected);
			expected.clear();
			expected.put(USER_B, getGridMember(USER_B));
			verifyMembers(composite, MemberFilter.COMPOSITE_MEMBERS, expected);

			// TODO: Possible Grouper BUG: Make sure that the Membership is
			// working as intended.
			expected.clear();
			expected.put(USER_B, getGridMembership(USER_B, composite.getName(), null, 0));
			verifyMemberships(composite, MemberFilter.ALL, 1, expected);
			expected.clear();
			verifyMemberships(composite, MemberFilter.EFFECTIVE_MEMBERS, 0, expected);
			expected.clear();
			verifyMemberships(composite, MemberFilter.IMMEDIATE_MEMBERS, 0, expected);
			expected.clear();
			expected.put(USER_B, getGridMembership(USER_B, composite.getName(), null, 0));
			verifyMemberships(composite, MemberFilter.COMPOSITE_MEMBERS, 1, expected);

			// Test Remove the shared user
			grouper.deleteMember(SUPER_USER, Utils.getGroupIdentifier(grpx), USER_B);
			expected.clear();
			verifyMembers(composite, MemberFilter.ALL, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.EFFECTIVE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.IMMEDIATE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.COMPOSITE_MEMBERS, expected);

			grouper.deleteCompositeMember(SUPER_USER, Utils.getGroupIdentifier(composite));

			expected.clear();
			verifyMembers(composite, MemberFilter.ALL, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.EFFECTIVE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.IMMEDIATE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.COMPOSITE_MEMBERS, expected);

			grpx = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpx));
			grpy = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpy));
			assertFalse(grpx.isIsComposite());
			assertFalse(grpy.isIsComposite());

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testComplementComposite() {
		try {
			Map expected = new HashMap();
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.CREATE));

			StemDescriptor root = grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());
			assertNotNull(root);
			assertEquals(root.getName(), Utils.getRootStemIdentifier().getStemName());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);

			final String groupExtensionX = "mygroupx";
			final String groupDisplayExtensionX = "My Group X";

			GroupDescriptor grpx = createAndCheckGroup(test, groupExtensionX, groupDisplayExtensionX, 1);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grpx), USER_A);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grpx), USER_B);

			final String groupExtensionY = "mygroupy";
			final String groupDisplayExtensionY = "My Group Y";

			GroupDescriptor grpy = createAndCheckGroup(test, groupExtensionY, groupDisplayExtensionY, 2);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grpy), USER_B);
			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grpy), USER_C);

			final String compositeGroupExtension = "compositegroup";
			final String compositeGroupDisplayExtension = "Composite Group";

			GroupDescriptor composite = createAndCheckGroup(test, compositeGroupExtension,
				compositeGroupDisplayExtension, 3);
			assertFalse(composite.isHasComposite());
			composite = grouper.addCompositeMember(SUPER_USER, GroupCompositeType.COMPLEMENT, Utils
				.getGroupIdentifier(composite), Utils.getGroupIdentifier(grpx), Utils.getGroupIdentifier(grpy));
			assertTrue(composite.isHasComposite());
			assertFalse(grpx.isIsComposite());
			assertFalse(grpy.isIsComposite());
			grpx = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpx));
			grpy = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpy));
			assertTrue(grpx.isIsComposite());
			assertTrue(grpy.isIsComposite());
			// Utils.printMemberships(composite);

			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			verifyMembers(composite, MemberFilter.ALL, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.EFFECTIVE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.IMMEDIATE_MEMBERS, expected);
			expected.clear();
			expected.put(USER_A, getGridMember(USER_A));
			verifyMembers(composite, MemberFilter.COMPOSITE_MEMBERS, expected);

			// TODO: Possible Grouper BUG: Make sure that the Membership is
			// working as intended.
			expected.clear();
			expected.put(USER_A, getGridMembership(USER_A, composite.getName(), null, 0));
			verifyMemberships(composite, MemberFilter.ALL, 1, expected);
			expected.clear();
			verifyMemberships(composite, MemberFilter.EFFECTIVE_MEMBERS, 0, expected);
			expected.clear();
			verifyMemberships(composite, MemberFilter.IMMEDIATE_MEMBERS, 0, expected);
			expected.clear();
			expected.put(USER_A, getGridMembership(USER_A, composite.getName(), null, 0));
			verifyMemberships(composite, MemberFilter.COMPOSITE_MEMBERS, 1, expected);

			// Test Remove the shared user
			grouper.deleteMember(SUPER_USER, Utils.getGroupIdentifier(grpx), USER_A);
			expected.clear();
			verifyMembers(composite, MemberFilter.ALL, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.EFFECTIVE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.IMMEDIATE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.COMPOSITE_MEMBERS, expected);

			grouper.deleteCompositeMember(SUPER_USER, Utils.getGroupIdentifier(composite));

			expected.clear();
			verifyMembers(composite, MemberFilter.ALL, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.EFFECTIVE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.IMMEDIATE_MEMBERS, expected);
			expected.clear();
			verifyMembers(composite, MemberFilter.COMPOSITE_MEMBERS, expected);

			grpx = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpx));
			grpy = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpy));
			assertFalse(grpx.isIsComposite());
			assertFalse(grpy.isIsComposite());

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testNegativeComposites() {
		try {
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.CREATE));

			StemDescriptor root = grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());
			assertNotNull(root);
			assertEquals(root.getName(), Utils.getRootStemIdentifier().getStemName());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);

			final String groupExtensionX = "mygroupx";
			final String groupDisplayExtensionX = "My Group X";

			GroupDescriptor grpx = createAndCheckGroup(test, groupExtensionX, groupDisplayExtensionX, 1);

			final String groupExtensionY = "mygroupy";
			final String groupDisplayExtensionY = "My Group Y";

			GroupDescriptor grpy = createAndCheckGroup(test, groupExtensionY, groupDisplayExtensionY, 2);

			final String compositeGroupExtension = "compositegroup";
			final String compositeGroupDisplayExtension = "Composite Group";

			// Create Composite Union Group
			GroupDescriptor composite = createAndCheckGroup(test, compositeGroupExtension,
				compositeGroupDisplayExtension, 3);
			assertFalse(composite.isHasComposite());
			composite = grouper.addCompositeMember(SUPER_USER, GroupCompositeType.UNION, Utils
				.getGroupIdentifier(composite), Utils.getGroupIdentifier(grpx), Utils.getGroupIdentifier(grpy));
			assertTrue(composite.isHasComposite());
			assertFalse(grpx.isIsComposite());
			assertFalse(grpy.isIsComposite());
			grpx = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpx));
			grpy = grouper.getGroup(SUPER_USER, Utils.getGroupIdentifier(grpy));
			assertTrue(grpx.isIsComposite());
			assertTrue(grpy.isIsComposite());

			// Negative Tests.
			try {
				composite = grouper.addCompositeMember(SUPER_USER, GroupCompositeType.INTERSECTION, Utils
					.getGroupIdentifier(composite), Utils.getGroupIdentifier(grpx), Utils.getGroupIdentifier(grpy));
				fail("Should not be able to add composite membership to group with composite membership.");
			} catch (MemberAddException e) {

			}

			try {
				grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(composite), USER_D);
				fail("Should not be able to add a member to group with composite membership.");
			} catch (MemberAddException e) {

			}

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

    @Test
	public void testMemberships() {
		try {
			GridGrouperBootstrapper.addAdminMember(SUPER_USER);

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier(), SUPER_USER, StemPrivilegeType.CREATE));

			StemDescriptor root = grouper.getStem(SUPER_USER, Utils.getRootStemIdentifier());
			assertNotNull(root);
			assertEquals(root.getName(), Utils.getRootStemIdentifier().getStemName());

			String testStem = "TestStem";
			StemDescriptor test = grouper.addChildStem(SUPER_USER, Utils.getRootStemIdentifier(), testStem, testStem);

			Map expected = new HashMap();
			final String groupExtension = "mygroup";
			final String groupDisplayExtension = "My Group";

			GroupDescriptor grp = createAndCheckGroup(test, groupExtension, groupDisplayExtension, 1);

			final String subGroupExtension = "mysubgroup";
			final String subGroupDisplayExtension = "My Sub Group";

			GroupDescriptor subgrp = createAndCheckGroup(test, subGroupExtension, subGroupDisplayExtension, 2);

			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A);

			expected.clear();
			expected.put(USER_A, getGridMembership(USER_A, grp.getName(), null, 0));
			verifyMemberships(grp, MemberFilter.ALL, 1, expected);

			expected.clear();
			expected.put(USER_A, getGridMembership(USER_A, grp.getName(), null, 0));
			verifyMemberships(grp, MemberFilter.IMMEDIATE_MEMBERS, 1, expected);

			expected.clear();
			verifyMemberships(grp, MemberFilter.EFFECTIVE_MEMBERS, 0, expected);

			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(subgrp), USER_B);

			expected.clear();
			expected.put(USER_B, getGridMembership(USER_B, subgrp.getName(), null, 0));
			verifyMemberships(subgrp, MemberFilter.ALL, 1, expected);

			expected.clear();
			expected.put(USER_B, getGridMembership(USER_B, subgrp.getName(), null, 0));
			verifyMemberships(subgrp, MemberFilter.IMMEDIATE_MEMBERS, 1, expected);

			expected.clear();
			verifyMemberships(subgrp, MemberFilter.EFFECTIVE_MEMBERS, 0, expected);

			grouper.addMember(SUPER_USER, Utils.getGroupIdentifier(grp), subgrp.getUUID());

			expected.clear();
			expected.put(USER_A, getGridMembership(USER_A, grp.getName(), null, 0));
			expected.put(USER_B, getGridMembership(USER_B, grp.getName(), subgrp.getName(), 1));
			expected.put(subgrp.getUUID(), getGroupMembership(subgrp.getUUID(), grp.getName(), null, 0));
			verifyMemberships(grp, MemberFilter.ALL, 3, expected);

			expected.clear();
			expected.put(USER_A, getGridMembership(USER_A, grp.getName(), null, 0));
			expected.put(subgrp.getUUID(), getGroupMembership(subgrp.getUUID(), grp.getName(), null, 0));
			verifyMemberships(grp, MemberFilter.IMMEDIATE_MEMBERS, 2, expected);

			expected.clear();
			expected.put(USER_B, getGridMembership(USER_B, grp.getName(), subgrp.getName(), 1));
			verifyMemberships(grp, MemberFilter.EFFECTIVE_MEMBERS, 1, expected);

			grouper.deleteMember(SUPER_USER, Utils.getGroupIdentifier(subgrp), USER_B);

			expected.clear();
			expected.put(USER_A, getGridMembership(USER_A, grp.getName(), null, 0));
			expected.put(subgrp.getUUID(), getGroupMembership(subgrp.getUUID(), grp.getName(), null, 0));
			verifyMemberships(grp, MemberFilter.ALL, 2, expected);

			expected.clear();
			expected.put(USER_A, getGridMembership(USER_A, grp.getName(), null, 0));
			expected.put(subgrp.getUUID(), getGroupMembership(subgrp.getUUID(), grp.getName(), null, 0));
			verifyMemberships(grp, MemberFilter.IMMEDIATE_MEMBERS, 2, expected);

			expected.clear();
			verifyMemberships(grp, MemberFilter.EFFECTIVE_MEMBERS, 0, expected);

			verifyMemberships(subgrp, MemberFilter.ALL, 0, expected);
			verifyMemberships(subgrp, MemberFilter.EFFECTIVE_MEMBERS, 0, expected);
			verifyMemberships(subgrp, MemberFilter.IMMEDIATE_MEMBERS, 0, expected);

			grouper.deleteMember(SUPER_USER, Utils.getGroupIdentifier(grp), USER_A);

			expected.clear();
			expected.put(subgrp.getUUID(), getGroupMembership(subgrp.getUUID(), grp.getName(), null, 0));
			verifyMemberships(grp, MemberFilter.ALL, 1, expected);

			expected.clear();
			expected.put(subgrp.getUUID(), getGroupMembership(subgrp.getUUID(), grp.getName(), null, 0));
			verifyMemberships(grp, MemberFilter.IMMEDIATE_MEMBERS, 1, expected);

			expected.clear();
			verifyMemberships(grp, MemberFilter.EFFECTIVE_MEMBERS, 0, expected);

			grouper.deleteMember(SUPER_USER, Utils.getGroupIdentifier(grp), subgrp.getUUID());

			verifyMemberships(grp, MemberFilter.ALL, 0, expected);
			verifyMemberships(grp, MemberFilter.EFFECTIVE_MEMBERS, 0, expected);
			verifyMemberships(grp, MemberFilter.IMMEDIATE_MEMBERS, 0, expected);

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}







	private MembershipCaddy getGridMembership(String name, String group, String viaGroup, int depth) {
		return new MembershipCaddy(name, group, viaGroup, depth, MemberType.GRID);
	}


	private MembershipCaddy getGroupMembership(String name, String group, String viaGroup, int depth) {
		return new MembershipCaddy(name, group, viaGroup, depth, MemberType.GROUPER_GROUP);
	}

}
