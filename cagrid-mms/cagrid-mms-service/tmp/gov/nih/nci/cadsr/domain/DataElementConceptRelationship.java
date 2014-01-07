package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* The information about the  affiliation between two occurrences of Data Element Concepts.	**/
public class DataElementConceptRelationship  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* Username of the person who created the record	**/
	public String createdBy;
	/**
	* Retreives the value of createdBy attribute
	* @return createdBy
	**/

	public String getCreatedBy(){
		return createdBy;
	}

	/**
	* Sets the value of createdBy attribue
	**/

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}
	
		/**
	* The date the record was created.	**/
	public java.util.Date dateCreated;
	/**
	* Retreives the value of dateCreated attribute
	* @return dateCreated
	**/

	public java.util.Date getDateCreated(){
		return dateCreated;
	}

	/**
	* Sets the value of dateCreated attribue
	**/

	public void setDateCreated(java.util.Date dateCreated){
		this.dateCreated = dateCreated;
	}
	
		/**
	* Date on which the record was modified.	**/
	public java.util.Date dateModified;
	/**
	* Retreives the value of dateModified attribute
	* @return dateModified
	**/

	public java.util.Date getDateModified(){
		return dateModified;
	}

	/**
	* Sets the value of dateModified attribue
	**/

	public void setDateModified(java.util.Date dateModified){
		this.dateModified = dateModified;
	}
	
		/**
	* A text description of the meaning of the item.	**/
	public String description;
	/**
	* Retreives the value of description attribute
	* @return description
	**/

	public String getDescription(){
		return description;
	}

	/**
	* Sets the value of description attribue
	**/

	public void setDescription(String description){
		this.description = description;
	}
	
		/**
	* The 36 character caDSR database identifier.	**/
	public String id;
	/**
	* Retreives the value of id attribute
	* @return id
	**/

	public String getId(){
		return id;
	}

	/**
	* Sets the value of id attribue
	**/

	public void setId(String id){
		this.id = id;
	}
	
		/**
	* The username of the person who last changed the item.	**/
	public String modifiedBy;
	/**
	* Retreives the value of modifiedBy attribute
	* @return modifiedBy
	**/

	public String getModifiedBy(){
		return modifiedBy;
	}

	/**
	* Sets the value of modifiedBy attribue
	**/

	public void setModifiedBy(String modifiedBy){
		this.modifiedBy = modifiedBy;
	}
	
		/**
	* The name by which the item is known.	**/
	public String name;
	/**
	* Retreives the value of name attribute
	* @return name
	**/

	public String getName(){
		return name;
	}

	/**
	* Sets the value of name attribue
	**/

	public void setName(String name){
		this.name = name;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.DataElementConcept object
	**/
			
	private DataElementConcept childDataElementConcept;
	/**
	* Retreives the value of childDataElementConcept attribue
	* @return childDataElementConcept
	**/
	
	public DataElementConcept getChildDataElementConcept(){
		return childDataElementConcept;
	}
	/**
	* Sets the value of childDataElementConcept attribue
	**/

	public void setChildDataElementConcept(DataElementConcept childDataElementConcept){
		this.childDataElementConcept = childDataElementConcept;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.DataElementConcept object
	**/
			
	private DataElementConcept parentDataElementConcept;
	/**
	* Retreives the value of parentDataElementConcept attribue
	* @return parentDataElementConcept
	**/
	
	public DataElementConcept getParentDataElementConcept(){
		return parentDataElementConcept;
	}
	/**
	* Sets the value of parentDataElementConcept attribue
	**/

	public void setParentDataElementConcept(DataElementConcept parentDataElementConcept){
		this.parentDataElementConcept = parentDataElementConcept;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof DataElementConceptRelationship) 
		{
			DataElementConceptRelationship c =(DataElementConceptRelationship)obj; 			 
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