
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DorianPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DorianPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}IdentityProviderPolicy"/>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}FederationPolicy"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DorianPolicy", propOrder = {
    "identityProviderPolicy",
    "federationPolicy"
})
public class DorianPolicy
    implements Serializable
{

    @XmlElement(name = "IdentityProviderPolicy", required = true)
    protected IdentityProviderPolicy identityProviderPolicy;
    @XmlElement(name = "FederationPolicy", required = true)
    protected FederationPolicy federationPolicy;

    /**
     * Gets the value of the identityProviderPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link IdentityProviderPolicy }
     *     
     */
    public IdentityProviderPolicy getIdentityProviderPolicy() {
        return identityProviderPolicy;
    }

    /**
     * Sets the value of the identityProviderPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentityProviderPolicy }
     *     
     */
    public void setIdentityProviderPolicy(IdentityProviderPolicy value) {
        this.identityProviderPolicy = value;
    }

    /**
     * Gets the value of the federationPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link FederationPolicy }
     *     
     */
    public FederationPolicy getFederationPolicy() {
        return federationPolicy;
    }

    /**
     * Sets the value of the federationPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link FederationPolicy }
     *     
     */
    public void setFederationPolicy(FederationPolicy value) {
        this.federationPolicy = value;
    }

}
