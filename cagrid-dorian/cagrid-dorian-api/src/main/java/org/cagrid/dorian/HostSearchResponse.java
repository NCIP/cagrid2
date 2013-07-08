
package org.cagrid.dorian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.cagrid.dorian.model.federation.HostRecord;
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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostRecord" maxOccurs="unbounded"/>
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
    "hostRecord"
})
@XmlRootElement(name = "HostSearchResponse")
public class HostSearchResponse
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "HostRecord", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
    protected List<HostRecord> hostRecord;

    /**
     * Gets the value of the hostRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hostRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHostRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HostRecord }
     * 
     * 
     */
    public List<HostRecord> getHostRecord() {
        if (hostRecord == null) {
            hostRecord = new ArrayList<HostRecord>();
        }
        return this.hostRecord;
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
            List<HostRecord> theHostRecord;
            theHostRecord = (((this.hostRecord!= null)&&(!this.hostRecord.isEmpty()))?this.getHostRecord():null);
            strategy.appendField(locator, this, "hostRecord", buffer, theHostRecord);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<HostRecord> theHostRecord;
            theHostRecord = (((this.hostRecord!= null)&&(!this.hostRecord.isEmpty()))?this.getHostRecord():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostRecord", theHostRecord), currentHashCode, theHostRecord);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof HostSearchResponse)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final HostSearchResponse that = ((HostSearchResponse) object);
        {
            List<HostRecord> lhsHostRecord;
            lhsHostRecord = (((this.hostRecord!= null)&&(!this.hostRecord.isEmpty()))?this.getHostRecord():null);
            List<HostRecord> rhsHostRecord;
            rhsHostRecord = (((that.hostRecord!= null)&&(!that.hostRecord.isEmpty()))?that.getHostRecord():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hostRecord", lhsHostRecord), LocatorUtils.property(thatLocator, "hostRecord", rhsHostRecord), lhsHostRecord, rhsHostRecord)) {
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
