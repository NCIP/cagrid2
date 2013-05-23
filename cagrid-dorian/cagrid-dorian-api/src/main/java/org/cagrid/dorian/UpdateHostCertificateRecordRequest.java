
package org.cagrid.dorian;

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
 *         &lt;element name="hostCertificateUpdate">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateUpdate"/>
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
    "hostCertificateUpdate"
})
@XmlRootElement(name = "UpdateHostCertificateRecordRequest")
public class UpdateHostCertificateRecordRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected UpdateHostCertificateRecordRequest.HostCertificateUpdate hostCertificateUpdate;

    /**
     * Gets the value of the hostCertificateUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateHostCertificateRecordRequest.HostCertificateUpdate }
     *     
     */
    public UpdateHostCertificateRecordRequest.HostCertificateUpdate getHostCertificateUpdate() {
        return hostCertificateUpdate;
    }

    /**
     * Sets the value of the hostCertificateUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateHostCertificateRecordRequest.HostCertificateUpdate }
     *     
     */
    public void setHostCertificateUpdate(UpdateHostCertificateRecordRequest.HostCertificateUpdate value) {
        this.hostCertificateUpdate = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateUpdate"/>
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
        "hostCertificateUpdate"
    })
    public static class HostCertificateUpdate
        implements Serializable
    {

        @XmlElement(name = "HostCertificateUpdate", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.ifs.HostCertificateUpdate hostCertificateUpdate;

        /**
         * Gets the value of the hostCertificateUpdate property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.ifs.HostCertificateUpdate }
         *     
         */
        public org.cagrid.dorian.ifs.HostCertificateUpdate getHostCertificateUpdate() {
            return hostCertificateUpdate;
        }

        /**
         * Sets the value of the hostCertificateUpdate property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.ifs.HostCertificateUpdate }
         *     
         */
        public void setHostCertificateUpdate(org.cagrid.dorian.ifs.HostCertificateUpdate value) {
            this.hostCertificateUpdate = value;
        }

    }

}
