package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.GroupDeleteFault;
import org.cagrid.gridgrouper.types.StemNotFoundFault;

public class GroupDeleteException extends Exception implements FaultException<GroupDeleteFault> {

    private final GroupDeleteFault fault;

	public GroupDeleteException(GroupDeleteFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public GroupDeleteFault getFault() {
		return fault;
	}
}
