
package org.cagrid.gts.wsrf.stubs;

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
 *         &lt;element name="trustLevel">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}TrustLevel"/>
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
    "trustLevel"
})
@XmlRootElement(name = "AddTrustLevelRequest")
public class AddTrustLevelRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected AddTrustLevelRequest.TrustLevel trustLevel;

    /**
     * Gets the value of the trustLevel property.
     * 
     * @return
     *     possible object is
     *     {@link AddTrustLevelRequest.TrustLevel }
     *     
     */
    public AddTrustLevelRequest.TrustLevel getTrustLevel() {
        return trustLevel;
    }

    /**
     * Sets the value of the trustLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddTrustLevelRequest.TrustLevel }
     *     
     */
    public void setTrustLevel(AddTrustLevelRequest.TrustLevel value) {
        this.trustLevel = value;
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
            AddTrustLevelRequest.TrustLevel theTrustLevel;
            theTrustLevel = this.getTrustLevel();
            strategy.appendField(locator, this, "trustLevel", buffer, theTrustLevel);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            AddTrustLevelRequest.TrustLevel theTrustLevel;
            theTrustLevel = this.getTrustLevel();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trustLevel", theTrustLevel), currentHashCode, theTrustLevel);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AddTrustLevelRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AddTrustLevelRequest that = ((AddTrustLevelRequest) object);
        {
            AddTrustLevelRequest.TrustLevel lhsTrustLevel;
            lhsTrustLevel = this.getTrustLevel();
            AddTrustLevelRequest.TrustLevel rhsTrustLevel;
            rhsTrustLevel = that.getTrustLevel();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "trustLevel", lhsTrustLevel), LocatorUtils.property(thatLocator, "trustLevel", rhsTrustLevel), lhsTrustLevel, rhsTrustLevel)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}TrustLevel"/>
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
        "trustLevel"
    })
    public static class TrustLevel
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "TrustLevel", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected org.cagrid.gts.model.TrustLevel trustLevel;

        /**
         * Gets the value of the trustLevel property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gts.model.TrustLevel }
         *     
         */
        public org.cagrid.gts.model.TrustLevel getTrustLevel() {
            return trustLevel;
        }

        /**
         * Sets the value of the trustLevel property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gts.model.TrustLevel }
         *     
         */
        public void setTrustLevel(org.cagrid.gts.model.TrustLevel value) {
            this.trustLevel = value;
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
                org.cagrid.gts.model.TrustLevel theTrustLevel;
                theTrustLevel = this.getTrustLevel();
                strategy.appendField(locator, this, "trustLevel", buffer, theTrustLevel);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                org.cagrid.gts.model.TrustLevel theTrustLevel;
                theTrustLevel = this.getTrustLevel();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trustLevel", theTrustLevel), currentHashCode, theTrustLevel);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof AddTrustLevelRequest.TrustLevel)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final AddTrustLevelRequest.TrustLevel that = ((AddTrustLevelRequest.TrustLevel) object);
            {
                org.cagrid.gts.model.TrustLevel lhsTrustLevel;
                lhsTrustLevel = this.getTrustLevel();
                org.cagrid.gts.model.TrustLevel rhsTrustLevel;
                rhsTrustLevel = that.getTrustLevel();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "trustLevel", lhsTrustLevel), LocatorUtils.property(thatLocator, "trustLevel", rhsTrustLevel), lhsTrustLevel, rhsTrustLevel)) {
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
