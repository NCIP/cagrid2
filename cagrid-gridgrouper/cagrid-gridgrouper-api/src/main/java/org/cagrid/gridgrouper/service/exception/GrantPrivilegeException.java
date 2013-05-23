package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.GrantPrivilegeFault;

public class GrantPrivilegeException extends Exception implements FaultException<GrantPrivilegeFault> {

    private final GrantPrivilegeFault fault;

	public GrantPrivilegeException(GrantPrivilegeFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public GrantPrivilegeFault getFault() {
		return fault;
	}
}
