
package org.cagrid.dorian.model.federation;

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
 * <p>Java class for FederationAuditFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FederationAuditFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TargetId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReportingPartyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AuditType" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}FederationAudit" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="AuditMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FederationAuditFilter", propOrder = {
    "targetId",
    "reportingPartyId",
    "auditType",
    "startDate",
    "endDate",
    "auditMessage"
})
public class FederationAuditFilter
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "TargetId")
    protected String targetId;
    @XmlElement(name = "ReportingPartyId")
    protected String reportingPartyId;
    @XmlElement(name = "AuditType")
    protected FederationAudit auditType;
    @XmlElement(name = "StartDate", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar startDate;
    @XmlElement(name = "EndDate", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar endDate;
    @XmlElement(name = "AuditMessage")
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
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(Calendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(Calendar value) {
        this.endDate = value;
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
            FederationAudit theAuditType;
            theAuditType = this.getAuditType();
            strategy.appendField(locator, this, "auditType", buffer, theAuditType);
        }
        {
            Calendar theStartDate;
            theStartDate = this.getStartDate();
            strategy.appendField(locator, this, "startDate", buffer, theStartDate);
        }
        {
            Calendar theEndDate;
            theEndDate = this.getEndDate();
            strategy.appendField(locator, this, "endDate", buffer, theEndDate);
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
            FederationAudit theAuditType;
            theAuditType = this.getAuditType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "auditType", theAuditType), currentHashCode, theAuditType);
        }
        {
            Calendar theStartDate;
            theStartDate = this.getStartDate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "startDate", theStartDate), currentHashCode, theStartDate);
        }
        {
            Calendar theEndDate;
            theEndDate = this.getEndDate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "endDate", theEndDate), currentHashCode, theEndDate);
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
        if (!(object instanceof FederationAuditFilter)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FederationAuditFilter that = ((FederationAuditFilter) object);
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
            FederationAudit lhsAuditType;
            lhsAuditType = this.getAuditType();
            FederationAudit rhsAuditType;
            rhsAuditType = that.getAuditType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "auditType", lhsAuditType), LocatorUtils.property(thatLocator, "auditType", rhsAuditType), lhsAuditType, rhsAuditType)) {
                return false;
            }
        }
        {
            Calendar lhsStartDate;
            lhsStartDate = this.getStartDate();
            Calendar rhsStartDate;
            rhsStartDate = that.getStartDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "startDate", lhsStartDate), LocatorUtils.property(thatLocator, "startDate", rhsStartDate), lhsStartDate, rhsStartDate)) {
                return false;
            }
        }
        {
            Calendar lhsEndDate;
            lhsEndDate = this.getEndDate();
            Calendar rhsEndDate;
            rhsEndDate = that.getEndDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "endDate", lhsEndDate), LocatorUtils.property(thatLocator, "endDate", rhsEndDate), lhsEndDate, rhsEndDate)) {
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
