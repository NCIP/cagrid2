
package org.cagrid.dorian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.cagrid.dorian.model.federation.GridUser;
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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}GridUser" maxOccurs="unbounded"/>
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
    "gridUser"
})
@XmlRootElement(name = "FindGridUsersResponse")
public class FindGridUsersResponse
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "GridUser", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
    protected List<GridUser> gridUser;

    /**
     * Gets the value of the gridUser property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gridUser property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGridUser().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GridUser }
     * 
     * 
     */
    public List<GridUser> getGridUser() {
        if (gridUser == null) {
            gridUser = new ArrayList<GridUser>();
        }
        return this.gridUser;
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
            List<GridUser> theGridUser;
            theGridUser = (((this.gridUser!= null)&&(!this.gridUser.isEmpty()))?this.getGridUser():null);
            strategy.appendField(locator, this, "gridUser", buffer, theGridUser);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<GridUser> theGridUser;
            theGridUser = (((this.gridUser!= null)&&(!this.gridUser.isEmpty()))?this.getGridUser():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "gridUser", theGridUser), currentHashCode, theGridUser);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FindGridUsersResponse)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FindGridUsersResponse that = ((FindGridUsersResponse) object);
        {
            List<GridUser> lhsGridUser;
            lhsGridUser = (((this.gridUser!= null)&&(!this.gridUser.isEmpty()))?this.getGridUser():null);
            List<GridUser> rhsGridUser;
            rhsGridUser = (((that.gridUser!= null)&&(!that.gridUser.isEmpty()))?that.getGridUser():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gridUser", lhsGridUser), LocatorUtils.property(thatLocator, "gridUser", rhsGridUser), lhsGridUser, rhsGridUser)) {
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
