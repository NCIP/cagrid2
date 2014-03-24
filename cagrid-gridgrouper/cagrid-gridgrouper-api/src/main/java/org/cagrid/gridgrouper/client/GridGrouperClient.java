package org.cagrid.gridgrouper.client;

import org.cagrid.gridgrouper.model.GroupDescriptor;
import org.cagrid.gridgrouper.model.GroupIdentifier;
import org.cagrid.gridgrouper.model.MemberFilter;
import org.cagrid.gridgrouper.service.exception.GridGrouperRuntimeException;
import org.cagrid.gridgrouper.service.exception.GroupNotFoundException;

public interface GridGrouperClient {

    public boolean isMemberOf(String subjectId, String groupName) throws GroupNotFoundException, GridGrouperRuntimeException;

    public boolean isMemberOf(GroupIdentifier group, String member, MemberFilter filter) throws GroupNotFoundException, GridGrouperRuntimeException;

    public GroupDescriptor getGroup(String name) throws GroupNotFoundException, GridGrouperRuntimeException;

    public GroupDescriptor getGroup(GroupIdentifier group) throws GroupNotFoundException, GridGrouperRuntimeException;
}
