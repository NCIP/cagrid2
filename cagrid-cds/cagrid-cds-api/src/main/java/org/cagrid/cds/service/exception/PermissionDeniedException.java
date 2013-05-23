package org.cagrid.cds.service.exception;

import org.cagrid.cds.types.PermissionDeniedFault;
import org.cagrid.core.common.FaultException;

public class PermissionDeniedException extends Exception implements
		FaultException<PermissionDeniedFault> {

    private static final long serialVersionUID = -2041339734432299965L;
    private final PermissionDeniedFault fault;

	public PermissionDeniedException(PermissionDeniedFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public PermissionDeniedFault getFault() {
		return fault;
	}
}
