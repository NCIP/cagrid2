
package org.cagrid.serviceregistration.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
import org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType;


/**
 * <p>Java class for ServiceGroupRegistrationParameters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceGroupRegistrationParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceGroupEPR" type="{http://schemas.xmlsoap.org/ws/2004/03/addressing}EndpointReferenceType" minOccurs="0"/>
 *         &lt;element name="RegistrantEPR" type="{http://schemas.xmlsoap.org/ws/2004/03/addressing}EndpointReferenceType" minOccurs="0"/>
 *         &lt;element name="InitialTerminationTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="RefreshIntervalSecs" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SecurityDescriptorFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Content" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceGroupRegistrationParameters", propOrder = {
    "serviceGroupEPR",
    "registrantEPR",
    "initialTerminationTime",
    "refreshIntervalSecs",
    "securityDescriptorFile",
    "content"
})
@XmlRootElement(name = "ServiceGroupRegistrationParameters")
public class ServiceGroupRegistrationParameters
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "ServiceGroupEPR")
    protected EndpointReferenceType serviceGroupEPR;
    @XmlElement(name = "RegistrantEPR")
    protected EndpointReferenceType registrantEPR;
    @XmlElement(name = "InitialTerminationTime", type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar initialTerminationTime;
    @XmlElement(name = "RefreshIntervalSecs")
    protected int refreshIntervalSecs;
    @XmlElement(name = "SecurityDescriptorFile")
    protected String securityDescriptorFile;
    @XmlElement(name = "Content", required = true)
    protected Object content;

    /**
     * Gets the value of the serviceGroupEPR property.
     * 
     * @return
     *     possible object is
     *     {@link EndpointReferenceType }
     *     
     */
    public EndpointReferenceType getServiceGroupEPR() {
        return serviceGroupEPR;
    }

    /**
     * Sets the value of the serviceGroupEPR property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndpointReferenceType }
     *     
     */
    public void setServiceGroupEPR(EndpointReferenceType value) {
        this.serviceGroupEPR = value;
    }

    /**
     * Gets the value of the registrantEPR property.
     * 
     * @return
     *     possible object is
     *     {@link EndpointReferenceType }
     *     
     */
    public EndpointReferenceType getRegistrantEPR() {
        return registrantEPR;
    }

    /**
     * Sets the value of the registrantEPR property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndpointReferenceType }
     *     
     */
    public void setRegistrantEPR(EndpointReferenceType value) {
        this.registrantEPR = value;
    }

    /**
     * Gets the value of the initialTerminationTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getInitialTerminationTime() {
        return initialTerminationTime;
    }

    /**
     * Sets the value of the initialTerminationTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialTerminationTime(Calendar value) {
        this.initialTerminationTime = value;
    }

    /**
     * Gets the value of the refreshIntervalSecs property.
     * 
     */
    public int getRefreshIntervalSecs() {
        return refreshIntervalSecs;
    }

    /**
     * Sets the value of the refreshIntervalSecs property.
     * 
     */
    public void setRefreshIntervalSecs(int value) {
        this.refreshIntervalSecs = value;
    }

    /**
     * Gets the value of the securityDescriptorFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityDescriptorFile() {
        return securityDescriptorFile;
    }

    /**
     * Sets the value of the securityDescriptorFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityDescriptorFile(String value) {
        this.securityDescriptorFile = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setContent(Object value) {
        this.content = value;
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
            EndpointReferenceType theServiceGroupEPR;
            theServiceGroupEPR = this.getServiceGroupEPR();
            strategy.appendField(locator, this, "serviceGroupEPR", buffer, theServiceGroupEPR);
        }
        {
            EndpointReferenceType theRegistrantEPR;
            theRegistrantEPR = this.getRegistrantEPR();
            strategy.appendField(locator, this, "registrantEPR", buffer, theRegistrantEPR);
        }
        {
            Calendar theInitialTerminationTime;
            theInitialTerminationTime = this.getInitialTerminationTime();
            strategy.appendField(locator, this, "initialTerminationTime", buffer, theInitialTerminationTime);
        }
        {
            int theRefreshIntervalSecs;
            theRefreshIntervalSecs = (true?this.getRefreshIntervalSecs(): 0);
            strategy.appendField(locator, this, "refreshIntervalSecs", buffer, theRefreshIntervalSecs);
        }
        {
            String theSecurityDescriptorFile;
            theSecurityDescriptorFile = this.getSecurityDescriptorFile();
            strategy.appendField(locator, this, "securityDescriptorFile", buffer, theSecurityDescriptorFile);
        }
        {
            Object theContent;
            theContent = this.getContent();
            strategy.appendField(locator, this, "content", buffer, theContent);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            EndpointReferenceType theServiceGroupEPR;
            theServiceGroupEPR = this.getServiceGroupEPR();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "serviceGroupEPR", theServiceGroupEPR), currentHashCode, theServiceGroupEPR);
        }
        {
            EndpointReferenceType theRegistrantEPR;
            theRegistrantEPR = this.getRegistrantEPR();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "registrantEPR", theRegistrantEPR), currentHashCode, theRegistrantEPR);
        }
        {
            Calendar theInitialTerminationTime;
            theInitialTerminationTime = this.getInitialTerminationTime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "initialTerminationTime", theInitialTerminationTime), currentHashCode, theInitialTerminationTime);
        }
        {
            int theRefreshIntervalSecs;
            theRefreshIntervalSecs = (true?this.getRefreshIntervalSecs(): 0);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "refreshIntervalSecs", theRefreshIntervalSecs), currentHashCode, theRefreshIntervalSecs);
        }
        {
            String theSecurityDescriptorFile;
            theSecurityDescriptorFile = this.getSecurityDescriptorFile();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "securityDescriptorFile", theSecurityDescriptorFile), currentHashCode, theSecurityDescriptorFile);
        }
        {
            Object theContent;
            theContent = this.getContent();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "content", theContent), currentHashCode, theContent);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceGroupRegistrationParameters)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceGroupRegistrationParameters that = ((ServiceGroupRegistrationParameters) object);
        {
            EndpointReferenceType lhsServiceGroupEPR;
            lhsServiceGroupEPR = this.getServiceGroupEPR();
            EndpointReferenceType rhsServiceGroupEPR;
            rhsServiceGroupEPR = that.getServiceGroupEPR();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceGroupEPR", lhsServiceGroupEPR), LocatorUtils.property(thatLocator, "serviceGroupEPR", rhsServiceGroupEPR), lhsServiceGroupEPR, rhsServiceGroupEPR)) {
                return false;
            }
        }
        {
            EndpointReferenceType lhsRegistrantEPR;
            lhsRegistrantEPR = this.getRegistrantEPR();
            EndpointReferenceType rhsRegistrantEPR;
            rhsRegistrantEPR = that.getRegistrantEPR();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "registrantEPR", lhsRegistrantEPR), LocatorUtils.property(thatLocator, "registrantEPR", rhsRegistrantEPR), lhsRegistrantEPR, rhsRegistrantEPR)) {
                return false;
            }
        }
        {
            Calendar lhsInitialTerminationTime;
            lhsInitialTerminationTime = this.getInitialTerminationTime();
            Calendar rhsInitialTerminationTime;
            rhsInitialTerminationTime = that.getInitialTerminationTime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "initialTerminationTime", lhsInitialTerminationTime), LocatorUtils.property(thatLocator, "initialTerminationTime", rhsInitialTerminationTime), lhsInitialTerminationTime, rhsInitialTerminationTime)) {
                return false;
            }
        }
        {
            int lhsRefreshIntervalSecs;
            lhsRefreshIntervalSecs = (true?this.getRefreshIntervalSecs(): 0);
            int rhsRefreshIntervalSecs;
            rhsRefreshIntervalSecs = (true?that.getRefreshIntervalSecs(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "refreshIntervalSecs", lhsRefreshIntervalSecs), LocatorUtils.property(thatLocator, "refreshIntervalSecs", rhsRefreshIntervalSecs), lhsRefreshIntervalSecs, rhsRefreshIntervalSecs)) {
                return false;
            }
        }
        {
            String lhsSecurityDescriptorFile;
            lhsSecurityDescriptorFile = this.getSecurityDescriptorFile();
            String rhsSecurityDescriptorFile;
            rhsSecurityDescriptorFile = that.getSecurityDescriptorFile();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "securityDescriptorFile", lhsSecurityDescriptorFile), LocatorUtils.property(thatLocator, "securityDescriptorFile", rhsSecurityDescriptorFile), lhsSecurityDescriptorFile, rhsSecurityDescriptorFile)) {
                return false;
            }
        }
        {
            Object lhsContent;
            lhsContent = this.getContent();
            Object rhsContent;
            rhsContent = that.getContent();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "content", lhsContent), LocatorUtils.property(thatLocator, "content", rhsContent), lhsContent, rhsContent)) {
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
