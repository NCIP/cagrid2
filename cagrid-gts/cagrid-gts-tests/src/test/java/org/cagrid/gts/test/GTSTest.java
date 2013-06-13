package org.cagrid.gts.test;

import static org.ops4j.pax.exam.CoreOptions.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class GTSTest extends CaGridTestSupport {
    public static final String GTS_FEATURE_VERSION_PROPERTY = "gts.version";

    @Before
    public void setUp() {
        
        /**
        features:addurl mvn:org.cagrid/cagrid-third-party-features/2.0.0-SNAPSHOT/xml/features
        features:install cagrid-third-party
        features:addurl mvn:org.cagrid.wsrf/wsrf-draft-features/2.0.3-SNAPSHOT/xml/features
        features:install wsrf-draft
        features:addurl mvn:org.cagrid/cagrid-features/2.0.0-SNAPSHOT/xml/features
        features:install cagrid
        features:addurl mvn:org.cagrid/cagrid-gaards-features/2.0.0-SNAPSHOT/xml/features
        features:install cagrid-gaards
        */
        
        
        System.err.println(executeCommand("features:addurl "
                + maven().groupId("org.cagrid").artifactId("cagrid-gts-features").version(System.getProperty(GTS_FEATURE_VERSION_PROPERTY))
                        .classifier("features").type("xml").getURL()));
    }

    @Override
    @Configuration
    public Option[] config() {
        Option[] options = new Option[] { systemProperty(GTS_FEATURE_VERSION_PROPERTY, MavenUtils.getArtifactVersion("org.cagrid", "cagrid-gts-features")) };
        return CaGridTestSupport.concatAll(super.config(), options);
    }

    @Test
    public void testScr() throws Exception {
        // System.err.println(executeCommand("features:addurl " + gtsFeaturesURL));
        System.err.println(executeCommand("features:listurl"));
        System.err.println(executeCommand("features:list"));
        executeCommand("features:install cagrid-gts");
        assertFeatureInstalled("cagrid-gts");
    }

}
