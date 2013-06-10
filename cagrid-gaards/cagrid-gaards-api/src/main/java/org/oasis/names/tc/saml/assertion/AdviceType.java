
package org.oasis.names.tc.saml.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
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
import org.w3c.dom.Element;


/**
 * <p>Java class for AdviceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdviceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}AssertionIDReference"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}Assertion"/>
 *         &lt;any processContents='lax' namespace='##other'/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdviceType", propOrder = {
    "assertionIDReferenceOrAssertionOrAny"
})
public class AdviceType
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElementRefs({
        @XmlElementRef(name = "Assertion", namespace = "urn:oasis:names:tc:SAML:1.0:assertion", type = JAXBElement.class),
        @XmlElementRef(name = "AssertionIDReference", namespace = "urn:oasis:names:tc:SAML:1.0:assertion", type = JAXBElement.class)
    })
    @XmlAnyElement(lax = true)
    protected List<Object> assertionIDReferenceOrAssertionOrAny;

    /**
     * Gets the value of the assertionIDReferenceOrAssertionOrAny property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assertionIDReferenceOrAssertionOrAny property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssertionIDReferenceOrAssertionOrAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link Object }
     * {@link JAXBElement }{@code <}{@link AssertionType }{@code >}
     * {@link Element }
     * 
     * 
     */
    public List<Object> getAssertionIDReferenceOrAssertionOrAny() {
        if (assertionIDReferenceOrAssertionOrAny == null) {
            assertionIDReferenceOrAssertionOrAny = new ArrayList<Object>();
        }
        return this.assertionIDReferenceOrAssertionOrAny;
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
            List<Object> theAssertionIDReferenceOrAssertionOrAny;
            theAssertionIDReferenceOrAssertionOrAny = (((this.assertionIDReferenceOrAssertionOrAny!= null)&&(!this.assertionIDReferenceOrAssertionOrAny.isEmpty()))?this.getAssertionIDReferenceOrAssertionOrAny():null);
            strategy.appendField(locator, this, "assertionIDReferenceOrAssertionOrAny", buffer, theAssertionIDReferenceOrAssertionOrAny);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<Object> theAssertionIDReferenceOrAssertionOrAny;
            theAssertionIDReferenceOrAssertionOrAny = (((this.assertionIDReferenceOrAssertionOrAny!= null)&&(!this.assertionIDReferenceOrAssertionOrAny.isEmpty()))?this.getAssertionIDReferenceOrAssertionOrAny():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "assertionIDReferenceOrAssertionOrAny", theAssertionIDReferenceOrAssertionOrAny), currentHashCode, theAssertionIDReferenceOrAssertionOrAny);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AdviceType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AdviceType that = ((AdviceType) object);
        {
            List<Object> lhsAssertionIDReferenceOrAssertionOrAny;
            lhsAssertionIDReferenceOrAssertionOrAny = (((this.assertionIDReferenceOrAssertionOrAny!= null)&&(!this.assertionIDReferenceOrAssertionOrAny.isEmpty()))?this.getAssertionIDReferenceOrAssertionOrAny():null);
            List<Object> rhsAssertionIDReferenceOrAssertionOrAny;
            rhsAssertionIDReferenceOrAssertionOrAny = (((that.assertionIDReferenceOrAssertionOrAny!= null)&&(!that.assertionIDReferenceOrAssertionOrAny.isEmpty()))?that.getAssertionIDReferenceOrAssertionOrAny():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "assertionIDReferenceOrAssertionOrAny", lhsAssertionIDReferenceOrAssertionOrAny), LocatorUtils.property(thatLocator, "assertionIDReferenceOrAssertionOrAny", rhsAssertionIDReferenceOrAssertionOrAny), lhsAssertionIDReferenceOrAssertionOrAny, rhsAssertionIDReferenceOrAssertionOrAny)) {
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
