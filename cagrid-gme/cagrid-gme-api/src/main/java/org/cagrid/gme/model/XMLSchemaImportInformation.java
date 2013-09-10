
package org.cagrid.gme.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Contains information about the imports of a particular XMLSchema, identified
 * by it's targetNamespace (represented by an XMLSchemaNamespace). NOTE: the
 * hashcode of this Class only considers the targetNamespace, so one should not
 * put multiple instances of this Class, referring to the same XMLSchema but
 * with different imports, in a Set.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLSchemaImportInformation", propOrder = {
    "xmlSchemaNamespace",
    "imports"
})
public class XMLSchemaImportInformation
    implements Serializable
{

    @XmlElement(name = "XMLSchemaNamespace", required = true)
    protected XMLSchemaNamespace xmlSchemaNamespace;
    protected XMLSchemaImportInformation.Imports imports;

    /**
     * Gets the value of the xmlSchemaNamespace property.
     * 
     * @return
     *     possible object is
     *     {@link XMLSchemaNamespace }
     *     
     */
    public XMLSchemaNamespace getXMLSchemaNamespace() {
        return xmlSchemaNamespace;
    }

    /**
     * Sets the value of the xmlSchemaNamespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLSchemaNamespace }
     *     
     */
    public void setXMLSchemaNamespace(XMLSchemaNamespace value) {
        this.xmlSchemaNamespace = value;
    }

    /**
     * Gets the value of the imports property.
     * 
     * @return
     *     possible object is
     *     {@link XMLSchemaImportInformation.Imports }
     *     
     */
    public XMLSchemaImportInformation.Imports getImports() {
    	if (this.imports == null) {
    		this.imports = new Imports();
    	}
        return this.imports;
    }

    /**
     * Sets the value of the imports property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLSchemaImportInformation.Imports }
     *     
     */
    public void setImports(XMLSchemaImportInformation.Imports value) {
    	if (value == null) {
    		this.imports = new Imports();
    	}
    	else {
    		this.imports = value;
    	}
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{gme://gme.cagrid.org/2.0/GlobalModelExchange/domain}XMLSchemaNamespace" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "xmlSchemaNamespace"
    })
    public static class Imports
        implements Serializable
    {

        @XmlElement(name = "XMLSchemaNamespace", required = true)
        protected List<XMLSchemaNamespace> xmlSchemaNamespace;

        /**
         * Gets the value of the xmlSchemaNamespace property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the xmlSchemaNamespace property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getXMLSchemaNamespace().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link XMLSchemaNamespace }
         * 
         * 
         */
        public List<XMLSchemaNamespace> getXMLSchemaNamespace() {
            if (xmlSchemaNamespace == null) {
                xmlSchemaNamespace = new ArrayList<XMLSchemaNamespace>();
            }
            return this.xmlSchemaNamespace;
        }

    }

    /**
     * Only considers the xmlSchemaNamespace, so one should not put multiple
     * instances of this Class, referring to the same XMLSchema but with
     * different imports, in a Set.
     */
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((xmlSchemaNamespace == null) ? 0 : xmlSchemaNamespace
						.hashCode());
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
		XMLSchemaImportInformation other = (XMLSchemaImportInformation) obj;
		if (imports == null) {
			if (other.imports != null)
				return false;
		} else if (!imports.equals(other.imports))
			return false;
		if (xmlSchemaNamespace == null) {
			if (other.xmlSchemaNamespace != null)
				return false;
		} else if (!xmlSchemaNamespace.equals(other.xmlSchemaNamespace))
			return false;
		return true;
	}



}
