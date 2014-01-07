package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* The data element component(s) used for a derived data element.	**/
public class DataElementDerivation  implements Serializable
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
	public Integer displayOrder;
	/**
	* Retreives the value of displayOrder attribute
	* @return displayOrder
	**/

	public Integer getDisplayOrder(){
		return displayOrder;
	}

	/**
	* Sets the value of displayOrder attribue
	**/

	public void setDisplayOrder(Integer displayOrder){
		this.displayOrder = displayOrder;
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
	* String of Characters to be concatenated on the left hand side of the Data Element	**/
	public String leadingCharacters;
	/**
	* Retreives the value of leadingCharacters attribute
	* @return leadingCharacters
	**/

	public String getLeadingCharacters(){
		return leadingCharacters;
	}

	/**
	* Sets the value of leadingCharacters attribue
	**/

	public void setLeadingCharacters(String leadingCharacters){
		this.leadingCharacters = leadingCharacters;
	}
	
		/**
	* The username of the person who last changed the Item.	**/
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
	* String of Characters to be concatenated on the right hand side of the Data Element	**/
	public String trailingCharacters;
	/**
	* Retreives the value of trailingCharacters attribute
	* @return trailingCharacters
	**/

	public String getTrailingCharacters(){
		return trailingCharacters;
	}

	/**
	* Sets the value of trailingCharacters attribue
	**/

	public void setTrailingCharacters(String trailingCharacters){
		this.trailingCharacters = trailingCharacters;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.DataElement object
	**/
			
	private DataElement dataElement;
	/**
	* Retreives the value of dataElement attribue
	* @return dataElement
	**/
	
	public DataElement getDataElement(){
		return dataElement;
	}
	/**
	* Sets the value of dataElement attribue
	**/

	public void setDataElement(DataElement dataElement){
		this.dataElement = dataElement;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.Function object
	**/
			
	private Function leftOperand;
	/**
	* Retreives the value of leftOperand attribue
	* @return leftOperand
	**/
	
	public Function getLeftOperand(){
		return leftOperand;
	}
	/**
	* Sets the value of leftOperand attribue
	**/

	public void setLeftOperand(Function leftOperand){
		this.leftOperand = leftOperand;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.DerivedDataElement object
	**/
			
	private DerivedDataElement derivedDataElement;
	/**
	* Retreives the value of derivedDataElement attribue
	* @return derivedDataElement
	**/
	
	public DerivedDataElement getDerivedDataElement(){
		return derivedDataElement;
	}
	/**
	* Sets the value of derivedDataElement attribue
	**/

	public void setDerivedDataElement(DerivedDataElement derivedDataElement){
		this.derivedDataElement = derivedDataElement;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof DataElementDerivation) 
		{
			DataElementDerivation c =(DataElementDerivation)obj; 			 
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