package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* Mechanism by which the functional and/or presentational category of an item may be conveyed to a user. Component of a Data Element Name that describes how data are represented (i.e. the combination of a Value Domain, data type, and if necessary a unit of 	**/
public class Representation extends AdministeredComponent implements Serializable
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
	* An associated gov.nih.nci.cadsr.domain.ValueDomain object's collection 
	**/
			
	private Collection<ValueDomain> valueDomainCollection;
	/**
	* Retreives the value of valueDomainCollection attribue
	* @return valueDomainCollection
	**/

	public Collection<ValueDomain> getValueDomainCollection(){
		return valueDomainCollection;
	}

	/**
	* Sets the value of valueDomainCollection attribue
	**/

	public void setValueDomainCollection(Collection<ValueDomain> valueDomainCollection){
		this.valueDomainCollection = valueDomainCollection;
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
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Representation) 
		{
			Representation c =(Representation)obj; 			 
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