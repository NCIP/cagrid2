package org.cagrid.gme.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gme.types.InvalidSchemaSubmissionFault;

public class InvalidSchemaSubmissionException extends Exception implements
		FaultException<InvalidSchemaSubmissionFault> {

    private final InvalidSchemaSubmissionFault fault;

	public InvalidSchemaSubmissionException(InvalidSchemaSubmissionFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InvalidSchemaSubmissionFault getFault() {
		return fault;
	}
}
