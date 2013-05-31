package org.cagrid.gridgrouper.service.impl;

import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
import org.cagrid.core.resource.JAXBResourceProperties;
import org.cagrid.core.resource.JAXBResourcePropertySupport;
import org.cagrid.core.resource.ResourceImpl;
import org.cagrid.core.resource.ResourcePropertyDescriptor;
import org.cagrid.core.resource.SingletonResourceHomeImpl;
import org.cagrid.gridgrouper.model.GroupCompositeType;
import org.cagrid.gridgrouper.model.GroupDescriptor;
import org.cagrid.gridgrouper.model.GroupIdentifier;
import org.cagrid.gridgrouper.model.GroupPrivilege;
import org.cagrid.gridgrouper.model.GroupPrivilegeType;
import org.cagrid.gridgrouper.model.GroupUpdate;
import org.cagrid.gridgrouper.model.MemberDescriptor;
import org.cagrid.gridgrouper.model.MemberFilter;
import org.cagrid.gridgrouper.model.MembershipDescriptor;
import org.cagrid.gridgrouper.model.MembershipExpression;
import org.cagrid.gridgrouper.model.MembershipRequestDescriptor;
import org.cagrid.gridgrouper.model.MembershipRequestStatus;
import org.cagrid.gridgrouper.model.MembershipRequestUpdate;
import org.cagrid.gridgrouper.model.MembershipType;
import org.cagrid.gridgrouper.model.StemDescriptor;
import org.cagrid.gridgrouper.model.StemIdentifier;
import org.cagrid.gridgrouper.model.StemPrivilege;
import org.cagrid.gridgrouper.model.StemPrivilegeType;
import org.cagrid.gridgrouper.model.StemUpdate;
import org.cagrid.gridgrouper.service.GridGrouperService;
import org.cagrid.gridgrouper.service.exception.GrantPrivilegeException;
import org.cagrid.gridgrouper.service.exception.GridGrouperRuntimeException;
import org.cagrid.gridgrouper.service.exception.GroupAddException;
import org.cagrid.gridgrouper.service.exception.GroupDeleteException;
import org.cagrid.gridgrouper.service.exception.GroupModifyException;
import org.cagrid.gridgrouper.service.exception.GroupNotFoundException;
import org.cagrid.gridgrouper.service.exception.InsufficientPrivilegeException;
import org.cagrid.gridgrouper.service.exception.MemberAddException;
import org.cagrid.gridgrouper.service.exception.MemberDeleteException;
import org.cagrid.gridgrouper.service.exception.RevokePrivilegeException;
import org.cagrid.gridgrouper.service.exception.SchemaException;
import org.cagrid.gridgrouper.service.exception.StemAddException;
import org.cagrid.gridgrouper.service.exception.StemDeleteException;
import org.cagrid.gridgrouper.service.exception.StemModifyException;
import org.cagrid.gridgrouper.service.exception.StemNotFoundException;
import org.cagrid.gridgrouper.wsrf.stubs.GridGrouperResourceProperties;
import org.cagrid.wsrf.properties.ResourceHome;
import org.cagrid.wsrf.properties.ResourceProperty;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GridGrouperServiceImpl implements GridGrouperService {

    GridGrouper gridGrouper;

    private ResourceProperty<ServiceMetadata> serviceMetadataResourceProperty;
    private ResourceProperty<ServiceSecurityMetadata> serviceSecurityMetadataResourceProperty;
    private final Map<String, String> jaxbResourcePropertiesMap;
    private final ResourceImpl resource = new ResourceImpl(null);
    private final ResourceHome resourceHome = new SingletonResourceHomeImpl(resource);

    public GridGrouperServiceImpl(Map<String, String> jaxbResourcePropertiesMap) throws GridGrouperRuntimeException {
        this.gridGrouper = new GridGrouper();
        this.jaxbResourcePropertiesMap = jaxbResourcePropertiesMap;
    }

    @Override
    public ServiceSecurityMetadata getServiceSecurityMetadata() {
        return (serviceSecurityMetadataResourceProperty != null) ? serviceSecurityMetadataResourceProperty
                .get(0) : null;
    }

    @Override
    public StemDescriptor getStem(String callerIdentity, StemIdentifier stem) throws GridGrouperRuntimeException, StemNotFoundException {
        return gridGrouper.getStem(callerIdentity, stem);
    }

    @Override
    public List<StemDescriptor> getChildStems(String callerIdentity, StemIdentifier parentStem) throws GridGrouperRuntimeException, StemNotFoundException {
        return gridGrouper.getChildStems(callerIdentity, parentStem);
    }

    @Override
    public StemDescriptor getParentStem(String callerIdentity, StemIdentifier childStem) throws GridGrouperRuntimeException, StemNotFoundException {
        return gridGrouper.getParentStem(callerIdentity, childStem);
    }

    @Override
    public List<String> getSubjectsWithStemPrivilege(String callerIdentity, StemIdentifier stem, StemPrivilegeType privilege) throws GridGrouperRuntimeException, StemNotFoundException {
        return gridGrouper.getSubjectsWithStemPrivilege(callerIdentity, stem, privilege);
    }

    @Override
    public List<StemPrivilege> getStemPrivileges(String callerIdentity, StemIdentifier stem, String subject) throws GridGrouperRuntimeException, StemNotFoundException {
        return gridGrouper.getStemPrivileges(callerIdentity, stem, subject);
    }

    @Override
    public boolean hasStemPrivilege(String callerIdentity, StemIdentifier stem, String subject, StemPrivilegeType privilege) throws GridGrouperRuntimeException, StemNotFoundException {
        return gridGrouper.hasStemPrivilege(callerIdentity, stem, subject, privilege);
    }

    @Override
    public void grantStemPrivilege(String callerIdentity, StemIdentifier stem, String subject, StemPrivilegeType privilege) throws GrantPrivilegeException, SchemaException, InsufficientPrivilegeException, StemNotFoundException, GridGrouperRuntimeException {
        gridGrouper.grantStemPrivilege(callerIdentity, stem, subject, privilege);
    }

    @Override
    public void revokeStemPrivilege(String callerIdentity, StemIdentifier stem, String subject, StemPrivilegeType privilege) throws InsufficientPrivilegeException, RevokePrivilegeException, SchemaException, StemNotFoundException, GridGrouperRuntimeException {
        gridGrouper.revokeStemPrivilege(callerIdentity, stem, subject, privilege);
    }

    @Override
    public StemDescriptor addChildStem(String callerIdentity, StemIdentifier stem, String extension, String displayExtension) throws StemNotFoundException, StemAddException, InsufficientPrivilegeException, GridGrouperRuntimeException {
        return gridGrouper.addChildStem(callerIdentity, stem, extension, displayExtension);
    }

    @Override
    public void deleteStem(String callerIdentity, StemIdentifier stem) throws StemDeleteException, StemNotFoundException, InsufficientPrivilegeException, GridGrouperRuntimeException {
        gridGrouper.deleteStem(callerIdentity, stem);
    }

    @Override
    public GroupDescriptor getGroup(String callerIdentity, GroupIdentifier group) throws GridGrouperRuntimeException, GroupNotFoundException {
        return gridGrouper.getGroup(callerIdentity, group);
    }

    @Override
    public List<GroupDescriptor> getChildGroups(String callerIdentity, StemIdentifier stem) throws GridGrouperRuntimeException, StemNotFoundException {
        return gridGrouper.getChildGroups(callerIdentity, stem);
    }

    @Override
    public GroupDescriptor addChildGroup(String callerIdentity, StemIdentifier stem, String extension, String displayExtension) throws GroupAddException, InsufficientPrivilegeException, StemNotFoundException, GridGrouperRuntimeException {
        return gridGrouper.addChildGroup(callerIdentity, stem, extension, displayExtension);
    }

    @Override
    public void deleteGroup(String callerIdentity, GroupIdentifier group) throws InsufficientPrivilegeException, GroupDeleteException, GroupNotFoundException, GridGrouperRuntimeException {
        gridGrouper.deleteGroup(callerIdentity, group);
    }

    @Override
    public StemDescriptor updateStem(String callerIdentity, StemIdentifier stem, StemUpdate update) throws StemModifyException, GridGrouperRuntimeException, InsufficientPrivilegeException {
        return gridGrouper.updateStem(callerIdentity, stem, update);
    }

    @Override
    public GroupDescriptor updateGroup(String callerIdentity, GroupIdentifier group, GroupUpdate update) throws InsufficientPrivilegeException, GroupModifyException, GroupNotFoundException, GridGrouperRuntimeException {
        return gridGrouper.updateGroup(callerIdentity, group, update);
    }

    @Override
    public void addMember(String callerIdentity, GroupIdentifier group, String subject) throws InsufficientPrivilegeException, MemberAddException, GroupNotFoundException, GridGrouperRuntimeException {
        gridGrouper.addMember(callerIdentity, group, subject);
    }

    @Override
    public List<MemberDescriptor> getMembers(String callerIdentity, GroupIdentifier group, MemberFilter filter) throws GridGrouperRuntimeException, GroupNotFoundException {
        return gridGrouper.getMembers(callerIdentity, group, filter);
    }

    @Override
    public boolean isMemberOf(String callerIdentity, GroupIdentifier group, String member, MemberFilter filter) throws GridGrouperRuntimeException, GroupNotFoundException {
        return gridGrouper.isMemberOf(callerIdentity, group, member, filter);
    }

    @Override
    public List<MembershipDescriptor> getMemberships(String callerIdentity, GroupIdentifier group, MemberFilter filter) throws GridGrouperRuntimeException, GroupNotFoundException {
        return this.gridGrouper.getMemberships(callerIdentity, group, filter);
    }

    @Override
    public void deleteMember(String callerIdentity, GroupIdentifier group, String member) throws GroupNotFoundException, InsufficientPrivilegeException, MemberDeleteException, GridGrouperRuntimeException {
        gridGrouper.deleteMember(callerIdentity, group, member);
    }

    @Override
    public GroupDescriptor addCompositeMember(String callerIdentity, GroupCompositeType type, GroupIdentifier composite, GroupIdentifier left, GroupIdentifier right) throws InsufficientPrivilegeException, MemberAddException, GroupNotFoundException, GridGrouperRuntimeException {
        return gridGrouper.addCompositeMember(callerIdentity, type, composite, left, right);
    }

    @Override
    public GroupDescriptor deleteCompositeMember(String callerIdentity, GroupIdentifier group) throws InsufficientPrivilegeException, GroupNotFoundException, MemberDeleteException, GridGrouperRuntimeException {
        return gridGrouper.deleteCompositeMember(callerIdentity, group);
    }

    @Override
    public void grantGroupPrivilege(String callerIdentity, GroupIdentifier group, String subject, GroupPrivilegeType privilege) throws GrantPrivilegeException, InsufficientPrivilegeException, GroupNotFoundException, GridGrouperRuntimeException {
        gridGrouper.grantGroupPrivilege(callerIdentity, group, subject, privilege);
    }

    @Override
    public void revokeGroupPrivilege(String callerIdentity, GroupIdentifier group, String subject, GroupPrivilegeType privilege) throws GroupNotFoundException, RevokePrivilegeException, SchemaException, InsufficientPrivilegeException, GridGrouperRuntimeException {
        gridGrouper.revokeGroupPrivilege(callerIdentity, group, subject, privilege);
    }

    @Override
    public List<String> getSubjectsWithGroupPrivilege(String callerIdentity, GroupIdentifier group, GroupPrivilegeType privilege) throws GridGrouperRuntimeException, GroupNotFoundException {
        return gridGrouper.getSubjectsWithGroupPrivilege(callerIdentity, group, privilege);
    }

    @Override
    public List<GroupPrivilege> getGroupPrivileges(String callerIdentity, GroupIdentifier group, String subject) throws GridGrouperRuntimeException, GroupNotFoundException {
        return gridGrouper.getGroupPrivileges(callerIdentity, group, subject);
    }

    @Override
    public boolean hasGroupPrivilege(String callerIdentity, GroupIdentifier group, String subject, GroupPrivilegeType privilege) throws GridGrouperRuntimeException, GroupNotFoundException {
        return gridGrouper.hasGroupPrivilege(callerIdentity, group, subject, privilege);
    }

    @Override
    public boolean isMember(String callerIdentity, String member, MembershipExpression expression) throws GridGrouperRuntimeException {
        return gridGrouper.isMember(callerIdentity, member, expression);
    }

    @Override
    public MemberDescriptor getMember(String callerIdentity, String member) throws GridGrouperRuntimeException, InsufficientPrivilegeException {
        return gridGrouper.getMember(callerIdentity, member);
    }

    @Override
    public List<GroupDescriptor> getMembersGroups(String callerIdentity, String member, MembershipType type) throws GridGrouperRuntimeException, InsufficientPrivilegeException {
        return gridGrouper.getMembersGroups(callerIdentity, member, type);
    }

    @Override
    public void addMembershipRequest(String callerIdentity, GroupIdentifier group) throws InsufficientPrivilegeException, MemberAddException, GroupNotFoundException, GridGrouperRuntimeException {
        gridGrouper.addMembershipRequest(callerIdentity, group);
    }

    @Override
    public MembershipRequestDescriptor updateMembershipRequest(String callerIdentity, GroupIdentifier group, String subject, MembershipRequestUpdate update) throws InsufficientPrivilegeException, MemberAddException, GroupNotFoundException, GridGrouperRuntimeException {
        return gridGrouper.updateMembershipRequest(callerIdentity, group, subject, update);
    }

    @Override
    public List<MembershipRequestDescriptor> getMembershipRequests(String callerIdentity, GroupIdentifier group, MembershipRequestStatus status) throws GridGrouperRuntimeException, GroupNotFoundException {
        return gridGrouper.getMembershipRequests(callerIdentity, group, status);
    }

    @Override
    public void enableMembershipRequests(String callerIdentity, GroupIdentifier group) throws GrantPrivilegeException, InsufficientPrivilegeException, GroupNotFoundException, GridGrouperRuntimeException {
        gridGrouper.enableMembershipRequests(callerIdentity, group);
    }

    @Override
    public void disableMembershipRequests(String callerIdentity, GroupIdentifier group) throws GroupNotFoundException, RevokePrivilegeException, SchemaException, InsufficientPrivilegeException, GridGrouperRuntimeException {
        gridGrouper.disableMembershipRequests(callerIdentity, group);
    }

    @Override
    public boolean isMembershipRequestEnabled(String callerIdentity, GroupIdentifier group) throws GrantPrivilegeException, InsufficientPrivilegeException, GroupNotFoundException, GridGrouperRuntimeException {
        return gridGrouper.isMembershipRequestEnabled(callerIdentity, group);
    }

    @Override
    public ResourceHome getResourceHome() {
        return resourceHome;
    }

    private void initialize() throws JAXBException {
        // What resource properties should we know about?
        Collection<ResourcePropertyDescriptor<?>> resourcePropertyDescriptors = ResourcePropertyDescriptor
                .analyzeResourcePropertiesHolder(GridGrouperResourceProperties.class);

        // Map them by field.
        Map<String, ResourcePropertyDescriptor<?>> descriptorsByField = ResourcePropertyDescriptor
                .mapByField(resourcePropertyDescriptors);

        // Load the static jaxb resource properties.
        JAXBResourceProperties jaxbResourceProperties = new JAXBResourceProperties(
                getClass().getClassLoader(), descriptorsByField, jaxbResourcePropertiesMap);

        // The serviceMetadata property is static.
        @SuppressWarnings("unchecked")
        ResourcePropertyDescriptor<ServiceMetadata> serviceMetadataDescriptor = (ResourcePropertyDescriptor<ServiceMetadata>) descriptorsByField
                .get("serviceMetadata");
        if (serviceMetadataDescriptor != null) {
            @SuppressWarnings("unchecked")
            ResourceProperty<ServiceMetadata> resourceProperty = (ResourceProperty<ServiceMetadata>) jaxbResourceProperties
                    .getResourceProperties().get(serviceMetadataDescriptor);
            serviceMetadataResourceProperty = resourceProperty;
            resource.add(serviceMetadataResourceProperty);
        }

		/*
		 * ServiceSecurityMetadata isn't a resource property, but use that
		 * framework to handle it.
		 */
        String serviceSecurityMetadataURLString = jaxbResourcePropertiesMap
                .get("serviceSecurityMetadata");
        if (serviceSecurityMetadataURLString != null) {
            URL url = null;
            try {
                url = new URL(serviceSecurityMetadataURLString);
            } catch (MalformedURLException ignored) {
            }
            if (url == null) {
                url = getClass().getClassLoader().getResource(
                        serviceSecurityMetadataURLString);
            }
            if (url != null) {
                QName serviceSecurityMetadataQName = new QName(
                        getClass().getName(), "serviceSecurityMetadata");
                ResourcePropertyDescriptor<ServiceSecurityMetadata> serviceSecurityMetadataDescriptor = new ResourcePropertyDescriptor<ServiceSecurityMetadata>(
                        serviceSecurityMetadataQName,
                        ServiceSecurityMetadata.class,
                        "serviceSecurityMetadata");
                serviceSecurityMetadataResourceProperty = JAXBResourcePropertySupport
                        .createJAXBResourceProperty(
                                serviceSecurityMetadataDescriptor, url);
            }
        }
    }
}
