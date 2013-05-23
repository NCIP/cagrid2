package org.cagrid.dorian.ca.impl;

import java.util.Calendar;

import org.cagrid.core.common.FaultException;
import org.oasis_open.docs.wsrf._2004._06.wsrf_ws_basefaults_1_2_draft_01.BaseFaultType;
import org.xmlsoap.schemas.ws._2004._03.addressing.EndpointReferenceType;

public class CertificateAuthorityException extends Exception implements
		FaultException<CertificateAuthorityFault> {
	private static final long serialVersionUID = -5016177995278798307L;

	public static CertificateAuthorityException newInstance(Calendar timestamp,
			EndpointReferenceType originator,
			BaseFaultType.ErrorCode errorCode,
			BaseFaultType.Description[] description, BaseFaultType[] faultCause) {

		CertificateAuthorityFault fault = new CertificateAuthorityFault(
				timestamp, originator, errorCode, description, faultCause);
		String message = null;
		if ((description != null) && (description.length > 0)) {
			message = description[0].getValue();
		}
		return new CertificateAuthorityException(fault, message);
	}

	private final CertificateAuthorityFault fault;

	public CertificateAuthorityException(CertificateAuthorityFault fault,
			String message) {
		super(message);
		this.fault = fault;
	}

	@Override
	public CertificateAuthorityFault getFault() {
		return fault;
	}
}
