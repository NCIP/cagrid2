
package org.cagrid.gaards.security.servicesecurity;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.gaards.security.servicesecurity package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.gaards.security.servicesecurity
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceSecurityResourceProperties }
     * 
     */
    public ServiceSecurityResourceProperties createServiceSecurityResourceProperties() {
        return new ServiceSecurityResourceProperties();
    }

    /**
     * Create an instance of {@link GetServiceSecurityMetadataRequest }
     * 
     */
    public GetServiceSecurityMetadataRequest createGetServiceSecurityMetadataRequest() {
        return new GetServiceSecurityMetadataRequest();
    }

    /**
     * Create an instance of {@link GetServiceSecurityMetadataResponse }
     * 
     */
    public GetServiceSecurityMetadataResponse createGetServiceSecurityMetadataResponse() {
        return new GetServiceSecurityMetadataResponse();
    }

}
