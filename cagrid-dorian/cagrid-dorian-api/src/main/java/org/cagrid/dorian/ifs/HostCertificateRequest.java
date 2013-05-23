
package org.cagrid.dorian.ifs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HostCertificateRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HostCertificateRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Hostname" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "HostCertificateRequest", propOrder = {
    "hostname",
    "publicKey"
})
public class HostCertificateRequest
    implements Serializable
{

    @XmlElement(name = "Hostname", required = true)
    protected String hostname;
    @XmlElement(name = "PublicKey", required = true)
    protected PublicKey publicKey;

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
