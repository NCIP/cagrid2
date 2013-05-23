
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.idp.AccountProfile;


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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}AccountProfile"/>
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
    "accountProfile"
})
@XmlRootElement(name = "GetAccountProfileResponse")
public class GetAccountProfileResponse
    implements Serializable
{

    @XmlElement(name = "AccountProfile", namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", required = true)
    protected AccountProfile accountProfile;

    /**
     * Gets the value of the accountProfile property.
     * 
     * @return
     *     possible object is
     *     {@link AccountProfile }
     *     
     */
    public AccountProfile getAccountProfile() {
        return accountProfile;
    }

    /**
     * Sets the value of the accountProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountProfile }
     *     
     */
    public void setAccountProfile(AccountProfile value) {
        this.accountProfile = value;
    }

}
