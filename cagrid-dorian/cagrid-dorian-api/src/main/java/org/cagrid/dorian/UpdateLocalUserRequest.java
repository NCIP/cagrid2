
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.idp.LocalUser;


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
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}LocalUser"/>
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
@XmlRootElement(name = "UpdateLocalUserRequest")
public class UpdateLocalUserRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected UpdateLocalUserRequest.User user;

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateLocalUserRequest.User }
     *     
     */
    public UpdateLocalUserRequest.User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateLocalUserRequest.User }
     *     
     */
    public void setUser(UpdateLocalUserRequest.User value) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}LocalUser"/>
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
        "localUser"
    })
    public static class User
        implements Serializable
    {

        @XmlElement(name = "LocalUser", namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", required = true)
        protected LocalUser localUser;

        /**
         * Gets the value of the localUser property.
         * 
         * @return
         *     possible object is
         *     {@link LocalUser }
         *     
         */
        public LocalUser getLocalUser() {
            return localUser;
        }

        /**
         * Sets the value of the localUser property.
         * 
         * @param value
         *     allowed object is
         *     {@link LocalUser }
         *     
         */
        public void setLocalUser(LocalUser value) {
            this.localUser = value;
        }

    }

}
