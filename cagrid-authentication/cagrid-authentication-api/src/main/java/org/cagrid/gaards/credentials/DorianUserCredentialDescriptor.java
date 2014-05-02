
package org.cagrid.gaards.credentials;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


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
    implements Serializable
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

}
