
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
 *         &lt;element name="permission">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}Permission"/>
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
    "permission"
})
@XmlRootElement(name = "AddPermissionRequest")
public class AddPermissionRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected AddPermissionRequest.Permission permission;

    /**
     * Gets the value of the permission property.
     * 
     * @return
     *     possible object is
     *     {@link AddPermissionRequest.Permission }
     *     
     */
    public AddPermissionRequest.Permission getPermission() {
        return permission;
    }

    /**
     * Sets the value of the permission property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddPermissionRequest.Permission }
     *     
     */
    public void setPermission(AddPermissionRequest.Permission value) {
        this.permission = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}Permission"/>
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
        "permission"
    })
    public static class Permission
        implements Serializable
    {

        @XmlElement(name = "Permission", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected org.cagrid.gts.model.Permission permission;

        /**
         * Gets the value of the permission property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gts.model.Permission }
         *     
         */
        public org.cagrid.gts.model.Permission getPermission() {
            return permission;
        }

        /**
         * Sets the value of the permission property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gts.model.Permission }
         *     
         */
        public void setPermission(org.cagrid.gts.model.Permission value) {
            this.permission = value;
        }

    }

}
