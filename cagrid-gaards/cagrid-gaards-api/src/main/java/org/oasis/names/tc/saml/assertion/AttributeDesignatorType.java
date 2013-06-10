
package org.oasis.names.tc.saml.assertion;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Java class for AttributeDesignatorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AttributeDesignatorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="AttributeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AttributeNamespace" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttributeDesignatorType")
@XmlSeeAlso({
    AttributeType.class
})
public class AttributeDesignatorType
    implements Serializable, Equals, HashCode, ToString
{

    @XmlAttribute(name = "AttributeName", required = true)
    protected String attributeName;
    @XmlAttribute(name = "AttributeNamespace", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String attributeNamespace;

    /**
     * Gets the value of the attributeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Sets the value of the attributeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeName(String value) {
        this.attributeName = value;
    }

    /**
     * Gets the value of the attributeNamespace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeNamespace() {
        return attributeNamespace;
    }

    /**
     * Sets the value of the attributeNamespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeNamespace(String value) {
        this.attributeNamespace = value;
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
            String theAttributeName;
            theAttributeName = this.getAttributeName();
            strategy.appendField(locator, this, "attributeName", buffer, theAttributeName);
        }
        {
            String theAttributeNamespace;
            theAttributeNamespace = this.getAttributeNamespace();
            strategy.appendField(locator, this, "attributeNamespace", buffer, theAttributeNamespace);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theAttributeName;
            theAttributeName = this.getAttributeName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "attributeName", theAttributeName), currentHashCode, theAttributeName);
        }
        {
            String theAttributeNamespace;
            theAttributeNamespace = this.getAttributeNamespace();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "attributeNamespace", theAttributeNamespace), currentHashCode, theAttributeNamespace);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AttributeDesignatorType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AttributeDesignatorType that = ((AttributeDesignatorType) object);
        {
            String lhsAttributeName;
            lhsAttributeName = this.getAttributeName();
            String rhsAttributeName;
            rhsAttributeName = that.getAttributeName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "attributeName", lhsAttributeName), LocatorUtils.property(thatLocator, "attributeName", rhsAttributeName), lhsAttributeName, rhsAttributeName)) {
                return false;
            }
        }
        {
            String lhsAttributeNamespace;
            lhsAttributeNamespace = this.getAttributeNamespace();
            String rhsAttributeNamespace;
            rhsAttributeNamespace = that.getAttributeNamespace();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "attributeNamespace", lhsAttributeNamespace), LocatorUtils.property(thatLocator, "attributeNamespace", rhsAttributeNamespace), lhsAttributeNamespace, rhsAttributeNamespace)) {
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
