package org.cagrid.cds.service.exception;

import org.cagrid.cds.types.InvalidPolicyFault;
import org.cagrid.core.common.FaultException;

public class InvalidPolicyException extends Exception implements
		FaultException<InvalidPolicyFault> {

    private static final long serialVersionUID = -1360543900452947163L;
    private final InvalidPolicyFault fault;

	public InvalidPolicyException(InvalidPolicyFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public InvalidPolicyFault getFault() {
		return fault;
	}
}
