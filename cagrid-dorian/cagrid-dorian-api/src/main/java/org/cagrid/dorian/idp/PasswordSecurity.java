
package org.cagrid.dorian.idp;

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
    implements Serializable, Equals, HashCode, ToString
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
            long theConsecutiveInvalidLogins;
            theConsecutiveInvalidLogins = (true?this.getConsecutiveInvalidLogins(): 0L);
            strategy.appendField(locator, this, "consecutiveInvalidLogins", buffer, theConsecutiveInvalidLogins);
        }
        {
            long theTotalInvalidLogins;
            theTotalInvalidLogins = (true?this.getTotalInvalidLogins(): 0L);
            strategy.appendField(locator, this, "totalInvalidLogins", buffer, theTotalInvalidLogins);
        }
        {
            long theLockoutExpiration;
            theLockoutExpiration = (true?this.getLockoutExpiration(): 0L);
            strategy.appendField(locator, this, "lockoutExpiration", buffer, theLockoutExpiration);
        }
        {
            String theDigestSalt;
            theDigestSalt = this.getDigestSalt();
            strategy.appendField(locator, this, "digestSalt", buffer, theDigestSalt);
        }
        {
            String theDigestAlgorithm;
            theDigestAlgorithm = this.getDigestAlgorithm();
            strategy.appendField(locator, this, "digestAlgorithm", buffer, theDigestAlgorithm);
        }
        {
            PasswordStatus thePasswordStatus;
            thePasswordStatus = this.getPasswordStatus();
            strategy.appendField(locator, this, "passwordStatus", buffer, thePasswordStatus);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            long theConsecutiveInvalidLogins;
            theConsecutiveInvalidLogins = (true?this.getConsecutiveInvalidLogins(): 0L);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "consecutiveInvalidLogins", theConsecutiveInvalidLogins), currentHashCode, theConsecutiveInvalidLogins);
        }
        {
            long theTotalInvalidLogins;
            theTotalInvalidLogins = (true?this.getTotalInvalidLogins(): 0L);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "totalInvalidLogins", theTotalInvalidLogins), currentHashCode, theTotalInvalidLogins);
        }
        {
            long theLockoutExpiration;
            theLockoutExpiration = (true?this.getLockoutExpiration(): 0L);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lockoutExpiration", theLockoutExpiration), currentHashCode, theLockoutExpiration);
        }
        {
            String theDigestSalt;
            theDigestSalt = this.getDigestSalt();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "digestSalt", theDigestSalt), currentHashCode, theDigestSalt);
        }
        {
            String theDigestAlgorithm;
            theDigestAlgorithm = this.getDigestAlgorithm();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "digestAlgorithm", theDigestAlgorithm), currentHashCode, theDigestAlgorithm);
        }
        {
            PasswordStatus thePasswordStatus;
            thePasswordStatus = this.getPasswordStatus();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "passwordStatus", thePasswordStatus), currentHashCode, thePasswordStatus);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PasswordSecurity)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PasswordSecurity that = ((PasswordSecurity) object);
        {
            long lhsConsecutiveInvalidLogins;
            lhsConsecutiveInvalidLogins = (true?this.getConsecutiveInvalidLogins(): 0L);
            long rhsConsecutiveInvalidLogins;
            rhsConsecutiveInvalidLogins = (true?that.getConsecutiveInvalidLogins(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "consecutiveInvalidLogins", lhsConsecutiveInvalidLogins), LocatorUtils.property(thatLocator, "consecutiveInvalidLogins", rhsConsecutiveInvalidLogins), lhsConsecutiveInvalidLogins, rhsConsecutiveInvalidLogins)) {
                return false;
            }
        }
        {
            long lhsTotalInvalidLogins;
            lhsTotalInvalidLogins = (true?this.getTotalInvalidLogins(): 0L);
            long rhsTotalInvalidLogins;
            rhsTotalInvalidLogins = (true?that.getTotalInvalidLogins(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "totalInvalidLogins", lhsTotalInvalidLogins), LocatorUtils.property(thatLocator, "totalInvalidLogins", rhsTotalInvalidLogins), lhsTotalInvalidLogins, rhsTotalInvalidLogins)) {
                return false;
            }
        }
        {
            long lhsLockoutExpiration;
            lhsLockoutExpiration = (true?this.getLockoutExpiration(): 0L);
            long rhsLockoutExpiration;
            rhsLockoutExpiration = (true?that.getLockoutExpiration(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lockoutExpiration", lhsLockoutExpiration), LocatorUtils.property(thatLocator, "lockoutExpiration", rhsLockoutExpiration), lhsLockoutExpiration, rhsLockoutExpiration)) {
                return false;
            }
        }
        {
            String lhsDigestSalt;
            lhsDigestSalt = this.getDigestSalt();
            String rhsDigestSalt;
            rhsDigestSalt = that.getDigestSalt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "digestSalt", lhsDigestSalt), LocatorUtils.property(thatLocator, "digestSalt", rhsDigestSalt), lhsDigestSalt, rhsDigestSalt)) {
                return false;
            }
        }
        {
            String lhsDigestAlgorithm;
            lhsDigestAlgorithm = this.getDigestAlgorithm();
            String rhsDigestAlgorithm;
            rhsDigestAlgorithm = that.getDigestAlgorithm();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "digestAlgorithm", lhsDigestAlgorithm), LocatorUtils.property(thatLocator, "digestAlgorithm", rhsDigestAlgorithm), lhsDigestAlgorithm, rhsDigestAlgorithm)) {
                return false;
            }
        }
        {
            PasswordStatus lhsPasswordStatus;
            lhsPasswordStatus = this.getPasswordStatus();
            PasswordStatus rhsPasswordStatus;
            rhsPasswordStatus = that.getPasswordStatus();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "passwordStatus", lhsPasswordStatus), LocatorUtils.property(thatLocator, "passwordStatus", rhsPasswordStatus), lhsPasswordStatus, rhsPasswordStatus)) {
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
