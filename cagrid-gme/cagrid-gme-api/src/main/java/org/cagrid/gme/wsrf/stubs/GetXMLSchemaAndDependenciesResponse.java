
package org.cagrid.gme.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gme.model.XMLSchemaBundle;


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
 *         &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchemaBundle"/>
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
    "xmlSchemaBundle"
})
@XmlRootElement(name = "GetXMLSchemaAndDependenciesResponse")
public class GetXMLSchemaAndDependenciesResponse
    implements Serializable
{

    @XmlElement(name = "XMLSchemaBundle", namespace = "gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", required = true)
    protected XMLSchemaBundle xmlSchemaBundle;

    /**
     * Gets the value of the xmlSchemaBundle property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gme.model.XMLSchemaBundle }
     *     
     */
    public XMLSchemaBundle getXMLSchemaBundle() {
        return xmlSchemaBundle;
    }

    /**
     * Sets the value of the xmlSchemaBundle property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gme.model.XMLSchemaBundle }
     *     
     */
    public void setXMLSchemaBundle(XMLSchemaBundle value) {
        this.xmlSchemaBundle = value;
    }

}
