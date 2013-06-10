
package org.cagrid.dorian.policy;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 * <p>Java class for UserIdPolicy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserIdPolicy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="MinLength" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="MaxLength" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserIdPolicy")
public class UserIdPolicy
    implements Serializable, Equals, HashCode, ToString
{

    @XmlAttribute(name = "MinLength", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int minLength;
    @XmlAttribute(name = "MaxLength", namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", required = true)
    protected int maxLength;

    /**
     * Gets the value of the minLength property.
     * 
     */
    public int getMinLength() {
        return minLength;
    }

    /**
     * Sets the value of the minLength property.
     * 
     */
    public void setMinLength(int value) {
        this.minLength = value;
    }

    /**
     * Gets the value of the maxLength property.
     * 
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * Sets the value of the maxLength property.
     * 
     */
    public void setMaxLength(int value) {
        this.maxLength = value;
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
            int theMinLength;
            theMinLength = (true?this.getMinLength(): 0);
            strategy.appendField(locator, this, "minLength", buffer, theMinLength);
        }
        {
            int theMaxLength;
            theMaxLength = (true?this.getMaxLength(): 0);
            strategy.appendField(locator, this, "maxLength", buffer, theMaxLength);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            int theMinLength;
            theMinLength = (true?this.getMinLength(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "minLength", theMinLength), currentHashCode, theMinLength);
        }
        {
            int theMaxLength;
            theMaxLength = (true?this.getMaxLength(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "maxLength", theMaxLength), currentHashCode, theMaxLength);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof UserIdPolicy)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final UserIdPolicy that = ((UserIdPolicy) object);
        {
            int lhsMinLength;
            lhsMinLength = (true?this.getMinLength(): 0);
            int rhsMinLength;
            rhsMinLength = (true?that.getMinLength(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minLength", lhsMinLength), LocatorUtils.property(thatLocator, "minLength", rhsMinLength), lhsMinLength, rhsMinLength)) {
                return false;
            }
        }
        {
            int lhsMaxLength;
            lhsMaxLength = (true?this.getMaxLength(): 0);
            int rhsMaxLength;
            rhsMaxLength = (true?that.getMaxLength(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxLength", lhsMaxLength), LocatorUtils.property(thatLocator, "maxLength", rhsMaxLength), lhsMaxLength, rhsMaxLength)) {
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
