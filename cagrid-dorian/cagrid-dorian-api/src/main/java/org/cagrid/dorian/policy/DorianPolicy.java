
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


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
    implements Serializable, Equals, HashCode, ToString
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

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            IdentityProviderPolicy theIdentityProviderPolicy;
            theIdentityProviderPolicy = this.getIdentityProviderPolicy();
            strategy.appendField(locator, this, "identityProviderPolicy", buffer, theIdentityProviderPolicy);
        }
        {
            FederationPolicy theFederationPolicy;
            theFederationPolicy = this.getFederationPolicy();
            strategy.appendField(locator, this, "federationPolicy", buffer, theFederationPolicy);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            IdentityProviderPolicy theIdentityProviderPolicy;
            theIdentityProviderPolicy = this.getIdentityProviderPolicy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "identityProviderPolicy", theIdentityProviderPolicy), currentHashCode, theIdentityProviderPolicy);
        }
        {
            FederationPolicy theFederationPolicy;
            theFederationPolicy = this.getFederationPolicy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "federationPolicy", theFederationPolicy), currentHashCode, theFederationPolicy);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DorianPolicy)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final DorianPolicy that = ((DorianPolicy) object);
        {
            IdentityProviderPolicy lhsIdentityProviderPolicy;
            lhsIdentityProviderPolicy = this.getIdentityProviderPolicy();
            IdentityProviderPolicy rhsIdentityProviderPolicy;
            rhsIdentityProviderPolicy = that.getIdentityProviderPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "identityProviderPolicy", lhsIdentityProviderPolicy), LocatorUtils.property(thatLocator, "identityProviderPolicy", rhsIdentityProviderPolicy), lhsIdentityProviderPolicy, rhsIdentityProviderPolicy)) {
                return false;
            }
        }
        {
            FederationPolicy lhsFederationPolicy;
            lhsFederationPolicy = this.getFederationPolicy();
            FederationPolicy rhsFederationPolicy;
            rhsFederationPolicy = that.getFederationPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "federationPolicy", lhsFederationPolicy), LocatorUtils.property(thatLocator, "federationPolicy", rhsFederationPolicy), lhsFederationPolicy, rhsFederationPolicy)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

}
