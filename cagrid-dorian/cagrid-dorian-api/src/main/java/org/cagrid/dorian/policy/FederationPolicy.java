
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FederationPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FederationPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}UserPolicy"/>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}HostPolicy"/>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-policy}SearchPolicy"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FederationPolicy", propOrder = {
    "userPolicy",
    "hostPolicy",
    "searchPolicy"
})
public class FederationPolicy
    implements Serializable
{

    @XmlElement(name = "UserPolicy", required = true)
    protected UserPolicy userPolicy;
    @XmlElement(name = "HostPolicy", required = true)
    protected HostPolicy hostPolicy;
    @XmlElement(name = "SearchPolicy", required = true)
    protected SearchPolicy searchPolicy;

    /**
     * Gets the value of the userPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link UserPolicy }
     *     
     */
    public UserPolicy getUserPolicy() {
        return userPolicy;
    }

    /**
     * Sets the value of the userPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserPolicy }
     *     
     */
    public void setUserPolicy(UserPolicy value) {
        this.userPolicy = value;
    }

    /**
     * Gets the value of the hostPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link HostPolicy }
     *     
     */
    public HostPolicy getHostPolicy() {
        return hostPolicy;
    }

    /**
     * Sets the value of the hostPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link HostPolicy }
     *     
     */
    public void setHostPolicy(HostPolicy value) {
        this.hostPolicy = value;
    }

    /**
     * Gets the value of the searchPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link SearchPolicy }
     *     
     */
    public SearchPolicy getSearchPolicy() {
        return searchPolicy;
    }

    /**
     * Sets the value of the searchPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchPolicy }
     *     
     */
    public void setSearchPolicy(SearchPolicy value) {
        this.searchPolicy = value;
    }

}
