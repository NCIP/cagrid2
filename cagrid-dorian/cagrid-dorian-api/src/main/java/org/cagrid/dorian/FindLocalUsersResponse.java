
package org.cagrid.dorian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.cagrid.dorian.model.idp.LocalUser;
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
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}LocalUser" maxOccurs="unbounded"/>
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
@XmlRootElement(name = "FindLocalUsersResponse")
public class FindLocalUsersResponse
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "LocalUser", namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", required = true)
    protected List<LocalUser> localUser;

    /**
     * Gets the value of the localUser property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the localUser property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocalUser().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalUser }
     * 
     * 
     */
    public List<LocalUser> getLocalUser() {
        if (localUser == null) {
            localUser = new ArrayList<LocalUser>();
        }
        return this.localUser;
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
            List<LocalUser> theLocalUser;
            theLocalUser = (((this.localUser!= null)&&(!this.localUser.isEmpty()))?this.getLocalUser():null);
            strategy.appendField(locator, this, "localUser", buffer, theLocalUser);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<LocalUser> theLocalUser;
            theLocalUser = (((this.localUser!= null)&&(!this.localUser.isEmpty()))?this.getLocalUser():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "localUser", theLocalUser), currentHashCode, theLocalUser);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FindLocalUsersResponse)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FindLocalUsersResponse that = ((FindLocalUsersResponse) object);
        {
            List<LocalUser> lhsLocalUser;
            lhsLocalUser = (((this.localUser!= null)&&(!this.localUser.isEmpty()))?this.getLocalUser():null);
            List<LocalUser> rhsLocalUser;
            rhsLocalUser = (((that.localUser!= null)&&(!that.localUser.isEmpty()))?that.getLocalUser():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "localUser", lhsLocalUser), LocatorUtils.property(thatLocator, "localUser", rhsLocalUser), lhsLocalUser, rhsLocalUser)) {
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
