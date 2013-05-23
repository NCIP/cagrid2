
package org.cagrid.cds.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="delegationSigningResponse">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://gaards.cagrid.org/cds}DelegationSigningResponse"/>
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
    "delegationSigningResponse"
})
@XmlRootElement(name = "ApproveDelegationRequest")
public class ApproveDelegationRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected DelegationSigningResponse delegationSigningResponse;

    /**
     * Gets the value of the delegationSigningResponse property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.wsrf.stubs.ApproveDelegationRequest.DelegationSigningResponse }
     *     
     */
    public DelegationSigningResponse getDelegationSigningResponse() {
        return delegationSigningResponse;
    }

    /**
     * Sets the value of the delegationSigningResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.wsrf.stubs.ApproveDelegationRequest.DelegationSigningResponse }
     *     
     */
    public void setDelegationSigningResponse(DelegationSigningResponse value) {
        this.delegationSigningResponse = value;
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
     *         &lt;element ref="{http://gaards.cagrid.org/cds}DelegationSigningResponse"/>
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
        "delegationSigningResponse"
    })
    public static class DelegationSigningResponse
        implements Serializable
    {

        @XmlElement(name = "DelegationSigningResponse", namespace = "http://gaards.cagrid.org/cds", required = true)
        protected org.cagrid.cds.model.DelegationSigningResponse delegationSigningResponse;

        /**
         * Gets the value of the delegationSigningResponse property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.cds.model.DelegationSigningResponse }
         *     
         */
        public org.cagrid.cds.model.DelegationSigningResponse getDelegationSigningResponse() {
            return delegationSigningResponse;
        }

        /**
         * Sets the value of the delegationSigningResponse property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.cds.model.DelegationSigningResponse }
         *     
         */
        public void setDelegationSigningResponse(org.cagrid.cds.model.DelegationSigningResponse value) {
            this.delegationSigningResponse = value;
        }

    }

}
