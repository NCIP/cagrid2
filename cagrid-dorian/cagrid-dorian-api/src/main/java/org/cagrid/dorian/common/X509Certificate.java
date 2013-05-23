
package org.cagrid.dorian.common;

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
 *         &lt;element name="certificateAsString" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "certificateAsString"
})
public class X509Certificate
    implements Serializable
{

    @XmlElement(required = true)
    protected String certificateAsString;

    /**
     * Gets the value of the certificateAsString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificateAsString() {
        return certificateAsString;
    }

    /**
     * Sets the value of the certificateAsString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificateAsString(String value) {
        this.certificateAsString = value;
    }

}
