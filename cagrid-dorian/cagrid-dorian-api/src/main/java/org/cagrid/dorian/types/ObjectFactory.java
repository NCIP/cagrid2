
package org.cagrid.dorian.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.dorian.types package. 
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

    private final static QName _InvalidHostCertificateRequestFault_QNAME = new QName("http://cagrid.nci.nih.gov/Dorian/types", "InvalidHostCertificateRequestFault");
    private final static QName _InvalidUserCertificateFault_QNAME = new QName("http://cagrid.nci.nih.gov/Dorian/types", "InvalidUserCertificateFault");
    private final static QName _InvalidHostCertificateFault_QNAME = new QName("http://cagrid.nci.nih.gov/Dorian/types", "InvalidHostCertificateFault");
    private final static QName _AdminAlreadyExists_QNAME = new QName("http://cagrid.nci.nih.gov/Dorian/types", "AdminAlreadyExists");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.dorian.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserPolicyFault }
     * 
     */
    public UserPolicyFault createUserPolicyFault() {
        return new UserPolicyFault();
    }

    /**
     * Create an instance of {@link DorianReference }
     * 
     */
    public DorianReference createDorianReference() {
        return new DorianReference();
    }

    /**
     * Create an instance of {@link InvalidProxyFault }
     * 
     */
    public InvalidProxyFault createInvalidProxyFault() {
        return new InvalidProxyFault();
    }

    /**
     * Create an instance of {@link InvalidUserCertificateFault }
     * 
     */
    public InvalidUserCertificateFault createInvalidUserCertificateFault() {
        return new InvalidUserCertificateFault();
    }

    /**
     * Create an instance of {@link InvalidUserFault }
     * 
     */
    public InvalidUserFault createInvalidUserFault() {
        return new InvalidUserFault();
    }

    /**
     * Create an instance of {@link InvalidHostCertificateFault }
     * 
     */
    public InvalidHostCertificateFault createInvalidHostCertificateFault() {
        return new InvalidHostCertificateFault();
    }

    /**
     * Create an instance of {@link PermissionDeniedFault }
     * 
     */
    public PermissionDeniedFault createPermissionDeniedFault() {
        return new PermissionDeniedFault();
    }

    /**
     * Create an instance of {@link NoSuchUserFault }
     * 
     */
    public NoSuchUserFault createNoSuchUserFault() {
        return new NoSuchUserFault();
    }

    /**
     * Create an instance of {@link InvalidHostCertificateRequestFault }
     * 
     */
    public InvalidHostCertificateRequestFault createInvalidHostCertificateRequestFault() {
        return new InvalidHostCertificateRequestFault();
    }

    /**
     * Create an instance of {@link DorianInternalFault }
     * 
     */
    public DorianInternalFault createDorianInternalFault() {
        return new DorianInternalFault();
    }

    /**
     * Create an instance of {@link InvalidTrustedIdPFault }
     * 
     */
    public InvalidTrustedIdPFault createInvalidTrustedIdPFault() {
        return new InvalidTrustedIdPFault();
    }

    /**
     * Create an instance of {@link AdminAlreadyExists }
     * 
     */
    public AdminAlreadyExists createAdminAlreadyExists() {
        return new AdminAlreadyExists();
    }

    /**
     * Create an instance of {@link InvalidAssertionFault }
     * 
     */
    public InvalidAssertionFault createInvalidAssertionFault() {
        return new InvalidAssertionFault();
    }

    /**
     * Create an instance of {@link InvalidUserPropertyFault }
     * 
     */
    public InvalidUserPropertyFault createInvalidUserPropertyFault() {
        return new InvalidUserPropertyFault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidHostCertificateRequestFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/Dorian/types", name = "InvalidHostCertificateRequestFault")
    public JAXBElement<InvalidHostCertificateRequestFault> createInvalidHostCertificateRequestFault(InvalidHostCertificateRequestFault value) {
        return new JAXBElement<InvalidHostCertificateRequestFault>(_InvalidHostCertificateRequestFault_QNAME, InvalidHostCertificateRequestFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidUserCertificateFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/Dorian/types", name = "InvalidUserCertificateFault")
    public JAXBElement<InvalidUserCertificateFault> createInvalidUserCertificateFault(InvalidUserCertificateFault value) {
        return new JAXBElement<InvalidUserCertificateFault>(_InvalidUserCertificateFault_QNAME, InvalidUserCertificateFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidHostCertificateFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/Dorian/types", name = "InvalidHostCertificateFault")
    public JAXBElement<InvalidHostCertificateFault> createInvalidHostCertificateFault(InvalidHostCertificateFault value) {
        return new JAXBElement<InvalidHostCertificateFault>(_InvalidHostCertificateFault_QNAME, InvalidHostCertificateFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdminAlreadyExists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cagrid.nci.nih.gov/Dorian/types", name = "AdminAlreadyExists")
    public JAXBElement<AdminAlreadyExists> createAdminAlreadyExists(AdminAlreadyExists value) {
        return new JAXBElement<AdminAlreadyExists>(_AdminAlreadyExists_QNAME, AdminAlreadyExists.class, null, value);
    }

}
