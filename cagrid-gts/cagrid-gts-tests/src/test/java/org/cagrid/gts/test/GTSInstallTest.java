package org.cagrid.gts.test;

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
        assertBundleActive("cagrid-gts-service");
        GTS gts = getOsgiService(GTS.class, TIMEOUT);
        Assert.assertNotNull(gts);
    }
    
}