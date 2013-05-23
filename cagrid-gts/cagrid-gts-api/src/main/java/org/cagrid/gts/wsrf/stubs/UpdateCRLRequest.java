
package org.cagrid.gts.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gts.model.X509CRL;


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
 *         &lt;element name="trustedAuthorityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="crl">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}X509CRL"/>
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
    "trustedAuthorityName",
    "crl"
})
@XmlRootElement(name = "UpdateCRLRequest")
public class UpdateCRLRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected String trustedAuthorityName;
    @XmlElement(required = true)
    protected UpdateCRLRequest.Crl crl;

    /**
     * Gets the value of the trustedAuthorityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustedAuthorityName() {
        return trustedAuthorityName;
    }

    /**
     * Sets the value of the trustedAuthorityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustedAuthorityName(String value) {
        this.trustedAuthorityName = value;
    }

    /**
     * Gets the value of the crl property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateCRLRequest.Crl }
     *     
     */
    public UpdateCRLRequest.Crl getCrl() {
        return crl;
    }

    /**
     * Sets the value of the crl property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateCRLRequest.Crl }
     *     
     */
    public void setCrl(UpdateCRLRequest.Crl value) {
        this.crl = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}X509CRL"/>
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
        "x509CRL"
    })
    public static class Crl
        implements Serializable
    {

        @XmlElement(name = "X509CRL", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected X509CRL x509CRL;

        /**
         * Gets the value of the x509CRL property.
         * 
         * @return
         *     possible object is
         *     {@link X509CRL }
         *     
         */
        public X509CRL getX509CRL() {
            return x509CRL;
        }

        /**
         * Sets the value of the x509CRL property.
         * 
         * @param value
         *     allowed object is
         *     {@link X509CRL }
         *     
         */
        public void setX509CRL(X509CRL value) {
            this.x509CRL = value;
        }

    }

}
