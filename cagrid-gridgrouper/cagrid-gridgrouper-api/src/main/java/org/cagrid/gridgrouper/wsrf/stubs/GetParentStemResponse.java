
package org.cagrid.gridgrouper.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gridgrouper.model.StemDescriptor;


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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}StemDescriptor"/>
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
    "stemDescriptor"
})
@XmlRootElement(name = "GetParentStemResponse")
public class GetParentStemResponse
    implements Serializable
{

    @XmlElement(name = "StemDescriptor", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
    protected StemDescriptor stemDescriptor;

    /**
     * Gets the value of the stemDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.model.StemDescriptor }
     *     
     */
    public StemDescriptor getStemDescriptor() {
        return stemDescriptor;
    }

    /**
     * Sets the value of the stemDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.model.StemDescriptor }
     *     
     */
    public void setStemDescriptor(StemDescriptor value) {
        this.stemDescriptor = value;
    }

}
