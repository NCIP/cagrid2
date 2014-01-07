
package org.cagrid.mms.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.mms.types package. 
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

    private final static QName _InvalidUMLProjectIndentifier_QNAME = new QName("http://mms.cagrid.org/MetadataModelService/types", "InvalidUMLProjectIndentifier");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.mms.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvalidUMLProjectIndentifier }
     * 
     */
    public InvalidUMLProjectIndentifier createInvalidUMLProjectIndentifier() {
        return new InvalidUMLProjectIndentifier();
    }

    /**
     * Create an instance of {@link MetadataModelServiceReference }
     * 
     */
    public MetadataModelServiceReference createMetadataModelServiceReference() {
        return new MetadataModelServiceReference();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidUMLProjectIndentifier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://mms.cagrid.org/MetadataModelService/types", name = "InvalidUMLProjectIndentifier")
    public JAXBElement<InvalidUMLProjectIndentifier> createInvalidUMLProjectIndentifier(InvalidUMLProjectIndentifier value) {
        return new JAXBElement<InvalidUMLProjectIndentifier>(_InvalidUMLProjectIndentifier_QNAME, InvalidUMLProjectIndentifier.class, null, value);
    }

}
