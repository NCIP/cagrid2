
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 * <p>Java class for SearchPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="UserSearchPolicy" use="required" type="{http://cagrid.nci.nih.gov/1/dorian-policy}SearchPolicyType" />
 *       &lt;attribute name="HostSearchPolicy" use="required" type="{http://cagrid.nci.nih.gov/1/dorian-policy}SearchPolicyType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchPolicy")
public class SearchPolicy
    implements Serializable, Equals, HashCode, ToString
{

    @XmlAttribute(name = "UserSearchPolicy", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected SearchPolicyType userSearchPolicy;
    @XmlAttribute(name = "HostSearchPolicy", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected SearchPolicyType hostSearchPolicy;

    /**
     * Gets the value of the userSearchPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link SearchPolicyType }
     *     
     */
    public SearchPolicyType getUserSearchPolicy() {
        return userSearchPolicy;
    }

    /**
     * Sets the value of the userSearchPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchPolicyType }
     *     
     */
    public void setUserSearchPolicy(SearchPolicyType value) {
        this.userSearchPolicy = value;
    }

    /**
     * Gets the value of the hostSearchPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link SearchPolicyType }
     *     
     */
    public SearchPolicyType getHostSearchPolicy() {
        return hostSearchPolicy;
    }

    /**
     * Sets the value of the hostSearchPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchPolicyType }
     *     
     */
    public void setHostSearchPolicy(SearchPolicyType value) {
        this.hostSearchPolicy = value;
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
            SearchPolicyType theUserSearchPolicy;
            theUserSearchPolicy = this.getUserSearchPolicy();
            strategy.appendField(locator, this, "userSearchPolicy", buffer, theUserSearchPolicy);
        }
        {
            SearchPolicyType theHostSearchPolicy;
            theHostSearchPolicy = this.getHostSearchPolicy();
            strategy.appendField(locator, this, "hostSearchPolicy", buffer, theHostSearchPolicy);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            SearchPolicyType theUserSearchPolicy;
            theUserSearchPolicy = this.getUserSearchPolicy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "userSearchPolicy", theUserSearchPolicy), currentHashCode, theUserSearchPolicy);
        }
        {
            SearchPolicyType theHostSearchPolicy;
            theHostSearchPolicy = this.getHostSearchPolicy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostSearchPolicy", theHostSearchPolicy), currentHashCode, theHostSearchPolicy);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SearchPolicy)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SearchPolicy that = ((SearchPolicy) object);
        {
            SearchPolicyType lhsUserSearchPolicy;
            lhsUserSearchPolicy = this.getUserSearchPolicy();
            SearchPolicyType rhsUserSearchPolicy;
            rhsUserSearchPolicy = that.getUserSearchPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "userSearchPolicy", lhsUserSearchPolicy), LocatorUtils.property(thatLocator, "userSearchPolicy", rhsUserSearchPolicy), lhsUserSearchPolicy, rhsUserSearchPolicy)) {
                return false;
            }
        }
        {
            SearchPolicyType lhsHostSearchPolicy;
            lhsHostSearchPolicy = this.getHostSearchPolicy();
            SearchPolicyType rhsHostSearchPolicy;
            rhsHostSearchPolicy = that.getHostSearchPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hostSearchPolicy", lhsHostSearchPolicy), LocatorUtils.property(thatLocator, "hostSearchPolicy", rhsHostSearchPolicy), lhsHostSearchPolicy, rhsHostSearchPolicy)) {
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
