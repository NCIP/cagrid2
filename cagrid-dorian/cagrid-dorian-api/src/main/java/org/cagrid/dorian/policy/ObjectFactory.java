
package org.cagrid.dorian.policy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.dorian.policy package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SearchPolicyType_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "SearchPolicyType");
    private final static QName _HostCertificateRenewalPolicy_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "HostCertificateRenewalPolicy");
    private final static QName _FederationPolicy_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "FederationPolicy");
    private final static QName _HostCertificateLifetime_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "HostCertificateLifetime");
    private final static QName _DorianPolicy_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "DorianPolicy");
    private final static QName _PasswordPolicy_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "PasswordPolicy");
    private final static QName _HostPolicy_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "HostPolicy");
    private final static QName _SearchPolicy_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "SearchPolicy");
    private final static QName _IdentityProviderPolicy_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "IdentityProviderPolicy");
    private final static QName _UserIdPolicy_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "UserIdPolicy");
    private final static QName _UserCertificateLifetime_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "UserCertificateLifetime");
    private final static QName _PasswordLockoutPolicy_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "PasswordLockoutPolicy");
    private final static QName _UserPolicy_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "UserPolicy");
    private final static QName _AccountInformationModificationPolicy_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-policy", "AccountInformationModificationPolicy");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.dorian.policy
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PasswordLockoutPolicy }
     * 
     */
    public PasswordLockoutPolicy createPasswordLockoutPolicy() {
        return new PasswordLockoutPolicy();
    }

    /**
     * Create an instance of {@link DorianPolicy }
     * 
     */
    public DorianPolicy createDorianPolicy() {
        return new DorianPolicy();
    }

    /**
     * Create an instance of {@link IdentityProviderPolicy }
     * 
     */
    public IdentityProviderPolicy createIdentityProviderPolicy() {
        return new IdentityProviderPolicy();
    }

    /**
     * Create an instance of {@link PasswordPolicy }
     * 
     */
    public PasswordPolicy createPasswordPolicy() {
        return new PasswordPolicy();
    }

    /**
     * Create an instance of {@link FederationPolicy }
     * 
     */
    public FederationPolicy createFederationPolicy() {
        return new FederationPolicy();
    }

    /**
     * Create an instance of {@link SearchPolicy }
     * 
     */
    public SearchPolicy createSearchPolicy() {
        return new SearchPolicy();
    }

    /**
     * Create an instance of {@link UserCertificateLifetime }
     * 
     */
    public UserCertificateLifetime createUserCertificateLifetime() {
        return new UserCertificateLifetime();
    }

    /**
     * Create an instance of {@link HostPolicy }
     * 
     */
    public HostPolicy createHostPolicy() {
        return new HostPolicy();
    }

    /**
     * Create an instance of {@link HostCertificateLifetime }
     * 
     */
    public HostCertificateLifetime createHostCertificateLifetime() {
        return new HostCertificateLifetime();
    }

    /**
     * Create an instance of {@link UserIdPolicy }
     * 
     */
    public UserIdPolicy createUserIdPolicy() {
        return new UserIdPolicy();
    }

    /**
     * Create an instance of {@link UserPolicy }
     * 
     */
    public UserPolicy createUserPolicy() {
        return new UserPolicy();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchPolicyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "SearchPolicyType")
    public JAXBElement<SearchPolicyType> createSearchPolicyType(SearchPolicyType value) {
        return new JAXBElement<SearchPolicyType>(_SearchPolicyType_QNAME, SearchPolicyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HostCertificateRenewalPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "HostCertificateRenewalPolicy")
    public JAXBElement<HostCertificateRenewalPolicy> createHostCertificateRenewalPolicy(HostCertificateRenewalPolicy value) {
        return new JAXBElement<HostCertificateRenewalPolicy>(_HostCertificateRenewalPolicy_QNAME, HostCertificateRenewalPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FederationPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "FederationPolicy")
    public JAXBElement<FederationPolicy> createFederationPolicy(FederationPolicy value) {
        return new JAXBElement<FederationPolicy>(_FederationPolicy_QNAME, FederationPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HostCertificateLifetime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "HostCertificateLifetime")
    public JAXBElement<HostCertificateLifetime> createHostCertificateLifetime(HostCertificateLifetime value) {
        return new JAXBElement<HostCertificateLifetime>(_HostCertificateLifetime_QNAME, HostCertificateLifetime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DorianPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "DorianPolicy")
    public JAXBElement<DorianPolicy> createDorianPolicy(DorianPolicy value) {
        return new JAXBElement<DorianPolicy>(_DorianPolicy_QNAME, DorianPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PasswordPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "PasswordPolicy")
    public JAXBElement<PasswordPolicy> createPasswordPolicy(PasswordPolicy value) {
        return new JAXBElement<PasswordPolicy>(_PasswordPolicy_QNAME, PasswordPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HostPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "HostPolicy")
    public JAXBElement<HostPolicy> createHostPolicy(HostPolicy value) {
        return new JAXBElement<HostPolicy>(_HostPolicy_QNAME, HostPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "SearchPolicy")
    public JAXBElement<SearchPolicy> createSearchPolicy(SearchPolicy value) {
        return new JAXBElement<SearchPolicy>(_SearchPolicy_QNAME, SearchPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdentityProviderPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "IdentityProviderPolicy")
    public JAXBElement<IdentityProviderPolicy> createIdentityProviderPolicy(IdentityProviderPolicy value) {
        return new JAXBElement<IdentityProviderPolicy>(_IdentityProviderPolicy_QNAME, IdentityProviderPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserIdPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "UserIdPolicy")
    public JAXBElement<UserIdPolicy> createUserIdPolicy(UserIdPolicy value) {
        return new JAXBElement<UserIdPolicy>(_UserIdPolicy_QNAME, UserIdPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserCertificateLifetime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "UserCertificateLifetime")
    public JAXBElement<UserCertificateLifetime> createUserCertificateLifetime(UserCertificateLifetime value) {
        return new JAXBElement<UserCertificateLifetime>(_UserCertificateLifetime_QNAME, UserCertificateLifetime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PasswordLockoutPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "PasswordLockoutPolicy")
    public JAXBElement<PasswordLockoutPolicy> createPasswordLockoutPolicy(PasswordLockoutPolicy value) {
        return new JAXBElement<PasswordLockoutPolicy>(_PasswordLockoutPolicy_QNAME, PasswordLockoutPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "UserPolicy")
    public JAXBElement<UserPolicy> createUserPolicy(UserPolicy value) {
        return new JAXBElement<UserPolicy>(_UserPolicy_QNAME, UserPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountInformationModificationPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-policy", name = "AccountInformationModificationPolicy")
    public JAXBElement<AccountInformationModificationPolicy> createAccountInformationModificationPolicy(AccountInformationModificationPolicy value) {
        return new JAXBElement<AccountInformationModificationPolicy>(_AccountInformationModificationPolicy_QNAME, AccountInformationModificationPolicy.class, null, value);
    }

}
