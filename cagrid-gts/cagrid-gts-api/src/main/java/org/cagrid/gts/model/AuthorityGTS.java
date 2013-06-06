
package org.cagrid.gts.model;

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
 * <p>Java class for AuthorityGTS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthorityGTS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceURI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Priority" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SyncTrustLevels" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TimeToLive" type="{http://cagrid.nci.nih.gov/8/gts}TimeToLive"/>
 *         &lt;element name="PerformAuthorization" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ServiceIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorityGTS", propOrder = {
    "serviceURI",
    "priority",
    "syncTrustLevels",
    "timeToLive",
    "performAuthorization",
    "serviceIdentity"
})
public class AuthorityGTS
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "ServiceURI", required = true)
    protected String serviceURI;
    @XmlElement(name = "Priority")
    protected int priority;
    @XmlElement(name = "SyncTrustLevels")
    protected boolean syncTrustLevels;
    @XmlElement(name = "TimeToLive", required = true)
    protected TimeToLive timeToLive;
    @XmlElement(name = "PerformAuthorization")
    protected boolean performAuthorization;
    @XmlElement(name = "ServiceIdentity")
    protected String serviceIdentity;

    /**
     * Gets the value of the serviceURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceURI() {
        return serviceURI;
    }

    /**
     * Sets the value of the serviceURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceURI(String value) {
        this.serviceURI = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     */
    public void setPriority(int value) {
        this.priority = value;
    }

    /**
     * Gets the value of the syncTrustLevels property.
     * 
     */
    public boolean isSyncTrustLevels() {
        return syncTrustLevels;
    }

    /**
     * Sets the value of the syncTrustLevels property.
     * 
     */
    public void setSyncTrustLevels(boolean value) {
        this.syncTrustLevels = value;
    }

    /**
     * Gets the value of the timeToLive property.
     * 
     * @return
     *     possible object is
     *     {@link TimeToLive }
     *     
     */
    public TimeToLive getTimeToLive() {
        return timeToLive;
    }

    /**
     * Sets the value of the timeToLive property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeToLive }
     *     
     */
    public void setTimeToLive(TimeToLive value) {
        this.timeToLive = value;
    }

    /**
     * Gets the value of the performAuthorization property.
     * 
     */
    public boolean isPerformAuthorization() {
        return performAuthorization;
    }

    /**
     * Sets the value of the performAuthorization property.
     * 
     */
    public void setPerformAuthorization(boolean value) {
        this.performAuthorization = value;
    }

    /**
     * Gets the value of the serviceIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceIdentity() {
        return serviceIdentity;
    }

    /**
     * Sets the value of the serviceIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceIdentity(String value) {
        this.serviceIdentity = value;
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
            String theServiceURI;
            theServiceURI = this.getServiceURI();
            strategy.appendField(locator, this, "serviceURI", buffer, theServiceURI);
        }
        {
            int thePriority;
            thePriority = (true?this.getPriority(): 0);
            strategy.appendField(locator, this, "priority", buffer, thePriority);
        }
        {
            boolean theSyncTrustLevels;
            theSyncTrustLevels = (true?this.isSyncTrustLevels():false);
            strategy.appendField(locator, this, "syncTrustLevels", buffer, theSyncTrustLevels);
        }
        {
            TimeToLive theTimeToLive;
            theTimeToLive = this.getTimeToLive();
            strategy.appendField(locator, this, "timeToLive", buffer, theTimeToLive);
        }
        {
            boolean thePerformAuthorization;
            thePerformAuthorization = (true?this.isPerformAuthorization():false);
            strategy.appendField(locator, this, "performAuthorization", buffer, thePerformAuthorization);
        }
        {
            String theServiceIdentity;
            theServiceIdentity = this.getServiceIdentity();
            strategy.appendField(locator, this, "serviceIdentity", buffer, theServiceIdentity);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theServiceURI;
            theServiceURI = this.getServiceURI();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "serviceURI", theServiceURI), currentHashCode, theServiceURI);
        }
        {
            int thePriority;
            thePriority = (true?this.getPriority(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "priority", thePriority), currentHashCode, thePriority);
        }
        {
            boolean theSyncTrustLevels;
            theSyncTrustLevels = (true?this.isSyncTrustLevels():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "syncTrustLevels", theSyncTrustLevels), currentHashCode, theSyncTrustLevels);
        }
        {
            TimeToLive theTimeToLive;
            theTimeToLive = this.getTimeToLive();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "timeToLive", theTimeToLive), currentHashCode, theTimeToLive);
        }
        {
            boolean thePerformAuthorization;
            thePerformAuthorization = (true?this.isPerformAuthorization():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "performAuthorization", thePerformAuthorization), currentHashCode, thePerformAuthorization);
        }
        {
            String theServiceIdentity;
            theServiceIdentity = this.getServiceIdentity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "serviceIdentity", theServiceIdentity), currentHashCode, theServiceIdentity);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AuthorityGTS)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AuthorityGTS that = ((AuthorityGTS) object);
        {
            String lhsServiceURI;
            lhsServiceURI = this.getServiceURI();
            String rhsServiceURI;
            rhsServiceURI = that.getServiceURI();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceURI", lhsServiceURI), LocatorUtils.property(thatLocator, "serviceURI", rhsServiceURI), lhsServiceURI, rhsServiceURI)) {
                return false;
            }
        }
        {
            int lhsPriority;
            lhsPriority = (true?this.getPriority(): 0);
            int rhsPriority;
            rhsPriority = (true?that.getPriority(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "priority", lhsPriority), LocatorUtils.property(thatLocator, "priority", rhsPriority), lhsPriority, rhsPriority)) {
                return false;
            }
        }
        {
            boolean lhsSyncTrustLevels;
            lhsSyncTrustLevels = (true?this.isSyncTrustLevels():false);
            boolean rhsSyncTrustLevels;
            rhsSyncTrustLevels = (true?that.isSyncTrustLevels():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "syncTrustLevels", lhsSyncTrustLevels), LocatorUtils.property(thatLocator, "syncTrustLevels", rhsSyncTrustLevels), lhsSyncTrustLevels, rhsSyncTrustLevels)) {
                return false;
            }
        }
        {
            TimeToLive lhsTimeToLive;
            lhsTimeToLive = this.getTimeToLive();
            TimeToLive rhsTimeToLive;
            rhsTimeToLive = that.getTimeToLive();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "timeToLive", lhsTimeToLive), LocatorUtils.property(thatLocator, "timeToLive", rhsTimeToLive), lhsTimeToLive, rhsTimeToLive)) {
                return false;
            }
        }
        {
            boolean lhsPerformAuthorization;
            lhsPerformAuthorization = (true?this.isPerformAuthorization():false);
            boolean rhsPerformAuthorization;
            rhsPerformAuthorization = (true?that.isPerformAuthorization():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "performAuthorization", lhsPerformAuthorization), LocatorUtils.property(thatLocator, "performAuthorization", rhsPerformAuthorization), lhsPerformAuthorization, rhsPerformAuthorization)) {
                return false;
            }
        }
        {
            String lhsServiceIdentity;
            lhsServiceIdentity = this.getServiceIdentity();
            String rhsServiceIdentity;
            rhsServiceIdentity = that.getServiceIdentity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceIdentity", lhsServiceIdentity), LocatorUtils.property(thatLocator, "serviceIdentity", rhsServiceIdentity), lhsServiceIdentity, rhsServiceIdentity)) {
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
