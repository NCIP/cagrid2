package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.InsufficientPrivilegeFault;

public class InsufficientPrivilegeException extends Exception implements FaultException<InsufficientPrivilegeFault> {

    private final InsufficientPrivilegeFault fault;

	public InsufficientPrivilegeException(InsufficientPrivilegeFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InsufficientPrivilegeFault getFault() {
		return fault;
	}
}
