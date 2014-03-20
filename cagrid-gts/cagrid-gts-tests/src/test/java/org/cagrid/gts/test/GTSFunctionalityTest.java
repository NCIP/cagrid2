package org.cagrid.gts.test;

import static org.junit.Assert.*;
import static org.ops4j.pax.exam.CoreOptions.maven;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.net.ssl.KeyManager;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileExtendOption;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileReplacementOption;
import org.cagrid.core.common.security.CredentialFactory;
import org.cagrid.core.common.security.X509Credential;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
import org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest;
import org.cagrid.gts.model.Permission;
import org.cagrid.gts.model.PermissionFilter;
import org.cagrid.gts.model.Role;
import org.cagrid.gts.model.TrustLevel;
import org.cagrid.gts.service.GTS;
import org.cagrid.gts.service.exception.PermissionDeniedException;
import org.cagrid.gts.soapclient.GTSSoapClientFactory;
import org.cagrid.gts.tools.service.PermissionBootstrapper;
import org.cagrid.gts.wsrf.stubs.FindPermissionsRequest;
import org.cagrid.gts.wsrf.stubs.GTSPortType;
import org.cagrid.gts.wsrf.stubs.GetTrustLevelsRequest;
import org.cagrid.gts.wsrf.stubs.GetTrustLevelsResponse;
import org.cagrid.gts.wsrf.stubs.PermissionDeniedFaultFaultMessage;
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

    private static final String LEGACY_GTS_URL = "https://localhost:7741/wsrf/services/cagrid/GTS";
    private static final String NEW_GTS_URL = "https://localhost:7742/gts";

    private static final String HOST = "etc/cagrid-gts/gts-host.jks";
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
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresBoot", ",servicemix-quartz"),
                //new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresBoot", ",cagrid-gts"),

                // Get our resource files to the "etc" area
                new KarafDistributionConfigurationFileReplacementOption("etc/cagrid.gts.wsrf.cfg", new File("src/test/resources/cagrid.gts.wsrf.cfg")),
                new KarafDistributionConfigurationFileReplacementOption("etc/cagrid.gts.service.cfg", new File("src/test/resources/cagrid.gts.service.cfg")),
                new KarafDistributionConfigurationFileReplacementOption(HOST, new File("src/test/resources/gts-host.jks")),
                new KarafDistributionConfigurationFileReplacementOption(TRUSTSTORE, new File("src/test/resources/truststore.jks"))
        // TODO: get a client keystore and a sha2 cert for new endpoint

        };

        return CaGridTestSupport.concatAll(super.config(), options);
    }

    @Test
    public void testGTS() throws Exception {
        try {
            // see if we have our expected service URLs
            System.err.println("testGTS() - "+executeCommand("features:listurl"));
            System.err.println("testGTS() - "+executeCommand("features:list"));

            installAndAssertFeature("cagrid-gts", TIMEOUT);

            assertBundleActive("cagrid-gts-service");
            GTS gts = getOsgiService(GTS.class, TIMEOUT);
            Assert.assertNotNull(gts);

            try {
                gts.findPermissions(ADMIN_USER, new PermissionFilter());
                Assert.fail("Should not be able to find permissions, no admin permission are configured.");
            } catch (PermissionDeniedException f) { // expected
            }

            PermissionBootstrapper pb = new PermissionBootstrapper(new File("etc/cagrid-gts/gts-conf.xml"));
            pb.addAdminUser(ADMIN_USER);

            // grab its metadata
            ServiceMetadata metadata = gts.getServiceMetadata();
            assertNotNull(metadata);
            assertEquals("Service metadata name was not as expected.", "GTS", metadata.getServiceDescription().getService().getName());
            ServiceSecurityMetadata securityMetadata = gts.getServiceSecurityMetadata();
            assertNotNull(securityMetadata);

            try {
                Permission[] perms = gts.findPermissions(ADMIN_USER, new PermissionFilter());
                assertEquals(1, perms.length);
                assertEquals(ADMIN_USER, perms[0].getGridIdentity());
                assertEquals(Role.TRUST_SERVICE_ADMIN, perms[0].getRole());
                assertEquals("*", perms[0].getTrustedAuthorityName());

            } catch (PermissionDeniedException f) {
                Assert.fail("Should be able to find permissions.");
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
            ServiceSecurityMetadata securityMetadata3 = newClient.getServiceSecurityMetadata(new GetServiceSecurityMetadataRequest())
                    .getServiceSecurityMetadata();
            // TODO: add the equals methods to this
            assertEquals(securityMetadata2.getDefaultCommunicationMechanism().isAnonymousPermitted(), securityMetadata3.getDefaultCommunicationMechanism()
                    .isAnonymousPermitted());

            FindPermissionsRequest fpReq = new FindPermissionsRequest();
            FindPermissionsRequest.Filter filter = new FindPermissionsRequest.Filter();
            filter.setPermissionFilter(new PermissionFilter());
            fpReq.setFilter(filter);
            try {
                legacyClient.findPermissions(fpReq);
                Assert.fail("Should not be able to find permissions, no admin permission are configured.");
            } catch (PermissionDeniedFaultFaultMessage pd) {
                // expected
            }

            GetTrustLevelsResponse trustLevelResp = newClient.getTrustLevels(new GetTrustLevelsRequest());
            List<TrustLevel> levels = trustLevelResp.getTrustLevel();
            assertEquals(0, levels.size());

            // newClient.addPermission(parameters)

        } catch (Exception e) {
            Assert.fail("Unexpected fault(" + e.getClass().getCanonicalName() + ") was raised:" + e.getMessage());
        }

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