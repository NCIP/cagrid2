
package org.cagrid.cds.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DelegationRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DelegationRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DelegationIdentifier" type="{http://gaards.cagrid.org/cds}DelegationIdentifier"/>
 *         &lt;element name="GridIdentity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DelegationStatus" type="{http://gaards.cagrid.org/cds}DelegationStatus"/>
 *         &lt;element name="IssuedCredentialPathLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DateInitiated" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="DateApproved" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Expiration" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IssuedCredentialLifetime" type="{http://gaards.cagrid.org/cds}ProxyLifetime"/>
 *         &lt;element name="DelegationPolicy" type="{http://gaards.cagrid.org/cds}DelegationPolicy"/>
 *         &lt;element name="CertificateChain" type="{http://gaards.cagrid.org/cds}CertificateChain"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DelegationRecord", propOrder = {
    "delegationIdentifier",
    "gridIdentity",
    "delegationStatus",
    "issuedCredentialPathLength",
    "dateInitiated",
    "dateApproved",
    "expiration",
    "issuedCredentialLifetime",
    "delegationPolicy",
    "certificateChain"
})
public class DelegationRecord
    implements Serializable
{

    @XmlElement(name = "DelegationIdentifier", required = true)
    protected DelegationIdentifier delegationIdentifier;
    @XmlElement(name = "GridIdentity", required = true)
    protected String gridIdentity;
    @XmlElement(name = "DelegationStatus", required = true)
    protected DelegationStatus delegationStatus;
    @XmlElement(name = "IssuedCredentialPathLength")
    protected int issuedCredentialPathLength;
    @XmlElement(name = "DateInitiated")
    protected long dateInitiated;
    @XmlElement(name = "DateApproved")
    protected long dateApproved;
    @XmlElement(name = "Expiration")
    protected long expiration;
    @XmlElement(name = "IssuedCredentialLifetime", required = true)
    protected ProxyLifetime issuedCredentialLifetime;
    @XmlElement(name = "DelegationPolicy", required = true)
    protected DelegationPolicy delegationPolicy;
    @XmlElement(name = "CertificateChain", required = true)
    protected CertificateChain certificateChain;

    /**
     * Gets the value of the delegationIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link DelegationIdentifier }
     *     
     */
    public DelegationIdentifier getDelegationIdentifier() {
        return delegationIdentifier;
    }

    /**
     * Sets the value of the delegationIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link DelegationIdentifier }
     *     
     */
    public void setDelegationIdentifier(DelegationIdentifier value) {
        this.delegationIdentifier = value;
    }

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
     * Gets the value of the delegationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link DelegationStatus }
     *     
     */
    public DelegationStatus getDelegationStatus() {
        return delegationStatus;
    }

    /**
     * Sets the value of the delegationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link DelegationStatus }
     *     
     */
    public void setDelegationStatus(DelegationStatus value) {
        this.delegationStatus = value;
    }

    /**
     * Gets the value of the issuedCredentialPathLength property.
     * 
     */
    public int getIssuedCredentialPathLength() {
        return issuedCredentialPathLength;
    }

    /**
     * Sets the value of the issuedCredentialPathLength property.
     * 
     */
    public void setIssuedCredentialPathLength(int value) {
        this.issuedCredentialPathLength = value;
    }

    /**
     * Gets the value of the dateInitiated property.
     * 
     */
    public long getDateInitiated() {
        return dateInitiated;
    }

    /**
     * Sets the value of the dateInitiated property.
     * 
     */
    public void setDateInitiated(long value) {
        this.dateInitiated = value;
    }

    /**
     * Gets the value of the dateApproved property.
     * 
     */
    public long getDateApproved() {
        return dateApproved;
    }

    /**
     * Sets the value of the dateApproved property.
     * 
     */
    public void setDateApproved(long value) {
        this.dateApproved = value;
    }

    /**
     * Gets the value of the expiration property.
     * 
     */
    public long getExpiration() {
        return expiration;
    }

    /**
     * Sets the value of the expiration property.
     * 
     */
    public void setExpiration(long value) {
        this.expiration = value;
    }

    /**
     * Gets the value of the issuedCredentialLifetime property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.ProxyLifetime }
     *     
     */
    public ProxyLifetime getIssuedCredentialLifetime() {
        return issuedCredentialLifetime;
    }

    /**
     * Sets the value of the issuedCredentialLifetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.model.ProxyLifetime }
     *     
     */
    public void setIssuedCredentialLifetime(ProxyLifetime value) {
        this.issuedCredentialLifetime = value;
    }

    /**
     * Gets the value of the delegationPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.DelegationPolicy }
     *     
     */
    public DelegationPolicy getDelegationPolicy() {
        return delegationPolicy;
    }

    /**
     * Sets the value of the delegationPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.model.DelegationPolicy }
     *     
     */
    public void setDelegationPolicy(DelegationPolicy value) {
        this.delegationPolicy = value;
    }

    /**
     * Gets the value of the certificateChain property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.CertificateChain }
     *     
     */
    public CertificateChain getCertificateChain() {
        return certificateChain;
    }

    /**
     * Sets the value of the certificateChain property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.model.CertificateChain }
     *     
     */
    public void setCertificateChain(CertificateChain value) {
        this.certificateChain = value;
    }

}
