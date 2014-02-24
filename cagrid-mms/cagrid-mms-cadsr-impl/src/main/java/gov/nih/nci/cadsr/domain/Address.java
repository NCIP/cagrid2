package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* The location at which an organization or person can be reached. (from American Heritage Dictionary)	**/
public class Address  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* The first line of the exact address where a mail piece is intended to be delivered, including urban-style street address, rural route, and post office box.	**/
	public String addressLine1;
	/**
	* Retreives the value of addressLine1 attribute
	* @return addressLine1
	**/

	public String getAddressLine1(){
		return addressLine1;
	}

	/**
	* Sets the value of addressLine1 attribue
	**/

	public void setAddressLine1(String addressLine1){
		this.addressLine1 = addressLine1;
	}
	
		/**
	* The second line additional address text to describe where a mail piece is intended to be delivered.	**/
	public String addressLine2;
	/**
	* Retreives the value of addressLine2 attribute
	* @return addressLine2
	**/

	public String getAddressLine2(){
		return addressLine2;
	}

	/**
	* Sets the value of addressLine2 attribue
	**/

	public void setAddressLine2(String addressLine2){
		this.addressLine2 = addressLine2;
	}
	
		/**
	* A large and densely populated urban area; a city specified in an address.	**/
	public String city;
	/**
	* Retreives the value of city attribute
	* @return city
	**/

	public String getCity(){
		return city;
	}

	/**
	* Sets the value of city attribue
	**/

	public void setCity(String city){
		this.city = city;
	}
	
		/**
	* A component of an address that specifies a location by identification of a governmental administrative subdivision.	**/
	public String country;
	/**
	* Retreives the value of country attribute
	* @return country
	**/

	public String getCountry(){
		return country;
	}

	/**
	* Sets the value of country attribue
	**/

	public void setCountry(String country){
		this.country = country;
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
	* A component of an address that specifies a location by identification of a country.	**/
	public String postalCode;
	/**
	* Retreives the value of postalCode attribute
	* @return postalCode
	**/

	public String getPostalCode(){
		return postalCode;
	}

	/**
	* Sets the value of postalCode attribue
	**/

	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}
	
		/**
	* The relative importance of one address to another.	**/
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
	* A component of an address that specifies a location by identification of a principal administrative unit of a country. 	**/
	public String state;
	/**
	* Retreives the value of state attribute
	* @return state
	**/

	public String getState(){
		return state;
	}

	/**
	* Sets the value of state attribue
	**/

	public void setState(String state){
		this.state = state;
	}
	
		/**
	* Something distinguishable as an identifiable class based on common qualities that categorize one kind of address from another.	**/
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
		if(obj instanceof Address) 
		{
			Address c =(Address)obj; 			 
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