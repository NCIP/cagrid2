package org.cagrid.gme.test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.ops4j.pax.exam.CoreOptions.maven;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.KeyManager;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileExtendOption;
import org.apache.karaf.tooling.exam.options.KarafDistributionConfigurationFileReplacementOption;
import org.cagrid.core.common.security.CredentialFactory;
import org.cagrid.core.common.security.X509Credential;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.model.XMLSchemaNamespace;
import org.cagrid.gme.service.GlobalModelExchangeService;
import org.cagrid.gme.soapclient.GMESoapClientFactory;
import org.cagrid.gme.test.utils.GMETestUtils;
import org.cagrid.gme.wsrf.stubs.GetXMLSchemaNamespacesRequest;
import org.cagrid.gme.wsrf.stubs.GetXMLSchemaNamespacesResponse;
import org.cagrid.gme.wsrf.stubs.GetXMLSchemaRequest;
import org.cagrid.gme.wsrf.stubs.GetXMLSchemaRequest.TargetNamespace;
import org.cagrid.gme.wsrf.stubs.GetXMLSchemaResponse;
import org.cagrid.gme.wsrf.stubs.GlobalModelExchangePortType;
import org.cagrid.gme.wsrf.stubs.InvalidSchemaSubmissionFaultFaultMessage;
import org.cagrid.gme.wsrf.stubs.NoSuchNamespaceExistsFaultFaultMessage;
import org.cagrid.gme.wsrf.stubs.PublishXMLSchemasRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class GMEFunctionalTest extends CaGridTestSupport {

    private static final String GME_URL = "https://localhost:7742/gme";

    private static final String HOST = "etc/cagrid-gme/gme-host.jks";
    private static final String TRUSTSTORE = "etc/cagrid-gme/truststore.jks";
    private static final String TRUSTSTORETYPE = "JKS";
    private static final String KEYALIAS = "tomcat";
    private static final String TRUSTSTOREPASSWORD = "inventrio";
    private static final String KEYSTOREPASSWORD = "inventrio";
    private static final String KEYPASSWORD = "inventrio";

    private static final String SCHEMA_A = "etc/cagrid-gme/A.xsd";
    private static final String SCHEMA_B = "etc/cagrid-gme/B.xsd";
    private static final String SCHEMA_XML = "etc/cagrid-gme/xml.xsd";

    @Override
    @Configuration
    public Option[] config() {
        Option[] options = new Option[] {
                // Install GME feature
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresRepositories", "," + maven().groupId("org.cagrid").artifactId("cagrid-features").versionAsInProject().classifier("features").type("xml").getURL()),
                new KarafDistributionConfigurationFileExtendOption("etc/org.apache.karaf.features.cfg", "featuresBoot", ",cagrid-gme"),

                // Get our resource files to the "etc" area
                new KarafDistributionConfigurationFileReplacementOption("etc/cagrid.gme.wsrf.cfg", new File("src/test/resources/cagrid.gme.wsrf.cfg")),
                new KarafDistributionConfigurationFileReplacementOption(HOST, new File("src/test/resources/gme-host.jks")),
                new KarafDistributionConfigurationFileReplacementOption(TRUSTSTORE, new File("src/test/resources/truststore.jks")),
                new KarafDistributionConfigurationFileReplacementOption(SCHEMA_A, new File("src/test/resources/A.xsd")),
                new KarafDistributionConfigurationFileReplacementOption(SCHEMA_B, new File("src/test/resources/B.xsd")),
                new KarafDistributionConfigurationFileReplacementOption(SCHEMA_XML, new File("src/test/resources/xml.xsd")),
                new KarafDistributionConfigurationFileReplacementOption(GMETestUtils.SERVICEMETADATA, new File("src/test/resources/serviceMetadata.xml")),
                new KarafDistributionConfigurationFileReplacementOption(GMETestUtils.SERVICESECURITYMETADATA, new File("src/test/resources/serviceSecurityMetadata.xml"))

                // seeing once in a while an spurious linkage error:
                // java.lang.LinkageError: loader constraint violation: loader (instance of <bootloader>) previously initiated loading for a different type with name "javax/xml/soap/SOAPFault"
                // adding this to get some info:
                // System.err.println(executeCommand("packages:exports | grep javax.xml.soap"));
                // System.err.println(executeCommand("osgi:list"));
                // it seems there could be a conflict between the one included with the jre and the saaj feature in
                // servicemix, both jars have this class (javax.xml.soap.SOAPFault)
                , new KarafDistributionConfigurationFileExtendOption("etc/jre.properties", "jre-1.6", ",javax.xml.soap;version=\"1.3\"")
                , new KarafDistributionConfigurationFileExtendOption("etc/jre.properties", "jre-1.7", ",javax.xml.soap;version=\"1.3\"")
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

            GlobalModelExchangeService gmeService = getOsgiService(GlobalModelExchangeService.class, 30000L);
            assertNotNull(gmeService);

            // get gme soap client
            GlobalModelExchangePortType gme = getGMESoapClient();
            assertNotNull(gme);

            // publish schemas
            publishXMLSchemas(gme);

            // get schemas
            List<XMLSchemaNamespace> schemas = getXMLSchemaNamespaces(gme);
            assertEquals(3, schemas.size());

            // publish bad schemas test
            publishBadXMLSchemas(gme);
            
            getXMLSchema(gme);
            
        } catch(Exception e) {
            fail(ExceptionUtils.getFullStackTrace(e));
        }
    }

    private GlobalModelExchangePortType getGMESoapClient() throws GeneralSecurityException, IOException {
        KeyStoreType truststore = new KeyStoreType();
        truststore.setFile(TRUSTSTORE);
        truststore.setType(TRUSTSTORETYPE);
        truststore.setPassword(TRUSTSTOREPASSWORD);

        X509Credential credential = CredentialFactory.getCredential(
                HOST,
                KEYSTOREPASSWORD,
                KEYALIAS,
                KEYPASSWORD);

        KeyManager keyManager = new SingleEntityKeyManager(KEYALIAS, credential);

        GlobalModelExchangePortType gmePort = GMESoapClientFactory.createSoapClient(GME_URL, truststore, keyManager);
        Client client = ClientProxy.getClient(gmePort);
        client.getInInterceptors().add(new LoggingInInterceptor());
        client.getOutInterceptors().add(new LoggingOutInterceptor()); 
 
        return gmePort;
    }

    private List<XMLSchemaNamespace> getXMLSchemaNamespaces(GlobalModelExchangePortType gme) {
        GetXMLSchemaNamespacesResponse response = gme.getXMLSchemaNamespaces(new GetXMLSchemaNamespacesRequest());
        return response.getXMLSchemaNamespace();
    }

    private void publishXMLSchemas(GlobalModelExchangePortType gme) throws URISyntaxException, IOException, InvalidSchemaSubmissionFaultFaultMessage {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(GMETestUtils.createSchema(new URI("gme://a"), new File(SCHEMA_A)));
        schemas.add(GMETestUtils.createSchema(new URI("gme://b"), new File(SCHEMA_B)));
        schemas.add(GMETestUtils.createSchema(new URI("http://www.w3.org/XML/1998/namespace"), new File(SCHEMA_XML)));

        PublishXMLSchemasRequest req = new PublishXMLSchemasRequest();
        PublishXMLSchemasRequest.Schemas reqschemas = new PublishXMLSchemasRequest.Schemas();
        reqschemas.getXMLSchema().addAll(schemas);
        req.setSchemas(reqschemas);
        gme.publishXMLSchemas(req);
    }

    private void publishBadXMLSchemas(GlobalModelExchangePortType gme) throws URISyntaxException, IOException {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(GMETestUtils.createSchema(new URI("gme://ac"), new File(SCHEMA_A)));
        schemas.add(GMETestUtils.createSchema(new URI("gme://bc"), new File(SCHEMA_B)));

        PublishXMLSchemasRequest req = new PublishXMLSchemasRequest();
        PublishXMLSchemasRequest.Schemas reqschemas = new PublishXMLSchemasRequest.Schemas();
        reqschemas.getXMLSchema().addAll(schemas);
        req.setSchemas(reqschemas);
        try {
            gme.publishXMLSchemas(req);
        } catch(InvalidSchemaSubmissionFaultFaultMessage e) {
            // expected
            System.out.println("####################### Expected fault caught ##########################");
            System.out.println("Got expected exception " + e.getMessage());
            return;
        }

        fail("Should have thrown fault");
    }
    
    private void getXMLSchema(GlobalModelExchangePortType gme) throws URISyntaxException, NoSuchNamespaceExistsFaultFaultMessage {
    	GetXMLSchemaRequest request = new GetXMLSchemaRequest();
    	TargetNamespace tn = new TargetNamespace();
    	XMLSchemaNamespace xmlsn = new XMLSchemaNamespace();
    	xmlsn.setUri(new URI("http://www.w3.org/XML/1998/namespace"));
    	tn.setXMLSchemaNamespace(xmlsn);
    	request.setTargetNamespace(tn);
    	GetXMLSchemaResponse response = gme.getXMLSchema(request);
    	assertNotNull(response);
    	assertNotNull(response.getXMLSchema());
    }
}
