package org.cagrid.gme.service.impl;

import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.service.impl.testutils.GMETestCaseBase;
import org.cagrid.gme.service.impl.testutils.SpringTestApplicationContextConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;

public class GMEAddSchemaIncludesTestCase extends GMETestCaseBase {

    @Resource
    protected XMLSchema testSchemaInclude;
    @Resource
    protected XMLSchema testSchemaIncludeCycle;
    @Resource
    protected XMLSchema testSchemaIncludeNoNamespace;

    @Before
    public void onSetUp() throws Exception {
        assertNotNull(this.testSchemaInclude);
        assertNotNull(this.testSchemaIncludeCycle);
        assertNotNull(this.testSchemaIncludeNoNamespace);
    }

    @Test
    public void testSchemaInclude() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaInclude);
        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }

    @Test
    public void testSchemaIncludeCycle() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaIncludeCycle);
        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }

    @Test
    public void testSchemaIncludeNoNamespace() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaIncludeNoNamespace);
        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }
}
