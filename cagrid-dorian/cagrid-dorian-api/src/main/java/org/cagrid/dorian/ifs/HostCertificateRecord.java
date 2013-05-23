
package org.cagrid.dorian.ifs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.common.X509Certificate;


/**
 * <p>Java class for HostCertificateRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HostCertificateRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SerialNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Host" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Owner" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateStatus"/>
 *         &lt;element name="Certificate" type="{http://cagrid.nci.nih.gov/1/dorian-common}X509Certificate"/>
 *         &lt;element name="PublicKey" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}PublicKey"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HostCertificateRecord", propOrder = {
    "id",
    "serialNumber",
    "host",
    "subject",
    "owner",
    "status",
    "certificate",
    "publicKey"
})
public class HostCertificateRecord
    implements Serializable
{

    @XmlElement(name = "Id")
    protected int id;
    @XmlElement(name = "SerialNumber")
    protected long serialNumber;
    @XmlElement(name = "Host", required = true)
    protected String host;
    @XmlElement(name = "Subject", required = true)
    protected String subject;
    @XmlElement(name = "Owner", required = true)
    protected String owner;
    @XmlElement(name = "Status", required = true)
    protected HostCertificateStatus status;
    @XmlElement(name = "Certificate", required = true)
    protected X509Certificate certificate;
    @XmlElement(name = "PublicKey", required = true)
    protected PublicKey publicKey;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

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
     * Gets the value of the host property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the value of the host property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link HostCertificateStatus }
     *     
     */
    public HostCertificateStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link HostCertificateStatus }
     *     
     */
    public void setStatus(HostCertificateStatus value) {
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
     * Gets the value of the publicKey property.
     * 
     * @return
     *     possible object is
     *     {@link PublicKey }
     *     
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Sets the value of the publicKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link PublicKey }
     *     
     */
    public void setPublicKey(PublicKey value) {
        this.publicKey = value;
    }

}
