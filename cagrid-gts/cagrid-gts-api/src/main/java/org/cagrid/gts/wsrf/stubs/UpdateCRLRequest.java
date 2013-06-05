
package org.cagrid.gts.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gts.model.X509CRL;
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
 *         &lt;element name="trustedAuthorityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="crl">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}X509CRL"/>
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
    "trustedAuthorityName",
    "crl"
})
@XmlRootElement(name = "UpdateCRLRequest")
public class UpdateCRLRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected String trustedAuthorityName;
    @XmlElement(required = true)
    protected UpdateCRLRequest.Crl crl;

    /**
     * Gets the value of the trustedAuthorityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustedAuthorityName() {
        return trustedAuthorityName;
    }

    /**
     * Sets the value of the trustedAuthorityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustedAuthorityName(String value) {
        this.trustedAuthorityName = value;
    }

    /**
     * Gets the value of the crl property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateCRLRequest.Crl }
     *     
     */
    public UpdateCRLRequest.Crl getCrl() {
        return crl;
    }

    /**
     * Sets the value of the crl property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateCRLRequest.Crl }
     *     
     */
    public void setCrl(UpdateCRLRequest.Crl value) {
        this.crl = value;
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
            String theTrustedAuthorityName;
            theTrustedAuthorityName = this.getTrustedAuthorityName();
            strategy.appendField(locator, this, "trustedAuthorityName", buffer, theTrustedAuthorityName);
        }
        {
            UpdateCRLRequest.Crl theCrl;
            theCrl = this.getCrl();
            strategy.appendField(locator, this, "crl", buffer, theCrl);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theTrustedAuthorityName;
            theTrustedAuthorityName = this.getTrustedAuthorityName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trustedAuthorityName", theTrustedAuthorityName), currentHashCode, theTrustedAuthorityName);
        }
        {
            UpdateCRLRequest.Crl theCrl;
            theCrl = this.getCrl();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "crl", theCrl), currentHashCode, theCrl);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof UpdateCRLRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final UpdateCRLRequest that = ((UpdateCRLRequest) object);
        {
            String lhsTrustedAuthorityName;
            lhsTrustedAuthorityName = this.getTrustedAuthorityName();
            String rhsTrustedAuthorityName;
            rhsTrustedAuthorityName = that.getTrustedAuthorityName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "trustedAuthorityName", lhsTrustedAuthorityName), LocatorUtils.property(thatLocator, "trustedAuthorityName", rhsTrustedAuthorityName), lhsTrustedAuthorityName, rhsTrustedAuthorityName)) {
                return false;
            }
        }
        {
            UpdateCRLRequest.Crl lhsCrl;
            lhsCrl = this.getCrl();
            UpdateCRLRequest.Crl rhsCrl;
            rhsCrl = that.getCrl();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "crl", lhsCrl), LocatorUtils.property(thatLocator, "crl", rhsCrl), lhsCrl, rhsCrl)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}X509CRL"/>
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
        "x509CRL"
    })
    public static class Crl
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "X509CRL", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected X509CRL x509CRL;

        /**
         * Gets the value of the x509CRL property.
         * 
         * @return
         *     possible object is
         *     {@link X509CRL }
         *     
         */
        public X509CRL getX509CRL() {
            return x509CRL;
        }

        /**
         * Sets the value of the x509CRL property.
         * 
         * @param value
         *     allowed object is
         *     {@link X509CRL }
         *     
         */
        public void setX509CRL(X509CRL value) {
            this.x509CRL = value;
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
                X509CRL theX509CRL;
                theX509CRL = this.getX509CRL();
                strategy.appendField(locator, this, "x509CRL", buffer, theX509CRL);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                X509CRL theX509CRL;
                theX509CRL = this.getX509CRL();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "x509CRL", theX509CRL), currentHashCode, theX509CRL);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof UpdateCRLRequest.Crl)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final UpdateCRLRequest.Crl that = ((UpdateCRLRequest.Crl) object);
            {
                X509CRL lhsX509CRL;
                lhsX509CRL = this.getX509CRL();
                X509CRL rhsX509CRL;
                rhsX509CRL = that.getX509CRL();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "x509CRL", lhsX509CRL), LocatorUtils.property(thatLocator, "x509CRL", rhsX509CRL), lhsX509CRL, rhsX509CRL)) {
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
