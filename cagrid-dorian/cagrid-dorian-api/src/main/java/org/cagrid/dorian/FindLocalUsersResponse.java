
package org.cagrid.dorian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.idp.LocalUser;


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
    implements Serializable
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

}
