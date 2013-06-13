package org.cagrid.gme.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gme.types.NoSuchNamespaceExistsFault;

public class NoSuchNamespaceExistsException extends Exception implements
		FaultException<NoSuchNamespaceExistsFault> {

    private final NoSuchNamespaceExistsFault fault;

	public NoSuchNamespaceExistsException(NoSuchNamespaceExistsFault fault, String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public NoSuchNamespaceExistsFault getFault() {
		return fault;
	}
}
