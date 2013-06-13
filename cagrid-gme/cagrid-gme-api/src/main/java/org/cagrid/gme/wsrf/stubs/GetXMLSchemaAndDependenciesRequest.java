
package org.cagrid.gme.wsrf.stubs;

import java.io.Serializable;
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
 *         &lt;element name="targetNamespace">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchemaNamespace"/>
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
    "targetNamespace"
})
@XmlRootElement(name = "GetXMLSchemaAndDependenciesRequest")
public class GetXMLSchemaAndDependenciesRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected TargetNamespace targetNamespace;

    /**
     * Gets the value of the targetNamespace property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gme.wsrf.stubs.GetXMLSchemaAndDependenciesRequest.TargetNamespace }
     *     
     */
    public TargetNamespace getTargetNamespace() {
        return targetNamespace;
    }

    /**
     * Sets the value of the targetNamespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gme.wsrf.stubs.GetXMLSchemaAndDependenciesRequest.TargetNamespace }
     *     
     */
    public void setTargetNamespace(TargetNamespace value) {
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
     *         &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchemaNamespace"/>
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
    public static class TargetNamespace
        implements Serializable
    {

        @XmlElement(name = "XMLSchemaNamespace", namespace = "gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", required = true)
        protected XMLSchemaNamespace xmlSchemaNamespace;

        /**
         * Gets the value of the xmlSchemaNamespace property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gme.model.XMLSchemaNamespace }
         *     
         */
        public XMLSchemaNamespace getXMLSchemaNamespace() {
            return xmlSchemaNamespace;
        }

        /**
         * Sets the value of the xmlSchemaNamespace property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gme.model.XMLSchemaNamespace }
         *     
         */
        public void setXMLSchemaNamespace(XMLSchemaNamespace value) {
            this.xmlSchemaNamespace = value;
        }

    }

}
