
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PasswordLockoutPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PasswordLockoutPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Hours" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Minutes" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Seconds" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ConsecutiveInvalidLogins" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PasswordLockoutPolicy")
public class PasswordLockoutPolicy
    implements Serializable
{

    @XmlAttribute(name = "Hours", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int hours;
    @XmlAttribute(name = "Minutes", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int minutes;
    @XmlAttribute(name = "Seconds", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int seconds;
    @XmlAttribute(name = "ConsecutiveInvalidLogins", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int consecutiveInvalidLogins;

    /**
     * Gets the value of the hours property.
     * 
     */
    public int getHours() {
        return hours;
    }

    /**
     * Sets the value of the hours property.
     * 
     */
    public void setHours(int value) {
        this.hours = value;
    }

    /**
     * Gets the value of the minutes property.
     * 
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Sets the value of the minutes property.
     * 
     */
    public void setMinutes(int value) {
        this.minutes = value;
    }

    /**
     * Gets the value of the seconds property.
     * 
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Sets the value of the seconds property.
     * 
     */
    public void setSeconds(int value) {
        this.seconds = value;
    }

    /**
     * Gets the value of the consecutiveInvalidLogins property.
     * 
     */
    public int getConsecutiveInvalidLogins() {
        return consecutiveInvalidLogins;
    }

    /**
     * Sets the value of the consecutiveInvalidLogins property.
     * 
     */
    public void setConsecutiveInvalidLogins(int value) {
        this.consecutiveInvalidLogins = value;
    }

}
