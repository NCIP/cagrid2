package org.cagrid.mms.soapclient;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;

import java.net.URI;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.cagrid.mms.model.ModelSourceMetadata;
import org.cagrid.mms.model.NamespaceToProjectMapping;
import org.cagrid.mms.model.UMLAssociationExclude;
import org.cagrid.mms.model.UMLProjectIdentifer;
import org.cagrid.mms.service.InvalidUMLProjectIndentifier;
import org.cagrid.mms.service.MMS;
import org.cagrid.mms.service.MMSGeneralException;
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

public class MMSSoapClient implements MMS {

	MetadataModelServicePortType port;
	
	public MMSSoapClient(MetadataModelServicePortType port){
		this.port = port;
	}


	@Override
	public DomainModel generateDomainModelForProject(
			UMLProjectIdentifer umlProjectIdentifer) throws InvalidUMLProjectIndentifier {
		GenerateDomainModelForProjectRequest request = new GenerateDomainModelForProjectRequest();
		GenerateDomainModelForProjectRequest.UmlProjectIdentifer pid = new GenerateDomainModelForProjectRequest.UmlProjectIdentifer();
		pid.setUMLProjectIdentifer(umlProjectIdentifer);
		request.setUmlProjectIdentifer(pid);
		GenerateDomainModelForProjectResponse response = null;
		try {
			response = port.generateDomainModelForProject(request);
		} catch (InvalidUMLProjectIndentifierFaultMessage e) {
			throw new InvalidUMLProjectIndentifier();
		}
		return response.getDomainModel();
	}


	@Override
	public ModelSourceMetadata getModelSourceMetadata() {
		GetModelSourceMetadataRequest request = new GetModelSourceMetadataRequest();
		GetModelSourceMetadataResponse response = port.getModelSourceMetadata(request);
		return response.getModelSourceMetadata();

	}

	@Override
	public DomainModel generateDomainModelForPackages(
			UMLProjectIdentifer umlProjectIdentifer,
			Collection<String> packageNames) throws MMSGeneralException,
			InvalidUMLProjectIndentifier {
		GenerateDomainModelForPackagesRequest request = new GenerateDomainModelForPackagesRequest();
		GenerateDomainModelForPackagesRequest.UmlProjectIdentifer pid = new GenerateDomainModelForPackagesRequest.UmlProjectIdentifer();
		pid.setUMLProjectIdentifer(umlProjectIdentifer);
		request.setUmlProjectIdentifer(pid);
		request.getPackageNames().addAll(packageNames);
		GenerateDomainModelForPackagesResponse response = null;
		try {
			response = port.generateDomainModelForPackages(request);
		} catch (InvalidUMLProjectIndentifierFaultMessage e) {
			throw new InvalidUMLProjectIndentifier();
		}
		return response.getDomainModel();
	}

	@Override
	public DomainModel generateDomainModelForClasses(
			UMLProjectIdentifer umlProjectIdentifer,
			Collection<String> fullyQualifiedClassNames)
			throws MMSGeneralException, InvalidUMLProjectIndentifier {
		GenerateDomainModelForClassesRequest request = new GenerateDomainModelForClassesRequest();
		GenerateDomainModelForClassesRequest.UmlProjectIdentifer pid = new GenerateDomainModelForClassesRequest.UmlProjectIdentifer();
		pid.setUMLProjectIdentifer(umlProjectIdentifer);
		request.setUmlProjectIdentifer(pid);
		request.getFullyQualifiedClassNames().addAll(fullyQualifiedClassNames);
		GenerateDomainModelForClassesResponse response = null;
		try {
			response = port.generateDomainModelForClasses(request);
		} catch (InvalidUMLProjectIndentifierFaultMessage e) {
			throw new InvalidUMLProjectIndentifier();
		}
		return response.getDomainModel();
	}

	@Override
	public DomainModel generateDomainModelForClassesWithExcludes(
			UMLProjectIdentifer umlProjectIdentifer,
			Collection<String> fullyQualifiiedClassNames,
			Collection<UMLAssociationExclude> umlAssociationExclude)
			throws MMSGeneralException, InvalidUMLProjectIndentifier {
		GenerateDomainModelForClassesWithExcludesRequest request = new GenerateDomainModelForClassesWithExcludesRequest();
		GenerateDomainModelForClassesWithExcludesRequest.UmlProjectIdentifer pid = new GenerateDomainModelForClassesWithExcludesRequest.UmlProjectIdentifer();
		pid.setUMLProjectIdentifer(umlProjectIdentifer);
		request.setUmlProjectIdentifer(pid);
		request.getFullyQualifiedClassNames().addAll(fullyQualifiiedClassNames);
		GenerateDomainModelForClassesWithExcludesRequest.UmlAssociationExclude excludes = new GenerateDomainModelForClassesWithExcludesRequest.UmlAssociationExclude();
		excludes.getUMLAssociationExclude().addAll(umlAssociationExclude);
		request.setUmlAssociationExclude(excludes);
		GenerateDomainModelForClassesWithExcludesResponse response = null;
		try {
			response = port.generateDomainModelForClassesWithExcludes(request);
		} catch (InvalidUMLProjectIndentifierFaultMessage e) {
			throw new InvalidUMLProjectIndentifier();
		}
		return response.getDomainModel();
	}

	@Override
	public ServiceMetadata annotateServiceMetadata(
			ServiceMetadata serviceMetadata,
			Map<URI, UMLProjectIdentifer> namespaceToProjectMappings)
			throws MMSGeneralException, InvalidUMLProjectIndentifier {
		AnnotateServiceMetadataRequest request = new AnnotateServiceMetadataRequest();
		AnnotateServiceMetadataRequest.NamespaceToProjectMappings mappings = new AnnotateServiceMetadataRequest.NamespaceToProjectMappings();
		
		//TODO:
		//mappings.getNamespaceToProjectMapping().addAll(namespaceToProjectMappings);
		request.setNamespaceToProjectMappings(mappings);
		AnnotateServiceMetadataRequest.ServiceMetadata metadata = new AnnotateServiceMetadataRequest.ServiceMetadata();
		metadata.setServiceMetadata(serviceMetadata);
		request.setServiceMetadata(metadata);
		AnnotateServiceMetadataResponse response = null;
		try {
			response = port.annotateServiceMetadata(request);
		} catch (InvalidUMLProjectIndentifierFaultMessage e) {
			throw new InvalidUMLProjectIndentifier();
		}
		return response.getServiceMetadata();
	}

}
