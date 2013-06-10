
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.idp.LocalUserFilter;
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
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}LocalUserFilter"/>
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
@XmlRootElement(name = "FindLocalUsersRequest")
public class FindLocalUsersRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected FindLocalUsersRequest.F f;

    /**
     * Gets the value of the f property.
     * 
     * @return
     *     possible object is
     *     {@link FindLocalUsersRequest.F }
     *     
     */
    public FindLocalUsersRequest.F getF() {
        return f;
    }

    /**
     * Sets the value of the f property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindLocalUsersRequest.F }
     *     
     */
    public void setF(FindLocalUsersRequest.F value) {
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
            FindLocalUsersRequest.F theF;
            theF = this.getF();
            strategy.appendField(locator, this, "f", buffer, theF);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            FindLocalUsersRequest.F theF;
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
        if (!(object instanceof FindLocalUsersRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FindLocalUsersRequest that = ((FindLocalUsersRequest) object);
        {
            FindLocalUsersRequest.F lhsF;
            lhsF = this.getF();
            FindLocalUsersRequest.F rhsF;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}LocalUserFilter"/>
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
        "localUserFilter"
    })
    public static class F
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "LocalUserFilter", namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", required = true)
        protected LocalUserFilter localUserFilter;

        /**
         * Gets the value of the localUserFilter property.
         * 
         * @return
         *     possible object is
         *     {@link LocalUserFilter }
         *     
         */
        public LocalUserFilter getLocalUserFilter() {
            return localUserFilter;
        }

        /**
         * Sets the value of the localUserFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link LocalUserFilter }
         *     
         */
        public void setLocalUserFilter(LocalUserFilter value) {
            this.localUserFilter = value;
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
                LocalUserFilter theLocalUserFilter;
                theLocalUserFilter = this.getLocalUserFilter();
                strategy.appendField(locator, this, "localUserFilter", buffer, theLocalUserFilter);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                LocalUserFilter theLocalUserFilter;
                theLocalUserFilter = this.getLocalUserFilter();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "localUserFilter", theLocalUserFilter), currentHashCode, theLocalUserFilter);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof FindLocalUsersRequest.F)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final FindLocalUsersRequest.F that = ((FindLocalUsersRequest.F) object);
            {
                LocalUserFilter lhsLocalUserFilter;
                lhsLocalUserFilter = this.getLocalUserFilter();
                LocalUserFilter rhsLocalUserFilter;
                rhsLocalUserFilter = that.getLocalUserFilter();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "localUserFilter", lhsLocalUserFilter), LocatorUtils.property(thatLocator, "localUserFilter", rhsLocalUserFilter), lhsLocalUserFilter, rhsLocalUserFilter)) {
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
