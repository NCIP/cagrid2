
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
    implements Serializable, Equals, HashCode, ToString
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
            int theHours;
            theHours = (true?this.getHours(): 0);
            strategy.appendField(locator, this, "hours", buffer, theHours);
        }
        {
            int theMinutes;
            theMinutes = (true?this.getMinutes(): 0);
            strategy.appendField(locator, this, "minutes", buffer, theMinutes);
        }
        {
            int theSeconds;
            theSeconds = (true?this.getSeconds(): 0);
            strategy.appendField(locator, this, "seconds", buffer, theSeconds);
        }
        {
            int theConsecutiveInvalidLogins;
            theConsecutiveInvalidLogins = (true?this.getConsecutiveInvalidLogins(): 0);
            strategy.appendField(locator, this, "consecutiveInvalidLogins", buffer, theConsecutiveInvalidLogins);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            int theHours;
            theHours = (true?this.getHours(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hours", theHours), currentHashCode, theHours);
        }
        {
            int theMinutes;
            theMinutes = (true?this.getMinutes(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "minutes", theMinutes), currentHashCode, theMinutes);
        }
        {
            int theSeconds;
            theSeconds = (true?this.getSeconds(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "seconds", theSeconds), currentHashCode, theSeconds);
        }
        {
            int theConsecutiveInvalidLogins;
            theConsecutiveInvalidLogins = (true?this.getConsecutiveInvalidLogins(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "consecutiveInvalidLogins", theConsecutiveInvalidLogins), currentHashCode, theConsecutiveInvalidLogins);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PasswordLockoutPolicy)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PasswordLockoutPolicy that = ((PasswordLockoutPolicy) object);
        {
            int lhsHours;
            lhsHours = (true?this.getHours(): 0);
            int rhsHours;
            rhsHours = (true?that.getHours(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hours", lhsHours), LocatorUtils.property(thatLocator, "hours", rhsHours), lhsHours, rhsHours)) {
                return false;
            }
        }
        {
            int lhsMinutes;
            lhsMinutes = (true?this.getMinutes(): 0);
            int rhsMinutes;
            rhsMinutes = (true?that.getMinutes(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minutes", lhsMinutes), LocatorUtils.property(thatLocator, "minutes", rhsMinutes), lhsMinutes, rhsMinutes)) {
                return false;
            }
        }
        {
            int lhsSeconds;
            lhsSeconds = (true?this.getSeconds(): 0);
            int rhsSeconds;
            rhsSeconds = (true?that.getSeconds(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "seconds", lhsSeconds), LocatorUtils.property(thatLocator, "seconds", rhsSeconds), lhsSeconds, rhsSeconds)) {
                return false;
            }
        }
        {
            int lhsConsecutiveInvalidLogins;
            lhsConsecutiveInvalidLogins = (true?this.getConsecutiveInvalidLogins(): 0);
            int rhsConsecutiveInvalidLogins;
            rhsConsecutiveInvalidLogins = (true?that.getConsecutiveInvalidLogins(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "consecutiveInvalidLogins", lhsConsecutiveInvalidLogins), LocatorUtils.property(thatLocator, "consecutiveInvalidLogins", rhsConsecutiveInvalidLogins), lhsConsecutiveInvalidLogins, rhsConsecutiveInvalidLogins)) {
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
