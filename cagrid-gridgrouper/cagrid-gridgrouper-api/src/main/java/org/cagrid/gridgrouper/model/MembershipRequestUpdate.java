
package org.cagrid.gridgrouper.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MembershipRequestUpdate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MembershipRequestUpdate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipRequestStatus" minOccurs="0"/>
 *         &lt;element name="PublicNote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdminNote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MembershipRequestUpdate", propOrder = {
    "status",
    "publicNote",
    "adminNote"
})
public class MembershipRequestUpdate
    implements Serializable
{

    @XmlElement(name = "Status")
    protected MembershipRequestStatus status;
    @XmlElement(name = "PublicNote")
    protected String publicNote;
    @XmlElement(name = "AdminNote")
    protected String adminNote;

    public MembershipRequestUpdate() {
    }

    public MembershipRequestUpdate(
            String adminNote,
            String publicNote,
            MembershipRequestStatus status) {
        this.status = status;
        this.publicNote = publicNote;
        this.adminNote = adminNote;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.model.MembershipRequestStatus }
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
     *     {@link org.cagrid.gridgrouper.model.MembershipRequestStatus }
     *     
     */
    public void setStatus(MembershipRequestStatus value) {
        this.status = value;
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
