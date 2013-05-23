
package org.cagrid.dorian.idp;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PasswordSecurity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PasswordSecurity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConsecutiveInvalidLogins" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="TotalInvalidLogins" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="LockoutExpiration" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="DigestSalt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DigestAlgorithm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PasswordStatus" type="{http://cagrid.nci.nih.gov/1/dorian-idp}PasswordStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PasswordSecurity", propOrder = {
    "consecutiveInvalidLogins",
    "totalInvalidLogins",
    "lockoutExpiration",
    "digestSalt",
    "digestAlgorithm",
    "passwordStatus"
})
public class PasswordSecurity
    implements Serializable
{

    @XmlElement(name = "ConsecutiveInvalidLogins")
    protected long consecutiveInvalidLogins;
    @XmlElement(name = "TotalInvalidLogins")
    protected long totalInvalidLogins;
    @XmlElement(name = "LockoutExpiration")
    protected long lockoutExpiration;
    @XmlElement(name = "DigestSalt", required = true)
    protected String digestSalt;
    @XmlElement(name = "DigestAlgorithm", required = true)
    protected String digestAlgorithm;
    @XmlElement(name = "PasswordStatus", required = true)
    protected PasswordStatus passwordStatus;

    /**
     * Gets the value of the consecutiveInvalidLogins property.
     * 
     */
    public long getConsecutiveInvalidLogins() {
        return consecutiveInvalidLogins;
    }

    /**
     * Sets the value of the consecutiveInvalidLogins property.
     * 
     */
    public void setConsecutiveInvalidLogins(long value) {
        this.consecutiveInvalidLogins = value;
    }

    /**
     * Gets the value of the totalInvalidLogins property.
     * 
     */
    public long getTotalInvalidLogins() {
        return totalInvalidLogins;
    }

    /**
     * Sets the value of the totalInvalidLogins property.
     * 
     */
    public void setTotalInvalidLogins(long value) {
        this.totalInvalidLogins = value;
    }

    /**
     * Gets the value of the lockoutExpiration property.
     * 
     */
    public long getLockoutExpiration() {
        return lockoutExpiration;
    }

    /**
     * Sets the value of the lockoutExpiration property.
     * 
     */
    public void setLockoutExpiration(long value) {
        this.lockoutExpiration = value;
    }

    /**
     * Gets the value of the digestSalt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDigestSalt() {
        return digestSalt;
    }

    /**
     * Sets the value of the digestSalt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDigestSalt(String value) {
        this.digestSalt = value;
    }

    /**
     * Gets the value of the digestAlgorithm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDigestAlgorithm() {
        return digestAlgorithm;
    }

    /**
     * Sets the value of the digestAlgorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDigestAlgorithm(String value) {
        this.digestAlgorithm = value;
    }

    /**
     * Gets the value of the passwordStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PasswordStatus }
     *     
     */
    public PasswordStatus getPasswordStatus() {
        return passwordStatus;
    }

    /**
     * Sets the value of the passwordStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PasswordStatus }
     *     
     */
    public void setPasswordStatus(PasswordStatus value) {
        this.passwordStatus = value;
    }

}
