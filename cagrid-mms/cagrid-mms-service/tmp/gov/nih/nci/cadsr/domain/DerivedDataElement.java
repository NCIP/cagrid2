package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* Information about a Data Element which is derived, the rule controlling its derivation, and the Data
Element(s) from which it is derived. (ISO 11179)	**/
public class DerivedDataElement  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* The symbol used to represent a linking of separate elements.	**/
	public String concatenationCharacter;
	/**
	* Retreives the value of concatenationCharacter attribute
	* @return concatenationCharacter
	**/

	public String getConcatenationCharacter(){
		return concatenationCharacter;
	}

	/**
	* Sets the value of concatenationCharacter attribue
	**/

	public void setConcatenationCharacter(String concatenationCharacter){
		this.concatenationCharacter = concatenationCharacter;
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
	* Scientific or clinical procedures and methods.	**/
	public String methods;
	/**
	* Retreives the value of methods attribute
	* @return methods
	**/

	public String getMethods(){
		return methods;
	}

	/**
	* Sets the value of methods attribue
	**/

	public void setMethods(String methods){
		this.methods = methods;
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
	* The logical, mathematical, and/or other operations specifying derivation. The rule may range from a simple operation such as subtraction to a very complex set of derivations.	**/
	public String rule;
	/**
	* Retreives the value of rule attribute
	* @return rule
	**/

	public String getRule(){
		return rule;
	}

	/**
	* Sets the value of rule attribue
	**/

	public void setRule(String rule){
		this.rule = rule;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.DerivationType object
	**/
			
	private DerivationType derivationType;
	/**
	* Retreives the value of derivationType attribue
	* @return derivationType
	**/
	
	public DerivationType getDerivationType(){
		return derivationType;
	}
	/**
	* Sets the value of derivationType attribue
	**/

	public void setDerivationType(DerivationType derivationType){
		this.derivationType = derivationType;
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
	* An associated gov.nih.nci.cadsr.domain.DataElementDerivation object's collection 
	**/
			
	private Collection<DataElementDerivation> dataElementDerivationCollection;
	/**
	* Retreives the value of dataElementDerivationCollection attribue
	* @return dataElementDerivationCollection
	**/

	public Collection<DataElementDerivation> getDataElementDerivationCollection(){
		return dataElementDerivationCollection;
	}

	/**
	* Sets the value of dataElementDerivationCollection attribue
	**/

	public void setDataElementDerivationCollection(Collection<DataElementDerivation> dataElementDerivationCollection){
		this.dataElementDerivationCollection = dataElementDerivationCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof DerivedDataElement) 
		{
			DerivedDataElement c =(DerivedDataElement)obj; 			 
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