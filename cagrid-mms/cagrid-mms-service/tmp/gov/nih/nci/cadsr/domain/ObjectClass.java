package gov.nih.nci.cadsr.domain;

import gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata;import java.util.Collection;
import java.io.Serializable;
	/**
	* A set of ideas, abstractions, or things in the real world that can be identified with explicit boundaries and meaning and whose properties and behavior follow the same rules. (ISO 11179)	**/
public class ObjectClass extends AdministeredComponent implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* The person or authoritative body who provided the definition	**/
	public String definitionSource;
	/**
	* Retreives the value of definitionSource attribute
	* @return definitionSource
	**/

	public String getDefinitionSource(){
		return definitionSource;
	}

	/**
	* Sets the value of definitionSource attribue
	**/

	public void setDefinitionSource(String definitionSource){
		this.definitionSource = definitionSource;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.DataElementConcept object's collection 
	**/
			
	private Collection<DataElementConcept> dataElementConceptCollection;
	/**
	* Retreives the value of dataElementConceptCollection attribue
	* @return dataElementConceptCollection
	**/

	public Collection<DataElementConcept> getDataElementConceptCollection(){
		return dataElementConceptCollection;
	}

	/**
	* Sets the value of dataElementConceptCollection attribue
	**/

	public void setDataElementConceptCollection(Collection<DataElementConcept> dataElementConceptCollection){
		this.dataElementConceptCollection = dataElementConceptCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ConceptDerivationRule object
	**/
			
	private ConceptDerivationRule conceptDerivationRule;
	/**
	* Retreives the value of conceptDerivationRule attribue
	* @return conceptDerivationRule
	**/
	
	public ConceptDerivationRule getConceptDerivationRule(){
		return conceptDerivationRule;
	}
	/**
	* Sets the value of conceptDerivationRule attribue
	**/

	public void setConceptDerivationRule(ConceptDerivationRule conceptDerivationRule){
		this.conceptDerivationRule = conceptDerivationRule;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata object's collection 
	**/
			
	private Collection<UMLClassMetadata> UMLClassMetadata;
	/**
	* Retreives the value of UMLClassMetadata attribue
	* @return UMLClassMetadata
	**/

	public Collection<UMLClassMetadata> getUMLClassMetadata(){
		return UMLClassMetadata;
	}

	/**
	* Sets the value of UMLClassMetadata attribue
	**/

	public void setUMLClassMetadata(Collection<UMLClassMetadata> UMLClassMetadata){
		this.UMLClassMetadata = UMLClassMetadata;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ObjectClassRelationship object's collection 
	**/
			
	private Collection<ObjectClassRelationship> targetObjectClassRelationshipCollection;
	/**
	* Retreives the value of targetObjectClassRelationshipCollection attribue
	* @return targetObjectClassRelationshipCollection
	**/

	public Collection<ObjectClassRelationship> getTargetObjectClassRelationshipCollection(){
		return targetObjectClassRelationshipCollection;
	}

	/**
	* Sets the value of targetObjectClassRelationshipCollection attribue
	**/

	public void setTargetObjectClassRelationshipCollection(Collection<ObjectClassRelationship> targetObjectClassRelationshipCollection){
		this.targetObjectClassRelationshipCollection = targetObjectClassRelationshipCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ObjectClassRelationship object's collection 
	**/
			
	private Collection<ObjectClassRelationship> sourcObjectClassRelationshipCollection;
	/**
	* Retreives the value of sourcObjectClassRelationshipCollection attribue
	* @return sourcObjectClassRelationshipCollection
	**/

	public Collection<ObjectClassRelationship> getSourcObjectClassRelationshipCollection(){
		return sourcObjectClassRelationshipCollection;
	}

	/**
	* Sets the value of sourcObjectClassRelationshipCollection attribue
	**/

	public void setSourcObjectClassRelationshipCollection(Collection<ObjectClassRelationship> sourcObjectClassRelationshipCollection){
		this.sourcObjectClassRelationshipCollection = sourcObjectClassRelationshipCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ObjectClass) 
		{
			ObjectClass c =(ObjectClass)obj; 			 
			if(getId() != null && getId().equals(c.getId()))
				return true;
		}
		return false;
	}
		
	/**
	* Returns hash code for the primary key of the object
	**/
	public int hashCode()
	{
		if(getId() != null)
			return getId().hashCode();
		return 0;
	}
	
}