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

public class GMEAddSchemaErrorsTestCase extends GMETestCaseBase {

    @Resource
    protected XMLSchema testSchemaDuplicates;
    @Resource
    protected XMLSchema testSchemaMissingInclude;
    @Resource
    protected XMLSchema testSchemaMissingType;
    @Resource
    protected XMLSchema testSchemaNoNamespace;
    @Resource
    protected XMLSchema testSchemaWrongNamespace;
    @Resource
    protected XMLSchema testSchemaNoImports;

    @Before
    public void onSetUp() throws Exception {
        assertNotNull(this.testSchemaDuplicates);
        assertNotNull(this.testSchemaMissingInclude);
        assertNotNull(this.testSchemaMissingType);
        assertNotNull(this.testSchemaNoNamespace);
        assertNotNull(this.testSchemaWrongNamespace);
        assertNotNull(this.testSchemaNoImports);
    }

    @Test
    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testEmptySubmission() throws Exception {
        this.gme.publishSchemas(new ArrayList<XMLSchema>());
    }

    @Test
    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testNullSubmission() throws Exception {
        this.gme.publishSchemas(null);
    }

    @Test
    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testSchemaDuplicates() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaDuplicates);
        this.gme.publishSchemas(schemas);
    }

    @Test
    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testSchemaMissingInclude() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaMissingInclude);
        this.gme.publishSchemas(schemas);
    }

    @Test
    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testSchemaMissingType() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaMissingType);
        this.gme.publishSchemas(schemas);
    }

    @Test
    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testSchemaNoNamespace() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaNoNamespace);
        this.gme.publishSchemas(schemas);
    }

    @Test
    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testSchemaWrongNamespace() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaWrongNamespace);
        this.gme.publishSchemas(schemas);

    }

    @Test
    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testSchemaNoImports() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaNoImports);
        this.gme.publishSchemas(schemas);

    }

}
