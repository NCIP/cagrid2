
package org.cagrid.cds.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.cds.model.DelegationRecordFilter;


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
 *                   &lt;element ref="{http://gaards.cagrid.org/cds}DelegationRecordFilter"/>
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
@XmlRootElement(name = "FindDelegatedCredentialsRequest")
public class FindDelegatedCredentialsRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected Filter filter;

    /**
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.cds.wsrf.stubs.FindDelegatedCredentialsRequest.Filter }
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
     *     {@link org.cagrid.cds.wsrf.stubs.FindDelegatedCredentialsRequest.Filter }
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
     *         &lt;element ref="{http://gaards.cagrid.org/cds}DelegationRecordFilter"/>
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
        "delegationRecordFilter"
    })
    public static class Filter
        implements Serializable
    {

        @XmlElement(name = "DelegationRecordFilter", namespace = "http://gaards.cagrid.org/cds", required = true)
        protected DelegationRecordFilter delegationRecordFilter;

        /**
         * Gets the value of the delegationRecordFilter property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.cds.model.DelegationRecordFilter }
         *     
         */
        public DelegationRecordFilter getDelegationRecordFilter() {
            return delegationRecordFilter;
        }

        /**
         * Sets the value of the delegationRecordFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.cds.model.DelegationRecordFilter }
         *     
         */
        public void setDelegationRecordFilter(DelegationRecordFilter value) {
            this.delegationRecordFilter = value;
        }

    }

}
