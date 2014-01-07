package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* The messge associated to a condition	**/
public class ConditionMessage  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* id of the message	**/
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
	* text of the message	**/
	public String message;
	/**
	* Retreives the value of message attribute
	* @return message
	**/

	public String getMessage(){
		return message;
	}

	/**
	* Sets the value of message attribue
	**/

	public void setMessage(String message){
		this.message = message;
	}
	
		/**
	* Type of the message. It could be Error, Warning, Alert	**/
	public String messageType;
	/**
	* Retreives the value of messageType attribute
	* @return messageType
	**/

	public String getMessageType(){
		return messageType;
	}

	/**
	* Sets the value of messageType attribue
	**/

	public void setMessageType(String messageType){
		this.messageType = messageType;
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
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ConditionMessage) 
		{
			ConditionMessage c =(ConditionMessage)obj; 			 
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