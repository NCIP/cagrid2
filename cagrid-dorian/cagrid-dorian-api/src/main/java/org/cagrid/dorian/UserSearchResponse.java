
package org.cagrid.dorian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.ifs.GridUserRecord;
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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}GridUserRecord" maxOccurs="unbounded"/>
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
    "gridUserRecord"
})
@XmlRootElement(name = "UserSearchResponse")
public class UserSearchResponse
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "GridUserRecord", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
    protected List<GridUserRecord> gridUserRecord;

    /**
     * Gets the value of the gridUserRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gridUserRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGridUserRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GridUserRecord }
     * 
     * 
     */
    public List<GridUserRecord> getGridUserRecord() {
        if (gridUserRecord == null) {
            gridUserRecord = new ArrayList<GridUserRecord>();
        }
        return this.gridUserRecord;
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
            List<GridUserRecord> theGridUserRecord;
            theGridUserRecord = (((this.gridUserRecord!= null)&&(!this.gridUserRecord.isEmpty()))?this.getGridUserRecord():null);
            strategy.appendField(locator, this, "gridUserRecord", buffer, theGridUserRecord);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<GridUserRecord> theGridUserRecord;
            theGridUserRecord = (((this.gridUserRecord!= null)&&(!this.gridUserRecord.isEmpty()))?this.getGridUserRecord():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "gridUserRecord", theGridUserRecord), currentHashCode, theGridUserRecord);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof UserSearchResponse)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final UserSearchResponse that = ((UserSearchResponse) object);
        {
            List<GridUserRecord> lhsGridUserRecord;
            lhsGridUserRecord = (((this.gridUserRecord!= null)&&(!this.gridUserRecord.isEmpty()))?this.getGridUserRecord():null);
            List<GridUserRecord> rhsGridUserRecord;
            rhsGridUserRecord = (((that.gridUserRecord!= null)&&(!that.gridUserRecord.isEmpty()))?that.getGridUserRecord():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gridUserRecord", lhsGridUserRecord), LocatorUtils.property(thatLocator, "gridUserRecord", rhsGridUserRecord), lhsGridUserRecord, rhsGridUserRecord)) {
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
