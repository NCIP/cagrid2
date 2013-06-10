
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
 * <p>Java class for HostPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HostPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}HostCertificateLifetime"/>
 *       &lt;/sequence>
 *       &lt;attribute name="AdministrativeApprovalRequired" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="HostCertificateRenewalPolicy" use="required" type="{http://cagrid.nci.nih.gov/1/dorian-policy}HostCertificateRenewalPolicy" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HostPolicy", propOrder = {
    "hostCertificateLifetime"
})
public class HostPolicy
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "HostCertificateLifetime", required = true)
    protected HostCertificateLifetime hostCertificateLifetime;
    @XmlAttribute(name = "AdministrativeApprovalRequired", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected boolean administrativeApprovalRequired;
    @XmlAttribute(name = "HostCertificateRenewalPolicy", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected HostCertificateRenewalPolicy hostCertificateRenewalPolicy;

    /**
     * Gets the value of the hostCertificateLifetime property.
     * 
     * @return
     *     possible object is
     *     {@link HostCertificateLifetime }
     *     
     */
    public HostCertificateLifetime getHostCertificateLifetime() {
        return hostCertificateLifetime;
    }

    /**
     * Sets the value of the hostCertificateLifetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link HostCertificateLifetime }
     *     
     */
    public void setHostCertificateLifetime(HostCertificateLifetime value) {
        this.hostCertificateLifetime = value;
    }

    /**
     * Gets the value of the administrativeApprovalRequired property.
     * 
     */
    public boolean isAdministrativeApprovalRequired() {
        return administrativeApprovalRequired;
    }

    /**
     * Sets the value of the administrativeApprovalRequired property.
     * 
     */
    public void setAdministrativeApprovalRequired(boolean value) {
        this.administrativeApprovalRequired = value;
    }

    /**
     * Gets the value of the hostCertificateRenewalPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link HostCertificateRenewalPolicy }
     *     
     */
    public HostCertificateRenewalPolicy getHostCertificateRenewalPolicy() {
        return hostCertificateRenewalPolicy;
    }

    /**
     * Sets the value of the hostCertificateRenewalPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link HostCertificateRenewalPolicy }
     *     
     */
    public void setHostCertificateRenewalPolicy(HostCertificateRenewalPolicy value) {
        this.hostCertificateRenewalPolicy = value;
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
            HostCertificateLifetime theHostCertificateLifetime;
            theHostCertificateLifetime = this.getHostCertificateLifetime();
            strategy.appendField(locator, this, "hostCertificateLifetime", buffer, theHostCertificateLifetime);
        }
        {
            boolean theAdministrativeApprovalRequired;
            theAdministrativeApprovalRequired = (true?this.isAdministrativeApprovalRequired():false);
            strategy.appendField(locator, this, "administrativeApprovalRequired", buffer, theAdministrativeApprovalRequired);
        }
        {
            HostCertificateRenewalPolicy theHostCertificateRenewalPolicy;
            theHostCertificateRenewalPolicy = this.getHostCertificateRenewalPolicy();
            strategy.appendField(locator, this, "hostCertificateRenewalPolicy", buffer, theHostCertificateRenewalPolicy);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            HostCertificateLifetime theHostCertificateLifetime;
            theHostCertificateLifetime = this.getHostCertificateLifetime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostCertificateLifetime", theHostCertificateLifetime), currentHashCode, theHostCertificateLifetime);
        }
        {
            boolean theAdministrativeApprovalRequired;
            theAdministrativeApprovalRequired = (true?this.isAdministrativeApprovalRequired():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "administrativeApprovalRequired", theAdministrativeApprovalRequired), currentHashCode, theAdministrativeApprovalRequired);
        }
        {
            HostCertificateRenewalPolicy theHostCertificateRenewalPolicy;
            theHostCertificateRenewalPolicy = this.getHostCertificateRenewalPolicy();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostCertificateRenewalPolicy", theHostCertificateRenewalPolicy), currentHashCode, theHostCertificateRenewalPolicy);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof HostPolicy)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final HostPolicy that = ((HostPolicy) object);
        {
            HostCertificateLifetime lhsHostCertificateLifetime;
            lhsHostCertificateLifetime = this.getHostCertificateLifetime();
            HostCertificateLifetime rhsHostCertificateLifetime;
            rhsHostCertificateLifetime = that.getHostCertificateLifetime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hostCertificateLifetime", lhsHostCertificateLifetime), LocatorUtils.property(thatLocator, "hostCertificateLifetime", rhsHostCertificateLifetime), lhsHostCertificateLifetime, rhsHostCertificateLifetime)) {
                return false;
            }
        }
        {
            boolean lhsAdministrativeApprovalRequired;
            lhsAdministrativeApprovalRequired = (true?this.isAdministrativeApprovalRequired():false);
            boolean rhsAdministrativeApprovalRequired;
            rhsAdministrativeApprovalRequired = (true?that.isAdministrativeApprovalRequired():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "administrativeApprovalRequired", lhsAdministrativeApprovalRequired), LocatorUtils.property(thatLocator, "administrativeApprovalRequired", rhsAdministrativeApprovalRequired), lhsAdministrativeApprovalRequired, rhsAdministrativeApprovalRequired)) {
                return false;
            }
        }
        {
            HostCertificateRenewalPolicy lhsHostCertificateRenewalPolicy;
            lhsHostCertificateRenewalPolicy = this.getHostCertificateRenewalPolicy();
            HostCertificateRenewalPolicy rhsHostCertificateRenewalPolicy;
            rhsHostCertificateRenewalPolicy = that.getHostCertificateRenewalPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hostCertificateRenewalPolicy", lhsHostCertificateRenewalPolicy), LocatorUtils.property(thatLocator, "hostCertificateRenewalPolicy", rhsHostCertificateRenewalPolicy), lhsHostCertificateRenewalPolicy, rhsHostCertificateRenewalPolicy)) {
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
