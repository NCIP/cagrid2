package org.cagrid.security.ssl.proxy.trust;

import java.security.KeyStore;
import java.security.cert.CRL;
import java.util.Collection;

import javax.net.ssl.TrustManager;

import org.eclipse.jetty.http.ssl.SslContextFactory;

/*
 * Based on Jetty 7.5.4.v20111024
 */
public class JettySslContextFactory extends SslContextFactory {

	public JettySslContextFactory() {
		super();
	}

	public JettySslContextFactory(boolean trustAll) {
		super(trustAll);
	}

	public JettySslContextFactory(String keyStorePath) {
		super(keyStorePath);
	}

	@Override
	protected TrustManager[] getTrustManagers(KeyStore trustStore,
			Collection<? extends CRL> crls) throws Exception {
		TrustManager[] plainTrustManagers = super.getTrustManagers(trustStore,
				crls);
		ProxyTrustManager proxyTrustManager = new ProxyTrustManager(
				plainTrustManagers);
		return new TrustManager[] { proxyTrustManager };
	}
}
