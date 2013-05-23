
package org.cagrid.cds.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.cds.types package. 
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

    private final static QName _PermissionDeniedFault_QNAME = new QName("http://cds.gaards.cagrid.org/CredentialDelegationService/types", "PermissionDeniedFault");
    private final static QName _InvalidPolicyFault_QNAME = new QName("http://cds.gaards.cagrid.org/CredentialDelegationService/types", "InvalidPolicyFault");
    private final static QName _DelegationFault_QNAME = new QName("http://cds.gaards.cagrid.org/CredentialDelegationService/types", "DelegationFault");
    private final static QName _CDSInternalFault_QNAME = new QName("http://cds.gaards.cagrid.org/CredentialDelegationService/types", "CDSInternalFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.cds.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link org.cagrid.cds.types.DelegationFault }
     * 
     */
    public DelegationFault createDelegationFault() {
        return new DelegationFault();
    }

    /**
     * Create an instance of {@link org.cagrid.cds.types.PermissionDeniedFault }
     * 
     */
    public PermissionDeniedFault createPermissionDeniedFault() {
        return new PermissionDeniedFault();
    }

    /**
     * Create an instance of {@link org.cagrid.cds.types.CDSInternalFault }
     * 
     */
    public CDSInternalFault createCDSInternalFault() {
        return new CDSInternalFault();
    }

    /**
     * Create an instance of {@link org.cagrid.cds.types.CredentialDelegationServiceReference }
     * 
     */
    public CredentialDelegationServiceReference createCredentialDelegationServiceReference() {
        return new CredentialDelegationServiceReference();
    }

    /**
     * Create an instance of {@link org.cagrid.cds.types.InvalidPolicyFault }
     * 
     */
    public InvalidPolicyFault createInvalidPolicyFault() {
        return new InvalidPolicyFault();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.cds.types.PermissionDeniedFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cds.gaards.cagrid.org/CredentialDelegationService/types", name = "PermissionDeniedFault")
    public JAXBElement<PermissionDeniedFault> createPermissionDeniedFault(PermissionDeniedFault value) {
        return new JAXBElement<PermissionDeniedFault>(_PermissionDeniedFault_QNAME, PermissionDeniedFault.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.cds.types.InvalidPolicyFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cds.gaards.cagrid.org/CredentialDelegationService/types", name = "InvalidPolicyFault")
    public JAXBElement<InvalidPolicyFault> createInvalidPolicyFault(InvalidPolicyFault value) {
        return new JAXBElement<InvalidPolicyFault>(_InvalidPolicyFault_QNAME, InvalidPolicyFault.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.cds.types.DelegationFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cds.gaards.cagrid.org/CredentialDelegationService/types", name = "DelegationFault")
    public JAXBElement<DelegationFault> createDelegationFault(DelegationFault value) {
        return new JAXBElement<DelegationFault>(_DelegationFault_QNAME, DelegationFault.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link org.cagrid.cds.types.CDSInternalFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cds.gaards.cagrid.org/CredentialDelegationService/types", name = "CDSInternalFault")
    public JAXBElement<CDSInternalFault> createCDSInternalFault(CDSInternalFault value) {
        return new JAXBElement<CDSInternalFault>(_CDSInternalFault_QNAME, CDSInternalFault.class, null, value);
    }

}
