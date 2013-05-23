package org.cagrid.dorian.soapclient;

import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.cagrid.dorian.DorianPortType;

public abstract class DorianClientBase {

	public final static String LOCAL_URL = "http://localhost:7734/dorian";
	public final static String LOCALS_URL = "https://localhost:7734/dorian";
	public final static String TOMCAT_URL = "http://localhost:8080/wsrf/services/cagrid/Dorian";
	public final static String TRAINING_URL = "https://dorian.training.cagrid.org:8443/wsrf/services/cagrid/Dorian";

	protected DorianPortType dorian;

	DorianClientBase(String url) throws Exception {
		KeyStoreType truststore = new KeyStoreType();
		truststore.setUrl(getClass().getClassLoader()
				.getResource("truststore.jks").toString());
		truststore.setType("JKS");
		truststore.setPassword("changeit");

		dorian = DorianSoapClientFactory.createSoapClient(url, truststore,
				(KeyManagersType) null);
	}
}
