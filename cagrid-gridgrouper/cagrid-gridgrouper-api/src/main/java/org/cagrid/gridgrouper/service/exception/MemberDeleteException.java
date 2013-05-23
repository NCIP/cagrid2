package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.MemberDeleteFault;

public class MemberDeleteException extends Exception implements FaultException<MemberDeleteFault> {

    private final MemberDeleteFault fault;

	public MemberDeleteException(MemberDeleteFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public MemberDeleteFault getFault() {
		return fault;
	}
}
