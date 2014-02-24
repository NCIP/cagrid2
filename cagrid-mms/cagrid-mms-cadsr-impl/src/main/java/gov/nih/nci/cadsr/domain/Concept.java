package gov.nih.nci.cadsr.domain;

import java.util.Collection;import gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata;
import java.io.Serializable;
	/**
	* The information pertaining to a concept. A unit of knowledge created by a unique combination of characteristics. (ISO 1087)	**/
public class Concept extends AdministeredComponent implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* The person or authoritative body who provided the definition.	**/
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
	* The name of the EVS concept code that was the source for the concept identifier.  e.g. NCI_CONCEPT_CODE, UMLS_CUI, NCI_Meta_CUI, GO_CODE, MedDRA_CODE etc.	**/
	public String evsSource;
	/**
	* Retreives the value of evsSource attribute
	* @return evsSource
	**/

	public String getEvsSource(){
		return evsSource;
	}

	/**
	* Sets the value of evsSource attribue
	**/

	public void setEvsSource(String evsSource){
		this.evsSource = evsSource;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.ComponentConcept object's collection 
	**/
			
	private Collection<ComponentConcept> componentConceptCollection;
	/**
	* Retreives the value of componentConceptCollection attribue
	* @return componentConceptCollection
	**/

	public Collection<ComponentConcept> getComponentConceptCollection(){
		return componentConceptCollection;
	}

	/**
	* Sets the value of componentConceptCollection attribue
	**/

	public void setComponentConceptCollection(Collection<ComponentConcept> componentConceptCollection){
		this.componentConceptCollection = componentConceptCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue object's collection 
	**/
			
	private Collection<ValueDomainPermissibleValue> valueDomainPermissibleValueCollection;
	/**
	* Retreives the value of valueDomainPermissibleValueCollection attribue
	* @return valueDomainPermissibleValueCollection
	**/

	public Collection<ValueDomainPermissibleValue> getValueDomainPermissibleValueCollection(){
		return valueDomainPermissibleValueCollection;
	}

	/**
	* Sets the value of valueDomainPermissibleValueCollection attribue
	**/

	public void setValueDomainPermissibleValueCollection(Collection<ValueDomainPermissibleValue> valueDomainPermissibleValueCollection){
		this.valueDomainPermissibleValueCollection = valueDomainPermissibleValueCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Concept) 
		{
			Concept c =(Concept)obj; 			 
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