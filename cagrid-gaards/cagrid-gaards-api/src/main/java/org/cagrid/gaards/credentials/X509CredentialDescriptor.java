
package org.cagrid.gaards.credentials;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Java class for X509CredentialDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X509CredentialDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EncodedCertificates" type="{http://gaards.cagrid.org/credentials}EncodedCertificates"/>
 *         &lt;element name="EncodedKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Identity" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X509CredentialDescriptor", propOrder = {
    "encodedCertificates",
    "encodedKey"
})
@XmlSeeAlso({
    DorianUserCredentialDescriptor.class
})
public class X509CredentialDescriptor
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "EncodedCertificates", required = true)
    protected EncodedCertificates encodedCertificates;
    @XmlElement(name = "EncodedKey", required = true)
    protected String encodedKey;
    @XmlAttribute(name = "Identity", namespace = "http://gaards.cagrid.org/credentials", required = true)
    protected String identity;

    /**
     * Gets the value of the encodedCertificates property.
     * 
     * @return
     *     possible object is
     *     {@link EncodedCertificates }
     *     
     */
    public EncodedCertificates getEncodedCertificates() {
        return encodedCertificates;
    }

    /**
     * Sets the value of the encodedCertificates property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncodedCertificates }
     *     
     */
    public void setEncodedCertificates(EncodedCertificates value) {
        this.encodedCertificates = value;
    }

    /**
     * Gets the value of the encodedKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncodedKey() {
        return encodedKey;
    }

    /**
     * Sets the value of the encodedKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncodedKey(String value) {
        this.encodedKey = value;
    }

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
            EncodedCertificates theEncodedCertificates;
            theEncodedCertificates = this.getEncodedCertificates();
            strategy.appendField(locator, this, "encodedCertificates", buffer, theEncodedCertificates);
        }
        {
            String theEncodedKey;
            theEncodedKey = this.getEncodedKey();
            strategy.appendField(locator, this, "encodedKey", buffer, theEncodedKey);
        }
        {
            String theIdentity;
            theIdentity = this.getIdentity();
            strategy.appendField(locator, this, "identity", buffer, theIdentity);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            EncodedCertificates theEncodedCertificates;
            theEncodedCertificates = this.getEncodedCertificates();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "encodedCertificates", theEncodedCertificates), currentHashCode, theEncodedCertificates);
        }
        {
            String theEncodedKey;
            theEncodedKey = this.getEncodedKey();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "encodedKey", theEncodedKey), currentHashCode, theEncodedKey);
        }
        {
            String theIdentity;
            theIdentity = this.getIdentity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "identity", theIdentity), currentHashCode, theIdentity);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof X509CredentialDescriptor)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final X509CredentialDescriptor that = ((X509CredentialDescriptor) object);
        {
            EncodedCertificates lhsEncodedCertificates;
            lhsEncodedCertificates = this.getEncodedCertificates();
            EncodedCertificates rhsEncodedCertificates;
            rhsEncodedCertificates = that.getEncodedCertificates();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "encodedCertificates", lhsEncodedCertificates), LocatorUtils.property(thatLocator, "encodedCertificates", rhsEncodedCertificates), lhsEncodedCertificates, rhsEncodedCertificates)) {
                return false;
            }
        }
        {
            String lhsEncodedKey;
            lhsEncodedKey = this.getEncodedKey();
            String rhsEncodedKey;
            rhsEncodedKey = that.getEncodedKey();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "encodedKey", lhsEncodedKey), LocatorUtils.property(thatLocator, "encodedKey", rhsEncodedKey), lhsEncodedKey, rhsEncodedKey)) {
                return false;
            }
        }
        {
            String lhsIdentity;
            lhsIdentity = this.getIdentity();
            String rhsIdentity;
            rhsIdentity = that.getIdentity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "identity", lhsIdentity), LocatorUtils.property(thatLocator, "identity", rhsIdentity), lhsIdentity, rhsIdentity)) {
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
