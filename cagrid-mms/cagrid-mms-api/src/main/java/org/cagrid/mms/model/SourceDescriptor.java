
package org.cagrid.mms.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Identifies a particular model source and its characteristics
 *       
 * 
 * <p>Java class for SourceDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SourceDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{gme://caGrid.caBIG/2.0/org.cagrid.mms.domain}SupportedProjectProperties"/>
 *       &lt;/sequence>
 *       &lt;attribute name="identifier" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SourceDescriptor", propOrder = {
    "supportedProjectProperties"
})
public class SourceDescriptor
    implements Serializable
{

    @XmlElement(name = "SupportedProjectProperties", required = true)
    protected SupportedProjectProperties supportedProjectProperties;
    @XmlAttribute(name = "identifier")
    protected String identifier;
    @XmlAttribute(name = "description")
    protected String description;

    /**
     * Gets the value of the supportedProjectProperties property.
     * 
     * @return
     *     possible object is
     *     {@link SupportedProjectProperties }
     *     
     */
    public SupportedProjectProperties getSupportedProjectProperties() {
        return supportedProjectProperties;
    }

    /**
     * Sets the value of the supportedProjectProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupportedProjectProperties }
     *     
     */
    public void setSupportedProjectProperties(SupportedProjectProperties value) {
        this.supportedProjectProperties = value;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifier(String value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
