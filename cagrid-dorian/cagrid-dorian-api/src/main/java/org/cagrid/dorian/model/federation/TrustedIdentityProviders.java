
package org.cagrid.dorian.model.federation;

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
 * <p>Java class for TrustedIdentityProviders complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrustedIdentityProviders">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrustedIdentityProvider" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}TrustedIdentityProvider" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrustedIdentityProviders", propOrder = {
    "trustedIdentityProvider"
})
public class TrustedIdentityProviders
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "TrustedIdentityProvider")
    protected List<TrustedIdentityProvider> trustedIdentityProvider;

    /**
     * Gets the value of the trustedIdentityProvider property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trustedIdentityProvider property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrustedIdentityProvider().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrustedIdentityProvider }
     * 
     * 
     */
    public List<TrustedIdentityProvider> getTrustedIdentityProvider() {
        if (trustedIdentityProvider == null) {
            trustedIdentityProvider = new ArrayList<TrustedIdentityProvider>();
        }
        return this.trustedIdentityProvider;
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
            List<TrustedIdentityProvider> theTrustedIdentityProvider;
            theTrustedIdentityProvider = (((this.trustedIdentityProvider!= null)&&(!this.trustedIdentityProvider.isEmpty()))?this.getTrustedIdentityProvider():null);
            strategy.appendField(locator, this, "trustedIdentityProvider", buffer, theTrustedIdentityProvider);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<TrustedIdentityProvider> theTrustedIdentityProvider;
            theTrustedIdentityProvider = (((this.trustedIdentityProvider!= null)&&(!this.trustedIdentityProvider.isEmpty()))?this.getTrustedIdentityProvider():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "trustedIdentityProvider", theTrustedIdentityProvider), currentHashCode, theTrustedIdentityProvider);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TrustedIdentityProviders)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TrustedIdentityProviders that = ((TrustedIdentityProviders) object);
        {
            List<TrustedIdentityProvider> lhsTrustedIdentityProvider;
            lhsTrustedIdentityProvider = (((this.trustedIdentityProvider!= null)&&(!this.trustedIdentityProvider.isEmpty()))?this.getTrustedIdentityProvider():null);
            List<TrustedIdentityProvider> rhsTrustedIdentityProvider;
            rhsTrustedIdentityProvider = (((that.trustedIdentityProvider!= null)&&(!that.trustedIdentityProvider.isEmpty()))?that.getTrustedIdentityProvider():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "trustedIdentityProvider", lhsTrustedIdentityProvider), LocatorUtils.property(thatLocator, "trustedIdentityProvider", rhsTrustedIdentityProvider), lhsTrustedIdentityProvider, rhsTrustedIdentityProvider)) {
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
