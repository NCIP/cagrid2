package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.StemNotFoundFault;

public class StemNotFoundException extends Exception implements FaultException<StemNotFoundFault> {

    private final StemNotFoundFault fault;

	public StemNotFoundException(StemNotFoundFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public StemNotFoundFault getFault() {
		return fault;
	}
}
