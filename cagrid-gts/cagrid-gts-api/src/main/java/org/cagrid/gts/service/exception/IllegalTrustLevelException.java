package org.cagrid.gts.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gts.types.IllegalTrustLevelFault;

public class IllegalTrustLevelException extends Exception implements FaultException<IllegalTrustLevelFault> {

    private static final long serialVersionUID = -2661511393842929888L;
    private final IllegalTrustLevelFault fault;

    public IllegalTrustLevelException(IllegalTrustLevelFault fault, String message) {
        super(message);
        this.fault = fault;
    }

    @Override
    public IllegalTrustLevelFault getFault() {
        return fault;
    }
}
