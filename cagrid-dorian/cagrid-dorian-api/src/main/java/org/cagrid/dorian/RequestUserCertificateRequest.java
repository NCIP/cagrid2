
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.cagrid.dorian.model.federation.CertificateLifetime;
import org.cagrid.dorian.model.federation.PublicKey;
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
import org.oasis.names.tc.saml.assertion.AssertionType;


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
 *         &lt;element name="saml">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}Assertion"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="key">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}PublicKey"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="lifetime">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}CertificateLifetime"/>
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
    "saml",
    "key",
    "lifetime"
})
@XmlRootElement(name = "RequestUserCertificateRequest")
public class RequestUserCertificateRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected RequestUserCertificateRequest.Saml saml;
    @XmlElement(required = true)
    protected RequestUserCertificateRequest.Key key;
    @XmlElement(required = true)
    protected RequestUserCertificateRequest.Lifetime lifetime;

    /**
     * Gets the value of the saml property.
     * 
     * @return
     *     possible object is
     *     {@link RequestUserCertificateRequest.Saml }
     *     
     */
    public RequestUserCertificateRequest.Saml getSaml() {
        return saml;
    }

    /**
     * Sets the value of the saml property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestUserCertificateRequest.Saml }
     *     
     */
    public void setSaml(RequestUserCertificateRequest.Saml value) {
        this.saml = value;
    }

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link RequestUserCertificateRequest.Key }
     *     
     */
    public RequestUserCertificateRequest.Key getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestUserCertificateRequest.Key }
     *     
     */
    public void setKey(RequestUserCertificateRequest.Key value) {
        this.key = value;
    }

    /**
     * Gets the value of the lifetime property.
     * 
     * @return
     *     possible object is
     *     {@link RequestUserCertificateRequest.Lifetime }
     *     
     */
    public RequestUserCertificateRequest.Lifetime getLifetime() {
        return lifetime;
    }

    /**
     * Sets the value of the lifetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestUserCertificateRequest.Lifetime }
     *     
     */
    public void setLifetime(RequestUserCertificateRequest.Lifetime value) {
        this.lifetime = value;
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
            RequestUserCertificateRequest.Saml theSaml;
            theSaml = this.getSaml();
            strategy.appendField(locator, this, "saml", buffer, theSaml);
        }
        {
            RequestUserCertificateRequest.Key theKey;
            theKey = this.getKey();
            strategy.appendField(locator, this, "key", buffer, theKey);
        }
        {
            RequestUserCertificateRequest.Lifetime theLifetime;
            theLifetime = this.getLifetime();
            strategy.appendField(locator, this, "lifetime", buffer, theLifetime);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            RequestUserCertificateRequest.Saml theSaml;
            theSaml = this.getSaml();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "saml", theSaml), currentHashCode, theSaml);
        }
        {
            RequestUserCertificateRequest.Key theKey;
            theKey = this.getKey();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "key", theKey), currentHashCode, theKey);
        }
        {
            RequestUserCertificateRequest.Lifetime theLifetime;
            theLifetime = this.getLifetime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lifetime", theLifetime), currentHashCode, theLifetime);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RequestUserCertificateRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final RequestUserCertificateRequest that = ((RequestUserCertificateRequest) object);
        {
            RequestUserCertificateRequest.Saml lhsSaml;
            lhsSaml = this.getSaml();
            RequestUserCertificateRequest.Saml rhsSaml;
            rhsSaml = that.getSaml();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "saml", lhsSaml), LocatorUtils.property(thatLocator, "saml", rhsSaml), lhsSaml, rhsSaml)) {
                return false;
            }
        }
        {
            RequestUserCertificateRequest.Key lhsKey;
            lhsKey = this.getKey();
            RequestUserCertificateRequest.Key rhsKey;
            rhsKey = that.getKey();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "key", lhsKey), LocatorUtils.property(thatLocator, "key", rhsKey), lhsKey, rhsKey)) {
                return false;
            }
        }
        {
            RequestUserCertificateRequest.Lifetime lhsLifetime;
            lhsLifetime = this.getLifetime();
            RequestUserCertificateRequest.Lifetime rhsLifetime;
            rhsLifetime = that.getLifetime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lifetime", lhsLifetime), LocatorUtils.property(thatLocator, "lifetime", rhsLifetime), lhsLifetime, rhsLifetime)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}PublicKey"/>
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
        "publicKey"
    })
    public static class Key
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "PublicKey", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected PublicKey publicKey;

        /**
         * Gets the value of the publicKey property.
         * 
         * @return
         *     possible object is
         *     {@link PublicKey }
         *     
         */
        public PublicKey getPublicKey() {
            return publicKey;
        }

        /**
         * Sets the value of the publicKey property.
         * 
         * @param value
         *     allowed object is
         *     {@link PublicKey }
         *     
         */
        public void setPublicKey(PublicKey value) {
            this.publicKey = value;
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
                PublicKey thePublicKey;
                thePublicKey = this.getPublicKey();
                strategy.appendField(locator, this, "publicKey", buffer, thePublicKey);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                PublicKey thePublicKey;
                thePublicKey = this.getPublicKey();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "publicKey", thePublicKey), currentHashCode, thePublicKey);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof RequestUserCertificateRequest.Key)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final RequestUserCertificateRequest.Key that = ((RequestUserCertificateRequest.Key) object);
            {
                PublicKey lhsPublicKey;
                lhsPublicKey = this.getPublicKey();
                PublicKey rhsPublicKey;
                rhsPublicKey = that.getPublicKey();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "publicKey", lhsPublicKey), LocatorUtils.property(thatLocator, "publicKey", rhsPublicKey), lhsPublicKey, rhsPublicKey)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}CertificateLifetime"/>
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
        "certificateLifetime"
    })
    public static class Lifetime
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "CertificateLifetime", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected CertificateLifetime certificateLifetime;

        /**
         * Gets the value of the certificateLifetime property.
         * 
         * @return
         *     possible object is
         *     {@link CertificateLifetime }
         *     
         */
        public CertificateLifetime getCertificateLifetime() {
            return certificateLifetime;
        }

        /**
         * Sets the value of the certificateLifetime property.
         * 
         * @param value
         *     allowed object is
         *     {@link CertificateLifetime }
         *     
         */
        public void setCertificateLifetime(CertificateLifetime value) {
            this.certificateLifetime = value;
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
                CertificateLifetime theCertificateLifetime;
                theCertificateLifetime = this.getCertificateLifetime();
                strategy.appendField(locator, this, "certificateLifetime", buffer, theCertificateLifetime);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                CertificateLifetime theCertificateLifetime;
                theCertificateLifetime = this.getCertificateLifetime();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "certificateLifetime", theCertificateLifetime), currentHashCode, theCertificateLifetime);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof RequestUserCertificateRequest.Lifetime)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final RequestUserCertificateRequest.Lifetime that = ((RequestUserCertificateRequest.Lifetime) object);
            {
                CertificateLifetime lhsCertificateLifetime;
                lhsCertificateLifetime = this.getCertificateLifetime();
                CertificateLifetime rhsCertificateLifetime;
                rhsCertificateLifetime = that.getCertificateLifetime();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "certificateLifetime", lhsCertificateLifetime), LocatorUtils.property(thatLocator, "certificateLifetime", rhsCertificateLifetime), lhsCertificateLifetime, rhsCertificateLifetime)) {
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
     *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}Assertion"/>
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
        "assertion"
    })
    public static class Saml
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "Assertion", namespace = "urn:oasis:names:tc:SAML:1.0:assertion", required = true)
        protected AssertionType assertion;

        /**
         * Gets the value of the assertion property.
         * 
         * @return
         *     possible object is
         *     {@link AssertionType }
         *     
         */
        public AssertionType getAssertion() {
            return assertion;
        }

        /**
         * Sets the value of the assertion property.
         * 
         * @param value
         *     allowed object is
         *     {@link AssertionType }
         *     
         */
        public void setAssertion(AssertionType value) {
            this.assertion = value;
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
                AssertionType theAssertion;
                theAssertion = this.getAssertion();
                strategy.appendField(locator, this, "assertion", buffer, theAssertion);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                AssertionType theAssertion;
                theAssertion = this.getAssertion();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "assertion", theAssertion), currentHashCode, theAssertion);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof RequestUserCertificateRequest.Saml)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final RequestUserCertificateRequest.Saml that = ((RequestUserCertificateRequest.Saml) object);
            {
                AssertionType lhsAssertion;
                lhsAssertion = this.getAssertion();
                AssertionType rhsAssertion;
                rhsAssertion = that.getAssertion();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "assertion", lhsAssertion), LocatorUtils.property(thatLocator, "assertion", rhsAssertion), lhsAssertion, rhsAssertion)) {
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
