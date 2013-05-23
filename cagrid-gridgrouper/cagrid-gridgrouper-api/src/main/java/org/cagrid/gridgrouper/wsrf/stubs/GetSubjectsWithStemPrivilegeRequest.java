
package org.cagrid.gridgrouper.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gridgrouper.model.StemIdentifier;
import org.cagrid.gridgrouper.model.StemPrivilegeType;


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
 *         &lt;element name="privilege">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}StemPrivilegeType"/>
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
    "stem",
    "privilege"
})
@XmlRootElement(name = "GetSubjectsWithStemPrivilegeRequest")
public class GetSubjectsWithStemPrivilegeRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected Stem stem;
    @XmlElement(required = true)
    protected Privilege privilege;

    /**
     * Gets the value of the stem property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.GetSubjectsWithStemPrivilegeRequest.Stem }
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
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.GetSubjectsWithStemPrivilegeRequest.Stem }
     *     
     */
    public void setStem(Stem value) {
        this.stem = value;
    }

    /**
     * Gets the value of the privilege property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.GetSubjectsWithStemPrivilegeRequest.Privilege }
     *     
     */
    public Privilege getPrivilege() {
        return privilege;
    }

    /**
     * Sets the value of the privilege property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.GetSubjectsWithStemPrivilegeRequest.Privilege }
     *     
     */
    public void setPrivilege(Privilege value) {
        this.privilege = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}StemPrivilegeType"/>
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
        "stemPrivilegeType"
    })
    public static class Privilege
        implements Serializable
    {

        @XmlElement(name = "StemPrivilegeType", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
        protected StemPrivilegeType stemPrivilegeType;

        /**
         * Gets the value of the stemPrivilegeType property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gridgrouper.model.StemPrivilegeType }
         *     
         */
        public StemPrivilegeType getStemPrivilegeType() {
            return stemPrivilegeType;
        }

        /**
         * Sets the value of the stemPrivilegeType property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gridgrouper.model.StemPrivilegeType }
         *     
         */
        public void setStemPrivilegeType(StemPrivilegeType value) {
            this.stemPrivilegeType = value;
        }

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
