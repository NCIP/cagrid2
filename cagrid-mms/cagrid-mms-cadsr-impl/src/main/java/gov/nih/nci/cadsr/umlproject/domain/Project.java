package gov.nih.nci.cadsr.umlproject.domain;

import gov.nih.nci.cadsr.domain.ClassificationScheme;import java.util.Collection;
import java.io.Serializable;
	/**
	* The collection of metadata associated with a project. 	**/
public class Project  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* Text pertaining to the nature of the project.	**/
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
	* Used to represent the XMLNamespace from the GME	**/
	private String gmeNamespace;
	/**
	* Retreives the value of gmeNamespace attribute
	* @return gmeNamespace
	**/

	public String getGmeNamespace(){
		return gmeNamespace;
	}

	/**
	* Sets the value of gmeNamespace attribue
	**/

	public void setGmeNamespace(String gmeNamespace){
		this.gmeNamespace = gmeNamespace;
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
	* The long name of the project stored as a classification scheme preferred name.	**/
	public String longName;
	/**
	* Retreives the value of longName attribute
	* @return longName
	**/

	public String getLongName(){
		return longName;
	}

	/**
	* Sets the value of longName attribue
	**/

	public void setLongName(String longName){
		this.longName = longName;
	}
	
		/**
	* The short name of the project stored in the classification scheme short name.	**/
	public String shortName;
	/**
	* Retreives the value of shortName attribute
	* @return shortName
	**/

	public String getShortName(){
		return shortName;
	}

	/**
	* Sets the value of shortName attribue
	**/

	public void setShortName(String shortName){
		this.shortName = shortName;
	}
	
		/**
	* The version number including decimal position and trailing zero for the project. 	**/
	public String version;
	/**
	* Retreives the value of version attribute
	* @return version
	**/

	public String getVersion(){
		return version;
	}

	/**
	* Sets the value of version attribue
	**/

	public void setVersion(String version){
		this.version = version;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata object's collection 
	**/
			
	private Collection<UMLPackageMetadata> UMLPackageMetadataCollection;
	/**
	* Retreives the value of UMLPackageMetadataCollection attribue
	* @return UMLPackageMetadataCollection
	**/

	public Collection<UMLPackageMetadata> getUMLPackageMetadataCollection(){
		return UMLPackageMetadataCollection;
	}

	/**
	* Sets the value of UMLPackageMetadataCollection attribue
	**/

	public void setUMLPackageMetadataCollection(Collection<UMLPackageMetadata> UMLPackageMetadataCollection){
		this.UMLPackageMetadataCollection = UMLPackageMetadataCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.umlproject.domain.SubProject object's collection 
	**/
			
	private Collection<SubProject> subProjectCollection;
	/**
	* Retreives the value of subProjectCollection attribue
	* @return subProjectCollection
	**/

	public Collection<SubProject> getSubProjectCollection(){
		return subProjectCollection;
	}

	/**
	* Sets the value of subProjectCollection attribue
	**/

	public void setSubProjectCollection(Collection<SubProject> subProjectCollection){
		this.subProjectCollection = subProjectCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata object's collection 
	**/
			
	private Collection<UMLAttributeMetadata> UMLAttributeMetadataCollection;
	/**
	* Retreives the value of UMLAttributeMetadataCollection attribue
	* @return UMLAttributeMetadataCollection
	**/

	public Collection<UMLAttributeMetadata> getUMLAttributeMetadataCollection(){
		return UMLAttributeMetadataCollection;
	}

	/**
	* Sets the value of UMLAttributeMetadataCollection attribue
	**/

	public void setUMLAttributeMetadataCollection(Collection<UMLAttributeMetadata> UMLAttributeMetadataCollection){
		this.UMLAttributeMetadataCollection = UMLAttributeMetadataCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ClassificationScheme object
	**/
			
	private ClassificationScheme classificationScheme;
	/**
	* Retreives the value of classificationScheme attribue
	* @return classificationScheme
	**/
	
	public ClassificationScheme getClassificationScheme(){
		return classificationScheme;
	}
	/**
	* Sets the value of classificationScheme attribue
	**/

	public void setClassificationScheme(ClassificationScheme classificationScheme){
		this.classificationScheme = classificationScheme;
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
	* An associated gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata object's collection 
	**/
			
	private Collection<UMLAssociationMetadata> UMLAssociationMetadataCollection;
	/**
	* Retreives the value of UMLAssociationMetadataCollection attribue
	* @return UMLAssociationMetadataCollection
	**/

	public Collection<UMLAssociationMetadata> getUMLAssociationMetadataCollection(){
		return UMLAssociationMetadataCollection;
	}

	/**
	* Sets the value of UMLAssociationMetadataCollection attribue
	**/

	public void setUMLAssociationMetadataCollection(Collection<UMLAssociationMetadata> UMLAssociationMetadataCollection){
		this.UMLAssociationMetadataCollection = UMLAssociationMetadataCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Project) 
		{
			Project c =(Project)obj; 			 
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