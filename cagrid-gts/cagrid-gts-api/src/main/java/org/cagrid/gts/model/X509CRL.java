
package org.cagrid.gts.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for X509CRL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X509CRL">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="crlEncodedString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X509CRL", propOrder = {
    "crlEncodedString"
})
public class X509CRL
    implements Serializable
{

    @XmlElement(required = true)
    protected String crlEncodedString;

    /**
     * Gets the value of the crlEncodedString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrlEncodedString() {
        return crlEncodedString;
    }

    /**
     * Sets the value of the crlEncodedString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrlEncodedString(String value) {
        this.crlEncodedString = value;
    }

}
