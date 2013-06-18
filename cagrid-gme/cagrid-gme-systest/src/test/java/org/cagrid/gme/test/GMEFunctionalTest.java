//package org.cagrid.gme.test;
//
//import org.apache.cxf.configuration.security.KeyStoreType;
//import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileExtendOption;
//import org.cagrid.core.common.security.CredentialFactory;
//import org.cagrid.core.common.security.X509Credential;
//import org.cagrid.core.soapclient.SingleEntityKeyManager;
//import org.cagrid.gme.soapclient.GMESoapClientFactory;
//import org.cagrid.gme.wsrf.stubs.GlobalModelExchangePortType;
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
//import static org.ops4j.pax.exam.CoreOptions.maven;
//
//import static junit.framework.Assert.assertNotNull;
//
//@RunWith(JUnit4TestRunner.class)
//@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
//public class GMEFunctionalTest extends CaGridTestSupport {
//
//    public static final String GME_URL = "https://localhost:7741/gme";
//
//    @Override
//    @Configuration
//    public Option[] config() {
//        Option[] options = new Option[] {
//                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresRepositories", ","
//                        + maven().groupId("org.cagrid").artifactId("cagrid-features").versionAsInProject().classifier("features").type("xml").getURL()),
//                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresBoot", ",cagrid-gme")
//
//        };
//        return CaGridTestSupport.concatAll(super.config(), options);
//    }
//
//    @Test
//    public void testFunctionalGME() throws Exception {
//        System.err.println(executeCommand("features:list"));
//        assertBundleInstalled("cagrid-gme-api");
//        assertBundleInstalled("cagrid-gme-service");
//        assertBundleInstalled("cagrid-gme-wsrf");
//        GlobalModelExchangePortType gme = getGMESoapClient();
//        assertNotNull(gme);
//    }
//
//    public static GlobalModelExchangePortType getGMESoapClient() throws GeneralSecurityException, IOException {
//        KeyStoreType truststore = new KeyStoreType();
//        truststore.setFile("etc/gme/truststore.jks");
//        truststore.setType("JKS");
//        truststore.setPassword("inventrio");
//
//        X509Credential credential = CredentialFactory.getCredential(
//                "etc/gme/host.jks",
//                "inventrio",
//                "tomcat",
//                "inventrio");
//
//        KeyManager keyManager = new SingleEntityKeyManager("tomcat", credential);
//
//        return GMESoapClientFactory.createSoapClient(GME_URL, truststore, keyManager);
//    }
//
//}
