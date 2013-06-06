
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
 *         &lt;element name="authorityPriorityUpdate">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}AuthorityPriorityUpdate"/>
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
    "authorityPriorityUpdate"
})
@XmlRootElement(name = "UpdateAuthorityPrioritiesRequest")
public class UpdateAuthorityPrioritiesRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate authorityPriorityUpdate;

    /**
     * Gets the value of the authorityPriorityUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate }
     *     
     */
    public UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate getAuthorityPriorityUpdate() {
        return authorityPriorityUpdate;
    }

    /**
     * Sets the value of the authorityPriorityUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate }
     *     
     */
    public void setAuthorityPriorityUpdate(UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate value) {
        this.authorityPriorityUpdate = value;
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
            UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate theAuthorityPriorityUpdate;
            theAuthorityPriorityUpdate = this.getAuthorityPriorityUpdate();
            strategy.appendField(locator, this, "authorityPriorityUpdate", buffer, theAuthorityPriorityUpdate);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate theAuthorityPriorityUpdate;
            theAuthorityPriorityUpdate = this.getAuthorityPriorityUpdate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authorityPriorityUpdate", theAuthorityPriorityUpdate), currentHashCode, theAuthorityPriorityUpdate);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof UpdateAuthorityPrioritiesRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final UpdateAuthorityPrioritiesRequest that = ((UpdateAuthorityPrioritiesRequest) object);
        {
            UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate lhsAuthorityPriorityUpdate;
            lhsAuthorityPriorityUpdate = this.getAuthorityPriorityUpdate();
            UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate rhsAuthorityPriorityUpdate;
            rhsAuthorityPriorityUpdate = that.getAuthorityPriorityUpdate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authorityPriorityUpdate", lhsAuthorityPriorityUpdate), LocatorUtils.property(thatLocator, "authorityPriorityUpdate", rhsAuthorityPriorityUpdate), lhsAuthorityPriorityUpdate, rhsAuthorityPriorityUpdate)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}AuthorityPriorityUpdate"/>
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
        "authorityPriorityUpdate"
    })
    public static class AuthorityPriorityUpdate
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "AuthorityPriorityUpdate", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected org.cagrid.gts.model.AuthorityPriorityUpdate authorityPriorityUpdate;

        /**
         * Gets the value of the authorityPriorityUpdate property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gts.model.AuthorityPriorityUpdate }
         *     
         */
        public org.cagrid.gts.model.AuthorityPriorityUpdate getAuthorityPriorityUpdate() {
            return authorityPriorityUpdate;
        }

        /**
         * Sets the value of the authorityPriorityUpdate property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gts.model.AuthorityPriorityUpdate }
         *     
         */
        public void setAuthorityPriorityUpdate(org.cagrid.gts.model.AuthorityPriorityUpdate value) {
            this.authorityPriorityUpdate = value;
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
                org.cagrid.gts.model.AuthorityPriorityUpdate theAuthorityPriorityUpdate;
                theAuthorityPriorityUpdate = this.getAuthorityPriorityUpdate();
                strategy.appendField(locator, this, "authorityPriorityUpdate", buffer, theAuthorityPriorityUpdate);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                org.cagrid.gts.model.AuthorityPriorityUpdate theAuthorityPriorityUpdate;
                theAuthorityPriorityUpdate = this.getAuthorityPriorityUpdate();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authorityPriorityUpdate", theAuthorityPriorityUpdate), currentHashCode, theAuthorityPriorityUpdate);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate that = ((UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate) object);
            {
                org.cagrid.gts.model.AuthorityPriorityUpdate lhsAuthorityPriorityUpdate;
                lhsAuthorityPriorityUpdate = this.getAuthorityPriorityUpdate();
                org.cagrid.gts.model.AuthorityPriorityUpdate rhsAuthorityPriorityUpdate;
                rhsAuthorityPriorityUpdate = that.getAuthorityPriorityUpdate();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "authorityPriorityUpdate", lhsAuthorityPriorityUpdate), LocatorUtils.property(thatLocator, "authorityPriorityUpdate", rhsAuthorityPriorityUpdate), lhsAuthorityPriorityUpdate, rhsAuthorityPriorityUpdate)) {
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
