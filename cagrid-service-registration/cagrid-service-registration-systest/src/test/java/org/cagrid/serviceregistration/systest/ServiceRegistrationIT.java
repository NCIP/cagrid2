package org.cagrid.serviceregistration.systest;

import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.cagrid.dorian.service.Dorian;
import org.cagrid.dorian.service.tools.DorianTestBootstrapper;
import org.cagrid.systest.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.osgi.framework.Bundle;

public class ServiceRegistrationIT extends TestBase {

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
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
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
		options.add(features(featureURL, "cagrid-service-registration"));
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

//		String karafBase = System.getProperty(ContextLoader.KARAF_BASE_KEY);
//		KeyStoreType truststore = new KeyStoreType();
//		truststore.setFile(karafBase + "/etc/dorian/truststore.jks");
//		truststore.setType("JKS");
//		truststore.setPassword("changeit");
//
//		DorianPortType dorianSoapAnon = DorianSoapClientFactory
//				.createSoapClient("https://localhost:7734/dorian", truststore,
//						(KeyManager) null);
//
//		BasicAuthentication basicAuthentication = new BasicAuthentication();
//		basicAuthentication.setUserId(USERNAME);
//		basicAuthentication.setPassword(PASSWORD);
//		Credential credential = new Credential();
//		credential.setCredential(basicAuthentication);
//		AuthenticateUserRequest authenticateUserRequest = new AuthenticateUserRequest();
//		authenticateUserRequest.setCredential(credential);
//		AuthenticateUserResponse authenticateUserResponse = dorianSoapAnon
//				.authenticateUser(authenticateUserRequest);
//		AssertionType assertion = authenticateUserResponse.getAssertion();
//		Assert.assertNotNull(assertion);
//
//		KeyPair keyPair = KeyUtil.generateRSAKeyPair(2048);
//		Saml saml = new Saml();
//		saml.setAssertion(assertion);
//		PublicKey caPublicKey = new PublicKey();
//		caPublicKey.setKeyAsString(KeyUtil.writePublicKey(keyPair.getPublic()));
//		RequestUserCertificateRequest userCertificateRequest = new RequestUserCertificateRequest();
//		userCertificateRequest.setSaml(saml);
//		Key caKey = new Key();
//		caKey.setPublicKey(caPublicKey);
//		userCertificateRequest.setKey(caKey);
//		CertificateLifetime certificateLifetime = new CertificateLifetime();
//		certificateLifetime.setHours(6);
//		Lifetime lifetime = new Lifetime();
//		lifetime.setCertificateLifetime(certificateLifetime);
//		userCertificateRequest.setLifetime(lifetime);
//		RequestUserCertificateResponse requestUserCertificateResponse = dorianSoapAnon
//				.requestUserCertificate(userCertificateRequest);
//		String certificateString = requestUserCertificateResponse
//				.getX509Certificate().getCertificateAsString();
//		X509Certificate certificate = CertUtil
//				.loadCertificate(certificateString);
//		Assert.assertNotNull(certificate);
//
//		KeyManager keyManager = new SingleEntityKeyManager("client",
//				new X509Certificate[] { certificate }, keyPair.getPrivate());
//		DorianPortType dorianSoapAuth = DorianSoapClientFactory
//				.createSoapClient("https://localhost:7734/dorian", truststore,
//						keyManager);
//
//		DoesLocalUserExistRequest doesLocalUserExistRequest = new DoesLocalUserExistRequest();
//		doesLocalUserExistRequest.setUserId(USERNAME);
//		DoesLocalUserExistResponse doesLocalUserExistResponse = dorianSoapAuth
//				.doesLocalUserExist(doesLocalUserExistRequest);
//		boolean localUserExists = doesLocalUserExistResponse.isResponse();
//		Assert.assertTrue(localUserExists);
//
//		doesLocalUserExistRequest.setUserId("not a user");
//		doesLocalUserExistResponse = dorianSoapAuth
//				.doesLocalUserExist(doesLocalUserExistRequest);
//		localUserExists = doesLocalUserExistResponse.isResponse();
//		Assert.assertFalse(localUserExists);
////		
//		ServiceGroupRegistrationClient client = new ServiceGroupRegistrationClient();
//		try {
//			EndpointReferenceType epr = new EndpointReferenceType();
//			AttributedURI uri = new AttributedURI();
//			uri.setValue("https://localhost:7734/dorian");
//			epr.setAddress(uri);
//			ServiceGroupRegistrationParameters params = ServiceGroupRegistrationClient.readParams(karafBase + "/etc/dorian/Dorian_registration.xml");
//			params.setRegistrantEPR(epr);
//			client.register(params);
//			Thread.sleep(30000);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		DorianPortType dorianSoapAuth2 = DorianSoapClientFactory
//				.createSoapClient("https://localhost:7734/dorian", truststore,
//						(KeyManager)null);
//		
//		
//		GetMultipleResourceProperties request = new GetMultipleResourceProperties();
//		request.getResourceProperty().add(new QName("gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata","ServiceMetadata"));
//		GetMultipleResourcePropertiesResponse response = dorianSoapAuth2.getMultipleResourceProperties(request);
//		System.out.println(response.getAny().get(0));
//		
//		JAXBContext jc = JAXBContext.newInstance(((JAXBElement)response.getAny().get(0)).getValue().getClass());  
//        Marshaller marshaller = jc.createMarshaller();  
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); 
//        marshaller.marshal(response.getAny().get(0), System.out);
	}
}
