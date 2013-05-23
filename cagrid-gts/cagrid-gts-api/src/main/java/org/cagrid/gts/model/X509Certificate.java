
package org.cagrid.gts.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for X509Certificate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X509Certificate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="certificateEncodedString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X509Certificate", propOrder = {
    "certificateEncodedString"
})
public class X509Certificate
    implements Serializable
{

    @XmlElement(required = true)
    protected String certificateEncodedString;

    /**
     * Gets the value of the certificateEncodedString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificateEncodedString() {
        return certificateEncodedString;
    }

    /**
     * Sets the value of the certificateEncodedString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificateEncodedString(String value) {
        this.certificateEncodedString = value;
    }

}
