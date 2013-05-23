
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.ifs.UserCertificateUpdate;


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
 *         &lt;element name="update">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}UserCertificateUpdate"/>
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
    "update"
})
@XmlRootElement(name = "UpdateUserCertificateRequest")
public class UpdateUserCertificateRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected UpdateUserCertificateRequest.Update update;

    /**
     * Gets the value of the update property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateUserCertificateRequest.Update }
     *     
     */
    public UpdateUserCertificateRequest.Update getUpdate() {
        return update;
    }

    /**
     * Sets the value of the update property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateUserCertificateRequest.Update }
     *     
     */
    public void setUpdate(UpdateUserCertificateRequest.Update value) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}UserCertificateUpdate"/>
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
        "userCertificateUpdate"
    })
    public static class Update
        implements Serializable
    {

        @XmlElement(name = "UserCertificateUpdate", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected UserCertificateUpdate userCertificateUpdate;

        /**
         * Gets the value of the userCertificateUpdate property.
         * 
         * @return
         *     possible object is
         *     {@link UserCertificateUpdate }
         *     
         */
        public UserCertificateUpdate getUserCertificateUpdate() {
            return userCertificateUpdate;
        }

        /**
         * Sets the value of the userCertificateUpdate property.
         * 
         * @param value
         *     allowed object is
         *     {@link UserCertificateUpdate }
         *     
         */
        public void setUserCertificateUpdate(UserCertificateUpdate value) {
            this.userCertificateUpdate = value;
        }

    }

}
