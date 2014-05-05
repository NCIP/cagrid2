
package org.cagrid.mms.wsrf.stubs;

import java.io.Serializable;

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
@XmlRootElement(name = "GenerateDomainModelForProjectRequest")
public class GenerateDomainModelForProjectRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected GenerateDomainModelForProjectRequest.UmlProjectIdentifer umlProjectIdentifer;

    /**
     * Gets the value of the umlProjectIdentifer property.
     * 
     * @return
     *     possible object is
     *     {@link GenerateDomainModelForProjectRequest.UmlProjectIdentifer }
     *     
     */
    public GenerateDomainModelForProjectRequest.UmlProjectIdentifer getUmlProjectIdentifer() {
        return umlProjectIdentifer;
    }

    /**
     * Sets the value of the umlProjectIdentifer property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenerateDomainModelForProjectRequest.UmlProjectIdentifer }
     *     
     */
    public void setUmlProjectIdentifer(GenerateDomainModelForProjectRequest.UmlProjectIdentifer value) {
        this.umlProjectIdentifer = value;
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
