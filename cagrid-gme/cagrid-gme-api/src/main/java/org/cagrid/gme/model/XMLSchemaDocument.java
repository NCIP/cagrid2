
package org.cagrid.gme.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for XMLSchemaDocument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="XMLSchemaDocument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="schemaText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="systemID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLSchemaDocument", propOrder = {
    "schemaText"
})
@Embeddable
public class XMLSchemaDocument
    implements Serializable
{

    @XmlElement(required = true)
    // TODO: I would like for this to be non nullable, but then hibernate makes
    // it a primary key, which doesn't work when it is an unbounded size CLOB
    // @Column(nullable = false)
    @Lob
    @Column(length=16777215)
    protected String schemaText;
    // TODO: is there a way to check unique=true within the containing schema?
    // right now if you pass the same system id into the set, one will replace
    // the other
    @XmlAttribute(name = "systemID")
    @Column(nullable = false)
    protected String systemID;

    /**
     * Gets the value of the schemaText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchemaText() {
        return schemaText;
    }

    /**
     * Sets the value of the schemaText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchemaText(String value) {
        this.schemaText = value;
    }

    /**
     * Gets the value of the systemID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemID() {
        return systemID;
    }

    /**
     * Sets the value of the systemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemID(String value) {
        this.systemID = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((schemaText == null) ? 0 : schemaText.hashCode());
		result = prime * result
				+ ((systemID == null) ? 0 : systemID.hashCode());
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
		XMLSchemaDocument other = (XMLSchemaDocument) obj;
		if (schemaText == null) {
			if (other.schemaText != null)
				return false;
		} else if (!schemaText.equals(other.schemaText))
			return false;
		if (systemID == null) {
			if (other.systemID != null)
				return false;
		} else if (!systemID.equals(other.systemID))
			return false;
		return true;
	}

}
