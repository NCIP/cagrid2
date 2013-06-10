
package org.oasis.names.tc.saml.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
import org.w3._2001.xmlschema.Adapter1;


/**
 * <p>Java class for ConditionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConditionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}AudienceRestrictionCondition"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}DoNotCacheCondition"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}Condition"/>
 *       &lt;/choice>
 *       &lt;attribute name="NotBefore" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="NotOnOrAfter" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConditionsType", propOrder = {
    "audienceRestrictionConditionOrDoNotCacheConditionOrCondition"
})
public class ConditionsType
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElements({
        @XmlElement(name = "DoNotCacheCondition", type = DoNotCacheConditionType.class),
        @XmlElement(name = "AudienceRestrictionCondition", type = AudienceRestrictionConditionType.class),
        @XmlElement(name = "Condition")
    })
    protected List<ConditionAbstractType> audienceRestrictionConditionOrDoNotCacheConditionOrCondition;
    @XmlAttribute(name = "NotBefore")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar notBefore;
    @XmlAttribute(name = "NotOnOrAfter")
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar notOnOrAfter;

    /**
     * Gets the value of the audienceRestrictionConditionOrDoNotCacheConditionOrCondition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the audienceRestrictionConditionOrDoNotCacheConditionOrCondition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAudienceRestrictionConditionOrDoNotCacheConditionOrCondition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DoNotCacheConditionType }
     * {@link AudienceRestrictionConditionType }
     * {@link ConditionAbstractType }
     * 
     * 
     */
    public List<ConditionAbstractType> getAudienceRestrictionConditionOrDoNotCacheConditionOrCondition() {
        if (audienceRestrictionConditionOrDoNotCacheConditionOrCondition == null) {
            audienceRestrictionConditionOrDoNotCacheConditionOrCondition = new ArrayList<ConditionAbstractType>();
        }
        return this.audienceRestrictionConditionOrDoNotCacheConditionOrCondition;
    }

    /**
     * Gets the value of the notBefore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getNotBefore() {
        return notBefore;
    }

    /**
     * Sets the value of the notBefore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotBefore(Calendar value) {
        this.notBefore = value;
    }

    /**
     * Gets the value of the notOnOrAfter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getNotOnOrAfter() {
        return notOnOrAfter;
    }

    /**
     * Sets the value of the notOnOrAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotOnOrAfter(Calendar value) {
        this.notOnOrAfter = value;
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
            List<ConditionAbstractType> theAudienceRestrictionConditionOrDoNotCacheConditionOrCondition;
            theAudienceRestrictionConditionOrDoNotCacheConditionOrCondition = (((this.audienceRestrictionConditionOrDoNotCacheConditionOrCondition!= null)&&(!this.audienceRestrictionConditionOrDoNotCacheConditionOrCondition.isEmpty()))?this.getAudienceRestrictionConditionOrDoNotCacheConditionOrCondition():null);
            strategy.appendField(locator, this, "audienceRestrictionConditionOrDoNotCacheConditionOrCondition", buffer, theAudienceRestrictionConditionOrDoNotCacheConditionOrCondition);
        }
        {
            Calendar theNotBefore;
            theNotBefore = this.getNotBefore();
            strategy.appendField(locator, this, "notBefore", buffer, theNotBefore);
        }
        {
            Calendar theNotOnOrAfter;
            theNotOnOrAfter = this.getNotOnOrAfter();
            strategy.appendField(locator, this, "notOnOrAfter", buffer, theNotOnOrAfter);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<ConditionAbstractType> theAudienceRestrictionConditionOrDoNotCacheConditionOrCondition;
            theAudienceRestrictionConditionOrDoNotCacheConditionOrCondition = (((this.audienceRestrictionConditionOrDoNotCacheConditionOrCondition!= null)&&(!this.audienceRestrictionConditionOrDoNotCacheConditionOrCondition.isEmpty()))?this.getAudienceRestrictionConditionOrDoNotCacheConditionOrCondition():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "audienceRestrictionConditionOrDoNotCacheConditionOrCondition", theAudienceRestrictionConditionOrDoNotCacheConditionOrCondition), currentHashCode, theAudienceRestrictionConditionOrDoNotCacheConditionOrCondition);
        }
        {
            Calendar theNotBefore;
            theNotBefore = this.getNotBefore();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "notBefore", theNotBefore), currentHashCode, theNotBefore);
        }
        {
            Calendar theNotOnOrAfter;
            theNotOnOrAfter = this.getNotOnOrAfter();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "notOnOrAfter", theNotOnOrAfter), currentHashCode, theNotOnOrAfter);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ConditionsType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ConditionsType that = ((ConditionsType) object);
        {
            List<ConditionAbstractType> lhsAudienceRestrictionConditionOrDoNotCacheConditionOrCondition;
            lhsAudienceRestrictionConditionOrDoNotCacheConditionOrCondition = (((this.audienceRestrictionConditionOrDoNotCacheConditionOrCondition!= null)&&(!this.audienceRestrictionConditionOrDoNotCacheConditionOrCondition.isEmpty()))?this.getAudienceRestrictionConditionOrDoNotCacheConditionOrCondition():null);
            List<ConditionAbstractType> rhsAudienceRestrictionConditionOrDoNotCacheConditionOrCondition;
            rhsAudienceRestrictionConditionOrDoNotCacheConditionOrCondition = (((that.audienceRestrictionConditionOrDoNotCacheConditionOrCondition!= null)&&(!that.audienceRestrictionConditionOrDoNotCacheConditionOrCondition.isEmpty()))?that.getAudienceRestrictionConditionOrDoNotCacheConditionOrCondition():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "audienceRestrictionConditionOrDoNotCacheConditionOrCondition", lhsAudienceRestrictionConditionOrDoNotCacheConditionOrCondition), LocatorUtils.property(thatLocator, "audienceRestrictionConditionOrDoNotCacheConditionOrCondition", rhsAudienceRestrictionConditionOrDoNotCacheConditionOrCondition), lhsAudienceRestrictionConditionOrDoNotCacheConditionOrCondition, rhsAudienceRestrictionConditionOrDoNotCacheConditionOrCondition)) {
                return false;
            }
        }
        {
            Calendar lhsNotBefore;
            lhsNotBefore = this.getNotBefore();
            Calendar rhsNotBefore;
            rhsNotBefore = that.getNotBefore();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "notBefore", lhsNotBefore), LocatorUtils.property(thatLocator, "notBefore", rhsNotBefore), lhsNotBefore, rhsNotBefore)) {
                return false;
            }
        }
        {
            Calendar lhsNotOnOrAfter;
            lhsNotOnOrAfter = this.getNotOnOrAfter();
            Calendar rhsNotOnOrAfter;
            rhsNotOnOrAfter = that.getNotOnOrAfter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "notOnOrAfter", lhsNotOnOrAfter), LocatorUtils.property(thatLocator, "notOnOrAfter", rhsNotOnOrAfter), lhsNotOnOrAfter, rhsNotOnOrAfter)) {
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
