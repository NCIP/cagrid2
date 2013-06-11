package org.cagrid.gridgrouper.service.impl;

import edu.internet2.middleware.grouper.RegistryReset;
import edu.internet2.middleware.subject.AnonymousGridUserSubject;
import junit.framework.TestCase;
import net.sf.hibernate.exception.ExceptionUtils;
import org.cagrid.gridgrouper.model.GroupDescriptor;
import org.cagrid.gridgrouper.model.StemDescriptor;
import org.cagrid.gridgrouper.model.StemPrivilege;
import org.cagrid.gridgrouper.model.StemPrivilegeType;
import org.cagrid.gridgrouper.model.StemUpdate;
import org.cagrid.gridgrouper.service.exception.InsufficientPrivilegeException;
import org.cagrid.gridgrouper.service.exception.StemDeleteException;
import org.cagrid.gridgrouper.service.impl.testutils.Utils;
import org.cagrid.gridgrouper.service.impl.tools.GridGrouperBootstrapper;

import java.util.List;


/**
* @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
* @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
* @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
* @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
*          Exp $
*/
public class TestStems extends TestCase {

	private GridGrouper grouper = null;

	private String SUPER_USER = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=super admin";

	private String ADMIN_USER = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=admin";

	private String USER_A = "/O=OSU/OU=BMI/OU=caGrid/OU=Dorian/OU=cagrid05/OU=IdP [1]/CN=user a";


	public void testRootStem() {
		try {
			StemDescriptor root = grouper.getStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier());
			assertNotNull(root);
			assertEquals(root.getName(), Utils.getRootStemIdentifier().getStemName());
			String displayExtension = root.getDisplayExtension();
			String description = root.getDescription();
			assertNotNull(displayExtension);
			assertNotNull(description);
			String updatedDisplayExtension = displayExtension + " Update";
			String updatedDescription = displayExtension + " Description Update";

			try {
				StemUpdate update = new StemUpdate();
				update.setDisplayExtension(updatedDisplayExtension);
				grouper.updateStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils.getStemIdentifier(root),
					update);
				fail("Should have failed, insufficient privilege!!!");
			} catch (InsufficientPrivilegeException f) {

			}

			try {
				StemUpdate update = new StemUpdate();
				update.setDescription(updatedDescription);
				grouper.updateStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils.getStemIdentifier(root),
					update);
				fail("Should have failed, insufficient privilege!!!");
			} catch (InsufficientPrivilegeException f) {

			}

			checkStem(grouper.getStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils.getRootStemIdentifier()),
				displayExtension, description);
			// TODO: BUG IN GROUPER CACHING?
			// assertFalse(grouper
			// .hasStemPrivilege(
			// AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID,
			// getStemIdentifier(root), ADMIN_USER,
			// StemPrivilegeType.STEM));
			// assertFalse(grouper.hasStemPrivilege(
			// AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID,
			// getStemIdentifier(root), ADMIN_USER,
			// StemPrivilegeType.CREATE));

			// Now create an admin user and do the update
			GridGrouperBootstrapper.addAdminMember(ADMIN_USER);
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root), ADMIN_USER, StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root), ADMIN_USER, StemPrivilegeType.CREATE));

			StemUpdate update1 = new StemUpdate();
			update1.setDisplayExtension(updatedDisplayExtension);
			update1.setDescription(updatedDescription);
			grouper.updateStem(ADMIN_USER, Utils.getStemIdentifier(root), update1);

			checkStem(grouper.getStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils.getRootStemIdentifier()),
				updatedDisplayExtension, updatedDescription);

			StemUpdate update2 = new StemUpdate();
			update2.setDisplayExtension(displayExtension);
			update2.setDescription(description);
			grouper.updateStem(ADMIN_USER, Utils.getStemIdentifier(root), update2);

			checkStem(grouper.getStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils.getRootStemIdentifier()),
				displayExtension, description);

			// Now try with another user

			assertFalse(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root), USER_A, StemPrivilegeType.STEM));
			assertFalse(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root), USER_A, StemPrivilegeType.CREATE));

			try {
				grouper.grantStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
					.getStemIdentifier(root), USER_A, StemPrivilegeType.STEM);
				fail("Should have failed, insufficient privilege!!!");
			} catch (InsufficientPrivilegeException e) {

			}

			assertFalse(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root), USER_A, StemPrivilegeType.STEM));
			assertFalse(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root), USER_A, StemPrivilegeType.CREATE));

			grouper.grantStemPrivilege(ADMIN_USER, Utils.getStemIdentifier(root), USER_A, StemPrivilegeType.STEM);

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root), USER_A, StemPrivilegeType.STEM));
			assertFalse(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root), USER_A, StemPrivilegeType.CREATE));

			StemUpdate update3 = new StemUpdate();
			update3.setDisplayExtension(updatedDisplayExtension);
			update3.setDescription(updatedDescription);

			grouper.updateStem(USER_A, Utils.getStemIdentifier(root), update3);

			checkStem(grouper.getStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils.getRootStemIdentifier()),
				updatedDisplayExtension, updatedDescription);

			StemUpdate update4 = new StemUpdate();
			update4.setDisplayExtension(displayExtension);
			update4.setDescription(description);

			grouper.updateStem(USER_A, Utils.getStemIdentifier(root), update4);

			checkStem(grouper.getStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils.getRootStemIdentifier()),
				displayExtension, description);

			List<StemPrivilege> privs = grouper.getStemPrivileges(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root), USER_A);
			assertNotNull(privs);
			assertEquals(1, privs.size());
			assertEquals(USER_A, privs.get(0).getSubject());
			assertEquals(StemPrivilegeType.STEM, privs.get(0).getPrivilegeType());
			assertEquals(root.getName(), privs.get(0).getStemName());

			List<StemPrivilege> privs2 = grouper.getStemPrivileges(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root), ADMIN_USER);
			assertNotNull(privs2);
			assertEquals(0, privs2.size());

			// TODO: Should I be able to call with anon user?s
			List<String> subs1 = grouper.getSubjectsWithStemPrivilege(ADMIN_USER, Utils.getStemIdentifier(root),
				StemPrivilegeType.STEM);
			assertNotNull(subs1);
			assertEquals(1, subs1.size());
			assertEquals(USER_A, subs1.get(0));

			// TODO: Should I be able to call with anon user?
			List<String> subs2 = grouper.getSubjectsWithStemPrivilege(ADMIN_USER, Utils.getStemIdentifier(root),
				StemPrivilegeType.CREATE);
			assertNotNull(subs2);
			assertEquals(0, subs2.size());

			try {

				grouper.revokeStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
					.getStemIdentifier(root), USER_A, StemPrivilegeType.STEM);
				fail("Should have failed, insufficient privilege!!!");
			} catch (InsufficientPrivilegeException e) {

			}

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root), USER_A, StemPrivilegeType.STEM));

			grouper.revokeStemPrivilege(USER_A, Utils.getStemIdentifier(root), USER_A, StemPrivilegeType.STEM);

			assertFalse(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root), USER_A, StemPrivilegeType.STEM));

		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}

	}


	public void testAddingGroups() {
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

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(test), SUPER_USER, StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(test), SUPER_USER, StemPrivilegeType.CREATE));

			final String group1Extension = "group1";
			final String group1DisplayExtension = "group 1";
			final String group2Extension = "group2";
			final String group2DisplayExtension = "group 2";

			// TODO: BUG IN GROUPER CACHING?
			// assertFalse(grouper
			// .hasStemPrivilege(
			// SUPER_USER,
			// getStemIdentifier(test), ADMIN_USER,
			// StemPrivilegeType.STEM));
			// assertFalse(grouper.hasStemPrivilege(
			// SUPER_USER,
			// getStemIdentifier(test), ADMIN_USER,
			// StemPrivilegeType.CREATE));
			//
			// try {
			// grouper.addChildGroup(ADMIN_USER, getStemIdentifier(root),
			// group1Extension, group1DisplayExtension);
			// fail("Should have failed, insufficient privilege!!!");
			// } catch (InsufficientPrivilegeFault f) {
			//
			// }

			// Now create an admin user and do the update
			GridGrouperBootstrapper.addAdminMember(ADMIN_USER);

			assertTrue(grouper.hasStemPrivilege(SUPER_USER, Utils.getStemIdentifier(test), ADMIN_USER,
				StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(SUPER_USER, Utils.getStemIdentifier(test), ADMIN_USER,
				StemPrivilegeType.CREATE));

			assertEquals(0, grouper.getChildGroups(ADMIN_USER, Utils.getStemIdentifier(test)).size());
			grouper.addChildGroup(ADMIN_USER, Utils.getStemIdentifier(test), group1Extension, group1DisplayExtension);
			List<GroupDescriptor> grps = grouper.getChildGroups(ADMIN_USER, Utils.getStemIdentifier(test));
			assertEquals(1, grps.size());
			assertEquals(group1Extension, grps.get(0).getExtension());
			assertEquals(group1DisplayExtension, grps.get(0).getDisplayExtension());

			// Now try with another user

			assertFalse(grouper.hasStemPrivilege(SUPER_USER, Utils.getStemIdentifier(test), USER_A,
				StemPrivilegeType.STEM));
			assertFalse(grouper.hasStemPrivilege(SUPER_USER, Utils.getStemIdentifier(test), USER_A,
				StemPrivilegeType.CREATE));

			try {
				grouper.addChildGroup(USER_A, Utils.getStemIdentifier(test), group2Extension, group2DisplayExtension);
				fail("Should have failed, insufficient privilege!!!");
			} catch (InsufficientPrivilegeException e) {

			}

			grouper.grantStemPrivilege(SUPER_USER, Utils.getStemIdentifier(test), USER_A, StemPrivilegeType.CREATE);

			assertFalse(grouper.hasStemPrivilege(SUPER_USER, Utils.getStemIdentifier(test), USER_A,
				StemPrivilegeType.STEM));
			assertTrue(grouper.hasStemPrivilege(SUPER_USER, Utils.getStemIdentifier(test), USER_A,
				StemPrivilegeType.CREATE));
			grouper.addChildGroup(USER_A, Utils.getStemIdentifier(test), group2Extension, group2DisplayExtension);
			grps = grouper.getChildGroups(USER_A, Utils.getStemIdentifier(test));
			assertEquals(2, grps.size());

		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(ExceptionUtils.getFullStackTrace(e), false);
		}

	}


	public void testChildStems() {
		try {
			StemDescriptor root = grouper.getStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getRootStemIdentifier());
			assertNotNull(root);
			GridGrouperBootstrapper.addAdminMember(ADMIN_USER);
			String extensionChildX = "X";
			String extensionChildX1 = "X.1";
			try {
				grouper.addChildStem(USER_A, Utils.getStemIdentifier(root), extensionChildX, extensionChildX);
				fail("Should have failed, insufficient privilege!!!");
			} catch (InsufficientPrivilegeException e) {

			}

			grouper.grantStemPrivilege(ADMIN_USER, Utils.getStemIdentifier(root), USER_A, StemPrivilegeType.STEM);

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root), USER_A, StemPrivilegeType.STEM));

			StemDescriptor childX = grouper.addChildStem(USER_A, Utils.getStemIdentifier(root), extensionChildX,
				extensionChildX);
			assertEquals(extensionChildX, childX.getExtension());
			assertEquals(extensionChildX, childX.getDisplayExtension());

			List<StemDescriptor> c1 = grouper.getChildStems(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root));
			assertNotNull(c1);
			assertEquals(2, c1.size());

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(childX), USER_A, StemPrivilegeType.STEM));
			try {
				grouper.grantStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
					.getStemIdentifier(childX), USER_A, StemPrivilegeType.CREATE);
				fail("Should have failed, insufficient privilege!!!");
			} catch (InsufficientPrivilegeException e) {

			}
			grouper.grantStemPrivilege(USER_A, Utils.getStemIdentifier(childX), USER_A, StemPrivilegeType.CREATE);
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(childX), USER_A, StemPrivilegeType.CREATE));
			assertEquals(grouper
				.getStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils.getRootStemIdentifier()), grouper
				.getParentStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils.getStemIdentifier(childX)));

			StemDescriptor childX1 = grouper.addChildStem(USER_A, Utils.getStemIdentifier(childX), extensionChildX1,
				extensionChildX1);
			assertEquals(extensionChildX1, childX1.getExtension());
			assertEquals(extensionChildX1, childX1.getDisplayExtension());

			List<StemDescriptor> c2 = grouper.getChildStems(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(childX));
			assertNotNull(c2);
			assertEquals(1, c2.size());

			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(childX1), USER_A, StemPrivilegeType.STEM));
			try {
				grouper.grantStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
					.getStemIdentifier(childX1), USER_A, StemPrivilegeType.CREATE);
				fail("Should have failed, insufficient privilege!!!");
			} catch (InsufficientPrivilegeException e) {

			}
			grouper.grantStemPrivilege(USER_A, Utils.getStemIdentifier(childX1), USER_A, StemPrivilegeType.CREATE);
			assertTrue(grouper.hasStemPrivilege(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(childX1), USER_A, StemPrivilegeType.CREATE));
			assertEquals(grouper.getStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(childX)), grouper.getParentStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID,
				Utils.getStemIdentifier(childX1)));
			try {
				grouper.deleteStem(USER_A, Utils.getStemIdentifier(childX));
				fail("Should not to be able to delete stem, it has child stems!!!");
			} catch (StemDeleteException e) {

			}

			try {
				grouper.deleteStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils.getStemIdentifier(childX1));
				fail("Should have failed, insufficient privilege!!!");
			} catch (InsufficientPrivilegeException e) {

			}

			grouper.deleteStem(USER_A, Utils.getStemIdentifier(childX1));

			List<StemDescriptor> c3 = grouper.getChildStems(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(childX));
			assertNotNull(c3);
			assertEquals(0, c3.size());

			try {
				grouper.deleteStem(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils.getStemIdentifier(childX));
				fail("Should have failed, insufficient privilege!!!");
			} catch (InsufficientPrivilegeException e) {

			}

			grouper.deleteStem(USER_A, Utils.getStemIdentifier(childX));

			List<StemDescriptor> c4 = grouper.getChildStems(AnonymousGridUserSubject.ANONYMOUS_GRID_USER_ID, Utils
				.getStemIdentifier(root));
			assertNotNull(c4);
			assertEquals(1, c4.size());

		} catch (Exception e) {
			assertTrue(ExceptionUtils.getFullStackTrace(e), false);
		}

	}


	private void checkStem(StemDescriptor des, String displayExtension, String description) {
		assertEquals(displayExtension, des.getDisplayExtension());
		assertEquals(description, des.getDescription());
	}


	protected void setUp() throws Exception {
		super.setUp();
		RegistryReset.reset();
		this.grouper = new GridGrouper();
	}


	protected void tearDown() throws Exception {
		super.tearDown();
		RegistryReset.reset();
	}

}
