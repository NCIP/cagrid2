
package org.cagrid.gaards.credentials;

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
 * <p>Java class for DorianUserCredentialDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DorianUserCredentialDescriptor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://gaards.cagrid.org/credentials}X509CredentialDescriptor">
 *       &lt;attribute name="AuthenticationServiceURL" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DorianURL" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FirstName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="LastName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Email" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Organization" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DorianUserCredentialDescriptor")
public class DorianUserCredentialDescriptor
    extends X509CredentialDescriptor
    implements Serializable, Equals, HashCode, ToString
{

    @XmlAttribute(name = "AuthenticationServiceURL", namespace = "http://gaards.cagrid.org/credentials", required = true)
    protected String authenticationServiceURL;
    @XmlAttribute(name = "DorianURL", namespace = "http://gaards.cagrid.org/credentials", required = true)
    protected String dorianURL;
    @XmlAttribute(name = "FirstName", namespace = "http://gaards.cagrid.org/credentials", required = true)
    protected String firstName;
    @XmlAttribute(name = "LastName", namespace = "http://gaards.cagrid.org/credentials", required = true)
    protected String lastName;
    @XmlAttribute(name = "Email", namespace = "http://gaards.cagrid.org/credentials", required = true)
    protected String email;
    @XmlAttribute(name = "Organization", namespace = "http://gaards.cagrid.org/credentials", required = true)
    protected String organization;

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
     * Gets the value of the dorianURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDorianURL() {
        return dorianURL;
    }

    /**
     * Sets the value of the dorianURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDorianURL(String value) {
        this.dorianURL = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the organization property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Sets the value of the organization property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganization(String value) {
        this.organization = value;
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
        super.appendFields(locator, buffer, strategy);
        {
            String theAuthenticationServiceURL;
            theAuthenticationServiceURL = this.getAuthenticationServiceURL();
            strategy.appendField(locator, this, "authenticationServiceURL", buffer, theAuthenticationServiceURL);
        }
        {
            String theDorianURL;
            theDorianURL = this.getDorianURL();
            strategy.appendField(locator, this, "dorianURL", buffer, theDorianURL);
        }
        {
            String theFirstName;
            theFirstName = this.getFirstName();
            strategy.appendField(locator, this, "firstName", buffer, theFirstName);
        }
        {
            String theLastName;
            theLastName = this.getLastName();
            strategy.appendField(locator, this, "lastName", buffer, theLastName);
        }
        {
            String theEmail;
            theEmail = this.getEmail();
            strategy.appendField(locator, this, "email", buffer, theEmail);
        }
        {
            String theOrganization;
            theOrganization = this.getOrganization();
            strategy.appendField(locator, this, "organization", buffer, theOrganization);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            String theAuthenticationServiceURL;
            theAuthenticationServiceURL = this.getAuthenticationServiceURL();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authenticationServiceURL", theAuthenticationServiceURL), currentHashCode, theAuthenticationServiceURL);
        }
        {
            String theDorianURL;
            theDorianURL = this.getDorianURL();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dorianURL", theDorianURL), currentHashCode, theDorianURL);
        }
        {
            String theFirstName;
            theFirstName = this.getFirstName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "firstName", theFirstName), currentHashCode, theFirstName);
        }
        {
            String theLastName;
            theLastName = this.getLastName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastName", theLastName), currentHashCode, theLastName);
        }
        {
            String theEmail;
            theEmail = this.getEmail();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "email", theEmail), currentHashCode, theEmail);
        }
        {
            String theOrganization;
            theOrganization = this.getOrganization();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "organization", theOrganization), currentHashCode, theOrganization);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DorianUserCredentialDescriptor)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final DorianUserCredentialDescriptor that = ((DorianUserCredentialDescriptor) object);
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
            String lhsDorianURL;
            lhsDorianURL = this.getDorianURL();
            String rhsDorianURL;
            rhsDorianURL = that.getDorianURL();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dorianURL", lhsDorianURL), LocatorUtils.property(thatLocator, "dorianURL", rhsDorianURL), lhsDorianURL, rhsDorianURL)) {
                return false;
            }
        }
        {
            String lhsFirstName;
            lhsFirstName = this.getFirstName();
            String rhsFirstName;
            rhsFirstName = that.getFirstName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "firstName", lhsFirstName), LocatorUtils.property(thatLocator, "firstName", rhsFirstName), lhsFirstName, rhsFirstName)) {
                return false;
            }
        }
        {
            String lhsLastName;
            lhsLastName = this.getLastName();
            String rhsLastName;
            rhsLastName = that.getLastName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lastName", lhsLastName), LocatorUtils.property(thatLocator, "lastName", rhsLastName), lhsLastName, rhsLastName)) {
                return false;
            }
        }
        {
            String lhsEmail;
            lhsEmail = this.getEmail();
            String rhsEmail;
            rhsEmail = that.getEmail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "email", lhsEmail), LocatorUtils.property(thatLocator, "email", rhsEmail), lhsEmail, rhsEmail)) {
                return false;
            }
        }
        {
            String lhsOrganization;
            lhsOrganization = this.getOrganization();
            String rhsOrganization;
            rhsOrganization = that.getOrganization();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "organization", lhsOrganization), LocatorUtils.property(thatLocator, "organization", rhsOrganization), lhsOrganization, rhsOrganization)) {
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
