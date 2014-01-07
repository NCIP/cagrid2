package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* A questionnaire that documents all the patient data stipulated in the protocol and used by clinicians to record information about patient's visits while on the clinical trial.	**/
public class Form extends FormElement implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* A name used to represent the Case Report Form in user interfaces.	**/
	public String displayName;
	/**
	* Retreives the value of displayName attribute
	* @return displayName
	**/

	public String getDisplayName(){
		return displayName;
	}

	/**
	* Sets the value of displayName attribue
	**/

	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}
	
		/**
	* The type of form.	**/
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
	* An associated gov.nih.nci.cadsr.domain.Module object's collection 
	**/
			
	private Collection<Module> moduleCollection;
	/**
	* Retreives the value of moduleCollection attribue
	* @return moduleCollection
	**/

	public Collection<Module> getModuleCollection(){
		return moduleCollection;
	}

	/**
	* Sets the value of moduleCollection attribue
	**/

	public void setModuleCollection(Collection<Module> moduleCollection){
		this.moduleCollection = moduleCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.Protocol object's collection 
	**/
			
	private Collection<Protocol> protocolCollection;
	/**
	* Retreives the value of protocolCollection attribue
	* @return protocolCollection
	**/

	public Collection<Protocol> getProtocolCollection(){
		return protocolCollection;
	}

	/**
	* Sets the value of protocolCollection attribue
	**/

	public void setProtocolCollection(Collection<Protocol> protocolCollection){
		this.protocolCollection = protocolCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Form) 
		{
			Form c =(Form)obj; 			 
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