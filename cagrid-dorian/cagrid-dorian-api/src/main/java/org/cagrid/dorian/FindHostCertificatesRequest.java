
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
 *         &lt;element name="hostCertificateFilter">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateFilter"/>
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
    "hostCertificateFilter"
})
@XmlRootElement(name = "FindHostCertificatesRequest")
public class FindHostCertificatesRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected FindHostCertificatesRequest.HostCertificateFilter hostCertificateFilter;

    /**
     * Gets the value of the hostCertificateFilter property.
     * 
     * @return
     *     possible object is
     *     {@link FindHostCertificatesRequest.HostCertificateFilter }
     *     
     */
    public FindHostCertificatesRequest.HostCertificateFilter getHostCertificateFilter() {
        return hostCertificateFilter;
    }

    /**
     * Sets the value of the hostCertificateFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindHostCertificatesRequest.HostCertificateFilter }
     *     
     */
    public void setHostCertificateFilter(FindHostCertificatesRequest.HostCertificateFilter value) {
        this.hostCertificateFilter = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateFilter"/>
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
        "hostCertificateFilter"
    })
    public static class HostCertificateFilter
        implements Serializable
    {

        @XmlElement(name = "HostCertificateFilter", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.ifs.HostCertificateFilter hostCertificateFilter;

        /**
         * Gets the value of the hostCertificateFilter property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.ifs.HostCertificateFilter }
         *     
         */
        public org.cagrid.dorian.ifs.HostCertificateFilter getHostCertificateFilter() {
            return hostCertificateFilter;
        }

        /**
         * Sets the value of the hostCertificateFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.ifs.HostCertificateFilter }
         *     
         */
        public void setHostCertificateFilter(org.cagrid.dorian.ifs.HostCertificateFilter value) {
            this.hostCertificateFilter = value;
        }

    }

}
