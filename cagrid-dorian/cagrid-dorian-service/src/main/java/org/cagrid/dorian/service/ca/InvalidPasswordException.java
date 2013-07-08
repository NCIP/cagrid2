package org.cagrid.dorian.service.ca;

import org.cagrid.core.common.FaultException;

public class InvalidPasswordException extends Exception implements
		FaultException<InvalidPasswordFault> {
	private static final long serialVersionUID = 8311945092306448512L;

	private final InvalidPasswordFault fault;

	public InvalidPasswordException(InvalidPasswordFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InvalidPasswordFault getFault() {
		return fault;
	}
}
