
package org.cagrid.gts.wsrf.stubs;

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
 *         &lt;element name="trustLevelName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "trustLevelName"
})
@XmlRootElement(name = "RemoveTrustLevelRequest")
public class RemoveTrustLevelRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected String trustLevelName;

    /**
     * Gets the value of the trustLevelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustLevelName() {
        return trustLevelName;
    }

    /**
     * Sets the value of the trustLevelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustLevelName(String value) {
        this.trustLevelName = value;
    }

}
