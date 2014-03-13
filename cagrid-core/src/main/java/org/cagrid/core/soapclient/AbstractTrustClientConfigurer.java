package org.cagrid.core.soapclient;

import org.cagrid.core.common.security.KeyStoreUtil;
import org.cagrid.core.common.security.X509Credential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * Created by langella on 3/13/14.
 */
public abstract class AbstractTrustClientConfigurer implements ClientConfigurer {

    protected Logger log = LoggerFactory.getLogger(AbstractTrustClientConfigurer.class);

    private String keystoreFile;
    private String keystorePassword;
    private String keyAlias;
    private String keyPassword;
    private X509Credential credential;


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

    @Override
    public void configureClient(AbstractSoapClient client) {
        X509Credential cred = getCredential();
        if (cred != null) {
            client.setCredential(cred);
            if (log.isDebugEnabled()) {
                log.debug("Configured the client, " + client.getURL() + " to use the credential " + cred.getSubject());
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("No credential configured for the client " + client.getURL() + ".");
            }
        }
        configureTrustForClient(client);
    }

    public abstract void configureTrustForClient(AbstractSoapClient client);


    public X509Credential getCredential() {

        if (credential == null) {

            if (log.isDebugEnabled()) {
                log.debug("Loading X509 credential.....");
            }

            if (getKeystoreFile() != null) {
                try {
                    if (log.isDebugEnabled()) {
                        log.debug("Loading credential from the keystore " + getKeystoreFile() + ".");
                    }
                    KeyStore keystore = KeyStoreUtil.getKeyStore(getKeystoreFile(), getKeystorePassword().toCharArray());
                    String alias = getKeyAlias();
                    if (alias == null) {
                        Enumeration<String> aliases = keystore.aliases();
                        while (aliases.hasMoreElements()) {
                            String a = aliases.nextElement();
                            if (keystore.entryInstanceOf(a, KeyStore.PrivateKeyEntry.class)) {
                                alias = a;
                                break;
                            }
                        }
                    }
                    if (log.isDebugEnabled()) {
                        log.debug("Using the key alias " + alias);
                    }

                    Key key = keystore.getKey(alias, getKeyPassword().toCharArray());
                    Certificate[] certAry = keystore.getCertificateChain(alias);
                    if (certAry == null) {
                        throw new GeneralSecurityException("A credential with the alias " + alias + " could not be found in the keystore " + getKeystoreFile() + ".");
                    }
                    X509Certificate[] chain = new X509Certificate[certAry.length];
                    for (int i = 0; i < certAry.length; i++) {
                        chain[i] = (X509Certificate) certAry[i];
                    }
                    this.credential = new X509Credential(chain, (PrivateKey) key);

                    if (log.isDebugEnabled()) {
                        log.debug("Successfully loaded the credential for " + this.credential.getSubject());
                    }
                } catch (IOException e) {
                    log.error("IOException while getting credential", e);
                } catch (GeneralSecurityException e) {
                    log.error("GeneralSecurityException while getting credential", e);
                }
            } else {
                if(log.isDebugEnabled()){
                    log.debug("No keystore file provided to load the credential from.");
                }
            }

        }
        return this.credential;
    }


}

