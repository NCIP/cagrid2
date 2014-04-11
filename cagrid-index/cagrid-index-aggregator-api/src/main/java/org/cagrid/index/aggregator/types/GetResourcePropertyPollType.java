
package org.cagrid.index.aggregator.types;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for GetResourcePropertyPollType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetResourcePropertyPollType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://mds.globus.org/aggregator/types}AggregatorPollType">
 *       &lt;sequence>
 *         &lt;element name="ResourcePropertyName" type="{http://www.w3.org/2001/XMLSchema}QName"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetResourcePropertyPollType", propOrder = {
    "resourcePropertyName"
})
public class GetResourcePropertyPollType
    extends AggregatorPollType
    implements Serializable
{

    @XmlElement(name = "ResourcePropertyName", required = true)
    protected QName resourcePropertyName;

    /**
     * Gets the value of the resourcePropertyName property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getResourcePropertyName() {
        return resourcePropertyName;
    }

    /**
     * Sets the value of the resourcePropertyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setResourcePropertyName(QName value) {
        this.resourcePropertyName = value;
    }

}
