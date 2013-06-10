
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.ifs.HostCertificateRecord;
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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateRecord"/>
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
    "hostCertificateRecord"
})
@XmlRootElement(name = "RenewHostCertificateResponse")
public class RenewHostCertificateResponse
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "HostCertificateRecord", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
    protected HostCertificateRecord hostCertificateRecord;

    /**
     * Gets the value of the hostCertificateRecord property.
     * 
     * @return
     *     possible object is
     *     {@link HostCertificateRecord }
     *     
     */
    public HostCertificateRecord getHostCertificateRecord() {
        return hostCertificateRecord;
    }

    /**
     * Sets the value of the hostCertificateRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link HostCertificateRecord }
     *     
     */
    public void setHostCertificateRecord(HostCertificateRecord value) {
        this.hostCertificateRecord = value;
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
            HostCertificateRecord theHostCertificateRecord;
            theHostCertificateRecord = this.getHostCertificateRecord();
            strategy.appendField(locator, this, "hostCertificateRecord", buffer, theHostCertificateRecord);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            HostCertificateRecord theHostCertificateRecord;
            theHostCertificateRecord = this.getHostCertificateRecord();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostCertificateRecord", theHostCertificateRecord), currentHashCode, theHostCertificateRecord);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RenewHostCertificateResponse)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final RenewHostCertificateResponse that = ((RenewHostCertificateResponse) object);
        {
            HostCertificateRecord lhsHostCertificateRecord;
            lhsHostCertificateRecord = this.getHostCertificateRecord();
            HostCertificateRecord rhsHostCertificateRecord;
            rhsHostCertificateRecord = that.getHostCertificateRecord();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hostCertificateRecord", lhsHostCertificateRecord), LocatorUtils.property(thatLocator, "hostCertificateRecord", rhsHostCertificateRecord), lhsHostCertificateRecord, rhsHostCertificateRecord)) {
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
