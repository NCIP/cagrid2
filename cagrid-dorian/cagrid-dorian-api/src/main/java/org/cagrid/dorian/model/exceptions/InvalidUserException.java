package org.cagrid.dorian.model.exceptions;

import org.cagrid.core.common.FaultException;

public class InvalidUserException extends Exception implements
		FaultException<InvalidUserFault> {
	private static final long serialVersionUID = 1215275807157714161L;

	private final InvalidUserFault fault;

	public InvalidUserException(InvalidUserFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InvalidUserFault getFault() {
		return fault;
	}
}
