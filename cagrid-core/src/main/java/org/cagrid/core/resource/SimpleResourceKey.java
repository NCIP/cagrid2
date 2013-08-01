package org.cagrid.core.resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;

import org.cagrid.core.common.JAXBUtils;
import org.cagrid.wsrf.properties.ResourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleResourceKey implements ResourceKey {

    private final Logger logger = LoggerFactory.getLogger(SimpleResourceKey.class.getName());

    private QName name;
    private Object value;

    // public SimpleResourceKey(SOAPElement header, Class type)
    // throws InvalidResourceKeyException {
    // try {
    // this.value = ObjectDeserializer.toObject(header, type);
    // } catch (Exception e) {
    // throw new InvalidResourceKeyException(e);
    // }
    // this.name = new QName(header.getElementName().getURI(),
    // header.getElementName().getLocalName());
    // }

    /**
     * Creates a new SimpleResourceKey.
     * 
     * @param name
     *            the name of the key
     * @param value
     *            the value of the key. The value of the key can be any simple/primitive type or any WSDL2Java generated type or any other type with proper type
     *            mappings.
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
        SOAPElement element = null;
        try {
            JAXBContext jc1 = JAXBUtils.getJAXBContext(getValue().getClass());
            Marshaller marshaller = jc1.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // tell JAXB to marshal directly into an element
            element = SOAPFactory.newInstance().createElement("parent");
            // i had to do this mojo to get it to serialize the "type" into an element of the name's QName
            // if you just marshall the getValue(), you'll get errors about it not being a root element (unless it is), or you'll get an extra element for the
            // getValue() itself, instead of it's "type" information being serialized into an element of the name's QName
            JAXBElement<?> f = new JAXBElement(getName(), getValue().getClass(), getValue());
            marshaller.marshal(f, element);
        } catch (JAXBException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        } catch (SOAPException e) {
            logger.error(e.getMessage(), e);
        }

        return (SOAPElement)element.getChildElements().next() ;
    }

    public String toString() {
        return this.name + "=" + this.value;
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
