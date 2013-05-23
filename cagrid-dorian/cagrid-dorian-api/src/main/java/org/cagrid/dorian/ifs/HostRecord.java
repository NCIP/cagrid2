
package org.cagrid.dorian.ifs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HostRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HostRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hostname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hostCertificateSubject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="owner" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ownerFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ownerLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ownerEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HostRecord", propOrder = {
    "identity",
    "hostname",
    "hostCertificateSubject",
    "owner",
    "ownerFirstName",
    "ownerLastName",
    "ownerEmail"
})
public class HostRecord
    implements Serializable
{

    @XmlElement(required = true)
    protected String identity;
    @XmlElement(required = true)
    protected String hostname;
    @XmlElement(required = true)
    protected String hostCertificateSubject;
    @XmlElement(required = true)
    protected String owner;
    @XmlElement(required = true)
    protected String ownerFirstName;
    @XmlElement(required = true)
    protected String ownerLastName;
    @XmlElement(required = true)
    protected String ownerEmail;

    /**
     * Gets the value of the identity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * Sets the value of the identity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentity(String value) {
        this.identity = value;
    }

    /**
     * Gets the value of the hostname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Sets the value of the hostname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostname(String value) {
        this.hostname = value;
    }

    /**
     * Gets the value of the hostCertificateSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostCertificateSubject() {
        return hostCertificateSubject;
    }

    /**
     * Sets the value of the hostCertificateSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostCertificateSubject(String value) {
        this.hostCertificateSubject = value;
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
     * Gets the value of the ownerFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    /**
     * Sets the value of the ownerFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerFirstName(String value) {
        this.ownerFirstName = value;
    }

    /**
     * Gets the value of the ownerLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerLastName() {
        return ownerLastName;
    }

    /**
     * Sets the value of the ownerLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerLastName(String value) {
        this.ownerLastName = value;
    }

    /**
     * Gets the value of the ownerEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerEmail() {
        return ownerEmail;
    }

    /**
     * Sets the value of the ownerEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerEmail(String value) {
        this.ownerEmail = value;
    }

}
