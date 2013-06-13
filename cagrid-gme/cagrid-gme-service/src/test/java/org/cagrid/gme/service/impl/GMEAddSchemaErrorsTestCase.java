package org.cagrid.gme.service.impl;

import gov.nih.nci.cagrid.common.Utils;
import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.service.exception.InvalidSchemaSubmissionException;
import org.cagrid.gme.service.impl.testutils.GMETestCaseBase;
import org.cagrid.gme.service.impl.testutils.SpringTestApplicationContextConstants;
import org.springframework.test.annotation.ExpectedException;

import java.util.ArrayList;
import java.util.List;


public class GMEAddSchemaErrorsTestCase extends GMETestCaseBase {

    // these are loaded by Spring
    protected XMLSchema testSchemaDuplicates;
    protected XMLSchema testSchemaMissingInclude;
    protected XMLSchema testSchemaMissingType;
    protected XMLSchema testSchemaNoNamespace;
    protected XMLSchema testSchemaWrongNamespace;
    protected XMLSchema testSchemaNoImports;


    @Override
    protected String[] getConfigLocations() {
        return (String[]) Utils.appendToArray(super.getConfigLocations(),
                SpringTestApplicationContextConstants.ERRORS_LOCATION);
    }


    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        assertNotNull(this.testSchemaDuplicates);
        assertNotNull(this.testSchemaMissingInclude);
        assertNotNull(this.testSchemaMissingType);
        assertNotNull(this.testSchemaNoNamespace);
        assertNotNull(this.testSchemaWrongNamespace);
        assertNotNull(this.testSchemaNoImports);
    }


    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testEmptySubmission() throws Exception {
        this.gme.publishSchemas(new ArrayList<XMLSchema>());
    }


    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testNullSubmission() throws Exception {
        this.gme.publishSchemas(null);
    }


    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testSchemaDuplicates() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaDuplicates);
        this.gme.publishSchemas(schemas);
    }


    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testSchemaMissingInclude() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaMissingInclude);
        this.gme.publishSchemas(schemas);
    }


    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testSchemaMissingType() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaMissingType);
        this.gme.publishSchemas(schemas);
    }


    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testSchemaNoNamespace() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaNoNamespace);
        this.gme.publishSchemas(schemas);
    }


    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testSchemaWrongNamespace() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaWrongNamespace);
        this.gme.publishSchemas(schemas);

    }


    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testSchemaNoImports() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaNoImports);
        this.gme.publishSchemas(schemas);

    }

}
