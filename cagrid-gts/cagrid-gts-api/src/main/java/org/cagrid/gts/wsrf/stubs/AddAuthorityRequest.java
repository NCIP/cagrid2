
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
 *         &lt;element name="authorityGTS">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}AuthorityGTS"/>
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
    "authorityGTS"
})
@XmlRootElement(name = "AddAuthorityRequest")
public class AddAuthorityRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected AddAuthorityRequest.AuthorityGTS authorityGTS;

    /**
     * Gets the value of the authorityGTS property.
     * 
     * @return
     *     possible object is
     *     {@link AddAuthorityRequest.AuthorityGTS }
     *     
     */
    public AddAuthorityRequest.AuthorityGTS getAuthorityGTS() {
        return authorityGTS;
    }

    /**
     * Sets the value of the authorityGTS property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddAuthorityRequest.AuthorityGTS }
     *     
     */
    public void setAuthorityGTS(AddAuthorityRequest.AuthorityGTS value) {
        this.authorityGTS = value;
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
            AddAuthorityRequest.AuthorityGTS theAuthorityGTS;
            theAuthorityGTS = this.getAuthorityGTS();
            strategy.appendField(locator, this, "authorityGTS", buffer, theAuthorityGTS);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            AddAuthorityRequest.AuthorityGTS theAuthorityGTS;
            theAuthorityGTS = this.getAuthorityGTS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authorityGTS", theAuthorityGTS), currentHashCode, theAuthorityGTS);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AddAuthorityRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AddAuthorityRequest that = ((AddAuthorityRequest) object);
        {
            AddAuthorityRequest.AuthorityGTS lhsAuthorityGTS;
            lhsAuthorityGTS = this.getAuthorityGTS();
            AddAuthorityRequest.AuthorityGTS rhsAuthorityGTS;
            rhsAuthorityGTS = that.getAuthorityGTS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authorityGTS", lhsAuthorityGTS), LocatorUtils.property(thatLocator, "authorityGTS", rhsAuthorityGTS), lhsAuthorityGTS, rhsAuthorityGTS)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}AuthorityGTS"/>
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
        "authorityGTS"
    })
    public static class AuthorityGTS
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "AuthorityGTS", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected org.cagrid.gts.model.AuthorityGTS authorityGTS;

        /**
         * Gets the value of the authorityGTS property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gts.model.AuthorityGTS }
         *     
         */
        public org.cagrid.gts.model.AuthorityGTS getAuthorityGTS() {
            return authorityGTS;
        }

        /**
         * Sets the value of the authorityGTS property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gts.model.AuthorityGTS }
         *     
         */
        public void setAuthorityGTS(org.cagrid.gts.model.AuthorityGTS value) {
            this.authorityGTS = value;
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
                org.cagrid.gts.model.AuthorityGTS theAuthorityGTS;
                theAuthorityGTS = this.getAuthorityGTS();
                strategy.appendField(locator, this, "authorityGTS", buffer, theAuthorityGTS);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                org.cagrid.gts.model.AuthorityGTS theAuthorityGTS;
                theAuthorityGTS = this.getAuthorityGTS();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authorityGTS", theAuthorityGTS), currentHashCode, theAuthorityGTS);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof AddAuthorityRequest.AuthorityGTS)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final AddAuthorityRequest.AuthorityGTS that = ((AddAuthorityRequest.AuthorityGTS) object);
            {
                org.cagrid.gts.model.AuthorityGTS lhsAuthorityGTS;
                lhsAuthorityGTS = this.getAuthorityGTS();
                org.cagrid.gts.model.AuthorityGTS rhsAuthorityGTS;
                rhsAuthorityGTS = that.getAuthorityGTS();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "authorityGTS", lhsAuthorityGTS), LocatorUtils.property(thatLocator, "authorityGTS", rhsAuthorityGTS), lhsAuthorityGTS, rhsAuthorityGTS)) {
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
