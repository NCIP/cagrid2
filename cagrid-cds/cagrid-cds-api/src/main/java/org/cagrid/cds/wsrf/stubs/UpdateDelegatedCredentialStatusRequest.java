
package org.cagrid.cds.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.cds.model.DelegationIdentifier;
import org.cagrid.cds.model.DelegationStatus;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://gaards.cagrid.org/cds}DelegationIdentifier"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="status">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://gaards.cagrid.org/cds}DelegationStatus"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "status"
})
@XmlRootElement(name = "UpdateDelegatedCredentialStatusRequest")
public class UpdateDelegatedCredentialStatusRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected Id id;
    @XmlElement(required = true)
    protected Status status;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.wsrf.stubs.UpdateDelegatedCredentialStatusRequest.Id }
     *     
     */
    public Id getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.wsrf.stubs.UpdateDelegatedCredentialStatusRequest.Id }
     *     
     */
    public void setId(Id value) {
        this.id = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.wsrf.stubs.UpdateDelegatedCredentialStatusRequest.Status }
     *     
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.wsrf.stubs.UpdateDelegatedCredentialStatusRequest.Status }
     *     
     */
    public void setStatus(Status value) {
        this.status = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{http://gaards.cagrid.org/cds}DelegationIdentifier"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "delegationIdentifier"
    })
    public static class Id
        implements Serializable
    {

        @XmlElement(name = "DelegationIdentifier", namespace = "http://gaards.cagrid.org/cds", required = true)
        protected DelegationIdentifier delegationIdentifier;

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

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{http://gaards.cagrid.org/cds}DelegationStatus"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "delegationStatus"
    })
    public static class Status
        implements Serializable
    {

        @XmlElement(name = "DelegationStatus", namespace = "http://gaards.cagrid.org/cds", required = true)
        protected DelegationStatus delegationStatus;

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

    }

}
