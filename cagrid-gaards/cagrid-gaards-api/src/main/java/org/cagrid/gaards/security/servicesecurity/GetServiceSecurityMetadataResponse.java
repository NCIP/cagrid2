
package org.cagrid.gaards.security.servicesecurity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
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
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.security}ServiceSecurityMetadata"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serviceSecurityMetadata"
})
@XmlRootElement(name = "GetServiceSecurityMetadataResponse")
public class GetServiceSecurityMetadataResponse
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "ServiceSecurityMetadata", namespace = "gme://caGrid.caBIG/1.0/gov.nih.nci.cagrid.metadata.security", required = true)
    protected ServiceSecurityMetadata serviceSecurityMetadata;

    /**
     * Gets the value of the serviceSecurityMetadata property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceSecurityMetadata }
     *     
     */
    public ServiceSecurityMetadata getServiceSecurityMetadata() {
        return serviceSecurityMetadata;
    }

    /**
     * Sets the value of the serviceSecurityMetadata property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceSecurityMetadata }
     *     
     */
    public void setServiceSecurityMetadata(ServiceSecurityMetadata value) {
        this.serviceSecurityMetadata = value;
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
            ServiceSecurityMetadata theServiceSecurityMetadata;
            theServiceSecurityMetadata = this.getServiceSecurityMetadata();
            strategy.appendField(locator, this, "serviceSecurityMetadata", buffer, theServiceSecurityMetadata);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            ServiceSecurityMetadata theServiceSecurityMetadata;
            theServiceSecurityMetadata = this.getServiceSecurityMetadata();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "serviceSecurityMetadata", theServiceSecurityMetadata), currentHashCode, theServiceSecurityMetadata);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof GetServiceSecurityMetadataResponse)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final GetServiceSecurityMetadataResponse that = ((GetServiceSecurityMetadataResponse) object);
        {
            ServiceSecurityMetadata lhsServiceSecurityMetadata;
            lhsServiceSecurityMetadata = this.getServiceSecurityMetadata();
            ServiceSecurityMetadata rhsServiceSecurityMetadata;
            rhsServiceSecurityMetadata = that.getServiceSecurityMetadata();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceSecurityMetadata", lhsServiceSecurityMetadata), LocatorUtils.property(thatLocator, "serviceSecurityMetadata", rhsServiceSecurityMetadata), lhsServiceSecurityMetadata, rhsServiceSecurityMetadata)) {
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
