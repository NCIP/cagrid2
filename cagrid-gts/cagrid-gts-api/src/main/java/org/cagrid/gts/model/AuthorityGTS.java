
package org.cagrid.gts.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthorityGTS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthorityGTS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceURI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Priority" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SyncTrustLevels" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TimeToLive" type="{http://cagrid.nci.nih.gov/8/gts}TimeToLive"/>
 *         &lt;element name="PerformAuthorization" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ServiceIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorityGTS", propOrder = {
    "serviceURI",
    "priority",
    "syncTrustLevels",
    "timeToLive",
    "performAuthorization",
    "serviceIdentity"
})
public class AuthorityGTS
    implements Serializable
{

    @XmlElement(name = "ServiceURI", required = true)
    protected String serviceURI;
    @XmlElement(name = "Priority")
    protected int priority;
    @XmlElement(name = "SyncTrustLevels")
    protected boolean syncTrustLevels;
    @XmlElement(name = "TimeToLive", required = true)
    protected TimeToLive timeToLive;
    @XmlElement(name = "PerformAuthorization")
    protected boolean performAuthorization;
    @XmlElement(name = "ServiceIdentity")
    protected String serviceIdentity;

    /**
     * Gets the value of the serviceURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceURI() {
        return serviceURI;
    }

    /**
     * Sets the value of the serviceURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceURI(String value) {
        this.serviceURI = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     */
    public void setPriority(int value) {
        this.priority = value;
    }

    /**
     * Gets the value of the syncTrustLevels property.
     * 
     */
    public boolean isSyncTrustLevels() {
        return syncTrustLevels;
    }

    /**
     * Sets the value of the syncTrustLevels property.
     * 
     */
    public void setSyncTrustLevels(boolean value) {
        this.syncTrustLevels = value;
    }

    /**
     * Gets the value of the timeToLive property.
     * 
     * @return
     *     possible object is
     *     {@link TimeToLive }
     *     
     */
    public TimeToLive getTimeToLive() {
        return timeToLive;
    }

    /**
     * Sets the value of the timeToLive property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeToLive }
     *     
     */
    public void setTimeToLive(TimeToLive value) {
        this.timeToLive = value;
    }

    /**
     * Gets the value of the performAuthorization property.
     * 
     */
    public boolean isPerformAuthorization() {
        return performAuthorization;
    }

    /**
     * Sets the value of the performAuthorization property.
     * 
     */
    public void setPerformAuthorization(boolean value) {
        this.performAuthorization = value;
    }

    /**
     * Gets the value of the serviceIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceIdentity() {
        return serviceIdentity;
    }

    /**
     * Sets the value of the serviceIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceIdentity(String value) {
        this.serviceIdentity = value;
    }

}
