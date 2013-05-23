
package org.cagrid.dorian.ifs;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2001.xmlschema.Adapter1;


/**
 * <p>Java class for FederationAuditRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FederationAuditRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TargetId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReportingPartyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AuditType" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}FederationAudit"/>
 *         &lt;element name="OccurredAt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="AuditMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FederationAuditRecord", propOrder = {
    "targetId",
    "reportingPartyId",
    "auditType",
    "occurredAt",
    "auditMessage"
})
public class FederationAuditRecord
    implements Serializable
{

    @XmlElement(name = "TargetId")
    protected String targetId;
    @XmlElement(name = "ReportingPartyId", required = true)
    protected String reportingPartyId;
    @XmlElement(name = "AuditType", required = true)
    protected FederationAudit auditType;
    @XmlElement(name = "OccurredAt", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar occurredAt;
    @XmlElement(name = "AuditMessage", required = true)
    protected String auditMessage;

    /**
     * Gets the value of the targetId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetId() {
        return targetId;
    }

    /**
     * Sets the value of the targetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetId(String value) {
        this.targetId = value;
    }

    /**
     * Gets the value of the reportingPartyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportingPartyId() {
        return reportingPartyId;
    }

    /**
     * Sets the value of the reportingPartyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportingPartyId(String value) {
        this.reportingPartyId = value;
    }

    /**
     * Gets the value of the auditType property.
     * 
     * @return
     *     possible object is
     *     {@link FederationAudit }
     *     
     */
    public FederationAudit getAuditType() {
        return auditType;
    }

    /**
     * Sets the value of the auditType property.
     * 
     * @param value
     *     allowed object is
     *     {@link FederationAudit }
     *     
     */
    public void setAuditType(FederationAudit value) {
        this.auditType = value;
    }

    /**
     * Gets the value of the occurredAt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getOccurredAt() {
        return occurredAt;
    }

    /**
     * Sets the value of the occurredAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOccurredAt(Calendar value) {
        this.occurredAt = value;
    }

    /**
     * Gets the value of the auditMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuditMessage() {
        return auditMessage;
    }

    /**
     * Sets the value of the auditMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuditMessage(String value) {
        this.auditMessage = value;
    }

}
