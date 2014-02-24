package gov.nih.nci.cadsr.umlproject.domain;

import gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem;import java.util.Collection;
import java.io.Serializable;
	/**
	* Metadata associated with CSI type = UMLPACKAGE	**/
public class UMLPackageMetadata  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* Text pertaining to the nature of the package within the project (CSI definition)	**/
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
	* The words by which the package is known within the project. 	**/
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
	* An associated gov.nih.nci.cadsr.umlproject.domain.SubProject object
	**/
			
	private SubProject subProject;
	/**
	* Retreives the value of subProject attribue
	* @return subProject
	**/
	
	public SubProject getSubProject(){
		return subProject;
	}
	/**
	* Sets the value of subProject attribue
	**/

	public void setSubProject(SubProject subProject){
		this.subProject = subProject;
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
		if(obj instanceof UMLPackageMetadata) 
		{
			UMLPackageMetadata c =(UMLPackageMetadata)obj; 			 
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