package org.cagrid.dorian.soapclient;

import java.security.KeyPair;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.common.JAXBUtils;
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
import org.cagrid.gaards.authentication.AuthenticateUserRequest;
import org.cagrid.gaards.authentication.AuthenticateUserRequest.Credential;
import org.cagrid.gaards.authentication.AuthenticateUserResponse;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.oasis.names.tc.saml.assertion.AssertionType;

public class LoginClient extends DorianClientBase {

	private final static String USERNAME = "dorian";
	private final static String PASSWORD = "DorianAdmin$1";

	public LoginClient() throws Exception {
		super(LOCALS_URL);
	}

	public AssertionType login1() throws Exception {
		BasicAuthentication basicAuthentication = new BasicAuthentication();
		basicAuthentication.setUserId(USERNAME);
		basicAuthentication.setPassword(PASSWORD);
		Credential credential = new Credential();
		credential.setCredential(basicAuthentication);
		AuthenticateUserRequest authenticateUserRequest = new AuthenticateUserRequest();
		authenticateUserRequest.setCredential(credential);
		AuthenticateUserResponse authenticateUserResponse = dorian
				.authenticateUser(authenticateUserRequest);
		AssertionType assertion = authenticateUserResponse.getAssertion();
		return assertion;
	}

	public X509Certificate login2(AssertionType assertion,
			java.security.PublicKey javaPublicKey) throws Exception {
		Saml saml = new Saml();
		saml.setAssertion(assertion);
		PublicKey publicKey = new PublicKey();
		publicKey.setKeyAsString(KeyUtil.writePublicKey(javaPublicKey));
		RequestUserCertificateRequest userCertificateRequest = new RequestUserCertificateRequest();
		userCertificateRequest.setSaml(saml);
		Key key = new Key();
		key.setPublicKey(publicKey);
		userCertificateRequest.setKey(key);
		CertificateLifetime certificateLifetime = new CertificateLifetime();
		certificateLifetime.setHours(6);
		Lifetime lifetime = new Lifetime();
		lifetime.setCertificateLifetime(certificateLifetime);
		userCertificateRequest.setLifetime(lifetime);
		RequestUserCertificateResponse requestUserCertificateResponse = dorian
				.requestUserCertificate(userCertificateRequest);
		System.out.println(JAXBUtils.marshal(requestUserCertificateResponse));
		String certificateString = requestUserCertificateResponse
				.getX509Certificate().getCertificateAsString();
		X509Certificate certificate = CertUtil
				.loadCertificate(certificateString);
		return certificate;
	}

	public static void main(String[] args) throws Exception {
//		System.setProperty("javax.net.debug", "ssl,handshake");

		LoginClient loginClient = new LoginClient();
		AssertionType assertion = loginClient.login1();

		String assertionXML = JAXBUtils.marshal(assertion);
		System.out.println(assertionXML);

		KeyPair keyPair = KeyUtil.generateRSAKeyPair(2048);
		X509Certificate certificate = loginClient.login2(assertion,
				keyPair.getPublic());
		System.out.println("!!! certificate: " + certificate);

		KeyStoreType truststore = new KeyStoreType();
		truststore.setUrl(LoginClient.class.getClassLoader()
				.getResource("truststore.jks").toString());
		truststore.setType("JKS");
		truststore.setPassword("changeit");

		KeyManager keyManager = new SingleEntityKeyManager("client",
				new X509Certificate[] { certificate }, keyPair.getPrivate());
		DorianPortType dorians = DorianSoapClientFactory.createSoapClient(
				LOCALS_URL, truststore, keyManager);

		DoesLocalUserExistRequest doesLocalUserExistRequest = new DoesLocalUserExistRequest();
		doesLocalUserExistRequest.setUserId("dorian");
		DoesLocalUserExistResponse doesLocalUserExistResponse = dorians
				.doesLocalUserExist(doesLocalUserExistRequest);
		boolean localUserExists = doesLocalUserExistResponse.isResponse();
		System.out.println("local user dorian exists? " + localUserExists);
	}
}
