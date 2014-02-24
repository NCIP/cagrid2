package gov.nih.nci.cadsr.domain;

import gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata;import gov.nih.nci.cadsr.umlproject.domain.UMLGeneralizationMetadata;
import java.io.Serializable;
	/**
	* A description of the affiliation between two occurrences of Object  Classes	**/
public class ObjectClassRelationship extends AdministeredComponent implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* the dimensionality of the array. is null if the relationship is not an array	**/
	public Integer dimensionality;
	/**
	* Retreives the value of dimensionality attribute
	* @return dimensionality
	**/

	public Integer getDimensionality(){
		return dimensionality;
	}

	/**
	* Sets the value of dimensionality attribue
	**/

	public void setDimensionality(Integer dimensionality){
		this.dimensionality = dimensionality;
	}
	
		/**
	* The words describing the navigability between two objects.	**/
	public String direction;
	/**
	* Retreives the value of direction attribute
	* @return direction
	**/

	public String getDirection(){
		return direction;
	}

	/**
	* Sets the value of direction attribue
	**/

	public void setDirection(String direction){
		this.direction = direction;
	}
	
		/**
	* an optional order when  the relationship to a single source are listed	**/
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
	* Is the relationship an array	**/
	public String isArray;
	/**
	* Retreives the value of isArray attribute
	* @return isArray
	**/

	public String getIsArray(){
		return isArray;
	}

	/**
	* Sets the value of isArray attribue
	**/

	public void setIsArray(String isArray){
		this.isArray = isArray;
	}
	
		/**
	* The words by which an item is known.	**/
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
	* Maximum number of instances of source class linked to one instance of the target class	**/
	public Integer sourceHighMultiplicity;
	/**
	* Retreives the value of sourceHighMultiplicity attribute
	* @return sourceHighMultiplicity
	**/

	public Integer getSourceHighMultiplicity(){
		return sourceHighMultiplicity;
	}

	/**
	* Sets the value of sourceHighMultiplicity attribue
	**/

	public void setSourceHighMultiplicity(Integer sourceHighMultiplicity){
		this.sourceHighMultiplicity = sourceHighMultiplicity;
	}
	
		/**
	* Minimum number of instances of source class linked to one instance of the target class	**/
	public Integer sourceLowMultiplicity;
	/**
	* Retreives the value of sourceLowMultiplicity attribute
	* @return sourceLowMultiplicity
	**/

	public Integer getSourceLowMultiplicity(){
		return sourceLowMultiplicity;
	}

	/**
	* Sets the value of sourceLowMultiplicity attribue
	**/

	public void setSourceLowMultiplicity(Integer sourceLowMultiplicity){
		this.sourceLowMultiplicity = sourceLowMultiplicity;
	}
	
		/**
	* Role of the source class in the association	**/
	public String sourceRole;
	/**
	* Retreives the value of sourceRole attribute
	* @return sourceRole
	**/

	public String getSourceRole(){
		return sourceRole;
	}

	/**
	* Sets the value of sourceRole attribue
	**/

	public void setSourceRole(String sourceRole){
		this.sourceRole = sourceRole;
	}
	
		/**
	* Maximum number of instances of target class linked to one instance of the source class	**/
	public Integer targetHighMultiplicity;
	/**
	* Retreives the value of targetHighMultiplicity attribute
	* @return targetHighMultiplicity
	**/

	public Integer getTargetHighMultiplicity(){
		return targetHighMultiplicity;
	}

	/**
	* Sets the value of targetHighMultiplicity attribue
	**/

	public void setTargetHighMultiplicity(Integer targetHighMultiplicity){
		this.targetHighMultiplicity = targetHighMultiplicity;
	}
	
		/**
	* Minimum number of instances of target class linked to one instance of the source class	**/
	public Integer targetLowMultiplicity;
	/**
	* Retreives the value of targetLowMultiplicity attribute
	* @return targetLowMultiplicity
	**/

	public Integer getTargetLowMultiplicity(){
		return targetLowMultiplicity;
	}

	/**
	* Sets the value of targetLowMultiplicity attribue
	**/

	public void setTargetLowMultiplicity(Integer targetLowMultiplicity){
		this.targetLowMultiplicity = targetLowMultiplicity;
	}
	
		/**
	* Role of the target class in the association	**/
	public String targetRole;
	/**
	* Retreives the value of targetRole attribute
	* @return targetRole
	**/

	public String getTargetRole(){
		return targetRole;
	}

	/**
	* Sets the value of targetRole attribue
	**/

	public void setTargetRole(String targetRole){
		this.targetRole = targetRole;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem object
	**/
			
	private AdministeredComponentClassSchemeItem targetObjectClassClassification;
	/**
	* Retreives the value of targetObjectClassClassification attribue
	* @return targetObjectClassClassification
	**/
	
	public AdministeredComponentClassSchemeItem getTargetObjectClassClassification(){
		return targetObjectClassClassification;
	}
	/**
	* Sets the value of targetObjectClassClassification attribue
	**/

	public void setTargetObjectClassClassification(AdministeredComponentClassSchemeItem targetObjectClassClassification){
		this.targetObjectClassClassification = targetObjectClassClassification;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.ConceptDerivationRule object
	**/
			
	private ConceptDerivationRule targetConceptDerivationRule;
	/**
	* Retreives the value of targetConceptDerivationRule attribue
	* @return targetConceptDerivationRule
	**/
	
	public ConceptDerivationRule getTargetConceptDerivationRule(){
		return targetConceptDerivationRule;
	}
	/**
	* Sets the value of targetConceptDerivationRule attribue
	**/

	public void setTargetConceptDerivationRule(ConceptDerivationRule targetConceptDerivationRule){
		this.targetConceptDerivationRule = targetConceptDerivationRule;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.ObjectClass object
	**/
			
	private ObjectClass targetObjectClass;
	/**
	* Retreives the value of targetObjectClass attribue
	* @return targetObjectClass
	**/
	
	public ObjectClass getTargetObjectClass(){
		return targetObjectClass;
	}
	/**
	* Sets the value of targetObjectClass attribue
	**/

	public void setTargetObjectClass(ObjectClass targetObjectClass){
		this.targetObjectClass = targetObjectClass;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.ConceptDerivationRule object
	**/
			
	private ConceptDerivationRule sourceConceptDerivationRule;
	/**
	* Retreives the value of sourceConceptDerivationRule attribue
	* @return sourceConceptDerivationRule
	**/
	
	public ConceptDerivationRule getSourceConceptDerivationRule(){
		return sourceConceptDerivationRule;
	}
	/**
	* Sets the value of sourceConceptDerivationRule attribue
	**/

	public void setSourceConceptDerivationRule(ConceptDerivationRule sourceConceptDerivationRule){
		this.sourceConceptDerivationRule = sourceConceptDerivationRule;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.ObjectClass object
	**/
			
	private ObjectClass sourceObjectClass;
	/**
	* Retreives the value of sourceObjectClass attribue
	* @return sourceObjectClass
	**/
	
	public ObjectClass getSourceObjectClass(){
		return sourceObjectClass;
	}
	/**
	* Sets the value of sourceObjectClass attribue
	**/

	public void setSourceObjectClass(ObjectClass sourceObjectClass){
		this.sourceObjectClass = sourceObjectClass;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.AdministeredComponentClassSchemeItem object
	**/
			
	private AdministeredComponentClassSchemeItem sourceObjectClassClassification;
	/**
	* Retreives the value of sourceObjectClassClassification attribue
	* @return sourceObjectClassClassification
	**/
	
	public AdministeredComponentClassSchemeItem getSourceObjectClassClassification(){
		return sourceObjectClassClassification;
	}
	/**
	* Sets the value of sourceObjectClassClassification attribue
	**/

	public void setSourceObjectClassClassification(AdministeredComponentClassSchemeItem sourceObjectClassClassification){
		this.sourceObjectClassClassification = sourceObjectClassClassification;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.ConceptDerivationRule object
	**/
			
	private ConceptDerivationRule conceptDerivationRule;
	/**
	* Retreives the value of conceptDerivationRule attribue
	* @return conceptDerivationRule
	**/
	
	public ConceptDerivationRule getConceptDerivationRule(){
		return conceptDerivationRule;
	}
	/**
	* Sets the value of conceptDerivationRule attribue
	**/

	public void setConceptDerivationRule(ConceptDerivationRule conceptDerivationRule){
		this.conceptDerivationRule = conceptDerivationRule;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ObjectClassRelationship) 
		{
			ObjectClassRelationship c =(ObjectClassRelationship)obj; 			 
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