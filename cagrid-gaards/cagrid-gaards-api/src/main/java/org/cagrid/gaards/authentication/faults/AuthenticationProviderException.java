package org.cagrid.gaards.authentication.faults;

import org.cagrid.core.common.FaultException;

public class AuthenticationProviderException extends Exception implements
		FaultException<AuthenticationProviderFault> {
	private static final long serialVersionUID = 760357918660847859L;

	private final AuthenticationProviderFault fault;

	public AuthenticationProviderException(AuthenticationProviderFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public AuthenticationProviderFault getFault() {
		return fault;
	}
}
