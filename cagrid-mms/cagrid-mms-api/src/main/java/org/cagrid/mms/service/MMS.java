package org.cagrid.mms.service;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

import org.cagrid.mms.model.ModelSourceMetadata;
import org.cagrid.mms.model.UMLAssociationExclude;
import org.cagrid.mms.model.UMLProjectIdentifer;
import org.cagrid.mms.service.InvalidUMLProjectIndentifier;


public interface MMS {

    public ModelSourceMetadata getModelSourceMetadata() throws MMSGeneralException;


    public DomainModel generateDomainModelForProject(UMLProjectIdentifer umlProjectIdentifer)
        throws MMSGeneralException, InvalidUMLProjectIndentifier;


    public DomainModel generateDomainModelForPackages(UMLProjectIdentifer umlProjectIdentifer,
        Collection<String> packageNames) throws MMSGeneralException, InvalidUMLProjectIndentifier;


    public DomainModel generateDomainModelForClasses(UMLProjectIdentifer umlProjectIdentifer,
        Collection<String> fullyQualifiedClassNames) throws MMSGeneralException, InvalidUMLProjectIndentifier;


    public DomainModel generateDomainModelForClassesWithExcludes(UMLProjectIdentifer umlProjectIdentifer,
        Collection<String> fullyQualifiiedClassNames, Collection<UMLAssociationExclude> umlAssociationExclude)
        throws MMSGeneralException, InvalidUMLProjectIndentifier;


    public ServiceMetadata annotateServiceMetadata(ServiceMetadata serviceMetadata,
        Map<URI, UMLProjectIdentifer> namespaceToProjectMappings) throws MMSGeneralException, InvalidUMLProjectIndentifier;

}