package gov.nih.nci.cadsr.umlproject.domain;

import gov.nih.nci.cadsr.domain.ObjectClassRelationship;import java.util.Collection;import gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem;
import java.io.Serializable;
	/**
	* The attributes associated with the UML Class describing its relationships to other UML Classes.  This information is stored in the caDSR Object Class Relationship.	**/
public class UMLAssociationMetadata  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* the XML schema tag name in the schema registered in caGrid Global Model Exchange assigned to the association in a UML Model representing the source Class in the association	**/
	private String gmeSourceXMLLocReference;
	/**
	* Retreives the value of gmeSourceXMLLocReference attribute
	* @return gmeSourceXMLLocReference
	**/

	public String getGmeSourceXMLLocReference(){
		return gmeSourceXMLLocReference;
	}

	/**
	* Sets the value of gmeSourceXMLLocReference attribue
	**/

	public void setGmeSourceXMLLocReference(String gmeSourceXMLLocReference){
		this.gmeSourceXMLLocReference = gmeSourceXMLLocReference;
	}
	
		/**
	* the XML schema tag name in the schema registered in caGrid Global Model Exchange assigned to the association in a UML Model representing the target Class in the association	**/
	private String gmeTargetXMLLocReference;
	/**
	* Retreives the value of gmeTargetXMLLocReference attribute
	* @return gmeTargetXMLLocReference
	**/

	public String getGmeTargetXMLLocReference(){
		return gmeTargetXMLLocReference;
	}

	/**
	* Sets the value of gmeTargetXMLLocReference attribue
	**/

	public void setGmeTargetXMLLocReference(String gmeTargetXMLLocReference){
		this.gmeTargetXMLLocReference = gmeTargetXMLLocReference;
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
	* An indicator that can be set or unset in order to signal if the relationship can be navigated in opposite directions	**/
	public Boolean isBidirectional;
	/**
	* Retreives the value of isBidirectional attribute
	* @return isBidirectional
	**/

	public Boolean getIsBidirectional(){
		return isBidirectional;
	}

	/**
	* Sets the value of isBidirectional attribue
	**/

	public void setIsBidirectional(Boolean isBidirectional){
		this.isBidirectional = isBidirectional;
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
	* Approaching or constituting a maximum; the highest number of elements in a set where the association originates.	**/
	public Integer sourceHighCardinality;
	/**
	* Retreives the value of sourceHighCardinality attribute
	* @return sourceHighCardinality
	**/

	public Integer getSourceHighCardinality(){
		return sourceHighCardinality;
	}

	/**
	* Sets the value of sourceHighCardinality attribue
	**/

	public void setSourceHighCardinality(Integer sourceHighCardinality){
		this.sourceHighCardinality = sourceHighCardinality;
	}
	
		/**
	* Approaching or constituting a minimum; the lowest number of elements in a set from which the association originates.	**/
	public Integer sourceLowCardinality;
	/**
	* Retreives the value of sourceLowCardinality attribute
	* @return sourceLowCardinality
	**/

	public Integer getSourceLowCardinality(){
		return sourceLowCardinality;
	}

	/**
	* Sets the value of sourceLowCardinality attribue
	**/

	public void setSourceLowCardinality(Integer sourceLowCardinality){
		this.sourceLowCardinality = sourceLowCardinality;
	}
	
		/**
	* The words or language units by which the class from where the association originates is known.	**/
	public String sourceRoleName;
	/**
	* Retreives the value of sourceRoleName attribute
	* @return sourceRoleName
	**/

	public String getSourceRoleName(){
		return sourceRoleName;
	}

	/**
	* Sets the value of sourceRoleName attribue
	**/

	public void setSourceRoleName(String sourceRoleName){
		this.sourceRoleName = sourceRoleName;
	}
	
		/**
	* Approaching or constituting a maximum; the highest number of objects fixed as a goal or point of an association destination.	**/
	public Integer targetHighCardinality;
	/**
	* Retreives the value of targetHighCardinality attribute
	* @return targetHighCardinality
	**/

	public Integer getTargetHighCardinality(){
		return targetHighCardinality;
	}

	/**
	* Sets the value of targetHighCardinality attribue
	**/

	public void setTargetHighCardinality(Integer targetHighCardinality){
		this.targetHighCardinality = targetHighCardinality;
	}
	
		/**
	* Approaching or constituting a minimum; the lowest number of objects fixed as a goal or point of an association destination.	**/
	public Integer targetLowCardinality;
	/**
	* Retreives the value of targetLowCardinality attribute
	* @return targetLowCardinality
	**/

	public Integer getTargetLowCardinality(){
		return targetLowCardinality;
	}

	/**
	* Sets the value of targetLowCardinality attribue
	**/

	public void setTargetLowCardinality(Integer targetLowCardinality){
		this.targetLowCardinality = targetLowCardinality;
	}
	
		/**
	* The words or language units by which the class where the association ends is known.	**/
	public String targetRoleName;
	/**
	* Retreives the value of targetRoleName attribute
	* @return targetRoleName
	**/

	public String getTargetRoleName(){
		return targetRoleName;
	}

	/**
	* Sets the value of targetRoleName attribue
	**/

	public void setTargetRoleName(String targetRoleName){
		this.targetRoleName = targetRoleName;
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
	* An associated gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata object
	**/
			
	private UMLClassMetadata UMLClassMetadata;
	/**
	* Retreives the value of UMLClassMetadata attribue
	* @return UMLClassMetadata
	**/
	
	public UMLClassMetadata getUMLClassMetadata(){
		return UMLClassMetadata;
	}
	/**
	* Sets the value of UMLClassMetadata attribue
	**/

	public void setUMLClassMetadata(UMLClassMetadata UMLClassMetadata){
		this.UMLClassMetadata = UMLClassMetadata;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata object
	**/
			
	private UMLClassMetadata targetUMLClassMetadata;
	/**
	* Retreives the value of targetUMLClassMetadata attribue
	* @return targetUMLClassMetadata
	**/
	
	public UMLClassMetadata getTargetUMLClassMetadata(){
		return targetUMLClassMetadata;
	}
	/**
	* Sets the value of targetUMLClassMetadata attribue
	**/

	public void setTargetUMLClassMetadata(UMLClassMetadata targetUMLClassMetadata){
		this.targetUMLClassMetadata = targetUMLClassMetadata;
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
	* An associated gov.nih.nci.cadsr.umlproject.domain.UMLClassMetadata object
	**/
			
	private UMLClassMetadata sourceUMLClassMetadata;
	/**
	* Retreives the value of sourceUMLClassMetadata attribue
	* @return sourceUMLClassMetadata
	**/
	
	public UMLClassMetadata getSourceUMLClassMetadata(){
		return sourceUMLClassMetadata;
	}
	/**
	* Sets the value of sourceUMLClassMetadata attribue
	**/

	public void setSourceUMLClassMetadata(UMLClassMetadata sourceUMLClassMetadata){
		this.sourceUMLClassMetadata = sourceUMLClassMetadata;
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
		if(obj instanceof UMLAssociationMetadata) 
		{
			UMLAssociationMetadata c =(UMLAssociationMetadata)obj; 			 
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