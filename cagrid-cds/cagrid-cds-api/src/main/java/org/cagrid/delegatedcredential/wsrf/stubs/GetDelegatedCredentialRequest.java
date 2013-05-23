
package org.cagrid.delegatedcredential.wsrf.stubs;

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
 *         &lt;element name="publicKey">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://gaards.cagrid.org/cds}PublicKey"/>
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
    "publicKey"
})
@XmlRootElement(name = "GetDelegatedCredentialRequest")
public class GetDelegatedCredentialRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected PublicKey publicKey;

    /**
     * Gets the value of the publicKey property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.delegatedcredential.wsrf.stubs.GetDelegatedCredentialRequest.PublicKey }
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
     *     {@link org.cagrid.delegatedcredential.wsrf.stubs.GetDelegatedCredentialRequest.PublicKey }
     *     
     */
    public void setPublicKey(PublicKey value) {
        this.publicKey = value;
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
     *         &lt;element ref="{http://gaards.cagrid.org/cds}PublicKey"/>
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

        @XmlElement(name = "PublicKey", namespace = "http://gaards.cagrid.org/cds", required = true)
        protected org.cagrid.cds.model.PublicKey publicKey;

        /**
         * Gets the value of the publicKey property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.cds.model.PublicKey }
         *     
         */
        public org.cagrid.cds.model.PublicKey getPublicKey() {
            return publicKey;
        }

        /**
         * Sets the value of the publicKey property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.cds.model.PublicKey }
         *     
         */
        public void setPublicKey(org.cagrid.cds.model.PublicKey value) {
            this.publicKey = value;
        }

    }

}
