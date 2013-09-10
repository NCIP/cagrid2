
package org.w3._2001.xmlschema;

import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, URI>
{


    public URI unmarshal(String value) throws URISyntaxException {
        return new URI(value);
    }

    public String marshal(URI value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}
