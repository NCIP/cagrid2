package org.cagrid.cds.soapclient;

import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.cds.wsrf.stubs.CredentialDelegationServicePortType;

public abstract class CDSClientBase {

	public final static String LOCAL_URL = "http://localhost:7735/cds";
	public final static String TOMCAT_URL = "http://localhost:8080/wsrf/services/cagrid/CredentialDelegationService";
	public final static String TRAINING_URL = "https://dorian.training.cagrid.org:8443/wsrf/services/cagrid/CredentialDelegationService";

	protected CredentialDelegationServicePortType cds;

	CDSClientBase(String url) throws Exception {
		KeyStoreType truststore = new KeyStoreType();
		truststore.setUrl(getClass().getClassLoader().getResource("truststore.jks").toString());
		truststore.setType("JKS");
		truststore.setPassword("changeit");

		cds = CDSSoapClientFactory.createSoapClient(url, truststore, null);
	}
}
