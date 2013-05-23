
package org.cagrid.cds.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DelegationSigningResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DelegationSigningResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DelegationIdentifier" type="{http://gaards.cagrid.org/cds}DelegationIdentifier"/>
 *         &lt;element name="CertificateChain" type="{http://gaards.cagrid.org/cds}CertificateChain"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DelegationSigningResponse", propOrder = {
    "delegationIdentifier",
    "certificateChain"
})
public class DelegationSigningResponse
    implements Serializable
{

    @XmlElement(name = "DelegationIdentifier", required = true)
    protected DelegationIdentifier delegationIdentifier;
    @XmlElement(name = "CertificateChain", required = true)
    protected CertificateChain certificateChain;

    /**
     * Gets the value of the delegationIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.DelegationIdentifier }
     *     
     */
    public DelegationIdentifier getDelegationIdentifier() {
        return delegationIdentifier;
    }

    /**
     * Sets the value of the delegationIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.model.DelegationIdentifier }
     *     
     */
    public void setDelegationIdentifier(DelegationIdentifier value) {
        this.delegationIdentifier = value;
    }

    /**
     * Gets the value of the certificateChain property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.CertificateChain }
     *     
     */
    public CertificateChain getCertificateChain() {
        return certificateChain;
    }

    /**
     * Sets the value of the certificateChain property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.model.CertificateChain }
     *     
     */
    public void setCertificateChain(CertificateChain value) {
        this.certificateChain = value;
    }

}
