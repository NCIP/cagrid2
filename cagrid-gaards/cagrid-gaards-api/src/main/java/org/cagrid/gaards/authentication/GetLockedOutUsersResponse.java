
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
    implements Serializable
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

}
