package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* A characteristic common to all members of an Object Class.  It may be any feature that humans naturally use to distinguish one individual object from another.  It is conceptual and thus has no particular representation.	**/
public class Property extends AdministeredComponent implements Serializable
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
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Property) 
		{
			Property c =(Property)obj; 			 
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