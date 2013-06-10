
package org.cagrid.dorian.idp;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
import org.w3._2001.xmlschema.Adapter1;


/**
 * <p>Java class for IdentityProviderAuditRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentityProviderAuditRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TargetId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReportingPartyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AuditType" type="{http://cagrid.nci.nih.gov/1/dorian-idp}IdentityProviderAudit"/>
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
@XmlType(name = "IdentityProviderAuditRecord", propOrder = {
    "targetId",
    "reportingPartyId",
    "auditType",
    "occurredAt",
    "auditMessage"
})
public class IdentityProviderAuditRecord
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "TargetId")
    protected String targetId;
    @XmlElement(name = "ReportingPartyId", required = true)
    protected String reportingPartyId;
    @XmlElement(name = "AuditType", required = true)
    protected IdentityProviderAudit auditType;
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
     *     {@link IdentityProviderAudit }
     *     
     */
    public IdentityProviderAudit getAuditType() {
        return auditType;
    }

    /**
     * Sets the value of the auditType property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentityProviderAudit }
     *     
     */
    public void setAuditType(IdentityProviderAudit value) {
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
            String theTargetId;
            theTargetId = this.getTargetId();
            strategy.appendField(locator, this, "targetId", buffer, theTargetId);
        }
        {
            String theReportingPartyId;
            theReportingPartyId = this.getReportingPartyId();
            strategy.appendField(locator, this, "reportingPartyId", buffer, theReportingPartyId);
        }
        {
            IdentityProviderAudit theAuditType;
            theAuditType = this.getAuditType();
            strategy.appendField(locator, this, "auditType", buffer, theAuditType);
        }
        {
            Calendar theOccurredAt;
            theOccurredAt = this.getOccurredAt();
            strategy.appendField(locator, this, "occurredAt", buffer, theOccurredAt);
        }
        {
            String theAuditMessage;
            theAuditMessage = this.getAuditMessage();
            strategy.appendField(locator, this, "auditMessage", buffer, theAuditMessage);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theTargetId;
            theTargetId = this.getTargetId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "targetId", theTargetId), currentHashCode, theTargetId);
        }
        {
            String theReportingPartyId;
            theReportingPartyId = this.getReportingPartyId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "reportingPartyId", theReportingPartyId), currentHashCode, theReportingPartyId);
        }
        {
            IdentityProviderAudit theAuditType;
            theAuditType = this.getAuditType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "auditType", theAuditType), currentHashCode, theAuditType);
        }
        {
            Calendar theOccurredAt;
            theOccurredAt = this.getOccurredAt();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "occurredAt", theOccurredAt), currentHashCode, theOccurredAt);
        }
        {
            String theAuditMessage;
            theAuditMessage = this.getAuditMessage();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "auditMessage", theAuditMessage), currentHashCode, theAuditMessage);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof IdentityProviderAuditRecord)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final IdentityProviderAuditRecord that = ((IdentityProviderAuditRecord) object);
        {
            String lhsTargetId;
            lhsTargetId = this.getTargetId();
            String rhsTargetId;
            rhsTargetId = that.getTargetId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "targetId", lhsTargetId), LocatorUtils.property(thatLocator, "targetId", rhsTargetId), lhsTargetId, rhsTargetId)) {
                return false;
            }
        }
        {
            String lhsReportingPartyId;
            lhsReportingPartyId = this.getReportingPartyId();
            String rhsReportingPartyId;
            rhsReportingPartyId = that.getReportingPartyId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "reportingPartyId", lhsReportingPartyId), LocatorUtils.property(thatLocator, "reportingPartyId", rhsReportingPartyId), lhsReportingPartyId, rhsReportingPartyId)) {
                return false;
            }
        }
        {
            IdentityProviderAudit lhsAuditType;
            lhsAuditType = this.getAuditType();
            IdentityProviderAudit rhsAuditType;
            rhsAuditType = that.getAuditType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "auditType", lhsAuditType), LocatorUtils.property(thatLocator, "auditType", rhsAuditType), lhsAuditType, rhsAuditType)) {
                return false;
            }
        }
        {
            Calendar lhsOccurredAt;
            lhsOccurredAt = this.getOccurredAt();
            Calendar rhsOccurredAt;
            rhsOccurredAt = that.getOccurredAt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "occurredAt", lhsOccurredAt), LocatorUtils.property(thatLocator, "occurredAt", rhsOccurredAt), lhsOccurredAt, rhsOccurredAt)) {
                return false;
            }
        }
        {
            String lhsAuditMessage;
            lhsAuditMessage = this.getAuditMessage();
            String rhsAuditMessage;
            rhsAuditMessage = that.getAuditMessage();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "auditMessage", lhsAuditMessage), LocatorUtils.property(thatLocator, "auditMessage", rhsAuditMessage), lhsAuditMessage, rhsAuditMessage)) {
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
