
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
 *         &lt;element name="authorityGTS">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}AuthorityGTS"/>
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
    "authorityGTS"
})
@XmlRootElement(name = "AddAuthorityRequest")
public class AddAuthorityRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected AddAuthorityRequest.AuthorityGTS authorityGTS;

    /**
     * Gets the value of the authorityGTS property.
     * 
     * @return
     *     possible object is
     *     {@link AddAuthorityRequest.AuthorityGTS }
     *     
     */
    public AddAuthorityRequest.AuthorityGTS getAuthorityGTS() {
        return authorityGTS;
    }

    /**
     * Sets the value of the authorityGTS property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddAuthorityRequest.AuthorityGTS }
     *     
     */
    public void setAuthorityGTS(AddAuthorityRequest.AuthorityGTS value) {
        this.authorityGTS = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}AuthorityGTS"/>
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
        "authorityGTS"
    })
    public static class AuthorityGTS
        implements Serializable
    {

        @XmlElement(name = "AuthorityGTS", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
        protected org.cagrid.gts.model.AuthorityGTS authorityGTS;

        /**
         * Gets the value of the authorityGTS property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.gts.model.AuthorityGTS }
         *     
         */
        public org.cagrid.gts.model.AuthorityGTS getAuthorityGTS() {
            return authorityGTS;
        }

        /**
         * Sets the value of the authorityGTS property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.gts.model.AuthorityGTS }
         *     
         */
        public void setAuthorityGTS(org.cagrid.gts.model.AuthorityGTS value) {
            this.authorityGTS = value;
        }

    }

}
