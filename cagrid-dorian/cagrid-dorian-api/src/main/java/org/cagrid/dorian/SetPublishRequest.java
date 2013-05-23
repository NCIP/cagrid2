
package org.cagrid.dorian;

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
 *         &lt;element name="trustedIdP">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}TrustedIdP"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="publish" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "trustedIdP",
    "publish"
})
@XmlRootElement(name = "SetPublishRequest")
public class SetPublishRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected SetPublishRequest.TrustedIdP trustedIdP;
    protected boolean publish;

    /**
     * Gets the value of the trustedIdP property.
     * 
     * @return
     *     possible object is
     *     {@link SetPublishRequest.TrustedIdP }
     *     
     */
    public SetPublishRequest.TrustedIdP getTrustedIdP() {
        return trustedIdP;
    }

    /**
     * Sets the value of the trustedIdP property.
     * 
     * @param value
     *     allowed object is
     *     {@link SetPublishRequest.TrustedIdP }
     *     
     */
    public void setTrustedIdP(SetPublishRequest.TrustedIdP value) {
        this.trustedIdP = value;
    }

    /**
     * Gets the value of the publish property.
     * 
     */
    public boolean isPublish() {
        return publish;
    }

    /**
     * Sets the value of the publish property.
     * 
     */
    public void setPublish(boolean value) {
        this.publish = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}TrustedIdP"/>
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
        "trustedIdP"
    })
    public static class TrustedIdP
        implements Serializable
    {

        @XmlElement(name = "TrustedIdP", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.ifs.TrustedIdP trustedIdP;

        /**
         * Gets the value of the trustedIdP property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.ifs.TrustedIdP }
         *     
         */
        public org.cagrid.dorian.ifs.TrustedIdP getTrustedIdP() {
            return trustedIdP;
        }

        /**
         * Sets the value of the trustedIdP property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.ifs.TrustedIdP }
         *     
         */
        public void setTrustedIdP(org.cagrid.dorian.ifs.TrustedIdP value) {
            this.trustedIdP = value;
        }

    }

}
