
package org.cagrid.gaards.credentials;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 * <p>Java class for EncodedCertificates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EncodedCertificates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EncodedCertificate" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncodedCertificates", propOrder = {
    "encodedCertificate"
})
public class EncodedCertificates
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "EncodedCertificate", required = true)
    protected List<String> encodedCertificate;

    /**
     * Gets the value of the encodedCertificate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the encodedCertificate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEncodedCertificate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEncodedCertificate() {
        if (encodedCertificate == null) {
            encodedCertificate = new ArrayList<String>();
        }
        return this.encodedCertificate;
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
            List<String> theEncodedCertificate;
            theEncodedCertificate = (((this.encodedCertificate!= null)&&(!this.encodedCertificate.isEmpty()))?this.getEncodedCertificate():null);
            strategy.appendField(locator, this, "encodedCertificate", buffer, theEncodedCertificate);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<String> theEncodedCertificate;
            theEncodedCertificate = (((this.encodedCertificate!= null)&&(!this.encodedCertificate.isEmpty()))?this.getEncodedCertificate():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "encodedCertificate", theEncodedCertificate), currentHashCode, theEncodedCertificate);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EncodedCertificates)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EncodedCertificates that = ((EncodedCertificates) object);
        {
            List<String> lhsEncodedCertificate;
            lhsEncodedCertificate = (((this.encodedCertificate!= null)&&(!this.encodedCertificate.isEmpty()))?this.getEncodedCertificate():null);
            List<String> rhsEncodedCertificate;
            rhsEncodedCertificate = (((that.encodedCertificate!= null)&&(!that.encodedCertificate.isEmpty()))?that.getEncodedCertificate():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "encodedCertificate", lhsEncodedCertificate), LocatorUtils.property(thatLocator, "encodedCertificate", rhsEncodedCertificate), lhsEncodedCertificate, rhsEncodedCertificate)) {
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
