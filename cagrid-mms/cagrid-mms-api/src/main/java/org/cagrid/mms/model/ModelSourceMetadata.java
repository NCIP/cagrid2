
package org.cagrid.mms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModelSourceMetadata complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModelSourceMetadata">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SupportedModelSources">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Source" type="{gme://caGrid.caBIG/2.0/org.cagrid.mms.domain}SourceDescriptor" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="defaultSourceIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModelSourceMetadata", propOrder = {
    "supportedModelSources"
})
public class ModelSourceMetadata
    implements Serializable
{

    @XmlElement(name = "SupportedModelSources", required = true)
    protected ModelSourceMetadata.SupportedModelSources supportedModelSources;
    @XmlAttribute(name = "defaultSourceIdentifier")
    protected String defaultSourceIdentifier;

    /**
     * Gets the value of the supportedModelSources property.
     * 
     * @return
     *     possible object is
     *     {@link ModelSourceMetadata.SupportedModelSources }
     *     
     */
    public ModelSourceMetadata.SupportedModelSources getSupportedModelSources() {
        return supportedModelSources;
    }

    /**
     * Sets the value of the supportedModelSources property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModelSourceMetadata.SupportedModelSources }
     *     
     */
    public void setSupportedModelSources(ModelSourceMetadata.SupportedModelSources value) {
        this.supportedModelSources = value;
    }

    /**
     * Gets the value of the defaultSourceIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultSourceIdentifier() {
        return defaultSourceIdentifier;
    }

    /**
     * Sets the value of the defaultSourceIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultSourceIdentifier(String value) {
        this.defaultSourceIdentifier = value;
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
     *         &lt;element name="Source" type="{gme://caGrid.caBIG/2.0/org.cagrid.mms.domain}SourceDescriptor" maxOccurs="unbounded"/>
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
        "source"
    })
    public static class SupportedModelSources
        implements Serializable
    {

        @XmlElement(name = "Source", required = true)
        protected List<SourceDescriptor> source;

        /**
         * Gets the value of the source property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the source property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSource().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SourceDescriptor }
         * 
         * 
         */
        public List<SourceDescriptor> getSource() {
            if (source == null) {
                source = new ArrayList<SourceDescriptor>();
            }
            return this.source;
        }

    }

}
