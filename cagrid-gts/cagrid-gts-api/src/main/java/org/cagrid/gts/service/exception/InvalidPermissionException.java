package org.cagrid.gts.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gts.types.InvalidPermissionFault;

public class InvalidPermissionException extends Exception implements FaultException<InvalidPermissionFault> {

    private static final long serialVersionUID = -2661511393842929888L;
    private final InvalidPermissionFault fault;

    public InvalidPermissionException(InvalidPermissionFault fault, String message) {
        super(message);
        this.fault = fault;
    }

    @Override
    public InvalidPermissionFault getFault() {
        return fault;
    }
}
