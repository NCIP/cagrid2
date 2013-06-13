
package org.cagrid.gme.wsrf.stubs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gme.model.XMLSchema;


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
 *         &lt;element name="schemas">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchema" maxOccurs="unbounded"/>
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
    "schemas"
})
@XmlRootElement(name = "PublishXMLSchemasRequest")
public class PublishXMLSchemasRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected Schemas schemas;

    /**
     * Gets the value of the schemas property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gme.wsrf.stubs.PublishXMLSchemasRequest.Schemas }
     *     
     */
    public Schemas getSchemas() {
        return schemas;
    }

    /**
     * Sets the value of the schemas property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gme.wsrf.stubs.PublishXMLSchemasRequest.Schemas }
     *     
     */
    public void setSchemas(Schemas value) {
        this.schemas = value;
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
     *         &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchema" maxOccurs="unbounded"/>
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
        "xmlSchema"
    })
    public static class Schemas
        implements Serializable
    {

        @XmlElement(name = "XMLSchema", namespace = "gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", required = true)
        protected List<XMLSchema> xmlSchema;

        /**
         * Gets the value of the xmlSchema property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the xmlSchema property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getXMLSchema().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link org.cagrid.gme.model.XMLSchema }
         * 
         * 
         */
        public List<XMLSchema> getXMLSchema() {
            if (xmlSchema == null) {
                xmlSchema = new ArrayList<XMLSchema>();
            }
            return this.xmlSchema;
        }

    }

}
