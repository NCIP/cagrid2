
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import org.cagrid.dorian.ifs.TrustedIdentityProviders;
import org.cagrid.dorian.policy.DorianPolicy;
import org.cagrid.gaards.authentication.AuthenticationProfiles;
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
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata}ServiceMetadata"/>
 *         &lt;element ref="{http://gaards.cagrid.org/authentication}AuthenticationProfiles"/>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}TrustedIdentityProviders"/>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}DorianPolicy"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serviceMetadata",
    "authenticationProfiles",
    "trustedIdentityProviders",
    "dorianPolicy"
})
@XmlRootElement(name = "DorianResourceProperties")
public class DorianResourceProperties
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "ServiceMetadata", namespace = "gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata", required = true)
    protected ServiceMetadata serviceMetadata;
    @XmlElement(name = "AuthenticationProfiles", namespace = "http://gaards.cagrid.org/authentication", required = true)
    protected AuthenticationProfiles authenticationProfiles;
    @XmlElement(name = "TrustedIdentityProviders", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
    protected TrustedIdentityProviders trustedIdentityProviders;
    @XmlElement(name = "DorianPolicy", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected DorianPolicy dorianPolicy;

    /**
     * Gets the value of the serviceMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceMetadata }
     *     
     */
    public ServiceMetadata getServiceMetadata() {
        return serviceMetadata;
    }

    /**
     * Sets the value of the serviceMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceMetadata }
     *     
     */
    public void setServiceMetadata(ServiceMetadata value) {
        this.serviceMetadata = value;
    }

    /**
     * Gets the value of the authenticationProfiles property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationProfiles }
     *     
     */
    public AuthenticationProfiles getAuthenticationProfiles() {
        return authenticationProfiles;
    }

    /**
     * Sets the value of the authenticationProfiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationProfiles }
     *     
     */
    public void setAuthenticationProfiles(AuthenticationProfiles value) {
        this.authenticationProfiles = value;
    }

    /**
     * Gets the value of the trustedIdentityProviders property.
     * 
     * @return
     *     possible object is
     *     {@link TrustedIdentityProviders }
     *     
     */
    public TrustedIdentityProviders getTrustedIdentityProviders() {
        return trustedIdentityProviders;
    }

    /**
     * Sets the value of the trustedIdentityProviders property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrustedIdentityProviders }
     *     
     */
    public void setTrustedIdentityProviders(TrustedIdentityProviders value) {
        this.trustedIdentityProviders = value;
    }

    /**
     * Gets the value of the dorianPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link DorianPolicy }
     *     
     */
    public DorianPolicy getDorianPolicy() {
        return dorianPolicy;
    }

    /**
     * Sets the value of the dorianPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link DorianPolicy }
     *     
     */
    public void setDorianPolicy(DorianPolicy value) {
        this.dorianPolicy = value;
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
            ServiceMetadata theServiceMetadata;
            theServiceMetadata = this.getServiceMetadata();
            strategy.appendField(locator, this, "serviceMetadata", buffer, theServiceMetadata);
        }
        {
            AuthenticationProfiles theAuthenticationProfiles;
            theAuthenticationProfiles = this.getAuthenticationProfiles();
            strategy.appendField(locator, this, "authenticationProfiles", buffer, theAuthenticationProfiles);
        }
        {
            TrustedIdentityProviders theTrustedIdentityProviders;
            theTrustedIdentityProviders = this.getTrustedIdentityProviders();
            strategy.appendField(locator, this, "trustedIdentityProviders", buffer, theTrustedIdentityProviders);
        }
        {
            DorianPolicy theDorianPolicy;
            theDorianPolicy = this.getDorianPolicy();
            strategy.appendField(locator, this, "dorianPolicy", buffer, theDorianPolicy);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            ServiceMetadata theServiceMetadata;
            theServiceMetadata = this.getServiceMetadata();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "serviceMetadata", theServiceMetadata), currentHashCode, theServiceMetadata);
        }
        {
            AuthenticationProfiles theAuthenticationProfiles;
            theAuthenticationProfiles = this.getAuthenticationProfiles();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authenticationProfiles", theAuthenticationProfiles), currentHashCode, theAuthenticationProfiles);
        }
        {
            TrustedIdentityProviders theTrustedIdentityProviders;
            theTrustedIdentityProviders = this.getTrustedIdentityProviders();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trustedIdentityProviders", theTrustedIdentityProviders), currentHashCode, theTrustedIdentityProviders);
        }
        {
            DorianPolicy theDorianPolicy;
            theDorianPolicy = this.getDorianPolicy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dorianPolicy", theDorianPolicy), currentHashCode, theDorianPolicy);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DorianResourceProperties)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final DorianResourceProperties that = ((DorianResourceProperties) object);
        {
            ServiceMetadata lhsServiceMetadata;
            lhsServiceMetadata = this.getServiceMetadata();
            ServiceMetadata rhsServiceMetadata;
            rhsServiceMetadata = that.getServiceMetadata();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceMetadata", lhsServiceMetadata), LocatorUtils.property(thatLocator, "serviceMetadata", rhsServiceMetadata), lhsServiceMetadata, rhsServiceMetadata)) {
                return false;
            }
        }
        {
            AuthenticationProfiles lhsAuthenticationProfiles;
            lhsAuthenticationProfiles = this.getAuthenticationProfiles();
            AuthenticationProfiles rhsAuthenticationProfiles;
            rhsAuthenticationProfiles = that.getAuthenticationProfiles();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authenticationProfiles", lhsAuthenticationProfiles), LocatorUtils.property(thatLocator, "authenticationProfiles", rhsAuthenticationProfiles), lhsAuthenticationProfiles, rhsAuthenticationProfiles)) {
                return false;
            }
        }
        {
            TrustedIdentityProviders lhsTrustedIdentityProviders;
            lhsTrustedIdentityProviders = this.getTrustedIdentityProviders();
            TrustedIdentityProviders rhsTrustedIdentityProviders;
            rhsTrustedIdentityProviders = that.getTrustedIdentityProviders();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "trustedIdentityProviders", lhsTrustedIdentityProviders), LocatorUtils.property(thatLocator, "trustedIdentityProviders", rhsTrustedIdentityProviders), lhsTrustedIdentityProviders, rhsTrustedIdentityProviders)) {
                return false;
            }
        }
        {
            DorianPolicy lhsDorianPolicy;
            lhsDorianPolicy = this.getDorianPolicy();
            DorianPolicy rhsDorianPolicy;
            rhsDorianPolicy = that.getDorianPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dorianPolicy", lhsDorianPolicy), LocatorUtils.property(thatLocator, "dorianPolicy", rhsDorianPolicy), lhsDorianPolicy, rhsDorianPolicy)) {
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
