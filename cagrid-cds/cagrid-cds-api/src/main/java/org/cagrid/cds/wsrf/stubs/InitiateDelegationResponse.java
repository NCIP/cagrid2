
package org.cagrid.cds.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.cds.model.DelegationSigningRequest;


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
 *         &lt;element ref="{http://gaards.cagrid.org/cds}DelegationSigningRequest"/>
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
    "delegationSigningRequest"
})
@XmlRootElement(name = "InitiateDelegationResponse")
public class InitiateDelegationResponse
    implements Serializable
{

    @XmlElement(name = "DelegationSigningRequest", namespace = "http://gaards.cagrid.org/cds", required = true)
    protected DelegationSigningRequest delegationSigningRequest;

    /**
     * Gets the value of the delegationSigningRequest property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.model.DelegationSigningRequest }
     *     
     */
    public DelegationSigningRequest getDelegationSigningRequest() {
        return delegationSigningRequest;
    }

    /**
     * Sets the value of the delegationSigningRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.model.DelegationSigningRequest }
     *     
     */
    public void setDelegationSigningRequest(DelegationSigningRequest value) {
        this.delegationSigningRequest = value;
    }

}
