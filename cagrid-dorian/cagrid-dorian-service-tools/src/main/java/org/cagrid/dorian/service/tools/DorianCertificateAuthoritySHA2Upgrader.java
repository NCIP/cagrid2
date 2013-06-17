package org.cagrid.dorian.service.tools;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import org.bouncycastle.asn1.x509.X509Name;
import org.cagrid.dorian.ca.impl.CertificateAuthority;
import org.cagrid.dorian.ca.impl.CertificateAuthorityProperties;
import org.cagrid.dorian.ca.impl.CredentialsManager;
import org.cagrid.gaards.pki.CertUtil;

public class DorianCertificateAuthoritySHA2Upgrader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			BootstrapperSpringUtils utils = new BootstrapperSpringUtils();
			CertificateAuthorityProperties props = utils.getCertificateAuthorityProperties();
			CertificateAuthority ca = utils.getCertificateAuthority();
			X509Certificate cert = ca.getCACertificate();
			PrivateKey key = ca.getPrivateKey();
			KeyPair pair = new KeyPair(cert.getPublicKey(), key);
			X509Certificate cacert = CertUtil.generateCACertificate("BC", new X509Name(cert.getSubjectDN().getName()), cert.getNotBefore(), cert.getNotAfter(), pair, "SHA256WITHRSAENCRYPTION");
			System.out.println(cacert.getSubjectDN().getName());
			String certStr = CertUtil.writeCertificate(cacert);
			utils.getDatabase().update("update " + CredentialsManager.CREDENTIALS_TABLE + " set CERTIFICATE='" + certStr + "'");

			// ca.setCACredentials(cacert, key,
			// props.getCertificateAuthorityPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
