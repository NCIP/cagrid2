
package org.cagrid.cds.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.cds.model package. 
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

    private final static QName _DelegationPolicy_QNAME = new QName("http://gaards.cagrid.org/cds", "DelegationPolicy");
    private final static QName _X509Certificate_QNAME = new QName("http://gaards.cagrid.org/cds", "X509Certificate");
    private final static QName _DelegationSigningRequest_QNAME = new QName("http://gaards.cagrid.org/cds", "DelegationSigningRequest");
    private final static QName _IdentityDelegationPolicy_QNAME = new QName("http://gaards.cagrid.org/cds", "IdentityDelegationPolicy");
    private final static QName _DelegationRequest_QNAME = new QName("http://gaards.cagrid.org/cds", "DelegationRequest");
    private final static QName _DelegationDescriptor_QNAME = new QName("http://gaards.cagrid.org/cds", "DelegationDescriptor");
    private final static QName _DelegatedCredentialAuditRecord_QNAME = new QName("http://gaards.cagrid.org/cds", "DelegatedCredentialAuditRecord");
    private final static QName _DelegationIdentifier_QNAME = new QName("http://gaards.cagrid.org/cds", "DelegationIdentifier");
    private final static QName _PublicKey_QNAME = new QName("http://gaards.cagrid.org/cds", "PublicKey");
    private final static QName _DelegatedCredentialAuditFilter_QNAME = new QName("http://gaards.cagrid.org/cds", "DelegatedCredentialAuditFilter");
    private final static QName _ClientDelegationFilter_QNAME = new QName("http://gaards.cagrid.org/cds", "ClientDelegationFilter");
    private final static QName _GroupDelegationPolicy_QNAME = new QName("http://gaards.cagrid.org/cds", "GroupDelegationPolicy");
    private final static QName _DelegationStatus_QNAME = new QName("http://gaards.cagrid.org/cds", "DelegationStatus");
    private final static QName _DelegationRecord_QNAME = new QName("http://gaards.cagrid.org/cds", "DelegationRecord");
    private final static QName _DelegationSigningResponse_QNAME = new QName("http://gaards.cagrid.org/cds", "DelegationSigningResponse");
    private final static QName _CertificateChain_QNAME = new QName("http://gaards.cagrid.org/cds", "CertificateChain");
    private final static QName _DelegationRecordFilter_QNAME = new QName("http://gaards.cagrid.org/cds", "DelegationRecordFilter");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.cds.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DelegationSigningRequest }
     * 
     */
    public DelegationSigningRequest createDelegationSigningRequest() {
        return new DelegationSigningRequest();
    }

    /**
     * Create an instance of {@link CertificateChain }
     * 
     */
    public CertificateChain createCertificateChain() {
        return new CertificateChain();
    }

    /**
     * Create an instance of {@link ProxyLifetime }
     * 
     */
    public ProxyLifetime createProxyLifetime() {
        return new ProxyLifetime();
    }

    /**
     * Create an instance of {@link DelegationDescriptor }
     * 
     */
    public DelegationDescriptor createDelegationDescriptor() {
        return new DelegationDescriptor();
    }

    /**
     * Create an instance of {@link DelegationRecord }
     * 
     */
    public DelegationRecord createDelegationRecord() {
        return new DelegationRecord();
    }

    /**
     * Create an instance of {@link DelegationRecordFilter }
     * 
     */
    public DelegationRecordFilter createDelegationRecordFilter() {
        return new DelegationRecordFilter();
    }

    /**
     * Create an instance of {@link DelegatedCredentialAuditFilter }
     * 
     */
    public DelegatedCredentialAuditFilter createDelegatedCredentialAuditFilter() {
        return new DelegatedCredentialAuditFilter();
    }

    /**
     * Create an instance of {@link DelegationRequest }
     * 
     */
    public DelegationRequest createDelegationRequest() {
        return new DelegationRequest();
    }

    /**
     * Create an instance of {@link AllowedParties }
     * 
     */
    public AllowedParties createAllowedParties() {
        return new AllowedParties();
    }

    /**
     * Create an instance of {@link DelegationSigningResponse }
     * 
     */
    public DelegationSigningResponse createDelegationSigningResponse() {
        return new DelegationSigningResponse();
    }

    /**
     * Create an instance of {@link GroupDelegationPolicy }
     * 
     */
    public GroupDelegationPolicy createGroupDelegationPolicy() {
        return new GroupDelegationPolicy();
    }

    /**
     * Create an instance of {@link IdentityDelegationPolicy }
     * 
     */
    public IdentityDelegationPolicy createIdentityDelegationPolicy() {
        return new IdentityDelegationPolicy();
    }

    /**
     * Create an instance of {@link ClientDelegationFilter }
     * 
     */
    public ClientDelegationFilter createClientDelegationFilter() {
        return new ClientDelegationFilter();
    }

    /**
     * Create an instance of {@link X509Certificate }
     * 
     */
    public X509Certificate createX509Certificate() {
        return new X509Certificate();
    }

    /**
     * Create an instance of {@link DelegationIdentifier }
     * 
     */
    public DelegationIdentifier createDelegationIdentifier() {
        return new DelegationIdentifier();
    }

    /**
     * Create an instance of {@link DelegatedCredentialAuditRecord }
     * 
     */
    public DelegatedCredentialAuditRecord createDelegatedCredentialAuditRecord() {
        return new DelegatedCredentialAuditRecord();
    }

    /**
     * Create an instance of {@link org.cagrid.cds.model.PublicKey }
     * 
     */
    public PublicKey createPublicKey() {
        return new PublicKey();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link DelegationPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "DelegationPolicy")
    public JAXBElement<DelegationPolicy> createDelegationPolicy(DelegationPolicy value) {
        return new JAXBElement<DelegationPolicy>(_DelegationPolicy_QNAME, DelegationPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link X509Certificate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "X509Certificate")
    public JAXBElement<X509Certificate> createX509Certificate(X509Certificate value) {
        return new JAXBElement<X509Certificate>(_X509Certificate_QNAME, X509Certificate.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link DelegationSigningRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "DelegationSigningRequest")
    public JAXBElement<DelegationSigningRequest> createDelegationSigningRequest(DelegationSigningRequest value) {
        return new JAXBElement<DelegationSigningRequest>(_DelegationSigningRequest_QNAME, DelegationSigningRequest.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link IdentityDelegationPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "IdentityDelegationPolicy")
    public JAXBElement<IdentityDelegationPolicy> createIdentityDelegationPolicy(IdentityDelegationPolicy value) {
        return new JAXBElement<IdentityDelegationPolicy>(_IdentityDelegationPolicy_QNAME, IdentityDelegationPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link DelegationRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "DelegationRequest")
    public JAXBElement<DelegationRequest> createDelegationRequest(DelegationRequest value) {
        return new JAXBElement<DelegationRequest>(_DelegationRequest_QNAME, DelegationRequest.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link DelegationDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "DelegationDescriptor")
    public JAXBElement<DelegationDescriptor> createDelegationDescriptor(DelegationDescriptor value) {
        return new JAXBElement<DelegationDescriptor>(_DelegationDescriptor_QNAME, DelegationDescriptor.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link DelegatedCredentialAuditRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "DelegatedCredentialAuditRecord")
    public JAXBElement<DelegatedCredentialAuditRecord> createDelegatedCredentialAuditRecord(DelegatedCredentialAuditRecord value) {
        return new JAXBElement<DelegatedCredentialAuditRecord>(_DelegatedCredentialAuditRecord_QNAME, DelegatedCredentialAuditRecord.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link DelegationIdentifier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "DelegationIdentifier")
    public JAXBElement<DelegationIdentifier> createDelegationIdentifier(DelegationIdentifier value) {
        return new JAXBElement<DelegationIdentifier>(_DelegationIdentifier_QNAME, DelegationIdentifier.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.cds.model.PublicKey }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "PublicKey")
    public JAXBElement<PublicKey> createPublicKey(PublicKey value) {
        return new JAXBElement<PublicKey>(_PublicKey_QNAME, PublicKey.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link DelegatedCredentialAuditFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "DelegatedCredentialAuditFilter")
    public JAXBElement<DelegatedCredentialAuditFilter> createDelegatedCredentialAuditFilter(DelegatedCredentialAuditFilter value) {
        return new JAXBElement<DelegatedCredentialAuditFilter>(_DelegatedCredentialAuditFilter_QNAME, DelegatedCredentialAuditFilter.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link ClientDelegationFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "ClientDelegationFilter")
    public JAXBElement<ClientDelegationFilter> createClientDelegationFilter(ClientDelegationFilter value) {
        return new JAXBElement<ClientDelegationFilter>(_ClientDelegationFilter_QNAME, ClientDelegationFilter.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link GroupDelegationPolicy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "GroupDelegationPolicy")
    public JAXBElement<GroupDelegationPolicy> createGroupDelegationPolicy(GroupDelegationPolicy value) {
        return new JAXBElement<GroupDelegationPolicy>(_GroupDelegationPolicy_QNAME, GroupDelegationPolicy.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link DelegationStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "DelegationStatus")
    public JAXBElement<DelegationStatus> createDelegationStatus(DelegationStatus value) {
        return new JAXBElement<DelegationStatus>(_DelegationStatus_QNAME, DelegationStatus.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link DelegationRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "DelegationRecord")
    public JAXBElement<DelegationRecord> createDelegationRecord(DelegationRecord value) {
        return new JAXBElement<DelegationRecord>(_DelegationRecord_QNAME, DelegationRecord.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link DelegationSigningResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "DelegationSigningResponse")
    public JAXBElement<DelegationSigningResponse> createDelegationSigningResponse(DelegationSigningResponse value) {
        return new JAXBElement<DelegationSigningResponse>(_DelegationSigningResponse_QNAME, DelegationSigningResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link CertificateChain }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "CertificateChain")
    public JAXBElement<CertificateChain> createCertificateChain(CertificateChain value) {
        return new JAXBElement<CertificateChain>(_CertificateChain_QNAME, CertificateChain.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link DelegationRecordFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/cds", name = "DelegationRecordFilter")
    public JAXBElement<DelegationRecordFilter> createDelegationRecordFilter(DelegationRecordFilter value) {
        return new JAXBElement<DelegationRecordFilter>(_DelegationRecordFilter_QNAME, DelegationRecordFilter.class, null, value);
    }

}
