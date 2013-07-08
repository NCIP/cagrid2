package org.cagrid.dorian.service.ca;

import gov.nih.nci.cagrid.common.Utils;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.common.Lifetime;
import org.cagrid.dorian.model.exceptions.DorianInternalException;

public class CertificateAuthorityCreationPolicy {
	private String subject;
	private int keySize;
	private Lifetime lifetime;

	public CertificateAuthorityCreationPolicy(String subject, int keySize,
			Lifetime lifetime) throws DorianInternalException {

		if (Utils.clean(subject) == null) {
			DorianInternalException f = FaultHelper.createFaultException(
					DorianInternalException.class,
					"Could not initialize CA, invalid subject specified.");
			throw f;
		}

		this.subject = subject;

		if (KeySizeValidator.isKeySizeValid(keySize)) {
			this.keySize = keySize;
		} else {
			DorianInternalException f = FaultHelper.createFaultException(
					DorianInternalException.class,
					"Could not initialize CA, invalid key size specified.");
			throw f;
		}

		if (lifetime == null) {
			DorianInternalException f = FaultHelper.createFaultException(
					DorianInternalException.class,
					"Could not initialize CA, invalid lifetime specified.");
			throw f;
		}

		this.lifetime = lifetime;

	}

	public String getSubject() {
		return subject;
	}

	public int getKeySize() {
		return keySize;
	}

	public Lifetime getLifetime() {
		return lifetime;
	}
}
