
package org.cagrid.cds.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="gridIdentity" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "gridIdentity"
})
@XmlRootElement(name = "RemoveAdminRequest")
public class RemoveAdminRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected String gridIdentity;

    /**
     * Gets the value of the gridIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridIdentity() {
        return gridIdentity;
    }

    /**
     * Sets the value of the gridIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridIdentity(String value) {
        this.gridIdentity = value;
    }

}
