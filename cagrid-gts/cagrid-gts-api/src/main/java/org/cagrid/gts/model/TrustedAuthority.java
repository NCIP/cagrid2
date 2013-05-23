
package org.cagrid.gts.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrustedAuthority complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrustedAuthority">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TrustLevels" type="{http://cagrid.nci.nih.gov/8/gts}TrustLevels"/>
 *         &lt;element name="Status" type="{http://cagrid.nci.nih.gov/8/gts}Status"/>
 *         &lt;element name="IsAuthority" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AuthorityGTS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SourceGTS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Expires" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="LastUpdated" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Certificate" type="{http://cagrid.nci.nih.gov/8/gts}X509Certificate"/>
 *         &lt;element name="CRL" type="{http://cagrid.nci.nih.gov/8/gts}X509CRL" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrustedAuthority", propOrder = {
    "name",
    "trustLevels",
    "status",
    "isAuthority",
    "authorityGTS",
    "sourceGTS",
    "expires",
    "lastUpdated",
    "certificate",
    "crl"
})
public class TrustedAuthority
    implements Serializable
{

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "TrustLevels", required = true)
    protected TrustLevels trustLevels;
    @XmlElement(name = "Status", required = true)
    protected Status status;
    @XmlElement(name = "IsAuthority")
    protected Boolean isAuthority;
    @XmlElement(name = "AuthorityGTS")
    protected String authorityGTS;
    @XmlElement(name = "SourceGTS")
    protected String sourceGTS;
    @XmlElement(name = "Expires")
    protected long expires;
    @XmlElement(name = "LastUpdated")
    protected long lastUpdated;
    @XmlElement(name = "Certificate", required = true)
    protected X509Certificate certificate;
    @XmlElement(name = "CRL")
    protected X509CRL crl;

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

    /**
     * Gets the value of the expires property.
     * 
     */
    public long getExpires() {
        return expires;
    }

    /**
     * Sets the value of the expires property.
     * 
     */
    public void setExpires(long value) {
        this.expires = value;
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

    /**
     * Gets the value of the certificate property.
     * 
     * @return
     *     possible object is
     *     {@link X509Certificate }
     *     
     */
    public X509Certificate getCertificate() {
        return certificate;
    }

    /**
     * Sets the value of the certificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link X509Certificate }
     *     
     */
    public void setCertificate(X509Certificate value) {
        this.certificate = value;
    }

    /**
     * Gets the value of the crl property.
     * 
     * @return
     *     possible object is
     *     {@link X509CRL }
     *     
     */
    public X509CRL getCRL() {
        return crl;
    }

    /**
     * Sets the value of the crl property.
     * 
     * @param value
     *     allowed object is
     *     {@link X509CRL }
     *     
     */
    public void setCRL(X509CRL value) {
        this.crl = value;
    }

}
