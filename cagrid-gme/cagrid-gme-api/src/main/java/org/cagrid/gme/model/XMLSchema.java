
package org.cagrid.gme.model;

import java.io.Serializable;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.Type;
import org.w3._2001.xmlschema.Adapter1;


/**
 * <p>Java class for XMLSchema complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="XMLSchema">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rootDocument">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchemaDocument"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="additionalDocuments" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchemaDocument" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="targetNamespace" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLSchema", propOrder = {
    "rootDocument",
    "additionalDocuments"
})
@Embeddable
public class XMLSchema
    implements Serializable
{

    @Column(nullable = false)
    @XmlElement(required = true)
    protected XMLSchema.RootDocument rootDocument;
    @CollectionOfElements
    @JoinTable(name = "xmlschema_additionaldocuments", joinColumns = {@JoinColumn(name = "referencing_xmlschema_id")})
    @XmlElementWrapper
    protected Set<XMLSchemaDocument> additionalDocuments;
    @XmlAttribute(name = "targetNamespace")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "anyURI")
    @Column(nullable = false, unique = true)
    @Type(type = "org.cagrid.gme.model.URIUserType")
    protected URI targetNamespace;

    /**
     * Gets the value of the rootDocument property.
     * 
     * @return
     *     possible object is
     *     {@link XMLSchema.RootDocument }
     *     
     */
    public XMLSchemaDocument getRootDocument() {
        return rootDocument.getXMLSchemaDocument();
    }

    /**
     * Sets the value of the rootDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLSchema.RootDocument }
     *     
     */
    public void setRootDocument(XMLSchemaDocument value) {
        this.rootDocument = new XMLSchema.RootDocument();
        rootDocument.setXMLSchemaDocument(value);
    }

    /**
     * Gets the value of the additionalDocuments property.
     * 
     * @return
     *     possible object is
     *     {@link XMLSchema.AdditionalDocuments }
     *     
     */
    public Set<XMLSchemaDocument> getAdditionalDocuments() {
    	if (additionalDocuments == null) {
    		additionalDocuments = new HashSet<XMLSchemaDocument>();
    	}
        return additionalDocuments;
    }

    public void setAdditionalDocuments(Set<XMLSchemaDocument> additionalDocuments) {
		this.additionalDocuments = additionalDocuments;
	}

	/**
     * Gets the value of the targetNamespace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public URI getTargetNamespace() {
        return targetNamespace;
    }

    /**
     * Sets the value of the targetNamespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetNamespace(URI value) {
        this.targetNamespace = value;
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
     *         &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchemaDocument"/>
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
        "xmlSchemaDocument"
    })
    @Embeddable
    public static class RootDocument 
        implements Serializable
    {

        @XmlElement(name = "XMLSchemaDocument", required = true)
        protected XMLSchemaDocument xmlSchemaDocument;

        /**
         * Gets the value of the xmlSchemaDocument property.
         * 
         * @return
         *     possible object is
         *     {@link XMLSchemaDocument }
         *     
         */
        public XMLSchemaDocument getXMLSchemaDocument() {
            return xmlSchemaDocument;
        }

        /**
         * Sets the value of the xmlSchemaDocument property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLSchemaDocument }
         *     
         */
        public void setXMLSchemaDocument(XMLSchemaDocument value) {
            this.xmlSchemaDocument = value;
        }

    }
}
