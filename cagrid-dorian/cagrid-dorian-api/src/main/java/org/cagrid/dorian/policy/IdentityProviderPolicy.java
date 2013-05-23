
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
    implements Serializable
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

}
