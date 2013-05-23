
package org.cagrid.gts.wsrf.stubs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gts.model.TrustedAuthorityFilter;
import org.cagrid.gts.model.X509Certificate;


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
 *         &lt;element name="chain">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}X509Certificate" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "chain",
    "filter"
})
@XmlRootElement(name = "ValidateRequest")
public class ValidateRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected ValidateRequest.Chain chain;
    @XmlElement(required = true)
    protected ValidateRequest.Filter filter;

    /**
     * Gets the value of the chain property.
     * 
     * @return
     *     possible object is
     *     {@link ValidateRequest.Chain }
     *     
     */
    public ValidateRequest.Chain getChain() {
        return chain;
    }

    /**
     * Sets the value of the chain property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidateRequest.Chain }
     *     
     */
    public void setChain(ValidateRequest.Chain value) {
        this.chain = value;
    }

    /**
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link ValidateRequest.Filter }
     *     
     */
    public ValidateRequest.Filter getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidateRequest.Filter }
     *     
     */
    public void setFilter(ValidateRequest.Filter value) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}X509Certificate" maxOccurs="unbounded"/>
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
        "x509Certificate"
    })
    public static class Chain
        implements Serializable
    {

        @XmlElement(name = "X509Certificate", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected List<X509Certificate> x509Certificate;

        /**
         * Gets the value of the x509Certificate property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the x509Certificate property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getX509Certificate().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link X509Certificate }
         * 
         * 
         */
        public List<X509Certificate> getX509Certificate() {
            if (x509Certificate == null) {
                x509Certificate = new ArrayList<X509Certificate>();
            }
            return this.x509Certificate;
        }

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
