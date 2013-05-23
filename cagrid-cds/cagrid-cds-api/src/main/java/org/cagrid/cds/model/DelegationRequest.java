
package org.cagrid.cds.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DelegationRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DelegationRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DelegationPolicy" type="{http://gaards.cagrid.org/cds}DelegationPolicy"/>
 *         &lt;element name="KeyLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IssuedCredentialPathLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IssuedCredentialLifetime" type="{http://gaards.cagrid.org/cds}ProxyLifetime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DelegationRequest", propOrder = {
    "delegationPolicy",
    "keyLength",
    "issuedCredentialPathLength",
    "issuedCredentialLifetime"
})
public class DelegationRequest
    implements Serializable
{

    @XmlElement(name = "DelegationPolicy", required = true)
    protected DelegationPolicy delegationPolicy;
    @XmlElement(name = "KeyLength")
    protected int keyLength;
    @XmlElement(name = "IssuedCredentialPathLength")
    protected int issuedCredentialPathLength;
    @XmlElement(name = "IssuedCredentialLifetime", required = true)
    protected ProxyLifetime issuedCredentialLifetime;

    /**
     * Gets the value of the delegationPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.DelegationPolicy }
     *     
     */
    public DelegationPolicy getDelegationPolicy() {
        return delegationPolicy;
    }

    /**
     * Sets the value of the delegationPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.model.DelegationPolicy }
     *     
     */
    public void setDelegationPolicy(DelegationPolicy value) {
        this.delegationPolicy = value;
    }

    /**
     * Gets the value of the keyLength property.
     * 
     */
    public int getKeyLength() {
        return keyLength;
    }

    /**
     * Sets the value of the keyLength property.
     * 
     */
    public void setKeyLength(int value) {
        this.keyLength = value;
    }

    /**
     * Gets the value of the issuedCredentialPathLength property.
     * 
     */
    public int getIssuedCredentialPathLength() {
        return issuedCredentialPathLength;
    }

    /**
     * Sets the value of the issuedCredentialPathLength property.
     * 
     */
    public void setIssuedCredentialPathLength(int value) {
        this.issuedCredentialPathLength = value;
    }

    /**
     * Gets the value of the issuedCredentialLifetime property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.ProxyLifetime }
     *     
     */
    public ProxyLifetime getIssuedCredentialLifetime() {
        return issuedCredentialLifetime;
    }

    /**
     * Sets the value of the issuedCredentialLifetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.model.ProxyLifetime }
     *     
     */
    public void setIssuedCredentialLifetime(ProxyLifetime value) {
        this.issuedCredentialLifetime = value;
    }

}
