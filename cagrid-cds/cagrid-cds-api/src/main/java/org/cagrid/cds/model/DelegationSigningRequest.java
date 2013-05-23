
package org.cagrid.cds.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DelegationSigningRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DelegationSigningRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DelegationIdentifier" type="{http://gaards.cagrid.org/cds}DelegationIdentifier"/>
 *         &lt;element name="PublicKey" type="{http://gaards.cagrid.org/cds}PublicKey"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DelegationSigningRequest", propOrder = {
    "delegationIdentifier",
    "publicKey"
})
public class DelegationSigningRequest
    implements Serializable
{

    @XmlElement(name = "DelegationIdentifier", required = true)
    protected DelegationIdentifier delegationIdentifier;
    @XmlElement(name = "PublicKey", required = true)
    protected PublicKey publicKey;

    /**
     * Gets the value of the delegationIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.DelegationIdentifier }
     *     
     */
    public DelegationIdentifier getDelegationIdentifier() {
        return delegationIdentifier;
    }

    /**
     * Sets the value of the delegationIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.model.DelegationIdentifier }
     *     
     */
    public void setDelegationIdentifier(DelegationIdentifier value) {
        this.delegationIdentifier = value;
    }

    /**
     * Gets the value of the publicKey property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.PublicKey }
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
     *     {@link org.cagrid.cds.model.PublicKey }
     *     
     */
    public void setPublicKey(PublicKey value) {
        this.publicKey = value;
    }

}
