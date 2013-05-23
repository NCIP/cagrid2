
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
    implements Serializable
{

    @XmlElements({
        @XmlElement(name = "Condition"),
        @XmlElement(name = "AudienceRestrictionCondition", type = AudienceRestrictionConditionType.class),
        @XmlElement(name = "DoNotCacheCondition", type = DoNotCacheConditionType.class)
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
     * {@link ConditionAbstractType }
     * {@link AudienceRestrictionConditionType }
     * {@link DoNotCacheConditionType }
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

}
