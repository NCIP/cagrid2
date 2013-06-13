
package org.cagrid.gme.wsrf.stubs;

import java.io.Serializable;
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
 *         &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchema"/>
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
@XmlRootElement(name = "GetXMLSchemaResponse")
public class GetXMLSchemaResponse
    implements Serializable
{

    @XmlElement(name = "XMLSchema", namespace = "gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", required = true)
    protected XMLSchema xmlSchema;

    /**
     * Gets the value of the xmlSchema property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gme.model.XMLSchema }
     *     
     */
    public XMLSchema getXMLSchema() {
        return xmlSchema;
    }

    /**
     * Sets the value of the xmlSchema property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gme.model.XMLSchema }
     *     
     */
    public void setXMLSchema(XMLSchema value) {
        this.xmlSchema = value;
    }

}
