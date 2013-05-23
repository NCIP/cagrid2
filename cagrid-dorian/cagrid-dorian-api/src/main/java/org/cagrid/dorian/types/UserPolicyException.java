package org.cagrid.dorian.types;

import org.cagrid.core.common.FaultException;

public class UserPolicyException extends Exception implements
		FaultException<UserPolicyFault> {
	private static final long serialVersionUID = 4863270588742162742L;

	private final UserPolicyFault fault;

	public UserPolicyException(UserPolicyFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public UserPolicyFault getFault() {
		return fault;
	}
}
