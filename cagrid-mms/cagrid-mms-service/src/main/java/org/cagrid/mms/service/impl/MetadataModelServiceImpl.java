package org.cagrid.mms.service.impl;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cagrid.core.resource.JAXBResourceProperties;
import org.cagrid.core.resource.JAXBResourcePropertySupport;
import org.cagrid.core.resource.ResourceImpl;
import org.cagrid.core.resource.ResourcePropertyDescriptor;
import org.cagrid.core.resource.SingletonResourceHomeImpl;
import org.cagrid.mms.model.ModelSourceMetadata;
import org.cagrid.mms.model.NamespaceToProjectMapping;
import org.cagrid.mms.model.UMLAssociationExclude;
import org.cagrid.mms.model.UMLProjectIdentifer;
import org.cagrid.mms.service.InvalidUMLProjectIndentifier;
import org.cagrid.mms.service.MMS;
import org.cagrid.mms.service.MMSGeneralException;
import org.cagrid.mms.service.MetadataModelService;
import org.cagrid.mms.wsrf.stubs.MetadataModelServiceResourceProperties;
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceProperty;

/**
 * MMS Grid Service Implementation
 * 
 * @created by Introduce Toolkit version 1.3
 */
public class MetadataModelServiceImpl implements MetadataModelService {

	protected static Log LOG = LogFactory.getLog(MetadataModelServiceImpl.class
			.getName());

	private static final String MMS_BEAN_NAME = "mms";
	private MMS mms;

	private final ResourceImpl resource = new ResourceImpl(null);
	private final ResourceHome resourceHome = new SingletonResourceHomeImpl(
			resource);
	private final Map<String, String> jaxbResourcePropertiesMap;
	private ResourceProperty<ServiceMetadata> serviceMetadataResourceProperty;
	private ResourceProperty<ServiceSecurityMetadata> serviceSecurityMetadataResourceProperty;

	public MetadataModelServiceImpl(MMS mms,
			Map<String, String> jaxbResourcePropertiesMap)
			throws RemoteException {
		super();

		this.mms = mms;
		this.jaxbResourcePropertiesMap = jaxbResourcePropertiesMap;
	}

	protected MMS getMms() {
		return this.mms;
	}

	@Override
	public ResourceHome getResourceHome() {
		return resourceHome;
	}

	@Override
	public ServiceSecurityMetadata getServiceSecurityMetadata() {
		return (serviceSecurityMetadataResourceProperty != null) ? serviceSecurityMetadataResourceProperty
				.get(0) : null;
	}

	@Override
	public ServiceMetadata getServiceMetadata() {
		return (serviceMetadataResourceProperty != null) ? serviceMetadataResourceProperty
				.get(0) : null;
	}

	public gov.nih.nci.cagrid.metadata.dataservice.DomainModel generateDomainModelForProject(
			UMLProjectIdentifer umlProjectIdentifer)
			throws RemoteException,
			InvalidUMLProjectIndentifier {
		if (umlProjectIdentifer == null) {
			throw new InvalidUMLProjectIndentifier();
		}

		try {
			return getMms().generateDomainModelForProject(umlProjectIdentifer);
		} catch (MMSGeneralException e) {
			// TODO: replace with typed exception?
			throw new RemoteException(e.getMessage(), e);
		}
	}

	public gov.nih.nci.cagrid.metadata.dataservice.DomainModel generateDomainModelForPackages(
			UMLProjectIdentifer umlProjectIdentifer,
			java.lang.String[] packageNames) throws RemoteException,
			InvalidUMLProjectIndentifier {

		if (umlProjectIdentifer == null) {
			throw new InvalidUMLProjectIndentifier();
		}

		Collection<String> packages = new ArrayList<String>();
		if (packageNames != null) {
			for (String pkg : packageNames) {
				packages.add(pkg);
			}
		}

		try {
			return getMms().generateDomainModelForPackages(umlProjectIdentifer,
					packages);
		} catch (MMSGeneralException e) {
			// TODO: replace with typed exception?
			throw new RemoteException(e.getMessage(), e);
		}
	}

	public gov.nih.nci.cagrid.metadata.dataservice.DomainModel generateDomainModelForClasses(
			UMLProjectIdentifer umlProjectIdentifer,
			java.lang.String[] fullyQualifiedClassNames)
			throws RemoteException,
			InvalidUMLProjectIndentifier {

		if (umlProjectIdentifer == null) {
			throw new InvalidUMLProjectIndentifier();
		}

		Collection<String> classes = new ArrayList<String>();
		if (fullyQualifiedClassNames != null) {
			for (String className : fullyQualifiedClassNames) {
				classes.add(className);
			}
		}

		try {
			return getMms().generateDomainModelForClasses(umlProjectIdentifer,
					classes);
		} catch (MMSGeneralException e) {
			// TODO: replace with typed exception?
			throw new RemoteException(e.getMessage(), e);
		}
	}

	public gov.nih.nci.cagrid.metadata.dataservice.DomainModel generateDomainModelForClassesWithExcludes(
			UMLProjectIdentifer umlProjectIdentifer,
			java.lang.String[] fullyQualifiedClassNames,
			UMLAssociationExclude[] umlAssociationExclude)
			throws RemoteException,
			InvalidUMLProjectIndentifier {

		if (umlProjectIdentifer == null) {
			throw new InvalidUMLProjectIndentifier();
		}

		Collection<String> classes = new ArrayList<String>();
		if (fullyQualifiedClassNames != null) {
			for (String className : fullyQualifiedClassNames) {
				classes.add(className);
			}
		}

		Collection<UMLAssociationExclude> excludes = new ArrayList<UMLAssociationExclude>();
		if (umlAssociationExclude != null) {
			for (UMLAssociationExclude exclude : umlAssociationExclude) {
				excludes.add(exclude);
			}
		}

		try {
			return getMms().generateDomainModelForClassesWithExcludes(
					umlProjectIdentifer, classes, excludes);
		} catch (MMSGeneralException e) {
			// TODO: replace with typed exception?
			throw new RemoteException(e.getMessage(), e);
		}
	}

	public gov.nih.nci.cagrid.metadata.ServiceMetadata annotateServiceMetadata(
			gov.nih.nci.cagrid.metadata.ServiceMetadata serviceMetadata,
			NamespaceToProjectMapping[] namespaceToProjectMappings)
			throws RemoteException,
			InvalidUMLProjectIndentifier {

		Map<URI, UMLProjectIdentifer> mappings = new HashMap<URI, UMLProjectIdentifer>();
		if (namespaceToProjectMappings != null) {
			for (NamespaceToProjectMapping mapping : namespaceToProjectMappings) {
				try {
					if (mapping.getUMLProjectIdentifer() == null) {
						throw new InvalidUMLProjectIndentifier();
					}
					mappings.put(new URI(mapping.getNamespaceURI().toString()),
							mapping.getUMLProjectIdentifer());
				} catch (URISyntaxException e) {
					String message = "Problem parsing specified URI:"
							+ e.getMessage();
					LOG.error(message, e);
					throw new RemoteException(message);
				}
			}
		}

		try {
			return getMms().annotateServiceMetadata(serviceMetadata, mappings);
		} catch (MMSGeneralException e) {
			// TODO: replace with typed exception?
			throw new RemoteException(e.getMessage(), e);
		}
	}

	public ModelSourceMetadata getModelSourceMetadata()
			throws RemoteException {

		try {
			return getMms().getModelSourceMetadata();
		} catch (MMSGeneralException e) {
			// TODO: replace with typed exception?
			throw new RemoteException(e.getMessage(), e);
		}
	}
	
	 private void initialize() throws JAXBException {

	        // What resource properties should we know about?
	        Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors = ResourcePropertyDescriptor.analyzeResourcePropertiesHolder(MetadataModelServiceResourceProperties.class);

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
	                    QName serviceSecurityMetadataQName = new QName(MetadataModelServiceImpl.class.getName(), "serviceSecurityMetadata");
	                    ResourcePropertyDescriptor<ServiceSecurityMetadata> serviceSecurityMetadataDescriptor = new ResourcePropertyDescriptor<ServiceSecurityMetadata>(serviceSecurityMetadataQName,
	                            ServiceSecurityMetadata.class, "serviceSecurityMetadata");
	                    serviceSecurityMetadataResourceProperty = JAXBResourcePropertySupport.createJAXBResourceProperty(serviceSecurityMetadataDescriptor, url);
	                }
	            }
	        }
	    }

}
