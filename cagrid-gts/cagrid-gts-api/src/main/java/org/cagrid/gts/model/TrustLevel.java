
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
 * <p>Java class for TrustLevel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrustLevel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsAuthority" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AuthorityGTS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SourceGTS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastUpdated" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrustLevel", propOrder = {
    "name",
    "description",
    "isAuthority",
    "authorityGTS",
    "sourceGTS",
    "lastUpdated"
})
public class TrustLevel
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(name = "IsAuthority")
    protected Boolean isAuthority;
    @XmlElement(name = "AuthorityGTS")
    protected String authorityGTS;
    @XmlElement(name = "SourceGTS")
    protected String sourceGTS;
    @XmlElement(name = "LastUpdated")
    protected long lastUpdated;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the isAuthority property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAuthority() {
        return isAuthority;
    }

    /**
     * Sets the value of the isAuthority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAuthority(Boolean value) {
        this.isAuthority = value;
    }

    /**
     * Gets the value of the authorityGTS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorityGTS() {
        return authorityGTS;
    }

    /**
     * Sets the value of the authorityGTS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorityGTS(String value) {
        this.authorityGTS = value;
    }

    /**
     * Gets the value of the sourceGTS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceGTS() {
        return sourceGTS;
    }

    /**
     * Sets the value of the sourceGTS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceGTS(String value) {
        this.sourceGTS = value;
    }

    /**
     * Gets the value of the lastUpdated property.
     * 
     */
    public long getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the value of the lastUpdated property.
     * 
     */
    public void setLastUpdated(long value) {
        this.lastUpdated = value;
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
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            Boolean theIsAuthority;
            theIsAuthority = this.isIsAuthority();
            strategy.appendField(locator, this, "isAuthority", buffer, theIsAuthority);
        }
        {
            String theAuthorityGTS;
            theAuthorityGTS = this.getAuthorityGTS();
            strategy.appendField(locator, this, "authorityGTS", buffer, theAuthorityGTS);
        }
        {
            String theSourceGTS;
            theSourceGTS = this.getSourceGTS();
            strategy.appendField(locator, this, "sourceGTS", buffer, theSourceGTS);
        }
        {
            long theLastUpdated;
            theLastUpdated = (true?this.getLastUpdated(): 0L);
            strategy.appendField(locator, this, "lastUpdated", buffer, theLastUpdated);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theName;
            theName = this.getName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);
        }
        {
            Boolean theIsAuthority;
            theIsAuthority = this.isIsAuthority();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "isAuthority", theIsAuthority), currentHashCode, theIsAuthority);
        }
        {
            String theAuthorityGTS;
            theAuthorityGTS = this.getAuthorityGTS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authorityGTS", theAuthorityGTS), currentHashCode, theAuthorityGTS);
        }
        {
            String theSourceGTS;
            theSourceGTS = this.getSourceGTS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sourceGTS", theSourceGTS), currentHashCode, theSourceGTS);
        }
        {
            long theLastUpdated;
            theLastUpdated = (true?this.getLastUpdated(): 0L);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastUpdated", theLastUpdated), currentHashCode, theLastUpdated);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TrustLevel)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TrustLevel that = ((TrustLevel) object);
        {
            String lhsName;
            lhsName = this.getName();
            String rhsName;
            rhsName = that.getName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName)) {
                return false;
            }
        }
        {
            String lhsDescription;
            lhsDescription = this.getDescription();
            String rhsDescription;
            rhsDescription = that.getDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
                return false;
            }
        }
        {
            Boolean lhsIsAuthority;
            lhsIsAuthority = this.isIsAuthority();
            Boolean rhsIsAuthority;
            rhsIsAuthority = that.isIsAuthority();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "isAuthority", lhsIsAuthority), LocatorUtils.property(thatLocator, "isAuthority", rhsIsAuthority), lhsIsAuthority, rhsIsAuthority)) {
                return false;
            }
        }
        {
            String lhsAuthorityGTS;
            lhsAuthorityGTS = this.getAuthorityGTS();
            String rhsAuthorityGTS;
            rhsAuthorityGTS = that.getAuthorityGTS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authorityGTS", lhsAuthorityGTS), LocatorUtils.property(thatLocator, "authorityGTS", rhsAuthorityGTS), lhsAuthorityGTS, rhsAuthorityGTS)) {
                return false;
            }
        }
        {
            String lhsSourceGTS;
            lhsSourceGTS = this.getSourceGTS();
            String rhsSourceGTS;
            rhsSourceGTS = that.getSourceGTS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sourceGTS", lhsSourceGTS), LocatorUtils.property(thatLocator, "sourceGTS", rhsSourceGTS), lhsSourceGTS, rhsSourceGTS)) {
                return false;
            }
        }
        {
            long lhsLastUpdated;
            lhsLastUpdated = (true?this.getLastUpdated(): 0L);
            long rhsLastUpdated;
            rhsLastUpdated = (true?that.getLastUpdated(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lastUpdated", lhsLastUpdated), LocatorUtils.property(thatLocator, "lastUpdated", rhsLastUpdated), lhsLastUpdated, rhsLastUpdated)) {
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
