
package org.cagrid.dorian.idp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.dorian.idp package. 
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

    private final static QName _ApplicationReview_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-idp", "ApplicationReview");
    private final static QName _BasicAuthCredential_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-idp", "BasicAuthCredential");
    private final static QName _AccountProfile_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-idp", "AccountProfile");
    private final static QName _IdentityProviderAudit_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-idp", "IdentityProviderAudit");
    private final static QName _LocalUserFilter_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-idp", "LocalUserFilter");
    private final static QName _Application_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-idp", "Application");
    private final static QName _LocalUser_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-idp", "LocalUser");
    private final static QName _IdentityProviderAuditFilter_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-idp", "IdentityProviderAuditFilter");
    private final static QName _IdentityProviderAuditRecord_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-idp", "IdentityProviderAuditRecord");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.dorian.idp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ApplicationReview }
     * 
     */
    public ApplicationReview createApplicationReview() {
        return new ApplicationReview();
    }

    /**
     * Create an instance of {@link Application }
     * 
     */
    public Application createApplication() {
        return new Application();
    }

    /**
     * Create an instance of {@link LocalUserFilter }
     * 
     */
    public LocalUserFilter createLocalUserFilter() {
        return new LocalUserFilter();
    }

    /**
     * Create an instance of {@link LocalUser }
     * 
     */
    public LocalUser createLocalUser() {
        return new LocalUser();
    }

    /**
     * Create an instance of {@link IdentityProviderAuditRecord }
     * 
     */
    public IdentityProviderAuditRecord createIdentityProviderAuditRecord() {
        return new IdentityProviderAuditRecord();
    }

    /**
     * Create an instance of {@link AccountProfile }
     * 
     */
    public AccountProfile createAccountProfile() {
        return new AccountProfile();
    }

    /**
     * Create an instance of {@link IdentityProviderAuditFilter }
     * 
     */
    public IdentityProviderAuditFilter createIdentityProviderAuditFilter() {
        return new IdentityProviderAuditFilter();
    }

    /**
     * Create an instance of {@link BasicAuthCredential }
     * 
     */
    public BasicAuthCredential createBasicAuthCredential() {
        return new BasicAuthCredential();
    }

    /**
     * Create an instance of {@link PasswordSecurity }
     * 
     */
    public PasswordSecurity createPasswordSecurity() {
        return new PasswordSecurity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApplicationReview }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", name = "ApplicationReview")
    public JAXBElement<ApplicationReview> createApplicationReview(ApplicationReview value) {
        return new JAXBElement<ApplicationReview>(_ApplicationReview_QNAME, ApplicationReview.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BasicAuthCredential }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", name = "BasicAuthCredential")
    public JAXBElement<BasicAuthCredential> createBasicAuthCredential(BasicAuthCredential value) {
        return new JAXBElement<BasicAuthCredential>(_BasicAuthCredential_QNAME, BasicAuthCredential.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccountProfile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", name = "AccountProfile")
    public JAXBElement<AccountProfile> createAccountProfile(AccountProfile value) {
        return new JAXBElement<AccountProfile>(_AccountProfile_QNAME, AccountProfile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdentityProviderAudit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", name = "IdentityProviderAudit")
    public JAXBElement<IdentityProviderAudit> createIdentityProviderAudit(IdentityProviderAudit value) {
        return new JAXBElement<IdentityProviderAudit>(_IdentityProviderAudit_QNAME, IdentityProviderAudit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocalUserFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", name = "LocalUserFilter")
    public JAXBElement<LocalUserFilter> createLocalUserFilter(LocalUserFilter value) {
        return new JAXBElement<LocalUserFilter>(_LocalUserFilter_QNAME, LocalUserFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Application }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", name = "Application")
    public JAXBElement<Application> createApplication(Application value) {
        return new JAXBElement<Application>(_Application_QNAME, Application.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocalUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", name = "LocalUser")
    public JAXBElement<LocalUser> createLocalUser(LocalUser value) {
        return new JAXBElement<LocalUser>(_LocalUser_QNAME, LocalUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdentityProviderAuditFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", name = "IdentityProviderAuditFilter")
    public JAXBElement<IdentityProviderAuditFilter> createIdentityProviderAuditFilter(IdentityProviderAuditFilter value) {
        return new JAXBElement<IdentityProviderAuditFilter>(_IdentityProviderAuditFilter_QNAME, IdentityProviderAuditFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IdentityProviderAuditRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-idp", name = "IdentityProviderAuditRecord")
    public JAXBElement<IdentityProviderAuditRecord> createIdentityProviderAuditRecord(IdentityProviderAuditRecord value) {
        return new JAXBElement<IdentityProviderAuditRecord>(_IdentityProviderAuditRecord_QNAME, IdentityProviderAuditRecord.class, null, value);
    }

}
