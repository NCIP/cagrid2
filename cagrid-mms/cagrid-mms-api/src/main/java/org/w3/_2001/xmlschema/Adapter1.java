
package org.w3._2001.xmlschema;

import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, URI>
{


    public URI unmarshal(String value) {
        try {
			return new URI(value);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

    public String marshal(URI value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}
