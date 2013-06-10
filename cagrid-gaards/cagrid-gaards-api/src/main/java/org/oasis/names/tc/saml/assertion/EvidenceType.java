
package org.oasis.names.tc.saml.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
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
 * <p>Java class for EvidenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EvidenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}AssertionIDReference"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}Assertion"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EvidenceType", propOrder = {
    "assertionIDReferenceOrAssertion"
})
public class EvidenceType
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElementRefs({
        @XmlElementRef(name = "Assertion", namespace = "urn:oasis:names:tc:SAML:1.0:assertion", type = JAXBElement.class),
        @XmlElementRef(name = "AssertionIDReference", namespace = "urn:oasis:names:tc:SAML:1.0:assertion", type = JAXBElement.class)
    })
    protected List<JAXBElement<? extends Serializable>> assertionIDReferenceOrAssertion;

    /**
     * Gets the value of the assertionIDReferenceOrAssertion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assertionIDReferenceOrAssertion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssertionIDReferenceOrAssertion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link AssertionType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends Serializable>> getAssertionIDReferenceOrAssertion() {
        if (assertionIDReferenceOrAssertion == null) {
            assertionIDReferenceOrAssertion = new ArrayList<JAXBElement<? extends Serializable>>();
        }
        return this.assertionIDReferenceOrAssertion;
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
            List<JAXBElement<? extends Serializable>> theAssertionIDReferenceOrAssertion;
            theAssertionIDReferenceOrAssertion = (((this.assertionIDReferenceOrAssertion!= null)&&(!this.assertionIDReferenceOrAssertion.isEmpty()))?this.getAssertionIDReferenceOrAssertion():null);
            strategy.appendField(locator, this, "assertionIDReferenceOrAssertion", buffer, theAssertionIDReferenceOrAssertion);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<JAXBElement<? extends Serializable>> theAssertionIDReferenceOrAssertion;
            theAssertionIDReferenceOrAssertion = (((this.assertionIDReferenceOrAssertion!= null)&&(!this.assertionIDReferenceOrAssertion.isEmpty()))?this.getAssertionIDReferenceOrAssertion():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "assertionIDReferenceOrAssertion", theAssertionIDReferenceOrAssertion), currentHashCode, theAssertionIDReferenceOrAssertion);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EvidenceType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EvidenceType that = ((EvidenceType) object);
        {
            List<JAXBElement<? extends Serializable>> lhsAssertionIDReferenceOrAssertion;
            lhsAssertionIDReferenceOrAssertion = (((this.assertionIDReferenceOrAssertion!= null)&&(!this.assertionIDReferenceOrAssertion.isEmpty()))?this.getAssertionIDReferenceOrAssertion():null);
            List<JAXBElement<? extends Serializable>> rhsAssertionIDReferenceOrAssertion;
            rhsAssertionIDReferenceOrAssertion = (((that.assertionIDReferenceOrAssertion!= null)&&(!that.assertionIDReferenceOrAssertion.isEmpty()))?that.getAssertionIDReferenceOrAssertion():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "assertionIDReferenceOrAssertion", lhsAssertionIDReferenceOrAssertion), LocatorUtils.property(thatLocator, "assertionIDReferenceOrAssertion", rhsAssertionIDReferenceOrAssertion), lhsAssertionIDReferenceOrAssertion, rhsAssertionIDReferenceOrAssertion)) {
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
