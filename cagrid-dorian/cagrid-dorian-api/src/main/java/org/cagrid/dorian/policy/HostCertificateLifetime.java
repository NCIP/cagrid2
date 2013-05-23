
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


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
    implements Serializable
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

}
