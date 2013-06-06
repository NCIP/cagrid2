
package org.cagrid.gts.model;

import java.io.Serializable;
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
 * <p>Java class for AuthorityPrioritySpecification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthorityPrioritySpecification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceURI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Priority" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthorityPrioritySpecification", propOrder = {
    "serviceURI",
    "priority"
})
public class AuthorityPrioritySpecification
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "ServiceURI", required = true)
    protected String serviceURI;
    @XmlElement(name = "Priority")
    protected int priority;

    /**
     * Gets the value of the serviceURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceURI() {
        return serviceURI;
    }

    /**
     * Sets the value of the serviceURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceURI(String value) {
        this.serviceURI = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     */
    public void setPriority(int value) {
        this.priority = value;
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
            String theServiceURI;
            theServiceURI = this.getServiceURI();
            strategy.appendField(locator, this, "serviceURI", buffer, theServiceURI);
        }
        {
            int thePriority;
            thePriority = (true?this.getPriority(): 0);
            strategy.appendField(locator, this, "priority", buffer, thePriority);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theServiceURI;
            theServiceURI = this.getServiceURI();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "serviceURI", theServiceURI), currentHashCode, theServiceURI);
        }
        {
            int thePriority;
            thePriority = (true?this.getPriority(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "priority", thePriority), currentHashCode, thePriority);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AuthorityPrioritySpecification)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AuthorityPrioritySpecification that = ((AuthorityPrioritySpecification) object);
        {
            String lhsServiceURI;
            lhsServiceURI = this.getServiceURI();
            String rhsServiceURI;
            rhsServiceURI = that.getServiceURI();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceURI", lhsServiceURI), LocatorUtils.property(thatLocator, "serviceURI", rhsServiceURI), lhsServiceURI, rhsServiceURI)) {
                return false;
            }
        }
        {
            int lhsPriority;
            lhsPriority = (true?this.getPriority(): 0);
            int rhsPriority;
            rhsPriority = (true?that.getPriority(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "priority", lhsPriority), LocatorUtils.property(thatLocator, "priority", rhsPriority), lhsPriority, rhsPriority)) {
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
