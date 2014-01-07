
package org.cagrid.mms.wsrf.stubs;

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
 *         &lt;element ref="{gme://caGrid.caBIG/2.0/org.cagrid.mms.domain}ModelSourceMetadata"/>
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
    "modelSourceMetadata"
})
@XmlRootElement(name = "GetModelSourceMetadataResponse")
public class GetModelSourceMetadataResponse
    implements Serializable
{

    @XmlElement(name = "ModelSourceMetadata", namespace = "gme://caGrid.caBIG/2.0/org.cagrid.mms.domain", required = true)
    protected ModelSourceMetadata modelSourceMetadata;

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
