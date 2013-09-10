
package org.cagrid.gme.model;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Type;
import org.w3._2001.xmlschema.Adapter1;


/**
 * <p>Java class for XMLSchemaNamespace complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="XMLSchemaNamespace">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="uri" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLSchemaNamespace")
@Embeddable
public class XMLSchemaNamespace
    implements Serializable
{

    @XmlAttribute(name = "uri")
    @XmlJavaTypeAdapter(Adapter1.class)
    @XmlSchemaType(name = "anyURI")
    @Column(nullable = false, unique = true)
    @Type(type = "org.cagrid.gme.model.URIUserType")
    protected URI uri;

    public XMLSchemaNamespace(URI targetNamespace) {
		this.uri = targetNamespace;
	}

	public XMLSchemaNamespace() {
		// TODO Auto-generated constructor stub
	}

	public XMLSchemaNamespace(String string) throws URISyntaxException {
		this.uri = new URI(string);
	}

	/**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public URI getUri() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUri(URI value) {
        this.uri = value;
    }

	public URI getURI() {
		return this.uri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XMLSchemaNamespace other = (XMLSchemaNamespace) obj;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}

}
