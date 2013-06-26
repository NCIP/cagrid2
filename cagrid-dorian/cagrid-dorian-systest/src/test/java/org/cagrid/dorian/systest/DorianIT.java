package org.cagrid.dorian.systest;

import static org.apache.karaf.tooling.exam.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.apache.karaf.tooling.exam.options.KarafDistributionOption.keepRuntimeFolder;
import static org.ops4j.pax.exam.CoreOptions.autoWrap;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.localRepository;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.scanFeatures;
import static org.ops4j.pax.exam.CoreOptions.vmOption;

import java.io.File;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.net.ssl.KeyManager;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileExtendOption;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFilePutOption;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
import org.cagrid.dorian.DoesLocalUserExistRequest;
import org.cagrid.dorian.DoesLocalUserExistResponse;
import org.cagrid.dorian.DorianPortType;
import org.cagrid.dorian.RequestUserCertificateRequest;
import org.cagrid.dorian.RequestUserCertificateRequest.Key;
import org.cagrid.dorian.RequestUserCertificateRequest.Lifetime;
import org.cagrid.dorian.RequestUserCertificateRequest.Saml;
import org.cagrid.dorian.RequestUserCertificateResponse;
import org.cagrid.dorian.ifs.CertificateLifetime;
import org.cagrid.dorian.ifs.PublicKey;
import org.cagrid.dorian.service.Dorian;
import org.cagrid.dorian.soapclient.DorianSoapClientFactory;
import org.cagrid.gaards.authentication.AuthenticateUserRequest;
import org.cagrid.gaards.authentication.AuthenticateUserRequest.Credential;
import org.cagrid.gaards.authentication.AuthenticateUserResponse;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oasis.names.tc.saml.assertion.AssertionType;
import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

@RunWith(JUnit4TestRunner.class)
public class DorianIT {

	private final static String USERNAME = "dorian";
	private final static String PASSWORD = "DorianAdmin$1";

	/**
	 * Debug system property, set to any value to enable.
	 */
	public final static String DEBUG_PROPERTY = "systest.debug";

	/**
	 * Debug suspend system property, set to 'y' or 'n' (or leave unset).
	 */
	public final static String DEBUG_SUSPEND_PROPERTY = "systest.suspend";

	private final Map<Integer, String> bundleStates = new HashMap<Integer, String>();

	@Inject
	private BundleContext bundleContext;

	@Inject
	private Dorian dorian;

	private DorianBootstrap dorianBootstrap = null;

	public DorianIT() {
		bundleStates.put(Bundle.UNINSTALLED, "UNINSTALLED");
		bundleStates.put(Bundle.INSTALLED, "INSTALLED");
		bundleStates.put(Bundle.RESOLVED, "RESOLVED");
		bundleStates.put(Bundle.STARTING, "STARTING");
		bundleStates.put(Bundle.STOPPING, "STOPPING");
		bundleStates.put(Bundle.ACTIVE, "ACTIVE");
	}

	@Configuration
	public Option[] config() {

		beforePAX();
		File karafBase = new File(
				System.getProperty(ContextLoader.KARAF_BASE_KEY));

		List<Option> options = new ArrayList<Option>();

		String localRepository = System.getProperty("maven.repo.local");
		System.out.println("!!! localRepository = " + localRepository);
		if (localRepository != null) {
			options.add(vmOption("-Dorg.ops4j.pax.url.mvn.localRepository=" + localRepository));
//			options.add(localRepository(localRepository));
		}
		options.add(autoWrap());

		if (System.getProperty(DEBUG_PROPERTY) != null) {
			options.add(vmOption("-agentlib:jdwp=transport=dt_socket,server=y,address=5005,suspend="
					+ System.getProperty(DEBUG_SUSPEND_PROPERTY, "n")));
			options.add(vmOption("-Dcom.sun.management.jmxremote.ssl=false"));
			options.add(vmOption("-Dcom.sun.management.jmxremote.authenticate=false"));
			options.add(vmOption("-Dcom.sun.management.jmxremote.port=5006"));
		}

		String karafVersion = MavenUtils.getArtifactVersion("org.apache.karaf",
				"apache-karaf");
		options.add(karafDistributionConfiguration()
				.frameworkUrl(
						maven("org.apache.servicemix", "apache-servicemix")
								.versionAsInProject().type("tar.gz"))
				.name("Apache ServiceMix").karafVersion(karafVersion)
				.unpackDirectory(karafBase));
//		options.add(new KarafDistributionConfigurationFileExtendOption(
//				"etc/org.apache.karaf.features.cfg", "featuresBoot",
//				",spring-jdbc,spring-orm"));
//		options.add(new KarafDistributionConfigurationFilePutOption(
//				"etc/config.properties", "karaf.framework", "equinox"));
//		options.add(new KarafDistributionConfigurationFileExtendOption(
//				"etc/jre.properties", "jre-1.7",
//				",javax.xml.soap;version=\"1.3\""));
		options.add(keepRuntimeFolder());
		options.add(junitBundles());

		options.addAll(dorianBootstrap.getFileOptions());

		String featureVersion = MavenUtils.getArtifactVersion("org.cagrid",
				"cagrid-features");
		String featureURL = "mvn:org.cagrid/cagrid-features/" + featureVersion
				+ "/xml/features";
		options.add(scanFeatures(featureURL, "cagrid-dorian"));

		return options(options.toArray(new Option[options.size()]));
	}

	@Test
	public void testDorian() throws Exception {
		Assert.assertNotNull(bundleContext);

		for (Bundle bundle : bundleContext.getBundles()) {
			String bundleState = bundleStates.get(bundle.getState());
			System.out.println(bundle.getBundleId() + ": "
					+ bundle.getSymbolicName() + " - " + bundle.getLocation()
					+ " [" + bundleState + "]");
		}

		Assert.assertNotNull(dorian);

		String karafBase = System.getProperty(ContextLoader.KARAF_BASE_KEY);
		KeyStoreType truststore = new KeyStoreType();
		truststore.setFile(karafBase + "/etc/dorian/truststore.jks");
		truststore.setType("JKS");
		truststore.setPassword("changeit");

		DorianPortType dorianSoapAnon = DorianSoapClientFactory
				.createSoapClient("https://localhost:7734/dorian", truststore,
						(KeyManager) null);

		BasicAuthentication basicAuthentication = new BasicAuthentication();
		basicAuthentication.setUserId(USERNAME);
		basicAuthentication.setPassword(PASSWORD);
		Credential credential = new Credential();
		credential.setCredential(basicAuthentication);
		AuthenticateUserRequest authenticateUserRequest = new AuthenticateUserRequest();
		authenticateUserRequest.setCredential(credential);
		AuthenticateUserResponse authenticateUserResponse = dorianSoapAnon
				.authenticateUser(authenticateUserRequest);
		AssertionType assertion = authenticateUserResponse.getAssertion();
		Assert.assertNotNull(assertion);

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
		Assert.assertNotNull(certificate);

		KeyManager keyManager = new SingleEntityKeyManager("client",
				new X509Certificate[] { certificate }, keyPair.getPrivate());
		DorianPortType dorianSoapAuth = DorianSoapClientFactory
				.createSoapClient("https://localhost:7734/dorian", truststore,
						keyManager);

		DoesLocalUserExistRequest doesLocalUserExistRequest = new DoesLocalUserExistRequest();
		doesLocalUserExistRequest.setUserId(USERNAME);
		DoesLocalUserExistResponse doesLocalUserExistResponse = dorianSoapAuth
				.doesLocalUserExist(doesLocalUserExistRequest);
		boolean localUserExists = doesLocalUserExistResponse.isResponse();
		Assert.assertTrue(localUserExists);

		doesLocalUserExistRequest.setUserId("not a user");
		doesLocalUserExistResponse = dorianSoapAuth
				.doesLocalUserExist(doesLocalUserExistRequest);
		localUserExists = doesLocalUserExistResponse.isResponse();
		Assert.assertFalse(localUserExists);
	}

	protected void beforePAX() {
		try {
			dorianBootstrap = new DorianBootstrap();
			dorianBootstrap.createKeyAndTrustStores();
		} catch (Exception e) {
			throw new RuntimeException("Exception bootstrapping Dorian", e);
		}
	}
}
