package org.cagrid.gme.service.impl.testutils;

import gov.nih.nci.cagrid.common.Utils;
import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.service.exception.InvalidSchemaSubmissionException;
import org.cagrid.gme.service.exception.NoSuchNamespaceExistsException;

import java.util.ArrayList;
import java.util.List;


public abstract class GMETestCaseWithSimpleModel extends GMETestCaseBase {

    // these are loaded by Spring
    protected XMLSchema testSchemaSimpleA;
    protected XMLSchema testSchemaSimpleB;
    protected XMLSchema testSchemaSimpleC;
    protected XMLSchema testSchemaSimpleD;
    protected XMLSchema testSchemaSimpleE;
    protected XMLSchema testSchemaSimpleF;


    @Override
    protected String[] getConfigLocations() {
        return (String[]) Utils.appendToArray(super.getConfigLocations(),
                SpringTestApplicationContextConstants.SIMPLE_LOCATION);
    }


    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
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
