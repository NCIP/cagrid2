package org.cagrid.dorian.types;

import org.cagrid.core.common.FaultException;

public class InvalidUserCertificateException extends Exception implements
		FaultException<InvalidUserCertificateFault> {
	private static final long serialVersionUID = 2258250325334052970L;

	private final InvalidUserCertificateFault fault;

	public InvalidUserCertificateException(InvalidUserCertificateFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InvalidUserCertificateFault getFault() {
		return fault;
	}
}
