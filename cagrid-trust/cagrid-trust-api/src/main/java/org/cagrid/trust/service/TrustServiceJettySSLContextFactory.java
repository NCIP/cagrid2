package org.cagrid.trust.service;

import org.eclipse.jetty.http.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.TrustManager;
import java.security.KeyStore;
import java.security.cert.CRL;
import java.util.Collection;
import java.util.Date;

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
        System.out.println("getTrustManagers() - CALLED ON " + new Date());
        if (getTrustService() == null) {
            log.warn("A trust service was not specified, using the default trust managers");
            return super.getTrustManagers(trustStore, crls);
        } else if (getTrustService().getTrustManager() == null) {
            log.warn("The trust service did NOT provide a trust manager to use, using the default trust managers");
            return super.getTrustManagers(trustStore, crls);
        } else {
            return new TrustManager[]{getTrustService().getTrustManager()};
        }
    }

    public TrustService getTrustService() {
        return trustService;
    }

    public void setTrustService(TrustService trustService) {
        this.trustService = trustService;
    }

}