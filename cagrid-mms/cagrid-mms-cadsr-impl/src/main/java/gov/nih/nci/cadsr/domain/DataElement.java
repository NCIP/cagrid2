package gov.nih.nci.cadsr.domain;

import gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata;import java.util.Collection;
import java.io.Serializable;
	/**
	* A unit of data for which the definition, identification, representation and permissible values are specified by means of a set of attributes.	**/
public class DataElement extends AdministeredComponent implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
	/**
	* An associated gov.nih.nci.cadsr.domain.DerivedDataElement object
	**/
			
	private DerivedDataElement derivedDataElement;
	/**
	* Retreives the value of derivedDataElement attribue
	* @return derivedDataElement
	**/
	
	public DerivedDataElement getDerivedDataElement(){
		return derivedDataElement;
	}
	/**
	* Sets the value of derivedDataElement attribue
	**/

	public void setDerivedDataElement(DerivedDataElement derivedDataElement){
		this.derivedDataElement = derivedDataElement;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.DataElementDerivation object's collection 
	**/
			
	private Collection<DataElementDerivation> dataElementDerivationCollection;
	/**
	* Retreives the value of dataElementDerivationCollection attribue
	* @return dataElementDerivationCollection
	**/

	public Collection<DataElementDerivation> getDataElementDerivationCollection(){
		return dataElementDerivationCollection;
	}

	/**
	* Sets the value of dataElementDerivationCollection attribue
	**/

	public void setDataElementDerivationCollection(Collection<DataElementDerivation> dataElementDerivationCollection){
		this.dataElementDerivationCollection = dataElementDerivationCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.Question object's collection 
	**/
			
	private Collection<Question> questionCollection;
	/**
	* Retreives the value of questionCollection attribue
	* @return questionCollection
	**/

	public Collection<Question> getQuestionCollection(){
		return questionCollection;
	}

	/**
	* Sets the value of questionCollection attribue
	**/

	public void setQuestionCollection(Collection<Question> questionCollection){
		this.questionCollection = questionCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ValueDomain object
	**/
			
	private ValueDomain valueDomain;
	/**
	* Retreives the value of valueDomain attribue
	* @return valueDomain
	**/
	
	public ValueDomain getValueDomain(){
		return valueDomain;
	}
	/**
	* Sets the value of valueDomain attribue
	**/

	public void setValueDomain(ValueDomain valueDomain){
		this.valueDomain = valueDomain;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.DataElementRelationship object's collection 
	**/
			
	private Collection<DataElementRelationship> parentDataElementRelationshipsCollection;
	/**
	* Retreives the value of parentDataElementRelationshipsCollection attribue
	* @return parentDataElementRelationshipsCollection
	**/

	public Collection<DataElementRelationship> getParentDataElementRelationshipsCollection(){
		return parentDataElementRelationshipsCollection;
	}

	/**
	* Sets the value of parentDataElementRelationshipsCollection attribue
	**/

	public void setParentDataElementRelationshipsCollection(Collection<DataElementRelationship> parentDataElementRelationshipsCollection){
		this.parentDataElementRelationshipsCollection = parentDataElementRelationshipsCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.DataElementRelationship object's collection 
	**/
			
	private Collection<DataElementRelationship> childDataElementRelationshipsCollection;
	/**
	* Retreives the value of childDataElementRelationshipsCollection attribue
	* @return childDataElementRelationshipsCollection
	**/

	public Collection<DataElementRelationship> getChildDataElementRelationshipsCollection(){
		return childDataElementRelationshipsCollection;
	}

	/**
	* Sets the value of childDataElementRelationshipsCollection attribue
	**/

	public void setChildDataElementRelationshipsCollection(Collection<DataElementRelationship> childDataElementRelationshipsCollection){
		this.childDataElementRelationshipsCollection = childDataElementRelationshipsCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.DataElementConcept object
	**/
			
	private DataElementConcept dataElementConcept;
	/**
	* Retreives the value of dataElementConcept attribue
	* @return dataElementConcept
	**/
	
	public DataElementConcept getDataElementConcept(){
		return dataElementConcept;
	}
	/**
	* Sets the value of dataElementConcept attribue
	**/

	public void setDataElementConcept(DataElementConcept dataElementConcept){
		this.dataElementConcept = dataElementConcept;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof DataElement) 
		{
			DataElement c =(DataElement)obj; 			 
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