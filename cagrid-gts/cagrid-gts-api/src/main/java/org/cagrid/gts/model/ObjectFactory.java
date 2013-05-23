
package org.cagrid.gts.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.gts.model package. 
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

    private final static QName _TrustLevel_QNAME = new QName("http://cagrid.nci.nih.gov/8/gts", "TrustLevel");
    private final static QName _TrustedAuthority_QNAME = new QName("http://cagrid.nci.nih.gov/8/gts", "TrustedAuthority");
    private final static QName _AuthorityGTS_QNAME = new QName("http://cagrid.nci.nih.gov/8/gts", "AuthorityGTS");
    private final static QName _X509Certificate_QNAME = new QName("http://cagrid.nci.nih.gov/8/gts", "X509Certificate");
    private final static QName _PermissionFilter_QNAME = new QName("http://cagrid.nci.nih.gov/8/gts", "PermissionFilter");
    private final static QName _TrustedAuthorityFilter_QNAME = new QName("http://cagrid.nci.nih.gov/8/gts", "TrustedAuthorityFilter");
    private final static QName _AuthorityPriorityUpdate_QNAME = new QName("http://cagrid.nci.nih.gov/8/gts", "AuthorityPriorityUpdate");
    private final static QName _Permission_QNAME = new QName("http://cagrid.nci.nih.gov/8/gts", "Permission");
    private final static QName _X509CRL_QNAME = new QName("http://cagrid.nci.nih.gov/8/gts", "X509CRL");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.gts.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthorityGTS }
     * 
     */
    public AuthorityGTS createAuthorityGTS() {
        return new AuthorityGTS();
    }

    /**
     * Create an instance of {@link TrustLevel }
     * 
     */
    public TrustLevel createTrustLevel() {
        return new TrustLevel();
    }

    /**
     * Create an instance of {@link AuthorityPriorityUpdate }
     * 
     */
    public AuthorityPriorityUpdate createAuthorityPriorityUpdate() {
        return new AuthorityPriorityUpdate();
    }

    /**
     * Create an instance of {@link TrustedAuthorityFilter }
     * 
     */
    public TrustedAuthorityFilter createTrustedAuthorityFilter() {
        return new TrustedAuthorityFilter();
    }

    /**
     * Create an instance of {@link X509Certificate }
     * 
     */
    public X509Certificate createX509Certificate() {
        return new X509Certificate();
    }

    /**
     * Create an instance of {@link Permission }
     * 
     */
    public Permission createPermission() {
        return new Permission();
    }

    /**
     * Create an instance of {@link TimeToLive }
     * 
     */
    public TimeToLive createTimeToLive() {
        return new TimeToLive();
    }

    /**
     * Create an instance of {@link TrustedAuthority }
     * 
     */
    public TrustedAuthority createTrustedAuthority() {
        return new TrustedAuthority();
    }

    /**
     * Create an instance of {@link AuthorityPrioritySpecification }
     * 
     */
    public AuthorityPrioritySpecification createAuthorityPrioritySpecification() {
        return new AuthorityPrioritySpecification();
    }

    /**
     * Create an instance of {@link PermissionFilter }
     * 
     */
    public PermissionFilter createPermissionFilter() {
        return new PermissionFilter();
    }

    /**
     * Create an instance of {@link TrustLevels }
     * 
     */
    public TrustLevels createTrustLevels() {
        return new TrustLevels();
    }

    /**
     * Create an instance of {@link X509CRL }
     * 
     */
    public X509CRL createX509CRL() {
        return new X509CRL();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrustLevel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/8/gts", name = "TrustLevel")
    public JAXBElement<TrustLevel> createTrustLevel(TrustLevel value) {
        return new JAXBElement<TrustLevel>(_TrustLevel_QNAME, TrustLevel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrustedAuthority }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/8/gts", name = "TrustedAuthority")
    public JAXBElement<TrustedAuthority> createTrustedAuthority(TrustedAuthority value) {
        return new JAXBElement<TrustedAuthority>(_TrustedAuthority_QNAME, TrustedAuthority.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthorityGTS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/8/gts", name = "AuthorityGTS")
    public JAXBElement<AuthorityGTS> createAuthorityGTS(AuthorityGTS value) {
        return new JAXBElement<AuthorityGTS>(_AuthorityGTS_QNAME, AuthorityGTS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link X509Certificate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/8/gts", name = "X509Certificate")
    public JAXBElement<X509Certificate> createX509Certificate(X509Certificate value) {
        return new JAXBElement<X509Certificate>(_X509Certificate_QNAME, X509Certificate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PermissionFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/8/gts", name = "PermissionFilter")
    public JAXBElement<PermissionFilter> createPermissionFilter(PermissionFilter value) {
        return new JAXBElement<PermissionFilter>(_PermissionFilter_QNAME, PermissionFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrustedAuthorityFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/8/gts", name = "TrustedAuthorityFilter")
    public JAXBElement<TrustedAuthorityFilter> createTrustedAuthorityFilter(TrustedAuthorityFilter value) {
        return new JAXBElement<TrustedAuthorityFilter>(_TrustedAuthorityFilter_QNAME, TrustedAuthorityFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthorityPriorityUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/8/gts", name = "AuthorityPriorityUpdate")
    public JAXBElement<AuthorityPriorityUpdate> createAuthorityPriorityUpdate(AuthorityPriorityUpdate value) {
        return new JAXBElement<AuthorityPriorityUpdate>(_AuthorityPriorityUpdate_QNAME, AuthorityPriorityUpdate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Permission }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/8/gts", name = "Permission")
    public JAXBElement<Permission> createPermission(Permission value) {
        return new JAXBElement<Permission>(_Permission_QNAME, Permission.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link X509CRL }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/8/gts", name = "X509CRL")
    public JAXBElement<X509CRL> createX509CRL(X509CRL value) {
        return new JAXBElement<X509CRL>(_X509CRL_QNAME, X509CRL.class, null, value);
    }

}
