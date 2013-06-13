
package org.cagrid.gme.wsrf.stubs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gme.model.XMLSchemaNamespace;


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
 *         &lt;element name="targetNamespaces">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchemaNamespace" maxOccurs="unbounded"/>
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
    "targetNamespaces"
})
@XmlRootElement(name = "DeleteXMLSchemasRequest")
public class DeleteXMLSchemasRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected TargetNamespaces targetNamespaces;

    /**
     * Gets the value of the targetNamespaces property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gme.wsrf.stubs.DeleteXMLSchemasRequest.TargetNamespaces }
     *     
     */
    public TargetNamespaces getTargetNamespaces() {
        return targetNamespaces;
    }

    /**
     * Sets the value of the targetNamespaces property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gme.wsrf.stubs.DeleteXMLSchemasRequest.TargetNamespaces }
     *     
     */
    public void setTargetNamespaces(TargetNamespaces value) {
        this.targetNamespaces = value;
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
     *         &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchemaNamespace" maxOccurs="unbounded"/>
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
        "xmlSchemaNamespace"
    })
    public static class TargetNamespaces
        implements Serializable
    {

        @XmlElement(name = "XMLSchemaNamespace", namespace = "gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", required = true)
        protected List<XMLSchemaNamespace> xmlSchemaNamespace;

        /**
         * Gets the value of the xmlSchemaNamespace property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the xmlSchemaNamespace property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getXMLSchemaNamespace().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link org.cagrid.gme.model.XMLSchemaNamespace }
         * 
         * 
         */
        public List<XMLSchemaNamespace> getXMLSchemaNamespace() {
            if (xmlSchemaNamespace == null) {
                xmlSchemaNamespace = new ArrayList<XMLSchemaNamespace>();
            }
            return this.xmlSchemaNamespace;
        }

    }

}
