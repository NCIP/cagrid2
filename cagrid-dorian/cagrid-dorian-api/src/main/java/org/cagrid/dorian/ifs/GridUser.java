
package org.cagrid.dorian.ifs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GridUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GridUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdPId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="UID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="gridId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userStatus" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}GridUserStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GridUser", propOrder = {
    "idPId",
    "uid",
    "gridId",
    "firstName",
    "lastName",
    "email",
    "userStatus"
})
public class GridUser
    implements Serializable
{

    @XmlElement(name = "IdPId")
    protected long idPId;
    @XmlElement(name = "UID", required = true)
    protected String uid;
    @XmlElement(required = true)
    protected String gridId;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected GridUserStatus userStatus;

    /**
     * Gets the value of the idPId property.
     * 
     */
    public long getIdPId() {
        return idPId;
    }

    /**
     * Sets the value of the idPId property.
     * 
     */
    public void setIdPId(long value) {
        this.idPId = value;
    }

    /**
     * Gets the value of the uid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUID() {
        return uid;
    }

    /**
     * Sets the value of the uid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUID(String value) {
        this.uid = value;
    }

    /**
     * Gets the value of the gridId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridId() {
        return gridId;
    }

    /**
     * Sets the value of the gridId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridId(String value) {
        this.gridId = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the userStatus property.
     * 
     * @return
     *     possible object is
     *     {@link GridUserStatus }
     *     
     */
    public GridUserStatus getUserStatus() {
        return userStatus;
    }

    /**
     * Sets the value of the userStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link GridUserStatus }
     *     
     */
    public void setUserStatus(GridUserStatus value) {
        this.userStatus = value;
    }

}
