
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
 * <p>Java class for HostCertificateRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HostCertificateRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SerialNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Host" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Owner" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateStatus"/>
 *         &lt;element name="Certificate" type="{http://cagrid.nci.nih.gov/1/dorian-common}X509Certificate"/>
 *         &lt;element name="PublicKey" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}PublicKey"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HostCertificateRecord", propOrder = {
    "id",
    "serialNumber",
    "host",
    "subject",
    "owner",
    "status",
    "certificate",
    "publicKey"
})
public class HostCertificateRecord
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "Id")
    protected int id;
    @XmlElement(name = "SerialNumber")
    protected long serialNumber;
    @XmlElement(name = "Host", required = true)
    protected String host;
    @XmlElement(name = "Subject", required = true)
    protected String subject;
    @XmlElement(name = "Owner", required = true)
    protected String owner;
    @XmlElement(name = "Status", required = true)
    protected HostCertificateStatus status;
    @XmlElement(name = "Certificate", required = true)
    protected X509Certificate certificate;
    @XmlElement(name = "PublicKey", required = true)
    protected PublicKey publicKey;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

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
     * Gets the value of the host property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the value of the host property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link HostCertificateStatus }
     *     
     */
    public HostCertificateStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link HostCertificateStatus }
     *     
     */
    public void setStatus(HostCertificateStatus value) {
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
     * Gets the value of the publicKey property.
     * 
     * @return
     *     possible object is
     *     {@link PublicKey }
     *     
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Sets the value of the publicKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link PublicKey }
     *     
     */
    public void setPublicKey(PublicKey value) {
        this.publicKey = value;
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
            int theId;
            theId = (true?this.getId(): 0);
            strategy.appendField(locator, this, "id", buffer, theId);
        }
        {
            long theSerialNumber;
            theSerialNumber = (true?this.getSerialNumber(): 0L);
            strategy.appendField(locator, this, "serialNumber", buffer, theSerialNumber);
        }
        {
            String theHost;
            theHost = this.getHost();
            strategy.appendField(locator, this, "host", buffer, theHost);
        }
        {
            String theSubject;
            theSubject = this.getSubject();
            strategy.appendField(locator, this, "subject", buffer, theSubject);
        }
        {
            String theOwner;
            theOwner = this.getOwner();
            strategy.appendField(locator, this, "owner", buffer, theOwner);
        }
        {
            HostCertificateStatus theStatus;
            theStatus = this.getStatus();
            strategy.appendField(locator, this, "status", buffer, theStatus);
        }
        {
            X509Certificate theCertificate;
            theCertificate = this.getCertificate();
            strategy.appendField(locator, this, "certificate", buffer, theCertificate);
        }
        {
            PublicKey thePublicKey;
            thePublicKey = this.getPublicKey();
            strategy.appendField(locator, this, "publicKey", buffer, thePublicKey);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            int theId;
            theId = (true?this.getId(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "id", theId), currentHashCode, theId);
        }
        {
            long theSerialNumber;
            theSerialNumber = (true?this.getSerialNumber(): 0L);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "serialNumber", theSerialNumber), currentHashCode, theSerialNumber);
        }
        {
            String theHost;
            theHost = this.getHost();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "host", theHost), currentHashCode, theHost);
        }
        {
            String theSubject;
            theSubject = this.getSubject();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "subject", theSubject), currentHashCode, theSubject);
        }
        {
            String theOwner;
            theOwner = this.getOwner();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "owner", theOwner), currentHashCode, theOwner);
        }
        {
            HostCertificateStatus theStatus;
            theStatus = this.getStatus();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "status", theStatus), currentHashCode, theStatus);
        }
        {
            X509Certificate theCertificate;
            theCertificate = this.getCertificate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "certificate", theCertificate), currentHashCode, theCertificate);
        }
        {
            PublicKey thePublicKey;
            thePublicKey = this.getPublicKey();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "publicKey", thePublicKey), currentHashCode, thePublicKey);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof HostCertificateRecord)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final HostCertificateRecord that = ((HostCertificateRecord) object);
        {
            int lhsId;
            lhsId = (true?this.getId(): 0);
            int rhsId;
            rhsId = (true?that.getId(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "id", lhsId), LocatorUtils.property(thatLocator, "id", rhsId), lhsId, rhsId)) {
                return false;
            }
        }
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
            String lhsHost;
            lhsHost = this.getHost();
            String rhsHost;
            rhsHost = that.getHost();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "host", lhsHost), LocatorUtils.property(thatLocator, "host", rhsHost), lhsHost, rhsHost)) {
                return false;
            }
        }
        {
            String lhsSubject;
            lhsSubject = this.getSubject();
            String rhsSubject;
            rhsSubject = that.getSubject();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subject", lhsSubject), LocatorUtils.property(thatLocator, "subject", rhsSubject), lhsSubject, rhsSubject)) {
                return false;
            }
        }
        {
            String lhsOwner;
            lhsOwner = this.getOwner();
            String rhsOwner;
            rhsOwner = that.getOwner();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "owner", lhsOwner), LocatorUtils.property(thatLocator, "owner", rhsOwner), lhsOwner, rhsOwner)) {
                return false;
            }
        }
        {
            HostCertificateStatus lhsStatus;
            lhsStatus = this.getStatus();
            HostCertificateStatus rhsStatus;
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
            PublicKey lhsPublicKey;
            lhsPublicKey = this.getPublicKey();
            PublicKey rhsPublicKey;
            rhsPublicKey = that.getPublicKey();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "publicKey", lhsPublicKey), LocatorUtils.property(thatLocator, "publicKey", rhsPublicKey), lhsPublicKey, rhsPublicKey)) {
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
