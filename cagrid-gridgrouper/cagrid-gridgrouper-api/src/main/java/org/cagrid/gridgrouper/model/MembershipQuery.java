
package org.cagrid.gridgrouper.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MembershipQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MembershipQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GroupIdentifier" type="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupIdentifier"/>
 *       &lt;/sequence>
 *       &lt;attribute name="MembershipStatus" type="{http://cagrid.nci.nih.gov/1/GridGrouper}MembershipStatus" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MembershipQuery", propOrder = {
    "groupIdentifier"
})
public class MembershipQuery implements Serializable
{

    @XmlElement(name = "GroupIdentifier", required = true)
    protected GroupIdentifier groupIdentifier;
    @XmlAttribute(name = "MembershipStatus", namespace = "http://cagrid.nci.nih.gov/1/GridGrouper")
    protected MembershipStatus membershipStatus;

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

    /**
     * Gets the value of the membershipStatus property.
     * 
     * @return
     *     possible object is
     *     {@link MembershipStatus }
     *     
     */
    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }

    /**
     * Sets the value of the membershipStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link MembershipStatus }
     *     
     */
    public void setMembershipStatus(MembershipStatus value) {
        this.membershipStatus = value;
    }

}
