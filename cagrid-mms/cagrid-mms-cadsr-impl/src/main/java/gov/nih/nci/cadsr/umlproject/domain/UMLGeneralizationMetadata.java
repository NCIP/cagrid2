package gov.nih.nci.cadsr.umlproject.domain;

import gov.nih.nci.cadsr.domain.ObjectClassRelationship;
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