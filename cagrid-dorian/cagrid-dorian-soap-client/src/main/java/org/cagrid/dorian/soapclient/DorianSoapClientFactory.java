package org.cagrid.dorian.soapclient;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.net.ssl.KeyManager;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.soapclient.SoapClientFactory;
import org.cagrid.dorian.DorianPortType;
import org.cagrid.dorian.service.DorianService;

public class DorianSoapClientFactory {

	public static DorianPortType createSoapClient(String url) {
		DorianService dorianService = new DorianService();
		DorianPortType dorianPort = dorianService.getDorianPortTypePort();
		SoapClientFactory
				.configureSoapClient((BindingProvider) dorianPort, url);
		return dorianPort;
	}

	public static DorianPortType createSoapClient(String url,
			KeyStoreType truststore, KeyManagersType keyManager)
			throws GeneralSecurityException, IOException {
		DorianService dorianService = new DorianService();
		DorianPortType dorianPort = dorianService.getDorianPortTypePort();
		SoapClientFactory.configureSoapClient((BindingProvider) dorianPort,
				url, truststore, keyManager);
		return dorianPort;
	}

	public static DorianPortType createSoapClient(String url,
			KeyStoreType truststore, KeyManager keyManager)
			throws GeneralSecurityException, IOException {
		DorianService dorianService = new DorianService();
		DorianPortType dorianPort = dorianService.getDorianPortTypePort();
		SoapClientFactory.configureSoapClient((BindingProvider) dorianPort,
				url, truststore, keyManager);
		return dorianPort;
	}
}
