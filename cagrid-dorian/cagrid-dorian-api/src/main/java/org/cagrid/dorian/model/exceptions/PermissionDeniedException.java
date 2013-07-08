package org.cagrid.dorian.model.exceptions;

import org.cagrid.core.common.FaultException;

public class PermissionDeniedException extends Exception implements
		FaultException<PermissionDeniedFault> {
	private static final long serialVersionUID = 5218674420478275624L;

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
