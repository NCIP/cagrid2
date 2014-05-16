package org.cagrid.index.test;

import static junit.framework.Assert.*;

import org.cagrid.index.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class IndexInstallTest extends CaGridTestSupport {


    @Test
    public void testInstallIndexService() throws Exception {
        // Install Index feature here
        installAndAssertFeature("cagrid-index", 30000L);
        System.err.println(executeCommand("features:list"));
        assertBundleInstalled("cagrid-index-api");
        assertBundleInstalled("cagrid-index-service");
        assertBundleInstalled("cagrid-index-wsrf");

        IndexService indexService = getOsgiService(IndexService.class, 30000L);
        assertNotNull(indexService);

        // TODO: test
    }
}
