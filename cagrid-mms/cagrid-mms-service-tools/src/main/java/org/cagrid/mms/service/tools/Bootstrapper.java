package org.cagrid.mms.service.tools;

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
	private static final String MMS_SERVICE_DIR = "cagrid-mms";
	private static final String MMS_SERVICE_CFG = "cagrid.mms.service.cfg";
	private static final String MMS_WSRF_CFG = "cagrid.mms.wsrf.cfg";

	public static final String CONFIGURE_LEGACY_WSRF_PROMPT = "Do you want to configure a Legacy WSRF Endpoint (true|false)";
	public static final String CONFIGURE_LEGACY_WSRF_PROPERTY = "cagrid.mms.configure.legacy.wsrf";

	private static final String WSRF_INDEXSVC_PROMPT = "Please enter index service endpoint";
	private static final String WSRF_INDEXSVC_PROPERTY = "cagrid.mms.wsrf.registration.index.url";
	private static final String WSRF_HOSTNAME_PROMPT = "Please enter a hostname for the WSRF endpoint";
	private static final String WSRF_HOSTNAME_PROPERTY = "cagrid.mms.wsrf.host";
	public static final String WSRF_PORT_PROMPT = "Enter a port number for the WSRF service";
	public static final String WSRF_PORT_PROPERTY = "cagrid.mms.wsrf.port";
	public static final String WSRF_URL_PROPERTY = "cagrid.mms.wsrf.url";
	private static final String WSRF_URL_PATH_PROMPT = "Please enter the url path of the WSRF endpoint";
	private static final String WSRF_URL_PATH_PROPERTY = "cagrid.mms.wsrf.url.path";

	private static final String LEGACY_WSRF_HOSTNAME_PROMPT = "Please enter a legacy hostname";
	private static final String LEGACY_WSRF_HOSTNAME_PROPERTY = "cagrid.mms.legacy-wsrf.host";
	private static final String LEGACY_WSRF_URL_PATH_PROMPT = "Please enter the url path of the legacy WSRF endpoint";
	private static final String LEGACY_WSRF_URL_PATH_PROPERTY = "cagrid.mms.legacy-wsrf.url.path";
	private static final String LEGACY_WSRF_URL_PROPERTY = "cagrid.mms.legacy-wsrf.url";
	private static final String LEGACY_WSRF_PORT_PROMPT = "Enter a port number for the legacy WSRF service";
	private static final String LEGACY_WSRF_PORT_PROPERTY = "cagrid.mms.legacy-wsrf.port";

	private Boolean configureLegacyWSRF;
	private File mmsEtcDir;
	private String hostname;
	private String legacyHostname;

	public Bootstrapper(File propertiesFile) throws Exception {
		super(propertiesFile);
	}

	public Bootstrapper(Properties properties) throws Exception {
		super(properties);
	}

	@Override
	public void execute() throws Exception {
		System.out
				.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out
				.println("*           Metadata Model Service Bootstrapper             *");
		System.out
				.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
		System.out.println("");
		mmsEtcDir = new File(getServiceMixEtc().getAbsolutePath()
				+ File.separator + MMS_SERVICE_DIR);
		mmsEtcDir.mkdirs();
		configureMetadataModelService();
		configureWSRFService();

	}

	private void configureMetadataModelService() throws Exception {
		Properties props = new Properties();
		File config = new File(getServiceMixEtc(), MMS_SERVICE_CFG);
		props.store(new FileOutputStream(config),
				"Metadata Model Service Configuration saved by bootstrapper on "
						+ new Date());
	}

	private void configureWSRFService() throws Exception {
		Properties props = new Properties();
		props.setProperty(WSRF_HOSTNAME_PROPERTY, getHostname());
		String port = getValue(WSRF_PORT_PROMPT, WSRF_PORT_PROPERTY);
		props.setProperty(WSRF_PORT_PROPERTY, port);

		String url = "http://" + getHostname() + ":" + port + "/"
				+ getValue(WSRF_URL_PATH_PROMPT, WSRF_URL_PATH_PROPERTY);
		props.setProperty(WSRF_URL_PROPERTY, url);
		props.setProperty(WSRF_INDEXSVC_PROPERTY,
				getValue(WSRF_INDEXSVC_PROMPT, WSRF_INDEXSVC_PROPERTY));

		if (this.configureLegacyWSRF()) {
			props.setProperty(LEGACY_WSRF_HOSTNAME_PROPERTY,
					getLegacyHostname());
			String legacyPort = getValue(LEGACY_WSRF_PORT_PROMPT,
					LEGACY_WSRF_PORT_PROPERTY);
			props.setProperty(LEGACY_WSRF_PORT_PROPERTY, legacyPort);
			String legacyURL = "http://"
					+ getLegacyHostname()
					+ ":"
					+ legacyPort
					+ "/"
					+ getValue(LEGACY_WSRF_URL_PATH_PROMPT,
							LEGACY_WSRF_URL_PATH_PROPERTY);
			props.setProperty(LEGACY_WSRF_URL_PROPERTY, legacyURL);
		}

		File wsrfConfig = new File(getServiceMixEtc(), MMS_WSRF_CFG);
		props.store(new FileOutputStream(wsrfConfig),
				"Metadata Model WSRF Service Configuration saved by bootstrapper on "
						+ new Date());
	}

	public boolean configureLegacyWSRF() {
		if (configureLegacyWSRF == null) {
			configureLegacyWSRF = Boolean.valueOf(getValue(
					CONFIGURE_LEGACY_WSRF_PROMPT,
					CONFIGURE_LEGACY_WSRF_PROPERTY));
		}
		return configureLegacyWSRF;
	}

	public String getHostname() {
		if (hostname == null) {
			hostname = getValue(WSRF_HOSTNAME_PROMPT, WSRF_HOSTNAME_PROPERTY);
		}
		return hostname;
	}

	public String getLegacyHostname() {
		if (legacyHostname == null) {
			legacyHostname = getValue(LEGACY_WSRF_HOSTNAME_PROMPT,
					LEGACY_WSRF_HOSTNAME_PROPERTY);
		}
		return legacyHostname;
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
