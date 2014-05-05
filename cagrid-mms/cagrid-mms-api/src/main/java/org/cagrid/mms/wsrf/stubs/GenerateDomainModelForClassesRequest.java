
package org.cagrid.mms.wsrf.stubs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.cagrid.mms.model.UMLProjectIdentifer;


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
 *         &lt;element name="umlProjectIdentifer">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{gme://caGrid.caBIG/1.0/org.cagrid.mms.domain}UMLProjectIdentifer"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="fullyQualifiedClassNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
    "umlProjectIdentifer",
    "fullyQualifiedClassNames"
})
@XmlRootElement(name = "GenerateDomainModelForClassesRequest")
public class GenerateDomainModelForClassesRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected GenerateDomainModelForClassesRequest.UmlProjectIdentifer umlProjectIdentifer;
    @XmlElement(required = true)
    protected List<String> fullyQualifiedClassNames;

    /**
     * Gets the value of the umlProjectIdentifer property.
     * 
     * @return
     *     possible object is
     *     {@link GenerateDomainModelForClassesRequest.UmlProjectIdentifer }
     *     
     */
    public GenerateDomainModelForClassesRequest.UmlProjectIdentifer getUmlProjectIdentifer() {
        return umlProjectIdentifer;
    }

    /**
     * Sets the value of the umlProjectIdentifer property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenerateDomainModelForClassesRequest.UmlProjectIdentifer }
     *     
     */
    public void setUmlProjectIdentifer(GenerateDomainModelForClassesRequest.UmlProjectIdentifer value) {
        this.umlProjectIdentifer = value;
    }

    /**
     * Gets the value of the fullyQualifiedClassNames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fullyQualifiedClassNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFullyQualifiedClassNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFullyQualifiedClassNames() {
        if (fullyQualifiedClassNames == null) {
            fullyQualifiedClassNames = new ArrayList<String>();
        }
        return this.fullyQualifiedClassNames;
    }


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
     *         &lt;element ref="{gme://caGrid.caBIG/1.0/org.cagrid.mms.domain}UMLProjectIdentifer"/>
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
        "umlProjectIdentifer"
    })
    public static class UmlProjectIdentifer
        implements Serializable
    {

        @XmlElement(name = "UMLProjectIdentifer", namespace = "gme://caGrid.caBIG/1.0/org.cagrid.mms.domain", required = true)
        protected UMLProjectIdentifer umlProjectIdentifer;

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

    }

}
