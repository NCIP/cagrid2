
package org.cagrid.gaards.authentication.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.gaards.authentication.service package. 
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

    private final static QName _Credential_QNAME = new QName("http://cagrid.nci.nih.gov/1/authentication-service", "Credential");
    private final static QName _SAMLAssertion_QNAME = new QName("http://cagrid.nci.nih.gov/1/authentication-service", "SAMLAssertion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.gaards.authentication.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Credential }
     * 
     */
    public Credential createCredential() {
        return new Credential();
    }

    /**
     * Create an instance of {@link SAMLAssertion }
     * 
     */
    public SAMLAssertion createSAMLAssertion() {
        return new SAMLAssertion();
    }

    /**
     * Create an instance of {@link BasicAuthenticationCredential }
     * 
     */
    public BasicAuthenticationCredential createBasicAuthenticationCredential() {
        return new BasicAuthenticationCredential();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Credential }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/authentication-service", name = "Credential")
    public JAXBElement<Credential> createCredential(Credential value) {
        return new JAXBElement<Credential>(_Credential_QNAME, Credential.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SAMLAssertion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/authentication-service", name = "SAMLAssertion")
    public JAXBElement<SAMLAssertion> createSAMLAssertion(SAMLAssertion value) {
        return new JAXBElement<SAMLAssertion>(_SAMLAssertion_QNAME, SAMLAssertion.class, null, value);
    }

}
