package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.GroupAddFault;
import org.cagrid.gridgrouper.types.StemNotFoundFault;

public class GroupAddException extends Exception implements FaultException<GroupAddFault> {

    private final GroupAddFault fault;

	public GroupAddException(GroupAddFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public GroupAddFault getFault() {
		return fault;
	}
}
