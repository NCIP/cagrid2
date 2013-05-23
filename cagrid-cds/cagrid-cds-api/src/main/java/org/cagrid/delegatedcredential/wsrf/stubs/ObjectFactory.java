
package org.cagrid.delegatedcredential.wsrf.stubs;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.delegatedcredential.wsrf.stubs package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.delegatedcredential.wsrf.stubs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDelegatedCredentialRequest.PublicKey }
     * 
     */
    public GetDelegatedCredentialRequest.PublicKey createGetDelegatedCredentialRequestPublicKey() {
        return new GetDelegatedCredentialRequest.PublicKey();
    }

    /**
     * Create an instance of {@link GetDelegatedCredentialRequest }
     * 
     */
    public GetDelegatedCredentialRequest createGetDelegatedCredentialRequest() {
        return new GetDelegatedCredentialRequest();
    }

    /**
     * Create an instance of {@link DelegatedCredentialResourceProperties }
     * 
     */
    public DelegatedCredentialResourceProperties createDelegatedCredentialResourceProperties() {
        return new DelegatedCredentialResourceProperties();
    }

    /**
     * Create an instance of {@link GetDelegatedCredentialResponse }
     * 
     */
    public GetDelegatedCredentialResponse createGetDelegatedCredentialResponse() {
        return new GetDelegatedCredentialResponse();
    }

}
