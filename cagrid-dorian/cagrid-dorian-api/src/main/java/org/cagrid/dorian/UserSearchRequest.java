
package org.cagrid.dorian;

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
 *         &lt;element name="gridUserSearchCriteria">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}GridUserSearchCriteria"/>
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
    "gridUserSearchCriteria"
})
@XmlRootElement(name = "UserSearchRequest")
public class UserSearchRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected UserSearchRequest.GridUserSearchCriteria gridUserSearchCriteria;

    /**
     * Gets the value of the gridUserSearchCriteria property.
     * 
     * @return
     *     possible object is
     *     {@link UserSearchRequest.GridUserSearchCriteria }
     *     
     */
    public UserSearchRequest.GridUserSearchCriteria getGridUserSearchCriteria() {
        return gridUserSearchCriteria;
    }

    /**
     * Sets the value of the gridUserSearchCriteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserSearchRequest.GridUserSearchCriteria }
     *     
     */
    public void setGridUserSearchCriteria(UserSearchRequest.GridUserSearchCriteria value) {
        this.gridUserSearchCriteria = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}GridUserSearchCriteria"/>
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
        "gridUserSearchCriteria"
    })
    public static class GridUserSearchCriteria
        implements Serializable
    {

        @XmlElement(name = "GridUserSearchCriteria", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.ifs.GridUserSearchCriteria gridUserSearchCriteria;

        /**
         * Gets the value of the gridUserSearchCriteria property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.ifs.GridUserSearchCriteria }
         *     
         */
        public org.cagrid.dorian.ifs.GridUserSearchCriteria getGridUserSearchCriteria() {
            return gridUserSearchCriteria;
        }

        /**
         * Sets the value of the gridUserSearchCriteria property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.ifs.GridUserSearchCriteria }
         *     
         */
        public void setGridUserSearchCriteria(org.cagrid.dorian.ifs.GridUserSearchCriteria value) {
            this.gridUserSearchCriteria = value;
        }

    }

}
