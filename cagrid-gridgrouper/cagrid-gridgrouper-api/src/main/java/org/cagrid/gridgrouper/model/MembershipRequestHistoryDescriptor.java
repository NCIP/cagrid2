
package org.cagrid.gridgrouper.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MembershipRequestHistoryDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MembershipRequestHistoryDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipRequestStatus"/>
 *         &lt;element name="Reviewer" type="{http://cagrid.nci.nih.gov/1/GridGrouper}MemberDescriptor"/>
 *         &lt;element name="UpdateDate" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PublicNote" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AdminNote" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MembershipRequestHistoryDescriptor", propOrder = {
    "uuid",
    "status",
    "reviewer",
    "updateDate",
    "publicNote",
    "adminNote"
})
public class MembershipRequestHistoryDescriptor
    implements Serializable
{

    @XmlElement(name = "UUID", required = true)
    protected String uuid;
    @XmlElement(name = "Status", required = true)
    protected MembershipRequestStatus status;
    @XmlElement(name = "Reviewer", required = true)
    protected MemberDescriptor reviewer;
    @XmlElement(name = "UpdateDate")
    protected long updateDate;
    @XmlElement(name = "PublicNote", required = true)
    protected String publicNote;
    @XmlElement(name = "AdminNote", required = true)
    protected String adminNote;

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUUID() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUUID(String value) {
        this.uuid = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link MembershipRequestStatus }
     *     
     */
    public MembershipRequestStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link MembershipRequestStatus }
     *     
     */
    public void setStatus(MembershipRequestStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the reviewer property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.model.MemberDescriptor }
     *     
     */
    public MemberDescriptor getReviewer() {
        return reviewer;
    }

    /**
     * Sets the value of the reviewer property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.model.MemberDescriptor }
     *     
     */
    public void setReviewer(MemberDescriptor value) {
        this.reviewer = value;
    }

    /**
     * Gets the value of the updateDate property.
     * 
     */
    public long getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets the value of the updateDate property.
     * 
     */
    public void setUpdateDate(long value) {
        this.updateDate = value;
    }

    /**
     * Gets the value of the publicNote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublicNote() {
        return publicNote;
    }

    /**
     * Sets the value of the publicNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicNote(String value) {
        this.publicNote = value;
    }

    /**
     * Gets the value of the adminNote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminNote() {
        return adminNote;
    }

    /**
     * Sets the value of the adminNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminNote(String value) {
        this.adminNote = value;
    }

}
