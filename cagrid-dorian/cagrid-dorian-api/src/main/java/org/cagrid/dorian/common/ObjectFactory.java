
package org.cagrid.dorian.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.dorian.common package. 
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

    private final static QName _Metadata_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-common", "Metadata");
    private final static QName _SAMLAssertion_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-common", "SAMLAssertion");
    private final static QName _X509Certificate_QNAME = new QName("http://cagrid.nci.nih.gov/1/dorian-common", "X509Certificate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.dorian.common
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link X509Certificate }
     * 
     */
    public X509Certificate createX509Certificate() {
        return new X509Certificate();
    }

    /**
     * Create an instance of {@link SAMLAssertion }
     * 
     */
    public SAMLAssertion createSAMLAssertion() {
        return new SAMLAssertion();
    }

    /**
     * Create an instance of {@link Metadata }
     * 
     */
    public Metadata createMetadata() {
        return new Metadata();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Metadata }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-common", name = "Metadata")
    public JAXBElement<Metadata> createMetadata(Metadata value) {
        return new JAXBElement<Metadata>(_Metadata_QNAME, Metadata.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SAMLAssertion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-common", name = "SAMLAssertion")
    public JAXBElement<SAMLAssertion> createSAMLAssertion(SAMLAssertion value) {
        return new JAXBElement<SAMLAssertion>(_SAMLAssertion_QNAME, SAMLAssertion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link X509Certificate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/1/dorian-common", name = "X509Certificate")
    public JAXBElement<X509Certificate> createX509Certificate(X509Certificate value) {
        return new JAXBElement<X509Certificate>(_X509Certificate_QNAME, X509Certificate.class, null, value);
    }

}
