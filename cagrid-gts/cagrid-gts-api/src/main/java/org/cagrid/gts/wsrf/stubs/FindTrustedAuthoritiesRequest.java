
package org.cagrid.gts.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gts.model.TrustedAuthorityFilter;


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
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}TrustedAuthorityFilter"/>
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
@XmlRootElement(name = "FindTrustedAuthoritiesRequest")
public class FindTrustedAuthoritiesRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected FindTrustedAuthoritiesRequest.Filter filter;

    /**
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link FindTrustedAuthoritiesRequest.Filter }
     *     
     */
    public FindTrustedAuthoritiesRequest.Filter getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindTrustedAuthoritiesRequest.Filter }
     *     
     */
    public void setFilter(FindTrustedAuthoritiesRequest.Filter value) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}TrustedAuthorityFilter"/>
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
        "trustedAuthorityFilter"
    })
    public static class Filter
        implements Serializable
    {

        @XmlElement(name = "TrustedAuthorityFilter", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected TrustedAuthorityFilter trustedAuthorityFilter;

        /**
         * Gets the value of the trustedAuthorityFilter property.
         * 
         * @return
         *     possible object is
         *     {@link TrustedAuthorityFilter }
         *     
         */
        public TrustedAuthorityFilter getTrustedAuthorityFilter() {
            return trustedAuthorityFilter;
        }

        /**
         * Sets the value of the trustedAuthorityFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link TrustedAuthorityFilter }
         *     
         */
        public void setTrustedAuthorityFilter(TrustedAuthorityFilter value) {
            this.trustedAuthorityFilter = value;
        }

    }

}
