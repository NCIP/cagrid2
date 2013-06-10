
package org.cagrid.dorian.ifs;

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
 * <p>Java class for TrustedIdentityProvider complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrustedIdentityProvider">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="displayName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authenticationServiceURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authenticationServiceIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrustedIdentityProvider", propOrder = {
    "name",
    "displayName",
    "authenticationServiceURL",
    "authenticationServiceIdentity"
})
public class TrustedIdentityProvider
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String displayName;
    protected String authenticationServiceURL;
    protected String authenticationServiceIdentity;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayName(String value) {
        this.displayName = value;
    }

    /**
     * Gets the value of the authenticationServiceURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationServiceURL() {
        return authenticationServiceURL;
    }

    /**
     * Sets the value of the authenticationServiceURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationServiceURL(String value) {
        this.authenticationServiceURL = value;
    }

    /**
     * Gets the value of the authenticationServiceIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationServiceIdentity() {
        return authenticationServiceIdentity;
    }

    /**
     * Sets the value of the authenticationServiceIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationServiceIdentity(String value) {
        this.authenticationServiceIdentity = value;
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
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            String theDisplayName;
            theDisplayName = this.getDisplayName();
            strategy.appendField(locator, this, "displayName", buffer, theDisplayName);
        }
        {
            String theAuthenticationServiceURL;
            theAuthenticationServiceURL = this.getAuthenticationServiceURL();
            strategy.appendField(locator, this, "authenticationServiceURL", buffer, theAuthenticationServiceURL);
        }
        {
            String theAuthenticationServiceIdentity;
            theAuthenticationServiceIdentity = this.getAuthenticationServiceIdentity();
            strategy.appendField(locator, this, "authenticationServiceIdentity", buffer, theAuthenticationServiceIdentity);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theName;
            theName = this.getName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName);
        }
        {
            String theDisplayName;
            theDisplayName = this.getDisplayName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "displayName", theDisplayName), currentHashCode, theDisplayName);
        }
        {
            String theAuthenticationServiceURL;
            theAuthenticationServiceURL = this.getAuthenticationServiceURL();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authenticationServiceURL", theAuthenticationServiceURL), currentHashCode, theAuthenticationServiceURL);
        }
        {
            String theAuthenticationServiceIdentity;
            theAuthenticationServiceIdentity = this.getAuthenticationServiceIdentity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authenticationServiceIdentity", theAuthenticationServiceIdentity), currentHashCode, theAuthenticationServiceIdentity);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TrustedIdentityProvider)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TrustedIdentityProvider that = ((TrustedIdentityProvider) object);
        {
            String lhsName;
            lhsName = this.getName();
            String rhsName;
            rhsName = that.getName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName)) {
                return false;
            }
        }
        {
            String lhsDisplayName;
            lhsDisplayName = this.getDisplayName();
            String rhsDisplayName;
            rhsDisplayName = that.getDisplayName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "displayName", lhsDisplayName), LocatorUtils.property(thatLocator, "displayName", rhsDisplayName), lhsDisplayName, rhsDisplayName)) {
                return false;
            }
        }
        {
            String lhsAuthenticationServiceURL;
            lhsAuthenticationServiceURL = this.getAuthenticationServiceURL();
            String rhsAuthenticationServiceURL;
            rhsAuthenticationServiceURL = that.getAuthenticationServiceURL();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authenticationServiceURL", lhsAuthenticationServiceURL), LocatorUtils.property(thatLocator, "authenticationServiceURL", rhsAuthenticationServiceURL), lhsAuthenticationServiceURL, rhsAuthenticationServiceURL)) {
                return false;
            }
        }
        {
            String lhsAuthenticationServiceIdentity;
            lhsAuthenticationServiceIdentity = this.getAuthenticationServiceIdentity();
            String rhsAuthenticationServiceIdentity;
            rhsAuthenticationServiceIdentity = that.getAuthenticationServiceIdentity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authenticationServiceIdentity", lhsAuthenticationServiceIdentity), LocatorUtils.property(thatLocator, "authenticationServiceIdentity", rhsAuthenticationServiceIdentity), lhsAuthenticationServiceIdentity, rhsAuthenticationServiceIdentity)) {
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
