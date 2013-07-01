package org.cagrid.cds.soapclient;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.net.ssl.KeyManager;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.cds.wsrf.service.CredentialDelegationService;
import org.cagrid.cds.wsrf.stubs.CredentialDelegationServicePortType;
import org.cagrid.core.soapclient.SoapClientFactory;

public class CDSSoapClientFactory {

	public static CredentialDelegationServicePortType createSoapClient(
			String url) {
		CredentialDelegationService cds = new CredentialDelegationService();
		CredentialDelegationServicePortType cdsPort = cds
				.getCredentialDelegationServicePortTypePort();
		SoapClientFactory.configureSoapClient((BindingProvider) cdsPort, url);
		return cdsPort;
	}

	public static CredentialDelegationServicePortType createSoapClient(
			String url, KeyStoreType truststore, KeyManagersType keyManager)
			throws GeneralSecurityException, IOException {
		CredentialDelegationService cds = new CredentialDelegationService();
		CredentialDelegationServicePortType cdsPort = cds
				.getCredentialDelegationServicePortTypePort();
		SoapClientFactory.configureSoapClient((BindingProvider) cdsPort, url,
				truststore, keyManager);
		return cdsPort;
	}

	public static CredentialDelegationServicePortType createSoapClient(
			String url, KeyStoreType truststore, KeyManager keyManager)
			throws GeneralSecurityException, IOException {
		CredentialDelegationService cds = new CredentialDelegationService();
		CredentialDelegationServicePortType cdsPort = cds
				.getCredentialDelegationServicePortTypePort();
		SoapClientFactory.configureSoapClient((BindingProvider) cdsPort, url,
				truststore, keyManager);
		return cdsPort;
	}
}
