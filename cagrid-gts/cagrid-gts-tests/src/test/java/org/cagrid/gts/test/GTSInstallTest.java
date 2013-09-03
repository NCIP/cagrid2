package org.cagrid.gts.test;

import java.io.File;

import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFilePutOption;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileReplacementOption;
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
    private static final String HOST = "etc/cagrid-gts/gts-host.jks";
    private static final String TRUSTSTORE = "etc/cagrid-gts/truststore.jks";

    @Override
    @Configuration
    public Option[] config() {
        Option[] options = new Option[] {
                // Get our resource files to the "etc" area
                new KarafDistributionConfigurationFileReplacementOption("etc/cagrid.gts.wsrf.cfg", new File("src/test/resources/cagrid.gts.wsrf.cfg")),
                new KarafDistributionConfigurationFileReplacementOption("etc/cagrid.gts.service.cfg", new File("src/test/resources/cagrid.gts.service.cfg")),
                new KarafDistributionConfigurationFileReplacementOption(HOST, new File("src/test/resources/gts-host.jks")),
                new KarafDistributionConfigurationFileReplacementOption(TRUSTSTORE, new File("src/test/resources/truststore.jks")) };
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
        Thread.sleep(300000);
    }

}