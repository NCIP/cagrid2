
package org.cagrid.gridgrouper.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MembershipRequestDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MembershipRequestDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Group" type="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupDescriptor"/>
 *         &lt;element name="RequestorId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RequestTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Status" type="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipRequestStatus"/>
 *         &lt;element name="Reviewer" type="{http://cagrid.nci.nih.gov/1/GridGrouper}MemberDescriptor"/>
 *         &lt;element name="ReviewTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PublicNote" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AdminNote" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="history" type="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipRequestHistoryDescriptor" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MembershipRequestDescriptor", propOrder = {
    "uuid",
    "group",
    "requestorId",
    "requestTime",
    "status",
    "reviewer",
    "reviewTime",
    "publicNote",
    "adminNote",
    "history"
})
public class MembershipRequestDescriptor
    implements Serializable
{

    @XmlElement(name = "UUID", required = true)
    protected String uuid;
    @XmlElement(name = "Group", required = true)
    protected GroupDescriptor group;
    @XmlElement(name = "RequestorId", required = true)
    protected String requestorId;
    @XmlElement(name = "RequestTime")
    protected long requestTime;
    @XmlElement(name = "Status", required = true)
    protected MembershipRequestStatus status;
    @XmlElement(name = "Reviewer", required = true)
    protected MemberDescriptor reviewer;
    @XmlElement(name = "ReviewTime")
    protected long reviewTime;
    @XmlElement(name = "PublicNote", required = true)
    protected String publicNote;
    @XmlElement(name = "AdminNote", required = true)
    protected String adminNote;
    @XmlElement(required = true)
    protected List<MembershipRequestHistoryDescriptor> history;

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
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.model.GroupDescriptor }
     *     
     */
    public GroupDescriptor getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.model.GroupDescriptor }
     *     
     */
    public void setGroup(GroupDescriptor value) {
        this.group = value;
    }

    /**
     * Gets the value of the requestorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestorId() {
        return requestorId;
    }

    /**
     * Sets the value of the requestorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestorId(String value) {
        this.requestorId = value;
    }

    /**
     * Gets the value of the requestTime property.
     * 
     */
    public long getRequestTime() {
        return requestTime;
    }

    /**
     * Sets the value of the requestTime property.
     * 
     */
    public void setRequestTime(long value) {
        this.requestTime = value;
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
     * Gets the value of the reviewTime property.
     * 
     */
    public long getReviewTime() {
        return reviewTime;
    }

    /**
     * Sets the value of the reviewTime property.
     * 
     */
    public void setReviewTime(long value) {
        this.reviewTime = value;
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

    /**
     * Gets the value of the history property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the history property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHistory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MembershipRequestHistoryDescriptor }
     * 
     * 
     */
    public List<MembershipRequestHistoryDescriptor> getHistory() {
        if (history == null) {
            history = new ArrayList<MembershipRequestHistoryDescriptor>();
        }
        return this.history;
    }

}
