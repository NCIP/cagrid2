
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gaards.authentication.BasicAuthentication;
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
 *                   &lt;element ref="{http://gaards.cagrid.org/authentication}BasicAuthentication"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="newPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "credential",
    "newPassword"
})
@XmlRootElement(name = "ChangeLocalUserPasswordRequest")
public class ChangeLocalUserPasswordRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected ChangeLocalUserPasswordRequest.Credential credential;
    @XmlElement(required = true)
    protected String newPassword;

    /**
     * Gets the value of the credential property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeLocalUserPasswordRequest.Credential }
     *     
     */
    public ChangeLocalUserPasswordRequest.Credential getCredential() {
        return credential;
    }

    /**
     * Sets the value of the credential property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeLocalUserPasswordRequest.Credential }
     *     
     */
    public void setCredential(ChangeLocalUserPasswordRequest.Credential value) {
        this.credential = value;
    }

    /**
     * Gets the value of the newPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the value of the newPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewPassword(String value) {
        this.newPassword = value;
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
            ChangeLocalUserPasswordRequest.Credential theCredential;
            theCredential = this.getCredential();
            strategy.appendField(locator, this, "credential", buffer, theCredential);
        }
        {
            String theNewPassword;
            theNewPassword = this.getNewPassword();
            strategy.appendField(locator, this, "newPassword", buffer, theNewPassword);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            ChangeLocalUserPasswordRequest.Credential theCredential;
            theCredential = this.getCredential();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "credential", theCredential), currentHashCode, theCredential);
        }
        {
            String theNewPassword;
            theNewPassword = this.getNewPassword();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "newPassword", theNewPassword), currentHashCode, theNewPassword);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ChangeLocalUserPasswordRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ChangeLocalUserPasswordRequest that = ((ChangeLocalUserPasswordRequest) object);
        {
            ChangeLocalUserPasswordRequest.Credential lhsCredential;
            lhsCredential = this.getCredential();
            ChangeLocalUserPasswordRequest.Credential rhsCredential;
            rhsCredential = that.getCredential();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "credential", lhsCredential), LocatorUtils.property(thatLocator, "credential", rhsCredential), lhsCredential, rhsCredential)) {
                return false;
            }
        }
        {
            String lhsNewPassword;
            lhsNewPassword = this.getNewPassword();
            String rhsNewPassword;
            rhsNewPassword = that.getNewPassword();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "newPassword", lhsNewPassword), LocatorUtils.property(thatLocator, "newPassword", rhsNewPassword), lhsNewPassword, rhsNewPassword)) {
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
     *         &lt;element ref="{http://gaards.cagrid.org/authentication}BasicAuthentication"/>
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
        "basicAuthentication"
    })
    public static class Credential
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "BasicAuthentication", namespace = "http://gaards.cagrid.org/authentication", required = true)
        protected BasicAuthentication basicAuthentication;

        /**
         * Gets the value of the basicAuthentication property.
         * 
         * @return
         *     possible object is
         *     {@link BasicAuthentication }
         *     
         */
        public BasicAuthentication getBasicAuthentication() {
            return basicAuthentication;
        }

        /**
         * Sets the value of the basicAuthentication property.
         * 
         * @param value
         *     allowed object is
         *     {@link BasicAuthentication }
         *     
         */
        public void setBasicAuthentication(BasicAuthentication value) {
            this.basicAuthentication = value;
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
                BasicAuthentication theBasicAuthentication;
                theBasicAuthentication = this.getBasicAuthentication();
                strategy.appendField(locator, this, "basicAuthentication", buffer, theBasicAuthentication);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                BasicAuthentication theBasicAuthentication;
                theBasicAuthentication = this.getBasicAuthentication();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "basicAuthentication", theBasicAuthentication), currentHashCode, theBasicAuthentication);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof ChangeLocalUserPasswordRequest.Credential)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final ChangeLocalUserPasswordRequest.Credential that = ((ChangeLocalUserPasswordRequest.Credential) object);
            {
                BasicAuthentication lhsBasicAuthentication;
                lhsBasicAuthentication = this.getBasicAuthentication();
                BasicAuthentication rhsBasicAuthentication;
                rhsBasicAuthentication = that.getBasicAuthentication();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "basicAuthentication", lhsBasicAuthentication), LocatorUtils.property(thatLocator, "basicAuthentication", rhsBasicAuthentication), lhsBasicAuthentication, rhsBasicAuthentication)) {
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
