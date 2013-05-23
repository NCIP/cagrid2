
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.ifs.HostCertificateRequest;


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
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateRequest"/>
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
@XmlRootElement(name = "RequestHostCertificateRequest")
public class RequestHostCertificateRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected RequestHostCertificateRequest.Req req;

    /**
     * Gets the value of the req property.
     * 
     * @return
     *     possible object is
     *     {@link RequestHostCertificateRequest.Req }
     *     
     */
    public RequestHostCertificateRequest.Req getReq() {
        return req;
    }

    /**
     * Sets the value of the req property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestHostCertificateRequest.Req }
     *     
     */
    public void setReq(RequestHostCertificateRequest.Req value) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateRequest"/>
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
        "hostCertificateRequest"
    })
    public static class Req
        implements Serializable
    {

        @XmlElement(name = "HostCertificateRequest", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected HostCertificateRequest hostCertificateRequest;

        /**
         * Gets the value of the hostCertificateRequest property.
         * 
         * @return
         *     possible object is
         *     {@link HostCertificateRequest }
         *     
         */
        public HostCertificateRequest getHostCertificateRequest() {
            return hostCertificateRequest;
        }

        /**
         * Sets the value of the hostCertificateRequest property.
         * 
         * @param value
         *     allowed object is
         *     {@link HostCertificateRequest }
         *     
         */
        public void setHostCertificateRequest(HostCertificateRequest value) {
            this.hostCertificateRequest = value;
        }

    }

}
