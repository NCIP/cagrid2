package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.StemDeleteFault;
import org.cagrid.gridgrouper.types.StemNotFoundFault;

public class StemDeleteException extends Exception implements FaultException<StemDeleteFault> {

    private final StemDeleteFault fault;

	public StemDeleteException(StemDeleteFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public StemDeleteFault getFault() {
		return fault;
	}
}
