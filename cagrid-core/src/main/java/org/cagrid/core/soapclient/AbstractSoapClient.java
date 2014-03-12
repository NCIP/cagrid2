package org.cagrid.core.soapclient;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.configuration.Configurer;
import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.cagrid.core.common.security.KeyStoreUtil;
import org.cagrid.core.common.security.SSLConfigurer;
import org.cagrid.core.common.security.X509Credential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

/**
 * Created by langella on 2/18/14.
 */
public abstract class AbstractSoapClient<S, P> {

    protected Logger log;
    private X509Credential credential;
    private KeyStoreType truststore;
    private KeyManagersType keystore;
    private String url;
    private TrustManager[] trustManagers;
    private P client;
    private Class<S> serviceClass;
    private Class<P> portTypeClass;
    private KeyManager[] keyManagers;


    public AbstractSoapClient(String url, Class<S> serviceClass, Class<P> portTypeClass) {
        log = LoggerFactory.getLogger(this.getClass().getName());
        this.url = url;
        this.serviceClass = serviceClass;
        this.portTypeClass = portTypeClass;
    }

    public void configureTruststore(String location, String password) {
        this.truststore = new KeyStoreType();
        this.truststore.setPassword(password);
        this.truststore.setFile(location);
    }

    public void configureKeystore(String keyStoreFile, String keyStorePassword, String keyAlias, String keyPassword) {
        X509Credential cred = null;
        try {
            KeyStore keystore = KeyStoreUtil.getKeyStore(keyStoreFile, keyStorePassword.toCharArray());

            Key key = keystore.getKey(keyAlias, keyPassword.toCharArray());
            Certificate[] certAry = keystore.getCertificateChain(keyAlias);
            X509Certificate[] chain = new X509Certificate[certAry.length];
            for(int i=0; i<certAry.length; i++) {
                chain[i] = (X509Certificate)certAry[i];
            }
            cred = new X509Credential(chain, (PrivateKey) key);
            setCredential(cred);
        } catch (IOException e) {
            log.error("IOException while getting rest client credential", e);
        } catch (GeneralSecurityException e) {
            log.error("GeneralSecurityException while getting rest client credential", e);
        }
    }


    public P getClient() {

        if (client == null) {
            SpringBusFactory bf = new SpringBusFactory();
            Bus bus = bf.createBus();
            Configurer baseConf = bus.getExtension(Configurer.class);



            SSLConfigurer sslConf = new SSLConfigurer(baseConf);
            if (getTrustManagers() != null) {
                if(log.isDebugEnabled()){
                    log.debug("getClient() - Using Trust Managers....");

                }

                sslConf.setTrustManagers(getTrustManagers());
            } else if(getTruststore()!=null){
                if(log.isDebugEnabled()){
                    log.debug("getClient() - Using truststore");

                }

                sslConf.setTruststore(getTruststore());
            }else{
                if(log.isDebugEnabled()){
                    log.debug("getClient() - No trustmanager/truststore configured.");

                }
            }

            if(getKeyManagers()!=null){
                if(log.isDebugEnabled()){
                    log.debug("getClient() - Using Key Managers for client authentication......");
                }
                sslConf.setKm(getKeyManagers());
            }else if(getCredential()!=null){
                if(log.isDebugEnabled()){
                    log.debug("getClient() - Using credential for client authentication....");
                }
                sslConf.setCredential(credential);
            }else if(getKeystore() != null){
                if(log.isDebugEnabled()){
                    log.debug("getClient() - Using Key Managers for client authentication......");
                }
                sslConf.setKeystore(getKeystore());
            }else{
                if(log.isDebugEnabled()){
                    log.debug("getClient() - No credentials configured for client authentication......");
                }
            }




            bus.setExtension(sslConf, Configurer.class);

            JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
            cf.setAddress(url);
            cf.setServiceClass(this.serviceClass);
            cf.setBus(bus);
            this.client = cf.create(this.portTypeClass);
        }
        return client;
    }

    public TrustManager[] getTrustManagers() {
        return trustManagers;
    }

    public void setTrustManagers(TrustManager[] trustManagers) {
        this.trustManagers = trustManagers;
    }

    public X509Credential getCredential() {
        return credential;
    }

    public void setCredential(X509Credential credential) {
        this.credential = credential;
    }

    public KeyStoreType getTruststore() {
        return truststore;
    }

    public void setTruststore(KeyStoreType truststore) {
        this.truststore = truststore;
    }

    public KeyManagersType getKeystore() {
        return keystore;
    }

    public void setKeystore(KeyManagersType keystore) {
        this.keystore = keystore;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public KeyManager[] getKeyManagers() {
        return keyManagers;
    }

    public void setKeyManagers(KeyManager[] keyManager) {
        this.keyManagers = keyManager;
    }
}
