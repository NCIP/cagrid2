
package org.cagrid.mms.wsrf.stubs;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.cagrid.mms.model.ModelSourceMetadata;


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
 *         &lt;element ref="{gme://caGrid.caBIG/1.0/org.cagrid.mms.domain}ModelSourceMetadata"/>
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
    "modelSourceMetadata"
})
@XmlRootElement(name = "MetadataModelServiceResourceProperties")
public class MetadataModelServiceResourceProperties
    implements Serializable
{

    @XmlElement(name = "ServiceMetadata", namespace = "gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata", required = true)
    protected ServiceMetadata serviceMetadata;
    @XmlElement(name = "ModelSourceMetadata", namespace = "gme://caGrid.caBIG/1.0/org.cagrid.mms.domain", required = true)
    protected ModelSourceMetadata modelSourceMetadata;

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
     * Gets the value of the modelSourceMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link ModelSourceMetadata }
     *     
     */
    public ModelSourceMetadata getModelSourceMetadata() {
        return modelSourceMetadata;
    }

    /**
     * Sets the value of the modelSourceMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModelSourceMetadata }
     *     
     */
    public void setModelSourceMetadata(ModelSourceMetadata value) {
        this.modelSourceMetadata = value;
    }

}
