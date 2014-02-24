package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* The exact names, codes and text that can be stored in a data field in an information management system.  ISO DEF: An expression of a value meaning in a specific value domain.	**/
public class PermissibleValue  implements Serializable
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
	* The upper limit of the values in the value domain	**/
	public Long highValueNumber;
	/**
	* Retreives the value of highValueNumber attribute
	* @return highValueNumber
	**/

	public Long getHighValueNumber(){
		return highValueNumber;
	}

	/**
	* Sets the value of highValueNumber attribue
	**/

	public void setHighValueNumber(Long highValueNumber){
		this.highValueNumber = highValueNumber;
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
	* The lower limit of the values in the value domain	**/
	public Long lowValueNumber;
	/**
	* Retreives the value of lowValueNumber attribute
	* @return lowValueNumber
	**/

	public Long getLowValueNumber(){
		return lowValueNumber;
	}

	/**
	* Sets the value of lowValueNumber attribue
	**/

	public void setLowValueNumber(Long lowValueNumber){
		this.lowValueNumber = lowValueNumber;
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
	* A representation of a Value Meaning in a specific Value Domain ¿ the actual Value. (ISO 11179)	**/
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
	* An associated gov.nih.nci.cadsr.domain.ValueMeaning object
	**/
			
	private ValueMeaning valueMeaning;
	/**
	* Retreives the value of valueMeaning attribue
	* @return valueMeaning
	**/
	
	public ValueMeaning getValueMeaning(){
		return valueMeaning;
	}
	/**
	* Sets the value of valueMeaning attribue
	**/

	public void setValueMeaning(ValueMeaning valueMeaning){
		this.valueMeaning = valueMeaning;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue object's collection 
	**/
			
	private Collection<ValueDomainPermissibleValue> valueDomainPermissibleValueCollection;
	/**
	* Retreives the value of valueDomainPermissibleValueCollection attribue
	* @return valueDomainPermissibleValueCollection
	**/

	public Collection<ValueDomainPermissibleValue> getValueDomainPermissibleValueCollection(){
		return valueDomainPermissibleValueCollection;
	}

	/**
	* Sets the value of valueDomainPermissibleValueCollection attribue
	**/

	public void setValueDomainPermissibleValueCollection(Collection<ValueDomainPermissibleValue> valueDomainPermissibleValueCollection){
		this.valueDomainPermissibleValueCollection = valueDomainPermissibleValueCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof PermissibleValue) 
		{
			PermissibleValue c =(PermissibleValue)obj; 			 
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