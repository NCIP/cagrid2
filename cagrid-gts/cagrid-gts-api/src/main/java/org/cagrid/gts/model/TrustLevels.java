
package org.cagrid.gts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 * <p>Java class for TrustLevels complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrustLevels">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrustLevel" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrustLevels", propOrder = {
    "trustLevel"
})
public class TrustLevels
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "TrustLevel")
    protected List<String> trustLevel;

    /**
     * Gets the value of the trustLevel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trustLevel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrustLevel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTrustLevel() {
        if (trustLevel == null) {
            trustLevel = new ArrayList<String>();
        }
        return this.trustLevel;
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
            List<String> theTrustLevel;
            theTrustLevel = (((this.trustLevel!= null)&&(!this.trustLevel.isEmpty()))?this.getTrustLevel():null);
            strategy.appendField(locator, this, "trustLevel", buffer, theTrustLevel);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<String> theTrustLevel;
            theTrustLevel = (((this.trustLevel!= null)&&(!this.trustLevel.isEmpty()))?this.getTrustLevel():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trustLevel", theTrustLevel), currentHashCode, theTrustLevel);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TrustLevels)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TrustLevels that = ((TrustLevels) object);
        {
            List<String> lhsTrustLevel;
            lhsTrustLevel = (((this.trustLevel!= null)&&(!this.trustLevel.isEmpty()))?this.getTrustLevel():null);
            List<String> rhsTrustLevel;
            rhsTrustLevel = (((that.trustLevel!= null)&&(!that.trustLevel.isEmpty()))?that.getTrustLevel():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "trustLevel", lhsTrustLevel), LocatorUtils.property(thatLocator, "trustLevel", rhsTrustLevel), lhsTrustLevel, rhsTrustLevel)) {
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
