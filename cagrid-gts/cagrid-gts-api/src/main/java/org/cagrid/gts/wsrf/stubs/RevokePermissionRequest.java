
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
 *         &lt;element name="permission">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}Permission"/>
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
    "permission"
})
@XmlRootElement(name = "RevokePermissionRequest")
public class RevokePermissionRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected RevokePermissionRequest.Permission permission;

    /**
     * Gets the value of the permission property.
     * 
     * @return
     *     possible object is
     *     {@link RevokePermissionRequest.Permission }
     *     
     */
    public RevokePermissionRequest.Permission getPermission() {
        return permission;
    }

    /**
     * Sets the value of the permission property.
     * 
     * @param value
     *     allowed object is
     *     {@link RevokePermissionRequest.Permission }
     *     
     */
    public void setPermission(RevokePermissionRequest.Permission value) {
        this.permission = value;
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
            RevokePermissionRequest.Permission thePermission;
            thePermission = this.getPermission();
            strategy.appendField(locator, this, "permission", buffer, thePermission);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            RevokePermissionRequest.Permission thePermission;
            thePermission = this.getPermission();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "permission", thePermission), currentHashCode, thePermission);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RevokePermissionRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final RevokePermissionRequest that = ((RevokePermissionRequest) object);
        {
            RevokePermissionRequest.Permission lhsPermission;
            lhsPermission = this.getPermission();
            RevokePermissionRequest.Permission rhsPermission;
            rhsPermission = that.getPermission();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "permission", lhsPermission), LocatorUtils.property(thatLocator, "permission", rhsPermission), lhsPermission, rhsPermission)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}Permission"/>
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
        "permission"
    })
    public static class Permission
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "Permission", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected org.cagrid.gts.model.Permission permission;

        /**
         * Gets the value of the permission property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gts.model.Permission }
         *     
         */
        public org.cagrid.gts.model.Permission getPermission() {
            return permission;
        }

        /**
         * Sets the value of the permission property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gts.model.Permission }
         *     
         */
        public void setPermission(org.cagrid.gts.model.Permission value) {
            this.permission = value;
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
                org.cagrid.gts.model.Permission thePermission;
                thePermission = this.getPermission();
                strategy.appendField(locator, this, "permission", buffer, thePermission);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                org.cagrid.gts.model.Permission thePermission;
                thePermission = this.getPermission();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "permission", thePermission), currentHashCode, thePermission);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof RevokePermissionRequest.Permission)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final RevokePermissionRequest.Permission that = ((RevokePermissionRequest.Permission) object);
            {
                org.cagrid.gts.model.Permission lhsPermission;
                lhsPermission = this.getPermission();
                org.cagrid.gts.model.Permission rhsPermission;
                rhsPermission = that.getPermission();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "permission", lhsPermission), LocatorUtils.property(thatLocator, "permission", rhsPermission), lhsPermission, rhsPermission)) {
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
