package org.cagrid.cds.service.impl;

import junit.framework.TestCase;
import org.cagrid.cds.model.AllowedParties;
import org.cagrid.cds.model.DelegatedCredentialAuditFilter;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.DelegationPolicy;
import org.cagrid.cds.model.DelegationRecord;
import org.cagrid.cds.model.DelegationRecordFilter;
import org.cagrid.cds.model.DelegationRequest;
import org.cagrid.cds.model.DelegationSigningRequest;
import org.cagrid.cds.model.DelegationSigningResponse;
import org.cagrid.cds.model.DelegationStatus;
import org.cagrid.cds.model.ExpirationStatus;
import org.cagrid.cds.model.IdentityDelegationPolicy;
import org.cagrid.cds.model.ProxyLifetime;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.exception.PermissionDeniedException;
import org.cagrid.cds.service.impl.manager.DelegationManager;
import org.cagrid.cds.service.impl.testutils.CA;
import org.cagrid.cds.service.impl.testutils.Constants;
import org.cagrid.cds.service.impl.testutils.Utils;
import org.cagrid.cds.service.impl.util.Errors;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.tools.groups.Group;
import org.cagrid.tools.groups.GroupManager;
import org.globus.gsi.GlobusCredential;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DelegationManagerTest extends TestCase {

	private int DEFAULT_PROXY_LIFETIME_SECONDS = 300;
	private final static String ADMIN_ALIAS = "admin";

	private CA ca;
	private File caCert;
	private String caDN;

	public void testDelegatedCredentialCreateDestroy() {
		try {
			DelegationManager cds = Utils.getCDS();
			cds.clear();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testManagesAdmins() {
		DelegationManager cds = null;
		try {
			GlobusCredential admin = addInitialAdmin();
			cds = Utils.getCDS();
			List<String> admins = new ArrayList<String>();
			admins.add(admin.getIdentity());
			checkAdminList(admin.getIdentity(), cds, admins);
			String userPrefix = "/O=XYZ/OU=ABC/CN=user";
			int count = 3;
			for (int i = 0; i < count; i++) {
				String user = userPrefix + i;
				try {
					cds.addAdmin(user, user);
					fail("Should not be able to execute admin operation.");
				} catch (PermissionDeniedException e) {
                    assertEquals("Should not be able to execute admin operation.", e.getMessage(), Errors.ADMIN_REQUIRED);
				}

				try {
					cds.removeAdmin(user, user);
					fail("Should not be able to execute admin operation.");
				} catch (PermissionDeniedException e) {
                    assertEquals("Should not be able to execute admin operation.", e.getMessage(), Errors.ADMIN_REQUIRED);
				}

				try {
					cds.getAdmins(user);
					fail("Should not be able to execute admin operation.");
				} catch (PermissionDeniedException e) {
                    assertEquals("Should not be able to execute admin operation.", e.getMessage(), Errors.ADMIN_REQUIRED);
				}
				cds.addAdmin(admin.getIdentity(), user);
				admins.add(user);
				this.checkAdminList(user, cds, admins);
			}

			for (int i = 0; i < count; i++) {
				String user = userPrefix + i;
				cds.removeAdmin(admin.getIdentity(), user);
				admins.remove(user);
				try {
					cds.getAdmins(user);
					fail("Should not be able to execute admin operation.");
				} catch (PermissionDeniedException e) {
                    assertEquals("Should not be able to execute admin operation.", e.getMessage(), Errors.ADMIN_REQUIRED);
				}
				this.checkAdminList(admin.getIdentity(), cds, admins);
			}
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			if (cds != null) {
				try {
					cds.clear();
				} catch (Exception e) {
				}
			}
		}
	}

	private void checkAdminList(String admin, DelegationManager cds,
			List<String> expected) throws Exception {
		assertNotNull(expected);
		assertNotNull(admin);
		assertNotNull(cds);
		List<String> actual = cds.getAdmins(admin);
		assertNotNull(actual);
		assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++) {
			boolean found = false;
			for (int j = 0; j < actual.size(); j++) {
				if (actual.get(j).equals(expected.get(i))) {
					found = true;
					break;
				}
			}
			if (!found) {
				fail("Did not find an expected administrator.");
			}
		}
	}

	private GlobusCredential addInitialAdmin() throws Exception {
		GlobusCredential admin = ca.createCredential(ADMIN_ALIAS);
		GroupManager gm = Utils.getGroupManager();
		Group admins = null;
		if (!gm.groupExists(DelegationManager.ADMINISTRATORS)) {
			gm.addGroup(DelegationManager.ADMINISTRATORS);
			admins = gm.getGroup(DelegationManager.ADMINISTRATORS);
		} else {
			admins = gm.getGroup(DelegationManager.ADMINISTRATORS);
		}
		admins.addMember(admin.getIdentity());
		assertTrue(admins.isMember(admin.getIdentity()));
		return admin;
	}

	public void testUpdateDelegationStatusNonAdminUser() {
		DelegationManager cds = null;
		try {
			cds = Utils.getCDS();
			String leonardoAlias = "leonardo";
			String donatelloAlias = "donatello";

			GlobusCredential leonardoCred = ca.createCredential(leonardoAlias);
			GlobusCredential donatelloCred = ca
					.createCredential(donatelloAlias);

			DelegationPolicy policy = getSimplePolicy(donatelloCred
					.getIdentity());

			DelegationSigningRequest leonardoReq = cds.initiateDelegation(
					leonardoCred.getIdentity(),
					getSimpleDelegationRequest(policy));
			DelegationSigningResponse leonardoRes = new DelegationSigningResponse();
			leonardoRes.setDelegationIdentifier(leonardoReq
					.getDelegationIdentifier());
			leonardoRes.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
					.toCertificateChain(ca.createProxyCertifcates(
							leonardoAlias, KeyUtil.loadPublicKey(leonardoReq
                            .getPublicKey().getKeyAsString()), 2)));
			cds.approveDelegation(leonardoCred.getIdentity(), leonardoRes);
			DelegationIdentifier id = leonardoReq.getDelegationIdentifier();
			try {
				cds.updateDelegatedCredentialStatus(
						donatelloCred.getIdentity(), id,
						DelegationStatus.SUSPENDED);
				fail("Should not be able to update the status of the delegated credential.");
			} catch (PermissionDeniedException e) {

			}

			try {
				cds.updateDelegatedCredentialStatus(leonardoCred.getIdentity(),
						id, DelegationStatus.APPROVED);
				fail("Should not be able to update the status of the delegated credential.");
			} catch (PermissionDeniedException e) {

			}

			try {
				cds.updateDelegatedCredentialStatus(leonardoCred.getIdentity(),
						id, DelegationStatus.PENDING);
				fail("Should not be able to update the status of the delegated credential.");
			} catch (PermissionDeniedException e) {

			}

			cds.updateDelegatedCredentialStatus(leonardoCred.getIdentity(), id,
					DelegationStatus.SUSPENDED);

			DelegationRecordFilter f = new DelegationRecordFilter();
			f.setDelegationIdentifier(id);
			List<DelegationRecord> records = cds.findDelegatedCredentials(
					leonardoCred.getIdentity(), f);
			assertNotNull(records);
			assertEquals(1, records.size());
			assertEquals(DelegationStatus.SUSPENDED, records.get(0)
					.getDelegationStatus());

			try {
				cds.updateDelegatedCredentialStatus(leonardoCred.getIdentity(),
						id, DelegationStatus.SUSPENDED);
				fail("Should not be able to update the status of the delegated credential.");
			} catch (PermissionDeniedException e) {

			}

			try {
				cds.updateDelegatedCredentialStatus(leonardoCred.getIdentity(),
						id, DelegationStatus.APPROVED);
				fail("Should not be able to update the status of the delegated credential.");
			} catch (PermissionDeniedException e) {

			}

			try {
				cds.updateDelegatedCredentialStatus(leonardoCred.getIdentity(),
						id, DelegationStatus.PENDING);
				fail("Should not be able to update the status of the delegated credential.");
			} catch (PermissionDeniedException e) {

			}

		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			if (cds != null) {
				try {
					cds.clear();
				} catch (Exception e) {
				}
			}
		}
	}

	public void testUpdateDelegationStatusAdminUser() {
		DelegationManager cds = null;
		try {
			cds = Utils.getCDS();
			GlobusCredential admin = addInitialAdmin();
			String leonardoAlias = "leonardo";
			String donatelloAlias = "donatello";

			GlobusCredential leonardoCred = ca.createCredential(leonardoAlias);
			GlobusCredential donatelloCred = ca
					.createCredential(donatelloAlias);

			DelegationPolicy policy = getSimplePolicy(donatelloCred
                    .getIdentity());

			DelegationSigningRequest leonardoReq = cds.initiateDelegation(
					leonardoCred.getIdentity(),
					getSimpleDelegationRequest(policy));
			DelegationSigningResponse leonardoRes = new DelegationSigningResponse();
			leonardoRes.setDelegationIdentifier(leonardoReq
                    .getDelegationIdentifier());
			leonardoRes.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
                    .toCertificateChain(ca.createProxyCertifcates(
                            leonardoAlias, KeyUtil.loadPublicKey(leonardoReq
                            .getPublicKey().getKeyAsString()), 2)));
			cds.approveDelegation(leonardoCred.getIdentity(), leonardoRes);
			DelegationIdentifier id = leonardoReq.getDelegationIdentifier();

			try {
				cds.updateDelegatedCredentialStatus(admin.getIdentity(), id,
						DelegationStatus.PENDING);
				fail("Should not be able to update the status of the delegated credential.");
			} catch (DelegationException e) {
                assertEquals("Should not be able to update the status of the delegated credential.", e.getMessage(), Errors.CANNOT_CHANGE_STATUS_TO_PENDING);
			}

			cds.updateDelegatedCredentialStatus(admin.getIdentity(), id,
					DelegationStatus.SUSPENDED);

			DelegationRecordFilter f = new DelegationRecordFilter();
			f.setDelegationIdentifier(id);
			List<DelegationRecord> records = cds.findDelegatedCredentials(
					leonardoCred.getIdentity(), f);
			assertNotNull(records);
			assertEquals(1, records.size());
			assertEquals(DelegationStatus.SUSPENDED, records.get(0)
					.getDelegationStatus());

			cds.updateDelegatedCredentialStatus(admin.getIdentity(), id,
                    DelegationStatus.APPROVED);

			records = cds.findDelegatedCredentials(leonardoCred.getIdentity(),
					f);
			assertNotNull(records);
			assertEquals(1, records.size());
			assertEquals(DelegationStatus.APPROVED, records.get(0)
					.getDelegationStatus());

		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			if (cds != null) {
				try {
					cds.clear();
				} catch (Exception e) {
				}
			}
		}
	}

	public void testDeleteDelegatedCredential() {
		DelegationManager cds = null;
		try {
			cds = Utils.getCDS();
			GlobusCredential admin = addInitialAdmin();
			String leonardoAlias = "leonardo";
			String donatelloAlias = "donatello";

			GlobusCredential leonardoCred = ca.createCredential(leonardoAlias);
			GlobusCredential donatelloCred = ca
					.createCredential(donatelloAlias);

			DelegationPolicy policy = getSimplePolicy(donatelloCred
					.getIdentity());

			DelegationSigningRequest leonardoReq = cds.initiateDelegation(
					leonardoCred.getIdentity(),
					getSimpleDelegationRequest(policy));
			DelegationSigningResponse leonardoRes = new DelegationSigningResponse();
			leonardoRes.setDelegationIdentifier(leonardoReq
					.getDelegationIdentifier());
			leonardoRes.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
					.toCertificateChain(ca.createProxyCertifcates(
							leonardoAlias, KeyUtil.loadPublicKey(leonardoReq
                            .getPublicKey().getKeyAsString()), 2)));
			cds.approveDelegation(leonardoCred.getIdentity(), leonardoRes);
			DelegationIdentifier id = leonardoReq.getDelegationIdentifier();
			DelegationRecordFilter f = new DelegationRecordFilter();
			f.setDelegationIdentifier(id);
			assertEquals(1, cds.findDelegatedCredentials(admin.getIdentity(), f).size());


			try {
				cds.deleteDelegatedCredential(leonardoCred
						.getIdentity(), id);
				fail("Should not be able to delete the delegate credentail.");
			} catch (PermissionDeniedException e) {
                assertEquals("Should not be able to delete the delegate credentail.", e.getMessage(), Errors.ADMIN_REQUIRED);
			}

			try {
				cds.deleteDelegatedCredential(donatelloCred
						.getIdentity(), id);
				fail("Should not be able to delete the delegate credentail.");
			} catch (PermissionDeniedException e) {
                assertEquals("Should not be able to delete the delegate credentail.", e.getMessage(), Errors.ADMIN_REQUIRED);
			}
			cds.deleteDelegatedCredential(admin.getIdentity(), id);
			assertEquals(0, cds.findDelegatedCredentials(admin.getIdentity(), f).size());
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			if (cds != null) {
				try {
					cds.clear();
				} catch (Exception e) {
				}
			}
		}
	}


	public void testAuditAdminUser() {
		DelegationManager cds = null;
		try {
			cds = Utils.getCDS();
			GlobusCredential admin = addInitialAdmin();
			String leonardoAlias = "leonardo";
			String donatelloAlias = "donatello";

			GlobusCredential leonardoCred = ca.createCredential(leonardoAlias);
			GlobusCredential donatelloCred = ca
					.createCredential(donatelloAlias);

			DelegationPolicy policy = getSimplePolicy(donatelloCred
					.getIdentity());

			DelegationSigningRequest leonardoReq = cds.initiateDelegation(
					leonardoCred.getIdentity(),
					getSimpleDelegationRequest(policy));
			DelegationSigningResponse leonardoRes = new DelegationSigningResponse();
			leonardoRes.setDelegationIdentifier(leonardoReq
					.getDelegationIdentifier());
			leonardoRes.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
					.toCertificateChain(ca.createProxyCertifcates(
							leonardoAlias, KeyUtil.loadPublicKey(leonardoReq
                            .getPublicKey().getKeyAsString()), 2)));
			cds.approveDelegation(leonardoCred.getIdentity(), leonardoRes);
			DelegationIdentifier id = leonardoReq.getDelegationIdentifier();

			DelegatedCredentialAuditFilter f = null;

			try {
				cds.searchDelegatedCredentialAuditLog(leonardoCred
						.getIdentity(), f);
				fail("Should not be able to search the audit log.");
			} catch (PermissionDeniedException e) {
                assertEquals("Should not be able to search the audit log.", e.getMessage(), Errors.PERMISSION_DENIED_NO_DELEGATED_CREDENTIAL_SPECIFIED);
			}
			f = new DelegatedCredentialAuditFilter();

			cds.searchDelegatedCredentialAuditLog(admin.getIdentity(), f);
			assertEquals(2, cds.searchDelegatedCredentialAuditLog(admin
					.getIdentity(), f).size());

			f.setDelegationIdentifier(id);
			assertEquals(2, cds.searchDelegatedCredentialAuditLog(admin
					.getIdentity(), f).size());

			try {
				cds.searchDelegatedCredentialAuditLog(donatelloCred
						.getIdentity(), f);
				fail("Should not be able to search the audit log.");
			} catch (PermissionDeniedException e) {
                assertEquals("Should not be able to search the audit log.", e.getMessage(), Errors.PERMISSION_DENIED_TO_AUDIT);
			}

		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			if (cds != null) {
				try {
					cds.clear();
				} catch (Exception e) {
				}
			}
		}
	}

	public void testAuditNonAdminUser() {
		DelegationManager cds = null;
		try {
			cds = Utils.getCDS();
			String leonardoAlias = "leonardo";
			String donatelloAlias = "donatello";

			GlobusCredential leonardoCred = ca.createCredential(leonardoAlias);
			GlobusCredential donatelloCred = ca
					.createCredential(donatelloAlias);

			DelegationPolicy policy = getSimplePolicy(donatelloCred
                    .getIdentity());

			DelegationSigningRequest leonardoReq = cds.initiateDelegation(
                    leonardoCred.getIdentity(),
                    getSimpleDelegationRequest(policy));
			DelegationSigningResponse leonardoRes = new DelegationSigningResponse();
			leonardoRes.setDelegationIdentifier(leonardoReq
					.getDelegationIdentifier());
			leonardoRes.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
                    .toCertificateChain(ca.createProxyCertifcates(
                            leonardoAlias, KeyUtil.loadPublicKey(leonardoReq
                            .getPublicKey().getKeyAsString()), 2)));
			cds.approveDelegation(leonardoCred.getIdentity(), leonardoRes);
			DelegationIdentifier id = leonardoReq.getDelegationIdentifier();

			DelegatedCredentialAuditFilter f = null;

			try {
				cds.searchDelegatedCredentialAuditLog(leonardoCred
						.getIdentity(), f);
				fail("Should not be able to search the audit log.");
			} catch (PermissionDeniedException e) {
                assertEquals("Should not be able to search the audit log.", e.getMessage(), Errors.PERMISSION_DENIED_NO_DELEGATED_CREDENTIAL_SPECIFIED);
			}
			f = new DelegatedCredentialAuditFilter();
			try {
				cds.searchDelegatedCredentialAuditLog(leonardoCred
						.getIdentity(), f);
				fail("Should not be able to search the audit log.");
			} catch (PermissionDeniedException e) {
                assertEquals("Should not be able to search the audit log.", e.getMessage(), Errors.PERMISSION_DENIED_NO_DELEGATED_CREDENTIAL_SPECIFIED);
			}

			f.setDelegationIdentifier(id);
			assertEquals(2, cds.searchDelegatedCredentialAuditLog(leonardoCred
					.getIdentity(), f).size());

			try {
				cds.searchDelegatedCredentialAuditLog(donatelloCred
						.getIdentity(), f);
				fail("Should not be able to search the audit log.");
			} catch (PermissionDeniedException e) {
                assertEquals("Should not be able to search the audit log.", e.getMessage(), Errors.PERMISSION_DENIED_TO_AUDIT);
			}

		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			if (cds != null) {
				try {
					cds.clear();
				} catch (Exception e) {
				}
			}
		}
	}

	public void testFindDelegatedCredentials() {
		DelegationManager cds = null;
		try {
			cds = Utils.getCDS();
			GlobusCredential admin = addInitialAdmin();
			String leonardoAlias = "leonardo";
			String donatelloAlias = "donatello";
			String michelangeloAlias = "michelangelo";

			GlobusCredential leonardoCred = ca.createCredential(leonardoAlias);
			GlobusCredential donatelloCred = ca
					.createCredential(donatelloAlias);
			GlobusCredential michelangeloCred = ca
					.createCredential(michelangeloAlias);

			DelegationPolicy policy = getSimplePolicy(michelangeloCred
					.getIdentity());

			DelegationSigningRequest leonardoReq = cds.initiateDelegation(
					leonardoCred.getIdentity(),
					getSimpleDelegationRequest(policy));
			DelegationSigningResponse leonardoRes = new DelegationSigningResponse();
			leonardoRes.setDelegationIdentifier(leonardoReq
					.getDelegationIdentifier());
			leonardoRes.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
					.toCertificateChain(ca.createProxyCertifcates(
							leonardoAlias, KeyUtil.loadPublicKey(leonardoReq
                            .getPublicKey().getKeyAsString()), 2)));
			cds.approveDelegation(leonardoCred.getIdentity(), leonardoRes);

			DelegationSigningRequest donatelloReq = cds.initiateDelegation(
					donatelloCred.getIdentity(),
					getSimpleDelegationRequest(policy));
			DelegationSigningResponse donatelloRes = new DelegationSigningResponse();
			donatelloRes.setDelegationIdentifier(donatelloReq
					.getDelegationIdentifier());
			donatelloRes.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
					.toCertificateChain(ca.createProxyCertifcates(
							donatelloAlias, KeyUtil.loadPublicKey(donatelloReq
                            .getPublicKey().getKeyAsString()), 2)));
			cds.approveDelegation(donatelloCred.getIdentity(), donatelloRes);

			cds.initiateDelegation(donatelloCred.getIdentity(),
					getSimpleDelegationRequest(policy));

			DelegationRecordFilter f = new DelegationRecordFilter();
			validateFind(cds, admin.getIdentity(), f, 3,false);
			validateFindMy(cds, leonardoCred.getIdentity(), f, 1);
			validateFindMy(cds, donatelloCred.getIdentity(), f, 2);
			validateFindMy(cds, michelangeloCred.getIdentity(), f, 0);

			resetFilter(f);
			f.setDelegationIdentifier(leonardoReq.getDelegationIdentifier());
			validateFind(cds, admin.getIdentity(), f, 1,false);
			validateFindMy(cds, leonardoCred.getIdentity(), f, 1);
			validateFindMy(cds, donatelloCred.getIdentity(), f, 0);
			validateFindMy(cds, michelangeloCred.getIdentity(), f, 0);

			resetFilter(f);
			f.setDelegationIdentifier(donatelloReq.getDelegationIdentifier());
			validateFind(cds, admin.getIdentity(), f, 1,false);
			validateFindMy(cds, leonardoCred.getIdentity(), f, 0);
			validateFindMy(cds, donatelloCred.getIdentity(), f, 1);
			validateFindMy(cds, michelangeloCred.getIdentity(), f, 0);

			resetFilter(f);
			f.setGridIdentity(leonardoCred.getIdentity());
			validateFind(cds, admin.getIdentity(), f, 1,false);
			validateFindMy(cds, leonardoCred.getIdentity(), f, 1);
			validateFindMy(cds, donatelloCred.getIdentity(), f, 2);
			validateFindMy(cds, michelangeloCred.getIdentity(), f, 0);

			resetFilter(f);
			f.setGridIdentity(donatelloCred.getIdentity());
			validateFind(cds, admin.getIdentity(), f, 2,false);
			validateFindMy(cds, leonardoCred.getIdentity(), f, 1);
			validateFindMy(cds, donatelloCred.getIdentity(), f, 2);
			validateFindMy(cds, michelangeloCred.getIdentity(), f, 0);

			resetFilter(f);
			f.setGridIdentity(michelangeloCred.getIdentity());
			validateFind(cds, admin.getIdentity(), f, 0,false);
			validateFindMy(cds, leonardoCred.getIdentity(), f, 1);
			validateFindMy(cds, donatelloCred.getIdentity(), f, 2);
			validateFindMy(cds, michelangeloCred.getIdentity(), f, 0);

			resetFilter(f);
			f.setExpirationStatus(ExpirationStatus.VALID);
			validateFind(cds, admin.getIdentity(), f, 2,false);
			validateFindMy(cds, leonardoCred.getIdentity(), f, 1);
			validateFindMy(cds, donatelloCred.getIdentity(), f, 1);
			validateFindMy(cds, michelangeloCred.getIdentity(), f, 0);

			resetFilter(f);
			f.setExpirationStatus(ExpirationStatus.EXPIRED);
			validateFind(cds, admin.getIdentity(), f, 0,false);
			validateFindMy(cds, leonardoCred.getIdentity(), f, 0);
			validateFindMy(cds, donatelloCred.getIdentity(), f, 0);
			validateFindMy(cds, michelangeloCred.getIdentity(), f, 0);

			resetFilter(f);
			f.setDelegationStatus(DelegationStatus.APPROVED);
			validateFind(cds, admin.getIdentity(), f, 2,false);
			validateFindMy(cds, leonardoCred.getIdentity(), f, 1);
			validateFindMy(cds, donatelloCred.getIdentity(), f, 1);
			validateFindMy(cds, michelangeloCred.getIdentity(), f, 0);

			resetFilter(f);
			f.setDelegationStatus(DelegationStatus.PENDING);
			validateFind(cds, admin.getIdentity(), f, 1,false);
			validateFindMy(cds, leonardoCred.getIdentity(), f, 0);
			validateFindMy(cds, donatelloCred.getIdentity(), f, 1);
			validateFindMy(cds, michelangeloCred.getIdentity(), f, 0);

		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			if (cds != null) {
				try {
					cds.clear();
				} catch (Exception e) {
				}
			}
		}
	}

	protected void resetFilter(DelegationRecordFilter f) throws Exception {
		f.setDelegationIdentifier(null);
		f.setGridIdentity(null);
		f.setDelegationStatus(null);
		f.setExpirationStatus(null);
	}

	protected void validateFindMy(DelegationManager cds, String gridIdentity,
			DelegationRecordFilter f, int expectedCount) throws Exception {
		validateFind(cds, gridIdentity, f, expectedCount, true);
	}

	protected void validateFind(DelegationManager cds, String gridIdentity,
			DelegationRecordFilter f, int expectedCount, boolean matchIds)
			throws Exception {
		List<DelegationRecord> records = cds.findDelegatedCredentials(gridIdentity,
				f);
		assertEquals(expectedCount, records.size());
		if (f.getDelegationIdentifier() != null) {
			for (int i = 0; i < records.size(); i++) {
				assertEquals(f.getDelegationIdentifier(), records.get(i)
						.getDelegationIdentifier());
			}
		}
		if (matchIds) {
			for (int i = 0; i < records.size(); i++) {
				assertEquals(gridIdentity, records.get(i).getGridIdentity());
			}
		}

		if (f.getDelegationStatus() != null) {
			for (int i = 0; i < records.size(); i++) {
				assertEquals(f.getDelegationStatus(), records.get(i)
						.getDelegationStatus());
			}
		}
	}

	protected IdentityDelegationPolicy getSimplePolicy(String gridIdentity) {
		IdentityDelegationPolicy policy = new IdentityDelegationPolicy();
		AllowedParties ap = new AllowedParties();
		ap.getGridIdentity().add(gridIdentity);
		policy.setAllowedParties(ap);
		return policy;
	}

	protected DelegationRequest getSimpleDelegationRequest(
			DelegationPolicy policy) {
		DelegationRequest req = new DelegationRequest();
		req.setDelegationPolicy(policy);
		req.setKeyLength(Constants.KEY_LENGTH);
		req.setIssuedCredentialPathLength(Constants.DELEGATION_PATH_LENGTH);
		ProxyLifetime lifetime = new ProxyLifetime();
		lifetime.setHours(0);
		lifetime.setMinutes(0);
		lifetime.setSeconds(DEFAULT_PROXY_LIFETIME_SECONDS);
		req.setIssuedCredentialLifetime(lifetime);
		return req;
	}

	protected void setUp() throws Exception {
		super.setUp();
		Utils.getDatabase().createDatabaseIfNeeded();
		try {
			Date now = new Date();	
			this.caDN = "O=Delegation Credential Manager,OU="+now.getTime()+",CN=Certificate Authority";
			this.ca = new CA(this.caDN);
			File f = gov.nih.nci.cagrid.common.Utils
					.getTrustedCerificatesDirectory();
			f.mkdirs();
			caCert = new File(f.getAbsoluteFile() + File.separator
					+ now.getTime()+".0");
			CertUtil.writeCertificate(this.ca.getCertificate(), caCert);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	protected void tearDown() throws Exception {
		super.setUp();
		caCert.delete();
	}

}
