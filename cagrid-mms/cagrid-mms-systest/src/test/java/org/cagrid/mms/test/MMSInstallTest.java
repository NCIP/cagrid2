package org.cagrid.mms.test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.ops4j.pax.exam.CoreOptions.maven;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import java.io.File;

import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileExtendOption;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileReplacementOption;
import org.cagrid.mms.service.MetadataModelService;
import org.cagrid.mms.test.utils.MMSTestUtils;
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
public class MMSInstallTest extends CaGridTestSupport {

    @Override
    @Configuration
    public Option[] config() {
        Option[] options = new Option[] {
                new KarafDistributionConfigurationFileReplacementOption(MMSTestUtils.SERVICEMETADATA, new File("src/test/resources/serviceMetadata.xml")),
                new KarafDistributionConfigurationFileReplacementOption(MMSTestUtils.SERVICESECURITYMETADATA, new File("src/test/resources/serviceSecurityMetadata.xml")),

                // Had to install the hibernate stuff at boot
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresRepositories", "," + maven().groupId("org.cagrid").artifactId("cagrid-features").versionAsInProject().classifier("features").type("xml").getURL()),
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresBoot", ",cagrid-third-party"),
        };
        return CaGridTestSupport.concatAll(super.config(), options);
    }

    @Test
    public void testInstallMMS() throws Exception {
        installAndAssertFeature("cagrid-mms", 30000L);
        System.err.println(executeCommand("features:list"));
        assertBundleInstalled("cagrid-mms-api");
        assertBundleInstalled("cagrid-mms-service");
        assertBundleInstalled("cagrid-mms-wsrf");
        //System.out.println(executeCommand("log:display"));
        //System.out.println(executeCommand("package:export | grep com.nanthealth.mms"));

        MetadataModelService mmsService = getOsgiService(MetadataModelService.class, 30000L);
        assertNotNull(mmsService);

        // grab its metadata
        ServiceMetadata metadata = mmsService.getServiceMetadata();
        Assert.assertNotNull(metadata);
        assertEquals("Service metadata name was not as expected.", "MetadataModelService", metadata.getServiceDescription().getService().getName());
        ServiceSecurityMetadata securityMetadata = mmsService.getServiceSecurityMetadata();
        Assert.assertNotNull(securityMetadata);
    }
}
