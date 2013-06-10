
package org.cagrid.dorian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.idp.IdentityProviderAuditRecord;
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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-idp}IdentityProviderAuditRecord" maxOccurs="unbounded"/>
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
    "identityProviderAuditRecord"
})
@XmlRootElement(name = "PerformIdentityProviderAuditResponse")
public class PerformIdentityProviderAuditResponse
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "IdentityProviderAuditRecord", namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", required = true)
    protected List<IdentityProviderAuditRecord> identityProviderAuditRecord;

    /**
     * Gets the value of the identityProviderAuditRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identityProviderAuditRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentityProviderAuditRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentityProviderAuditRecord }
     * 
     * 
     */
    public List<IdentityProviderAuditRecord> getIdentityProviderAuditRecord() {
        if (identityProviderAuditRecord == null) {
            identityProviderAuditRecord = new ArrayList<IdentityProviderAuditRecord>();
        }
        return this.identityProviderAuditRecord;
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
            List<IdentityProviderAuditRecord> theIdentityProviderAuditRecord;
            theIdentityProviderAuditRecord = (((this.identityProviderAuditRecord!= null)&&(!this.identityProviderAuditRecord.isEmpty()))?this.getIdentityProviderAuditRecord():null);
            strategy.appendField(locator, this, "identityProviderAuditRecord", buffer, theIdentityProviderAuditRecord);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<IdentityProviderAuditRecord> theIdentityProviderAuditRecord;
            theIdentityProviderAuditRecord = (((this.identityProviderAuditRecord!= null)&&(!this.identityProviderAuditRecord.isEmpty()))?this.getIdentityProviderAuditRecord():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "identityProviderAuditRecord", theIdentityProviderAuditRecord), currentHashCode, theIdentityProviderAuditRecord);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PerformIdentityProviderAuditResponse)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PerformIdentityProviderAuditResponse that = ((PerformIdentityProviderAuditResponse) object);
        {
            List<IdentityProviderAuditRecord> lhsIdentityProviderAuditRecord;
            lhsIdentityProviderAuditRecord = (((this.identityProviderAuditRecord!= null)&&(!this.identityProviderAuditRecord.isEmpty()))?this.getIdentityProviderAuditRecord():null);
            List<IdentityProviderAuditRecord> rhsIdentityProviderAuditRecord;
            rhsIdentityProviderAuditRecord = (((that.identityProviderAuditRecord!= null)&&(!that.identityProviderAuditRecord.isEmpty()))?that.getIdentityProviderAuditRecord():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "identityProviderAuditRecord", lhsIdentityProviderAuditRecord), LocatorUtils.property(thatLocator, "identityProviderAuditRecord", rhsIdentityProviderAuditRecord), lhsIdentityProviderAuditRecord, rhsIdentityProviderAuditRecord)) {
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
