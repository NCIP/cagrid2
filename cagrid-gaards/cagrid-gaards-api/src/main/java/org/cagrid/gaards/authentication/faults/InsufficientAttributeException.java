package org.cagrid.gaards.authentication.faults;

import org.cagrid.core.common.FaultException;

public class InsufficientAttributeException extends Exception implements
		FaultException<InsufficientAttributeFault> {
	private static final long serialVersionUID = 9209236878339264846L;

	private final InsufficientAttributeFault fault;

	public InsufficientAttributeException(InsufficientAttributeFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InsufficientAttributeFault getFault() {
		return fault;
	}
}
