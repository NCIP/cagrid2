package org.cagrid.gaards.authentication.faults;

import org.cagrid.core.common.FaultException;

public class InvalidCredentialException extends Exception implements
		FaultException<InvalidCredentialFault> {
	private static final long serialVersionUID = 4141719777100880254L;

	private final InvalidCredentialFault fault;

	public InvalidCredentialException(InvalidCredentialFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InvalidCredentialFault getFault() {
		return fault;
	}
}
