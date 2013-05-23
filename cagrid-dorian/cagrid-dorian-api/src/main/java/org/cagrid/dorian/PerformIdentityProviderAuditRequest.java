
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.idp.IdentityProviderAuditFilter;


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
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}IdentityProviderAuditFilter"/>
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
@XmlRootElement(name = "PerformIdentityProviderAuditRequest")
public class PerformIdentityProviderAuditRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected PerformIdentityProviderAuditRequest.F f;

    /**
     * Gets the value of the f property.
     * 
     * @return
     *     possible object is
     *     {@link PerformIdentityProviderAuditRequest.F }
     *     
     */
    public PerformIdentityProviderAuditRequest.F getF() {
        return f;
    }

    /**
     * Sets the value of the f property.
     * 
     * @param value
     *     allowed object is
     *     {@link PerformIdentityProviderAuditRequest.F }
     *     
     */
    public void setF(PerformIdentityProviderAuditRequest.F value) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}IdentityProviderAuditFilter"/>
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
        "identityProviderAuditFilter"
    })
    public static class F
        implements Serializable
    {

        @XmlElement(name = "IdentityProviderAuditFilter", namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", required = true)
        protected IdentityProviderAuditFilter identityProviderAuditFilter;

        /**
         * Gets the value of the identityProviderAuditFilter property.
         * 
         * @return
         *     possible object is
         *     {@link IdentityProviderAuditFilter }
         *     
         */
        public IdentityProviderAuditFilter getIdentityProviderAuditFilter() {
            return identityProviderAuditFilter;
        }

        /**
         * Sets the value of the identityProviderAuditFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link IdentityProviderAuditFilter }
         *     
         */
        public void setIdentityProviderAuditFilter(IdentityProviderAuditFilter value) {
            this.identityProviderAuditFilter = value;
        }

    }

}
