
package org.cagrid.gaards.authentication;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;


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
 *         &lt;element ref="{gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata}ServiceMetadata"/>
 *         &lt;element ref="{http://gaards.cagrid.org/authentication}AuthenticationProfiles"/>
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
    "serviceMetadata",
    "authenticationProfiles"
})
@XmlRootElement(name = "AuthenticationServiceResourceProperties")
public class AuthenticationServiceResourceProperties
    implements Serializable
{

    @XmlElement(name = "ServiceMetadata", namespace = "gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata", required = true)
    protected ServiceMetadata serviceMetadata;
    @XmlElement(name = "AuthenticationProfiles", namespace = "http://gaards.cagrid.org/authentication", required = true)
    protected AuthenticationProfiles authenticationProfiles;

    /**
     * Gets the value of the serviceMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceMetadata }
     *     
     */
    public ServiceMetadata getServiceMetadata() {
        return serviceMetadata;
    }

    /**
     * Sets the value of the serviceMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceMetadata }
     *     
     */
    public void setServiceMetadata(ServiceMetadata value) {
        this.serviceMetadata = value;
    }

    /**
     * Gets the value of the authenticationProfiles property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationProfiles }
     *     
     */
    public AuthenticationProfiles getAuthenticationProfiles() {
        return authenticationProfiles;
    }

    /**
     * Sets the value of the authenticationProfiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationProfiles }
     *     
     */
    public void setAuthenticationProfiles(AuthenticationProfiles value) {
        this.authenticationProfiles = value;
    }

}
