package org.cagrid.gts.service.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.cagrid.core.commandline.BaseCommandLine;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;

public class Bootstrapper extends BaseCommandLine {

	private static final String PROPERTIES_FILE = "src/main/resources/bootstrapper.properties";
	private static final String GTS_SERVICE_DIR = "cagrid-gts";
	private static final String GTS_SERVICE_CFG = "cagrid.gts.service.cfg";
	private static final String GTS_WSRF_CFG = "cagrid.gts.wsrf.cfg";

	public static final String CONFIGURE_LEGACY_WSRF_PROMPT = "Do you want to configure a Legacy WSRF Endpoint (true|false)";
	public static final String CONFIGURE_LEGACY_WSRF_PROPERTY = "cagrid.gts.configure.legacy.wsrf";

	private static final String TRUSTSTORE_FILE_NAME = "truststore.jks";

	private static final String WSRF_INDEXSVC_PROMPT = "Please enter index service endpoint";
	private static final String WSRF_INDEXSVC_PROPERTY = "cagrid.gts.wsrf.registration.index.url";
	private static final String WSRF_HOSTNAME_PROMPT = "Please enter a hostname for the WSRF endpoint";
	private static final String WSRF_HOSTNAME_PROPERTY = "cagrid.gts.wsrf.host";
	private static final String WSRF_CERTIFICATE_PROMPT = "Please enter the location of the WSRF endpoint host certificate";
	private static final String WSRF_CERTIFICATE_PROPERTY = "cagrid.gts.wsrf.certificate.location";
	private static final String WSRF_KEY_PROMPT = "Please enter the location of the WSRF endpoint private key";
	private static final String WSRF_KEY_PROPERTY = "cagrid.gts.wsrf.key.location";
	private static final String WSRF_KEYSTORE_ALIAS_PROMPT = "Please enter a alias for the WSRF keystore";
	private static final String WSRF_KEYSTORE_ALIAS_PROPERTY = "cagrid.gts.wsrf.keystore.alias";
	private static final String WSRF_KEYSTORE_PASSWORD_PROMPT = "Please enter a password for the WSRF keystore";
	private static final String WSRF_KEYSTORE_PASSWORD_PROPERTY = "cagrid.gts.wsrf.keystore.password";
	private static final String WSRF_KEY_PASSWORD_PROMPT = "Please enter a password for the WSRF private key";
	private static final String WSRF_KEY_PASSWORD_PROPERTY = "cagrid.gts.wsrf.key.password";
	private static final String WSRF_KEYSTORE_FILE_NAME = "gts-host.jks";
	private static final String WSRF_KEYSTORE_PATH_PROPERTY = "cagrid.gts.wsrf.keystore.path";
	private static final String WSRF_KEYSTORE_PATH = "${karaf.base}/etc/" + GTS_SERVICE_DIR + "/" + WSRF_KEYSTORE_FILE_NAME;
	private static final String WSRF_TRUSTSTORE_PATH_PROPERTY = "cagrid.gts.wsrf.truststore.path";
	private static final String WSRF_TRUSTSTORE_PATH = "${karaf.base}/etc/" + GTS_SERVICE_DIR + "/" + TRUSTSTORE_FILE_NAME;
	private static final String WSRF_TRUSTSTORE_PASSWORD_PROMPT = "Please enter a password for the WSRF truststore";
	private static final String WSRF_TRUSTSTORE_PASSWORD_PROPERTY = "cagrid.gts.wsrf.truststore.password";
	public static final String WSRF_URL_PROPERTY = "cagrid.gts.wsrf.url";
	public static final String WSRF_PORT_PROMPT = "Enter a port number for the WSRF service";
	public static final String WSRF_PORT_PROPERTY = "cagrid.gts.wsrf.port";

	private static final String LEGACY_WSRF_HOSTNAME_PROMPT = "Please enter a legacy hostname";
	private static final String LEGACY_WSRF_HOSTNAME_PROPERTY = "cagrid.gts.legacy-wsrf.host";
	private static final String LEGACY_WSRF_CERTIFICATE_PROMPT = "Please enter the location of the legacy host certificate";
	private static final String LEGACY_WSRF_CERTIFICATE_PROPERTY = "cagrid.gts.legacy-wsrf.certificate.location";
	private static final String LEGACY_WSRF_KEY_PROMPT = "Please enter the location of the legacy private key";
	private static final String LEGACY_WSRF_KEY_PROPERTY = "cagrid.gts.legacy-wsrf.key.location";
	private static final String LEGACY_WSRF_KEYSTORE_ALIAS_PROMPT = "Please enter a alias for the legacy keystore";
	private static final String LEGACY_WSRF_KEYSTORE_ALIAS_PROPERTY = "cagrid.gts.legacy-wsrf.keystore.alias";
	private static final String LEGACY_WSRF_KEYSTORE_PASSWORD_PROMPT = "Please enter a password for the legacy keystore";
	private static final String LEGACY_WSRF_KEYSTORE_PASSWORD_PROPERTY = "cagrid.gts.legacy-wsrf.keystore.password";
	private static final String LEGACY_WSRF_KEY_PASSWORD_PROMPT = "Please enter a password for the legacy private key";
	private static final String LEGACY_WSRF_KEY_PASSWORD_PROPERTY = "cagrid.gts.legacy-wsrf.key.password";
	private static final String LEGACY_WSRF_KEYSTORE_FILE_NAME = "legacy-gts-host.jks";
	private static final String LEGACY_WSRF_KEYSTORE_PATH_PROPERTY = "cagrid.gts.legacy-wsrf.keystore.path";
	private static final String LEGACY_WSRF_KEYSTORE_PATH = "${karaf.base}/etc/" + GTS_SERVICE_DIR + "/" + LEGACY_WSRF_KEYSTORE_FILE_NAME;
	private static final String LEGACY_WSRF_TRUSTSTORE_PATH_PROPERTY = "cagrid.gts.legacy-wsrf.truststore.path";
	private static final String LEGACY_WSRF_TRUSTSTORE_PATH = "${karaf.base}/etc/" + GTS_SERVICE_DIR + "/" + TRUSTSTORE_FILE_NAME;
	private static final String LEGACY_WSRF_TRUSTSTORE_PASSWORD_PROPERTY = "cagrid.gts.legacy-wsrf.truststore.password";
	private static final String LEGACY_WSRF_URL_PROPERTY = "cagrid.gts.legacy-wsrf.url";
	private static final String LEGACY_WSRF_PORT_PROMPT = "Enter a port number for the legacy WSRF service";
	private static final String LEGACY_WSRF_PORT_PROPERTY = "cagrid.gts.legacy-wsrf.port";
	private static final String GTS_URL_PROPERTY = "cagrid.gts.service.url";

	private String truststorePassword;
	private Boolean configureLegacyWSRF;
	private File gtsEtcDir;
	private String keystorePassword;
	private String hostname;
	private String legacyHostname;
	private String keystoreAlias;
	private String keyPassword;
	private String legacyKeystorePassword;
	private String legacyKeystoreAlias;
	private String legacyKeyPassword;
	private String gtsURL;

	public Bootstrapper(File propertiesFile) throws Exception {
		super(propertiesFile);

	}

	public Bootstrapper(Properties properties) throws Exception {
		super(properties);

	}

	@Override
	public void execute() throws Exception {
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println("*                   GTS Bootstrapper                        *");
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println("");
		gtsEtcDir = new File(getServiceMixEtc().getAbsolutePath() + File.separator + GTS_SERVICE_DIR);
		gtsEtcDir.mkdirs();
		configureTruststore();
		createWSRFKeystore();
		configureLegacyWSRFCredentials();
		configureWSRFService();
		configureGTS();
		File srcGTSConf = new File(".." + File.separator + "cagrid-gts-service" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "gts-conf.xml");
		File destGTSConf = new File(gtsEtcDir + File.separator + "gts-conf.xml");
		FileUtils.copyFile(srcGTSConf, destGTSConf);
	}

	private void configureGTS() throws Exception {
		Properties props = new Properties();
		props.setProperty(GTS_URL_PROPERTY, this.gtsURL);
		props.setProperty("cagrid.gts.service.client.keystore.file", WSRF_KEYSTORE_PATH);
		props.setProperty("cagrid.gts.service.client.keystore.password", getKeystorePassword());
		props.setProperty("cagrid.gts.service.client.key.alias", getKeystoreAlias());
		props.setProperty("cagrid.gts.service.client.key.password", getKeyPassword());
		props.setProperty("cagrid.gts.service.client.truststore.file", WSRF_TRUSTSTORE_PATH);
		props.setProperty("cagrid.gts.service.client.truststore.password", getTruststorePassword());
		File config = new File(getServiceMixEtc(), GTS_SERVICE_CFG);
		props.store(new FileOutputStream(config), "GTS Configuration saved by bootstrapper on " + new Date());
	}

	private void configureWSRFService() throws Exception {
		Properties props = new Properties();
		props.setProperty(WSRF_HOSTNAME_PROPERTY, getHostname());
		props.setProperty(WSRF_TRUSTSTORE_PATH_PROPERTY, WSRF_TRUSTSTORE_PATH);
		props.setProperty(WSRF_TRUSTSTORE_PASSWORD_PROPERTY, getTruststorePassword());
		props.setProperty(WSRF_KEYSTORE_PATH_PROPERTY, WSRF_KEYSTORE_PATH);
		props.setProperty(WSRF_KEYSTORE_PASSWORD_PROPERTY, getKeystorePassword());
		props.setProperty(WSRF_KEYSTORE_ALIAS_PROPERTY, getKeystoreAlias());
		props.setProperty(WSRF_KEY_PASSWORD_PROPERTY, getKeyPassword());
		String port = getValue(WSRF_PORT_PROMPT, WSRF_PORT_PROPERTY);
		props.setProperty(WSRF_PORT_PROPERTY, port);
		String url = "https://" + getHostname() + ":" + port + "/gts";
		this.gtsURL = url;
		props.setProperty(WSRF_URL_PROPERTY, url);
		props.setProperty(WSRF_INDEXSVC_PROPERTY, getValue(WSRF_INDEXSVC_PROMPT, WSRF_INDEXSVC_PROPERTY));

		if (this.configureLegacyWSRF()) {
			props.setProperty(LEGACY_WSRF_TRUSTSTORE_PATH_PROPERTY, LEGACY_WSRF_TRUSTSTORE_PATH);
			props.setProperty(LEGACY_WSRF_TRUSTSTORE_PASSWORD_PROPERTY, getTruststorePassword());
			props.setProperty(LEGACY_WSRF_HOSTNAME_PROPERTY, getLegacyHostname());
			props.setProperty(LEGACY_WSRF_KEYSTORE_PATH_PROPERTY, LEGACY_WSRF_KEYSTORE_PATH);
			props.setProperty(LEGACY_WSRF_KEYSTORE_PASSWORD_PROPERTY, getLegacyKeystorePassword());
			props.setProperty(LEGACY_WSRF_KEYSTORE_ALIAS_PROPERTY, getLegacyKeystoreAlias());
			props.setProperty(LEGACY_WSRF_KEY_PASSWORD_PROPERTY, getLegacyKeyPassword());
			String legacyPort = getValue(LEGACY_WSRF_PORT_PROMPT, LEGACY_WSRF_PORT_PROPERTY);
			props.setProperty(LEGACY_WSRF_PORT_PROPERTY, legacyPort);
			String legacyURL = "https://" + getLegacyHostname() + ":" + legacyPort + "/wsrf/services/cagrid/GTS";
			this.gtsURL = legacyURL;
			props.setProperty(LEGACY_WSRF_URL_PROPERTY, legacyURL);
		}

		File wsrfConfig = new File(getServiceMixEtc(), GTS_WSRF_CFG);
		props.store(new FileOutputStream(wsrfConfig), "GridGrouper WSRF Service Configuration saved by bootstrapper on " + new Date());
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
			File hostPath = new File(this.gtsEtcDir.getAbsolutePath() + File.separator + LEGACY_WSRF_KEYSTORE_FILE_NAME);
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
		String hostCertificate = getValue(WSRF_CERTIFICATE_PROMPT, WSRF_CERTIFICATE_PROPERTY);
		X509Certificate cert = CertUtil.loadCertificate(new File(hostCertificate));
		String key = getValue(WSRF_KEY_PROMPT, WSRF_KEY_PROPERTY);
		PrivateKey pkey = KeyUtil.loadPrivateKey(new File(key), null);
		java.security.cert.Certificate[] hostCertChain = { cert };
		KeyStore hks = KeyStore.getInstance("jks");
		hks.load(null);

		String alias = getKeystoreAlias();
		String keyPassword = getKeyPassword();
		String password = getKeystorePassword();
		hks.setKeyEntry(alias, pkey, keyPassword.toCharArray(), hostCertChain);
		File hostPath = new File(this.gtsEtcDir.getAbsolutePath() + File.separator + WSRF_KEYSTORE_FILE_NAME);
		FileOutputStream out = new FileOutputStream(hostPath);
		hks.store(out, password.toCharArray());
		out.close();
		System.out.println("WSRF keystore created for " + cert.getSubjectDN() + " at " + hostPath.getAbsolutePath());
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
		File f = new File(this.gtsEtcDir.getAbsolutePath() + File.separator + TRUSTSTORE_FILE_NAME);
		this.copyTrustStore(f.getAbsolutePath(), getTruststorePassword());
		System.out.println("Truststore created for GTS at " + f.getAbsolutePath());
	}

	public String getTruststorePassword() {
		if (truststorePassword == null) {
			truststorePassword = getValue(WSRF_TRUSTSTORE_PASSWORD_PROMPT, WSRF_TRUSTSTORE_PASSWORD_PROPERTY);
		}
		return truststorePassword;
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
