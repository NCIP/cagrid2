
package org.cagrid.mms.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Used to identify a particular pattern of associations to exclude. The
 *         single
 *         character wildcard "*" is supported, otherwise an exact string match is performed.
 *       
 * 
 * <p>Java class for UMLAssociationExclude complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UMLAssociationExclude">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="sourceRoleName" type="{http://www.w3.org/2001/XMLSchema}string" default="*" />
 *       &lt;attribute name="sourceClassName" type="{http://www.w3.org/2001/XMLSchema}string" default="*" />
 *       &lt;attribute name="targetRoleName" type="{http://www.w3.org/2001/XMLSchema}string" default="*" />
 *       &lt;attribute name="targetClassName" type="{http://www.w3.org/2001/XMLSchema}string" default="*" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UMLAssociationExclude")
public class UMLAssociationExclude
    implements Serializable
{

    @XmlAttribute(name = "sourceRoleName")
    protected String sourceRoleName;
    @XmlAttribute(name = "sourceClassName")
    protected String sourceClassName;
    @XmlAttribute(name = "targetRoleName")
    protected String targetRoleName;
    @XmlAttribute(name = "targetClassName")
    protected String targetClassName;

    /**
     * Gets the value of the sourceRoleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceRoleName() {
        if (sourceRoleName == null) {
            return "*";
        } else {
            return sourceRoleName;
        }
    }

    /**
     * Sets the value of the sourceRoleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceRoleName(String value) {
        this.sourceRoleName = value;
    }

    /**
     * Gets the value of the sourceClassName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceClassName() {
        if (sourceClassName == null) {
            return "*";
        } else {
            return sourceClassName;
        }
    }

    /**
     * Sets the value of the sourceClassName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceClassName(String value) {
        this.sourceClassName = value;
    }

    /**
     * Gets the value of the targetRoleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetRoleName() {
        if (targetRoleName == null) {
            return "*";
        } else {
            return targetRoleName;
        }
    }

    /**
     * Sets the value of the targetRoleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetRoleName(String value) {
        this.targetRoleName = value;
    }

    /**
     * Gets the value of the targetClassName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetClassName() {
        if (targetClassName == null) {
            return "*";
        } else {
            return targetClassName;
        }
    }

    /**
     * Sets the value of the targetClassName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetClassName(String value) {
        this.targetClassName = value;
    }

}
