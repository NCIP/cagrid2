package org.cagrid.core.resource;

import org.cagrid.wsrf.properties.ResourceKey;
import org.globus.util.I18n;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;

public class SimpleResourceKey implements ResourceKey {

    private static I18n i18n = I18n.getI18n(SimpleResourceKey.class.getName());

    private final Logger logger = LoggerFactory.getLogger(SimpleResourceKey.class.getName());

    private QName name;
    private Object value;

//    public SimpleResourceKey(SOAPElement header, Class type)
//        throws InvalidResourceKeyException {
//        try {
//            this.value = ObjectDeserializer.toObject(header, type);
//        } catch (Exception e) {
//            throw new InvalidResourceKeyException(e);
//        }
//        this.name = new QName(header.getElementName().getURI(),
//                              header.getElementName().getLocalName());
//    }
    
    /**
     * Creates a new SimpleResourceKey.
     *
     * @param name the name of the key
     * @param value the value of the key. The value of the key can be any 
     *        simple/primitive type or any WSDL2Java generated type or any
     *        other type with proper type mappings.
     */
    public SimpleResourceKey(QName name, Object value) {
        this.name = name;
        this.value = value;
    }
    
    public QName getName() {
        return this.name;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public SOAPElement toSOAPElement() {
            //TODO
        throw new RuntimeException("toSOAPElement not implemented");
    }

    public String toString() {
        return this.name  + "=" + this.value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SimpleResourceKey)) {
            return false;
        }
        
        SimpleResourceKey otherKey = (SimpleResourceKey) obj;
        
        if (!this.name.equals(otherKey.name)) {
            return false;
        }
        
        if (!this.value.equals(otherKey.value)) {
            return false;
        }
        
        return true;
    }
    
    public int hashCode() {
        return this.name.hashCode() + this.value.hashCode();
    }

}
