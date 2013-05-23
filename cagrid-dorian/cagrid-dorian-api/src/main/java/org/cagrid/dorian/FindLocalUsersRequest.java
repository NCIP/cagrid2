
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.idp.LocalUserFilter;


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
 *         &lt;element name="f">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}LocalUserFilter"/>
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
    "f"
})
@XmlRootElement(name = "FindLocalUsersRequest")
public class FindLocalUsersRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected FindLocalUsersRequest.F f;

    /**
     * Gets the value of the f property.
     * 
     * @return
     *     possible object is
     *     {@link FindLocalUsersRequest.F }
     *     
     */
    public FindLocalUsersRequest.F getF() {
        return f;
    }

    /**
     * Sets the value of the f property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindLocalUsersRequest.F }
     *     
     */
    public void setF(FindLocalUsersRequest.F value) {
        this.f = value;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}LocalUserFilter"/>
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
        "localUserFilter"
    })
    public static class F
        implements Serializable
    {

        @XmlElement(name = "LocalUserFilter", namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", required = true)
        protected LocalUserFilter localUserFilter;

        /**
         * Gets the value of the localUserFilter property.
         * 
         * @return
         *     possible object is
         *     {@link LocalUserFilter }
         *     
         */
        public LocalUserFilter getLocalUserFilter() {
            return localUserFilter;
        }

        /**
         * Sets the value of the localUserFilter property.
         * 
         * @param value
         *     allowed object is
         *     {@link LocalUserFilter }
         *     
         */
        public void setLocalUserFilter(LocalUserFilter value) {
            this.localUserFilter = value;
        }

    }

}
