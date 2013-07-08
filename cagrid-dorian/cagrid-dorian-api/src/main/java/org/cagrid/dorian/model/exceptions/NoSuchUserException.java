package org.cagrid.dorian.model.exceptions;

import org.cagrid.core.common.FaultException;

public class NoSuchUserException extends Exception implements
		FaultException<NoSuchUserFault> {
	private static final long serialVersionUID = 506413738513912589L;

	private final NoSuchUserFault fault;

	public NoSuchUserException(NoSuchUserFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public NoSuchUserFault getFault() {
		return fault;
	}
}
