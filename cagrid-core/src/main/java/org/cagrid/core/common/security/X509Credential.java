package org.cagrid.core.common.security;

import javax.net.ssl.X509KeyManager;
import java.io.Serializable;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class X509Credential implements Serializable {

	private X509CertificateChain chain;
	private PrivateKey key;

	public X509Credential(X509Certificate[] chain, PrivateKey key) {
		this.chain = new X509CertificateChain(chain);
		this.key = key;
	}

	public X509Credential(X509CertificateChain chain, PrivateKey key) {
		this.chain = chain;
		this.key = key;
	}

	public X509Certificate[] getCertificates() {
		return chain.getCertificateChain();
	}

	public X509CertificateChain getCertificateChain() {
		return chain;
	}

	public PrivateKey getKey() {
		return key;
	}

	public X509KeyManager getKeyManager() {
		return new SingleEntityX509KeyManager(chain.getCertificateChain(), key);
	}

	public Principal getSubject() {
		return chain.getIdentityCertificate().getSubjectDN();
	}
}
