
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
 *         &lt;element name="userCertificateFilter">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}UserCertificateFilter"/>
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
    "userCertificateFilter"
})
@XmlRootElement(name = "FindUserCertificatesRequest")
public class FindUserCertificatesRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected FindUserCertificatesRequest.UserCertificateFilter userCertificateFilter;

    /**
     * Gets the value of the userCertificateFilter property.
     * 
     * @return
     *     possible object is
     *     {@link FindUserCertificatesRequest.UserCertificateFilter }
     *     
     */
    public FindUserCertificatesRequest.UserCertificateFilter getUserCertificateFilter() {
        return userCertificateFilter;
    }

    /**
     * Sets the value of the userCertificateFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindUserCertificatesRequest.UserCertificateFilter }
     *     
     */
    public void setUserCertificateFilter(FindUserCertificatesRequest.UserCertificateFilter value) {
        this.userCertificateFilter = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}UserCertificateFilter"/>
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
        "userCertificateFilter"
    })
    public static class UserCertificateFilter
        implements Serializable
    {

        @XmlElement(name = "UserCertificateFilter", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.ifs.UserCertificateFilter userCertificateFilter;

        /**
         * Gets the value of the userCertificateFilter property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.ifs.UserCertificateFilter }
         *     
         */
        public org.cagrid.dorian.ifs.UserCertificateFilter getUserCertificateFilter() {
            return userCertificateFilter;
        }

        /**
         * Sets the value of the userCertificateFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.ifs.UserCertificateFilter }
         *     
         */
        public void setUserCertificateFilter(org.cagrid.dorian.ifs.UserCertificateFilter value) {
            this.userCertificateFilter = value;
        }

    }

}
