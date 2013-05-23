
package org.cagrid.gridgrouper.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MembershipDescriptor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MembershipDescriptor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Member" type="{http://cagrid.nci.nih.gov/1/GridGrouper}MemberDescriptor"/>
 *         &lt;element name="Group" type="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupDescriptor"/>
 *         &lt;element name="ViaGroup" type="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupDescriptor" minOccurs="0"/>
 *         &lt;element name="Depth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MembershipDescriptor", propOrder = {
    "member",
    "group",
    "viaGroup",
    "depth"
})
public class MembershipDescriptor
    implements Serializable
{

    @XmlElement(name = "Member", required = true)
    protected MemberDescriptor member;
    @XmlElement(name = "Group", required = true)
    protected GroupDescriptor group;
    @XmlElement(name = "ViaGroup")
    protected GroupDescriptor viaGroup;
    @XmlElement(name = "Depth")
    protected int depth;

    /**
     * Gets the value of the member property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.model.MemberDescriptor }
     *     
     */
    public MemberDescriptor getMember() {
        return member;
    }

    /**
     * Sets the value of the member property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.model.MemberDescriptor }
     *     
     */
    public void setMember(MemberDescriptor value) {
        this.member = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.model.GroupDescriptor }
     *     
     */
    public GroupDescriptor getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.model.GroupDescriptor }
     *     
     */
    public void setGroup(GroupDescriptor value) {
        this.group = value;
    }

    /**
     * Gets the value of the viaGroup property.
     * 
     * @return
     *     possible object is
     *     {@link org.cagrid.gridgrouper.model.GroupDescriptor }
     *     
     */
    public GroupDescriptor getViaGroup() {
        return viaGroup;
    }

    /**
     * Sets the value of the viaGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.cagrid.gridgrouper.model.GroupDescriptor }
     *     
     */
    public void setViaGroup(GroupDescriptor value) {
        this.viaGroup = value;
    }

    /**
     * Gets the value of the depth property.
     * 
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     * 
     */
    public void setDepth(int value) {
        this.depth = value;
    }

}
