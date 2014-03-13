package org.cagrid.cds.inttest;

import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;

import java.io.File;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.net.ssl.KeyManager;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
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
import org.cagrid.cds.soapclient.DCSSoapClientFactory;
import org.cagrid.cds.util.Utils;
import org.cagrid.cds.wsrf.stubs.ApproveDelegationRequest;
import org.cagrid.cds.wsrf.stubs.ApproveDelegationResponse;
import org.cagrid.cds.wsrf.stubs.CredentialDelegationServicePortType;
import org.cagrid.cds.wsrf.stubs.InitiateDelegationRequest;
import org.cagrid.cds.wsrf.stubs.InitiateDelegationRequest.Req;
import org.cagrid.cds.wsrf.stubs.InitiateDelegationResponse;
import org.cagrid.core.common.JAXBUtils;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
import org.cagrid.delegatedcredential.types.DelegatedCredentialReference;
import org.cagrid.delegatedcredential.wsrf.stubs.DelegatedCredentialPortType;
import org.cagrid.delegatedcredential.wsrf.stubs.GetDelegatedCredentialRequest;
import org.cagrid.delegatedcredential.wsrf.stubs.GetDelegatedCredentialResponse;
import org.cagrid.delegatedcredential.wsrf.stubs.PermissionDeniedFaultFaultMessage;
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
import org.cagrid.dorian.model.federation.CertificateLifetime;
import org.cagrid.dorian.model.federation.GridUser;
import org.cagrid.dorian.model.federation.GridUserFilter;
import org.cagrid.dorian.model.federation.PublicKey;
import org.cagrid.dorian.model.idp.Application;
import org.cagrid.dorian.model.idp.CountryCode;
import org.cagrid.dorian.model.idp.LocalUser;
import org.cagrid.dorian.model.idp.LocalUserFilter;
import org.cagrid.dorian.model.idp.LocalUserStatus;
import org.cagrid.dorian.model.idp.StateCode;
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
import org.w3c.dom.Node;
import org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType;

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
            if (dorianTestBootstrapper != null) {
                dorianTestBootstrapper.close();
            }
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
        String featureVersion = MavenUtils.getArtifactVersion("org.cagrid", "cagrid-features");
        String featureURL = "mvn:org.cagrid/cagrid-features/" + featureVersion + "/xml/features";
        options.add(features(featureURL, "cagrid-dorian", "cagrid-gridgrouper", "cagrid-cds"));
        return options;
    }

    /*
     * What actually needs to be tested: create 3 users (a,b,c) login as a, identity-delegate to b try to get delegated cred as c (should fail) login as b, get
     * delegated cred a destroy the delegated cred, b try to get the delegated cred again (should fail)
     * 
     * do the same thing with group delegation
     */

    @Test
    public void testCDS() throws Exception {

        Assert.assertNotNull(bundleContext);

        for (Bundle bundle : bundleContext.getBundles()) {
            String bundleState = bundleStates.get(bundle.getState());
            System.out.println(bundle.getBundleId() + ": " + bundle.getSymbolicName() + " - " + bundle.getLocation() + " [" + bundleState + "]");
        }

        System.out.println("X509_CERT_DIR=" + System.getProperty("X509_CERT_DIR"));
        for (File certFile : new File(System.getProperty("X509_CERT_DIR")).listFiles()) {
            System.out.println(certFile);
        }

        final String dorianURL = "https://localhost:7734/dorian";
        final String cdsURL = "https://localhost:7736/cds";
        final String dcsURL = "https://localhost:7736/DelegatedCredential";

        Assert.assertNotNull(dorian);
        Assert.assertNotNull(cds);

        String karafBase = System.getProperty(ContextLoader.KARAF_BASE_KEY);
        KeyStoreType truststore = new KeyStoreType();
        truststore.setFile(karafBase + "/etc/cagrid-orian/truststore.jks");
        truststore.setType("JKS");
        truststore.setPassword("changeit");

        // login as Dorian admin using the anon soap client
        DorianPortType dorianSoapAnon = DorianSoapClientFactory.createSoapClient(dorianURL, truststore, (KeyManager) null);
        UserInfo adminUserInfo = login(dorianSoapAnon, USERNAME, PASSWORD);
        Assert.assertNotNull(adminUserInfo.x509Certificate);

        // make a new soap client using the X.509 obtained from logging in
        KeyManager keyManager = new SingleEntityKeyManager("client", new X509Certificate[] { adminUserInfo.x509Certificate }, adminUserInfo.privateKey);
        DorianPortType dorianSoapAuth = DorianSoapClientFactory.createSoapClient(dorianURL, truststore, keyManager);

        // Create the users as the Dorian Admin
        // Delegator delegates to Delegatee; BadUser shouldn't be able to access anything
        UserInfo endUserInfoDelegator = createLocalUser(dorianSoapAuth, "Delegator", "Delegator");
        Assert.assertNotNull(endUserInfoDelegator.gridId);
        UserInfo proxyUserInfo = createLocalUser(dorianSoapAuth, "Delgatee", "Delgatee");
        Assert.assertNotNull(proxyUserInfo.gridId);
        UserInfo endUserInfoBad = createLocalUser(dorianSoapAuth, "BadUser", "BadUser");
        Assert.assertNotNull(endUserInfoBad.gridId);

        // create a CDS soap client for the delegator
        KeyManager endKeyManager = new SingleEntityKeyManager("client", new X509Certificate[] { endUserInfoDelegator.x509Certificate },
                endUserInfoDelegator.privateKey);
        CredentialDelegationServicePortType delegatorCDSSoapEnd = CDSSoapClientFactory.createSoapClient(cdsURL, truststore, endKeyManager);

        // send CDS the delegation policy (delegatee can become delegator)
        InitiateDelegationRequest initiateDelegationRequest = createDelegationPolicy(proxyUserInfo);
        InitiateDelegationResponse initiateDelegationResponse = delegatorCDSSoapEnd.initiateDelegation(initiateDelegationRequest);
        DelegationSigningRequest delegationSigningRequest = initiateDelegationResponse.getDelegationSigningRequest();
        Assert.assertNotNull(delegationSigningRequest);

        // approve the delegation policy, signing the cert from the initiate request using the delegator's private key
        DelegationIdentifier delegationIdentifier = delegationSigningRequest.getDelegationIdentifier();
        System.out.println("Delegation Id:" + delegationIdentifier);
        org.cagrid.cds.model.PublicKey cdsPublicKey = delegationSigningRequest.getPublicKey();
        // java.security.PublicKey publicKey = KeyUtil.loadPublicKey(cdsPublicKey.getKeyAsString());
        X509Certificate[] cdsX509Certificates = ProxyCreator.createImpersonationProxyCertificate(endUserInfoDelegator.x509Certificate,
                endUserInfoDelegator.privateKey, KeyUtil.loadPublicKey(cdsPublicKey.getKeyAsString()), 3, 0, 0);
        CertificateChain cdsX509CertificateChain = Utils.toCertificateChain(cdsX509Certificates);
        DelegationSigningResponse delegationSigningResponse = new DelegationSigningResponse();
        delegationSigningResponse.setDelegationIdentifier(delegationIdentifier);
        delegationSigningResponse.setCertificateChain(cdsX509CertificateChain);
        ApproveDelegationRequest.DelegationSigningResponse _delegationSigningResponse = new ApproveDelegationRequest.DelegationSigningResponse();
        _delegationSigningResponse.setDelegationSigningResponse(delegationSigningResponse);
        ApproveDelegationRequest approveDelegationRequest = new ApproveDelegationRequest();
        approveDelegationRequest.setDelegationSigningResponse(_delegationSigningResponse);
        ApproveDelegationResponse approveDelegationResponse = delegatorCDSSoapEnd.approveDelegation(approveDelegationRequest);
        DelegatedCredentialReference delegatedCredentialReference = approveDelegationResponse.getDelegatedCredentialReference();
        Assert.assertNotNull(delegatedCredentialReference);

        EndpointReferenceType dcEPR = delegatedCredentialReference.getEndpointReference();
        //System.out.println(JAXBUtils.marshal(dcEPR));

        /**
         * Delegatee using the dcEPR should pass
         */
        KeyManager delegateeKeyManager = new SingleEntityKeyManager("client", new X509Certificate[] { proxyUserInfo.x509Certificate }, proxyUserInfo.privateKey);
        DelegatedCredentialPortType dcs = DCSSoapClientFactory.createSoapClient(dcsURL, truststore, delegateeKeyManager);
        GetDelegatedCredentialRequest dcr = new GetDelegatedCredentialRequest();
        dcr.setPublicKey(new GetDelegatedCredentialRequest.PublicKey());
        dcr.getPublicKey().setPublicKey(cdsPublicKey);

        ((BindingProvider) dcs).getRequestContext().put(Header.HEADER_LIST, makeDelegationIdHeader(dcEPR));
        GetDelegatedCredentialResponse cred = dcs.getDelegatedCredential(dcr);
        Assert.assertNotNull(cred);
        Assert.assertNotNull(cred.getCertificateChain());
        List<org.cagrid.cds.model.X509Certificate> certChain = cred.getCertificateChain().getX509Certificate();
        Assert.assertNotNull(certChain);
        Assert.assertEquals(3, certChain.size());
        // last cert should be the delegator
        X509Certificate x509Certificate = CertUtil.loadCertificate(certChain.get(2).getCertificateAsString());
        Assert.assertEquals(endUserInfoDelegator.x509Certificate.getIssuerDN(), x509Certificate.getIssuerDN());
        Assert.assertEquals(endUserInfoDelegator.x509Certificate.getSubjectDN().getName(), x509Certificate.getSubjectDN().getName());
        // second cert should be the proxy cert sent to CDS
        x509Certificate = CertUtil.loadCertificate(certChain.get(1).getCertificateAsString());
        Assert.assertEquals(cdsX509Certificates[0].getIssuerDN().getName(), x509Certificate.getIssuerDN().getName());
        Assert.assertEquals(cdsX509Certificates[0].getSubjectDN().getName(), x509Certificate.getSubjectDN().getName());
        // first cert should be the delegated proxy
        x509Certificate = CertUtil.loadCertificate(certChain.get(0).getCertificateAsString());
        // the subject of the proxy should be the issuer of the delgated proxy
        Assert.assertEquals(cdsX509Certificates[0].getSubjectDN().getName(), x509Certificate.getIssuerDN().getName());
        // we don't really know what the final subject will be, but it should be a superstring of the delegated proxy DN
        Assert.assertEquals(0, x509Certificate.getSubjectDN().getName().indexOf(cdsX509Certificates[0].getSubjectDN().getName()));

        /**
         * Bad user using the dcEPR should fail
         */
        KeyManager badUserKeyManager = new SingleEntityKeyManager("client", new X509Certificate[] { endUserInfoBad.x509Certificate }, endUserInfoBad.privateKey);
        dcs = DCSSoapClientFactory.createSoapClient(dcsURL, truststore, badUserKeyManager);
        ((BindingProvider) dcs).getRequestContext().put(Header.HEADER_LIST, makeDelegationIdHeader(dcEPR));
        try {
            dcs.getDelegatedCredential(dcr);
            Assert.fail("Should not be able to get delegated credential");
        } catch (PermissionDeniedFaultFaultMessage e) {
            // expected
           // System.out.println("Expected " + e.toString());
        }

        /**
         * good user with bad EPR should fail; not inadvertantly return the wrong cred
         */
        //change the EPR
        Object o=((org.xmlsoap.schemas.ws._2004._03.addressing.ReferencePropertiesType) dcEPR.getReferenceProperties()).getAny().get(0);
        ((Node) o).getChildNodes().item(0).getChildNodes().item(0).setNodeValue("10000000");
        //System.out.println(JAXBUtils.marshal(dcEPR));
        
        List<Header> badHeader = makeDelegationIdHeader(dcEPR);
        ((BindingProvider) dcs).getRequestContext().put(Header.HEADER_LIST, badHeader);
        try {
            cred = dcs.getDelegatedCredential(dcr);
            Assert.fail("Should not have been able to obtain a credential for a bad EPR.");
        } catch (Exception e) {
            // expected
            //e.printStackTrace();
        }
    }

    private List<Header> makeDelegationIdHeader(EndpointReferenceType epr) throws JAXBException {
        List<Header> headers = new ArrayList<Header>();
        Header delegationIdHeader = new Header(new QName("http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential",
                "DelegatedCredentialKey"), epr, new JAXBDataBinding(EndpointReferenceType.class));
        headers.add(delegationIdHeader);
        return headers;
    }

    private InitiateDelegationRequest createDelegationPolicy(UserInfo proxyUserInfo) {
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
        return initiateDelegationRequest;
    }

    private UserInfo createLocalUser(DorianPortType dorianSoap, String userId, String firstName) throws Exception {
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
        FindLocalUsersResponse findLocalUsersResponse = dorianSoap.findLocalUsers(findLocalUsersRequest);
        List<LocalUser> localUsers = findLocalUsersResponse.getLocalUser();
        if (localUsers.size() != 1) {
            throw new Exception("findLocalUsers returned " + localUsers.size() + " users!");
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
        FindGridUsersResponse findGridUsersResponse = dorianSoap.findGridUsers(findGridUsersRequest);
        List<GridUser> gridUsers = findGridUsersResponse.getGridUser();
        if (gridUsers.size() != 1) {
            throw new Exception("findGridUsers returned " + gridUsers.size() + " users!");
        }

        GridUser gridUser = gridUsers.get(0);
        String gridId = gridUser.getGridId();

        UserInfo userInfo = new UserInfo(gridId, userInfo0.x509Certificate, userInfo0.privateKey);
        return userInfo;
    }

    private UserInfo login(DorianPortType dorianSoap, String userId, String password) throws Exception {

        BasicAuthentication basicAuthentication = new BasicAuthentication();
        basicAuthentication.setUserId(userId);
        basicAuthentication.setPassword(password);
        Credential credential = new Credential();
        credential.setCredential(basicAuthentication);
        AuthenticateUserRequest authenticateUserRequest = new AuthenticateUserRequest();
        authenticateUserRequest.setCredential(credential);
        AuthenticateUserResponse authenticateUserResponse = dorianSoap.authenticateUser(authenticateUserRequest);
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
        RequestUserCertificateResponse requestUserCertificateResponse = dorianSoap.requestUserCertificate(userCertificateRequest);
        String certificateString = requestUserCertificateResponse.getX509Certificate().getCertificateAsString();
        X509Certificate x509Certificate = CertUtil.loadCertificate(certificateString);

        UserInfo userInfo = new UserInfo(null, x509Certificate, keyPair.getPrivate());
        return userInfo;
    }
}
