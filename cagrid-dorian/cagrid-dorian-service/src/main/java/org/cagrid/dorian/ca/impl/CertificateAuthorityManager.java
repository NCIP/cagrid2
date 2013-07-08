package org.cagrid.dorian.ca.impl;

import java.util.List;

import org.cagrid.dorian.model.exceptions.DorianInternalException;

public interface CertificateAuthorityManager {

	public CertificateAuthority getCertificateAuthority(String dn) throws DorianInternalException;

	public CertificateAuthority getDefaultCertificateAuthority() throws DorianInternalException;
	
	public String getDefaultCertificateAuthoritySubjectDN();

	public List<CertificateAuthority> getCertificateAuthorities();
}
