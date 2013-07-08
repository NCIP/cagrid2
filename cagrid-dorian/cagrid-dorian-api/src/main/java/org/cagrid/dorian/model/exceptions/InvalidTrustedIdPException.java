package org.cagrid.dorian.model.exceptions;

import org.cagrid.core.common.FaultException;

public class InvalidTrustedIdPException extends Exception implements
		FaultException<InvalidTrustedIdPFault> {
	private static final long serialVersionUID = 6785397235374438901L;

	private final InvalidTrustedIdPFault fault;

	public InvalidTrustedIdPException(InvalidTrustedIdPFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InvalidTrustedIdPFault getFault() {
		return fault;
	}
}
