package org.cagrid.mms.service.wsrf;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;

import java.rmi.RemoteException;
import java.util.Iterator;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;

import org.cagrid.core.common.JAXBUtils;
import org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataRequest;
import org.cagrid.gaards.security.servicesecurity.GetServiceSecurityMetadataResponse;
import org.cagrid.mms.model.ModelSourceMetadata;
import org.cagrid.mms.model.NamespaceToProjectMapping;
import org.cagrid.mms.model.UMLAssociationExclude;
import org.cagrid.mms.service.InvalidUMLProjectIndentifier;
import org.cagrid.mms.service.MetadataModelService;
import org.cagrid.mms.wsrf.stubs.AnnotateServiceMetadataRequest;
import org.cagrid.mms.wsrf.stubs.AnnotateServiceMetadataResponse;
import org.cagrid.mms.wsrf.stubs.GenerateDomainModelForClassesRequest;
import org.cagrid.mms.wsrf.stubs.GenerateDomainModelForClassesResponse;
import org.cagrid.mms.wsrf.stubs.GenerateDomainModelForClassesWithExcludesRequest;
import org.cagrid.mms.wsrf.stubs.GenerateDomainModelForClassesWithExcludesResponse;
import org.cagrid.mms.wsrf.stubs.GenerateDomainModelForPackagesRequest;
import org.cagrid.mms.wsrf.stubs.GenerateDomainModelForPackagesResponse;
import org.cagrid.mms.wsrf.stubs.GenerateDomainModelForProjectRequest;
import org.cagrid.mms.wsrf.stubs.GenerateDomainModelForProjectResponse;
import org.cagrid.mms.wsrf.stubs.GetModelSourceMetadataRequest;
import org.cagrid.mms.wsrf.stubs.GetModelSourceMetadataResponse;
import org.cagrid.mms.wsrf.stubs.InvalidUMLProjectIndentifierFaultMessage;
import org.cagrid.mms.wsrf.stubs.MetadataModelServicePortType;
import org.cagrid.wsrf.properties.InvalidResourceKeyException;
import org.cagrid.wsrf.properties.NoSuchResourceException;
import org.cagrid.wsrf.properties.Resource;
import org.cagrid.wsrf.properties.ResourceException;
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceProperty;
import org.cagrid.wsrf.properties.ResourcePropertySet;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourceProperties;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetMultipleResourcePropertiesResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.GetResourcePropertyResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourceProperties;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01.QueryResourcePropertiesResponse;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidQueryExpressionFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.InvalidResourcePropertyQNameFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.QueryEvaluationErrorFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.ResourceUnknownFault;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01_wsdl.UnknownQueryExpressionDialectFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

public class MMSWSRFImpl implements MetadataModelServicePortType {

	private final static String ANONYMOUS_ID = "anonymous";

	private final static Logger LOG = LoggerFactory
			.getLogger(MMSWSRFImpl.class);

	private final MetadataModelService mms;
	private final ResourceHome resourceHome;

	@javax.annotation.Resource
	private WebServiceContext wsContext;

	public MMSWSRFImpl(MetadataModelService mms) {
		this.mms = mms;
		resourceHome = mms.getResourceHome();
	}

	@Override
	public GetMultipleResourcePropertiesResponse getMultipleResourceProperties(
			GetMultipleResourceProperties getMultipleResourcePropertiesRequest)
			throws ResourceUnknownFault, InvalidResourcePropertyQNameFault {
		LOG.info("getMultipleResourceProperty "
				+ getMultipleResourcePropertiesRequest);
		System.out.println(getMultipleResourcePropertiesRequest);
		GetMultipleResourcePropertiesResponse response = new GetMultipleResourcePropertiesResponse();
		for (Iterator iterator = getMultipleResourcePropertiesRequest
				.getResourceProperty().iterator(); iterator.hasNext();) {
			QName qname = (QName) iterator.next();
			Exception e;
			try {
				Resource resource = resourceHome.find(null);
				if (resource instanceof ResourcePropertySet) {
					ResourcePropertySet resourcePropertySet = (ResourcePropertySet) resource;
					ResourceProperty<?> resourceProperty = resourcePropertySet
							.get(qname);
					if (resourceProperty != null) {
						Object resourcePropertyValue = resourceProperty.get(0);
						LOG.info("getResourceProperty " + qname + " returning "
								+ resourcePropertyValue);
						if (!(resourcePropertyValue instanceof Node)
								&& !(resourcePropertyValue instanceof JAXBElement<?>)) {
							resourcePropertyValue = JAXBUtils
									.wrap(resourcePropertyValue);
						}
						response.getAny().add(resourcePropertyValue);
					}
				}
			} catch (NoSuchResourceException nsre) {
				e = nsre;
			} catch (InvalidResourceKeyException irke) {
				e = irke;
			} catch (ResourceException re) {
				e = re;
			}
		}

		return response;
	}

	@Override
	public GetServiceSecurityMetadataResponse getServiceSecurityMetadata(
			GetServiceSecurityMetadataRequest parameters) {
		LOG.debug("Executing operation getServiceSecurityMetadata");
		ServiceSecurityMetadata serviceSecurityMetadata = mms
				.getServiceSecurityMetadata();
		GetServiceSecurityMetadataResponse response = new GetServiceSecurityMetadataResponse();
		response.setServiceSecurityMetadata(serviceSecurityMetadata);
		return response;
	}

	@Override
	public QueryResourcePropertiesResponse queryResourceProperties(
			QueryResourceProperties queryResourcePropertiesRequest)
			throws QueryEvaluationErrorFault, ResourceUnknownFault,
			InvalidResourcePropertyQNameFault, InvalidQueryExpressionFault,
			UnknownQueryExpressionDialectFault {
		LOG.debug("Executing operation queryResourceProperties");
		// TODO
		QueryResourcePropertiesResponse response = null;
		response = new QueryResourcePropertiesResponse();
		return response;
	}

	@Override
	public GetResourcePropertyResponse getResourceProperty(
			QName resourcePropertyQName) throws ResourceUnknownFault,
			InvalidResourcePropertyQNameFault {
		LOG.debug("Executing operation getResourceProperty");
		Exception e = null;
		GetResourcePropertyResponse response = null;
		try {
			Resource resource = resourceHome.find(null);
			if (resource instanceof ResourcePropertySet) {
				ResourcePropertySet resourcePropertySet = (ResourcePropertySet) resource;
				ResourceProperty<?> resourceProperty = resourcePropertySet
						.get(resourcePropertyQName);
				if (resourceProperty != null) {
					Object resourcePropertyValue = resourceProperty.get(0);
					LOG.info("getResourceProperty " + resourcePropertyQName
							+ " returning " + resourcePropertyValue);
					if (!(resourcePropertyValue instanceof Node)
							&& !(resourcePropertyValue instanceof JAXBElement<?>)) {
						resourcePropertyValue = JAXBUtils
								.wrap(resourcePropertyValue);
					}
					response = new GetResourcePropertyResponse();
					response.getAny().add(resourcePropertyValue);
				}
			}
		} catch (NoSuchResourceException nsre) {
			e = nsre;
		} catch (InvalidResourceKeyException irke) {
			e = irke;
		} catch (ResourceException re) {
			e = re;
		}
		if ((response == null) || (e != null)) {
			throw new ResourceUnknownFault("No resource for '"
					+ resourcePropertyQName + "'", e);
		}
		return response;
	}

	@Override
	public GenerateDomainModelForClassesWithExcludesResponse generateDomainModelForClassesWithExcludes(
			GenerateDomainModelForClassesWithExcludesRequest parameters)
			throws InvalidUMLProjectIndentifierFaultMessage {
		LOG.debug("Executing operation generateDomainModelForClassesWithExcludes");

		try {
			String[] classNames = new String[parameters.getFullyQualifiedClassNames().size()];
			UMLAssociationExclude[] excludes = new UMLAssociationExclude[parameters.getUmlAssociationExclude()
			   							.getUMLAssociationExclude().size()];
			DomainModel model = mms.generateDomainModelForClassesWithExcludes(parameters
					.getUmlProjectIdentifer().getUMLProjectIdentifer(),
					(String[]) parameters.getFullyQualifiedClassNames().toArray(classNames),
					(UMLAssociationExclude[]) parameters.getUmlAssociationExclude()
							.getUMLAssociationExclude().toArray(excludes));
			GenerateDomainModelForClassesWithExcludesResponse response =  new GenerateDomainModelForClassesWithExcludesResponse();
			response.setDomainModel(model);
			return response;
		} catch (RemoteException e) {
			throw new InvalidUMLProjectIndentifierFaultMessage(e.getMessage(),e);
		} catch (InvalidUMLProjectIndentifier e) {
			throw new InvalidUMLProjectIndentifierFaultMessage(e.getMessage(),e);
		}

	}

	@Override
	public AnnotateServiceMetadataResponse annotateServiceMetadata(
			AnnotateServiceMetadataRequest parameters)
			throws InvalidUMLProjectIndentifierFaultMessage {
		LOG.debug("Executing operation annotateServiceMetadata");

		try {
			ServiceMetadata metadata = mms.annotateServiceMetadata(parameters.getServiceMetadata().getServiceMetadata(),(NamespaceToProjectMapping[])parameters.getNamespaceToProjectMappings().getNamespaceToProjectMapping().toArray());
			AnnotateServiceMetadataResponse response =  new AnnotateServiceMetadataResponse();
			response.setServiceMetadata(metadata);
			return response;
		} catch (RemoteException e) {
			throw new InvalidUMLProjectIndentifierFaultMessage(e.getMessage(),e);
		} catch (InvalidUMLProjectIndentifier e) {
			throw new InvalidUMLProjectIndentifierFaultMessage(e.getMessage(),e);
		}
	}

	@Override
	public GenerateDomainModelForProjectResponse generateDomainModelForProject(
			GenerateDomainModelForProjectRequest parameters)
			throws InvalidUMLProjectIndentifierFaultMessage {
		LOG.debug("Executing operation generateDomainModelForProject");

		try {
			DomainModel model = mms.generateDomainModelForProject(parameters.getUmlProjectIdentifer().getUMLProjectIdentifer());
			GenerateDomainModelForProjectResponse response =  new GenerateDomainModelForProjectResponse();
			response.setDomainModel(model);
			return response;
		} catch (RemoteException e) {
			throw new InvalidUMLProjectIndentifierFaultMessage(e.getMessage(),e);
		} catch (InvalidUMLProjectIndentifier e) {
			throw new InvalidUMLProjectIndentifierFaultMessage(e.getMessage(),e);
		}
	}

	@Override
	public GetModelSourceMetadataResponse getModelSourceMetadata(
			GetModelSourceMetadataRequest parameters) {
		LOG.debug("Executing operation getModelSourceMetadata");

		try {
			ModelSourceMetadata metadata = mms.getModelSourceMetadata();
			GetModelSourceMetadataResponse response =  new GetModelSourceMetadataResponse();
			response.setModelSourceMetadata(metadata);
			return response;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public GenerateDomainModelForClassesResponse generateDomainModelForClasses(
			GenerateDomainModelForClassesRequest parameters)
			throws InvalidUMLProjectIndentifierFaultMessage {
		LOG.debug("Executing operation generateDomainModelForClasses");

		try {
			String[] classNames = new String[parameters.getFullyQualifiedClassNames().size()];
			DomainModel model = mms.generateDomainModelForClasses(parameters
					.getUmlProjectIdentifer().getUMLProjectIdentifer(),
					(String[]) parameters.getFullyQualifiedClassNames().toArray(classNames));
			GenerateDomainModelForClassesResponse response =  new GenerateDomainModelForClassesResponse();
			response.setDomainModel(model);
			return response;
		} catch (RemoteException e) {
			throw new InvalidUMLProjectIndentifierFaultMessage(e.getMessage(),e);
		} catch (InvalidUMLProjectIndentifier e) {
			throw new InvalidUMLProjectIndentifierFaultMessage(e.getMessage(),e);
		}
	}

	@Override
	public GenerateDomainModelForPackagesResponse generateDomainModelForPackages(
			GenerateDomainModelForPackagesRequest parameters)
			throws InvalidUMLProjectIndentifierFaultMessage {
		LOG.debug("Executing operation generateDomainModelForPackages");

		try {
			String[] packageNames = new String[parameters.getPackageNames().size()];
			DomainModel model = mms.generateDomainModelForPackages(parameters
					.getUmlProjectIdentifer().getUMLProjectIdentifer(),
					(String[]) parameters.getPackageNames().toArray(packageNames));
			GenerateDomainModelForPackagesResponse response =  new GenerateDomainModelForPackagesResponse();
			response.setDomainModel(model);
			return response;
		} catch (RemoteException e) {
			throw new InvalidUMLProjectIndentifierFaultMessage(e.getMessage(),e);
		} catch (InvalidUMLProjectIndentifier e) {
			throw new InvalidUMLProjectIndentifierFaultMessage(e.getMessage(),e);
		}
	}
}
