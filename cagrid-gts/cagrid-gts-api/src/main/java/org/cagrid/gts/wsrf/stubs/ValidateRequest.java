
package org.cagrid.gts.wsrf.stubs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gts.model.TrustedAuthorityFilter;
import org.cagrid.gts.model.X509Certificate;
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
 *         &lt;element name="chain">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}X509Certificate" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="filter">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}TrustedAuthorityFilter"/>
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
    "chain",
    "filter"
})
@XmlRootElement(name = "ValidateRequest")
public class ValidateRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected ValidateRequest.Chain chain;
    @XmlElement(required = true)
    protected ValidateRequest.Filter filter;

    /**
     * Gets the value of the chain property.
     * 
     * @return
     *     possible object is
     *     {@link ValidateRequest.Chain }
     *     
     */
    public ValidateRequest.Chain getChain() {
        return chain;
    }

    /**
     * Sets the value of the chain property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidateRequest.Chain }
     *     
     */
    public void setChain(ValidateRequest.Chain value) {
        this.chain = value;
    }

    /**
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link ValidateRequest.Filter }
     *     
     */
    public ValidateRequest.Filter getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidateRequest.Filter }
     *     
     */
    public void setFilter(ValidateRequest.Filter value) {
        this.filter = value;
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
            ValidateRequest.Chain theChain;
            theChain = this.getChain();
            strategy.appendField(locator, this, "chain", buffer, theChain);
        }
        {
            ValidateRequest.Filter theFilter;
            theFilter = this.getFilter();
            strategy.appendField(locator, this, "filter", buffer, theFilter);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            ValidateRequest.Chain theChain;
            theChain = this.getChain();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "chain", theChain), currentHashCode, theChain);
        }
        {
            ValidateRequest.Filter theFilter;
            theFilter = this.getFilter();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "filter", theFilter), currentHashCode, theFilter);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ValidateRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ValidateRequest that = ((ValidateRequest) object);
        {
            ValidateRequest.Chain lhsChain;
            lhsChain = this.getChain();
            ValidateRequest.Chain rhsChain;
            rhsChain = that.getChain();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "chain", lhsChain), LocatorUtils.property(thatLocator, "chain", rhsChain), lhsChain, rhsChain)) {
                return false;
            }
        }
        {
            ValidateRequest.Filter lhsFilter;
            lhsFilter = this.getFilter();
            ValidateRequest.Filter rhsFilter;
            rhsFilter = that.getFilter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "filter", lhsFilter), LocatorUtils.property(thatLocator, "filter", rhsFilter), lhsFilter, rhsFilter)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}X509Certificate" maxOccurs="unbounded"/>
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
        "x509Certificate"
    })
    public static class Chain
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "X509Certificate", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected List<X509Certificate> x509Certificate;

        /**
         * Gets the value of the x509Certificate property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the x509Certificate property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getX509Certificate().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link X509Certificate }
         * 
         * 
         */
        public List<X509Certificate> getX509Certificate() {
            if (x509Certificate == null) {
                x509Certificate = new ArrayList<X509Certificate>();
            }
            return this.x509Certificate;
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
                List<X509Certificate> theX509Certificate;
                theX509Certificate = (((this.x509Certificate!= null)&&(!this.x509Certificate.isEmpty()))?this.getX509Certificate():null);
                strategy.appendField(locator, this, "x509Certificate", buffer, theX509Certificate);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                List<X509Certificate> theX509Certificate;
                theX509Certificate = (((this.x509Certificate!= null)&&(!this.x509Certificate.isEmpty()))?this.getX509Certificate():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "x509Certificate", theX509Certificate), currentHashCode, theX509Certificate);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof ValidateRequest.Chain)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final ValidateRequest.Chain that = ((ValidateRequest.Chain) object);
            {
                List<X509Certificate> lhsX509Certificate;
                lhsX509Certificate = (((this.x509Certificate!= null)&&(!this.x509Certificate.isEmpty()))?this.getX509Certificate():null);
                List<X509Certificate> rhsX509Certificate;
                rhsX509Certificate = (((that.x509Certificate!= null)&&(!that.x509Certificate.isEmpty()))?that.getX509Certificate():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "x509Certificate", lhsX509Certificate), LocatorUtils.property(thatLocator, "x509Certificate", rhsX509Certificate), lhsX509Certificate, rhsX509Certificate)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}TrustedAuthorityFilter"/>
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
        "trustedAuthorityFilter"
    })
    public static class Filter
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "TrustedAuthorityFilter", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected TrustedAuthorityFilter trustedAuthorityFilter;

        /**
         * Gets the value of the trustedAuthorityFilter property.
         * 
         * @return
         *     possible object is
         *     {@link TrustedAuthorityFilter }
         *     
         */
        public TrustedAuthorityFilter getTrustedAuthorityFilter() {
            return trustedAuthorityFilter;
        }

        /**
         * Sets the value of the trustedAuthorityFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link TrustedAuthorityFilter }
         *     
         */
        public void setTrustedAuthorityFilter(TrustedAuthorityFilter value) {
            this.trustedAuthorityFilter = value;
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
                TrustedAuthorityFilter theTrustedAuthorityFilter;
                theTrustedAuthorityFilter = this.getTrustedAuthorityFilter();
                strategy.appendField(locator, this, "trustedAuthorityFilter", buffer, theTrustedAuthorityFilter);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                TrustedAuthorityFilter theTrustedAuthorityFilter;
                theTrustedAuthorityFilter = this.getTrustedAuthorityFilter();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trustedAuthorityFilter", theTrustedAuthorityFilter), currentHashCode, theTrustedAuthorityFilter);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof ValidateRequest.Filter)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final ValidateRequest.Filter that = ((ValidateRequest.Filter) object);
            {
                TrustedAuthorityFilter lhsTrustedAuthorityFilter;
                lhsTrustedAuthorityFilter = this.getTrustedAuthorityFilter();
                TrustedAuthorityFilter rhsTrustedAuthorityFilter;
                rhsTrustedAuthorityFilter = that.getTrustedAuthorityFilter();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "trustedAuthorityFilter", lhsTrustedAuthorityFilter), LocatorUtils.property(thatLocator, "trustedAuthorityFilter", rhsTrustedAuthorityFilter), lhsTrustedAuthorityFilter, rhsTrustedAuthorityFilter)) {
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
