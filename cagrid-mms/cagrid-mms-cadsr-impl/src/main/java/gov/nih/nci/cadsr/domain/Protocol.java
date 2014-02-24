package gov.nih.nci.cadsr.domain;

import java.util.Collection;
import java.io.Serializable;
	/**
	* Identification of a Clinical Trial Protocol document and its collection of Case Report Forms (CRFs).   Note: Protocols will be uniquely identified within each of the 3 areas of caCORE - caBIO, SPORES and caDSR-  using  the following three attributes: Prot	**/
public class Protocol extends AdministeredComponent implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;

	
		/**
	* The person or authoritative body who approved the Protocol Forms Set.	**/
	public String approvedBy;
	/**
	* Retreives the value of approvedBy attribute
	* @return approvedBy
	**/

	public String getApprovedBy(){
		return approvedBy;
	}

	/**
	* Sets the value of approvedBy attribue
	**/

	public void setApprovedBy(String approvedBy){
		this.approvedBy = approvedBy;
	}
	
		/**
	* The particular day, month and year this item was approved. 	**/
	public java.util.Date approvedDate;
	/**
	* Retreives the value of approvedDate attribute
	* @return approvedDate
	**/

	public java.util.Date getApprovedDate(){
		return approvedDate;
	}

	/**
	* Sets the value of approvedDate attribue
	**/

	public void setApprovedDate(java.util.Date approvedDate){
		this.approvedDate = approvedDate;
	}
	
		/**
	* The particular alteration or modification iteration for this item.	**/
	public String changeNumber;
	/**
	* Retreives the value of changeNumber attribute
	* @return changeNumber
	**/

	public String getChangeNumber(){
		return changeNumber;
	}

	/**
	* Sets the value of changeNumber attribue
	**/

	public void setChangeNumber(String changeNumber){
		this.changeNumber = changeNumber;
	}
	
		/**
	* The particular type of change for this item.	**/
	public String changeType;
	/**
	* Retreives the value of changeType attribute
	* @return changeType
	**/

	public String getChangeType(){
		return changeType;
	}

	/**
	* Sets the value of changeType attribue
	**/

	public void setChangeType(String changeType){
		this.changeType = changeType;
	}
	
		/**
	* The organization in charge of this Protocol.	**/
	public String leadOrganizationName;
	/**
	* Retreives the value of leadOrganizationName attribute
	* @return leadOrganizationName
	**/

	public String getLeadOrganizationName(){
		return leadOrganizationName;
	}

	/**
	* Sets the value of leadOrganizationName attribue
	**/

	public void setLeadOrganizationName(String leadOrganizationName){
		this.leadOrganizationName = leadOrganizationName;
	}
	
		/**
	* The distinguishable part of the series of events in this protocol.	**/
	public String phase;
	/**
	* Retreives the value of phase attribute
	* @return phase
	**/

	public String getPhase(){
		return phase;
	}

	/**
	* Sets the value of phase attribue
	**/

	public void setPhase(String phase){
		this.phase = phase;
	}
	
		/**
	* The set of characters used to identify this Protocol.	**/
	public String protocolID;
	/**
	* Retreives the value of protocolID attribute
	* @return protocolID
	**/

	public String getProtocolID(){
		return protocolID;
	}

	/**
	* Sets the value of protocolID attribue
	**/

	public void setProtocolID(String protocolID){
		this.protocolID = protocolID;
	}
	
		/**
	* The username of the person who reviewed this protocol forms set.	**/
	public String reviewedBy;
	/**
	* Retreives the value of reviewedBy attribute
	* @return reviewedBy
	**/

	public String getReviewedBy(){
		return reviewedBy;
	}

	/**
	* Sets the value of reviewedBy attribue
	**/

	public void setReviewedBy(String reviewedBy){
		this.reviewedBy = reviewedBy;
	}
	
		/**
	* The particular day, month and year the protocol forms set was reviewed.	**/
	public java.util.Date reviewedDate;
	/**
	* Retreives the value of reviewedDate attribute
	* @return reviewedDate
	**/

	public java.util.Date getReviewedDate(){
		return reviewedDate;
	}

	/**
	* Sets the value of reviewedDate attribue
	**/

	public void setReviewedDate(java.util.Date reviewedDate){
		this.reviewedDate = reviewedDate;
	}
	
		/**
	* The particular type of protocol form set.	**/
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
	* An associated gov.nih.nci.cadsr.domain.Form object's collection 
	**/
			
	private Collection<Form> formCollection;
	/**
	* Retreives the value of formCollection attribue
	* @return formCollection
	**/

	public Collection<Form> getFormCollection(){
		return formCollection;
	}

	/**
	* Sets the value of formCollection attribue
	**/

	public void setFormCollection(Collection<Form> formCollection){
		this.formCollection = formCollection;
	}
		
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof Protocol) 
		{
			Protocol c =(Protocol)obj; 			 
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