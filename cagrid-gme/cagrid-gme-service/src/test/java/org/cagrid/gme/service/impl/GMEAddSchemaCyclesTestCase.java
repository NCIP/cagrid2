package org.cagrid.gme.service.impl;

import gov.nih.nci.cagrid.common.Utils;
import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.service.exception.InvalidSchemaSubmissionException;
import org.cagrid.gme.service.impl.testutils.GMETestCaseBase;
import org.cagrid.gme.service.impl.testutils.SpringTestApplicationContextConstants;
import org.springframework.test.annotation.ExpectedException;

import java.util.ArrayList;
import java.util.List;


public class GMEAddSchemaCyclesTestCase extends GMETestCaseBase {

    // these are loaded by Spring
    protected XMLSchema testSchemaCycleA;
    protected XMLSchema testSchemaCycleB;


    @Override
    protected String[] getConfigLocations() {
        return (String[]) Utils.appendToArray(super.getConfigLocations(),
                SpringTestApplicationContextConstants.CYCLES_LOCATION);
    }


    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testCycleAMissingDocumentB() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaCycleA);
        this.gme.publishSchemas(schemas);
    }


    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testCycleBMissingDocumentA() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaCycleB);
        this.gme.publishSchemas(schemas);
    }


    public void testCyclesAFirst() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaCycleA);
        schemas.add(this.testSchemaCycleB);

        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }


    public void testCyclesBFirst() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaCycleB);
        schemas.add(this.testSchemaCycleA);

        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }
}
