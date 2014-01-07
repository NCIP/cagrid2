package gov.nih.nci.cadsr.umlproject.domain;

import gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem;import java.util.Collection;
import java.io.Serializable;
	/**
	* A lower level witihn a higher level of a project. CSI type UML Package Alias.	**/
public class SubProject  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* The text representing an unambiguous definition.  This is stored as the Classification Scheme Item description in caDSR.  e.g CSI description	**/
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
	* The unique words that identify a Classification Scheme Item of type "UML_Package_Alias" in caDSR. 	**/
	public String name;
	/**
	* Retreives the value of name attribute
	* @return name
	**/

	public String getName(){
		return name;
	}

	/**
	* Sets the value of name attribue
	**/

	public void setName(String name){
		this.name = name;
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
	* An associated gov.nih.nci.cadsr.umlproject.domain.Project object
	**/
			
	private Project project;
	/**
	* Retreives the value of project attribue
	* @return project
	**/
	
	public Project getProject(){
		return project;
	}
	/**
	* Sets the value of project attribue
	**/

	public void setProject(Project project){
		this.project = project;
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
	* An associated gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem object
	**/
			
	private ClassSchemeClassSchemeItem classSchemeClassSchemeItem;
	/**
	* Retreives the value of classSchemeClassSchemeItem attribue
	* @return classSchemeClassSchemeItem
	**/
	
	public ClassSchemeClassSchemeItem getClassSchemeClassSchemeItem(){
		return classSchemeClassSchemeItem;
	}
	/**
	* Sets the value of classSchemeClassSchemeItem attribue
	**/

	public void setClassSchemeClassSchemeItem(ClassSchemeClassSchemeItem classSchemeClassSchemeItem){
		this.classSchemeClassSchemeItem = classSchemeClassSchemeItem;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof SubProject) 
		{
			SubProject c =(SubProject)obj; 			 
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