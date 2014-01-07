package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* An entity in the registry that may be associated with an administered item.	**/
public class Organization  implements Serializable
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
	* An identifier assigned to a Registration Authority.	**/
	private String rai;
	/**
	* Retreives the value of rai attribute
	* @return rai
	**/

	public String getRai(){
		return rai;
	}

	/**
	* Sets the value of rai attribue
	**/

	public void setRai(String rai){
		this.rai = rai;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.Person object's collection 
	**/
			
	private Collection<Person> person;
	/**
	* Retreives the value of person attribue
	* @return person
	**/

	public Collection<Person> getPerson(){
		return person;
	}

	/**
	* Sets the value of person attribue
	**/

	public void setPerson(Collection<Person> person){
		this.person = person;
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
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Organization) 
		{
			Organization c =(Organization)obj; 			 
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