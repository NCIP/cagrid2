package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* The derivation rule between one or more concepts.	**/
public class ConceptDerivationRule  implements Serializable
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
	* The words by which the item is known.	**/
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
	* An associated gov.nih.nci.cadsr.domain.ObjectClassRelationship object's collection 
	**/
			
	private Collection<ObjectClassRelationship> targetRoleConcept;
	/**
	* Retreives the value of targetRoleConcept attribue
	* @return targetRoleConcept
	**/

	public Collection<ObjectClassRelationship> getTargetRoleConcept(){
		return targetRoleConcept;
	}

	/**
	* Sets the value of targetRoleConcept attribue
	**/

	public void setTargetRoleConcept(Collection<ObjectClassRelationship> targetRoleConcept){
		this.targetRoleConcept = targetRoleConcept;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ObjectClass object's collection 
	**/
			
	private Collection<ObjectClass> objectClassCollection;
	/**
	* Retreives the value of objectClassCollection attribue
	* @return objectClassCollection
	**/

	public Collection<ObjectClass> getObjectClassCollection(){
		return objectClassCollection;
	}

	/**
	* Sets the value of objectClassCollection attribue
	**/

	public void setObjectClassCollection(Collection<ObjectClass> objectClassCollection){
		this.objectClassCollection = objectClassCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.Representation object's collection 
	**/
			
	private Collection<Representation> representationCollection;
	/**
	* Retreives the value of representationCollection attribue
	* @return representationCollection
	**/

	public Collection<Representation> getRepresentationCollection(){
		return representationCollection;
	}

	/**
	* Sets the value of representationCollection attribue
	**/

	public void setRepresentationCollection(Collection<Representation> representationCollection){
		this.representationCollection = representationCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ClassificationScheme object's collection 
	**/
			
	private Collection<ClassificationScheme> classificationSchemeCollection;
	/**
	* Retreives the value of classificationSchemeCollection attribue
	* @return classificationSchemeCollection
	**/

	public Collection<ClassificationScheme> getClassificationSchemeCollection(){
		return classificationSchemeCollection;
	}

	/**
	* Sets the value of classificationSchemeCollection attribue
	**/

	public void setClassificationSchemeCollection(Collection<ClassificationScheme> classificationSchemeCollection){
		this.classificationSchemeCollection = classificationSchemeCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.DerivationType object
	**/
			
	private DerivationType derivationType;
	/**
	* Retreives the value of derivationType attribue
	* @return derivationType
	**/
	
	public DerivationType getDerivationType(){
		return derivationType;
	}
	/**
	* Sets the value of derivationType attribue
	**/

	public void setDerivationType(DerivationType derivationType){
		this.derivationType = derivationType;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.ObjectClassRelationship object's collection 
	**/
			
	private Collection<ObjectClassRelationship> objectClassRelationship;
	/**
	* Retreives the value of objectClassRelationship attribue
	* @return objectClassRelationship
	**/

	public Collection<ObjectClassRelationship> getObjectClassRelationship(){
		return objectClassRelationship;
	}

	/**
	* Sets the value of objectClassRelationship attribue
	**/

	public void setObjectClassRelationship(Collection<ObjectClassRelationship> objectClassRelationship){
		this.objectClassRelationship = objectClassRelationship;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ClassificationSchemeItem object's collection 
	**/
			
	private Collection<ClassificationSchemeItem> classificationSchemeItemCollection;
	/**
	* Retreives the value of classificationSchemeItemCollection attribue
	* @return classificationSchemeItemCollection
	**/

	public Collection<ClassificationSchemeItem> getClassificationSchemeItemCollection(){
		return classificationSchemeItemCollection;
	}

	/**
	* Sets the value of classificationSchemeItemCollection attribue
	**/

	public void setClassificationSchemeItemCollection(Collection<ClassificationSchemeItem> classificationSchemeItemCollection){
		this.classificationSchemeItemCollection = classificationSchemeItemCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.Property object's collection 
	**/
			
	private Collection<Property> propertyCollection;
	/**
	* Retreives the value of propertyCollection attribue
	* @return propertyCollection
	**/

	public Collection<Property> getPropertyCollection(){
		return propertyCollection;
	}

	/**
	* Sets the value of propertyCollection attribue
	**/

	public void setPropertyCollection(Collection<Property> propertyCollection){
		this.propertyCollection = propertyCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ComponentConcept object's collection 
	**/
			
	private Collection<ComponentConcept> componentConceptCollection;
	/**
	* Retreives the value of componentConceptCollection attribue
	* @return componentConceptCollection
	**/

	public Collection<ComponentConcept> getComponentConceptCollection(){
		return componentConceptCollection;
	}

	/**
	* Sets the value of componentConceptCollection attribue
	**/

	public void setComponentConceptCollection(Collection<ComponentConcept> componentConceptCollection){
		this.componentConceptCollection = componentConceptCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ValueDomain object's collection 
	**/
			
	private Collection<ValueDomain> valueDomainCollection;
	/**
	* Retreives the value of valueDomainCollection attribue
	* @return valueDomainCollection
	**/

	public Collection<ValueDomain> getValueDomainCollection(){
		return valueDomainCollection;
	}

	/**
	* Sets the value of valueDomainCollection attribue
	**/

	public void setValueDomainCollection(Collection<ValueDomain> valueDomainCollection){
		this.valueDomainCollection = valueDomainCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ObjectClassRelationship object's collection 
	**/
			
	private Collection<ObjectClassRelationship> sourceRoleConcept;
	/**
	* Retreives the value of sourceRoleConcept attribue
	* @return sourceRoleConcept
	**/

	public Collection<ObjectClassRelationship> getSourceRoleConcept(){
		return sourceRoleConcept;
	}

	/**
	* Sets the value of sourceRoleConcept attribue
	**/

	public void setSourceRoleConcept(Collection<ObjectClassRelationship> sourceRoleConcept){
		this.sourceRoleConcept = sourceRoleConcept;
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
	* An associated gov.nih.nci.cadsr.domain.ValueMeaning object's collection 
	**/
			
	private Collection<ValueMeaning> valueMeaningCollection;
	/**
	* Retreives the value of valueMeaningCollection attribue
	* @return valueMeaningCollection
	**/

	public Collection<ValueMeaning> getValueMeaningCollection(){
		return valueMeaningCollection;
	}

	/**
	* Sets the value of valueMeaningCollection attribue
	**/

	public void setValueMeaningCollection(Collection<ValueMeaning> valueMeaningCollection){
		this.valueMeaningCollection = valueMeaningCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ConceptDerivationRule) 
		{
			ConceptDerivationRule c =(ConceptDerivationRule)obj; 			 
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