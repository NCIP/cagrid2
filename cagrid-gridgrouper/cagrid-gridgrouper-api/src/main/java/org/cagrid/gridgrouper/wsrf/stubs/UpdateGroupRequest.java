
package org.cagrid.gridgrouper.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gridgrouper.model.GroupIdentifier;
import org.cagrid.gridgrouper.model.GroupUpdate;


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
 *         &lt;element name="group">
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
 *         &lt;element name="update">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupUpdate"/>
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
    "group",
    "update"
})
@XmlRootElement(name = "UpdateGroupRequest")
public class UpdateGroupRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected Group group;
    @XmlElement(required = true)
    protected Update update;

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.UpdateGroupRequest.Group }
     *     
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.UpdateGroupRequest.Group }
     *     
     */
    public void setGroup(Group value) {
        this.group = value;
    }

    /**
     * Gets the value of the update property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.UpdateGroupRequest.Update }
     *     
     */
    public Update getUpdate() {
        return update;
    }

    /**
     * Sets the value of the update property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.wsrf.stubs.UpdateGroupRequest.Update }
     *     
     */
    public void setUpdate(Update value) {
        this.update = value;
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
    public static class Group
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupUpdate"/>
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
        "groupUpdate"
    })
    public static class Update
        implements Serializable
    {

        @XmlElement(name = "GroupUpdate", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", required = true)
        protected GroupUpdate groupUpdate;

        /**
         * Gets the value of the groupUpdate property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gridgrouper.model.GroupUpdate }
         *     
         */
        public GroupUpdate getGroupUpdate() {
            return groupUpdate;
        }

        /**
         * Sets the value of the groupUpdate property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gridgrouper.model.GroupUpdate }
         *     
         */
        public void setGroupUpdate(GroupUpdate value) {
            this.groupUpdate = value;
        }

    }

}
