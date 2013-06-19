package org.cagrid.gme.service;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.model.XMLSchemaBundle;
import org.cagrid.gme.model.XMLSchemaNamespace;
import org.cagrid.gme.service.exception.InvalidSchemaSubmissionException;
import org.cagrid.gme.service.exception.NoSuchNamespaceExistsException;
import org.cagrid.gme.service.exception.UnableToDeleteSchemaException;
import org.cagrid.wsrf.properties.ResourceHome;

import java.rmi.RemoteException;
import java.util.List;

public interface GlobalModelExchangeService {
    ResourceHome getResourceHome();

    void publishXMLSchemas(List<XMLSchema> schemas) throws InvalidSchemaSubmissionException;

    XMLSchema getXMLSchema(XMLSchemaNamespace targetNamespace) throws NoSuchNamespaceExistsException;

    List<XMLSchemaNamespace> getXMLSchemaNamespaces() throws RemoteException;

    void deleteXMLSchemas(List<XMLSchemaNamespace> targetNamespaces) throws UnableToDeleteSchemaException, NoSuchNamespaceExistsException;

    XMLSchemaBundle getXMLSchemaAndDependencies(XMLSchemaNamespace targetNamespace) throws NoSuchNamespaceExistsException;

    List<XMLSchemaNamespace> getImportedXMLSchemaNamespaces(XMLSchemaNamespace targetNamespace) throws NoSuchNamespaceExistsException;

    List<XMLSchemaNamespace> getImportingXMLSchemaNamespaces(XMLSchemaNamespace targetNamespace) throws NoSuchNamespaceExistsException;

    ServiceSecurityMetadata getServiceSecurityMetadata();

    ServiceMetadata getServiceMetadata();
}
