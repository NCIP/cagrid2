package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* This captures the many-to-many relationship between value domain and permissible values and allows to associate a value domain to a permissible value.	**/
public class ValueDomainPermissibleValue  implements Serializable
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
	* An associated gov.nih.nci.cadsr.domain.ValidValue object's collection 
	**/
			
	private Collection<ValidValue> validValueCollection;
	/**
	* Retreives the value of validValueCollection attribue
	* @return validValueCollection
	**/

	public Collection<ValidValue> getValidValueCollection(){
		return validValueCollection;
	}

	/**
	* Sets the value of validValueCollection attribue
	**/

	public void setValidValueCollection(Collection<ValidValue> validValueCollection){
		this.validValueCollection = validValueCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.PermissibleValue object
	**/
			
	private PermissibleValue permissibleValue;
	/**
	* Retreives the value of permissibleValue attribue
	* @return permissibleValue
	**/
	
	public PermissibleValue getPermissibleValue(){
		return permissibleValue;
	}
	/**
	* Sets the value of permissibleValue attribue
	**/

	public void setPermissibleValue(PermissibleValue permissibleValue){
		this.permissibleValue = permissibleValue;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.Concept object
	**/
			
	private Concept concept;
	/**
	* Retreives the value of concept attribue
	* @return concept
	**/
	
	public Concept getConcept(){
		return concept;
	}
	/**
	* Sets the value of concept attribue
	**/

	public void setConcept(Concept concept){
		this.concept = concept;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.EnumeratedValueDomain object
	**/
			
	private EnumeratedValueDomain enumeratedValueDomain;
	/**
	* Retreives the value of enumeratedValueDomain attribue
	* @return enumeratedValueDomain
	**/
	
	public EnumeratedValueDomain getEnumeratedValueDomain(){
		return enumeratedValueDomain;
	}
	/**
	* Sets the value of enumeratedValueDomain attribue
	**/

	public void setEnumeratedValueDomain(EnumeratedValueDomain enumeratedValueDomain){
		this.enumeratedValueDomain = enumeratedValueDomain;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ValueDomainPermissibleValue) 
		{
			ValueDomainPermissibleValue c =(ValueDomainPermissibleValue)obj; 			 
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