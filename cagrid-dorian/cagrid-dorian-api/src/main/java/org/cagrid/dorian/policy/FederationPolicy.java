
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
 * <p>Java class for FederationPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FederationPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}UserPolicy"/>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}HostPolicy"/>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}SearchPolicy"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FederationPolicy", propOrder = {
    "userPolicy",
    "hostPolicy",
    "searchPolicy"
})
public class FederationPolicy
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "UserPolicy", required = true)
    protected UserPolicy userPolicy;
    @XmlElement(name = "HostPolicy", required = true)
    protected HostPolicy hostPolicy;
    @XmlElement(name = "SearchPolicy", required = true)
    protected SearchPolicy searchPolicy;

    /**
     * Gets the value of the userPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link UserPolicy }
     *     
     */
    public UserPolicy getUserPolicy() {
        return userPolicy;
    }

    /**
     * Sets the value of the userPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserPolicy }
     *     
     */
    public void setUserPolicy(UserPolicy value) {
        this.userPolicy = value;
    }

    /**
     * Gets the value of the hostPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link HostPolicy }
     *     
     */
    public HostPolicy getHostPolicy() {
        return hostPolicy;
    }

    /**
     * Sets the value of the hostPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link HostPolicy }
     *     
     */
    public void setHostPolicy(HostPolicy value) {
        this.hostPolicy = value;
    }

    /**
     * Gets the value of the searchPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link SearchPolicy }
     *     
     */
    public SearchPolicy getSearchPolicy() {
        return searchPolicy;
    }

    /**
     * Sets the value of the searchPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchPolicy }
     *     
     */
    public void setSearchPolicy(SearchPolicy value) {
        this.searchPolicy = value;
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
            UserPolicy theUserPolicy;
            theUserPolicy = this.getUserPolicy();
            strategy.appendField(locator, this, "userPolicy", buffer, theUserPolicy);
        }
        {
            HostPolicy theHostPolicy;
            theHostPolicy = this.getHostPolicy();
            strategy.appendField(locator, this, "hostPolicy", buffer, theHostPolicy);
        }
        {
            SearchPolicy theSearchPolicy;
            theSearchPolicy = this.getSearchPolicy();
            strategy.appendField(locator, this, "searchPolicy", buffer, theSearchPolicy);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            UserPolicy theUserPolicy;
            theUserPolicy = this.getUserPolicy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "userPolicy", theUserPolicy), currentHashCode, theUserPolicy);
        }
        {
            HostPolicy theHostPolicy;
            theHostPolicy = this.getHostPolicy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostPolicy", theHostPolicy), currentHashCode, theHostPolicy);
        }
        {
            SearchPolicy theSearchPolicy;
            theSearchPolicy = this.getSearchPolicy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "searchPolicy", theSearchPolicy), currentHashCode, theSearchPolicy);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FederationPolicy)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FederationPolicy that = ((FederationPolicy) object);
        {
            UserPolicy lhsUserPolicy;
            lhsUserPolicy = this.getUserPolicy();
            UserPolicy rhsUserPolicy;
            rhsUserPolicy = that.getUserPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "userPolicy", lhsUserPolicy), LocatorUtils.property(thatLocator, "userPolicy", rhsUserPolicy), lhsUserPolicy, rhsUserPolicy)) {
                return false;
            }
        }
        {
            HostPolicy lhsHostPolicy;
            lhsHostPolicy = this.getHostPolicy();
            HostPolicy rhsHostPolicy;
            rhsHostPolicy = that.getHostPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hostPolicy", lhsHostPolicy), LocatorUtils.property(thatLocator, "hostPolicy", rhsHostPolicy), lhsHostPolicy, rhsHostPolicy)) {
                return false;
            }
        }
        {
            SearchPolicy lhsSearchPolicy;
            lhsSearchPolicy = this.getSearchPolicy();
            SearchPolicy rhsSearchPolicy;
            rhsSearchPolicy = that.getSearchPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "searchPolicy", lhsSearchPolicy), LocatorUtils.property(thatLocator, "searchPolicy", rhsSearchPolicy), lhsSearchPolicy, rhsSearchPolicy)) {
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
