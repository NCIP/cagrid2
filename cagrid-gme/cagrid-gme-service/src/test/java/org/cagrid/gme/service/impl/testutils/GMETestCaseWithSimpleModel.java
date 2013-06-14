package org.cagrid.gme.service.impl.testutils;

import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.service.exception.InvalidSchemaSubmissionException;
import org.cagrid.gme.service.exception.NoSuchNamespaceExistsException;
import org.junit.Before;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;


public abstract class GMETestCaseWithSimpleModel extends GMETestCaseBase {

    @Resource
    protected XMLSchema testSchemaSimpleA;
    @Resource
    protected XMLSchema testSchemaSimpleB;
    @Resource
    protected XMLSchema testSchemaSimpleC;
    @Resource
    protected XMLSchema testSchemaSimpleD;
    @Resource
    protected XMLSchema testSchemaSimpleE;
    @Resource
    protected XMLSchema testSchemaSimpleF;

    @Before
    public void onSetUp() throws Exception {
        assertNotNull(this.testSchemaSimpleA);
        assertNotNull(this.testSchemaSimpleB);
        assertNotNull(this.testSchemaSimpleC);
        assertNotNull(this.testSchemaSimpleD);
        assertNotNull(this.testSchemaSimpleE);
        assertNotNull(this.testSchemaSimpleF);
    }

    protected void publishAllSchemas() throws InvalidSchemaSubmissionException, NoSuchNamespaceExistsException {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaSimpleA);
        schemas.add(this.testSchemaSimpleB);
        schemas.add(this.testSchemaSimpleC);
        schemas.add(this.testSchemaSimpleD);
        schemas.add(this.testSchemaSimpleE);
        schemas.add(this.testSchemaSimpleF);
        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }

}
