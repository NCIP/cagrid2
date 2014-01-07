package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* A concept that can be represented in the form of a data element, described independently of any particular representation. (ISO 11179)	**/
public class DataElementConcept extends AdministeredComponent implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
	/**
	* An associated gov.nih.nci.cadsr.domain.ObjectClass object
	**/
			
	private ObjectClass objectClass;
	/**
	* Retreives the value of objectClass attribue
	* @return objectClass
	**/
	
	public ObjectClass getObjectClass(){
		return objectClass;
	}
	/**
	* Sets the value of objectClass attribue
	**/

	public void setObjectClass(ObjectClass objectClass){
		this.objectClass = objectClass;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.DataElementConceptRelationship object's collection 
	**/
			
	private Collection<DataElementConceptRelationship> childDataElementConceptRelationshipCollection;
	/**
	* Retreives the value of childDataElementConceptRelationshipCollection attribue
	* @return childDataElementConceptRelationshipCollection
	**/

	public Collection<DataElementConceptRelationship> getChildDataElementConceptRelationshipCollection(){
		return childDataElementConceptRelationshipCollection;
	}

	/**
	* Sets the value of childDataElementConceptRelationshipCollection attribue
	**/

	public void setChildDataElementConceptRelationshipCollection(Collection<DataElementConceptRelationship> childDataElementConceptRelationshipCollection){
		this.childDataElementConceptRelationshipCollection = childDataElementConceptRelationshipCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.DataElement object's collection 
	**/
			
	private Collection<DataElement> dataElementCollection;
	/**
	* Retreives the value of dataElementCollection attribue
	* @return dataElementCollection
	**/

	public Collection<DataElement> getDataElementCollection(){
		return dataElementCollection;
	}

	/**
	* Sets the value of dataElementCollection attribue
	**/

	public void setDataElementCollection(Collection<DataElement> dataElementCollection){
		this.dataElementCollection = dataElementCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ConceptualDomain object
	**/
			
	private ConceptualDomain conceptualDomain;
	/**
	* Retreives the value of conceptualDomain attribue
	* @return conceptualDomain
	**/
	
	public ConceptualDomain getConceptualDomain(){
		return conceptualDomain;
	}
	/**
	* Sets the value of conceptualDomain attribue
	**/

	public void setConceptualDomain(ConceptualDomain conceptualDomain){
		this.conceptualDomain = conceptualDomain;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.Property object
	**/
			
	private Property property;
	/**
	* Retreives the value of property attribue
	* @return property
	**/
	
	public Property getProperty(){
		return property;
	}
	/**
	* Sets the value of property attribue
	**/

	public void setProperty(Property property){
		this.property = property;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.DataElementConceptRelationship object's collection 
	**/
			
	private Collection<DataElementConceptRelationship> parentDataElementConceptRelationshipCollection;
	/**
	* Retreives the value of parentDataElementConceptRelationshipCollection attribue
	* @return parentDataElementConceptRelationshipCollection
	**/

	public Collection<DataElementConceptRelationship> getParentDataElementConceptRelationshipCollection(){
		return parentDataElementConceptRelationshipCollection;
	}

	/**
	* Sets the value of parentDataElementConceptRelationshipCollection attribue
	**/

	public void setParentDataElementConceptRelationshipCollection(Collection<DataElementConceptRelationship> parentDataElementConceptRelationshipCollection){
		this.parentDataElementConceptRelationshipCollection = parentDataElementConceptRelationshipCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof DataElementConcept) 
		{
			DataElementConcept c =(DataElementConcept)obj; 			 
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