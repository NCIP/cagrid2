
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.idp.IdentityProviderAuditFilter;
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
 *         &lt;element name="f">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}IdentityProviderAuditFilter"/>
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
    "f"
})
@XmlRootElement(name = "PerformIdentityProviderAuditRequest")
public class PerformIdentityProviderAuditRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected PerformIdentityProviderAuditRequest.F f;

    /**
     * Gets the value of the f property.
     * 
     * @return
     *     possible object is
     *     {@link PerformIdentityProviderAuditRequest.F }
     *     
     */
    public PerformIdentityProviderAuditRequest.F getF() {
        return f;
    }

    /**
     * Sets the value of the f property.
     * 
     * @param value
     *     allowed object is
     *     {@link PerformIdentityProviderAuditRequest.F }
     *     
     */
    public void setF(PerformIdentityProviderAuditRequest.F value) {
        this.f = value;
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
            PerformIdentityProviderAuditRequest.F theF;
            theF = this.getF();
            strategy.appendField(locator, this, "f", buffer, theF);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            PerformIdentityProviderAuditRequest.F theF;
            theF = this.getF();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "f", theF), currentHashCode, theF);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PerformIdentityProviderAuditRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PerformIdentityProviderAuditRequest that = ((PerformIdentityProviderAuditRequest) object);
        {
            PerformIdentityProviderAuditRequest.F lhsF;
            lhsF = this.getF();
            PerformIdentityProviderAuditRequest.F rhsF;
            rhsF = that.getF();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "f", lhsF), LocatorUtils.property(thatLocator, "f", rhsF), lhsF, rhsF)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}IdentityProviderAuditFilter"/>
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
        "identityProviderAuditFilter"
    })
    public static class F
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "IdentityProviderAuditFilter", namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", required = true)
        protected IdentityProviderAuditFilter identityProviderAuditFilter;

        /**
         * Gets the value of the identityProviderAuditFilter property.
         * 
         * @return
         *     possible object is
         *     {@link IdentityProviderAuditFilter }
         *     
         */
        public IdentityProviderAuditFilter getIdentityProviderAuditFilter() {
            return identityProviderAuditFilter;
        }

        /**
         * Sets the value of the identityProviderAuditFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link IdentityProviderAuditFilter }
         *     
         */
        public void setIdentityProviderAuditFilter(IdentityProviderAuditFilter value) {
            this.identityProviderAuditFilter = value;
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
                IdentityProviderAuditFilter theIdentityProviderAuditFilter;
                theIdentityProviderAuditFilter = this.getIdentityProviderAuditFilter();
                strategy.appendField(locator, this, "identityProviderAuditFilter", buffer, theIdentityProviderAuditFilter);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                IdentityProviderAuditFilter theIdentityProviderAuditFilter;
                theIdentityProviderAuditFilter = this.getIdentityProviderAuditFilter();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "identityProviderAuditFilter", theIdentityProviderAuditFilter), currentHashCode, theIdentityProviderAuditFilter);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof PerformIdentityProviderAuditRequest.F)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final PerformIdentityProviderAuditRequest.F that = ((PerformIdentityProviderAuditRequest.F) object);
            {
                IdentityProviderAuditFilter lhsIdentityProviderAuditFilter;
                lhsIdentityProviderAuditFilter = this.getIdentityProviderAuditFilter();
                IdentityProviderAuditFilter rhsIdentityProviderAuditFilter;
                rhsIdentityProviderAuditFilter = that.getIdentityProviderAuditFilter();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "identityProviderAuditFilter", lhsIdentityProviderAuditFilter), LocatorUtils.property(thatLocator, "identityProviderAuditFilter", rhsIdentityProviderAuditFilter), lhsIdentityProviderAuditFilter, rhsIdentityProviderAuditFilter)) {
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
