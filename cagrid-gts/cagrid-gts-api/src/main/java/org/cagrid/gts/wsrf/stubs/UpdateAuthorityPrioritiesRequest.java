
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
 *         &lt;element name="authorityPriorityUpdate">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}AuthorityPriorityUpdate"/>
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
    "authorityPriorityUpdate"
})
@XmlRootElement(name = "UpdateAuthorityPrioritiesRequest")
public class UpdateAuthorityPrioritiesRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate authorityPriorityUpdate;

    /**
     * Gets the value of the authorityPriorityUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate }
     *     
     */
    public UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate getAuthorityPriorityUpdate() {
        return authorityPriorityUpdate;
    }

    /**
     * Sets the value of the authorityPriorityUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate }
     *     
     */
    public void setAuthorityPriorityUpdate(UpdateAuthorityPrioritiesRequest.AuthorityPriorityUpdate value) {
        this.authorityPriorityUpdate = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}AuthorityPriorityUpdate"/>
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
        "authorityPriorityUpdate"
    })
    public static class AuthorityPriorityUpdate
        implements Serializable
    {

        @XmlElement(name = "AuthorityPriorityUpdate", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected org.cagrid.gts.model.AuthorityPriorityUpdate authorityPriorityUpdate;

        /**
         * Gets the value of the authorityPriorityUpdate property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gts.model.AuthorityPriorityUpdate }
         *     
         */
        public org.cagrid.gts.model.AuthorityPriorityUpdate getAuthorityPriorityUpdate() {
            return authorityPriorityUpdate;
        }

        /**
         * Sets the value of the authorityPriorityUpdate property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gts.model.AuthorityPriorityUpdate }
         *     
         */
        public void setAuthorityPriorityUpdate(org.cagrid.gts.model.AuthorityPriorityUpdate value) {
            this.authorityPriorityUpdate = value;
        }

    }

}
