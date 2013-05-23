
package org.cagrid.gaards.authentication;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.gaards.authentication package. 
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

    private final static QName _BasicAuthentication_QNAME = new QName("http://gaards.cagrid.org/authentication", "BasicAuthentication");
    private final static QName _AuthenticationProfiles_QNAME = new QName("http://gaards.cagrid.org/authentication", "AuthenticationProfiles");
    private final static QName _Credential_QNAME = new QName("http://gaards.cagrid.org/authentication", "Credential");
    private final static QName _OneTimePassword_QNAME = new QName("http://gaards.cagrid.org/authentication", "OneTimePassword");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.gaards.authentication
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthenticationServiceResourceProperties }
     * 
     */
    public AuthenticationServiceResourceProperties createAuthenticationServiceResourceProperties() {
        return new AuthenticationServiceResourceProperties();
    }

    /**
     * Create an instance of {@link GetLockedOutUsersResponse }
     * 
     */
    public GetLockedOutUsersResponse createGetLockedOutUsersResponse() {
        return new GetLockedOutUsersResponse();
    }

    /**
     * Create an instance of {@link BasicAuthentication }
     * 
     */
    public BasicAuthentication createBasicAuthentication() {
        return new BasicAuthentication();
    }

    /**
     * Create an instance of {@link AuthenticateUserRequest.Credential }
     * 
     */
    public AuthenticateUserRequest.Credential createAuthenticateUserRequestCredential() {
        return new AuthenticateUserRequest.Credential();
    }

    /**
     * Create an instance of {@link GetLockedOutUsersRequest }
     * 
     */
    public GetLockedOutUsersRequest createGetLockedOutUsersRequest() {
        return new GetLockedOutUsersRequest();
    }

    /**
     * Create an instance of {@link AuthenticateUserResponse }
     * 
     */
    public AuthenticateUserResponse createAuthenticateUserResponse() {
        return new AuthenticateUserResponse();
    }

    /**
     * Create an instance of {@link OneTimePassword }
     * 
     */
    public OneTimePassword createOneTimePassword() {
        return new OneTimePassword();
    }

    /**
     * Create an instance of {@link AuthenticateUserRequest }
     * 
     */
    public AuthenticateUserRequest createAuthenticateUserRequest() {
        return new AuthenticateUserRequest();
    }

    /**
     * Create an instance of {@link AuthenticationProfiles }
     * 
     */
    public AuthenticationProfiles createAuthenticationProfiles() {
        return new AuthenticationProfiles();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BasicAuthentication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/authentication", name = "BasicAuthentication")
    public JAXBElement<BasicAuthentication> createBasicAuthentication(BasicAuthentication value) {
        return new JAXBElement<BasicAuthentication>(_BasicAuthentication_QNAME, BasicAuthentication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthenticationProfiles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/authentication", name = "AuthenticationProfiles")
    public JAXBElement<AuthenticationProfiles> createAuthenticationProfiles(AuthenticationProfiles value) {
        return new JAXBElement<AuthenticationProfiles>(_AuthenticationProfiles_QNAME, AuthenticationProfiles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.cagrid.gaards.authentication.Credential }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/authentication", name = "Credential")
    public JAXBElement<org.cagrid.gaards.authentication.Credential> createCredential(org.cagrid.gaards.authentication.Credential value) {
        return new JAXBElement<org.cagrid.gaards.authentication.Credential>(_Credential_QNAME, org.cagrid.gaards.authentication.Credential.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OneTimePassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/authentication", name = "OneTimePassword")
    public JAXBElement<OneTimePassword> createOneTimePassword(OneTimePassword value) {
        return new JAXBElement<OneTimePassword>(_OneTimePassword_QNAME, OneTimePassword.class, null, value);
    }

}
