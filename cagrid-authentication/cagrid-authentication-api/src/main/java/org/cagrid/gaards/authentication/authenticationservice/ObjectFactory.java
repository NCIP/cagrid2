
package org.cagrid.gaards.authentication.authenticationservice;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.gaards.authentication.authenticationservice package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.gaards.authentication.authenticationservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthenticateUserRequest }
     * 
     */
    public AuthenticateUserRequest createAuthenticateUserRequest() {
        return new AuthenticateUserRequest();
    }

    /**
     * Create an instance of {@link AuthenticationServiceResourceProperties }
     * 
     */
    public AuthenticationServiceResourceProperties createAuthenticationServiceResourceProperties() {
        return new AuthenticationServiceResourceProperties();
    }

    /**
     * Create an instance of {@link AuthenticateUserResponse }
     * 
     */
    public AuthenticateUserResponse createAuthenticateUserResponse() {
        return new AuthenticateUserResponse();
    }

    /**
     * Create an instance of {@link GetLockedOutUsersResponse }
     * 
     */
    public GetLockedOutUsersResponse createGetLockedOutUsersResponse() {
        return new GetLockedOutUsersResponse();
    }

    /**
     * Create an instance of {@link GetLockedOutUsersRequest }
     * 
     */
    public GetLockedOutUsersRequest createGetLockedOutUsersRequest() {
        return new GetLockedOutUsersRequest();
    }

    /**
     * Create an instance of {@link AuthenticateUserRequest.Credential }
     * 
     */
    public AuthenticateUserRequest.Credential createAuthenticateUserRequestCredential() {
        return new AuthenticateUserRequest.Credential();
    }

}
