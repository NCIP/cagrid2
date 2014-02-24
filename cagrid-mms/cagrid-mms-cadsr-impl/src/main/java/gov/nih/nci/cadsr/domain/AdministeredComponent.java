package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* A registry item for which administrative information is recorded in an Administration Record. (ISO 11179)	**/
public class AdministeredComponent  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* The particular day, month and year this item became allowed.   (ISO 11179)	**/
	public java.util.Date beginDate;
	/**
	* Retreives the value of beginDate attribute
	* @return beginDate
	**/

	public java.util.Date getBeginDate(){
		return beginDate;
	}

	/**
	* Sets the value of beginDate attribue
	**/

	public void setBeginDate(java.util.Date beginDate){
		this.beginDate = beginDate;
	}
	
		/**
	* The description of what has changed in the Administered Item since the prior version of the Administered
Item. (ISO 11179)	**/
	public String changeNote;
	/**
	* Retreives the value of changeNote attribute
	* @return changeNote
	**/

	public String getChangeNote(){
		return changeNote;
	}

	/**
	* Sets the value of changeNote attribue
	**/

	public void setChangeNote(String changeNote){
		this.changeNote = changeNote;
	}
	
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
	* The date the Administered Item was created. (ISO 11179)	**/
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
	* Date on which the record was modified; last date the object was modified	**/
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
	* An indicator that characterizes the item as eliminated from the registry, thought the physical record still exists.	**/
	public String deletedIndicator;
	/**
	* Retreives the value of deletedIndicator attribute
	* @return deletedIndicator
	**/

	public String getDeletedIndicator(){
		return deletedIndicator;
	}

	/**
	* Sets the value of deletedIndicator attribue
	**/

	public void setDeletedIndicator(String deletedIndicator){
		this.deletedIndicator = deletedIndicator;
	}
	
		/**
	* The day, month and year this item becomes no longer allowed. (ISO 11179)	**/
	public java.util.Date endDate;
	/**
	* Retreives the value of endDate attribute
	* @return endDate
	**/

	public java.util.Date getEndDate(){
		return endDate;
	}

	/**
	* Sets the value of endDate attribue
	**/

	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
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
	* The present form of an Administered Item. 	**/
	public String latestVersionIndicator;
	/**
	* Retreives the value of latestVersionIndicator attribute
	* @return latestVersionIndicator
	**/

	public String getLatestVersionIndicator(){
		return latestVersionIndicator;
	}

	/**
	* Sets the value of latestVersionIndicator attribue
	**/

	public void setLatestVersionIndicator(String latestVersionIndicator){
		this.latestVersionIndicator = latestVersionIndicator;
	}
	
		/**
	* A 255 character name for an item in the registry.  	**/
	public String longName;
	/**
	* Retreives the value of longName attribute
	* @return longName
	**/

	public String getLongName(){
		return longName;
	}

	/**
	* Sets the value of longName attribue
	**/

	public void setLongName(String longName){
		this.longName = longName;
	}
	
		/**
	* Username of the person who modified the record	**/
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
	* The source (document, project, discipline or model) for the Administered Item. (ISO 11179)	**/
	public String origin;
	/**
	* Retreives the value of origin attribute
	* @return origin
	**/

	public String getOrigin(){
		return origin;
	}

	/**
	* Sets the value of origin attribue
	**/

	public void setOrigin(String origin){
		this.origin = origin;
	}
	
		/**
	* The text of the most desirable meaning for the Administered Item.  (Definition Text ISO 111797)	**/
	public String preferredDefinition;
	/**
	* Retreives the value of preferredDefinition attribute
	* @return preferredDefinition
	**/

	public String getPreferredDefinition(){
		return preferredDefinition;
	}

	/**
	* Sets the value of preferredDefinition attribue
	**/

	public void setPreferredDefinition(String preferredDefinition){
		this.preferredDefinition = preferredDefinition;
	}
	
		/**
	* A 30 character name by which an Administered Item is designated within a specific Context.	**/
	public String preferredName;
	/**
	* Retreives the value of preferredName attribute
	* @return preferredName
	**/

	public String getPreferredName(){
		return preferredName;
	}

	/**
	* Sets the value of preferredName attribue
	**/

	public void setPreferredName(String preferredName){
		this.preferredName = preferredName;
	}
	
		/**
	* The unique identifier for an Administered Item within a Registration Authority.  (ISO 11179)	**/
	public Long publicID;
	/**
	* Retreives the value of publicID attribute
	* @return publicID
	**/

	public Long getPublicID(){
		return publicID;
	}

	/**
	* Sets the value of publicID attribue
	**/

	public void setPublicID(Long publicID){
		this.publicID = publicID;
	}
	
		/**
	* A designation of the status in the registration life-cycle of an Administered Item. (ISO 11179)	**/
	public String registrationStatus;
	/**
	* Retreives the value of registrationStatus attribute
	* @return registrationStatus
	**/

	public String getRegistrationStatus(){
		return registrationStatus;
	}

	/**
	* Sets the value of registrationStatus attribue
	**/

	public void setRegistrationStatus(String registrationStatus){
		this.registrationStatus = registrationStatus;
	}
	
		/**
	* Any problem that remains unresolved regarding proper documentation of the Administered Item. (ISO 11179)	**/
	public String unresolvedIssue;
	/**
	* Retreives the value of unresolvedIssue attribute
	* @return unresolvedIssue
	**/

	public String getUnresolvedIssue(){
		return unresolvedIssue;
	}

	/**
	* Sets the value of unresolvedIssue attribue
	**/

	public void setUnresolvedIssue(String unresolvedIssue){
		this.unresolvedIssue = unresolvedIssue;
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
	* A text description of a designation of the status in the administrative life-cycle of a Context Owner for handling new administered items.	**/
	public String workflowStatusDescription;
	/**
	* Retreives the value of workflowStatusDescription attribute
	* @return workflowStatusDescription
	**/

	public String getWorkflowStatusDescription(){
		return workflowStatusDescription;
	}

	/**
	* Sets the value of workflowStatusDescription attribue
	**/

	public void setWorkflowStatusDescription(String workflowStatusDescription){
		this.workflowStatusDescription = workflowStatusDescription;
	}
	
		/**
	* A name of a designation of the status in the administrative life-cycle of a Context Owner for handling new administered items.	**/
	public String workflowStatusName;
	/**
	* Retreives the value of workflowStatusName attribute
	* @return workflowStatusName
	**/

	public String getWorkflowStatusName(){
		return workflowStatusName;
	}

	/**
	* Sets the value of workflowStatusName attribue
	**/

	public void setWorkflowStatusName(String workflowStatusName){
		this.workflowStatusName = workflowStatusName;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.AdministeredComponentContact object's collection 
	**/
			
	private Collection<AdministeredComponentContact> administeredComponentContactCollection;
	/**
	* Retreives the value of administeredComponentContactCollection attribue
	* @return administeredComponentContactCollection
	**/

	public Collection<AdministeredComponentContact> getAdministeredComponentContactCollection(){
		return administeredComponentContactCollection;
	}

	/**
	* Sets the value of administeredComponentContactCollection attribue
	**/

	public void setAdministeredComponentContactCollection(Collection<AdministeredComponentContact> administeredComponentContactCollection){
		this.administeredComponentContactCollection = administeredComponentContactCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem object's collection 
	**/
			
	private Collection<AdministeredComponentClassSchemeItem> administeredComponentClassSchemeItemCollection;
	/**
	* Retreives the value of administeredComponentClassSchemeItemCollection attribue
	* @return administeredComponentClassSchemeItemCollection
	**/

	public Collection<AdministeredComponentClassSchemeItem> getAdministeredComponentClassSchemeItemCollection(){
		return administeredComponentClassSchemeItemCollection;
	}

	/**
	* Sets the value of administeredComponentClassSchemeItemCollection attribue
	**/

	public void setAdministeredComponentClassSchemeItemCollection(Collection<AdministeredComponentClassSchemeItem> administeredComponentClassSchemeItemCollection){
		this.administeredComponentClassSchemeItemCollection = administeredComponentClassSchemeItemCollection;
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
	* An associated gov.nih.nci.cadsr.domain.Registration object's collection 
	**/
			
	private Collection<Registration> registrations;
	/**
	* Retreives the value of registrations attribue
	* @return registrations
	**/

	public Collection<Registration> getRegistrations(){
		return registrations;
	}

	/**
	* Sets the value of registrations attribue
	**/

	public void setRegistrations(Collection<Registration> registrations){
		this.registrations = registrations;
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
		if(obj instanceof AdministeredComponent) 
		{
			AdministeredComponent c =(AdministeredComponent)obj; 			 
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