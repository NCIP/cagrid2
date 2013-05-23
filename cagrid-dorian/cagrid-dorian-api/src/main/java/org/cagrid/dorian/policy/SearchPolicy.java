
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="UserSearchPolicy" use="required" type="{http://cagrid.nci.nih.gov/1/dorian-policy}SearchPolicyType" />
 *       &lt;attribute name="HostSearchPolicy" use="required" type="{http://cagrid.nci.nih.gov/1/dorian-policy}SearchPolicyType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchPolicy")
public class SearchPolicy
    implements Serializable
{

    @XmlAttribute(name = "UserSearchPolicy", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected SearchPolicyType userSearchPolicy;
    @XmlAttribute(name = "HostSearchPolicy", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected SearchPolicyType hostSearchPolicy;

    /**
     * Gets the value of the userSearchPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link SearchPolicyType }
     *     
     */
    public SearchPolicyType getUserSearchPolicy() {
        return userSearchPolicy;
    }

    /**
     * Sets the value of the userSearchPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchPolicyType }
     *     
     */
    public void setUserSearchPolicy(SearchPolicyType value) {
        this.userSearchPolicy = value;
    }

    /**
     * Gets the value of the hostSearchPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link SearchPolicyType }
     *     
     */
    public SearchPolicyType getHostSearchPolicy() {
        return hostSearchPolicy;
    }

    /**
     * Sets the value of the hostSearchPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchPolicyType }
     *     
     */
    public void setHostSearchPolicy(SearchPolicyType value) {
        this.hostSearchPolicy = value;
    }

}
