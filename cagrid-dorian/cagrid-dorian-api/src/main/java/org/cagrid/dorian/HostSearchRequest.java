
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
 *         &lt;element name="hostSearchCriteria">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostSearchCriteria"/>
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
    "hostSearchCriteria"
})
@XmlRootElement(name = "HostSearchRequest")
public class HostSearchRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected HostSearchRequest.HostSearchCriteria hostSearchCriteria;

    /**
     * Gets the value of the hostSearchCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link HostSearchRequest.HostSearchCriteria }
     *     
     */
    public HostSearchRequest.HostSearchCriteria getHostSearchCriteria() {
        return hostSearchCriteria;
    }

    /**
     * Sets the value of the hostSearchCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link HostSearchRequest.HostSearchCriteria }
     *     
     */
    public void setHostSearchCriteria(HostSearchRequest.HostSearchCriteria value) {
        this.hostSearchCriteria = value;
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
            HostSearchRequest.HostSearchCriteria theHostSearchCriteria;
            theHostSearchCriteria = this.getHostSearchCriteria();
            strategy.appendField(locator, this, "hostSearchCriteria", buffer, theHostSearchCriteria);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            HostSearchRequest.HostSearchCriteria theHostSearchCriteria;
            theHostSearchCriteria = this.getHostSearchCriteria();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostSearchCriteria", theHostSearchCriteria), currentHashCode, theHostSearchCriteria);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof HostSearchRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final HostSearchRequest that = ((HostSearchRequest) object);
        {
            HostSearchRequest.HostSearchCriteria lhsHostSearchCriteria;
            lhsHostSearchCriteria = this.getHostSearchCriteria();
            HostSearchRequest.HostSearchCriteria rhsHostSearchCriteria;
            rhsHostSearchCriteria = that.getHostSearchCriteria();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hostSearchCriteria", lhsHostSearchCriteria), LocatorUtils.property(thatLocator, "hostSearchCriteria", rhsHostSearchCriteria), lhsHostSearchCriteria, rhsHostSearchCriteria)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostSearchCriteria"/>
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
        "hostSearchCriteria"
    })
    public static class HostSearchCriteria
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "HostSearchCriteria", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.ifs.HostSearchCriteria hostSearchCriteria;

        /**
         * Gets the value of the hostSearchCriteria property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.ifs.HostSearchCriteria }
         *     
         */
        public org.cagrid.dorian.ifs.HostSearchCriteria getHostSearchCriteria() {
            return hostSearchCriteria;
        }

        /**
         * Sets the value of the hostSearchCriteria property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.ifs.HostSearchCriteria }
         *     
         */
        public void setHostSearchCriteria(org.cagrid.dorian.ifs.HostSearchCriteria value) {
            this.hostSearchCriteria = value;
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
                org.cagrid.dorian.ifs.HostSearchCriteria theHostSearchCriteria;
                theHostSearchCriteria = this.getHostSearchCriteria();
                strategy.appendField(locator, this, "hostSearchCriteria", buffer, theHostSearchCriteria);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                org.cagrid.dorian.ifs.HostSearchCriteria theHostSearchCriteria;
                theHostSearchCriteria = this.getHostSearchCriteria();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostSearchCriteria", theHostSearchCriteria), currentHashCode, theHostSearchCriteria);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof HostSearchRequest.HostSearchCriteria)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final HostSearchRequest.HostSearchCriteria that = ((HostSearchRequest.HostSearchCriteria) object);
            {
                org.cagrid.dorian.ifs.HostSearchCriteria lhsHostSearchCriteria;
                lhsHostSearchCriteria = this.getHostSearchCriteria();
                org.cagrid.dorian.ifs.HostSearchCriteria rhsHostSearchCriteria;
                rhsHostSearchCriteria = that.getHostSearchCriteria();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "hostSearchCriteria", lhsHostSearchCriteria), LocatorUtils.property(thatLocator, "hostSearchCriteria", rhsHostSearchCriteria), lhsHostSearchCriteria, rhsHostSearchCriteria)) {
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
