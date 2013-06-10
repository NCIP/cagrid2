
package org.cagrid.dorian;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.cagrid.dorian.common.SAMLAssertion;
import org.cagrid.dorian.ifs.DelegationPathLength;
import org.cagrid.dorian.ifs.ProxyLifetime;
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
 *         &lt;element name="saml">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-common}SAMLAssertion"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="publicKey">
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
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}ProxyLifetime"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="delegation">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}DelegationPathLength"/>
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
    "publicKey",
    "lifetime",
    "delegation"
})
@XmlRootElement(name = "CreateProxyRequest")
public class CreateProxyRequest
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected CreateProxyRequest.Saml saml;
    @XmlElement(required = true)
    protected CreateProxyRequest.PublicKey publicKey;
    @XmlElement(required = true)
    protected CreateProxyRequest.Lifetime lifetime;
    @XmlElement(required = true)
    protected CreateProxyRequest.Delegation delegation;

    /**
     * Gets the value of the saml property.
     * 
     * @return
     *     possible object is
     *     {@link CreateProxyRequest.Saml }
     *     
     */
    public CreateProxyRequest.Saml getSaml() {
        return saml;
    }

    /**
     * Sets the value of the saml property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateProxyRequest.Saml }
     *     
     */
    public void setSaml(CreateProxyRequest.Saml value) {
        this.saml = value;
    }

    /**
     * Gets the value of the publicKey property.
     * 
     * @return
     *     possible object is
     *     {@link CreateProxyRequest.PublicKey }
     *     
     */
    public CreateProxyRequest.PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Sets the value of the publicKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateProxyRequest.PublicKey }
     *     
     */
    public void setPublicKey(CreateProxyRequest.PublicKey value) {
        this.publicKey = value;
    }

    /**
     * Gets the value of the lifetime property.
     * 
     * @return
     *     possible object is
     *     {@link CreateProxyRequest.Lifetime }
     *     
     */
    public CreateProxyRequest.Lifetime getLifetime() {
        return lifetime;
    }

    /**
     * Sets the value of the lifetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateProxyRequest.Lifetime }
     *     
     */
    public void setLifetime(CreateProxyRequest.Lifetime value) {
        this.lifetime = value;
    }

    /**
     * Gets the value of the delegation property.
     * 
     * @return
     *     possible object is
     *     {@link CreateProxyRequest.Delegation }
     *     
     */
    public CreateProxyRequest.Delegation getDelegation() {
        return delegation;
    }

    /**
     * Sets the value of the delegation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateProxyRequest.Delegation }
     *     
     */
    public void setDelegation(CreateProxyRequest.Delegation value) {
        this.delegation = value;
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
            CreateProxyRequest.Saml theSaml;
            theSaml = this.getSaml();
            strategy.appendField(locator, this, "saml", buffer, theSaml);
        }
        {
            CreateProxyRequest.PublicKey thePublicKey;
            thePublicKey = this.getPublicKey();
            strategy.appendField(locator, this, "publicKey", buffer, thePublicKey);
        }
        {
            CreateProxyRequest.Lifetime theLifetime;
            theLifetime = this.getLifetime();
            strategy.appendField(locator, this, "lifetime", buffer, theLifetime);
        }
        {
            CreateProxyRequest.Delegation theDelegation;
            theDelegation = this.getDelegation();
            strategy.appendField(locator, this, "delegation", buffer, theDelegation);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            CreateProxyRequest.Saml theSaml;
            theSaml = this.getSaml();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "saml", theSaml), currentHashCode, theSaml);
        }
        {
            CreateProxyRequest.PublicKey thePublicKey;
            thePublicKey = this.getPublicKey();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "publicKey", thePublicKey), currentHashCode, thePublicKey);
        }
        {
            CreateProxyRequest.Lifetime theLifetime;
            theLifetime = this.getLifetime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lifetime", theLifetime), currentHashCode, theLifetime);
        }
        {
            CreateProxyRequest.Delegation theDelegation;
            theDelegation = this.getDelegation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "delegation", theDelegation), currentHashCode, theDelegation);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CreateProxyRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CreateProxyRequest that = ((CreateProxyRequest) object);
        {
            CreateProxyRequest.Saml lhsSaml;
            lhsSaml = this.getSaml();
            CreateProxyRequest.Saml rhsSaml;
            rhsSaml = that.getSaml();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "saml", lhsSaml), LocatorUtils.property(thatLocator, "saml", rhsSaml), lhsSaml, rhsSaml)) {
                return false;
            }
        }
        {
            CreateProxyRequest.PublicKey lhsPublicKey;
            lhsPublicKey = this.getPublicKey();
            CreateProxyRequest.PublicKey rhsPublicKey;
            rhsPublicKey = that.getPublicKey();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "publicKey", lhsPublicKey), LocatorUtils.property(thatLocator, "publicKey", rhsPublicKey), lhsPublicKey, rhsPublicKey)) {
                return false;
            }
        }
        {
            CreateProxyRequest.Lifetime lhsLifetime;
            lhsLifetime = this.getLifetime();
            CreateProxyRequest.Lifetime rhsLifetime;
            rhsLifetime = that.getLifetime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lifetime", lhsLifetime), LocatorUtils.property(thatLocator, "lifetime", rhsLifetime), lhsLifetime, rhsLifetime)) {
                return false;
            }
        }
        {
            CreateProxyRequest.Delegation lhsDelegation;
            lhsDelegation = this.getDelegation();
            CreateProxyRequest.Delegation rhsDelegation;
            rhsDelegation = that.getDelegation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "delegation", lhsDelegation), LocatorUtils.property(thatLocator, "delegation", rhsDelegation), lhsDelegation, rhsDelegation)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}DelegationPathLength"/>
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
        "delegationPathLength"
    })
    public static class Delegation
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "DelegationPathLength", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected DelegationPathLength delegationPathLength;

        /**
         * Gets the value of the delegationPathLength property.
         * 
         * @return
         *     possible object is
         *     {@link DelegationPathLength }
         *     
         */
        public DelegationPathLength getDelegationPathLength() {
            return delegationPathLength;
        }

        /**
         * Sets the value of the delegationPathLength property.
         * 
         * @param value
         *     allowed object is
         *     {@link DelegationPathLength }
         *     
         */
        public void setDelegationPathLength(DelegationPathLength value) {
            this.delegationPathLength = value;
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
                DelegationPathLength theDelegationPathLength;
                theDelegationPathLength = this.getDelegationPathLength();
                strategy.appendField(locator, this, "delegationPathLength", buffer, theDelegationPathLength);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                DelegationPathLength theDelegationPathLength;
                theDelegationPathLength = this.getDelegationPathLength();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "delegationPathLength", theDelegationPathLength), currentHashCode, theDelegationPathLength);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof CreateProxyRequest.Delegation)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final CreateProxyRequest.Delegation that = ((CreateProxyRequest.Delegation) object);
            {
                DelegationPathLength lhsDelegationPathLength;
                lhsDelegationPathLength = this.getDelegationPathLength();
                DelegationPathLength rhsDelegationPathLength;
                rhsDelegationPathLength = that.getDelegationPathLength();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "delegationPathLength", lhsDelegationPathLength), LocatorUtils.property(thatLocator, "delegationPathLength", rhsDelegationPathLength), lhsDelegationPathLength, rhsDelegationPathLength)) {
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-ifs}ProxyLifetime"/>
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
        "proxyLifetime"
    })
    public static class Lifetime
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "ProxyLifetime", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected ProxyLifetime proxyLifetime;

        /**
         * Gets the value of the proxyLifetime property.
         * 
         * @return
         *     possible object is
         *     {@link ProxyLifetime }
         *     
         */
        public ProxyLifetime getProxyLifetime() {
            return proxyLifetime;
        }

        /**
         * Sets the value of the proxyLifetime property.
         * 
         * @param value
         *     allowed object is
         *     {@link ProxyLifetime }
         *     
         */
        public void setProxyLifetime(ProxyLifetime value) {
            this.proxyLifetime = value;
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
                ProxyLifetime theProxyLifetime;
                theProxyLifetime = this.getProxyLifetime();
                strategy.appendField(locator, this, "proxyLifetime", buffer, theProxyLifetime);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                ProxyLifetime theProxyLifetime;
                theProxyLifetime = this.getProxyLifetime();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "proxyLifetime", theProxyLifetime), currentHashCode, theProxyLifetime);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof CreateProxyRequest.Lifetime)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final CreateProxyRequest.Lifetime that = ((CreateProxyRequest.Lifetime) object);
            {
                ProxyLifetime lhsProxyLifetime;
                lhsProxyLifetime = this.getProxyLifetime();
                ProxyLifetime rhsProxyLifetime;
                rhsProxyLifetime = that.getProxyLifetime();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "proxyLifetime", lhsProxyLifetime), LocatorUtils.property(thatLocator, "proxyLifetime", rhsProxyLifetime), lhsProxyLifetime, rhsProxyLifetime)) {
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
    public static class PublicKey
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "PublicKey", namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", required = true)
        protected org.cagrid.dorian.ifs.PublicKey publicKey;

        /**
         * Gets the value of the publicKey property.
         * 
         * @return
         *     possible object is
         *     {@link org.cagrid.dorian.ifs.PublicKey }
         *     
         */
        public org.cagrid.dorian.ifs.PublicKey getPublicKey() {
            return publicKey;
        }

        /**
         * Sets the value of the publicKey property.
         * 
         * @param value
         *     allowed object is
         *     {@link org.cagrid.dorian.ifs.PublicKey }
         *     
         */
        public void setPublicKey(org.cagrid.dorian.ifs.PublicKey value) {
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
                org.cagrid.dorian.ifs.PublicKey thePublicKey;
                thePublicKey = this.getPublicKey();
                strategy.appendField(locator, this, "publicKey", buffer, thePublicKey);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                org.cagrid.dorian.ifs.PublicKey thePublicKey;
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
            if (!(object instanceof CreateProxyRequest.PublicKey)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final CreateProxyRequest.PublicKey that = ((CreateProxyRequest.PublicKey) object);
            {
                org.cagrid.dorian.ifs.PublicKey lhsPublicKey;
                lhsPublicKey = this.getPublicKey();
                org.cagrid.dorian.ifs.PublicKey rhsPublicKey;
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
     *         &lt;element ref="{http://cagrid.nci.nih.gov/1/dorian-common}SAMLAssertion"/>
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
        "samlAssertion"
    })
    public static class Saml
        implements Serializable, Equals, HashCode, ToString
    {

        @XmlElement(name = "SAMLAssertion", namespace = "http://cagrid.nci.nih.gov/1/dorian-common", required = true)
        protected SAMLAssertion samlAssertion;

        /**
         * Gets the value of the samlAssertion property.
         * 
         * @return
         *     possible object is
         *     {@link SAMLAssertion }
         *     
         */
        public SAMLAssertion getSAMLAssertion() {
            return samlAssertion;
        }

        /**
         * Sets the value of the samlAssertion property.
         * 
         * @param value
         *     allowed object is
         *     {@link SAMLAssertion }
         *     
         */
        public void setSAMLAssertion(SAMLAssertion value) {
            this.samlAssertion = value;
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
                SAMLAssertion theSAMLAssertion;
                theSAMLAssertion = this.getSAMLAssertion();
                strategy.appendField(locator, this, "samlAssertion", buffer, theSAMLAssertion);
            }
            return buffer;
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                SAMLAssertion theSAMLAssertion;
                theSAMLAssertion = this.getSAMLAssertion();
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "samlAssertion", theSAMLAssertion), currentHashCode, theSAMLAssertion);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof CreateProxyRequest.Saml)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final CreateProxyRequest.Saml that = ((CreateProxyRequest.Saml) object);
            {
                SAMLAssertion lhsSAMLAssertion;
                lhsSAMLAssertion = this.getSAMLAssertion();
                SAMLAssertion rhsSAMLAssertion;
                rhsSAMLAssertion = that.getSAMLAssertion();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "samlAssertion", lhsSAMLAssertion), LocatorUtils.property(thatLocator, "samlAssertion", rhsSAMLAssertion), lhsSAMLAssertion, rhsSAMLAssertion)) {
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
