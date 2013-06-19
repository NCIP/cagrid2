//package org.cagrid.gme.test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.ops4j.pax.exam.Option;
//import org.ops4j.pax.exam.junit.Configuration;
//import org.ops4j.pax.exam.junit.ExamReactorStrategy;
//import org.ops4j.pax.exam.junit.JUnit4TestRunner;
//import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;
//
//@RunWith(JUnit4TestRunner.class)
//@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
//public class GMEInstallTest extends CaGridTestSupport {
//
//    public static final String GME_URL = "https://localhost:7741/gme";
//
//    @Override
//    @Configuration
//    public Option[] config() {
//        Option[] options = new Option[] {
//        // put Custom stuff here
//        };
//        return CaGridTestSupport.concatAll(super.config(), options);
//    }
//
//    @Test
//    public void testInstallGME() throws Exception {
//        installAndAssertFeature("cagrid-gme", 30000L);
//        System.err.println(executeCommand("features:list"));
//        assertBundleInstalled("cagrid-gme-api");
//        assertBundleInstalled("cagrid-gme-service");
//        assertBundleInstalled("cagrid-gme-wsrf");
//    }
//}
