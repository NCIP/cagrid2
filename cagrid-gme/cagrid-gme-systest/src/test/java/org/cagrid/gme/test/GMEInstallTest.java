package org.cagrid.gme.test;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileExtendOption;
import org.cagrid.gme.service.GlobalModelExchangeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.ops4j.pax.exam.CoreOptions.maven;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class GMEInstallTest extends CaGridTestSupport {

    @Override
    @Configuration
    public Option[] config() {
        Option[] options = new Option[] {
                // Had to install the hibernate stuff at boot
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresRepositories", "," + maven().groupId("org.cagrid").artifactId("cagrid-features").versionAsInProject().classifier("features").type("xml").getURL()),
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresBoot", ",cagrid-third-party"),
        };
        return CaGridTestSupport.concatAll(super.config(), options);
    }

    @Test
    public void testInstallGME() throws Exception {
        // Install GME feature here
        installAndAssertFeature("cagrid-gme", 30000L);
        System.err.println(executeCommand("features:list"));
        assertBundleInstalled("cagrid-gme-api");
        assertBundleInstalled("cagrid-gme-service");
        assertBundleInstalled("cagrid-gme-wsrf");

        GlobalModelExchangeService gmeService = getOsgiService(GlobalModelExchangeService.class, 30000L);
        assertNotNull(gmeService);

        // grab its metadata
        ServiceMetadata metadata = gmeService.getServiceMetadata();
        Assert.assertNotNull(metadata);
        assertEquals("Service metadata name was not as expected.", "GlobalModelExchange", metadata.getServiceDescription().getService().getName());
        ServiceSecurityMetadata securityMetadata = gmeService.getServiceSecurityMetadata();
        Assert.assertNotNull(securityMetadata);
    }
}
