package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.GroupNotFoundFault;
import org.cagrid.gridgrouper.types.StemModifyFault;

public class GroupNotFoundException extends Exception implements FaultException<GroupNotFoundFault> {

    private final GroupNotFoundFault fault;

	public GroupNotFoundException(GroupNotFoundFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public GroupNotFoundFault getFault() {
		return fault;
	}
}
