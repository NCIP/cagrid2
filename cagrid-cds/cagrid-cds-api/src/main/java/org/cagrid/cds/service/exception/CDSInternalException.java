package org.cagrid.cds.service.exception;

import org.cagrid.cds.types.CDSInternalFault;
import org.cagrid.core.common.FaultException;

public class CDSInternalException extends Exception implements
		FaultException<CDSInternalFault> {

    private static final long serialVersionUID = -3106633838874738985L;
    private final CDSInternalFault fault;

	public CDSInternalException(CDSInternalFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public CDSInternalFault getFault() {
		return fault;
	}
}
