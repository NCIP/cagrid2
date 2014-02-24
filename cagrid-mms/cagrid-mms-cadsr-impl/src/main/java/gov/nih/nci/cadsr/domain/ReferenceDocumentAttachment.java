package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* An attachment associated with a reference document	**/
public class ReferenceDocumentAttachment  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* Username of the person who created the record.	**/
	private String createdBy;
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
	private java.util.Date dateCreated;
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
	* Date on which the record was modified; last date the object was modified.	**/
	private String dateModified;
	/**
	* Retreives the value of dateModified attribute
	* @return dateModified
	**/

	public String getDateModified(){
		return dateModified;
	}

	/**
	* Sets the value of dateModified attribue
	**/

	public void setDateModified(String dateModified){
		this.dateModified = dateModified;
	}
	
		/**
	* 	**/
	private String id;
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
	* mime type of the attachment	**/
	private String mimeType;
	/**
	* Retreives the value of mimeType attribute
	* @return mimeType
	**/

	public String getMimeType(){
		return mimeType;
	}

	/**
	* Sets the value of mimeType attribue
	**/

	public void setMimeType(String mimeType){
		this.mimeType = mimeType;
	}
	
		/**
	* Username of the person who modified the record.	**/
	private String modifiedBy;
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
	* name of the attachment	**/
	private String name;
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
	* size of the attachment in bytes	**/
	private Integer size;
	/**
	* Retreives the value of size attribute
	* @return size
	**/

	public Integer getSize(){
		return size;
	}

	/**
	* Sets the value of size attribue
	**/

	public void setSize(Integer size){
		this.size = size;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.ReferenceDocument object
	**/
			
	private ReferenceDocument referenceDocument;
	/**
	* Retreives the value of referenceDocument attribue
	* @return referenceDocument
	**/
	
	public ReferenceDocument getReferenceDocument(){
		return referenceDocument;
	}
	/**
	* Sets the value of referenceDocument attribue
	**/

	public void setReferenceDocument(ReferenceDocument referenceDocument){
		this.referenceDocument = referenceDocument;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ReferenceDocumentAttachment) 
		{
			ReferenceDocumentAttachment c =(ReferenceDocumentAttachment)obj; 			 
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