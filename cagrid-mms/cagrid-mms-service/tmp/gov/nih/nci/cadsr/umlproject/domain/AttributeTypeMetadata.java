package gov.nih.nci.cadsr.umlproject.domain;

import java.util.Collection;import gov.nih.nci.cadsr.domain.ValueDomain;
import java.io.Serializable;
	/**
	* The datatype of the Value Domain associated with the attribute.	**/
public class AttributeTypeMetadata  implements Serializable
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
	* The unique identifier for an Administered Item within a Registration Authority.  (ISO 11179)	**/
	private Long publicID;
	/**
	* Retreives the value of publicID attribute
	* @return publicID
	**/

	public Long getPublicID(){
		return publicID;
	}

	/**
	* Sets the value of publicID attribue
	**/

	public void setPublicID(Long publicID){
		this.publicID = publicID;
	}
	
		/**
	* The description of the data form of a value in the permissible value set for the attribute.	**/
	public String valueDomainDataType;
	/**
	* Retreives the value of valueDomainDataType attribute
	* @return valueDomainDataType
	**/

	public String getValueDomainDataType(){
		return valueDomainDataType;
	}

	/**
	* Sets the value of valueDomainDataType attribue
	**/

	public void setValueDomainDataType(String valueDomainDataType){
		this.valueDomainDataType = valueDomainDataType;
	}
	
		/**
	* The descriptive name for the administered component containing the permissible value set for the attribute.	**/
	public String valueDomainLongName;
	/**
	* Retreives the value of valueDomainLongName attribute
	* @return valueDomainLongName
	**/

	public String getValueDomainLongName(){
		return valueDomainLongName;
	}

	/**
	* Sets the value of valueDomainLongName attribue
	**/

	public void setValueDomainLongName(String valueDomainLongName){
		this.valueDomainLongName = valueDomainLongName;
	}
	
		/**
	* The unique version identifier of the Administered Item. (ISO 11179)	**/
	private Float version;
	/**
	* Retreives the value of version attribute
	* @return version
	**/

	public Float getVersion(){
		return version;
	}

	/**
	* Sets the value of version attribue
	**/

	public void setVersion(Float version){
		this.version = version;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.umlproject.domain.TypeEnumerationMetadata object's collection 
	**/
			
	private Collection<TypeEnumerationMetadata> typeEnumerationCollection;
	/**
	* Retreives the value of typeEnumerationCollection attribue
	* @return typeEnumerationCollection
	**/

	public Collection<TypeEnumerationMetadata> getTypeEnumerationCollection(){
		return typeEnumerationCollection;
	}

	/**
	* Sets the value of typeEnumerationCollection attribue
	**/

	public void setTypeEnumerationCollection(Collection<TypeEnumerationMetadata> typeEnumerationCollection){
		this.typeEnumerationCollection = typeEnumerationCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ValueDomain object
	**/
			
	private ValueDomain valueDomain;
	/**
	* Retreives the value of valueDomain attribue
	* @return valueDomain
	**/
	
	public ValueDomain getValueDomain(){
		return valueDomain;
	}
	/**
	* Sets the value of valueDomain attribue
	**/

	public void setValueDomain(ValueDomain valueDomain){
		this.valueDomain = valueDomain;
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
		if(obj instanceof AttributeTypeMetadata) 
		{
			AttributeTypeMetadata c =(AttributeTypeMetadata)obj; 			 
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