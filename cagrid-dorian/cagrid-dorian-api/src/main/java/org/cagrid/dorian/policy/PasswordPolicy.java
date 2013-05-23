
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
    implements Serializable
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

}
