package gov.nih.nci.cadsr.domain;

import gov.nih.nci.cadsr.umlproject.domain.Project;import java.util.Collection;
import java.io.Serializable;
	/**
	* The descriptive information for an arrangement or division of objects into groups based on characteristics, which the objects have in common. e.g., origin, composition, structure, application, function, etc.  Adds information not easily included in defini	**/
public class ClassificationScheme extends AdministeredComponent implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* An indicator that specifies if the classification scheme is A= alphanumeric; C=character; N=numeric.	**/
	public String labelTypeFlag;
	/**
	* Retreives the value of labelTypeFlag attribute
	* @return labelTypeFlag
	**/

	public String getLabelTypeFlag(){
		return labelTypeFlag;
	}

	/**
	* Sets the value of labelTypeFlag attribue
	**/

	public void setLabelTypeFlag(String labelTypeFlag){
		this.labelTypeFlag = labelTypeFlag;
	}
	
		/**
	* The words by which the item is known.	**/
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
	* An associated gov.nih.nci.cadsr.umlproject.domain.Project object's collection 
	**/
			
	private Collection<Project> projectCollection;
	/**
	* Retreives the value of projectCollection attribue
	* @return projectCollection
	**/

	public Collection<Project> getProjectCollection(){
		return projectCollection;
	}

	/**
	* Sets the value of projectCollection attribue
	**/

	public void setProjectCollection(Collection<Project> projectCollection){
		this.projectCollection = projectCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ClassificationSchemeRelationship object's collection 
	**/
			
	private Collection<ClassificationSchemeRelationship> parentClassificationSchemeRelationshipCollection;
	/**
	* Retreives the value of parentClassificationSchemeRelationshipCollection attribue
	* @return parentClassificationSchemeRelationshipCollection
	**/

	public Collection<ClassificationSchemeRelationship> getParentClassificationSchemeRelationshipCollection(){
		return parentClassificationSchemeRelationshipCollection;
	}

	/**
	* Sets the value of parentClassificationSchemeRelationshipCollection attribue
	**/

	public void setParentClassificationSchemeRelationshipCollection(Collection<ClassificationSchemeRelationship> parentClassificationSchemeRelationshipCollection){
		this.parentClassificationSchemeRelationshipCollection = parentClassificationSchemeRelationshipCollection;
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
	* An associated gov.nih.nci.cadsr.domain.ClassificationScheme object's collection 
	**/
			
	private Collection<ClassificationScheme> childClassificationScheme;
	/**
	* Retreives the value of childClassificationScheme attribue
	* @return childClassificationScheme
	**/

	public Collection<ClassificationScheme> getChildClassificationScheme(){
		return childClassificationScheme;
	}

	/**
	* Sets the value of childClassificationScheme attribue
	**/

	public void setChildClassificationScheme(Collection<ClassificationScheme> childClassificationScheme){
		this.childClassificationScheme = childClassificationScheme;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ClassificationSchemeRelationship object's collection 
	**/
			
	private Collection<ClassificationSchemeRelationship> childClassificationSchemeRelationshipCollection;
	/**
	* Retreives the value of childClassificationSchemeRelationshipCollection attribue
	* @return childClassificationSchemeRelationshipCollection
	**/

	public Collection<ClassificationSchemeRelationship> getChildClassificationSchemeRelationshipCollection(){
		return childClassificationSchemeRelationshipCollection;
	}

	/**
	* Sets the value of childClassificationSchemeRelationshipCollection attribue
	**/

	public void setChildClassificationSchemeRelationshipCollection(Collection<ClassificationSchemeRelationship> childClassificationSchemeRelationshipCollection){
		this.childClassificationSchemeRelationshipCollection = childClassificationSchemeRelationshipCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ClassificationScheme) 
		{
			ClassificationScheme c =(ClassificationScheme)obj; 			 
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