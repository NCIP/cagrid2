
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.ifs.CertificateLifetime;
import org.cagrid.dorian.ifs.PublicKey;
import org.oasis.names.tc.saml.assertion.AssertionType;


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
 *         &lt;element name="saml">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}Assertion"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="key">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}PublicKey"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="lifetime">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}CertificateLifetime"/>
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
    "saml",
    "key",
    "lifetime"
})
@XmlRootElement(name = "RequestUserCertificateRequest")
public class RequestUserCertificateRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected RequestUserCertificateRequest.Saml saml;
    @XmlElement(required = true)
    protected RequestUserCertificateRequest.Key key;
    @XmlElement(required = true)
    protected RequestUserCertificateRequest.Lifetime lifetime;

    /**
     * Gets the value of the saml property.
     * 
     * @return
     *     possible object is
     *     {@link RequestUserCertificateRequest.Saml }
     *     
     */
    public RequestUserCertificateRequest.Saml getSaml() {
        return saml;
    }

    /**
     * Sets the value of the saml property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestUserCertificateRequest.Saml }
     *     
     */
    public void setSaml(RequestUserCertificateRequest.Saml value) {
        this.saml = value;
    }

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link RequestUserCertificateRequest.Key }
     *     
     */
    public RequestUserCertificateRequest.Key getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestUserCertificateRequest.Key }
     *     
     */
    public void setKey(RequestUserCertificateRequest.Key value) {
        this.key = value;
    }

    /**
     * Gets the value of the lifetime property.
     * 
     * @return
     *     possible object is
     *     {@link RequestUserCertificateRequest.Lifetime }
     *     
     */
    public RequestUserCertificateRequest.Lifetime getLifetime() {
        return lifetime;
    }

    /**
     * Sets the value of the lifetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestUserCertificateRequest.Lifetime }
     *     
     */
    public void setLifetime(RequestUserCertificateRequest.Lifetime value) {
        this.lifetime = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}PublicKey"/>
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
        "publicKey"
    })
    public static class Key
        implements Serializable
    {

        @XmlElement(name = "PublicKey", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected PublicKey publicKey;

        /**
         * Gets the value of the publicKey property.
         * 
         * @return
         *     possible object is
         *     {@link PublicKey }
         *     
         */
        public PublicKey getPublicKey() {
            return publicKey;
        }

        /**
         * Sets the value of the publicKey property.
         * 
         * @param value
         *     allowed object is
         *     {@link PublicKey }
         *     
         */
        public void setPublicKey(PublicKey value) {
            this.publicKey = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}CertificateLifetime"/>
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
        "certificateLifetime"
    })
    public static class Lifetime
        implements Serializable
    {

        @XmlElement(name = "CertificateLifetime", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected CertificateLifetime certificateLifetime;

        /**
         * Gets the value of the certificateLifetime property.
         * 
         * @return
         *     possible object is
         *     {@link CertificateLifetime }
         *     
         */
        public CertificateLifetime getCertificateLifetime() {
            return certificateLifetime;
        }

        /**
         * Sets the value of the certificateLifetime property.
         * 
         * @param value
         *     allowed object is
         *     {@link CertificateLifetime }
         *     
         */
        public void setCertificateLifetime(CertificateLifetime value) {
            this.certificateLifetime = value;
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
     *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}Assertion"/>
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
        "assertion"
    })
    public static class Saml
        implements Serializable
    {

        @XmlElement(name = "Assertion", namespace = "urn:oasis:names:tc:SAML:1.0:assertion", required = true)
        protected AssertionType assertion;

        /**
         * Gets the value of the assertion property.
         * 
         * @return
         *     possible object is
         *     {@link AssertionType }
         *     
         */
        public AssertionType getAssertion() {
            return assertion;
        }

        /**
         * Sets the value of the assertion property.
         * 
         * @param value
         *     allowed object is
         *     {@link AssertionType }
         *     
         */
        public void setAssertion(AssertionType value) {
            this.assertion = value;
        }

    }

}
