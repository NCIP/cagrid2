package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.GridGrouperRuntimeFault;

public class GridGrouperRuntimeException extends Exception implements FaultException<GridGrouperRuntimeFault> {

    private final GridGrouperRuntimeFault fault;

	public GridGrouperRuntimeException(GridGrouperRuntimeFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public GridGrouperRuntimeFault getFault() {
		return fault;
	}
}
