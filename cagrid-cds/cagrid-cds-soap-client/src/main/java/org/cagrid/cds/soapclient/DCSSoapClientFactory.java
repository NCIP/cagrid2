package org.cagrid.cds.soapclient;

import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.soapclient.SoapClientFactory;
import org.cagrid.delegatedcredential.wsrf.service.DelegatedCredentialService;
import org.cagrid.delegatedcredential.wsrf.stubs.DelegatedCredentialPortType;

import javax.net.ssl.KeyManager;
import javax.xml.ws.BindingProvider;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class DCSSoapClientFactory {

	public static DelegatedCredentialPortType createSoapClient(
			String url) {
        DelegatedCredentialService cds = new DelegatedCredentialService();
        DelegatedCredentialPortType cdsPort = cds.getDelegatedCredentialPortTypePort();
		SoapClientFactory.configureSoapClient((BindingProvider) cdsPort, url);
		return cdsPort;
	}

	public static DelegatedCredentialPortType createSoapClient(
			String url, KeyStoreType truststore, KeyManagersType keyManager)
			throws GeneralSecurityException, IOException {
        DelegatedCredentialService cds = new DelegatedCredentialService();
        DelegatedCredentialPortType cdsPort = cds.getDelegatedCredentialPortTypePort();
		SoapClientFactory.configureSoapClient((BindingProvider) cdsPort, url, truststore, keyManager);
		return cdsPort;
	}

	public static DelegatedCredentialPortType createSoapClient(
			String url, KeyStoreType truststore, KeyManager keyManager)
			throws GeneralSecurityException, IOException {
        DelegatedCredentialService cds = new DelegatedCredentialService();
        DelegatedCredentialPortType cdsPort = cds.getDelegatedCredentialPortTypePort();
		SoapClientFactory.configureSoapClient((BindingProvider) cdsPort, url, truststore, keyManager);
		return cdsPort;
	}
}
