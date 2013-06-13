
package org.cagrid.gme.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.gme.types package. 
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

    private final static QName _InvalidSchemaSubmissionFault_QNAME = new QName("http://gme.cagrid.org/GlobalModelExchange/types", "InvalidSchemaSubmissionFault");
    private final static QName _UnableToDeleteSchemaFault_QNAME = new QName("http://gme.cagrid.org/GlobalModelExchange/types", "UnableToDeleteSchemaFault");
    private final static QName _NoSuchNamespaceExistsFault_QNAME = new QName("http://gme.cagrid.org/GlobalModelExchange/types", "NoSuchNamespaceExistsFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.gme.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link org.cagrid.gme.types.NoSuchNamespaceExistsFault }
     * 
     */
    public NoSuchNamespaceExistsFault createNoSuchNamespaceExistsFault() {
        return new NoSuchNamespaceExistsFault();
    }

    /**
     * Create an instance of {@link org.cagrid.gme.types.InvalidSchemaSubmissionFault }
     * 
     */
    public InvalidSchemaSubmissionFault createInvalidSchemaSubmissionFault() {
        return new InvalidSchemaSubmissionFault();
    }

    /**
     * Create an instance of {@link org.cagrid.gme.types.GlobalModelExchangeReference }
     * 
     */
    public GlobalModelExchangeReference createGlobalModelExchangeReference() {
        return new GlobalModelExchangeReference();
    }

    /**
     * Create an instance of {@link UnableToDeleteSchemaFault }
     * 
     */
    public UnableToDeleteSchemaFault createUnableToDeleteSchemaFault() {
        return new UnableToDeleteSchemaFault();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gme.types.InvalidSchemaSubmissionFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gme.cagrid.org/GlobalModelExchange/types", name = "InvalidSchemaSubmissionFault")
    public JAXBElement<InvalidSchemaSubmissionFault> createInvalidSchemaSubmissionFault(InvalidSchemaSubmissionFault value) {
        return new JAXBElement<InvalidSchemaSubmissionFault>(_InvalidSchemaSubmissionFault_QNAME, InvalidSchemaSubmissionFault.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link UnableToDeleteSchemaFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gme.cagrid.org/GlobalModelExchange/types", name = "UnableToDeleteSchemaFault")
    public JAXBElement<UnableToDeleteSchemaFault> createUnableToDeleteSchemaFault(UnableToDeleteSchemaFault value) {
        return new JAXBElement<UnableToDeleteSchemaFault>(_UnableToDeleteSchemaFault_QNAME, UnableToDeleteSchemaFault.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.gme.types.NoSuchNamespaceExistsFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gme.cagrid.org/GlobalModelExchange/types", name = "NoSuchNamespaceExistsFault")
    public JAXBElement<NoSuchNamespaceExistsFault> createNoSuchNamespaceExistsFault(NoSuchNamespaceExistsFault value) {
        return new JAXBElement<NoSuchNamespaceExistsFault>(_NoSuchNamespaceExistsFault_QNAME, NoSuchNamespaceExistsFault.class, null, value);
    }

}
