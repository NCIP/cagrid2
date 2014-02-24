package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* The relationship among items in a Classification Scheme. (ISO 11179)	**/
public class ClassificationSchemeItemRelationship  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* Username of the person who created the item.	**/
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
	* The username of the person who last changed the Item.	**/
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
	* The words by which the item is known.	**/
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
	* An associated gov.nih.nci.cadsr.domain.ClassificationSchemeItem object
	**/
			
	private ClassificationSchemeItem childClassificationSchemeItem;
	/**
	* Retreives the value of childClassificationSchemeItem attribue
	* @return childClassificationSchemeItem
	**/
	
	public ClassificationSchemeItem getChildClassificationSchemeItem(){
		return childClassificationSchemeItem;
	}
	/**
	* Sets the value of childClassificationSchemeItem attribue
	**/

	public void setChildClassificationSchemeItem(ClassificationSchemeItem childClassificationSchemeItem){
		this.childClassificationSchemeItem = childClassificationSchemeItem;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.ClassificationSchemeItem object
	**/
			
	private ClassificationSchemeItem parentClassificationSchemeItem;
	/**
	* Retreives the value of parentClassificationSchemeItem attribue
	* @return parentClassificationSchemeItem
	**/
	
	public ClassificationSchemeItem getParentClassificationSchemeItem(){
		return parentClassificationSchemeItem;
	}
	/**
	* Sets the value of parentClassificationSchemeItem attribue
	**/

	public void setParentClassificationSchemeItem(ClassificationSchemeItem parentClassificationSchemeItem){
		this.parentClassificationSchemeItem = parentClassificationSchemeItem;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ClassificationSchemeItemRelationship) 
		{
			ClassificationSchemeItemRelationship c =(ClassificationSchemeItemRelationship)obj; 			 
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