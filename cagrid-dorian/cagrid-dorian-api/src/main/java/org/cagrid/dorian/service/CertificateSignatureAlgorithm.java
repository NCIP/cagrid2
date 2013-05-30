package org.cagrid.dorian.service;

public enum CertificateSignatureAlgorithm {

	SHA1("SHA1"), SHA2("SHA2");
	private final String value;

	CertificateSignatureAlgorithm(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static CertificateSignatureAlgorithm fromValue(String v) {
		for (CertificateSignatureAlgorithm c : CertificateSignatureAlgorithm.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
