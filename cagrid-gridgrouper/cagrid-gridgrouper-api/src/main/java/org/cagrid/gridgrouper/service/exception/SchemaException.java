package org.cagrid.gridgrouper.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gridgrouper.types.SchemaFault;
import org.cagrid.gridgrouper.types.StemModifyFault;

public class SchemaException extends Exception implements FaultException<SchemaFault> {

    private final SchemaFault fault;

	public SchemaException(SchemaFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public SchemaFault getFault() {
		return fault;
	}
}
