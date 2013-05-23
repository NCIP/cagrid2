
package org.cagrid.gridgrouper.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StemIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StemIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gridGrouperURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StemIdentifier", propOrder = {
    "gridGrouperURL",
    "stemName"
})
public class StemIdentifier
    implements Serializable
{

    @XmlElement(required = true)
    protected String gridGrouperURL;
    @XmlElement(required = true)
    protected String stemName;

    /**
     * Gets the value of the gridGrouperURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridGrouperURL() {
        return gridGrouperURL;
    }

    /**
     * Sets the value of the gridGrouperURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridGrouperURL(String value) {
        this.gridGrouperURL = value;
    }

    /**
     * Gets the value of the stemName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStemName() {
        return stemName;
    }

    /**
     * Sets the value of the stemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStemName(String value) {
        this.stemName = value;
    }

}
