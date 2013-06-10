
package org.cagrid.gaards.authentication.faults;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.gaards.authentication.faults package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.gaards.authentication.faults
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvalidCredentialFault }
     * 
     */
    public InvalidCredentialFault createInvalidCredentialFault() {
        return new InvalidCredentialFault();
    }

    /**
     * Create an instance of {@link InsufficientAttributeFault }
     * 
     */
    public InsufficientAttributeFault createInsufficientAttributeFault() {
        return new InsufficientAttributeFault();
    }

    /**
     * Create an instance of {@link CredentialNotSupportedFault }
     * 
     */
    public CredentialNotSupportedFault createCredentialNotSupportedFault() {
        return new CredentialNotSupportedFault();
    }

    /**
     * Create an instance of {@link AuthenticationProviderFault }
     * 
     */
    public AuthenticationProviderFault createAuthenticationProviderFault() {
        return new AuthenticationProviderFault();
    }

}
