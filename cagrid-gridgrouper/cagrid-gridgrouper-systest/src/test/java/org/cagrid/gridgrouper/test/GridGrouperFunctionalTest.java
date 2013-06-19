//package org.cagrid.gridgrouper.test;
//
//import org.apache.commons.lang.exception.ExceptionUtils;
//import org.apache.cxf.configuration.security.KeyStoreType;
//import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileExtendOption;
//import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileReplacementOption;
//import org.cagrid.core.common.security.CredentialFactory;
//import org.cagrid.core.common.security.X509Credential;
//import org.cagrid.core.soapclient.SingleEntityKeyManager;
//import org.cagrid.gridgrouper.service.GridGrouperService;
//import org.cagrid.gridgrouper.soapclient.GridGrouperSoapClientFactory;
//import org.cagrid.gridgrouper.wsrf.stubs.GridGrouperPortType;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.ops4j.pax.exam.Option;
//import org.ops4j.pax.exam.junit.Configuration;
//import org.ops4j.pax.exam.junit.ExamReactorStrategy;
//import org.ops4j.pax.exam.junit.JUnit4TestRunner;
//import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;
//
//import javax.net.ssl.KeyManager;
//import java.io.File;
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//
//import static junit.framework.Assert.assertNotNull;
//import static org.junit.Assert.fail;
//import static org.ops4j.pax.exam.CoreOptions.maven;
//
//@RunWith(JUnit4TestRunner.class)
//@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
//public class GridGrouperFunctionalTest extends CaGridTestSupport {
//
//    private static final String GRIDGROUPER_URL = "https://localhost:7738/gridgrouper";
//
//    private static final String HOST = "etc/grouper/host.jks";
//    private static final String TRUSTSTORE = "etc/grouper/truststore.jks";
//    private static final String TRUSTSTORETYPE = "JKS";
//    private static final String KEYALIAS = "tomcat";
//    private static final String TRUSTSTOREPASSWORD = "inventrio";
//    private static final String KEYSTOREPASSWORD = "inventrio";
//    private static final String KEYPASSWORD = "inventrio";
//
//    @Override
//    @Configuration
//    public Option[] config() {
//        Option[] options = new Option[] {
//                // Install Grid Grouper feature
//                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresRepositories", "," + maven().groupId("org.cagrid").artifactId("cagrid-features").versionAsInProject().classifier("features").type("xml").getURL()),
//                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresBoot", ",cagrid-gridgrouper"),
//
//                // Get our resource files to the "etc" area
//                new KarafDistributionConfigurationFileReplacementOption("etc/cagrid.gridgrouper.wsrf.cfg", new File("src/test/resources/cagrid.gridgrouper.wsrf.cfg")),
//                new KarafDistributionConfigurationFileReplacementOption(HOST, new File("src/test/resources/host.jks")),
//                new KarafDistributionConfigurationFileReplacementOption(TRUSTSTORE, new File("src/test/resources/truststore.jks"))
//        };
//        return CaGridTestSupport.concatAll(super.config(), options);
//    }
//
//    @Test
//    public void testFunctionalGridGrouper() {
//        try {
//            System.err.println(executeCommand("features:list"));
//            assertBundleInstalled("cagrid-gridgrouper-api");
//            assertBundleInstalled("cagrid-gridgrouper-service");
//            assertBundleInstalled("cagrid-gridgrouper-wsrf");
//            GridGrouperService gridGrouperService = getOsgiService(GridGrouperService.class, 30000L);
//            assertNotNull(gridGrouperService);
//
//            // get soap client
//            GridGrouperPortType gridGrouperSoapClient = getGridGrouperSoapClient();
//            assertNotNull(gridGrouperSoapClient);
//
//        } catch(Exception e) {
//            fail(ExceptionUtils.getFullStackTrace(e));
//        }
//    }
//
//    private GridGrouperPortType getGridGrouperSoapClient() throws GeneralSecurityException, IOException {
//        KeyStoreType truststore = new KeyStoreType();
//        truststore.setFile(TRUSTSTORE);
//        truststore.setType(TRUSTSTORETYPE);
//        truststore.setPassword(TRUSTSTOREPASSWORD);
//
//        X509Credential credential = CredentialFactory.getCredential(
//                HOST,
//                KEYSTOREPASSWORD,
//                KEYALIAS,
//                KEYPASSWORD);
//
//        KeyManager keyManager = new SingleEntityKeyManager(KEYALIAS, credential);
//
//        return GridGrouperSoapClientFactory.createSoapClient(GRIDGROUPER_URL, truststore, keyManager);
//    }
//}
