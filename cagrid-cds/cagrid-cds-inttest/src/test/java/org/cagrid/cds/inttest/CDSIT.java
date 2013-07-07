package org.cagrid.cds.inttest;

import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;

import java.io.File;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.net.ssl.KeyManager;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.cds.model.AllowedParties;
import org.cagrid.cds.model.CertificateChain;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.DelegationRequest;
import org.cagrid.cds.model.DelegationSigningRequest;
import org.cagrid.cds.model.DelegationSigningResponse;
import org.cagrid.cds.model.IdentityDelegationPolicy;
import org.cagrid.cds.model.ProxyLifetime;
import org.cagrid.cds.service.CredentialDelegationService;
import org.cagrid.cds.soapclient.CDSSoapClientFactory;
import org.cagrid.cds.util.Utils;
import org.cagrid.cds.wsrf.stubs.ApproveDelegationRequest;
import org.cagrid.cds.wsrf.stubs.ApproveDelegationResponse;
import org.cagrid.cds.wsrf.stubs.CredentialDelegationServicePortType;
import org.cagrid.cds.wsrf.stubs.InitiateDelegationRequest;
import org.cagrid.cds.wsrf.stubs.InitiateDelegationRequest.Req;
import org.cagrid.cds.wsrf.stubs.InitiateDelegationResponse;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
import org.cagrid.delegatedcredential.types.DelegatedCredentialReference;
import org.cagrid.dorian.DorianPortType;
import org.cagrid.dorian.FindGridUsersRequest;
import org.cagrid.dorian.FindGridUsersRequest.Filter;
import org.cagrid.dorian.FindGridUsersResponse;
import org.cagrid.dorian.FindLocalUsersRequest;
import org.cagrid.dorian.FindLocalUsersResponse;
import org.cagrid.dorian.RegisterLocalUserRequest;
import org.cagrid.dorian.RequestUserCertificateRequest;
import org.cagrid.dorian.RequestUserCertificateRequest.Key;
import org.cagrid.dorian.RequestUserCertificateRequest.Lifetime;
import org.cagrid.dorian.RequestUserCertificateRequest.Saml;
import org.cagrid.dorian.RequestUserCertificateResponse;
import org.cagrid.dorian.UpdateLocalUserRequest;
import org.cagrid.dorian.UpdateLocalUserRequest.User;
import org.cagrid.dorian.idp.Application;
import org.cagrid.dorian.idp.CountryCode;
import org.cagrid.dorian.idp.LocalUser;
import org.cagrid.dorian.idp.LocalUserFilter;
import org.cagrid.dorian.idp.LocalUserStatus;
import org.cagrid.dorian.idp.StateCode;
import org.cagrid.dorian.ifs.CertificateLifetime;
import org.cagrid.dorian.ifs.GridUser;
import org.cagrid.dorian.ifs.GridUserFilter;
import org.cagrid.dorian.ifs.PublicKey;
import org.cagrid.dorian.service.Dorian;
import org.cagrid.dorian.service.tools.DorianTestBootstrapper;
import org.cagrid.dorian.soapclient.DorianSoapClientFactory;
import org.cagrid.gaards.authentication.AuthenticateUserRequest;
import org.cagrid.gaards.authentication.AuthenticateUserRequest.Credential;
import org.cagrid.gaards.authentication.AuthenticateUserResponse;
import org.cagrid.gaards.authentication.BasicAuthentication;
import org.cagrid.gaards.pki.CertUtil;
import org.cagrid.gaards.pki.KeyUtil;
import org.cagrid.gaards.pki.ProxyCreator;
import org.cagrid.systest.ContextLoader;
import org.cagrid.systest.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.oasis.names.tc.saml.assertion.AssertionType;
import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.osgi.framework.Bundle;

public class CDSIT extends TestBase {

	private final static String USERNAME = "dorian";
	private final static String PASSWORD = "DorianAdmin$1";

	@Inject
	private Dorian dorian;

	@Inject
	CredentialDelegationService cds;

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
		List<Class> additionalClasses = new ArrayList<Class>(2);
		additionalClasses.add(DorianTestBootstrapper.class);
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
		options.add(features(featureURL, "cagrid-dorian", "cagrid-gridgrouper",
				"cagrid-cds"));
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

		System.out.println("X509_CERT_DIR=" + System.getProperty("X509_CERT_DIR"));
		for (File certFile : new File(System.getProperty("X509_CERT_DIR")).listFiles()) {
			System.out.println(certFile);
		}

		final String dorianURL = "https://localhost:7734/dorian";
		final String cdsURL = "https://localhost:7736/cds";

		Assert.assertNotNull(dorian);
		Assert.assertNotNull(cds);

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
		DorianPortType dorianSoapAuth = DorianSoapClientFactory
				.createSoapClient(dorianURL, truststore, keyManager);

		UserInfo endUserInfo = createLocalUser(dorianSoapAuth, "endUser", "End");
		Assert.assertNotNull(endUserInfo.gridId);

		UserInfo proxyUserInfo = createLocalUser(dorianSoapAuth, "proxyUser",
				"Proxy");
		Assert.assertNotNull(proxyUserInfo.gridId);

		KeyManager endKeyManager = new SingleEntityKeyManager("client",
				new X509Certificate[] { endUserInfo.x509Certificate },
				endUserInfo.privateKey);
		CredentialDelegationServicePortType cdsSoapEnd = CDSSoapClientFactory
				.createSoapClient(cdsURL, truststore, endKeyManager);

		AllowedParties allowedParties = new AllowedParties();
		allowedParties.getGridIdentity().add(proxyUserInfo.gridId);
		IdentityDelegationPolicy delegationPolicy = new IdentityDelegationPolicy();
		delegationPolicy.setAllowedParties(allowedParties);
		DelegationRequest delegationRequest = new DelegationRequest();
		delegationRequest.setDelegationPolicy(delegationPolicy);
		ProxyLifetime proxyLifetime = new ProxyLifetime();
		proxyLifetime.setHours(6);
		delegationRequest.setIssuedCredentialLifetime(proxyLifetime);
		delegationRequest.setIssuedCredentialPathLength(0);
		delegationRequest.setKeyLength(2048);
		Req req = new InitiateDelegationRequest.Req();
		req.setDelegationRequest(delegationRequest);
		InitiateDelegationRequest initiateDelegationRequest = new InitiateDelegationRequest();
		initiateDelegationRequest.setReq(req);
		InitiateDelegationResponse initiateDelegationResponse = cdsSoapEnd
				.initiateDelegation(initiateDelegationRequest);
		DelegationSigningRequest delegationSigningRequest = initiateDelegationResponse
				.getDelegationSigningRequest();
		Assert.assertNotNull(delegationSigningRequest);

		DelegationIdentifier delegationIdentifier = delegationSigningRequest
				.getDelegationIdentifier();
		org.cagrid.cds.model.PublicKey cdsPublicKey = delegationSigningRequest.getPublicKey();
		KeyUtil.loadPublicKey(cdsPublicKey.getKeyAsString());
		X509Certificate[] cdsX509Certificates = ProxyCreator
				.createImpersonationProxyCertificate(
						endUserInfo.x509Certificate, endUserInfo.privateKey,
						KeyUtil.loadPublicKey(cdsPublicKey.getKeyAsString()),
						3, 0, 0);
		CertificateChain cdsX509CertificateChain = Utils.toCertificateChain(cdsX509Certificates);
		DelegationSigningResponse delegationSigningResponse = new DelegationSigningResponse();
		delegationSigningResponse.setDelegationIdentifier(delegationIdentifier);
		delegationSigningResponse.setCertificateChain(cdsX509CertificateChain);
		ApproveDelegationRequest.DelegationSigningResponse _delegationSigningResponse = new ApproveDelegationRequest.DelegationSigningResponse();
		_delegationSigningResponse.setDelegationSigningResponse(delegationSigningResponse);
		ApproveDelegationRequest approveDelegationRequest = new ApproveDelegationRequest();
		approveDelegationRequest.setDelegationSigningResponse(_delegationSigningResponse);
		ApproveDelegationResponse approveDelegationResponse = cdsSoapEnd.approveDelegation(approveDelegationRequest);
		DelegatedCredentialReference delegatedCredentialReference = approveDelegationResponse.getDelegatedCredentialReference();
		Assert.assertNotNull(delegatedCredentialReference);
		
		System.out.println(delegatedCredentialReference.getEndpointReference());
	}

	private UserInfo createLocalUser(DorianPortType dorianSoap, String userId,
			String firstName) throws Exception {
		final String password = "$D0ct0rC0de$";

		Application application = new Application();
		application.setUserId(userId);
		application.setPassword(password);
		application.setFirstName(firstName);
		application.setLastName("User");
		application.setEmail(firstName + ".User@test.org");
		application.setAddress("123 Fake St.");
		application.setCity("Columbus");
		application.setState(StateCode.OH);
		application.setCountry(CountryCode.US);
		application.setZipcode("43210");
		application.setPhoneNumber("614-555-5555");
		application.setOrganization("organization");
		RegisterLocalUserRequest.A a = new RegisterLocalUserRequest.A();
		a.setApplication(application);
		RegisterLocalUserRequest registerLocalUserRequest = new RegisterLocalUserRequest();
		registerLocalUserRequest.setA(a);
		dorianSoap.registerLocalUser(registerLocalUserRequest);

		LocalUserFilter localUserFilter = new LocalUserFilter();
		localUserFilter.setUserId(userId);
		FindLocalUsersRequest.F f = new FindLocalUsersRequest.F();
		f.setLocalUserFilter(localUserFilter);
		FindLocalUsersRequest findLocalUsersRequest = new FindLocalUsersRequest();
		findLocalUsersRequest.setF(f);
		FindLocalUsersResponse findLocalUsersResponse = dorianSoap
				.findLocalUsers(findLocalUsersRequest);
		List<LocalUser> localUsers = findLocalUsersResponse.getLocalUser();
		if (localUsers.size() != 1) {
			throw new Exception("findLocalUsers returned " + localUsers.size()
					+ " users!");
		}

		LocalUser localUser = localUsers.get(0);
		localUser.setStatus(LocalUserStatus.ACTIVE);
		User user = new User();
		user.setLocalUser(localUser);
		UpdateLocalUserRequest updateLocalUserRequest = new UpdateLocalUserRequest();
		updateLocalUserRequest.setUser(user);
		dorianSoap.updateLocalUser(updateLocalUserRequest);

		UserInfo userInfo0 = login(dorianSoap, userId, password);

		GridUserFilter gridUserFilter = new GridUserFilter();
		gridUserFilter.setUID(userId);
		Filter filter = new Filter();
		filter.setGridUserFilter(gridUserFilter);
		FindGridUsersRequest findGridUsersRequest = new FindGridUsersRequest();
		findGridUsersRequest.setFilter(filter);
		FindGridUsersResponse findGridUsersResponse = dorianSoap
				.findGridUsers(findGridUsersRequest);
		List<GridUser> gridUsers = findGridUsersResponse.getGridUser();
		if (gridUsers.size() != 1) {
			throw new Exception("findGridUsers returned " + gridUsers.size()
					+ " users!");
		}

		GridUser gridUser = gridUsers.get(0);
		String gridId = gridUser.getGridId();

		UserInfo userInfo = new UserInfo(gridId, userInfo0.x509Certificate,
				userInfo0.privateKey);
		return userInfo;
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
