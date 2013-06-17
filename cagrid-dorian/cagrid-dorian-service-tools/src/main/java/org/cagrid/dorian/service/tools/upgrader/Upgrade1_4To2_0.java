package org.cagrid.dorian.service.tools.upgrader;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import org.bouncycastle.asn1.x509.X509Name;
import org.cagrid.dorian.ca.impl.CertificateAuthority;
import org.cagrid.dorian.ca.impl.CertificateAuthorityProperties;
import org.cagrid.dorian.ca.impl.CredentialsManager;
import org.cagrid.dorian.federation.impl.AutoApprovalPolicy;
import org.cagrid.dorian.federation.impl.ManualApprovalPolicy;
import org.cagrid.dorian.federation.impl.TrustedIdPManager;
import org.cagrid.dorian.service.PropertyManager;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.tools.database.Database;

public class Upgrade1_4To2_0 extends Upgrade {

	private boolean upgradeCA = false;

	public String getStartingVersion() {
		return PropertyManager.DORIAN_VERSION_1_4;
	}

	public String getUpgradedVersion() {
		return PropertyManager.DORIAN_VERSION_2_0;
	}

	public void upgrade(boolean trialRun) throws Exception {
		Database db = getBeanUtils().getDatabase();
		db.createDatabaseIfNeeded();
		PropertyManager pm = new PropertyManager(db);
		if (pm.getVersion().equals(PropertyManager.DORIAN_VERSION_1_4)) {
			if (!trialRun) {
				System.out.print("Upgrading version number from " + pm.getVersion() + " to " + PropertyManager.DORIAN_VERSION_2_0 + "....");
				pm.setVersion(PropertyManager.DORIAN_VERSION_2_0);
				System.out.println(" COMPLETED.");
			} else {
				System.out.println("The version needs to be upgraded from " + pm.getVersion() + " to " + PropertyManager.DORIAN_VERSION_2_0 + ".");
			}
			modifyTrustManagerTable(trialRun);
			upgradeCertificateAuthorities(trialRun);
		} else {
			if (!trialRun) {
				throw new Exception("Failed to run upgrader " + getClass().getName() + " the version of Dorian you are running is not " + PropertyManager.DORIAN_VERSION_1_3 + ".");
			}
		}

	}

	public boolean isUpgradeCA() {
		return upgradeCA;
	}

	public void setUpgradeCA(boolean upgradeCA) {
		this.upgradeCA = upgradeCA;
	}

	private void modifyTrustManagerTable(boolean trialRun) throws Exception {
		if (!trialRun) {
			String sql = "ALTER TABLE " + TrustedIdPManager.TRUST_MANAGER_TABLE + " MODIFY " + TrustedIdPManager.AUTHENTICATION_SERVICE_URL_FIELD + " TEXT null";
			System.out.print("Altering the Trust Manager table (" + sql + ")....");
			try {
				getBeanUtils().getDatabase().update(sql);
				System.out.println("COMPLETED");
			} catch (Exception e) {
				System.out.println("FAILED");
				throw e;
			}

			String sql2 = "ALTER TABLE " + TrustedIdPManager.TRUST_MANAGER_TABLE + " MODIFY " + TrustedIdPManager.AUTHENTICATION_SERVICE_IDENTITY_FIELD + " TEXT null";
			System.out.print("Altering the Trust Manager table (" + sql2 + ")....");
			try {
				getBeanUtils().getDatabase().update(sql2);
				System.out.println("COMPLETED");
			} catch (Exception e) {
				System.out.println("FAILED");
				throw e;
			}

			String sql3 = "UPDATE " + TrustedIdPManager.TRUST_MANAGER_TABLE + " SET " + TrustedIdPManager.POLICY_CLASS_FIELD + " = '" + AutoApprovalPolicy.class.getName() + "' where "
					+ TrustedIdPManager.POLICY_CLASS_FIELD + " = 'org.cagrid.gaards.dorian.federation.AutoApprovalPolicy'";
			System.out.print("Altering the Trust Manager table (" + sql3 + ")....");
			try {
				getBeanUtils().getDatabase().update(sql3);
				System.out.println("COMPLETED");
			} catch (Exception e) {
				System.out.println("FAILED");
				throw e;
			}

			String sql4 = "UPDATE " + TrustedIdPManager.TRUST_MANAGER_TABLE + " SET " + TrustedIdPManager.POLICY_CLASS_FIELD + " = '" + ManualApprovalPolicy.class.getName() + "' where "
					+ TrustedIdPManager.POLICY_CLASS_FIELD + " = 'org.cagrid.gaards.dorian.federation.ManualApprovalPolicy'";
			System.out.print("Altering the Trust Manager table (" + sql4 + ")....");
			try {
				getBeanUtils().getDatabase().update(sql4);
				System.out.println("COMPLETED");
			} catch (Exception e) {
				System.out.println("FAILED");
				throw e;
			}

		}
	}

	private void upgradeCertificateAuthorities(boolean trialRun) throws Exception {
		if (isUpgradeCA()) {
			System.out.println("Upgrading certificate authority to SHA2 and archiving legacy CA.....");
			CertificateAuthorityProperties legacyCAProperties = getBeanUtils().getLegacyCertificateAuthorityProperties();
			CertificateAuthorityProperties caProperties = getBeanUtils().getCertificateAuthorityProperties();
			CertificateAuthority ca = getBeanUtils().getCertificateAuthority();
			X509Certificate legacyCert = ca.getCACertificate();
			PrivateKey key = ca.getPrivateKey();
			if (!trialRun) {
				String legacyAlias = "legacyca";
				String legacyCertStr = CertUtil.writeCertificate(legacyCert);
				String legacyKeyStr = KeyUtil.writePrivateKey(key, legacyCAProperties.getCertificateAuthorityPassword());
				getBeanUtils().getDatabase().update(
						"INSERT INTO " + CredentialsManager.CREDENTIALS_TABLE + " SET ALIAS= '" + legacyAlias + "', SERIAL_NUMBER=" + legacyCert.getSerialNumber() + ",CERTIFICATE='" + legacyCertStr
								+ "', PRIVATE_KEY='" + legacyKeyStr + "'");

				System.out.println("Successfully archived the legacy CA " + legacyCert.getSubjectDN().getName() + " under the alias " + legacyAlias + ".");

				KeyPair pair = new KeyPair(legacyCert.getPublicKey(), key);

				// TODO: Decide on the dates for the upgrade
				X509Certificate cacert = CertUtil.generateCACertificate("BC", new X509Name(caProperties.getCreationPolicy().getSubject()), legacyCert.getNotBefore(), legacyCert.getNotAfter(), pair,
						CertUtil.SHA2_SIGNATURE_ALGORITHM);
				System.out.println(cacert.getSubjectDN().getName());
				String certStr = CertUtil.writeCertificate(cacert);
				getBeanUtils().getDatabase().update(
						"update " + CredentialsManager.CREDENTIALS_TABLE + " set CERTIFICATE='" + certStr + "', SERIAL_NUMBER=" + cacert.getSerialNumber() + " where alias = 'dorianca'");
				System.out.println("Created new SHA2 certificate authority " + cacert.getSubjectDN().getName() + ", it is valid from " + cacert.getNotBefore() + " to  " + cacert.getNotAfter() + ".");
				System.out.println("Successfully upgraded the certificate authority to SHA2 and archived the legacy CA.");
			}
		}
	}
}