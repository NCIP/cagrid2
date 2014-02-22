package org.cagrid.dorian.service.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import org.cagrid.dorian.model.federation.HostCertificateRecord;
import org.cagrid.dorian.model.federation.HostCertificateRequest;
import org.cagrid.dorian.model.federation.PublicKey;
import org.cagrid.dorian.service.Dorian;
import org.cagrid.dorian.service.ca.CertificateAuthorityProperties;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;

public class KeyStoreCreator {

	public KeyStoreCreator() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String keyStorePassword = "changeit";
			String keyPassword = "changeit";
			String keyStoreAlias = "host";
			File keyStoreFile = new File("/Users/langella/Desktop/legacy-cds-host.jks");
			PrivateKey key = KeyUtil.loadPrivateKey(new File("/Users/langella/Documents/caGrid/environments/Training/training-grid-configuration/Services/cds.training.cagrid.org/cds.training.cagrid.org-key.pem"), null);
			X509Certificate cert = CertUtil.loadCertificate(new File("/Users/langella/Documents/caGrid/environments/Training/training-grid-configuration/Services/cds.training.cagrid.org/cds.training.cagrid.org-cert.pem"));
			KeyStore hks = KeyStore.getInstance("jks");
			hks.load(null);
			java.security.cert.Certificate[] hostCertChain = { cert };
			hks.setKeyEntry(keyStoreAlias, key, keyPassword.toCharArray(), hostCertChain);

			FileOutputStream out = new FileOutputStream(keyStoreFile);
			hks.store(out, keyStorePassword.toCharArray());
			out.close();
			System.out.println("Keystore created for " + cert.getSubjectDN() + " at " + keyStoreFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
