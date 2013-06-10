
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
 * <p>Java class for HostCertificateLifetime complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HostCertificateLifetime">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Years" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Months" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Days" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Hours" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Minutes" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Seconds" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HostCertificateLifetime")
public class HostCertificateLifetime
    implements Serializable, Equals, HashCode, ToString
{

    @XmlAttribute(name = "Years", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int years;
    @XmlAttribute(name = "Months", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int months;
    @XmlAttribute(name = "Days", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int days;
    @XmlAttribute(name = "Hours", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int hours;
    @XmlAttribute(name = "Minutes", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int minutes;
    @XmlAttribute(name = "Seconds", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int seconds;

    /**
     * Gets the value of the years property.
     * 
     */
    public int getYears() {
        return years;
    }

    /**
     * Sets the value of the years property.
     * 
     */
    public void setYears(int value) {
        this.years = value;
    }

    /**
     * Gets the value of the months property.
     * 
     */
    public int getMonths() {
        return months;
    }

    /**
     * Sets the value of the months property.
     * 
     */
    public void setMonths(int value) {
        this.months = value;
    }

    /**
     * Gets the value of the days property.
     * 
     */
    public int getDays() {
        return days;
    }

    /**
     * Sets the value of the days property.
     * 
     */
    public void setDays(int value) {
        this.days = value;
    }

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
            int theYears;
            theYears = (true?this.getYears(): 0);
            strategy.appendField(locator, this, "years", buffer, theYears);
        }
        {
            int theMonths;
            theMonths = (true?this.getMonths(): 0);
            strategy.appendField(locator, this, "months", buffer, theMonths);
        }
        {
            int theDays;
            theDays = (true?this.getDays(): 0);
            strategy.appendField(locator, this, "days", buffer, theDays);
        }
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
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            int theYears;
            theYears = (true?this.getYears(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "years", theYears), currentHashCode, theYears);
        }
        {
            int theMonths;
            theMonths = (true?this.getMonths(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "months", theMonths), currentHashCode, theMonths);
        }
        {
            int theDays;
            theDays = (true?this.getDays(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "days", theDays), currentHashCode, theDays);
        }
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
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof HostCertificateLifetime)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final HostCertificateLifetime that = ((HostCertificateLifetime) object);
        {
            int lhsYears;
            lhsYears = (true?this.getYears(): 0);
            int rhsYears;
            rhsYears = (true?that.getYears(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "years", lhsYears), LocatorUtils.property(thatLocator, "years", rhsYears), lhsYears, rhsYears)) {
                return false;
            }
        }
        {
            int lhsMonths;
            lhsMonths = (true?this.getMonths(): 0);
            int rhsMonths;
            rhsMonths = (true?that.getMonths(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "months", lhsMonths), LocatorUtils.property(thatLocator, "months", rhsMonths), lhsMonths, rhsMonths)) {
                return false;
            }
        }
        {
            int lhsDays;
            lhsDays = (true?this.getDays(): 0);
            int rhsDays;
            rhsDays = (true?that.getDays(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "days", lhsDays), LocatorUtils.property(thatLocator, "days", rhsDays), lhsDays, rhsDays)) {
                return false;
            }
        }
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
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

}
