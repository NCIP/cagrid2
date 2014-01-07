package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* The set of all possible Valid Value meanings of a Data Element Concept expressed without representation.	**/
public class ConceptualDomain extends AdministeredComponent implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* An expression of measurement pertaining to the values in the value domain, without units. (ISO 11179)	**/
	public String dimensionality;
	/**
	* Retreives the value of dimensionality attribute
	* @return dimensionality
	**/

	public String getDimensionality(){
		return dimensionality;
	}

	/**
	* Sets the value of dimensionality attribue
	**/

	public void setDimensionality(String dimensionality){
		this.dimensionality = dimensionality;
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
	* An associated gov.nih.nci.cadsr.domain.ValueMeaning object's collection 
	**/
			
	private Collection<ValueMeaning> valueMeaningCollection;
	/**
	* Retreives the value of valueMeaningCollection attribue
	* @return valueMeaningCollection
	**/

	public Collection<ValueMeaning> getValueMeaningCollection(){
		return valueMeaningCollection;
	}

	/**
	* Sets the value of valueMeaningCollection attribue
	**/

	public void setValueMeaningCollection(Collection<ValueMeaning> valueMeaningCollection){
		this.valueMeaningCollection = valueMeaningCollection;
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
		if(obj instanceof ConceptualDomain) 
		{
			ConceptualDomain c =(ConceptualDomain)obj; 			 
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