
package org.cagrid.gaards.authentication.lockout;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cagrid.gaards.authentication.lockout package. 
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

    private final static QName _LockedUserInfo_QNAME = new QName("http://gaards.cagrid.org/authentication/lockout", "LockedUserInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cagrid.gaards.authentication.lockout
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LockedUserInfo }
     * 
     */
    public LockedUserInfo createLockedUserInfo() {
        return new LockedUserInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LockedUserInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://gaards.cagrid.org/authentication/lockout", name = "LockedUserInfo")
    public JAXBElement<LockedUserInfo> createLockedUserInfo(LockedUserInfo value) {
        return new JAXBElement<LockedUserInfo>(_LockedUserInfo_QNAME, LockedUserInfo.class, null, value);
    }

}
