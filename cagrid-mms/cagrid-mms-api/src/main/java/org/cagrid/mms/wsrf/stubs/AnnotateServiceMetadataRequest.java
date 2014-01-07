
package org.cagrid.mms.wsrf.stubs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.mms.model.NamespaceToProjectMapping;


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
 *         &lt;element name="serviceMetadata">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata}ServiceMetadata"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="namespaceToProjectMappings">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{gme://caGrid.caBIG/2.0/org.cagrid.mms.domain}NamespaceToProjectMapping" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "namespaceToProjectMappings"
})
@XmlRootElement(name = "AnnotateServiceMetadataRequest")
public class AnnotateServiceMetadataRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected AnnotateServiceMetadataRequest.ServiceMetadata serviceMetadata;
    @XmlElement(required = true)
    protected AnnotateServiceMetadataRequest.NamespaceToProjectMappings namespaceToProjectMappings;

    /**
     * Gets the value of the serviceMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link AnnotateServiceMetadataRequest.ServiceMetadata }
     *     
     */
    public AnnotateServiceMetadataRequest.ServiceMetadata getServiceMetadata() {
        return serviceMetadata;
    }

    /**
     * Sets the value of the serviceMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnotateServiceMetadataRequest.ServiceMetadata }
     *     
     */
    public void setServiceMetadata(AnnotateServiceMetadataRequest.ServiceMetadata value) {
        this.serviceMetadata = value;
    }

    /**
     * Gets the value of the namespaceToProjectMappings property.
     * 
     * @return
     *     possible object is
     *     {@link AnnotateServiceMetadataRequest.NamespaceToProjectMappings }
     *     
     */
    public AnnotateServiceMetadataRequest.NamespaceToProjectMappings getNamespaceToProjectMappings() {
        return namespaceToProjectMappings;
    }

    /**
     * Sets the value of the namespaceToProjectMappings property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnotateServiceMetadataRequest.NamespaceToProjectMappings }
     *     
     */
    public void setNamespaceToProjectMappings(AnnotateServiceMetadataRequest.NamespaceToProjectMappings value) {
        this.namespaceToProjectMappings = value;
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
     *         &lt;element ref="{gme://caGrid.caBIG/2.0/org.cagrid.mms.domain}NamespaceToProjectMapping" maxOccurs="unbounded"/>
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
        "namespaceToProjectMapping"
    })
    public static class NamespaceToProjectMappings
        implements Serializable
    {

        @XmlElement(name = "NamespaceToProjectMapping", namespace = "gme://caGrid.caBIG/2.0/org.cagrid.mms.domain", required = true)
        protected List<NamespaceToProjectMapping> namespaceToProjectMapping;

        /**
         * Gets the value of the namespaceToProjectMapping property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the namespaceToProjectMapping property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNamespaceToProjectMapping().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link NamespaceToProjectMapping }
         * 
         * 
         */
        public List<NamespaceToProjectMapping> getNamespaceToProjectMapping() {
            if (namespaceToProjectMapping == null) {
                namespaceToProjectMapping = new ArrayList<NamespaceToProjectMapping>();
            }
            return this.namespaceToProjectMapping;
        }

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
     *         &lt;element ref="{gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata}ServiceMetadata"/>
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
        "serviceMetadata"
    })
    public static class ServiceMetadata
        implements Serializable
    {

        @XmlElement(name = "ServiceMetadata", namespace = "gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata", required = true)
        protected gov.nih.nci.cagrid.metadata.ServiceMetadata serviceMetadata;

        /**
         * Gets the value of the serviceMetadata property.
         * 
         * @return
         *     possible object is
         *     {@link gov.nih.nci.cagrid.metadata.ServiceMetadata }
         *     
         */
        public gov.nih.nci.cagrid.metadata.ServiceMetadata getServiceMetadata() {
            return serviceMetadata;
        }

        /**
         * Sets the value of the serviceMetadata property.
         * 
         * @param value
         *     allowed object is
         *     {@link gov.nih.nci.cagrid.metadata.ServiceMetadata }
         *     
         */
        public void setServiceMetadata(gov.nih.nci.cagrid.metadata.ServiceMetadata value) {
            this.serviceMetadata = value;
        }

    }

}
