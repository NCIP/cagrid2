
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.cagrid.dorian.model.idp.BasicAuthCredential;
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
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}BasicAuthCredential"/>
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
@XmlRootElement(name = "ChangeIdPUserPasswordRequest")
public class ChangeIdPUserPasswordRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected ChangeIdPUserPasswordRequest.Credential credential;
    @XmlElement(required = true)
    protected String newPassword;

    /**
     * Gets the value of the credential property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeIdPUserPasswordRequest.Credential }
     *     
     */
    public ChangeIdPUserPasswordRequest.Credential getCredential() {
        return credential;
    }

    /**
     * Sets the value of the credential property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeIdPUserPasswordRequest.Credential }
     *     
     */
    public void setCredential(ChangeIdPUserPasswordRequest.Credential value) {
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
            ChangeIdPUserPasswordRequest.Credential theCredential;
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
            ChangeIdPUserPasswordRequest.Credential theCredential;
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
        if (!(object instanceof ChangeIdPUserPasswordRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ChangeIdPUserPasswordRequest that = ((ChangeIdPUserPasswordRequest) object);
        {
            ChangeIdPUserPasswordRequest.Credential lhsCredential;
            lhsCredential = this.getCredential();
            ChangeIdPUserPasswordRequest.Credential rhsCredential;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}BasicAuthCredential"/>
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
        "basicAuthCredential"
    })
    public static class Credential
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "BasicAuthCredential", namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", required = true)
        protected BasicAuthCredential basicAuthCredential;

        /**
         * Gets the value of the basicAuthCredential property.
         * 
         * @return
         *     possible object is
         *     {@link BasicAuthCredential }
         *     
         */
        public BasicAuthCredential getBasicAuthCredential() {
            return basicAuthCredential;
        }

        /**
         * Sets the value of the basicAuthCredential property.
         * 
         * @param value
         *     allowed object is
         *     {@link BasicAuthCredential }
         *     
         */
        public void setBasicAuthCredential(BasicAuthCredential value) {
            this.basicAuthCredential = value;
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
                BasicAuthCredential theBasicAuthCredential;
                theBasicAuthCredential = this.getBasicAuthCredential();
                strategy.appendField(locator, this, "basicAuthCredential", buffer, theBasicAuthCredential);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                BasicAuthCredential theBasicAuthCredential;
                theBasicAuthCredential = this.getBasicAuthCredential();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "basicAuthCredential", theBasicAuthCredential), currentHashCode, theBasicAuthCredential);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof ChangeIdPUserPasswordRequest.Credential)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final ChangeIdPUserPasswordRequest.Credential that = ((ChangeIdPUserPasswordRequest.Credential) object);
            {
                BasicAuthCredential lhsBasicAuthCredential;
                lhsBasicAuthCredential = this.getBasicAuthCredential();
                BasicAuthCredential rhsBasicAuthCredential;
                rhsBasicAuthCredential = that.getBasicAuthCredential();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "basicAuthCredential", lhsBasicAuthCredential), LocatorUtils.property(thatLocator, "basicAuthCredential", rhsBasicAuthCredential), lhsBasicAuthCredential, rhsBasicAuthCredential)) {
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
