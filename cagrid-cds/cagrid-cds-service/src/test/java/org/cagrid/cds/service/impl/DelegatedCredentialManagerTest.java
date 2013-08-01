package org.cagrid.cds.service.impl;

import junit.framework.TestCase;
import org.cagrid.cds.model.AllowedParties;
import org.cagrid.cds.model.CertificateChain;
import org.cagrid.cds.model.ClientDelegationFilter;
import org.cagrid.cds.model.DelegatedCredentialAuditFilter;
import org.cagrid.cds.model.DelegatedCredentialAuditRecord;
import org.cagrid.cds.model.DelegatedCredentialEvent;
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
import org.cagrid.cds.service.exception.CDSInternalException;
import org.cagrid.cds.service.exception.DelegationException;
import org.cagrid.cds.service.exception.InvalidPolicyException;
import org.cagrid.cds.service.exception.PermissionDeniedException;
import org.cagrid.cds.service.impl.manager.DelegatedCredentialManager;
import org.cagrid.cds.service.impl.manager.KeyManager;
import org.cagrid.cds.service.impl.policy.PolicyHandler;
import org.cagrid.cds.service.impl.testutils.CA;
import org.cagrid.cds.service.impl.testutils.Constants;
import org.cagrid.cds.service.impl.testutils.DelegatedCredential;
import org.cagrid.cds.service.impl.testutils.InvalidDelegationPolicy;
import org.cagrid.cds.service.impl.testutils.Utils;
import org.cagrid.cds.service.impl.util.Errors;
import org.cagrid.cds.service.impl.util.ProxyPolicy;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.globus.gsi.CertificateRevocationLists;
import org.globus.gsi.GlobusCredential;
import org.globus.gsi.TrustedCertificates;
import org.globus.gsi.proxy.ProxyPathValidator;

import java.io.File;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DelegatedCredentialManagerTest extends TestCase {

	private static String GRID_IDENTITY = "/C=US/O=abc/OU=xyz/OU=caGrid/CN=user";

	private CA ca;

	private File caCert;
	
	private String caDN;

	private int DEFAULT_PROXY_LIFETIME_SECONDS = 300;

	private int PROXY_BUFFER_MAX_COUNT = 8;

	private int PROXY_BUFFER_TIME_MULTIPLIER = 2;
	
	

	public void testDelegatedCredentialCreateDestroy() {
		try {
			DelegatedCredentialManager dcm = Utils
					.getDelegatedCredentialManager();
			dcm.clearDatabase();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testChangeKeyManager() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			try {
				new DelegatedCredentialManager(Utils.getDatabase(), Utils
						.getPropertyManager(), new InvalidKeyManager(),
						new ArrayList<PolicyHandler>(), new ProxyPolicy(),
						Utils.getEventManager());
				fail("Should not be able to change Key Manager.");
			} catch (CDSInternalException e) {
                assertEquals("Should not be able to change Key Manager.", e.getMessage(), Errors.KEY_MANAGER_CHANGED);
			}
			assertEquals(
					0,
					dcm.searchAuditLog(new DelegatedCredentialAuditFilter()).size());
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialInvalidPolicy() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			try {
				DelegationRequest req = new DelegationRequest();
				req.setDelegationPolicy(new InvalidDelegationPolicy());
				req.setKeyLength(Constants.KEY_LENGTH);
				req
						.setIssuedCredentialPathLength(Constants.DELEGATION_PATH_LENGTH);
				dcm.initiateDelegation("some user", req);
				fail("Should not be able to delegate a credential with an invalid delegation policy.");
			} catch (InvalidPolicyException e) {

			}
			assertEquals(
					0,
					dcm.searchAuditLog(new DelegatedCredentialAuditFilter()).size());
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialInvalidProxyLifetime() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			try {
				DelegationRequest req = getSimpleDelegationRequest();
				req.setIssuedCredentialLifetime(null);
				dcm.initiateDelegation("some user", req);
				fail("Should not be able to delegate a credential without a delegate proxy lifetime specified.");
			} catch (DelegationException e) {
                assertEquals("Should not be able to delegate a credential without a delegate proxy lifetime specified.", e.getMessage(), Errors.PROXY_LIFETIME_NOT_SPECIFIED);
			}
			assertEquals(
					0,
					dcm.searchAuditLog(new DelegatedCredentialAuditFilter()).size());
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialInvalidKeyLength() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();

			DelegationRequest req = getSimpleDelegationRequest();
			req.setKeyLength(1);

			try {
				dcm.initiateDelegation("some user", req);
				fail("Should not be able to delegate a credential with an invalid Key Length.");
			} catch (DelegationException e) {
                assertEquals("Should not be able to delegate a credential with an invalid Key Length.", e.getMessage(), Errors.INVALID_KEY_LENGTH_SPECIFIED);
			}
			assertEquals(
					0,
					dcm.searchAuditLog(new DelegatedCredentialAuditFilter()).size());
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialInvalidDelegationPathLength() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();

			DelegationRequest req = getSimpleDelegationRequest();
			req.setIssuedCredentialPathLength(1);

			try {
				dcm.initiateDelegation("some user", req);
				fail("Should not be able to delegate a credential with an invalid delegation path length.");
			} catch (DelegationException e) {
                assertEquals("Should not be able to delegate a credential with an invalid delegation path length.", e.getMessage(), Errors.INVALID_DELEGATION_PATH_LENGTH_SPECIFIED);
			}
			assertEquals(
					0,
					dcm.searchAuditLog(new DelegatedCredentialAuditFilter()).size());
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialInitiatorDoesNotMatchApprover() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();

			String alias = "some user";
			assertEquals(0,
					dcm.searchAuditLog(Utils.getInitiatedAuditFilter()).size());
			DelegationSigningRequest req = dcm.initiateDelegation(alias,
					getSimpleDelegationRequest());
			assertEquals(1,
					dcm.searchAuditLog(Utils.getInitiatedAuditFilter()).size());
			try {
				DelegationSigningResponse res = new DelegationSigningResponse();
				res.setDelegationIdentifier(req.getDelegationIdentifier());
				res.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
						.toCertificateChain(ca.createProxy(alias, 0)
								.getCertificateChain()));
				dcm.approveDelegation("some other user", res);
				fail("Should not be able to approve delegation.");
			} catch (DelegationException e) {
                assertEquals("Should not be able to approve delegation.", e.getMessage(), Errors.INITIATOR_DOES_NOT_MATCH_APPROVER);
			}

		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialApproveWithInvalidStatus() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "jdoe";
			GlobusCredential cred = ca.createCredential(alias);
			DelegatedCredential dc = delegateAndValidate(dcm, alias, cred
					.getIdentity(), null);
			try {
				dcm.approveDelegation(cred.getIdentity(), dc
						.getSigningResponse());
			} catch (DelegationException e) {
                assertEquals("Should not be able to approve delegation.", e.getMessage(), Errors.CANNOT_APPROVE_INVALID_STATUS);
			}
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialsWithIdentityDelegationPolicy() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			List<DelegationIdentifier> list = new ArrayList<DelegationIdentifier>();
			int size = 3;

			String alias = "jdoe";
			GlobusCredential cred = ca.createCredential(alias);
			String gridIdentity = cred.getIdentity();
			for (int i = 0; i < size; i++) {
				IdentityDelegationPolicy policy = new IdentityDelegationPolicy();
                AllowedParties ap = new AllowedParties();
				for (int j = 0; j <= i; j++) {
                    ap.getGridIdentity().add(GRID_IDENTITY + (j + 1));
				}

				policy.setAllowedParties(ap);
				list.add(delegateAndValidate(dcm, alias, gridIdentity, policy,
						i).getDelegationIdentifier());
			}
			for (int i = 0; i < list.size(); i++) {
				DelegationIdentifier id = list.get(i);
				assertTrue(dcm.delegationExists(id));
				dcm.delete(id);
				assertFalse(dcm.delegationExists(id));
				DelegatedCredentialAuditFilter f = new DelegatedCredentialAuditFilter();
				f.setDelegationIdentifier(id);
				assertEquals(0, dcm.searchAuditLog(f).size());
			}
		} catch (Exception e) {
			
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialNoCertificateChain() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "some user";
			GlobusCredential cred = ca.createCredential(alias);
			DelegationSigningRequest req = dcm.initiateDelegation(cred
					.getIdentity(), getSimpleDelegationRequest());
			try {
				DelegationSigningResponse res = new DelegationSigningResponse();
				res.setDelegationIdentifier(req.getDelegationIdentifier());
				res.setCertificateChain(null);
				dcm.approveDelegation(cred.getIdentity(), res);
				fail("Should not be able to approve delegation.");
			} catch (DelegationException e) {
                assertEquals("Should not be able to approve delegation.", e.getMessage(), Errors.CERTIFICATE_CHAIN_NOT_SPECIFIED);
			}

		} catch (Exception e) {
			
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialInsufficientCertificateChain() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "some user";
			GlobusCredential cred = ca.createCredential(alias);
			DelegationSigningRequest req = dcm.initiateDelegation(cred
					.getIdentity(), getSimpleDelegationRequest());
			try {

				DelegationSigningResponse res = new DelegationSigningResponse();
				res.setDelegationIdentifier(req.getDelegationIdentifier());
				res.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
						.toCertificateChain(cred.getCertificateChain()));
				dcm.approveDelegation(cred.getIdentity(), res);
				fail("Should not be able to approve delegation.");
			} catch (DelegationException e) {
                assertEquals("Should not be able to approve delegation.", e.getMessage(), Errors.INSUFFICIENT_CERTIFICATE_CHAIN_SPECIFIED);
			}

		} catch (Exception e) {
			
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialMismatchingPublicKeys() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "some user";
			GlobusCredential cred = ca.createCredential(alias);
			DelegationSigningRequest req = dcm.initiateDelegation(cred
					.getIdentity(), getSimpleDelegationRequest());
			try {
				GlobusCredential proxy = ca.createProxy(alias, 1);
				DelegationSigningResponse res = new DelegationSigningResponse();
				res.setDelegationIdentifier(req.getDelegationIdentifier());
				res.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
						.toCertificateChain(proxy.getCertificateChain()));
				dcm.approveDelegation(cred.getIdentity(), res);
				fail("Should not be able to approve delegation.");
			} catch (DelegationException e) {
                assertEquals("Should not be able to approve delegation.", e.getMessage(), Errors.PUBLIC_KEY_DOES_NOT_MATCH);
			}
		} catch (Exception e) {
			
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialInvalidProxyIdentity() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "some user";
			GlobusCredential cred = ca.createCredential(alias);
			DelegationSigningRequest req = dcm.initiateDelegation(cred
					.getIdentity(), getSimpleDelegationRequest());
			try {
				PublicKey publicKey = KeyUtil.loadPublicKey(req.getPublicKey()
                        .getKeyAsString());
				X509Certificate[] certs = ca.createProxyCertifcates(
						alias + "2", publicKey, 1);
				DelegationSigningResponse res = new DelegationSigningResponse();
				res.setDelegationIdentifier(req.getDelegationIdentifier());
				res.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
						.toCertificateChain(certs));
				dcm.approveDelegation(cred.getIdentity(), res);
				fail("Should not be able to approve delegation.");
			} catch (DelegationException e) {
				if (!e.getMessage().equals(
						Errors.IDENTITY_DOES_NOT_MATCH_INITIATOR)) {
					fail("Should not be able to approve delegation.");
				}
			}
		} catch (Exception e) {
			
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialInvalidProxyChain() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "some user";
			GlobusCredential cred = ca.createCredential(alias);
			DelegationSigningRequest req = dcm.initiateDelegation(cred
					.getIdentity(), getSimpleDelegationRequest());
			try {
				PublicKey publicKey = KeyUtil.loadPublicKey(req.getPublicKey()
                        .getKeyAsString());
				X509Certificate[] certs = ca.createProxyCertifcates(alias,
						publicKey, 1);
				X509Certificate[] certs2 = ca.createProxyCertifcates(alias + 2,
						publicKey, 1);
				certs[1] = certs2[1];
				DelegationSigningResponse res = new DelegationSigningResponse();
				res.setDelegationIdentifier(req.getDelegationIdentifier());
				res.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
						.toCertificateChain(certs));
				dcm.approveDelegation(cred.getIdentity(), res);
				fail("Should not be able to approve delegation.");
			} catch (DelegationException e) {
				if (!e.getMessage()
						.equals(Errors.INVALID_CERTIFICATE_CHAIN)) {
					fail("Should not be able to approve delegation.");
				}
			}
		} catch (Exception e) {
			
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialInsufficientDelegationPathLength() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "some user";
			GlobusCredential cred = ca.createCredential(alias);
			DelegationSigningRequest req = dcm.initiateDelegation(cred
					.getIdentity(), getSimpleDelegationRequest());
			try {
				PublicKey publicKey = KeyUtil.loadPublicKey(req.getPublicKey()
                        .getKeyAsString());
				X509Certificate[] certs = ca.createProxyCertifcates(alias,
						publicKey, 0);
				DelegationSigningResponse res = new DelegationSigningResponse();
				res.setDelegationIdentifier(req.getDelegationIdentifier());
				res.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
						.toCertificateChain(certs));
				dcm.approveDelegation(cred.getIdentity(), res);
				fail("Should not be able to approve delegation.");
			} catch (DelegationException e) {
				if (!e.getMessage().equals(
						Errors.INSUFFICIENT_DELEGATION_PATH_LENGTH)) {
					fail("Should not be able to approve delegation.");
				}
			}
		} catch (Exception e) {
			
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testDelegateCredentialApprovalBufferExpired() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			DelegatedCredentialManager.DELEGATION_BUFFER_SECONDS = 1;
			String alias = "some user";
			GlobusCredential cred = ca.createCredential(alias);
			DelegationSigningRequest req = dcm.initiateDelegation(cred
					.getIdentity(), getSimpleDelegationRequest());
			try {
				PublicKey publicKey = KeyUtil.loadPublicKey(req.getPublicKey()
                        .getKeyAsString());
				X509Certificate[] certs = ca.createProxyCertifcates(alias,
						publicKey, 1);
				DelegationSigningResponse res = new DelegationSigningResponse();
				res.setDelegationIdentifier(req.getDelegationIdentifier());
				res.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
						.toCertificateChain(certs));
				Thread
						.sleep(((DelegatedCredentialManager.DELEGATION_BUFFER_SECONDS * 1000) + 100));
				dcm.approveDelegation(cred.getIdentity(), res);
				fail("Should not be able to approve delegation.");
			} catch (DelegationException e) {
				if (!e.getMessage().equals(
						Errors.DELEGATION_APPROVAL_BUFFER_EXPIRED)) {
					fail("Should not be able to approve delegation.");
				}
			}
		} catch (Exception e) {
			
			fail(e.getMessage());
		} finally {
			DelegatedCredentialManager.DELEGATION_BUFFER_SECONDS = 120;
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testGetNonExistingDelegatedCredential() {

		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			KeyPair pair = KeyUtil.generateRSAKeyPair1024();
			org.cagrid.cds.model.PublicKey publicKey = new org.cagrid.cds.model.PublicKey();
			publicKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));
			DelegationIdentifier id = new DelegationIdentifier();
			id.setDelegationId(2);
			try {
				dcm.getDelegatedCredential(GRID_IDENTITY, id, publicKey);
				fail("Should not be able get delegated credential.");
			} catch (DelegationException e) {
				if (!e.getMessage().equals(
						Errors.DELEGATION_RECORD_DOES_NOT_EXIST)) {
					fail("Should not be able get delegated credential.");
				}
			}

		} catch (Exception e) {
			
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}

	}

	public void testGetDelegatedCredentialUnAuthorizedUser() {

		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "jdoe";
			GlobusCredential cred = ca.createCredential(alias);
			String gridIdentity = cred.getIdentity();
			IdentityDelegationPolicy policy = getSimplePolicy();
			DelegationIdentifier id = this.delegateAndValidate(dcm, alias,
					gridIdentity, policy).getDelegationIdentifier();
			KeyPair pair = KeyUtil.generateRSAKeyPair1024();
			org.cagrid.cds.model.PublicKey publicKey = new org.cagrid.cds.model.PublicKey();
			publicKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));
			try {
				dcm.getDelegatedCredential(GRID_IDENTITY + 2, id, publicKey);
				fail("Should not be able get delegated credential.");
			} catch (PermissionDeniedException e) {
				if (!e.getMessage().equals(
						Errors.PERMISSION_DENIED_TO_DELEGATED_CREDENTIAL)) {
					fail("Should not be able get delegated credential.");
				}
			}
			assertEquals(0,
					dcm.searchAuditLog(Utils.getIssuedAuditFilter(id)).size());
			List<DelegatedCredentialAuditRecord> ar = dcm.searchAuditLog(Utils
					.getAccessDeniedAuditFilter(id));
			assertEquals(1, ar.size());
			assertEquals(id, ar.get(0).getDelegationIdentifier());
			assertEquals(GRID_IDENTITY + 2, ar.get(0).getSourceGridIdentity());
			assertEquals(
					DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ACCESS_DENIED,
					ar.get(0).getEvent());
		} catch (Exception e) {
			
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}

	}

	public void testGetDelegatedCredentialExpiredSigningCredential() {

		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "jdoe";
			GlobusCredential cred = ca.createCredential(alias);
			String gridIdentity = cred.getIdentity();

			boolean ok = false;
			int count = 1;
			int seconds = 2;
			while (!ok && (count <= PROXY_BUFFER_MAX_COUNT)) {
				DelegationRequest request = getSimpleDelegationRequest();
				DelegationSigningRequest req = dcm.initiateDelegation(
						gridIdentity, request);
				DelegationIdentifier id = req.getDelegationIdentifier();
				PublicKey publicKey = KeyUtil.loadPublicKey(req.getPublicKey()
                        .getKeyAsString());

				X509Certificate[] proxy = this.ca.createProxyCertifcates(alias,
						publicKey, 1, 0, 0, seconds);
				DelegationSigningResponse res = new DelegationSigningResponse();
				res.setDelegationIdentifier(id);
				res.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
						.toCertificateChain(proxy));
				dcm.approveDelegation(gridIdentity, res);
				KeyPair pair = KeyUtil.generateRSAKeyPair1024();
				org.cagrid.cds.model.PublicKey pKey = new org.cagrid.cds.model.PublicKey();
				pKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));
				Thread.sleep(((seconds * 1000) + 100));
				DelegationRecordFilter f = new DelegationRecordFilter();
				f.setDelegationIdentifier(id);
				f.setExpirationStatus(ExpirationStatus.EXPIRED);
				validateFind(dcm, f, 1);
				try {
					dcm.getDelegatedCredential(GRID_IDENTITY, id, pKey);
					fail("Should not be able get delegated credential.");
				} catch (DelegationException e) {
					if (e.getMessage().equals(
							Errors.SIGNING_CREDENTIAL_EXPIRED)) {
						ok = true;
					}
				}
				assertEquals(0, dcm.searchAuditLog(Utils
						.getIssuedAuditFilter(id)).size());
				List<DelegatedCredentialAuditRecord> ar = dcm.searchAuditLog(Utils
						.getAccessDeniedAuditFilter(id));
				assertEquals(1, ar.size());
				assertEquals(id, ar.get(0).getDelegationIdentifier());
				assertEquals(GRID_IDENTITY, ar.get(0).getSourceGridIdentity());
				assertEquals(
						DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ACCESS_DENIED,
						ar.get(0).getEvent());
				seconds = seconds * PROXY_BUFFER_TIME_MULTIPLIER;
				count = count+1;
			}

			if (!ok) {
				fail("Unable to validate testGetDelegatedCredentialSignatureCredentialAboutToExpire.");
			}

		} catch (Exception e) {
			
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}

	}

	public void testGetDelegatedCredentialInvalidStatus() {

		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "jdoe";
			GlobusCredential cred = ca.createCredential(alias);
			String gridIdentity = cred.getIdentity();
			DelegationRequest request = getSimpleDelegationRequest();
			DelegationSigningRequest req = dcm.initiateDelegation(gridIdentity,
					request);
			DelegationIdentifier id = req.getDelegationIdentifier();

			KeyPair pair = KeyUtil.generateRSAKeyPair1024();
			org.cagrid.cds.model.PublicKey pKey = new org.cagrid.cds.model.PublicKey();
			pKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));

			try {
				dcm.getDelegatedCredential(GRID_IDENTITY, id, pKey);
			} catch (DelegationException e) {
				if (!e.getMessage()
						.equals(Errors.CANNOT_GET_INVALID_STATUS)) {
					fail("Should not be able get delegated credential.");
				}
			}

			assertEquals(0,
					dcm.searchAuditLog(Utils.getIssuedAuditFilter(id)).size());
			List<DelegatedCredentialAuditRecord> ar = dcm.searchAuditLog(Utils
					.getAccessDeniedAuditFilter(id));
			assertEquals(1, ar.size());
			assertEquals(id, ar.get(0).getDelegationIdentifier());
			assertEquals(GRID_IDENTITY, ar.get(0).getSourceGridIdentity());
			assertEquals(
					DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ACCESS_DENIED,
					ar.get(0).getEvent());

			PublicKey publicKey = KeyUtil.loadPublicKey(req.getPublicKey()
                    .getKeyAsString());
			X509Certificate[] proxy = this.ca.createProxyCertifcates(alias,
					publicKey, 1);
			DelegationSigningResponse res = new DelegationSigningResponse();
			res.setDelegationIdentifier(id);
			res.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
					.toCertificateChain(proxy));

			dcm.approveDelegation(gridIdentity, res);

			List<DelegatedCredentialAuditRecord> ar2 = dcm.searchAuditLog(Utils
					.getApprovedAuditFilter(id));
			assertEquals(1, ar2.size());
			assertEquals(id, ar2.get(0).getDelegationIdentifier());
			assertEquals(gridIdentity, ar2.get(0).getSourceGridIdentity());
			assertEquals(DelegatedCredentialEvent.DELEGATION_APPROVED, ar2.get(0)
					.getEvent());

			dcm.getDelegatedCredential(GRID_IDENTITY, id, pKey);

			List<DelegatedCredentialAuditRecord> ar3 = dcm.searchAuditLog(Utils
					.getIssuedAuditFilter(id));
			assertEquals(1, ar3.size());
			assertEquals(id, ar3.get(0).getDelegationIdentifier());
			assertEquals(GRID_IDENTITY, ar3.get(0).getSourceGridIdentity());
			assertEquals(DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ISSUED,
					ar3.get(0).getEvent());

			dcm.updateDelegatedCredentialStatus(gridIdentity, id,
					DelegationStatus.SUSPENDED);

			List<DelegatedCredentialAuditRecord> ar4 = dcm.searchAuditLog(Utils
					.getUpdateStatusAuditFilter(id));
			assertEquals(1, ar4.size());
			assertEquals(id, ar4.get(0).getDelegationIdentifier());
			assertEquals(gridIdentity, ar4.get(0).getSourceGridIdentity());
			assertEquals(DelegatedCredentialEvent.DELEGATION_STATUS_UPDATED,
					ar4.get(0).getEvent());
			try {
				dcm.getDelegatedCredential(GRID_IDENTITY, id, pKey);
				fail("Should not be able get delegated credential.");
			} catch (DelegationException e) {
				if (!e.getMessage()
						.equals(Errors.CANNOT_GET_INVALID_STATUS)) {
					fail("Should not be able get delegated credential.");
				}
			}
			assertEquals(2, dcm.searchAuditLog(Utils
					.getAccessDeniedAuditFilter(id)).size());
			dcm.updateDelegatedCredentialStatus(gridIdentity, id,
					DelegationStatus.APPROVED);
			assertEquals(2, dcm.searchAuditLog(Utils
					.getUpdateStatusAuditFilter(id)).size());
			dcm.getDelegatedCredential(GRID_IDENTITY, id, pKey);
			assertEquals(2,
					dcm.searchAuditLog(Utils.getIssuedAuditFilter(id)).size());

		} catch (Exception e) {
			
			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}

	}

	public void testGetDelegatedCredentialSignatureCredentialAboutToExpire() {

		DelegatedCredentialManager dcm = null;
		int originalProxyExpiration = DelegatedCredentialManager.PROXY_EXPIRATION_BUFFER_SECONDS;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "jdoe";
			GlobusCredential cred = ca.createCredential(alias);
			String gridIdentity = cred.getIdentity();

			boolean ok = false;
			int count = 1;

			while (!ok && (count <= PROXY_BUFFER_MAX_COUNT)) {

				DelegationRequest request = getSimpleDelegationRequest();
				DelegationSigningRequest req = dcm.initiateDelegation(
						gridIdentity, request);
				DelegationIdentifier id = req.getDelegationIdentifier();

				KeyPair pair = KeyUtil.generateRSAKeyPair1024();
				org.cagrid.cds.model.PublicKey pKey = new org.cagrid.cds.model.PublicKey();
				pKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));

				PublicKey publicKey = KeyUtil.loadPublicKey(req.getPublicKey()
                        .getKeyAsString());

				X509Certificate[] proxy = this.ca
						.createProxyCertifcates(
								alias,
								publicKey,
								1,
								0,
								0,
								DelegatedCredentialManager.PROXY_EXPIRATION_BUFFER_SECONDS);
				DelegationSigningResponse res = new DelegationSigningResponse();
				res.setDelegationIdentifier(id);
				res.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
						.toCertificateChain(proxy));
				dcm.approveDelegation(gridIdentity, res);

				List<DelegatedCredentialAuditRecord> ar1 = dcm.searchAuditLog(Utils
						.getApprovedAuditFilter(id));
				assertEquals(1, ar1.size());
				assertEquals(id, ar1.get(0).getDelegationIdentifier());
				assertEquals(gridIdentity, ar1.get(0).getSourceGridIdentity());
				assertEquals(DelegatedCredentialEvent.DELEGATION_APPROVED,
						ar1.get(0).getEvent());

				try {
					dcm.getDelegatedCredential(GRID_IDENTITY, id, pKey);
					fail("Should not be able get delegated credential.");
				} catch (DelegationException e) {
					if (e.getMessage().equals(
							Errors.SIGNING_CREDENTIAL_ABOUT_EXPIRE)) {
						ok = true;
					} else if (!e.getMessage().equals(
							Errors.SIGNING_CREDENTIAL_EXPIRED)) {
						
						fail("Unexpected error encountered");
					}
				}

				List<DelegatedCredentialAuditRecord> ar2 = dcm.searchAuditLog(Utils
						.getAccessDeniedAuditFilter(id));
				assertEquals(1, ar2.size());
				assertEquals(id, ar2.get(0).getDelegationIdentifier());
				assertEquals(GRID_IDENTITY, ar2.get(0).getSourceGridIdentity());
				assertEquals(
						DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ACCESS_DENIED,
						ar2.get(0).getEvent());
				DelegatedCredentialManager.PROXY_EXPIRATION_BUFFER_SECONDS = DelegatedCredentialManager.PROXY_EXPIRATION_BUFFER_SECONDS
						* PROXY_BUFFER_TIME_MULTIPLIER;
				count = count + 1;
			}

			if (!ok) {
				fail("Unable to validate testGetDelegatedCredentialSignatureCredentialAboutToExpire.");
			}

		} catch (Exception e) {
			
			fail(e.getMessage());
		} finally {
			DelegatedCredentialManager.PROXY_EXPIRATION_BUFFER_SECONDS = originalProxyExpiration;
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}

	}

	// ///////////////////////////////////////////////////////////////////////////////
	/* LEFT OFF HERE */
	// ///////////////////////////////////////////////////////////////////////////////
	public void testGetDelegatedCredentialInvalidKeyLength() {

		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "jdoe";
			GlobusCredential cred = ca.createCredential(alias);
			String gridIdentity = cred.getIdentity();
			DelegationRequest request = getSimpleDelegationRequest();
			DelegationSigningRequest req = dcm.initiateDelegation(gridIdentity,
					request);
			DelegationIdentifier id = req.getDelegationIdentifier();

			KeyPair pair = KeyUtil.generateRSAKeyPair512();
			org.cagrid.cds.model.PublicKey pKey = new org.cagrid.cds.model.PublicKey();
			pKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));

			PublicKey publicKey = KeyUtil.loadPublicKey(req.getPublicKey()
                    .getKeyAsString());
			X509Certificate[] proxy = this.ca.createProxyCertifcates(alias,
					publicKey, 1);
			DelegationSigningResponse res = new DelegationSigningResponse();
			res.setDelegationIdentifier(id);
			res.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
					.toCertificateChain(proxy));
			dcm.approveDelegation(gridIdentity, res);

			List<DelegatedCredentialAuditRecord> ar1 = dcm.searchAuditLog(Utils
					.getApprovedAuditFilter(id));
			assertEquals(1, ar1.size());
			assertEquals(id, ar1.get(0).getDelegationIdentifier());
			assertEquals(gridIdentity, ar1.get(0).getSourceGridIdentity());
			assertEquals(DelegatedCredentialEvent.DELEGATION_APPROVED, ar1.get(0)
					.getEvent());

			try {
				dcm.getDelegatedCredential(GRID_IDENTITY, id, pKey);
				fail("Should not be able get delegated credential.");
			} catch (DelegationException e) {
				if (!e.getMessage().equals(
						Errors.INVALID_KEY_LENGTH_SPECIFIED)) {
					fail("Should not be able get delegated credential.");
				}
			}

			List<DelegatedCredentialAuditRecord> ar2 = dcm.searchAuditLog(Utils
					.getAccessDeniedAuditFilter(id));
			assertEquals(1, ar2.size());
			assertEquals(id, ar2.get(0).getDelegationIdentifier());
			assertEquals(GRID_IDENTITY, ar2.get(0).getSourceGridIdentity());
			assertEquals(
					DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ACCESS_DENIED,
					ar2.get(0).getEvent());

		} catch (Exception e) {

			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}

	}

	public void testGetDelegatedCredentialShorterThanNormalProxy() {

		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "jdoe";
			GlobusCredential cred = ca.createCredential(alias);
			String gridIdentity = cred.getIdentity();
			int hours = 0;
			int minutes = 0;
			int seconds = 120;
			DelegatedCredential dc = this.delegateAndValidate(dcm, alias,
					gridIdentity, null, hours, minutes, seconds);
			GlobusCredential delegatedProxy = getDelegatedCredentialAndValidate(
					dcm, GRID_IDENTITY, dc.getDelegationIdentifier(), dc
							.getSigningResponse().getCertificateChain());
			assertTrue((delegatedProxy.getTimeLeft() <= seconds));
		} catch (Exception e) {

			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}
	}

	public void testGetDelegatedCredential() {

		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "jdoe";
			GlobusCredential cred = ca.createCredential(alias);
			String gridIdentity = cred.getIdentity();
			int hours = 12;
			int minutes = 0;
			int seconds = 0;
			DelegatedCredential dc = this.delegateAndValidate(dcm, alias,
					gridIdentity, null, hours, minutes, seconds);
			GlobusCredential delegatedProxy = getDelegatedCredentialAndValidate(
					dcm, GRID_IDENTITY, dc.getDelegationIdentifier(), dc
							.getSigningResponse().getCertificateChain());
			assertTrue((delegatedProxy.getTimeLeft() <= DEFAULT_PROXY_LIFETIME_SECONDS));
			assertTrue((delegatedProxy.getTimeLeft() > (DEFAULT_PROXY_LIFETIME_SECONDS - 60)));
		} catch (Exception e) {

			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}

	}

	public void testFindCredentialsDelegatedToClient() {
		DelegatedCredentialManager dcm = null;
		try {
			dcm = Utils.getDelegatedCredentialManager();
			String alias = "jdoe";
			GlobusCredential jdoe = ca.createCredential(alias);
			GlobusCredential enemy = ca.createCredential("enemy");
			DelegatedCredential dc = this.delegateAndValidate(dcm, alias, jdoe
					.getIdentity(), null, 12, 0, 0);
			assertEquals(0, dcm.findCredentialsDelegatedToClient(enemy
					.getIdentity(), new ClientDelegationFilter()).size());
			List<DelegationRecord> records = dcm.findCredentialsDelegatedToClient(
					GRID_IDENTITY, null);
			assertEquals(1, records.size());
			assertEquals(dc.getDelegationIdentifier(), records.get(0)
					.getDelegationIdentifier());
			assertEquals(jdoe.getIdentity(), records.get(0).getGridIdentity());
		} catch (Exception e) {

			fail(e.getMessage());
		} finally {
			try {
				dcm.clearDatabase();
			} catch (Exception e) {
			}
		}

	}

	private GlobusCredential getDelegatedCredentialAndValidate(
			DelegatedCredentialManager dcm, String gridIdentity,
			DelegationIdentifier id, CertificateChain chain) throws Exception {
		X509Certificate[] signingChain = org.cagrid.cds.service.impl.util.Utils
				.toCertificateArray(chain);
		KeyPair pair = KeyUtil.generateRSAKeyPair1024();
        org.cagrid.cds.model.PublicKey pKey = new org.cagrid.cds.model.PublicKey();
		pKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));
		X509Certificate[] delegatedProxy = org.cagrid.cds.service.impl.util.Utils
				.toCertificateArray(dcm.getDelegatedCredential(GRID_IDENTITY,
						id, pKey));
		assertNotNull(delegatedProxy);
		assertEquals(delegatedProxy.length, (signingChain.length + 1));
		for (int i = 0; i < signingChain.length; i++) {
			assertEquals(signingChain[i], delegatedProxy[i + 1]);
		}

		ProxyPathValidator validator = new ProxyPathValidator();
		validator.validate(delegatedProxy, TrustedCertificates
				.getDefaultTrustedCertificates().getCertificates(),
				CertificateRevocationLists
						.getDefaultCertificateRevocationLists());

		List<DelegatedCredentialAuditRecord> ar = dcm.searchAuditLog(Utils
				.getIssuedAuditFilter(id));
		assertEquals(1, ar.size());
		assertEquals(id, ar.get(0).getDelegationIdentifier());
		assertEquals(GRID_IDENTITY, ar.get(0).getSourceGridIdentity());
		assertEquals(DelegatedCredentialEvent.DELEGATED_CREDENTIAL_ISSUED, ar.get(0)
				.getEvent());

		return new GlobusCredential(pair.getPrivate(), delegatedProxy);
	}

	protected DelegationRequest getSimpleDelegationRequest() {
		DelegationRequest req = new DelegationRequest();
		req.setDelegationPolicy(getSimplePolicy());
		req.setKeyLength(Constants.KEY_LENGTH);
		req.setIssuedCredentialPathLength(Constants.DELEGATION_PATH_LENGTH);
		ProxyLifetime lifetime = new ProxyLifetime();
		lifetime.setHours(0);
		lifetime.setMinutes(0);
		lifetime.setSeconds(DEFAULT_PROXY_LIFETIME_SECONDS);
		req.setIssuedCredentialLifetime(lifetime);
		return req;
	}

	protected IdentityDelegationPolicy getSimplePolicy() {
		IdentityDelegationPolicy policy = new IdentityDelegationPolicy();
		AllowedParties ap = new AllowedParties();
		ap.getGridIdentity().add(GRID_IDENTITY);
		policy.setAllowedParties(ap);
		return policy;
	}

	protected DelegatedCredential delegateAndValidate(
			DelegatedCredentialManager dcm, String alias, String gridIdentity,
			DelegationPolicy policy) throws Exception {
		return delegateAndValidate(dcm, alias, gridIdentity, policy, 12, 0, 0);
	}

	protected DelegatedCredential delegateAndValidate(
			DelegatedCredentialManager dcm, String alias, String gridIdentity,
			DelegationPolicy policy, int expected) throws Exception {
		return delegateAndValidate(dcm, alias, gridIdentity, policy, 12, 0, 0,
				expected);
	}

	protected DelegatedCredential delegateAndValidate(
			DelegatedCredentialManager dcm, String alias, String gridIdentity,
			DelegationPolicy policy, int hours, int minutes, int seconds)
			throws Exception {
		return delegateAndValidate(dcm, alias, gridIdentity, policy, hours,
				minutes, seconds, 0);

	}

	protected DelegatedCredential delegateAndValidate(
			DelegatedCredentialManager dcm, String alias, String gridIdentity,
			DelegationPolicy policy, int hours, int minutes, int seconds,
			int delegationCount) throws Exception {

		DelegationRecordFilter f = new DelegationRecordFilter();

		validateFind(dcm, f, delegationCount);

		DelegationRequest request = getSimpleDelegationRequest();
		if (policy != null) {
			request.setDelegationPolicy(policy);
		}
		DelegationSigningRequest req = dcm.initiateDelegation(gridIdentity,
				request);

		DelegationIdentifier id = req.getDelegationIdentifier();
		assertNotNull(id);
		assertTrue(dcm.delegationExists(id));
		DelegationRecord r = dcm.getDelegationRecord(id);
		assertEquals(id, r.getDelegationIdentifier());
		assertEquals(gridIdentity, r.getGridIdentity());
		assertEquals(0, r.getDateApproved());
		assertEquals(0, r.getExpiration());
		assertEquals(DelegationStatus.PENDING, r.getDelegationStatus());
		assertTrue((0 < r.getDateInitiated()));
		assertEquals(request.getIssuedCredentialPathLength(), r
				.getIssuedCredentialPathLength());
		assertEquals(request.getDelegationPolicy(), r.getDelegationPolicy());
		assertNotNull(r.getCertificateChain());
		assertEquals(0, r.getCertificateChain().getX509Certificate().size());

		// Validate Auditing
		List<DelegatedCredentialAuditRecord> ar1 = dcm.searchAuditLog(Utils
				.getInitiatedAuditFilter(id));
		assertEquals(1, ar1.size());
		assertEquals(id, ar1.get(0).getDelegationIdentifier());
		assertEquals(DelegatedCredentialEvent.DELEGATION_INITIATED, ar1.get(0)
				.getEvent());
		assertEquals(gridIdentity, ar1.get(0).getSourceGridIdentity());

		// Validate Find Operation

		validateFind(dcm, f, (delegationCount + 1));
		resetFilter(f);
		f.setDelegationIdentifier(req.getDelegationIdentifier());
		validateFind(dcm, f, 1);
		resetFilter(f);
		f.setGridIdentity(gridIdentity);
		validateFind(dcm, f, (delegationCount + 1));
		resetFilter(f);
		f.setExpirationStatus(ExpirationStatus.VALID);
		validateFind(dcm, f, (delegationCount));
		resetFilter(f);
		f.setExpirationStatus(ExpirationStatus.EXPIRED);
		validateFind(dcm, f, 0);
		resetFilter(f);
		f.setDelegationStatus(DelegationStatus.SUSPENDED);
		validateFind(dcm, f, 0);
		resetFilter(f);
		f.setDelegationStatus(DelegationStatus.PENDING);
		validateFind(dcm, f, 1);
		resetFilter(f);
		f.setDelegationStatus(DelegationStatus.APPROVED);
		validateFind(dcm, f, delegationCount);
		resetFilter(f);
		f.setDelegationIdentifier(req.getDelegationIdentifier());
		f.setGridIdentity(gridIdentity);
		f.setDelegationStatus(DelegationStatus.PENDING);
		validateFind(dcm, f, 1);

		PublicKey publicKey = KeyUtil.loadPublicKey(req.getPublicKey()
                .getKeyAsString());
		X509Certificate[] proxy = this.ca.createProxyCertifcates(alias,
				publicKey, 1, hours, minutes, seconds);
		assertNotNull(proxy);
		DelegationSigningResponse res = new DelegationSigningResponse();
		res.setDelegationIdentifier(id);
		res.setCertificateChain(org.cagrid.cds.service.impl.util.Utils
				.toCertificateChain(proxy));
		dcm.approveDelegation(gridIdentity, res);

		DelegationRecord r2 = dcm.getDelegationRecord(id);
		assertEquals(gridIdentity, r2.getGridIdentity());
		assertTrue(0 < r2.getDateApproved());
		assertEquals(org.cagrid.cds.service.impl.util.Utils.getEarliestExpiration(
				proxy).getTime(), r2.getExpiration());
		assertEquals(DelegationStatus.APPROVED, r2.getDelegationStatus());
		assertEquals(r.getDateInitiated(), r2.getDateInitiated());
		assertEquals(r.getIssuedCredentialPathLength(), r2
				.getIssuedCredentialPathLength());
		assertEquals(request.getDelegationPolicy(), r2.getDelegationPolicy());
		X509Certificate[] chain = org.cagrid.cds.service.impl.util.Utils
				.toCertificateArray(r2.getCertificateChain());
		assertNotNull(chain);
		assertEquals(proxy.length, chain.length);
		for (int i = 0; i < proxy.length; i++) {
			assertEquals(proxy[i], chain[i]);
		}

		// Validate Auditing
		List<DelegatedCredentialAuditRecord> ar2 = dcm.searchAuditLog(Utils
				.getApprovedAuditFilter(id));
		assertEquals(1, ar2.size());
		assertEquals(id, ar2.get(0).getDelegationIdentifier());
		assertEquals(DelegatedCredentialEvent.DELEGATION_APPROVED, ar2.get(0)
				.getEvent());
		assertEquals(gridIdentity, ar2.get(0).getSourceGridIdentity());

		// Validate Find Operation
		resetFilter(f);
		validateFind(dcm, f, (delegationCount + 1));
		resetFilter(f);
		f.setDelegationIdentifier(req.getDelegationIdentifier());
		validateFind(dcm, f, 1);
		resetFilter(f);
		f.setGridIdentity(gridIdentity);
		validateFind(dcm, f, (delegationCount + 1));
		resetFilter(f);
		f.setExpirationStatus(ExpirationStatus.VALID);
		validateFind(dcm, f, (delegationCount + 1));
		resetFilter(f);
		f.setExpirationStatus(ExpirationStatus.EXPIRED);
		validateFind(dcm, f, 0);
		resetFilter(f);
		f.setDelegationStatus(DelegationStatus.SUSPENDED);
		validateFind(dcm, f, 0);
		resetFilter(f);
		f.setDelegationStatus(DelegationStatus.PENDING);
		validateFind(dcm, f, 0);
		resetFilter(f);
		f.setDelegationStatus(DelegationStatus.APPROVED);
		validateFind(dcm, f, delegationCount + 1);
		resetFilter(f);
		f.setDelegationIdentifier(req.getDelegationIdentifier());
		f.setGridIdentity(gridIdentity);
		f.setDelegationStatus(DelegationStatus.APPROVED);
		validateFind(dcm, f, 1);

		return new DelegatedCredential(req, res);
	}

	protected void resetFilter(DelegationRecordFilter f) throws Exception {
		f.setDelegationIdentifier(null);
		f.setGridIdentity(null);
		f.setDelegationStatus(null);
		f.setExpirationStatus(null);
	}

	protected void validateFind(DelegatedCredentialManager dcm,
			DelegationRecordFilter f, int expectedCount) throws Exception {
		List<DelegationRecord> records = dcm.findDelegatedCredentials(f);
		assertEquals(expectedCount, records.size());
		if (f.getDelegationIdentifier() != null) {
			for (int i = 0; i < records.size(); i++) {
				assertEquals(f.getDelegationIdentifier(), records.get(i)
						.getDelegationIdentifier());
			}
		}

		if (f.getGridIdentity() != null) {
			for (int i = 0; i < records.size(); i++) {
				assertEquals(f.getGridIdentity(), records.get(i).getGridIdentity());
			}
		}

		if (f.getDelegationStatus() != null) {
			for (int i = 0; i < records.size(); i++) {
				assertEquals(f.getDelegationStatus(), records.get(i)
						.getDelegationStatus());
			}
		}
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

	public class InvalidKeyManager implements KeyManager {

		public KeyPair createAndStoreKeyPair(String alias, int keyLength)
				throws CDSInternalException {
			return null;
		}

		public void delete(String alias) throws CDSInternalException {

		}

		public void deleteAll() throws CDSInternalException {

		}

		public boolean exists(String alias) throws CDSInternalException {
			return false;
		}

		public X509Certificate[] getCertificates(String alias)
				throws CDSInternalException {
			return null;
		}

		public PrivateKey getPrivateKey(String alias) throws CDSInternalException {
			return null;
		}

		public PublicKey getPublicKey(String alias) throws CDSInternalException {
			return null;
		}

		public void storeCertificates(String alias, X509Certificate[] cert)
				throws CDSInternalException, DelegationException {

		}
		
		public String getName() {
		    return "Invalid Key Manager";
		}

	}

}
