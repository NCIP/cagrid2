package org.cagrid.dorian.soapclient;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.Bus;
import org.apache.cxf.configuration.Configurer;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.jsse.TLSParameterJaxBUtils;
import org.apache.cxf.configuration.security.FiltersType;
import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.cxf.configuration.security.TrustManagersType;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.cagrid.dorian.DorianPortType;
import org.cagrid.dorian.service.DorianService;

public class DorianSoapClientFactory {

	public static DorianPortType createSoapClient(String url) {
		DorianService dorianService = new DorianService();
		DorianPortType dorianPort = dorianService.getDorianPortTypePort();

		BindingProvider bp = (BindingProvider) dorianPort;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				url);
		return dorianPort;
	}

	public static DorianPortType createSoapClient(String url,
			KeyStoreType truststore, KeyManagersType keyManager)
			throws GeneralSecurityException, IOException {
		DorianPortType dorianPort = createSoapClient(url);

		Client dorianClient = ClientProxy.getClient(dorianPort);
		Bus bus = dorianClient.getBus();
		Configurer baseConf = bus.getExtension(Configurer.class);

		TrustManager[] trustManagers = SSLConfigurer
				.createTrustManagers(truststore);
		KeyManager[] keyManagers = SSLConfigurer.createKeyManagers(keyManager);
		SSLConfigurer sslConf = new SSLConfigurer(baseConf, trustManagers,
				keyManagers);
		bus.setExtension(sslConf, Configurer.class);

		return dorianPort;
	}

	public static DorianPortType createSoapClient(String url,
			KeyStoreType truststore, KeyManager keyManager)
			throws GeneralSecurityException, IOException {
		DorianPortType dorianPort = createSoapClient(url);

		Client dorianClient = ClientProxy.getClient(dorianPort);
		Bus bus = dorianClient.getBus();
		Configurer baseConf = bus.getExtension(Configurer.class);

		TrustManager[] trustManagers = SSLConfigurer
				.createTrustManagers(truststore);
		SSLConfigurer sslConf = new SSLConfigurer(baseConf, trustManagers,
				new KeyManager[] { keyManager });
		bus.setExtension(sslConf, Configurer.class);

		return dorianPort;
	}

	static class SSLConfigurer implements Configurer {
		private final Configurer parentConfigurer;
		private final TrustManager[] trustManagers;
		private final KeyManager[] keyManagers;

		public static TrustManager[] createTrustManagers(KeyStoreType truststore)
				throws GeneralSecurityException, IOException {
			TrustManagersType trustManagersType = new TrustManagersType();
			trustManagersType.setKeyStore(truststore);
			TrustManager[] trustManagers = TLSParameterJaxBUtils
					.getTrustManagers(trustManagersType);
			return trustManagers;
		}

		public static KeyManager[] createKeyManagers(KeyManagersType keyManager)
				throws GeneralSecurityException, IOException {
			KeyManager[] keyManagers = null;
			if (keyManager != null) {
				keyManagers = TLSParameterJaxBUtils.getKeyManagers(keyManager);
			} else {
				keyManagers = null;
			}
			return keyManagers;
		}

		public SSLConfigurer(Configurer parentConfigurer,
				TrustManager[] trustManagers, KeyManager[] keyManagers) {
			this.parentConfigurer = parentConfigurer;
			this.trustManagers = trustManagers;
			this.keyManagers = keyManagers;
		}

		@Override
		public void configureBean(Object beanInstance) {
			configureBean(null, beanInstance);
		}

		@Override
		public void configureBean(String name, Object beanInstance) {
			if (beanInstance instanceof HTTPConduit) {
				HTTPConduit http = (HTTPConduit) beanInstance;
				TLSClientParameters tls = new TLSClientParameters();
				tls.setTrustManagers(trustManagers);
				tls.setKeyManagers(keyManagers);
				tls.setDisableCNCheck(true);
				tls.setCipherSuitesFilter(getCipherSuites());
				http.setTlsClientParameters(tls);
				HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
				httpClientPolicy.setConnectionTimeout(36000);
				httpClientPolicy.setAllowChunking(false);
				httpClientPolicy.setReceiveTimeout(120000);
				http.setClient(httpClientPolicy);
			} else {
				parentConfigurer.configureBean(name, beanInstance);
			}
		}

		private FiltersType getCipherSuites() {
			FiltersType filters = new FiltersType();
			filters.getInclude().add(".*_WITH_3DES_.*");
			filters.getInclude().add(".*_WITH_DES_.*");
			filters.getExclude().add(".*_WITH_NULL_.*");
			filters.getExclude().add(".*_DH_anon_.*");
			return filters;
		}
	}
}
