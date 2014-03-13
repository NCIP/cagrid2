package org.cagrid.core.soapclient;

import org.apache.cxf.configuration.security.KeyStoreType;

/**
 * Created by langella on 3/13/14.
 */
public class TruststoreClientConfigurer extends AbstractTrustClientConfigurer {
    private String truststoreFile;
    private String truststorePassword;
    private KeyStoreType truststore;

    @Override
    public void configureTrustForClient(AbstractSoapClient client) {

        KeyStoreType ts = getTruststore();
        if (ts != null) {
            client.setTruststore(ts);
            if (log.isDebugEnabled()) {
                log.debug("Configured the client " + client.getURL() + " to use the truststore " + getTruststoreFile());
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("The truststore was not configured for the client " + client.getURL() + ".");
            }
        }
    }

    public KeyStoreType getTruststore() {
        if (truststore == null) {
            if (getTruststoreFile() != null) {
                if (log.isDebugEnabled()) {
                    log.debug("Loading the truststore " + getKeystoreFile());
                }
                truststore = new KeyStoreType();
                truststore.setPassword(this.getTruststorePassword());
                truststore.setFile(getTruststoreFile());
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("No truststore file was configured for client.");
                }
            }
        }
        return truststore;
    }


    public String getTruststoreFile() {
        return truststoreFile;
    }

    public void setTruststoreFile(String truststoreFile) {
        this.truststoreFile = truststoreFile;
    }

    public String getTruststorePassword() {
        return truststorePassword;
    }

    public void setTruststorePassword(String truststorePassword) {
        this.truststorePassword = truststorePassword;
    }
}
