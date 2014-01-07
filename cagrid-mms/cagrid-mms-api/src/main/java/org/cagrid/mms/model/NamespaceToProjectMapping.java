
package org.cagrid.mms.model;

import java.io.Serializable;
import java.net.URI;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2001.xmlschema.Adapter1;


/**
 * 
 *         Used to identify a mapping from a Namespace to a Project, for the purposes
 *         of locating types.
 *       
 * 
 * <p>Java class for NamespaceToProjectMapping complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NamespaceToProjectMapping">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{gme://caGrid.caBIG/2.0/org.cagrid.mms.domain}UMLProjectIdentifer"/>
 *       &lt;/sequence>
 *       &lt;attribute name="namespaceURI" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NamespaceToProjectMapping", propOrder = {
    "umlProjectIdentifer"
})
public class NamespaceToProjectMapping
    implements Serializable
{

    @XmlElement(name = "UMLProjectIdentifer", required = true)
    protected UMLProjectIdentifer umlProjectIdentifer;
    @XmlAttribute(name = "namespaceURI", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "anyURI")
    protected URI namespaceURI;

    /**
     * Gets the value of the umlProjectIdentifer property.
     * 
     * @return
     *     possible object is
     *     {@link UMLProjectIdentifer }
     *     
     */
    public UMLProjectIdentifer getUMLProjectIdentifer() {
        return umlProjectIdentifer;
    }

    /**
     * Sets the value of the umlProjectIdentifer property.
     * 
     * @param value
     *     allowed object is
     *     {@link UMLProjectIdentifer }
     *     
     */
    public void setUMLProjectIdentifer(UMLProjectIdentifer value) {
        this.umlProjectIdentifer = value;
    }

    /**
     * Gets the value of the namespaceURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public URI getNamespaceURI() {
        return namespaceURI;
    }

    /**
     * Sets the value of the namespaceURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamespaceURI(URI value) {
        this.namespaceURI = value;
    }

}
