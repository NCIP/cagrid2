package gov.nih.nci.cadsr.umlproject.domain;

import gov.nih.nci.cadsr.domain.Concept;
import java.io.Serializable;
	/**
	* The group of concept codes in a partivular order that are the unambiguous signature of each of the associated with a particular element in the UML Model.	**/
public class SemanticMetadata  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* The evs concept identifier.	**/
	public String conceptCode;
	/**
	* Retreives the value of conceptCode attribute
	* @return conceptCode
	**/

	public String getConceptCode(){
		return conceptCode;
	}

	/**
	* Sets the value of conceptCode attribue
	**/

	public void setConceptCode(String conceptCode){
		this.conceptCode = conceptCode;
	}
	
		/**
	* The preferred definition for the semantic concept.	**/
	public String conceptDefinition;
	/**
	* Retreives the value of conceptDefinition attribute
	* @return conceptDefinition
	**/

	public String getConceptDefinition(){
		return conceptDefinition;
	}

	/**
	* Sets the value of conceptDefinition attribue
	**/

	public void setConceptDefinition(String conceptDefinition){
		this.conceptDefinition = conceptDefinition;
	}
	
		/**
	* the preferred name for the concept.	**/
	public String conceptName;
	/**
	* Retreives the value of conceptName attribute
	* @return conceptName
	**/

	public String getConceptName(){
		return conceptName;
	}

	/**
	* Sets the value of conceptName attribue
	**/

	public void setConceptName(String conceptName){
		this.conceptName = conceptName;
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
	* A flag that indicates that the concept is the one of the most significance in the semantic signature.	**/
	public Boolean isPrimaryConcept;
	/**
	* Retreives the value of isPrimaryConcept attribute
	* @return isPrimaryConcept
	**/

	public Boolean getIsPrimaryConcept(){
		return isPrimaryConcept;
	}

	/**
	* Sets the value of isPrimaryConcept attribue
	**/

	public void setIsPrimaryConcept(Boolean isPrimaryConcept){
		this.isPrimaryConcept = isPrimaryConcept;
	}
	
		/**
	* The relative position of the concept in the semantic signature for the item.	**/
	public Integer order;
	/**
	* Retreives the value of order attribute
	* @return order
	**/

	public Integer getOrder(){
		return order;
	}

	/**
	* Sets the value of order attribue
	**/

	public void setOrder(Integer order){
		this.order = order;
	}
	
		/**
	* The relative position of a group of concepts in the overall ordeing of the concepts in teh semantic sigature.  More than one concept can be grouped together with and/or logic to form a single level of qualification fon for the primary concept.	**/
	public Integer orderLevel;
	/**
	* Retreives the value of orderLevel attribute
	* @return orderLevel
	**/

	public Integer getOrderLevel(){
		return orderLevel;
	}

	/**
	* Sets the value of orderLevel attribue
	**/

	public void setOrderLevel(Integer orderLevel){
		this.orderLevel = orderLevel;
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
	* An associated gov.nih.nci.cadsr.domain.Concept object
	**/
			
	private Concept concept;
	/**
	* Retreives the value of concept attribue
	* @return concept
	**/
	
	public Concept getConcept(){
		return concept;
	}
	/**
	* Sets the value of concept attribue
	**/

	public void setConcept(Concept concept){
		this.concept = concept;
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
	* An associated gov.nih.nci.cadsr.umlproject.domain.UMLAttributeMetadata object
	**/
			
	private UMLAttributeMetadata UMLAttributeMetadata;
	/**
	* Retreives the value of UMLAttributeMetadata attribue
	* @return UMLAttributeMetadata
	**/
	
	public UMLAttributeMetadata getUMLAttributeMetadata(){
		return UMLAttributeMetadata;
	}
	/**
	* Sets the value of UMLAttributeMetadata attribue
	**/

	public void setUMLAttributeMetadata(UMLAttributeMetadata UMLAttributeMetadata){
		this.UMLAttributeMetadata = UMLAttributeMetadata;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.umlproject.domain.TypeEnumerationMetadata object
	**/
			
	private TypeEnumerationMetadata typeEnumerationMetadata;
	/**
	* Retreives the value of typeEnumerationMetadata attribue
	* @return typeEnumerationMetadata
	**/
	
	public TypeEnumerationMetadata getTypeEnumerationMetadata(){
		return typeEnumerationMetadata;
	}
	/**
	* Sets the value of typeEnumerationMetadata attribue
	**/

	public void setTypeEnumerationMetadata(TypeEnumerationMetadata typeEnumerationMetadata){
		this.typeEnumerationMetadata = typeEnumerationMetadata;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.umlproject.domain.UMLAssociationMetadata object
	**/
			
	private UMLAssociationMetadata UMLAssociationMetadata;
	/**
	* Retreives the value of UMLAssociationMetadata attribue
	* @return UMLAssociationMetadata
	**/
	
	public UMLAssociationMetadata getUMLAssociationMetadata(){
		return UMLAssociationMetadata;
	}
	/**
	* Sets the value of UMLAssociationMetadata attribue
	**/

	public void setUMLAssociationMetadata(UMLAssociationMetadata UMLAssociationMetadata){
		this.UMLAssociationMetadata = UMLAssociationMetadata;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.umlproject.domain.AttributeTypeMetadata object
	**/
			
	private AttributeTypeMetadata attributeTypeMetadata;
	/**
	* Retreives the value of attributeTypeMetadata attribue
	* @return attributeTypeMetadata
	**/
	
	public AttributeTypeMetadata getAttributeTypeMetadata(){
		return attributeTypeMetadata;
	}
	/**
	* Sets the value of attributeTypeMetadata attribue
	**/

	public void setAttributeTypeMetadata(AttributeTypeMetadata attributeTypeMetadata){
		this.attributeTypeMetadata = attributeTypeMetadata;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof SemanticMetadata) 
		{
			SemanticMetadata c =(SemanticMetadata)obj; 			 
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