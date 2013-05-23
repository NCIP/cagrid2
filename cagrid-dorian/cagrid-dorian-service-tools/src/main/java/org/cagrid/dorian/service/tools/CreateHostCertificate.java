package org.cagrid.dorian.service.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Properties;

import org.cagrid.core.commandline.BaseCommandLine;
import org.cagrid.dorian.ca.impl.CertificateAuthorityProperties;
import org.cagrid.dorian.federation.impl.IdentityAssignmentPolicy;
import org.cagrid.dorian.ifs.HostCertificateRecord;
import org.cagrid.dorian.ifs.HostCertificateRequest;
import org.cagrid.dorian.ifs.PublicKey;
import org.cagrid.dorian.service.Dorian;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;

public class CreateHostCertificate extends BaseCommandLine {

	public static final String PROPERTIES_FILE = "src/main/resources/host-certificate-request.properties";
	public static final String HOSTNAME_PROMPT = "Please enter a hostname";
	public static final String HOSTNAME_PROPERTY = "org.cagrid.dorian.cert.request.hostname";
	public static final String ADMIN_USER_ID_PROMPT = "Please enter the user id of admin";
	public static final String ADMIN_USER_ID_PROPERTY = "org.cagrid.dorian.admin.user.id";
	public static final String KEYSTORE_ALIAS_PROMPT = "Please enter a alias for the keystore";
	public static final String KEYSTORE_ALIAS_PROPERTY = "org.cagrid.dorian.keystore.alias";
	public static final String KEYSTORE_PASSWORD_PROMPT = "Please enter a password for the keystore";
	public static final String KEYSTORE_PASSWORD_PROPERTY = "org.cagrid.dorian.keystore.password";
	public static final String KEY_PASSWORD_PROMPT = "Please enter a password for the private key";
	public static final String KEY_PASSWORD_PROPERTY = "org.cagrid.dorian.key.password";

	public CreateHostCertificate(File propertiesFile) {
		super(propertiesFile);
	}

	public CreateHostCertificate(Properties properties) {
		super(properties);
	}

	@Override
	public void execute() throws Exception {

		SpringUtils utils = new SpringUtils();
		Dorian dorian = utils.getDorian();
		String hostname = getValue(HOSTNAME_PROMPT, HOSTNAME_PROPERTY);
		String userId = getValue(ADMIN_USER_ID_PROMPT, ADMIN_USER_ID_PROPERTY);
		CertificateAuthorityProperties caProperties = utils
				.getCertificateAuthorityProperties();
		KeyPair pair = KeyUtil.generateRSAKeyPair(caProperties
				.getIssuedCertificateKeySize());
		X509Certificate cacert = dorian.getCACertificate();
		String caSubject = cacert.getSubjectDN().getName();
		int index = caSubject.lastIndexOf(",");
		String subjectPrefix = caSubject.substring(0, index);

		String gridId = null;
		if (utils.getIdentityFederationProperties()
				.getIdentityAssignmentPolicy()
				.equals(IdentityAssignmentPolicy.NAME)) {
			gridId = CertUtil.subjectToIdentity(subjectPrefix + ",OU="
					+ utils.getIdentityProviderProperties().getName() + "/CN="
					+ userId);
		} else {
			gridId = CertUtil.subjectToIdentity(subjectPrefix
					+ ",OU=IdP [1]/CN=" + userId);
		}
		System.out.println(gridId);
		HostCertificateRequest req = new HostCertificateRequest();
		req.setHostname(hostname);
		PublicKey publicKey = new PublicKey();
		publicKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));
		req.setPublicKey(publicKey);
		HostCertificateRecord record = dorian.requestHostCertificate(gridId,
				req);
		X509Certificate cert = CertUtil.loadCertificate(record.getCertificate()
				.getCertificateAsString());
		System.out.println("Successfully created the host certificate:");
		System.out.println("Subject: " + cert.getSubjectDN());
		System.out.println("Created: " + cert.getNotBefore());
		System.out.println("Expires: " + cert.getNotAfter());

		String keyStorePassword = getValue(KEYSTORE_PASSWORD_PROMPT,
				KEYSTORE_PASSWORD_PROPERTY);
		String keyPassword = getValue(KEY_PASSWORD_PROMPT,
				KEY_PASSWORD_PROPERTY);
		String keyStoreAlias = getValue(KEYSTORE_ALIAS_PROMPT,
				KEYSTORE_ALIAS_PROPERTY);

		KeyStore hks = KeyStore.getInstance("jks");
		hks.load(null);
		java.security.cert.Certificate[] hostCertChain = { cert };
		hks.setKeyEntry(keyStoreAlias, pair.getPrivate(),
				keyPassword.toCharArray(), hostCertChain);
		File keyStoreFile = new File(hostname + ".jks");
		FileOutputStream out = new FileOutputStream(keyStoreFile);
		hks.store(out, keyStorePassword.toCharArray());
		out.close();
		System.out.println("Keystore created for " + cert.getSubjectDN()
				+ " at " + keyStoreFile);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CreateHostCertificate main = new CreateHostCertificate(new File(
					PROPERTIES_FILE));
			main.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
