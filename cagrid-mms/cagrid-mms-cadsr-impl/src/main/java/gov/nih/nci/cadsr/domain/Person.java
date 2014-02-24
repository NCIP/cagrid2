package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* Information about a contact person	**/
public class Person  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
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
	* A word or group of words indicating a person's first (personal or given) name; the name that precedes the surname. Synonym = Given Name. 	**/
	public String firstName;
	/**
	* Retreives the value of firstName attribute
	* @return firstName
	**/

	public String getFirstName(){
		return firstName;
	}

	/**
	* Sets the value of firstName attribue
	**/

	public void setFirstName(String firstName){
		this.firstName = firstName;
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
	* A means of identifying an individual by using a word or group of words indicating a person's last (family) name. Synonym = Last Name, Surname. 	**/
	public String lastName;
	/**
	* Retreives the value of lastName attribute
	* @return lastName
	**/

	public String getLastName(){
		return lastName;
	}

	/**
	* Sets the value of lastName attribue
	**/

	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
		/**
	* The first letter of the middle name of a person.	**/
	public String middleInitial;
	/**
	* Retreives the value of middleInitial attribute
	* @return middleInitial
	**/

	public String getMiddleInitial(){
		return middleInitial;
	}

	/**
	* Sets the value of middleInitial attribue
	**/

	public void setMiddleInitial(String middleInitial){
		this.middleInitial = middleInitial;
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
	* Job title of the person.	**/
	public String position;
	/**
	* Retreives the value of position attribute
	* @return position
	**/

	public String getPosition(){
		return position;
	}

	/**
	* Sets the value of position attribue
	**/

	public void setPosition(String position){
		this.position = position;
	}
	
		/**
	* The relative order.	**/
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
	* An associated gov.nih.nci.cadsr.domain.Address object's collection 
	**/
			
	private Collection<Address> addressCollection;
	/**
	* Retreives the value of addressCollection attribue
	* @return addressCollection
	**/

	public Collection<Address> getAddressCollection(){
		return addressCollection;
	}

	/**
	* Sets the value of addressCollection attribue
	**/

	public void setAddressCollection(Collection<Address> addressCollection){
		this.addressCollection = addressCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.AdministeredComponentContact object's collection 
	**/
			
	private Collection<AdministeredComponentContact> administeredComponentContact;
	/**
	* Retreives the value of administeredComponentContact attribue
	* @return administeredComponentContact
	**/

	public Collection<AdministeredComponentContact> getAdministeredComponentContact(){
		return administeredComponentContact;
	}

	/**
	* Sets the value of administeredComponentContact attribue
	**/

	public void setAdministeredComponentContact(Collection<AdministeredComponentContact> administeredComponentContact){
		this.administeredComponentContact = administeredComponentContact;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ContactCommunication object's collection 
	**/
			
	private Collection<ContactCommunication> contactCommunication;
	/**
	* Retreives the value of contactCommunication attribue
	* @return contactCommunication
	**/

	public Collection<ContactCommunication> getContactCommunication(){
		return contactCommunication;
	}

	/**
	* Sets the value of contactCommunication attribue
	**/

	public void setContactCommunication(Collection<ContactCommunication> contactCommunication){
		this.contactCommunication = contactCommunication;
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
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Person) 
		{
			Person c =(Person)obj; 			 
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