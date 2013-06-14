package org.cagrid.gme.service.impl;

import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.service.exception.InvalidSchemaSubmissionException;
import org.cagrid.gme.service.impl.testutils.GMETestCaseBase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.ExpectedException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;


public class GMEAddSchemaRedefinesTestCase extends GMETestCaseBase {

    @Resource
    protected XMLSchema testSchemaRedefine;
    @Resource
    protected XMLSchema testSchemaRedefined;
    @Resource
    protected XMLSchema testSchemaRedefineNoNamespace;
    @Resource
    protected XMLSchema testInvalidSchemaRedefineWrongNamespace;
    @Resource
    protected XMLSchema testSchemaRedefineWrongNamespaceRedefinedOnly;

    @Before
    public void onSetUp() throws Exception {
        assertNotNull(this.testSchemaRedefine);
        assertNotNull(this.testSchemaRedefined);
        assertNotNull(this.testSchemaRedefineNoNamespace);
        assertNotNull(this.testInvalidSchemaRedefineWrongNamespace);
        assertNotNull(this.testSchemaRedefineWrongNamespaceRedefinedOnly);
    }

    @Test
    public void testSchemaRedefine() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaRedefine);
        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);

    }

    @Test
    public void testSchemaRedefined() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaRedefined);
        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }

    @Test
    public void testSchemaRedefineNoNamespace() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaRedefineNoNamespace);
        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }

    @Test
    public void testSchemaRedefineWrongNamespaceRedefinedOnly() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaRedefineWrongNamespaceRedefinedOnly);
        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }

    @Test
    @ExpectedException(value = InvalidSchemaSubmissionException.class)
    public void testInvalidSchemaRedefineWrongNamespace() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testInvalidSchemaRedefineWrongNamespace);
        this.gme.publishSchemas(schemas);
    }

}
