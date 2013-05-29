package org.cagrid.gts.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gts.types.GTSInternalFault;

public class GTSInternalException extends Exception implements FaultException<GTSInternalFault> {

    private static final long serialVersionUID = -2661511393842929888L;
    private final GTSInternalFault fault;

    public GTSInternalException(GTSInternalFault fault, String message) {
        super(message);
        this.fault = fault;
    }

    @Override
    public GTSInternalFault getFault() {
        return fault;
    }
}
