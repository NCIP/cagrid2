package org.cagrid.gridgrouper.soapclient;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.net.ssl.KeyManager;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.soapclient.SoapClientFactory;
import org.cagrid.gridgrouper.wsrf.service.GridGrouperService;
import org.cagrid.gridgrouper.wsrf.stubs.GridGrouperPortType;

public class GridGrouperSoapClientFactory {

	public static GridGrouperPortType createSoapClient(String url) {
		GridGrouperService gridGrouperService = new GridGrouperService();
		GridGrouperPortType gridGrouperPort = gridGrouperService
				.getGridGrouperPortTypePort();
		SoapClientFactory.configureSoapClient(
				(BindingProvider) gridGrouperPort, url);
		return gridGrouperPort;
	}

	public static GridGrouperPortType createSoapClient(String url,
			KeyStoreType truststore, KeyManagersType keyManager)
			throws GeneralSecurityException, IOException {
		GridGrouperService gridGrouperService = new GridGrouperService();
		GridGrouperPortType gridGrouperPort = gridGrouperService
				.getGridGrouperPortTypePort();
		SoapClientFactory.configureSoapClient(
				(BindingProvider) gridGrouperPort, url, truststore, keyManager);
		return gridGrouperPort;
	}

	public static GridGrouperPortType createSoapClient(String url,
			KeyStoreType truststore, KeyManager keyManager)
			throws GeneralSecurityException, IOException {
		GridGrouperService gridGrouperService = new GridGrouperService();
		GridGrouperPortType gridGrouperPort = gridGrouperService
				.getGridGrouperPortTypePort();
		SoapClientFactory.configureSoapClient(
				(BindingProvider) gridGrouperPort, url, truststore, keyManager);
		return gridGrouperPort;
	}
}
