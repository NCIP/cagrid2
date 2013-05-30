package org.cagrid.gts.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gts.types.IllegalAuthorityFault;

public class IllegalAuthorityException extends Exception implements FaultException<IllegalAuthorityFault> {

    private static final long serialVersionUID = -2661511393842929888L;
    private final IllegalAuthorityFault fault;

    public IllegalAuthorityException(IllegalAuthorityFault fault, String message) {
        super(message);
        this.fault = fault;
    }

    @Override
    public IllegalAuthorityFault getFault() {
        return fault;
    }
}
