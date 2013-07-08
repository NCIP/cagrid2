
package org.cagrid.dorian.model.federation;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.common.X509Certificate;
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
 * <p>Java class for UserCertificateRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserCertificateRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SerialNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="GridIdentity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}UserCertificateStatus"/>
 *         &lt;element name="Certificate" type="{http://cagrid.nci.nih.gov/1/dorian-common}X509Certificate"/>
 *         &lt;element name="Notes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserCertificateRecord", propOrder = {
    "serialNumber",
    "gridIdentity",
    "status",
    "certificate",
    "notes"
})
public class UserCertificateRecord
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "SerialNumber")
    protected long serialNumber;
    @XmlElement(name = "GridIdentity", required = true)
    protected String gridIdentity;
    @XmlElement(name = "Status", required = true)
    protected UserCertificateStatus status;
    @XmlElement(name = "Certificate", required = true)
    protected X509Certificate certificate;
    @XmlElement(name = "Notes", required = true)
    protected String notes;

    /**
     * Gets the value of the serialNumber property.
     * 
     */
    public long getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the value of the serialNumber property.
     * 
     */
    public void setSerialNumber(long value) {
        this.serialNumber = value;
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
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link UserCertificateStatus }
     *     
     */
    public UserCertificateStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserCertificateStatus }
     *     
     */
    public void setStatus(UserCertificateStatus value) {
        this.status = value;
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
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
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
            long theSerialNumber;
            theSerialNumber = (true?this.getSerialNumber(): 0L);
            strategy.appendField(locator, this, "serialNumber", buffer, theSerialNumber);
        }
        {
            String theGridIdentity;
            theGridIdentity = this.getGridIdentity();
            strategy.appendField(locator, this, "gridIdentity", buffer, theGridIdentity);
        }
        {
            UserCertificateStatus theStatus;
            theStatus = this.getStatus();
            strategy.appendField(locator, this, "status", buffer, theStatus);
        }
        {
            X509Certificate theCertificate;
            theCertificate = this.getCertificate();
            strategy.appendField(locator, this, "certificate", buffer, theCertificate);
        }
        {
            String theNotes;
            theNotes = this.getNotes();
            strategy.appendField(locator, this, "notes", buffer, theNotes);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            long theSerialNumber;
            theSerialNumber = (true?this.getSerialNumber(): 0L);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "serialNumber", theSerialNumber), currentHashCode, theSerialNumber);
        }
        {
            String theGridIdentity;
            theGridIdentity = this.getGridIdentity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "gridIdentity", theGridIdentity), currentHashCode, theGridIdentity);
        }
        {
            UserCertificateStatus theStatus;
            theStatus = this.getStatus();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "status", theStatus), currentHashCode, theStatus);
        }
        {
            X509Certificate theCertificate;
            theCertificate = this.getCertificate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "certificate", theCertificate), currentHashCode, theCertificate);
        }
        {
            String theNotes;
            theNotes = this.getNotes();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "notes", theNotes), currentHashCode, theNotes);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof UserCertificateRecord)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final UserCertificateRecord that = ((UserCertificateRecord) object);
        {
            long lhsSerialNumber;
            lhsSerialNumber = (true?this.getSerialNumber(): 0L);
            long rhsSerialNumber;
            rhsSerialNumber = (true?that.getSerialNumber(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serialNumber", lhsSerialNumber), LocatorUtils.property(thatLocator, "serialNumber", rhsSerialNumber), lhsSerialNumber, rhsSerialNumber)) {
                return false;
            }
        }
        {
            String lhsGridIdentity;
            lhsGridIdentity = this.getGridIdentity();
            String rhsGridIdentity;
            rhsGridIdentity = that.getGridIdentity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gridIdentity", lhsGridIdentity), LocatorUtils.property(thatLocator, "gridIdentity", rhsGridIdentity), lhsGridIdentity, rhsGridIdentity)) {
                return false;
            }
        }
        {
            UserCertificateStatus lhsStatus;
            lhsStatus = this.getStatus();
            UserCertificateStatus rhsStatus;
            rhsStatus = that.getStatus();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "status", lhsStatus), LocatorUtils.property(thatLocator, "status", rhsStatus), lhsStatus, rhsStatus)) {
                return false;
            }
        }
        {
            X509Certificate lhsCertificate;
            lhsCertificate = this.getCertificate();
            X509Certificate rhsCertificate;
            rhsCertificate = that.getCertificate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "certificate", lhsCertificate), LocatorUtils.property(thatLocator, "certificate", rhsCertificate), lhsCertificate, rhsCertificate)) {
                return false;
            }
        }
        {
            String lhsNotes;
            lhsNotes = this.getNotes();
            String rhsNotes;
            rhsNotes = that.getNotes();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "notes", lhsNotes), LocatorUtils.property(thatLocator, "notes", rhsNotes), lhsNotes, rhsNotes)) {
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
