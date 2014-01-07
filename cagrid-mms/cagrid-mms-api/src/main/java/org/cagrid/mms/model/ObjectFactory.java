
package org.cagrid.mms.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.mms.model package. 
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

    private final static QName _UMLAssociationExclude_QNAME = new QName("gme://caGrid.caBIG/2.0/org.cagrid.mms.domain", "UMLAssociationExclude");
    private final static QName _UMLProjectIdentifer_QNAME = new QName("gme://caGrid.caBIG/2.0/org.cagrid.mms.domain", "UMLProjectIdentifer");
    private final static QName _ModelSourceMetadata_QNAME = new QName("gme://caGrid.caBIG/2.0/org.cagrid.mms.domain", "ModelSourceMetadata");
    private final static QName _NamespaceToProjectMapping_QNAME = new QName("gme://caGrid.caBIG/2.0/org.cagrid.mms.domain", "NamespaceToProjectMapping");
    private final static QName _PropertyDescriptor_QNAME = new QName("gme://caGrid.caBIG/2.0/org.cagrid.mms.domain", "PropertyDescriptor");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.mms.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SupportedProjectProperties }
     * 
     */
    public SupportedProjectProperties createSupportedProjectProperties() {
        return new SupportedProjectProperties();
    }

    /**
     * Create an instance of {@link ModelSourceMetadata }
     * 
     */
    public ModelSourceMetadata createModelSourceMetadata() {
        return new ModelSourceMetadata();
    }

    /**
     * Create an instance of {@link Property }
     * 
     */
    public Property createProperty() {
        return new Property();
    }

    /**
     * Create an instance of {@link NamespaceToProjectMapping }
     * 
     */
    public NamespaceToProjectMapping createNamespaceToProjectMapping() {
        return new NamespaceToProjectMapping();
    }

    /**
     * Create an instance of {@link UMLProjectIdentifer }
     * 
     */
    public UMLProjectIdentifer createUMLProjectIdentifer() {
        return new UMLProjectIdentifer();
    }

    /**
     * Create an instance of {@link ModelSourceMetadata.SupportedModelSources }
     * 
     */
    public ModelSourceMetadata.SupportedModelSources createModelSourceMetadataSupportedModelSources() {
        return new ModelSourceMetadata.SupportedModelSources();
    }

    /**
     * Create an instance of {@link UMLAssociationExclude }
     * 
     */
    public UMLAssociationExclude createUMLAssociationExclude() {
        return new UMLAssociationExclude();
    }

    /**
     * Create an instance of {@link SourceDescriptor }
     * 
     */
    public SourceDescriptor createSourceDescriptor() {
        return new SourceDescriptor();
    }

    /**
     * Create an instance of {@link PropertyDescriptor }
     * 
     */
    public PropertyDescriptor createPropertyDescriptor() {
        return new PropertyDescriptor();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UMLAssociationExclude }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "gme://caGrid.caBIG/2.0/org.cagrid.mms.domain", name = "UMLAssociationExclude")
    public JAXBElement<UMLAssociationExclude> createUMLAssociationExclude(UMLAssociationExclude value) {
        return new JAXBElement<UMLAssociationExclude>(_UMLAssociationExclude_QNAME, UMLAssociationExclude.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UMLProjectIdentifer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "gme://caGrid.caBIG/2.0/org.cagrid.mms.domain", name = "UMLProjectIdentifer")
    public JAXBElement<UMLProjectIdentifer> createUMLProjectIdentifer(UMLProjectIdentifer value) {
        return new JAXBElement<UMLProjectIdentifer>(_UMLProjectIdentifer_QNAME, UMLProjectIdentifer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModelSourceMetadata }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "gme://caGrid.caBIG/2.0/org.cagrid.mms.domain", name = "ModelSourceMetadata")
    public JAXBElement<ModelSourceMetadata> createModelSourceMetadata(ModelSourceMetadata value) {
        return new JAXBElement<ModelSourceMetadata>(_ModelSourceMetadata_QNAME, ModelSourceMetadata.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NamespaceToProjectMapping }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "gme://caGrid.caBIG/2.0/org.cagrid.mms.domain", name = "NamespaceToProjectMapping")
    public JAXBElement<NamespaceToProjectMapping> createNamespaceToProjectMapping(NamespaceToProjectMapping value) {
        return new JAXBElement<NamespaceToProjectMapping>(_NamespaceToProjectMapping_QNAME, NamespaceToProjectMapping.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PropertyDescriptor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "gme://caGrid.caBIG/2.0/org.cagrid.mms.domain", name = "PropertyDescriptor")
    public JAXBElement<PropertyDescriptor> createPropertyDescriptor(PropertyDescriptor value) {
        return new JAXBElement<PropertyDescriptor>(_PropertyDescriptor_QNAME, PropertyDescriptor.class, null, value);
    }

}
