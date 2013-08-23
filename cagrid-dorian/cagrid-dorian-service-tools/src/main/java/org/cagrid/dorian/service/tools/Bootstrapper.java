package org.cagrid.dorian.service.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

import org.cagrid.core.commandline.BaseCommandLine;
import org.cagrid.dorian.model.federation.HostCertificateRecord;
import org.cagrid.dorian.model.federation.HostCertificateRequest;
import org.cagrid.dorian.model.federation.PublicKey;
import org.cagrid.dorian.service.CertificateSignatureAlgorithm;
import org.cagrid.dorian.service.ca.CertificateAuthority;
import org.cagrid.dorian.service.ca.CertificateAuthorityManager;
import org.cagrid.dorian.service.ca.CertificateAuthorityProperties;
import org.cagrid.dorian.service.core.DorianImpl;
import org.cagrid.dorian.service.core.DorianProperties;
import org.cagrid.dorian.service.federation.IdentityAssignmentPolicy;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;

public class Bootstrapper extends BaseCommandLine {

	public static final String PROPERTIES_FILE = "src/main/resources/bootstrapper.properties";
	private static final String DORIAN_SERVICE_DIR = "cagrid-dorian";
	private static final String DORIAN_SERVICE_CFG = "cagrid.dorian.service.cfg";
	private static final String DORIAN_WSRF_CFG = "cagrid.dorian.wsrf.cfg";
	public static final String TRUST_CA_SHA1_PROMPT = "Please enter the location of the SHA1 trust fabric CA certificate";
	public static final String TRUST_CA_SHA1_PROPERTY = "cagrid.dorian.trust.ca.sha1.cert.location";
	public static final String TRUST_CA_SHA2_PROMPT = "Please enter the location of the SHA2 trust fabric CA certificate";
	public static final String TRUST_CA_SHA2_PROPERTY = "cagrid.dorian.trust.ca.sha2.cert.location";

	public static final String WSRF_HOSTNAME_PROMPT = "Please enter a hostname";
	public static final String WSRF_HOSTNAME_PROPERTY = "org.cagrid.dorian.wsrf.hostname";
	public static final String ADMIN_USER_ID_PROMPT = "Please enter the user id of admin";
	public static final String ADMIN_USER_ID_PROPERTY = "org.cagrid.dorian.admin.user.id";
	public static final String KEYSTORE_ALIAS_PROMPT = "Please enter a alias for the keystore";
	public static final String KEYSTORE_ALIAS_PROPERTY = "org.cagrid.dorian.keystore.alias";
	public static final String KEYSTORE_PASSWORD_PROMPT = "Please enter a password for the keystore";
	public static final String KEYSTORE_PASSWORD_PROPERTY = "org.cagrid.dorian.keystore.password";
	public static final String KEY_PASSWORD_PROMPT = "Please enter a password for the private key";
	public static final String KEY_PASSWORD_PROPERTY = "org.cagrid.dorian.key.password";
	public static final String KEYSTORE_FILE_NAME = "dorian-host.jks";
	public static final String TRUSTSTORE_FILE_NAME = "truststore.jks";
	public static final String TRUSTSTORE_ALIAS_PROMPT = "Please enter a alias for the truststore";
	public static final String TRUSTSTORE_PASSWORD_PROMPT = "Please enter a password for the truststore";
	public static final String TRUSTSTORE_PASSWORD_PROPERTY = "org.cagrid.dorian.truststore.password";
	public static final String WSRF_KEYSTORE_PATH_PROPERTY = "cagrid.dorian.wsrf.keystore.path";
	public static final String WSRF_KEYSTORE_PATH = "${karaf.base}/etc/" + DORIAN_SERVICE_DIR + "/" + KEYSTORE_FILE_NAME;
	public static final String WSRF_KEYSTORE_PASSWORD_PROPERTY = "cagrid.dorian.wsrf.keystore.password";
	public static final String WSRF_KEYSTORE_ALIAS_PROPERTY = "cagrid.dorian.wsrf.keystore.alias";
	public static final String WSRF_KEY_PASSWORD_PROPERTY = "cagrid.dorian.wsrf.key.password";
	public static final String WSRF_TRUSTSTORE_PATH_PROPERTY = "cagrid.dorian.wsrf.truststore.path";
	public static final String WSRF_TRUSTSTORE_PATH = "${karaf.base}/etc/" + DORIAN_SERVICE_DIR + "/" + TRUSTSTORE_FILE_NAME;
	public static final String WSRF_TRUSTSTORE_PASSWORD_PROPERTY = "cagrid.dorian.wsrf.truststore.password";
	public static final String WSRF_TRUSTED_IDP_MAPPING_PROPERTY = "cagrid.dorian.wsrf.trusted.idp.mapping";
	public static final String WSRF_URL_PROPERTY = "cagrid.dorian.wsrf.url";
	public static final String WSRF_PORT_PROMPT = "Enter a port number for the WSRF service";
	public static final String WSRF_PORT_PROPERTY = "cagrid.dorian.wsrf.port";

	public static final String CONFIGURE_LEGACY_WSRF_PROMPT = "Do you want to configure a Legacy WSRF Endpoint (true|false)";
	public static final String CONFIGURE_LEGACY_WSRF_PROPERTY = "org.cagrid.dorian.configure.legacy.wsrf";

	public static final String LEGACY_WSRF_HOSTNAME_PROMPT = "Please enter a legacy hostname";
	public static final String LEGACY_WSRF_HOSTNAME_PROPERTY = "org.cagrid.dorian.legacy-wsrf.hostname";
	public static final String LEGACY_CERTIFICATE_PROMPT = "Please enter the location of the legacy host certificate";
	public static final String LEGACY_CERTIFICATE_PROPERTY = "org.cagrid.dorian.legacy-certificate.location";
	public static final String LEGACY_KEY_PROMPT = "Please enter the location of the legacy private key";
	public static final String LEGACY_KEY_PROPERTY = "org.cagrid.dorian.legacy-key.location";
	public static final String LEGACY_KEYSTORE_ALIAS_PROMPT = "Please enter a alias for the legacy keystore";
	public static final String LEGACY_KEYSTORE_ALIAS_PROPERTY = "org.cagrid.dorian.legacy-keystore.alias";
	public static final String LEGACY_KEYSTORE_PASSWORD_PROMPT = "Please enter a password for the legacy keystore";
	public static final String LEGACY_KEYSTORE_PASSWORD_PROPERTY = "org.cagrid.dorian.legacy-keystore.password";
	public static final String LEGACY_KEY_PASSWORD_PROMPT = "Please enter a password for the legacy private key";
	public static final String LEGACY_KEY_PASSWORD_PROPERTY = "org.cagrid.dorian.legacy-key.password";
	public static final String LEGACY_KEYSTORE_FILE_NAME = "legacy-dorian-host.jks";
	public static final String LEGACY_WSRF_KEYSTORE_PATH_PROPERTY = "cagrid.dorian.legacy-wsrf.keystore.path";
	public static final String LEGACY_WSRF_KEYSTORE_PATH = "${karaf.base}/etc/" + DORIAN_SERVICE_DIR + "/" + LEGACY_KEYSTORE_FILE_NAME;
	public static final String LEGACY_WSRF_KEYSTORE_PASSWORD_PROPERTY = "cagrid.dorian.legacy-wsrf.keystore.password";
	public static final String LEGACY_WSRF_KEYSTORE_ALIAS_PROPERTY = "cagrid.dorian.legacy-wsrf.keystore.alias";
	public static final String LEGACY_WSRF_KEY_PASSWORD_PROPERTY = "cagrid.dorian.legacy-wsrf.key.password";
	public static final String LEGACY_WSRF_TRUSTSTORE_PATH_PROPERTY = "cagrid.dorian.legacy-wsrf.truststore.path";
	public static final String LEGACY_WSRF_TRUSTSTORE_PATH = "${karaf.base}/etc/" + DORIAN_SERVICE_DIR + "/" + TRUSTSTORE_FILE_NAME;
	public static final String LEGACY_WSRF_TRUSTSTORE_PASSWORD_PROPERTY = "cagrid.dorian.legacy-wsrf.truststore.password";
	public static final String LEGACY_WSRF_TRUSTED_IDP_MAPPING_PROPERTY = "cagrid.dorian.legacy-wsrf.trusted.idp.mapping";
	public static final String LEGACY_WSRF_URL_PROPERTY = "cagrid.dorian.legacy-wsrf.url";
	public static final String LEGACY_WSRF_PORT_PROMPT = "Enter a port number for the legacy WSRF service";
	public static final String LEGACY_WSRF_PORT_PROPERTY = "cagrid.dorian.legacy-wsrf.port";

	public static final String DORIAN_CLIENT_KEYSTORE_FILE_PROPERTY = "cagrid.dorian.service.client.keystore.file";
	public static final String DORIAN_CLIENT_KEYSTORE_PASSWORD_PROPERTY = "cagrid.dorian.service.client.keystore.password";
	public static final String DORIAN_CLIENT_KEY_ALIAS_PROPERTY = "cagrid.dorian.service.client.key.alias";
	public static final String DORIAN_CLIENT_KEY_PASSWORD_PROPERTY = "cagrid.dorian.service.client.key.password";
	public static final String DORIAN_CLIENT_TRUSTSTORE_FILE_PROPERTY = "cagrid.dorian.service.client.truststore.file";
	public static final String DORIAN_CLIENT_TRUSTSTORE_PASSWORD_PROPERTY = "cagrid.dorian.service.client.truststore.password";

	public static final String DORIAN_DB_NAME_PROMPT = "Please enter the name of the database";
	public static final String DORIAN_DB_NAME_PROPERTY = "cagrid.dorian.service.name";
	public static final String DORIAN_DB_USER_PROMPT = "Please enter the database username";
	public static final String DORIAN_DB_USER_PROPERTY = "cagrid.dorian.service.db.user";
	public static final String DORIAN_DB_PASSWORD_PROMPT = "Please enter the database password";
	public static final String DORIAN_DB_PASSWORD_PROPERTY = "cagrid.dorian.service.db.password";
	public static final String DORIAN_DB_HOST_PROMPT = "Please enter the database hostname";
	public static final String DORIAN_DB_HOST_PROPERTY = "cagrid.dorian.service.db.host";
	public static final String DORIAN_DB_PORT_PROMPT = "Please enter the database port";
	public static final String DORIAN_DB_PORT_PROPERTY = "cagrid.dorian.service.db.port";
	public static final String DORIAN_CRL_PUBLISH_PROMPT = "Please enter the URL of the GTS Dorian should publish its CRL to";
	public static final String DORIAN_CRL_PUBLISH_PROPERTY = "cagrid.dorian.service.federation.crl.publish";

	public static final String DORIAN_CA_SUBJECT_PROPERTY = "cagrid.dorian.service.ca.auto.create.subject";
	public static final String LEGACY_DORIAN_CA_SUBJECT_PROPERTY = "cagrid.dorian.service.legacy-ca.auto.create.subject";

	private String adminIdentity;
	private String keystorePassword;
	private String hostname;
	private String legacyHostname;
	private String keystoreAlias;
	private String keyPassword;
	private String truststorePassword;
	private File dorianEtcDir;
	private BootstrapperSpringUtils utils;
	private Boolean configureLegacyWSRF;
	private X509Certificate hostCertificate;
	private X509Certificate legacyHostCertificate;

	private String legacyKeystorePassword;
	private String legacyKeystoreAlias;
	private String legacyKeyPassword;

	public Bootstrapper(File propertiesFile) throws Exception {
		super(propertiesFile);
		utils = new BootstrapperSpringUtils();
	}

	public Bootstrapper(Properties properties) throws Exception {
		super(properties);
		utils = new BootstrapperSpringUtils();
	}

	private void configureDorian() throws Exception {
		Properties dorianProperties = new Properties();
		DorianProperties props = utils.getDorianProperties();
		CertificateAuthorityProperties legacyCAProperties = utils.getLegacyCertificateAuthorityProperties();
		CertificateAuthorityProperties caProperties = utils.getCertificateAuthorityProperties();
		// TODO: If the database properties get injected correctly we can reuse
		// them instead of prompting for them.
		dorianProperties.setProperty(DORIAN_DB_NAME_PROPERTY, getValue(DORIAN_DB_NAME_PROMPT, DORIAN_DB_NAME_PROPERTY));
		dorianProperties.setProperty(DORIAN_DB_USER_PROPERTY, getValue(DORIAN_DB_USER_PROMPT, DORIAN_DB_USER_PROPERTY));
		dorianProperties.setProperty(DORIAN_DB_PASSWORD_PROPERTY, getValue(DORIAN_DB_PASSWORD_PROMPT, DORIAN_DB_PASSWORD_PROPERTY));
		dorianProperties.setProperty(DORIAN_DB_HOST_PROPERTY, getValue(DORIAN_DB_HOST_PROMPT, DORIAN_DB_HOST_PROPERTY));
		dorianProperties.setProperty(DORIAN_DB_PORT_PROPERTY, getValue(DORIAN_DB_PORT_PROMPT, DORIAN_DB_PORT_PROPERTY));
		dorianProperties.setProperty(DORIAN_CLIENT_TRUSTSTORE_FILE_PROPERTY, WSRF_TRUSTSTORE_PATH);
		dorianProperties.setProperty(DORIAN_CLIENT_TRUSTSTORE_PASSWORD_PROPERTY, getTruststorePassword());
		dorianProperties.setProperty(DORIAN_CLIENT_KEYSTORE_FILE_PROPERTY, WSRF_KEYSTORE_PATH);
		dorianProperties.setProperty(DORIAN_CLIENT_KEYSTORE_PASSWORD_PROPERTY, getKeystorePassword());
		dorianProperties.setProperty(DORIAN_CLIENT_KEY_ALIAS_PROPERTY, getKeystoreAlias());
		dorianProperties.setProperty(DORIAN_CLIENT_KEY_PASSWORD_PROPERTY, getKeyPassword());
		dorianProperties.setProperty(DORIAN_CRL_PUBLISH_PROPERTY, getValue(DORIAN_CRL_PUBLISH_PROMPT, DORIAN_CRL_PUBLISH_PROPERTY));
		dorianProperties.setProperty(DORIAN_CA_SUBJECT_PROPERTY, caProperties.getCreationPolicy().getSubject());
		dorianProperties.setProperty(LEGACY_DORIAN_CA_SUBJECT_PROPERTY, legacyCAProperties.getCreationPolicy().getSubject());
		File config = new File(getServiceMixEtc(), DORIAN_SERVICE_CFG);
		dorianProperties.store(new FileOutputStream(config), "Dorian Service Configuration saved by bootstrapper on " + new Date());
	}

	public boolean configureLegacyWSRF() {
		if (configureLegacyWSRF == null) {
			configureLegacyWSRF = Boolean.valueOf(getValue(CONFIGURE_LEGACY_WSRF_PROMPT, CONFIGURE_LEGACY_WSRF_PROPERTY));
		}
		return configureLegacyWSRF;
	}

	public String getAdminIdentity() throws Exception {
		if (adminIdentity == null) {
			String userId = getValue(ADMIN_USER_ID_PROMPT, ADMIN_USER_ID_PROPERTY);
			X509Certificate cacert = utils.getDorian().getCACertificate();
			String caSubject = cacert.getSubjectDN().getName();
			int index = caSubject.lastIndexOf(",");
			String subjectPrefix = caSubject.substring(0, index);
			if (utils.getIdentityFederationProperties().getIdentityAssignmentPolicy().equals(IdentityAssignmentPolicy.NAME)) {
				adminIdentity = CertUtil.subjectToIdentity(subjectPrefix + ",OU=" + utils.getIdentityProviderProperties().getName() + "/CN=" + userId);
			} else {
				adminIdentity = CertUtil.subjectToIdentity(subjectPrefix + ",OU=IdP [1]/CN=" + userId);
			}
			System.out.println(adminIdentity);
		}
		return adminIdentity;
	}

	public String getHostname() {
		if (hostname == null) {
			hostname = getValue(WSRF_HOSTNAME_PROMPT, WSRF_HOSTNAME_PROPERTY);
		}
		return hostname;
	}

	public String getLegacyHostname() {
		if (legacyHostname == null) {
			legacyHostname = getValue(LEGACY_WSRF_HOSTNAME_PROMPT, LEGACY_WSRF_HOSTNAME_PROPERTY);
		}
		return legacyHostname;
	}

	public String getKeystorePassword() {
		if (keystorePassword == null) {
			keystorePassword = getValue(KEYSTORE_PASSWORD_PROMPT, KEYSTORE_PASSWORD_PROPERTY);
		}
		return keystorePassword;
	}

	public String getKeystoreAlias() {
		if (keystoreAlias == null) {
			keystoreAlias = getValue(KEYSTORE_ALIAS_PROMPT, KEYSTORE_ALIAS_PROPERTY);
		}
		return keystoreAlias;
	}

	public String getKeyPassword() {
		if (this.keyPassword == null) {
			this.keyPassword = getValue(KEY_PASSWORD_PROMPT, KEY_PASSWORD_PROPERTY);
		}
		return this.keyPassword;
	}

	public String getLegacyKeystorePassword() {
		if (legacyKeystorePassword == null) {
			legacyKeystorePassword = getValue(LEGACY_KEYSTORE_PASSWORD_PROMPT, LEGACY_KEYSTORE_PASSWORD_PROPERTY);
		}
		return keystorePassword;
	}

	public String getLegacyKeystoreAlias() {
		if (legacyKeystoreAlias == null) {
			legacyKeystoreAlias = getValue(LEGACY_KEYSTORE_ALIAS_PROMPT, LEGACY_KEYSTORE_ALIAS_PROPERTY);
		}
		return legacyKeystoreAlias;
	}

	public String getLegacyKeyPassword() {
		if (this.legacyKeyPassword == null) {
			this.legacyKeyPassword = getValue(LEGACY_KEY_PASSWORD_PROMPT, LEGACY_KEY_PASSWORD_PROPERTY);
		}
		return this.legacyKeyPassword;
	}

	public String getTruststorePassword() {
		if (truststorePassword == null) {
			truststorePassword = getValue(TRUSTSTORE_PASSWORD_PROMPT, TRUSTSTORE_PASSWORD_PROPERTY);
		}
		return truststorePassword;
	}

	@Override
	public void execute() throws Exception {
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println("*                   Dorian Bootstrapper                     *");
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println("");
		((DorianImpl) utils.getDorian()).initialize();
		dorianEtcDir = new File(getServiceMixEtc().getAbsolutePath() + File.separator + DORIAN_SERVICE_DIR);
		dorianEtcDir.mkdirs();
		createTruststore();
		getAndSaveDorianHostCredentials();
		getAndSaveLegacyWSRFCredentials();
		configureDorian();
		configureDorianWSRF();

	}

	private void getAndSaveDorianHostCredentials() throws Exception {
		File keystoreFile = new File(this.dorianEtcDir.getAbsolutePath() + File.separator + KEYSTORE_FILE_NAME);
		writeHostCertificate(getHostname(), getKeystoreAlias(), keystoreFile.getAbsolutePath(), getKeystorePassword(), getKeyPassword());
	}

	private void configureDorianWSRF() throws Exception {
		Properties dorianWSRFProperties = new Properties();
		dorianWSRFProperties.setProperty(WSRF_HOSTNAME_PROPERTY, getHostname());
		dorianWSRFProperties.setProperty(WSRF_TRUSTSTORE_PATH_PROPERTY, WSRF_TRUSTSTORE_PATH);
		dorianWSRFProperties.setProperty(WSRF_TRUSTSTORE_PASSWORD_PROPERTY, getTruststorePassword());
		dorianWSRFProperties.setProperty(WSRF_KEYSTORE_PATH_PROPERTY, WSRF_KEYSTORE_PATH);
		dorianWSRFProperties.setProperty(WSRF_KEYSTORE_PASSWORD_PROPERTY, getKeystorePassword());
		dorianWSRFProperties.setProperty(WSRF_KEYSTORE_ALIAS_PROPERTY, getKeystoreAlias());
		dorianWSRFProperties.setProperty(WSRF_KEY_PASSWORD_PROPERTY, getKeyPassword());
		String port = getValue(WSRF_PORT_PROMPT, WSRF_PORT_PROPERTY);
		dorianWSRFProperties.setProperty(WSRF_PORT_PROPERTY, port);
		String url = "https://" + getHostname() + ":" + port + "/dorian";
		dorianWSRFProperties.setProperty(WSRF_URL_PROPERTY, url);
		dorianWSRFProperties.setProperty(WSRF_TRUSTED_IDP_MAPPING_PROPERTY, "Dorian," + url + "," + CertUtil.subjectToIdentity(this.hostCertificate.getSubjectDN().getName()));

		if (this.configureLegacyWSRF()) {
			dorianWSRFProperties.setProperty(LEGACY_WSRF_TRUSTSTORE_PATH_PROPERTY, WSRF_TRUSTSTORE_PATH);
			dorianWSRFProperties.setProperty(LEGACY_WSRF_TRUSTSTORE_PASSWORD_PROPERTY, getTruststorePassword());
			dorianWSRFProperties.setProperty(LEGACY_WSRF_HOSTNAME_PROPERTY, getLegacyHostname());
			dorianWSRFProperties.setProperty(LEGACY_WSRF_KEYSTORE_PATH_PROPERTY, LEGACY_WSRF_KEYSTORE_PATH);
			dorianWSRFProperties.setProperty(LEGACY_WSRF_KEYSTORE_PASSWORD_PROPERTY, getLegacyKeystorePassword());
			dorianWSRFProperties.setProperty(LEGACY_WSRF_KEYSTORE_ALIAS_PROPERTY, getLegacyKeystoreAlias());
			dorianWSRFProperties.setProperty(LEGACY_WSRF_KEY_PASSWORD_PROPERTY, getLegacyKeyPassword());
			String legacyPort = getValue(LEGACY_WSRF_PORT_PROMPT, LEGACY_WSRF_PORT_PROPERTY);
			dorianWSRFProperties.setProperty(LEGACY_WSRF_PORT_PROPERTY, legacyPort);
			String legacyURL = "https://" + getLegacyHostname() + ":" + legacyPort + "/wsrf/services/cagrid/Dorian";
			dorianWSRFProperties.setProperty(LEGACY_WSRF_URL_PROPERTY, legacyURL);

			dorianWSRFProperties.setProperty(LEGACY_WSRF_TRUSTED_IDP_MAPPING_PROPERTY, "Dorian," + legacyURL + "," + CertUtil.subjectToIdentity(this.legacyHostCertificate.getSubjectDN().getName()));
		}

		File wsrfConfig = new File(getServiceMixEtc(), DORIAN_WSRF_CFG);
		dorianWSRFProperties.store(new FileOutputStream(wsrfConfig), "Dorian WSRF Service Configuration saved by bootstrapper on " + new Date());
	}

	private void createTruststore() {

		try {
			File f = new File(this.dorianEtcDir.getAbsolutePath() + File.separator + TRUSTSTORE_FILE_NAME);
			CertificateAuthorityManager caManager = utils.getCertificateAuthorityManager();

			List<CertificateAuthority> list = caManager.getCertificateAuthorities();

			KeyStore keyStore = KeyStore.getInstance("jks");

			keyStore.load(null);

			X509Certificate sha1TrustCA = CertUtil.loadCertificate(new File(getValue(TRUST_CA_SHA1_PROMPT, TRUST_CA_SHA1_PROPERTY)));
			keyStore.setEntry("trustca1", new KeyStore.TrustedCertificateEntry(sha1TrustCA), null);
			X509Certificate sha2TrustCA = CertUtil.loadCertificate(new File(getValue(TRUST_CA_SHA2_PROMPT, TRUST_CA_SHA2_PROPERTY)));
			keyStore.setEntry("trustca2", new KeyStore.TrustedCertificateEntry(sha2TrustCA), null);
			int count = 1;
			for (CertificateAuthority ca : list) {
				X509Certificate cert = ca.getCACertificate();
				keyStore.setEntry("dorianca" + count, new KeyStore.TrustedCertificateEntry(cert), null);
				count = count + 1;
				System.out.println("Adding " + cert.getSubjectDN().getName() + " to the dorian truststore.");
			}

			FileOutputStream fos = new FileOutputStream(f);
			keyStore.store(fos, getTruststorePassword().toCharArray());
			fos.close();

			System.out.println("Truststore created for Dorian at " + f.getAbsolutePath());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAndSaveLegacyWSRFCredentials() throws Exception {
		if (configureLegacyWSRF()) {
			String hostCertificate = getValue(LEGACY_CERTIFICATE_PROMPT, LEGACY_CERTIFICATE_PROPERTY);
			legacyHostCertificate = CertUtil.loadCertificate(new File(hostCertificate));
			String key = getValue(LEGACY_KEY_PROMPT, LEGACY_KEY_PROPERTY);
			PrivateKey pkey = KeyUtil.loadPrivateKey(new File(key), null);
			java.security.cert.Certificate[] hostCertChain = { legacyHostCertificate };
			KeyStore hks = KeyStore.getInstance("jks");
			hks.load(null);

			hks.setKeyEntry(getLegacyKeystoreAlias(), pkey, getLegacyKeyPassword().toCharArray(), hostCertChain);
			File hostPath = new File(this.dorianEtcDir.getAbsolutePath() + File.separator + LEGACY_KEYSTORE_FILE_NAME);
			FileOutputStream out = new FileOutputStream(hostPath);
			hks.store(out, getLegacyKeystorePassword().toCharArray());
			out.close();
			System.out.println("Legacy keystore created for " + legacyHostCertificate.getSubjectDN() + " at " + hostPath.getAbsolutePath());
		}
	}

	private void writeHostCertificate(String host, String hostAlias, String hostPath, String keystorePassword, String keyPassword) throws Exception {
		CertificateAuthorityProperties caProperties = utils.getCertificateAuthorityProperties();
		KeyPair pair = KeyUtil.generateRSAKeyPair(caProperties.getIssuedCertificateKeySize());

		HostCertificateRequest req = new HostCertificateRequest();
		req.setHostname(host);
		PublicKey publicKey = new PublicKey();
		publicKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));
		req.setPublicKey(publicKey);
		HostCertificateRecord record = utils.getDorian().requestHostCertificate(this.getAdminIdentity(), req, CertificateSignatureAlgorithm.SHA2);
		hostCertificate = CertUtil.loadCertificate(record.getCertificate().getCertificateAsString());
		System.out.println("Successfully created the host certificate:");
		System.out.println("Subject: " + hostCertificate.getSubjectDN());
		System.out.println("Created: " + hostCertificate.getNotBefore());
		System.out.println("Expires: " + hostCertificate.getNotAfter());

		java.security.cert.Certificate[] hostCertChain = { hostCertificate };
		KeyStore hks = KeyStore.getInstance("jks");
		hks.load(null);

		hks.setKeyEntry(hostAlias, pair.getPrivate(), keyPassword.toCharArray(), hostCertChain);
		FileOutputStream out = new FileOutputStream(hostPath);
		hks.store(out, keystorePassword.toCharArray());
		out.close();
		System.out.println("Keystore created for " + hostCertificate.getSubjectDN() + " at " + hostPath);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Bootstrapper main = new Bootstrapper(new File(PROPERTIES_FILE));
			main.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
