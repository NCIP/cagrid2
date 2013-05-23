
package org.cagrid.gts.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gts.model.PermissionFilter;


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
 *         &lt;element name="filter">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}PermissionFilter"/>
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
    "filter"
})
@XmlRootElement(name = "FindPermissionsRequest")
public class FindPermissionsRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected FindPermissionsRequest.Filter filter;

    /**
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link FindPermissionsRequest.Filter }
     *     
     */
    public FindPermissionsRequest.Filter getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindPermissionsRequest.Filter }
     *     
     */
    public void setFilter(FindPermissionsRequest.Filter value) {
        this.filter = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}PermissionFilter"/>
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
        "permissionFilter"
    })
    public static class Filter
        implements Serializable
    {

        @XmlElement(name = "PermissionFilter", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected PermissionFilter permissionFilter;

        /**
         * Gets the value of the permissionFilter property.
         * 
         * @return
         *     possible object is
         *     {@link PermissionFilter }
         *     
         */
        public PermissionFilter getPermissionFilter() {
            return permissionFilter;
        }

        /**
         * Sets the value of the permissionFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link PermissionFilter }
         *     
         */
        public void setPermissionFilter(PermissionFilter value) {
            this.permissionFilter = value;
        }

    }

}
