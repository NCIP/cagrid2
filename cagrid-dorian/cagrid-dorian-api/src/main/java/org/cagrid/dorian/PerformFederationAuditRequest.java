
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.ifs.FederationAuditFilter;


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
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}FederationAuditFilter"/>
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
@XmlRootElement(name = "PerformFederationAuditRequest")
public class PerformFederationAuditRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected PerformFederationAuditRequest.F f;

    /**
     * Gets the value of the f property.
     * 
     * @return
     *     possible object is
     *     {@link PerformFederationAuditRequest.F }
     *     
     */
    public PerformFederationAuditRequest.F getF() {
        return f;
    }

    /**
     * Sets the value of the f property.
     * 
     * @param value
     *     allowed object is
     *     {@link PerformFederationAuditRequest.F }
     *     
     */
    public void setF(PerformFederationAuditRequest.F value) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}FederationAuditFilter"/>
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
        "federationAuditFilter"
    })
    public static class F
        implements Serializable
    {

        @XmlElement(name = "FederationAuditFilter", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected FederationAuditFilter federationAuditFilter;

        /**
         * Gets the value of the federationAuditFilter property.
         * 
         * @return
         *     possible object is
         *     {@link FederationAuditFilter }
         *     
         */
        public FederationAuditFilter getFederationAuditFilter() {
            return federationAuditFilter;
        }

        /**
         * Sets the value of the federationAuditFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link FederationAuditFilter }
         *     
         */
        public void setFederationAuditFilter(FederationAuditFilter value) {
            this.federationAuditFilter = value;
        }

    }

}
