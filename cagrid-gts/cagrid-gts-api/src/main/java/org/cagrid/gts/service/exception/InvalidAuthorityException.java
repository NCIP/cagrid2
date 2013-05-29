package org.cagrid.gts.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gts.types.InvalidAuthorityFault;

public class InvalidAuthorityException extends Exception implements FaultException<InvalidAuthorityFault> {

    private static final long serialVersionUID = -2661511393842929888L;
    private final InvalidAuthorityFault fault;

    public InvalidAuthorityException(InvalidAuthorityFault fault, String message) {
        super(message);
        this.fault = fault;
    }

    @Override
    public InvalidAuthorityFault getFault() {
        return fault;
    }
}
