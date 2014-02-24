package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* Information about the default valid values everytime the question repeats on a form	**/
public class QuestionRepetition extends FormElement implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* default value for the question when it is non enumerated	**/
	public String defaultValue;
	/**
	* Retreives the value of defaultValue attribute
	* @return defaultValue
	**/

	public String getDefaultValue(){
		return defaultValue;
	}

	/**
	* Sets the value of defaultValue attribue
	**/

	public void setDefaultValue(String defaultValue){
		this.defaultValue = defaultValue;
	}
	
		/**
	* Is the default valid value editable	**/
	public String isEditable;
	/**
	* Retreives the value of isEditable attribute
	* @return isEditable
	**/

	public String getIsEditable(){
		return isEditable;
	}

	/**
	* Sets the value of isEditable attribue
	**/

	public void setIsEditable(String isEditable){
		this.isEditable = isEditable;
	}
	
		/**
	* the number of repeat for the question	**/
	public Integer repeatSequenceNumber;
	/**
	* Retreives the value of repeatSequenceNumber attribute
	* @return repeatSequenceNumber
	**/

	public Integer getRepeatSequenceNumber(){
		return repeatSequenceNumber;
	}

	/**
	* Sets the value of repeatSequenceNumber attribue
	**/

	public void setRepeatSequenceNumber(Integer repeatSequenceNumber){
		this.repeatSequenceNumber = repeatSequenceNumber;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.ValidValue object
	**/
			
	private ValidValue defaultValidValue;
	/**
	* Retreives the value of defaultValidValue attribue
	* @return defaultValidValue
	**/
	
	public ValidValue getDefaultValidValue(){
		return defaultValidValue;
	}
	/**
	* Sets the value of defaultValidValue attribue
	**/

	public void setDefaultValidValue(ValidValue defaultValidValue){
		this.defaultValidValue = defaultValidValue;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof QuestionRepetition) 
		{
			QuestionRepetition c =(QuestionRepetition)obj; 			 
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