package org.cagrid.gme.soapclient;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.core.common.security.CredentialFactory;
import org.cagrid.core.common.security.X509Credential;
import org.cagrid.core.soapclient.SingleEntityKeyManager;
import org.cagrid.gme.wsrf.stubs.GlobalModelExchangePortType;

import javax.net.ssl.KeyManager;

public abstract class GrouperClientBase {

	public final static String LOCAL_URL = "https://localhost:7741/gme";

	protected GlobalModelExchangePortType gme;

	GrouperClientBase(String url) throws Exception {
		KeyStoreType truststore = new KeyStoreType();
        truststore.setFile("/Users/cmelean/Documents/Developer/source/cagrid/apache-servicemix-4.5.1/etc/gme/truststore.jks");
        truststore.setType("JKS");
        truststore.setPassword("inventrio");

        X509Credential credential = CredentialFactory.getCredential(
                "/Users/cmelean/Documents/Developer/source/cagrid/apache-servicemix-4.5.1/etc/gme/host.jks",
                "inventrio",
                "tomcat",
                "inventrio");

        KeyManager keyManager = new SingleEntityKeyManager("tomcat", credential);

        gme = GMESoapClientFactory.createSoapClient(url, truststore, keyManager);
	}
}
