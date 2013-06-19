package org.cagrid.gts.test;

import static org.junit.Assert.*;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import org.cagrid.gts.service.GTS;
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
public class GTSInstallTest extends CaGridTestSupport {

    private static final long TIMEOUT = 30000L;

    @Override
    @Configuration
    public Option[] config() {
        Option[] options = new Option[] {
        // customize here
        };
        return CaGridTestSupport.concatAll(super.config(), options);
    }

    @Test
    public void testGTS() throws Exception {
        // see if we have our expected service URLs
        System.err.println(executeCommand("features:listurl"));

        // install the GTS and make sure the service starts
        installAndAssertFeature("cagrid-gts", TIMEOUT);
        System.err.println(executeCommand("features:list"));
        assertBundleInstalled("cagrid-gts-service");
        GTS gts = getOsgiService(GTS.class, TIMEOUT);
        Assert.assertNotNull(gts);

        // grab its metadata
        ServiceMetadata metadata = gts.getServiceMetadata();
        assertNotNull(metadata);
        assertEquals("Service metadata name was not as expected.", "GTS", metadata.getServiceDescription().getService().getName());
        ServiceSecurityMetadata securityMetadata = gts.getServiceSecurityMetadata();
        assertNotNull(securityMetadata);
    }
    
}