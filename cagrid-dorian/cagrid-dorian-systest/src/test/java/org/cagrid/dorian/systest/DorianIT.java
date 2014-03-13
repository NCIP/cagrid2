package org.cagrid.dorian.systest;

import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.net.ssl.KeyManager;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
import org.cagrid.dorian.DoesLocalUserExistRequest;
import org.cagrid.dorian.DoesLocalUserExistResponse;
import org.cagrid.dorian.DorianPortType;
import org.cagrid.dorian.RequestUserCertificateRequest;
import org.cagrid.dorian.RequestUserCertificateRequest.Key;
import org.cagrid.dorian.RequestUserCertificateRequest.Lifetime;
import org.cagrid.dorian.RequestUserCertificateRequest.Saml;
import org.cagrid.dorian.RequestUserCertificateResponse;
import org.cagrid.dorian.model.federation.CertificateLifetime;
import org.cagrid.dorian.model.federation.PublicKey;
import org.cagrid.dorian.service.Dorian;
import org.cagrid.dorian.service.tools.DorianTestBootstrapper;
import org.cagrid.dorian.soapclient.DorianSoapClientFactory;
import org.cagrid.gaards.authentication.AuthenticateUserRequest;
import org.cagrid.gaards.authentication.AuthenticateUserRequest.Credential;
import org.cagrid.gaards.authentication.AuthenticateUserResponse;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.systest.ContextLoader;
import org.cagrid.systest.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.oasis.names.tc.saml.assertion.AssertionType;
import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.osgi.framework.Bundle;

public class DorianIT extends TestBase {

	private final static String USERNAME = "dorian";
	private final static String PASSWORD = "DorianAdmin$1";

	@Inject
	private Dorian dorian;

	@Override
	protected void prePAX() {
		DorianTestBootstrapper dorianTestBootstrapper = null;
		try {
			dorianTestBootstrapper = new DorianTestBootstrapper();
			dorianTestBootstrapper.createKeyAndTrustStores();
		} catch (Exception e) {
			throw new RuntimeException("Exception bootstrapping Dorian", e);
		} finally {
			dorianTestBootstrapper.close();
		}
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Class> getAdditionalClasses() {
		return Collections.singletonList((Class) DorianTestBootstrapper.class);
	}

	@Override
	public List<Option> getTestBundles() {
		List<Option> options = new ArrayList<Option>();
		String featureVersion = MavenUtils.getArtifactVersion("org.cagrid",
				"cagrid-features");
		String featureURL = "mvn:org.cagrid/cagrid-features/" + featureVersion
				+ "/xml/features";
		options.add(features(featureURL, "cagrid-dorian"));
		return options;
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
		truststore.setFile(karafBase + "/etc/cagrid-dorian/truststore.jks");
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
}
