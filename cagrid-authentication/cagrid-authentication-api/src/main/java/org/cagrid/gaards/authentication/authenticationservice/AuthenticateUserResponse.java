
package org.cagrid.gaards.authentication.authenticationservice;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.oasis.names.tc.saml.assertion.SAMLAssertion;


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
@XmlRootElement(name = "AuthenticateUserResponse")
public class AuthenticateUserResponse
    implements Serializable
{

    @XmlElement(name = "Assertion", namespace = "urn:oasis:names:tc:SAML:1.0:assertion", required = true)
    protected SAMLAssertion assertion;

    /**
     * Gets the value of the assertion property.
     * 
     * @return
     *     possible object is
     *     {@link SAMLAssertion }
     *     
     */
    public SAMLAssertion getAssertion() {
        return assertion;
    }

    /**
     * Sets the value of the assertion property.
     * 
     * @param value
     *     allowed object is
     *     {@link SAMLAssertion }
     *     
     */
    public void setAssertion(SAMLAssertion value) {
        this.assertion = value;
    }

}
