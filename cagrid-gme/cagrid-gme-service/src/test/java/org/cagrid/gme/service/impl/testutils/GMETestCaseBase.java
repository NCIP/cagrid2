package org.cagrid.gme.service.impl.testutils;

import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.model.XMLSchemaBundle;
import org.cagrid.gme.model.XMLSchemaImportInformation;
import org.cagrid.gme.model.XMLSchemaNamespace;
import org.cagrid.gme.service.GlobalModelExchangeService;
import org.cagrid.gme.service.exception.NoSuchNamespaceExistsException;
import org.cagrid.gme.service.impl.GME;
import org.cagrid.gme.service.impl.GMEImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations={
                SpringTestApplicationContextConstants.GME_BASE_LOCATION,
                SpringTestApplicationContextConstants.TEST_BASE_LOCATION,
                SpringTestApplicationContextConstants.CYCLES_LOCATION,
                SpringTestApplicationContextConstants.ERRORS_LOCATION,
                SpringTestApplicationContextConstants.INCLUDES_LOCATION,
                SpringTestApplicationContextConstants.REDEFINES_LOCATION,
                SpringTestApplicationContextConstants.SIMPLE_LOCATION
        })
public abstract class GMETestCaseBase extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource
    protected GME gme;

    protected GlobalModelExchangeService serviceImpl;

    @Before
    public void onSetUp() throws Exception {
        assertNotNull(this.gme);
        serviceImpl = new GMEImpl(gme, null);
    }

    protected void assertPublishedContents(XMLSchema schema) throws NoSuchNamespaceExistsException {
        List<XMLSchema> list = new ArrayList<XMLSchema>(1);
        list.add(schema);
        assertPublishedContents(list);
    }


    protected void assertPublishedContents(List<XMLSchema> schemas) throws NoSuchNamespaceExistsException {
        Collection<URI> namespaces = this.gme.getNamespaces();
        assertEquals(schemas.size(), namespaces.size());

        for (XMLSchema schema : schemas) {
            namespaces.contains(schema.getTargetNamespace());
            assertEquals(schema, this.gme.getSchema(schema.getTargetNamespace()));
        }
    }


    protected void assertSchemaImportsSchema(XMLSchema importer, XMLSchema imported) throws NoSuchNamespaceExistsException {
        Collection<URI> importedNamespaces = this.gme.getImportedNamespaces(importer.getTargetNamespace());
        assertTrue(importedNamespaces.contains(imported.getTargetNamespace()));

        Collection<URI> importingNamespaces = this.gme.getImportingNamespaces(imported.getTargetNamespace());
        assertTrue(importingNamespaces.contains(importer.getTargetNamespace()));

        XMLSchemaBundle schemaBundle = this.gme.getSchemBundle(importer.getTargetNamespace());
        XMLSchemaImportInformation ii = schemaBundle.getImportInformationForTargetNamespace(new XMLSchemaNamespace(
            importer.getTargetNamespace()));
        assertTrue(ii.getImports().contains(new XMLSchemaNamespace(imported.getTargetNamespace())));
    }


    protected void assertNotPublished(XMLSchema schema) throws NoSuchNamespaceExistsException {
        List<XMLSchema> list = new ArrayList<XMLSchema>(1);
        list.add(schema);
        assertNotPublished(list);
    }


    protected void assertNotPublished(List<XMLSchema> schemas) throws NoSuchNamespaceExistsException {
        for (XMLSchema schema : schemas) {
            // Make sure the namespace isn't in the list
            assertFalse(this.gme.getNamespaces().contains(schema.getTargetNamespace()));

            // Make sure an error is raised when it's asked to be returned
            try {
                this.gme.getSchema(schema.getTargetNamespace());
                fail("The deleted schema should no longer exist, but was returned.");
            } catch (NoSuchNamespaceExistsException f) {
                // expected
            }
        }
    }


    protected void assertNoImports(XMLSchema schema) throws NoSuchNamespaceExistsException {
        assertEquals(0, this.gme.getImportedNamespaces(schema.getTargetNamespace()).size());
    }


    protected void assertNotImported(XMLSchema schema) throws NoSuchNamespaceExistsException {
        assertEquals(0, this.gme.getImportingNamespaces(schema.getTargetNamespace()).size());
    }
}
