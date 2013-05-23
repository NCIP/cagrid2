package org.cagrid.cds.service.exception;

import org.cagrid.cds.types.DelegationFault;
import org.cagrid.core.common.FaultException;

public class DelegationException extends Exception implements
		FaultException<DelegationFault> {

    private static final long serialVersionUID = -3445317694420894510L;
    private final DelegationFault fault;

	public DelegationException(DelegationFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public DelegationFault getFault() {
		return fault;
	}
}
