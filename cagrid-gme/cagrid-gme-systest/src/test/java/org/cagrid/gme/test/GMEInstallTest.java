package org.cagrid.gme.test;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.common.security.CredentialFactory;
import org.cagrid.core.common.security.X509Credential;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
import org.cagrid.gme.soapclient.GMESoapClientFactory;
import org.cagrid.gme.wsrf.stubs.GlobalModelExchangePortType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

import javax.net.ssl.KeyManager;
import java.io.IOException;
import java.security.GeneralSecurityException;

import static junit.framework.Assert.assertNotNull;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class GMEInstallTest extends CaGridTestSupport {

    public static final String GME_URL = "https://localhost:7741/gme";

    @Override
    @Configuration
    public Option[] config() {
        Option[] options = new Option[] {
        // put Custom stuff here
        };
        return CaGridTestSupport.concatAll(super.config(), options);
    }

    @Test
    public void testInstallGME() throws Exception {
        installAndAssertFeature("cagrid-gme", 30000L);
        System.err.println(executeCommand("features:list"));
        assertBundleInstalled("cagrid-gme-api");
        assertBundleInstalled("cagrid-gme-service");
        assertBundleInstalled("cagrid-gme-wsrf");
    }
}
