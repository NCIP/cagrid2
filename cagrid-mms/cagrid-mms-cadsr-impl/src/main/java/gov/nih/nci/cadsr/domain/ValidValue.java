package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* The allowable values for a given question on a Case Report Form.	**/
public class ValidValue extends FormElement implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* the description associated with the valid value	**/
	public String description;
	/**
	* Retreives the value of description attribute
	* @return description
	**/

	public String getDescription(){
		return description;
	}

	/**
	* Sets the value of description attribue
	**/

	public void setDescription(String description){
		this.description = description;
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
	* the meaning associated with the valid value	**/
	private String meaningText;
	/**
	* Retreives the value of meaningText attribute
	* @return meaningText
	**/

	public String getMeaningText(){
		return meaningText;
	}

	/**
	* Sets the value of meaningText attribue
	**/

	public void setMeaningText(String meaningText){
		this.meaningText = meaningText;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.QuestionConditionComponents object's collection 
	**/
			
	private Collection<QuestionConditionComponents> conditionComponent;
	/**
	* Retreives the value of conditionComponent attribue
	* @return conditionComponent
	**/

	public Collection<QuestionConditionComponents> getConditionComponent(){
		return conditionComponent;
	}

	/**
	* Sets the value of conditionComponent attribue
	**/

	public void setConditionComponent(Collection<QuestionConditionComponents> conditionComponent){
		this.conditionComponent = conditionComponent;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.Question object
	**/
			
	private Question question;
	/**
	* Retreives the value of question attribue
	* @return question
	**/
	
	public Question getQuestion(){
		return question;
	}
	/**
	* Sets the value of question attribue
	**/

	public void setQuestion(Question question){
		this.question = question;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue object
	**/
			
	private ValueDomainPermissibleValue valueDomainPermissibleValue;
	/**
	* Retreives the value of valueDomainPermissibleValue attribue
	* @return valueDomainPermissibleValue
	**/
	
	public ValueDomainPermissibleValue getValueDomainPermissibleValue(){
		return valueDomainPermissibleValue;
	}
	/**
	* Sets the value of valueDomainPermissibleValue attribue
	**/

	public void setValueDomainPermissibleValue(ValueDomainPermissibleValue valueDomainPermissibleValue){
		this.valueDomainPermissibleValue = valueDomainPermissibleValue;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ValidValue) 
		{
			ValidValue c =(ValidValue)obj; 			 
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