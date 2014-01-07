package gov.nih.nci.cadsr.domain;

import java.util.Collection;import gov.nih.nci.cadsr.umlproject.domain.AttributeTypeMetadata;
import java.io.Serializable;
	/**
	* A set of attributes describing the representation for a data element.	**/
public class ValueDomain extends AdministeredComponent implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* The words for which the character set is known. 	**/
	public String characterSetName;
	/**
	* Retreives the value of characterSetName attribute
	* @return characterSetName
	**/

	public String getCharacterSetName(){
		return characterSetName;
	}

	/**
	* Sets the value of characterSetName attribue
	**/

	public void setCharacterSetName(String characterSetName){
		this.characterSetName = characterSetName;
	}
	
		/**
	* Specifying information to further define the Datatype. (ISO 11179 ED 3)	**/
	public String datatypeAnnotation;
	/**
	* Retreives the value of datatypeAnnotation attribute
	* @return datatypeAnnotation
	**/

	public String getDatatypeAnnotation(){
		return datatypeAnnotation;
	}

	/**
	* Sets the value of datatypeAnnotation attribue
	**/

	public void setDatatypeAnnotation(String datatypeAnnotation){
		this.datatypeAnnotation = datatypeAnnotation;
	}
	
		/**
	* Descriptive information to futher clarify the datatype (ISO 11179 Ed 3)	**/
	public String datatypeDescription;
	/**
	* Retreives the value of datatypeDescription attribute
	* @return datatypeDescription
	**/

	public String getDatatypeDescription(){
		return datatypeDescription;
	}

	/**
	* Sets the value of datatypeDescription attribue
	**/

	public void setDatatypeDescription(String datatypeDescription){
		this.datatypeDescription = datatypeDescription;
	}
	
		/**
	* A boolean flag to indicate if the datatype is compatible with the caCORE SDK codegen.	**/
	public String datatypeIsCodegenCompatible;
	/**
	* Retreives the value of datatypeIsCodegenCompatible attribute
	* @return datatypeIsCodegenCompatible
	**/

	public String getDatatypeIsCodegenCompatible(){
		return datatypeIsCodegenCompatible;
	}

	/**
	* Sets the value of datatypeIsCodegenCompatible attribue
	**/

	public void setDatatypeIsCodegenCompatible(String datatypeIsCodegenCompatible){
		this.datatypeIsCodegenCompatible = datatypeIsCodegenCompatible;
	}
	
		/**
	* The words by which the datatype for an item is known.	**/
	public String datatypeName;
	/**
	* Retreives the value of datatypeName attribute
	* @return datatypeName
	**/

	public String getDatatypeName(){
		return datatypeName;
	}

	/**
	* Sets the value of datatypeName attribue
	**/

	public void setDatatypeName(String datatypeName){
		this.datatypeName = datatypeName;
	}
	
		/**
	* a reference identifying the source of the Datatype specification	**/
	public String datatypeSchemeReference;
	/**
	* Retreives the value of datatypeSchemeReference attribute
	* @return datatypeSchemeReference
	**/

	public String getDatatypeSchemeReference(){
		return datatypeSchemeReference;
	}

	/**
	* Sets the value of datatypeSchemeReference attribue
	**/

	public void setDatatypeSchemeReference(String datatypeSchemeReference){
		this.datatypeSchemeReference = datatypeSchemeReference;
	}
	
		/**
	* An indication of the specific number of digits to be expected to the right of the decimal point in a line of numbers.	**/
	public Integer decimalPlace;
	/**
	* Retreives the value of decimalPlace attribute
	* @return decimalPlace
	**/

	public Integer getDecimalPlace(){
		return decimalPlace;
	}

	/**
	* Sets the value of decimalPlace attribue
	**/

	public void setDecimalPlace(Integer decimalPlace){
		this.decimalPlace = decimalPlace;
	}
	
		/**
	* The words by which a template for the structure of the presentation of the Value(s) is known.  (ISO 11179)	**/
	public String formatName;
	/**
	* Retreives the value of formatName attribute
	* @return formatName
	**/

	public String getFormatName(){
		return formatName;
	}

	/**
	* Sets the value of formatName attribue
	**/

	public void setFormatName(String formatName){
		this.formatName = formatName;
	}
	
		/**
	* The upper limit of a range	**/
	public String highValueNumber;
	/**
	* Retreives the value of highValueNumber attribute
	* @return highValueNumber
	**/

	public String getHighValueNumber(){
		return highValueNumber;
	}

	/**
	* Sets the value of highValueNumber attribue
	**/

	public void setHighValueNumber(String highValueNumber){
		this.highValueNumber = highValueNumber;
	}
	
		/**
	* The lower limit of a range	**/
	public String lowValueNumber;
	/**
	* Retreives the value of lowValueNumber attribute
	* @return lowValueNumber
	**/

	public String getLowValueNumber(){
		return lowValueNumber;
	}

	/**
	* Sets the value of lowValueNumber attribue
	**/

	public void setLowValueNumber(String lowValueNumber){
		this.lowValueNumber = lowValueNumber;
	}
	
		/**
	* The maximum number of characters to represent the Data Element value. (ISO 11179)	**/
	public Integer maximumLengthNumber;
	/**
	* Retreives the value of maximumLengthNumber attribute
	* @return maximumLengthNumber
	**/

	public Integer getMaximumLengthNumber(){
		return maximumLengthNumber;
	}

	/**
	* Sets the value of maximumLengthNumber attribue
	**/

	public void setMaximumLengthNumber(Integer maximumLengthNumber){
		this.maximumLengthNumber = maximumLengthNumber;
	}
	
		/**
	* The minimum number of characters to represent the Data Element value. 	**/
	public Integer minimumLengthNumber;
	/**
	* Retreives the value of minimumLengthNumber attribute
	* @return minimumLengthNumber
	**/

	public Integer getMinimumLengthNumber(){
		return minimumLengthNumber;
	}

	/**
	* Sets the value of minimumLengthNumber attribue
	**/

	public void setMinimumLengthNumber(Integer minimumLengthNumber){
		this.minimumLengthNumber = minimumLengthNumber;
	}
	
		/**
	* The name of the actual units in which the associated values are measured. (ISO 11179)	**/
	public String UOMName;
	/**
	* Retreives the value of UOMName attribute
	* @return UOMName
	**/

	public String getUOMName(){
		return UOMName;
	}

	/**
	* Sets the value of UOMName attribue
	**/

	public void setUOMName(String UOMName){
		this.UOMName = UOMName;
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
	* An associated gov.nih.nci.cadsr.domain.ValueDomainRelationship object's collection 
	**/
			
	private Collection<ValueDomainRelationship> parentValueDomainRelationshipCollection;
	/**
	* Retreives the value of parentValueDomainRelationshipCollection attribue
	* @return parentValueDomainRelationshipCollection
	**/

	public Collection<ValueDomainRelationship> getParentValueDomainRelationshipCollection(){
		return parentValueDomainRelationshipCollection;
	}

	/**
	* Sets the value of parentValueDomainRelationshipCollection attribue
	**/

	public void setParentValueDomainRelationshipCollection(Collection<ValueDomainRelationship> parentValueDomainRelationshipCollection){
		this.parentValueDomainRelationshipCollection = parentValueDomainRelationshipCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ValueDomainRelationship object's collection 
	**/
			
	private Collection<ValueDomainRelationship> childValueDomainRelationshipCollection;
	/**
	* Retreives the value of childValueDomainRelationshipCollection attribue
	* @return childValueDomainRelationshipCollection
	**/

	public Collection<ValueDomainRelationship> getChildValueDomainRelationshipCollection(){
		return childValueDomainRelationshipCollection;
	}

	/**
	* Sets the value of childValueDomainRelationshipCollection attribue
	**/

	public void setChildValueDomainRelationshipCollection(Collection<ValueDomainRelationship> childValueDomainRelationshipCollection){
		this.childValueDomainRelationshipCollection = childValueDomainRelationshipCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.Representation object
	**/
			
	private Representation represention;
	/**
	* Retreives the value of represention attribue
	* @return represention
	**/
	
	public Representation getRepresention(){
		return represention;
	}
	/**
	* Sets the value of represention attribue
	**/

	public void setRepresention(Representation represention){
		this.represention = represention;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.Question object's collection 
	**/
			
	private Collection<Question> questionCollection;
	/**
	* Retreives the value of questionCollection attribue
	* @return questionCollection
	**/

	public Collection<Question> getQuestionCollection(){
		return questionCollection;
	}

	/**
	* Sets the value of questionCollection attribue
	**/

	public void setQuestionCollection(Collection<Question> questionCollection){
		this.questionCollection = questionCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.DataElement object's collection 
	**/
			
	private Collection<DataElement> dataElementCollection;
	/**
	* Retreives the value of dataElementCollection attribue
	* @return dataElementCollection
	**/

	public Collection<DataElement> getDataElementCollection(){
		return dataElementCollection;
	}

	/**
	* Sets the value of dataElementCollection attribue
	**/

	public void setDataElementCollection(Collection<DataElement> dataElementCollection){
		this.dataElementCollection = dataElementCollection;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.umlproject.domain.AttributeTypeMetadata object's collection 
	**/
			
	private Collection<AttributeTypeMetadata> attributeTypeMetadata;
	/**
	* Retreives the value of attributeTypeMetadata attribue
	* @return attributeTypeMetadata
	**/

	public Collection<AttributeTypeMetadata> getAttributeTypeMetadata(){
		return attributeTypeMetadata;
	}

	/**
	* Sets the value of attributeTypeMetadata attribue
	**/

	public void setAttributeTypeMetadata(Collection<AttributeTypeMetadata> attributeTypeMetadata){
		this.attributeTypeMetadata = attributeTypeMetadata;
	}
		
	/**
	* An associated gov.nih.nci.cadsr.domain.ConceptualDomain object
	**/
			
	private ConceptualDomain conceptualDomain;
	/**
	* Retreives the value of conceptualDomain attribue
	* @return conceptualDomain
	**/
	
	public ConceptualDomain getConceptualDomain(){
		return conceptualDomain;
	}
	/**
	* Sets the value of conceptualDomain attribue
	**/

	public void setConceptualDomain(ConceptualDomain conceptualDomain){
		this.conceptualDomain = conceptualDomain;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ValueDomain) 
		{
			ValueDomain c =(ValueDomain)obj; 			 
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