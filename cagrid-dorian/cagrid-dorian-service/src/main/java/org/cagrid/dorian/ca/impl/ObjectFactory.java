
package org.cagrid.dorian.ca.impl;

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

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.dorian.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CertificateAuthorityFault }
     * 
     */
    public CertificateAuthorityFault createCertificateAuthorityFault() {
        return new CertificateAuthorityFault();
    }

    /**
     * Create an instance of {@link InvalidPasswordFault }
     * 
     */
    public InvalidPasswordFault createInvalidPasswordFault() {
        return new InvalidPasswordFault();
    }

    /**
     * Create an instance of {@link NoCACredentialsFault }
     * 
     */
    public NoCACredentialsFault createNoCACredentialsFault() {
        return new NoCACredentialsFault();
    }

}
