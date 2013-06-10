
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
 *         &lt;element name="userCertificateFilter">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}UserCertificateFilter"/>
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
    "userCertificateFilter"
})
@XmlRootElement(name = "FindUserCertificatesRequest")
public class FindUserCertificatesRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected FindUserCertificatesRequest.UserCertificateFilter userCertificateFilter;

    /**
     * Gets the value of the userCertificateFilter property.
     * 
     * @return
     *     possible object is
     *     {@link FindUserCertificatesRequest.UserCertificateFilter }
     *     
     */
    public FindUserCertificatesRequest.UserCertificateFilter getUserCertificateFilter() {
        return userCertificateFilter;
    }

    /**
     * Sets the value of the userCertificateFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindUserCertificatesRequest.UserCertificateFilter }
     *     
     */
    public void setUserCertificateFilter(FindUserCertificatesRequest.UserCertificateFilter value) {
        this.userCertificateFilter = value;
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
            FindUserCertificatesRequest.UserCertificateFilter theUserCertificateFilter;
            theUserCertificateFilter = this.getUserCertificateFilter();
            strategy.appendField(locator, this, "userCertificateFilter", buffer, theUserCertificateFilter);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            FindUserCertificatesRequest.UserCertificateFilter theUserCertificateFilter;
            theUserCertificateFilter = this.getUserCertificateFilter();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "userCertificateFilter", theUserCertificateFilter), currentHashCode, theUserCertificateFilter);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FindUserCertificatesRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FindUserCertificatesRequest that = ((FindUserCertificatesRequest) object);
        {
            FindUserCertificatesRequest.UserCertificateFilter lhsUserCertificateFilter;
            lhsUserCertificateFilter = this.getUserCertificateFilter();
            FindUserCertificatesRequest.UserCertificateFilter rhsUserCertificateFilter;
            rhsUserCertificateFilter = that.getUserCertificateFilter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "userCertificateFilter", lhsUserCertificateFilter), LocatorUtils.property(thatLocator, "userCertificateFilter", rhsUserCertificateFilter), lhsUserCertificateFilter, rhsUserCertificateFilter)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}UserCertificateFilter"/>
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
        "userCertificateFilter"
    })
    public static class UserCertificateFilter
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "UserCertificateFilter", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.ifs.UserCertificateFilter userCertificateFilter;

        /**
         * Gets the value of the userCertificateFilter property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.ifs.UserCertificateFilter }
         *     
         */
        public org.cagrid.dorian.ifs.UserCertificateFilter getUserCertificateFilter() {
            return userCertificateFilter;
        }

        /**
         * Sets the value of the userCertificateFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.ifs.UserCertificateFilter }
         *     
         */
        public void setUserCertificateFilter(org.cagrid.dorian.ifs.UserCertificateFilter value) {
            this.userCertificateFilter = value;
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
                org.cagrid.dorian.ifs.UserCertificateFilter theUserCertificateFilter;
                theUserCertificateFilter = this.getUserCertificateFilter();
                strategy.appendField(locator, this, "userCertificateFilter", buffer, theUserCertificateFilter);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                org.cagrid.dorian.ifs.UserCertificateFilter theUserCertificateFilter;
                theUserCertificateFilter = this.getUserCertificateFilter();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "userCertificateFilter", theUserCertificateFilter), currentHashCode, theUserCertificateFilter);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof FindUserCertificatesRequest.UserCertificateFilter)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final FindUserCertificatesRequest.UserCertificateFilter that = ((FindUserCertificatesRequest.UserCertificateFilter) object);
            {
                org.cagrid.dorian.ifs.UserCertificateFilter lhsUserCertificateFilter;
                lhsUserCertificateFilter = this.getUserCertificateFilter();
                org.cagrid.dorian.ifs.UserCertificateFilter rhsUserCertificateFilter;
                rhsUserCertificateFilter = that.getUserCertificateFilter();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "userCertificateFilter", lhsUserCertificateFilter), LocatorUtils.property(thatLocator, "userCertificateFilter", rhsUserCertificateFilter), lhsUserCertificateFilter, rhsUserCertificateFilter)) {
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
