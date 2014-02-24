package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* A document that provides pertinent details for consultation about a subject.  (ISO 11179)	**/
public class ReferenceDocument  implements Serializable
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
	* The logical arrangement of items for viewing in a user interface.	**/
	public Long displayOrder;
	/**
	* Retreives the value of displayOrder attribute
	* @return displayOrder
	**/

	public Long getDisplayOrder(){
		return displayOrder;
	}

	/**
	* Sets the value of displayOrder attribue
	**/

	public void setDisplayOrder(Long displayOrder){
		this.displayOrder = displayOrder;
	}
	
		/**
	* The description of the reference document. 	**/
	public String doctext;
	/**
	* Retreives the value of doctext attribute
	* @return doctext
	**/

	public String getDoctext(){
		return doctext;
	}

	/**
	* Sets the value of doctext attribue
	**/

	public void setDoctext(String doctext){
		this.doctext = doctext;
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
	* The characters used to identify the organization owning the reference document.	**/
	public String organizationId;
	/**
	* Retreives the value of organizationId attribute
	* @return organizationId
	**/

	public String getOrganizationId(){
		return organizationId;
	}

	/**
	* Sets the value of organizationId attribue
	**/

	public void setOrganizationId(String organizationId){
		this.organizationId = organizationId;
	}
	
		/**
	* The description associated with a particular reference document type. 	**/
	public String rdtlName;
	/**
	* Retreives the value of rdtlName attribute
	* @return rdtlName
	**/

	public String getRdtlName(){
		return rdtlName;
	}

	/**
	* Sets the value of rdtlName attribue
	**/

	public void setRdtlName(String rdtlName){
		this.rdtlName = rdtlName;
	}
	
		/**
	* The particular type of reference document.	**/
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
	* A Uniform Resource Locator is an Internet address which tells a browser where to find an Internet resource. (from http://www.computeruser.com)	**/
	public String URL;
	/**
	* Retreives the value of URL attribute
	* @return URL
	**/

	public String getURL(){
		return URL;
	}

	/**
	* Sets the value of URL attribue
	**/

	public void setURL(String URL){
		this.URL = URL;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.ReferenceDocumentAttachment object's collection 
	**/
			
	private Collection<ReferenceDocumentAttachment> attachments;
	/**
	* Retreives the value of attachments attribue
	* @return attachments
	**/

	public Collection<ReferenceDocumentAttachment> getAttachments(){
		return attachments;
	}

	/**
	* Sets the value of attachments attribue
	**/

	public void setAttachments(Collection<ReferenceDocumentAttachment> attachments){
		this.attachments = attachments;
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
	* An associated gov.nih.nci.cadsr.domain.Registration object
	**/
			
	private Registration registration;
	/**
	* Retreives the value of registration attribue
	* @return registration
	**/
	
	public Registration getRegistration(){
		return registration;
	}
	/**
	* Sets the value of registration attribue
	**/

	public void setRegistration(Registration registration){
		this.registration = registration;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ReferenceDocument) 
		{
			ReferenceDocument c =(ReferenceDocument)obj; 			 
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