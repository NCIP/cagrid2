package org.cagrid.dorian.model.exceptions;

import org.cagrid.core.common.FaultException;

public class InvalidHostCertificateRequestException extends Exception implements
		FaultException<InvalidHostCertificateRequestFault> {
	private static final long serialVersionUID = -1783293409509750730L;

	private final InvalidHostCertificateRequestFault fault;

	public InvalidHostCertificateRequestException(
			InvalidHostCertificateRequestFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InvalidHostCertificateRequestFault getFault() {
		return fault;
	}
}
