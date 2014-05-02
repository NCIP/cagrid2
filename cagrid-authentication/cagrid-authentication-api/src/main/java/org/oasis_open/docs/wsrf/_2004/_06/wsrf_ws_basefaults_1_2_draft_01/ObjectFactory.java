
package org.oasis_open.docs.wsrf._2004._06.wsrf_ws_basefaults_1_2_draft_01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.oasis_open.docs.wsrf._2004._06.wsrf_ws_basefaults_1_2_draft_01 package. 
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

    private final static QName _BaseFault_QNAME = new QName("http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-BaseFaults-1.2-draft-01.xsd", "BaseFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.oasis_open.docs.wsrf._2004._06.wsrf_ws_basefaults_1_2_draft_01
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BaseFaultType }
     * 
     */
    public BaseFaultType createBaseFaultType() {
        return new BaseFaultType();
    }

    /**
     * Create an instance of {@link BaseFaultType.ErrorCode }
     * 
     */
    public BaseFaultType.ErrorCode createBaseFaultTypeErrorCode() {
        return new BaseFaultType.ErrorCode();
    }

    /**
     * Create an instance of {@link BaseFaultType.Description }
     * 
     */
    public BaseFaultType.Description createBaseFaultTypeDescription() {
        return new BaseFaultType.Description();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseFaultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://docs.oasis-open.org/wsrf/2004/06/wsrf-WS-BaseFaults-1.2-draft-01.xsd", name = "BaseFault")
    public JAXBElement<BaseFaultType> createBaseFault(BaseFaultType value) {
        return new JAXBElement<BaseFaultType>(_BaseFault_QNAME, BaseFaultType.class, null, value);
    }

}
