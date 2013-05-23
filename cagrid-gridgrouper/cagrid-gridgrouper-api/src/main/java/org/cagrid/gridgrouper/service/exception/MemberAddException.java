package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.GrantPrivilegeFault;
import org.cagrid.gridgrouper.types.MemberAddFault;

public class MemberAddException extends Exception implements FaultException<MemberAddFault> {

    private final MemberAddFault fault;

	public MemberAddException(MemberAddFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public MemberAddFault getFault() {
		return fault;
	}
}
