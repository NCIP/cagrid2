package gov.nih.nci.cadsr.domain;

import gov.nih.nci.cadsr.umlproject.domain.UMLPackageMetadata;import java.util.Collection;import gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata;import gov.nih.nci.cadsr.umlproject.domain.SubProject;
import java.io.Serializable;
	/**
	* Information pertaining to the association between Classification Schemes and Classification Scheme Items.  	**/
public class ClassSchemeClassSchemeItem  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* Username of the person who created the record	**/
	public String createdBy;
	/**
	* Retreives the value of createdBy attribute
	* @return createdBy
	**/

	public String getCreatedBy(){
		return createdBy;
	}

	/**
	* Sets the value of createdBy attribue
	**/

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}
	
		/**
	* The date the record was created.	**/
	public java.util.Date dateCreated;
	/**
	* Retreives the value of dateCreated attribute
	* @return dateCreated
	**/

	public java.util.Date getDateCreated(){
		return dateCreated;
	}

	/**
	* Sets the value of dateCreated attribue
	**/

	public void setDateCreated(java.util.Date dateCreated){
		this.dateCreated = dateCreated;
	}
	
		/**
	* Date on which the record was modified.	**/
	public java.util.Date dateModified;
	/**
	* Retreives the value of dateModified attribute
	* @return dateModified
	**/

	public java.util.Date getDateModified(){
		return dateModified;
	}

	/**
	* Sets the value of dateModified attribue
	**/

	public void setDateModified(java.util.Date dateModified){
		this.dateModified = dateModified;
	}
	
		/**
	* The logical arrangement of items for viewing in a user interface.	**/
	public Integer displayOrder;
	/**
	* Retreives the value of displayOrder attribute
	* @return displayOrder
	**/

	public Integer getDisplayOrder(){
		return displayOrder;
	}

	/**
	* Sets the value of displayOrder attribue
	**/

	public void setDisplayOrder(Integer displayOrder){
		this.displayOrder = displayOrder;
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
	* The username of the person who last changed the item.	**/
	public String modifiedBy;
	/**
	* Retreives the value of modifiedBy attribute
	* @return modifiedBy
	**/

	public String getModifiedBy(){
		return modifiedBy;
	}

	/**
	* Sets the value of modifiedBy attribue
	**/

	public void setModifiedBy(String modifiedBy){
		this.modifiedBy = modifiedBy;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.ClassificationSchemeItem object
	**/
			
	private ClassificationSchemeItem classificationSchemeItem;
	/**
	* Retreives the value of classificationSchemeItem attribue
	* @return classificationSchemeItem
	**/
	
	public ClassificationSchemeItem getClassificationSchemeItem(){
		return classificationSchemeItem;
	}
	/**
	* Sets the value of classificationSchemeItem attribue
	**/

	public void setClassificationSchemeItem(ClassificationSchemeItem classificationSchemeItem){
		this.classificationSchemeItem = classificationSchemeItem;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.DefinitionClassSchemeItem object's collection 
	**/
			
	private Collection<DefinitionClassSchemeItem> definitionClassSchemeItemCollection;
	/**
	* Retreives the value of definitionClassSchemeItemCollection attribue
	* @return definitionClassSchemeItemCollection
	**/

	public Collection<DefinitionClassSchemeItem> getDefinitionClassSchemeItemCollection(){
		return definitionClassSchemeItemCollection;
	}

	/**
	* Sets the value of definitionClassSchemeItemCollection attribue
	**/

	public void setDefinitionClassSchemeItemCollection(Collection<DefinitionClassSchemeItem> definitionClassSchemeItemCollection){
		this.definitionClassSchemeItemCollection = definitionClassSchemeItemCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem object's collection 
	**/
			
	private Collection<ClassSchemeClassSchemeItem> childClassSchemeClassSchemeItemCollection;
	/**
	* Retreives the value of childClassSchemeClassSchemeItemCollection attribue
	* @return childClassSchemeClassSchemeItemCollection
	**/

	public Collection<ClassSchemeClassSchemeItem> getChildClassSchemeClassSchemeItemCollection(){
		return childClassSchemeClassSchemeItemCollection;
	}

	/**
	* Sets the value of childClassSchemeClassSchemeItemCollection attribue
	**/

	public void setChildClassSchemeClassSchemeItemCollection(Collection<ClassSchemeClassSchemeItem> childClassSchemeClassSchemeItemCollection){
		this.childClassSchemeClassSchemeItemCollection = childClassSchemeClassSchemeItemCollection;
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
	* An associated gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem object's collection 
	**/
			
	private Collection<AdministeredComponentClassSchemeItem> administeredComponentClassSchemeItemCollection;
	/**
	* Retreives the value of administeredComponentClassSchemeItemCollection attribue
	* @return administeredComponentClassSchemeItemCollection
	**/

	public Collection<AdministeredComponentClassSchemeItem> getAdministeredComponentClassSchemeItemCollection(){
		return administeredComponentClassSchemeItemCollection;
	}

	/**
	* Sets the value of administeredComponentClassSchemeItemCollection attribue
	**/

	public void setAdministeredComponentClassSchemeItemCollection(Collection<AdministeredComponentClassSchemeItem> administeredComponentClassSchemeItemCollection){
		this.administeredComponentClassSchemeItemCollection = administeredComponentClassSchemeItemCollection;
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
	* An associated gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata object's collection 
	**/
			
	private Collection<UMLAssociationMetadata> association;
	/**
	* Retreives the value of association attribue
	* @return association
	**/

	public Collection<UMLAssociationMetadata> getAssociation(){
		return association;
	}

	/**
	* Sets the value of association attribue
	**/

	public void setAssociation(Collection<UMLAssociationMetadata> association){
		this.association = association;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ReferenceDocument object's collection 
	**/
			
	private Collection<ReferenceDocument> referenceDocumentCollection;
	/**
	* Retreives the value of referenceDocumentCollection attribue
	* @return referenceDocumentCollection
	**/

	public Collection<ReferenceDocument> getReferenceDocumentCollection(){
		return referenceDocumentCollection;
	}

	/**
	* Sets the value of referenceDocumentCollection attribue
	**/

	public void setReferenceDocumentCollection(Collection<ReferenceDocument> referenceDocumentCollection){
		this.referenceDocumentCollection = referenceDocumentCollection;
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
	* An associated gov.nih.nci.cadsr.domain.AdministeredComponentContact object's collection 
	**/
			
	private Collection<AdministeredComponentContact> AdministeredComponentContact;
	/**
	* Retreives the value of AdministeredComponentContact attribue
	* @return AdministeredComponentContact
	**/

	public Collection<AdministeredComponentContact> getAdministeredComponentContact(){
		return AdministeredComponentContact;
	}

	/**
	* Sets the value of AdministeredComponentContact attribue
	**/

	public void setAdministeredComponentContact(Collection<AdministeredComponentContact> AdministeredComponentContact){
		this.AdministeredComponentContact = AdministeredComponentContact;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.DesignationClassSchemeItem object's collection 
	**/
			
	private Collection<DesignationClassSchemeItem> designationClassSchemeItemCollection;
	/**
	* Retreives the value of designationClassSchemeItemCollection attribue
	* @return designationClassSchemeItemCollection
	**/

	public Collection<DesignationClassSchemeItem> getDesignationClassSchemeItemCollection(){
		return designationClassSchemeItemCollection;
	}

	/**
	* Sets the value of designationClassSchemeItemCollection attribue
	**/

	public void setDesignationClassSchemeItemCollection(Collection<DesignationClassSchemeItem> designationClassSchemeItemCollection){
		this.designationClassSchemeItemCollection = designationClassSchemeItemCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ClassSchemeClassSchemeItem) 
		{
			ClassSchemeClassSchemeItem c =(ClassSchemeClassSchemeItem)obj; 			 
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