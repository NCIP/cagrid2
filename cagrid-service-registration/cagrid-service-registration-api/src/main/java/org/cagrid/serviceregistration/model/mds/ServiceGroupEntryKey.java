
package org.cagrid.serviceregistration.model.mds;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://mds.globus.org/aggregator/types}GroupKey"/>
 *         &lt;element ref="{http://mds.globus.org/aggregator/types}EntryKey"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "groupKey",
    "entryKey"
})
@XmlRootElement(name = "ServiceGroupEntryKey")
public class ServiceGroupEntryKey
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "GroupKey", namespace = "http://mds.globus.org/aggregator/types", required = true)
    protected BigInteger groupKey;
    @XmlElement(name = "EntryKey", namespace = "http://mds.globus.org/aggregator/types", required = true)
    protected String entryKey;

    /**
     * Gets the value of the groupKey property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGroupKey() {
        return groupKey;
    }

    /**
     * Sets the value of the groupKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGroupKey(BigInteger value) {
        this.groupKey = value;
    }

    /**
     * Gets the value of the entryKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntryKey() {
        return entryKey;
    }

    /**
     * Sets the value of the entryKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntryKey(String value) {
        this.entryKey = value;
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
            BigInteger theGroupKey;
            theGroupKey = this.getGroupKey();
            strategy.appendField(locator, this, "groupKey", buffer, theGroupKey);
        }
        {
            String theEntryKey;
            theEntryKey = this.getEntryKey();
            strategy.appendField(locator, this, "entryKey", buffer, theEntryKey);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            BigInteger theGroupKey;
            theGroupKey = this.getGroupKey();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "groupKey", theGroupKey), currentHashCode, theGroupKey);
        }
        {
            String theEntryKey;
            theEntryKey = this.getEntryKey();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "entryKey", theEntryKey), currentHashCode, theEntryKey);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceGroupEntryKey)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceGroupEntryKey that = ((ServiceGroupEntryKey) object);
        {
            BigInteger lhsGroupKey;
            lhsGroupKey = this.getGroupKey();
            BigInteger rhsGroupKey;
            rhsGroupKey = that.getGroupKey();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "groupKey", lhsGroupKey), LocatorUtils.property(thatLocator, "groupKey", rhsGroupKey), lhsGroupKey, rhsGroupKey)) {
                return false;
            }
        }
        {
            String lhsEntryKey;
            lhsEntryKey = this.getEntryKey();
            String rhsEntryKey;
            rhsEntryKey = that.getEntryKey();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "entryKey", lhsEntryKey), LocatorUtils.property(thatLocator, "entryKey", rhsEntryKey), lhsEntryKey, rhsEntryKey)) {
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
