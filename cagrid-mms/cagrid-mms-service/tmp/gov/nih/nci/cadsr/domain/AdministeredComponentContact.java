package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* Information pertaining to the person to contact regarding this registry item.	**/
public class AdministeredComponentContact  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* The role of the contact	**/
	public String contactRole;
	/**
	* Retreives the value of contactRole attribute
	* @return contactRole
	**/

	public String getContactRole(){
		return contactRole;
	}

	/**
	* Sets the value of contactRole attribue
	**/

	public void setContactRole(String contactRole){
		this.contactRole = contactRole;
	}
	
		/**
	* Username of the person who created the record.	**/
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
	* The relative rank of this contact to another.	**/
	public Integer rank;
	/**
	* Retreives the value of rank attribute
	* @return rank
	**/

	public Integer getRank(){
		return rank;
	}

	/**
	* Sets the value of rank attribue
	**/

	public void setRank(Integer rank){
		this.rank = rank;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.Person object
	**/
			
	private Person person;
	/**
	* Retreives the value of person attribue
	* @return person
	**/
	
	public Person getPerson(){
		return person;
	}
	/**
	* Sets the value of person attribue
	**/

	public void setPerson(Person person){
		this.person = person;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.Registration object
	**/
			
	private Registration submissions;
	/**
	* Retreives the value of submissions attribue
	* @return submissions
	**/
	
	public Registration getSubmissions(){
		return submissions;
	}
	/**
	* Sets the value of submissions attribue
	**/

	public void setSubmissions(Registration submissions){
		this.submissions = submissions;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.Organization object
	**/
			
	private Organization organization;
	/**
	* Retreives the value of organization attribue
	* @return organization
	**/
	
	public Organization getOrganization(){
		return organization;
	}
	/**
	* Sets the value of organization attribue
	**/

	public void setOrganization(Organization organization){
		this.organization = organization;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.Registration object
	**/
			
	private Registration registrations;
	/**
	* Retreives the value of registrations attribue
	* @return registrations
	**/
	
	public Registration getRegistrations(){
		return registrations;
	}
	/**
	* Sets the value of registrations attribue
	**/

	public void setRegistrations(Registration registrations){
		this.registrations = registrations;
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
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof AdministeredComponentContact) 
		{
			AdministeredComponentContact c =(AdministeredComponentContact)obj; 			 
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