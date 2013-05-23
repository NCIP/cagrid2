package org.cagrid.gridgrouper.service.impl;

import edu.internet2.middleware.grouper.AccessPrivilege;
import edu.internet2.middleware.grouper.CompositeType;
import edu.internet2.middleware.grouper.Group;
import edu.internet2.middleware.grouper.GroupFinder;
import edu.internet2.middleware.grouper.GrouperSession;
import edu.internet2.middleware.grouper.GrouperSourceAdapter;
import edu.internet2.middleware.grouper.Member;
import edu.internet2.middleware.grouper.MemberFinder;
import edu.internet2.middleware.grouper.Membership;
import edu.internet2.middleware.grouper.MembershipRequest;
import edu.internet2.middleware.grouper.MembershipRequestFinder;
import edu.internet2.middleware.grouper.MembershipRequestHistory;
import edu.internet2.middleware.grouper.NamingPrivilege;
import edu.internet2.middleware.grouper.Privilege;
import edu.internet2.middleware.grouper.Stem;
import edu.internet2.middleware.grouper.StemFinder;
import edu.internet2.middleware.grouper.SubjectFinder;
import edu.internet2.middleware.subject.GridSourceAdapter;
import edu.internet2.middleware.subject.Subject;
import edu.internet2.middleware.subject.SubjectNotFoundException;
import edu.internet2.middleware.subject.provider.SubjectTypeEnum;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.cagrid.gridgrouper.model.GroupCompositeType;
import org.cagrid.gridgrouper.model.GroupDescriptor;
import org.cagrid.gridgrouper.model.GroupIdentifier;
import org.cagrid.gridgrouper.model.GroupPrivilege;
import org.cagrid.gridgrouper.model.GroupPrivilegeType;
import org.cagrid.gridgrouper.model.GroupUpdate;
import org.cagrid.gridgrouper.model.LogicalOperator;
import org.cagrid.gridgrouper.model.MemberDescriptor;
import org.cagrid.gridgrouper.model.MemberFilter;
import org.cagrid.gridgrouper.model.MemberType;
import org.cagrid.gridgrouper.model.MembershipDescriptor;
import org.cagrid.gridgrouper.model.MembershipExpression;
import org.cagrid.gridgrouper.model.MembershipQuery;
import org.cagrid.gridgrouper.model.MembershipRequestDescriptor;
import org.cagrid.gridgrouper.model.MembershipRequestHistoryDescriptor;
import org.cagrid.gridgrouper.model.MembershipRequestStatus;
import org.cagrid.gridgrouper.model.MembershipRequestUpdate;
import org.cagrid.gridgrouper.model.MembershipStatus;
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
import org.cagrid.gridgrouper.service.impl.util.SubjectUtils;
import org.cagrid.gridgrouper.service.impl.util.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GridGrouper {

    public static final String GROUPER_SUPER_USER = "GrouperSystem";

    public static final String GROUPER_ADMIN_STEM_NAME = "grouperadministration";

    public static final String GROUPER_ADMIN_STEM_DISPLAY_NAME = "Grouper Administration";

    public static final String GROUPER_ADMIN_GROUP_NAME_EXTENTION = "gridgrouperadministrators";

    public static final String GROUPER_ADMIN_GROUP_DISPLAY_NAME_EXTENTION = "Grid Grouper Administrators";

    public static final String GROUPER_ADMIN_GROUP_NAME = "grouperadministration:gridgrouperadministrators";

    public static final String UNKNOWN_SUBJECT = "Unknown";

    private Group adminGroup;

    private Logger log;


    public GridGrouper() throws GridGrouperRuntimeException {
        this.log = LoggerFactory.getLogger(this.getClass().getName());
        
        try {
            GrouperSession session = GrouperSession.start(SubjectFinder.findById(GROUPER_SUPER_USER));
            Stem adminStem;
            try {
                adminStem = StemFinder.findByName(session, GROUPER_ADMIN_STEM_NAME);
            } catch (edu.internet2.middleware.grouper.StemNotFoundException e) {
                Stem root = StemFinder.findRootStem(session);
                adminStem = root.addChildStem(GROUPER_ADMIN_STEM_NAME, GROUPER_ADMIN_STEM_DISPLAY_NAME);
            }
            try {
                this.adminGroup = GroupFinder.findByName(session, GROUPER_ADMIN_GROUP_NAME);
            } catch (edu.internet2.middleware.grouper.GroupNotFoundException gne) {
                this.adminGroup = adminStem.addChildGroup(GROUPER_ADMIN_GROUP_NAME_EXTENTION,
                        GROUPER_ADMIN_GROUP_DISPLAY_NAME_EXTENTION);
            }
        } catch (Exception e) {
            this.log.error(ExceptionUtils.getFullStackTrace(e));
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred initializing Grid Grouper: " + e.getMessage(), e);
        }
    }


    public StemDescriptor getStem(String gridIdentity, StemIdentifier stemId) throws GridGrouperRuntimeException, StemNotFoundException {
        GrouperSession session = null;
        try {
            Subject subject = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subject);
            StemDescriptor des = null;
            Stem stem = StemFinder.findByName(session, stemId.getStemName());
            des = stemtoStemDescriptor(stem);

            return des;
        } catch (edu.internet2.middleware.grouper.StemNotFoundException e) {
            this.log.error(e.getMessage(), e);
            throw Errors.makeException(StemNotFoundException.class, "The stem, " + stemId.getStemName() + "was not found.", e);
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred getting the stem " + stemId.getStemName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public List<StemDescriptor> getChildStems(String gridIdentity, StemIdentifier parentStemId) throws RemoteException,
            GridGrouperRuntimeException, StemNotFoundException {
        GrouperSession session = null;
        try {
            Subject subject = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subject);
            Stem parent = StemFinder.findByName(session, parentStemId.getStemName());
            Set set = parent.getChildStems();
            if (set == null) {
                return Collections.emptyList();
            }
            return new ArrayList<StemDescriptor>(set);
        } catch (edu.internet2.middleware.grouper.StemNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(StemNotFoundException.class, "The parent stem, " + parentStemId.getStemName() + "was not found.", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);             
            throw Errors.makeException(StemNotFoundException.class, "Error occurred getting the child stems for the parent stem, "
                    + parentStemId.getStemName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public StemDescriptor getParentStem(String gridIdentity, StemIdentifier childStemId) throws RemoteException,
            GridGrouperRuntimeException, StemNotFoundException {
        GrouperSession session = null;
        try {
            Subject subject = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subject);
            StemDescriptor parent = null;
            Stem child = StemFinder.findByName(session, childStemId.getStemName());
            Stem s = child.getParentStem();
            parent = stemtoStemDescriptor(s);

            return parent;
        } catch (edu.internet2.middleware.grouper.StemNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(StemNotFoundException.class, "The parent stem for the child " + childStemId.getStemName()
                    + " could not be found!!!", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);             
            throw Errors.makeException(StemNotFoundException.class, "Error occurred getting the parent stem for the child stem, "
                    + childStemId.getStemName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public StemDescriptor updateStem(String gridIdentity, StemIdentifier stem, StemUpdate update)
            throws GridGrouperRuntimeException, InsufficientPrivilegeException, StemModifyException {
        GrouperSession session = null;
        try {
            Subject subject = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subject);
            StemDescriptor des = null;
            Stem target = StemFinder.findByName(session, stem.getStemName());
            if ((update.getDescription() != null) && (!update.getDescription().equals(target.getDescription()))) {
                target.setDescription(update.getDescription());
            }

            if ((update.getDisplayExtension() != null)
                    && (!update.getDisplayExtension().equals(target.getDisplayExtension()))) {
                target.setDisplayExtension(update.getDisplayExtension());
            }

            des = stemtoStemDescriptor(target);
            return des;
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);                          
            throw Errors.makeException(InsufficientPrivilegeException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.StemModifyException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(StemModifyException.class, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred getting the stem " + stem.getStemName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }

    }


    public List<String> getSubjectsWithStemPrivilege(String gridIdentity, StemIdentifier stem, StemPrivilegeType privilege)
            throws RemoteException, GridGrouperRuntimeException, StemNotFoundException {
        GrouperSession session = null;
        try {
            Subject subject = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subject);
            Stem target = StemFinder.findByName(session, stem.getStemName());
            Set<Subject> subs;
            if (privilege.equals(StemPrivilegeType.CREATE)) {
                subs = target.getCreators();
            } else if (privilege.equals(StemPrivilegeType.STEM)) {
                subs = target.getStemmers();
            } else {
                throw new Exception(privilege.value() + " is not a valid stem privilege!!!");
            }
            List<String> subjects = new ArrayList<String>();
            if (subs != null) {
                for(Subject s : subs) {
                    subjects.add(s.getId());
                }
            }
            return subjects;
        } catch (edu.internet2.middleware.grouper.StemNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(StemNotFoundException.class, "The stem " + stem.getStemName() + " could not be found!!!", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred getting the subjects with the privilege " + privilege.value()
                    + " on the stem " + stem.getStemName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public List<StemPrivilege> getStemPrivileges(String gridIdentity, StemIdentifier stem, String subject)
            throws RemoteException, GridGrouperRuntimeException, StemNotFoundException {
        GrouperSession session = null;
        try {

            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Stem target = StemFinder.findByName(session, stem.getStemName());
            Set privs = target.getPrivs(SubjectFinder.findById(subject));
            int size = 0;
            if (privs != null) {
                size = privs.size();
            }
            List<StemPrivilege> rights = new ArrayList<StemPrivilege>();
            if (privs != null) {
                Iterator itr = privs.iterator();
                int count = 0;
                while (itr.hasNext()) {
                    NamingPrivilege p = (NamingPrivilege) itr.next();
                    StemPrivilege right = new StemPrivilege();
                    right.setStemName(p.getStem().getName());
                    right.setImplementationClass(p.getImplementationName());
                    right.setIsRevokable(p.isRevokable());
                    right.setOwner(p.getOwner().getId());
                    right.setPrivilegeType(StemPrivilegeType.fromValue(p.getName()));
                    right.setSubject(p.getSubject().getId());
                    rights.add(right);
                }
            }
            return rights;
        } catch (edu.internet2.middleware.grouper.StemNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(StemNotFoundException.class, "The stem " + stem.getStemName() + " could not be found!!!", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);             
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred getting the privileges for the subject " + subject + " on the stem "
                    + stem.getStemName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public boolean hasStemPrivilege(String gridIdentity, StemIdentifier stem, String subject,
                                    StemPrivilegeType privilege) throws GridGrouperRuntimeException, StemNotFoundException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Stem target = StemFinder.findByName(session, stem.getStemName());
            if (privilege == null) {
                return false;
            } else if (privilege.equals(StemPrivilegeType.CREATE)) {
                return target.hasCreate(SubjectFinder.findById(subject));
            } else if (privilege.equals(StemPrivilegeType.STEM)) {
                return target.hasStem(SubjectFinder.findById(subject));
            } else {
                return false;
            }

        } catch (edu.internet2.middleware.grouper.StemNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(StemNotFoundException.class, "The stem " + stem.getStemName() + " could not be found!!!", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);             
            throw Errors.makeException(StemNotFoundException.class, "Error determing if the subject " + subject + " has the privilege "
                    + privilege.value() + " on the stem " + stem.getStemName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public void grantStemPrivilege(String gridIdentity, StemIdentifier stem, String subject, StemPrivilegeType privilege)
            throws GridGrouperRuntimeException, StemNotFoundException, GrantPrivilegeException, InsufficientPrivilegeException, SchemaException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Stem target = StemFinder.findByName(session, stem.getStemName());
            target.grantPriv(SubjectFinder.findById(subject), Privilege.getInstance(privilege.value()));
        } catch (edu.internet2.middleware.grouper.GrantPrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GrantPrivilegeException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(InsufficientPrivilegeException.class, "You do not have the right to manages privileges on the stem " + stem.getStemName()
                    + ": " + e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.SchemaException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(SchemaException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.StemNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(StemNotFoundException.class, "The stem " + stem.getStemName() + " could not be found!!!", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);             
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred granting a privilege for the subject " + subject + " on the stem "
                    + stem.getStemName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public void revokeStemPrivilege(String gridIdentity, StemIdentifier stem, String subject,
                                    StemPrivilegeType privilege) throws GridGrouperRuntimeException, StemNotFoundException, InsufficientPrivilegeException,
            RevokePrivilegeException, SchemaException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Stem target = StemFinder.findByName(session, stem.getStemName());
            target.revokePriv(SubjectFinder.findById(subject), Privilege.getInstance(privilege.value()));
        } catch (edu.internet2.middleware.grouper.RevokePrivilegeException e) {   
            log.error(e.getMessage(), e);
            throw Errors.makeException(RevokePrivilegeException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);                          
            throw Errors.makeException(InsufficientPrivilegeException.class, "You do not have the right to manages privileges on the stem " + stem.getStemName()
                    + ": " + e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.SchemaException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(SchemaException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.StemNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(StemNotFoundException.class, "The stem " + stem.getStemName() + " could not be found!!!", e);
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);           
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred getting the privileges for the subject " + subject + " on the stem "
                    + stem.getStemName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public StemDescriptor addChildStem(String gridIdentity, StemIdentifier stem, String extension,
                                       String displayExtension) throws GridGrouperRuntimeException, InsufficientPrivilegeException, StemAddException,
            StemNotFoundException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Stem target = StemFinder.findByName(session, stem.getStemName());
            Stem child = target.addChildStem(extension, displayExtension);
            return stemtoStemDescriptor(child);
        } catch (edu.internet2.middleware.grouper.StemAddException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(StemAddException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(InsufficientPrivilegeException.class, "You do not have the right to add children to the stem " + stem.getStemName() + ": "
                    + e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.StemNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(StemNotFoundException.class, "The stem " + stem.getStemName() + " could not be found!!!", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred adding the child " + extension + " to the stem " + stem.getStemName()
                    + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public void deleteStem(String gridIdentity, StemIdentifier stem) throws GridGrouperRuntimeException,
            InsufficientPrivilegeException, StemDeleteException, StemNotFoundException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Stem target = StemFinder.findByName(session, stem.getStemName());
            target.delete();
        } catch (edu.internet2.middleware.grouper.StemDeleteException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(StemDeleteException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(InsufficientPrivilegeException.class, "You do not have the right to add children to the stem " + stem.getStemName() + ": "
                    + e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.StemNotFoundException e) {
            log.error(e.getMessage(), e);
            Errors.makeException(StemNotFoundException.class, "The stem " + stem.getStemName() + " could not be found!!!", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "An error occurred in deleting the stem " + stem.getStemName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public List<GroupDescriptor> getChildGroups(String gridIdentity, StemIdentifier stem) throws GridGrouperRuntimeException,
            StemNotFoundException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Stem target = StemFinder.findByName(session, stem.getStemName());
            Set set = target.getChildGroups();
            if (set == null) {
                return Collections.emptyList();
            }
            return new ArrayList<GroupDescriptor>(set);
        } catch (edu.internet2.middleware.grouper.StemNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(StemNotFoundException.class, "The stem " + stem.getStemName() + " could not be found!!!", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "An error occurred in getting the child groups for the stem " + stem.getStemName()
                    + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public GroupDescriptor addChildGroup(String gridIdentity, StemIdentifier stem,
                                                                             String extension, String displayExtension) throws RemoteException, StemNotFoundException, GridGrouperRuntimeException, GroupAddException,
            InsufficientPrivilegeException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Stem target = StemFinder.findByName(session, stem.getStemName());
            Group child = target.addChildGroup(extension, displayExtension);
            return grouptoGroupDescriptor(child);
        } catch (edu.internet2.middleware.grouper.GroupAddException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupAddException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);                       
            throw Errors.makeException(InsufficientPrivilegeException.class, "You do not have the right to add groups to the stem " + stem.getStemName() + ": "
                    + e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.StemNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(StemNotFoundException.class, "The stem " + stem.getStemName() + " could not be found!!!", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);             
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred adding the group " + extension + " to the stem " + stem.getStemName()
                    + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public GroupDescriptor getGroup(String gridIdentity, GroupIdentifier group) throws GridGrouperRuntimeException,
            GroupNotFoundException {
        GrouperSession session = null;
        try {
            Subject subject = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subject);
            Group grp = GroupFinder.findByName(session, group.getGroupName());
            return grouptoGroupDescriptor(grp);
        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);             
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred getting the group " + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public void deleteGroup(String gridIdentity, GroupIdentifier group) throws GridGrouperRuntimeException,
            GroupNotFoundException, GroupDeleteException, InsufficientPrivilegeException {
        GrouperSession session = null;
        try {
            Subject subject = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subject);
            Group grp = GroupFinder.findByName(session, group.getGroupName());
            grp.delete();
        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (edu.internet2.middleware.grouper.GroupDeleteException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupDeleteException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(InsufficientPrivilegeException.class, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred deleting the group " + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }

    }


    public GroupDescriptor updateGroup(String gridIdentity, GroupIdentifier group, GroupUpdate update)
            throws GridGrouperRuntimeException, GroupNotFoundException, GroupModifyException, InsufficientPrivilegeException {
        GrouperSession session = null;
        try {
            Subject subject = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subject);
            Group grp = GroupFinder.findByName(session, group.getGroupName());
            if ((update.getDescription() != null) && (!update.getDescription().equals(grp.getDescription()))) {
                grp.setDescription(update.getDescription());
            }

            if ((update.getExtension() != null) && (!update.getExtension().equals(grp.getExtension()))) {
                grp.setExtension(update.getExtension());
            }

            if ((update.getDisplayExtension() != null)
                    && (!update.getDisplayExtension().equals(grp.getDisplayExtension()))) {
                grp.setDisplayExtension(update.getDisplayExtension());
            }
            return grouptoGroupDescriptor(grp);
        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);                          
            throw Errors.makeException(InsufficientPrivilegeException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.GroupModifyException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupModifyException.class, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);             
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred updating the group " + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public void addMember(String gridIdentity, GroupIdentifier group, String subject) throws GridGrouperRuntimeException,
            GroupNotFoundException, InsufficientPrivilegeException, MemberAddException {
        GrouperSession session = null;
        try {
            Subject caller = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(caller);
            Group grp = GroupFinder.findByName(session, group.getGroupName());
            grp.addMember(SubjectFinder.findById(subject));
        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (edu.internet2.middleware.grouper.MemberAddException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(MemberAddException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(InsufficientPrivilegeException.class, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred adding a member to the group " + group.getGroupName() + ": "
                    + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public List<MemberDescriptor> getMembers(String gridIdentity, GroupIdentifier group, MemberFilter filter)
            throws RemoteException, GridGrouperRuntimeException, GroupNotFoundException {
        GrouperSession session = null;
        try {
            Subject caller = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(caller);
            Group target = GroupFinder.findByName(session, group.getGroupName());
            Set set = null;
            if (MemberFilter.ALL.equals(filter)) {
                set = target.getMembers();
            } else if (MemberFilter.EFFECTIVE_MEMBERS.equals(filter)) {
                set = target.getEffectiveMembers();
            } else if (MemberFilter.IMMEDIATE_MEMBERS.equals(filter)) {
                set = target.getImmediateMembers();
            } else if (MemberFilter.COMPOSITE_MEMBERS.equals(filter)) {
                set = target.getCompositeMembers();
            } else {
                throw new Exception("Unsupported member filter type!!!");
            }

            List<MemberDescriptor> members = new ArrayList<MemberDescriptor>();
            Iterator itr = set.iterator();
            while (itr.hasNext()) {
                Member m = (Member) itr.next();
                members.add(memberToMemberDescriptor(m));
            }
            return members;
        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + " was not found.", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred getting the members of the group " + group.getGroupName() + ": "
                    + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public boolean isMemberOf(GrouperSession session, GroupIdentifier group, String member, MemberFilter filter)
            throws GridGrouperRuntimeException, GroupNotFoundException {
        try {
            Group target = GroupFinder.findByName(session, group.getGroupName());
            if (filter.equals(MemberFilter.ALL)) {
                return target.hasMember(SubjectFinder.findById(member));
            } else if (filter.equals(MemberFilter.EFFECTIVE_MEMBERS)) {
                return target.hasEffectiveMember(SubjectFinder.findById(member));
            } else if (filter.equals(MemberFilter.IMMEDIATE_MEMBERS)) {
                return target.hasImmediateMember(SubjectFinder.findById(member));
            } else {
                throw new Exception("Unsuppoted member filter type!!!");
            }

        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred determining if " + member + " is a member of the group "
                    + group.getGroupName() + ": " + e.getMessage(), e);
        }
    }


    public boolean isMemberOf(String gridIdentity, GroupIdentifier group, String member, MemberFilter filter)
            throws GridGrouperRuntimeException, GroupNotFoundException {
        GrouperSession session = null;
        try {
            Subject caller = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(caller);
            return isMemberOf(session, group, member, filter);
        } catch (GridGrouperRuntimeException e) {
            throw e;
        } catch (GroupNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred determining if " + member + " is a member of the group "
                    + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }

    }


    public List<MembershipDescriptor> getMemberships(String gridIdentity, GroupIdentifier group, MemberFilter filter)
            throws RemoteException, GridGrouperRuntimeException, GroupNotFoundException{
        GrouperSession session = null;
        try {
            Subject caller = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(caller);
            Group target = GroupFinder.findByName(session, group.getGroupName());
            Set set = null;
            if (filter.equals(MemberFilter.ALL)) {
                set = target.getMemberships();
            } else if (filter.equals(MemberFilter.EFFECTIVE_MEMBERS)) {
                set = target.getEffectiveMemberships();
            } else if (filter.equals(MemberFilter.IMMEDIATE_MEMBERS)) {
                set = target.getImmediateMemberships();
            } else if (filter.equals(MemberFilter.COMPOSITE_MEMBERS)) {
                set = target.getCompositeMemberships();
            } else {
                throw new Exception("Unsuppoted member filter type!!!");
            }

            List<MembershipDescriptor> members = new ArrayList<MembershipDescriptor>();
            Iterator itr = set.iterator();
            int count = 0;
            while (itr.hasNext()) {
                Membership m = (Membership) itr.next();
                MembershipDescriptor member = new MembershipDescriptor();
                member.setMember(memberToMemberDescriptor(m.getMember()));
                member.setGroup(grouptoGroupDescriptor(m.getGroup()));
                try {
                    member.setViaGroup(grouptoGroupDescriptor(m.getViaGroup()));
                } catch (edu.internet2.middleware.grouper.GroupNotFoundException gnfe) {

                }

                member.setDepth(m.getDepth());
                members.add(member);
            }
            return members;
        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred getting the members of the group " + group.getGroupName() + ": "
                    + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }

    }


    public Group getAdminGroup() {
        return this.adminGroup;
    }


    private MemberDescriptor memberToMemberDescriptor(Member m) throws Exception {
        MemberDescriptor member = new MemberDescriptor();
        member.setUUID(m.getUuid());
        member.setSubjectId(m.getSubjectId());
        member.setSubjectName(m.getSubject().getName());
        if (m.getSubject().getSource().getClass().getName().equals(GridSourceAdapter.class.getName())) {
            member.setMemberType(MemberType.GRID);

        } else if ((m.getSubjectType().equals(SubjectTypeEnum.GROUP))
                && (m.getSubject().getSource().getClass().getName().equals(GrouperSourceAdapter.class.getName()))) {
            member.setMemberType(MemberType.GROUPER_GROUP);
        } else {
            member.setMemberType(MemberType.OTHER);
        }
        return member;
    }


    private GroupDescriptor grouptoGroupDescriptor(Group group) throws Exception {
        GroupDescriptor des = new GroupDescriptor();
        des.setCreateSource(group.getCreateSource());
        des.setParentStem(group.getParentStem().getName());
        des.setCreateSubject(group.getCreateSubject().getId());
        des.setCreateTime(group.getCreateTime().getTime());
        des.setDescription(group.getDescription());
        des.setDisplayExtension(group.getDisplayExtension());
        des.setDisplayName(group.getDisplayName());
        des.setExtension(group.getExtension());
        des.setModifySource(group.getModifySource());
        try {
            des.setModifySubject(group.getModifySubject().getId());
        } catch (Exception ex) {
            if (ex.getMessage().indexOf("has not been modified") != -1) {
                des.setModifySubject("");
            } else {
                throw ex;
            }
        }
        des.setModifyTime(group.getModifyTime().getTime());
        des.setName(group.getName());
        des.setUUID(group.getUuid());
        des.setHasComposite(group.hasComposite());
        des.setIsComposite(group.isComposite());

        return des;
    }

    private MembershipRequestDescriptor membershiprequesttoMembershipRequestDescriptor(MembershipRequest membershipRequest) throws Exception {
        MembershipRequestDescriptor des = new MembershipRequestDescriptor();
        des.setGroup(grouptoGroupDescriptor(membershipRequest.getGroup()));
        des.setUUID(membershipRequest.getId());
        des.setRequestorId(membershipRequest.getRequestorId());
        des.setRequestTime(membershipRequest.getRequestTime());
        des.setStatus(membershipRequest.getStatus());
        if (membershipRequest.getReviewer() != null) {
            des.setReviewer(memberToMemberDescriptor(membershipRequest.getReviewer()));
        }
        des.setReviewTime(membershipRequest.getReviewTime());
        des.setPublicNote(membershipRequest.getPublicNote());
        des.setAdminNote(membershipRequest.getAdminNote());

        ArrayList<MembershipRequestHistory> history = membershipRequest.getHistory();
        if (history != null && history.size() != 0) {

            for (int i = 0; i < history.size(); i++) {
                des.getHistory().add(membershiprequesthistorytoMembershipRequestHistoryDescriptor(history.get(i)));
            }
        }
        return des;
    }

    private MembershipRequestHistoryDescriptor membershiprequesthistorytoMembershipRequestHistoryDescriptor(MembershipRequestHistory membershipRequestHistory) throws Exception {
        MembershipRequestHistoryDescriptor des = new MembershipRequestHistoryDescriptor();
        des.setUUID(membershipRequestHistory.getId());
        des.setStatus(membershipRequestHistory.getStatus());
        if (membershipRequestHistory.getReviewer() != null) {
            des.setReviewer(memberToMemberDescriptor(membershipRequestHistory.getReviewer()));
        }
        des.setUpdateDate(membershipRequestHistory.getUpdateDate());
        des.setPublicNote(membershipRequestHistory.getPublicNote());
        des.setAdminNote(membershipRequestHistory.getAdminNote());
        return des;
    }


    private StemDescriptor stemtoStemDescriptor(Stem stem) throws Exception {
        StemDescriptor des = new StemDescriptor();
        des.setCreateSource(stem.getCreateSource());
        des.setCreateSubject(stem.getCreateSubject().getId());
        des.setCreateTime(stem.getCreateTime().getTime());
        des.setDescription(stem.getDescription());
        des.setDisplayExtension(stem.getDisplayExtension());
        des.setDisplayName(stem.getDisplayName());
        des.setExtension(stem.getExtension());
        des.setModifySource(stem.getModifySource());
        try {
            des.setModifySubject(stem.getModifySubject().getId());
        } catch (Exception ex) {
            if (ex.getMessage().indexOf("has not been modified") != -1) {
                des.setModifySubject("");
            } else {
                throw ex;
            }
        }
        des.setModifyTime(stem.getModifyTime().getTime());
        des.setName(stem.getName());
        des.setUUID(stem.getUuid());
        return des;
    }


    public void deleteMember(String gridIdentity, GroupIdentifier group, String member) throws RemoteException,
            GridGrouperRuntimeException, InsufficientPrivilegeException, GroupNotFoundException, MemberDeleteException {
        GrouperSession session = null;
        try {
            Subject caller = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(caller);
            Group grp = GroupFinder.findByName(session, group.getGroupName());
            grp.deleteMember(SubjectFinder.findById(member));
            MembershipRequestFinder.removeRequest(caller, grp, member);
        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (edu.internet2.middleware.grouper.MemberDeleteException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(MemberDeleteException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(InsufficientPrivilegeException.class, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred deleting the member " + member + " from the group "
                    + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public GroupDescriptor addCompositeMember(String gridIdentity, GroupCompositeType type, GroupIdentifier composite,
                                              GroupIdentifier left, GroupIdentifier right) throws GridGrouperRuntimeException, GroupNotFoundException,
            MemberAddException, InsufficientPrivilegeException {
        GrouperSession session = null;
        try {
            Subject caller = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(caller);
            Group grp = GroupFinder.findByName(session, composite.getGroupName());
            Group leftgrp = GroupFinder.findByName(session, left.getGroupName());
            Group rightgrp = GroupFinder.findByName(session, right.getGroupName());
            CompositeType ct = null;
            if (type.equals(GroupCompositeType.UNION)) {
                ct = CompositeType.UNION;
            } else if (type.equals(GroupCompositeType.INTERSECTION)) {
                ct = CompositeType.INTERSECTION;
            } else if (type.equals(GroupCompositeType.COMPLEMENT)) {
                ct = CompositeType.COMPLEMENT;
            } else {
                throw new Exception("The composite type " + type.value() + " is not supported!!!");
            }
            grp.addCompositeMember(ct, leftgrp, rightgrp);
            return grouptoGroupDescriptor(grp);
        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.MemberAddException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(MemberAddException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(InsufficientPrivilegeException.class, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred adding a composite member to the group " + composite.getGroupName()
                    + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public GroupDescriptor deleteCompositeMember(String gridIdentity, GroupIdentifier group)
            throws GridGrouperRuntimeException, GroupNotFoundException, InsufficientPrivilegeException, MemberDeleteException {
        GrouperSession session = null;
        try {
            Subject caller = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(caller);
            Group grp = GroupFinder.findByName(session, group.getGroupName());
            grp.deleteCompositeMember();
            return grouptoGroupDescriptor(grp);
        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (edu.internet2.middleware.grouper.MemberDeleteException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(MemberDeleteException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(InsufficientPrivilegeException.class, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred deleting the composite member from the group " + group.getGroupName()
                    + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public void grantGroupPrivilege(String gridIdentity, GroupIdentifier group, String subject,
                                    GroupPrivilegeType privilege) throws GridGrouperRuntimeException, GroupNotFoundException, GrantPrivilegeException,
            InsufficientPrivilegeException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Group grp = GroupFinder.findByName(session, group.getGroupName());

            grp.grantPriv(SubjectFinder.findById(subject), Privilege.getInstance(privilege.value()));

        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (edu.internet2.middleware.grouper.GrantPrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GrantPrivilegeException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(InsufficientPrivilegeException.class, "You do not have the right to manages privileges on the group " + group.getGroupName()
                    + ": " + e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred granting a privilege for the subject " + subject + " on the group "
                    + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public void revokeGroupPrivilege(String gridIdentity, GroupIdentifier group, String subject,
                                     GroupPrivilegeType privilege) throws RemoteException, GridGrouperRuntimeException, GroupNotFoundException,
            RevokePrivilegeException, InsufficientPrivilegeException, SchemaException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Group grp = GroupFinder.findByName(session, group.getGroupName());

            grp.revokePriv(SubjectFinder.findById(subject), Privilege.getInstance(privilege.value()));

        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (edu.internet2.middleware.grouper.RevokePrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(RevokePrivilegeException.class, e.getMessage(), e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(InsufficientPrivilegeException.class, "You do not have the right to manages privileges on the group " + group.getGroupName()
                    + ": " + e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred revoking a privilege for the subject " + subject + " on the group "
                    + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public List<String> getSubjectsWithGroupPrivilege(String gridIdentity, GroupIdentifier group,
                                                  GroupPrivilegeType privilege) throws RemoteException, GridGrouperRuntimeException, GroupNotFoundException {
        GrouperSession session = null;
        try {
            Subject subject = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subject);
            Group grp = GroupFinder.findByName(session, group.getGroupName());
            Set subs = null;
            if (privilege.equals(GroupPrivilegeType.ADMIN)) {
                subs = grp.getAdmins();
            } else if (privilege.equals(GroupPrivilegeType.OPTIN)) {
                subs = grp.getOptins();
            } else if (privilege.equals(GroupPrivilegeType.OPTOUT)) {
                subs = grp.getOptouts();
            } else if (privilege.equals(GroupPrivilegeType.READ)) {
                subs = grp.getReaders();
            } else if (privilege.equals(GroupPrivilegeType.UPDATE)) {
                subs = grp.getUpdaters();
            } else if (privilege.equals(GroupPrivilegeType.VIEW)) {
                subs = grp.getViewers();
            } else {
                throw new Exception(privilege.value() + " is not a valid group privilege!!!");
            }
            List<String> subjects = new ArrayList<String>();
            if (subs != null) {
                Iterator itr = subs.iterator();
                while (itr.hasNext()) {
                    Subject s = (Subject) itr.next();
                    subjects.add(s.getId());
                }
            }
            return subjects;
        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred getting the subjects with the privilege " + privilege.value()
                    + " on the group " + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public List<GroupPrivilege> getGroupPrivileges(String gridIdentity, GroupIdentifier group, String subject)
            throws GridGrouperRuntimeException, GroupNotFoundException {
        GrouperSession session = null;
        try {

            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Group grp = GroupFinder.findByName(session, group.getGroupName());
            Set privs = grp.getPrivs(SubjectFinder.findById(subject));
            int size = 0;
            if (privs != null) {
                size = privs.size();
            }
            List<GroupPrivilege> rights = new ArrayList<GroupPrivilege>();
            if (privs != null) {
                Iterator itr = privs.iterator();
                while (itr.hasNext()) {
                    AccessPrivilege p = (AccessPrivilege) itr.next();
                    GroupPrivilege right = new GroupPrivilege();
                    right.setGroupName(p.getGroup().getName());
                    right.setImplementationClass(p.getImplementationName());
                    right.setIsRevokable(p.isRevokable());
                    right.setOwner(p.getOwner().getId());
                    right.setPrivilegeType(GroupPrivilegeType.fromValue(p.getName()));
                    right.setSubject(p.getSubject().getId());
                    rights.add(right);
                }
            }
            return rights;
        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred getting the privileges for the subject " + subject + " on the group "
                    + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public boolean hasGroupPrivilege(String gridIdentity, GroupIdentifier group, String subject,
                                     GroupPrivilegeType privilege) throws GridGrouperRuntimeException, GroupNotFoundException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Group target = GroupFinder.findByName(session, group.getGroupName());
            if (privilege == null) {
                return false;
            } else if (privilege.equals(GroupPrivilegeType.ADMIN)) {
                return target.hasAdmin(SubjectFinder.findById(subject));
            } else if (privilege.equals(GroupPrivilegeType.OPTIN)) {
                return target.hasOptin(SubjectFinder.findById(subject));
            } else if (privilege.equals(GroupPrivilegeType.OPTOUT)) {
                return target.hasOptout(SubjectFinder.findById(subject));
            } else if (privilege.equals(GroupPrivilegeType.READ)) {
                return target.hasRead(SubjectFinder.findById(subject));
            } else if (privilege.equals(GroupPrivilegeType.UPDATE)) {
                return target.hasUpdate(SubjectFinder.findById(subject));
            } else if (privilege.equals(GroupPrivilegeType.VIEW)) {
                return target.hasView(SubjectFinder.findById(subject));
            } else {
                return false;
            }

        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error determing if the subject " + subject + " has the privilege "
                    + privilege.value() + " on the group " + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }

    private void validateMemberAccess(String caller, String member) throws SubjectNotFoundException,
            InsufficientPrivilegeException {
        if ((caller.equals(member)) || (getAdminGroup().hasMember(SubjectUtils.getSubject(caller)))) {
            return;
        } else {
            throw Errors.makeException(InsufficientPrivilegeException.class, "You do not have access to the member " + member + ".");
        }
    }

    public MemberDescriptor getMember(String gridIdentity, String memberIdentity) throws GridGrouperRuntimeException,
            InsufficientPrivilegeException {
        GrouperSession session = null;
        try {
            validateMemberAccess(gridIdentity, memberIdentity);
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Member m = MemberFinder.findBySubject(session, SubjectUtils.getSubject(memberIdentity));
            return memberToMemberDescriptor(m);
            // TODO: We may need to also throw a member not found fault
        } catch (InsufficientPrivilegeException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error finding the member " + memberIdentity + ":\n" + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }

    }


    public List<GroupDescriptor> getMembersGroups(String gridIdentity, String memberIdentity, MembershipType type)
            throws GridGrouperRuntimeException, InsufficientPrivilegeException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Member m = MemberFinder.findBySubject(session, SubjectUtils.getSubject(memberIdentity));
            Set<?> set = null;
            if ((type != null) && (type.equals(MembershipType.EFFECTIVE_MEMBERS))) {
                set = m.getEffectiveGroups();
            } else if ((type != null) && (type.equals(MembershipType.IMMEDIATE_MEMBERS))) {
                set = m.getImmediateGroups();
            } else {
                set = m.getGroups();
            }

            Iterator<?> itr = set.iterator();
            ArrayList<GroupDescriptor> groups = new ArrayList<GroupDescriptor>();
            while (itr.hasNext()) {
                Group group = (Group) itr.next();
                if ((group.hasRead(subj) && group.hasView(subj)) || group.hasUpdate(subj) || group.hasAdmin(subj) || gridIdentity.equals(memberIdentity) || (getAdminGroup().hasMember(subj))) {
                    groups.add(grouptoGroupDescriptor(group));
                }
            }
            return groups;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error finding the member " + memberIdentity + ":\n" + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }

    }


    public boolean isMember(String gridIdentity, String member, MembershipExpression exp)
            throws GridGrouperRuntimeException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            return isMember(session, member, exp);
        } catch (GridGrouperRuntimeException f) {
            throw f;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error determing if the subject " + member + " is a member: " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    private boolean isMember(GrouperSession session, String member, MembershipExpression exp)
            throws GridGrouperRuntimeException {
        if (exp.getLogicRelation().equals(LogicalOperator.AND)) {
            return evaluateAndExpression(session, member, exp);
        } else {
            return evaluateOrExpression(session, member, exp);
        }
    }


    private boolean evaluateAndExpression(GrouperSession session, String member, MembershipExpression exp)
            throws GridGrouperRuntimeException {

        if (exp.getMembershipQueryOrMembershipExpression().size() == 0) {
            throw Errors.makeException(GridGrouperRuntimeException.class, "Invalid Expression");
        }

        for(Serializable obj : exp.getMembershipQueryOrMembershipExpression()) {
            if (obj instanceof MembershipExpression) {
                if (!isMember(session, member, (MembershipExpression)obj)) {
                    return false;
                }
            } else if (obj instanceof MembershipQuery) {
                MembershipQuery query = (MembershipQuery)obj;
                String grpName = query.getGroupIdentifier().getGroupName();
                try {
                    Group grp = GroupFinder.findByName(session, grpName);
                    boolean isMember = grp.hasMember(SubjectFinder.findById(member));
                    if (query.getMembershipStatus().equals(MembershipStatus.NOT_MEMBER_OF)) {
                        if (isMember) {
                            return false;
                        }
                    } else {
                        if (!isMember) {
                            return false;
                        }
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    throw Errors.makeException(GridGrouperRuntimeException.class, "Error in determining if the subject " + member + " is a member of the group "
                            + grpName + ": " + e.getMessage(), e);
                }
            }
        }

        return true;
    }


    private boolean evaluateOrExpression(GrouperSession session, String member, MembershipExpression exp)
            throws GridGrouperRuntimeException {

        if (exp.getMembershipQueryOrMembershipExpression().size() == 0) {
            throw Errors.makeException(GridGrouperRuntimeException.class, "Invalid Expression");
        }

        for(Serializable obj : exp.getMembershipQueryOrMembershipExpression()) {
            if (obj instanceof MembershipExpression) {
                if (isMember(session, member, (MembershipExpression)obj)) {
                    return true;
                }
            } else if (obj instanceof MembershipQuery) {
                MembershipQuery query = (MembershipQuery)obj;
                String grpName = query.getGroupIdentifier().getGroupName();
                try {
                    Group grp = GroupFinder.findByName(session, grpName);
                    boolean isMember = grp.hasMember(SubjectFinder.findById(member));
                    if (query.getMembershipStatus().equals(MembershipStatus.NOT_MEMBER_OF)) {
                        if (!isMember) {
                            return true;
                        }
                    } else {
                        if (isMember) {
                            return true;
                        }
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    throw Errors.makeException(GridGrouperRuntimeException.class, "Error in determining if the subject " + member + " is a member of the group "
                            + grpName + ": " + e.getMessage(), e);
                }
            }
        }

        return false;
    }

    public void addMembershipRequest(String gridIdentity, GroupIdentifier group) throws GridGrouperRuntimeException,
            GroupNotFoundException, InsufficientPrivilegeException, MemberAddException {
        GrouperSession session = null;
        try {
            Subject caller = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(caller);
            Group grp = GroupFinder.findByName(session, group.getGroupName());

            try {
                if (!"true".equals(grp.getAttribute("allowMembershipRequests"))) {
                    throw Errors.makeException(InsufficientPrivilegeException.class, group.getGroupName() + " does not allow membership requests.");
                }
            } catch (edu.internet2.middleware.grouper.AttributeNotFoundException e) {
                log.error(e.getMessage(), e);
                throw Errors.makeException(InsufficientPrivilegeException.class, group.getGroupName() + " does not allow membership requests.", e);
            }

            if (grp.hasMember(caller)){
                throw Errors.makeException(MemberAddException.class, gridIdentity + " already belongs to group: " + group.getGroupName());
            }

            MembershipRequest request = MembershipRequestFinder.findRequest(session, grp, gridIdentity);

            if (request == null) {
                MembershipRequest.create(grp, gridIdentity);
            } else if (MembershipRequestStatus.REMOVED.equals(request.getStatus())) {
                request.pending();
            } else {
                throw Errors.makeException(MemberAddException.class, gridIdentity + " has an existing " + request.getStatus().value().toLowerCase() + " membership request to group: " + group.getGroupName());
            }

        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (MemberAddException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (InsufficientPrivilegeException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred adding a member to the group " + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }

    public List<MembershipRequestDescriptor> getMembershipRequests(String gridIdentity, GroupIdentifier group, MembershipRequestStatus status)
            throws RemoteException, GridGrouperRuntimeException, GroupNotFoundException {
        GrouperSession session = null;
        try {
            Subject caller = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(caller);
            Group grp = GroupFinder.findByName(session, group.getGroupName());

            ArrayList<MembershipRequest> requests = MembershipRequestFinder.findRequestsByStatus(session, grp, status);
            ArrayList<MembershipRequestDescriptor> membershipRequestDescriptor = new ArrayList<MembershipRequestDescriptor>();
            for (MembershipRequest request : requests) {
                if (grp.hasAdmin(caller) || gridIdentity.equals(request.getRequestorId()) || (getAdminGroup().hasMember(caller))) {
                    membershipRequestDescriptor.add(membershiprequesttoMembershipRequestDescriptor(request));
                }
                if (!grp.hasAdmin(caller) && !getAdminGroup().hasMember(caller)) {
                    clearAdminNotes(membershipRequestDescriptor);
                }
            }

            return membershipRequestDescriptor;

        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred fetching membership requests for group: " + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }

    private void clearAdminNotes(ArrayList<MembershipRequestDescriptor> membershipRequestDescriptor) {
        for (MembershipRequestDescriptor des : membershipRequestDescriptor) {
            des.setAdminNote(null);
            for (MembershipRequestHistoryDescriptor membershipRequestHistoryDescriptor : des.getHistory()) {
                membershipRequestHistoryDescriptor.setAdminNote(null);
            }

        }
    }


    public MembershipRequestDescriptor updateMembershipRequest(String gridIdentity, GroupIdentifier group, String subject, MembershipRequestUpdate update) throws GridGrouperRuntimeException,
            GroupNotFoundException, InsufficientPrivilegeException, MemberAddException {
        GrouperSession session = null;
        try {
            Subject callerSubject = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(callerSubject);
            Group grp = GroupFinder.findByName(session, group.getGroupName());

            Member caller = MemberFinder.findBySubject(session, callerSubject);
            Subject subj = SubjectFinder.findById(subject);

            MembershipRequest membershipRequest = MembershipRequestFinder.findRequest(session, grp, subject);

            if (MembershipRequestStatus.APPROVED.equals(update.getStatus())) {
                membershipRequest.approve(caller, update.getPublicNote(), update.getAdminNote());
                grp.addMember(subj);
            } else {
                membershipRequest.reject(caller, update.getPublicNote(), update.getAdminNote());
            }

            return membershiprequesttoMembershipRequestDescriptor(membershipRequest);

        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(InsufficientPrivilegeException.class, e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred adding a member to the group " + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }

    public void enableMembershipRequests(String gridIdentity, GroupIdentifier group) throws GridGrouperRuntimeException, GroupNotFoundException, GrantPrivilegeException,
            InsufficientPrivilegeException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Group grp = GroupFinder.findByName(session, group.getGroupName());

            MembershipRequest.configureGroup(session, grp);
            grp.setAttribute("allowMembershipRequests", "true");

        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);                          
            throw Errors.makeException(InsufficientPrivilegeException.class, "You do not have the right to manages privileges on the group " + group.getGroupName()
                    + ": " + e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);             
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred granting a membership requests on the group "
                    + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }


    public void disableMembershipRequests(String gridIdentity, GroupIdentifier group) throws RemoteException, GridGrouperRuntimeException, GroupNotFoundException,
            RevokePrivilegeException, InsufficientPrivilegeException, SchemaException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Group grp = GroupFinder.findByName(session, group.getGroupName());

            MembershipRequest.configureGroup(session, grp);
            grp.setAttribute("allowMembershipRequests", "false");
            MembershipRequest.markAllRequestsRemoved(session, MemberFinder.findBySubject(session, subj), grp, "Membership requests to this group were disabled.  All pending requests have been marked removed.");

        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (edu.internet2.middleware.grouper.InsufficientPrivilegeException e) {
            log.error(e.getMessage(), e);                          
            throw Errors.makeException(InsufficientPrivilegeException.class, "You do not have the right to manages privileges on the group " + group.getGroupName()
                    + ": " + e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);             
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred revoking membership requests on the group "
                    + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }

    public boolean isMembershipRequestEnabled(String gridIdentity, GroupIdentifier group) throws GridGrouperRuntimeException, GroupNotFoundException, GrantPrivilegeException,
            InsufficientPrivilegeException {
        GrouperSession session = null;
        try {
            Subject subj = SubjectFinder.findById(gridIdentity);
            session = GrouperSession.start(subj);
            Group grp = GroupFinder.findByName(session, group.getGroupName());

            String isEnabled = grp.getAttribute("allowMembershipRequests");

            if ("true".equalsIgnoreCase(isEnabled)) {
                return true;
            } else {
                return false;
            }

        } catch (edu.internet2.middleware.grouper.GroupNotFoundException e) {
            log.error(e.getMessage(), e);
            throw Errors.makeException(GroupNotFoundException.class, "The group, " + group.getGroupName() + "was not found.", e);
        } catch (edu.internet2.middleware.grouper.AttributeNotFoundException e) {
            this.log.debug(e.getMessage(), e);
            return false;
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);           
            throw Errors.makeException(GridGrouperRuntimeException.class, "Error occurred granting a membership requests on the group "
                    + group.getGroupName() + ": " + e.getMessage(), e);
        } finally {
            if (session != null) {
                try {
                    session.stop();
                } catch (Exception e) {
                    this.log.error(e.getMessage(), e);
                }
            }
        }
    }

}
