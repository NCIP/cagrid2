package org.cagrid.gme.soapclient;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.configuration.Configurer;
import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.cagrid.gme.wsrf.service.GlobalModelExchangeService;
import org.cagrid.gme.wsrf.stubs.GlobalModelExchangePortType;

import javax.net.ssl.KeyManager;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class GMESoapClientFactory {

    public static GlobalModelExchangePortType createSoapClient(String url) {
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(GlobalModelExchangeService.class);
        cf.setBus(bus);
        GlobalModelExchangePortType gmePort = cf.create(GlobalModelExchangePortType.class);
        return gmePort;
    }

    public static GlobalModelExchangePortType createSoapClient(String url, KeyStoreType truststore, KeyManagersType keyManager) throws GeneralSecurityException, IOException {
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        Configurer baseConf = bus.getExtension(Configurer.class);
        org.cagrid.core.common.security.SSLConfigurer sslConf = new org.cagrid.core.common.security.SSLConfigurer(baseConf);
        sslConf.setTruststore(truststore);
        sslConf.setKeystore(keyManager);
        bus.setExtension(sslConf, Configurer.class);

        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(GlobalModelExchangeService.class);
        cf.setBus(bus);
        GlobalModelExchangePortType gmePort = cf.create(GlobalModelExchangePortType.class);
        return gmePort;
    }

    public static GlobalModelExchangePortType createSoapClient(String url, KeyStoreType truststore, KeyManager keyManager) throws GeneralSecurityException, IOException {
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        Configurer baseConf = bus.getExtension(Configurer.class);
        org.cagrid.core.common.security.SSLConfigurer sslConf = new org.cagrid.core.common.security.SSLConfigurer(baseConf);
        sslConf.setTruststore(truststore);
        sslConf.setKm(new KeyManager[]{keyManager});
        bus.setExtension(sslConf, Configurer.class);

        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(GlobalModelExchangeService.class);
        cf.setBus(bus);
        GlobalModelExchangePortType gmePort = cf.create(GlobalModelExchangePortType.class);
        return gmePort;
    }


}
