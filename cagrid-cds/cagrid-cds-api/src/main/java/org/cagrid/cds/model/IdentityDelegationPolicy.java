
package org.cagrid.cds.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdentityDelegationPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentityDelegationPolicy">
 *   &lt;complexContent>
 *     &lt;extension base="{http://gaards.cagrid.org/cds}DelegationPolicy">
 *       &lt;sequence>
 *         &lt;element name="AllowedParties" type="{http://gaards.cagrid.org/cds}AllowedParties"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentityDelegationPolicy", propOrder = {
    "allowedParties"
})
public class IdentityDelegationPolicy
    extends DelegationPolicy
    implements Serializable
{

    @XmlElement(name = "AllowedParties", required = true)
    protected AllowedParties allowedParties;

    /**
     * Gets the value of the allowedParties property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.AllowedParties }
     *     
     */
    public AllowedParties getAllowedParties() {
        return allowedParties;
    }

    /**
     * Sets the value of the allowedParties property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.model.AllowedParties }
     *     
     */
    public void setAllowedParties(AllowedParties value) {
        this.allowedParties = value;
    }

}
