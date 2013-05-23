
package org.cagrid.cds.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.delegatedcredential.types.DelegatedCredentialReference;


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
 *         &lt;element ref="{http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/types}DelegatedCredentialReference"/>
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
    "delegatedCredentialReference"
})
@XmlRootElement(name = "ApproveDelegationResponse")
public class ApproveDelegationResponse
    implements Serializable
{

    @XmlElement(name = "DelegatedCredentialReference", namespace = "http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/types", required = true)
    protected DelegatedCredentialReference delegatedCredentialReference;

    /**
     * Gets the value of the delegatedCredentialReference property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.delegatedcredential.types.DelegatedCredentialReference }
     *     
     */
    public DelegatedCredentialReference getDelegatedCredentialReference() {
        return delegatedCredentialReference;
    }

    /**
     * Sets the value of the delegatedCredentialReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.delegatedcredential.types.DelegatedCredentialReference }
     *     
     */
    public void setDelegatedCredentialReference(DelegatedCredentialReference value) {
        this.delegatedCredentialReference = value;
    }

}
