
package org.cagrid.dorian.ifs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
    implements Serializable
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

}
