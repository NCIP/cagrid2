
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
 *         &lt;element name="trustedIdP">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}TrustedIdP"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="publish" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "trustedIdP",
    "publish"
})
@XmlRootElement(name = "SetPublishRequest")
public class SetPublishRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected SetPublishRequest.TrustedIdP trustedIdP;
    protected boolean publish;

    /**
     * Gets the value of the trustedIdP property.
     * 
     * @return
     *     possible object is
     *     {@link SetPublishRequest.TrustedIdP }
     *     
     */
    public SetPublishRequest.TrustedIdP getTrustedIdP() {
        return trustedIdP;
    }

    /**
     * Sets the value of the trustedIdP property.
     * 
     * @param value
     *     allowed object is
     *     {@link SetPublishRequest.TrustedIdP }
     *     
     */
    public void setTrustedIdP(SetPublishRequest.TrustedIdP value) {
        this.trustedIdP = value;
    }

    /**
     * Gets the value of the publish property.
     * 
     */
    public boolean isPublish() {
        return publish;
    }

    /**
     * Sets the value of the publish property.
     * 
     */
    public void setPublish(boolean value) {
        this.publish = value;
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
            SetPublishRequest.TrustedIdP theTrustedIdP;
            theTrustedIdP = this.getTrustedIdP();
            strategy.appendField(locator, this, "trustedIdP", buffer, theTrustedIdP);
        }
        {
            boolean thePublish;
            thePublish = (true?this.isPublish():false);
            strategy.appendField(locator, this, "publish", buffer, thePublish);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            SetPublishRequest.TrustedIdP theTrustedIdP;
            theTrustedIdP = this.getTrustedIdP();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trustedIdP", theTrustedIdP), currentHashCode, theTrustedIdP);
        }
        {
            boolean thePublish;
            thePublish = (true?this.isPublish():false);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "publish", thePublish), currentHashCode, thePublish);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SetPublishRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SetPublishRequest that = ((SetPublishRequest) object);
        {
            SetPublishRequest.TrustedIdP lhsTrustedIdP;
            lhsTrustedIdP = this.getTrustedIdP();
            SetPublishRequest.TrustedIdP rhsTrustedIdP;
            rhsTrustedIdP = that.getTrustedIdP();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "trustedIdP", lhsTrustedIdP), LocatorUtils.property(thatLocator, "trustedIdP", rhsTrustedIdP), lhsTrustedIdP, rhsTrustedIdP)) {
                return false;
            }
        }
        {
            boolean lhsPublish;
            lhsPublish = (true?this.isPublish():false);
            boolean rhsPublish;
            rhsPublish = (true?that.isPublish():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "publish", lhsPublish), LocatorUtils.property(thatLocator, "publish", rhsPublish), lhsPublish, rhsPublish)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}TrustedIdP"/>
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
        "trustedIdP"
    })
    public static class TrustedIdP
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "TrustedIdP", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.model.federation.TrustedIdP trustedIdP;

        /**
         * Gets the value of the trustedIdP property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.model.federation.TrustedIdP }
         *     
         */
        public org.cagrid.dorian.model.federation.TrustedIdP getTrustedIdP() {
            return trustedIdP;
        }

        /**
         * Sets the value of the trustedIdP property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.model.federation.TrustedIdP }
         *     
         */
        public void setTrustedIdP(org.cagrid.dorian.model.federation.TrustedIdP value) {
            this.trustedIdP = value;
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
                org.cagrid.dorian.model.federation.TrustedIdP theTrustedIdP;
                theTrustedIdP = this.getTrustedIdP();
                strategy.appendField(locator, this, "trustedIdP", buffer, theTrustedIdP);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                org.cagrid.dorian.model.federation.TrustedIdP theTrustedIdP;
                theTrustedIdP = this.getTrustedIdP();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trustedIdP", theTrustedIdP), currentHashCode, theTrustedIdP);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof SetPublishRequest.TrustedIdP)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final SetPublishRequest.TrustedIdP that = ((SetPublishRequest.TrustedIdP) object);
            {
                org.cagrid.dorian.model.federation.TrustedIdP lhsTrustedIdP;
                lhsTrustedIdP = this.getTrustedIdP();
                org.cagrid.dorian.model.federation.TrustedIdP rhsTrustedIdP;
                rhsTrustedIdP = that.getTrustedIdP();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "trustedIdP", lhsTrustedIdP), LocatorUtils.property(thatLocator, "trustedIdP", rhsTrustedIdP), lhsTrustedIdP, rhsTrustedIdP)) {
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
