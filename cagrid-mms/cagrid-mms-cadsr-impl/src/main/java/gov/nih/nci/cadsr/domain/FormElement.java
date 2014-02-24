package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* The element of a Form	**/
public class FormElement extends AdministeredComponent implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
	/**
	* An associated gov.nih.nci.cadsr.domain.TriggerAction object's collection 
	**/
			
	private Collection<TriggerAction> triggerActionCollection;
	/**
	* Retreives the value of triggerActionCollection attribue
	* @return triggerActionCollection
	**/

	public Collection<TriggerAction> getTriggerActionCollection(){
		return triggerActionCollection;
	}

	/**
	* Sets the value of triggerActionCollection attribue
	**/

	public void setTriggerActionCollection(Collection<TriggerAction> triggerActionCollection){
		this.triggerActionCollection = triggerActionCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.Instruction object's collection 
	**/
			
	private Collection<Instruction> instruction;
	/**
	* Retreives the value of instruction attribue
	* @return instruction
	**/

	public Collection<Instruction> getInstruction(){
		return instruction;
	}

	/**
	* Sets the value of instruction attribue
	**/

	public void setInstruction(Collection<Instruction> instruction){
		this.instruction = instruction;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof FormElement) 
		{
			FormElement c =(FormElement)obj; 			 
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