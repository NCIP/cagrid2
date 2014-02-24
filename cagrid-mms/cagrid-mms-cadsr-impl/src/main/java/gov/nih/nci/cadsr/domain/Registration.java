package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* 	**/
public class Registration  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* The particular day, month and year this item became allowed.   (ISO 11179)	**/
	private java.util.Date beginDate;
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
	* Username of the person who created the record	**/
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
	* An identifier assigned to a data element within an RA	**/
	private String dataIdentifier;
	/**
	* Retreives the value of dataIdentifier attribute
	* @return dataIdentifier
	**/

	public String getDataIdentifier(){
		return dataIdentifier;
	}

	/**
	* Sets the value of dataIdentifier attribue
	**/

	public void setDataIdentifier(String dataIdentifier){
		this.dataIdentifier = dataIdentifier;
	}
	
		/**
	* The date the Registration Record was created. (ISO 11179)	**/
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
	* Date on which the record was modified; last date the object was modified	**/
	private java.util.Date dateModified;
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
	* The day, month and year this item becomes no longer allowed. (ISO 11179)	**/
	private java.util.Date endDate;
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
	* International Registration Data Identifier. An internationally unique identifier for an administered component( ISO/IEC 11179).
	**/
	private String IRDI;
	/**
	* Retreives the value of IRDI attribute
	* @return IRDI
	**/

	public String getIRDI(){
		return IRDI;
	}

	/**
	* Sets the value of IRDI attribue
	**/

	public void setIRDI(String IRDI){
		this.IRDI = IRDI;
	}
	
		/**
	* The description of what has changed in the Registration	**/
	private String lastChange;
	/**
	* Retreives the value of lastChange attribute
	* @return lastChange
	**/

	public String getLastChange(){
		return lastChange;
	}

	/**
	* Sets the value of lastChange attribue
	**/

	public void setLastChange(String lastChange){
		this.lastChange = lastChange;
	}
	
		/**
	* Username of the person who modified the record	**/
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
	* The source (document, project, discipline or model) for the Administered Item. (ISO 11179)	**/
	private String origin;
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
	* A designation of the status in the registration life-cycle of an Administered Item. (ISO 11179)	**/
	private String registrationStatus;
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
	private String unresolvedIssue;
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
	* An identifier assigned to a Version under which an administered component registration is submitted or updated
	**/
	private String versionIdentifier;
	/**
	* Retreives the value of versionIdentifier attribute
	* @return versionIdentifier
	**/

	public String getVersionIdentifier(){
		return versionIdentifier;
	}

	/**
	* Sets the value of versionIdentifier attribue
	**/

	public void setVersionIdentifier(String versionIdentifier){
		this.versionIdentifier = versionIdentifier;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.AdministeredComponentContact object
	**/
			
	private AdministeredComponentContact submitter;
	/**
	* Retreives the value of submitter attribue
	* @return submitter
	**/
	
	public AdministeredComponentContact getSubmitter(){
		return submitter;
	}
	/**
	* Sets the value of submitter attribue
	**/

	public void setSubmitter(AdministeredComponentContact submitter){
		this.submitter = submitter;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.AdministeredComponent object
	**/
			
	private AdministeredComponent administeredComponent;
	/**
	* Retreives the value of administeredComponent attribue
	* @return administeredComponent
	**/
	
	public AdministeredComponent getAdministeredComponent(){
		return administeredComponent;
	}
	/**
	* Sets the value of administeredComponent attribue
	**/

	public void setAdministeredComponent(AdministeredComponent administeredComponent){
		this.administeredComponent = administeredComponent;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.ReferenceDocument object's collection 
	**/
			
	private Collection<ReferenceDocument> referenceDocumentsCollection;
	/**
	* Retreives the value of referenceDocumentsCollection attribue
	* @return referenceDocumentsCollection
	**/

	public Collection<ReferenceDocument> getReferenceDocumentsCollection(){
		return referenceDocumentsCollection;
	}

	/**
	* Sets the value of referenceDocumentsCollection attribue
	**/

	public void setReferenceDocumentsCollection(Collection<ReferenceDocument> referenceDocumentsCollection){
		this.referenceDocumentsCollection = referenceDocumentsCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.Organization object
	**/
			
	private Organization registrationAuthority;
	/**
	* Retreives the value of registrationAuthority attribue
	* @return registrationAuthority
	**/
	
	public Organization getRegistrationAuthority(){
		return registrationAuthority;
	}
	/**
	* Sets the value of registrationAuthority attribue
	**/

	public void setRegistrationAuthority(Organization registrationAuthority){
		this.registrationAuthority = registrationAuthority;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.AdministeredComponentContact object
	**/
			
	private AdministeredComponentContact registrar;
	/**
	* Retreives the value of registrar attribue
	* @return registrar
	**/
	
	public AdministeredComponentContact getRegistrar(){
		return registrar;
	}
	/**
	* Sets the value of registrar attribue
	**/

	public void setRegistrar(AdministeredComponentContact registrar){
		this.registrar = registrar;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Registration) 
		{
			Registration c =(Registration)obj; 			 
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