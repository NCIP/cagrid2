
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.ifs.GridUser;


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
 *         &lt;element name="user">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}GridUser"/>
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
    "user"
})
@XmlRootElement(name = "UpdateGridUserRequest")
public class UpdateGridUserRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected UpdateGridUserRequest.User user;

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateGridUserRequest.User }
     *     
     */
    public UpdateGridUserRequest.User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateGridUserRequest.User }
     *     
     */
    public void setUser(UpdateGridUserRequest.User value) {
        this.user = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}GridUser"/>
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
        "gridUser"
    })
    public static class User
        implements Serializable
    {

        @XmlElement(name = "GridUser", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected GridUser gridUser;

        /**
         * Gets the value of the gridUser property.
         * 
         * @return
         *     possible object is
         *     {@link GridUser }
         *     
         */
        public GridUser getGridUser() {
            return gridUser;
        }

        /**
         * Sets the value of the gridUser property.
         * 
         * @param value
         *     allowed object is
         *     {@link GridUser }
         *     
         */
        public void setGridUser(GridUser value) {
            this.gridUser = value;
        }

    }

}
