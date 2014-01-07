package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* The actual text of the data element as specified on a Case Report Form of a Protocol.	**/
public class Question extends FormElement implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* The identifier for the value from an enumeration to automatically be displayed as a response to a question when no value is entered.	**/
	public String defaultValidValueId;
	/**
	* Retreives the value of defaultValidValueId attribute
	* @return defaultValidValueId
	**/

	public String getDefaultValidValueId(){
		return defaultValidValueId;
	}

	/**
	* Sets the value of defaultValidValueId attribue
	**/

	public void setDefaultValidValueId(String defaultValidValueId){
		this.defaultValidValueId = defaultValidValueId;
	}
	
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
	* Is the question mandatory	**/
	public String isMandatory;
	/**
	* Retreives the value of isMandatory attribute
	* @return isMandatory
	**/

	public String getIsMandatory(){
		return isMandatory;
	}

	/**
	* Sets the value of isMandatory attribue
	**/

	public void setIsMandatory(String isMandatory){
		this.isMandatory = isMandatory;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.QuestionCondition object
	**/
			
	private QuestionCondition questionCondition;
	/**
	* Retreives the value of questionCondition attribue
	* @return questionCondition
	**/
	
	public QuestionCondition getQuestionCondition(){
		return questionCondition;
	}
	/**
	* Sets the value of questionCondition attribue
	**/

	public void setQuestionCondition(QuestionCondition questionCondition){
		this.questionCondition = questionCondition;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.QuestionRepetition object's collection 
	**/
			
	private Collection<QuestionRepetition> questionRepetitionCollection;
	/**
	* Retreives the value of questionRepetitionCollection attribue
	* @return questionRepetitionCollection
	**/

	public Collection<QuestionRepetition> getQuestionRepetitionCollection(){
		return questionRepetitionCollection;
	}

	/**
	* Sets the value of questionRepetitionCollection attribue
	**/

	public void setQuestionRepetitionCollection(Collection<QuestionRepetition> questionRepetitionCollection){
		this.questionRepetitionCollection = questionRepetitionCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.Module object
	**/
			
	private Module module;
	/**
	* Retreives the value of module attribue
	* @return module
	**/
	
	public Module getModule(){
		return module;
	}
	/**
	* Sets the value of module attribue
	**/

	public void setModule(Module module){
		this.module = module;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.ValueDomain object
	**/
			
	private ValueDomain valueDomain;
	/**
	* Retreives the value of valueDomain attribue
	* @return valueDomain
	**/
	
	public ValueDomain getValueDomain(){
		return valueDomain;
	}
	/**
	* Sets the value of valueDomain attribue
	**/

	public void setValueDomain(ValueDomain valueDomain){
		this.valueDomain = valueDomain;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.QuestionConditionComponents object's collection 
	**/
			
	private Collection<QuestionConditionComponents> questionComponentCollection;
	/**
	* Retreives the value of questionComponentCollection attribue
	* @return questionComponentCollection
	**/

	public Collection<QuestionConditionComponents> getQuestionComponentCollection(){
		return questionComponentCollection;
	}

	/**
	* Sets the value of questionComponentCollection attribue
	**/

	public void setQuestionComponentCollection(Collection<QuestionConditionComponents> questionComponentCollection){
		this.questionComponentCollection = questionComponentCollection;
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
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Question) 
		{
			Question c =(Question)obj; 			 
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