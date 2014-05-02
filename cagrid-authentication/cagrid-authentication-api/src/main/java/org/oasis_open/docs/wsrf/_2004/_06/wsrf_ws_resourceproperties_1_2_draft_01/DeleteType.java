
package org.oasis_open.docs.wsrf._2004._06.wsrf_ws_resourceproperties_1_2_draft_01;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for DeleteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeleteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="ResourceProperty" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteType")
public class DeleteType implements Serializable
{

    @XmlAttribute(name = "ResourceProperty", required = true)
    protected QName resourceProperty;

    /**
     * Gets the value of the resourceProperty property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getResourceProperty() {
        return resourceProperty;
    }

    /**
     * Sets the value of the resourceProperty property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setResourceProperty(QName value) {
        this.resourceProperty = value;
    }

}
