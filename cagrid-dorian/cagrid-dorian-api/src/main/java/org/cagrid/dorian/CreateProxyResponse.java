
package org.cagrid.dorian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.common.X509Certificate;
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
 *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-common}X509Certificate" maxOccurs="unbounded"/>
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
    "x509Certificate"
})
@XmlRootElement(name = "CreateProxyResponse")
public class CreateProxyResponse
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "X509Certificate", namespace = "http://cagrid.nci.nih.gov/1/dorian-common", required = true)
    protected List<X509Certificate> x509Certificate;

    /**
     * Gets the value of the x509Certificate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the x509Certificate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getX509Certificate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link X509Certificate }
     * 
     * 
     */
    public List<X509Certificate> getX509Certificate() {
        if (x509Certificate == null) {
            x509Certificate = new ArrayList<X509Certificate>();
        }
        return this.x509Certificate;
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
            List<X509Certificate> theX509Certificate;
            theX509Certificate = (((this.x509Certificate!= null)&&(!this.x509Certificate.isEmpty()))?this.getX509Certificate():null);
            strategy.appendField(locator, this, "x509Certificate", buffer, theX509Certificate);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            List<X509Certificate> theX509Certificate;
            theX509Certificate = (((this.x509Certificate!= null)&&(!this.x509Certificate.isEmpty()))?this.getX509Certificate():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "x509Certificate", theX509Certificate), currentHashCode, theX509Certificate);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CreateProxyResponse)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CreateProxyResponse that = ((CreateProxyResponse) object);
        {
            List<X509Certificate> lhsX509Certificate;
            lhsX509Certificate = (((this.x509Certificate!= null)&&(!this.x509Certificate.isEmpty()))?this.getX509Certificate():null);
            List<X509Certificate> rhsX509Certificate;
            rhsX509Certificate = (((that.x509Certificate!= null)&&(!that.x509Certificate.isEmpty()))?that.getX509Certificate():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "x509Certificate", lhsX509Certificate), LocatorUtils.property(thatLocator, "x509Certificate", rhsX509Certificate), lhsX509Certificate, rhsX509Certificate)) {
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
