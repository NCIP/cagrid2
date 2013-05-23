
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.common.SAMLAssertion;
import org.cagrid.dorian.ifs.DelegationPathLength;
import org.cagrid.dorian.ifs.ProxyLifetime;


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
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-common}SAMLAssertion"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="publicKey">
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
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}ProxyLifetime"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="delegation">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}DelegationPathLength"/>
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
    "publicKey",
    "lifetime",
    "delegation"
})
@XmlRootElement(name = "CreateProxyRequest")
public class CreateProxyRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected CreateProxyRequest.Saml saml;
    @XmlElement(required = true)
    protected CreateProxyRequest.PublicKey publicKey;
    @XmlElement(required = true)
    protected CreateProxyRequest.Lifetime lifetime;
    @XmlElement(required = true)
    protected CreateProxyRequest.Delegation delegation;

    /**
     * Gets the value of the saml property.
     * 
     * @return
     *     possible object is
     *     {@link CreateProxyRequest.Saml }
     *     
     */
    public CreateProxyRequest.Saml getSaml() {
        return saml;
    }

    /**
     * Sets the value of the saml property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateProxyRequest.Saml }
     *     
     */
    public void setSaml(CreateProxyRequest.Saml value) {
        this.saml = value;
    }

    /**
     * Gets the value of the publicKey property.
     * 
     * @return
     *     possible object is
     *     {@link CreateProxyRequest.PublicKey }
     *     
     */
    public CreateProxyRequest.PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Sets the value of the publicKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateProxyRequest.PublicKey }
     *     
     */
    public void setPublicKey(CreateProxyRequest.PublicKey value) {
        this.publicKey = value;
    }

    /**
     * Gets the value of the lifetime property.
     * 
     * @return
     *     possible object is
     *     {@link CreateProxyRequest.Lifetime }
     *     
     */
    public CreateProxyRequest.Lifetime getLifetime() {
        return lifetime;
    }

    /**
     * Sets the value of the lifetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateProxyRequest.Lifetime }
     *     
     */
    public void setLifetime(CreateProxyRequest.Lifetime value) {
        this.lifetime = value;
    }

    /**
     * Gets the value of the delegation property.
     * 
     * @return
     *     possible object is
     *     {@link CreateProxyRequest.Delegation }
     *     
     */
    public CreateProxyRequest.Delegation getDelegation() {
        return delegation;
    }

    /**
     * Sets the value of the delegation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateProxyRequest.Delegation }
     *     
     */
    public void setDelegation(CreateProxyRequest.Delegation value) {
        this.delegation = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}DelegationPathLength"/>
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
        "delegationPathLength"
    })
    public static class Delegation
        implements Serializable
    {

        @XmlElement(name = "DelegationPathLength", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected DelegationPathLength delegationPathLength;

        /**
         * Gets the value of the delegationPathLength property.
         * 
         * @return
         *     possible object is
         *     {@link DelegationPathLength }
         *     
         */
        public DelegationPathLength getDelegationPathLength() {
            return delegationPathLength;
        }

        /**
         * Sets the value of the delegationPathLength property.
         * 
         * @param value
         *     allowed object is
         *     {@link DelegationPathLength }
         *     
         */
        public void setDelegationPathLength(DelegationPathLength value) {
            this.delegationPathLength = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}ProxyLifetime"/>
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
        "proxyLifetime"
    })
    public static class Lifetime
        implements Serializable
    {

        @XmlElement(name = "ProxyLifetime", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected ProxyLifetime proxyLifetime;

        /**
         * Gets the value of the proxyLifetime property.
         * 
         * @return
         *     possible object is
         *     {@link ProxyLifetime }
         *     
         */
        public ProxyLifetime getProxyLifetime() {
            return proxyLifetime;
        }

        /**
         * Sets the value of the proxyLifetime property.
         * 
         * @param value
         *     allowed object is
         *     {@link ProxyLifetime }
         *     
         */
        public void setProxyLifetime(ProxyLifetime value) {
            this.proxyLifetime = value;
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
    public static class PublicKey
        implements Serializable
    {

        @XmlElement(name = "PublicKey", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.ifs.PublicKey publicKey;

        /**
         * Gets the value of the publicKey property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.ifs.PublicKey }
         *     
         */
        public org.cagrid.dorian.ifs.PublicKey getPublicKey() {
            return publicKey;
        }

        /**
         * Sets the value of the publicKey property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.ifs.PublicKey }
         *     
         */
        public void setPublicKey(org.cagrid.dorian.ifs.PublicKey value) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-common}SAMLAssertion"/>
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
        "samlAssertion"
    })
    public static class Saml
        implements Serializable
    {

        @XmlElement(name = "SAMLAssertion", namespace = "http://cagrid.nci.nih.gov/1/dorian-common", required = true)
        protected SAMLAssertion samlAssertion;

        /**
         * Gets the value of the samlAssertion property.
         * 
         * @return
         *     possible object is
         *     {@link SAMLAssertion }
         *     
         */
        public SAMLAssertion getSAMLAssertion() {
            return samlAssertion;
        }

        /**
         * Sets the value of the samlAssertion property.
         * 
         * @param value
         *     allowed object is
         *     {@link SAMLAssertion }
         *     
         */
        public void setSAMLAssertion(SAMLAssertion value) {
            this.samlAssertion = value;
        }

    }

}
