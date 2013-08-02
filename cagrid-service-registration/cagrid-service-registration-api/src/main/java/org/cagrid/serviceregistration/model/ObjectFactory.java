
package org.cagrid.serviceregistration.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.serviceregistration.client package. 
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

    private final static QName _ServiceGroupRegistrationParameters_QNAME = new QName("http://mds.globus.org/servicegroup/client", "ServiceGroupRegistrationParameters");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.serviceregistration.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceGroupRegistrationParameters }
     * 
     */
    public ServiceGroupRegistrationParameters createServiceGroupRegistrationParameters() {
        return new ServiceGroupRegistrationParameters();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceGroupRegistrationParameters }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mds.globus.org/servicegroup/client", name = "ServiceGroupRegistrationParameters")
    public JAXBElement<ServiceGroupRegistrationParameters> createServiceGroupRegistrationParameters(ServiceGroupRegistrationParameters value) {
        return new JAXBElement<ServiceGroupRegistrationParameters>(_ServiceGroupRegistrationParameters_QNAME, ServiceGroupRegistrationParameters.class, null, value);
    }

}
