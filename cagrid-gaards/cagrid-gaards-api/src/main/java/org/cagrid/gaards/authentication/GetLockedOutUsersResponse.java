
package org.cagrid.gaards.authentication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gaards.authentication.lockout.LockedUserInfo;
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
 *         &lt;element ref="{http://gaards.cagrid.org/authentication/lockout}LockedUserInfo" maxOccurs="unbounded"/>
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
    "lockedUserInfo"
})
@XmlRootElement(name = "GetLockedOutUsersResponse")
public class GetLockedOutUsersResponse
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "LockedUserInfo", namespace = "http://gaards.cagrid.org/authentication/lockout", required = true)
    protected List<LockedUserInfo> lockedUserInfo;

    /**
     * Gets the value of the lockedUserInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lockedUserInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLockedUserInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LockedUserInfo }
     * 
     * 
     */
    public List<LockedUserInfo> getLockedUserInfo() {
        if (lockedUserInfo == null) {
            lockedUserInfo = new ArrayList<LockedUserInfo>();
        }
        return this.lockedUserInfo;
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
            List<LockedUserInfo> theLockedUserInfo;
            theLockedUserInfo = (((this.lockedUserInfo!= null)&&(!this.lockedUserInfo.isEmpty()))?this.getLockedUserInfo():null);
            strategy.appendField(locator, this, "lockedUserInfo", buffer, theLockedUserInfo);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<LockedUserInfo> theLockedUserInfo;
            theLockedUserInfo = (((this.lockedUserInfo!= null)&&(!this.lockedUserInfo.isEmpty()))?this.getLockedUserInfo():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lockedUserInfo", theLockedUserInfo), currentHashCode, theLockedUserInfo);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof GetLockedOutUsersResponse)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final GetLockedOutUsersResponse that = ((GetLockedOutUsersResponse) object);
        {
            List<LockedUserInfo> lhsLockedUserInfo;
            lhsLockedUserInfo = (((this.lockedUserInfo!= null)&&(!this.lockedUserInfo.isEmpty()))?this.getLockedUserInfo():null);
            List<LockedUserInfo> rhsLockedUserInfo;
            rhsLockedUserInfo = (((that.lockedUserInfo!= null)&&(!that.lockedUserInfo.isEmpty()))?that.getLockedUserInfo():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lockedUserInfo", lhsLockedUserInfo), LocatorUtils.property(thatLocator, "lockedUserInfo", rhsLockedUserInfo), lhsLockedUserInfo, rhsLockedUserInfo)) {
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
