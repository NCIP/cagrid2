package org.cagrid.gridgrouper.inttest;

import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.net.ssl.KeyManager;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
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
import org.cagrid.gridgrouper.model.StemDescriptor;
import org.cagrid.gridgrouper.model.StemIdentifier;
import org.cagrid.gridgrouper.service.GridGrouperService;
import org.cagrid.gridgrouper.soapclient.GridGrouperSoapClientFactory;
import org.cagrid.gridgrouper.wsrf.stubs.GetStemRequest;
import org.cagrid.gridgrouper.wsrf.stubs.GetStemRequest.Stem;
import org.cagrid.gridgrouper.wsrf.stubs.GetStemResponse;
import org.cagrid.gridgrouper.wsrf.stubs.GridGrouperPortType;
import org.cagrid.systest.ContextLoader;
import org.cagrid.systest.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.oasis.names.tc.saml.assertion.AssertionType;
import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.osgi.framework.Bundle;

public class GrouperIT extends TestBase {

	private final static String USERNAME = "dorian";
	private final static String PASSWORD = "DorianAdmin$1";

	@Inject
	private Dorian dorian;

	@Inject
	private GridGrouperService grouper;

	@Override
	protected void prePAX() {
		DorianBootstrap dorianBootstrap = null;
		try {
			dorianBootstrap = new DorianBootstrap();
			dorianBootstrap.createKeyAndTrustStores();
		} catch (Exception e) {
			throw new RuntimeException("Exception bootstrapping Dorian", e);
		} finally {
			dorianBootstrap.close();
		}
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Class> getAdditionalClasses() {
		List<Class> additionalClasses = new ArrayList<Class>(2);
		additionalClasses.add(DorianBootstrap.class);
		additionalClasses.add(UserInfo.class);
		return additionalClasses;
	}

	@Override
	public List<Option> getTestBundles() {
		List<Option> options = new ArrayList<Option>();
		String featureVersion = MavenUtils.getArtifactVersion("org.cagrid",
				"cagrid-features");
		String featureURL = "mvn:org.cagrid/cagrid-features/" + featureVersion
				+ "/xml/features";
		options.add(features(featureURL, "cagrid-dorian", "cagrid-gridgrouper"));
		return options;
	}

	@Test
	public void testCDS() throws Exception {
		Assert.assertNotNull(bundleContext);

		for (Bundle bundle : bundleContext.getBundles()) {
			String bundleState = bundleStates.get(bundle.getState());
			System.out.println(bundle.getBundleId() + ": "
					+ bundle.getSymbolicName() + " - " + bundle.getLocation()
					+ " [" + bundleState + "]");
		}

		final String dorianURL = "https://localhost:7734/dorian";
		final String grouperURL = "https://localhost:7738/gridgrouper";

		Assert.assertNotNull(dorian);
		Assert.assertNotNull(grouper);

		String karafBase = System.getProperty(ContextLoader.KARAF_BASE_KEY);
		KeyStoreType truststore = new KeyStoreType();
		truststore.setFile(karafBase + "/etc/dorian/truststore.jks");
		truststore.setType("JKS");
		truststore.setPassword("changeit");

		DorianPortType dorianSoapAnon = DorianSoapClientFactory
				.createSoapClient(dorianURL, truststore, (KeyManager) null);

		UserInfo adminUserInfo = login(dorianSoapAnon, USERNAME, PASSWORD);
		Assert.assertNotNull(adminUserInfo.x509Certificate);

		KeyManager keyManager = new SingleEntityKeyManager("client",
				new X509Certificate[] { adminUserInfo.x509Certificate },
				adminUserInfo.privateKey);

		GridGrouperPortType grouperAuth = GridGrouperSoapClientFactory
				.createSoapClient(grouperURL, truststore, keyManager);
		StemIdentifier stemIdentifier = new StemIdentifier();
		stemIdentifier.setStemName("grouperadministration");
		Stem stem = new Stem();
		stem.setStemIdentifier(stemIdentifier);
		GetStemRequest getStemRequest = new GetStemRequest();
		getStemRequest.setStem(stem);
		GetStemResponse getStemResponse = grouperAuth.getStem(getStemRequest);
		StemDescriptor stemDescriptor = getStemResponse.getStemDescriptor();
		Assert.assertNotNull(stemDescriptor);

		System.out.println("!!! step display name: "
				+ stemDescriptor.getDisplayName());
	}

	private UserInfo login(DorianPortType dorianSoap, String userId,
			String password) throws Exception {

		BasicAuthentication basicAuthentication = new BasicAuthentication();
		basicAuthentication.setUserId(userId);
		basicAuthentication.setPassword(password);
		Credential credential = new Credential();
		credential.setCredential(basicAuthentication);
		AuthenticateUserRequest authenticateUserRequest = new AuthenticateUserRequest();
		authenticateUserRequest.setCredential(credential);
		AuthenticateUserResponse authenticateUserResponse = dorianSoap
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
		RequestUserCertificateResponse requestUserCertificateResponse = dorianSoap
				.requestUserCertificate(userCertificateRequest);
		String certificateString = requestUserCertificateResponse
				.getX509Certificate().getCertificateAsString();
		X509Certificate x509Certificate = CertUtil
				.loadCertificate(certificateString);

		UserInfo userInfo = new UserInfo(null, x509Certificate,
				keyPair.getPrivate());
		return userInfo;
	}
}
