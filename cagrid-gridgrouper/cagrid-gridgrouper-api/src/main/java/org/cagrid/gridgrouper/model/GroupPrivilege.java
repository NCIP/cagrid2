
package org.cagrid.gridgrouper.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GroupPrivilege complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GroupPrivilege">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GroupName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PrivilegeType" type="{http://cagrid.nci.nih.gov/1/GridGrouper}GroupPrivilegeType"/>
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ImplementationClass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsRevokable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Owner" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroupPrivilege", propOrder = {
    "groupName",
    "privilegeType",
    "subject",
    "implementationClass",
    "isRevokable",
    "owner"
})
public class GroupPrivilege
    implements Serializable
{

    @XmlElement(name = "GroupName", required = true)
    protected String groupName;
    @XmlElement(name = "PrivilegeType", required = true)
    protected GroupPrivilegeType privilegeType;
    @XmlElement(name = "Subject", required = true)
    protected String subject;
    @XmlElement(name = "ImplementationClass", required = true)
    protected String implementationClass;
    @XmlElement(name = "IsRevokable")
    protected boolean isRevokable;
    @XmlElement(name = "Owner", required = true)
    protected String owner;

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }

    /**
     * Gets the value of the privilegeType property.
     * 
     * @return
     *     possible object is
     *     {@link GroupPrivilegeType }
     *     
     */
    public GroupPrivilegeType getPrivilegeType() {
        return privilegeType;
    }

    /**
     * Sets the value of the privilegeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupPrivilegeType }
     *     
     */
    public void setPrivilegeType(GroupPrivilegeType value) {
        this.privilegeType = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the implementationClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImplementationClass() {
        return implementationClass;
    }

    /**
     * Sets the value of the implementationClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImplementationClass(String value) {
        this.implementationClass = value;
    }

    /**
     * Gets the value of the isRevokable property.
     * 
     */
    public boolean isIsRevokable() {
        return isRevokable;
    }

    /**
     * Sets the value of the isRevokable property.
     * 
     */
    public void setIsRevokable(boolean value) {
        this.isRevokable = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

}
