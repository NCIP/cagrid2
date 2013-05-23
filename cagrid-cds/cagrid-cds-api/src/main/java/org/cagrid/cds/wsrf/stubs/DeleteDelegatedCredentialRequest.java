
package org.cagrid.cds.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.cds.model.DelegationIdentifier;


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
 *         &lt;element name="id">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://gaards.cagrid.org/cds}DelegationIdentifier"/>
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
    "id"
})
@XmlRootElement(name = "DeleteDelegatedCredentialRequest")
public class DeleteDelegatedCredentialRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected Id id;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.wsrf.stubs.DeleteDelegatedCredentialRequest.Id }
     *     
     */
    public Id getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.wsrf.stubs.DeleteDelegatedCredentialRequest.Id }
     *     
     */
    public void setId(Id value) {
        this.id = value;
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
     *         &lt;element ref="{http://gaards.cagrid.org/cds}DelegationIdentifier"/>
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
        "delegationIdentifier"
    })
    public static class Id
        implements Serializable
    {

        @XmlElement(name = "DelegationIdentifier", namespace = "http://gaards.cagrid.org/cds", required = true)
        protected DelegationIdentifier delegationIdentifier;

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

    }

}
