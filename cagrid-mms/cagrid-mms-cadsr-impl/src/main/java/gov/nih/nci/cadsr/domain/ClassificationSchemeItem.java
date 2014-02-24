package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* A component of content in a Classification Scheme. This may be a node in a taxonomy or ontology or a term in a thesaurus, etc.	**/
public class ClassificationSchemeItem extends AdministeredComponent implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* The name of the particular type of the Item.	**/
	public String type;
	/**
	* Retreives the value of type attribute
	* @return type
	**/

	public String getType(){
		return type;
	}

	/**
	* Sets the value of type attribue
	**/

	public void setType(String type){
		this.type = type;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.ClassificationSchemeItemRelationship object's collection 
	**/
			
	private Collection<ClassificationSchemeItemRelationship> childClassificationSchemeItemRelationshipCollection;
	/**
	* Retreives the value of childClassificationSchemeItemRelationshipCollection attribue
	* @return childClassificationSchemeItemRelationshipCollection
	**/

	public Collection<ClassificationSchemeItemRelationship> getChildClassificationSchemeItemRelationshipCollection(){
		return childClassificationSchemeItemRelationshipCollection;
	}

	/**
	* Sets the value of childClassificationSchemeItemRelationshipCollection attribue
	**/

	public void setChildClassificationSchemeItemRelationshipCollection(Collection<ClassificationSchemeItemRelationship> childClassificationSchemeItemRelationshipCollection){
		this.childClassificationSchemeItemRelationshipCollection = childClassificationSchemeItemRelationshipCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ClassSchemeClassSchemeItem object's collection 
	**/
			
	private Collection<ClassSchemeClassSchemeItem> classSchemeClassSchemeItemCollection;
	/**
	* Retreives the value of classSchemeClassSchemeItemCollection attribue
	* @return classSchemeClassSchemeItemCollection
	**/

	public Collection<ClassSchemeClassSchemeItem> getClassSchemeClassSchemeItemCollection(){
		return classSchemeClassSchemeItemCollection;
	}

	/**
	* Sets the value of classSchemeClassSchemeItemCollection attribue
	**/

	public void setClassSchemeClassSchemeItemCollection(Collection<ClassSchemeClassSchemeItem> classSchemeClassSchemeItemCollection){
		this.classSchemeClassSchemeItemCollection = classSchemeClassSchemeItemCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ClassificationSchemeItemRelationship object's collection 
	**/
			
	private Collection<ClassificationSchemeItemRelationship> parentClassificationSchemeItemRelationshipCollection;
	/**
	* Retreives the value of parentClassificationSchemeItemRelationshipCollection attribue
	* @return parentClassificationSchemeItemRelationshipCollection
	**/

	public Collection<ClassificationSchemeItemRelationship> getParentClassificationSchemeItemRelationshipCollection(){
		return parentClassificationSchemeItemRelationshipCollection;
	}

	/**
	* Sets the value of parentClassificationSchemeItemRelationshipCollection attribue
	**/

	public void setParentClassificationSchemeItemRelationshipCollection(Collection<ClassificationSchemeItemRelationship> parentClassificationSchemeItemRelationshipCollection){
		this.parentClassificationSchemeItemRelationshipCollection = parentClassificationSchemeItemRelationshipCollection;
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
		if(obj instanceof ClassificationSchemeItem) 
		{
			ClassificationSchemeItem c =(ClassificationSchemeItem)obj; 			 
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