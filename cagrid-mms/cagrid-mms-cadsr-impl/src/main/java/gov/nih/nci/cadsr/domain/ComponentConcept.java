package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* The concept component(s) used for a concept derivation. A unit of knowledge created by a unique combination of characteristics. (ISO 1087)	**/
public class ComponentConcept  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
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
	* A flage indicating if the concept is the primary concept	**/
	public String primaryFlag;
	/**
	* Retreives the value of primaryFlag attribute
	* @return primaryFlag
	**/

	public String getPrimaryFlag(){
		return primaryFlag;
	}

	/**
	* Sets the value of primaryFlag attribue
	**/

	public void setPrimaryFlag(String primaryFlag){
		this.primaryFlag = primaryFlag;
	}
	
		/**
	* the value for the concept	**/
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
	* An associated gov.nih.nci.cadsr.domain.ComponentLevel object
	**/
			
	private ComponentLevel componentlevel;
	/**
	* Retreives the value of componentlevel attribue
	* @return componentlevel
	**/
	
	public ComponentLevel getComponentlevel(){
		return componentlevel;
	}
	/**
	* Sets the value of componentlevel attribue
	**/

	public void setComponentlevel(ComponentLevel componentlevel){
		this.componentlevel = componentlevel;
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
	* An associated gov.nih.nci.cadsr.domain.ConceptDerivationRule object
	**/
			
	private ConceptDerivationRule derivationRule;
	/**
	* Retreives the value of derivationRule attribue
	* @return derivationRule
	**/
	
	public ConceptDerivationRule getDerivationRule(){
		return derivationRule;
	}
	/**
	* Sets the value of derivationRule attribue
	**/

	public void setDerivationRule(ConceptDerivationRule derivationRule){
		this.derivationRule = derivationRule;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ComponentConcept) 
		{
			ComponentConcept c =(ComponentConcept)obj; 			 
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