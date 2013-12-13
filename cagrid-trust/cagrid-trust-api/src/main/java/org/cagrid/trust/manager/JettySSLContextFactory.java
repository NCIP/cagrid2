package org.cagrid.trust.manager;

import java.security.KeyStore;
import java.security.cert.CRL;
import java.util.Collection;

import javax.net.ssl.TrustManager;

import org.cagrid.security.ssl.proxy.trust.ProxyTrustManager;
import org.eclipse.jetty.http.ssl.SslContextFactory;

public class JettySSLContextFactory extends SslContextFactory {

	private String trustedCertificatesDirectory;

	public JettySSLContextFactory() {
		super();
	}

	public JettySSLContextFactory(boolean trustAll) {
		super(trustAll);
	}

	public JettySSLContextFactory(String keyStorePath) {
		super(keyStorePath);
	}

	@Override
	protected TrustManager[] getTrustManagers(KeyStore trustStore, Collection<? extends CRL> crls) throws Exception {
		TrustManager[] tms = new TrustManager[1];
		tms[0] = new CertificateDirectoryTrustManager(getTrustedCertificatesDirectory());
		ProxyTrustManager proxyTrustManager = new ProxyTrustManager(tms);
		return new TrustManager[] { proxyTrustManager };
	}

	public String getTrustedCertificatesDirectory() {
		return trustedCertificatesDirectory;
	}

	public void setTrustedCertificatesDirectory(String trustedCertificatesDirectory) {
		this.trustedCertificatesDirectory = trustedCertificatesDirectory;
	}

}