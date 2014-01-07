package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* A value domain expressed as a list of all permissible values.	**/
public class EnumeratedValueDomain extends ValueDomain implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
	/**
	* An associated gov.nih.nci.cadsr.domain.ValueDomainPermissibleValue object's collection 
	**/
			
	private Collection<ValueDomainPermissibleValue> valueDomainPermissibleValueCollection;
	/**
	* Retreives the value of valueDomainPermissibleValueCollection attribue
	* @return valueDomainPermissibleValueCollection
	**/

	public Collection<ValueDomainPermissibleValue> getValueDomainPermissibleValueCollection(){
		return valueDomainPermissibleValueCollection;
	}

	/**
	* Sets the value of valueDomainPermissibleValueCollection attribue
	**/

	public void setValueDomainPermissibleValueCollection(Collection<ValueDomainPermissibleValue> valueDomainPermissibleValueCollection){
		this.valueDomainPermissibleValueCollection = valueDomainPermissibleValueCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof EnumeratedValueDomain) 
		{
			EnumeratedValueDomain c =(EnumeratedValueDomain)obj; 			 
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