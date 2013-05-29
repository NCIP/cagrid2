package org.cagrid.gts.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gts.types.PermissionDeniedFault;

public class PermissionDeniedException extends Exception implements FaultException<PermissionDeniedFault> {

    private static final long serialVersionUID = -2661511393842929888L;
    private final PermissionDeniedFault fault;

    public PermissionDeniedException(PermissionDeniedFault fault, String message) {
        super(message);
        this.fault = fault;
    }

    @Override
    public PermissionDeniedFault getFault() {
        return fault;
    }
}
