
package org.cagrid.cds.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.delegatedcredential.types.DelegatedCredentialReference;


/**
 * <p>Java class for DelegationDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DelegationDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/types}DelegatedCredentialReference"/>
 *         &lt;element name="GridIdentity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IssuedCredentialPathLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Expiration" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IssuedCredentialLifetime" type="{http://gaards.cagrid.org/cds}ProxyLifetime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DelegationDescriptor", propOrder = {
    "delegatedCredentialReference",
    "gridIdentity",
    "issuedCredentialPathLength",
    "expiration",
    "issuedCredentialLifetime"
})
public class DelegationDescriptor
    implements Serializable
{

    @XmlElement(name = "DelegatedCredentialReference", namespace = "http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/types", required = true)
    protected DelegatedCredentialReference delegatedCredentialReference;
    @XmlElement(name = "GridIdentity", required = true)
    protected String gridIdentity;
    @XmlElement(name = "IssuedCredentialPathLength")
    protected int issuedCredentialPathLength;
    @XmlElement(name = "Expiration")
    protected long expiration;
    @XmlElement(name = "IssuedCredentialLifetime", required = true)
    protected ProxyLifetime issuedCredentialLifetime;

    /**
     * Gets the value of the delegatedCredentialReference property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.delegatedcredential.types.DelegatedCredentialReference }
     *     
     */
    public DelegatedCredentialReference getDelegatedCredentialReference() {
        return delegatedCredentialReference;
    }

    /**
     * Sets the value of the delegatedCredentialReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.delegatedcredential.types.DelegatedCredentialReference }
     *     
     */
    public void setDelegatedCredentialReference(DelegatedCredentialReference value) {
        this.delegatedCredentialReference = value;
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

}
