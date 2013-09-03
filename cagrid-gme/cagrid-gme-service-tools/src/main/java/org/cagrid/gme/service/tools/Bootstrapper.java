package org.cagrid.gme.service.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Properties;

import javax.net.ssl.KeyManager;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.commandline.BaseCommandLine;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
import org.cagrid.dorian.DorianPortType;
import org.cagrid.dorian.RequestHostCertificateRequest;
import org.cagrid.dorian.RequestHostCertificateResponse;
import org.cagrid.dorian.RequestUserCertificateRequest;
import org.cagrid.dorian.RequestUserCertificateRequest.Key;
import org.cagrid.dorian.RequestUserCertificateRequest.Lifetime;
import org.cagrid.dorian.RequestUserCertificateRequest.Saml;
import org.cagrid.dorian.RequestUserCertificateResponse;
import org.cagrid.dorian.model.federation.CertificateLifetime;
import org.cagrid.dorian.model.federation.HostCertificateRecord;
import org.cagrid.dorian.model.federation.HostCertificateRequest;
import org.cagrid.dorian.model.federation.PublicKey;
import org.cagrid.dorian.service.CertificateSignatureAlgorithm;
import org.cagrid.dorian.service.ca.CertificateAuthorityProperties;
import org.cagrid.dorian.service.core.BeanUtils;
import org.cagrid.dorian.service.federation.IdentityAssignmentPolicy;
import org.cagrid.dorian.soapclient.DorianSoapClientFactory;
import org.cagrid.gaards.authentication.AuthenticateUserRequest;
import org.cagrid.gaards.authentication.AuthenticateUserRequest.Credential;
import org.cagrid.gaards.authentication.AuthenticateUserResponse;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.oasis.names.tc.saml.assertion.AssertionType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class Bootstrapper extends BaseCommandLine {

	private static final String PROPERTIES_FILE = "src/main/resources/bootstrapper.properties";
	private static final String GME_SERVICE_DIR = "cagrid-gme";
	private static final String GME_SERVICE_CFG = "cagrid.gme.service.cfg";
	private static final String GME_WSRF_CFG = "cagrid.gme.wsrf.cfg";

	public static final String CONFIGURE_LEGACY_WSRF_PROMPT = "Do you want to configure a Legacy WSRF Endpoint (true|false)";
	public static final String CONFIGURE_LEGACY_WSRF_PROPERTY = "cagrid.gme.configure.legacy.wsrf";

	private static final String TRUSTSTORE_FILE_NAME = "truststore.jks";

	public static final String ADMIN_USER_ID_PROMPT = "Please enter the user id of admin";
	public static final String ADMIN_USER_ID_PROPERTY = "org.cagrid.dorian.admin.user.id";

	private static final String WSRF_CERTIFICATE_SOURCE = "cagrid.gme.wsrf.cert.source";
	
	private static final String DORIAN_CONFIG_PROMPT = "Please enter classpath location of Dorian configuration file";
	private static final String DORIAN_CONFIG_PROPERTY = "cagrid.gme.dorian.config";
	private static final String DORIAN_PROPERTIES_PROMPT = "Please enter location of Dorian property file";
	private static final String DORIAN_PROPERTIES_PROPERTY = "cagrid.gme.dorian.properties";
	
	private static final String WSRF_HOSTNAME_PROMPT = "Please enter a hostname for the WSRF endpoint";
	private static final String WSRF_HOSTNAME_PROPERTY = "cagrid.gme.wsrf.host";
	public static final String WSRF_PORT_PROMPT = "Enter a port number for the WSRF service";
	public static final String WSRF_PORT_PROPERTY = "cagrid.gme.wsrf.port";
	public static final String WSRF_URL_PROPERTY = "cagrid.gme.wsrf.url";
	private static final String WSRF_URL_PATH_PROMPT = "Please enter the url path of the WSRF endpoint";
	private static final String WSRF_URL_PATH_PROPERTY = "cagrid.gme.wsrf.url.path";
	private static final String WSRF_CERTIFICATE_PROMPT = "Please enter the location of the WSRF endpoint host certificate";
	private static final String WSRF_CERTIFICATE_PROPERTY = "cagrid.gme.certificate.location";
	private static final String WSRF_KEY_PROMPT = "Please enter the location of the WSRF endpoint private key";
	private static final String WSRF_KEY_PROPERTY = "cagrid.gme.key.location";
	private static final String WSRF_KEYSTORE_ALIAS_PROMPT = "Please enter a alias for the WSRF keystore";
	private static final String WSRF_KEYSTORE_ALIAS_PROPERTY = "cagrid.gme.wsrf.keystore.alias";
	private static final String WSRF_KEYSTORE_PASSWORD_PROMPT = "Please enter a password for the WSRF keystore";
	private static final String WSRF_KEYSTORE_PASSWORD_PROPERTY = "cagrid.gme.wsrf.keystore.password";
	private static final String WSRF_KEY_PASSWORD_PROMPT = "Please enter a password for the WSRF private key";
	private static final String WSRF_KEY_PASSWORD_PROPERTY = "cagrid.gme.wsrf.key.password";
	private static final String WSRF_KEYSTORE_FILE_NAME = "grid-gme-host.jks";
	private static final String WSRF_KEYSTORE_PATH_PROPERTY = "cagrid.gme.wsrf.keystore.path";
	private static final String WSRF_KEYSTORE_PATH = "${karaf.base}/etc/" + GME_SERVICE_DIR + "/" + WSRF_KEYSTORE_FILE_NAME;
	private static final String WSRF_TRUSTSTORE_PATH_PROPERTY = "cagrid.gme.wsrf.truststore.path";
	private static final String WSRF_TRUSTSTORE_PATH = "${karaf.base}/etc/" + GME_SERVICE_DIR + "/" + TRUSTSTORE_FILE_NAME;
	private static final String WSRF_TRUSTSTORE_PASSWORD_PROMPT = "Please enter a password for the WSRF truststore";
	private static final String WSRF_TRUSTSTORE_PASSWORD_PROPERTY = "cagrid.gme.wsrf.truststore.password";

	private static final String LEGACY_WSRF_HOSTNAME_PROMPT = "Please enter a legacy hostname";
	private static final String LEGACY_WSRF_HOSTNAME_PROPERTY = "cagrid.gme.legacy-wsrf.host";
	private static final String LEGACY_WSRF_URL_PATH_PROMPT = "Please enter the url path of the legacy WSRF endpoint";
	private static final String LEGACY_WSRF_URL_PATH_PROPERTY = "cagrid.gme.legacy-wsrf.url.path";
	private static final String LEGACY_WSRF_CERTIFICATE_PROMPT = "Please enter the location of the legacy host certificate";
	private static final String LEGACY_WSRF_CERTIFICATE_PROPERTY = "cagrid.gme.legacy-certificate.location";
	private static final String LEGACY_WSRF_KEY_PROMPT = "Please enter the location of the legacy private key";
	private static final String LEGACY_WSRF_KEY_PROPERTY = "cagrid.gme.legacy-key.location";
	private static final String LEGACY_WSRF_KEYSTORE_ALIAS_PROMPT = "Please enter a alias for the legacy keystore";
	private static final String LEGACY_WSRF_KEYSTORE_ALIAS_PROPERTY = "cagrid.gme.legacy-wsrf.keystore.alias";
	private static final String LEGACY_WSRF_KEYSTORE_PASSWORD_PROMPT = "Please enter a password for the legacy keystore";
	private static final String LEGACY_WSRF_KEYSTORE_PASSWORD_PROPERTY = "cagrid.gme.legacy-wsrf.keystore.password";
	private static final String LEGACY_WSRF_KEY_PASSWORD_PROMPT = "Please enter a password for the legacy private key";
	private static final String LEGACY_WSRF_KEY_PASSWORD_PROPERTY = "cagrid.gme.legacy-wsrf.key.password";
	private static final String LEGACY_WSRF_KEYSTORE_FILE_NAME = "legacy-grid-gme-host.jks";
	private static final String LEGACY_WSRF_KEYSTORE_PATH_PROPERTY = "cagrid.gme.legacy-wsrf.keystore.path";
	private static final String LEGACY_WSRF_KEYSTORE_PATH = "${karaf.base}/etc/" + GME_SERVICE_DIR + "/" + LEGACY_WSRF_KEYSTORE_FILE_NAME;
	private static final String LEGACY_WSRF_TRUSTSTORE_PATH_PROPERTY = "cagrid.gme.legacy-wsrf.truststore.path";
	private static final String LEGACY_WSRF_TRUSTSTORE_PATH = "${karaf.base}/etc/" + GME_SERVICE_DIR + "/" + TRUSTSTORE_FILE_NAME;
	private static final String LEGACY_WSRF_TRUSTSTORE_PASSWORD_PROPERTY = "cagrid.gme.legacy-wsrf.truststore.password";
	private static final String LEGACY_WSRF_URL_PROPERTY = "cagrid.gme.legacy-wsrf.url";
	private static final String LEGACY_WSRF_PORT_PROMPT = "Enter a port number for the legacy WSRF service";
	private static final String LEGACY_WSRF_PORT_PROPERTY = "cagrid.gme.legacy-wsrf.port";
	private static final String DB_URL_PROMPT = "Please enter the database URL";
	private static final String DB_URL_PROPERTY = "cagrid.gme.service.db.url";
	private static final String DB_USER_PROMPT = "Please enter the database username";
	private static final String DB_USER_PROPERTY = "cagrid.gme.service.db.username";
	private static final String DB_PASSWORD_PROMPT = "Please enter the database password";
	private static final String DB_PASSWORD_PROPERTY = "cagrid.gme.service.db.password";;

	private String adminIdentity;
	private String truststorePassword;
	private Boolean configureLegacyWSRF;
	private File gmeEtcDir;
	private String keystorePassword;
	private String hostname;
	private String legacyHostname;
	private String keystoreAlias;
	private String keyPassword;
	private String legacyKeystorePassword;
	private String legacyKeystoreAlias;
	private String legacyKeyPassword;

	private BeanUtils dorianUtils;

	public Bootstrapper(File propertiesFile) throws Exception {
		super(propertiesFile);
	}

	public Bootstrapper(Properties properties) throws Exception {
		super(properties);
	}

	@Override
	public void execute() throws Exception {
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println("*            Global Model Exchange Bootstrapper             *");
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println("");
		gmeEtcDir = new File(getServiceMixEtc().getAbsolutePath() + File.separator + GME_SERVICE_DIR);
		gmeEtcDir.mkdirs();
		configureTruststore();
		createWSRFKeystore();
		configureLegacyWSRFCredentials();
		configureGlobalModelExchange();
		configureWSRFService();

	}

	private void configureGlobalModelExchange() throws Exception {
		Properties props = new Properties();
		props.setProperty(DB_URL_PROPERTY, getValue(DB_URL_PROMPT, DB_URL_PROPERTY));
		props.setProperty(DB_USER_PROPERTY, getValue(DB_USER_PROMPT, DB_USER_PROPERTY));
		props.setProperty(DB_PASSWORD_PROPERTY, getValue(DB_PASSWORD_PROMPT, DB_PASSWORD_PROPERTY));
		File config = new File(getServiceMixEtc(), GME_SERVICE_CFG);
		props.store(new FileOutputStream(config), "Global Model Exchange Service Configuration saved by bootstrapper on " + new Date());
	}

	private void configureWSRFService() throws Exception {
		Properties props = new Properties();
		props.setProperty(WSRF_HOSTNAME_PROPERTY, getHostname());
		String port = getValue(WSRF_PORT_PROMPT, WSRF_PORT_PROPERTY);
		props.setProperty(WSRF_PORT_PROPERTY, port);
		
		String url = "https://" + getHostname() + ":" + port + "/" + getValue(WSRF_URL_PATH_PROMPT, WSRF_URL_PATH_PROPERTY);
		props.setProperty(WSRF_URL_PROPERTY, url);
		props.setProperty(WSRF_TRUSTSTORE_PATH_PROPERTY, WSRF_TRUSTSTORE_PATH);
		props.setProperty(WSRF_TRUSTSTORE_PASSWORD_PROPERTY, getTruststorePassword());
		props.setProperty(WSRF_KEYSTORE_PATH_PROPERTY, WSRF_KEYSTORE_PATH);
		props.setProperty(WSRF_KEYSTORE_PASSWORD_PROPERTY, getKeystorePassword());
		props.setProperty(WSRF_KEYSTORE_ALIAS_PROPERTY, getKeystoreAlias());
		props.setProperty(WSRF_KEY_PASSWORD_PROPERTY, getKeyPassword());
		

		if (this.configureLegacyWSRF()) {
			props.setProperty(LEGACY_WSRF_TRUSTSTORE_PATH_PROPERTY, LEGACY_WSRF_TRUSTSTORE_PATH);
			props.setProperty(LEGACY_WSRF_TRUSTSTORE_PASSWORD_PROPERTY, getTruststorePassword());
			props.setProperty(LEGACY_WSRF_HOSTNAME_PROPERTY, getLegacyHostname());
			String legacyPort = getValue(LEGACY_WSRF_PORT_PROMPT, LEGACY_WSRF_PORT_PROPERTY);
			props.setProperty(LEGACY_WSRF_PORT_PROPERTY, legacyPort);
			String legacyURL = "https://" + getLegacyHostname() + ":" + legacyPort + "/" + getValue(LEGACY_WSRF_URL_PATH_PROMPT, LEGACY_WSRF_URL_PATH_PROPERTY);
			props.setProperty(LEGACY_WSRF_URL_PROPERTY, legacyURL);
			props.setProperty(LEGACY_WSRF_KEYSTORE_PATH_PROPERTY, LEGACY_WSRF_KEYSTORE_PATH);
			props.setProperty(LEGACY_WSRF_KEYSTORE_PASSWORD_PROPERTY, getLegacyKeystorePassword());
			props.setProperty(LEGACY_WSRF_KEYSTORE_ALIAS_PROPERTY, getLegacyKeystoreAlias());
			props.setProperty(LEGACY_WSRF_KEY_PASSWORD_PROPERTY, getLegacyKeyPassword());
		}

		File wsrfConfig = new File(getServiceMixEtc(), GME_WSRF_CFG);
		props.store(new FileOutputStream(wsrfConfig), "Global Model Exchange WSRF Service Configuration saved by bootstrapper on " + new Date());
	}

	public void configureLegacyWSRFCredentials() throws Exception {
		if (configureLegacyWSRF()) {
			String hostCertificate = getValue(LEGACY_WSRF_CERTIFICATE_PROMPT, LEGACY_WSRF_CERTIFICATE_PROPERTY);
			X509Certificate cert = CertUtil.loadCertificate(new File(hostCertificate));
			String key = getValue(LEGACY_WSRF_KEY_PROMPT, LEGACY_WSRF_KEY_PROPERTY);
			PrivateKey pkey = KeyUtil.loadPrivateKey(new File(key), null);
			java.security.cert.Certificate[] hostCertChain = { cert };
			KeyStore hks = KeyStore.getInstance("jks");
			hks.load(null);

			String alias = getLegacyKeystoreAlias();
			String keyPassword = getLegacyKeyPassword();
			String password = getLegacyKeystorePassword();
			hks.setKeyEntry(alias, pkey, keyPassword.toCharArray(), hostCertChain);
			File hostPath = new File(this.gmeEtcDir.getAbsolutePath() + File.separator + LEGACY_WSRF_KEYSTORE_FILE_NAME);
			FileOutputStream out = new FileOutputStream(hostPath);
			hks.store(out, password.toCharArray());
			out.close();
			System.out.println("Legacy keystore created for " + cert.getSubjectDN() + " at " + hostPath.getAbsolutePath());
		}
	}

	public boolean configureLegacyWSRF() {
		if (configureLegacyWSRF == null) {
			configureLegacyWSRF = Boolean.valueOf(getValue(CONFIGURE_LEGACY_WSRF_PROMPT, CONFIGURE_LEGACY_WSRF_PROPERTY));
		}
		return configureLegacyWSRF;
	}

	public void createWSRFKeystore() throws Exception {
		String certSource = getValueWithOptions("Obtain certificates from (f) filesystem, (r) remote dorian, (l) local database", WSRF_CERTIFICATE_SOURCE, new String[] {"f", "r", "l"});
		X509Certificate cert = null;
		PrivateKey pkey = null;
		String hostPath = this.gmeEtcDir.getAbsolutePath() + File.separator + WSRF_KEYSTORE_FILE_NAME;
		if ("f".equals(certSource)) {
			String hostCertificate = getValue(WSRF_CERTIFICATE_PROMPT, WSRF_CERTIFICATE_PROPERTY);
			cert = CertUtil.loadCertificate(new File(hostCertificate));
			String key = getValue(WSRF_KEY_PROMPT, WSRF_KEY_PROPERTY);
			pkey = KeyUtil.loadPrivateKey(new File(key), null);
			java.security.cert.Certificate[] hostCertChain = { cert };
			KeyStore hks = KeyStore.getInstance("jks");
			hks.load(null);

			String alias = getKeystoreAlias();
			String keyPassword = getKeyPassword();
			String password = getKeystorePassword();
			hks.setKeyEntry(alias, pkey, keyPassword.toCharArray(), hostCertChain);
			File hostFile = new File(this.gmeEtcDir.getAbsolutePath() + File.separator + WSRF_KEYSTORE_FILE_NAME);
			FileOutputStream out = new FileOutputStream(hostPath);
			hks.store(out, password.toCharArray());
			out.close();
			System.out.println("WSRF keystore created for " + cert.getSubjectDN() + " at " + hostFile.getAbsolutePath());
		} else if ("r".equals(certSource)) {
			DorianPortType authDorian = getAuthenticatedDorianSoapClient();
			
			KeyPair pair = KeyUtil.generateRSAKeyPair(1024);

			RequestHostCertificateRequest.Req rhcrr = new RequestHostCertificateRequest.Req(); //HostCertificateRequest();
			HostCertificateRequest req = new HostCertificateRequest();
			
			req.setHostname(getHostname());
			PublicKey publicKey = new PublicKey();
			publicKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));
			req.setPublicKey(publicKey);
			RequestHostCertificateRequest rhcr = new RequestHostCertificateRequest();
			rhcrr.setHostCertificateRequest(req);
			
			rhcr.setReq(rhcrr);
			System.out.println(rhcr);
			RequestHostCertificateResponse resp = authDorian.requestHostCertificate(rhcr);
			System.out.println(resp);
		} else if ("l".equals(certSource)) {
			writeHostCertificate(getHostname(), getKeystoreAlias(), hostPath, getKeystorePassword(), getKeyPassword());
		}
	}

	private DorianPortType getAuthenticatedDorianSoapClient() throws Exception {
		KeyStoreType truststore = new KeyStoreType();
		truststore.setFile(getDefaultTruststoreLocation());
		truststore.setType("JKS");
		truststore.setPassword("changeit");

		DorianPortType dorianSoapAnon = DorianSoapClientFactory
				.createSoapClient("https://localhost:4443/dorian", truststore,
						(KeyManager) null);

		BasicAuthentication basicAuthentication = new BasicAuthentication();
		basicAuthentication.setUserId("dorian");
		basicAuthentication.setPassword("DorianAdmin$1");
		Credential credential = new Credential();
		credential.setCredential(basicAuthentication);
		AuthenticateUserRequest authenticateUserRequest = new AuthenticateUserRequest();
		authenticateUserRequest.setCredential(credential);
		AuthenticateUserResponse authenticateUserResponse = dorianSoapAnon
				.authenticateUser(authenticateUserRequest);
		AssertionType assertion = authenticateUserResponse.getAssertion();
		//Assert.assertNotNull(assertion);

		KeyPair keyPair = KeyUtil.generateRSAKeyPair(2048);
		Saml saml = new Saml();
		saml.setAssertion(assertion);
		PublicKey caPublicKey = new PublicKey();
		caPublicKey.setKeyAsString(KeyUtil.writePublicKey(keyPair.getPublic()));
		RequestUserCertificateRequest userCertificateRequest = new RequestUserCertificateRequest();
		userCertificateRequest.setSaml(saml);
		Key caKey = new Key();
		caKey.setPublicKey(caPublicKey);
		userCertificateRequest.setKey(caKey);
		CertificateLifetime certificateLifetime = new CertificateLifetime();
		certificateLifetime.setHours(6);
		Lifetime lifetime = new Lifetime();
		lifetime.setCertificateLifetime(certificateLifetime);
		userCertificateRequest.setLifetime(lifetime);
		RequestUserCertificateResponse requestUserCertificateResponse = dorianSoapAnon
				.requestUserCertificate(userCertificateRequest);
		String certificateString = requestUserCertificateResponse
				.getX509Certificate().getCertificateAsString();
		X509Certificate certificate = CertUtil
				.loadCertificate(certificateString);
		//Assert.assertNotNull(certificate);

		KeyManager keyManager = new SingleEntityKeyManager("client",
				new X509Certificate[] { certificate }, keyPair.getPrivate());
		DorianPortType dorianSoapAuth = DorianSoapClientFactory
				.createSoapClient("https://localhost:7734/dorian", truststore,
						keyManager);
		return dorianSoapAuth;
	}
	
	private BeanUtils getDorianUtils() throws Exception {
		if (dorianUtils == null) {
			dorianUtils = new BeanUtils(new ClassPathResource(getValue(DORIAN_CONFIG_PROMPT, DORIAN_CONFIG_PROPERTY)), 
					new FileSystemResource(getValue(DORIAN_PROPERTIES_PROMPT, DORIAN_PROPERTIES_PROPERTY)));
		}
		return dorianUtils;
	}

	private void writeHostCertificate(String host, String hostAlias, String hostPath, String keystorePassword, String keyPassword) throws Exception {
		CertificateAuthorityProperties caProperties = getDorianUtils().getCertificateAuthorityProperties();
		KeyPair pair = KeyUtil.generateRSAKeyPair(caProperties.getIssuedCertificateKeySize());

		HostCertificateRequest req = new HostCertificateRequest();
		req.setHostname(host);
		PublicKey publicKey = new PublicKey();
		publicKey.setKeyAsString(KeyUtil.writePublicKey(pair.getPublic()));
		req.setPublicKey(publicKey);
		HostCertificateRecord record = getDorianUtils().getDorian().requestHostCertificate(this.getAdminIdentity(), req, CertificateSignatureAlgorithm.SHA2);
		X509Certificate hostCertificate = CertUtil.loadCertificate(record.getCertificate().getCertificateAsString());
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
			keystorePassword = getValue(WSRF_KEYSTORE_PASSWORD_PROMPT, WSRF_KEYSTORE_PASSWORD_PROPERTY);
		}
		return keystorePassword;
	}

	public String getKeystoreAlias() {
		if (keystoreAlias == null) {
			keystoreAlias = getValue(WSRF_KEYSTORE_ALIAS_PROMPT, WSRF_KEYSTORE_ALIAS_PROPERTY);
		}
		return keystoreAlias;
	}

	public String getKeyPassword() {
		if (this.keyPassword == null) {
			this.keyPassword = getValue(WSRF_KEY_PASSWORD_PROMPT, WSRF_KEY_PASSWORD_PROPERTY);
		}
		return this.keyPassword;
	}

	public String getLegacyKeystorePassword() {
		if (legacyKeystorePassword == null) {
			legacyKeystorePassword = getValue(LEGACY_WSRF_KEYSTORE_PASSWORD_PROMPT, LEGACY_WSRF_KEYSTORE_PASSWORD_PROPERTY);
		}
		return keystorePassword;
	}

	public String getLegacyKeystoreAlias() {
		if (legacyKeystoreAlias == null) {
			legacyKeystoreAlias = getValue(LEGACY_WSRF_KEYSTORE_ALIAS_PROMPT, LEGACY_WSRF_KEYSTORE_ALIAS_PROPERTY);
		}
		return legacyKeystoreAlias;
	}

	public String getLegacyKeyPassword() {
		if (this.legacyKeyPassword == null) {
			this.legacyKeyPassword = getValue(LEGACY_WSRF_KEY_PASSWORD_PROMPT, LEGACY_WSRF_KEY_PASSWORD_PROPERTY);
		}
		return this.legacyKeyPassword;
	}

	private void configureTruststore() throws Exception {
		File f = new File(this.gmeEtcDir.getAbsolutePath() + File.separator + TRUSTSTORE_FILE_NAME);
		this.copyTrustStore(f.getAbsolutePath(), getTruststorePassword());
		System.out.println("Truststore created for Global Model Excchange at " + f.getAbsolutePath());
	}

	public String getTruststorePassword() {
		if (truststorePassword == null) {
			truststorePassword = getValue(WSRF_TRUSTSTORE_PASSWORD_PROMPT, WSRF_TRUSTSTORE_PASSWORD_PROPERTY);
		}
		return truststorePassword;
	}
	
	public String getAdminIdentity() throws Exception {
		if (adminIdentity == null) {
			String userId = getValue(ADMIN_USER_ID_PROMPT, ADMIN_USER_ID_PROPERTY);
			X509Certificate cacert = getDorianUtils().getDorian().getCACertificate();
			String caSubject = cacert.getSubjectDN().getName();
			int index = caSubject.lastIndexOf(",");
			String subjectPrefix = caSubject.substring(0, index);
			if (getDorianUtils().getIdentityFederationProperties().getIdentityAssignmentPolicy().equals(IdentityAssignmentPolicy.NAME)) {
				adminIdentity = CertUtil.subjectToIdentity(subjectPrefix + ",OU=" + getDorianUtils().getIdentityProviderProperties().getName() + "/CN=" + userId);
			} else {
				adminIdentity = CertUtil.subjectToIdentity(subjectPrefix + ",OU=IdP [1]/CN=" + userId);
			}
			System.out.println(adminIdentity);
		}
		return adminIdentity;
	}

	public static void main(String[] args) {
		try {
			Bootstrapper main = new Bootstrapper(new File(PROPERTIES_FILE));
			main.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
