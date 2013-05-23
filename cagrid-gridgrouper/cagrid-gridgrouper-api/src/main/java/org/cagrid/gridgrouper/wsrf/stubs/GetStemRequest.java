
package org.cagrid.gridgrouper.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gridgrouper.model.StemIdentifier;


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
 *         &lt;element name="stem">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}StemIdentifier"/>
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
    "stem"
})
@XmlRootElement(name = "GetStemRequest")
public class GetStemRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected Stem stem;

    /**
     * Gets the value of the stem property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.GetStemRequest.Stem }
     *     
     */
    public Stem getStem() {
        return stem;
    }

    /**
     * Sets the value of the stem property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.GetStemRequest.Stem }
     *     
     */
    public void setStem(Stem value) {
        this.stem = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}StemIdentifier"/>
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
        "stemIdentifier"
    })
    public static class Stem
        implements Serializable
    {

        @XmlElement(name = "StemIdentifier", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
        protected StemIdentifier stemIdentifier;

        /**
         * Gets the value of the stemIdentifier property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gridgrouper.model.StemIdentifier }
         *     
         */
        public StemIdentifier getStemIdentifier() {
            return stemIdentifier;
        }

        /**
         * Sets the value of the stemIdentifier property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gridgrouper.model.StemIdentifier }
         *     
         */
        public void setStemIdentifier(StemIdentifier value) {
            this.stemIdentifier = value;
        }

    }

}
