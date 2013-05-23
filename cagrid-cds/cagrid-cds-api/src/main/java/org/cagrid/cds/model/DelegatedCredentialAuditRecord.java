
package org.cagrid.cds.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DelegatedCredentialAuditRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DelegatedCredentialAuditRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DelegationIdentifier" type="{http://gaards.cagrid.org/cds}DelegationIdentifier"/>
 *         &lt;element name="SourceGridIdentity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Event" type="{http://gaards.cagrid.org/cds}DelegatedCredentialEvent"/>
 *         &lt;element name="OccurredAt" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DelegatedCredentialAuditRecord", propOrder = {
    "delegationIdentifier",
    "sourceGridIdentity",
    "event",
    "occurredAt",
    "message"
})
public class DelegatedCredentialAuditRecord
    implements Serializable
{

    @XmlElement(name = "DelegationIdentifier", required = true)
    protected DelegationIdentifier delegationIdentifier;
    @XmlElement(name = "SourceGridIdentity", required = true)
    protected String sourceGridIdentity;
    @XmlElement(name = "Event", required = true)
    protected DelegatedCredentialEvent event;
    @XmlElement(name = "OccurredAt")
    protected long occurredAt;
    @XmlElement(name = "Message", required = true)
    protected String message;

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
     * Gets the value of the occurredAt property.
     * 
     */
    public long getOccurredAt() {
        return occurredAt;
    }

    /**
     * Sets the value of the occurredAt property.
     * 
     */
    public void setOccurredAt(long value) {
        this.occurredAt = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
