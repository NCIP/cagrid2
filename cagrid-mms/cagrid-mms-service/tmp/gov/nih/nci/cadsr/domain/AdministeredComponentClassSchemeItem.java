package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* An association class that serves to allow many to many relationships between Administered Component and ClassSchemeClassSchemeItem, providing uniqueness to the CS/CSI pairing to an AC.	**/
public class AdministeredComponentClassSchemeItem  implements Serializable
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
	* An associated gov.nih.nci.cadsr.domain.ObjectClassRelationship object's collection 
	**/
			
	private Collection<ObjectClassRelationship> targetObjectClass;
	/**
	* Retreives the value of targetObjectClass attribue
	* @return targetObjectClass
	**/

	public Collection<ObjectClassRelationship> getTargetObjectClass(){
		return targetObjectClass;
	}

	/**
	* Sets the value of targetObjectClass attribue
	**/

	public void setTargetObjectClass(Collection<ObjectClassRelationship> targetObjectClass){
		this.targetObjectClass = targetObjectClass;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem object
	**/
			
	private ClassSchemeClassSchemeItem classSchemeClassSchemeItem;
	/**
	* Retreives the value of classSchemeClassSchemeItem attribue
	* @return classSchemeClassSchemeItem
	**/
	
	public ClassSchemeClassSchemeItem getClassSchemeClassSchemeItem(){
		return classSchemeClassSchemeItem;
	}
	/**
	* Sets the value of classSchemeClassSchemeItem attribue
	**/

	public void setClassSchemeClassSchemeItem(ClassSchemeClassSchemeItem classSchemeClassSchemeItem){
		this.classSchemeClassSchemeItem = classSchemeClassSchemeItem;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.ObjectClassRelationship object's collection 
	**/
			
	private Collection<ObjectClassRelationship> sourceObjectClass;
	/**
	* Retreives the value of sourceObjectClass attribue
	* @return sourceObjectClass
	**/

	public Collection<ObjectClassRelationship> getSourceObjectClass(){
		return sourceObjectClass;
	}

	/**
	* Sets the value of sourceObjectClass attribue
	**/

	public void setSourceObjectClass(Collection<ObjectClassRelationship> sourceObjectClass){
		this.sourceObjectClass = sourceObjectClass;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof AdministeredComponentClassSchemeItem) 
		{
			AdministeredComponentClassSchemeItem c =(AdministeredComponentClassSchemeItem)obj; 			 
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