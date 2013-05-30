package org.cagrid.gts.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gts.types.InvalidTrustLevelFault;

public class InvalidTrustLevelException extends Exception implements FaultException<InvalidTrustLevelFault> {

    private static final long serialVersionUID = -2661511393842929888L;
    private final InvalidTrustLevelFault fault;

    public InvalidTrustLevelException(InvalidTrustLevelFault fault, String message) {
        super(message);
        this.fault = fault;
    }

    @Override
    public InvalidTrustLevelFault getFault() {
        return fault;
    }
}
