package org.cagrid.gme.test.utils;

import org.apache.commons.io.IOUtils;
import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.model.XMLSchemaDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GMETestUtils {
    public static XMLSchema createSchema(URI namespace, File schemaFile) throws IOException {
        List<File> list = new ArrayList<File>();
        list.add(schemaFile);
        return createSchema(namespace, list);
    }

    public static XMLSchema createSchema(URI namespace, List<File> schemaFiles) throws IOException {
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

    public static XMLSchemaDocument createSchemaDocument(File schemaFile) throws IOException {
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
