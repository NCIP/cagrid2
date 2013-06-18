package org.cagrid.dorian.ca.impl;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cagrid.core.common.FaultHelper;
import org.cagrid.dorian.types.DorianInternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultCertificateAuthorityManager implements CertificateAuthorityManager {

	private final static Logger logger = LoggerFactory.getLogger(DefaultCertificateAuthorityManager.class);
	private Map<String, CertificateAuthority> certificateAuthorities;
	private String defaultCA;

	public DefaultCertificateAuthorityManager(String defaultCADN, List<CertificateAuthority> caList) throws DorianInternalException {
		this.defaultCA = defaultCADN;
		this.certificateAuthorities = new HashMap<String, CertificateAuthority>();
		if (caList != null) {
			for (CertificateAuthority ca : caList) {
				try {
					X509Certificate cacert = ca.getCACertificate();
					String dn = cacert.getSubjectDN().getName();
					if (this.certificateAuthorities.containsKey(dn)) {
						DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "Duplicate certificate authorities were configured with the DN " + dn + ".");
						throw fault;
					} else {
						this.certificateAuthorities.put(dn, ca);
					}
				} catch (NoCACredentialsException e) {
					String msg = "Error initializing the certificate authority manager: " + e.getMessage() + ".";
					logger.error(msg, e);
					DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, msg);
					throw fault;
				} catch (CertificateAuthorityException e) {
					String msg = "Error initializing the certificate authority manager: " + e.getMessage() + ".";
					logger.error(msg, e);
					DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, msg);
					throw fault;
				}
			}
		}
		if (!this.certificateAuthorities.containsKey(this.defaultCA)) {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "The default CA " + this.defaultCA
					+ " was not specified in the list of configured certificate authorities.");
			throw fault;
		}
	}

	@Override
	public String getDefaultCertificateAuthoritySubjectDN() {
		return this.defaultCA;
	}

	@Override
	public CertificateAuthority getCertificateAuthority(String dn) throws DorianInternalException {
		if (this.certificateAuthorities.containsKey(dn)) {
			return this.certificateAuthorities.get(dn);
		} else {
			DorianInternalException fault = FaultHelper.createFaultException(DorianInternalException.class, "The certificate authority " + dn + " does not exist.");
			throw fault;
		}
	}

	@Override
	public CertificateAuthority getDefaultCertificateAuthority() throws DorianInternalException {
		return getCertificateAuthority(this.defaultCA);
	}

	@Override
	public List<CertificateAuthority> getCertificateAuthorities() {
		List<CertificateAuthority> list = new ArrayList<CertificateAuthority>();
		Iterator<CertificateAuthority> itr = this.certificateAuthorities.values().iterator();
		while (itr.hasNext()) {
			list.add(itr.next());
		}
		return list;
	}

}
