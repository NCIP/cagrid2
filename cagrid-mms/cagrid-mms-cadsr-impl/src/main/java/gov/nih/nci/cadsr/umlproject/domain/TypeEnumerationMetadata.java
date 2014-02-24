package gov.nih.nci.cadsr.umlproject.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* The permissible values for an attribute that has an enumerated value domain.	**/
public class TypeEnumerationMetadata  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
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
	* The exact words or values by which the data is stored in the data system.	**/
	public String permissibleValue;
	/**
	* Retreives the value of permissibleValue attribute
	* @return permissibleValue
	**/

	public String getPermissibleValue(){
		return permissibleValue;
	}

	/**
	* Sets the value of permissibleValue attribue
	**/

	public void setPermissibleValue(String permissibleValue){
		this.permissibleValue = permissibleValue;
	}
	
		/**
	* The significance associated with an allowable/permissible value.	**/
	public String valueMeaning;
	/**
	* Retreives the value of valueMeaning attribute
	* @return valueMeaning
	**/

	public String getValueMeaning(){
		return valueMeaning;
	}

	/**
	* Sets the value of valueMeaning attribue
	**/

	public void setValueMeaning(String valueMeaning){
		this.valueMeaning = valueMeaning;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata object's collection 
	**/
			
	private Collection<SemanticMetadata> semanticMetadataCollection;
	/**
	* Retreives the value of semanticMetadataCollection attribue
	* @return semanticMetadataCollection
	**/

	public Collection<SemanticMetadata> getSemanticMetadataCollection(){
		return semanticMetadataCollection;
	}

	/**
	* Sets the value of semanticMetadataCollection attribue
	**/

	public void setSemanticMetadataCollection(Collection<SemanticMetadata> semanticMetadataCollection){
		this.semanticMetadataCollection = semanticMetadataCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof TypeEnumerationMetadata) 
		{
			TypeEnumerationMetadata c =(TypeEnumerationMetadata)obj; 			 
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