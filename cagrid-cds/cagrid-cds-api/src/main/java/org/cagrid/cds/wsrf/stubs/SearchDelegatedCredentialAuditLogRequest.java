
package org.cagrid.cds.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.cds.model.DelegatedCredentialAuditFilter;


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
 *         &lt;element name="f">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://gaards.cagrid.org/cds}DelegatedCredentialAuditFilter"/>
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
    "f"
})
@XmlRootElement(name = "SearchDelegatedCredentialAuditLogRequest")
public class SearchDelegatedCredentialAuditLogRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected F f;

    /**
     * Gets the value of the f property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.wsrf.stubs.SearchDelegatedCredentialAuditLogRequest.F }
     *     
     */
    public F getF() {
        return f;
    }

    /**
     * Sets the value of the f property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.cds.wsrf.stubs.SearchDelegatedCredentialAuditLogRequest.F }
     *     
     */
    public void setF(F value) {
        this.f = value;
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
     *         &lt;element ref="{http://gaards.cagrid.org/cds}DelegatedCredentialAuditFilter"/>
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
        "delegatedCredentialAuditFilter"
    })
    public static class F
        implements Serializable
    {

        @XmlElement(name = "DelegatedCredentialAuditFilter", namespace = "http://gaards.cagrid.org/cds", required = true)
        protected DelegatedCredentialAuditFilter delegatedCredentialAuditFilter;

        /**
         * Gets the value of the delegatedCredentialAuditFilter property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.cds.model.DelegatedCredentialAuditFilter }
         *     
         */
        public DelegatedCredentialAuditFilter getDelegatedCredentialAuditFilter() {
            return delegatedCredentialAuditFilter;
        }

        /**
         * Sets the value of the delegatedCredentialAuditFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.cds.model.DelegatedCredentialAuditFilter }
         *     
         */
        public void setDelegatedCredentialAuditFilter(DelegatedCredentialAuditFilter value) {
            this.delegatedCredentialAuditFilter = value;
        }

    }

}
