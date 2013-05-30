package org.cagrid.gts.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gts.types.InvalidTrustedAuthorityFault;

public class InvalidTrustedAuthorityException extends Exception implements FaultException<InvalidTrustedAuthorityFault> {

    private static final long serialVersionUID = -2661511393842929888L;
    private final InvalidTrustedAuthorityFault fault;

    public InvalidTrustedAuthorityException(InvalidTrustedAuthorityFault fault, String message) {
        super(message);
        this.fault = fault;
    }

    @Override
    public InvalidTrustedAuthorityFault getFault() {
        return fault;
    }
}
