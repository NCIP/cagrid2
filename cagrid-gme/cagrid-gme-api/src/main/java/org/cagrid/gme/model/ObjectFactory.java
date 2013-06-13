
package org.cagrid.gme.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.gme.model package. 
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

    private final static QName _XMLSchema_QNAME = new QName("gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", "XMLSchema");
    private final static QName _XMLSchemaImportInformation_QNAME = new QName("gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", "XMLSchemaImportInformation");
    private final static QName _XMLSchemaBundle_QNAME = new QName("gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", "XMLSchemaBundle");
    private final static QName _XMLSchemaDocument_QNAME = new QName("gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", "XMLSchemaDocument");
    private final static QName _XMLSchemaNamespace_QNAME = new QName("gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", "XMLSchemaNamespace");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.gme.model
     * 
     */
    public ObjectFactory() {
    }



    /**
     * Create an instance of {@link XMLSchema }
     * 
     */
    public XMLSchema createXMLSchema() {
        return new XMLSchema();
    }

    /**
     * Create an instance of {@link XMLSchemaImportInformation }
     * 
     */
    public XMLSchemaImportInformation createXMLSchemaImportInformation() {
        return new XMLSchemaImportInformation();
    }



    /**
     * Create an instance of {@link XMLSchemaDocument }
     * 
     */
    public XMLSchemaDocument createXMLSchemaDocument() {
        return new XMLSchemaDocument();
    }

    /**
     * Create an instance of {@link XMLSchemaNamespace }
     * 
     */
    public XMLSchemaNamespace createXMLSchemaNamespace() {
        return new XMLSchemaNamespace();
    }



    /**
     * Create an instance of {@link XMLSchemaBundle }
     * 
     */
    public XMLSchemaBundle createXMLSchemaBundle() {
        return new XMLSchemaBundle();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLSchema }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", name = "XMLSchema")
    public JAXBElement<XMLSchema> createXMLSchema(XMLSchema value) {
        return new JAXBElement<XMLSchema>(_XMLSchema_QNAME, XMLSchema.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLSchemaImportInformation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", name = "XMLSchemaImportInformation")
    public JAXBElement<XMLSchemaImportInformation> createXMLSchemaImportInformation(XMLSchemaImportInformation value) {
        return new JAXBElement<XMLSchemaImportInformation>(_XMLSchemaImportInformation_QNAME, XMLSchemaImportInformation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLSchemaBundle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", name = "XMLSchemaBundle")
    public JAXBElement<XMLSchemaBundle> createXMLSchemaBundle(XMLSchemaBundle value) {
        return new JAXBElement<XMLSchemaBundle>(_XMLSchemaBundle_QNAME, XMLSchemaBundle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLSchemaDocument }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", name = "XMLSchemaDocument")
    public JAXBElement<XMLSchemaDocument> createXMLSchemaDocument(XMLSchemaDocument value) {
        return new JAXBElement<XMLSchemaDocument>(_XMLSchemaDocument_QNAME, XMLSchemaDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLSchemaNamespace }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "gme://gme.cagrid.org/2.0/GlobalModelExchange/domain", name = "XMLSchemaNamespace")
    public JAXBElement<XMLSchemaNamespace> createXMLSchemaNamespace(XMLSchemaNamespace value) {
        return new JAXBElement<XMLSchemaNamespace>(_XMLSchemaNamespace_QNAME, XMLSchemaNamespace.class, null, value);
    }

}
