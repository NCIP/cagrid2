package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.StemAddFault;
import org.cagrid.gridgrouper.types.StemNotFoundFault;

public class StemAddException extends Exception implements FaultException<StemAddFault> {

    private final StemAddFault fault;

	public StemAddException(StemAddFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public StemAddFault getFault() {
		return fault;
	}
}
