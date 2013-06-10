
package org.oasis.names.tc.saml.assertion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Java class for AuthenticationStatementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthenticationStatementType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SAML:1.0:assertion}SubjectStatementAbstractType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}SubjectLocality" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:1.0:assertion}AuthorityBinding" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="AuthenticationMethod" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="AuthenticationInstant" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthenticationStatementType", propOrder = {
    "subjectLocality",
    "authorityBinding"
})
public class AuthenticationStatementType
    extends SubjectStatementAbstractType
    implements Serializable, Equals, HashCode, ToString
{

    @XmlElement(name = "SubjectLocality")
    protected SubjectLocalityType subjectLocality;
    @XmlElement(name = "AuthorityBinding")
    protected List<AuthorityBindingType> authorityBinding;
    @XmlAttribute(name = "AuthenticationMethod", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String authenticationMethod;
    @XmlAttribute(name = "AuthenticationInstant", required = true)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar authenticationInstant;

    /**
     * Gets the value of the subjectLocality property.
     * 
     * @return
     *     possible object is
     *     {@link SubjectLocalityType }
     *     
     */
    public SubjectLocalityType getSubjectLocality() {
        return subjectLocality;
    }

    /**
     * Sets the value of the subjectLocality property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubjectLocalityType }
     *     
     */
    public void setSubjectLocality(SubjectLocalityType value) {
        this.subjectLocality = value;
    }

    /**
     * Gets the value of the authorityBinding property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the authorityBinding property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthorityBinding().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AuthorityBindingType }
     * 
     * 
     */
    public List<AuthorityBindingType> getAuthorityBinding() {
        if (authorityBinding == null) {
            authorityBinding = new ArrayList<AuthorityBindingType>();
        }
        return this.authorityBinding;
    }

    /**
     * Gets the value of the authenticationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    /**
     * Sets the value of the authenticationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationMethod(String value) {
        this.authenticationMethod = value;
    }

    /**
     * Gets the value of the authenticationInstant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getAuthenticationInstant() {
        return authenticationInstant;
    }

    /**
     * Sets the value of the authenticationInstant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationInstant(Calendar value) {
        this.authenticationInstant = value;
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
        super.appendFields(locator, buffer, strategy);
        {
            SubjectLocalityType theSubjectLocality;
            theSubjectLocality = this.getSubjectLocality();
            strategy.appendField(locator, this, "subjectLocality", buffer, theSubjectLocality);
        }
        {
            List<AuthorityBindingType> theAuthorityBinding;
            theAuthorityBinding = (((this.authorityBinding!= null)&&(!this.authorityBinding.isEmpty()))?this.getAuthorityBinding():null);
            strategy.appendField(locator, this, "authorityBinding", buffer, theAuthorityBinding);
        }
        {
            String theAuthenticationMethod;
            theAuthenticationMethod = this.getAuthenticationMethod();
            strategy.appendField(locator, this, "authenticationMethod", buffer, theAuthenticationMethod);
        }
        {
            Calendar theAuthenticationInstant;
            theAuthenticationInstant = this.getAuthenticationInstant();
            strategy.appendField(locator, this, "authenticationInstant", buffer, theAuthenticationInstant);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            SubjectLocalityType theSubjectLocality;
            theSubjectLocality = this.getSubjectLocality();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "subjectLocality", theSubjectLocality), currentHashCode, theSubjectLocality);
        }
        {
            List<AuthorityBindingType> theAuthorityBinding;
            theAuthorityBinding = (((this.authorityBinding!= null)&&(!this.authorityBinding.isEmpty()))?this.getAuthorityBinding():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authorityBinding", theAuthorityBinding), currentHashCode, theAuthorityBinding);
        }
        {
            String theAuthenticationMethod;
            theAuthenticationMethod = this.getAuthenticationMethod();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authenticationMethod", theAuthenticationMethod), currentHashCode, theAuthenticationMethod);
        }
        {
            Calendar theAuthenticationInstant;
            theAuthenticationInstant = this.getAuthenticationInstant();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authenticationInstant", theAuthenticationInstant), currentHashCode, theAuthenticationInstant);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AuthenticationStatementType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final AuthenticationStatementType that = ((AuthenticationStatementType) object);
        {
            SubjectLocalityType lhsSubjectLocality;
            lhsSubjectLocality = this.getSubjectLocality();
            SubjectLocalityType rhsSubjectLocality;
            rhsSubjectLocality = that.getSubjectLocality();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subjectLocality", lhsSubjectLocality), LocatorUtils.property(thatLocator, "subjectLocality", rhsSubjectLocality), lhsSubjectLocality, rhsSubjectLocality)) {
                return false;
            }
        }
        {
            List<AuthorityBindingType> lhsAuthorityBinding;
            lhsAuthorityBinding = (((this.authorityBinding!= null)&&(!this.authorityBinding.isEmpty()))?this.getAuthorityBinding():null);
            List<AuthorityBindingType> rhsAuthorityBinding;
            rhsAuthorityBinding = (((that.authorityBinding!= null)&&(!that.authorityBinding.isEmpty()))?that.getAuthorityBinding():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authorityBinding", lhsAuthorityBinding), LocatorUtils.property(thatLocator, "authorityBinding", rhsAuthorityBinding), lhsAuthorityBinding, rhsAuthorityBinding)) {
                return false;
            }
        }
        {
            String lhsAuthenticationMethod;
            lhsAuthenticationMethod = this.getAuthenticationMethod();
            String rhsAuthenticationMethod;
            rhsAuthenticationMethod = that.getAuthenticationMethod();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authenticationMethod", lhsAuthenticationMethod), LocatorUtils.property(thatLocator, "authenticationMethod", rhsAuthenticationMethod), lhsAuthenticationMethod, rhsAuthenticationMethod)) {
                return false;
            }
        }
        {
            Calendar lhsAuthenticationInstant;
            lhsAuthenticationInstant = this.getAuthenticationInstant();
            Calendar rhsAuthenticationInstant;
            rhsAuthenticationInstant = that.getAuthenticationInstant();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authenticationInstant", lhsAuthenticationInstant), LocatorUtils.property(thatLocator, "authenticationInstant", rhsAuthenticationInstant), lhsAuthenticationInstant, rhsAuthenticationInstant)) {
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
