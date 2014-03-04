
package org.cagrid.mms.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;


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
 *         &lt;element ref="{gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.dataservice}DomainModel"/>
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
    "domainModel"
})
@XmlRootElement(name = "GenerateDomainModelForPackagesResponse")
public class GenerateDomainModelForPackagesResponse
    implements Serializable
{

    @XmlElement(name = "DomainModel", namespace = "gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.dataservice", required = true)
    protected DomainModel domainModel;

    /**
     * Gets the value of the domainModel property.
     * 
     * @return
     *     possible object is
     *     {@link DomainModel }
     *     
     */
    public DomainModel getDomainModel() {
        return domainModel;
    }

    /**
     * Sets the value of the domainModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainModel }
     *     
     */
    public void setDomainModel(DomainModel value) {
        this.domainModel = value;
    }

}
