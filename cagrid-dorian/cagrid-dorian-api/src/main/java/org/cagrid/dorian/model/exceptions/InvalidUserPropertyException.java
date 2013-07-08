package org.cagrid.dorian.model.exceptions;

import org.cagrid.core.common.FaultException;

public class InvalidUserPropertyException extends Exception implements
		FaultException<InvalidUserPropertyFault> {
	private static final long serialVersionUID = 1001538866904820503L;

	private final InvalidUserPropertyFault fault;

	public InvalidUserPropertyException(InvalidUserPropertyFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InvalidUserPropertyFault getFault() {
		return fault;
	}
}
