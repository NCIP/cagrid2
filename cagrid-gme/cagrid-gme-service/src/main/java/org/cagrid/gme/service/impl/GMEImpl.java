package org.cagrid.gme.service.impl;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cagrid.core.resource.JAXBResourceProperties;
import org.cagrid.core.resource.JAXBResourcePropertySupport;
import org.cagrid.core.resource.ResourceImpl;
import org.cagrid.core.resource.ResourcePropertyDescriptor;
import org.cagrid.core.resource.SingletonResourceHomeImpl;
import org.cagrid.gme.model.XMLSchema;
import org.cagrid.gme.model.XMLSchemaBundle;
import org.cagrid.gme.model.XMLSchemaNamespace;
import org.cagrid.gme.service.GlobalModelExchangeService;
import org.cagrid.gme.service.exception.InvalidSchemaSubmissionException;
import org.cagrid.gme.service.exception.NoSuchNamespaceExistsException;
import org.cagrid.gme.service.exception.UnableToDeleteSchemaException;
import org.cagrid.gme.wsrf.stubs.GlobalModelExchangeResourceProperties;
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceProperty;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GMEImpl implements GlobalModelExchangeService {

    protected static Log LOG = LogFactory.getLog(GMEImpl.class.getName());

    protected GME gme = null;

    private final ResourceImpl resource = new ResourceImpl(null);
    private final ResourceHome resourceHome = new SingletonResourceHomeImpl(resource);
    private final Map<String, String> jaxbResourcePropertiesMap;
    private ResourceProperty<ServiceMetadata> serviceMetadataResourceProperty;
    private ResourceProperty<ServiceSecurityMetadata> serviceSecurityMetadataResourceProperty;

    public GMEImpl(GME gme, Map<String, String> jaxbResourcePropertiesMap) {
        this.gme = gme;
        this.jaxbResourcePropertiesMap = jaxbResourcePropertiesMap;
    }

    @Override
    public ResourceHome getResourceHome() {
        return resourceHome;
    }

    @Override
    public ServiceSecurityMetadata getServiceSecurityMetadata() {
        return (serviceSecurityMetadataResourceProperty != null) ? serviceSecurityMetadataResourceProperty.get(0) : null;
    }

    @Override
    public void publishXMLSchemas(List<XMLSchema> schemas) throws InvalidSchemaSubmissionException {
        this.gme.publishSchemas(schemas);
    }

    @Override
    public XMLSchema getXMLSchema(XMLSchemaNamespace targetNamespace) throws NoSuchNamespaceExistsException {
        URI uri = null;
        if (targetNamespace != null) {
            uri = targetNamespace.getURI();
        }
        return this.gme.getSchema(uri);
    }

    @Override
    public List<XMLSchemaNamespace> getXMLSchemaNamespaces() throws RemoteException {
        Collection<URI> namespaces = this.gme.getNamespaces();
        List<XMLSchemaNamespace> result = new ArrayList<XMLSchemaNamespace>();
        for (URI namespace : namespaces) {
            result.add(new XMLSchemaNamespace(namespace));
        }
        return result;
    }

    @Override
    public void deleteXMLSchemas(List<XMLSchemaNamespace> targetNamespaces) throws UnableToDeleteSchemaException, NoSuchNamespaceExistsException {
        List<URI> schemaNamespaces = new ArrayList<URI>();
        if (targetNamespaces != null) {
            for (XMLSchemaNamespace ns : targetNamespaces) {
                schemaNamespaces.add(ns.getURI());
            }
        }
        this.gme.deleteSchemas(schemaNamespaces);
    }

    @Override
    public XMLSchemaBundle getXMLSchemaAndDependencies(XMLSchemaNamespace targetNamespace) throws NoSuchNamespaceExistsException {

        URI uri = null;
        if (targetNamespace != null) {
            uri = targetNamespace.getURI();
        }
        return this.gme.getSchemBundle(uri);
    }

    @Override
    public List<XMLSchemaNamespace> getImportedXMLSchemaNamespaces(XMLSchemaNamespace targetNamespace) throws NoSuchNamespaceExistsException {
        URI uri = null;
        if (targetNamespace != null) {
            uri = targetNamespace.getURI();
        }

        List<XMLSchemaNamespace> result = null;
        Collection<URI> importedNamespaces = this.gme.getImportedNamespaces(uri);

        if (importedNamespaces != null) {
            result = new ArrayList<XMLSchemaNamespace>();
            Iterator<URI> iterator = importedNamespaces.iterator();
            for (int i = 0; i < importedNamespaces.size(); i++) {
                result.add(new XMLSchemaNamespace(iterator.next()));
            }
        }

        return result;
    }

    @Override
    public List<XMLSchemaNamespace> getImportingXMLSchemaNamespaces(XMLSchemaNamespace targetNamespace) throws NoSuchNamespaceExistsException {

        URI uri = null;
        if (targetNamespace != null) {
            uri = targetNamespace.getURI();
        }

        List<XMLSchemaNamespace> result = null;
        Collection<URI> importingNamespaces = this.gme.getImportingNamespaces(uri);

        if (importingNamespaces != null) {
            result = new ArrayList<XMLSchemaNamespace>();
            Iterator<URI> iterator = importingNamespaces.iterator();
            for (int i = 0; i < importingNamespaces.size(); i++) {
                result.add(new XMLSchemaNamespace(iterator.next()));
            }
        }

        return result;
    }

    private void initialize() throws JAXBException {

        // What resource properties should we know about?
        Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors = ResourcePropertyDescriptor.analyzeResourcePropertiesHolder(GlobalModelExchangeResourceProperties.class);

        // Map them by field.
        Map<String, ResourcePropertyDescriptor<?>> descriptorsByField = ResourcePropertyDescriptor.mapByField(resourcePropertyDescriptors);

        // Load the static jaxb resource properties.
        if (jaxbResourcePropertiesMap != null) {
            JAXBResourceProperties jaxbResourceProperties = new JAXBResourceProperties(getClass().getClassLoader(), descriptorsByField, jaxbResourcePropertiesMap);

            // The serviceMetadata property is static.
            @SuppressWarnings("unchecked")
            ResourcePropertyDescriptor<ServiceMetadata> serviceMetadataDescriptor = (ResourcePropertyDescriptor<ServiceMetadata>) descriptorsByField.get("serviceMetadata");
            if (serviceMetadataDescriptor != null) {
                @SuppressWarnings("unchecked")
                ResourceProperty<ServiceMetadata> resourceProperty = (ResourceProperty<ServiceMetadata>) jaxbResourceProperties.getResourceProperties().get(serviceMetadataDescriptor);
                serviceMetadataResourceProperty = resourceProperty;
                resource.add(serviceMetadataResourceProperty);
            }

			/*
			 * ServiceSecurityMetadata isn't a resource property, but use that
			 * framework to handle it.
			 */
            String serviceSecurityMetadataURLString = jaxbResourcePropertiesMap.get("serviceSecurityMetadata");
            if (serviceSecurityMetadataURLString != null) {
                URL url = null;
                try {
                    url = new URL(serviceSecurityMetadataURLString);
                } catch (MalformedURLException ignored) {
                }
                if (url == null) {
                    url = getClass().getClassLoader().getResource(serviceSecurityMetadataURLString);
                }
                if (url != null) {
                    QName serviceSecurityMetadataQName = new QName(GMEImpl.class.getName(), "serviceSecurityMetadata");
                    ResourcePropertyDescriptor<ServiceSecurityMetadata> serviceSecurityMetadataDescriptor = new ResourcePropertyDescriptor<ServiceSecurityMetadata>(serviceSecurityMetadataQName,
                            ServiceSecurityMetadata.class, "serviceSecurityMetadata");
                    serviceSecurityMetadataResourceProperty = JAXBResourcePropertySupport.createJAXBResourceProperty(serviceSecurityMetadataDescriptor, url);
                }
            }
        }
    }
}

