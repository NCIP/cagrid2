
package org.cagrid.gaards.authentication;

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
 * <p>Java class for OneTimePassword complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OneTimePassword">
 *   &lt;complexContent>
 *     &lt;extension base="{http://gaards.cagrid.org/authentication}Credential">
 *       &lt;attribute name="userId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="oneTimePassword" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OneTimePassword", namespace = "http://gaards.cagrid.org/authentication")
public class OneTimePassword
    extends Credential
    implements Serializable, Equals, HashCode, ToString
{

    @XmlAttribute(name = "userId", namespace = "http://gaards.cagrid.org/authentication", required = true)
    protected String userId;
    @XmlAttribute(name = "oneTimePassword", namespace = "http://gaards.cagrid.org/authentication", required = true)
    protected String oneTimePassword;

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the oneTimePassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOneTimePassword() {
        return oneTimePassword;
    }

    /**
     * Sets the value of the oneTimePassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOneTimePassword(String value) {
        this.oneTimePassword = value;
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
        super.appendFields(locator, buffer, strategy);
        {
            String theUserId;
            theUserId = this.getUserId();
            strategy.appendField(locator, this, "userId", buffer, theUserId);
        }
        {
            String theOneTimePassword;
            theOneTimePassword = this.getOneTimePassword();
            strategy.appendField(locator, this, "oneTimePassword", buffer, theOneTimePassword);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            String theUserId;
            theUserId = this.getUserId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "userId", theUserId), currentHashCode, theUserId);
        }
        {
            String theOneTimePassword;
            theOneTimePassword = this.getOneTimePassword();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "oneTimePassword", theOneTimePassword), currentHashCode, theOneTimePassword);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OneTimePassword)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final OneTimePassword that = ((OneTimePassword) object);
        {
            String lhsUserId;
            lhsUserId = this.getUserId();
            String rhsUserId;
            rhsUserId = that.getUserId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "userId", lhsUserId), LocatorUtils.property(thatLocator, "userId", rhsUserId), lhsUserId, rhsUserId)) {
                return false;
            }
        }
        {
            String lhsOneTimePassword;
            lhsOneTimePassword = this.getOneTimePassword();
            String rhsOneTimePassword;
            rhsOneTimePassword = that.getOneTimePassword();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oneTimePassword", lhsOneTimePassword), LocatorUtils.property(thatLocator, "oneTimePassword", rhsOneTimePassword), lhsOneTimePassword, rhsOneTimePassword)) {
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
