package org.cagrid.gme.soapclient;

import org.cagrid.mms.soapclient.MMSSoapClientFactory;
import org.cagrid.mms.wsrf.stubs.MetadataModelServicePortType;

public abstract class MMSClientBase {

	public final static String LOCAL_URL = "https://localhost:7742/mms";

	protected MetadataModelServicePortType mms;

	MMSClientBase(String url) throws Exception {
        mms = MMSSoapClientFactory.createSoapClient(url);
	}
}
