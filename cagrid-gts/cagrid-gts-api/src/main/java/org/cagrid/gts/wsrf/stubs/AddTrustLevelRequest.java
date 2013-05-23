
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
 *         &lt;element name="trustLevel">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}TrustLevel"/>
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
    "trustLevel"
})
@XmlRootElement(name = "AddTrustLevelRequest")
public class AddTrustLevelRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected AddTrustLevelRequest.TrustLevel trustLevel;

    /**
     * Gets the value of the trustLevel property.
     * 
     * @return
     *     possible object is
     *     {@link AddTrustLevelRequest.TrustLevel }
     *     
     */
    public AddTrustLevelRequest.TrustLevel getTrustLevel() {
        return trustLevel;
    }

    /**
     * Sets the value of the trustLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddTrustLevelRequest.TrustLevel }
     *     
     */
    public void setTrustLevel(AddTrustLevelRequest.TrustLevel value) {
        this.trustLevel = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}TrustLevel"/>
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
        "trustLevel"
    })
    public static class TrustLevel
        implements Serializable
    {

        @XmlElement(name = "TrustLevel", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected org.cagrid.gts.model.TrustLevel trustLevel;

        /**
         * Gets the value of the trustLevel property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gts.model.TrustLevel }
         *     
         */
        public org.cagrid.gts.model.TrustLevel getTrustLevel() {
            return trustLevel;
        }

        /**
         * Sets the value of the trustLevel property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gts.model.TrustLevel }
         *     
         */
        public void setTrustLevel(org.cagrid.gts.model.TrustLevel value) {
            this.trustLevel = value;
        }

    }

}
