
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="hostSearchCriteria">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostSearchCriteria"/>
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
    "hostSearchCriteria"
})
@XmlRootElement(name = "HostSearchRequest")
public class HostSearchRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected HostSearchRequest.HostSearchCriteria hostSearchCriteria;

    /**
     * Gets the value of the hostSearchCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link HostSearchRequest.HostSearchCriteria }
     *     
     */
    public HostSearchRequest.HostSearchCriteria getHostSearchCriteria() {
        return hostSearchCriteria;
    }

    /**
     * Sets the value of the hostSearchCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link HostSearchRequest.HostSearchCriteria }
     *     
     */
    public void setHostSearchCriteria(HostSearchRequest.HostSearchCriteria value) {
        this.hostSearchCriteria = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostSearchCriteria"/>
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
        "hostSearchCriteria"
    })
    public static class HostSearchCriteria
        implements Serializable
    {

        @XmlElement(name = "HostSearchCriteria", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.ifs.HostSearchCriteria hostSearchCriteria;

        /**
         * Gets the value of the hostSearchCriteria property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.ifs.HostSearchCriteria }
         *     
         */
        public org.cagrid.dorian.ifs.HostSearchCriteria getHostSearchCriteria() {
            return hostSearchCriteria;
        }

        /**
         * Sets the value of the hostSearchCriteria property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.ifs.HostSearchCriteria }
         *     
         */
        public void setHostSearchCriteria(org.cagrid.dorian.ifs.HostSearchCriteria value) {
            this.hostSearchCriteria = value;
        }

    }

}
