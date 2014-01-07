package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* The communication of a contact	**/
public class ContactCommunication  implements Serializable
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
	* The date the Administered Item was created. (ISO 11179).	**/
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
	* Date on which the record was modified; last date the object was modified.	**/
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
	* Username of the person who modified the record.	**/
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
	* The order of preference of the communication type	**/
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
	* The type of communication e.g fax, email, phone	**/
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
	* the value of the communication	**/
	public String value;
	/**
	* Retreives the value of value attribute
	* @return value
	**/

	public String getValue(){
		return value;
	}

	/**
	* Sets the value of value attribue
	**/

	public void setValue(String value){
		this.value = value;
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
		if(obj instanceof ContactCommunication) 
		{
			ContactCommunication c =(ContactCommunication)obj; 			 
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