package org.cagrid.dorian.model.exceptions;

import org.cagrid.core.common.FaultException;

public class InvalidProxyException extends Exception implements
		FaultException<InvalidProxyFault> {
	private static final long serialVersionUID = 8380508690509933727L;

	private final InvalidProxyFault fault;

	public InvalidProxyException(InvalidProxyFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InvalidProxyFault getFault() {
		return fault;
	}
}
