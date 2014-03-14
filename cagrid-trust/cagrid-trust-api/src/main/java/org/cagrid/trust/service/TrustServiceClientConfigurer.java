package org.cagrid.trust.service;

import org.cagrid.core.soapclient.AbstractSoapClient;
import org.cagrid.core.soapclient.AbstractTrustClientConfigurer;

import javax.net.ssl.TrustManager;

/**
 * Created by langella on 3/13/14.
 */
public class TrustServiceClientConfigurer extends AbstractTrustClientConfigurer {

    private TrustService trustService;

    @Override
    public void configureTrustForClient(AbstractSoapClient client) {
        if (log.isDebugEnabled()) {
            log.debug("Configuring the client " + client.getURL() + " to use the trust service.");
        }
        TrustService ts = getTrustService();
        try {
            if (log.isDebugEnabled()) {
                log.debug("Loading the trust manager from the Trust Service...");
            }
            TrustManager tm = ts.getTrustManager();
            client.setTrustManagers(new TrustManager[]{tm});

            if (log.isDebugEnabled()) {
                log.debug("Successfully configured the client " + client.getURL() + " to use the trust service.");
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public TrustService getTrustService() {
        return trustService;
    }

    public void setTrustService(TrustService trustService) {
        this.trustService = trustService;
    }
}
