
package org.oasis.names.tc.saml.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
import org.w3._2000._09.xmldsig.KeyInfoType;


/**
 * <p>Java class for SubjectConfirmationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubjectConfirmationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}ConfirmationMethod" maxOccurs="unbounded"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}SubjectConfirmationData" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}KeyInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectConfirmationType", propOrder = {
    "confirmationMethod",
    "subjectConfirmationData",
    "keyInfo"
})
public class SubjectConfirmationType implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "ConfirmationMethod", required = true)
    @XmlSchemaType(name = "anyURI")
    protected List<String> confirmationMethod;
    @XmlElement(name = "SubjectConfirmationData")
    protected Object subjectConfirmationData;
    @XmlElement(name = "KeyInfo", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected KeyInfoType keyInfo;

    /**
     * Gets the value of the confirmationMethod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the confirmationMethod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConfirmationMethod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getConfirmationMethod() {
        if (confirmationMethod == null) {
            confirmationMethod = new ArrayList<String>();
        }
        return this.confirmationMethod;
    }

    /**
     * Gets the value of the subjectConfirmationData property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSubjectConfirmationData() {
        return subjectConfirmationData;
    }

    /**
     * Sets the value of the subjectConfirmationData property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSubjectConfirmationData(Object value) {
        this.subjectConfirmationData = value;
    }

    /**
     * Gets the value of the keyInfo property.
     * 
     * @return
     *     possible object is
     *     {@link KeyInfoType }
     *     
     */
    public KeyInfoType getKeyInfo() {
        return keyInfo;
    }

    /**
     * Sets the value of the keyInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyInfoType }
     *     
     */
    public void setKeyInfo(KeyInfoType value) {
        this.keyInfo = value;
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
            List<String> theConfirmationMethod;
            theConfirmationMethod = (((this.confirmationMethod!= null)&&(!this.confirmationMethod.isEmpty()))?this.getConfirmationMethod():null);
            strategy.appendField(locator, this, "confirmationMethod", buffer, theConfirmationMethod);
        }
        {
            Object theSubjectConfirmationData;
            theSubjectConfirmationData = this.getSubjectConfirmationData();
            strategy.appendField(locator, this, "subjectConfirmationData", buffer, theSubjectConfirmationData);
        }
        {
            KeyInfoType theKeyInfo;
            theKeyInfo = this.getKeyInfo();
            strategy.appendField(locator, this, "keyInfo", buffer, theKeyInfo);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<String> theConfirmationMethod;
            theConfirmationMethod = (((this.confirmationMethod!= null)&&(!this.confirmationMethod.isEmpty()))?this.getConfirmationMethod():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "confirmationMethod", theConfirmationMethod), currentHashCode, theConfirmationMethod);
        }
        {
            Object theSubjectConfirmationData;
            theSubjectConfirmationData = this.getSubjectConfirmationData();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "subjectConfirmationData", theSubjectConfirmationData), currentHashCode, theSubjectConfirmationData);
        }
        {
            KeyInfoType theKeyInfo;
            theKeyInfo = this.getKeyInfo();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "keyInfo", theKeyInfo), currentHashCode, theKeyInfo);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SubjectConfirmationType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SubjectConfirmationType that = ((SubjectConfirmationType) object);
        {
            List<String> lhsConfirmationMethod;
            lhsConfirmationMethod = (((this.confirmationMethod!= null)&&(!this.confirmationMethod.isEmpty()))?this.getConfirmationMethod():null);
            List<String> rhsConfirmationMethod;
            rhsConfirmationMethod = (((that.confirmationMethod!= null)&&(!that.confirmationMethod.isEmpty()))?that.getConfirmationMethod():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "confirmationMethod", lhsConfirmationMethod), LocatorUtils.property(thatLocator, "confirmationMethod", rhsConfirmationMethod), lhsConfirmationMethod, rhsConfirmationMethod)) {
                return false;
            }
        }
        {
            Object lhsSubjectConfirmationData;
            lhsSubjectConfirmationData = this.getSubjectConfirmationData();
            Object rhsSubjectConfirmationData;
            rhsSubjectConfirmationData = that.getSubjectConfirmationData();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subjectConfirmationData", lhsSubjectConfirmationData), LocatorUtils.property(thatLocator, "subjectConfirmationData", rhsSubjectConfirmationData), lhsSubjectConfirmationData, rhsSubjectConfirmationData)) {
                return false;
            }
        }
        {
            KeyInfoType lhsKeyInfo;
            lhsKeyInfo = this.getKeyInfo();
            KeyInfoType rhsKeyInfo;
            rhsKeyInfo = that.getKeyInfo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "keyInfo", lhsKeyInfo), LocatorUtils.property(thatLocator, "keyInfo", rhsKeyInfo), lhsKeyInfo, rhsKeyInfo)) {
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
