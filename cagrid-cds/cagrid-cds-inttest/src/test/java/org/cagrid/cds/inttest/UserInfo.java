package org.cagrid.cds.inttest;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class UserInfo {

	public final String gridId;
	public final X509Certificate x509Certificate;
	public final PrivateKey privateKey;

	public UserInfo(String gridId, X509Certificate x509Certificate,
			PrivateKey privateKey) {
		this.gridId = gridId;
		this.x509Certificate = x509Certificate;
		this.privateKey = privateKey;
	}
}
