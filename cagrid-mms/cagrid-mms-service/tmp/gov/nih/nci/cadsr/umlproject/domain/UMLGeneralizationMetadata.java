package gov.nih.nci.cadsr.umlproject.domain;

import gov.nih.nci.cadsr.domain.ObjectClassRelationship;import java.util.Collection;
import java.io.Serializable;
	/**
	* The informatoin about inheritance type associations for a class in the uml model. 	**/
public class UMLGeneralizationMetadata  implements Serializable
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
	* The unique version identifier of the Administered Item. (ISO 11179)	**/
	private Long version;
	/**
	* Retreives the value of version attribute
	* @return version
	**/

	public Long getVersion(){
		return version;
	}

	/**
	* Sets the value of version attribue
	**/

	public void setVersion(Long version){
		this.version = version;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.ObjectClassRelationship object
	**/
			
	private ObjectClassRelationship objectClassRelationship;
	/**
	* Retreives the value of objectClassRelationship attribue
	* @return objectClassRelationship
	**/
	
	public ObjectClassRelationship getObjectClassRelationship(){
		return objectClassRelationship;
	}
	/**
	* Sets the value of objectClassRelationship attribue
	**/

	public void setObjectClassRelationship(ObjectClassRelationship objectClassRelationship){
		this.objectClassRelationship = objectClassRelationship;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata object's collection 
	**/
			
	private Collection<UMLClassMetadata> UMLClassMetadataCollection;
	/**
	* Retreives the value of UMLClassMetadataCollection attribue
	* @return UMLClassMetadataCollection
	**/

	public Collection<UMLClassMetadata> getUMLClassMetadataCollection(){
		return UMLClassMetadataCollection;
	}

	/**
	* Sets the value of UMLClassMetadataCollection attribue
	**/

	public void setUMLClassMetadataCollection(Collection<UMLClassMetadata> UMLClassMetadataCollection){
		this.UMLClassMetadataCollection = UMLClassMetadataCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata object
	**/
			
	private UMLClassMetadata superUMLClassMetadata;
	/**
	* Retreives the value of superUMLClassMetadata attribue
	* @return superUMLClassMetadata
	**/
	
	public UMLClassMetadata getSuperUMLClassMetadata(){
		return superUMLClassMetadata;
	}
	/**
	* Sets the value of superUMLClassMetadata attribue
	**/

	public void setSuperUMLClassMetadata(UMLClassMetadata superUMLClassMetadata){
		this.superUMLClassMetadata = superUMLClassMetadata;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof UMLGeneralizationMetadata) 
		{
			UMLGeneralizationMetadata c =(UMLGeneralizationMetadata)obj; 			 
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