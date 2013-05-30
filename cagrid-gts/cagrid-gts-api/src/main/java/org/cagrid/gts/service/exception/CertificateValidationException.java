package org.cagrid.gts.service.exception;

import org.cagrid.core.common.FaultException;
import org.cagrid.gts.types.CertificateValidationFault;

public class CertificateValidationException extends Exception implements FaultException<CertificateValidationFault> {

    private static final long serialVersionUID = -2661511393842929888L;
    private final CertificateValidationFault fault;

    public CertificateValidationException(CertificateValidationFault fault, String message) {
        super(message);
        this.fault = fault;
    }

    @Override
    public CertificateValidationFault getFault() {
        return fault;
    }
}
