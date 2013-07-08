
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
 *         &lt;element name="gridUserSearchCriteria">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}GridUserSearchCriteria"/>
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
    "gridUserSearchCriteria"
})
@XmlRootElement(name = "UserSearchRequest")
public class UserSearchRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected UserSearchRequest.GridUserSearchCriteria gridUserSearchCriteria;

    /**
     * Gets the value of the gridUserSearchCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link UserSearchRequest.GridUserSearchCriteria }
     *     
     */
    public UserSearchRequest.GridUserSearchCriteria getGridUserSearchCriteria() {
        return gridUserSearchCriteria;
    }

    /**
     * Sets the value of the gridUserSearchCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserSearchRequest.GridUserSearchCriteria }
     *     
     */
    public void setGridUserSearchCriteria(UserSearchRequest.GridUserSearchCriteria value) {
        this.gridUserSearchCriteria = value;
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
            UserSearchRequest.GridUserSearchCriteria theGridUserSearchCriteria;
            theGridUserSearchCriteria = this.getGridUserSearchCriteria();
            strategy.appendField(locator, this, "gridUserSearchCriteria", buffer, theGridUserSearchCriteria);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            UserSearchRequest.GridUserSearchCriteria theGridUserSearchCriteria;
            theGridUserSearchCriteria = this.getGridUserSearchCriteria();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "gridUserSearchCriteria", theGridUserSearchCriteria), currentHashCode, theGridUserSearchCriteria);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof UserSearchRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final UserSearchRequest that = ((UserSearchRequest) object);
        {
            UserSearchRequest.GridUserSearchCriteria lhsGridUserSearchCriteria;
            lhsGridUserSearchCriteria = this.getGridUserSearchCriteria();
            UserSearchRequest.GridUserSearchCriteria rhsGridUserSearchCriteria;
            rhsGridUserSearchCriteria = that.getGridUserSearchCriteria();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gridUserSearchCriteria", lhsGridUserSearchCriteria), LocatorUtils.property(thatLocator, "gridUserSearchCriteria", rhsGridUserSearchCriteria), lhsGridUserSearchCriteria, rhsGridUserSearchCriteria)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}GridUserSearchCriteria"/>
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
        "gridUserSearchCriteria"
    })
    public static class GridUserSearchCriteria
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "GridUserSearchCriteria", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.model.federation.GridUserSearchCriteria gridUserSearchCriteria;

        /**
         * Gets the value of the gridUserSearchCriteria property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.model.federation.GridUserSearchCriteria }
         *     
         */
        public org.cagrid.dorian.model.federation.GridUserSearchCriteria getGridUserSearchCriteria() {
            return gridUserSearchCriteria;
        }

        /**
         * Sets the value of the gridUserSearchCriteria property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.model.federation.GridUserSearchCriteria }
         *     
         */
        public void setGridUserSearchCriteria(org.cagrid.dorian.model.federation.GridUserSearchCriteria value) {
            this.gridUserSearchCriteria = value;
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
                org.cagrid.dorian.model.federation.GridUserSearchCriteria theGridUserSearchCriteria;
                theGridUserSearchCriteria = this.getGridUserSearchCriteria();
                strategy.appendField(locator, this, "gridUserSearchCriteria", buffer, theGridUserSearchCriteria);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                org.cagrid.dorian.model.federation.GridUserSearchCriteria theGridUserSearchCriteria;
                theGridUserSearchCriteria = this.getGridUserSearchCriteria();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "gridUserSearchCriteria", theGridUserSearchCriteria), currentHashCode, theGridUserSearchCriteria);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof UserSearchRequest.GridUserSearchCriteria)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final UserSearchRequest.GridUserSearchCriteria that = ((UserSearchRequest.GridUserSearchCriteria) object);
            {
                org.cagrid.dorian.model.federation.GridUserSearchCriteria lhsGridUserSearchCriteria;
                lhsGridUserSearchCriteria = this.getGridUserSearchCriteria();
                org.cagrid.dorian.model.federation.GridUserSearchCriteria rhsGridUserSearchCriteria;
                rhsGridUserSearchCriteria = that.getGridUserSearchCriteria();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "gridUserSearchCriteria", lhsGridUserSearchCriteria), LocatorUtils.property(thatLocator, "gridUserSearchCriteria", rhsGridUserSearchCriteria), lhsGridUserSearchCriteria, rhsGridUserSearchCriteria)) {
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
