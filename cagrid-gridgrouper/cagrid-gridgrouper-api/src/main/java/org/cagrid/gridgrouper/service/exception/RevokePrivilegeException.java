package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.RevokePrivilegeFault;
import org.cagrid.gridgrouper.types.StemNotFoundFault;

public class RevokePrivilegeException extends Exception implements FaultException<RevokePrivilegeFault> {

    private final RevokePrivilegeFault fault;

	public RevokePrivilegeException(RevokePrivilegeFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public RevokePrivilegeFault getFault() {
		return fault;
	}
}
