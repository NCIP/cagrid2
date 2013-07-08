package org.cagrid.dorian.model.exceptions;

import org.cagrid.core.common.FaultException;

public class InvalidHostCertificateException extends Exception implements
		FaultException<InvalidHostCertificateFault> {
	private static final long serialVersionUID = 6394700154820199411L;

	private final InvalidHostCertificateFault fault;

	public InvalidHostCertificateException(InvalidHostCertificateFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InvalidHostCertificateFault getFault() {
		return fault;
	}
}
