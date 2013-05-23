
package org.cagrid.cds.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DelegationRecordFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DelegationRecordFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DelegationIdentifier" type="{http://gaards.cagrid.org/cds}DelegationIdentifier"/>
 *         &lt;element name="GridIdentity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DelegationStatus" type="{http://gaards.cagrid.org/cds}DelegationStatus"/>
 *         &lt;element name="ExpirationStatus" type="{http://gaards.cagrid.org/cds}ExpirationStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DelegationRecordFilter", propOrder = {
    "delegationIdentifier",
    "gridIdentity",
    "delegationStatus",
    "expirationStatus"
})
public class DelegationRecordFilter
    implements Serializable
{

    @XmlElement(name = "DelegationIdentifier", required = true)
    protected DelegationIdentifier delegationIdentifier;
    @XmlElement(name = "GridIdentity", required = true)
    protected String gridIdentity;
    @XmlElement(name = "DelegationStatus", required = true)
    protected DelegationStatus delegationStatus;
    @XmlElement(name = "ExpirationStatus", required = true)
    protected ExpirationStatus expirationStatus;

    /**
     * Gets the value of the delegationIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.DelegationIdentifier }
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
     *     {@link org.cagrid.cds.model.DelegationIdentifier }
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
     *     {@link org.cagrid.cds.model.DelegationStatus }
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
     *     {@link org.cagrid.cds.model.DelegationStatus }
     *     
     */
    public void setDelegationStatus(DelegationStatus value) {
        this.delegationStatus = value;
    }

    /**
     * Gets the value of the expirationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.ExpirationStatus }
     *     
     */
    public ExpirationStatus getExpirationStatus() {
        return expirationStatus;
    }

    /**
     * Sets the value of the expirationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.model.ExpirationStatus }
     *     
     */
    public void setExpirationStatus(ExpirationStatus value) {
        this.expirationStatus = value;
    }

}
