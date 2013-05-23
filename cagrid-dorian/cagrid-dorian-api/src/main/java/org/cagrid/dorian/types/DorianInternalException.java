package org.cagrid.dorian.types;

import org.cagrid.core.common.FaultException;

public class DorianInternalException extends Exception implements
		FaultException<DorianInternalFault> {
	private static final long serialVersionUID = 821835969676264972L;

	private final DorianInternalFault fault;

	public DorianInternalException(DorianInternalFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public DorianInternalFault getFault() {
		return fault;
	}
}
