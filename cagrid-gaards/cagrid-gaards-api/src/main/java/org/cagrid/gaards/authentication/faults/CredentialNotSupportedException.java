package org.cagrid.gaards.authentication.faults;

import org.cagrid.core.common.FaultException;

public class CredentialNotSupportedException extends Exception implements
		FaultException<CredentialNotSupportedFault> {
	private static final long serialVersionUID = -1061388319796789181L;

	private final CredentialNotSupportedFault fault;

	public CredentialNotSupportedException(CredentialNotSupportedFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public CredentialNotSupportedFault getFault() {
		return fault;
	}
}
