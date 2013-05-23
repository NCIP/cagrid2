
package org.cagrid.gridgrouper.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gridgrouper.model.GroupCompositeType;
import org.cagrid.gridgrouper.model.GroupIdentifier;


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
 *         &lt;element name="type">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupCompositeType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="composite">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupIdentifier"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="left">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupIdentifier"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="right">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupIdentifier"/>
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
    "type",
    "composite",
    "left",
    "right"
})
@XmlRootElement(name = "AddCompositeMemberRequest")
public class AddCompositeMemberRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected Type type;
    @XmlElement(required = true)
    protected Composite composite;
    @XmlElement(required = true)
    protected Left left;
    @XmlElement(required = true)
    protected Right right;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.AddCompositeMemberRequest.Type }
     *     
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.AddCompositeMemberRequest.Type }
     *     
     */
    public void setType(Type value) {
        this.type = value;
    }

    /**
     * Gets the value of the composite property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.AddCompositeMemberRequest.Composite }
     *     
     */
    public Composite getComposite() {
        return composite;
    }

    /**
     * Sets the value of the composite property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.AddCompositeMemberRequest.Composite }
     *     
     */
    public void setComposite(Composite value) {
        this.composite = value;
    }

    /**
     * Gets the value of the left property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.AddCompositeMemberRequest.Left }
     *     
     */
    public Left getLeft() {
        return left;
    }

    /**
     * Sets the value of the left property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.AddCompositeMemberRequest.Left }
     *     
     */
    public void setLeft(Left value) {
        this.left = value;
    }

    /**
     * Gets the value of the right property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.AddCompositeMemberRequest.Right }
     *     
     */
    public Right getRight() {
        return right;
    }

    /**
     * Sets the value of the right property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.AddCompositeMemberRequest.Right }
     *     
     */
    public void setRight(Right value) {
        this.right = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupIdentifier"/>
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
        "groupIdentifier"
    })
    public static class Composite
        implements Serializable
    {

        @XmlElement(name = "GroupIdentifier", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
        protected GroupIdentifier groupIdentifier;

        /**
         * Gets the value of the groupIdentifier property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gridgrouper.model.GroupIdentifier }
         *     
         */
        public GroupIdentifier getGroupIdentifier() {
            return groupIdentifier;
        }

        /**
         * Sets the value of the groupIdentifier property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gridgrouper.model.GroupIdentifier }
         *     
         */
        public void setGroupIdentifier(GroupIdentifier value) {
            this.groupIdentifier = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupIdentifier"/>
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
        "groupIdentifier"
    })
    public static class Left
        implements Serializable
    {

        @XmlElement(name = "GroupIdentifier", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
        protected GroupIdentifier groupIdentifier;

        /**
         * Gets the value of the groupIdentifier property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gridgrouper.model.GroupIdentifier }
         *     
         */
        public GroupIdentifier getGroupIdentifier() {
            return groupIdentifier;
        }

        /**
         * Sets the value of the groupIdentifier property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gridgrouper.model.GroupIdentifier }
         *     
         */
        public void setGroupIdentifier(GroupIdentifier value) {
            this.groupIdentifier = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupIdentifier"/>
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
        "groupIdentifier"
    })
    public static class Right
        implements Serializable
    {

        @XmlElement(name = "GroupIdentifier", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
        protected GroupIdentifier groupIdentifier;

        /**
         * Gets the value of the groupIdentifier property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gridgrouper.model.GroupIdentifier }
         *     
         */
        public GroupIdentifier getGroupIdentifier() {
            return groupIdentifier;
        }

        /**
         * Sets the value of the groupIdentifier property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gridgrouper.model.GroupIdentifier }
         *     
         */
        public void setGroupIdentifier(GroupIdentifier value) {
            this.groupIdentifier = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupCompositeType"/>
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
        "groupCompositeType"
    })
    public static class Type
        implements Serializable
    {

        @XmlElement(name = "GroupCompositeType", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
        protected GroupCompositeType groupCompositeType;

        /**
         * Gets the value of the groupCompositeType property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gridgrouper.model.GroupCompositeType }
         *     
         */
        public GroupCompositeType getGroupCompositeType() {
            return groupCompositeType;
        }

        /**
         * Sets the value of the groupCompositeType property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gridgrouper.model.GroupCompositeType }
         *     
         */
        public void setGroupCompositeType(GroupCompositeType value) {
            this.groupCompositeType = value;
        }

    }

}
