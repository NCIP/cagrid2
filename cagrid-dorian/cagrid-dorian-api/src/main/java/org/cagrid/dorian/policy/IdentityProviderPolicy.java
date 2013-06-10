
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 * <p>Java class for IdentityProviderPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentityProviderPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}UserIdPolicy"/>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}PasswordPolicy"/>
 *       &lt;/sequence>
 *       &lt;attribute name="AccountInformationModificationPolicy" use="required" type="{http://cagrid.nci.nih.gov/1/dorian-policy}AccountInformationModificationPolicy" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentityProviderPolicy", propOrder = {
    "userIdPolicy",
    "passwordPolicy"
})
public class IdentityProviderPolicy
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "UserIdPolicy", required = true)
    protected UserIdPolicy userIdPolicy;
    @XmlElement(name = "PasswordPolicy", required = true)
    protected PasswordPolicy passwordPolicy;
    @XmlAttribute(name = "AccountInformationModificationPolicy", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected AccountInformationModificationPolicy accountInformationModificationPolicy;

    /**
     * Gets the value of the userIdPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link UserIdPolicy }
     *     
     */
    public UserIdPolicy getUserIdPolicy() {
        return userIdPolicy;
    }

    /**
     * Sets the value of the userIdPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserIdPolicy }
     *     
     */
    public void setUserIdPolicy(UserIdPolicy value) {
        this.userIdPolicy = value;
    }

    /**
     * Gets the value of the passwordPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link PasswordPolicy }
     *     
     */
    public PasswordPolicy getPasswordPolicy() {
        return passwordPolicy;
    }

    /**
     * Sets the value of the passwordPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link PasswordPolicy }
     *     
     */
    public void setPasswordPolicy(PasswordPolicy value) {
        this.passwordPolicy = value;
    }

    /**
     * Gets the value of the accountInformationModificationPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link AccountInformationModificationPolicy }
     *     
     */
    public AccountInformationModificationPolicy getAccountInformationModificationPolicy() {
        return accountInformationModificationPolicy;
    }

    /**
     * Sets the value of the accountInformationModificationPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountInformationModificationPolicy }
     *     
     */
    public void setAccountInformationModificationPolicy(AccountInformationModificationPolicy value) {
        this.accountInformationModificationPolicy = value;
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
            UserIdPolicy theUserIdPolicy;
            theUserIdPolicy = this.getUserIdPolicy();
            strategy.appendField(locator, this, "userIdPolicy", buffer, theUserIdPolicy);
        }
        {
            PasswordPolicy thePasswordPolicy;
            thePasswordPolicy = this.getPasswordPolicy();
            strategy.appendField(locator, this, "passwordPolicy", buffer, thePasswordPolicy);
        }
        {
            AccountInformationModificationPolicy theAccountInformationModificationPolicy;
            theAccountInformationModificationPolicy = this.getAccountInformationModificationPolicy();
            strategy.appendField(locator, this, "accountInformationModificationPolicy", buffer, theAccountInformationModificationPolicy);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            UserIdPolicy theUserIdPolicy;
            theUserIdPolicy = this.getUserIdPolicy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "userIdPolicy", theUserIdPolicy), currentHashCode, theUserIdPolicy);
        }
        {
            PasswordPolicy thePasswordPolicy;
            thePasswordPolicy = this.getPasswordPolicy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "passwordPolicy", thePasswordPolicy), currentHashCode, thePasswordPolicy);
        }
        {
            AccountInformationModificationPolicy theAccountInformationModificationPolicy;
            theAccountInformationModificationPolicy = this.getAccountInformationModificationPolicy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "accountInformationModificationPolicy", theAccountInformationModificationPolicy), currentHashCode, theAccountInformationModificationPolicy);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof IdentityProviderPolicy)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final IdentityProviderPolicy that = ((IdentityProviderPolicy) object);
        {
            UserIdPolicy lhsUserIdPolicy;
            lhsUserIdPolicy = this.getUserIdPolicy();
            UserIdPolicy rhsUserIdPolicy;
            rhsUserIdPolicy = that.getUserIdPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "userIdPolicy", lhsUserIdPolicy), LocatorUtils.property(thatLocator, "userIdPolicy", rhsUserIdPolicy), lhsUserIdPolicy, rhsUserIdPolicy)) {
                return false;
            }
        }
        {
            PasswordPolicy lhsPasswordPolicy;
            lhsPasswordPolicy = this.getPasswordPolicy();
            PasswordPolicy rhsPasswordPolicy;
            rhsPasswordPolicy = that.getPasswordPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "passwordPolicy", lhsPasswordPolicy), LocatorUtils.property(thatLocator, "passwordPolicy", rhsPasswordPolicy), lhsPasswordPolicy, rhsPasswordPolicy)) {
                return false;
            }
        }
        {
            AccountInformationModificationPolicy lhsAccountInformationModificationPolicy;
            lhsAccountInformationModificationPolicy = this.getAccountInformationModificationPolicy();
            AccountInformationModificationPolicy rhsAccountInformationModificationPolicy;
            rhsAccountInformationModificationPolicy = that.getAccountInformationModificationPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountInformationModificationPolicy", lhsAccountInformationModificationPolicy), LocatorUtils.property(thatLocator, "accountInformationModificationPolicy", rhsAccountInformationModificationPolicy), lhsAccountInformationModificationPolicy, rhsAccountInformationModificationPolicy)) {
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
