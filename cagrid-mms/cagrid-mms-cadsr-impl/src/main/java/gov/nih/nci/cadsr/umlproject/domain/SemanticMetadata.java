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