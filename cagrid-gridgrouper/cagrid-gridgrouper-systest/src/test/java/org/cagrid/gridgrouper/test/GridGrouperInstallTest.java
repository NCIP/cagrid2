package org.cagrid.gridgrouper.test;

import edu.internet2.middleware.GrouperInit;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileExtendOption;
import org.cagrid.gridgrouper.service.GridGrouperService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.CoreOptions;
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
public class GridGrouperInstallTest extends CaGridTestSupport {

    @Override
    @Configuration
    public Option[] config() {
        Option[] options = new Option[] {
                // need at least internet2 loaded so I can initialize the DB from the test (GrouperInit is part of the internet2 bunle)
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresRepositories", ","
                        + maven().groupId("org.cagrid").artifactId("cagrid-features").versionAsInProject().classifier("features").type("xml").getURL()),
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresBoot", ",cagrid-gridgrouper-internet2"),
                CoreOptions.mavenBundle("org.apache.ant", "com.springsource.org.apache.tools.ant", "1.7.0")
        };
        return CaGridTestSupport.concatAll(super.config(), options);
    }

    @Test
    public void testInstallGME() throws Exception {
        GrouperInit.main(new String[]{
                "schema-export.sql",
                "../../../src/test/resources/grouper.hibernate.properties",
                "../../../src/test/resources/hibernate"});

        installAndAssertFeature("cagrid-gridgrouper", 30000L);
        System.err.println(executeCommand("features:list"));
        assertBundleInstalled("cagrid-gridgrouper-api");
        assertBundleInstalled("cagrid-gridgrouper-service");
        assertBundleInstalled("cagrid-gridgrouper-wsrf");

        GridGrouperService gridGrouperService = getOsgiService(GridGrouperService.class, 30000L);
        assertNotNull(gridGrouperService);

        // grab its metadata
        ServiceMetadata metadata = gridGrouperService.getServiceMetadata();
        Assert.assertNotNull(metadata);
        assertEquals("Service metadata name was not as expected.", "GridGrouper", metadata.getServiceDescription().getService().getName());
        ServiceSecurityMetadata securityMetadata = gridGrouperService.getServiceSecurityMetadata();
        Assert.assertNotNull(securityMetadata);
    }
}
