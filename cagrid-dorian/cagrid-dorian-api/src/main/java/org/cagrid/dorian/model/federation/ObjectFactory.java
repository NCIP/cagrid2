
package org.cagrid.dorian.model.federation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.dorian.ifs package. 
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

    private final static QName _FederationAuditFilter_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "FederationAuditFilter");
    private final static QName _ProxyLifetime_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "ProxyLifetime");
    private final static QName _UserCertificateFilter_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "UserCertificateFilter");
    private final static QName _FederationAuditRecord_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "FederationAuditRecord");
    private final static QName _GridUserRecord_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "GridUserRecord");
    private final static QName _HostCertificateRequest_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "HostCertificateRequest");
    private final static QName _GridUserPolicy_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "GridUserPolicy");
    private final static QName _TrustedIdP_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "TrustedIdP");
    private final static QName _GridUserFilter_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "GridUserFilter");
    private final static QName _GridUserSearchCriteria_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "GridUserSearchCriteria");
    private final static QName _TrustedIdentityProviders_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "TrustedIdentityProviders");
    private final static QName _UserCertificateUpdate_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "UserCertificateUpdate");
    private final static QName _CertificateLifetime_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "CertificateLifetime");
    private final static QName _HostCertificateUpdate_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "HostCertificateUpdate");
    private final static QName _PublicKey_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "PublicKey");
    private final static QName _HostCertificateFilter_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "HostCertificateFilter");
    private final static QName _DelegationPathLength_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "DelegationPathLength");
    private final static QName _UserCertificateRecord_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "UserCertificateRecord");
    private final static QName _FederationAudit_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "FederationAudit");
    private final static QName _HostRecord_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "HostRecord");
    private final static QName _HostCertificateRecord_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "HostCertificateRecord");
    private final static QName _DateRange_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "DateRange");
    private final static QName _GridUser_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "GridUser");
    private final static QName _TrustedIdentityProvider_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "TrustedIdentityProvider");
    private final static QName _HostSearchCriteria_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-ifs", "HostSearchCriteria");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.dorian.ifs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FederationAuditFilter }
     * 
     */
    public FederationAuditFilter createFederationAuditFilter() {
        return new FederationAuditFilter();
    }

    /**
     * Create an instance of {@link PublicKey }
     * 
     */
    public PublicKey createPublicKey() {
        return new PublicKey();
    }

    /**
     * Create an instance of {@link TrustedIdentityProvider }
     * 
     */
    public TrustedIdentityProvider createTrustedIdentityProvider() {
        return new TrustedIdentityProvider();
    }

    /**
     * Create an instance of {@link CertificateLifetime }
     * 
     */
    public CertificateLifetime createCertificateLifetime() {
        return new CertificateLifetime();
    }

    /**
     * Create an instance of {@link HostSearchCriteria }
     * 
     */
    public HostSearchCriteria createHostSearchCriteria() {
        return new HostSearchCriteria();
    }

    /**
     * Create an instance of {@link HostCertificateFilter }
     * 
     */
    public HostCertificateFilter createHostCertificateFilter() {
        return new HostCertificateFilter();
    }

    /**
     * Create an instance of {@link UserCertificateUpdate }
     * 
     */
    public UserCertificateUpdate createUserCertificateUpdate() {
        return new UserCertificateUpdate();
    }

    /**
     * Create an instance of {@link GridUserSearchCriteria }
     * 
     */
    public GridUserSearchCriteria createGridUserSearchCriteria() {
        return new GridUserSearchCriteria();
    }

    /**
     * Create an instance of {@link HostCertificateUpdate }
     * 
     */
    public HostCertificateUpdate createHostCertificateUpdate() {
        return new HostCertificateUpdate();
    }

    /**
     * Create an instance of {@link ProxyLifetime }
     * 
     */
    public ProxyLifetime createProxyLifetime() {
        return new ProxyLifetime();
    }

    /**
     * Create an instance of {@link DelegationPathLength }
     * 
     */
    public DelegationPathLength createDelegationPathLength() {
        return new DelegationPathLength();
    }

    /**
     * Create an instance of {@link HostCertificateRecord }
     * 
     */
    public HostCertificateRecord createHostCertificateRecord() {
        return new HostCertificateRecord();
    }

    /**
     * Create an instance of {@link GridUserRecord }
     * 
     */
    public GridUserRecord createGridUserRecord() {
        return new GridUserRecord();
    }

    /**
     * Create an instance of {@link TrustedIdP }
     * 
     */
    public TrustedIdP createTrustedIdP() {
        return new TrustedIdP();
    }

    /**
     * Create an instance of {@link HostRecord }
     * 
     */
    public HostRecord createHostRecord() {
        return new HostRecord();
    }

    /**
     * Create an instance of {@link GridUserFilter }
     * 
     */
    public GridUserFilter createGridUserFilter() {
        return new GridUserFilter();
    }

    /**
     * Create an instance of {@link GridUserPolicy }
     * 
     */
    public GridUserPolicy createGridUserPolicy() {
        return new GridUserPolicy();
    }

    /**
     * Create an instance of {@link SAMLAttributeDescriptor }
     * 
     */
    public SAMLAttributeDescriptor createSAMLAttributeDescriptor() {
        return new SAMLAttributeDescriptor();
    }

    /**
     * Create an instance of {@link GridUser }
     * 
     */
    public GridUser createGridUser() {
        return new GridUser();
    }

    /**
     * Create an instance of {@link FederationAuditRecord }
     * 
     */
    public FederationAuditRecord createFederationAuditRecord() {
        return new FederationAuditRecord();
    }

    /**
     * Create an instance of {@link TrustedIdentityProviders }
     * 
     */
    public TrustedIdentityProviders createTrustedIdentityProviders() {
        return new TrustedIdentityProviders();
    }

    /**
     * Create an instance of {@link UserCertificateRecord }
     * 
     */
    public UserCertificateRecord createUserCertificateRecord() {
        return new UserCertificateRecord();
    }

    /**
     * Create an instance of {@link UserCertificateFilter }
     * 
     */
    public UserCertificateFilter createUserCertificateFilter() {
        return new UserCertificateFilter();
    }

    /**
     * Create an instance of {@link HostCertificateRequest }
     * 
     */
    public HostCertificateRequest createHostCertificateRequest() {
        return new HostCertificateRequest();
    }

    /**
     * Create an instance of {@link DateRange }
     * 
     */
    public DateRange createDateRange() {
        return new DateRange();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FederationAuditFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "FederationAuditFilter")
    public JAXBElement<FederationAuditFilter> createFederationAuditFilter(FederationAuditFilter value) {
        return new JAXBElement<FederationAuditFilter>(_FederationAuditFilter_QNAME, FederationAuditFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProxyLifetime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "ProxyLifetime")
    public JAXBElement<ProxyLifetime> createProxyLifetime(ProxyLifetime value) {
        return new JAXBElement<ProxyLifetime>(_ProxyLifetime_QNAME, ProxyLifetime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserCertificateFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "UserCertificateFilter")
    public JAXBElement<UserCertificateFilter> createUserCertificateFilter(UserCertificateFilter value) {
        return new JAXBElement<UserCertificateFilter>(_UserCertificateFilter_QNAME, UserCertificateFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FederationAuditRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "FederationAuditRecord")
    public JAXBElement<FederationAuditRecord> createFederationAuditRecord(FederationAuditRecord value) {
        return new JAXBElement<FederationAuditRecord>(_FederationAuditRecord_QNAME, FederationAuditRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GridUserRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "GridUserRecord")
    public JAXBElement<GridUserRecord> createGridUserRecord(GridUserRecord value) {
        return new JAXBElement<GridUserRecord>(_GridUserRecord_QNAME, GridUserRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HostCertificateRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "HostCertificateRequest")
    public JAXBElement<HostCertificateRequest> createHostCertificateRequest(HostCertificateRequest value) {
        return new JAXBElement<HostCertificateRequest>(_HostCertificateRequest_QNAME, HostCertificateRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GridUserPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "GridUserPolicy")
    public JAXBElement<GridUserPolicy> createGridUserPolicy(GridUserPolicy value) {
        return new JAXBElement<GridUserPolicy>(_GridUserPolicy_QNAME, GridUserPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrustedIdP }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "TrustedIdP")
    public JAXBElement<TrustedIdP> createTrustedIdP(TrustedIdP value) {
        return new JAXBElement<TrustedIdP>(_TrustedIdP_QNAME, TrustedIdP.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GridUserFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "GridUserFilter")
    public JAXBElement<GridUserFilter> createGridUserFilter(GridUserFilter value) {
        return new JAXBElement<GridUserFilter>(_GridUserFilter_QNAME, GridUserFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GridUserSearchCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "GridUserSearchCriteria")
    public JAXBElement<GridUserSearchCriteria> createGridUserSearchCriteria(GridUserSearchCriteria value) {
        return new JAXBElement<GridUserSearchCriteria>(_GridUserSearchCriteria_QNAME, GridUserSearchCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrustedIdentityProviders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "TrustedIdentityProviders")
    public JAXBElement<TrustedIdentityProviders> createTrustedIdentityProviders(TrustedIdentityProviders value) {
        return new JAXBElement<TrustedIdentityProviders>(_TrustedIdentityProviders_QNAME, TrustedIdentityProviders.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserCertificateUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "UserCertificateUpdate")
    public JAXBElement<UserCertificateUpdate> createUserCertificateUpdate(UserCertificateUpdate value) {
        return new JAXBElement<UserCertificateUpdate>(_UserCertificateUpdate_QNAME, UserCertificateUpdate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CertificateLifetime }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "CertificateLifetime")
    public JAXBElement<CertificateLifetime> createCertificateLifetime(CertificateLifetime value) {
        return new JAXBElement<CertificateLifetime>(_CertificateLifetime_QNAME, CertificateLifetime.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HostCertificateUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "HostCertificateUpdate")
    public JAXBElement<HostCertificateUpdate> createHostCertificateUpdate(HostCertificateUpdate value) {
        return new JAXBElement<HostCertificateUpdate>(_HostCertificateUpdate_QNAME, HostCertificateUpdate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PublicKey }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "PublicKey")
    public JAXBElement<PublicKey> createPublicKey(PublicKey value) {
        return new JAXBElement<PublicKey>(_PublicKey_QNAME, PublicKey.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HostCertificateFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "HostCertificateFilter")
    public JAXBElement<HostCertificateFilter> createHostCertificateFilter(HostCertificateFilter value) {
        return new JAXBElement<HostCertificateFilter>(_HostCertificateFilter_QNAME, HostCertificateFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DelegationPathLength }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "DelegationPathLength")
    public JAXBElement<DelegationPathLength> createDelegationPathLength(DelegationPathLength value) {
        return new JAXBElement<DelegationPathLength>(_DelegationPathLength_QNAME, DelegationPathLength.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserCertificateRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "UserCertificateRecord")
    public JAXBElement<UserCertificateRecord> createUserCertificateRecord(UserCertificateRecord value) {
        return new JAXBElement<UserCertificateRecord>(_UserCertificateRecord_QNAME, UserCertificateRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FederationAudit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "FederationAudit")
    public JAXBElement<FederationAudit> createFederationAudit(FederationAudit value) {
        return new JAXBElement<FederationAudit>(_FederationAudit_QNAME, FederationAudit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HostRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "HostRecord")
    public JAXBElement<HostRecord> createHostRecord(HostRecord value) {
        return new JAXBElement<HostRecord>(_HostRecord_QNAME, HostRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HostCertificateRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "HostCertificateRecord")
    public JAXBElement<HostCertificateRecord> createHostCertificateRecord(HostCertificateRecord value) {
        return new JAXBElement<HostCertificateRecord>(_HostCertificateRecord_QNAME, HostCertificateRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DateRange }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "DateRange")
    public JAXBElement<DateRange> createDateRange(DateRange value) {
        return new JAXBElement<DateRange>(_DateRange_QNAME, DateRange.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GridUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "GridUser")
    public JAXBElement<GridUser> createGridUser(GridUser value) {
        return new JAXBElement<GridUser>(_GridUser_QNAME, GridUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrustedIdentityProvider }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "TrustedIdentityProvider")
    public JAXBElement<TrustedIdentityProvider> createTrustedIdentityProvider(TrustedIdentityProvider value) {
        return new JAXBElement<TrustedIdentityProvider>(_TrustedIdentityProvider_QNAME, TrustedIdentityProvider.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HostSearchCriteria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-ifs", name = "HostSearchCriteria")
    public JAXBElement<HostSearchCriteria> createHostSearchCriteria(HostSearchCriteria value) {
        return new JAXBElement<HostSearchCriteria>(_HostSearchCriteria_QNAME, HostSearchCriteria.class, null, value);
    }

}
