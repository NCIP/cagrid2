package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* The information pertaining to definitions for an Administered Item  in a specific Context.	**/
public class Definition  implements Serializable
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
	* The name of the language in which the item is represented. 	**/
	public String languageName;
	/**
	* Retreives the value of languageName attribute
	* @return languageName
	**/

	public String getLanguageName(){
		return languageName;
	}

	/**
	* Sets the value of languageName attribue
	**/

	public void setLanguageName(String languageName){
		this.languageName = languageName;
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
	* The words describing the meaning.	**/
	public String text;
	/**
	* Retreives the value of text attribute
	* @return text
	**/

	public String getText(){
		return text;
	}

	/**
	* Sets the value of text attribue
	**/

	public void setText(String text){
		this.text = text;
	}
	
		/**
	* The particular type of definition.	**/
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
	* An associated gov.nih.nci.cadsr.domain.Context object
	**/
			
	private Context context;
	/**
	* Retreives the value of context attribue
	* @return context
	**/
	
	public Context getContext(){
		return context;
	}
	/**
	* Sets the value of context attribue
	**/

	public void setContext(Context context){
		this.context = context;
	}
			
	/**
	* An associated gov.nih.nci.cadsr.domain.DefinitionClassSchemeItem object's collection 
	**/
			
	private Collection<DefinitionClassSchemeItem> definitionClassSchemeItemCollection;
	/**
	* Retreives the value of definitionClassSchemeItemCollection attribue
	* @return definitionClassSchemeItemCollection
	**/

	public Collection<DefinitionClassSchemeItem> getDefinitionClassSchemeItemCollection(){
		return definitionClassSchemeItemCollection;
	}

	/**
	* Sets the value of definitionClassSchemeItemCollection attribue
	**/

	public void setDefinitionClassSchemeItemCollection(Collection<DefinitionClassSchemeItem> definitionClassSchemeItemCollection){
		this.definitionClassSchemeItemCollection = definitionClassSchemeItemCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Definition) 
		{
			Definition c =(Definition)obj; 			 
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