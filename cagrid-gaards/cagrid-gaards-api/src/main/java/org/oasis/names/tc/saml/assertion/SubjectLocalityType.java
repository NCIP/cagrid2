
package org.oasis.names.tc.saml.assertion;

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
 * <p>Java class for SubjectLocalityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubjectLocalityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="IPAddress" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DNSAddress" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectLocalityType")
public class SubjectLocalityType
    implements Serializable, Equals, HashCode, ToString
{

    @XmlAttribute(name = "IPAddress")
    protected String ipAddress;
    @XmlAttribute(name = "DNSAddress")
    protected String dnsAddress;

    /**
     * Gets the value of the ipAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIPAddress() {
        return ipAddress;
    }

    /**
     * Sets the value of the ipAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIPAddress(String value) {
        this.ipAddress = value;
    }

    /**
     * Gets the value of the dnsAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDNSAddress() {
        return dnsAddress;
    }

    /**
     * Sets the value of the dnsAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDNSAddress(String value) {
        this.dnsAddress = value;
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
            String theIPAddress;
            theIPAddress = this.getIPAddress();
            strategy.appendField(locator, this, "ipAddress", buffer, theIPAddress);
        }
        {
            String theDNSAddress;
            theDNSAddress = this.getDNSAddress();
            strategy.appendField(locator, this, "dnsAddress", buffer, theDNSAddress);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theIPAddress;
            theIPAddress = this.getIPAddress();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ipAddress", theIPAddress), currentHashCode, theIPAddress);
        }
        {
            String theDNSAddress;
            theDNSAddress = this.getDNSAddress();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dnsAddress", theDNSAddress), currentHashCode, theDNSAddress);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SubjectLocalityType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SubjectLocalityType that = ((SubjectLocalityType) object);
        {
            String lhsIPAddress;
            lhsIPAddress = this.getIPAddress();
            String rhsIPAddress;
            rhsIPAddress = that.getIPAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ipAddress", lhsIPAddress), LocatorUtils.property(thatLocator, "ipAddress", rhsIPAddress), lhsIPAddress, rhsIPAddress)) {
                return false;
            }
        }
        {
            String lhsDNSAddress;
            lhsDNSAddress = this.getDNSAddress();
            String rhsDNSAddress;
            rhsDNSAddress = that.getDNSAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dnsAddress", lhsDNSAddress), LocatorUtils.property(thatLocator, "dnsAddress", rhsDNSAddress), lhsDNSAddress, rhsDNSAddress)) {
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
