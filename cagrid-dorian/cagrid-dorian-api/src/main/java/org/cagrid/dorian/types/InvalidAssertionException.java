package org.cagrid.dorian.types;

import org.cagrid.core.common.FaultException;

public class InvalidAssertionException extends Exception implements
		FaultException<InvalidAssertionFault> {
	private static final long serialVersionUID = -9184319899925660446L;

	private final InvalidAssertionFault fault;

	public InvalidAssertionException(InvalidAssertionFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InvalidAssertionFault getFault() {
		return fault;
	}
}
