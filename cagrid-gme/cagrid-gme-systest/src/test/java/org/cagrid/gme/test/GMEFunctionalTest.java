package org.cagrid.gme.test;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileExtendOption;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileReplacementOption;
import org.cagrid.core.common.security.CredentialFactory;
import org.cagrid.core.common.security.X509Credential;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.model.XMLSchemaDocument;
import org.cagrid.gme.model.XMLSchemaNamespace;
import org.cagrid.gme.soapclient.GMESoapClientFactory;
import org.cagrid.gme.test.utils.GMETestUtils;
import org.cagrid.gme.wsrf.stubs.GetXMLSchemaNamespacesRequest;
import org.cagrid.gme.wsrf.stubs.GetXMLSchemaNamespacesResponse;
import org.cagrid.gme.wsrf.stubs.GlobalModelExchangePortType;
import org.cagrid.gme.wsrf.stubs.InvalidSchemaSubmissionFaultFaultMessage;
import org.cagrid.gme.wsrf.stubs.PublishXMLSchemasRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

import javax.net.ssl.KeyManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.ops4j.pax.exam.CoreOptions.maven;

import static junit.framework.Assert.assertNotNull;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class GMEFunctionalTest extends CaGridTestSupport {

    public static final String GME_URL = "https://localhost:7741/gme";

    @Override
    @Configuration
    public Option[] config() {
        Option[] options = new Option[] {
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresRepositories", "," + maven().groupId("org.cagrid").artifactId("cagrid-features").versionAsInProject().classifier("features").type("xml").getURL()),
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresBoot", ",cagrid-gme"),
                new KarafDistributionConfigurationFileReplacementOption("etc/cagrid.gme.wsrf.cfg", new File("src/test/resources/paxexam/etc/cagrid.gme.wsrf.cfg")),
                new KarafDistributionConfigurationFileReplacementOption("etc/gme/host.jks", new File("src/test/resources/paxexam/etc/gme/host.jks")),
                new KarafDistributionConfigurationFileReplacementOption("etc/gme/truststore.jks", new File("src/test/resources/paxexam/etc/gme/truststore.jks")),
                new KarafDistributionConfigurationFileReplacementOption("etc/gme/A.xsd", new File("src/test/resources/paxexam/etc/gme/A.xsd")),
                new KarafDistributionConfigurationFileReplacementOption("etc/gme/B.xsd", new File("src/test/resources/paxexam/etc/gme/B.xsd"))
        };
        return CaGridTestSupport.concatAll(super.config(), options);
    }

    @Test
    public void testFunctionalGME() {
        try {
            System.err.println(executeCommand("features:list"));
            assertBundleInstalled("cagrid-gme-api");
            assertBundleInstalled("cagrid-gme-service");
            assertBundleInstalled("cagrid-gme-wsrf");

            // get gme soap client
            GlobalModelExchangePortType gme = getGMESoapClient();
            assertNotNull(gme);

            // publish schemas
            publishXMLSchemas(gme);

            // get schemas
            List<XMLSchemaNamespace> schemas = getXMLSchemaNamespaces(gme);
            assertEquals(2, schemas.size());
        } catch(Exception e) {
            fail(ExceptionUtils.getFullStackTrace(e));
        }
    }

    private GlobalModelExchangePortType getGMESoapClient() throws GeneralSecurityException, IOException {
        KeyStoreType truststore = new KeyStoreType();
        truststore.setFile("etc/gme/truststore.jks");
        truststore.setType("JKS");
        truststore.setPassword("inventrio");

        X509Credential credential = CredentialFactory.getCredential(
                "etc/gme/host.jks",
                "inventrio",
                "tomcat",
                "inventrio");

        KeyManager keyManager = new SingleEntityKeyManager("tomcat", credential);

        return GMESoapClientFactory.createSoapClient(GME_URL, truststore, keyManager);
    }

    private List<XMLSchemaNamespace> getXMLSchemaNamespaces(GlobalModelExchangePortType gme) {
        GetXMLSchemaNamespacesResponse response = gme.getXMLSchemaNamespaces(new GetXMLSchemaNamespacesRequest());
        return response.getXMLSchemaNamespace();
    }

    private void publishXMLSchemas(GlobalModelExchangePortType gme) throws URISyntaxException, IOException, InvalidSchemaSubmissionFaultFaultMessage {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(GMETestUtils.createSchema(new URI("gme://a"), new File("etc/gme/A.xsd")));
        schemas.add(GMETestUtils.createSchema(new URI("gme://b"), new File("etc/gme/B.xsd")));

        PublishXMLSchemasRequest req = new PublishXMLSchemasRequest();
        PublishXMLSchemasRequest.Schemas reqschemas = new PublishXMLSchemasRequest.Schemas();
        reqschemas.getXMLSchema().addAll(schemas);
        req.setSchemas(reqschemas);
        gme.publishXMLSchemas(req);
    }
}
