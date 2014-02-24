package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* A universe of discourse in which a name or definition is used. (ISO 11179)	**/
public class Context  implements Serializable
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
	* The name of the language in which the item is represented. 	**/
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
	* The unique version identifier of the Administered Item. (ISO 11179)	**/
	public Float version;
	/**
	* Retreives the value of version attribute
	* @return version
	**/

	public Float getVersion(){
		return version;
	}

	/**
	* Sets the value of version attribue
	**/

	public void setVersion(Float version){
		this.version = version;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.Designation object's collection 
	**/
			
	private Collection<Designation> designationCollection;
	/**
	* Retreives the value of designationCollection attribue
	* @return designationCollection
	**/

	public Collection<Designation> getDesignationCollection(){
		return designationCollection;
	}

	/**
	* Sets the value of designationCollection attribue
	**/

	public void setDesignationCollection(Collection<Designation> designationCollection){
		this.designationCollection = designationCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ReferenceDocument object's collection 
	**/
			
	private Collection<ReferenceDocument> referenceDocumentCollection;
	/**
	* Retreives the value of referenceDocumentCollection attribue
	* @return referenceDocumentCollection
	**/

	public Collection<ReferenceDocument> getReferenceDocumentCollection(){
		return referenceDocumentCollection;
	}

	/**
	* Sets the value of referenceDocumentCollection attribue
	**/

	public void setReferenceDocumentCollection(Collection<ReferenceDocument> referenceDocumentCollection){
		this.referenceDocumentCollection = referenceDocumentCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.AdministeredComponent object's collection 
	**/
			
	private Collection<AdministeredComponent> administeredComponentCollection;
	/**
	* Retreives the value of administeredComponentCollection attribue
	* @return administeredComponentCollection
	**/

	public Collection<AdministeredComponent> getAdministeredComponentCollection(){
		return administeredComponentCollection;
	}

	/**
	* Sets the value of administeredComponentCollection attribue
	**/

	public void setAdministeredComponentCollection(Collection<AdministeredComponent> administeredComponentCollection){
		this.administeredComponentCollection = administeredComponentCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.Definition object's collection 
	**/
			
	private Collection<Definition> definitionCollection;
	/**
	* Retreives the value of definitionCollection attribue
	* @return definitionCollection
	**/

	public Collection<Definition> getDefinitionCollection(){
		return definitionCollection;
	}

	/**
	* Sets the value of definitionCollection attribue
	**/

	public void setDefinitionCollection(Collection<Definition> definitionCollection){
		this.definitionCollection = definitionCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Context) 
		{
			Context c =(Context)obj; 			 
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