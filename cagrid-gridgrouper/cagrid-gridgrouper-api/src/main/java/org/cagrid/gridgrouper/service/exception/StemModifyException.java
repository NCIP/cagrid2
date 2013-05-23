package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.StemModifyFault;

public class StemModifyException extends Exception implements FaultException<StemModifyFault> {

    private final StemModifyFault fault;

	public StemModifyException(StemModifyFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public StemModifyFault getFault() {
		return fault;
	}
}
