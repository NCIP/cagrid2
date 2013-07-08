
package org.cagrid.dorian.model.federation;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for GridUserFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GridUserFilter">
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
@XmlType(name = "GridUserFilter", propOrder = {
    "idPId",
    "uid",
    "gridId",
    "firstName",
    "lastName",
    "email",
    "userStatus"
})
public class GridUserFilter
    implements Serializable, Equals, HashCode, ToString
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

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            long theIdPId;
            theIdPId = (true?this.getIdPId(): 0L);
            strategy.appendField(locator, this, "idPId", buffer, theIdPId);
        }
        {
            String theUID;
            theUID = this.getUID();
            strategy.appendField(locator, this, "uid", buffer, theUID);
        }
        {
            String theGridId;
            theGridId = this.getGridId();
            strategy.appendField(locator, this, "gridId", buffer, theGridId);
        }
        {
            String theFirstName;
            theFirstName = this.getFirstName();
            strategy.appendField(locator, this, "firstName", buffer, theFirstName);
        }
        {
            String theLastName;
            theLastName = this.getLastName();
            strategy.appendField(locator, this, "lastName", buffer, theLastName);
        }
        {
            String theEmail;
            theEmail = this.getEmail();
            strategy.appendField(locator, this, "email", buffer, theEmail);
        }
        {
            GridUserStatus theUserStatus;
            theUserStatus = this.getUserStatus();
            strategy.appendField(locator, this, "userStatus", buffer, theUserStatus);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            long theIdPId;
            theIdPId = (true?this.getIdPId(): 0L);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "idPId", theIdPId), currentHashCode, theIdPId);
        }
        {
            String theUID;
            theUID = this.getUID();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "uid", theUID), currentHashCode, theUID);
        }
        {
            String theGridId;
            theGridId = this.getGridId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "gridId", theGridId), currentHashCode, theGridId);
        }
        {
            String theFirstName;
            theFirstName = this.getFirstName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "firstName", theFirstName), currentHashCode, theFirstName);
        }
        {
            String theLastName;
            theLastName = this.getLastName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastName", theLastName), currentHashCode, theLastName);
        }
        {
            String theEmail;
            theEmail = this.getEmail();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "email", theEmail), currentHashCode, theEmail);
        }
        {
            GridUserStatus theUserStatus;
            theUserStatus = this.getUserStatus();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "userStatus", theUserStatus), currentHashCode, theUserStatus);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof GridUserFilter)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final GridUserFilter that = ((GridUserFilter) object);
        {
            long lhsIdPId;
            lhsIdPId = (true?this.getIdPId(): 0L);
            long rhsIdPId;
            rhsIdPId = (true?that.getIdPId(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "idPId", lhsIdPId), LocatorUtils.property(thatLocator, "idPId", rhsIdPId), lhsIdPId, rhsIdPId)) {
                return false;
            }
        }
        {
            String lhsUID;
            lhsUID = this.getUID();
            String rhsUID;
            rhsUID = that.getUID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "uid", lhsUID), LocatorUtils.property(thatLocator, "uid", rhsUID), lhsUID, rhsUID)) {
                return false;
            }
        }
        {
            String lhsGridId;
            lhsGridId = this.getGridId();
            String rhsGridId;
            rhsGridId = that.getGridId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gridId", lhsGridId), LocatorUtils.property(thatLocator, "gridId", rhsGridId), lhsGridId, rhsGridId)) {
                return false;
            }
        }
        {
            String lhsFirstName;
            lhsFirstName = this.getFirstName();
            String rhsFirstName;
            rhsFirstName = that.getFirstName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "firstName", lhsFirstName), LocatorUtils.property(thatLocator, "firstName", rhsFirstName), lhsFirstName, rhsFirstName)) {
                return false;
            }
        }
        {
            String lhsLastName;
            lhsLastName = this.getLastName();
            String rhsLastName;
            rhsLastName = that.getLastName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lastName", lhsLastName), LocatorUtils.property(thatLocator, "lastName", rhsLastName), lhsLastName, rhsLastName)) {
                return false;
            }
        }
        {
            String lhsEmail;
            lhsEmail = this.getEmail();
            String rhsEmail;
            rhsEmail = that.getEmail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "email", lhsEmail), LocatorUtils.property(thatLocator, "email", rhsEmail), lhsEmail, rhsEmail)) {
                return false;
            }
        }
        {
            GridUserStatus lhsUserStatus;
            lhsUserStatus = this.getUserStatus();
            GridUserStatus rhsUserStatus;
            rhsUserStatus = that.getUserStatus();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "userStatus", lhsUserStatus), LocatorUtils.property(thatLocator, "userStatus", rhsUserStatus), lhsUserStatus, rhsUserStatus)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

}
