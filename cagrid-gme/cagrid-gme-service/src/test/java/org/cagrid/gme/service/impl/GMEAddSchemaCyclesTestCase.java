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

public class GMEAddSchemaCyclesTestCase extends GMETestCaseBase {

    @Resource
    protected XMLSchema testSchemaCycleA;
    @Resource
    protected XMLSchema testSchemaCycleB;

    @Before
    public void onSetUp() throws Exception {
        assertNotNull(this.testSchemaCycleA);
        assertNotNull(this.testSchemaCycleB);
    }

    @Test
    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testCycleAMissingDocumentB() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaCycleA);
        this.gme.publishSchemas(schemas);
    }

    @Test
    @ExpectedException(InvalidSchemaSubmissionException.class)
    public void testCycleBMissingDocumentA() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaCycleB);
        this.gme.publishSchemas(schemas);
    }

    @Test
    public void testCyclesAFirst() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaCycleA);
        schemas.add(this.testSchemaCycleB);

        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }

    @Test
    public void testCyclesBFirst() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaCycleB);
        schemas.add(this.testSchemaCycleA);

        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }
}
