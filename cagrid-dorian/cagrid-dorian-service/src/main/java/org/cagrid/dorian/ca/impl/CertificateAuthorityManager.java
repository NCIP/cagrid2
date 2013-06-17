package org.cagrid.dorian.ca.impl;

import java.util.List;

import org.cagrid.dorian.types.DorianInternalException;

public interface CertificateAuthorityManager {

	public CertificateAuthority getCertificateAuthority(String dn) throws DorianInternalException;

	public CertificateAuthority getDefaultCertificateAuthority() throws DorianInternalException;

	public List<CertificateAuthority> getCertificateAuthorities();
}
