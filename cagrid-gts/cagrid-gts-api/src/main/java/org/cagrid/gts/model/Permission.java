
package org.cagrid.gts.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Permission complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Permission">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GridIdentity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Role" type="{http://cagrid.nci.nih.gov/8/gts}Role"/>
 *         &lt;element name="TrustedAuthorityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Permission", propOrder = {
    "gridIdentity",
    "role",
    "trustedAuthorityName"
})
public class Permission
    implements Serializable
{

    @XmlElement(name = "GridIdentity", required = true)
    protected String gridIdentity;
    @XmlElement(name = "Role", required = true)
    protected Role role;
    @XmlElement(name = "TrustedAuthorityName")
    protected String trustedAuthorityName;

    /**
     * Gets the value of the gridIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridIdentity() {
        return gridIdentity;
    }

    /**
     * Sets the value of the gridIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridIdentity(String value) {
        this.gridIdentity = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link Role }
     *     
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link Role }
     *     
     */
    public void setRole(Role value) {
        this.role = value;
    }

    /**
     * Gets the value of the trustedAuthorityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustedAuthorityName() {
        return trustedAuthorityName;
    }

    /**
     * Sets the value of the trustedAuthorityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustedAuthorityName(String value) {
        this.trustedAuthorityName = value;
    }

}
