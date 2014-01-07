package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* Instruction for a Form, Module, Question or Valid Value on a Form	**/
public class Instruction extends AdministeredComponent implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* A particular kind of Instruction.	**/
	public String type;
	/**
	* Retreives the value of type attribute
	* @return type
	**/

	public String getType(){
		return type;
	}

	/**
	* Sets the value of type attribue
	**/

	public void setType(String type){
		this.type = type;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.FormElement object
	**/
			
	private FormElement formElement;
	/**
	* Retreives the value of formElement attribue
	* @return formElement
	**/
	
	public FormElement getFormElement(){
		return formElement;
	}
	/**
	* Sets the value of formElement attribue
	**/

	public void setFormElement(FormElement formElement){
		this.formElement = formElement;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Instruction) 
		{
			Instruction c =(Instruction)obj; 			 
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