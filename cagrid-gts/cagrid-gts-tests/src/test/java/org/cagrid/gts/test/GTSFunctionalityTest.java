package org.cagrid.gts.test;

import static org.junit.Assert.*;
import static org.ops4j.pax.exam.CoreOptions.maven;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.net.ssl.KeyManager;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileExtendOption;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileReplacementOption;
import org.cagrid.core.common.security.CredentialFactory;
import org.cagrid.core.common.security.X509Credential;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
import org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest;
import org.cagrid.gts.model.PermissionFilter;
import org.cagrid.gts.service.GTS;
import org.cagrid.gts.service.exception.PermissionDeniedException;
import org.cagrid.gts.soapclient.GTSSoapClientFactory;
import org.cagrid.gts.wsrf.stubs.GTSPortType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class GTSFunctionalityTest extends CaGridTestSupport {

    private static final long TIMEOUT = 30000L;
    private final static String ADMIN_USER = "O=Test Organization,OU=Test Unit,CN=GTS Admin";

    private static final String LEGACY_GTS_URL = "https://localhost:7742/gts";
    private static final String NEW_GTS_URL = "https://localhost:7741/gts";

    private static final String HOST = "etc/cagrid-gts/host.jks";
    private static final String TRUSTSTORE = "etc/cagrid-gts/truststore.jks";
    private static final String TRUSTSTORETYPE = "JKS";
    private static final String KEYALIAS = "tomcat";
    private static final String TRUSTSTOREPASSWORD = "inventrio";
    private static final String KEYSTOREPASSWORD = "inventrio";
    private static final String KEYPASSWORD = "inventrio";

    @Override
    @Configuration
    public Option[] config() {
        Option[] options = new Option[] {
                // Make sure the GTS feature is installed before the test probe runs (the install process is actually tested by the GTSInstallTest test
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresRepositories", ","
                        + maven().groupId("org.cagrid").artifactId("cagrid-features").versionAsInProject().classifier("features").type("xml").getURL()),
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresBoot", ",cagrid-gts"),

                // Get our resource files to the "etc" area
                new KarafDistributionConfigurationFileReplacementOption("etc/cagrid.gts.wsrf.cfg", new File("src/test/resources/cagrid.gts.wsrf.cfg")),
                new KarafDistributionConfigurationFileReplacementOption(HOST, new File("src/test/resources/host.jks")),
                new KarafDistributionConfigurationFileReplacementOption(TRUSTSTORE, new File("src/test/resources/truststore.jks"))
        // TODO: get a client keystore and a sha2 cert for new endpoint

        };

        return CaGridTestSupport.concatAll(super.config(), options);
    }

    @Test
    public void testGTS() throws Exception {
        // see if we have our expected service URLs
        System.err.println(executeCommand("features:listurl"));
        System.err.println(executeCommand("features:list"));

        assertBundleActive("cagrid-gts-service");
        GTS gts = getOsgiService(GTS.class, TIMEOUT);
        Assert.assertNotNull(gts);

        // grab its metadata
        ServiceMetadata metadata = gts.getServiceMetadata();
        assertNotNull(metadata);
        assertEquals("Service metadata name was not as expected.", "GTS", metadata.getServiceDescription().getService().getName());
        ServiceSecurityMetadata securityMetadata = gts.getServiceSecurityMetadata();
        assertNotNull(securityMetadata);

        try {
            gts.findPermissions(ADMIN_USER, new PermissionFilter());
            Assert.fail("Should not be able to find permissions, no admin permission are configured.");
        } catch (PermissionDeniedException f) { // expected
        }

        // test the soap end point
        assertBundleActive("cagrid-gts-wsrf");
        GTSPortType legacyClient = getGTSSoapClient(LEGACY_GTS_URL);

        ServiceSecurityMetadata securityMetadata2 = legacyClient.getServiceSecurityMetadata(new GetServiceSecurityMetadataRequest())
                .getServiceSecurityMetadata();

        // TODO: add the equals methods to this
        // assertEquals(securityMetadata.getOperations(), securityMetadata2);
        assertEquals(securityMetadata.getDefaultCommunicationMechanism().isAnonymousPermitted(), securityMetadata2.getDefaultCommunicationMechanism()
                .isAnonymousPermitted());

        GTSPortType newClient = getGTSSoapClient(NEW_GTS_URL);
        ServiceSecurityMetadata securityMetadata3 = newClient.getServiceSecurityMetadata(new GetServiceSecurityMetadataRequest()).getServiceSecurityMetadata();
        // TODO: add the equals methods to this
        assertEquals(securityMetadata2.getDefaultCommunicationMechanism().isAnonymousPermitted(), securityMetadata3.getDefaultCommunicationMechanism()
                .isAnonymousPermitted());


    }

    private GTSPortType getGTSSoapClient(String url) throws GeneralSecurityException, IOException {
        KeyStoreType truststore = new KeyStoreType();
        truststore.setFile(TRUSTSTORE);
        truststore.setType(TRUSTSTORETYPE);
        truststore.setPassword(TRUSTSTOREPASSWORD);

        X509Credential credential = CredentialFactory.getCredential(HOST, KEYSTOREPASSWORD, KEYALIAS, KEYPASSWORD);
        KeyManager keyManager = new SingleEntityKeyManager(KEYALIAS, credential);

        return GTSSoapClientFactory.createSoapClient(url, truststore, keyManager);
    }

}