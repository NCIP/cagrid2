package org.cagrid.dorian.service.core;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.common.security.KeyStoreUtil;
import org.cagrid.core.common.security.X509Credential;
import org.cagrid.gts.ws.client.GTSClient;
import org.cagrid.trust.service.TrustService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class CredentialManager {

    private Logger log = LoggerFactory.getLogger(CredentialManager.class);

    private String keystoreFile;
    private String keystorePassword;
    private String keyAlias;
    private String keyPassword;
    private String truststoreFile;
    private String truststorePassword;
    private TrustService trustService;
    private boolean useTrustService = false;


    public GTSClient getGTSClient(String url) {
        GTSClient client = new GTSClient(url);
        client.setCredential(getCredential());


        boolean trustServiceConfigured = false;
        if(useTrustService()){
            log.debug("Getting trust service...");
        TrustService ts = getTrustService();

            log.debug("Got trust service...." + ts.getClass().getName());
            try {
                log.debug("Getting trust manager...");
                TrustManager tm = ts.getTrustManager();
                log.debug("Got trust manager!!!!");
                client.setTrustManagers(new TrustManager[]{tm});
                trustServiceConfigured = true;
                log.debug("The GTS client is configured to use the trust service.");
            } catch (Exception e) {
                log.debug(e.getMessage(), e);
            }
        }
        if (!trustServiceConfigured) {
            client.setTruststore(getTruststore());
            log.debug("The GTS client is configured to use a trust store.");
        }
        return client;
    }

    public X509Credential getCredential() {
        X509Credential cred = null;
        try {
            KeyStore keystore = KeyStoreUtil.getKeyStore(getKeystoreFile(), getKeyPassword().toCharArray());

            if (keyAlias == null) {
                Enumeration<String> aliases = keystore.aliases();
                while (aliases.hasMoreElements()) {
                    String alias = aliases.nextElement();
                    if (keystore.entryInstanceOf(alias, KeyStore.PrivateKeyEntry.class)) {
                        keyAlias = alias;
                        break;
                    }
                }
            }

            Key key = keystore.getKey(keyAlias, keyPassword.toCharArray());
            Certificate[] certAry = keystore.getCertificateChain(keyAlias);
            if (certAry == null) {
                throw new GeneralSecurityException("A credential with the alias " + keyAlias + " could not be found in the keystore " + getKeystoreFile() + ".");
            }
            X509Certificate[] chain = new X509Certificate[certAry.length];
            for (int i = 0; i < certAry.length; i++) {
                chain[i] = (X509Certificate) certAry[i];
            }
            cred = new X509Credential(chain, (PrivateKey) key);
        } catch (IOException e) {
            log.error("IOException while getting credential", e);
        } catch (GeneralSecurityException e) {
            log.error("GeneralSecurityException while getting credential", e);
        }
        return cred;
    }


    public KeyStoreType getTruststore() {
        KeyStoreType ks = new KeyStoreType();
        ks.setPassword(this.getTruststorePassword());
        ks.setFile(getTruststoreFile());
        return ks;
    }

    public String getKeystoreFile() {
        return keystoreFile;
    }

    public void setKeystoreFile(String keystoreFile) {
        this.keystoreFile = keystoreFile;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public String getKeyPassword() {
        return keyPassword;
    }

    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
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

    public TrustService getTrustService() {
        return trustService;
    }

    public void setTrustService(TrustService trustService) {
        this.trustService = trustService;
    }

    public boolean useTrustService() {
        return useTrustService;
    }

    public void setUseTrustService(boolean useTrustService) {
        this.useTrustService = useTrustService;
    }
}
