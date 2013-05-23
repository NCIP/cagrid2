
package org.cagrid.cds.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.cds.model.DelegationRequest;


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
 *         &lt;element name="req">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://gaards.cagrid.org/cds}DelegationRequest"/>
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
    "req"
})
@XmlRootElement(name = "InitiateDelegationRequest")
public class InitiateDelegationRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected Req req;

    /**
     * Gets the value of the req property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.wsrf.stubs.InitiateDelegationRequest.Req }
     *     
     */
    public Req getReq() {
        return req;
    }

    /**
     * Sets the value of the req property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.wsrf.stubs.InitiateDelegationRequest.Req }
     *     
     */
    public void setReq(Req value) {
        this.req = value;
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
     *         &lt;element ref="{http://gaards.cagrid.org/cds}DelegationRequest"/>
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
        "delegationRequest"
    })
    public static class Req
        implements Serializable
    {

        @XmlElement(name = "DelegationRequest", namespace = "http://gaards.cagrid.org/cds", required = true)
        protected DelegationRequest delegationRequest;

        /**
         * Gets the value of the delegationRequest property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.cds.model.DelegationRequest }
         *     
         */
        public DelegationRequest getDelegationRequest() {
            return delegationRequest;
        }

        /**
         * Sets the value of the delegationRequest property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.cds.model.DelegationRequest }
         *     
         */
        public void setDelegationRequest(DelegationRequest value) {
            this.delegationRequest = value;
        }

    }

}
