package org.cagrid.gme.service.impl;

import gov.nih.nci.cagrid.common.Utils;
import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.service.impl.testutils.GMETestCaseBase;
import org.cagrid.gme.service.impl.testutils.SpringTestApplicationContextConstants;


import java.util.ArrayList;
import java.util.List;


public class GMEAddSchemaIncludesTestCase extends GMETestCaseBase {

    // these are loaded by Spring
    protected XMLSchema testSchemaInclude;
    protected XMLSchema testSchemaIncludeCycle;
    protected XMLSchema testSchemaIncludeNoNamespace;


    @Override
    protected String[] getConfigLocations() {
        return (String[]) Utils.appendToArray(super.getConfigLocations(),
                SpringTestApplicationContextConstants.INCLUDES_LOCATION);
    }


    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        assertNotNull(this.testSchemaInclude);
        assertNotNull(this.testSchemaIncludeCycle);
        assertNotNull(this.testSchemaIncludeNoNamespace);
    }


    public void testSchemaInclude() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaInclude);
        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }


    public void testSchemaIncludeCycle() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaIncludeCycle);
        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }


    public void testSchemaIncludeNoNamespace() throws Exception {
        List<XMLSchema> schemas = new ArrayList<XMLSchema>();
        schemas.add(this.testSchemaIncludeNoNamespace);
        this.gme.publishSchemas(schemas);

        assertPublishedContents(schemas);
    }
}
