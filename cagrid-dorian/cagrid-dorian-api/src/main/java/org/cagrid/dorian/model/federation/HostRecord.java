
package org.cagrid.dorian.model.federation;

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
 * <p>Java class for HostRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HostRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hostname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hostCertificateSubject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="owner" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ownerFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ownerLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ownerEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HostRecord", propOrder = {
    "identity",
    "hostname",
    "hostCertificateSubject",
    "owner",
    "ownerFirstName",
    "ownerLastName",
    "ownerEmail"
})
public class HostRecord
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected String identity;
    @XmlElement(required = true)
    protected String hostname;
    @XmlElement(required = true)
    protected String hostCertificateSubject;
    @XmlElement(required = true)
    protected String owner;
    @XmlElement(required = true)
    protected String ownerFirstName;
    @XmlElement(required = true)
    protected String ownerLastName;
    @XmlElement(required = true)
    protected String ownerEmail;

    /**
     * Gets the value of the identity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * Sets the value of the identity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentity(String value) {
        this.identity = value;
    }

    /**
     * Gets the value of the hostname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Sets the value of the hostname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostname(String value) {
        this.hostname = value;
    }

    /**
     * Gets the value of the hostCertificateSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostCertificateSubject() {
        return hostCertificateSubject;
    }

    /**
     * Sets the value of the hostCertificateSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostCertificateSubject(String value) {
        this.hostCertificateSubject = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

    /**
     * Gets the value of the ownerFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    /**
     * Sets the value of the ownerFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerFirstName(String value) {
        this.ownerFirstName = value;
    }

    /**
     * Gets the value of the ownerLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerLastName() {
        return ownerLastName;
    }

    /**
     * Sets the value of the ownerLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerLastName(String value) {
        this.ownerLastName = value;
    }

    /**
     * Gets the value of the ownerEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerEmail() {
        return ownerEmail;
    }

    /**
     * Sets the value of the ownerEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerEmail(String value) {
        this.ownerEmail = value;
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
            String theIdentity;
            theIdentity = this.getIdentity();
            strategy.appendField(locator, this, "identity", buffer, theIdentity);
        }
        {
            String theHostname;
            theHostname = this.getHostname();
            strategy.appendField(locator, this, "hostname", buffer, theHostname);
        }
        {
            String theHostCertificateSubject;
            theHostCertificateSubject = this.getHostCertificateSubject();
            strategy.appendField(locator, this, "hostCertificateSubject", buffer, theHostCertificateSubject);
        }
        {
            String theOwner;
            theOwner = this.getOwner();
            strategy.appendField(locator, this, "owner", buffer, theOwner);
        }
        {
            String theOwnerFirstName;
            theOwnerFirstName = this.getOwnerFirstName();
            strategy.appendField(locator, this, "ownerFirstName", buffer, theOwnerFirstName);
        }
        {
            String theOwnerLastName;
            theOwnerLastName = this.getOwnerLastName();
            strategy.appendField(locator, this, "ownerLastName", buffer, theOwnerLastName);
        }
        {
            String theOwnerEmail;
            theOwnerEmail = this.getOwnerEmail();
            strategy.appendField(locator, this, "ownerEmail", buffer, theOwnerEmail);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theIdentity;
            theIdentity = this.getIdentity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "identity", theIdentity), currentHashCode, theIdentity);
        }
        {
            String theHostname;
            theHostname = this.getHostname();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostname", theHostname), currentHashCode, theHostname);
        }
        {
            String theHostCertificateSubject;
            theHostCertificateSubject = this.getHostCertificateSubject();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostCertificateSubject", theHostCertificateSubject), currentHashCode, theHostCertificateSubject);
        }
        {
            String theOwner;
            theOwner = this.getOwner();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "owner", theOwner), currentHashCode, theOwner);
        }
        {
            String theOwnerFirstName;
            theOwnerFirstName = this.getOwnerFirstName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ownerFirstName", theOwnerFirstName), currentHashCode, theOwnerFirstName);
        }
        {
            String theOwnerLastName;
            theOwnerLastName = this.getOwnerLastName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ownerLastName", theOwnerLastName), currentHashCode, theOwnerLastName);
        }
        {
            String theOwnerEmail;
            theOwnerEmail = this.getOwnerEmail();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "ownerEmail", theOwnerEmail), currentHashCode, theOwnerEmail);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof HostRecord)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final HostRecord that = ((HostRecord) object);
        {
            String lhsIdentity;
            lhsIdentity = this.getIdentity();
            String rhsIdentity;
            rhsIdentity = that.getIdentity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "identity", lhsIdentity), LocatorUtils.property(thatLocator, "identity", rhsIdentity), lhsIdentity, rhsIdentity)) {
                return false;
            }
        }
        {
            String lhsHostname;
            lhsHostname = this.getHostname();
            String rhsHostname;
            rhsHostname = that.getHostname();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hostname", lhsHostname), LocatorUtils.property(thatLocator, "hostname", rhsHostname), lhsHostname, rhsHostname)) {
                return false;
            }
        }
        {
            String lhsHostCertificateSubject;
            lhsHostCertificateSubject = this.getHostCertificateSubject();
            String rhsHostCertificateSubject;
            rhsHostCertificateSubject = that.getHostCertificateSubject();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hostCertificateSubject", lhsHostCertificateSubject), LocatorUtils.property(thatLocator, "hostCertificateSubject", rhsHostCertificateSubject), lhsHostCertificateSubject, rhsHostCertificateSubject)) {
                return false;
            }
        }
        {
            String lhsOwner;
            lhsOwner = this.getOwner();
            String rhsOwner;
            rhsOwner = that.getOwner();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "owner", lhsOwner), LocatorUtils.property(thatLocator, "owner", rhsOwner), lhsOwner, rhsOwner)) {
                return false;
            }
        }
        {
            String lhsOwnerFirstName;
            lhsOwnerFirstName = this.getOwnerFirstName();
            String rhsOwnerFirstName;
            rhsOwnerFirstName = that.getOwnerFirstName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ownerFirstName", lhsOwnerFirstName), LocatorUtils.property(thatLocator, "ownerFirstName", rhsOwnerFirstName), lhsOwnerFirstName, rhsOwnerFirstName)) {
                return false;
            }
        }
        {
            String lhsOwnerLastName;
            lhsOwnerLastName = this.getOwnerLastName();
            String rhsOwnerLastName;
            rhsOwnerLastName = that.getOwnerLastName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ownerLastName", lhsOwnerLastName), LocatorUtils.property(thatLocator, "ownerLastName", rhsOwnerLastName), lhsOwnerLastName, rhsOwnerLastName)) {
                return false;
            }
        }
        {
            String lhsOwnerEmail;
            lhsOwnerEmail = this.getOwnerEmail();
            String rhsOwnerEmail;
            rhsOwnerEmail = that.getOwnerEmail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ownerEmail", lhsOwnerEmail), LocatorUtils.property(thatLocator, "ownerEmail", rhsOwnerEmail), lhsOwnerEmail, rhsOwnerEmail)) {
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
