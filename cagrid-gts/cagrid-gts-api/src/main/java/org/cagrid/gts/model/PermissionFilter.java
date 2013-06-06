
package org.cagrid.gts.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Java class for PermissionFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PermissionFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GridIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Role" type="{http://cagrid.nci.nih.gov/8/gts}Role" minOccurs="0"/>
 *         &lt;element name="TrustedAuthorityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PermissionFilter", propOrder = {
    "gridIdentity",
    "role",
    "trustedAuthorityName"
})
public class PermissionFilter
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "GridIdentity")
    protected String gridIdentity;
    @XmlElement(name = "Role")
    protected Role role;
    @XmlElement(name = "TrustedAuthorityName")
    protected String trustedAuthorityName;

    /**
     * Gets the value of the gridIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridIdentity() {
        return gridIdentity;
    }

    /**
     * Sets the value of the gridIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridIdentity(String value) {
        this.gridIdentity = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link Role }
     *     
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link Role }
     *     
     */
    public void setRole(Role value) {
        this.role = value;
    }

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
            String theGridIdentity;
            theGridIdentity = this.getGridIdentity();
            strategy.appendField(locator, this, "gridIdentity", buffer, theGridIdentity);
        }
        {
            Role theRole;
            theRole = this.getRole();
            strategy.appendField(locator, this, "role", buffer, theRole);
        }
        {
            String theTrustedAuthorityName;
            theTrustedAuthorityName = this.getTrustedAuthorityName();
            strategy.appendField(locator, this, "trustedAuthorityName", buffer, theTrustedAuthorityName);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theGridIdentity;
            theGridIdentity = this.getGridIdentity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "gridIdentity", theGridIdentity), currentHashCode, theGridIdentity);
        }
        {
            Role theRole;
            theRole = this.getRole();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "role", theRole), currentHashCode, theRole);
        }
        {
            String theTrustedAuthorityName;
            theTrustedAuthorityName = this.getTrustedAuthorityName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trustedAuthorityName", theTrustedAuthorityName), currentHashCode, theTrustedAuthorityName);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PermissionFilter)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PermissionFilter that = ((PermissionFilter) object);
        {
            String lhsGridIdentity;
            lhsGridIdentity = this.getGridIdentity();
            String rhsGridIdentity;
            rhsGridIdentity = that.getGridIdentity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gridIdentity", lhsGridIdentity), LocatorUtils.property(thatLocator, "gridIdentity", rhsGridIdentity), lhsGridIdentity, rhsGridIdentity)) {
                return false;
            }
        }
        {
            Role lhsRole;
            lhsRole = this.getRole();
            Role rhsRole;
            rhsRole = that.getRole();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "role", lhsRole), LocatorUtils.property(thatLocator, "role", rhsRole), lhsRole, rhsRole)) {
                return false;
            }
        }
        {
            String lhsTrustedAuthorityName;
            lhsTrustedAuthorityName = this.getTrustedAuthorityName();
            String rhsTrustedAuthorityName;
            rhsTrustedAuthorityName = that.getTrustedAuthorityName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "trustedAuthorityName", lhsTrustedAuthorityName), LocatorUtils.property(thatLocator, "trustedAuthorityName", rhsTrustedAuthorityName), lhsTrustedAuthorityName, rhsTrustedAuthorityName)) {
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
