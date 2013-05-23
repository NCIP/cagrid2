
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}UserCertificateLifetime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserPolicy", propOrder = {
    "userCertificateLifetime"
})
public class UserPolicy
    implements Serializable
{

    @XmlElement(name = "UserCertificateLifetime", required = true)
    protected UserCertificateLifetime userCertificateLifetime;

    /**
     * Gets the value of the userCertificateLifetime property.
     * 
     * @return
     *     possible object is
     *     {@link UserCertificateLifetime }
     *     
     */
    public UserCertificateLifetime getUserCertificateLifetime() {
        return userCertificateLifetime;
    }

    /**
     * Sets the value of the userCertificateLifetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserCertificateLifetime }
     *     
     */
    public void setUserCertificateLifetime(UserCertificateLifetime value) {
        this.userCertificateLifetime = value;
    }

}
