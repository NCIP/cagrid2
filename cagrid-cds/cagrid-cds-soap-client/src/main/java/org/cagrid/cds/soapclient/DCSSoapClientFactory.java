package org.cagrid.cds.soapclient;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.configuration.Configurer;
import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.cagrid.core.common.security.SSLConfigurer;
import org.cagrid.delegatedcredential.wsrf.service.DelegatedCredentialService;
import org.cagrid.delegatedcredential.wsrf.stubs.DelegatedCredentialPortType;

import javax.net.ssl.KeyManager;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class DCSSoapClientFactory {


    public static DelegatedCredentialPortType createSoapClient(
            String url) {

        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();

        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(DelegatedCredentialService.class);
        cf.setBus(bus);
        DelegatedCredentialPortType cdsPort = cf.create(DelegatedCredentialPortType.class);
        return cdsPort;
    }

    public static DelegatedCredentialPortType createSoapClient(
            String url, KeyStoreType truststore, KeyManagersType keyManager)
            throws GeneralSecurityException, IOException {

        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        Configurer baseConf = bus.getExtension(Configurer.class);
        SSLConfigurer sslConf = new SSLConfigurer(baseConf);
        sslConf.setTruststore(truststore);
        sslConf.setKeystore(keyManager);
        bus.setExtension(sslConf, Configurer.class);

        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(DelegatedCredentialService.class);
        cf.setBus(bus);
        DelegatedCredentialPortType cdsPort = cf.create(DelegatedCredentialPortType.class);
        return cdsPort;

    }

    public static DelegatedCredentialPortType createSoapClient(
            String url, KeyStoreType truststore, KeyManager keyManager)
            throws GeneralSecurityException, IOException {
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        Configurer baseConf = bus.getExtension(Configurer.class);
        SSLConfigurer sslConf = new SSLConfigurer(baseConf);
        sslConf.setTruststore(truststore);
        sslConf.setKm(new KeyManager[]{keyManager});
        bus.setExtension(sslConf, Configurer.class);

        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(DelegatedCredentialService.class);
        cf.setBus(bus);
        DelegatedCredentialPortType cdsPort = cf.create(DelegatedCredentialPortType.class);
        return cdsPort;
    }
}

