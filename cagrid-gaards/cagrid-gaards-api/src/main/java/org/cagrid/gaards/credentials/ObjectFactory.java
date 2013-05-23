
package org.cagrid.gaards.credentials;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.gaards.credentials package. 
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

    private final static QName _X509CredentialDescriptor_QNAME = new QName("http://gaards.cagrid.org/credentials", "X509CredentialDescriptor");
    private final static QName _DorianUserCredentialDescriptor_QNAME = new QName("http://gaards.cagrid.org/credentials", "DorianUserCredentialDescriptor");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.gaards.credentials
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link X509CredentialDescriptor }
     * 
     */
    public X509CredentialDescriptor createX509CredentialDescriptor() {
        return new X509CredentialDescriptor();
    }

    /**
     * Create an instance of {@link EncodedCertificates }
     * 
     */
    public EncodedCertificates createEncodedCertificates() {
        return new EncodedCertificates();
    }

    /**
     * Create an instance of {@link DorianUserCredentialDescriptor }
     * 
     */
    public DorianUserCredentialDescriptor createDorianUserCredentialDescriptor() {
        return new DorianUserCredentialDescriptor();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link X509CredentialDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/credentials", name = "X509CredentialDescriptor")
    public JAXBElement<X509CredentialDescriptor> createX509CredentialDescriptor(X509CredentialDescriptor value) {
        return new JAXBElement<X509CredentialDescriptor>(_X509CredentialDescriptor_QNAME, X509CredentialDescriptor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DorianUserCredentialDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/credentials", name = "DorianUserCredentialDescriptor")
    public JAXBElement<DorianUserCredentialDescriptor> createDorianUserCredentialDescriptor(DorianUserCredentialDescriptor value) {
        return new JAXBElement<DorianUserCredentialDescriptor>(_DorianUserCredentialDescriptor_QNAME, DorianUserCredentialDescriptor.class, null, value);
    }

}
