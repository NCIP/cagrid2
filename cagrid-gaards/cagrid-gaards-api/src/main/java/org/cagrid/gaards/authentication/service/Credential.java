
package org.cagrid.gaards.authentication.service;

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
 * <p>Java class for Credential complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Credential">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="BasicAuthenticationCredential" type="{http://cagrid.nci.nih.gov/1/authentication-service}BasicAuthenticationCredential"/>
 *         &lt;element name="CredentialExtension" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Credential", propOrder = {
    "basicAuthenticationCredential",
    "credentialExtension"
})
public class Credential
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "BasicAuthenticationCredential")
    protected BasicAuthenticationCredential basicAuthenticationCredential;
    @XmlElement(name = "CredentialExtension")
    protected Object credentialExtension;

    /**
     * Gets the value of the basicAuthenticationCredential property.
     * 
     * @return
     *     possible object is
     *     {@link BasicAuthenticationCredential }
     *     
     */
    public BasicAuthenticationCredential getBasicAuthenticationCredential() {
        return basicAuthenticationCredential;
    }

    /**
     * Sets the value of the basicAuthenticationCredential property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicAuthenticationCredential }
     *     
     */
    public void setBasicAuthenticationCredential(BasicAuthenticationCredential value) {
        this.basicAuthenticationCredential = value;
    }

    /**
     * Gets the value of the credentialExtension property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getCredentialExtension() {
        return credentialExtension;
    }

    /**
     * Sets the value of the credentialExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setCredentialExtension(Object value) {
        this.credentialExtension = value;
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
            BasicAuthenticationCredential theBasicAuthenticationCredential;
            theBasicAuthenticationCredential = this.getBasicAuthenticationCredential();
            strategy.appendField(locator, this, "basicAuthenticationCredential", buffer, theBasicAuthenticationCredential);
        }
        {
            Object theCredentialExtension;
            theCredentialExtension = this.getCredentialExtension();
            strategy.appendField(locator, this, "credentialExtension", buffer, theCredentialExtension);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            BasicAuthenticationCredential theBasicAuthenticationCredential;
            theBasicAuthenticationCredential = this.getBasicAuthenticationCredential();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "basicAuthenticationCredential", theBasicAuthenticationCredential), currentHashCode, theBasicAuthenticationCredential);
        }
        {
            Object theCredentialExtension;
            theCredentialExtension = this.getCredentialExtension();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "credentialExtension", theCredentialExtension), currentHashCode, theCredentialExtension);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Credential)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Credential that = ((Credential) object);
        {
            BasicAuthenticationCredential lhsBasicAuthenticationCredential;
            lhsBasicAuthenticationCredential = this.getBasicAuthenticationCredential();
            BasicAuthenticationCredential rhsBasicAuthenticationCredential;
            rhsBasicAuthenticationCredential = that.getBasicAuthenticationCredential();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "basicAuthenticationCredential", lhsBasicAuthenticationCredential), LocatorUtils.property(thatLocator, "basicAuthenticationCredential", rhsBasicAuthenticationCredential), lhsBasicAuthenticationCredential, rhsBasicAuthenticationCredential)) {
                return false;
            }
        }
        {
            Object lhsCredentialExtension;
            lhsCredentialExtension = this.getCredentialExtension();
            Object rhsCredentialExtension;
            rhsCredentialExtension = that.getCredentialExtension();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "credentialExtension", lhsCredentialExtension), LocatorUtils.property(thatLocator, "credentialExtension", rhsCredentialExtension), lhsCredentialExtension, rhsCredentialExtension)) {
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
