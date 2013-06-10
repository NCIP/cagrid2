
package org.cagrid.dorian.ifs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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


/**
 * <p>Java class for TrustedIdP complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrustedIdP">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="displayName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}TrustedIdPStatus"/>
 *         &lt;element name="UserPolicyClass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IdPCertificate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="authenticationServiceURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authenticationServiceIdentity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AuthenticationMethod" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}SAMLAuthenticationMethod" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="UserIdAttributeDescriptor" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}SAMLAttributeDescriptor"/>
 *         &lt;element name="FirstNameAttributeDescriptor" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}SAMLAttributeDescriptor"/>
 *         &lt;element name="LastNameAttributeDescriptor" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}SAMLAttributeDescriptor"/>
 *         &lt;element name="EmailAttributeDescriptor" type="{http://cagrid.nci.nih.gov/1/dorian-ifs}SAMLAttributeDescriptor"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrustedIdP", propOrder = {
    "id",
    "name",
    "displayName",
    "status",
    "userPolicyClass",
    "idPCertificate",
    "authenticationServiceURL",
    "authenticationServiceIdentity",
    "authenticationMethod",
    "userIdAttributeDescriptor",
    "firstNameAttributeDescriptor",
    "lastNameAttributeDescriptor",
    "emailAttributeDescriptor"
})
public class TrustedIdP
    implements Serializable, Equals, HashCode, ToString
{

    protected long id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String displayName;
    @XmlElement(required = true)
    protected TrustedIdPStatus status;
    @XmlElement(name = "UserPolicyClass", required = true)
    protected String userPolicyClass;
    @XmlElement(name = "IdPCertificate", required = true)
    protected String idPCertificate;
    protected String authenticationServiceURL;
    protected String authenticationServiceIdentity;
    @XmlElement(name = "AuthenticationMethod")
    protected List<SAMLAuthenticationMethod> authenticationMethod;
    @XmlElement(name = "UserIdAttributeDescriptor", required = true)
    protected SAMLAttributeDescriptor userIdAttributeDescriptor;
    @XmlElement(name = "FirstNameAttributeDescriptor", required = true)
    protected SAMLAttributeDescriptor firstNameAttributeDescriptor;
    @XmlElement(name = "LastNameAttributeDescriptor", required = true)
    protected SAMLAttributeDescriptor lastNameAttributeDescriptor;
    @XmlElement(name = "EmailAttributeDescriptor", required = true)
    protected SAMLAttributeDescriptor emailAttributeDescriptor;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayName(String value) {
        this.displayName = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link TrustedIdPStatus }
     *     
     */
    public TrustedIdPStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrustedIdPStatus }
     *     
     */
    public void setStatus(TrustedIdPStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the userPolicyClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserPolicyClass() {
        return userPolicyClass;
    }

    /**
     * Sets the value of the userPolicyClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserPolicyClass(String value) {
        this.userPolicyClass = value;
    }

    /**
     * Gets the value of the idPCertificate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPCertificate() {
        return idPCertificate;
    }

    /**
     * Sets the value of the idPCertificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPCertificate(String value) {
        this.idPCertificate = value;
    }

    /**
     * Gets the value of the authenticationServiceURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationServiceURL() {
        return authenticationServiceURL;
    }

    /**
     * Sets the value of the authenticationServiceURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationServiceURL(String value) {
        this.authenticationServiceURL = value;
    }

    /**
     * Gets the value of the authenticationServiceIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationServiceIdentity() {
        return authenticationServiceIdentity;
    }

    /**
     * Sets the value of the authenticationServiceIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationServiceIdentity(String value) {
        this.authenticationServiceIdentity = value;
    }

    /**
     * Gets the value of the authenticationMethod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the authenticationMethod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthenticationMethod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SAMLAuthenticationMethod }
     * 
     * 
     */
    public List<SAMLAuthenticationMethod> getAuthenticationMethod() {
        if (authenticationMethod == null) {
            authenticationMethod = new ArrayList<SAMLAuthenticationMethod>();
        }
        return this.authenticationMethod;
    }

    /**
     * Gets the value of the userIdAttributeDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link SAMLAttributeDescriptor }
     *     
     */
    public SAMLAttributeDescriptor getUserIdAttributeDescriptor() {
        return userIdAttributeDescriptor;
    }

    /**
     * Sets the value of the userIdAttributeDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link SAMLAttributeDescriptor }
     *     
     */
    public void setUserIdAttributeDescriptor(SAMLAttributeDescriptor value) {
        this.userIdAttributeDescriptor = value;
    }

    /**
     * Gets the value of the firstNameAttributeDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link SAMLAttributeDescriptor }
     *     
     */
    public SAMLAttributeDescriptor getFirstNameAttributeDescriptor() {
        return firstNameAttributeDescriptor;
    }

    /**
     * Sets the value of the firstNameAttributeDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link SAMLAttributeDescriptor }
     *     
     */
    public void setFirstNameAttributeDescriptor(SAMLAttributeDescriptor value) {
        this.firstNameAttributeDescriptor = value;
    }

    /**
     * Gets the value of the lastNameAttributeDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link SAMLAttributeDescriptor }
     *     
     */
    public SAMLAttributeDescriptor getLastNameAttributeDescriptor() {
        return lastNameAttributeDescriptor;
    }

    /**
     * Sets the value of the lastNameAttributeDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link SAMLAttributeDescriptor }
     *     
     */
    public void setLastNameAttributeDescriptor(SAMLAttributeDescriptor value) {
        this.lastNameAttributeDescriptor = value;
    }

    /**
     * Gets the value of the emailAttributeDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link SAMLAttributeDescriptor }
     *     
     */
    public SAMLAttributeDescriptor getEmailAttributeDescriptor() {
        return emailAttributeDescriptor;
    }

    /**
     * Sets the value of the emailAttributeDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link SAMLAttributeDescriptor }
     *     
     */
    public void setEmailAttributeDescriptor(SAMLAttributeDescriptor value) {
        this.emailAttributeDescriptor = value;
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
            long theId;
            theId = (true?this.getId(): 0L);
            strategy.appendField(locator, this, "id", buffer, theId);
        }
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            String theDisplayName;
            theDisplayName = this.getDisplayName();
            strategy.appendField(locator, this, "displayName", buffer, theDisplayName);
        }
        {
            TrustedIdPStatus theStatus;
            theStatus = this.getStatus();
            strategy.appendField(locator, this, "status", buffer, theStatus);
        }
        {
            String theUserPolicyClass;
            theUserPolicyClass = this.getUserPolicyClass();
            strategy.appendField(locator, this, "userPolicyClass", buffer, theUserPolicyClass);
        }
        {
            String theIdPCertificate;
            theIdPCertificate = this.getIdPCertificate();
            strategy.appendField(locator, this, "idPCertificate", buffer, theIdPCertificate);
        }
        {
            String theAuthenticationServiceURL;
            theAuthenticationServiceURL = this.getAuthenticationServiceURL();
            strategy.appendField(locator, this, "authenticationServiceURL", buffer, theAuthenticationServiceURL);
        }
        {
            String theAuthenticationServiceIdentity;
            theAuthenticationServiceIdentity = this.getAuthenticationServiceIdentity();
            strategy.appendField(locator, this, "authenticationServiceIdentity", buffer, theAuthenticationServiceIdentity);
        }
        {
            List<SAMLAuthenticationMethod> theAuthenticationMethod;
            theAuthenticationMethod = (((this.authenticationMethod!= null)&&(!this.authenticationMethod.isEmpty()))?this.getAuthenticationMethod():null);
            strategy.appendField(locator, this, "authenticationMethod", buffer, theAuthenticationMethod);
        }
        {
            SAMLAttributeDescriptor theUserIdAttributeDescriptor;
            theUserIdAttributeDescriptor = this.getUserIdAttributeDescriptor();
            strategy.appendField(locator, this, "userIdAttributeDescriptor", buffer, theUserIdAttributeDescriptor);
        }
        {
            SAMLAttributeDescriptor theFirstNameAttributeDescriptor;
            theFirstNameAttributeDescriptor = this.getFirstNameAttributeDescriptor();
            strategy.appendField(locator, this, "firstNameAttributeDescriptor", buffer, theFirstNameAttributeDescriptor);
        }
        {
            SAMLAttributeDescriptor theLastNameAttributeDescriptor;
            theLastNameAttributeDescriptor = this.getLastNameAttributeDescriptor();
            strategy.appendField(locator, this, "lastNameAttributeDescriptor", buffer, theLastNameAttributeDescriptor);
        }
        {
            SAMLAttributeDescriptor theEmailAttributeDescriptor;
            theEmailAttributeDescriptor = this.getEmailAttributeDescriptor();
            strategy.appendField(locator, this, "emailAttributeDescriptor", buffer, theEmailAttributeDescriptor);
        }
        return buffer;
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            long theId;
            theId = (true?this.getId(): 0L);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "id", theId), currentHashCode, theId);
        }
        {
            String theName;
            theName = this.getName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName);
        }
        {
            String theDisplayName;
            theDisplayName = this.getDisplayName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "displayName", theDisplayName), currentHashCode, theDisplayName);
        }
        {
            TrustedIdPStatus theStatus;
            theStatus = this.getStatus();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "status", theStatus), currentHashCode, theStatus);
        }
        {
            String theUserPolicyClass;
            theUserPolicyClass = this.getUserPolicyClass();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "userPolicyClass", theUserPolicyClass), currentHashCode, theUserPolicyClass);
        }
        {
            String theIdPCertificate;
            theIdPCertificate = this.getIdPCertificate();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "idPCertificate", theIdPCertificate), currentHashCode, theIdPCertificate);
        }
        {
            String theAuthenticationServiceURL;
            theAuthenticationServiceURL = this.getAuthenticationServiceURL();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authenticationServiceURL", theAuthenticationServiceURL), currentHashCode, theAuthenticationServiceURL);
        }
        {
            String theAuthenticationServiceIdentity;
            theAuthenticationServiceIdentity = this.getAuthenticationServiceIdentity();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authenticationServiceIdentity", theAuthenticationServiceIdentity), currentHashCode, theAuthenticationServiceIdentity);
        }
        {
            List<SAMLAuthenticationMethod> theAuthenticationMethod;
            theAuthenticationMethod = (((this.authenticationMethod!= null)&&(!this.authenticationMethod.isEmpty()))?this.getAuthenticationMethod():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "authenticationMethod", theAuthenticationMethod), currentHashCode, theAuthenticationMethod);
        }
        {
            SAMLAttributeDescriptor theUserIdAttributeDescriptor;
            theUserIdAttributeDescriptor = this.getUserIdAttributeDescriptor();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "userIdAttributeDescriptor", theUserIdAttributeDescriptor), currentHashCode, theUserIdAttributeDescriptor);
        }
        {
            SAMLAttributeDescriptor theFirstNameAttributeDescriptor;
            theFirstNameAttributeDescriptor = this.getFirstNameAttributeDescriptor();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "firstNameAttributeDescriptor", theFirstNameAttributeDescriptor), currentHashCode, theFirstNameAttributeDescriptor);
        }
        {
            SAMLAttributeDescriptor theLastNameAttributeDescriptor;
            theLastNameAttributeDescriptor = this.getLastNameAttributeDescriptor();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastNameAttributeDescriptor", theLastNameAttributeDescriptor), currentHashCode, theLastNameAttributeDescriptor);
        }
        {
            SAMLAttributeDescriptor theEmailAttributeDescriptor;
            theEmailAttributeDescriptor = this.getEmailAttributeDescriptor();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "emailAttributeDescriptor", theEmailAttributeDescriptor), currentHashCode, theEmailAttributeDescriptor);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TrustedIdP)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TrustedIdP that = ((TrustedIdP) object);
        {
            long lhsId;
            lhsId = (true?this.getId(): 0L);
            long rhsId;
            rhsId = (true?that.getId(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "id", lhsId), LocatorUtils.property(thatLocator, "id", rhsId), lhsId, rhsId)) {
                return false;
            }
        }
        {
            String lhsName;
            lhsName = this.getName();
            String rhsName;
            rhsName = that.getName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName)) {
                return false;
            }
        }
        {
            String lhsDisplayName;
            lhsDisplayName = this.getDisplayName();
            String rhsDisplayName;
            rhsDisplayName = that.getDisplayName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "displayName", lhsDisplayName), LocatorUtils.property(thatLocator, "displayName", rhsDisplayName), lhsDisplayName, rhsDisplayName)) {
                return false;
            }
        }
        {
            TrustedIdPStatus lhsStatus;
            lhsStatus = this.getStatus();
            TrustedIdPStatus rhsStatus;
            rhsStatus = that.getStatus();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "status", lhsStatus), LocatorUtils.property(thatLocator, "status", rhsStatus), lhsStatus, rhsStatus)) {
                return false;
            }
        }
        {
            String lhsUserPolicyClass;
            lhsUserPolicyClass = this.getUserPolicyClass();
            String rhsUserPolicyClass;
            rhsUserPolicyClass = that.getUserPolicyClass();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "userPolicyClass", lhsUserPolicyClass), LocatorUtils.property(thatLocator, "userPolicyClass", rhsUserPolicyClass), lhsUserPolicyClass, rhsUserPolicyClass)) {
                return false;
            }
        }
        {
            String lhsIdPCertificate;
            lhsIdPCertificate = this.getIdPCertificate();
            String rhsIdPCertificate;
            rhsIdPCertificate = that.getIdPCertificate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "idPCertificate", lhsIdPCertificate), LocatorUtils.property(thatLocator, "idPCertificate", rhsIdPCertificate), lhsIdPCertificate, rhsIdPCertificate)) {
                return false;
            }
        }
        {
            String lhsAuthenticationServiceURL;
            lhsAuthenticationServiceURL = this.getAuthenticationServiceURL();
            String rhsAuthenticationServiceURL;
            rhsAuthenticationServiceURL = that.getAuthenticationServiceURL();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authenticationServiceURL", lhsAuthenticationServiceURL), LocatorUtils.property(thatLocator, "authenticationServiceURL", rhsAuthenticationServiceURL), lhsAuthenticationServiceURL, rhsAuthenticationServiceURL)) {
                return false;
            }
        }
        {
            String lhsAuthenticationServiceIdentity;
            lhsAuthenticationServiceIdentity = this.getAuthenticationServiceIdentity();
            String rhsAuthenticationServiceIdentity;
            rhsAuthenticationServiceIdentity = that.getAuthenticationServiceIdentity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authenticationServiceIdentity", lhsAuthenticationServiceIdentity), LocatorUtils.property(thatLocator, "authenticationServiceIdentity", rhsAuthenticationServiceIdentity), lhsAuthenticationServiceIdentity, rhsAuthenticationServiceIdentity)) {
                return false;
            }
        }
        {
            List<SAMLAuthenticationMethod> lhsAuthenticationMethod;
            lhsAuthenticationMethod = (((this.authenticationMethod!= null)&&(!this.authenticationMethod.isEmpty()))?this.getAuthenticationMethod():null);
            List<SAMLAuthenticationMethod> rhsAuthenticationMethod;
            rhsAuthenticationMethod = (((that.authenticationMethod!= null)&&(!that.authenticationMethod.isEmpty()))?that.getAuthenticationMethod():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authenticationMethod", lhsAuthenticationMethod), LocatorUtils.property(thatLocator, "authenticationMethod", rhsAuthenticationMethod), lhsAuthenticationMethod, rhsAuthenticationMethod)) {
                return false;
            }
        }
        {
            SAMLAttributeDescriptor lhsUserIdAttributeDescriptor;
            lhsUserIdAttributeDescriptor = this.getUserIdAttributeDescriptor();
            SAMLAttributeDescriptor rhsUserIdAttributeDescriptor;
            rhsUserIdAttributeDescriptor = that.getUserIdAttributeDescriptor();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "userIdAttributeDescriptor", lhsUserIdAttributeDescriptor), LocatorUtils.property(thatLocator, "userIdAttributeDescriptor", rhsUserIdAttributeDescriptor), lhsUserIdAttributeDescriptor, rhsUserIdAttributeDescriptor)) {
                return false;
            }
        }
        {
            SAMLAttributeDescriptor lhsFirstNameAttributeDescriptor;
            lhsFirstNameAttributeDescriptor = this.getFirstNameAttributeDescriptor();
            SAMLAttributeDescriptor rhsFirstNameAttributeDescriptor;
            rhsFirstNameAttributeDescriptor = that.getFirstNameAttributeDescriptor();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "firstNameAttributeDescriptor", lhsFirstNameAttributeDescriptor), LocatorUtils.property(thatLocator, "firstNameAttributeDescriptor", rhsFirstNameAttributeDescriptor), lhsFirstNameAttributeDescriptor, rhsFirstNameAttributeDescriptor)) {
                return false;
            }
        }
        {
            SAMLAttributeDescriptor lhsLastNameAttributeDescriptor;
            lhsLastNameAttributeDescriptor = this.getLastNameAttributeDescriptor();
            SAMLAttributeDescriptor rhsLastNameAttributeDescriptor;
            rhsLastNameAttributeDescriptor = that.getLastNameAttributeDescriptor();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lastNameAttributeDescriptor", lhsLastNameAttributeDescriptor), LocatorUtils.property(thatLocator, "lastNameAttributeDescriptor", rhsLastNameAttributeDescriptor), lhsLastNameAttributeDescriptor, rhsLastNameAttributeDescriptor)) {
                return false;
            }
        }
        {
            SAMLAttributeDescriptor lhsEmailAttributeDescriptor;
            lhsEmailAttributeDescriptor = this.getEmailAttributeDescriptor();
            SAMLAttributeDescriptor rhsEmailAttributeDescriptor;
            rhsEmailAttributeDescriptor = that.getEmailAttributeDescriptor();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "emailAttributeDescriptor", lhsEmailAttributeDescriptor), LocatorUtils.property(thatLocator, "emailAttributeDescriptor", rhsEmailAttributeDescriptor), lhsEmailAttributeDescriptor, rhsEmailAttributeDescriptor)) {
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
