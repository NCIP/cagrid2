package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* A name by which an Administered Component is known in a specific Context. Also a placeholder to track the usage of Administered Components by different Contexts.	**/
public class Designation  implements Serializable
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
	* The name of the language in which the item is represented.	**/
	public String languageName;
	/**
	* Retreives the value of languageName attribute
	* @return languageName
	**/

	public String getLanguageName(){
		return languageName;
	}

	/**
	* Sets the value of languageName attribue
	**/

	public void setLanguageName(String languageName){
		this.languageName = languageName;
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
	* The words by which an item is known.	**/
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
	* A particular kind of designation.	**/
	public String type;
	/**
	* Retreives the value of type attribute
	* @return type
	**/

	public String getType(){
		return type;
	}

	/**
	* Sets the value of type attribue
	**/

	public void setType(String type){
		this.type = type;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.DesignationClassSchemeItem object's collection 
	**/
			
	private Collection<DesignationClassSchemeItem> designationClassSchemeItemCollection;
	/**
	* Retreives the value of designationClassSchemeItemCollection attribue
	* @return designationClassSchemeItemCollection
	**/

	public Collection<DesignationClassSchemeItem> getDesignationClassSchemeItemCollection(){
		return designationClassSchemeItemCollection;
	}

	/**
	* Sets the value of designationClassSchemeItemCollection attribue
	**/

	public void setDesignationClassSchemeItemCollection(Collection<DesignationClassSchemeItem> designationClassSchemeItemCollection){
		this.designationClassSchemeItemCollection = designationClassSchemeItemCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.Context object
	**/
			
	private Context context;
	/**
	* Retreives the value of context attribue
	* @return context
	**/
	
	public Context getContext(){
		return context;
	}
	/**
	* Sets the value of context attribue
	**/

	public void setContext(Context context){
		this.context = context;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Designation) 
		{
			Designation c =(Designation)obj; 			 
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