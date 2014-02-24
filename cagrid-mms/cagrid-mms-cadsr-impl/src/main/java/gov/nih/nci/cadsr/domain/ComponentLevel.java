package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* level of the component of the derivation rule	**/
public class ComponentLevel  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* A string of numeric characters that binds the components at a certain level together	**/
	public String concatenationString;
	/**
	* Retreives the value of concatenationString attribute
	* @return concatenationString
	**/

	public String getConcatenationString(){
		return concatenationString;
	}

	/**
	* Sets the value of concatenationString attribue
	**/

	public void setConcatenationString(String concatenationString){
		this.concatenationString = concatenationString;
	}
	
		/**
	* The 36 character caDSR database identifier.	**/
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
	* Level of the component.	**/
	public Integer level;
	/**
	* Retreives the value of level attribute
	* @return level
	**/

	public Integer getLevel(){
		return level;
	}

	/**
	* Sets the value of level attribue
	**/

	public void setLevel(Integer level){
		this.level = level;
	}
	
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ComponentLevel) 
		{
			ComponentLevel c =(ComponentLevel)obj; 			 
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