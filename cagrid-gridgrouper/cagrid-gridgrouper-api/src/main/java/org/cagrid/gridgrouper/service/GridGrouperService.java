package org.cagrid.gridgrouper.service;

import gov.nih.nci.cagrid.metadata.security.ServiceSecurityMetadata;
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
import org.cagrid.wsrf.properties.ResourceHome;

import java.util.List;

public interface GridGrouperService {

    public static final String ROOT_STEM = "";

    public ServiceSecurityMetadata getServiceSecurityMetadata();

    public StemDescriptor getStem(String callerIdentity, StemIdentifier stem) throws GridGrouperRuntimeException, StemNotFoundException;

    public List<StemDescriptor> getChildStems(String callerIdentity, StemIdentifier parentStem) throws GridGrouperRuntimeException,  StemNotFoundException;

    public StemDescriptor getParentStem(String callerIdentity, StemIdentifier childStem) throws GridGrouperRuntimeException,  StemNotFoundException;

    public List<String> getSubjectsWithStemPrivilege(String callerIdentity, StemIdentifier stem, StemPrivilegeType privilege) throws GridGrouperRuntimeException,  StemNotFoundException;

    public List<StemPrivilege> getStemPrivileges(String callerIdentity, StemIdentifier stem,String subject) throws GridGrouperRuntimeException,  StemNotFoundException;

    public boolean hasStemPrivilege(String callerIdentity, StemIdentifier stem,String subject, StemPrivilegeType privilege) throws GridGrouperRuntimeException, StemNotFoundException;

    public void grantStemPrivilege(String callerIdentity, StemIdentifier stem,String subject, StemPrivilegeType privilege) throws GrantPrivilegeException, SchemaException, InsufficientPrivilegeException, StemNotFoundException, GridGrouperRuntimeException;

    public void revokeStemPrivilege(String callerIdentity, StemIdentifier stem,String subject, StemPrivilegeType privilege) throws InsufficientPrivilegeException, RevokePrivilegeException, SchemaException, StemNotFoundException, GridGrouperRuntimeException;

    public StemDescriptor addChildStem(String callerIdentity, StemIdentifier stem,String extension,String displayExtension) throws StemNotFoundException, StemAddException, InsufficientPrivilegeException, GridGrouperRuntimeException;

    public void deleteStem(String callerIdentity, StemIdentifier stem) throws StemDeleteException, StemNotFoundException, InsufficientPrivilegeException, GridGrouperRuntimeException;

    public GroupDescriptor getGroup(String callerIdentity, GroupIdentifier group) throws GridGrouperRuntimeException, GroupNotFoundException;

    public List<GroupDescriptor> getChildGroups(String callerIdentity, StemIdentifier stem) throws GridGrouperRuntimeException, StemNotFoundException;

    public GroupDescriptor addChildGroup(String callerIdentity, StemIdentifier stem,String extension,String displayExtension) throws  GroupAddException, InsufficientPrivilegeException, StemNotFoundException, GridGrouperRuntimeException;

    public void deleteGroup(String callerIdentity, GroupIdentifier group) throws InsufficientPrivilegeException, GroupDeleteException, GroupNotFoundException, GridGrouperRuntimeException;
    
    public StemDescriptor updateStem(String callerIdentity, StemIdentifier stem,StemUpdate update) throws StemModifyException, GridGrouperRuntimeException, InsufficientPrivilegeException;

    public GroupDescriptor updateGroup(String callerIdentity, GroupIdentifier group,GroupUpdate update) throws InsufficientPrivilegeException, GroupModifyException, GroupNotFoundException, GridGrouperRuntimeException;
    
    public void addMember(String callerIdentity, GroupIdentifier group,String subject) throws InsufficientPrivilegeException, MemberAddException, GroupNotFoundException, GridGrouperRuntimeException;

    public List<MemberDescriptor> getMembers(String callerIdentity, GroupIdentifier group, MemberFilter filter) throws GridGrouperRuntimeException,  GroupNotFoundException;
    
    public boolean isMemberOf(String callerIdentity, GroupIdentifier group,String member, MemberFilter filter) throws GridGrouperRuntimeException, GroupNotFoundException;

    public List<MembershipDescriptor> getMemberships(String callerIdentity, GroupIdentifier group, MemberFilter filter) throws GridGrouperRuntimeException, GroupNotFoundException;

    public void deleteMember(String callerIdentity, GroupIdentifier group,String member) throws  GroupNotFoundException, InsufficientPrivilegeException, MemberDeleteException, GridGrouperRuntimeException;
    
    public GroupDescriptor addCompositeMember(String callerIdentity, GroupCompositeType type,GroupIdentifier composite,GroupIdentifier left,GroupIdentifier right) throws InsufficientPrivilegeException, MemberAddException, GroupNotFoundException, GridGrouperRuntimeException;
    
    public GroupDescriptor deleteCompositeMember(String callerIdentity, GroupIdentifier group) throws InsufficientPrivilegeException, GroupNotFoundException, MemberDeleteException, GridGrouperRuntimeException;

    public void grantGroupPrivilege(String callerIdentity, GroupIdentifier group,String subject, GroupPrivilegeType privilege) throws GrantPrivilegeException, InsufficientPrivilegeException, GroupNotFoundException, GridGrouperRuntimeException;
    
    public void revokeGroupPrivilege(String callerIdentity, GroupIdentifier group,String subject, GroupPrivilegeType privilege) throws  GroupNotFoundException, RevokePrivilegeException, SchemaException, InsufficientPrivilegeException, GridGrouperRuntimeException;

    public List<String> getSubjectsWithGroupPrivilege(String callerIdentity, GroupIdentifier group, GroupPrivilegeType privilege) throws GridGrouperRuntimeException,  GroupNotFoundException;

    public List<GroupPrivilege> getGroupPrivileges(String callerIdentity, GroupIdentifier group, String subject) throws GridGrouperRuntimeException, GroupNotFoundException;

    public boolean hasGroupPrivilege(String callerIdentity, GroupIdentifier group,String subject, GroupPrivilegeType privilege) throws GridGrouperRuntimeException, GroupNotFoundException;

    public boolean isMember(String callerIdentity, String member, MembershipExpression expression) throws GridGrouperRuntimeException;

    public MemberDescriptor getMember(String callerIdentity, String member) throws GridGrouperRuntimeException, InsufficientPrivilegeException;

    public List<GroupDescriptor> getMembersGroups(String callerIdentity, String member, MembershipType type) throws GridGrouperRuntimeException, InsufficientPrivilegeException;

    public void addMembershipRequest(String callerIdentity, GroupIdentifier group) throws InsufficientPrivilegeException, MemberAddException, GroupNotFoundException, GridGrouperRuntimeException;

    public MembershipRequestDescriptor updateMembershipRequest(String callerIdentity, GroupIdentifier group,String subject, MembershipRequestUpdate update) throws InsufficientPrivilegeException, MemberAddException, GroupNotFoundException, GridGrouperRuntimeException;
    
    public List<MembershipRequestDescriptor> getMembershipRequests(String callerIdentity, GroupIdentifier group, MembershipRequestStatus status) throws GridGrouperRuntimeException,  GroupNotFoundException;

    public void enableMembershipRequests(String callerIdentity, GroupIdentifier group) throws GrantPrivilegeException, InsufficientPrivilegeException, GroupNotFoundException, GridGrouperRuntimeException;

    public void disableMembershipRequests(String callerIdentity, GroupIdentifier group) throws  GroupNotFoundException, RevokePrivilegeException, SchemaException, InsufficientPrivilegeException, GridGrouperRuntimeException;

    public boolean isMembershipRequestEnabled(String callerIdentity, GroupIdentifier group) throws GrantPrivilegeException, InsufficientPrivilegeException, GroupNotFoundException, GridGrouperRuntimeException;

    public ResourceHome getResourceHome();
}
