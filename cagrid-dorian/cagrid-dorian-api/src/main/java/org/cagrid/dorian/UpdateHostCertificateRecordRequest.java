
package org.cagrid.dorian;

import java.io.Serializable;
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
 *         &lt;element name="hostCertificateUpdate">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateUpdate"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "hostCertificateUpdate"
})
@XmlRootElement(name = "UpdateHostCertificateRecordRequest")
public class UpdateHostCertificateRecordRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected UpdateHostCertificateRecordRequest.HostCertificateUpdate hostCertificateUpdate;

    /**
     * Gets the value of the hostCertificateUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateHostCertificateRecordRequest.HostCertificateUpdate }
     *     
     */
    public UpdateHostCertificateRecordRequest.HostCertificateUpdate getHostCertificateUpdate() {
        return hostCertificateUpdate;
    }

    /**
     * Sets the value of the hostCertificateUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateHostCertificateRecordRequest.HostCertificateUpdate }
     *     
     */
    public void setHostCertificateUpdate(UpdateHostCertificateRecordRequest.HostCertificateUpdate value) {
        this.hostCertificateUpdate = value;
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
            UpdateHostCertificateRecordRequest.HostCertificateUpdate theHostCertificateUpdate;
            theHostCertificateUpdate = this.getHostCertificateUpdate();
            strategy.appendField(locator, this, "hostCertificateUpdate", buffer, theHostCertificateUpdate);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            UpdateHostCertificateRecordRequest.HostCertificateUpdate theHostCertificateUpdate;
            theHostCertificateUpdate = this.getHostCertificateUpdate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostCertificateUpdate", theHostCertificateUpdate), currentHashCode, theHostCertificateUpdate);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof UpdateHostCertificateRecordRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final UpdateHostCertificateRecordRequest that = ((UpdateHostCertificateRecordRequest) object);
        {
            UpdateHostCertificateRecordRequest.HostCertificateUpdate lhsHostCertificateUpdate;
            lhsHostCertificateUpdate = this.getHostCertificateUpdate();
            UpdateHostCertificateRecordRequest.HostCertificateUpdate rhsHostCertificateUpdate;
            rhsHostCertificateUpdate = that.getHostCertificateUpdate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hostCertificateUpdate", lhsHostCertificateUpdate), LocatorUtils.property(thatLocator, "hostCertificateUpdate", rhsHostCertificateUpdate), lhsHostCertificateUpdate, rhsHostCertificateUpdate)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }


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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateUpdate"/>
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
        "hostCertificateUpdate"
    })
    public static class HostCertificateUpdate
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "HostCertificateUpdate", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.ifs.HostCertificateUpdate hostCertificateUpdate;

        /**
         * Gets the value of the hostCertificateUpdate property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.ifs.HostCertificateUpdate }
         *     
         */
        public org.cagrid.dorian.ifs.HostCertificateUpdate getHostCertificateUpdate() {
            return hostCertificateUpdate;
        }

        /**
         * Sets the value of the hostCertificateUpdate property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.ifs.HostCertificateUpdate }
         *     
         */
        public void setHostCertificateUpdate(org.cagrid.dorian.ifs.HostCertificateUpdate value) {
            this.hostCertificateUpdate = value;
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
                org.cagrid.dorian.ifs.HostCertificateUpdate theHostCertificateUpdate;
                theHostCertificateUpdate = this.getHostCertificateUpdate();
                strategy.appendField(locator, this, "hostCertificateUpdate", buffer, theHostCertificateUpdate);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                org.cagrid.dorian.ifs.HostCertificateUpdate theHostCertificateUpdate;
                theHostCertificateUpdate = this.getHostCertificateUpdate();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostCertificateUpdate", theHostCertificateUpdate), currentHashCode, theHostCertificateUpdate);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof UpdateHostCertificateRecordRequest.HostCertificateUpdate)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final UpdateHostCertificateRecordRequest.HostCertificateUpdate that = ((UpdateHostCertificateRecordRequest.HostCertificateUpdate) object);
            {
                org.cagrid.dorian.ifs.HostCertificateUpdate lhsHostCertificateUpdate;
                lhsHostCertificateUpdate = this.getHostCertificateUpdate();
                org.cagrid.dorian.ifs.HostCertificateUpdate rhsHostCertificateUpdate;
                rhsHostCertificateUpdate = that.getHostCertificateUpdate();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "hostCertificateUpdate", lhsHostCertificateUpdate), LocatorUtils.property(thatLocator, "hostCertificateUpdate", rhsHostCertificateUpdate), lhsHostCertificateUpdate, rhsHostCertificateUpdate)) {
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

}
