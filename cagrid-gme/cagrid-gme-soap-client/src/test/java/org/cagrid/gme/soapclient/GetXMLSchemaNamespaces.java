package org.cagrid.gme.soapclient;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.cagrid.gme.model.XMLSchemaNamespace;
import org.cagrid.gme.wsrf.stubs.GetXMLSchemaNamespacesRequest;
import org.cagrid.gme.wsrf.stubs.GetXMLSchemaNamespacesResponse;

import java.util.List;

public class GetXMLSchemaNamespaces extends GMEClientBase {

	private GetXMLSchemaNamespaces(String url) throws Exception {
		super(url);
	}

    public List<XMLSchemaNamespace> getXMLSchemaNamespaces() {
        GetXMLSchemaNamespacesResponse response = gme.getXMLSchemaNamespaces(new GetXMLSchemaNamespacesRequest());
        return response.getXMLSchemaNamespace();
    }

	public static void main(String[] args) {

        try {
		    GetXMLSchemaNamespaces data = new GetXMLSchemaNamespaces(LOCAL_URL);
            for(XMLSchemaNamespace ns : data.getXMLSchemaNamespaces()) {
                System.out.println(ns.toString());
            }
        } catch(Exception e) {
            System.out.println(ExceptionUtils.getFullStackTrace(e));
        }

	}
}
