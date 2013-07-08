
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.cagrid.dorian.model.idp.AccountProfile;
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
 *         &lt;element name="profile">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}AccountProfile"/>
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
    "profile"
})
@XmlRootElement(name = "UpdateAccountProfileRequest")
public class UpdateAccountProfileRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected UpdateAccountProfileRequest.Profile profile;

    /**
     * Gets the value of the profile property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateAccountProfileRequest.Profile }
     *     
     */
    public UpdateAccountProfileRequest.Profile getProfile() {
        return profile;
    }

    /**
     * Sets the value of the profile property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateAccountProfileRequest.Profile }
     *     
     */
    public void setProfile(UpdateAccountProfileRequest.Profile value) {
        this.profile = value;
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
            UpdateAccountProfileRequest.Profile theProfile;
            theProfile = this.getProfile();
            strategy.appendField(locator, this, "profile", buffer, theProfile);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            UpdateAccountProfileRequest.Profile theProfile;
            theProfile = this.getProfile();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "profile", theProfile), currentHashCode, theProfile);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof UpdateAccountProfileRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final UpdateAccountProfileRequest that = ((UpdateAccountProfileRequest) object);
        {
            UpdateAccountProfileRequest.Profile lhsProfile;
            lhsProfile = this.getProfile();
            UpdateAccountProfileRequest.Profile rhsProfile;
            rhsProfile = that.getProfile();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "profile", lhsProfile), LocatorUtils.property(thatLocator, "profile", rhsProfile), lhsProfile, rhsProfile)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}AccountProfile"/>
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
        "accountProfile"
    })
    public static class Profile
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "AccountProfile", namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", required = true)
        protected AccountProfile accountProfile;

        /**
         * Gets the value of the accountProfile property.
         * 
         * @return
         *     possible object is
         *     {@link AccountProfile }
         *     
         */
        public AccountProfile getAccountProfile() {
            return accountProfile;
        }

        /**
         * Sets the value of the accountProfile property.
         * 
         * @param value
         *     allowed object is
         *     {@link AccountProfile }
         *     
         */
        public void setAccountProfile(AccountProfile value) {
            this.accountProfile = value;
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
                AccountProfile theAccountProfile;
                theAccountProfile = this.getAccountProfile();
                strategy.appendField(locator, this, "accountProfile", buffer, theAccountProfile);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                AccountProfile theAccountProfile;
                theAccountProfile = this.getAccountProfile();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "accountProfile", theAccountProfile), currentHashCode, theAccountProfile);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof UpdateAccountProfileRequest.Profile)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final UpdateAccountProfileRequest.Profile that = ((UpdateAccountProfileRequest.Profile) object);
            {
                AccountProfile lhsAccountProfile;
                lhsAccountProfile = this.getAccountProfile();
                AccountProfile rhsAccountProfile;
                rhsAccountProfile = that.getAccountProfile();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "accountProfile", lhsAccountProfile), LocatorUtils.property(thatLocator, "accountProfile", rhsAccountProfile), lhsAccountProfile, rhsAccountProfile)) {
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
