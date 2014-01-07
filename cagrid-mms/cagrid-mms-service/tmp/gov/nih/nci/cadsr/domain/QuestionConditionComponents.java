package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* the components of a condition. the components could be a question or other condition	**/
public class QuestionConditionComponents  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* a constant value 	**/
	public String constantValue;
	/**
	* Retreives the value of constantValue attribute
	* @return constantValue
	**/

	public String getConstantValue(){
		return constantValue;
	}

	/**
	* Sets the value of constantValue attribue
	**/

	public void setConstantValue(String constantValue){
		this.constantValue = constantValue;
	}
	
		/**
	* the order in which the component will appear for the condition	**/
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
	* identifier of the condition component	**/
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
	* the logical operand for the condition. values may be AND , OR , NOT	**/
	public String logicalOperand;
	/**
	* Retreives the value of logicalOperand attribute
	* @return logicalOperand
	**/

	public String getLogicalOperand(){
		return logicalOperand;
	}

	/**
	* Sets the value of logicalOperand attribue
	**/

	public void setLogicalOperand(String logicalOperand){
		this.logicalOperand = logicalOperand;
	}
	
		/**
	* other operand like =,+ etc	**/
	public String operand;
	/**
	* Retreives the value of operand attribute
	* @return operand
	**/

	public String getOperand(){
		return operand;
	}

	/**
	* Sets the value of operand attribue
	**/

	public void setOperand(String operand){
		this.operand = operand;
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
	* An associated gov.nih.nci.cadsr.domain.ValidValue object
	**/
			
	private ValidValue validValue;
	/**
	* Retreives the value of validValue attribue
	* @return validValue
	**/
	
	public ValidValue getValidValue(){
		return validValue;
	}
	/**
	* Sets the value of validValue attribue
	**/

	public void setValidValue(ValidValue validValue){
		this.validValue = validValue;
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
	* An associated gov.nih.nci.cadsr.domain.Function object
	**/
			
	private Function function;
	/**
	* Retreives the value of function attribue
	* @return function
	**/
	
	public Function getFunction(){
		return function;
	}
	/**
	* Sets the value of function attribue
	**/

	public void setFunction(Function function){
		this.function = function;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.QuestionCondition object
	**/
			
	private QuestionCondition parentQuestionCondition;
	/**
	* Retreives the value of parentQuestionCondition attribue
	* @return parentQuestionCondition
	**/
	
	public QuestionCondition getParentQuestionCondition(){
		return parentQuestionCondition;
	}
	/**
	* Sets the value of parentQuestionCondition attribue
	**/

	public void setParentQuestionCondition(QuestionCondition parentQuestionCondition){
		this.parentQuestionCondition = parentQuestionCondition;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof QuestionConditionComponents) 
		{
			QuestionConditionComponents c =(QuestionConditionComponents)obj; 			 
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