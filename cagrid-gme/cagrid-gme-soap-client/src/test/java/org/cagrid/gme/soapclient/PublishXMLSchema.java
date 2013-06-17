package org.cagrid.gme.soapclient;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.model.XMLSchemaDocument;
import org.cagrid.gme.model.XMLSchemaNamespace;
import org.cagrid.gme.wsrf.stubs.GetXMLSchemaNamespacesRequest;
import org.cagrid.gme.wsrf.stubs.GetXMLSchemaNamespacesResponse;
import org.cagrid.gme.wsrf.stubs.InvalidSchemaSubmissionFaultFaultMessage;
import org.cagrid.gme.wsrf.stubs.PublishXMLSchemasRequest;
import org.cagrid.gme.wsrf.stubs.PublishXMLSchemasResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PublishXMLSchema extends GrouperClientBase {

	private PublishXMLSchema(String url) throws Exception {
		super(url);
	}

    public void publishXMLSchema(List<XMLSchema> schema) throws InvalidSchemaSubmissionFaultFaultMessage {
        PublishXMLSchemasRequest req = new PublishXMLSchemasRequest();
        PublishXMLSchemasRequest.Schemas schemas = new PublishXMLSchemasRequest.Schemas();
        schemas.getXMLSchema().addAll(schema);
        req.setSchemas(schemas);
        gme.publishXMLSchemas(req);
    }

	public static void main(String[] args) {

        try {
		    PublishXMLSchema data = new PublishXMLSchema(LOCAL_URL);

            List<XMLSchema> schemas = new ArrayList<XMLSchema>();
            schemas.add(PublishXMLSchema.createSchema(new URI("gme://a"),
                    new File("cagrid-gme/cagrid-gme-soap-client/src/test/resources/A.xsd")));
            schemas.add(PublishXMLSchema.createSchema(new URI("gme://b"),
                    new File("cagrid-gme/cagrid-gme-soap-client/src/test/resources/B.xsd")));
            data.publishXMLSchema(schemas);


        } catch(Exception e) {
            System.out.println(ExceptionUtils.getFullStackTrace(e));
        }

	}

    public static XMLSchema createSchema(URI namespace, File schemaFile) throws FileNotFoundException, IOException {
        List<File> list = new ArrayList<File>();
        list.add(schemaFile);
        return createSchema(namespace, list);
    }

    public static XMLSchema createSchema(URI namespace, List<File> schemaFiles) throws FileNotFoundException,
            IOException {
        if (schemaFiles == null || schemaFiles.size() == 0) {
            throw new IllegalArgumentException("schemaFiles must be a valid array of files.");
        }

        XMLSchemaDocument root = createSchemaDocument(schemaFiles.get(0));

        Set<XMLSchemaDocument> docs = new HashSet<XMLSchemaDocument>(schemaFiles.size() - 1);
        for (int i = 1; i < schemaFiles.size(); i++) {
            docs.add(createSchemaDocument(schemaFiles.get(i)));
        }

        XMLSchema schema = new XMLSchema();
        schema.setTargetNamespace(namespace);
        schema.setRootDocument(root);
        schema.setAdditionalSchemaDocuments(docs);

        return schema;
    }

    public static XMLSchemaDocument createSchemaDocument(File schemaFile) throws FileNotFoundException, IOException {
        if (schemaFile == null || !schemaFile.canRead()) {
            throw new IllegalArgumentException("schemaFile [" + schemaFile.getAbsolutePath() + "] must be a valid, readable file.");
        }

        FileInputStream fileInputStream = new FileInputStream(schemaFile);
        String fileContents = IOUtils.toString(fileInputStream);
        fileInputStream.close();
        String systemID = schemaFile.getName();

        return new XMLSchemaDocument(fileContents, systemID);
    }
}
