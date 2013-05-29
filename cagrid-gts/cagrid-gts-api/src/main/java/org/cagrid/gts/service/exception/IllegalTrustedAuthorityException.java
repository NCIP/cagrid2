package org.cagrid.gts.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gts.types.IllegalTrustedAuthorityFault;

public class IllegalTrustedAuthorityException extends Exception implements FaultException<IllegalTrustedAuthorityFault> {

    private static final long serialVersionUID = -2661511393842929888L;
    private final IllegalTrustedAuthorityFault fault;

    public IllegalTrustedAuthorityException(IllegalTrustedAuthorityFault fault, String message) {
        super(message);
        this.fault = fault;
    }

    @Override
    public IllegalTrustedAuthorityFault getFault() {
        return fault;
    }
}
