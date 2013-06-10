
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.ifs.FederationAuditFilter;
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
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}FederationAuditFilter"/>
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
@XmlRootElement(name = "PerformFederationAuditRequest")
public class PerformFederationAuditRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected PerformFederationAuditRequest.F f;

    /**
     * Gets the value of the f property.
     * 
     * @return
     *     possible object is
     *     {@link PerformFederationAuditRequest.F }
     *     
     */
    public PerformFederationAuditRequest.F getF() {
        return f;
    }

    /**
     * Sets the value of the f property.
     * 
     * @param value
     *     allowed object is
     *     {@link PerformFederationAuditRequest.F }
     *     
     */
    public void setF(PerformFederationAuditRequest.F value) {
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
            PerformFederationAuditRequest.F theF;
            theF = this.getF();
            strategy.appendField(locator, this, "f", buffer, theF);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            PerformFederationAuditRequest.F theF;
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
        if (!(object instanceof PerformFederationAuditRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PerformFederationAuditRequest that = ((PerformFederationAuditRequest) object);
        {
            PerformFederationAuditRequest.F lhsF;
            lhsF = this.getF();
            PerformFederationAuditRequest.F rhsF;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}FederationAuditFilter"/>
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
        "federationAuditFilter"
    })
    public static class F
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "FederationAuditFilter", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected FederationAuditFilter federationAuditFilter;

        /**
         * Gets the value of the federationAuditFilter property.
         * 
         * @return
         *     possible object is
         *     {@link FederationAuditFilter }
         *     
         */
        public FederationAuditFilter getFederationAuditFilter() {
            return federationAuditFilter;
        }

        /**
         * Sets the value of the federationAuditFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link FederationAuditFilter }
         *     
         */
        public void setFederationAuditFilter(FederationAuditFilter value) {
            this.federationAuditFilter = value;
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
                FederationAuditFilter theFederationAuditFilter;
                theFederationAuditFilter = this.getFederationAuditFilter();
                strategy.appendField(locator, this, "federationAuditFilter", buffer, theFederationAuditFilter);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                FederationAuditFilter theFederationAuditFilter;
                theFederationAuditFilter = this.getFederationAuditFilter();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "federationAuditFilter", theFederationAuditFilter), currentHashCode, theFederationAuditFilter);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof PerformFederationAuditRequest.F)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final PerformFederationAuditRequest.F that = ((PerformFederationAuditRequest.F) object);
            {
                FederationAuditFilter lhsFederationAuditFilter;
                lhsFederationAuditFilter = this.getFederationAuditFilter();
                FederationAuditFilter rhsFederationAuditFilter;
                rhsFederationAuditFilter = that.getFederationAuditFilter();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "federationAuditFilter", lhsFederationAuditFilter), LocatorUtils.property(thatLocator, "federationAuditFilter", rhsFederationAuditFilter), lhsFederationAuditFilter, rhsFederationAuditFilter)) {
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
