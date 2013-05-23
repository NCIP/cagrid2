
package org.cagrid.gridgrouper.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.gridgrouper.model package. 
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

    private final static QName _GroupCompositeType_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "GroupCompositeType");
    private final static QName _GroupDescriptor_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "GroupDescriptor");
    private final static QName _SubjectIdentifier_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "SubjectIdentifier");
    private final static QName _MembershipQuery_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "MembershipQuery");
    private final static QName _MembershipRequestUpdate_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "MembershipRequestUpdate");
    private final static QName _StemIdentifier_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "StemIdentifier");
    private final static QName _MembershipType_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "MembershipType");
    private final static QName _MembershipRequestStatus_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "MembershipRequestStatus");
    private final static QName _MemberFilter_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "MemberFilter");
    private final static QName _GroupPrivilegeType_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "GroupPrivilegeType");
    private final static QName _GroupIdentifier_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "GroupIdentifier");
    private final static QName _StemDescriptor_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "StemDescriptor");
    private final static QName _GroupUpdate_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "GroupUpdate");
    private final static QName _GroupPrivilege_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "GroupPrivilege");
    private final static QName _StemUpdate_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "StemUpdate");
    private final static QName _StemPrivilegeType_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "StemPrivilegeType");
    private final static QName _MembershipRequestHistoryDescriptor_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "MembershipRequestHistoryDescriptor");
    private final static QName _MembershipExpression_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "MembershipExpression");
    private final static QName _MembershipDescriptor_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "MembershipDescriptor");
    private final static QName _MembershipRequestDescriptor_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "MembershipRequestDescriptor");
    private final static QName _MembershipRequestIdentifier_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "MembershipRequestIdentifier");
    private final static QName _MemberDescriptor_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "MemberDescriptor");
    private final static QName _StemPrivilege_QNAME = new QName("http://cagrid.nci.nih.gov/1/GridGrouper", "StemPrivilege");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.gridgrouper.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link org.cagrid.gridgrouper.model.GroupDescriptor }
     * 
     */
    public GroupDescriptor createGroupDescriptor() {
        return new GroupDescriptor();
    }

    /**
     * Create an instance of {@link org.cagrid.gridgrouper.model.MembershipQuery }
     * 
     */
    public MembershipQuery createMembershipQuery() {
        return new MembershipQuery();
    }

    /**
     * Create an instance of {@link org.cagrid.gridgrouper.model.MemberDescriptor }
     * 
     */
    public MemberDescriptor createMemberDescriptor() {
        return new MemberDescriptor();
    }

    /**
     * Create an instance of {@link StemUpdate }
     * 
     */
    public StemUpdate createStemUpdate() {
        return new StemUpdate();
    }

    /**
     * Create an instance of {@link org.cagrid.gridgrouper.model.GroupPrivilege }
     * 
     */
    public GroupPrivilege createGroupPrivilege() {
        return new GroupPrivilege();
    }

    /**
     * Create an instance of {@link StemIdentifier }
     * 
     */
    public StemIdentifier createStemIdentifier() {
        return new StemIdentifier();
    }

    /**
     * Create an instance of {@link StemDescriptor }
     * 
     */
    public StemDescriptor createStemDescriptor() {
        return new StemDescriptor();
    }

    /**
     * Create an instance of {@link org.cagrid.gridgrouper.model.MembershipRequestHistoryDescriptor }
     * 
     */
    public MembershipRequestHistoryDescriptor createMembershipRequestHistoryDescriptor() {
        return new MembershipRequestHistoryDescriptor();
    }

    /**
     * Create an instance of {@link org.cagrid.gridgrouper.model.GroupIdentifier }
     * 
     */
    public GroupIdentifier createGroupIdentifier() {
        return new GroupIdentifier();
    }

    /**
     * Create an instance of {@link StemPrivilege }
     * 
     */
    public StemPrivilege createStemPrivilege() {
        return new StemPrivilege();
    }

    /**
     * Create an instance of {@link org.cagrid.gridgrouper.model.MembershipRequestDescriptor }
     * 
     */
    public MembershipRequestDescriptor createMembershipRequestDescriptor() {
        return new MembershipRequestDescriptor();
    }

    /**
     * Create an instance of {@link org.cagrid.gridgrouper.model.MembershipExpression }
     * 
     */
    public MembershipExpression createMembershipExpression() {
        return new MembershipExpression();
    }

    /**
     * Create an instance of {@link org.cagrid.gridgrouper.model.MembershipDescriptor }
     * 
     */
    public MembershipDescriptor createMembershipDescriptor() {
        return new MembershipDescriptor();
    }

    /**
     * Create an instance of {@link org.cagrid.gridgrouper.model.MembershipRequestUpdate }
     * 
     */
    public MembershipRequestUpdate createMembershipRequestUpdate() {
        return new MembershipRequestUpdate();
    }

    /**
     * Create an instance of {@link org.cagrid.gridgrouper.model.GroupUpdate }
     * 
     */
    public GroupUpdate createGroupUpdate() {
        return new GroupUpdate();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.GroupCompositeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "GroupCompositeType")
    public JAXBElement<GroupCompositeType> createGroupCompositeType(GroupCompositeType value) {
        return new JAXBElement<GroupCompositeType>(_GroupCompositeType_QNAME, GroupCompositeType.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.GroupDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "GroupDescriptor")
    public JAXBElement<GroupDescriptor> createGroupDescriptor(GroupDescriptor value) {
        return new JAXBElement<GroupDescriptor>(_GroupDescriptor_QNAME, GroupDescriptor.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "SubjectIdentifier")
    public JAXBElement<String> createSubjectIdentifier(String value) {
        return new JAXBElement<String>(_SubjectIdentifier_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.MembershipQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "MembershipQuery")
    public JAXBElement<MembershipQuery> createMembershipQuery(MembershipQuery value) {
        return new JAXBElement<MembershipQuery>(_MembershipQuery_QNAME, MembershipQuery.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.MembershipRequestUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "MembershipRequestUpdate")
    public JAXBElement<MembershipRequestUpdate> createMembershipRequestUpdate(MembershipRequestUpdate value) {
        return new JAXBElement<MembershipRequestUpdate>(_MembershipRequestUpdate_QNAME, MembershipRequestUpdate.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link StemIdentifier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "StemIdentifier")
    public JAXBElement<StemIdentifier> createStemIdentifier(StemIdentifier value) {
        return new JAXBElement<StemIdentifier>(_StemIdentifier_QNAME, StemIdentifier.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.MembershipType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "MembershipType")
    public JAXBElement<MembershipType> createMembershipType(MembershipType value) {
        return new JAXBElement<MembershipType>(_MembershipType_QNAME, MembershipType.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.MembershipRequestStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "MembershipRequestStatus")
    public JAXBElement<MembershipRequestStatus> createMembershipRequestStatus(MembershipRequestStatus value) {
        return new JAXBElement<MembershipRequestStatus>(_MembershipRequestStatus_QNAME, MembershipRequestStatus.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.MemberFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "MemberFilter")
    public JAXBElement<MemberFilter> createMemberFilter(MemberFilter value) {
        return new JAXBElement<MemberFilter>(_MemberFilter_QNAME, MemberFilter.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.GroupPrivilegeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "GroupPrivilegeType")
    public JAXBElement<GroupPrivilegeType> createGroupPrivilegeType(GroupPrivilegeType value) {
        return new JAXBElement<GroupPrivilegeType>(_GroupPrivilegeType_QNAME, GroupPrivilegeType.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.GroupIdentifier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "GroupIdentifier")
    public JAXBElement<GroupIdentifier> createGroupIdentifier(GroupIdentifier value) {
        return new JAXBElement<GroupIdentifier>(_GroupIdentifier_QNAME, GroupIdentifier.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link StemDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "StemDescriptor")
    public JAXBElement<StemDescriptor> createStemDescriptor(StemDescriptor value) {
        return new JAXBElement<StemDescriptor>(_StemDescriptor_QNAME, StemDescriptor.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.GroupUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "GroupUpdate")
    public JAXBElement<GroupUpdate> createGroupUpdate(GroupUpdate value) {
        return new JAXBElement<GroupUpdate>(_GroupUpdate_QNAME, GroupUpdate.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.GroupPrivilege }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "GroupPrivilege")
    public JAXBElement<GroupPrivilege> createGroupPrivilege(GroupPrivilege value) {
        return new JAXBElement<GroupPrivilege>(_GroupPrivilege_QNAME, GroupPrivilege.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link StemUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "StemUpdate")
    public JAXBElement<StemUpdate> createStemUpdate(StemUpdate value) {
        return new JAXBElement<StemUpdate>(_StemUpdate_QNAME, StemUpdate.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link StemPrivilegeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "StemPrivilegeType")
    public JAXBElement<StemPrivilegeType> createStemPrivilegeType(StemPrivilegeType value) {
        return new JAXBElement<StemPrivilegeType>(_StemPrivilegeType_QNAME, StemPrivilegeType.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.MembershipRequestHistoryDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "MembershipRequestHistoryDescriptor")
    public JAXBElement<MembershipRequestHistoryDescriptor> createMembershipRequestHistoryDescriptor(MembershipRequestHistoryDescriptor value) {
        return new JAXBElement<MembershipRequestHistoryDescriptor>(_MembershipRequestHistoryDescriptor_QNAME, MembershipRequestHistoryDescriptor.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.MembershipExpression }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "MembershipExpression")
    public JAXBElement<MembershipExpression> createMembershipExpression(MembershipExpression value) {
        return new JAXBElement<MembershipExpression>(_MembershipExpression_QNAME, MembershipExpression.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.MembershipDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "MembershipDescriptor")
    public JAXBElement<MembershipDescriptor> createMembershipDescriptor(MembershipDescriptor value) {
        return new JAXBElement<MembershipDescriptor>(_MembershipDescriptor_QNAME, MembershipDescriptor.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.MembershipRequestDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "MembershipRequestDescriptor")
    public JAXBElement<MembershipRequestDescriptor> createMembershipRequestDescriptor(MembershipRequestDescriptor value) {
        return new JAXBElement<MembershipRequestDescriptor>(_MembershipRequestDescriptor_QNAME, MembershipRequestDescriptor.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "MembershipRequestIdentifier")
    public JAXBElement<String> createMembershipRequestIdentifier(String value) {
        return new JAXBElement<String>(_MembershipRequestIdentifier_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gridgrouper.model.MemberDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "MemberDescriptor")
    public JAXBElement<MemberDescriptor> createMemberDescriptor(MemberDescriptor value) {
        return new JAXBElement<MemberDescriptor>(_MemberDescriptor_QNAME, MemberDescriptor.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link StemPrivilege }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/GridGrouper", name = "StemPrivilege")
    public JAXBElement<StemPrivilege> createStemPrivilege(StemPrivilege value) {
        return new JAXBElement<StemPrivilege>(_StemPrivilege_QNAME, StemPrivilege.class, null, value);
    }

}
