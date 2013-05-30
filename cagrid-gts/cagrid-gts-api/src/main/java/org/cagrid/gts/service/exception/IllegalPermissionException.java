package org.cagrid.gts.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gts.types.IllegalPermissionFault;

public class IllegalPermissionException extends Exception implements FaultException<IllegalPermissionFault> {

    private static final long serialVersionUID = -2661511393842929888L;
    private final IllegalPermissionFault fault;

    public IllegalPermissionException(IllegalPermissionFault fault, String message) {
        super(message);
        this.fault = fault;
    }

    @Override
    public IllegalPermissionFault getFault() {
        return fault;
    }
}
