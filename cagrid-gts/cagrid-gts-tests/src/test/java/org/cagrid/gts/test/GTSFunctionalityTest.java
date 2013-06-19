package org.cagrid.gts.test;

import static org.junit.Assert.*;
import static org.ops4j.pax.exam.CoreOptions.maven;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileExtendOption;
import org.cagrid.gts.model.PermissionFilter;
import org.cagrid.gts.service.GTS;
import org.cagrid.gts.service.exception.PermissionDeniedException;
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

    @Override
    @Configuration
    public Option[] config() {
        Option[] options = new Option[] {
                // Make sure the GTS feature is installed before the test probe runs (the install process is actually tested by the GTSInstallTest test
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresRepositories", ","
                        + maven().groupId("org.cagrid").artifactId("cagrid-features").versionAsInProject().classifier("features").type("xml").getURL()),
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresBoot", ",cagrid-gts")

        };

        return CaGridTestSupport.concatAll(super.config(), options);
    }

    @Test
    public void testGTS() throws Exception {
        // see if we have our expected service URLs
        System.err.println(executeCommand("features:listurl"));
        System.err.println(executeCommand("features:list"));

        assertBundleInstalled("cagrid-gts-service");
        GTS gts = getOsgiService(GTS.class, TIMEOUT);
        Assert.assertNotNull(gts);

        try {
            gts.findPermissions(ADMIN_USER, new PermissionFilter());
            Assert.fail("Should not be able to find permissions, no admin permission are configured.");
        } catch (PermissionDeniedException f) {

        }

    }

}