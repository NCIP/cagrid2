
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
 * <p>Java class for TrustedAuthorityFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrustedAuthorityFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CertificateDN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrustLevels" type="{http://cagrid.nci.nih.gov/8/gts}TrustLevels"/>
 *         &lt;element name="Lifetime" type="{http://cagrid.nci.nih.gov/8/gts}Lifetime" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://cagrid.nci.nih.gov/8/gts}Status" minOccurs="0"/>
 *         &lt;element name="IsAuthority" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AuthorityGTS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SourceGTS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrustedAuthorityFilter", propOrder = {
    "name",
    "certificateDN",
    "trustLevels",
    "lifetime",
    "status",
    "isAuthority",
    "authorityGTS",
    "sourceGTS"
})
public class TrustedAuthorityFilter
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "CertificateDN")
    protected String certificateDN;
    @XmlElement(name = "TrustLevels", required = true)
    protected TrustLevels trustLevels;
    @XmlElement(name = "Lifetime")
    protected Lifetime lifetime;
    @XmlElement(name = "Status")
    protected Status status;
    @XmlElement(name = "IsAuthority")
    protected Boolean isAuthority;
    @XmlElement(name = "AuthorityGTS")
    protected String authorityGTS;
    @XmlElement(name = "SourceGTS")
    protected String sourceGTS;

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
     * Gets the value of the certificateDN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificateDN() {
        return certificateDN;
    }

    /**
     * Sets the value of the certificateDN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificateDN(String value) {
        this.certificateDN = value;
    }

    /**
     * Gets the value of the trustLevels property.
     * 
     * @return
     *     possible object is
     *     {@link TrustLevels }
     *     
     */
    public TrustLevels getTrustLevels() {
        return trustLevels;
    }

    /**
     * Sets the value of the trustLevels property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrustLevels }
     *     
     */
    public void setTrustLevels(TrustLevels value) {
        this.trustLevels = value;
    }

    /**
     * Gets the value of the lifetime property.
     * 
     * @return
     *     possible object is
     *     {@link Lifetime }
     *     
     */
    public Lifetime getLifetime() {
        return lifetime;
    }

    /**
     * Sets the value of the lifetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lifetime }
     *     
     */
    public void setLifetime(Lifetime value) {
        this.lifetime = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
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
            String theCertificateDN;
            theCertificateDN = this.getCertificateDN();
            strategy.appendField(locator, this, "certificateDN", buffer, theCertificateDN);
        }
        {
            TrustLevels theTrustLevels;
            theTrustLevels = this.getTrustLevels();
            strategy.appendField(locator, this, "trustLevels", buffer, theTrustLevels);
        }
        {
            Lifetime theLifetime;
            theLifetime = this.getLifetime();
            strategy.appendField(locator, this, "lifetime", buffer, theLifetime);
        }
        {
            Status theStatus;
            theStatus = this.getStatus();
            strategy.appendField(locator, this, "status", buffer, theStatus);
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
            String theCertificateDN;
            theCertificateDN = this.getCertificateDN();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "certificateDN", theCertificateDN), currentHashCode, theCertificateDN);
        }
        {
            TrustLevels theTrustLevels;
            theTrustLevels = this.getTrustLevels();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trustLevels", theTrustLevels), currentHashCode, theTrustLevels);
        }
        {
            Lifetime theLifetime;
            theLifetime = this.getLifetime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lifetime", theLifetime), currentHashCode, theLifetime);
        }
        {
            Status theStatus;
            theStatus = this.getStatus();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "status", theStatus), currentHashCode, theStatus);
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
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TrustedAuthorityFilter)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TrustedAuthorityFilter that = ((TrustedAuthorityFilter) object);
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
            String lhsCertificateDN;
            lhsCertificateDN = this.getCertificateDN();
            String rhsCertificateDN;
            rhsCertificateDN = that.getCertificateDN();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "certificateDN", lhsCertificateDN), LocatorUtils.property(thatLocator, "certificateDN", rhsCertificateDN), lhsCertificateDN, rhsCertificateDN)) {
                return false;
            }
        }
        {
            TrustLevels lhsTrustLevels;
            lhsTrustLevels = this.getTrustLevels();
            TrustLevels rhsTrustLevels;
            rhsTrustLevels = that.getTrustLevels();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "trustLevels", lhsTrustLevels), LocatorUtils.property(thatLocator, "trustLevels", rhsTrustLevels), lhsTrustLevels, rhsTrustLevels)) {
                return false;
            }
        }
        {
            Lifetime lhsLifetime;
            lhsLifetime = this.getLifetime();
            Lifetime rhsLifetime;
            rhsLifetime = that.getLifetime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lifetime", lhsLifetime), LocatorUtils.property(thatLocator, "lifetime", rhsLifetime), lhsLifetime, rhsLifetime)) {
                return false;
            }
        }
        {
            Status lhsStatus;
            lhsStatus = this.getStatus();
            Status rhsStatus;
            rhsStatus = that.getStatus();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "status", lhsStatus), LocatorUtils.property(thatLocator, "status", rhsStatus), lhsStatus, rhsStatus)) {
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
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

}
