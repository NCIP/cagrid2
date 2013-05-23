
package org.cagrid.cds.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DelegatedCredentialAuditFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DelegatedCredentialAuditFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DelegationIdentifier" type="{http://gaards.cagrid.org/cds}DelegationIdentifier" minOccurs="0"/>
 *         &lt;element name="SourceGridIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Event" type="{http://gaards.cagrid.org/cds}DelegatedCredentialEvent" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DelegatedCredentialAuditFilter", propOrder = {
    "delegationIdentifier",
    "sourceGridIdentity",
    "event",
    "startDate",
    "endDate"
})
public class DelegatedCredentialAuditFilter
    implements Serializable
{

    @XmlElement(name = "DelegationIdentifier")
    protected DelegationIdentifier delegationIdentifier;
    @XmlElement(name = "SourceGridIdentity")
    protected String sourceGridIdentity;
    @XmlElement(name = "Event")
    protected DelegatedCredentialEvent event;
    @XmlElement(name = "StartDate")
    protected Long startDate;
    @XmlElement(name = "EndDate")
    protected Long endDate;

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
     * Gets the value of the sourceGridIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceGridIdentity() {
        return sourceGridIdentity;
    }

    /**
     * Sets the value of the sourceGridIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceGridIdentity(String value) {
        this.sourceGridIdentity = value;
    }

    /**
     * Gets the value of the event property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.DelegatedCredentialEvent }
     *     
     */
    public DelegatedCredentialEvent getEvent() {
        return event;
    }

    /**
     * Sets the value of the event property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.model.DelegatedCredentialEvent }
     *     
     */
    public void setEvent(DelegatedCredentialEvent value) {
        this.event = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStartDate(Long value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEndDate(Long value) {
        this.endDate = value;
    }

}
