package gov.nih.nci.cadsr.domain;


import java.io.Serializable;
	/**
	* A value domain that is specified by a description rather than a list of all Permissible Values. (ISO 11179)	**/
public class NonenumeratedValueDomain extends ValueDomain implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof NonenumeratedValueDomain) 
		{
			NonenumeratedValueDomain c =(NonenumeratedValueDomain)obj; 			 
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