package gov.nih.nci.cadsr.umlproject.domain;

import java.util.Collection;import gov.nih.nci.cadsr.domain.DataElement;
import java.io.Serializable;
	/**
	* caDSR properties of a UML Attribute in a UML Class.	**/
public class UMLAttributeMetadata  implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* Text pertaining to the UML attribute.	**/
	public String description;
	/**
	* Retreives the value of description attribute
	* @return description
	**/

	public String getDescription(){
		return description;
	}

	/**
	* Sets the value of description attribue
	**/

	public void setDescription(String description){
		this.description = description;
	}
	
		/**
	* The full path of the uml attribute within the package.	**/
	public String fullyQualifiedName;
	/**
	* Retreives the value of fullyQualifiedName attribute
	* @return fullyQualifiedName
	**/

	public String getFullyQualifiedName(){
		return fullyQualifiedName;
	}

	/**
	* Sets the value of fullyQualifiedName attribue
	**/

	public void setFullyQualifiedName(String fullyQualifiedName){
		this.fullyQualifiedName = fullyQualifiedName;
	}
	
		/**
	* Used to represent the XML location Reference in GME	**/
	private String gmeXMLLocReference;
	/**
	* Retreives the value of gmeXMLLocReference attribute
	* @return gmeXMLLocReference
	**/

	public String getGmeXMLLocReference(){
		return gmeXMLLocReference;
	}

	/**
	* Sets the value of gmeXMLLocReference attribue
	**/

	public void setGmeXMLLocReference(String gmeXMLLocReference){
		this.gmeXMLLocReference = gmeXMLLocReference;
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
	* The words by which the attribute is known in the project.	**/
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
	* An associated gov.nih.nci.cadsr.domain.DataElement object
	**/
			
	private DataElement dataElement;
	/**
	* Retreives the value of dataElement attribue
	* @return dataElement
	**/
	
	public DataElement getDataElement(){
		return dataElement;
	}
	/**
	* Sets the value of dataElement attribue
	**/

	public void setDataElement(DataElement dataElement){
		this.dataElement = dataElement;
	}
			
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof UMLAttributeMetadata) 
		{
			UMLAttributeMetadata c =(UMLAttributeMetadata)obj; 			 
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