
package org.cagrid.gaards.credentials;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for X509CredentialDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X509CredentialDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EncodedCertificates" type="{http://gaards.cagrid.org/credentials}EncodedCertificates"/>
 *         &lt;element name="EncodedKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Identity" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X509CredentialDescriptor", propOrder = {
    "encodedCertificates",
    "encodedKey"
})
@XmlSeeAlso({
    DorianUserCredentialDescriptor.class
})
public class X509CredentialDescriptor
    implements Serializable
{

    @XmlElement(name = "EncodedCertificates", required = true)
    protected EncodedCertificates encodedCertificates;
    @XmlElement(name = "EncodedKey", required = true)
    protected String encodedKey;
    @XmlAttribute(name = "Identity", namespace = "http://gaards.cagrid.org/credentials", required = true)
    protected String identity;

    /**
     * Gets the value of the encodedCertificates property.
     * 
     * @return
     *     possible object is
     *     {@link EncodedCertificates }
     *     
     */
    public EncodedCertificates getEncodedCertificates() {
        return encodedCertificates;
    }

    /**
     * Sets the value of the encodedCertificates property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncodedCertificates }
     *     
     */
    public void setEncodedCertificates(EncodedCertificates value) {
        this.encodedCertificates = value;
    }

    /**
     * Gets the value of the encodedKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncodedKey() {
        return encodedKey;
    }

    /**
     * Sets the value of the encodedKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncodedKey(String value) {
        this.encodedKey = value;
    }

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

}
