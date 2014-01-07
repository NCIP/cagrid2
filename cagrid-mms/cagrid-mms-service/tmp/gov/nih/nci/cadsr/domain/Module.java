package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* A collection of data elements, or Common Data Elements, logically grouped on a case report form.	**/
public class Module extends FormElement implements Serializable
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
	* The maximum number of times a question may repeat within a module 	**/
	public Integer maximumQuestionRepeat;
	/**
	* Retreives the value of maximumQuestionRepeat attribute
	* @return maximumQuestionRepeat
	**/

	public Integer getMaximumQuestionRepeat(){
		return maximumQuestionRepeat;
	}

	/**
	* Sets the value of maximumQuestionRepeat attribue
	**/

	public void setMaximumQuestionRepeat(Integer maximumQuestionRepeat){
		this.maximumQuestionRepeat = maximumQuestionRepeat;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.Form object
	**/
			
	private Form form;
	/**
	* Retreives the value of form attribue
	* @return form
	**/
	
	public Form getForm(){
		return form;
	}
	/**
	* Sets the value of form attribue
	**/

	public void setForm(Form form){
		this.form = form;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.Question object's collection 
	**/
			
	private Collection<Question> questionCollection;
	/**
	* Retreives the value of questionCollection attribue
	* @return questionCollection
	**/

	public Collection<Question> getQuestionCollection(){
		return questionCollection;
	}

	/**
	* Sets the value of questionCollection attribue
	**/

	public void setQuestionCollection(Collection<Question> questionCollection){
		this.questionCollection = questionCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Module) 
		{
			Module c =(Module)obj; 			 
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