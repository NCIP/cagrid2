package org.cagrid.gme.service.impl;

import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.model.XMLSchemaNamespace;
import org.cagrid.gme.service.exception.InvalidSchemaSubmissionException;
import org.cagrid.gme.service.exception.NoSuchNamespaceExistsException;
import org.cagrid.gme.service.impl.testutils.GMETestCaseBase;
import org.junit.Test;
import org.springframework.test.annotation.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
* Probes the GME grid service interface for issues dealing with the conversion
* to/from empty arrays and dealing with null. Correct function of the
* conversion dealing with real data is tested by the system tests against a
* deployed service.
*/

public class GMEServiceInterfaceErrorsTestCase extends GMETestCaseBase {

    @Test
    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testEmptySubmission() throws Exception {
        this.serviceImpl.publishXMLSchemas(new ArrayList<XMLSchema>());
    }

    @Test
    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testNullSubmission() throws Exception {
        this.serviceImpl.publishXMLSchemas(null);
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testNullDelete() throws Exception {
        this.serviceImpl.deleteXMLSchemas(null);
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testEmptyDelete() throws Exception {
        this.serviceImpl.deleteXMLSchemas(new ArrayList<XMLSchemaNamespace>());
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testNullImported() throws Exception {
        this.serviceImpl.getImportedXMLSchemaNamespaces(null);
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testNullImporting() throws Exception {
        this.serviceImpl.getImportingXMLSchemaNamespaces(null);
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testInvalidImported() throws Exception {
        XMLSchemaNamespace ns = new XMLSchemaNamespace("http://invalid");
        this.serviceImpl.getImportedXMLSchemaNamespaces(ns);
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testInvalidImporting() throws Exception {
        XMLSchemaNamespace ns = new XMLSchemaNamespace("http://invalid");
        this.serviceImpl.getImportingXMLSchemaNamespaces(ns);
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testEmptyImported() throws Exception {
        XMLSchemaNamespace ns = new XMLSchemaNamespace();
        this.serviceImpl.getImportedXMLSchemaNamespaces(ns);
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testEmptyImporting() throws Exception {
        XMLSchemaNamespace ns = new XMLSchemaNamespace();
        this.serviceImpl.getImportingXMLSchemaNamespaces(ns);
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testEmptyGet() throws Exception {
        XMLSchemaNamespace ns = new XMLSchemaNamespace();
        this.serviceImpl.getXMLSchema(ns);
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testNullGet() throws Exception {
        this.serviceImpl.getXMLSchema(null);
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testInvalidGet() throws Exception {
        XMLSchemaNamespace ns = new XMLSchemaNamespace("http://invalid");
        this.serviceImpl.getXMLSchema(ns);
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testInvalidBundle() throws Exception {
        XMLSchemaNamespace ns = new XMLSchemaNamespace("http://invalid");
        this.serviceImpl.getXMLSchemaAndDependencies(ns);
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testEmptyBundle() throws Exception {
        XMLSchemaNamespace ns = new XMLSchemaNamespace();
        this.serviceImpl.getXMLSchemaAndDependencies(ns);
    }

    @Test
    @ExpectedException(NoSuchNamespaceExistsException.class)
    public void testNullBundle() throws Exception {
        this.serviceImpl.getXMLSchemaAndDependencies(null);
    }

    @Test
    public void testGetNamespaces() throws Exception {
        List<XMLSchemaNamespace> schemaNamespaces = this.serviceImpl.getXMLSchemaNamespaces();
        assertEquals(0, schemaNamespaces.size());
    }

}
