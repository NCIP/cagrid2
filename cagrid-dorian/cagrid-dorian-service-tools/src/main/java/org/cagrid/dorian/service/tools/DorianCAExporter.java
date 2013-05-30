package org.cagrid.dorian.service.tools;

import java.io.File;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import org.cagrid.dorian.ca.impl.CertificateAuthority;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;

public class DorianCAExporter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SpringUtils utils = new SpringUtils();
			CertificateAuthority ca = utils.getCertificateAuthority();
			X509Certificate cert = ca.getCACertificate();
			PrivateKey key = ca.getPrivateKey();
			CertUtil.writeCertificate(cert, new File("dorian-ca.cert"));
			KeyUtil.writePrivateKey(key, new File("dorian-ca.key"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
