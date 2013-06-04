
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
 * <p>Java class for X509CRL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X509CRL">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="crlEncodedString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X509CRL", propOrder = {
    "crlEncodedString"
})
public class X509CRL
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected String crlEncodedString;

    /**
     * Gets the value of the crlEncodedString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrlEncodedString() {
        return crlEncodedString;
    }

    /**
     * Sets the value of the crlEncodedString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrlEncodedString(String value) {
        this.crlEncodedString = value;
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
            String theCrlEncodedString;
            theCrlEncodedString = this.getCrlEncodedString();
            strategy.appendField(locator, this, "crlEncodedString", buffer, theCrlEncodedString);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theCrlEncodedString;
            theCrlEncodedString = this.getCrlEncodedString();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "crlEncodedString", theCrlEncodedString), currentHashCode, theCrlEncodedString);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof X509CRL)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final X509CRL that = ((X509CRL) object);
        {
            String lhsCrlEncodedString;
            lhsCrlEncodedString = this.getCrlEncodedString();
            String rhsCrlEncodedString;
            rhsCrlEncodedString = that.getCrlEncodedString();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "crlEncodedString", lhsCrlEncodedString), LocatorUtils.property(thatLocator, "crlEncodedString", rhsCrlEncodedString), lhsCrlEncodedString, rhsCrlEncodedString)) {
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
