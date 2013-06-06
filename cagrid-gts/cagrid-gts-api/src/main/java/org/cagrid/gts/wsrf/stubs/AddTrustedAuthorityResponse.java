
package org.cagrid.gts.wsrf.stubs;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.gts.model.TrustedAuthority;
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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/8/gts}TrustedAuthority"/>
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
    "trustedAuthority"
})
@XmlRootElement(name = "AddTrustedAuthorityResponse")
public class AddTrustedAuthorityResponse
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "TrustedAuthority", namespace = "http://cagrid.nci.nih.gov/8/gts", required = true)
    protected TrustedAuthority trustedAuthority;

    /**
     * Gets the value of the trustedAuthority property.
     * 
     * @return
     *     possible object is
     *     {@link TrustedAuthority }
     *     
     */
    public TrustedAuthority getTrustedAuthority() {
        return trustedAuthority;
    }

    /**
     * Sets the value of the trustedAuthority property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrustedAuthority }
     *     
     */
    public void setTrustedAuthority(TrustedAuthority value) {
        this.trustedAuthority = value;
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
            TrustedAuthority theTrustedAuthority;
            theTrustedAuthority = this.getTrustedAuthority();
            strategy.appendField(locator, this, "trustedAuthority", buffer, theTrustedAuthority);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            TrustedAuthority theTrustedAuthority;
            theTrustedAuthority = this.getTrustedAuthority();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trustedAuthority", theTrustedAuthority), currentHashCode, theTrustedAuthority);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AddTrustedAuthorityResponse)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AddTrustedAuthorityResponse that = ((AddTrustedAuthorityResponse) object);
        {
            TrustedAuthority lhsTrustedAuthority;
            lhsTrustedAuthority = this.getTrustedAuthority();
            TrustedAuthority rhsTrustedAuthority;
            rhsTrustedAuthority = that.getTrustedAuthority();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "trustedAuthority", lhsTrustedAuthority), LocatorUtils.property(thatLocator, "trustedAuthority", rhsTrustedAuthority), lhsTrustedAuthority, rhsTrustedAuthority)) {
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
