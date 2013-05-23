
package org.cagrid.cds.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.cds.model.ClientDelegationFilter;


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
 *         &lt;element name="filter">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://gaards.cagrid.org/cds}ClientDelegationFilter"/>
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
    "filter"
})
@XmlRootElement(name = "FindCredentialsDelegatedToClientRequest")
public class FindCredentialsDelegatedToClientRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected Filter filter;

    /**
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.wsrf.stubs.FindCredentialsDelegatedToClientRequest.Filter }
     *     
     */
    public Filter getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.wsrf.stubs.FindCredentialsDelegatedToClientRequest.Filter }
     *     
     */
    public void setFilter(Filter value) {
        this.filter = value;
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
     *         &lt;element ref="{http://gaards.cagrid.org/cds}ClientDelegationFilter"/>
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
        "clientDelegationFilter"
    })
    public static class Filter
        implements Serializable
    {

        @XmlElement(name = "ClientDelegationFilter", namespace = "http://gaards.cagrid.org/cds", required = true)
        protected ClientDelegationFilter clientDelegationFilter;

        /**
         * Gets the value of the clientDelegationFilter property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.cds.model.ClientDelegationFilter }
         *     
         */
        public ClientDelegationFilter getClientDelegationFilter() {
            return clientDelegationFilter;
        }

        /**
         * Sets the value of the clientDelegationFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.cds.model.ClientDelegationFilter }
         *     
         */
        public void setClientDelegationFilter(ClientDelegationFilter value) {
            this.clientDelegationFilter = value;
        }

    }

}
