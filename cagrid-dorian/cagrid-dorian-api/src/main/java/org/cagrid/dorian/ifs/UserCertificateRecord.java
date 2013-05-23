
package org.cagrid.dorian.ifs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.common.X509Certificate;


/**
 * <p>Java class for UserCertificateRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserCertificateRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SerialNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="GridIdentity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}UserCertificateStatus"/>
 *         &lt;element name="Certificate" type="{http://cagrid.nci.nih.gov/1/dorian-common}X509Certificate"/>
 *         &lt;element name="Notes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserCertificateRecord", propOrder = {
    "serialNumber",
    "gridIdentity",
    "status",
    "certificate",
    "notes"
})
public class UserCertificateRecord
    implements Serializable
{

    @XmlElement(name = "SerialNumber")
    protected long serialNumber;
    @XmlElement(name = "GridIdentity", required = true)
    protected String gridIdentity;
    @XmlElement(name = "Status", required = true)
    protected UserCertificateStatus status;
    @XmlElement(name = "Certificate", required = true)
    protected X509Certificate certificate;
    @XmlElement(name = "Notes", required = true)
    protected String notes;

    /**
     * Gets the value of the serialNumber property.
     * 
     */
    public long getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the value of the serialNumber property.
     * 
     */
    public void setSerialNumber(long value) {
        this.serialNumber = value;
    }

    /**
     * Gets the value of the gridIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridIdentity() {
        return gridIdentity;
    }

    /**
     * Sets the value of the gridIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridIdentity(String value) {
        this.gridIdentity = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link UserCertificateStatus }
     *     
     */
    public UserCertificateStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserCertificateStatus }
     *     
     */
    public void setStatus(UserCertificateStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the certificate property.
     * 
     * @return
     *     possible object is
     *     {@link X509Certificate }
     *     
     */
    public X509Certificate getCertificate() {
        return certificate;
    }

    /**
     * Sets the value of the certificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link X509Certificate }
     *     
     */
    public void setCertificate(X509Certificate value) {
        this.certificate = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

}
