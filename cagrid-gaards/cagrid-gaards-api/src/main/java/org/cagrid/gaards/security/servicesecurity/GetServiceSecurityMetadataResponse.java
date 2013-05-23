
package org.cagrid.gaards.security.servicesecurity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;


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
 *         &lt;element ref="{gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.security}ServiceSecurityMetadata"/>
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
    "serviceSecurityMetadata"
})
@XmlRootElement(name = "GetServiceSecurityMetadataResponse")
public class GetServiceSecurityMetadataResponse
    implements Serializable
{

    @XmlElement(name = "ServiceSecurityMetadata", namespace = "gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.security", required = true)
    protected ServiceSecurityMetadata serviceSecurityMetadata;

    /**
     * Gets the value of the serviceSecurityMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceSecurityMetadata }
     *     
     */
    public ServiceSecurityMetadata getServiceSecurityMetadata() {
        return serviceSecurityMetadata;
    }

    /**
     * Sets the value of the serviceSecurityMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceSecurityMetadata }
     *     
     */
    public void setServiceSecurityMetadata(ServiceSecurityMetadata value) {
        this.serviceSecurityMetadata = value;
    }

}
