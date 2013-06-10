
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
 *         &lt;element name="hostCertificateFilter">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateFilter"/>
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
    "hostCertificateFilter"
})
@XmlRootElement(name = "FindHostCertificatesRequest")
public class FindHostCertificatesRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected FindHostCertificatesRequest.HostCertificateFilter hostCertificateFilter;

    /**
     * Gets the value of the hostCertificateFilter property.
     * 
     * @return
     *     possible object is
     *     {@link FindHostCertificatesRequest.HostCertificateFilter }
     *     
     */
    public FindHostCertificatesRequest.HostCertificateFilter getHostCertificateFilter() {
        return hostCertificateFilter;
    }

    /**
     * Sets the value of the hostCertificateFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindHostCertificatesRequest.HostCertificateFilter }
     *     
     */
    public void setHostCertificateFilter(FindHostCertificatesRequest.HostCertificateFilter value) {
        this.hostCertificateFilter = value;
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
            FindHostCertificatesRequest.HostCertificateFilter theHostCertificateFilter;
            theHostCertificateFilter = this.getHostCertificateFilter();
            strategy.appendField(locator, this, "hostCertificateFilter", buffer, theHostCertificateFilter);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            FindHostCertificatesRequest.HostCertificateFilter theHostCertificateFilter;
            theHostCertificateFilter = this.getHostCertificateFilter();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostCertificateFilter", theHostCertificateFilter), currentHashCode, theHostCertificateFilter);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FindHostCertificatesRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FindHostCertificatesRequest that = ((FindHostCertificatesRequest) object);
        {
            FindHostCertificatesRequest.HostCertificateFilter lhsHostCertificateFilter;
            lhsHostCertificateFilter = this.getHostCertificateFilter();
            FindHostCertificatesRequest.HostCertificateFilter rhsHostCertificateFilter;
            rhsHostCertificateFilter = that.getHostCertificateFilter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hostCertificateFilter", lhsHostCertificateFilter), LocatorUtils.property(thatLocator, "hostCertificateFilter", rhsHostCertificateFilter), lhsHostCertificateFilter, rhsHostCertificateFilter)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateFilter"/>
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
        "hostCertificateFilter"
    })
    public static class HostCertificateFilter
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "HostCertificateFilter", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.ifs.HostCertificateFilter hostCertificateFilter;

        /**
         * Gets the value of the hostCertificateFilter property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.ifs.HostCertificateFilter }
         *     
         */
        public org.cagrid.dorian.ifs.HostCertificateFilter getHostCertificateFilter() {
            return hostCertificateFilter;
        }

        /**
         * Sets the value of the hostCertificateFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.ifs.HostCertificateFilter }
         *     
         */
        public void setHostCertificateFilter(org.cagrid.dorian.ifs.HostCertificateFilter value) {
            this.hostCertificateFilter = value;
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
                org.cagrid.dorian.ifs.HostCertificateFilter theHostCertificateFilter;
                theHostCertificateFilter = this.getHostCertificateFilter();
                strategy.appendField(locator, this, "hostCertificateFilter", buffer, theHostCertificateFilter);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                org.cagrid.dorian.ifs.HostCertificateFilter theHostCertificateFilter;
                theHostCertificateFilter = this.getHostCertificateFilter();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostCertificateFilter", theHostCertificateFilter), currentHashCode, theHostCertificateFilter);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof FindHostCertificatesRequest.HostCertificateFilter)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final FindHostCertificatesRequest.HostCertificateFilter that = ((FindHostCertificatesRequest.HostCertificateFilter) object);
            {
                org.cagrid.dorian.ifs.HostCertificateFilter lhsHostCertificateFilter;
                lhsHostCertificateFilter = this.getHostCertificateFilter();
                org.cagrid.dorian.ifs.HostCertificateFilter rhsHostCertificateFilter;
                rhsHostCertificateFilter = that.getHostCertificateFilter();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "hostCertificateFilter", lhsHostCertificateFilter), LocatorUtils.property(thatLocator, "hostCertificateFilter", rhsHostCertificateFilter), lhsHostCertificateFilter, rhsHostCertificateFilter)) {
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
