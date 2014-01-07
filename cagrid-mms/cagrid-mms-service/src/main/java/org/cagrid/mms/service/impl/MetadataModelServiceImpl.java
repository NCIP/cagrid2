package org.cagrid.mms.service.impl;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cagrid.core.common.FaultHelper;
import org.cagrid.core.resource.ResourceImpl;
import org.cagrid.core.resource.SingletonResourceHomeImpl;
import org.cagrid.mms.model.ModelSourceMetadata;
import org.cagrid.mms.model.NamespaceToProjectMapping;
import org.cagrid.mms.model.UMLAssociationExclude;
import org.cagrid.mms.model.UMLProjectIdentifer;
import org.cagrid.mms.service.InvalidUMLProjectIndentifier;
import org.cagrid.mms.service.MetadataModelService;
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

}
