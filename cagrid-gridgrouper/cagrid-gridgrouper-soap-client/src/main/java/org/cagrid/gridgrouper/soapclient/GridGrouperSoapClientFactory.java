package org.cagrid.gridgrouper.soapclient;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.configuration.Configurer;
import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.cagrid.gridgrouper.wsrf.service.GridGrouperService;
import org.cagrid.gridgrouper.wsrf.stubs.GridGrouperPortType;

import javax.net.ssl.KeyManager;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class GridGrouperSoapClientFactory {

    public static GridGrouperPortType createSoapClient(String url) {
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();


        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(GridGrouperService.class);
        cf.setBus(bus);
        GridGrouperPortType gridGrouperPort = cf.create(GridGrouperPortType.class);
        return gridGrouperPort;
    }

    public static GridGrouperPortType createSoapClient(String url,
                                                       KeyStoreType truststore, KeyManagersType keyManager)
            throws GeneralSecurityException, IOException {
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        Configurer baseConf = bus.getExtension(Configurer.class);
        org.cagrid.core.common.security.SSLConfigurer sslConf = new org.cagrid.core.common.security.SSLConfigurer(baseConf);
        sslConf.setTruststore(truststore);
        sslConf.setKeystore(keyManager);
        bus.setExtension(sslConf, Configurer.class);

        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(GridGrouperService.class);
        cf.setBus(bus);
        GridGrouperPortType gridGrouperPort = cf.create(GridGrouperPortType.class);
        return gridGrouperPort;
    }

    public static GridGrouperPortType createSoapClient(String url,
                                                       KeyStoreType truststore, KeyManager keyManager)
            throws GeneralSecurityException, IOException {
        SpringBusFactory bf = new SpringBusFactory();
        Bus bus = bf.createBus();
        Configurer baseConf = bus.getExtension(Configurer.class);
        org.cagrid.core.common.security.SSLConfigurer sslConf = new org.cagrid.core.common.security.SSLConfigurer(baseConf);
        sslConf.setTruststore(truststore);
        sslConf.setKm(new KeyManager[]{keyManager});
        bus.setExtension(sslConf, Configurer.class);

        JaxWsProxyFactoryBean cf = new JaxWsProxyFactoryBean();
        cf.setAddress(url);
        cf.setServiceClass(GridGrouperService.class);
        cf.setBus(bus);
        GridGrouperPortType gridGrouperPort = cf.create(GridGrouperPortType.class);
        return gridGrouperPort;
    }
}
