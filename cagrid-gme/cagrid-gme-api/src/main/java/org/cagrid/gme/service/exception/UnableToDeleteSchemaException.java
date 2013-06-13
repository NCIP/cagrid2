package org.cagrid.gme.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gme.types.UnableToDeleteSchemaFault;

public class UnableToDeleteSchemaException extends Exception implements
		FaultException<UnableToDeleteSchemaFault> {

    private final UnableToDeleteSchemaFault fault;

	public UnableToDeleteSchemaException(UnableToDeleteSchemaFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public UnableToDeleteSchemaFault getFault() {
		return fault;
	}
}
