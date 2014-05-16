package org.cagrid.index.test;

import static org.junit.Assert.*;
import static org.ops4j.pax.exam.CoreOptions.*;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileExtendOption;
import org.cagrid.index.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class IndexFunctionalTest extends CaGridTestSupport {

    private static final String INDEX_URL = "http://localhost:8080/wsrf/services/DefaultIndexService";

    @Override
    @Configuration
    public Option[] config() {
        Option[] options = new Option[] {
                // Install GME feature
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresRepositories", "," + maven().groupId("org.cagrid").artifactId("cagrid-features").versionAsInProject().classifier("features").type("xml").getURL()),
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresBoot", ",cagrid-index"),


                // seeing once in a while an spurious linkage error:
                // java.lang.LinkageError: loader constraint violation: loader (instance of <bootloader>) previously initiated loading for a different type with name "javax/xml/soap/SOAPFault"
                // adding this to get some info:
                // System.err.println(executeCommand("packages:exports | grep javax.xml.soap"));
                // System.err.println(executeCommand("osgi:list"));
                // it seems there could be a conflict between the one included with the jre and the saaj feature in
                // servicemix, both jars have this class (javax.xml.soap.SOAPFault)
                 new KarafDistributionConfigurationFileExtendOption("etc/jre.properties", "jre-1.6", ",javax.xml.soap;version=\"1.3\"")
                , new KarafDistributionConfigurationFileExtendOption("etc/jre.properties", "jre-1.7", ",javax.xml.soap;version=\"1.3\"")
        };
        return CaGridTestSupport.concatAll(super.config(), options);
    }

    @Test
    public void testFunctionalIndexService() {
        try {
            System.err.println(executeCommand("features:list"));
            assertBundleInstalled("cagrid-index-api");
            assertBundleInstalled("cagrid-index-service");
            assertBundleInstalled("cagrid-index-wsrf");

            IndexService indexService = getOsgiService(IndexService.class, 30000L);
            assertNotNull(indexService);

            // TODO: test

        } catch (Exception e) {
            fail(ExceptionUtils.getFullStackTrace(e));
        }
    }

}
