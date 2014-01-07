package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* A condition created by using a question or other conditions	**/
public class QuestionCondition  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* the identifier for the condition	**/
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
	* An associated gov.nih.nci.cadsr.domain.QuestionConditionComponents object's collection 
	**/
			
	private Collection<QuestionConditionComponents> conditionComponentCollection;
	/**
	* Retreives the value of conditionComponentCollection attribue
	* @return conditionComponentCollection
	**/

	public Collection<QuestionConditionComponents> getConditionComponentCollection(){
		return conditionComponentCollection;
	}

	/**
	* Sets the value of conditionComponentCollection attribue
	**/

	public void setConditionComponentCollection(Collection<QuestionConditionComponents> conditionComponentCollection){
		this.conditionComponentCollection = conditionComponentCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.TriggerAction object's collection 
	**/
			
	private Collection<TriggerAction> forcedConditionTriggeredActionCollection;
	/**
	* Retreives the value of forcedConditionTriggeredActionCollection attribue
	* @return forcedConditionTriggeredActionCollection
	**/

	public Collection<TriggerAction> getForcedConditionTriggeredActionCollection(){
		return forcedConditionTriggeredActionCollection;
	}

	/**
	* Sets the value of forcedConditionTriggeredActionCollection attribue
	**/

	public void setForcedConditionTriggeredActionCollection(Collection<TriggerAction> forcedConditionTriggeredActionCollection){
		this.forcedConditionTriggeredActionCollection = forcedConditionTriggeredActionCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.Question object's collection 
	**/
			
	private Collection<Question> question;
	/**
	* Retreives the value of question attribue
	* @return question
	**/

	public Collection<Question> getQuestion(){
		return question;
	}

	/**
	* Sets the value of question attribue
	**/

	public void setQuestion(Collection<Question> question){
		this.question = question;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.TriggerAction object's collection 
	**/
			
	private Collection<TriggerAction> triggeredActionCollection;
	/**
	* Retreives the value of triggeredActionCollection attribue
	* @return triggeredActionCollection
	**/

	public Collection<TriggerAction> getTriggeredActionCollection(){
		return triggeredActionCollection;
	}

	/**
	* Sets the value of triggeredActionCollection attribue
	**/

	public void setTriggeredActionCollection(Collection<TriggerAction> triggeredActionCollection){
		this.triggeredActionCollection = triggeredActionCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ConditionMessage object's collection 
	**/
			
	private Collection<ConditionMessage> condtionMessage;
	/**
	* Retreives the value of condtionMessage attribue
	* @return condtionMessage
	**/

	public Collection<ConditionMessage> getCondtionMessage(){
		return condtionMessage;
	}

	/**
	* Sets the value of condtionMessage attribue
	**/

	public void setCondtionMessage(Collection<ConditionMessage> condtionMessage){
		this.condtionMessage = condtionMessage;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.QuestionConditionComponents object's collection 
	**/
			
	private Collection<QuestionConditionComponents> questionCondition;
	/**
	* Retreives the value of questionCondition attribue
	* @return questionCondition
	**/

	public Collection<QuestionConditionComponents> getQuestionCondition(){
		return questionCondition;
	}

	/**
	* Sets the value of questionCondition attribue
	**/

	public void setQuestionCondition(Collection<QuestionConditionComponents> questionCondition){
		this.questionCondition = questionCondition;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof QuestionCondition) 
		{
			QuestionCondition c =(QuestionCondition)obj; 			 
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