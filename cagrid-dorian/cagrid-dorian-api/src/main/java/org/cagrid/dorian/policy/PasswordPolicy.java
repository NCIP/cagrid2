
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
 * <p>Java class for PasswordPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PasswordPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SymbolsSupported" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}PasswordLockoutPolicy"/>
 *       &lt;/sequence>
 *       &lt;attribute name="MinLength" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="MaxLength" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="DictionaryWordsAllowed" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="UpperCaseLetterRequired" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="LowerCaseLetterRequired" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="SymbolRequired" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="NumericRequired" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PasswordPolicy", propOrder = {
    "description",
    "symbolsSupported",
    "passwordLockoutPolicy"
})
public class PasswordPolicy
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "SymbolsSupported", required = true)
    protected String symbolsSupported;
    @XmlElement(name = "PasswordLockoutPolicy", required = true)
    protected PasswordLockoutPolicy passwordLockoutPolicy;
    @XmlAttribute(name = "MinLength", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int minLength;
    @XmlAttribute(name = "MaxLength", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int maxLength;
    @XmlAttribute(name = "DictionaryWordsAllowed", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected boolean dictionaryWordsAllowed;
    @XmlAttribute(name = "UpperCaseLetterRequired", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected boolean upperCaseLetterRequired;
    @XmlAttribute(name = "LowerCaseLetterRequired", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected boolean lowerCaseLetterRequired;
    @XmlAttribute(name = "SymbolRequired", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected boolean symbolRequired;
    @XmlAttribute(name = "NumericRequired", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected boolean numericRequired;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the symbolsSupported property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSymbolsSupported() {
        return symbolsSupported;
    }

    /**
     * Sets the value of the symbolsSupported property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSymbolsSupported(String value) {
        this.symbolsSupported = value;
    }

    /**
     * Gets the value of the passwordLockoutPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link PasswordLockoutPolicy }
     *     
     */
    public PasswordLockoutPolicy getPasswordLockoutPolicy() {
        return passwordLockoutPolicy;
    }

    /**
     * Sets the value of the passwordLockoutPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link PasswordLockoutPolicy }
     *     
     */
    public void setPasswordLockoutPolicy(PasswordLockoutPolicy value) {
        this.passwordLockoutPolicy = value;
    }

    /**
     * Gets the value of the minLength property.
     * 
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * Sets the value of the minLength property.
     * 
     */
    public void setMinLength(int value) {
        this.minLength = value;
    }

    /**
     * Gets the value of the maxLength property.
     * 
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * Sets the value of the maxLength property.
     * 
     */
    public void setMaxLength(int value) {
        this.maxLength = value;
    }

    /**
     * Gets the value of the dictionaryWordsAllowed property.
     * 
     */
    public boolean isDictionaryWordsAllowed() {
        return dictionaryWordsAllowed;
    }

    /**
     * Sets the value of the dictionaryWordsAllowed property.
     * 
     */
    public void setDictionaryWordsAllowed(boolean value) {
        this.dictionaryWordsAllowed = value;
    }

    /**
     * Gets the value of the upperCaseLetterRequired property.
     * 
     */
    public boolean isUpperCaseLetterRequired() {
        return upperCaseLetterRequired;
    }

    /**
     * Sets the value of the upperCaseLetterRequired property.
     * 
     */
    public void setUpperCaseLetterRequired(boolean value) {
        this.upperCaseLetterRequired = value;
    }

    /**
     * Gets the value of the lowerCaseLetterRequired property.
     * 
     */
    public boolean isLowerCaseLetterRequired() {
        return lowerCaseLetterRequired;
    }

    /**
     * Sets the value of the lowerCaseLetterRequired property.
     * 
     */
    public void setLowerCaseLetterRequired(boolean value) {
        this.lowerCaseLetterRequired = value;
    }

    /**
     * Gets the value of the symbolRequired property.
     * 
     */
    public boolean isSymbolRequired() {
        return symbolRequired;
    }

    /**
     * Sets the value of the symbolRequired property.
     * 
     */
    public void setSymbolRequired(boolean value) {
        this.symbolRequired = value;
    }

    /**
     * Gets the value of the numericRequired property.
     * 
     */
    public boolean isNumericRequired() {
        return numericRequired;
    }

    /**
     * Sets the value of the numericRequired property.
     * 
     */
    public void setNumericRequired(boolean value) {
        this.numericRequired = value;
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
            String theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            String theSymbolsSupported;
            theSymbolsSupported = this.getSymbolsSupported();
            strategy.appendField(locator, this, "symbolsSupported", buffer, theSymbolsSupported);
        }
        {
            PasswordLockoutPolicy thePasswordLockoutPolicy;
            thePasswordLockoutPolicy = this.getPasswordLockoutPolicy();
            strategy.appendField(locator, this, "passwordLockoutPolicy", buffer, thePasswordLockoutPolicy);
        }
        {
            int theMinLength;
            theMinLength = (true?this.getMinLength(): 0);
            strategy.appendField(locator, this, "minLength", buffer, theMinLength);
        }
        {
            int theMaxLength;
            theMaxLength = (true?this.getMaxLength(): 0);
            strategy.appendField(locator, this, "maxLength", buffer, theMaxLength);
        }
        {
            boolean theDictionaryWordsAllowed;
            theDictionaryWordsAllowed = (true?this.isDictionaryWordsAllowed():false);
            strategy.appendField(locator, this, "dictionaryWordsAllowed", buffer, theDictionaryWordsAllowed);
        }
        {
            boolean theUpperCaseLetterRequired;
            theUpperCaseLetterRequired = (true?this.isUpperCaseLetterRequired():false);
            strategy.appendField(locator, this, "upperCaseLetterRequired", buffer, theUpperCaseLetterRequired);
        }
        {
            boolean theLowerCaseLetterRequired;
            theLowerCaseLetterRequired = (true?this.isLowerCaseLetterRequired():false);
            strategy.appendField(locator, this, "lowerCaseLetterRequired", buffer, theLowerCaseLetterRequired);
        }
        {
            boolean theSymbolRequired;
            theSymbolRequired = (true?this.isSymbolRequired():false);
            strategy.appendField(locator, this, "symbolRequired", buffer, theSymbolRequired);
        }
        {
            boolean theNumericRequired;
            theNumericRequired = (true?this.isNumericRequired():false);
            strategy.appendField(locator, this, "numericRequired", buffer, theNumericRequired);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);
        }
        {
            String theSymbolsSupported;
            theSymbolsSupported = this.getSymbolsSupported();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "symbolsSupported", theSymbolsSupported), currentHashCode, theSymbolsSupported);
        }
        {
            PasswordLockoutPolicy thePasswordLockoutPolicy;
            thePasswordLockoutPolicy = this.getPasswordLockoutPolicy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "passwordLockoutPolicy", thePasswordLockoutPolicy), currentHashCode, thePasswordLockoutPolicy);
        }
        {
            int theMinLength;
            theMinLength = (true?this.getMinLength(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "minLength", theMinLength), currentHashCode, theMinLength);
        }
        {
            int theMaxLength;
            theMaxLength = (true?this.getMaxLength(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maxLength", theMaxLength), currentHashCode, theMaxLength);
        }
        {
            boolean theDictionaryWordsAllowed;
            theDictionaryWordsAllowed = (true?this.isDictionaryWordsAllowed():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dictionaryWordsAllowed", theDictionaryWordsAllowed), currentHashCode, theDictionaryWordsAllowed);
        }
        {
            boolean theUpperCaseLetterRequired;
            theUpperCaseLetterRequired = (true?this.isUpperCaseLetterRequired():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "upperCaseLetterRequired", theUpperCaseLetterRequired), currentHashCode, theUpperCaseLetterRequired);
        }
        {
            boolean theLowerCaseLetterRequired;
            theLowerCaseLetterRequired = (true?this.isLowerCaseLetterRequired():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lowerCaseLetterRequired", theLowerCaseLetterRequired), currentHashCode, theLowerCaseLetterRequired);
        }
        {
            boolean theSymbolRequired;
            theSymbolRequired = (true?this.isSymbolRequired():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "symbolRequired", theSymbolRequired), currentHashCode, theSymbolRequired);
        }
        {
            boolean theNumericRequired;
            theNumericRequired = (true?this.isNumericRequired():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "numericRequired", theNumericRequired), currentHashCode, theNumericRequired);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PasswordPolicy)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PasswordPolicy that = ((PasswordPolicy) object);
        {
            String lhsDescription;
            lhsDescription = this.getDescription();
            String rhsDescription;
            rhsDescription = that.getDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
                return false;
            }
        }
        {
            String lhsSymbolsSupported;
            lhsSymbolsSupported = this.getSymbolsSupported();
            String rhsSymbolsSupported;
            rhsSymbolsSupported = that.getSymbolsSupported();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "symbolsSupported", lhsSymbolsSupported), LocatorUtils.property(thatLocator, "symbolsSupported", rhsSymbolsSupported), lhsSymbolsSupported, rhsSymbolsSupported)) {
                return false;
            }
        }
        {
            PasswordLockoutPolicy lhsPasswordLockoutPolicy;
            lhsPasswordLockoutPolicy = this.getPasswordLockoutPolicy();
            PasswordLockoutPolicy rhsPasswordLockoutPolicy;
            rhsPasswordLockoutPolicy = that.getPasswordLockoutPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "passwordLockoutPolicy", lhsPasswordLockoutPolicy), LocatorUtils.property(thatLocator, "passwordLockoutPolicy", rhsPasswordLockoutPolicy), lhsPasswordLockoutPolicy, rhsPasswordLockoutPolicy)) {
                return false;
            }
        }
        {
            int lhsMinLength;
            lhsMinLength = (true?this.getMinLength(): 0);
            int rhsMinLength;
            rhsMinLength = (true?that.getMinLength(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minLength", lhsMinLength), LocatorUtils.property(thatLocator, "minLength", rhsMinLength), lhsMinLength, rhsMinLength)) {
                return false;
            }
        }
        {
            int lhsMaxLength;
            lhsMaxLength = (true?this.getMaxLength(): 0);
            int rhsMaxLength;
            rhsMaxLength = (true?that.getMaxLength(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxLength", lhsMaxLength), LocatorUtils.property(thatLocator, "maxLength", rhsMaxLength), lhsMaxLength, rhsMaxLength)) {
                return false;
            }
        }
        {
            boolean lhsDictionaryWordsAllowed;
            lhsDictionaryWordsAllowed = (true?this.isDictionaryWordsAllowed():false);
            boolean rhsDictionaryWordsAllowed;
            rhsDictionaryWordsAllowed = (true?that.isDictionaryWordsAllowed():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dictionaryWordsAllowed", lhsDictionaryWordsAllowed), LocatorUtils.property(thatLocator, "dictionaryWordsAllowed", rhsDictionaryWordsAllowed), lhsDictionaryWordsAllowed, rhsDictionaryWordsAllowed)) {
                return false;
            }
        }
        {
            boolean lhsUpperCaseLetterRequired;
            lhsUpperCaseLetterRequired = (true?this.isUpperCaseLetterRequired():false);
            boolean rhsUpperCaseLetterRequired;
            rhsUpperCaseLetterRequired = (true?that.isUpperCaseLetterRequired():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "upperCaseLetterRequired", lhsUpperCaseLetterRequired), LocatorUtils.property(thatLocator, "upperCaseLetterRequired", rhsUpperCaseLetterRequired), lhsUpperCaseLetterRequired, rhsUpperCaseLetterRequired)) {
                return false;
            }
        }
        {
            boolean lhsLowerCaseLetterRequired;
            lhsLowerCaseLetterRequired = (true?this.isLowerCaseLetterRequired():false);
            boolean rhsLowerCaseLetterRequired;
            rhsLowerCaseLetterRequired = (true?that.isLowerCaseLetterRequired():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lowerCaseLetterRequired", lhsLowerCaseLetterRequired), LocatorUtils.property(thatLocator, "lowerCaseLetterRequired", rhsLowerCaseLetterRequired), lhsLowerCaseLetterRequired, rhsLowerCaseLetterRequired)) {
                return false;
            }
        }
        {
            boolean lhsSymbolRequired;
            lhsSymbolRequired = (true?this.isSymbolRequired():false);
            boolean rhsSymbolRequired;
            rhsSymbolRequired = (true?that.isSymbolRequired():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "symbolRequired", lhsSymbolRequired), LocatorUtils.property(thatLocator, "symbolRequired", rhsSymbolRequired), lhsSymbolRequired, rhsSymbolRequired)) {
                return false;
            }
        }
        {
            boolean lhsNumericRequired;
            lhsNumericRequired = (true?this.isNumericRequired():false);
            boolean rhsNumericRequired;
            rhsNumericRequired = (true?that.isNumericRequired():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "numericRequired", lhsNumericRequired), LocatorUtils.property(thatLocator, "numericRequired", rhsNumericRequired), lhsNumericRequired, rhsNumericRequired)) {
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
