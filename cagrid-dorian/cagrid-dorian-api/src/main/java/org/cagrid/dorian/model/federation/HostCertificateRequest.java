
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
 * <p>Java class for HostCertificateRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HostCertificateRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Hostname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PublicKey" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}PublicKey"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HostCertificateRequest", propOrder = {
    "hostname",
    "publicKey"
})
public class HostCertificateRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "Hostname", required = true)
    protected String hostname;
    @XmlElement(name = "PublicKey", required = true)
    protected PublicKey publicKey;

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
     * Gets the value of the publicKey property.
     * 
     * @return
     *     possible object is
     *     {@link PublicKey }
     *     
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Sets the value of the publicKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link PublicKey }
     *     
     */
    public void setPublicKey(PublicKey value) {
        this.publicKey = value;
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
            String theHostname;
            theHostname = this.getHostname();
            strategy.appendField(locator, this, "hostname", buffer, theHostname);
        }
        {
            PublicKey thePublicKey;
            thePublicKey = this.getPublicKey();
            strategy.appendField(locator, this, "publicKey", buffer, thePublicKey);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theHostname;
            theHostname = this.getHostname();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostname", theHostname), currentHashCode, theHostname);
        }
        {
            PublicKey thePublicKey;
            thePublicKey = this.getPublicKey();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "publicKey", thePublicKey), currentHashCode, thePublicKey);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof HostCertificateRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final HostCertificateRequest that = ((HostCertificateRequest) object);
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
            PublicKey lhsPublicKey;
            lhsPublicKey = this.getPublicKey();
            PublicKey rhsPublicKey;
            rhsPublicKey = that.getPublicKey();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "publicKey", lhsPublicKey), LocatorUtils.property(thatLocator, "publicKey", rhsPublicKey), lhsPublicKey, rhsPublicKey)) {
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
