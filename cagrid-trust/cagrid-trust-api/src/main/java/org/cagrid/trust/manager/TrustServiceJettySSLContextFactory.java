package org.cagrid.trust.manager;

import java.security.KeyStore;
import java.security.cert.CRL;
import java.util.Collection;

import javax.net.ssl.TrustManager;

import org.cagrid.security.ssl.proxy.trust.ProxyTrustManager;
import org.cagrid.trust.service.TrustService;
import org.eclipse.jetty.http.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrustServiceJettySSLContextFactory extends SslContextFactory {

	private Logger log;
	private TrustService trustService;

	public TrustServiceJettySSLContextFactory() {
		super();
		log = LoggerFactory.getLogger(this.getClass().getName());
	}

	public TrustServiceJettySSLContextFactory(boolean trustAll) {
		super(trustAll);
		log = LoggerFactory.getLogger(this.getClass().getName());
	}

	public TrustServiceJettySSLContextFactory(String keyStorePath) {
		super(keyStorePath);
		log = LoggerFactory.getLogger(this.getClass().getName());
	}

	@Override
	protected TrustManager[] getTrustManagers(KeyStore trustStore, Collection<? extends CRL> crls) throws Exception {

		if (getTrustService() == null) {
			log.warn("A trust service was not specified, using the default trust managers");
			return super.getTrustManagers(trustStore, crls);
		} else if (getTrustService().getTrustManager() == null) {
			log.warn("The trust service did NOT provide a trust manager to use, using the default trust managers");
			return super.getTrustManagers(trustStore, crls);
		} else {

			TrustManager[] tms = new TrustManager[1];

			tms[0] = getTrustService().getTrustManager();
			ProxyTrustManager proxyTrustManager = new ProxyTrustManager(tms);
			return new TrustManager[] { proxyTrustManager };
		}
	}

	public TrustService getTrustService() {
		return trustService;
	}

	public void setTrustService(TrustService trustService) {
		this.trustService = trustService;
	}

}