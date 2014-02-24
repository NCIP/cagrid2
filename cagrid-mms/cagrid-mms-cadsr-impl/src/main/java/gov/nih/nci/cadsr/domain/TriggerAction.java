package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* A class describing the action, the criteria and relationship between an item and an action.	**/
public class TriggerAction  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* Type of action to take on the target field when exiting the source field. Permitted values:	**/
	public String action;
	/**
	* Retreives the value of action attribute
	* @return action
	**/

	public String getAction(){
		return action;
	}

	/**
	* Sets the value of action attribue
	**/

	public void setAction(String action){
		this.action = action;
	}
	
		/**
	* Username of the person who created the record.	**/
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
	* The value for this attribute is the constant against which the assessment value is compared, to determine whether  to apply the action.	**/
	public String criterionValue;
	/**
	* Retreives the value of criterionValue attribute
	* @return criterionValue
	**/

	public String getCriterionValue(){
		return criterionValue;
	}

	/**
	* Sets the value of criterionValue attribue
	**/

	public void setCriterionValue(String criterionValue){
		this.criterionValue = criterionValue;
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
	* A value that is substituted into the target field if the trigger criterion is met.	**/
	public String forcedValue;
	/**
	* Retreives the value of forcedValue attribute
	* @return forcedValue
	**/

	public String getForcedValue(){
		return forcedValue;
	}

	/**
	* Sets the value of forcedValue attribue
	**/

	public void setForcedValue(String forcedValue){
		this.forcedValue = forcedValue;
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
	* Instruction for the triggered action	**/
	public String instruction;
	/**
	* Retreives the value of instruction attribute
	* @return instruction
	**/

	public String getInstruction(){
		return instruction;
	}

	/**
	* Sets the value of instruction attribue
	**/

	public void setInstruction(String instruction){
		this.instruction = instruction;
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
	* Describes the relationship between trigger and the action.	**/
	public String triggerRelationship;
	/**
	* Retreives the value of triggerRelationship attribute
	* @return triggerRelationship
	**/

	public String getTriggerRelationship(){
		return triggerRelationship;
	}

	/**
	* Sets the value of triggerRelationship attribue
	**/

	public void setTriggerRelationship(String triggerRelationship){
		this.triggerRelationship = triggerRelationship;
	}
	
	/**
	* An associated gov.nih.nci.cadsr.domain.FormElement object
	**/
			
	private FormElement sourceFormElement;
	/**
	* Retreives the value of sourceFormElement attribue
	* @return sourceFormElement
	**/
	
	public FormElement getSourceFormElement(){
		return sourceFormElement;
	}
	/**
	* Sets the value of sourceFormElement attribue
	**/

	public void setSourceFormElement(FormElement sourceFormElement){
		this.sourceFormElement = sourceFormElement;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.FormElement object
	**/
			
	private FormElement targetFormElement;
	/**
	* Retreives the value of targetFormElement attribue
	* @return targetFormElement
	**/
	
	public FormElement getTargetFormElement(){
		return targetFormElement;
	}
	/**
	* Sets the value of targetFormElement attribue
	**/

	public void setTargetFormElement(FormElement targetFormElement){
		this.targetFormElement = targetFormElement;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.Protocol object's collection 
	**/
			
	private Collection<Protocol> protocolCollection;
	/**
	* Retreives the value of protocolCollection attribue
	* @return protocolCollection
	**/

	public Collection<Protocol> getProtocolCollection(){
		return protocolCollection;
	}

	/**
	* Sets the value of protocolCollection attribue
	**/

	public void setProtocolCollection(Collection<Protocol> protocolCollection){
		this.protocolCollection = protocolCollection;
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
	* An associated gov.nih.nci.cadsr.domain.QuestionCondition object
	**/
			
	private QuestionCondition enforcedCondition;
	/**
	* Retreives the value of enforcedCondition attribue
	* @return enforcedCondition
	**/
	
	public QuestionCondition getEnforcedCondition(){
		return enforcedCondition;
	}
	/**
	* Sets the value of enforcedCondition attribue
	**/

	public void setEnforcedCondition(QuestionCondition enforcedCondition){
		this.enforcedCondition = enforcedCondition;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.QuestionCondition object
	**/
			
	private QuestionCondition questionCondition;
	/**
	* Retreives the value of questionCondition attribue
	* @return questionCondition
	**/
	
	public QuestionCondition getQuestionCondition(){
		return questionCondition;
	}
	/**
	* Sets the value of questionCondition attribue
	**/

	public void setQuestionCondition(QuestionCondition questionCondition){
		this.questionCondition = questionCondition;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof TriggerAction) 
		{
			TriggerAction c =(TriggerAction)obj; 			 
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