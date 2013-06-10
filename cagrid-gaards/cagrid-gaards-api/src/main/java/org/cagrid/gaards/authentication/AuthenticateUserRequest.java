
package org.cagrid.gaards.authentication;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="credential">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://gaards.cagrid.org/authentication}Credential"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "credential"
})
@XmlRootElement(name = "AuthenticateUserRequest")
public class AuthenticateUserRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected AuthenticateUserRequest.Credential credential;

    /**
     * Gets the value of the credential property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticateUserRequest.Credential }
     *     
     */
    public AuthenticateUserRequest.Credential getCredential() {
        return credential;
    }

    /**
     * Sets the value of the credential property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticateUserRequest.Credential }
     *     
     */
    public void setCredential(AuthenticateUserRequest.Credential value) {
        this.credential = value;
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
            AuthenticateUserRequest.Credential theCredential;
            theCredential = this.getCredential();
            strategy.appendField(locator, this, "credential", buffer, theCredential);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            AuthenticateUserRequest.Credential theCredential;
            theCredential = this.getCredential();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "credential", theCredential), currentHashCode, theCredential);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AuthenticateUserRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AuthenticateUserRequest that = ((AuthenticateUserRequest) object);
        {
            AuthenticateUserRequest.Credential lhsCredential;
            lhsCredential = this.getCredential();
            AuthenticateUserRequest.Credential rhsCredential;
            rhsCredential = that.getCredential();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "credential", lhsCredential), LocatorUtils.property(thatLocator, "credential", rhsCredential), lhsCredential, rhsCredential)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }


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
     *         &lt;element ref="{http://gaards.cagrid.org/authentication}Credential"/>
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
        "credential"
    })
    public static class Credential
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "Credential", namespace = "http://gaards.cagrid.org/authentication", required = true)
        protected org.cagrid.gaards.authentication.Credential credential;

        /**
         * Gets the value of the credential property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gaards.authentication.Credential }
         *     
         */
        public org.cagrid.gaards.authentication.Credential getCredential() {
            return credential;
        }

        /**
         * Sets the value of the credential property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gaards.authentication.Credential }
         *     
         */
        public void setCredential(org.cagrid.gaards.authentication.Credential value) {
            this.credential = value;
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
                org.cagrid.gaards.authentication.Credential theCredential;
                theCredential = this.getCredential();
                strategy.appendField(locator, this, "credential", buffer, theCredential);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                org.cagrid.gaards.authentication.Credential theCredential;
                theCredential = this.getCredential();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "credential", theCredential), currentHashCode, theCredential);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof AuthenticateUserRequest.Credential)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final AuthenticateUserRequest.Credential that = ((AuthenticateUserRequest.Credential) object);
            {
                org.cagrid.gaards.authentication.Credential lhsCredential;
                lhsCredential = this.getCredential();
                org.cagrid.gaards.authentication.Credential rhsCredential;
                rhsCredential = that.getCredential();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "credential", lhsCredential), LocatorUtils.property(thatLocator, "credential", rhsCredential), lhsCredential, rhsCredential)) {
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

}
