
package org.cagrid.gaards.authentication.service;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Credential complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Credential">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="BasicAuthenticationCredential" type="{http://cagrid.nci.nih.gov/1/authentication-service}BasicAuthenticationCredential"/>
 *         &lt;element name="CredentialExtension" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Credential", propOrder = {
    "basicAuthenticationCredential",
    "credentialExtension"
})
public class Credential
    implements Serializable
{

    @XmlElement(name = "BasicAuthenticationCredential")
    protected BasicAuthenticationCredential basicAuthenticationCredential;
    @XmlElement(name = "CredentialExtension")
    protected Object credentialExtension;

    /**
     * Gets the value of the basicAuthenticationCredential property.
     * 
     * @return
     *     possible object is
     *     {@link BasicAuthenticationCredential }
     *     
     */
    public BasicAuthenticationCredential getBasicAuthenticationCredential() {
        return basicAuthenticationCredential;
    }

    /**
     * Sets the value of the basicAuthenticationCredential property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicAuthenticationCredential }
     *     
     */
    public void setBasicAuthenticationCredential(BasicAuthenticationCredential value) {
        this.basicAuthenticationCredential = value;
    }

    /**
     * Gets the value of the credentialExtension property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getCredentialExtension() {
        return credentialExtension;
    }

    /**
     * Sets the value of the credentialExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setCredentialExtension(Object value) {
        this.credentialExtension = value;
    }

}
