
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.idp.BasicAuthCredential;


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
 *         &lt;element name="credential">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}BasicAuthCredential"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="newPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "credential",
    "newPassword"
})
@XmlRootElement(name = "ChangeIdPUserPasswordRequest")
public class ChangeIdPUserPasswordRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected ChangeIdPUserPasswordRequest.Credential credential;
    @XmlElement(required = true)
    protected String newPassword;

    /**
     * Gets the value of the credential property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeIdPUserPasswordRequest.Credential }
     *     
     */
    public ChangeIdPUserPasswordRequest.Credential getCredential() {
        return credential;
    }

    /**
     * Sets the value of the credential property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeIdPUserPasswordRequest.Credential }
     *     
     */
    public void setCredential(ChangeIdPUserPasswordRequest.Credential value) {
        this.credential = value;
    }

    /**
     * Gets the value of the newPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the value of the newPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewPassword(String value) {
        this.newPassword = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}BasicAuthCredential"/>
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
        "basicAuthCredential"
    })
    public static class Credential
        implements Serializable
    {

        @XmlElement(name = "BasicAuthCredential", namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", required = true)
        protected BasicAuthCredential basicAuthCredential;

        /**
         * Gets the value of the basicAuthCredential property.
         * 
         * @return
         *     possible object is
         *     {@link BasicAuthCredential }
         *     
         */
        public BasicAuthCredential getBasicAuthCredential() {
            return basicAuthCredential;
        }

        /**
         * Sets the value of the basicAuthCredential property.
         * 
         * @param value
         *     allowed object is
         *     {@link BasicAuthCredential }
         *     
         */
        public void setBasicAuthCredential(BasicAuthCredential value) {
            this.basicAuthCredential = value;
        }

    }

}
