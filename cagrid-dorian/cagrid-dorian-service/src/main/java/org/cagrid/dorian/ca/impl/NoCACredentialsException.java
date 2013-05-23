package org.cagrid.dorian.ca.impl;

import org.cagrid.core.common.FaultException;

public class NoCACredentialsException extends Exception implements
		FaultException<NoCACredentialsFault> {
	private static final long serialVersionUID = 2231934175750317657L;

	private final NoCACredentialsFault fault;

	public NoCACredentialsException(NoCACredentialsFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public NoCACredentialsFault getFault() {
		return fault;
	}
}
