
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gaards.authentication.BasicAuthentication;


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
 *                   &lt;element ref="{http://gaards.cagrid.org/authentication}BasicAuthentication"/>
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
@XmlRootElement(name = "ChangeLocalUserPasswordRequest")
public class ChangeLocalUserPasswordRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected ChangeLocalUserPasswordRequest.Credential credential;
    @XmlElement(required = true)
    protected String newPassword;

    /**
     * Gets the value of the credential property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeLocalUserPasswordRequest.Credential }
     *     
     */
    public ChangeLocalUserPasswordRequest.Credential getCredential() {
        return credential;
    }

    /**
     * Sets the value of the credential property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeLocalUserPasswordRequest.Credential }
     *     
     */
    public void setCredential(ChangeLocalUserPasswordRequest.Credential value) {
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
     *         &lt;element ref="{http://gaards.cagrid.org/authentication}BasicAuthentication"/>
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
        "basicAuthentication"
    })
    public static class Credential
        implements Serializable
    {

        @XmlElement(name = "BasicAuthentication", namespace = "http://gaards.cagrid.org/authentication", required = true)
        protected BasicAuthentication basicAuthentication;

        /**
         * Gets the value of the basicAuthentication property.
         * 
         * @return
         *     possible object is
         *     {@link BasicAuthentication }
         *     
         */
        public BasicAuthentication getBasicAuthentication() {
            return basicAuthentication;
        }

        /**
         * Sets the value of the basicAuthentication property.
         * 
         * @param value
         *     allowed object is
         *     {@link BasicAuthentication }
         *     
         */
        public void setBasicAuthentication(BasicAuthentication value) {
            this.basicAuthentication = value;
        }

    }

}
