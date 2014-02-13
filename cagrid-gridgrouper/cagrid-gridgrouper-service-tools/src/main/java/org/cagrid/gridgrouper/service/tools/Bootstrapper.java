package org.cagrid.gridgrouper.service.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.cagrid.core.commandline.BaseCommandLine;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.gridgrouper.service.impl.GridGrouper;

import edu.internet2.middleware.grouper.GrouperConfigHelper;
import edu.internet2.middleware.grouper.RegistryInstall;
import edu.internet2.middleware.grouper.RegistryReset;
import edu.internet2.middleware.subject.GridSourceAdapter;
import edu.internet2.middleware.subject.Subject;

public class Bootstrapper extends BaseCommandLine {

	private static final String PROPERTIES_FILE = "src/main/resources/bootstrapper.properties";
	private static final String GROUPER_SERVICE_DIR = "cagrid-grid-grouper";
	private static final String GROUPER_SERVICE_CFG = "cagrid.gridgrouper.service.cfg";
	private static final String GROUPER_CFG = "cagrid.gridgrouper.internet2.cfg";
	private static final String GROUPER_WSRF_CFG = "cagrid.gridgrouper.wsrf.cfg";

	public static final String CONFIGURE_LEGACY_WSRF_PROMPT = "Do you want to configure a Legacy WSRF Endpoint (true|false)";
	public static final String CONFIGURE_LEGACY_WSRF_PROPERTY = "cagrid.gridgrouper.configure.legacy.wsrf";

	private static final String TRUSTSTORE_FILE_NAME = "truststore.jks";

	private static final String WSRF_HOSTNAME_PROMPT = "Please enter a hostname for the WSRF endpoint";
	private static final String WSRF_HOSTNAME_PROPERTY = "cagrid.gridgrouper.wsrf.host";
	private static final String WSRF_CERTIFICATE_PROMPT = "Please enter the location of the WSRF endpoint host certificate";
	private static final String WSRF_CERTIFICATE_PROPERTY = "cagrid.gridgrouper.certificate.location";
	private static final String WSRF_KEY_PROMPT = "Please enter the location of the WSRF endpoint private key";
	private static final String WSRF_KEY_PROPERTY = "cagrid.gridgrouper.key.location";
	private static final String WSRF_KEYSTORE_ALIAS_PROMPT = "Please enter a alias for the WSRF keystore";
	private static final String WSRF_KEYSTORE_ALIAS_PROPERTY = "cagrid.gridgrouper.wsrf.keystore.alias";
	private static final String WSRF_KEYSTORE_PASSWORD_PROMPT = "Please enter a password for the WSRF keystore";
	private static final String WSRF_KEYSTORE_PASSWORD_PROPERTY = "cagrid.gridgrouper.wsrf.keystore.password";
	private static final String WSRF_KEY_PASSWORD_PROMPT = "Please enter a password for the WSRF private key";
	private static final String WSRF_KEY_PASSWORD_PROPERTY = "cagrid.gridgrouper.wsrf.key.password";
	private static final String WSRF_KEYSTORE_FILE_NAME = "grid-grouper-host.jks";
	private static final String WSRF_KEYSTORE_PATH_PROPERTY = "cagrid.gridgrouper.wsrf.keystore.path";
	private static final String WSRF_KEYSTORE_PATH = "${karaf.base}/etc/" + GROUPER_SERVICE_DIR + "/" + WSRF_KEYSTORE_FILE_NAME;
	private static final String WSRF_TRUSTSTORE_PATH_PROPERTY = "cagrid.gridgrouper.wsrf.truststore.path";
	private static final String WSRF_TRUSTSTORE_PATH = "${karaf.base}/etc/" + GROUPER_SERVICE_DIR + "/" + TRUSTSTORE_FILE_NAME;
	private static final String WSRF_TRUSTSTORE_PASSWORD_PROMPT = "Please enter a password for the WSRF truststore";
	private static final String WSRF_TRUSTSTORE_PASSWORD_PROPERTY = "cagrid.gridgrouper.wsrf.truststore.password";
	public static final String WSRF_URL_PROPERTY = "cagrid.gridgrouper.wsrf.url";
	public static final String WSRF_PORT_PROMPT = "Enter a port number for the WSRF service";
	public static final String WSRF_PORT_PROPERTY = "cagrid.gridgrouper.wsrf.port";

	private static final String LEGACY_WSRF_HOSTNAME_PROMPT = "Please enter a legacy hostname";
	private static final String LEGACY_WSRF_HOSTNAME_PROPERTY = "cagrid.gridgrouper.legacy-wsrf.host";
	private static final String LEGACY_WSRF_CERTIFICATE_PROMPT = "Please enter the location of the legacy host certificate";
	private static final String LEGACY_WSRF_CERTIFICATE_PROPERTY = "cagrid.gridgrouper.legacy-certificate.location";
	private static final String LEGACY_WSRF_KEY_PROMPT = "Please enter the location of the legacy private key";
	private static final String LEGACY_WSRF_KEY_PROPERTY = "cagrid.gridgrouper.legacy-key.location";
	private static final String LEGACY_WSRF_KEYSTORE_ALIAS_PROMPT = "Please enter a alias for the legacy keystore";
	private static final String LEGACY_WSRF_KEYSTORE_ALIAS_PROPERTY = "cagrid.gridgrouper.legacy-wsrf.keystore.alias";
	private static final String LEGACY_WSRF_KEYSTORE_PASSWORD_PROMPT = "Please enter a password for the legacy keystore";
	private static final String LEGACY_WSRF_KEYSTORE_PASSWORD_PROPERTY = "cagrid.gridgrouper.legacy-wsrf.keystore.password";
	private static final String LEGACY_WSRF_KEY_PASSWORD_PROMPT = "Please enter a password for the legacy private key";
	private static final String LEGACY_WSRF_KEY_PASSWORD_PROPERTY = "cagrid.gridgrouper.legacy-wsrf.key.password";
	private static final String LEGACY_WSRF_KEYSTORE_FILE_NAME = "legacy-grid-grouper-host.jks";
	private static final String LEGACY_WSRF_KEYSTORE_PATH_PROPERTY = "cagrid.gridgrouper.legacy-wsrf.keystore.path";
	private static final String LEGACY_WSRF_KEYSTORE_PATH = "${karaf.base}/etc/" + GROUPER_SERVICE_DIR + "/" + LEGACY_WSRF_KEYSTORE_FILE_NAME;
	private static final String LEGACY_WSRF_TRUSTSTORE_PATH_PROPERTY = "cagrid.gridgrouper.legacy-wsrf.truststore.path";
	private static final String LEGACY_WSRF_TRUSTSTORE_PATH = "${karaf.base}/etc/" + GROUPER_SERVICE_DIR + "/" + TRUSTSTORE_FILE_NAME;
	private static final String LEGACY_WSRF_TRUSTSTORE_PASSWORD_PROPERTY = "cagrid.gridgrouper.legacy-wsrf.truststore.password";
	private static final String LEGACY_WSRF_URL_PROPERTY = "cagrid.gridgrouper.legacy-wsrf.url";
	private static final String LEGACY_WSRF_PORT_PROMPT = "Enter a port number for the legacy WSRF service";
	private static final String LEGACY_WSRF_PORT_PROPERTY = "cagrid.gridgrouper.legacy-wsrf.port";
	private static final String DB_URL_PROMPT = "Please enter the database URL";
	private static final String DB_URL_PROPERTY = "cagrid.gridgrouper.internet2.hibernate.connection.url";
	private static final String DB_USER_PROMPT = "Please enter the database username";
	private static final String DB_USER_PROPERTY = "cagrid.gridgrouper.internet2.hibernate.connection.username";
	private static final String DB_PASSWORD_PROMPT = "Please enter the database password";
	private static final String DB_PASSWORD_PROPERTY = "cagrid.gridgrouper.internet2.hibernate.connection.password";
	private static final String DB_INIT_PROMPT = "Do you want to initialize the Grid Grouper database (true|false)";
	private static final String DB_INIT_PROPERTY = "cagrid.gridgrouper.database.init";

	public static final String WSRF_REGISTRATION_ENABLED_PROMPT = "Please specify whether or not to enable index service registration of the WSRF endpoint";
	public static final String WSRF_REGISTRATION_ENABLED_PROPERTY = "cagrid.gridgrouper.wsrf.registration.on";
	public static final String LEGACY_WSRF_REGISTRATION_ENABLED_PROPERTY = "cagrid.gridgrouper.wsrf.registration.legacy.on";
	public static final String LEGACY_WSRF_REGISTRATION_ENABLED_PROMPT = "Please specify whether or not to enable index service registration of the legacy WSRF endpoint";
	public static final String WSRF_REGISTRATION_URL_PROMPT = "Please specify the URL of the index service";
	public static final String WSRF_REGISTRATION_URL_PROPERTY = "cagrid.gridgrouper.wsrf.registration.index.url";

	private String truststorePassword;
	private Boolean configureLegacyWSRF;
	private File grouperEtcDir;
	private String keystorePassword;
	private String hostname;
	private String legacyHostname;
	private String keystoreAlias;
	private String keyPassword;
	private String legacyKeystorePassword;
	private String legacyKeystoreAlias;
	private String legacyKeyPassword;
	private String databaseURL;
	private String databaseUser;
	private String databasePassword;
	private GrouperConfigHelper helper = null;

	public Bootstrapper(File propertiesFile) throws Exception {
		super(propertiesFile);

	}

	public Bootstrapper(Properties properties) throws Exception {
		super(properties);

	}

	@Override
	public void execute() throws Exception {
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println("*                 GridGrouper Bootstrapper                  *");
		System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println("");
		grouperEtcDir = new File(getServiceMixEtc().getAbsolutePath() + File.separator + GROUPER_SERVICE_DIR);
		grouperEtcDir.mkdirs();
		initDatabase();
		configureTruststore();
		createWSRFKeystore();
		configureLegacyWSRFCredentials();
		configureGrouper();
		configureWSRFService();
		addAdmins();

	}

	private void addAdmins() throws Exception {
		getHelper();
		boolean addAdmin = getBooleanValue("Would you like to add an administrator to Grid Grouper (true|false)", "no.property");
		GridSourceAdapter guss = new GridSourceAdapter("grid", "Grid Grouper: Grid Source Adapter");
		GridGrouper gg = new GridGrouper();
		while (addAdmin) {
			String adminIdentity = getValue("Enter the identity of the admin you with to add to Grid Grouper", "no.property");
			Subject admin = guss.getSubject(adminIdentity);
			gg.getAdminGroup().addMember(admin);
			addAdmin = getBooleanValue("Would you like to add another administrator to Grid Grouper (true|false)", "no.property");
		}
	}

	private GrouperConfigHelper getHelper() {
		if (this.helper == null) {
			GrouperConfigHelper helper = new GrouperConfigHelper();
			helper.setHibernateConnectionUrl(getDatabaseURL());
			helper.setHibernateConnectionUsername(getDatabaseUser());
			helper.setHibernateConnectionPassword(getDatabasePassword());
		}
		return this.helper;
	}

	private void initDatabase() {
		String val = getValue(DB_INIT_PROMPT, DB_INIT_PROPERTY);
		if (val.equalsIgnoreCase("true")) {
			getHelper();
			RegistryInstall.main(new String[0]);
			RegistryReset.reset();
			// jdbc:mysql://localhost:3306/langella_grouper
		}

	}

	private void configureGrouper() throws Exception {
		Properties props = new Properties();
		props.setProperty(DB_URL_PROPERTY, getDatabaseURL());
		props.setProperty(DB_USER_PROPERTY, getDatabaseUser());
		props.setProperty(DB_PASSWORD_PROPERTY, getDatabasePassword());
		File config = new File(getServiceMixEtc(), GROUPER_CFG);
		props.store(new FileOutputStream(config), "Grouper Service Configuration saved by bootstrapper on " + new Date());
	}

	public String getDatabaseURL() {
		if (databaseURL == null) {
			this.databaseURL = getValue(DB_URL_PROMPT, DB_URL_PROPERTY);
		}
		return databaseURL;
	}

	public String getDatabaseUser() {
		if (databaseUser == null) {
			this.databaseUser = getValue(DB_USER_PROMPT, DB_USER_PROPERTY);
		}
		return databaseUser;
	}

	public String getDatabasePassword() {
		if (this.databasePassword == null) {
			this.databasePassword = getValue(DB_PASSWORD_PROMPT, DB_PASSWORD_PROPERTY);
			if (StringUtils.isBlank(this.databasePassword)) {
				databasePassword = "";
			}
		}
		return databasePassword;
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
		String url = "https://" + getHostname() + ":" + port + "/gridgrouper";
		props.setProperty(WSRF_URL_PROPERTY, url);
		boolean enableRegistration = getBooleanValue(WSRF_REGISTRATION_ENABLED_PROMPT, WSRF_REGISTRATION_ENABLED_PROPERTY);
		props.setProperty(WSRF_REGISTRATION_ENABLED_PROPERTY, Boolean.valueOf(enableRegistration).toString());
		boolean enableLegacyRegistration = false;

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
			String legacyURL = "https://" + getLegacyHostname() + ":" + legacyPort + "/wsrf/services/cagrid/GridGrouper";
			props.setProperty(LEGACY_WSRF_URL_PROPERTY, legacyURL);
			enableLegacyRegistration = getBooleanValue(LEGACY_WSRF_REGISTRATION_ENABLED_PROMPT, LEGACY_WSRF_REGISTRATION_ENABLED_PROPERTY);
			props.setProperty(LEGACY_WSRF_REGISTRATION_ENABLED_PROPERTY, Boolean.valueOf(enableLegacyRegistration).toString());
		} else {
			props.setProperty(LEGACY_WSRF_REGISTRATION_ENABLED_PROPERTY, "false");
		}

		if (enableRegistration || enableLegacyRegistration) {
			props.setProperty(WSRF_REGISTRATION_URL_PROPERTY, getValue(WSRF_REGISTRATION_URL_PROMPT, WSRF_REGISTRATION_URL_PROPERTY));
		}

		File wsrfConfig = new File(getServiceMixEtc(), GROUPER_WSRF_CFG);
		props.store(new FileOutputStream(wsrfConfig), "GridGrouper WSRF Service Configuration saved by bootstrapper on " + new Date());
	}

	private boolean getBooleanValue(String prompt, String property) {
		String val = getValue(prompt, property);
		while ((val == null) || ((!val.equalsIgnoreCase("true")) && (!val.equalsIgnoreCase("false")))) {
			val = getValue(prompt, property);
		}
		return Boolean.valueOf(val).booleanValue();
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
			File hostPath = new File(this.grouperEtcDir.getAbsolutePath() + File.separator + LEGACY_WSRF_KEYSTORE_FILE_NAME);
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
		File hostPath = new File(this.grouperEtcDir.getAbsolutePath() + File.separator + WSRF_KEYSTORE_FILE_NAME);
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
		File f = new File(this.grouperEtcDir.getAbsolutePath() + File.separator + TRUSTSTORE_FILE_NAME);
		this.copyTrustStore(f.getAbsolutePath(), getTruststorePassword());
		System.out.println("Truststore created for GridGrouper at " + f.getAbsolutePath());
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
