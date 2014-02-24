package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* The information pertaining to the meaning or semantic content of a Value. (ISO 11179)	**/
public class ValueMeaning extends AdministeredComponent implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* A written explanation added to an item.	**/
	public String comments;
	/**
	* Retreives the value of comments attribute
	* @return comments
	**/

	public String getComments(){
		return comments;
	}

	/**
	* Sets the value of comments attribue
	**/

	public void setComments(String comments){
		this.comments = comments;
	}
	
		/**
	* A statement in words representing the item. 	**/
	public String description;
	/**
	* Retreives the value of description attribute
	* @return description
	**/

	public String getDescription(){
		return description;
	}

	/**
	* Sets the value of description attribue
	**/

	public void setDescription(String description){
		this.description = description;
	}
	
		/**
	* The unique identifier for a Value Meaning, also known as the Value Meaning. 	**/
	public String shortMeaning;
	/**
	* Retreives the value of shortMeaning attribute
	* @return shortMeaning
	**/

	public String getShortMeaning(){
		return shortMeaning;
	}

	/**
	* Sets the value of shortMeaning attribue
	**/

	public void setShortMeaning(String shortMeaning){
		this.shortMeaning = shortMeaning;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.ConceptualDomain object's collection 
	**/
			
	private Collection<ConceptualDomain> conceptualDomainCollection;
	/**
	* Retreives the value of conceptualDomainCollection attribue
	* @return conceptualDomainCollection
	**/

	public Collection<ConceptualDomain> getConceptualDomainCollection(){
		return conceptualDomainCollection;
	}

	/**
	* Sets the value of conceptualDomainCollection attribue
	**/

	public void setConceptualDomainCollection(Collection<ConceptualDomain> conceptualDomainCollection){
		this.conceptualDomainCollection = conceptualDomainCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.PermissibleValue object's collection 
	**/
			
	private Collection<PermissibleValue> permissibleValueCollection;
	/**
	* Retreives the value of permissibleValueCollection attribue
	* @return permissibleValueCollection
	**/

	public Collection<PermissibleValue> getPermissibleValueCollection(){
		return permissibleValueCollection;
	}

	/**
	* Sets the value of permissibleValueCollection attribue
	**/

	public void setPermissibleValueCollection(Collection<PermissibleValue> permissibleValueCollection){
		this.permissibleValueCollection = permissibleValueCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ConceptDerivationRule object
	**/
			
	private ConceptDerivationRule conceptDerivationRule;
	/**
	* Retreives the value of conceptDerivationRule attribue
	* @return conceptDerivationRule
	**/
	
	public ConceptDerivationRule getConceptDerivationRule(){
		return conceptDerivationRule;
	}
	/**
	* Sets the value of conceptDerivationRule attribue
	**/

	public void setConceptDerivationRule(ConceptDerivationRule conceptDerivationRule){
		this.conceptDerivationRule = conceptDerivationRule;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ValueMeaning) 
		{
			ValueMeaning c =(ValueMeaning)obj; 			 
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