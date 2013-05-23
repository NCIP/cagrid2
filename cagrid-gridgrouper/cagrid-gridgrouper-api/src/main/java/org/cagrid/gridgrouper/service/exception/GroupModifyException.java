package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.GrantPrivilegeFault;
import org.cagrid.gridgrouper.types.GroupModifyFault;

public class GroupModifyException extends Exception implements FaultException<GroupModifyFault> {

    private final GroupModifyFault fault;

	public GroupModifyException(GroupModifyFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public GroupModifyFault getFault() {
		return fault;
	}
}
