
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HostPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HostPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}HostCertificateLifetime"/>
 *       &lt;/sequence>
 *       &lt;attribute name="AdministrativeApprovalRequired" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="HostCertificateRenewalPolicy" use="required" type="{http://cagrid.nci.nih.gov/1/dorian-policy}HostCertificateRenewalPolicy" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HostPolicy", propOrder = {
    "hostCertificateLifetime"
})
public class HostPolicy
    implements Serializable
{

    @XmlElement(name = "HostCertificateLifetime", required = true)
    protected HostCertificateLifetime hostCertificateLifetime;
    @XmlAttribute(name = "AdministrativeApprovalRequired", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected boolean administrativeApprovalRequired;
    @XmlAttribute(name = "HostCertificateRenewalPolicy", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected HostCertificateRenewalPolicy hostCertificateRenewalPolicy;

    /**
     * Gets the value of the hostCertificateLifetime property.
     * 
     * @return
     *     possible object is
     *     {@link HostCertificateLifetime }
     *     
     */
    public HostCertificateLifetime getHostCertificateLifetime() {
        return hostCertificateLifetime;
    }

    /**
     * Sets the value of the hostCertificateLifetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link HostCertificateLifetime }
     *     
     */
    public void setHostCertificateLifetime(HostCertificateLifetime value) {
        this.hostCertificateLifetime = value;
    }

    /**
     * Gets the value of the administrativeApprovalRequired property.
     * 
     */
    public boolean isAdministrativeApprovalRequired() {
        return administrativeApprovalRequired;
    }

    /**
     * Sets the value of the administrativeApprovalRequired property.
     * 
     */
    public void setAdministrativeApprovalRequired(boolean value) {
        this.administrativeApprovalRequired = value;
    }

    /**
     * Gets the value of the hostCertificateRenewalPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link HostCertificateRenewalPolicy }
     *     
     */
    public HostCertificateRenewalPolicy getHostCertificateRenewalPolicy() {
        return hostCertificateRenewalPolicy;
    }

    /**
     * Sets the value of the hostCertificateRenewalPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link HostCertificateRenewalPolicy }
     *     
     */
    public void setHostCertificateRenewalPolicy(HostCertificateRenewalPolicy value) {
        this.hostCertificateRenewalPolicy = value;
    }

}
