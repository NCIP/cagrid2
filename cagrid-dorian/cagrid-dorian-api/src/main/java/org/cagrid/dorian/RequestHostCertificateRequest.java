
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.cagrid.dorian.model.federation.HostCertificateRequest;
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
 *         &lt;element name="req">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateRequest"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "req"
})
@XmlRootElement(name = "RequestHostCertificateRequest")
public class RequestHostCertificateRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected RequestHostCertificateRequest.Req req;

    /**
     * Gets the value of the req property.
     * 
     * @return
     *     possible object is
     *     {@link RequestHostCertificateRequest.Req }
     *     
     */
    public RequestHostCertificateRequest.Req getReq() {
        return req;
    }

    /**
     * Sets the value of the req property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestHostCertificateRequest.Req }
     *     
     */
    public void setReq(RequestHostCertificateRequest.Req value) {
        this.req = value;
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
            RequestHostCertificateRequest.Req theReq;
            theReq = this.getReq();
            strategy.appendField(locator, this, "req", buffer, theReq);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            RequestHostCertificateRequest.Req theReq;
            theReq = this.getReq();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "req", theReq), currentHashCode, theReq);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RequestHostCertificateRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final RequestHostCertificateRequest that = ((RequestHostCertificateRequest) object);
        {
            RequestHostCertificateRequest.Req lhsReq;
            lhsReq = this.getReq();
            RequestHostCertificateRequest.Req rhsReq;
            rhsReq = that.getReq();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "req", lhsReq), LocatorUtils.property(thatLocator, "req", rhsReq), lhsReq, rhsReq)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }


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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}HostCertificateRequest"/>
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
        "hostCertificateRequest"
    })
    public static class Req
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "HostCertificateRequest", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected HostCertificateRequest hostCertificateRequest;

        /**
         * Gets the value of the hostCertificateRequest property.
         * 
         * @return
         *     possible object is
         *     {@link HostCertificateRequest }
         *     
         */
        public HostCertificateRequest getHostCertificateRequest() {
            return hostCertificateRequest;
        }

        /**
         * Sets the value of the hostCertificateRequest property.
         * 
         * @param value
         *     allowed object is
         *     {@link HostCertificateRequest }
         *     
         */
        public void setHostCertificateRequest(HostCertificateRequest value) {
            this.hostCertificateRequest = value;
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
                HostCertificateRequest theHostCertificateRequest;
                theHostCertificateRequest = this.getHostCertificateRequest();
                strategy.appendField(locator, this, "hostCertificateRequest", buffer, theHostCertificateRequest);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                HostCertificateRequest theHostCertificateRequest;
                theHostCertificateRequest = this.getHostCertificateRequest();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "hostCertificateRequest", theHostCertificateRequest), currentHashCode, theHostCertificateRequest);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof RequestHostCertificateRequest.Req)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final RequestHostCertificateRequest.Req that = ((RequestHostCertificateRequest.Req) object);
            {
                HostCertificateRequest lhsHostCertificateRequest;
                lhsHostCertificateRequest = this.getHostCertificateRequest();
                HostCertificateRequest rhsHostCertificateRequest;
                rhsHostCertificateRequest = that.getHostCertificateRequest();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "hostCertificateRequest", lhsHostCertificateRequest), LocatorUtils.property(thatLocator, "hostCertificateRequest", rhsHostCertificateRequest), lhsHostCertificateRequest, rhsHostCertificateRequest)) {
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

}
